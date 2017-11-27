package com.yt.shop.common.config;

import com.yt.shop.common.interceptor.TestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * anthor:liyun
 * create:2017-11-24 10:40
 */
@Configuration
@Component
public class WebAppConfig extends WebMvcConfigurerAdapter{

    private Logger log=LoggerFactory.getLogger(WebAppConfig.class);

    @Value("${img.upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("imagesPath="+uploadPath);
        registry.addResourceHandler("/upload/**").addResourceLocations(uploadPath);
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
