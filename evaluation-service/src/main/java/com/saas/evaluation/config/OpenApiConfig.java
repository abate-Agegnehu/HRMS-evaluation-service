package com.saas.evaluation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Value("${keycloak.openIdConnectUrl}")
        private String keycloakOpenIdConnectUrl;

        @Value("${ip-address}")
        private String ipAddress;

        @Bean
        public OpenAPI customOpenAPI() {

                String ipServer = "http://" + ipAddress + ":8000";
                String localServer = "http://localhost:8190";

                return new OpenAPI()
                                .info(new Info()
                                                .title("Evaluation Service APIs")
                                                .version("1.0")
                                                .description("SaaS ERP Evaluation Service APIs")
                                                .termsOfService("Terms of service")
                                                .contact(new Contact()
                                                                .name("Owner name")
                                                                .email("Owner email address")
                                                                .url("Owner website url"))
                                                .license(new License().name("License name").url("License url")))
                                .addServersItem(new Server().url(ipServer).description("Remote ENV"))
                                .addServersItem(new Server().url(localServer).description("Local ENV"))
                                .addSecurityItem(new SecurityRequirement().addList("Keycloak"))
                                .schemaRequirement(
                                                "Keycloak",
                                                new SecurityScheme()
                                                                .type(SecurityScheme.Type.OPENIDCONNECT)
                                                                .scheme("bearer")
                                                                .bearerFormat("JWT")
                                                                .in(SecurityScheme.In.HEADER)
                                                                .openIdConnectUrl(keycloakOpenIdConnectUrl));
        }
}
