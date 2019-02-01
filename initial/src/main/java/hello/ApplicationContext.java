package hello;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import hello.endpoint.GreetingController;
import hello.endpoint.GreetingHandler;
import hello.service.GreetingService;
import hello.service.GreetingServiceImpl;
import hello.service.ImmutableGreeting;
import io.micrometer.core.instrument.Metrics;

@Configuration
// @ConfigurationProperties("vcap.services.")
public class ApplicationContext {
  @Bean
  public Application application() {
    return new Application();
  }

  @Bean
  public GreetingService greetingService() {
    return new GreetingServiceImpl(ImmutableGreeting::builder, Metrics.globalRegistry);
  }

  // Sprint Web
  // @Bean
  public GreetingController greetingController() {
    return new GreetingController(greetingService());
  }

  // Spring Reactive WebFlux
  @Bean
  public GreetingHandler greetingHandler(GreetingService greetingService) {
    return new GreetingHandler(greetingService);
  }

  @Bean
  public RouterFunction<ServerResponse> helloRoute(GreetingHandler greetingHandler) {
    return route(GET("/hello").and(accept(MediaType.TEXT_PLAIN)), greetingHandler::greet);
  }
}
