package Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitindo CORS em todas as URLs
                .allowedOrigins("*") // Permitindo solicitações de todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitindo os métodos HTTP especificados
                .allowedHeaders("*"); // Permitindo todos os cabeçalhos
    }
}
