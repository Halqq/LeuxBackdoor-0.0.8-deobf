package me.obsidianbreaker.leux.client.modules.movement;

import give up;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.event.events.EventMotionUpdate;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Anchor extends Module {
  public static boolean AnchorING;
  
  public ArrayList holes = new ArrayList();
  
  public Setting Pull = create("Pull", "AnchorPull", true);
  
  public Vec3d Center = Vec3d.field_186680_a;
  
  public Setting Pitch = create("Pitch", "AnchorPitch", 60, 0, 90);
  
  public int holeblocks;
  
  @EventHandler
  public Listener OnClientTick = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Vec3d GetCenter(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    double d1 = Math.floor(paramDouble1) + 0.5D;
    double d2 = Math.floor(paramDouble2);
    double d3 = Math.floor(paramDouble3) + 0.5D;
    return new Vec3d(d1, d2, d3);
  }
  
  public boolean isBlockHole(BlockPos paramBlockPos) {
    (give up)null;
    this.holeblocks = 0;
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, 3, 0)).func_177230_c() == Blocks.field_150350_a)
      this.holeblocks++; 
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, 2, 0)).func_177230_c() == Blocks.field_150350_a)
      this.holeblocks++; 
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, 1, 0)).func_177230_c() == Blocks.field_150350_a)
      this.holeblocks++; 
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, 0, 0)).func_177230_c() == Blocks.field_150350_a)
      this.holeblocks++; 
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, -1, 0)).func_177230_c() == Blocks.field_150343_Z || mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, -1, 0)).func_177230_c() == Blocks.field_150357_h)
      this.holeblocks++; 
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(1, 0, 0)).func_177230_c() == Blocks.field_150343_Z || mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(1, 0, 0)).func_177230_c() == Blocks.field_150357_h)
      this.holeblocks++; 
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(-1, 0, 0)).func_177230_c() == Blocks.field_150343_Z || mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(-1, 0, 0)).func_177230_c() == Blocks.field_150357_h)
      this.holeblocks++; 
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, 0, 1)).func_177230_c() == Blocks.field_150343_Z || mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, 0, 1)).func_177230_c() == Blocks.field_150357_h)
      this.holeblocks++; 
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, 0, -1)).func_177230_c() == Blocks.field_150343_Z || mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, 0, -1)).func_177230_c() == Blocks.field_150357_h)
      this.holeblocks++; 
    return (this.holeblocks >= 9);
  }
  
  public void onDisable() {
    (give up)null;
    AnchorING = false;
    this.holeblocks = 0;
  }
  
  public BlockPos getPlayerPos() {
    (give up)null;
    return new BlockPos(Math.floor(mc.field_71439_g.field_70165_t), Math.floor(mc.field_71439_g.field_70163_u), Math.floor(mc.field_71439_g.field_70161_v));
  }
  
  public Anchor() {
    super(Category.movement);
  }
  
  public void lambda$new$0(EventMotionUpdate paramEventMotionUpdate) {
    (give up)null;
    if (mc.field_71439_g.field_70125_A >= this.Pitch.get_value(60))
      if (isBlockHole(getPlayerPos().func_177979_c(1)) || isBlockHole(getPlayerPos().func_177979_c(2)) || isBlockHole(getPlayerPos().func_177979_c(3)) || isBlockHole(getPlayerPos().func_177979_c(4))) {
        AnchorING = true;
        if (!this.Pull.get_value(true)) {
          mc.field_71439_g.field_70159_w = 0.0D;
          mc.field_71439_g.field_70179_y = 0.0D;
        } else {
          this.Center = GetCenter(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v);
          double d1 = Math.abs(this.Center.field_72450_a - mc.field_71439_g.field_70165_t);
          double d2 = Math.abs(this.Center.field_72449_c - mc.field_71439_g.field_70161_v);
          if (d1 <= 0.1D && d2 <= 0.1D) {
            this.Center = Vec3d.field_186680_a;
          } else {
            double d3 = this.Center.field_72450_a - mc.field_71439_g.field_70165_t;
            double d4 = this.Center.field_72449_c - mc.field_71439_g.field_70161_v;
            mc.field_71439_g.field_70159_w = d3 / 2.0D;
            mc.field_71439_g.field_70179_y = d4 / 2.0D;
          } 
        } 
      } else {
        AnchorING = false;
      }  
  }
}
