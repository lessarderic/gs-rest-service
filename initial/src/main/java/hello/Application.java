package hello;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Application {

  public static void main(String[] args) {
    mainJava(args);
  }

  private static void mainJava(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  private static void mainGroovy(String[] args) {
    SpringApplication application = new SpringApplication();
    application.setSources(Collections.singleton("spring/ApplicationContext.groovy"));
    application.run(args);
  }
}
