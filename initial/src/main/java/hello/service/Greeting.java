package hello.service;

import org.immutables.value.Value;

@Value.Immutable
public interface Greeting {
  long getId();

  String getContent();
}
