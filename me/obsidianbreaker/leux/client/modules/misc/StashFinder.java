package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public class StashFinder extends Module {
  public Setting sound = create("Play Sound", "StashFinderSound", true);
  
  public Setting file = create("Log to File", "StashFinderFile", true);
  
  public Setting shulkers = create("Log Shulkers", "StashFinderShulkers", false);
  
  public ArrayList chestPositions = new ArrayList();
  
  public Setting chests = create("Chests", "StashFinderChests", 6, 2, 20);
  
  public Map stashMap = new HashMap<>();
  
  public void enable() {
    (give up)null;
    this.chestPositions.clear();
    this.stashMap.clear();
  }
  
  public StashFinder() {
    super(Category.misc);
  }
  
  public void update() {
    (give up)null;
    for (TileEntity tileEntity : mc.field_71441_e.field_147482_g) {
      BlockPos blockPos = tileEntity.func_174877_v();
      if (tileEntity instanceof net.minecraft.tileentity.TileEntityChest || tileEntity instanceof net.minecraft.tileentity.TileEntityShulkerBox) {
        boolean bool = false;
        for (BlockPos blockPos1 : this.chestPositions) {
          if (blockPos1.equals(blockPos)) {
            bool = true;
            break;
          } 
        } 
        this.chestPositions.add(blockPos);
        int i = blockPos.func_177958_n() / 16;
        int j = blockPos.func_177952_p() / 16;
        long l = ChunkPos.func_77272_a(i, j);
        if (!this.stashMap.containsKey(Long.valueOf(l)))
          this.stashMap.put(Long.valueOf(l), Integer.valueOf(0)); 
        int k = this.chests.get_value(1);
        int m = ((Integer)this.stashMap.get(Long.valueOf(l))).intValue() + 1;
        if (this.shulkers.get_value(true) && tileEntity instanceof net.minecraft.tileentity.TileEntityShulkerBox && m < k)
          m = k; 
        this.stashMap.put(Long.valueOf(l), Integer.valueOf(m));
        if (m == k) {
          if (tileEntity instanceof net.minecraft.tileentity.TileEntityShulkerBox) {
            MessageUtil.send_client_message("1 or more Shulker Boxs found at " + blockPos.toString());
          } else {
            MessageUtil.send_client_message(m + " or more Chests found at " + blockPos.toString());
          } 
          if (this.sound.get_value(true))
            mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a(SoundEvents.field_187604_bf, Float.intBitsToFloat(1.09489997E9F ^ 0x7EC2D933), Float.intBitsToFloat(1.08253171E9F ^ 0x7F061FB1))); 
          if (this.file.get_value(true)) {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("LeuxBackdoor\\stashfinder.json", true));
            String str = "";
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = str + "[" + simpleDateFormat.format(calendar.getTime()) + "|";
            if (mc.func_147104_D() != null)
              str = str + (mc.func_147104_D()).field_78845_b + "|"; 
            switch (mc.field_71439_g.field_71093_bK) {
              case 0:
                str = str + "Overworld";
                break;
              case 1:
                str = str + "End";
                break;
              case -1:
                str = str + "Nether";
                break;
            } 
            str = str + "] ";
            if (tileEntity instanceof net.minecraft.tileentity.TileEntityShulkerBox && this.shulkers.get_value(true)) {
              str = str + "Shulker Box found at " + blockPos.toString();
            } else if (this.chests.get_value(true)) {
              str = str + m + " or more Chests found at " + blockPos.toString();
            } 
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.close();
          } 
        } 
      } 
    } 
  }
}
