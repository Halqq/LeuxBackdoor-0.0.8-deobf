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
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class PistonAura extends Module {
  public Setting maxYincr = create("Max Y", "AutoPistonMaxY", 3, 0, 5);
  
  public Setting blocksPerTick = create("Blocks Per Tick", "AutoPistonBPS", 4, 0, 20);
  
  public int[] meCoordsInt;
  
  public long endTime;
  
  public boolean deadPl;
  
  public boolean hasMoved = false;
  
  public int stage;
  
  public Setting blockPlayer = create("Block Player", "AutoPistonBlockPlayer", true);
  
  public double CrystalDeltaBreak = this.crystalDeltaBreak.get_value(1) * 0.1D;
  
  public Setting confirmPlace = create("Confirm Place", "AutoPistonConfirmPlace", true);
  
  public Setting midHitDelay = create("Mid HitDelay", "AutoPistonMidHitDelay", 5, 0, 20);
  
  public boolean broken;
  
  public int oldSlot = -1;
  
  public Setting allowCheapMode = create("Cheap Mode", "AutoPistonCheapMode", false);
  
  public Setting rotate = create("Rotate", "AutoPistonRotate", false);
  
  public Setting crystalDeltaBreak = create("Center Break", "AutoPistonCenterBreak", 1, 0, 5);
  
  public PistonAura$structureTemp toPlace;
  
  public Setting hitDelay = create("Hit Delay", "AutoPistonHitDelay", 2, 0, 20);
  
  public long startTime;
  
  public Setting pistonDelay = create("Piston Delay", "AutoPistonPistonDelay", 2, 0, 20);
  
  public Setting breakType = create("Break Types", "AutoPistonBreakTypes", "Swing", combobox(new String[] { "Swing", "Packet" }));
  
  public int delayTimeTicks;
  
  public int[] slot_mat;
  
  public static double yaw;
  
  public Setting betterPlacement = create("Better Place", "AutoPistonBetterPlace", true);
  
  public boolean enoughSpace = true;
  
  public Setting bypassObsidian = create("Bypass", "AutoPistonBypass", false);
  
  public boolean isHole = true;
  
  public Setting confirmBreak = create("Confirm Break", "AutoPistonConfirmBreak", true);
  
  public Setting stuckDetector = create("Stuck Check", "AutoPistonStuckDetector", 35, 0, 200);
  
  public Setting placeMode = create("Place Mode", "AutoPistonPlaceMode", "Torch", combobox(new String[] { "Block", "Torch", "Both" }));
  
  public boolean noMaterials = false;
  
  public Setting range = create("Range", "AutoPistonRange", 4.91D, 0.0D, 6.0D);
  
  public int hitTryTick;
  
  public EntityPlayer aimTarget;
  
  public int stuck = 0;
  
  public boolean rotationPlayerMoved;
  
  public double[] enemyCoordsDouble;
  
  public boolean brokenCrystalBug;
  
  public boolean fastModeActive = false;
  
  public Setting startDelay = create("Start Delay", "AutoPistonStartDelay", 4, 0, 20);
  
  public boolean brokenRedstoneTorch;
  
  public static boolean isSpoofingAngles;
  
  public Setting crystalDelay = create("Crystal Delay", "AutoPistonCrystalDelay", 2, 0, 20);
  
  public int[] enemyCoordsInt;
  
  public boolean redstoneBlockMode = false;
  
  public int[][] disp_surblock = new int[][] { { 1, 0, 0 }, { -1, 0, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
  
  public int[] delayTable;
  
  public boolean isSneaking = false;
  
  public Setting supBlocksDelay = create("Surround Delay", "AutoPistonSurroundDelay", 4, 0, 20);
  
  public int nCrystal;
  
  public boolean yUnder = false;
  
  public Setting target = create("Target Mode", "AutoPistonTargetMode", "Nearest", combobox(new String[] { "Nearest", "Looking" }));
  
  public Double[][] sur_block = new Double[4][3];
  
  public Setting antiWeakness = create("Anti Weakness", "AutoPistonAntiWeakness", false);
  
  public static double pitch;
  
  public Setting chatMsg = create("Chat Messages", "AutoPistongChatMSG", true);
  
  public static boolean someoneInCoords(double paramDouble1, double paramDouble2) {
    (give up)null;
    int i = (int)paramDouble1;
    int j = (int)paramDouble2;
    List list = mc.field_71441_e.field_73010_i;
    for (EntityPlayer entityPlayer : list) {
      if ((int)entityPlayer.field_70165_t == i && (int)entityPlayer.field_70161_v == j)
        return true; 
    } 
    return false;
  }
  
  public boolean checkPistonPlace() {
    (give up)null;
    BlockPos blockPos = compactBlockPos(1);
    if (!(get_block(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p()) instanceof net.minecraft.block.BlockPistonBase)) {
      this.stage--;
      return false;
    } 
    return true;
  }
  
  public static double[] calculateLookAt(double paramDouble1, double paramDouble2, double paramDouble3, EntityPlayer paramEntityPlayer) {
    (give up)null;
    double d1 = paramEntityPlayer.field_70165_t - paramDouble1;
    double d2 = paramEntityPlayer.field_70163_u - paramDouble2;
    double d3 = paramEntityPlayer.field_70161_v - paramDouble3;
    double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
    d1 /= d4;
    d2 /= d4;
    d3 /= d4;
    double d5 = Math.asin(d2);
    double d6 = Math.atan2(d3, d1);
    d5 = d5 * 180.0D / Math.PI;
    d6 = d6 * 180.0D / Math.PI;
    d6 += 90.0D;
    return new double[] { d6, d5 };
  }
  
  public boolean createStructure() {
    (give up)null;
    PistonAura$structureTemp pistonAura$structureTemp = new PistonAura$structureTemp(Double.MAX_VALUE, 0, null);
    if (this.meCoordsInt[1] - this.enemyCoordsInt[1] > -1 && this.meCoordsInt[1] - this.enemyCoordsInt[1] <= this.maxYincr.get_value(1))
      for (byte b = 1;; b--) {
        if (pistonAura$structureTemp.to_place == null) {
          int i = 0;
          ArrayList<Vec3d> arrayList = new ArrayList();
          while (this.meCoordsInt[1] > this.enemyCoordsInt[1] + i) {
            i++;
            for (int[] arrayOfInt : this.disp_surblock)
              arrayList.add(new Vec3d(arrayOfInt[0], i, arrayOfInt[2])); 
          } 
          i += b;
          byte b1 = -1;
          for (Double[] arrayOfDouble : this.sur_block) {
            b1++;
            double[] arrayOfDouble1 = { arrayOfDouble[0].doubleValue(), arrayOfDouble[1].doubleValue() + i, arrayOfDouble[2].doubleValue() };
            int[] arrayOfInt = { this.disp_surblock[b1][0], this.disp_surblock[b1][1] + i, this.disp_surblock[b1][2] };
            double d;
            if ((d = mc.field_71439_g.func_70011_f(arrayOfDouble1[0], arrayOfDouble1[1], arrayOfDouble1[2])) < pistonAura$structureTemp.distance && get_block(arrayOfDouble1[0], arrayOfDouble1[1], arrayOfDouble1[2]) instanceof net.minecraft.block.BlockAir && get_block(arrayOfDouble1[0], arrayOfDouble1[1] + 1.0D, arrayOfDouble1[2]) instanceof net.minecraft.block.BlockAir && !someoneInCoords(arrayOfDouble1[0], arrayOfDouble1[2])) {
              double[] arrayOfDouble2 = new double[3];
              int[] arrayOfInt1 = new int[3];
              if (this.rotate.get_value(true) || !this.betterPlacement.get_value(true)) {
                arrayOfDouble2 = new double[] { arrayOfDouble1[0] + this.disp_surblock[b1][0], arrayOfDouble1[1], arrayOfDouble1[2] + this.disp_surblock[b1][2] };
                Block block;
                if (block = get_block(arrayOfDouble2[0], arrayOfDouble2[1], arrayOfDouble2[2]) instanceof net.minecraft.block.BlockPistonBase != block instanceof net.minecraft.block.BlockAir && !someoneInCoords(arrayOfDouble2[0], arrayOfDouble2[2])) {
                  arrayOfInt1 = new int[] { arrayOfInt[0] * 2, arrayOfInt[1], arrayOfInt[2] * 2 };
                  if (this.rotate.get_value(true)) {
                    int[] arrayOfInt3 = { (int)arrayOfDouble2[0], (int)arrayOfDouble2[1], (int)arrayOfDouble2[2] };
                    boolean bool1 = false;
                    int[] arrayOfInt4 = { 0, 2 };
                    int j = arrayOfInt4.length;
                    byte b2 = 0;
                    while (true) {
                      int k = arrayOfInt4[b2];
                      if (this.meCoordsInt[k] == arrayOfInt3[k]) {
                        2;
                        byte b3 = 2;
                        (this.meCoordsInt[b3] >= this.enemyCoordsInt[b3]) ? 1 : 0;
                        bool1 = true;
                      } 
                    } 
                  } 
                  double[] arrayOfDouble3 = new double[3];
                  int[] arrayOfInt2 = new int[3];
                  double d1 = Double.MAX_VALUE;
                  double d2 = -1.0D;
                  boolean bool = true;
                  for (int[] arrayOfInt3 : this.disp_surblock) {
                    double[] arrayOfDouble4 = { arrayOfDouble2[0] + arrayOfInt3[0], arrayOfDouble2[1], arrayOfDouble2[2] + arrayOfInt3[2] };
                    if ((d2 = mc.field_71439_g.func_70011_f(arrayOfDouble4[0], arrayOfDouble4[1], arrayOfDouble4[2])) < d1 && (!this.redstoneBlockMode || arrayOfInt3[0] == arrayOfInt[0]) && !someoneInCoords(arrayOfDouble4[0], arrayOfDouble4[2]) && (get_block(arrayOfDouble4[0], arrayOfDouble4[1], arrayOfDouble4[2]) instanceof net.minecraft.block.BlockRedstoneTorch || get_block(arrayOfDouble4[0], arrayOfDouble4[1], arrayOfDouble4[2]) instanceof net.minecraft.block.BlockAir)) {
                      (int)arrayOfDouble1[0];
                      (int)arrayOfDouble1[2];
                    } 
                  } 
                } 
              } else {
                double d1 = Double.MAX_VALUE;
                for (int[] arrayOfInt2 : this.disp_surblock) {
                  BlockPos blockPos = new BlockPos(arrayOfDouble1[0] + arrayOfInt2[0], arrayOfDouble1[1], arrayOfDouble1[2] + arrayOfInt2[2]);
                  double d2;
                  if ((d2 = mc.field_71439_g.func_174831_c(blockPos)) <= d1 && (get_block(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p()) instanceof net.minecraft.block.BlockPistonBase || get_block(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p()) instanceof net.minecraft.block.BlockAir) && !someoneInCoords(arrayOfDouble1[0] + arrayOfInt2[0], arrayOfDouble1[2] + arrayOfInt2[2]) && get_block((blockPos.func_177958_n() - arrayOfInt[0]), blockPos.func_177956_o(), (blockPos.func_177952_p() - arrayOfInt[2])) instanceof net.minecraft.block.BlockAir) {
                    d1 = d2;
                    arrayOfDouble2 = new double[] { arrayOfDouble1[0] + arrayOfInt2[0], arrayOfDouble1[1], arrayOfDouble1[2] + arrayOfInt2[2] };
                    arrayOfInt1 = new int[] { arrayOfInt[0] + arrayOfInt2[0], arrayOfInt[1], arrayOfInt[2] + arrayOfInt2[2] };
                  } 
                } 
                Double.compare(d1, Double.MAX_VALUE);
              } 
            } 
          } 
        } 
      }  
    this.yUnder = true;
    return (pistonAura$structureTemp.to_place != null);
  }
  
  public void initValues() {
    (give up)null;
    this.aimTarget = null;
    this.delayTable = new int[] { this.startDelay.get_value(1), this.supBlocksDelay.get_value(1), this.pistonDelay.get_value(1), this.crystalDelay.get_value(1), this.hitDelay.get_value(1) };
    this.toPlace = new PistonAura$structureTemp(0.0D, 0, null);
    this.isHole = true;
    false;
    this.fastModeActive = false;
    this.redstoneBlockMode = false;
    this.yUnder = false;
    this.brokenRedstoneTorch = false;
    this.brokenCrystalBug = false;
    this.broken = false;
    this.deadPl = false;
    this.rotationPlayerMoved = false;
    this.hasMoved = false;
    this.slot_mat = new int[] { -1, -1, -1, -1, -1, -1 };
    false;
    this.stuck = 0;
    this.delayTimeTicks = 0;
    this.stage = 0;
    if (mc.field_71439_g == null) {
      set_disable();
      return;
    } 
    if (this.chatMsg.get_value(true))
      MessageUtil.send_client_error_message("AutoPiston off"); 
    this.oldSlot = mc.field_71439_g.field_71071_by.field_70461_c;
  }
  
  public Block get_block(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    return mc.field_71441_e.func_180495_p(new BlockPos(paramDouble1, paramDouble2, paramDouble3)).func_177230_c();
  }
  
  public static void setYawAndPitch(float paramFloat1, float paramFloat2) {
    (give up)null;
    yaw = paramFloat1;
    pitch = paramFloat2;
    isSpoofingAngles = true;
  }
  
  public PistonAura() {
    super(Category.combat);
  }
  
  public void disable() {
    (give up)null;
    if (mc.field_71439_g == null)
      return; 
    if (this.chatMsg.get_value(true)) {
      String str1 = "";
      String str2 = "";
      if (this.aimTarget == null) {
        str1 = "No target found";
      } else if (this.yUnder) {
        str1 = String.format("you cannot be 2+ blocks under the enemy or %d above", new Object[] { Integer.valueOf(this.maxYincr.get_value(1)) });
      } else if (this.noMaterials) {
        str1 = "No Materials Detected";
        str2 = getMissingMaterials();
      } else if (!this.isHole) {
        str1 = "The enemy is not in a hole";
      } else if (!this.enoughSpace) {
        str1 = "Not enough space";
      } else if (this.hasMoved) {
        str1 = "Out of range";
      } else if (this.deadPl) {
        str1 = "Enemy is dead";
      } else if (this.rotationPlayerMoved) {
        str1 = "You cannot move from your hole if you have rotation on";
      } 
      str2.equals("");
      MessageUtil.send_client_error_message(str1 + "");
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
  }
  
  public boolean getAimTarget() {
    (give up)null;
    if (this.target.in("Nearest")) {
      this.aimTarget = findClosestTarget(this.range.get_value(1), this.aimTarget);
    } else {
      this.aimTarget = findLookingPlayer(this.range.get_value(1));
    } 
    if (this.aimTarget == null || !this.target.in("Looking")) {
      if (!this.target.in("Looking") && this.aimTarget == null)
        set_disable(); 
      return (this.aimTarget == null);
    } 
    return false;
  }
  
  public void playerChecks() {
    (give up)null;
    if (getMaterialsSlot()) {
      if (is_in_hole()) {
        this.enemyCoordsDouble = new double[] { this.aimTarget.field_70165_t, this.aimTarget.field_70163_u, this.aimTarget.field_70161_v };
        this.enemyCoordsInt = new int[] { (int)this.enemyCoordsDouble[0], (int)this.enemyCoordsDouble[1], (int)this.enemyCoordsDouble[2] };
        this.meCoordsInt = new int[] { (int)mc.field_71439_g.field_70165_t, (int)mc.field_71439_g.field_70163_u, (int)mc.field_71439_g.field_70161_v };
        antiAutoDestruction();
        this.enoughSpace = createStructure();
      } else {
        this.isHole = false;
      } 
    } else {
      this.noMaterials = true;
    } 
  }
  
  public boolean placeBlock(BlockPos paramBlockPos, int paramInt, double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    Block block = mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c();
    EnumFacing enumFacing = BlockInteractHelper.getPlaceableSide(paramBlockPos);
    return (!(block instanceof net.minecraft.block.BlockAir) && !(block instanceof net.minecraft.block.BlockLiquid)) ? false : false;
  }
  
  public void placeBlockThings(int paramInt) {
    (give up)null;
    BlockPos blockPos = compactBlockPos(paramInt);
    placeBlock(blockPos, paramInt, this.toPlace.offsetX, this.toPlace.offsetZ, this.toPlace.offsetY);
    this.stage++;
  }
  
  public static EntityPlayer findClosestTarget(double paramDouble, EntityPlayer paramEntityPlayer) {
    (give up)null;
    List list = mc.field_71441_e.field_73010_i;
    EntityPlayer entityPlayer = null;
    for (EntityPlayer entityPlayer1 : list) {
      if (basicChecksEntity((Entity)entityPlayer1))
        continue; 
      if (mc.field_71439_g.func_70032_d((Entity)entityPlayer1) <= paramDouble) {
        entityPlayer = entityPlayer1;
        continue;
      } 
      if (mc.field_71439_g.func_70032_d((Entity)entityPlayer1) > paramDouble || mc.field_71439_g.func_70032_d((Entity)entityPlayer1) >= mc.field_71439_g.func_70032_d((Entity)paramEntityPlayer))
        continue; 
      entityPlayer = entityPlayer1;
    } 
    return entityPlayer;
  }
  
  public static void resetRotation() {
    (give up)null;
    if (isSpoofingAngles) {
      yaw = mc.field_71439_g.field_70177_z;
      pitch = mc.field_71439_g.field_70125_A;
      isSpoofingAngles = false;
    } 
  }
  
  public BlockPos compactBlockPos(int paramInt) {
    (give up)null;
    BlockPos blockPos = new BlockPos(this.toPlace.to_place.get(this.toPlace.supportBlock + paramInt - 1));
    return new BlockPos(this.enemyCoordsDouble[0] + blockPos.func_177958_n(), this.enemyCoordsDouble[1] + blockPos.func_177956_o(), this.enemyCoordsDouble[2] + blockPos.func_177952_p());
  }
  
  public static void breakCrystal(Entity paramEntity) {
    (give up)null;
    mc.field_71442_b.func_78764_a((EntityPlayer)mc.field_71439_g, paramEntity);
    mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
  }
  
  public BlockPos getTargetPos(int paramInt) {
    paramInt = 0;
    (give up)null;
    BlockPos blockPos = new BlockPos(this.toPlace.to_place.get(paramInt));
    return new BlockPos(this.enemyCoordsDouble[0] + blockPos.func_177958_n(), this.enemyCoordsDouble[1] + blockPos.func_177956_o(), this.enemyCoordsDouble[2] + blockPos.func_177952_p());
  }
  
  public void printTimeCrystals() {
    (give up)null;
    this.endTime = System.currentTimeMillis();
    MessageUtil.send_client_message("3 crystal, time took: " + (this.endTime - this.startTime));
    this.nCrystal = 0;
    this.startTime = System.currentTimeMillis();
  }
  
  public String getMissingMaterials() {
    (give up)null;
    StringBuilder stringBuilder = new StringBuilder();
    if (this.slot_mat[0] == -1)
      stringBuilder.append(" Obsidian"); 
    if (this.slot_mat[1] == -1)
      stringBuilder.append(" Piston"); 
    if (this.slot_mat[2] == -1)
      stringBuilder.append(" Crystals"); 
    if (this.slot_mat[3] == -1)
      stringBuilder.append(" Redstone"); 
    if (this.antiWeakness.get_value(true) && this.slot_mat[4] == -1)
      stringBuilder.append(" Sword"); 
    if (this.redstoneBlockMode && this.slot_mat[5] == -1)
      stringBuilder.append(" Pick"); 
    return stringBuilder.toString();
  }
  
  public boolean placeSupport() {
    (give up)null;
    byte b1 = 0;
    byte b2 = 0;
    if (this.toPlace.supportBlock > 0)
      do {
        BlockPos blockPos = getTargetPos(b1);
        if (placeBlock(blockPos, 0, 0.0D, 0.0D, 1.0D) && ++b2 == this.blocksPerTick.get_value(1))
          return false; 
      } while (++b1 != this.toPlace.supportBlock); 
    this.stage = (this.stage == 0) ? 1 : this.stage;
    return true;
  }
  
  public boolean is_in_hole() {
    (give up)null;
    this.sur_block = new Double[][] { { Double.valueOf(this.aimTarget.field_70165_t + 1.0D), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v) }, { Double.valueOf(this.aimTarget.field_70165_t - 1.0D), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v) }, { Double.valueOf(this.aimTarget.field_70165_t), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v + 1.0D) }, { Double.valueOf(this.aimTarget.field_70165_t), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v - 1.0D) } };
    return (!(get_block(this.sur_block[0][0].doubleValue(), this.sur_block[0][1].doubleValue(), this.sur_block[0][2].doubleValue()) instanceof net.minecraft.block.BlockAir) && !(get_block(this.sur_block[1][0].doubleValue(), this.sur_block[1][1].doubleValue(), this.sur_block[1][2].doubleValue()) instanceof net.minecraft.block.BlockAir) && !(get_block(this.sur_block[2][0].doubleValue(), this.sur_block[2][1].doubleValue(), this.sur_block[2][2].doubleValue()) instanceof net.minecraft.block.BlockAir) && !(get_block(this.sur_block[3][0].doubleValue(), this.sur_block[3][1].doubleValue(), this.sur_block[3][2].doubleValue()) instanceof net.minecraft.block.BlockAir));
  }
  
  public boolean getMaterialsSlot() {
    (give up)null;
    if (mc.field_71439_g.func_184592_cb().func_77973_b() instanceof net.minecraft.item.ItemEndCrystal)
      this.slot_mat[2] = 11; 
    if (this.placeMode.in("Block"))
      this.redstoneBlockMode = true; 
    byte b = 0;
    while (true) {
      9;
      ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b);
      if (itemStack != ItemStack.field_190927_a) {
        if (this.slot_mat[2] == -1 && itemStack.func_77973_b() instanceof net.minecraft.item.ItemEndCrystal) {
          this.slot_mat[2] = b;
        } else if (this.antiWeakness.get_value(true) && itemStack.func_77973_b() instanceof net.minecraft.item.ItemSword) {
          this.slot_mat[4] = b;
        } else if (itemStack.func_77973_b() instanceof net.minecraft.item.ItemPickaxe) {
          this.slot_mat[5] = b;
        } 
        if (itemStack.func_77973_b() instanceof ItemBlock) {
          Block block = ((ItemBlock)itemStack.func_77973_b()).func_179223_d();
          if (block instanceof net.minecraft.block.BlockObsidian) {
            this.slot_mat[0] = b;
          } else if (block instanceof net.minecraft.block.BlockPistonBase) {
            this.slot_mat[1] = b;
          } else if (!this.placeMode.in("Block") && block instanceof net.minecraft.block.BlockRedstoneTorch) {
            this.slot_mat[3] = b;
            this.redstoneBlockMode = false;
          } else if (!this.placeMode.in("Torch") && block.field_149770_b.equals("blockRedstone")) {
            this.slot_mat[3] = b;
            this.redstoneBlockMode = true;
          } 
        } 
      } 
    } 
  }
  
  public static boolean basicChecksEntity(Entity paramEntity) {
    (give up)null;
    return (paramEntity == mc.field_71439_g || FriendUtil.isFriend(paramEntity.func_70005_c_()) || paramEntity.field_70128_L);
  }
  
  public boolean checkVariable() {
    (give up)null;
    if (this.noMaterials || !this.isHole || !this.enoughSpace || this.hasMoved || this.deadPl || this.rotationPlayerMoved) {
      set_disable();
      return true;
    } 
    return false;
  }
  
  public boolean breakRedstone() {
    (give up)null;
    BlockPos blockPos1 = new BlockPos(this.toPlace.to_place.get(this.toPlace.supportBlock + 2));
    BlockPos blockPos2 = (new BlockPos(this.aimTarget.func_174791_d())).func_177982_a(blockPos1.func_177958_n(), blockPos1.func_177956_o(), blockPos1.func_177952_p());
    if (!(get_block(blockPos2.func_177958_n(), blockPos2.func_177956_o(), blockPos2.func_177952_p()) instanceof net.minecraft.block.BlockAir)) {
      breakBlock(blockPos2);
      return false;
    } 
    return true;
  }
  
  public void destroyCrystalAlgo() {
    (give up)null;
    Entity entity = null;
    for (Entity entity1 : mc.field_71441_e.field_72996_f) {
      if (entity1 instanceof net.minecraft.entity.item.EntityEnderCrystal && (((int)entity1.field_70165_t == this.enemyCoordsInt[0] && ((int)(entity1.field_70161_v - this.CrystalDeltaBreak) == this.enemyCoordsInt[2] || (int)(entity1.field_70161_v + this.CrystalDeltaBreak) == this.enemyCoordsInt[2])) || ((int)entity1.field_70161_v == this.enemyCoordsInt[2] && ((int)(entity1.field_70165_t - this.CrystalDeltaBreak) == this.enemyCoordsInt[0] || (int)(entity1.field_70165_t + this.CrystalDeltaBreak) == this.enemyCoordsInt[0]))))
        entity = entity1; 
    } 
    if (this.confirmBreak.get_value(true) && this.broken) {
      boolean bool = false;
      this.stuck = 0;
      this.stage = 0;
      this.broken = false;
    } 
    breakCrystalPiston(entity);
    if (this.confirmBreak.get_value(true)) {
      this.broken = true;
    } else {
      boolean bool = false;
      this.stuck = 0;
      this.stage = 0;
    } 
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
  
  public boolean checkCrystalPlace() {
    (give up)null;
    for (Entity entity : mc.field_71441_e.field_72996_f) {
      if (entity instanceof net.minecraft.entity.item.EntityEnderCrystal && (int)entity.field_70165_t == (int)(this.aimTarget.field_70165_t + ((Vec3d)this.toPlace.to_place.get(this.toPlace.supportBlock + 1)).field_72450_a) && (int)entity.field_70161_v == (int)(this.aimTarget.field_70161_v + ((Vec3d)this.toPlace.to_place.get(this.toPlace.supportBlock + 1)).field_72449_c))
        return true; 
    } 
    this.stage--;
    return false;
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g == null) {
      set_disable();
      return;
    } 
    if (this.delayTimeTicks < this.delayTable[this.stage]) {
      this.delayTimeTicks++;
      return;
    } 
    this.delayTimeTicks = 0;
    if (this.enemyCoordsDouble == null || this.aimTarget == null) {
      if (this.aimTarget == null) {
        this.aimTarget = findLookingPlayer(this.range.get_value(1));
        if (this.aimTarget != null)
          playerChecks(); 
      } else {
        checkVariable();
      } 
      return;
    } 
    if (this.aimTarget.field_70128_L)
      this.deadPl = true; 
    if (this.rotate.get_value(true) && (int)mc.field_71439_g.field_70165_t != this.meCoordsInt[0] && (int)mc.field_71439_g.field_70161_v != this.meCoordsInt[2])
      this.rotationPlayerMoved = true; 
    if ((int)this.aimTarget.field_70165_t != (int)this.enemyCoordsDouble[0] || (int)this.aimTarget.field_70161_v != (int)this.enemyCoordsDouble[2])
      this.hasMoved = true; 
    if (checkVariable())
      return; 
    if (placeSupport())
      switch (this.stage) {
        case 1:
          if (!this.fastModeActive && !breakRedstone())
            break; 
          if (!this.fastModeActive || checkCrystalPlace()) {
            placeBlockThings(this.stage);
            break;
          } 
          this.stage = 2;
          break;
        case 2:
          if (this.fastModeActive || !this.confirmPlace.get_value(true) || checkPistonPlace())
            placeBlockThings(this.stage); 
          break;
        case 3:
          if (!this.fastModeActive && this.confirmPlace.get_value(true) && !checkCrystalPlace())
            break; 
          placeBlockThings(this.stage);
          this.hitTryTick = 0;
          if (this.fastModeActive && !checkPistonPlace())
            this.stage = 1; 
          break;
        case 4:
          destroyCrystalAlgo();
          break;
      }  
  }
  
  public static void lookAtPacket(double paramDouble1, double paramDouble2, double paramDouble3, EntityPlayer paramEntityPlayer) {
    (give up)null;
    double[] arrayOfDouble = calculateLookAt(paramDouble1, paramDouble2, paramDouble3, paramEntityPlayer);
    setYawAndPitch((float)arrayOfDouble[0], (float)arrayOfDouble[1]);
  }
  
  public void antiAutoDestruction() {
    (give up)null;
    if (this.redstoneBlockMode || this.rotate.get_value(true))
      this.betterPlacement.set_value(false); 
  }
  
  public void enable() {
    (give up)null;
    initValues();
    if (getAimTarget())
      return; 
    playerChecks();
  }
  
  public void breakCrystalPiston(Entity paramEntity) {
    (give up)null;
    if (this.hitTryTick++ < this.midHitDelay.get_value(1))
      return; 
    this.hitTryTick = 0;
    if (this.antiWeakness.get_value(true))
      mc.field_71439_g.field_71071_by.field_70461_c = this.slot_mat[4]; 
    if (this.rotate.get_value(true))
      lookAtPacket(paramEntity.field_70165_t, paramEntity.field_70163_u, paramEntity.field_70161_v, (EntityPlayer)mc.field_71439_g); 
    if (this.breakType.in("Swing")) {
      breakCrystal(paramEntity);
    } else if (this.breakType.in("Packet")) {
      mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity(paramEntity));
      mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
    } 
    if (this.rotate.get_value(true))
      resetRotation(); 
  }
  
  public void breakBlock(BlockPos paramBlockPos) {
    (give up)null;
    if (this.redstoneBlockMode)
      mc.field_71439_g.field_71071_by.field_70461_c = this.slot_mat[5]; 
    EnumFacing enumFacing = BlockInteractHelper.getPlaceableSide(paramBlockPos);
    if (this.rotate.get_value(true)) {
      BlockPos blockPos = paramBlockPos.func_177972_a(enumFacing);
      EnumFacing enumFacing1 = enumFacing.func_176734_d();
      Vec3d vec3d = (new Vec3d((Vec3i)blockPos)).func_72441_c(0.5D, 1.0D, 0.5D).func_178787_e((new Vec3d(enumFacing1.func_176730_m())).func_186678_a(0.5D));
      BlockInteractHelper.faceVectorPacketInstant(vec3d);
    } 
    mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, paramBlockPos, enumFacing));
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, paramBlockPos, enumFacing));
  }
}
