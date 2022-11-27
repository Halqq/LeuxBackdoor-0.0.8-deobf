package me.obsidianbreaker.leux.client.modules.misc;

import com.mojang.authlib.GameProfile;
import give up;
import java.util.UUID;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class FakePlayer extends Module {
  public EntityOtherPlayerMP fake_player;
  
  public Setting copy = create("Copy Inv", "CopyInv", true);
  
  public FakePlayer() {
    super(Category.misc);
  }
  
  public void disable() {
    (give up)null;
    mc.field_71441_e.func_72900_e((Entity)this.fake_player);
  }
  
  public void enable() {
    (give up)null;
    this.fake_player = new EntityOtherPlayerMP((World)mc.field_71441_e, new GameProfile(UUID.fromString("192bfaa7-3fe9-450d-bab3-80e6c93f3d1b"), "CRYSTALPVPFEMBOY"));
    this.fake_player.func_82149_j((Entity)mc.field_71439_g);
    this.fake_player.field_70759_as = mc.field_71439_g.field_70759_as;
    if (this.copy.get_value(true))
      this.fake_player.field_71071_by.func_70455_b(mc.field_71439_g.field_71071_by); 
    mc.field_71441_e.func_73027_a(-100, (Entity)this.fake_player);
  }
}
