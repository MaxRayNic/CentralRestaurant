package com.stackroute.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
/**
 * SwaggerConfig class
 * @author 
 *
 */
public class SwaggerConfig {

	@Bean
	/**
	 * restaurantApi method
	 * @return Docket
	 */
	public Docket restaurantApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/api/v1/restaurant.*"))
				.build()
				.apiInfo(metaData());
	}
	/**
	 * metaData method
	 * @return ApiInfo
	 */
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(
				"Spring Boot REST API",
				"Spring Boot REST API for Restaurant Store",
				"1.0",
				"Terms of service",
				new Contact("Garima Vaishnavi",null, "garima.chaudhary@cgi.com"), null, null);
		return apiInfo;
	}
}
