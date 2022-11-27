package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventEntityRemoved;
import me.obsidianbreaker.leux.client.event.events.EventMotionUpdate;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.modules.misc.AutoGG;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import me.obsidianbreaker.leux.client.util.BlockUtil;
import me.obsidianbreaker.leux.client.util.CrystalUtil;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import me.obsidianbreaker.leux.client.util.MathUtil;
import me.obsidianbreaker.leux.client.util.PosManager;
import me.obsidianbreaker.leux.client.util.RenderUtil;
import me.obsidianbreaker.leux.client.util.RotationUtil;
import me.obsidianbreaker.leux.client.util.Timer;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class CrystalAuraNew extends Module {
  public Setting min_player_place = create("Min Enemy Place", "CaMinEnemyPlace", 6, 0, 20);
  
  @EventHandler
  public Listener receive_listener = new Listener(this::lambda$new$3, new java.util.function.Predicate[0]);
  
  public boolean outline;
  
  public Setting max_self_damage = create("Max Self Damage", "CaMaxSelfDamage", 8, 0, 20);
  
  public Setting render_damage = create("Render Damage", "RenderDamage", true);
  
  public int detail_hp = 0;
  
  public boolean already_attacking = false;
  
  public int break_delay_counter;
  
  public BlockPos render_block_init;
  
  public Setting auto_switch = create("Auto Switch", "CaAutoSwitch", false);
  
  public float pitch;
  
  public boolean is_rotating;
  
  public Timer remove_visual_timer = new Timer();
  
  public Setting g = create("G", "CaG", 100, 0, 255);
  
  public Setting luscius_mode_damage = create("Health", "CaLusciusModeHealth", 8, 0, 36);
  
  public Setting a = create("A", "CaA", 100, 0, 255);
  
  public int place_timeout;
  
  public Setting break_delay = create("Break Delay", "CaBreakDelay", 1, 0, 10);
  
  public Setting rotate_mode = create("Rotate", "CaRotateMode", "Off", combobox(new String[] { "Off", "Old", "Const", "Good" }));
  
  public Setting stop_while_mining = create("Stop While Mining", "CaStopWhileMining", false);
  
  @EventHandler
  public Listener send_listener = new Listener(this::lambda$new$1, new java.util.function.Predicate[0]);
  
  public Setting stop_while_eating = create("Stop While Eating", "CaStopWhileEating", false);
  
  public boolean did_anything;
  
  public Setting top_block = create("Top Block", "CaTopBlock", false);
  
  public Setting brightness = create("Brightness", "CaBrightness", 0.8D, 0.0D, 1.0D);
  
  public Setting faceplace_check = create("No Sword FP", "CaJumpyFaceMode", false);
  
  public Setting anti_suicide = create("Anti Suicide", "CaAntiSuicide", true);
  
  public Setting height = create("Height", "CaHeight", 1.0D, 0.0D, 1.0D);
  
  public List placePosList = new CopyOnWriteArrayList();
  
  public Setting raytrace = create("Raytrace", "CaRaytrace", false);
  
  public Setting client_side = create("Client Side", "CaClientSide", false);
  
  public Setting sat = create("Satiation", "CaSatiation", 0.8D, 0.0D, 1.0D);
  
  public static EntityPlayer ca_target = null;
  
  public EntityPlayer autoez_target = null;
  
  public Setting anti_weakness = create("Anti-Weakness", "CaAntiWeakness", true);
  
  public boolean solid;
  
  public Setting antiStuckTries = create("Anti Stuck Tries", "CaAntiStuckTries", 5, 1, 15);
  
  public int break_timeout;
  
  @EventHandler
  public Listener on_movement = new Listener(this::lambda$new$2, new java.util.function.Predicate[0]);
  
  public Setting enemyRange = create("Enemy Range", "CaEnemyRange", 7.0D, 5.0D, 15.0D);
  
  public Setting rainbow_mode = create("Rainbow", "CaRainbow", false);
  
  public BlockPos render_block_old;
  
  public Setting fuck_armor_mode_precent = create("Armor %", "CaArmorPercent", 25, 0, 100);
  
  public double render_damage_value;
  
  public Setting render_mode = create("Render", "CaRenderMode", "Pretty", combobox(new String[] { "Pretty", "Solid", "Outline", "None" }));
  
  public Setting jumpy_mode = create("Jumpy Mode", "CaJumpyMode", false);
  
  public Setting a_out = create("Outline A", "CaOutlineA", 255, 0, 255);
  
  @EventHandler
  public Listener on_entity_removed = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting break_range = create("Break Range", "CaBreakRange", 5.199999809265137D, 1.0D, 6.0D);
  
  public ConcurrentHashMap attacked_crystals = new ConcurrentHashMap<>();
  
  public Setting break_range_wall = create("Break Wall Range", "CaBreakWall", 4.0D, 1.0D, 6.0D);
  
  public Setting packet_break = create("Packet Break", "CaPacketBreak", true);
  
  public Setting fuck_armor_mode = create("Armor Destroy", "CaArmorDestory", true);
  
  public Setting luscius_mode = create("Luscius Mode", "CaLusciusMode", true);
  
  public Setting place_range = create("Place Range", "CaPlaceRange", 5.199999809265137D, 1.0D, 6.0D);
  
  public Setting old_render = create("Old Render", "CaOldRender", false);
  
  public Setting break_trys = create("Break Attempts", "CaBreakAttempts", 1, 1, 6);
  
  public Setting place_delay = create("Place Delay", "CaPlaceDelay", 1, 0, 10);
  
  public Setting place_crystal = create("Place", "CaPlace", true);
  
  public Setting place_range_wall = create("Place Wall Range", "CaPlaceWall", 4.0D, 0.0D, 6.0D);
  
  public Setting b = create("B", "CaB", 255, 0, 255);
  
  public Setting endcrystal = create("1.13 Mode", "CaThirteen", false);
  
  public Setting swing = create("Swing", "CaSwing", "Offhand", combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
  
  public Setting anti_stuck = create("Anti Stuck", "CaAntiStuck", false);
  
  public Setting future_render = create("Future Render", "CaFutureRender", false);
  
  public float yaw;
  
  public String detail_name = null;
  
  public Setting min_player_break = create("Min Enemy Break", "CaMinEnemyBreak", 6, 0, 20);
  
  public Setting break_crystal = create("Break", "CaBreak", true);
  
  public int place_delay_counter;
  
  public Setting r = create("R", "CaR", 100, 0, 255);
  
  public Setting stop_while_holefill = create("Stop While Holefill", "CaStopWhileHoleFill", false);
  
  public void add_attacked_crystal(EntityEnderCrystal paramEntityEnderCrystal) {
    (give up)null;
    if (this.attacked_crystals.containsKey(paramEntityEnderCrystal)) {
      int i = ((Integer)this.attacked_crystals.get(paramEntityEnderCrystal)).intValue();
      this.attacked_crystals.put(paramEntityEnderCrystal, Integer.valueOf(i + 1));
    } else {
      this.attacked_crystals.put(paramEntityEnderCrystal, Integer.valueOf(1));
    } 
  }
  
  public boolean get_armor_fucker(EntityPlayer paramEntityPlayer) {
    (give up)null;
    for (ItemStack itemStack : paramEntityPlayer.func_184193_aE()) {
      if (itemStack.func_77973_b() == Items.field_190931_a)
        return true; 
      float f = (itemStack.func_77958_k() - itemStack.func_77952_i()) / itemStack.func_77958_k() * 100.0F;
      if (!this.fuck_armor_mode.get_value(true) || this.fuck_armor_mode_precent.get_value(1) < f)
        continue; 
      return true;
    } 
    return false;
  }
  
  public void lambda$new$1(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (paramSendPacket.get_packet() instanceof CPacketPlayer && this.is_rotating && this.rotate_mode.in("Old")) {
      CPacketPlayer cPacketPlayer = (CPacketPlayer)paramSendPacket.get_packet();
      cPacketPlayer.field_149476_e = this.yaw;
      cPacketPlayer.field_149473_f = this.pitch;
      this.is_rotating = false;
    } 
    if (paramSendPacket.get_packet() instanceof CPacketPlayerTryUseItemOnBlock && this.is_rotating && this.rotate_mode.in("Old")) {
      CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)paramSendPacket.get_packet();
      cPacketPlayerTryUseItemOnBlock.field_149577_f = this.render_block_init.func_177958_n();
      cPacketPlayerTryUseItemOnBlock.field_149578_g = this.render_block_init.func_177956_o();
      cPacketPlayerTryUseItemOnBlock.field_149584_h = this.render_block_init.func_177952_p();
      this.is_rotating = false;
    } 
  }
  
  public CrystalAuraNew() {
    super(Category.combat);
  }
  
  public void render_block(BlockPos paramBlockPos) {
    (give up)null;
    BlockPos blockPos = this.top_block.get_value(true) ? paramBlockPos.func_177984_a() : paramBlockPos;
    float f = (float)this.height.get_value(1.0D);
    if (this.solid) {
      RenderHelp.prepare("quads");
      RenderHelp.draw_cube(RenderHelp.get_buffer_build(), blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), 1.0F, f, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
      RenderHelp.release();
    } 
    if (this.outline) {
      RenderHelp.prepare("lines");
      RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), 1.0F, f, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a_out.get_value(1), "all");
      RenderHelp.release();
    } 
  }
  
  public void place_crystal() {
    (give up)null;
    BlockPos blockPos = get_best_block();
  }
  
  public boolean check_pause() {
    (give up)null;
    if (find_crystals_hotbar() == -1 && mc.field_71439_g.func_184592_cb().func_77973_b() != Items.field_185158_cP)
      return true; 
    if (this.stop_while_mining.get_value(true) && mc.field_71474_y.field_74312_F.func_151470_d() && mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemPickaxe) {
      if (this.old_render.get_value(true))
        this.render_block_init = null; 
      return true;
    } 
    if (this.stop_while_eating.get_value(true) && mc.field_71474_y.field_74313_G.func_151470_d() && mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemAppleGold)
      return true; 
    if (Leux.get_hack_manager().get_module_with_tag("Surround").is_active()) {
      if (this.old_render.get_value(true))
        this.render_block_init = null; 
      return true;
    } 
    if (Leux.get_hack_manager().get_module_with_tag("HoleFill").is_active() && this.stop_while_holefill.get_value(true)) {
      if (this.old_render.get_value(true))
        this.render_block_init = null; 
      return true;
    } 
    if (Leux.get_hack_manager().get_module_with_tag("AutoTrap").is_active()) {
      if (this.old_render.get_value(true))
        this.render_block_init = null; 
      return true;
    } 
    return false;
  }
  
  public int find_crystals_hotbar() {
    (give up)null;
    byte b = 0;
    while (true) {
      9;
      if (mc.field_71439_g.field_71071_by.func_70301_a(b).func_77973_b() != Items.field_185158_cP)
        continue; 
      return b;
    } 
  }
  
  public void lambda$new$2(EventMotionUpdate paramEventMotionUpdate) {
    (give up)null;
    if (paramEventMotionUpdate.stage == 0 && (this.rotate_mode.in("Good") || this.rotate_mode.in("Const"))) {
      PosManager.updatePosition();
      RotationUtil.updateRotations();
      do_ca();
    } 
    if (paramEventMotionUpdate.stage == 1 && (this.rotate_mode.in("Good") || this.rotate_mode.in("Const"))) {
      PosManager.restorePosition();
      RotationUtil.restoreRotations();
    } 
  }
  
  public void break_crystal() {
    (give up)null;
    EntityEnderCrystal entityEnderCrystal = get_best_crystal();
  }
  
  public BlockPos get_best_block() {
    (give up)null;
    double d1 = 0.0D;
    double d2 = this.max_self_damage.get_value(1);
    BlockPos blockPos = null;
    NonNullList nonNullList = CrystalUtil.possiblePlacePositions(this.place_range.get_value(1), this.endcrystal.get_value(true));
    for (EntityPlayer entityPlayer : mc.field_71441_e.field_73010_i) {
      if (FriendUtil.isFriend(entityPlayer.func_70005_c_()))
        continue; 
      ca_target = entityPlayer;
      Iterator<BlockPos> iterator = nonNullList.iterator();
      while (true) {
        BlockPos blockPos1;
        if (iterator.hasNext()) {
          blockPos1 = iterator.next();
          if (entityPlayer == mc.field_71439_g || entityPlayer.func_70032_d((Entity)mc.field_71439_g) >= this.enemyRange.get_value(1) || !BlockUtil.rayTracePlaceCheck(blockPos1, this.raytrace.get_value(true)) || (!BlockUtil.canSeeBlock(blockPos1) && mc.field_71439_g.func_70011_f(blockPos1.func_177958_n(), blockPos1.func_177956_o(), blockPos1.func_177952_p()) > this.place_range_wall.get_value(1)) || entityPlayer.field_70128_L || entityPlayer.func_110143_aJ() <= 0.0F)
            continue; 
          boolean bool = (this.faceplace_check.get_value(true) && mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_151048_u) ? true : false;
          if (entityPlayer.func_110143_aJ() < this.luscius_mode_damage.get_value(1) && this.luscius_mode.get_value(true)) {
          
          } else if (get_armor_fucker(entityPlayer)) {
          
          } else {
            double d5 = this.min_player_place.get_value(1);
            double d6 = CrystalUtil.calculateDamage(blockPos1.func_177958_n() + 0.5D, blockPos1.func_177956_o() + 1.0D, blockPos1.func_177952_p() + 0.5D, (Entity)entityPlayer);
          } 
        } else {
          break;
        } 
        double d3 = 2.0D;
        double d4 = CrystalUtil.calculateDamage(blockPos1.func_177958_n() + 0.5D, blockPos1.func_177956_o() + 1.0D, blockPos1.func_177952_p() + 0.5D, (Entity)entityPlayer);
      } 
    } 
    nonNullList.clear();
    this.render_damage_value = d1;
    this.render_block_init = blockPos;
    return blockPos;
  }
  
  public void cycle_rainbow() {
    (give up)null;
    float[] arrayOfFloat = { (float)(System.currentTimeMillis() % 11520L) / 11520.0F };
    int i = Color.HSBtoRGB(arrayOfFloat[0], this.sat.get_value(1), this.brightness.get_value(1));
    this.r.set_value(i >> 16 & 0xFF);
    this.g.set_value(i >> 8 & 0xFF);
    this.b.set_value(i & 0xFF);
  }
  
  public void rotate_to_pos(BlockPos paramBlockPos) {
    (give up)null;
    float[] arrayOfFloat = this.rotate_mode.in("Const") ? MathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), new Vec3d((paramBlockPos.func_177958_n() + Float.intBitsToFloat(1.10703514E9F ^ 0x7EFC041B)), (paramBlockPos.func_177956_o() + Float.intBitsToFloat(1.08070387E9F ^ 0x7F6A3BA9)), (paramBlockPos.func_177952_p() + Float.intBitsToFloat(1.09686298E9F ^ 0x7E60CCCF)))) : MathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), new Vec3d((paramBlockPos.func_177958_n() + Float.intBitsToFloat(1.0913417E9F ^ 0x7E0C8D6B)), (paramBlockPos.func_177956_o() - Float.intBitsToFloat(1.10115366E9F ^ 0x7EA24559)), (paramBlockPos.func_177952_p() + Float.intBitsToFloat(1.10622643E9F ^ 0x7EEFACC7))));
    if (this.rotate_mode.in("Off"))
      this.is_rotating = false; 
    if (this.rotate_mode.in("Good") || this.rotate_mode.in("Const"))
      RotationUtil.setPlayerRotations(arrayOfFloat[0], arrayOfFloat[1]); 
    if (this.rotate_mode.in("Old")) {
      this.yaw = arrayOfFloat[0];
      this.pitch = arrayOfFloat[1];
      this.is_rotating = true;
    } 
  }
  
  public void rotate_to(Entity paramEntity) {
    (give up)null;
    float[] arrayOfFloat = MathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), paramEntity.func_174791_d());
    if (this.rotate_mode.in("Off"))
      this.is_rotating = false; 
    if (this.rotate_mode.in("Good"))
      RotationUtil.setPlayerRotations(arrayOfFloat[0], arrayOfFloat[1]); 
    if (this.rotate_mode.in("Old") || this.rotate_mode.in("Cont")) {
      this.yaw = arrayOfFloat[0];
      this.pitch = arrayOfFloat[1];
      this.is_rotating = true;
    } 
  }
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    if (this.render_block_init == null)
      return; 
    if (this.render_mode.in("None"))
      return; 
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
    render_block(this.render_block_init);
    if (this.future_render.get_value(true) && this.render_block_old != null)
      render_block(this.render_block_old); 
    if (this.render_damage.get_value(true))
      RenderUtil.drawText(this.render_block_init, ((Math.floor(this.render_damage_value) == this.render_damage_value) ? (String)Integer.valueOf((int)this.render_damage_value) : String.format("%.1f", new Object[] { Double.valueOf(this.render_damage_value) })) + ""); 
  }
  
  public void lambda$new$0(EventEntityRemoved paramEventEntityRemoved) {
    (give up)null;
    if (paramEventEntityRemoved.get_entity() instanceof EntityEnderCrystal)
      this.attacked_crystals.remove(paramEventEntityRemoved.get_entity()); 
  }
  
  public void enable() {
    (give up)null;
    this.place_timeout = this.place_delay.get_value(1);
    this.break_timeout = this.break_delay.get_value(1);
    this.is_rotating = false;
    this.autoez_target = null;
    this.remove_visual_timer.reset();
    this.detail_name = null;
    this.detail_hp = 20;
  }
  
  public void lambda$new$3(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    SPacketSoundEffect sPacketSoundEffect;
    if (paramReceivePacket.get_packet() instanceof SPacketSoundEffect && (sPacketSoundEffect = (SPacketSoundEffect)paramReceivePacket.get_packet()).func_186977_b() == SoundCategory.BLOCKS && sPacketSoundEffect.func_186978_a() == SoundEvents.field_187539_bB)
      for (Entity entity : mc.field_71441_e.field_72996_f) {
        if (!(entity instanceof EntityEnderCrystal) || entity.func_70011_f(sPacketSoundEffect.func_149207_d(), sPacketSoundEffect.func_149211_e(), sPacketSoundEffect.func_149210_f()) > 6.0D)
          continue; 
        mc.field_71441_e.func_73028_b(entity.func_145782_y());
      }  
    SPacketSpawnObject sPacketSpawnObject;
    if (paramReceivePacket.get_packet() instanceof SPacketSpawnObject && (sPacketSpawnObject = (SPacketSpawnObject)paramReceivePacket.get_packet()).func_148993_l() == 51 && this.placePosList.contains((new BlockPos(sPacketSpawnObject.func_186880_c(), sPacketSpawnObject.func_186882_d(), sPacketSpawnObject.func_186881_e())).func_177977_b())) {
      CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
      cPacketUseEntity.field_149566_b = CPacketUseEntity.Action.ATTACK;
      cPacketUseEntity.field_149567_a = sPacketSpawnObject.func_149001_c();
      ((NetHandlerPlayClient)Objects.<NetHandlerPlayClient>requireNonNull(mc.func_147114_u())).func_147297_a((Packet)cPacketUseEntity);
    } 
  }
  
  public static boolean isEntity() {
    (give up)null;
    return true;
  }
  
  public void update() {
    (give up)null;
    if (this.rotate_mode.in("Off") || this.rotate_mode.in("Old"))
      do_ca(); 
    if (mc.field_71439_g.field_70128_L || mc.field_71439_g.func_110143_aJ() <= 0.0F)
      ca_target = null; 
  }
  
  public void do_ca() {
    (give up)null;
    this.did_anything = false;
    if (mc.field_71439_g == null || mc.field_71441_e == null)
      return; 
    if (this.rainbow_mode.get_value(true))
      cycle_rainbow(); 
    if (this.remove_visual_timer.passed(1000L)) {
      this.remove_visual_timer.reset();
      this.attacked_crystals.clear();
    } 
    if (check_pause())
      return; 
    if (this.place_crystal.get_value(true) && this.place_delay_counter > this.place_timeout)
      place_crystal(); 
    if (this.break_crystal.get_value(true) && this.break_delay_counter > this.break_timeout)
      break_crystal(); 
    if (!this.did_anything) {
      if (this.old_render.get_value(true))
        this.render_block_init = null; 
      this.autoez_target = null;
      this.is_rotating = false;
    } 
    if (this.autoez_target != null) {
      AutoGG.add_target(this.autoez_target.func_70005_c_());
      this.detail_name = this.autoez_target.func_70005_c_();
      this.detail_hp = Math.round(this.autoez_target.func_110143_aJ() + this.autoez_target.func_110139_bj());
    } 
    this.render_block_old = this.render_block_init;
    this.break_delay_counter++;
    this.place_delay_counter++;
  }
  
  public EntityEnderCrystal get_best_crystal() {
    (give up)null;
    double d1 = 0.0D;
    double d2 = this.max_self_damage.get_value(1);
    double d3 = 0.0D;
    EntityEnderCrystal entityEnderCrystal = null;
    for (Entity entity : mc.field_71441_e.field_72996_f) {
      if (!(entity instanceof EntityEnderCrystal))
        continue; 
      EntityEnderCrystal entityEnderCrystal1 = (EntityEnderCrystal)entity;
      if (mc.field_71439_g.func_70032_d((Entity)entityEnderCrystal1) > (!mc.field_71439_g.func_70685_l((Entity)entityEnderCrystal1) ? this.break_range_wall.get_value(1) : this.break_range.get_value(1)) || (!mc.field_71439_g.func_70685_l((Entity)entityEnderCrystal1) && this.raytrace.get_value(true)) || (this.attacked_crystals.containsKey(entityEnderCrystal1) && ((Integer)this.attacked_crystals.get(entityEnderCrystal1)).intValue() > this.antiStuckTries.get_value(1) && this.anti_stuck.get_value(true)))
        continue; 
      Iterator<EntityPlayer> iterator = mc.field_71441_e.field_73010_i.iterator();
      while (true) {
        EntityPlayer entityPlayer;
        if (iterator.hasNext()) {
          entityPlayer = iterator.next();
          if (entityPlayer == mc.field_71439_g || FriendUtil.isFriend(entityPlayer.func_70005_c_()) || entityPlayer.func_70032_d((Entity)mc.field_71439_g) >= this.enemyRange.get_value(1) || entityPlayer.field_70128_L || entityPlayer.func_110143_aJ() <= 0.0F)
            continue; 
          boolean bool = (this.faceplace_check.get_value(true) && mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_151048_u) ? true : false;
          if (entityPlayer.func_110143_aJ() < this.luscius_mode_damage.get_value(1) && this.luscius_mode.get_value(true)) {
          
          } else if (get_armor_fucker(entityPlayer)) {
          
          } else {
            double d6 = this.min_player_break.get_value(1);
            double d7 = CrystalUtil.calculateDamage(entityEnderCrystal1, (Entity)entityPlayer);
          } 
        } else {
          break;
        } 
        double d4 = 2.0D;
        double d5 = CrystalUtil.calculateDamage(entityEnderCrystal1, (Entity)entityPlayer);
      } 
      if (!this.jumpy_mode.get_value(true) || mc.field_71439_g.func_70068_e((Entity)entityEnderCrystal1) <= d3)
        continue; 
      d3 = mc.field_71439_g.func_70068_e((Entity)entityEnderCrystal1);
      entityEnderCrystal = entityEnderCrystal1;
    } 
    return entityEnderCrystal;
  }
  
  public static EntityPlayer get_target() {
    (give up)null;
    return ca_target;
  }
  
  public void disable() {
    (give up)null;
    this.render_block_init = null;
    this.autoez_target = null;
  }
  
  public String array_detail() {
    (give up)null;
    return (this.detail_name != null) ? (this.detail_name + " | " + this.detail_hp) : "None";
  }
}
