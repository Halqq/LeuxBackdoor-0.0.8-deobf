package me.obsidianbreaker.leux.client.modules.render;

import give up;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.CrystalUtil;
import me.obsidianbreaker.leux.client.util.EntityUtil;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class FuckedDetector extends Module {
  public Setting draw_own = create("Draw Own", "FuckedDrawOwn", false);
  
  public Setting r = create("R", "FuckedR", 255, 0, 255);
  
  public Setting render_mode = create("Render Mode", "FuckedRenderMode", "Pretty", combobox(new String[] { "Pretty", "Solid", "Outline" }));
  
  public Setting b = create("B", "FuckedB", 255, 0, 255);
  
  public Setting a = create("A", "FuckedA", 100, 0, 255);
  
  public Setting draw_friends = create("Draw Friends", "FuckedDrawFriends", false);
  
  public Setting g = create("G", "FuckedG", 255, 0, 255);
  
  public Set fucked_players = new HashSet();
  
  public boolean solid;
  
  public boolean outline;
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    if (this.render_mode.in("Pretty")) {
      this.outline = true;
      this.solid = true;
    } 
    if (this.render_mode.in("Solid")) {
      this.outline = false;
      this.solid = true;
    } 
    if (this.render_mode.in("Outline")) {
      this.outline = true;
      this.solid = false;
    } 
    Iterator<BlockPos> iterator = this.fucked_players.iterator();
    if (iterator.hasNext()) {
      BlockPos blockPos = iterator.next();
      return;
    } 
  }
  
  public void enable() {
    (give up)null;
    this.fucked_players.clear();
  }
  
  public boolean is_fucked(EntityPlayer paramEntityPlayer) {
    (give up)null;
    BlockPos blockPos = new BlockPos(paramEntityPlayer.field_70165_t, paramEntityPlayer.field_70163_u - 1.0D, paramEntityPlayer.field_70161_v);
    return (CrystalUtil.canPlaceCrystal(blockPos.func_177968_d()) || (CrystalUtil.canPlaceCrystal(blockPos.func_177968_d().func_177968_d()) && mc.field_71441_e.func_180495_p(blockPos.func_177982_a(0, 1, 1)).func_177230_c() == Blocks.field_150350_a)) ? true : ((CrystalUtil.canPlaceCrystal(blockPos.func_177974_f()) || (CrystalUtil.canPlaceCrystal(blockPos.func_177974_f().func_177974_f()) && mc.field_71441_e.func_180495_p(blockPos.func_177982_a(1, 1, 0)).func_177230_c() == Blocks.field_150350_a)) ? true : ((CrystalUtil.canPlaceCrystal(blockPos.func_177976_e()) || (CrystalUtil.canPlaceCrystal(blockPos.func_177976_e().func_177976_e()) && mc.field_71441_e.func_180495_p(blockPos.func_177982_a(-1, 1, 0)).func_177230_c() == Blocks.field_150350_a)) ? true : ((CrystalUtil.canPlaceCrystal(blockPos.func_177978_c()) || (CrystalUtil.canPlaceCrystal(blockPos.func_177978_c().func_177978_c()) && mc.field_71441_e.func_180495_p(blockPos.func_177982_a(0, 1, -1)).func_177230_c() == Blocks.field_150350_a)))));
  }
  
  public void set_fucked_players() {
    (give up)null;
    this.fucked_players.clear();
    for (EntityPlayer entityPlayer : mc.field_71441_e.field_73010_i) {
      if (!EntityUtil.isLiving((Entity)entityPlayer) || entityPlayer.func_110143_aJ() <= Float.intBitsToFloat(2.1314729E9F ^ 0x7F0BB20C) || !is_fucked(entityPlayer) || (FriendUtil.isFriend(entityPlayer.func_70005_c_()) && !this.draw_friends.get_value(true)) || (entityPlayer == mc.field_71439_g && !this.draw_own.get_value(true)))
        continue; 
      this.fucked_players.add(new BlockPos(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v));
    } 
  }
  
  public FuckedDetector() {
    super(Category.render);
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71441_e == null)
      return; 
    set_fucked_players();
  }
}
