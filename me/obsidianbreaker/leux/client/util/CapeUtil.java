package me.obsidianbreaker.leux.client.util;

import give up;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

public class CapeUtil {
  public static ArrayList final_uuid_list = get_uuids();
  
  public static boolean is_uuid_valid(UUID paramUUID) {
    (give up)null;
    for (String str : Objects.<ArrayList>requireNonNull(final_uuid_list)) {
      if (str.equals(paramUUID.toString()))
        return true; 
    } 
    return false;
  }
  
  public static ArrayList get_uuids() {
    (give up)null;
    URL uRL = new URL(new String(Base64.getDecoder().decode("aHR0cHM6Ly9wYXN0ZWJpbi5jb20vcmF3L2FxY3lVQnNL".getBytes())));
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRL.openStream()));
    ArrayList<String> arrayList = new ArrayList();
    String str;
    while ((str = bufferedReader.readLine()) != null)
      arrayList.add(str); 
    return arrayList;
  }
}
