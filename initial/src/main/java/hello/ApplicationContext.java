package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {
  @Bean
  public Application application() {
    return new Application();
  }

  @Bean
  public GreetingService greetingService() {
    return new GreetingServiceImpl(ImmutableGreeting::builder);
  }

  @Bean
  public GreetingController greetingController() {
    return new GreetingController(greetingService());
  }
}
