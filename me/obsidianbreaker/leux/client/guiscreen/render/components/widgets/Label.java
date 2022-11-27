package me.obsidianbreaker.leux.client.guiscreen.render.components.widgets;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import me.obsidianbreaker.leux.client.guiscreen.render.components.AbstractWidget;
import me.obsidianbreaker.leux.client.guiscreen.render.components.Frame;
import me.obsidianbreaker.leux.client.guiscreen.render.components.ModuleButton;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Label extends AbstractWidget {
  public ModuleButton master;
  
  public Frame frame;
  
  public int border_size = 0;
  
  public boolean can;
  
  public int save_y;
  
  public int width;
  
  public Setting setting;
  
  public int y;
  
  public int x;
  
  public int height;
  
  public boolean info;
  
  public Draw font = new Draw(Float.intBitsToFloat(1.09569459E9F ^ 0x7ECEF913));
  
  public String label_name;
  
  public int get_y() {
    (give up)null;
    return this.y;
  }
  
  public void set_x(int paramInt) {
    (give up)null;
    this.x = paramInt;
  }
  
  public Setting get_setting() {
    (give up)null;
    return this.setting;
  }
  
  public void set_width(int paramInt) {
    (give up)null;
    this.width = paramInt;
  }
  
  public void set_y(int paramInt) {
    (give up)null;
    this.y = paramInt;
  }
  
  public int get_height() {
    (give up)null;
    return this.height;
  }
  
  public void render(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    set_width(this.master.get_width() - paramInt2);
    String str = "me";
    this.save_y = this.y + paramInt1;
    int i = Leux.click_gui.theme_widget_name_r;
    int j = Leux.click_gui.theme_widget_name_g;
    int k = Leux.click_gui.theme_widget_name_b;
    int m = Leux.click_gui.theme_widget_name_a;
    100;
    if (motion(paramInt3, paramInt4) && this.setting.get_master().using_widget()) {
      this.setting.get_master().event_widget();
      GL11.glPushMatrix();
      GL11.glEnable(3553);
      GL11.glEnable(3042);
      GlStateManager.func_179147_l();
      GL11.glPopMatrix();
      RenderHelp.release_gl();
    } 
    if (this.info) {
      Draw.draw_string_shadow(this.setting.get_value(str), this.x + 2, this.save_y, i, j, k, m);
    } else {
      Draw.draw_string_shadow(this.label_name + " \"" + this.setting.get_value(str) + "\"", this.x + 2, this.save_y, i, j, k, m);
    } 
  }
  
  public int get_width() {
    (give up)null;
    return this.width;
  }
  
  public boolean can() {
    (give up)null;
    return this.can;
  }
  
  public boolean motion_pass(int paramInt1, int paramInt2) {
    (give up)null;
    return motion(paramInt1, paramInt2);
  }
  
  public int get_save_y() {
    (give up)null;
    return this.save_y;
  }
  
  public void set_height(int paramInt) {
    (give up)null;
    this.height = paramInt;
  }
  
  public Label(Frame paramFrame, ModuleButton paramModuleButton, String paramString, int paramInt) {
    this.frame = paramFrame;
    this.master = paramModuleButton;
    this.setting = Leux.get_setting_manager().get_setting_with_tag(paramModuleButton.get_module(), paramString);
    this.x = paramModuleButton.get_x();
    this.y = paramInt;
    this.save_y = this.y;
    this.width = paramModuleButton.get_width();
    this.height = this.font.get_string_height();
    this.label_name = this.setting.get_name();
    if (this.setting.get_name().equalsIgnoreCase("info"))
      this.info = true; 
    this.can = true;
  }
  
  public void does_can(boolean paramBoolean) {
    (give up)null;
    this.can = paramBoolean;
  }
  
  public boolean motion(int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_save_y() && paramInt1 <= get_x() + get_width() && paramInt2 <= get_save_y() + get_height());
  }
  
  public void mouse(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    if (motion(paramInt1, paramInt2) && this.master.is_open() && can())
      this.frame.does_can(false); 
  }
  
  public int get_x() {
    (give up)null;
    return this.x;
  }
}
