package me.obsidianbreaker.leux.client.modules.movement;

import give up;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.Wrapper;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoSlowByPass extends Module {
  public boolean sneaking;
  
  @SubscribeEvent
  @SubscribeEvent
  public void onUseItem(LivingEntityUseItemEvent paramLivingEntityUseItemEvent) {
    (give up)null;
    if (!this.sneaking) {
      (Wrapper.getPlayer()).field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)Wrapper.getPlayer(), CPacketEntityAction.Action.START_SNEAKING));
      this.sneaking = true;
    } 
  }
  
  public NoSlowByPass() {
    super(Category.movement);
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71441_e != null) {
      Item item = Wrapper.getPlayer().func_184607_cu().func_77973_b();
      if (this.sneaking && ((!Wrapper.getPlayer().func_184587_cr() && item instanceof net.minecraft.item.ItemFood) || item instanceof net.minecraft.item.ItemBow || item instanceof net.minecraft.item.ItemPotion || !(item instanceof net.minecraft.item.ItemFood) || !(item instanceof net.minecraft.item.ItemBow) || !(item instanceof net.minecraft.item.ItemPotion))) {
        (Wrapper.getPlayer()).field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)Wrapper.getPlayer(), CPacketEntityAction.Action.STOP_SNEAKING));
        this.sneaking = false;
      } 
    } 
  }
}
