package me.obsidianbreaker.leux.client.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import net.minecraft.entity.Entity;

public class EntitySearch extends Module {
  public Setting llamas = create("Llamas", "Llamas", true);
  
  public Setting slimes = create("Slimes", "Slimes", false);
  
  public Setting donkeys = create("Donkeys", "Donkeys", true);
  
  public int delay;
  
  public Setting mules = create("Mules", "Mules", true);
  
  public void lambda$update$3(Entity paramEntity) {
    (give up)null;
    if (paramEntity instanceof net.minecraft.entity.passive.EntityDonkey && this.delay == 0) {
      MessageUtil.send_client_message("Found a donkey at: " + Math.round(paramEntity.field_70142_S) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(paramEntity.field_70137_T) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(paramEntity.field_70136_U));
      this.delay = 200;
    } 
    if (this.llamas.get_value(true))
      mc.field_71441_e.field_72996_f.forEach(this::lambda$null$2); 
  }
  
  public void lambda$null$1(Entity paramEntity) {
    (give up)null;
    if (paramEntity instanceof net.minecraft.entity.passive.EntityMule && this.delay == 0) {
      MessageUtil.send_client_message("Found a mule at: " + Math.round(paramEntity.field_70142_S) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(paramEntity.field_70137_T) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(paramEntity.field_70136_U));
      this.delay = 200;
    } 
    if (this.slimes.get_value(true))
      mc.field_71441_e.field_72996_f.forEach(this::lambda$null$0); 
  }
  
  public void lambda$null$0(Entity paramEntity) {
    (give up)null;
    if (paramEntity instanceof net.minecraft.entity.monster.EntitySlime && this.delay == 0) {
      MessageUtil.send_client_message("Found a slime at: " + Math.round(paramEntity.field_70142_S) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(paramEntity.field_70137_T) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(paramEntity.field_70136_U));
      this.delay = 200;
    } 
  }
  
  public EntitySearch() {
    super(Category.misc);
  }
  
  public void lambda$null$2(Entity paramEntity) {
    (give up)null;
    if (paramEntity instanceof net.minecraft.entity.passive.EntityLlama && this.delay == 0) {
      MessageUtil.send_client_message("Found a llama at: " + Math.round(paramEntity.field_70142_S) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(paramEntity.field_70137_T) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(paramEntity.field_70136_U));
      this.delay = 200;
    } 
    if (this.mules.get_value(true))
      mc.field_71441_e.field_72996_f.forEach(this::lambda$null$1); 
  }
  
  public void enable() {
    (give up)null;
    this.delay = 0;
  }
  
  public void update() {
    (give up)null;
    if (this.delay > 0)
      this.delay--; 
    if (this.donkeys.get_value(true))
      mc.field_71441_e.field_72996_f.forEach(this::lambda$update$3); 
  }
}
