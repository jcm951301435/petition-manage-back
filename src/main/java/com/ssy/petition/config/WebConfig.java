package com.ssy.petition.config;

import com.ssy.petition.config.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

/**
 * @author jcm
 */
@Configuration
public class WebConfig {

    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Autowired
    public WebConfig(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    /**
     * 日期格式化
     */
    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter
                .getWebBindingInitializer();
        if (initializer != null && initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }
    }

    //@Bean
    //public MultipartConfigElement multipartConfigElement() {
    //    MultipartConfigFactory factory = new MultipartConfigFactory();
    //    factory.setMaxFileSize(DataSize.ofGigabytes(1));
    //    factory.setMaxRequestSize(DataSize.ofMegabytes(100));
    //    return factory.createMultipartConfig();
    //}

    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }

}
