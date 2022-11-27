package me.obsidianbreaker.leux.client.event;

import give up;
import me.obsidianbreaker.leux.client.manager.CommandManager;
import me.obsidianbreaker.leux.client.manager.EventManager;
import net.minecraftforge.common.MinecraftForge;

public class EventRegister {
  public static void register_command_manager(CommandManager paramCommandManager) {
    (give up)null;
    MinecraftForge.EVENT_BUS.register(paramCommandManager);
  }
  
  public static void register_module_manager(EventManager paramEventManager) {
    (give up)null;
    MinecraftForge.EVENT_BUS.register(paramEventManager);
  }
}
