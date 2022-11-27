package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class PlayerModel extends Pinnable {
  public void render() {
    (give up)null;
    if (this.mc.field_71439_g == null || this.mc.field_71441_e == null)
      return; 
    EntityPlayerSP entityPlayerSP = this.mc.field_71439_g;
    float f1 = interpolateAndWrap(Float.valueOf(((EntityLivingBase)entityPlayerSP).field_70126_B), Float.valueOf(((EntityLivingBase)entityPlayerSP).field_70177_z));
    float f2 = interpolateAndWrap(Float.valueOf(((EntityLivingBase)entityPlayerSP).field_70127_C), Float.valueOf(((EntityLivingBase)entityPlayerSP).field_70125_A));
    GL11.glColor3f(Float.intBitsToFloat(1.08431834E9F ^ 0x7F216292), Float.intBitsToFloat(1.08694259E9F ^ 0x7F496DBF), Float.intBitsToFloat(1.08265293E9F ^ 0x7F07F8D1));
    GuiInventory.func_147046_a(get_x() + 20, get_y() + 80, 40, -f1, -f2, (EntityLivingBase)entityPlayerSP);
  }
  
  public float interpolateAndWrap(Float paramFloat1, Float paramFloat2) {
    (give up)null;
    return MathHelper.func_76142_g(paramFloat1.floatValue() + (paramFloat2.floatValue() - paramFloat1.floatValue()) * this.mc.func_184121_ak());
  }
  
  public PlayerModel() {
    super("Player Model", "PlayerModel", Float.intBitsToFloat(1.13162291E9F ^ 0x7CF331FF), 0, 0);
    set_width(40);
    set_height(100);
  }
}
