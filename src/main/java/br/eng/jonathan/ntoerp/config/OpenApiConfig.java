package br.eng.jonathan.ntoerp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.core.DefaultLinkRelationProvider;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("NTO-ERP API")
                        .version("1.0.0")
                        .description("NTO-ERP is a specialized micro-ERP platform tailored for cafes, bistros, and small food service establishments. It offers modular business management solutions including POS, inventory control, and order management.\n\n" +
                                "O NTO-ERP é uma plataforma de micro-ERP especializada para cafés, bistrôs e estabelecimentos de alimentação. Oferece soluções modulares de gestão empresarial, incluindo frente de caixa (PDV), controle de estoque e gestão de pedidos.")
                        .contact(new Contact()
                                .name("Jonathan Nascimento")
                                .url("https://jonathan.eng.br"))
                        .license(new License()
                                .name("Apache 2.0 License")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/v1/**")
                .build();
    }

    @Bean
    public LinkRelationProvider linkRelationProvider() {
        return new DefaultLinkRelationProvider();
    }
}