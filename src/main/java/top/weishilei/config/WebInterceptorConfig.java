package top.weishilei.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.weishilei.Interceptor.LoginInterceptor;

@Configuration
public class WebInterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/uditImage");
        loginRegistry.addPathPatterns("/record/add");
        loginRegistry.addPathPatterns("/user/image");
        loginRegistry.addPathPatterns("/record/user");
        loginRegistry.addPathPatterns("/record/del");

    }

}
