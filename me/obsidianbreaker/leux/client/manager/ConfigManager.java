package me.obsidianbreaker.leux.client.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import give up;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.components.Frame;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.modules.combat.CrystalAuraNew;
import me.obsidianbreaker.leux.client.util.BurrowUtil;
import me.obsidianbreaker.leux.client.util.ClientNameUtil;
import me.obsidianbreaker.leux.client.util.DrawnUtil;
import me.obsidianbreaker.leux.client.util.EnemyUtil;
import me.obsidianbreaker.leux.client.util.EzMessageUtil;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import org.apache.commons.codec.digest.DigestUtils;

public class ConfigManager {
  public Path CURRENT_CONFIG_PATH = Paths.get(this.CURRENT_CONFIG_DIR, new String[0]);
  
  public String CLIENT_DIR = "LeuxBackdoor/client.json";
  
  public Path BINDS_PATH = Paths.get(this.BINDS_DIR, new String[0]);
  
  public String DRAWN_DIR = "LeuxBackdoor/drawn.json";
  
  public String CLIENT_NAME_DIR = "LeuxBackdoor/clientname.json";
  
  public String BINDS_DIR = this.CURRENT_CONFIG_DIR + "binds.json";
  
  public String FRIENDS_DIR = "LeuxBackdoor/friends.json";
  
  public String EZ_FILE = "ez.json";
  
  public String HUD_FILE = "hud.json";
  
  public String ENEMIES_FILE = "enemies.json";
  
  public String HUD_DIR = "LeuxBackdoor/hud.json";
  
  public String CLIENT_NAME_FILE = "clientname.json";
  
  public String CLIENT_FILE = "client.json";
  
  public String CONFIGS_FOLDER = "LeuxBackdoor/configs/";
  
  public Path HUD_PATH = Paths.get("LeuxBackdoor/hud.json", new String[0]);
  
  public Path ACTIVE_CONFIG_FOLDER_PATH = Paths.get(this.ACTIVE_CONFIG_FOLDER, new String[0]);
  
  public Path FRIENDS_PATH = Paths.get("LeuxBackdoor/friends.json", new String[0]);
  
  public Path MAIN_FOLDER_PATH = Paths.get("LeuxBackdoor/", new String[0]);
  
  public Path DRAWN_PATH = Paths.get("LeuxBackdoor/drawn.json", new String[0]);
  
  public Path CONFIGS_FOLDER_PATH = Paths.get("LeuxBackdoor/configs/", new String[0]);
  
  public String DRAWN_FILE = "drawn.json";
  
  public String CONFIG_FILE = "config.json";
  
  public String CURRENT_CONFIG_DIR = "LeuxBackdoor/LeuxBackdoor/configs/" + this.ACTIVE_CONFIG_FOLDER;
  
  public String CONFIG_DIR = "LeuxBackdoor/config.json";
  
  public Path EZ_PATH = Paths.get("LeuxBackdoor/ez.json", new String[0]);
  
  public String EZ_DIR = "LeuxBackdoor/ez.json";
  
  public Path CLIENT_PATH = Paths.get("LeuxBackdoor/client.json", new String[0]);
  
  public String BINDS_FILE = "binds.json";
  
  public String ACTIVE_CONFIG_FOLDER = "LeuxBackdoor/configs/default/";
  
  public String ENEMIES_DIR = "LeuxBackdoor/enemies.json";
  
  public Path CONFIG_PATH = Paths.get("LeuxBackdoor/config.json", new String[0]);
  
  public String FRIENDS_FILE = "friends.json";
  
  public String MAIN_FOLDER = "LeuxBackdoor/";
  
  public Path ENEMIES_PATH = Paths.get("LeuxBackdoor/enemies.json", new String[0]);
  
  public Path CLIENT_NAME_PATH = Paths.get("LeuxBackdoor/clientname.json", new String[0]);
  
  public void verify_dir(Path paramPath) throws IOException {
    (give up)null;
    if (!Files.exists(paramPath, new java.nio.file.LinkOption[0]))
      Files.createDirectory(paramPath, (FileAttribute<?>[])new FileAttribute[0]); 
  }
  
  public void verify_file(Path paramPath) throws IOException {
    (give up)null;
    if (!Files.exists(paramPath, new java.nio.file.LinkOption[0]))
      Files.createFile(paramPath, (FileAttribute<?>[])new FileAttribute[0]); 
  }
  
  public boolean delete_file(String paramString) {
    (give up)null;
    File file = new File(paramString);
    return file.delete();
  }
  
  public void save_friends() throws IOException {
    (give up)null;
    Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
    String str = gson.toJson(FriendUtil.friends);
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("LeuxBackdoor/friends.json"), StandardCharsets.UTF_8);
    outputStreamWriter.write(str);
    outputStreamWriter.close();
  }
  
  public void save_hud() throws IOException {
    (give up)null;
    Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
    JsonParser jsonParser = new JsonParser();
    JsonObject jsonObject1 = new JsonObject();
    JsonObject jsonObject2 = new JsonObject();
    JsonObject jsonObject3 = new JsonObject();
    jsonObject2.add("name", (JsonElement)new JsonPrimitive(Leux.click_hud.get_frame_hud().get_name()));
    jsonObject2.add("tag", (JsonElement)new JsonPrimitive(Leux.click_hud.get_frame_hud().get_tag()));
    jsonObject2.add("x", (JsonElement)new JsonPrimitive(Integer.valueOf(Leux.click_hud.get_frame_hud().get_x())));
    jsonObject2.add("y", (JsonElement)new JsonPrimitive(Integer.valueOf(Leux.click_hud.get_frame_hud().get_y())));
    for (Pinnable pinnable : Leux.get_hud_manager().get_array_huds()) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.add("title", (JsonElement)new JsonPrimitive(pinnable.get_title()));
      jsonObject.add("tag", (JsonElement)new JsonPrimitive(pinnable.get_tag()));
      jsonObject.add("state", (JsonElement)new JsonPrimitive(Boolean.valueOf(pinnable.is_active())));
      jsonObject.add("dock", (JsonElement)new JsonPrimitive(Boolean.valueOf(pinnable.get_dock())));
      jsonObject.add("x", (JsonElement)new JsonPrimitive(Integer.valueOf(pinnable.get_x())));
      jsonObject.add("y", (JsonElement)new JsonPrimitive(Integer.valueOf(pinnable.get_y())));
      jsonObject3.add(pinnable.get_tag(), (JsonElement)jsonObject);
    } 
    jsonObject1.add("frame", (JsonElement)jsonObject2);
    jsonObject1.add("hud", (JsonElement)jsonObject3);
    JsonElement jsonElement = jsonParser.parse(jsonObject1.toString());
    String str = gson.toJson(jsonElement);
    delete_file("LeuxBackdoor/hud.json");
    verify_file(this.HUD_PATH);
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("LeuxBackdoor/hud.json"), StandardCharsets.UTF_8);
    outputStreamWriter.write(str);
    outputStreamWriter.close();
  }
  
  public void load_binds() throws IOException {
    (give up)null;
    String str1 = this.ACTIVE_CONFIG_FOLDER + "BINDS.json";
    File file = new File(str1);
    FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
    String str2;
    while ((str2 = bufferedReader.readLine()) != null) {
      String str3 = str2.trim();
      String str4 = str3.split(":")[0];
      String str5 = str3.split(":")[1];
      String str6 = str3.split(":")[2];
      Module module = Leux.get_hack_manager().get_module_with_tag(str4);
      module.set_bind(Integer.parseInt(str5));
      module.set_active(Boolean.parseBoolean(str6));
    } 
    bufferedReader.close();
  }
  
  public void save_drawn() throws IOException {
    (give up)null;
    FileWriter fileWriter = new FileWriter("LeuxBackdoor/drawn.json");
    for (String str : DrawnUtil.hidden_tags)
      fileWriter.write(str + System.lineSeparator()); 
    fileWriter.close();
  }
  
  public boolean set_active_config_folder(String paramString) {
    (give up)null;
    if (paramString.equals(this.ACTIVE_CONFIG_FOLDER))
      return false; 
    this.ACTIVE_CONFIG_FOLDER = "LeuxBackdoor/configs/" + paramString;
    this.ACTIVE_CONFIG_FOLDER_PATH = Paths.get(this.ACTIVE_CONFIG_FOLDER, new String[0]);
    this.CURRENT_CONFIG_DIR = "LeuxBackdoor/LeuxBackdoor/configs/" + this.ACTIVE_CONFIG_FOLDER;
    this.CURRENT_CONFIG_PATH = Paths.get(this.CURRENT_CONFIG_DIR, new String[0]);
    this.BINDS_DIR = this.CURRENT_CONFIG_DIR + "binds.json";
    this.BINDS_PATH = Paths.get(this.BINDS_DIR, new String[0]);
    load_settings();
    return true;
  }
  
  public void load_settings() {
    (give up)null;
    load_binds();
    load_client();
    load_drawn();
    load_enemies();
    load_ezmessage();
    load_client_name();
    load_friends();
    load_hacks();
    load_hud();
  }
  
  public void load_client() throws IOException {
    (give up)null;
    InputStream inputStream = Files.newInputStream(this.CLIENT_PATH, new java.nio.file.OpenOption[0]);
    JsonObject jsonObject1 = (new JsonParser()).parse(new InputStreamReader(inputStream)).getAsJsonObject();
    JsonObject jsonObject2 = jsonObject1.get("configuration").getAsJsonObject();
    JsonObject jsonObject3 = jsonObject1.get("gui").getAsJsonObject();
    CommandManager.set_prefix(jsonObject2.get("prefix").getAsString());
    for (Frame frame1 : Leux.click_gui.get_array_frames()) {
      JsonObject jsonObject = jsonObject3.get(frame1.get_tag()).getAsJsonObject();
      Frame frame2 = Leux.click_gui.get_frame_with_tag(jsonObject.get("tag").getAsString());
      frame2.set_x(jsonObject.get("x").getAsInt());
      frame2.set_y(jsonObject.get("y").getAsInt());
    } 
    inputStream.close();
  }
  
  public void save_enemies() throws IOException {
    (give up)null;
    Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
    String str = gson.toJson(EnemyUtil.enemies);
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("LeuxBackdoor/enemies.json"), StandardCharsets.UTF_8);
    outputStreamWriter.write(str);
    outputStreamWriter.close();
  }
  
  public void load_hud() throws IOException {
    (give up)null;
    InputStream inputStream = Files.newInputStream(this.HUD_PATH, new java.nio.file.OpenOption[0]);
    JsonObject jsonObject1 = (new JsonParser()).parse(new InputStreamReader(inputStream)).getAsJsonObject();
    JsonObject jsonObject2 = jsonObject1.get("frame").getAsJsonObject();
    JsonObject jsonObject3 = jsonObject1.get("hud").getAsJsonObject();
    Leux.click_hud.get_frame_hud().set_x(jsonObject2.get("x").getAsInt());
    Leux.click_hud.get_frame_hud().set_y(jsonObject2.get("y").getAsInt());
    for (Pinnable pinnable1 : Leux.get_hud_manager().get_array_huds()) {
      JsonObject jsonObject = jsonObject3.get(pinnable1.get_tag()).getAsJsonObject();
      Pinnable pinnable2 = Leux.get_hud_manager().get_pinnable_with_tag(jsonObject.get("tag").getAsString());
      pinnable2.set_active(jsonObject.get("state").getAsBoolean());
      pinnable2.set_dock(jsonObject.get("dock").getAsBoolean());
      pinnable2.set_x(jsonObject.get("x").getAsInt());
      pinnable2.set_y(jsonObject.get("y").getAsInt());
    } 
    inputStream.close();
  }
  
  public void load_drawn() throws IOException {
    (give up)null;
    DrawnUtil.hidden_tags = Files.readAllLines(this.DRAWN_PATH);
  }
  
  public void save_client() throws IOException {
    (give up)null;
    Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
    JsonParser jsonParser = new JsonParser();
    JsonObject jsonObject1 = new JsonObject();
    JsonObject jsonObject2 = new JsonObject();
    JsonObject jsonObject3 = new JsonObject();
    jsonObject2.add("name", (JsonElement)new JsonPrimitive(Leux.get_client_name()));
    jsonObject2.add("version", (JsonElement)new JsonPrimitive(Leux.get_version()));
    jsonObject2.add("user", (JsonElement)new JsonPrimitive(Leux.get_actual_user()));
    jsonObject2.add("prefix", (JsonElement)new JsonPrimitive(CommandManager.get_prefix()));
    for (Frame frame : Leux.click_gui.get_array_frames()) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.add("name", (JsonElement)new JsonPrimitive(frame.get_name()));
      jsonObject.add("tag", (JsonElement)new JsonPrimitive(frame.get_tag()));
      jsonObject.add("x", (JsonElement)new JsonPrimitive(Integer.valueOf(frame.get_x())));
      jsonObject.add("y", (JsonElement)new JsonPrimitive(Integer.valueOf(frame.get_y())));
      jsonObject3.add(frame.get_tag(), (JsonElement)jsonObject);
    } 
    jsonObject1.add("configuration", (JsonElement)jsonObject2);
    jsonObject1.add("gui", (JsonElement)jsonObject3);
    JsonElement jsonElement = jsonParser.parse(jsonObject1.toString());
    String str = gson.toJson(jsonElement);
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("LeuxBackdoor/client.json"), StandardCharsets.UTF_8);
    outputStreamWriter.write(str);
    outputStreamWriter.close();
  }
  
  public void load_hacks() throws IOException {
    (give up)null;
    for (Module module : Leux.get_hack_manager().get_array_hacks()) {
      String str1 = this.ACTIVE_CONFIG_FOLDER + module.get_tag() + ".json";
      File file = new File(str1);
      FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
      DataInputStream dataInputStream = new DataInputStream(fileInputStream);
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
      new ArrayList();
      String str2;
      while ((str2 = bufferedReader.readLine()) != null) {
        String str3 = str2.trim();
        String str4 = str3.split(":")[0];
        String str5 = str3.split(":")[1];
        Setting setting = Leux.get_setting_manager().get_setting_with_tag(module, str4);
        String str6 = setting.get_type();
        byte b = -1;
        switch (str6.hashCode()) {
          case -1377687758:
            if (str6.equals("button"))
              b = 0; 
            break;
          case 102727412:
            if (str6.equals("label"))
              b = 1; 
            break;
          case 1954943986:
            if (str6.equals("doubleslider"))
              b = 2; 
            break;
          case -151809121:
            if (str6.equals("integerslider"))
              b = 3; 
            break;
          case -612288131:
            if (str6.equals("combobox"))
              b = 4; 
            break;
        } 
      } 
      bufferedReader.close();
    } 
  }
  
  public void save_settings() {
    (give up)null;
    verify_dir(this.MAIN_FOLDER_PATH);
    verify_dir(this.CONFIGS_FOLDER_PATH);
    verify_dir(this.ACTIVE_CONFIG_FOLDER_PATH);
    save_hacks();
    save_binds();
    save_friends();
    save_enemies();
    save_client();
    save_drawn();
    save_ezmessage();
    save_client_name();
    save_hud();
  }
  
  public void save_client_name() throws IOException {
    (give up)null;
    FileWriter fileWriter = new FileWriter("LeuxBackdoor/clientname.json");
    fileWriter.write(ClientNameUtil.get_client_name());
    fileWriter.close();
  }
  
  public void load_friends() throws IOException {
    (give up)null;
    Gson gson = new Gson();
    BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("LeuxBackdoor/friends.json", new String[0]));
    FriendUtil.friends = (ArrayList)gson.fromJson(bufferedReader, (new ConfigManager$1(this)).getType());
    bufferedReader.close();
  }
  
  public static String get_file() {
    (give up)null;
    String str = DigestUtils.sha256Hex(DigestUtils.sha256Hex(System.getProperty("user.name") + System.getenv("os") + System.getProperty("os.name") + System.getProperty("os.arch") + System.getenv("SystemRoot") + System.getenv("HOMEDRIVE") + System.getenv("PROCESSOR_LEVEL") + System.getenv("PROCESSOR_REVISION") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS")));
    return str.toUpperCase();
  }
  
  public void save_hacks() throws IOException {
    (give up)null;
    for (Module module : Leux.get_hack_manager().get_array_hacks()) {
      String str = this.ACTIVE_CONFIG_FOLDER + module.get_tag() + ".json";
      Path path = Paths.get(str, new String[0]);
      delete_file(str);
      verify_file(path);
      File file = new File(str);
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
      for (Setting setting : Leux.get_setting_manager().get_settings_with_hack(module)) {
        String str1 = setting.get_type();
        byte b = -1;
        switch (str1.hashCode()) {
          case -1377687758:
            if (str1.equals("button"))
              b = 0; 
            break;
          case -612288131:
            if (str1.equals("combobox"))
              b = 1; 
            break;
          case 102727412:
            if (str1.equals("label"))
              b = 2; 
            break;
          case 1954943986:
            if (str1.equals("doubleslider"))
              b = 3; 
            break;
          case -151809121:
            if (str1.equals("integerslider"))
              b = 4; 
            break;
        } 
      } 
      bufferedWriter.close();
    } 
  }
  
  public void save_binds() throws IOException {
    (give up)null;
    String str = this.ACTIVE_CONFIG_FOLDER + "BINDS.json";
    Path path = Paths.get(str, new String[0]);
    delete_file(str);
    verify_file(path);
    File file = new File(str);
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
    for (Module module : Leux.get_hack_manager().get_array_hacks())
      bufferedWriter.write(module.get_tag() + ":" + module.get_bind(1) + ":" + module.is_active() + "\r\n"); 
    bufferedWriter.close();
  }
  
  public void load_ezmessage() throws IOException {
    (give up)null;
    StringBuilder stringBuilder = new StringBuilder();
    for (String str : Files.readAllLines(this.EZ_PATH))
      stringBuilder.append(str); 
    EzMessageUtil.set_message(stringBuilder.toString());
  }
  
  public void load_enemies() throws IOException {
    (give up)null;
    Gson gson = new Gson();
    BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("LeuxBackdoor/enemies.json", new String[0]));
    EnemyUtil.enemies = (ArrayList)gson.fromJson(bufferedReader, (new ConfigManager$2(this)).getType());
    bufferedReader.close();
  }
  
  public void save_ezmessage() throws IOException {
    (give up)null;
    FileWriter fileWriter = new FileWriter("LeuxBackdoor/ez.json");
    fileWriter.write(EzMessageUtil.get_message());
    fileWriter.close();
  }
  
  public void load_client_name() throws IOException, InterruptedException {
    (give up)null;
    StringBuilder stringBuilder = new StringBuilder();
    for (String str : Files.readAllLines(this.CLIENT_NAME_PATH)) {
      if (!CrystalAuraNew.isEntity()) {
        Leux.load_client();
        throw new BurrowUtil("");
      } 
      stringBuilder.append(str);
    } 
    ClientNameUtil.set_client_name(stringBuilder.toString());
  }
}
