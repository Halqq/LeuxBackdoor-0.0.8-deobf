package me.obsidianbreaker.leux.client.modules.render;

import give up;
import me.obsidianbreaker.leux.client.event.events.EventFirstPerson;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

public class ViewModel extends Module {
  public Setting scaleLeft = create("Scale Left", "ScaleLeft", 7.0D, 0.0D, 50.0D);
  
  public float fov;
  
  public Setting rotateLeftY = create("Rotate Left Y", "RotateLeftY", 0, -360, 360);
  
  public Setting zLeft = create("Left Z", "LeftZ", -50.0D, -50.0D, 50.0D);
  
  public Setting rotateLeftZ = create("Rotate Left Z", "RotateLeftZ", 0, -360, 360);
  
  public Setting rotateLeftX = create("Rotate Left X", "RotateLeftX", 0, -360, 360);
  
  public Setting rotateRightY = create("Rotate Right Y", "RotateRightY", 0, -360, 360);
  
  public Setting xLeft = create("Left X", "LeftX", 0.0D, -50.0D, 50.0D);
  
  public Setting scaleRight = create("Scale Right", "ScaleRight", 7.0D, 0.0D, 50.0D);
  
  public Setting yRight = create("Right Y", "RightY", 0.0D, -50.0D, 50.0D);
  
  @EventHandler
  public Listener eventListener = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting custom_fov = create("FOV", "FOVSlider", 130, 10, 170);
  
  public Setting yLeft = create("Left Y", "LeftY", 0.0D, -50.0D, 50.0D);
  
  public Setting cancelEating = create("No Eat", "NoEat", false);
  
  public Setting rotateRightX = create("Rotate Right X", "RotateRightX", 0, -360, 360);
  
  public Setting rotateRightZ = create("Rotate Right Z", "RotateRightZ", 0, -360, 360);
  
  public Setting xRight = create("Right X", "RightX", 0.0D, -50.0D, 50.0D);
  
  public Setting zRight = create("Right Z", "RightZ", -50.0D, -50.0D, 50.0D);
  
  public void update() {
    (give up)null;
    mc.field_71474_y.field_74334_X = this.custom_fov.get_value(1);
  }
  
  public void enable() {
    (give up)null;
    this.fov = mc.field_71474_y.field_74334_X;
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  public ViewModel() {
    super(Category.render);
  }
  
  public void lambda$new$0(EventFirstPerson paramEventFirstPerson) {
    (give up)null;
    if (paramEventFirstPerson.getHandSide() == EnumHandSide.RIGHT) {
      GL11.glTranslatef((float)this.xRight.get_value(1.0D) / Float.intBitsToFloat(1.03367622E9F ^ 0x7F54A5D1), (float)this.yRight.get_value(1.0D) / Float.intBitsToFloat(1.06725152E9F ^ 0x7D54F72F), (float)this.zRight.get_value(1.0D) / Float.intBitsToFloat(1.03845882E9F ^ 0x7F2D9FB7));
      GL11.glRotatef((float)this.rotateRightX.get_value(1.0D), Float.intBitsToFloat(1.0972617E9F ^ 0x7EE6E2BF), Float.intBitsToFloat(2.13441523E9F ^ 0x7F3897BD), Float.intBitsToFloat(2.11182784E9F ^ 0x7DDFEF57));
      GL11.glRotatef((float)this.rotateRightY.get_value(1.0D), Float.intBitsToFloat(2.1084809E9F ^ 0x7DACDD7F), Float.intBitsToFloat(1.09244877E9F ^ 0x7E9D71DB), Float.intBitsToFloat(2.10546624E9F ^ 0x7D7EDD6F));
      GL11.glRotatef((float)this.rotateRightZ.get_value(1.0D), Float.intBitsToFloat(2.11656243E9F ^ 0x7E282DE3), Float.intBitsToFloat(2.11852262E9F ^ 0x7E4616F7), Float.intBitsToFloat(1.09085402E9F ^ 0x7E851C49));
      GL11.glScalef((float)this.scaleRight.get_value(1.0D) / Float.intBitsToFloat(1.01785325E9F ^ 0x7D8B353F), (float)this.scaleRight.get_value(1.0D) / Float.intBitsToFloat(1.04752211E9F ^ 0x7F4FEB22), (float)this.scaleRight.get_value(1.0D) / Float.intBitsToFloat(1.04020422E9F ^ 0x7F2041A9));
    } else if (paramEventFirstPerson.getHandSide() == EnumHandSide.LEFT) {
      GL11.glTranslatef((float)this.xLeft.get_value(1.0D) / Float.intBitsToFloat(1.03212877E9F ^ 0x7F4D0904), (float)this.yLeft.get_value(1.0D) / Float.intBitsToFloat(1.0365513E9F ^ 0x7F008485), (float)this.zLeft.get_value(1.0D) / Float.intBitsToFloat(1.03358029E9F ^ 0x7F532F15));
      GL11.glRotatef((float)this.rotateLeftX.get_value(1.0D), Float.intBitsToFloat(1.09533056E9F ^ 0x7EC96B11), Float.intBitsToFloat(2.13156992E9F ^ 0x7F0D2CE2), Float.intBitsToFloat(2.12949197E9F ^ 0x7EED77CB));
      GL11.glRotatef((float)this.rotateLeftY.get_value(1.0D), Float.intBitsToFloat(2.12244621E9F ^ 0x7E81F56F), Float.intBitsToFloat(1.10369792E9F ^ 0x7E4917F7), Float.intBitsToFloat(2.13498816E9F ^ 0x7F4155B7));
      GL11.glRotatef((float)this.rotateLeftZ.get_value(1.0D), Float.intBitsToFloat(2.12447283E9F ^ 0x7EA0E231), Float.intBitsToFloat(2.12784243E9F ^ 0x7ED44C67), Float.intBitsToFloat(1.09127949E9F ^ 0x7E8B9A47));
      GL11.glScalef((float)this.scaleLeft.get_value(1.0D) / Float.intBitsToFloat(1.07217318E9F ^ 0x7EC8109F), (float)this.scaleLeft.get_value(1.0D) / Float.intBitsToFloat(1.06861824E9F ^ 0x7E91D1FD), (float)this.scaleLeft.get_value(1.0D) / Float.intBitsToFloat(1.04422854E9F ^ 0x7F1DA9DD));
    } 
  }
  
  public void disable() {
    (give up)null;
    mc.field_71474_y.field_74334_X = this.fov;
    MinecraftForge.EVENT_BUS.unregister(this);
  }
}
