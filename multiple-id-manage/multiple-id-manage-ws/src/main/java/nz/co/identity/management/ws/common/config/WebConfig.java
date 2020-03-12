package nz.co.identity.management.ws.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebConfig
 *
 * @since Staveware Core Ver.5.3
 */
@Configuration
public class WebConfig {

    /**
     * cors.origins.allowed
     *
     * @since Staveware Core Ver.5.3
     */
    @Value("${cors.origins.allowed:*}")
    private String[] allowedOrigins;

    /**
     * corsConfigurer
     *
     * @return WebMvcConfigurer
     * @since Staveware Core Ver.5.3
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(allowedOrigins)
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }

}