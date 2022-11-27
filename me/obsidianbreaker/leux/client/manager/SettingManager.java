package me.obsidianbreaker.leux.client.manager;

import give up;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Module;

public class SettingManager {
  public ArrayList array_setting = new ArrayList();
  
  public Setting get_setting_with_tag(String paramString1, String paramString2) {
    (give up)null;
    Setting setting = null;
    for (Setting setting1 : get_array_settings()) {
      if (setting1.get_master().get_tag().equalsIgnoreCase(paramString1) && setting1.get_tag().equalsIgnoreCase(paramString2)) {
        setting = setting1;
        break;
      } 
    } 
    return setting;
  }
  
  public Setting get_setting_with_tag(Module paramModule, String paramString) {
    (give up)null;
    Setting setting = null;
    for (Setting setting1 : get_array_settings()) {
      if (setting1.get_master().equals(paramModule) && setting1.get_tag().equalsIgnoreCase(paramString))
        setting = setting1; 
    } 
    return setting;
  }
  
  public void register(Setting paramSetting) {
    (give up)null;
    this.array_setting.add(paramSetting);
  }
  
  public ArrayList get_settings_with_hack(Module paramModule) {
    (give up)null;
    ArrayList<Setting> arrayList = new ArrayList();
    for (Setting setting : get_array_settings()) {
      if (setting.get_master().equals(paramModule))
        arrayList.add(setting); 
    } 
    return arrayList;
  }
  
  public ArrayList get_array_settings() {
    (give up)null;
    return this.array_setting;
  }
}
