package com.bitsndbytes.product;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Locale;

@OpenAPIDefinition(
		info = @Info(
				title = "Product Service API",
				version = "v1.0",
				description = "This API manages products and categories",
				contact = @Contact(
						name = "Omkar Sathe",
						email = "omkar@gmail.com",
						url = "https://company.com"
				),
				license = @License(name = "MIT", url = "https://opensource.org/licenses/MIT")
		),
		externalDocs = @ExternalDocumentation(
				description = "SharePoint / Confluence API Docs",
				url = "https://example.com"
		),
		servers = {
				@Server(url = "http://localhost:8080", description = "Local Dev Server"),
				@Server(url = "https://api.company.com", description = "Production Server")
		}
)

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ProductApplication.class, args);
	}
}



