package me.obsidianbreaker.leux.client.turok;

import give up;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class TurokDisplay {
  public Minecraft mc;
  
  public int scaleFactor;
  
  public float partialTicks;
  
  public void setPartialTicks(float paramFloat) {
    (give up)null;
    this.partialTicks = paramFloat;
  }
  
  public TurokDisplay(Minecraft paramMinecraft) {
    this.mc = paramMinecraft;
    this.scaleFactor = 1;
  }
  
  public void onUpdate() {
    (give up)null;
    boolean bool = this.mc.func_152349_b();
    int i = this.mc.field_71474_y.field_74335_Z;
    i = 1000;
    while (this.scaleFactor < i && getWidth() / (this.scaleFactor + 1) >= 320 && getHeight() / (this.scaleFactor + 1) >= 240)
      this.scaleFactor++; 
    if (this.scaleFactor % 2 != 0 && this.scaleFactor != 1)
      this.scaleFactor--; 
  }
  
  public int getWidth() {
    (give up)null;
    return this.mc.field_71443_c;
  }
  
  public int getHeight() {
    (give up)null;
    return this.mc.field_71440_d;
  }
  
  public float getPartialTicks(GuiScreen paramGuiScreen) {
    (give up)null;
    return this.partialTicks;
  }
  
  public int getScaledWidth() {
    (give up)null;
    onUpdate();
    return (int)(this.mc.field_71443_c / this.scaleFactor);
  }
  
  public int getScaleFactor() {
    (give up)null;
    return this.scaleFactor;
  }
  
  public int getScaledHeight() {
    (give up)null;
    onUpdate();
    return (int)(this.mc.field_71440_d / this.scaleFactor);
  }
}
