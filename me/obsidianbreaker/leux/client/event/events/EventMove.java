package me.obsidianbreaker.leux.client.event.events;

import give up;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.entity.MoverType;

public class EventMove extends EventCancellable {
  public double z;
  
  public double y;
  
  public double x;
  
  public MoverType move_type;
  
  public void set_x(double paramDouble) {
    (give up)null;
    this.x = paramDouble;
  }
  
  public double get_y() {
    (give up)null;
    return this.y;
  }
  
  public void set_move_type(MoverType paramMoverType) {
    (give up)null;
    this.move_type = paramMoverType;
  }
  
  public void set_z(double paramDouble) {
    (give up)null;
    this.z = paramDouble;
  }
  
  public EventMove(MoverType paramMoverType, double paramDouble1, double paramDouble2, double paramDouble3) {
    this.move_type = paramMoverType;
    this.x = paramDouble1;
    this.y = paramDouble2;
    this.z = paramDouble3;
  }
  
  public MoverType get_move_type() {
    (give up)null;
    return this.move_type;
  }
  
  public double get_x() {
    (give up)null;
    return this.x;
  }
  
  public double get_z() {
    (give up)null;
    return this.z;
  }
  
  public void set_y(double paramDouble) {
    (give up)null;
    this.y = paramDouble;
  }
}
