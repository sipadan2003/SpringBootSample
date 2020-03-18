package tv.minato.sbs.core;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private static final Logger LOG = Logger.getLogger(WebMvcConfig.class.getName());

	@Autowired
	private HandlerInterceptor sampleInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LOG.info(()->"addInterceptors()");
		registry.addInterceptor(sampleInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/manage/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/WEB-INF/resources/");
//			.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/WEB-INF/resources/webjars/");
	}
}
