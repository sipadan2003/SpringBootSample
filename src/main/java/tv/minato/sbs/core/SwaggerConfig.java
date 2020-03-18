package tv.minato.sbs.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tv.minato.sbs.Util;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {
	private static final String DOMAIN_NAME = "example.com";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant(Util.API1_PATH+"/**"))
				.build()
				.useDefaultResponseMessages(false);
	}
	
	private static ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("sbs - Spring Boot Sample 0.0.1")
				.description("APIs for sbs")
				.termsOfServiceUrl("http://"+DOMAIN_NAME+"/sbs")
				.contact(new Contact("Example LIMITED", "http://"+DOMAIN_NAME+"/contact", "contact@"+DOMAIN_NAME))
				.license("(No License)")
				.licenseUrl("http://"+DOMAIN_NAME+"/licenses/none")
				.version("0.0.1")
				.build();
	}
}
