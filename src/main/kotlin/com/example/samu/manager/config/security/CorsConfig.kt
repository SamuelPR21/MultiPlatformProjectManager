import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // Permitir todas las rutas
            .allowedOrigins("*") // Permitir desde cualquier origen
            .allowedMethods("*") // Permitir todos los métodos HTTP
    }
}
