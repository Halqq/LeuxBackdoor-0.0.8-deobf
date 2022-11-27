package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.ArrayList;
import java.util.Iterator;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.BlockUtil;
import me.obsidianbreaker.leux.client.util.InventoryUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;

public class AntiCrystal extends Module {
  public Setting delay = create("Delay", "Delay", 2, 1, 20);
  
  public Setting rotate = create("Rotate", "Rotate", true);
  
  public Setting range = create("Range", "Range", 5, 0, 10);
  
  public Setting switch_mode = create("Switch Mode", "SwitchMode", "Normal", combobox(new String[] { "Ghost", "Normal", "None" }));
  
  public int index = 0;
  
  public Setting disable = create("Toggle", "Toggle", false);
  
  public void update() {
    (give up)null;
    if (this.index > 2000)
      this.index = 0; 
    for (EntityEnderCrystal entityEnderCrystal1 : getNonPlaced()) {
      EntityEnderCrystal entityEnderCrystal2 = entityEnderCrystal1;
      if (this.index % this.delay.get_value(1) != 0)
        continue; 
      if (this.switch_mode.in("Normal")) {
        InventoryUtil.switchToSlot(Item.func_150898_a(Blocks.field_150452_aw));
      } else if (this.switch_mode.in("Ghost")) {
        InventoryUtil.switchToSlotGhost(Item.func_150898_a(Blocks.field_150452_aw));
      } else {
        this.switch_mode.in("None");
      } 
      if (mc.field_71439_g.func_184586_b(EnumHand.MAIN_HAND).func_77973_b() != Item.func_150898_a(Blocks.field_150452_aw))
        continue; 
      BlockUtil.betterPlaceBlock(entityEnderCrystal2.func_180425_c(), this.rotate.get_value(true));
      if (this.disable.get_value(true))
        set_disable(); 
      return;
    } 
  }
  
  public ArrayList getCrystals() {
    (give up)null;
    ArrayList<Entity> arrayList = new ArrayList();
    Iterator<Entity> iterator = mc.field_71441_e.func_72910_y().iterator();
    while (true) {
      Entity entity;
      if (iterator.hasNext()) {
        entity = iterator.next();
        if (!(entity instanceof EntityEnderCrystal))
          continue; 
      } else {
        return arrayList;
      } 
      arrayList.add(entity);
    } 
  }
  
  public AntiCrystal() {
    super(Category.combat);
  }
  
  public ArrayList getInRange() {
    (give up)null;
    ArrayList<EntityEnderCrystal> arrayList = new ArrayList();
    for (EntityEnderCrystal entityEnderCrystal1 : getCrystals()) {
      EntityEnderCrystal entityEnderCrystal2 = entityEnderCrystal1;
      if (mc.field_71439_g.func_70032_d((Entity)entityEnderCrystal2) > this.range.get_value(1))
        continue; 
      arrayList.add(entityEnderCrystal2);
    } 
    return arrayList;
  }
  
  public ArrayList getNonPlaced() {
    (give up)null;
    ArrayList<EntityEnderCrystal> arrayList = new ArrayList();
    Iterator<Object> iterator = getInRange().iterator();
    while (true) {
      EntityEnderCrystal entityEnderCrystal;
      if (iterator.hasNext()) {
        EntityEnderCrystal entityEnderCrystal1 = (EntityEnderCrystal)iterator.next();
        entityEnderCrystal = entityEnderCrystal1;
        if (mc.field_71441_e.func_180495_p(entityEnderCrystal.func_180425_c()).func_177230_c() == Blocks.field_150452_aw)
          continue; 
      } else {
        return arrayList;
      } 
      arrayList.add(entityEnderCrystal);
    } 
  }
}
