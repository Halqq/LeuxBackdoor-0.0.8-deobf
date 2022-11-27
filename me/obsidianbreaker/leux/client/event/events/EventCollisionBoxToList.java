package me.obsidianbreaker.leux.client.event.events;

import give up;
import java.util.List;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EventCollisionBoxToList extends EventCancellable {
  public Block block;
  
  public IBlockState state;
  
  public World world;
  
  public List collidingBoxes;
  
  public BlockPos pos;
  
  public Entity entity;
  
  public AxisAlignedBB entityBox;
  
  public boolean bool;
  
  public Block getBlock() {
    (give up)null;
    return this.block;
  }
  
  public EventCollisionBoxToList(Block paramBlock, IBlockState paramIBlockState, World paramWorld, BlockPos paramBlockPos, AxisAlignedBB paramAxisAlignedBB, List paramList, Entity paramEntity, boolean paramBoolean) {
    this.block = paramBlock;
    this.state = paramIBlockState;
    this.world = paramWorld;
    this.pos = paramBlockPos;
    this.entityBox = paramAxisAlignedBB;
    this.collidingBoxes = paramList;
    this.entity = paramEntity;
    this.bool = paramBoolean;
  }
  
  public List getCollidingBoxes() {
    (give up)null;
    return this.collidingBoxes;
  }
  
  public World getWorld() {
    (give up)null;
    return this.world;
  }
  
  public IBlockState getState() {
    (give up)null;
    return this.state;
  }
  
  public Entity getEntity() {
    (give up)null;
    return this.entity;
  }
  
  public boolean isBool() {
    (give up)null;
    return this.bool;
  }
  
  public BlockPos getPos() {
    (give up)null;
    return this.pos;
  }
  
  public AxisAlignedBB getEntityBox() {
    (give up)null;
    return this.entityBox;
  }
}
