package me.obsidianbreaker.leux.client.event.events;

import give up;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EventDamageBlock extends EventCancellable {
  public BlockPos BlockPos;
  
  public EnumFacing Direction;
  
  public void setDirection(EnumFacing paramEnumFacing) {
    (give up)null;
    this.Direction = paramEnumFacing;
  }
  
  public EnumFacing getDirection() {
    (give up)null;
    return this.Direction;
  }
  
  public BlockPos getPos() {
    (give up)null;
    return this.BlockPos;
  }
  
  public EventDamageBlock(BlockPos paramBlockPos, EnumFacing paramEnumFacing) {
    this.BlockPos = paramBlockPos;
    setDirection(paramEnumFacing);
  }
}
