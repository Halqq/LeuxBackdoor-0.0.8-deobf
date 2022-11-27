package me.zero.alpine.fork.listener;

@FunctionalInterface
public interface EventHook {
  void invoke(Object paramObject);
}
