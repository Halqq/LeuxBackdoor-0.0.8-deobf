package me.obsidianbreaker.leux.client.modules.render;

import give up;
import java.awt.Color;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class Tracers extends Module {
  public Setting width = create("Width", "TracerWidth", 1.0D, 0.0D, 5.0D);
  
  public Setting range = create("Range", "TracerRange", 75, 0, 250);
  
  public Setting friends = create("Friends", "TracerFriends", true);
  
  public Setting offset = create("Offset", "TracerOffset", 0.0D, -4.0D, 4.0D);
  
  public void lambda$render$0(float[][] paramArrayOffloat, Entity paramEntity) {
    (give up)null;
    if (!(paramEntity instanceof EntityPlayer) || paramEntity == mc.field_71439_g)
      return; 
    EntityPlayer entityPlayer = (EntityPlayer)paramEntity;
    if (mc.field_71439_g.func_70032_d((Entity)entityPlayer) > this.range.get_value(1))
      return; 
    if (FriendUtil.isFriend(entityPlayer.func_70005_c_()) && !this.friends.get_value(true))
      return; 
    paramArrayOffloat[0] = getColorByDistance((Entity)entityPlayer);
    drawLineToEntity((Entity)entityPlayer, paramArrayOffloat[0][0], paramArrayOffloat[0][1], paramArrayOffloat[0][2], paramArrayOffloat[0][3]);
  }
  
  public void drawLine(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    (give up)null;
    Vec3d vec3d = (new Vec3d(0.0D, 0.0D, 1.0D)).func_178789_a(-((float)Math.toRadians(mc.field_71439_g.field_70125_A))).func_178785_b(-((float)Math.toRadians(mc.field_71439_g.field_70177_z)));
    drawLineFromPosToPos(vec3d.field_72450_a, vec3d.field_72448_b + mc.field_71439_g.func_70047_e() + this.offset.get_value(1), vec3d.field_72449_c, paramDouble1, paramDouble2, paramDouble3, paramDouble4, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public double[] interpolate(Entity paramEntity) {
    (give up)null;
    double d1 = interpolate(paramEntity.field_70165_t, paramEntity.field_70142_S) - (mc.func_175598_ae()).field_78725_b;
    double d2 = interpolate(paramEntity.field_70163_u, paramEntity.field_70137_T) - (mc.func_175598_ae()).field_78726_c;
    double d3 = interpolate(paramEntity.field_70161_v, paramEntity.field_70136_U) - (mc.func_175598_ae()).field_78723_d;
    return new double[] { d1, d2, d3 };
  }
  
  public Tracers() {
    super(Category.render);
  }
  
  public void drawLineFromPosToPos(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    (give up)null;
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glLineWidth(this.width.get_value(1));
    GL11.glDisable(3553);
    GL11.glDisable(2929);
    GL11.glDepthMask(false);
    GL11.glColor4f(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    GlStateManager.func_179140_f();
    GL11.glLoadIdentity();
    mc.field_71460_t.func_78467_g(mc.func_184121_ak());
    GL11.glBegin(1);
    GL11.glVertex3d(paramDouble1, paramDouble2, paramDouble3);
    GL11.glVertex3d(paramDouble4, paramDouble5, paramDouble6);
    GL11.glVertex3d(paramDouble4, paramDouble5, paramDouble6);
    GL11.glEnd();
    GL11.glEnable(3553);
    GL11.glEnable(2929);
    GL11.glDepthMask(true);
    GL11.glDisable(3042);
    GL11.glColor3d(1.0D, 1.0D, 1.0D);
    GlStateManager.func_179145_e();
  }
  
  public void render(EventRender paramEventRender) {
    // Byte code:
    //   0: aconst_null
    //   1: checkcast give up
    //   4: pop
    //   5: getstatic me/obsidianbreaker/leux/client/modules/render/Tracers.mc : Lnet/minecraft/client/Minecraft;
    //   8: getfield field_71441_e : Lnet/minecraft/client/multiplayer/WorldClient;
    //   11: ifnonnull -> 15
    //   14: return
    //   15: invokestatic func_179094_E : ()V
    //   18: iconst_1
    //   19: iconst_1
    //   20: multianewarray[[F 2
    //   24: astore_2
    //   25: getstatic me/obsidianbreaker/leux/client/modules/render/Tracers.mc : Lnet/minecraft/client/Minecraft;
    //   28: getfield field_71441_e : Lnet/minecraft/client/multiplayer/WorldClient;
    //   31: getfield field_72996_f : Ljava/util/List;
    //   34: aload_0
    //   35: aload_2
    //   36: <illegal opcode> accept : (Lme/obsidianbreaker/leux/client/modules/render/Tracers;[[F)Ljava/util/function/Consumer;
    //   41: invokeinterface forEach : (Ljava/util/function/Consumer;)V
    //   46: invokestatic func_179121_F : ()V
    //   49: return
    //   50: nop
    //   51: nop
    //   52: nop
    //   53: nop
    //   54: nop
    //   55: nop
    //   56: nop
    //   57: nop
    //   58: nop
    //   59: nop
    //   60: nop
    //   61: nop
    //   62: nop
    //   63: nop
    //   64: athrow
    //   65: nop
    //   66: nop
    //   67: nop
    //   68: nop
    //   69: nop
    //   70: nop
    //   71: nop
    //   72: nop
    //   73: nop
    //   74: nop
    //   75: nop
    //   76: nop
    //   77: nop
    //   78: nop
    //   79: nop
    //   80: nop
    //   81: nop
    //   82: nop
    //   83: nop
    //   84: nop
    //   85: nop
    //   86: nop
    //   87: nop
    //   88: nop
    //   89: nop
    //   90: nop
    //   91: nop
    //   92: nop
    //   93: nop
    //   94: nop
    //   95: nop
    //   96: nop
    //   97: nop
    //   98: nop
    //   99: athrow
  }
  
  public float[] getColorByDistance(Entity paramEntity) {
    (give up)null;
    if (paramEntity instanceof EntityPlayer && FriendUtil.isFriend(paramEntity.func_70005_c_()))
      return new float[] { Float.intBitsToFloat(2.12990502E9F ^ 0x7EF3C5AD), Float.intBitsToFloat(1.07565184E9F ^ 0x7F1D24E6), Float.intBitsToFloat(1.09700966E9F ^ 0x7EE309DB), Float.intBitsToFloat(1.09872179E9F ^ 0x7EFD2A3B) }; 
    Color color = new Color(Color.HSBtoRGB((float)(Math.max(0.0D, Math.min(mc.field_71439_g.func_70068_e(paramEntity), 2500.0D) / 2500.0D) / 3.0D), Float.intBitsToFloat(1.08453709E9F ^ 0x7F24B8FD), Float.intBitsToFloat(1.07659328E9F ^ 0x7F674E68)) | 0xFF000000);
    return new float[] { color.getRed() / Float.intBitsToFloat(1.01240698E9F ^ 0x7F271AB1), color.getGreen() / Float.intBitsToFloat(1.01438726E9F ^ 0x7F095243), color.getBlue() / Float.intBitsToFloat(1.04710611E9F ^ 0x7D16924F), Float.intBitsToFloat(1.08367014E9F ^ 0x7F177E59) };
  }
  
  public double interpolate(double paramDouble1, double paramDouble2) {
    (give up)null;
    return paramDouble2 + (paramDouble1 - paramDouble2) * mc.func_184121_ak();
  }
  
  public void drawLineToEntity(Entity paramEntity, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    (give up)null;
    double[] arrayOfDouble = interpolate(paramEntity);
    drawLine(arrayOfDouble[0], arrayOfDouble[1], arrayOfDouble[2], paramEntity.field_70131_O, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
}
