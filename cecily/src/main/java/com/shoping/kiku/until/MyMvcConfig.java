package com.shoping.kiku.until;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new LoginHandLerInterCeptor())
				.addPathPatterns("/shopping/center", "/shopping/favorite", "/shopping/myCart")
				.excludePathPatterns(
						"../css/**",
						"../img/**",
						"../js/**",
						"/searchKeyword/**",
						"/search/**");

	}

//	//paging
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//
//		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
//		// 1ページに表示する最大件数(10件)を設定する
//		resolver.setMaxPageSize(10);
//		argumentResolvers.add(resolver);
//	}

}