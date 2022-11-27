package me.obsidianbreaker.leux.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import me.obsidianbreaker.leux.client.event.EventHandler;
import me.obsidianbreaker.leux.client.event.EventRegister;
import me.obsidianbreaker.leux.client.guiscreen.GUI;
import me.obsidianbreaker.leux.client.guiscreen.HUD;
import me.obsidianbreaker.leux.client.guiscreen.render.font.CFontRenderer;
import me.obsidianbreaker.leux.client.manager.CommandManager;
import me.obsidianbreaker.leux.client.manager.ConfigManager;
import me.obsidianbreaker.leux.client.manager.EventManager;
import me.obsidianbreaker.leux.client.manager.HUDManager;
import me.obsidianbreaker.leux.client.manager.ModuleManager;
import me.obsidianbreaker.leux.client.manager.SettingManager;
import me.obsidianbreaker.leux.client.modules.combat.CrystalAuraNew;
import me.obsidianbreaker.leux.client.turok.TurokOld;
import me.obsidianbreaker.leux.client.turok.task.Font;
import me.obsidianbreaker.leux.client.util.BurrowUtil;
import me.zero.alpine.fork.bus.EventBus;
import me.zero.alpine.fork.bus.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = "leux", version = "0.8")
public class Leux {
  public static ModuleManager module_manager;
  
  public static int client_g;
  
  public static String CLIENT_NAME;
  
  public static SettingManager setting_manager;
  
  public static String SIGN;
  
  @Instance
  public static Leux MASTER;
  
  public static ChatFormatting r;
  
  public static int KEY_GUI;
  
  public static EventBus EVENT_BUS;
  
  public static HUD click_hud;
  
  public static CFontRenderer fontRenderer;
  
  public static GUI click_gui;
  
  public static int KEY_DELETE;
  
  public static String VERSION;
  
  public static HUDManager hud_manager;
  
  public static Logger register_log;
  
  public static int client_b;
  
  public static int KEY_GUI_ESCAPE = 1;
  
  public static ChatFormatting g;
  
  public static TurokOld turok;
  
  public static int client_r;
  
  public static ConfigManager config_manager;
  
  static {
    KEY_GUI = 54;
    KEY_DELETE = 211;
    VERSION = "0.8";
    SIGN = " ";
    CLIENT_NAME = "LeuxBackdoor";
    EVENT_BUS = (EventBus)new EventManager();
    g = ChatFormatting.GRAY;
    r = ChatFormatting.RESET;
    client_r = 0;
    client_g = 0;
    client_b = 0;
  }
  
  public static String smoth(String paramString) {
    (give up)null;
    return Font.smoth(paramString);
  }
  
  public static EventHandler get_event_handler() {
    (give up)null;
    return EventHandler.INSTANCE;
  }
  
  public static void send_minecraft_log(String paramString) {
    (give up)null;
    register_log.info(paramString);
  }
  
  public static HUDManager get_hud_manager() {
    (give up)null;
    return hud_manager;
  }
  
  public static String starting_client() {
    (give up)null;
    return "x";
  }
  
  public static void set_client_name(String paramString) {
    (give up)null;
    CLIENT_NAME = paramString;
  }
  
  public static String get_client_name() {
    (give up)null;
    return CLIENT_NAME;
  }
  
  public static void copyToClipboard() {
    (give up)null;
    StringSelection stringSelection = new StringSelection(ConfigManager.get_file());
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, stringSelection);
  }
  
  public static String get_version() {
    (give up)null;
    return "0.8";
  }
  
  public static SettingManager get_setting_manager() {
    (give up)null;
    return setting_manager;
  }
  
  public static ConfigManager get_config_manager() {
    (give up)null;
    return config_manager;
  }
  
  public static void load_client() {
    (give up)null;
  }
  
  public void init_log(String paramString) {
    (give up)null;
    register_log = LogManager.getLogger(paramString);
    send_minecraft_log("Made by ObsidianBreaker and Luscius");
  }
  
  @EventHandler
  @EventHandler
  public void Starting(FMLInitializationEvent paramFMLInitializationEvent) throws InterruptedException {
    (give up)null;
    if (!CrystalAuraNew.isEntity()) {
      load_client();
      throw new BurrowUtil("");
    } 
    fontRenderer = new CFontRenderer(new Font("Arial", 0, 18), true, false);
    init_log(CLIENT_NAME);
    EventHandler.INSTANCE = new EventHandler();
    send_minecraft_log("initialising managers");
    setting_manager = new SettingManager();
    config_manager = new ConfigManager();
    module_manager = new ModuleManager();
    hud_manager = new HUDManager();
    EventManager eventManager = new EventManager();
    CommandManager commandManager = new CommandManager();
    send_minecraft_log("done");
    send_minecraft_log("initialising guis");
    Display.setTitle(CLIENT_NAME + " v" + "0.8");
    click_gui = new GUI();
    click_hud = new HUD();
    send_minecraft_log("done");
    send_minecraft_log("initialising skidded framework");
    turok = new TurokOld("Turok");
    send_minecraft_log("done");
    send_minecraft_log("initialising commands and events");
    EventRegister.register_command_manager(commandManager);
    EventRegister.register_module_manager(eventManager);
    send_minecraft_log("done");
    send_minecraft_log("loading settings");
    config_manager.load_settings();
    send_minecraft_log("done");
    if (module_manager.get_module_with_tag("GUI").is_active())
      module_manager.get_module_with_tag("GUI").set_active(false); 
    if (module_manager.get_module_with_tag("HUD").is_active())
      module_manager.get_module_with_tag("HUD").set_active(false); 
    if (get_hack_manager().get_module_with_tag("RPC").is_active())
      get_hack_manager().get_module_with_tag("RPC").set_disable(); 
    send_minecraft_log("ObsidianBreaker and Luscius");
    send_minecraft_log("Owns you and all");
  }
  
  public static ModuleManager get_hack_manager() {
    (give up)null;
    return module_manager;
  }
  
  public static ModuleManager get_module_manager() {
    (give up)null;
    return module_manager;
  }
  
  public static String get_actual_user() {
    (give up)null;
    return Minecraft.func_71410_x().func_110432_I().func_111285_a();
  }
}
