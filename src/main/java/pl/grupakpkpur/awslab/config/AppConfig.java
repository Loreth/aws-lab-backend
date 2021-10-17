package pl.grupakpkpur.awslab.config;

import static software.amazon.awssdk.regions.Region.US_EAST_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class AppConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:4200");
  }

  @Bean
  public S3Presigner s3Presigner() {
    return S3Presigner.builder().region(US_EAST_1).build();
  }
}
