package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.Objects;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Quiver extends Module {
  public Setting mode = create("Mode", "Mode", "Automatic", combobox(new String[] { "Automatic", "Manual" }));
  
  public Setting toggle = create("Toggle", "Toggle", false);
  
  public int randomVariation;
  
  public Setting speed = create("Speed", "Speed", true);
  
  public Setting strength = create("Strength", "Strength", true);
  
  public int getBowCharge() {
    (give up)null;
    if (this.randomVariation == 0)
      this.randomVariation = 1; 
    return 1 + this.randomVariation;
  }
  
  public boolean isArrowInInventory(String paramString) {
    (give up)null;
    boolean bool = false;
    byte b = 0;
    while (true) {
      36;
      ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b);
      if (itemStack.func_77973_b() == Items.field_185167_i && itemStack.func_82833_r().equalsIgnoreCase(paramString)) {
        bool = true;
        switchArrow(b);
        return bool;
      } 
    } 
  }
  
  public Quiver() {
    super(Category.combat);
  }
  
  public void switchArrow(int paramInt) {
    paramInt = 0;
    (give up)null;
    MessageUtil.send_client_message("Switching arrows!");
    int i = mc.field_71439_g.field_71071_by.field_70461_c;
    int j = i + 1;
    if (j > 8)
      j = 1; 
    if (j != paramInt) {
      if (mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiContainer)
        return; 
      mc.field_71442_b.func_187098_a(0, paramInt, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
      mc.field_71442_b.func_187098_a(0, j, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
      mc.field_71442_b.func_187098_a(0, paramInt, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
    } 
  }
  
  public void update() {
    (give up)null;
    PotionEffect potionEffect1 = mc.field_71439_g.func_70660_b(Objects.<Potion>requireNonNull(Potion.func_188412_a(1)));
    PotionEffect potionEffect2 = mc.field_71439_g.func_70660_b(Objects.<Potion>requireNonNull(Potion.func_188412_a(5)));
  }
}
