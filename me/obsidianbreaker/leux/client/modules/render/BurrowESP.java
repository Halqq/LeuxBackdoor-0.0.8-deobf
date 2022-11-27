package me.obsidianbreaker.leux.client.modules.render;

import give up;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.RenderUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BurrowESP extends Module {
  public Setting alpha = create("Alpha", "Alpha", 100, 0, 255);
  
  public Setting box = create("Box", "Box", true);
  
  public Setting outline = create("Outline", "Outline", true);
  
  public List posList = new ArrayList();
  
  public Setting green = create("Green", "Green", 50, 0, 255);
  
  public Setting red = create("Red", "Red", 50, 0, 255);
  
  public Setting blue = create("Blue", "Blue", 50, 0, 255);
  
  public void update() {
    (give up)null;
    this.posList.clear();
    Iterator<EntityPlayer> iterator = mc.field_71441_e.field_73010_i.iterator();
    while (true) {
      if (iterator.hasNext()) {
        EntityPlayer entityPlayer = iterator.next();
        BlockPos blockPos = new BlockPos(entityPlayer.field_70165_t, entityPlayer.field_70163_u, entityPlayer.field_70161_v);
        if (mc.field_71441_e.func_180495_p(blockPos).func_177230_c().equals(Blocks.field_150343_Z) || mc.field_71441_e.func_180495_p(blockPos).func_177230_c().equals(Blocks.field_150477_bB))
          this.posList.add(blockPos); 
        continue;
      } 
      return;
    } 
  }
  
  public BurrowESP() {
    super(Category.render);
  }
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    for (BlockPos blockPos : this.posList)
      RenderUtil.drawBox(blockPos, new Color(this.red.get_value(1), this.green.get_value(1), this.blue.get_value(1), this.alpha.get_value(1)), this.box.get_value(true), this.outline.get_value(true)); 
  }
}
