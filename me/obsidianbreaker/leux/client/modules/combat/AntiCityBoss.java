package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.BlockInteractHelper;
import me.obsidianbreaker.leux.client.util.BlockUtil;
import me.obsidianbreaker.leux.client.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class AntiCityBoss extends Module {
  public Setting rotate = create("Rotate", "AntiCityBossRotate", false);
  
  public Setting swing = create("Swing", "AntiCityBossSwing", "Mainhand", combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
  
  public void enable() {
    (give up)null;
    if (find_in_hotbar() == -1)
      set_disable(); 
  }
  
  public int find_in_hotbar() {
    (give up)null;
    byte b = 0;
    while (true) {
      9;
      ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b);
      if (itemStack != ItemStack.field_190927_a && itemStack.func_77973_b() instanceof ItemBlock) {
        Block block = ((ItemBlock)itemStack.func_77973_b()).func_179223_d();
        if (block instanceof net.minecraft.block.BlockEnderChest)
          return b; 
        if (block instanceof net.minecraft.block.BlockObsidian)
          return b; 
      } 
    } 
  }
  
  public AntiCityBoss() {
    super(Category.combat);
  }
  
  public void update() {
    (give up)null;
    int i = find_in_hotbar();
    if (i == -1)
      return; 
    BlockPos blockPos1 = PlayerUtil.GetLocalPlayerPosFloored();
    ArrayList<BlockPos> arrayList = new ArrayList();
    switch (AntiCityBoss$1.$SwitchMap$me$obsidianbreaker$leux$client$util$PlayerUtil$FacingDirection[PlayerUtil.GetFacing().ordinal()]) {
      case 1:
        arrayList.add(blockPos1.func_177974_f().func_177974_f());
        arrayList.add(blockPos1.func_177974_f().func_177974_f().func_177984_a());
        arrayList.add(blockPos1.func_177974_f().func_177974_f().func_177974_f());
        arrayList.add(blockPos1.func_177974_f().func_177974_f().func_177974_f().func_177984_a());
        break;
      case 2:
        arrayList.add(blockPos1.func_177978_c().func_177978_c());
        arrayList.add(blockPos1.func_177978_c().func_177978_c().func_177984_a());
        arrayList.add(blockPos1.func_177978_c().func_177978_c().func_177978_c());
        arrayList.add(blockPos1.func_177978_c().func_177978_c().func_177978_c().func_177984_a());
        break;
      case 3:
        arrayList.add(blockPos1.func_177968_d().func_177968_d());
        arrayList.add(blockPos1.func_177968_d().func_177968_d().func_177984_a());
        arrayList.add(blockPos1.func_177968_d().func_177968_d().func_177968_d());
        arrayList.add(blockPos1.func_177968_d().func_177968_d().func_177968_d().func_177984_a());
        break;
      case 4:
        arrayList.add(blockPos1.func_177976_e().func_177976_e());
        arrayList.add(blockPos1.func_177976_e().func_177976_e().func_177984_a());
        arrayList.add(blockPos1.func_177976_e().func_177976_e().func_177976_e());
        arrayList.add(blockPos1.func_177976_e().func_177976_e().func_177976_e().func_177984_a());
        break;
    } 
    BlockPos blockPos2 = null;
    for (BlockPos blockPos : arrayList) {
      BlockInteractHelper.ValidResult validResult = BlockInteractHelper.valid(blockPos);
      if (validResult != BlockInteractHelper.ValidResult.Ok);
    } 
    BlockUtil.placeBlock(blockPos2, find_in_hotbar(), this.rotate.get_value(true), this.rotate.get_value(true), this.swing);
  }
}
