package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.ArrayList;
import java.util.Objects;
import me.obsidianbreaker.leux.client.event.events.EventDamageBlock;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.BlockInteractHelper;
import me.obsidianbreaker.leux.client.util.BlockUtil;
import me.obsidianbreaker.leux.client.util.EntityUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import me.obsidianbreaker.leux.client.util.PlayerUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class CevBreaker extends Module {
  public Setting supDelay = create("Sup Delay", "SupDelay", 1, 0, 4);
  
  public EntityPlayer aimTarget;
  
  public boolean deadPl;
  
  public int stage;
  
  public Setting crystalDelay = create("Crystal Delay", "CrystalDelay", 2, 0, 20);
  
  public boolean rotationPlayerMoved;
  
  public int delayTimeTicks;
  
  public int[] enemyCoordsInt;
  
  public boolean isHole = true;
  
  public CevBreaker$structureTemp toPlace;
  
  public boolean noMaterials = false;
  
  public Double[][] sur_block = new Double[4][3];
  
  public Setting target = create("Target", "Target", "Nearest", combobox(new String[] { "Nearest", "Looking" }));
  
  public Setting pickSwitchTick = create("PickSwitchTick", "PickSwitchTick", 100, 0, 500);
  
  public Setting antiStep = create("Anti Step", "AntiStep", false);
  
  public double[] enemyCoordsDouble;
  
  public Setting fastPlace = create("Fast Place", "FastPlace", false);
  
  public Setting confirmPlace = create("Confirm Place", "ConfirmPlace", true);
  
  public boolean enoughSpace = true;
  
  public Setting confirmBreak = create("Confirm Break", "ConfirmBreak", true);
  
  public int hitTryTick;
  
  public Setting enemyRange = create("Range", "CivBreakerEnemyRange", 4.9D, 1.0D, 6.0D);
  
  public Setting hitDelay = create("Hit Delay", "HitDelay", 2, 0, 20);
  
  public int[] delayTable;
  
  @EventHandler
  public Listener listener2 = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public int[][] model = new int[][] { { 1, 1, 0 }, { -1, 1, 0 }, { 0, 1, 1 }, { 0, 1, -1 } };
  
  public Setting trapPlayer = create("Trap Player", "TrapPlayer", false);
  
  public boolean hasMoved = false;
  
  public Setting rotate = create("Rotate", "Rotate", true);
  
  public boolean prevBreak;
  
  public Setting midHitDelay = create("Mid HitDelay", "MidHitDelay", 1, 0, 5);
  
  public Setting blocksPerTick = create("Blocks Per Tick", "BPS", 4, 2, 6);
  
  public Setting endDelay = create("End Delay", "EndDelay", 1, 0, 4);
  
  public int tickPick;
  
  public Setting antiWeakness = create("Anti Weakness", "AntiWeakness", false);
  
  public boolean isSneaking = false;
  
  public int[] slot_mat;
  
  public int oldSlot = -1;
  
  public Setting breakCrystal = create("Break Crystal", "BreakMode", "Packet", combobox(new String[] { "Vanilla", "Packet", "None" }));
  
  public boolean broken;
  
  public Setting chatMsg = create("Chat Messages", "Messages", true);
  
  public Setting switchSword = create("Switch Sword", "SwitchSword", false);
  
  public Setting placeCrystal = create("Place Crystal", "PlaceCrystal", true);
  
  public void breakCrystalPiston(Entity paramEntity) {
    (give up)null;
    if (this.hitTryTick++ < this.midHitDelay.get_value(1))
      return; 
    this.hitTryTick = 0;
    if (this.antiWeakness.get_value(true))
      mc.field_71439_g.field_71071_by.field_70461_c = this.slot_mat[3]; 
    if (this.breakCrystal.in("Vanilla")) {
      EntityUtil.attackEntity(paramEntity);
    } else if (this.breakCrystal.in("Packet")) {
      mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity(paramEntity));
      mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
    } 
  }
  
  public void placeBlockThings(int paramInt) {
    (give up)null;
    if (paramInt != 1 || this.placeCrystal.get_value(true)) {
      BlockPos blockPos = compactBlockPos(--paramInt);
      placeBlock(blockPos, paramInt);
    } 
    this.stage++;
  }
  
  public BlockPos getTargetPos(int paramInt) {
    paramInt = 0;
    (give up)null;
    BlockPos blockPos = new BlockPos(this.toPlace.to_place.get(paramInt));
    return new BlockPos(this.enemyCoordsDouble[0] + blockPos.func_177958_n(), this.enemyCoordsDouble[1] + blockPos.func_177956_o(), this.enemyCoordsDouble[2] + blockPos.func_177952_p());
  }
  
  public boolean createStructure() {
    (give up)null;
    if (((ResourceLocation)Objects.<ResourceLocation>requireNonNull(BlockInteractHelper.getBlock(this.enemyCoordsDouble[0], this.enemyCoordsDouble[1] + 2.0D, this.enemyCoordsDouble[2]).getRegistryName())).toString().toLowerCase().contains("bedrock") || !(BlockInteractHelper.getBlock(this.enemyCoordsDouble[0], this.enemyCoordsDouble[1] + 3.0D, this.enemyCoordsDouble[2]) instanceof net.minecraft.block.BlockAir) || !(BlockInteractHelper.getBlock(this.enemyCoordsDouble[0], this.enemyCoordsDouble[1] + 4.0D, this.enemyCoordsDouble[2]) instanceof net.minecraft.block.BlockAir))
      return false; 
    double d = Double.MAX_VALUE;
    byte b1 = 0;
    byte b2 = 0;
    for (Double[] arrayOfDouble : this.sur_block) {
      double d1;
      if ((d1 = mc.field_71439_g.func_174818_b(new BlockPos(arrayOfDouble[0].doubleValue(), arrayOfDouble[1].doubleValue(), arrayOfDouble[2].doubleValue()))) < d) {
        d = d1;
        b1 = b2;
      } 
      b2++;
    } 
    byte b3 = (this.enemyCoordsInt[0] == (int)mc.field_71439_g.field_70165_t || this.enemyCoordsInt[2] == (int)mc.field_71439_g.field_70161_v) ? -1 : 1;
    this.toPlace.to_place.add(new Vec3d((this.model[b1][0] * b3), 1.0D, (this.model[b1][2] * b3)));
    this.toPlace.to_place.add(new Vec3d((this.model[b1][0] * b3), 2.0D, (this.model[b1][2] * b3)));
    this.toPlace.supportBlock = 2;
    if (this.trapPlayer.get_value(true) || this.antiStep.get_value(true)) {
      byte b = 1;
      while (true) {
        3;
        2;
        for (int[] arrayOfInt : this.model) {
          Vec3d vec3d = new Vec3d(arrayOfInt[0], b, arrayOfInt[2]);
          if (!this.toPlace.to_place.contains(vec3d)) {
            this.toPlace.to_place.add(vec3d);
            CevBreaker$structureTemp cevBreaker$structureTemp = this.toPlace;
            cevBreaker$structureTemp.supportBlock++;
          } 
        } 
      } 
    } 
    this.toPlace.to_place.add(new Vec3d(0.0D, 2.0D, 0.0D));
    this.toPlace.to_place.add(new Vec3d(0.0D, 3.0D, 0.0D));
    return true;
  }
  
  public boolean is_in_hole() {
    (give up)null;
    this.sur_block = new Double[][] { { Double.valueOf(this.aimTarget.field_70165_t + 1.0D), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v) }, { Double.valueOf(this.aimTarget.field_70165_t - 1.0D), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v) }, { Double.valueOf(this.aimTarget.field_70165_t), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v + 1.0D) }, { Double.valueOf(this.aimTarget.field_70165_t), Double.valueOf(this.aimTarget.field_70163_u), Double.valueOf(this.aimTarget.field_70161_v - 1.0D) } };
    return (BlockUtil.isHole(EntityUtil.getPosition((Entity)this.aimTarget), true, true).getType() != BlockUtil.HoleType.NONE);
  }
  
  public BlockPos compactBlockPos(int paramInt) {
    (give up)null;
    BlockPos blockPos = new BlockPos(this.toPlace.to_place.get(this.toPlace.supportBlock + paramInt));
    return new BlockPos(this.enemyCoordsDouble[0] + blockPos.func_177958_n(), this.enemyCoordsDouble[1] + blockPos.func_177956_o(), this.enemyCoordsDouble[2] + blockPos.func_177952_p());
  }
  
  public Entity getCrystal() {
    (give up)null;
    for (Entity entity : mc.field_71441_e.field_72996_f) {
      if (entity instanceof net.minecraft.entity.item.EntityEnderCrystal && (int)entity.field_70165_t == this.enemyCoordsInt[0] && (int)entity.field_70161_v == this.enemyCoordsInt[2] && entity.field_70163_u - this.enemyCoordsInt[1] == 3.0D)
        return entity; 
    } 
    return null;
  }
  
  public CevBreaker() {
    super(Category.combat);
  }
  
  public boolean checkVariable() {
    (give up)null;
    if (this.noMaterials || !this.isHole || !this.enoughSpace || this.hasMoved || this.deadPl || this.rotationPlayerMoved) {
      set_disable();
      return true;
    } 
    return false;
  }
  
  public boolean getAimTarget() {
    (give up)null;
    if (this.target.in("Nearest")) {
      this.aimTarget = EntityUtil.findClosestTarget(this.enemyRange.get_value(1), this.aimTarget);
    } else {
      this.aimTarget = PlayerUtil.findLookingPlayer(this.enemyRange.get_value(1));
    } 
    if (this.aimTarget == null || !this.target.in("Looking")) {
      if (!this.target.in("Looking") && this.aimTarget == null)
        set_disable(); 
      return (this.aimTarget == null);
    } 
    return false;
  }
  
  public boolean placeSupport() {
    (give up)null;
    byte b1 = 0;
    byte b2 = 0;
    if (this.toPlace.supportBlock > 0)
      do {
        BlockPos blockPos = getTargetPos(b1);
        if (placeBlock(blockPos, 0) && ++b2 == this.blocksPerTick.get_value(1))
          return false; 
      } while (++b1 != this.toPlace.supportBlock); 
    this.stage = (this.stage == 0) ? 1 : this.stage;
    return true;
  }
  
  public boolean placeBlock(BlockPos paramBlockPos, int paramInt) {
    (give up)null;
    Block block = mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c();
    EnumFacing enumFacing = BlockInteractHelper.getPlaceableSide(paramBlockPos);
    return (!(block instanceof net.minecraft.block.BlockAir) && !(block instanceof net.minecraft.block.BlockLiquid)) ? false : false;
  }
  
  public void lambda$new$0(EventDamageBlock paramEventDamageBlock) {
    (give up)null;
    if (this.enemyCoordsInt != null && paramEventDamageBlock.getPos().func_177958_n() + ((paramEventDamageBlock.getPos().func_177958_n() < 0) ? 1 : 0) == this.enemyCoordsInt[0] && paramEventDamageBlock.getPos().func_177952_p() + ((paramEventDamageBlock.getPos().func_177952_p() < 0) ? 1 : 0) == this.enemyCoordsInt[2])
      destroyCrystalAlgo(); 
  }
  
  public void destroyCrystalAlgo() {
    (give up)null;
    Entity entity = getCrystal();
    if (this.confirmBreak.get_value(true) && this.broken) {
      this.stage = 0;
      this.broken = false;
    } 
    breakCrystalPiston(entity);
    if (this.confirmBreak.get_value(true)) {
      this.broken = true;
    } else {
      this.stage = 0;
    } 
  }
  
  public String getMissingMaterials() {
    (give up)null;
    StringBuilder stringBuilder = new StringBuilder();
    if (this.slot_mat[0] == -1)
      stringBuilder.append(" Obsidian"); 
    if (this.slot_mat[1] == -1)
      stringBuilder.append(" Crystal"); 
    if ((this.antiWeakness.get_value(true) || this.switchSword.get_value(true)) && this.slot_mat[3] == -1)
      stringBuilder.append(" Sword"); 
    if (this.slot_mat[2] == -1)
      stringBuilder.append(" Pickaxe"); 
    return stringBuilder.toString();
  }
  
  public boolean getMaterialsSlot() {
    (give up)null;
    if (mc.field_71439_g.func_184592_cb().func_77973_b() instanceof net.minecraft.item.ItemEndCrystal)
      this.slot_mat[1] = 11; 
    byte b = 0;
    while (true) {
      9;
      ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b);
      if (itemStack != ItemStack.field_190927_a) {
        if (this.slot_mat[1] == -1 && itemStack.func_77973_b() instanceof net.minecraft.item.ItemEndCrystal) {
          this.slot_mat[1] = b;
        } else if ((this.antiWeakness.get_value(true) || this.switchSword.get_value(true)) && itemStack.func_77973_b() instanceof net.minecraft.item.ItemSword) {
          this.slot_mat[3] = b;
        } else if (itemStack.func_77973_b() instanceof net.minecraft.item.ItemPickaxe) {
          this.slot_mat[2] = b;
        } 
        if (itemStack.func_77973_b() instanceof ItemBlock) {
          Block block = ((ItemBlock)itemStack.func_77973_b()).func_179223_d();
          if (block instanceof net.minecraft.block.BlockObsidian)
            this.slot_mat[0] = b; 
        } 
      } 
    } 
  }
  
  public void placeCrystal() {
    (give up)null;
    placeBlockThings(this.stage);
  }
  
  public void enable() {
    (give up)null;
    this.aimTarget = null;
    this.delayTable = new int[] { this.supDelay.get_value(1), this.crystalDelay.get_value(1), this.hitDelay.get_value(1), this.endDelay.get_value(1) };
    this.toPlace = new CevBreaker$structureTemp(0.0D, 0, new ArrayList());
    this.isHole = true;
    false;
    this.broken = false;
    this.deadPl = false;
    this.rotationPlayerMoved = false;
    this.hasMoved = false;
    this.slot_mat = new int[] { -1, -1, -1, -1 };
    false;
    this.delayTimeTicks = 0;
    this.stage = 0;
    if (mc.field_71439_g == null) {
      set_disable();
      return;
    } 
    if (this.chatMsg.get_value(true))
      MessageUtil.send_client_error_message("CevBreaker off"); 
    this.oldSlot = mc.field_71439_g.field_71071_by.field_70461_c;
    if (getAimTarget())
      return; 
    playerChecks();
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g == null || mc.field_71439_g.field_70128_L) {
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
        this.aimTarget = PlayerUtil.findLookingPlayer(this.enemyRange.get_value(1));
        if (this.aimTarget != null)
          playerChecks(); 
      } else {
        checkVariable();
      } 
      return;
    } 
    if (this.aimTarget.field_70128_L)
      this.deadPl = true; 
    if ((int)this.aimTarget.field_70165_t != (int)this.enemyCoordsDouble[0] || (int)this.aimTarget.field_70161_v != (int)this.enemyCoordsDouble[2])
      this.hasMoved = true; 
    if (checkVariable())
      return; 
    if (placeSupport()) {
      byte b;
      BlockPos blockPos;
      switch (this.stage) {
        case 1:
          placeBlockThings(this.stage);
          if (this.fastPlace.get_value(true))
            placeCrystal(); 
          this.prevBreak = false;
          this.tickPick = 0;
          break;
        case 2:
          if (this.confirmPlace.get_value(true) && !(BlockInteractHelper.getBlock(compactBlockPos(0)) instanceof net.minecraft.block.BlockObsidian)) {
            this.stage--;
            return;
          } 
          placeCrystal();
          break;
        case 3:
          if (this.confirmPlace.get_value(true) && getCrystal() == null) {
            this.stage--;
            return;
          } 
          b = 3;
          if (!this.switchSword.get_value(true) || this.tickPick == this.pickSwitchTick.get_value(1) || this.tickPick++ == 0)
            b = 2; 
          if (mc.field_71439_g.field_71071_by.field_70461_c != this.slot_mat[b])
            mc.field_71439_g.field_71071_by.field_70461_c = this.slot_mat[b]; 
          blockPos = new BlockPos(this.enemyCoordsDouble[0], (this.enemyCoordsInt[1] + 2), this.enemyCoordsDouble[2]);
          if (BlockInteractHelper.getBlock(blockPos) instanceof net.minecraft.block.BlockObsidian) {
            EnumFacing enumFacing = BlockInteractHelper.getPlaceableSide(blockPos);
            if (this.breakCrystal.in("Packet")) {
              if (!this.prevBreak) {
                mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
                mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, enumFacing));
                mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, enumFacing));
                this.prevBreak = true;
              } 
              break;
            } 
            if (this.breakCrystal.in("Normal")) {
              mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
              mc.field_71442_b.func_180512_c(blockPos, enumFacing);
            } 
            break;
          } 
          destroyCrystalAlgo();
          break;
      } 
    } 
  }
  
  public void playerChecks() {
    (give up)null;
    if (getMaterialsSlot()) {
      if (is_in_hole()) {
        this.enemyCoordsDouble = new double[] { this.aimTarget.field_70165_t, this.aimTarget.field_70163_u, this.aimTarget.field_70161_v };
        this.enemyCoordsInt = new int[] { (int)this.enemyCoordsDouble[0], (int)this.enemyCoordsDouble[1], (int)this.enemyCoordsDouble[2] };
        this.enoughSpace = createStructure();
      } else {
        this.isHole = false;
      } 
    } else {
      this.noMaterials = true;
    } 
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
      } else if (this.noMaterials) {
        str1 = "No Materials Detected";
        str2 = getMissingMaterials();
      } else if (!this.isHole) {
        str1 = "Enemy is not in hole";
      } else if (!this.enoughSpace) {
        str1 = "Not enough space";
      } else if (this.hasMoved) {
        str1 = "Out of range";
      } else if (this.deadPl) {
        str1 = "Enemy is dead ";
      } 
      MessageUtil.send_client_error_message(str1 + "");
      str2.equals("");
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
}
