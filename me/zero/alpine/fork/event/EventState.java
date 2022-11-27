package me.zero.alpine.fork.event;

import give up;

public enum EventState {
  PRE, POST;
  
  public static EventState[] $VALUES;
  
  static {
    $VALUES = new EventState[] { PRE, POST };
  }
}
