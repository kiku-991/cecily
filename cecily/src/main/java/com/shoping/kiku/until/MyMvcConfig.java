package com.shoping.kiku.until;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new LoginHandLerInterCeptor())
				.addPathPatterns("/shopping/center","/shopping/favorite","/shopping/myCart")
				.excludePathPatterns(
						"../css/**",
						"../img/**",
						"../js/**"
						);

	}
}