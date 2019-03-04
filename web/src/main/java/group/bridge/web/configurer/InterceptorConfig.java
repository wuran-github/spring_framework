package group.bridge.web.configurer;

import group.bridge.web.interceptor.ApiInterceptor;
import group.bridge.web.interceptor.PageInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/web/为前缀 url路径
        registry.addInterceptor(new PageInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/login").
                excludePathPatterns("/logout").
                excludePathPatterns("/api/**").
                excludePathPatterns("/**/*.jpg").
                excludePathPatterns("/**/*.css").
                excludePathPatterns("/**/*.js").
                excludePathPatterns("/**/*.png");
        //拦截api
        registry.addInterceptor(new ApiInterceptor()).addPathPatterns("/api/**");
    }

}
