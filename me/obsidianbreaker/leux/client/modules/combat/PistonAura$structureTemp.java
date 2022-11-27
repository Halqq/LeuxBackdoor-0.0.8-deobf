package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.List;

public class PistonAura$structureTemp {
  public float offsetY;
  
  public double distance;
  
  public int direction;
  
  public int supportBlock;
  
  public float offsetZ;
  
  public float offsetX;
  
  public List to_place;
  
  public void replaceValues(double paramDouble, int paramInt1, List paramList, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3) {
    (give up)null;
    this.distance = paramDouble;
    this.supportBlock = paramInt1;
    this.to_place = paramList;
    this.direction = paramInt2;
    this.offsetX = paramFloat1;
    this.offsetZ = paramFloat2;
    this.offsetY = paramFloat3;
  }
  
  public PistonAura$structureTemp(double paramDouble, int paramInt, List paramList) {
    this.distance = paramDouble;
    this.supportBlock = paramInt;
    this.to_place = paramList;
    this.direction = -1;
  }
}
