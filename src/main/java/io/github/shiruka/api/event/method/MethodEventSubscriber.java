package io.github.shiruka.api.event.method;

import io.github.shiruka.api.event.DispatchOrder;
import io.github.shiruka.api.event.Event;
import io.github.shiruka.api.event.EventExecutor;
import io.github.shiruka.api.event.EventSubscriber;
import io.github.shiruka.api.event.Listener;
import java.lang.reflect.Method;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an implementation for {@link EventSubscriber}.
 */
@ToString
@EqualsAndHashCode
public final class MethodEventSubscriber implements EventSubscriber {

  /**
   * the dispatch order.
   */
  @NotNull
  @Getter
  private final DispatchOrder dispatchOrder;

  /**
   * the event class.
   */
  @NotNull
  private final Class<? extends Event> eventClass;

  /**
   * the executor.
   */
  @NotNull
  private final EventExecutor executor;

  /**
   * the include cancelled.
   */
  @Getter
  private final boolean ignoreCancelled;

  /**
   * the listener.
   */
  @NotNull
  @Getter(AccessLevel.PACKAGE)
  private final Listener listener;

  /**
   * the generic.
   */
  @Nullable
  @Getter
  private final Class<?> type;

  /**
   * ctpr.
   *
   * @param eventClass the event class.
   * @param method the method.
   * @param executor the executor.
   * @param listener the listener.
   * @param dispatchOrder the dispatch order.
   * @param ignoreCancelled the include cancelled.
   */
  MethodEventSubscriber(@NotNull final Class<? extends Event> eventClass, @NotNull final Method method,
                        @NotNull final EventExecutor executor, @NotNull final Listener listener,
                        @NotNull final DispatchOrder dispatchOrder, final boolean ignoreCancelled) {
    this.eventClass = eventClass;
    this.type = method.getParameterTypes()[0];
    this.executor = executor;
    this.listener = listener;
    this.dispatchOrder = dispatchOrder;
    this.ignoreCancelled = ignoreCancelled;
  }

  @Override
  public void invoke(@NotNull final Event event) throws Throwable {
    this.executor.invoke(this.listener, event);
  }
}