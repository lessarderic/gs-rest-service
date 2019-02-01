#!/to/prevent/spring-boot-cli/detection
package spring

import hello.Application
import hello.ImmutableGreeting
import hello.endpoint.GreetingController
import hello.service.Greeting
import hello.service.GreetingServiceImpl

import java.util.function.Supplier

beans {
    app(Application)

    Supplier<Greeting> greetingSupplier = {
        ImmutableGreeting.builder()
    }

    greetingService(GreetingServiceImpl, greetingSupplier)
    greetingController(GreetingController, greetingService)
}
