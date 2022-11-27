package me.obsidianbreaker.leux.client.util;

import give up;
import net.minecraft.util.math.BlockPos;

public enum BlockUtil$BlockOffset {
  WEST,
  DOWN(0, -1, 0),
  UP(0, 1, 0),
  EAST(0, 1, 0),
  NORTH(0, 0, -1),
  SOUTH(0, 0, -1);
  
  public static BlockUtil$BlockOffset[] $VALUES;
  
  public int z;
  
  public int y;
  
  public int x;
  
  public BlockPos right(BlockPos paramBlockPos, int paramInt) {
    (give up)null;
    return paramBlockPos.func_177982_a(-this.z * paramInt, 0, this.x * paramInt);
  }
  
  public BlockPos offset(BlockPos paramBlockPos) {
    (give up)null;
    return paramBlockPos.func_177982_a(this.x, this.y, this.z);
  }
  
  public BlockPos forward(BlockPos paramBlockPos, int paramInt) {
    (give up)null;
    return paramBlockPos.func_177982_a(this.x * paramInt, 0, this.z * paramInt);
  }
  
  public BlockPos left(BlockPos paramBlockPos, int paramInt) {
    (give up)null;
    return paramBlockPos.func_177982_a(this.z * paramInt, 0, -this.x * paramInt);
  }
  
  static {
    EAST = new BlockUtil$BlockOffset("EAST", 3, 1, 0, 0);
    SOUTH = new BlockUtil$BlockOffset("SOUTH", 4, 0, 0, 1);
    WEST = new BlockUtil$BlockOffset("WEST", 5, -1, 0, 0);
    $VALUES = new BlockUtil$BlockOffset[] { DOWN, UP, NORTH, EAST, SOUTH, WEST };
  }
  
  BlockUtil$BlockOffset(int paramInt1, int paramInt2, int paramInt3) {
    this.x = paramInt1;
    this.y = paramInt2;
    this.z = paramInt3;
  }
  
  public BlockPos backward(BlockPos paramBlockPos, int paramInt) {
    (give up)null;
    return paramBlockPos.func_177982_a(-this.x * paramInt, 0, -this.z * paramInt);
  }
}
