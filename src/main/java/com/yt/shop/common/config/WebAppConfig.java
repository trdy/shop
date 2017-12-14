package com.yt.shop.common.config;

import com.yt.shop.common.interceptor.BackLoginInterceptor;
import com.yt.shop.common.interceptor.OperLogInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * anthor:liyun
 * create:2017-11-24 10:40
 */
@Configuration
@Component
public class WebAppConfig extends WebMvcConfigurerAdapter{

    private Logger log=LoggerFactory.getLogger(WebAppConfig.class);

    /** jar包以外文件资源路径设置 */
    @Value("${img.upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("imagesPath="+uploadPath);
        registry.addResourceHandler("/upload/**").addResourceLocations(uploadPath);
        super.addResourceHandlers(registry);
    }


    /** 跨域路由配置 */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ALL)
                .allowedHeaders(ALL)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Bean
    public OperLogInterceptor operLogInterceptor(){
        return new OperLogInterceptor();
    }

    @Bean
    public BackLoginInterceptor backLoginInterceptor(){
        return new BackLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(operLogInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(backLoginInterceptor())
                .excludePathPatterns("/valiCode")
                .excludePathPatterns("/admin/validUser")
                .addPathPatterns("/admin/**");
        super.addInterceptors(registry);
    }
}
