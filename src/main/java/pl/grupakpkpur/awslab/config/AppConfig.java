package pl.grupakpkpur.awslab.config;

import static software.amazon.awssdk.regions.Region.US_EAST_1;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
@EnableDynamoDBRepositories(basePackages = "pl.grupakpkpur.awslab.repository")
public class AppConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:4200");
  }

  @Bean
  public S3Presigner s3Presigner() {
    return S3Presigner.builder().region(US_EAST_1).build();
  }

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
  }
}
