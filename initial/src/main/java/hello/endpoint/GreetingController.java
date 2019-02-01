package hello.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.service.Greeting;
import hello.service.GreetingService;

/** Controller class for Spring Web implementation. */
@RestController
public class GreetingController {

  private final GreetingService greetingService;

  public GreetingController(GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return greetingService.greet(name);
  }
}
