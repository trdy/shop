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
 *
 * spring boot 配置类
 */
@Configuration
@Component
public class WebAppConfig extends WebMvcConfigurerAdapter{

    private Logger log=LoggerFactory.getLogger(WebAppConfig.class);

    /** jar包以外文件资源路径设置 */
    @Value("${img.upload.path}")
    private String uploadPath;

    /**
     * 增加外部文件系统路径到上下文环境
     * @param registry 配置注册
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("imagesPath="+uploadPath);
        registry.addResourceHandler("/upload/**").addResourceLocations(uploadPath);
        super.addResourceHandlers(registry);
    }


    /**
     * 跨域路由配置
     * @param registry 配置注册
     *
     *
     registry.addMapping("/api/**")
    .allowedOrigins("http://domain2.com")//本次请求来自哪个源（协议 + 域名 + 端口）。服务器根据这个值，决定是否同意这次请求,该字段是必须的。它的值要么是请求时Origin字段的值，要么是一个*，表示接受任意域名的请求。
    .allowedMethods("PUT", "DELETE") //允许请求method参数，put，gelete不支持跨域请求
    .allowedHeaders("header1", "header2", "header3")
    .exposedHeaders("header1", "header2")//该字段可选。CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定。上面的例子指定，getResponseHeader('FooBar')可以返回FooBar字段的值。
    .allowCredentials(false)//表示是否允许发送Cookie
    .maxAge(3600);
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ALL)
                .allowedMethods("GET", "POST")
                .allowedHeaders(ALL)
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
                .excludePathPatterns("/test/**")
                .addPathPatterns("/admin/**");
        super.addInterceptors(registry);
    }
}
