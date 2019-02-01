package hello.service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

public class GreetingServiceImpl implements GreetingService {
  private Logger LOGGER = LoggerFactory.getLogger(GreetingServiceImpl.class);

  private static final String template = "Hello, %s!";

  private final AtomicLong counter = new AtomicLong();

  private final Counter greetingsCounter;

  private final Timer greetingsTimer;

  private final Supplier<ImmutableGreeting.Builder> builder;

  public GreetingServiceImpl(Supplier<ImmutableGreeting.Builder> builder, MeterRegistry registry) {
    this.builder = builder;
    this.greetingsCounter = registry.counter("greetings.count");
    this.greetingsTimer = registry.timer("greetings.time");
  }

  @Override
  public Greeting greet(String name) {
    LOGGER.info("##### greet(\"{}\") called!", name);
    greetingsCounter.increment();

    return greetingsTimer.record(
        () ->
            builder
                .get()
                .id(counter.incrementAndGet())
                .content(String.format(template, name))
                .build());
  }
}
