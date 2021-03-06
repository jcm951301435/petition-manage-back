package com.ssy.petition.config.page;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

//@Configuration
//@ConditionalOnBean(SqlSessionFactory.class)
//@EnableConfigurationProperties(PageHelperProperties.class)
//@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class PageHelperConfiguration {

    private final List<SqlSessionFactory> sqlSessionFactoryList;

    private PageHelperProperties properties;

    @Autowired
    public PageHelperConfiguration(List<SqlSessionFactory> sqlSessionFactoryList) {
        this.sqlSessionFactoryList = sqlSessionFactoryList;
    }

    /**
     * 接受分页插件额外的属性
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = PageHelperProperties.PAGE_HELPER_PREFIX)
    public Properties pageHelperProperties() {
        return new Properties();
    }

    @PostConstruct
    public void addPageInterceptor() {
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //先把一般方式配置的属性放进去
        properties.putAll(pageHelperProperties());
        //在把特殊配置放进去，由于close-conn 利用上面方式时，属性名就是 close-conn 而不是 closeConn，所以需要额外的一步
        //properties.putAll(this.properties.getProperties());
        interceptor.setProperties(properties);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }
}
