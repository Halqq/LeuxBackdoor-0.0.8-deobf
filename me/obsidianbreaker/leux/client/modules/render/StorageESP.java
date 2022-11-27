package me.obsidianbreaker.leux.client.modules.render;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityShulkerBox;

public class StorageESP extends Module {
  public Setting enc = create("EChests", "StorageEChests", true);
  
  public Setting che_ = create("Chest Color", "StorageESPChest", "Client", combobox(new String[] { "HUD", "Client" }));
  
  public int color_alpha;
  
  public Setting che = create("Chests", "StorageChests", true);
  
  public Setting oth = create("Misc", "StorageESPMisc", false);
  
  public Setting shu_ = create("Shulker Color", "StorageESPShulker", "Client", combobox(new String[] { "HUD", "Client", "RAINBOW" }));
  
  public Setting ot_a = create("Outline A", "StorageESPOutlineA", 128, 0, 255);
  
  public Setting enc_ = create("EChest Color", "StorageESPEnchest", "Client", combobox(new String[] { "HUD", "Client" }));
  
  public Setting shu = create("Shulkers", "StorageShulker", true);
  
  public Setting a = create("Solid A", "StorageESPSolidA", 32, 0, 255);
  
  public Setting oth_ = create("Misc Color", "StorageESPOthers", "Client", combobox(new String[] { "HUD", "Client" }));
  
  public StorageESP() {
    super(Category.render);
  }
  
  public void draw(TileEntity paramTileEntity, int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    RenderHelp.prepare("quads");
    RenderHelp.draw_cube(paramTileEntity.func_174877_v(), paramInt1, paramInt2, paramInt3, this.a.get_value(1), "all");
    RenderHelp.release();
    RenderHelp.prepare("lines");
    RenderHelp.draw_cube_line(paramTileEntity.func_174877_v(), paramInt1, paramInt2, paramInt3, this.ot_a.get_value(1), "all");
    RenderHelp.release();
  }
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    int i = Leux.client_r;
    int j = Leux.client_g;
    int k = Leux.client_b;
    this.color_alpha = this.a.get_value(1);
    for (TileEntity tileEntity : mc.field_71441_e.field_147482_g) {
      if (this.shu.get_value(true) && tileEntity instanceof TileEntityShulkerBox) {
        TileEntityShulkerBox tileEntityShulkerBox = (TileEntityShulkerBox)tileEntity;
        int m = 0xFF000000 | tileEntityShulkerBox.func_190592_s().func_193350_e() & 0xFFFFFFFF;
        if (this.shu_.in("HUD")) {
          draw(tileEntity, i, j, k);
        } else if (this.shu_.in("RAINBOW")) {
          draw(tileEntity, (m & 0xFF0000) >> 16, (m & 0xFF00) >> 8, m & 0xFF);
        } else {
          draw(tileEntity, 204, 0, 0);
        } 
      } 
      if (this.enc.get_value(true) && tileEntity instanceof net.minecraft.tileentity.TileEntityEnderChest)
        if (this.enc_.in("HUD")) {
          draw(tileEntity, i, j, k);
        } else {
          draw(tileEntity, 204, 0, 255);
        }  
      if (this.che.get_value(true) && tileEntity instanceof net.minecraft.tileentity.TileEntityChest)
        if (this.che_.in("HUD")) {
          draw(tileEntity, i, j, k);
        } else {
          draw(tileEntity, 153, 102, 0);
        }  
      if (this.oth.get_value(true) && (tileEntity instanceof net.minecraft.tileentity.TileEntityDispenser || tileEntity instanceof net.minecraft.tileentity.TileEntityDropper || tileEntity instanceof net.minecraft.tileentity.TileEntityHopper || tileEntity instanceof net.minecraft.tileentity.TileEntityFurnace || tileEntity instanceof net.minecraft.tileentity.TileEntityBrewingStand)) {
        if (this.oth_.in("HUD")) {
          draw(tileEntity, i, j, k);
          continue;
        } 
        draw(tileEntity, 190, 190, 190);
      } 
    } 
  }
}
