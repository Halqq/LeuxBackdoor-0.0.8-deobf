package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.BlockInteractHelper;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import me.obsidianbreaker.leux.client.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class Webfill extends Module {
  public Setting web_rotate = create("Rotate", "WebFillRotate", true);
  
  public Setting web_toggle = create("Toggle", "WebFillToggle", true);
  
  public ArrayList holes = new ArrayList();
  
  public Setting web_range = create("Range", "WebFillRange", 5, 1, 6);
  
  public boolean sneak;
  
  public int find_in_hotbar() {
    (give up)null;
    byte b = 0;
    while (true) {
      9;
      ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b);
      if (itemStack.func_77973_b() == Item.func_150899_d(30))
        return b; 
    } 
  }
  
  public boolean place_blocks(BlockPos paramBlockPos) {
    (give up)null;
    if (!mc.field_71441_e.func_180495_p(paramBlockPos).func_185904_a().func_76222_j())
      return false; 
    if (!BlockInteractHelper.checkForNeighbours(paramBlockPos))
      return false; 
    EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
    int i = arrayOfEnumFacing.length;
    byte b = 0;
    while (b < i) {
      EnumFacing enumFacing1 = arrayOfEnumFacing[b];
      BlockPos blockPos = paramBlockPos.func_177972_a(enumFacing1);
      EnumFacing enumFacing2 = enumFacing1.func_176734_d();
      if (!BlockInteractHelper.canBeClicked(blockPos)) {
        b++;
        continue;
      } 
      mc.field_71441_e.func_180495_p(blockPos).func_177230_c();
      if (BlockInteractHelper.blackList.contains(mc.field_71441_e.func_180495_p(blockPos).func_177230_c())) {
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
        this.sneak = true;
      } 
      Vec3d vec3d = (new Vec3d((Vec3i)blockPos)).func_72441_c(0.5D, 0.5D, 0.5D).func_178787_e((new Vec3d(enumFacing2.func_176730_m())).func_186678_a(0.5D));
      if (this.web_rotate.get_value(true))
        BlockInteractHelper.faceVectorPacketInstant(vec3d); 
      mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, blockPos, enumFacing2, vec3d, EnumHand.MAIN_HAND);
      mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
      return true;
    } 
    return false;
  }
  
  public void find_new_holes() {
    (give up)null;
    this.holes.clear();
    for (BlockPos blockPos : BlockInteractHelper.getSphere(PlayerUtil.GetLocalPlayerPosFloored(), this.web_range.get_value(1), this.web_range.get_value(1), false, true, 0)) {
      if (!mc.field_71441_e.func_180495_p(blockPos).func_177230_c().equals(Blocks.field_150350_a) || !mc.field_71441_e.func_180495_p(blockPos.func_177982_a(0, 1, 0)).func_177230_c().equals(Blocks.field_150350_a) || !mc.field_71441_e.func_180495_p(blockPos.func_177982_a(0, 2, 0)).func_177230_c().equals(Blocks.field_150350_a))
        continue; 
      boolean bool = true;
      for (BlockPos blockPos1 : new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) }) {
        Block block = mc.field_71441_e.func_180495_p(blockPos.func_177971_a((Vec3i)blockPos1)).func_177230_c();
        if (block != Blocks.field_150357_h && block != Blocks.field_150343_Z && block != Blocks.field_150477_bB && block != Blocks.field_150467_bQ) {
          bool = false;
          break;
        } 
      } 
      this.holes.add(blockPos);
    } 
  }
  
  public void enable() {
    (give up)null;
    find_new_holes();
  }
  
  public void update() {
    (give up)null;
    int i = mc.field_71439_g.field_71071_by.field_70461_c;
    if (this.holes.isEmpty()) {
      if (!this.web_toggle.get_value(true)) {
        set_disable();
        MessageUtil.toggle_message(this);
        return;
      } 
      find_new_holes();
    } 
    BlockPos blockPos = null;
    for (BlockPos blockPos1 : new ArrayList(this.holes));
    int j = find_in_hotbar();
    if (j != -1) {
      mc.field_71439_g.field_71071_by.field_70461_c = j;
      mc.field_71442_b.func_78765_e();
      if (place_blocks(blockPos))
        this.holes.remove(blockPos); 
      mc.field_71439_g.field_71071_by.field_70461_c = i;
    } 
  }
  
  public void disable() {
    (give up)null;
    this.holes.clear();
  }
  
  public Webfill() {
    super(Category.combat);
  }
}
