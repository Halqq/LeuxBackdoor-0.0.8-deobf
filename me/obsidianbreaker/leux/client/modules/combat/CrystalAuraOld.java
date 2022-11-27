package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
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
import me.obsidianbreaker.leux.client.util.MessageUtil;
import me.obsidianbreaker.leux.client.util.Pair;
import me.obsidianbreaker.leux.client.util.PosManager;
import me.obsidianbreaker.leux.client.util.RenderUtil;
import me.obsidianbreaker.leux.client.util.RotationUtil;
import me.obsidianbreaker.leux.client.util.Timer;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class CrystalAuraOld extends Module {
  public Setting swing = create("Swing", "CaSwing", "Mainhand", combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
  
  public int break_timeout;
  
  public BlockPos render_block_old;
  
  public Setting brightness = create("Brightness", "CaBrightness", 0.8D, 0.0D, 1.0D);
  
  public Setting auto_switch = create("Auto Switch", "CaAutoSwitch", true);
  
  public double render_damage_value;
  
  public Setting min_player_break = create("Min Enemy Break", "CaMinEnemyBreak", 6, 0, 20);
  
  public EntityPlayer autoez_target = null;
  
  public Setting break_trys = create("Break Attempts", "CaBreakAttempts", 1, 1, 6);
  
  public Setting g = create("G", "CaG", 255, 0, 255);
  
  public float pitch;
  
  public int chain_step = 0;
  
  public boolean solid;
  
  public Timer remove_visual_timer = new Timer();
  
  public Setting render_mode = create("Render", "CaRenderMode", "Pretty", combobox(new String[] { "Pretty", "Solid", "Outline", "None" }));
  
  @EventHandler
  public Listener on_entity_removed = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting chain_length = create("Chain Length", "CaChainLength", 3, 1, 6);
  
  public Setting place_delay = create("Place Delay", "CaPlaceDelay", 1, 0, 10);
  
  public boolean outline;
  
  public Setting debug = create("Debug", "CaDebug", false);
  
  public Setting raytrace = create("Raytrace", "CaRaytrace", false);
  
  public Setting endcrystal = create("1.13 Mode", "CaThirteen", false);
  
  public String detail_name = null;
  
  public ConcurrentHashMap attacked_crystals = new ConcurrentHashMap<>();
  
  @EventHandler
  public Listener send_listener = new Listener(this::lambda$new$1, new java.util.function.Predicate[0]);
  
  public Setting faceplace_check = create("No Sword FP", "CaJumpyFaceMode", false);
  
  public Setting height = create("Height", "CaHeight", 1.0D, 0.0D, 1.0D);
  
  public boolean place_timeout_flag = false;
  
  public Setting break_crystal = create("Break", "CaBreak", true);
  
  public Setting fuck_armor_mode = create("Armor Destroy", "CaArmorDestory", true);
  
  public Setting anti_stuck = create("Anti Stuck", "CaAntiStuck", false);
  
  public static EntityPlayer ca_target = null;
  
  public int break_delay_counter;
  
  public int current_chain_index = 0;
  
  public Setting faceplace_mode_damage = create("T Health", "CaTabbottModeHealth", 8, 0, 36);
  
  public Setting hit_range_wall = create("Range Wall", "CaRangeWall", 4.0D, 1.0D, 6.0D);
  
  public Setting rainbow_mode = create("Rainbow", "CaRainbow", false);
  
  public Setting stop_while_mining = create("Stop While Mining", "CaStopWhileMining", false);
  
  public Setting place_crystal = create("Place", "CaPlace", true);
  
  public Setting b = create("B", "CaB", 255, 0, 255);
  
  public Setting break_delay = create("Break Delay", "CaBreakDelay", 1, 0, 10);
  
  public Setting min_player_place = create("Min Enemy Place", "CaMinEnemyPlace", 8, 0, 20);
  
  public Setting place_range = create("Place Range", "CaPlaceRange", 5.199999809265137D, 1.0D, 6.0D);
  
  public Setting hit_range = create("Hit Range", "CaHitRange", 5.199999809265137D, 1.0D, 6.0D);
  
  public Setting top_block = create("Top Block", "CaTopBlock", false);
  
  public Setting jumpy_mode = create("Jumpy Mode", "CaJumpyMode", false);
  
  public Setting anti_weakness = create("Anti-Weakness", "CaAntiWeakness", true);
  
  public Timer chain_timer = new Timer();
  
  public BlockPos render_block_init;
  
  public int detail_hp = 0;
  
  public Setting old_render = create("Old Render", "CaOldRender", false);
  
  @EventHandler
  public Listener on_movement = new Listener(this::lambda$new$2, new java.util.function.Predicate[0]);
  
  public float yaw;
  
  public Setting faceplace_mode = create("Tabbott Mode", "CaTabbottMode", true);
  
  public Setting client_side = create("Client Side", "CaClientSide", false);
  
  public Setting a = create("A", "CaA", 100, 0, 255);
  
  public Setting max_self_damage = create("Max Self Damage", "CaMaxSelfDamage", 6, 0, 20);
  
  public int place_timeout;
  
  public Setting r = create("R", "CaR", 255, 0, 255);
  
  public int place_delay_counter;
  
  public Setting render_damage = create("Render Damage", "RenderDamage", true);
  
  public boolean is_rotating;
  
  public Setting fast_mode = create("Fast Mode", "CaSpeed", true);
  
  public boolean already_attacking = false;
  
  public Setting rotate_mode = create("Rotate", "CaRotateMode", "Good", combobox(new String[] { "Off", "Old", "Const", "Good" }));
  
  public Setting fuck_armor_mode_precent = create("Armor %", "CaArmorPercent", 25, 0, 100);
  
  public Setting a_out = create("Outline A", "CaOutlineA", 255, 0, 255);
  
  public boolean did_anything;
  
  public Setting sat = create("Satiation", "CaSatiation", 0.8D, 0.0D, 1.0D);
  
  @EventHandler
  public Listener receive_listener = new Listener(CrystalAuraOld::lambda$new$3, new java.util.function.Predicate[0]);
  
  public Setting future_render = create("Future Render", "CaFutureRender", false);
  
  public Setting anti_suicide = create("Anti Suicide", "CaAntiSuicide", true);
  
  public void break_crystal() {
    (give up)null;
    EntityEnderCrystal entityEnderCrystal = get_best_crystal();
  }
  
  public void add_attacked_crystal(EntityEnderCrystal paramEntityEnderCrystal) {
    (give up)null;
    if (this.attacked_crystals.containsKey(paramEntityEnderCrystal)) {
      int i = ((Integer)this.attacked_crystals.get(paramEntityEnderCrystal)).intValue();
      this.attacked_crystals.put(paramEntityEnderCrystal, Integer.valueOf(i + 1));
    } else {
      this.attacked_crystals.put(paramEntityEnderCrystal, Integer.valueOf(1));
    } 
  }
  
  public List sort_best_blocks(List paramList) {
    (give up)null;
    ArrayList arrayList = new ArrayList();
    double d = 1000.0D;
    for (byte b = 0; b < paramList.size(); b++) {
      double d1 = 0.0D;
      Pair pair = null;
      for (Pair pair1 : paramList) {
        if (((Double)pair1.getKey()).doubleValue() > d1 && ((Double)pair1.getKey()).doubleValue() < d)
          pair = pair1; 
      } 
    } 
    return arrayList;
  }
  
  public BlockPos get_best_block() {
    (give up)null;
    if (get_best_crystal() != null && !this.fast_mode.get_value(true)) {
      this.place_timeout_flag = true;
      return null;
    } 
    if (this.place_timeout_flag) {
      this.place_timeout_flag = false;
      return null;
    } 
    ArrayList arrayList = new ArrayList();
    double d1 = 0.0D;
    double d2 = this.max_self_damage.get_value(1);
    BlockPos blockPos = null;
    List list2 = CrystalUtil.possiblePlacePositions(this.place_range.get_value(1), this.endcrystal.get_value(true), true);
    for (Entity entity : mc.field_71441_e.field_73010_i) {
      if (FriendUtil.isFriend(entity.func_70005_c_()))
        continue; 
      ca_target = (EntityPlayer)entity;
      Iterator<BlockPos> iterator = list2.iterator();
      while (true) {
        BlockPos blockPos1;
        EntityPlayer entityPlayer;
        if (iterator.hasNext()) {
          blockPos1 = iterator.next();
          if (entity == mc.field_71439_g || !(entity instanceof EntityPlayer) || entity.func_70032_d((Entity)mc.field_71439_g) >= Float.intBitsToFloat(1.06894656E9F ^ 0x7E86D48D) || !BlockUtil.rayTracePlaceCheck(blockPos1, this.raytrace.get_value(true)) || (!BlockUtil.canSeeBlock(blockPos1) && mc.field_71439_g.func_70011_f(blockPos1.func_177958_n(), blockPos1.func_177956_o(), blockPos1.func_177952_p()) > this.hit_range_wall.get_value(1)))
            continue; 
          entityPlayer = (EntityPlayer)entity;
          if (entityPlayer.field_70128_L || entityPlayer.func_110143_aJ() <= Float.intBitsToFloat(2.12809331E9F ^ 0x7ED820AD))
            continue; 
          boolean bool = (this.faceplace_check.get_value(true) && mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_151048_u) ? true : false;
          if (entityPlayer.func_110143_aJ() < this.faceplace_mode_damage.get_value(1) && this.faceplace_mode.get_value(true)) {
          
          } else if (get_armor_fucker(entityPlayer)) {
          
          } else {
            double d4 = this.min_player_place.get_value(1);
            double d5 = CrystalUtil.calculateDamage(blockPos1.func_177958_n() + 0.5D, blockPos1.func_177956_o() + 1.0D, blockPos1.func_177952_p() + 0.5D, (Entity)entityPlayer);
          } 
          double d3 = 2.0D;
        } else {
          break;
        } 
        double d = CrystalUtil.calculateDamage(blockPos1.func_177958_n() + 0.5D, blockPos1.func_177956_o() + 1.0D, blockPos1.func_177952_p() + 0.5D, (Entity)entityPlayer);
      } 
    } 
    list2.clear();
    if (this.chain_step == 1) {
      this.current_chain_index = this.chain_length.get_value(1);
    } else if (this.chain_step > 1) {
      this.current_chain_index--;
    } 
    this.render_damage_value = d1;
    this.render_block_init = blockPos;
    List list1 = sort_best_blocks(arrayList);
    return blockPos;
  }
  
  public void lambda$new$2(EventMotionUpdate paramEventMotionUpdate) {
    (give up)null;
    if (paramEventMotionUpdate.stage == 0 && (this.rotate_mode.in("Good") || this.rotate_mode.in("Const"))) {
      if (this.debug.get_value(true))
        MessageUtil.send_client_message("updating rotation"); 
      PosManager.updatePosition();
      RotationUtil.updateRotations();
      do_ca();
    } 
    if (paramEventMotionUpdate.stage == 1 && (this.rotate_mode.in("Good") || this.rotate_mode.in("Const"))) {
      if (this.debug.get_value(true))
        MessageUtil.send_client_message("resetting rotation"); 
      PosManager.restorePosition();
      RotationUtil.restoreRotations();
    } 
  }
  
  public void disable() {
    (give up)null;
    this.render_block_init = null;
    this.autoez_target = null;
  }
  
  public void enable() {
    (give up)null;
    this.place_timeout = this.place_delay.get_value(1);
    this.break_timeout = this.break_delay.get_value(1);
    this.place_timeout_flag = false;
    this.is_rotating = false;
    this.autoez_target = null;
    this.chain_step = 0;
    this.current_chain_index = 0;
    this.chain_timer.reset();
    this.remove_visual_timer.reset();
    this.detail_name = null;
    this.detail_hp = 20;
  }
  
  public CrystalAuraOld() {
    super(Category.combat);
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
    if (Leux.get_hack_manager().get_module_with_tag("Surround").is_active()) {
      if (this.old_render.get_value(true))
        this.render_block_init = null; 
      return true;
    } 
    if (Leux.get_hack_manager().get_module_with_tag("HoleFill").is_active()) {
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
  
  public static EntityPlayer get_target() {
    (give up)null;
    return ca_target;
  }
  
  public int find_crystals_hotbar() {
    (give up)null;
    byte b = 0;
    while (true) {
      9;
      if (mc.field_71439_g.field_71071_by.func_70301_a(b).func_77973_b() == Items.field_185158_cP)
        return b; 
    } 
  }
  
  public void lambda$new$1(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (paramSendPacket.get_packet() instanceof CPacketPlayer && this.is_rotating && this.rotate_mode.in("Old")) {
      if (this.debug.get_value(true))
        MessageUtil.send_client_message("Rotating"); 
      CPacketPlayer cPacketPlayer = (CPacketPlayer)paramSendPacket.get_packet();
      cPacketPlayer.field_149476_e = this.yaw;
      cPacketPlayer.field_149473_f = this.pitch;
      this.is_rotating = false;
    } 
    if (paramSendPacket.get_packet() instanceof CPacketPlayerTryUseItemOnBlock && this.is_rotating && this.rotate_mode.in("Old")) {
      if (this.debug.get_value(true))
        MessageUtil.send_client_message("Rotating"); 
      CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)paramSendPacket.get_packet();
      cPacketPlayerTryUseItemOnBlock.field_149577_f = this.render_block_init.func_177958_n();
      cPacketPlayerTryUseItemOnBlock.field_149578_g = this.render_block_init.func_177956_o();
      cPacketPlayerTryUseItemOnBlock.field_149584_h = this.render_block_init.func_177952_p();
      this.is_rotating = false;
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
  
  public String array_detail() {
    (give up)null;
    return (this.detail_name != null) ? (this.detail_name + " | " + this.detail_hp) : "None";
  }
  
  public void update() {
    (give up)null;
    if (this.rotate_mode.in("Off") || this.rotate_mode.in("Old"))
      do_ca(); 
    if (mc.field_71439_g.field_70128_L || mc.field_71439_g.func_110143_aJ() <= 0.0F)
      ca_target = null; 
  }
  
  public static void lambda$new$3(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    if (paramReceivePacket.get_packet() instanceof SPacketSoundEffect) {
      SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)paramReceivePacket.get_packet();
      if (sPacketSoundEffect.func_186977_b() == SoundCategory.BLOCKS && sPacketSoundEffect.func_186978_a() == SoundEvents.field_187539_bB)
        for (Entity entity : mc.field_71441_e.field_72996_f) {
          if (entity instanceof EntityEnderCrystal && entity.func_70011_f(sPacketSoundEffect.func_149207_d(), sPacketSoundEffect.func_149211_e(), sPacketSoundEffect.func_149210_f()) <= 6.0D)
            entity.func_70106_y(); 
        }  
    } 
  }
  
  public void place_crystal() {
    (give up)null;
    BlockPos blockPos = get_best_block();
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
  
  public void lambda$new$0(EventEntityRemoved paramEventEntityRemoved) {
    (give up)null;
    if (paramEventEntityRemoved.get_entity() instanceof EntityEnderCrystal)
      this.attacked_crystals.remove(paramEventEntityRemoved.get_entity()); 
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
      if (mc.field_71439_g.func_70032_d((Entity)entityEnderCrystal1) > (!mc.field_71439_g.func_70685_l((Entity)entityEnderCrystal1) ? this.hit_range_wall.get_value(1) : this.hit_range.get_value(1)) || (!mc.field_71439_g.func_70685_l((Entity)entityEnderCrystal1) && this.raytrace.get_value(true)) || entityEnderCrystal1.field_70128_L || (this.attacked_crystals.containsKey(entityEnderCrystal1) && ((Integer)this.attacked_crystals.get(entityEnderCrystal1)).intValue() > 5 && this.anti_stuck.get_value(true)))
        continue; 
      Iterator<Entity> iterator = mc.field_71441_e.field_73010_i.iterator();
      while (true) {
        EntityPlayer entityPlayer;
        if (iterator.hasNext()) {
          Entity entity1 = iterator.next();
          if (entity1 == mc.field_71439_g || !(entity1 instanceof EntityPlayer) || FriendUtil.isFriend(entity1.func_70005_c_()) || entity1.func_70032_d((Entity)mc.field_71439_g) >= 11.0F)
            continue; 
          entityPlayer = (EntityPlayer)entity1;
          if (entityPlayer.field_70128_L || entityPlayer.func_110143_aJ() <= 0.0F)
            continue; 
          boolean bool = (this.faceplace_check.get_value(true) && mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_151048_u) ? true : false;
          if (entityPlayer.func_110143_aJ() < this.faceplace_mode_damage.get_value(1) && this.faceplace_mode.get_value(true)) {
          
          } else if (get_armor_fucker(entityPlayer)) {
          
          } else {
            double d5 = this.min_player_break.get_value(1);
            double d6 = CrystalUtil.calculateDamage(entityEnderCrystal1, (Entity)entityPlayer);
          } 
          double d4 = 2.0D;
        } else {
          break;
        } 
        double d = CrystalUtil.calculateDamage(entityEnderCrystal1, (Entity)entityPlayer);
      } 
      if (this.jumpy_mode.get_value(true) && mc.field_71439_g.func_70068_e((Entity)entityEnderCrystal1) > d3) {
        d3 = mc.field_71439_g.func_70068_e((Entity)entityEnderCrystal1);
        entityEnderCrystal = entityEnderCrystal1;
      } 
    } 
    return entityEnderCrystal;
  }
  
  public boolean get_armor_fucker(EntityPlayer paramEntityPlayer) {
    (give up)null;
    for (ItemStack itemStack : paramEntityPlayer.func_184193_aE()) {
      if (itemStack.func_77973_b() == Items.field_190931_a)
        return true; 
      float f = (itemStack.func_77958_k() - itemStack.func_77952_i()) / itemStack.func_77958_k() * 100.0F;
      if (this.fuck_armor_mode.get_value(true) && this.fuck_armor_mode_precent.get_value(1) >= f)
        return true; 
    } 
    return false;
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
    if (this.chain_timer.passed(1000L)) {
      this.chain_timer.reset();
      this.chain_step = 0;
    } 
    this.render_block_old = this.render_block_init;
    this.break_delay_counter++;
    this.place_delay_counter++;
  }
  
  public void rotate_to_pos(BlockPos paramBlockPos) {
    float[] arrayOfFloat;
    (give up)null;
    if (this.rotate_mode.in("Const")) {
      arrayOfFloat = MathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), new Vec3d((paramBlockPos.func_177958_n() + Float.intBitsToFloat(1.09551693E9F ^ 0x7E4C42E7)), (paramBlockPos.func_177956_o() + Float.intBitsToFloat(1.07785792E9F ^ 0x7F3ECE98)), (paramBlockPos.func_177952_p() + Float.intBitsToFloat(1.07845606E9F ^ 0x7F47EF01))));
    } else {
      arrayOfFloat = MathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), new Vec3d((paramBlockPos.func_177958_n() + Float.intBitsToFloat(1.13272819E9F ^ 0x7C840FBF)), (paramBlockPos.func_177956_o() - Float.intBitsToFloat(1.07577357E9F ^ 0x7F1F00BD)), (paramBlockPos.func_177952_p() + Float.intBitsToFloat(1.09906496E9F ^ 0x7E826659))));
    } 
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
  
  public void cycle_rainbow() {
    (give up)null;
    float[] arrayOfFloat = { (float)(System.currentTimeMillis() % 11520L) / 11520.0F };
    int i = Color.HSBtoRGB(arrayOfFloat[0], this.sat.get_value(1), this.brightness.get_value(1));
    this.r.set_value(i >> 16 & 0xFF);
    this.g.set_value(i >> 8 & 0xFF);
    this.b.set_value(i & 0xFF);
  }
}
