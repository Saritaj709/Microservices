package com.bridgelabz.microservices.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
	
	/*@Autowired
	NotesInterceptor notesInterceptor;*/
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
	//	registry.addInterceptor(notesInterceptor).addPathPatterns("/api/notes/**","/api/labels/**");
	}
}
