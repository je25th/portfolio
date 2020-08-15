package com.projects.je25th.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.projects.je25th.UfMm.service.AuthCheckInterceptor;

@Configuration//설정 파일임을 알림
@EnableWebMvc
@ComponentScan(basePackages = { "com.projects.je25th.UfMm.controller" })//베이스 패키지에서 컨트롤러 찾을것
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//불러올 폴더 설정
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
 
    //default servlet handler 활성화 : 맵핑 정보가 없는 url 요청(/뒤에 아무것도 없는거)이 들어왔을 때 톰캣에게 맡김
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
   
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
    	//System.out.println("addViewControllers가 호출됩니다. ");
        //registry.addViewController("/UnfoldedMemo/loginSuccess").setViewName("UfMm/loginSuccess");
    }
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
    	//불러올 view의 경로 설정
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public AuthCheckInterceptor authCheckInterceptor() {
    	return new AuthCheckInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(authCheckInterceptor())
    		.addPathPatterns("/unfolded-memo/**")
    		.excludePathPatterns("/unfolded-memo/login");
    }
    
}
