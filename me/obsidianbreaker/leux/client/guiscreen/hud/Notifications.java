package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import me.obsidianbreaker.leux.client.util.Notification;
import me.obsidianbreaker.leux.client.util.NotificationUtil;

public class Notifications extends Pinnable {
  public void render() {
    (give up)null;
    NotificationUtil.update();
    ArrayList arrayList = NotificationUtil.get_notifications();
    byte b = 0;
    for (Notification notification : arrayList) {
      int i = get(notification.getMessage(), "width") + 25;
      int j = Math.max(i, 125);
      create_rect(get_width() - j, b, j, b + 40, 0, 0, 0, 69);
      create_rect(get_width() - j, b, get_width() - j + 5, b + 40, notification.getR(), notification.getG(), notification.getB(), 255);
      create_line(notification.getMessage(), get_width() - j + 10, b + (42 - get(notification.getMessage(), "height")) / 2);
      b += 42;
    } 
  }
  
  public Notifications() {
    super("Notifications", "notifications", Float.intBitsToFloat(1.0926889E9F ^ 0x7EA11BF7), 0, 0);
    set_width(125);
    set_height(42);
  }
}
