package me.obsidianbreaker.leux.client.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.util.UUIDTypeAdapter;
import give up;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;

public class EnemyUtil {
  public static ArrayList enemies = new ArrayList();
  
  public static EnemyUtil$Enemy get_enemy_object(String paramString) {
    (give up)null;
    ArrayList<NetworkPlayerInfo> arrayList = new ArrayList(Minecraft.func_71410_x().func_147114_u().func_175106_d());
    NetworkPlayerInfo networkPlayerInfo = arrayList.stream().filter(paramString::lambda$get_enemy_object$1).findFirst().orElse(null);
    String str = request_ids("[\"" + paramString + "\"]");
    if (!str.isEmpty()) {
      JsonElement jsonElement = (new JsonParser()).parse(str);
      if (jsonElement.getAsJsonArray().size() != 0) {
        String str1 = jsonElement.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
        String str2 = jsonElement.getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
        return new EnemyUtil$Enemy(str2, UUIDTypeAdapter.fromString(str1));
      } 
    } 
    return null;
  }
  
  public static String request_ids(String paramString) {
    (give up)null;
    URL uRL = new URL(new String(Base64.getDecoder().decode("aHR0cHM6Ly9hcGkubW9qYW5nLmNvbS9wcm9maWxlcy9taW5lY3JhZnQ=".getBytes())));
    HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
    httpURLConnection.setConnectTimeout(5000);
    httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    httpURLConnection.setDoOutput(true);
    httpURLConnection.setDoInput(true);
    httpURLConnection.setRequestMethod("POST");
    OutputStream outputStream = httpURLConnection.getOutputStream();
    outputStream.write(paramString.getBytes("UTF-8"));
    outputStream.close();
    BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
    String str = convertStreamToString(bufferedInputStream);
    bufferedInputStream.close();
    httpURLConnection.disconnect();
    return str;
  }
  
  public static String convertStreamToString(InputStream paramInputStream) {
    (give up)null;
    Scanner scanner = (new Scanner(paramInputStream)).useDelimiter("\\A");
    return scanner.hasNext() ? scanner.next() : "/";
  }
  
  public static boolean lambda$get_enemy_object$1(String paramString, NetworkPlayerInfo paramNetworkPlayerInfo) {
    (give up)null;
    return paramNetworkPlayerInfo.func_178845_a().getName().equalsIgnoreCase(paramString);
  }
  
  public static boolean lambda$isEnemy$0(String paramString, EnemyUtil$Enemy paramEnemyUtil$Enemy) {
    (give up)null;
    return paramEnemyUtil$Enemy.username.equalsIgnoreCase(paramString);
  }
  
  public static boolean isEnemy(String paramString) {
    (give up)null;
    return enemies.stream().anyMatch(paramString::lambda$isEnemy$0);
  }
}
