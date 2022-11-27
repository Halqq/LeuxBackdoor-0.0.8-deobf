package me.obsidianbreaker.leux.client.modules;

import give up;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.event.events.EventRenderEntityModel;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import me.zero.alpine.fork.listener.Listenable;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class Module implements Listenable {
  public boolean state_module;
  
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public String description = "";
  
  public boolean toggle_message = true;
  
  public String tag = "";
  
  public Category category;
  
  public int bind = -1;
  
  public String name = "";
  
  public boolean widget_usage = false;
  
  public void enable() {
    (give up)null;
  }
  
  public String get_bind(String paramString) {
    paramString = "string";
    (give up)null;
    null = "null";
    if (get_bind(0) < 0)
      null = "NONE"; 
    null.equals("NONE");
    String str = Keyboard.getKeyName(get_bind(0));
    return Character.toUpperCase(str.charAt(0)) + ((str.length() != 1) ? str.substring(1).toLowerCase() : "");
  }
  
  public String array_detail() {
    (give up)null;
    return null;
  }
  
  public List combobox(String... paramVarArgs) {
    (give up)null;
    return new ArrayList(Arrays.asList((Object[])paramVarArgs));
  }
  
  public void set_enable() {
    (give up)null;
    this.state_module = true;
    enable();
    EventClientBus.EVENT_BUS.subscribe(this);
  }
  
  public int get_bind(int paramInt) {
    (give up)null;
    return this.bind;
  }
  
  public void set_bind(int paramInt) {
    (give up)null;
    this.bind = paramInt;
  }
  
  public boolean using_widget() {
    (give up)null;
    return this.widget_usage;
  }
  
  public void set_disable() {
    (give up)null;
    this.state_module = false;
    disable();
    EventClientBus.EVENT_BUS.unsubscribe(this);
  }
  
  public void update() {
    (give up)null;
  }
  
  public void on_render_model(EventRenderEntityModel paramEventRenderEntityModel) {
    (give up)null;
  }
  
  public Module(Category paramCategory) {
    this.category = paramCategory;
  }
  
  public Setting create(String paramString1, String paramString2, String paramString3, List paramList) {
    (give up)null;
    Leux.get_setting_manager().register(new Setting(this, paramString1, paramString2, paramList, paramString3));
    return Leux.get_setting_manager().get_setting_with_tag(this, paramString2);
  }
  
  public void render() {
    (give up)null;
  }
  
  public String get_name() {
    (give up)null;
    return this.name;
  }
  
  public boolean is_disabled() {
    (give up)null;
    return !is_active();
  }
  
  public Setting create(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    Leux.get_setting_manager().register(new Setting(this, paramString1, paramString2, paramInt1, paramInt2, paramInt3));
    return Leux.get_setting_manager().get_setting_with_tag(this, paramString2);
  }
  
  public Setting create(String paramString1, String paramString2, double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    Leux.get_setting_manager().register(new Setting(this, paramString1, paramString2, paramDouble1, paramDouble2, paramDouble3));
    return Leux.get_setting_manager().get_setting_with_tag(this, paramString2);
  }
  
  public boolean is_active() {
    (give up)null;
    return this.state_module;
  }
  
  public Category get_category() {
    (give up)null;
    return this.category;
  }
  
  public boolean can_send_message_when_toggle() {
    (give up)null;
    return this.toggle_message;
  }
  
  public void disable() {
    (give up)null;
  }
  
  public void set_active(boolean paramBoolean) {
    (give up)null;
    if (this.state_module != paramBoolean)
      set_enable(); 
    if (!this.tag.equals("GUI") && !this.tag.equals("HUD") && this.toggle_message)
      MessageUtil.toggle_message(this); 
  }
  
  public String get_tag() {
    (give up)null;
    return this.tag;
  }
  
  public void event_widget() {
    (give up)null;
  }
  
  public String getMeta() {
    (give up)null;
    return "";
  }
  
  public void render(EventRender paramEventRender) {
    (give up)null;
  }
  
  public String get_description() {
    (give up)null;
    return this.description;
  }
  
  public void toggle() {
    (give up)null;
    set_active(!is_active());
  }
  
  public Setting create(String paramString1, String paramString2, boolean paramBoolean) {
    (give up)null;
    Leux.get_setting_manager().register(new Setting(this, paramString1, paramString2, paramBoolean));
    return Leux.get_setting_manager().get_setting_with_tag(this, paramString2);
  }
  
  public void set_if_can_send_message_toggle(boolean paramBoolean) {
    (give up)null;
    this.toggle_message = paramBoolean;
  }
  
  public Setting create(String paramString1, String paramString2, String paramString3) {
    (give up)null;
    Leux.get_setting_manager().register(new Setting(this, paramString1, paramString2, paramString3));
    return Leux.get_setting_manager().get_setting_with_tag(this, paramString2);
  }
}
