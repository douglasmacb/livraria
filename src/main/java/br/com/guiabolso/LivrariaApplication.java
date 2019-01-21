package br.com.guiabolso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@SpringBootApplication
@Configuration
@EnableSwagger
@EnableCaching
@EntityScan(basePackages = { "br.com.guiabolso.domain" })
@ComponentScan(basePackages = { "br.com.guiabolso.controllers", "br.com.guiabolso.services" })
public class LivrariaApplication {
	
    @Autowired
    private SpringSwaggerConfig swaggerConfig;

	public static void main(String[] args) {
		new SpringApplicationBuilder(LivrariaApplication.class).run(args);
	}

    @Bean
    public SwaggerSpringMvcPlugin groupOnePlugin() {
       return new SwaggerSpringMvcPlugin(swaggerConfig)
           .apiInfo(apiInfo()) 
           .includePatterns("/books.*?")
           .swaggerGroup("admin");
    }
    
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo( 
              "Book Application",
              "This is a simple application proposed by GuiaBolso to know my developer skills",
              "Free to use and mess around",
              "lorem@ipsum.com",
              "Open Licence",
              "lorem@ipsum.com"
        );
        return apiInfo;
     }
	
}
