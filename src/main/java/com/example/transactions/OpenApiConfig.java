package com.example.transactions;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type= SecuritySchemeType.HTTP,
        scheme="bearer",
        bearerFormat = "JWT"

)

public class OpenApiConfig {

    public OpenAPI walletApi(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Digital wallet API")
                                .description("Digital wallet backend API using kafka,postgres")
                                .version("1.0")
                                .contact(
                                        new Contact()
                                                .name("preethi")
                                                .email("preethi@gmail.com")

                                )
                );

    }
}
