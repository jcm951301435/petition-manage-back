package com.ssy.petition.config;

//@Configuration
//public class WebMvcConfig extends WebMvcConfigurationSupport {

/**
 * 该配置原为解决 vue 发布到 static 目录中时 vue 访问路径问题
 * 若此处继承自 WebMvcConfigurationSupport，则spring boot 会不启用 WebMvcConfigurationSupport 默认配置，导致 json 等配置需重新配置
 */
@Deprecated
public class WebMvcConfig {
    //@Override
    //protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    //    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    //}

}
