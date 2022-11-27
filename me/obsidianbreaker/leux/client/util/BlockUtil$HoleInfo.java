package me.obsidianbreaker.leux.client.util;

import give up;
import net.minecraft.util.math.AxisAlignedBB;

public class BlockUtil$HoleInfo {
  public AxisAlignedBB centre;
  
  public BlockUtil$HoleType type;
  
  public BlockUtil$BlockSafety safety;
  
  public void setCentre(AxisAlignedBB paramAxisAlignedBB) {
    (give up)null;
    this.centre = paramAxisAlignedBB;
  }
  
  public BlockUtil$BlockSafety getSafety() {
    (give up)null;
    return this.safety;
  }
  
  public BlockUtil$HoleInfo(BlockUtil$BlockSafety paramBlockUtil$BlockSafety, BlockUtil$HoleType paramBlockUtil$HoleType) {
    this.type = paramBlockUtil$HoleType;
    this.safety = paramBlockUtil$BlockSafety;
  }
  
  public BlockUtil$HoleType getType() {
    (give up)null;
    return this.type;
  }
  
  public BlockUtil$HoleInfo() {
    this(BlockUtil$BlockSafety.UNBREAKABLE, BlockUtil$HoleType.NONE);
  }
  
  public AxisAlignedBB getCentre() {
    (give up)null;
    return this.centre;
  }
  
  public void setSafety(BlockUtil$BlockSafety paramBlockUtil$BlockSafety) {
    (give up)null;
    this.safety = paramBlockUtil$BlockSafety;
  }
  
  public void setType(BlockUtil$HoleType paramBlockUtil$HoleType) {
    (give up)null;
    this.type = paramBlockUtil$HoleType;
  }
}
