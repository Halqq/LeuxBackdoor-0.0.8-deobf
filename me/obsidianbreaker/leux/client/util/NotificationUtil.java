package me.obsidianbreaker.leux.client.util;

import give up;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.Leux;

public class NotificationUtil {
  public static ArrayList active_notifications = new ArrayList();
  
  public static void update() {
    (give up)null;
    active_notifications.removeIf(NotificationUtil::lambda$update$0);
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "MaxNotifications").get_value(1);
    if (i < active_notifications.size())
      active_notifications.remove(i - 1); 
  }
  
  public static void send_notification(Notification paramNotification) {
    (give up)null;
    active_notifications.add(0, paramNotification);
  }
  
  public static boolean lambda$update$0(Notification paramNotification) {
    (give up)null;
    return (System.currentTimeMillis() - paramNotification.getTimeCreated() > (-1046371691L ^ 0xFFFFFFFFC1A1B11DL));
  }
  
  public static ArrayList get_notifications() {
    (give up)null;
    return active_notifications;
  }
}
