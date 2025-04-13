package pl.coderslab.finalproject.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.coderslab.finalproject.filter.AuthFilter;

// this sets particular link patterns for a filter
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuthFilter> loggingFilter(){
        FilterRegistrationBean<AuthFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthFilter());
//        registrationBean.addUrlPatterns("/user/*");
//        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }
}
