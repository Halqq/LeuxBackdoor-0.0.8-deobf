package me.obsidianbreaker.leux.client.modules.render;

import give up;
import java.awt.Color;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SkyColour extends Module {
  public Setting g = create("G", "SkyColourG", 255, 0, 255);
  
  public Setting b = create("B", "SkyColourB", 255, 0, 255);
  
  public Setting r = create("R", "SkyColourR", 255, 0, 255);
  
  public Setting rainbow_mode = create("Rainbow", "SkyColourRainbow", false);
  
  @SubscribeEvent
  @SubscribeEvent
  public void fog_density(EntityViewRenderEvent.FogDensity paramFogDensity) {
    (give up)null;
    paramFogDensity.setDensity(0.0F);
    paramFogDensity.setCanceled(true);
  }
  
  public SkyColour() {
    super(Category.render);
  }
  
  public void disable() {
    (give up)null;
    MinecraftForge.EVENT_BUS.unregister(this);
  }
  
  public void update() {
    (give up)null;
    if (this.rainbow_mode.get_value(true))
      cycle_rainbow(); 
  }
  
  public void enable() {
    (give up)null;
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  public void cycle_rainbow() {
    (give up)null;
    float[] arrayOfFloat = { (float)(System.currentTimeMillis() % 11520L) / 11520.0F };
    int i = Color.HSBtoRGB(arrayOfFloat[0], 0.8F, 0.8F);
    this.r.set_value(i >> 16 & 0xFF);
    this.g.set_value(i >> 8 & 0xFF);
    this.b.set_value(i & 0xFF);
  }
  
  @SubscribeEvent
  @SubscribeEvent
  public void fog_colour(EntityViewRenderEvent.FogColors paramFogColors) {
    (give up)null;
    paramFogColors.setRed(this.r.get_value(1) / Float.intBitsToFloat(1.04006227E9F ^ 0x7E811755));
    paramFogColors.setGreen(this.g.get_value(1) / Float.intBitsToFloat(1.03978445E9F ^ 0x7E86D9F7));
    paramFogColors.setBlue(this.b.get_value(1) / Float.intBitsToFloat(1.05509517E9F ^ 0x7D9C799F));
  }
}
