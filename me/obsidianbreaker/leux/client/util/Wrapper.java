package me.obsidianbreaker.leux.client.util;

import give up;
import me.obsidianbreaker.leux.client.modules.client.ClickGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class Wrapper {
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static FontRenderer fontRenderer;
  
  public static EntityPlayerSP GetPlayer() {
    (give up)null;
    return mc.field_71439_g;
  }
  
  public static void init() {
    (give up)null;
    fontRenderer = ClickGUI.mc.field_71466_p;
  }
  
  public static Minecraft getMinecraft() {
    (give up)null;
    return Minecraft.func_71410_x();
  }
  
  public static FontRenderer getFontRenderer() {
    (give up)null;
    return fontRenderer;
  }
  
  public static World getWorld() {
    (give up)null;
    return (World)(getMinecraft()).field_71441_e;
  }
  
  public static int getKey(String paramString) {
    (give up)null;
    return Keyboard.getKeyIndex(paramString.toUpperCase());
  }
  
  public static EntityPlayerSP getPlayer() {
    (give up)null;
    return (getMinecraft()).field_71439_g;
  }
}
