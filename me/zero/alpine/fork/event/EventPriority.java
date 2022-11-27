package me.zero.alpine.fork.event;

public interface EventPriority {
  public static final int DEFAULT;
  
  public static final int HIGH;
  
  public static final int LOWEST;
  
  public static final int LOW = -100;
  
  public static final int MEDIUM = 0;
  
  public static final int HIGHEST;
  
  static {
    HIGH = 100;
    LOWEST = -200;
    DEFAULT = 0;
    HIGHEST = 200;
  }
}
