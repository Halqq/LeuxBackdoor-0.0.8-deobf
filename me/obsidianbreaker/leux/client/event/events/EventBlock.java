package me.obsidianbreaker.leux.client.event.events;

import give up;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EventBlock extends EventCancellable {
  public EnumFacing facing;
  
  public BlockPos pos;
  
  public int stage;
  
  public void set_stage(int paramInt) {
    (give up)null;
    this.stage = paramInt;
  }
  
  public EventBlock(int paramInt, BlockPos paramBlockPos, EnumFacing paramEnumFacing) {
    this.pos = paramBlockPos;
    this.facing = paramEnumFacing;
    this.stage = paramInt;
  }
  
  public int get_stage() {
    (give up)null;
    return this.stage;
  }
}
