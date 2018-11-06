package spring

import hello.Application
import hello.Greeting
import hello.GreetingController
import hello.GreetingServiceImpl
import hello.ImmutableGreeting

import java.util.function.Supplier

beans {
    app(Application)

    Supplier<Greeting> greetingSupplier = {
        ImmutableGreeting.builder()
    }

    greetingService(GreetingServiceImpl, greetingSupplier)
    greetingController(GreetingController, greetingService)
}
