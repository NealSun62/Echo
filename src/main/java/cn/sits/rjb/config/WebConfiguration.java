package cn.sits.rjb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/***
 * 拦截器配置
 *
 * @author louis.lu
 * @date 2018 年6月7日
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfiguration.class);
//    @Autowired
//    TokenManager tokenManager;
//
//    @Autowired
//    IOperationManager operationManager;

    @Value("${swagger.show}")
    private boolean swaggerShow;

//    @Bean
//    public AuthenticationInterceptor getAuthenticationInterceptor() {
//        LOGGER.debug("111进入AuthenticationInterceptor......");
//        AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptor();
//        authenticationInterceptor.setTokenManager(tokenManager);
//        return authenticationInterceptor;
//    }

//    @Bean
//    public SecurityInterceptor getSecurityInterceptor() {
//    	LOGGER.debug("进入SecurityInterceptor......");
//        SecurityInterceptor securityInterceptor = new SecurityInterceptor();
//        securityInterceptor.setOperationManager(operationManager);
//        return securityInterceptor;
//    }

    /**
     * 添加拦截器（名称必须是addInterceptors）
     *
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置token拦截器（拦截是否登录）
//        InterceptorRegistration authInterception = registry.addInterceptor(getAuthenticationInterceptor()).addPathPatterns("/**");
//        authInterception.excludePathPatterns("/submitlogin/**","/logout/**","/getvalidatecode/**")
//                        .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");;
                        
//        InterceptorRegistration securityInterceptor = registry.addInterceptor(getSecurityInterceptor()).addPathPatterns("/**");
//        securityInterceptor.excludePathPatterns("/submitlogin/**","/logout/**","/getvalidatecode/**")
//        				.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");;

    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (this.swaggerShow) {
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        DomainFilter domainFilter = new DomainFilter();
//        registrationBean.setFilter(domainFilter);
//        List<String> urlPatterns = new ArrayList<String>();
//        urlPatterns.add("/*");
//        registrationBean.setUrlPatterns(urlPatterns);
//        return registrationBean;
//    }
}
