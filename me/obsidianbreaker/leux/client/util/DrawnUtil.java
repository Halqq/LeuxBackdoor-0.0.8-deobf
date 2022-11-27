package me.obsidianbreaker.leux.client.util;

import give up;
import java.util.ArrayList;
import java.util.List;

public class DrawnUtil {
  public static List hidden_tags = new ArrayList();
  
  public static void add_remove_item(String paramString) {
    (give up)null;
    paramString = paramString.toLowerCase();
    if (hidden_tags.contains(paramString)) {
      MessageUtil.send_client_message("Added " + paramString);
      hidden_tags.remove(paramString);
    } else {
      MessageUtil.send_client_message("Removed " + paramString);
      hidden_tags.add(paramString);
    } 
  }
}
