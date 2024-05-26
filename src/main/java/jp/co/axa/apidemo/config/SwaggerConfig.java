package jp.co.axa.apidemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class for Swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Creates a Docket object for Swagger API documentation.
     *
     * @return a Docket object configured with Swagger 2
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("jp.co.axa.apidemo.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Returns the API information for Swagger documentation.
     *
     * @return the API information
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DEMO REST API")
                .description("Description of my API demo")
                .version("1.0.0")
                .contact(new Contact("HANFEI CHE", "www.demo.com", "demo@company.com"))
                .license("API License")
                .licenseUrl("API license URL")
                .build();
    }
}
