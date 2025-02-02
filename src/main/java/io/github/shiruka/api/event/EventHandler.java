package io.github.shiruka.api.event;

import io.github.shiruka.api.event.events.Cancellable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.Range;

/**
 * this annotation should be marked on methods that calls when an event comes in.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

  /**
   * should receive events even if they have been {@link Cancellable#cancelled()}.
   *
   * @return {@code true} if the event accepts the event being cancelled.
   */
  boolean acceptsCancelled() default false;

  /**
   * the position of the listener in the dispatch sequence once the event has been fired.
   *
   * @return the event's {@link DispatchOrder}.
   */
  @Range(from = -100, to = 100)
  int priority() default DispatchOrder.MIDDLE;
}
