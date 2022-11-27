package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import me.obsidianbreaker.leux.client.event.events.EventPlayerTravel;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

public class MiddleClickPearl extends Module {
  public boolean clicked;
  
  @EventHandler
  public Listener listener = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public void lambda$new$0(EventPlayerTravel paramEventPlayerTravel) {
    (give up)null;
    if (mc.field_71462_r == null && Mouse.isButtonDown(2)) {
      int i;
      if (!this.clicked && (i = findPearlInHotbar()) != -1) {
        int j = mc.field_71439_g.field_71071_by.field_70461_c;
        mc.field_71439_g.field_71071_by.field_70461_c = i;
        mc.field_71442_b.func_187101_a((EntityPlayer)mc.field_71439_g, (World)mc.field_71441_e, EnumHand.MAIN_HAND);
        mc.field_71439_g.field_71071_by.field_70461_c = j;
      } 
      this.clicked = true;
    } else {
      this.clicked = false;
    } 
  }
  
  public boolean isItemStackPearl(ItemStack paramItemStack) {
    (give up)null;
    return paramItemStack.func_77973_b() instanceof net.minecraft.item.ItemEnderPearl;
  }
  
  public int findPearlInHotbar() {
    (give up)null;
    for (byte b = 0;; b++) {
      if (InventoryPlayer.func_184435_e(b)) {
        if (isItemStackPearl(mc.field_71439_g.field_71071_by.func_70301_a(b)))
          return b; 
      } else {
        return -1;
      } 
    } 
  }
  
  public MiddleClickPearl() {
    super(Category.misc);
  }
}
