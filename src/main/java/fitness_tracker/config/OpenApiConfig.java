package fitness_tracker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This file is like a digital business card for your project.
 * It automatically builds a "Help & Info" webpage so people
 * know what your app is and how to contact you.
 */
@Configuration
public class OpenApiConfig {

    /**
     * This section builds the actual information card.
     */
    @Bean
    public OpenAPI customAPI() {

        return new OpenAPI()
                .info(new Info()
                        /** The main name of your project */
                        .title("Fitness Tracking API")

                        /** The version number of your project */
                        .version("v1.0")

                        /** A short description of what this project is */
                        .description("Production Grade API's")

                        /** Your contact details: name, website, and email */
                        .contact(new Contact()
                                .name("NabinStriveX")
                                .url("https://nabin-oli.com.np")
                                .email("olinabin214@gmail.com")
                        )

                        /** The legal rule that allows others to safely use this code */
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://nabin-oli.com.np")
                        )
                );
    }
}