package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.Iterator;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import me.obsidianbreaker.leux.client.util.BlockInteractHelper;
import me.obsidianbreaker.leux.client.util.EntityUtil;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class AutoTrap extends Module {
  public BlockPos target_pos;
  
  public int last_hotbar_slot = -1;
  
  public int timeout_ticker = 0;
  
  public int y_level;
  
  public Vec3d[] offsets_default = new Vec3d[] { 
      new Vec3d(0.0D, 0.0D, -1.0D), new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(-1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 1.0D, -1.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(-1.0D, 1.0D, 0.0D), new Vec3d(0.0D, 2.0D, -1.0D), new Vec3d(1.0D, 2.0D, 0.0D), 
      new Vec3d(0.0D, 2.0D, 1.0D), new Vec3d(-1.0D, 2.0D, 0.0D), new Vec3d(0.0D, 3.0D, -1.0D), new Vec3d(0.0D, 3.0D, 1.0D), new Vec3d(1.0D, 3.0D, 0.0D), new Vec3d(-1.0D, 3.0D, 0.0D), new Vec3d(0.0D, 3.0D, 0.0D) };
  
  public Setting range = create("Range", "TrapRange", 5, 1, 6);
  
  public Vec3d[] offsets_feet = new Vec3d[] { 
      new Vec3d(0.0D, 0.0D, -1.0D), new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(-1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 1.0D, -1.0D), new Vec3d(0.0D, 2.0D, -1.0D), new Vec3d(1.0D, 2.0D, 0.0D), new Vec3d(0.0D, 2.0D, 1.0D), new Vec3d(-1.0D, 2.0D, 0.0D), new Vec3d(0.0D, 3.0D, -1.0D), 
      new Vec3d(0.0D, 3.0D, 1.0D), new Vec3d(1.0D, 3.0D, 0.0D), new Vec3d(-1.0D, 3.0D, 0.0D), new Vec3d(0.0D, 3.0D, 0.0D) };
  
  public Setting place_mode = create("Place Mode", "TrapPlaceMode", "Feet", combobox(new String[] { "Extra", "Face", "Normal", "Feet" }));
  
  public int player_hotbar_slot = -1;
  
  public String last_tick_target_name = "";
  
  public Vec3d[] offsets_face = new Vec3d[] { 
      new Vec3d(0.0D, 0.0D, -1.0D), new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(-1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 1.0D, -1.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(-1.0D, 1.0D, 0.0D), new Vec3d(0.0D, 2.0D, -1.0D), new Vec3d(0.0D, 3.0D, -1.0D), 
      new Vec3d(0.0D, 3.0D, 1.0D), new Vec3d(1.0D, 3.0D, 0.0D), new Vec3d(-1.0D, 3.0D, 0.0D), new Vec3d(0.0D, 3.0D, 0.0D) };
  
  public Setting r = create("Red", "TrapRed", 0, 0, 255);
  
  public Setting blocks_per_tick = create("Speed", "TrapSpeed", 3, 0, 8);
  
  public boolean first_run = true;
  
  public Setting g = create("Green", "TrapGreen", 100, 0, 255);
  
  public Setting rotate = create("Rotation", "TrapRotation", false);
  
  public Setting toggle = create("Toggle", "TrapToggle", false);
  
  public int offset_step = 0;
  
  public boolean is_sneaking = false;
  
  public Setting renderMode = create("Render Mode", "TrapMode", "Both", combobox(new String[] { "Quads", "Lines", "Both" }));
  
  public Setting render = create("Render", "TrapRender", true);
  
  public Vec3d[] offsets_extra = new Vec3d[] { 
      new Vec3d(0.0D, 0.0D, -1.0D), new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(-1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 1.0D, -1.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(-1.0D, 1.0D, 0.0D), new Vec3d(0.0D, 2.0D, -1.0D), new Vec3d(1.0D, 2.0D, 0.0D), 
      new Vec3d(0.0D, 2.0D, 1.0D), new Vec3d(-1.0D, 2.0D, 0.0D), new Vec3d(0.0D, 3.0D, -1.0D), new Vec3d(0.0D, 3.0D, 0.0D), new Vec3d(0.0D, 4.0D, 0.0D) };
  
  public Setting b = create("Blue", "TrapBlue", 100, 0, 255);
  
  public Setting a = create("Alpha", "TrapAlpha", 30, 0, 255);
  
  public void enable() {
    (give up)null;
    this.timeout_ticker = 0;
    this.y_level = (int)Math.round(mc.field_71439_g.field_70163_u);
    this.first_run = true;
    this.player_hotbar_slot = mc.field_71439_g.field_71071_by.field_70461_c;
    this.last_hotbar_slot = -1;
    if (find_obi_in_hotbar() == -1)
      set_disable(); 
  }
  
  public int find_obi_in_hotbar() {
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
  
  public void update() {
    (give up)null;
    if (this.timeout_ticker > 20 && !this.toggle.get_value(true)) {
      this.timeout_ticker = 0;
      set_disable();
    } else {
      EntityPlayer entityPlayer = find_closest_target();
      set_disable();
    } 
  }
  
  public void disable() {
    (give up)null;
    if (this.last_hotbar_slot != this.player_hotbar_slot && this.player_hotbar_slot != -1) {
      mc.field_71439_g.field_71071_by.field_70461_c = this.player_hotbar_slot;
      if (this.is_sneaking) {
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
        this.is_sneaking = false;
      } 
      this.player_hotbar_slot = -1;
      this.last_hotbar_slot = -1;
    } 
  }
  
  public boolean place_blocks(BlockPos paramBlockPos) {
    (give up)null;
    if (!mc.field_71441_e.func_180495_p(paramBlockPos).func_185904_a().func_76222_j())
      return false; 
    if (!BlockInteractHelper.checkForNeighbours(paramBlockPos))
      return false; 
    Vec3d vec3d = new Vec3d(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), mc.field_71439_g.field_70161_v);
    EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
    int i = arrayOfEnumFacing.length;
    byte b = 0;
    while (b < i) {
      EnumFacing enumFacing1 = arrayOfEnumFacing[b];
      BlockPos blockPos = paramBlockPos.func_177972_a(enumFacing1);
      EnumFacing enumFacing2 = enumFacing1.func_176734_d();
      if (!BlockInteractHelper.canBeClicked(blockPos) || vec3d.func_72438_d((new Vec3d((Vec3i)blockPos)).func_72441_c(0.5D, 0.5D, 0.5D).func_178787_e((new Vec3d(enumFacing2.func_176730_m())).func_186678_a(0.5D))) > this.range.get_value(1)) {
        b++;
        continue;
      } 
      int j = find_obi_in_hotbar();
      if (j == -1) {
        set_disable();
        return false;
      } 
      if (this.last_hotbar_slot != j) {
        mc.field_71439_g.field_71071_by.field_70461_c = j;
        this.last_hotbar_slot = j;
      } 
      if (BlockInteractHelper.blackList.contains(mc.field_71441_e.func_180495_p(blockPos).func_177230_c())) {
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
        this.is_sneaking = true;
      } 
      Vec3d vec3d1 = (new Vec3d((Vec3i)blockPos)).func_72441_c(0.5D, 0.5D, 0.5D).func_178787_e((new Vec3d(enumFacing2.func_176730_m())).func_186678_a(0.5D));
      if (this.rotate.get_value(true))
        BlockInteractHelper.faceVectorPacketInstant(vec3d1); 
      mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, blockPos, enumFacing2, vec3d1, EnumHand.MAIN_HAND);
      mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
      return true;
    } 
    return false;
  }
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    if (this.render.get_value(true) && this.target_pos != null)
      if (this.renderMode.in("Quads")) {
        RenderHelp.prepare("quads");
        RenderHelp.draw_cube(RenderHelp.get_buffer_build(), this.target_pos.func_177958_n(), this.target_pos.func_177956_o(), this.target_pos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      } else if (this.renderMode.in("Lines")) {
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), this.target_pos.func_177958_n(), this.target_pos.func_177956_o(), this.target_pos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      } else if (this.renderMode.in("Both")) {
        RenderHelp.prepare("quads");
        RenderHelp.draw_cube(RenderHelp.get_buffer_build(), this.target_pos.func_177958_n(), this.target_pos.func_177956_o(), this.target_pos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), this.target_pos.func_177958_n(), this.target_pos.func_177956_o(), this.target_pos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      }  
  }
  
  public AutoTrap() {
    super(Category.combat);
  }
  
  public EntityPlayer find_closest_target() {
    (give up)null;
    if (mc.field_71441_e.field_73010_i.isEmpty())
      return null; 
    Entity entity = null;
    Iterator<EntityPlayer> iterator = mc.field_71441_e.field_73010_i.iterator();
    while (true) {
      EntityPlayer entityPlayer;
      if (iterator.hasNext()) {
        EntityPlayer entityPlayer1 = iterator.next();
        if (entityPlayer1 != mc.field_71439_g && !FriendUtil.isFriend(entityPlayer1.func_70005_c_()) && EntityUtil.isLiving((Entity)entityPlayer1) && entityPlayer1.func_110143_aJ() > 0.0F) {
          if (mc.field_71439_g.func_70032_d((Entity)entityPlayer1) > mc.field_71439_g.func_70032_d(entity))
            continue; 
          entityPlayer = entityPlayer1;
        } 
        continue;
      } 
      return entityPlayer;
    } 
  }
}
