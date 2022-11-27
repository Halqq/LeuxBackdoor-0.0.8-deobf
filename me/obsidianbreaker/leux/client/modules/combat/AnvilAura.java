package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.ArrayList;
import java.util.List;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.BlockInteractHelper;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AnvilAura extends Module {
  public boolean isSneaking = false;
  
  public boolean noMaterials = false;
  
  public Setting range = create("Range", "Range", 4, 0, 6);
  
  public Setting anti_crystal = create("AntiCrystal", "AntiCrystal", false);
  
  public Setting target = create("Target", "Target", "Nearest", combobox(new String[] { "Nearest", "Looking" }));
  
  public int delayTimeTicks = 0;
  
  public int oldSlot = -1;
  
  public Setting tick_delay = create("Tick Delay", "Tick Delay", 5, 1, 10);
  
  public Setting break_mode = create("Break Mode", "Break Mode", "Pickaxe", combobox(new String[] { "Pickaxe", "Feet", "None" }));
  
  public Double[][] sur_block;
  
  public boolean blockUp = false;
  
  public Setting h_distance = create("H Distance", "HDistance", 7, 1, 10);
  
  public Setting rotate = create("Rotate", "Rotate", true);
  
  public int offsetSteps = 0;
  
  public boolean enoughSpace = true;
  
  public boolean hasMoved = false;
  
  public EntityPlayer aimTarget;
  
  public static ArrayList to_place = new ArrayList();
  
  public int blocksPlaced = 0;
  
  public int noKick;
  
  public boolean isHole = true;
  
  public boolean pick_d = false;
  
  public Setting fail_stop = create("Fail Stop", "FailStop", 2, 1, 10);
  
  public double[] enemyCoords;
  
  public boolean firstRun = false;
  
  public Setting blocks_per_tick = create("Blocks Per Tick", "BPS", 5, 1, 10);
  
  public int[] slot_mat = new int[] { -1, -1, -1, -1 };
  
  public Setting chatMsg = create("Chat Messages", "messages", true);
  
  public int[][] model = new int[][] { { 1, 1, 0 }, { -1, 1, 0 }, { 0, 1, 1 }, { 0, 1, -1 } };
  
  public Setting fast_anvil = create("FastAnvil", "FastAnvil", true);
  
  public Setting min_h = create("Min H", "MinH", 3, 1, 10);
  
  public Setting decrease = create("Decrease", "Decrease", 4, 0, 8);
  
  public boolean createStructure() {
    (give up)null;
    if (this.break_mode.in("Feet"))
      to_place.add(new Vec3d(0.0D, 0.0D, 0.0D)); 
    to_place.add(new Vec3d(1.0D, 1.0D, 0.0D));
    to_place.add(new Vec3d(-1.0D, 1.0D, 0.0D));
    to_place.add(new Vec3d(0.0D, 1.0D, 1.0D));
    to_place.add(new Vec3d(0.0D, 1.0D, -1.0D));
    to_place.add(new Vec3d(1.0D, 2.0D, 0.0D));
    to_place.add(new Vec3d(-1.0D, 2.0D, 0.0D));
    to_place.add(new Vec3d(0.0D, 2.0D, 1.0D));
    to_place.add(new Vec3d(0.0D, 2.0D, -1.0D));
    int i = this.h_distance.get_value(1);
    for (double d1 = mc.field_71439_g.func_70032_d((Entity)this.aimTarget); d1 > this.decrease.get_value(1); d1 -= this.decrease.get_value(1))
      i--; 
    int j = (int)(mc.field_71439_g.field_70163_u - this.aimTarget.field_70163_u);
    if (j > 1)
      j = 2; 
    i += (int)(mc.field_71439_g.field_70163_u - this.aimTarget.field_70163_u);
    double d2 = Double.MAX_VALUE;
    (new double[3])[0] = -1.0D;
    (new double[3])[1] = -1.0D;
    (new double[3])[2] = -1.0D;
    new double[3];
    byte b = -1;
    byte b1 = 0;
    for (Double[] arrayOfDouble : this.sur_block) {
      (new double[3])[0] = arrayOfDouble[0].doubleValue();
      (new double[3])[1] = arrayOfDouble[1].doubleValue();
      (new double[3])[2] = arrayOfDouble[2].doubleValue();
      new double[3];
      double d;
      if ((d = mc.field_71439_g.func_174818_b(new BlockPos(arrayOfDouble[0].doubleValue(), arrayOfDouble[1].doubleValue(), arrayOfDouble[2].doubleValue()))) < d2) {
        d2 = d;
        b = b1;
      } 
      b1++;
    } 
    boolean bool = false;
    byte b2 = 1;
    while (get_block(this.enemyCoords[0], this.enemyCoords[1] + b2, this.enemyCoords[2]) instanceof net.minecraft.block.BlockAir && b2 < i) {
      if (!this.anti_crystal.get_value(true)) {
        to_place.add(new Vec3d(this.model[b][0], (this.model[b][1] + b2), this.model[b][2]));
        b2++;
        continue;
      } 
      byte b3 = 0;
      while (true) {
        4;
        to_place.add(new Vec3d(this.model[b3][0], (this.model[b3][1] + b2), this.model[b3][2]));
      } 
    } 
    if (!(get_block(this.enemyCoords[0], this.enemyCoords[1] + b2, this.enemyCoords[2]) instanceof net.minecraft.block.BlockAir))
      b2--; 
    if (b2 >= this.min_h.get_value(1))
      bool = true; 
    to_place.add(new Vec3d(0.0D, (this.model[b][1] + b2 - 1), 0.0D));
    return bool;
  }
  
  public EntityPlayer getPlayerFromName(String paramString) {
    (give up)null;
    List list = mc.field_71441_e.field_73010_i;
    for (EntityPlayer entityPlayer : list) {
      if (entityPlayer.func_146103_bH().getName().equals(paramString))
        return entityPlayer; 
    } 
    return null;
  }
  
  public static EntityPlayer findClosestTarget(double paramDouble, EntityPlayer paramEntityPlayer) {
    (give up)null;
    List list = mc.field_71441_e.field_73010_i;
    EntityPlayer entityPlayer = null;
    for (EntityPlayer entityPlayer1 : list) {
      if (basicChecksEntity((Entity)entityPlayer1))
        continue; 
      if (mc.field_71439_g.func_70032_d((Entity)entityPlayer1) > paramDouble)
        if (mc.field_71439_g.func_70032_d((Entity)entityPlayer1) > paramDouble || mc.field_71439_g.func_70032_d((Entity)entityPlayer1) >= mc.field_71439_g.func_70032_d((Entity)paramEntityPlayer))
          continue;  
      entityPlayer = entityPlayer1;
    } 
    return entityPlayer;
  }
  
  public static EntityPlayer findLookingPlayer(double paramDouble) {
    (give up)null;
    ArrayList<EntityPlayer> arrayList = new ArrayList();
    for (EntityPlayer entityPlayer1 : mc.field_71441_e.field_73010_i) {
      if (basicChecksEntity((Entity)entityPlayer1) || mc.field_71439_g.func_70032_d((Entity)entityPlayer1) > paramDouble)
        continue; 
      arrayList.add(entityPlayer1);
    } 
    EntityPlayer entityPlayer = null;
    Vec3d vec3d1 = mc.field_71439_g.func_174824_e(mc.func_184121_ak());
    Vec3d vec3d2 = mc.field_71439_g.func_70676_i(mc.func_184121_ak());
    2;
    byte b = 0;
    if (b < (int)paramDouble)
      for (byte b1 = 2;; b1--) {
        for (EntityPlayer entityPlayer1 : arrayList) {
          AxisAlignedBB axisAlignedBB = entityPlayer1.func_174813_aQ();
          double d1 = vec3d1.field_72450_a + vec3d2.field_72450_a * b + vec3d2.field_72450_a / b1;
          double d2 = vec3d1.field_72448_b + vec3d2.field_72448_b * b + vec3d2.field_72448_b / b1;
          double d3 = vec3d1.field_72449_c + vec3d2.field_72449_c * b + vec3d2.field_72449_c / b1;
          if (axisAlignedBB.field_72337_e >= d2 && axisAlignedBB.field_72338_b <= d2 && axisAlignedBB.field_72336_d >= d1 && axisAlignedBB.field_72340_a <= d1 && axisAlignedBB.field_72334_f >= d3 && axisAlignedBB.field_72339_c <= d3)
            entityPlayer = entityPlayer1; 
        } 
      }  
    return entityPlayer;
  }
  
  public boolean getMaterialsSlot() {
    (give up)null;
    boolean bool1 = false;
    boolean bool2 = false;
    if (this.break_mode.in("Feet"))
      bool1 = true; 
    if (this.break_mode.in("Pick"))
      bool2 = true; 
    byte b = 0;
    while (true) {
      9;
      ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b);
      if (itemStack != ItemStack.field_190927_a)
        if (itemStack.func_77973_b() instanceof ItemBlock) {
          Block block = ((ItemBlock)itemStack.func_77973_b()).func_179223_d();
          if (block instanceof net.minecraft.block.BlockObsidian) {
            this.slot_mat[0] = b;
          } else if (block instanceof net.minecraft.block.BlockAnvil) {
            this.slot_mat[1] = b;
          } else {
          
          } 
        }  
    } 
  }
  
  public AnvilAura() {
    super(Category.combat);
  }
  
  public boolean placeBlock(BlockPos paramBlockPos, int paramInt) {
    (give up)null;
    Block block = mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c();
    EnumFacing enumFacing = BlockInteractHelper.getPlaceableSide(paramBlockPos);
    if (paramInt == to_place.size() - 1 && block instanceof net.minecraft.block.BlockAnvil) {
      mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, paramBlockPos, enumFacing));
      this.noKick++;
    } 
    return (!(block instanceof net.minecraft.block.BlockAir) && !(block instanceof net.minecraft.block.BlockLiquid)) ? false : false;
  }
  
  public Block get_block(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    return mc.field_71441_e.func_180495_p(new BlockPos(paramDouble1, paramDouble2, paramDouble3)).func_177230_c();
  }
  
  public static boolean basicChecksEntity(Entity paramEntity) {
    (give up)null;
    return (paramEntity == mc.field_71439_g || FriendUtil.isFriend(paramEntity.func_70005_c_()) || paramEntity.field_70128_L);
  }
  
  public boolean is_in_hole() {
    (give up)null;
    this.sur_block = new Double[][] { { Double.valueOf(this.aimTarget.field_70165_t + 1.0D), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v) }, { Double.valueOf(this.aimTarget.field_70165_t - 1.0D), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v) }, { Double.valueOf(this.aimTarget.field_70165_t), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v + 1.0D) }, { Double.valueOf(this.aimTarget.field_70165_t), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v - 1.0D) } };
    this.enemyCoords = new double[] { this.aimTarget.field_70165_t, this.aimTarget.field_70163_u, this.aimTarget.field_70161_v };
    return (!(get_block(this.sur_block[0][0].doubleValue(), this.sur_block[0][1].doubleValue(), this.sur_block[0][2].doubleValue()) instanceof net.minecraft.block.BlockAir) && !(get_block(this.sur_block[1][0].doubleValue(), this.sur_block[1][1].doubleValue(), this.sur_block[1][2].doubleValue()) instanceof net.minecraft.block.BlockAir) && !(get_block(this.sur_block[2][0].doubleValue(), this.sur_block[2][1].doubleValue(), this.sur_block[2][2].doubleValue()) instanceof net.minecraft.block.BlockAir) && !(get_block(this.sur_block[3][0].doubleValue(), this.sur_block[3][1].doubleValue(), this.sur_block[3][2].doubleValue()) instanceof net.minecraft.block.BlockAir));
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g == null) {
      set_disable();
      return;
    } 
    if (this.firstRun) {
      if (this.target.in("Nearest")) {
        this.aimTarget = findClosestTarget(this.range.get_value(1), this.aimTarget);
      } else if (this.target.in("Looking")) {
        this.aimTarget = findLookingPlayer(this.range.get_value(1));
      } 
      if (this.aimTarget == null)
        return; 
      this.firstRun = false;
      if (getMaterialsSlot()) {
        if (is_in_hole()) {
          this.enemyCoords = new double[] { this.aimTarget.field_70165_t, this.aimTarget.field_70163_u, this.aimTarget.field_70161_v };
          this.enoughSpace = createStructure();
        } else {
          this.isHole = false;
        } 
      } else {
        this.noMaterials = true;
      } 
    } else {
      if (this.delayTimeTicks < this.tick_delay.get_value(1)) {
        this.delayTimeTicks++;
        return;
      } 
      this.delayTimeTicks = 0;
      if ((int)this.enemyCoords[0] != (int)this.aimTarget.field_70165_t || (int)this.enemyCoords[2] != (int)this.aimTarget.field_70161_v)
        this.hasMoved = true; 
      if (!(get_block(this.enemyCoords[0], this.enemyCoords[1] + 2.0D, this.enemyCoords[2]) instanceof net.minecraft.block.BlockAir) || !(get_block(this.enemyCoords[0], this.enemyCoords[1] + 3.0D, this.enemyCoords[2]) instanceof net.minecraft.block.BlockAir))
        this.blockUp = true; 
    } 
    this.blocksPlaced = 0;
    if (this.noMaterials || !this.isHole || !this.enoughSpace || this.hasMoved || this.blockUp) {
      set_disable();
      return;
    } 
    this.noKick = 0;
    while (this.blocksPlaced <= this.blocks_per_tick.get_value(1)) {
      int i = to_place.size();
      if (this.offsetSteps >= i) {
        this.offsetSteps = 0;
        break;
      } 
      BlockPos blockPos1 = new BlockPos(to_place.get(this.offsetSteps));
      BlockPos blockPos2 = (new BlockPos(this.aimTarget.func_174791_d())).func_177982_a(blockPos1.func_177958_n(), blockPos1.func_177956_o(), blockPos1.func_177952_p());
      boolean bool = true;
      if (this.offsetSteps > 0 && this.offsetSteps < to_place.size() - 1)
        for (Entity entity : mc.field_71441_e.func_72839_b(null, new AxisAlignedBB(blockPos2))) {
          if (entity instanceof EntityPlayer) {
            bool = false;
            break;
          } 
        }  
      if (placeBlock(blockPos2, this.offsetSteps))
        this.blocksPlaced++; 
      this.offsetSteps++;
      if (this.isSneaking) {
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
        this.isSneaking = false;
      } 
      if (this.noKick == this.fail_stop.get_value(1))
        break; 
    } 
  }
  
  public void disable() {
    (give up)null;
    if (mc.field_71439_g == null)
      return; 
    if (this.chatMsg.get_value(true)) {
      MessageUtil.send_client_error_message("AutoAnvil off");
      if (this.noMaterials) {
        MessageUtil.send_client_error_message("No Materials Detected");
      } else if (!this.isHole) {
        MessageUtil.send_client_error_message("Enemy is not in a hole");
      } else if (!this.enoughSpace) {
        MessageUtil.send_client_error_message("Not enough space");
      } else if (this.hasMoved) {
        MessageUtil.send_client_error_message("Enemy moved away from the hole");
      } else if (this.blockUp) {
        MessageUtil.send_client_error_message("Enemy head blocked");
      } else {
        MessageUtil.send_client_error_message("");
      } 
    } 
    if (this.isSneaking) {
      mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
      this.isSneaking = false;
    } 
    if (this.oldSlot != mc.field_71439_g.field_71071_by.field_70461_c && this.oldSlot != -1) {
      mc.field_71439_g.field_71071_by.field_70461_c = this.oldSlot;
      this.oldSlot = -1;
    } 
    this.noMaterials = false;
    this.firstRun = true;
  }
  
  public void enable() {
    (give up)null;
    if (this.break_mode.in("Pickaxe"))
      this.pick_d = true; 
    this.blocksPlaced = 0;
    this.isHole = true;
    false;
    this.blockUp = false;
    this.hasMoved = false;
    this.firstRun = true;
    this.slot_mat = new int[] { -1, -1, -1, -1 };
    to_place = new ArrayList();
    if (mc.field_71439_g == null) {
      set_disable();
      return;
    } 
    this.oldSlot = mc.field_71439_g.field_71071_by.field_70461_c;
  }
}
