package hello;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class GreetingServiceImpl implements GreetingService {
  private static final String template = "Hello, %s!";

  private final AtomicLong counter = new AtomicLong();

  private final Supplier<ImmutableGreeting.Builder> builder;

  public GreetingServiceImpl(Supplier<ImmutableGreeting.Builder> builder) {
    this.builder = builder;
  }

  @Override
  public Greeting greet(String name) {
    return builder
        .get()
        .id(counter.incrementAndGet())
        .content(String.format(template, name))
        .build();
  }
}
