package com.ssy.petition.config;

import com.ssy.petition.config.handler.ResultAccessDeniedHandler;
import com.ssy.petition.config.handler.ResultAuthenticationEntryPoint;
import com.ssy.petition.config.service.SelfUserDetailsServiceImpl;
import com.ssy.petition.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 配置
 *
 * @author jcm
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ResultAccessDeniedHandler resultAccessDeniedHandler;

    private final ResultAuthenticationEntryPoint resultAuthenticationEntryPoint;

    private final SysUserService sysUserService;


    @Autowired
    public SecurityConfig(ResultAccessDeniedHandler resultAccessDeniedHandler,
                          ResultAuthenticationEntryPoint resultAuthenticationEntryPoint,
                          SysUserService sysUserService) {
        this.resultAccessDeniedHandler = resultAccessDeniedHandler;
        this.resultAuthenticationEntryPoint = resultAuthenticationEntryPoint;
        this.sysUserService = sysUserService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/*.html", "/favicon.ico", "/v2/api-docs/**", "/**/*.css", "/**/*.js", "/swagger-resources/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/sysUser/login").permitAll()
                .antMatchers("/sysUser/test").hasRole("admin")
                .anyRequest().authenticated();
        http.httpBasic().disable();
        http.formLogin().disable();
        http.logout().disable();
        http.csrf().disable();
        http.exceptionHandling()
                .accessDeniedHandler(resultAccessDeniedHandler)
                .authenticationEntryPoint(resultAuthenticationEntryPoint);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new SelfUserDetailsServiceImpl(sysUserService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
