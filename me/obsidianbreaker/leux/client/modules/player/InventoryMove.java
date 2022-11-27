package me.obsidianbreaker.leux.client.modules.player;

import give up;
import me.obsidianbreaker.leux.client.event.events.EventGUIScreen;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.input.Keyboard;

public class InventoryMove extends Module {
  public static KeyBinding[] KEYS = new KeyBinding[] { mc.field_71474_y.field_74351_w, mc.field_71474_y.field_74366_z, mc.field_71474_y.field_74368_y, mc.field_71474_y.field_74370_x, mc.field_71474_y.field_74314_A, mc.field_71474_y.field_151444_V };
  
  @EventHandler
  public Listener state_gui = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g == null && mc.field_71441_e == null)
      return; 
    if (mc.field_71462_r instanceof net.minecraft.client.gui.GuiChat || mc.field_71462_r == null)
      return; 
    walk();
  }
  
  public void lambda$new$0(EventGUIScreen paramEventGUIScreen) {
    (give up)null;
    if (mc.field_71439_g == null && mc.field_71441_e == null)
      return; 
    if (paramEventGUIScreen.get_guiscreen() instanceof net.minecraft.client.gui.GuiChat || paramEventGUIScreen.get_guiscreen() == null)
      return; 
    walk();
  }
  
  public InventoryMove() {
    super(Category.player);
  }
  
  public void walk() {
    (give up)null;
    for (KeyBinding keyBinding : KEYS) {
      if (Keyboard.isKeyDown(keyBinding.func_151463_i())) {
        if (keyBinding.getKeyConflictContext() != KeyConflictContext.UNIVERSAL)
          keyBinding.setKeyConflictContext((IKeyConflictContext)KeyConflictContext.UNIVERSAL); 
        KeyBinding.func_74510_a(keyBinding.func_151463_i(), true);
      } else {
        KeyBinding.func_74510_a(keyBinding.func_151463_i(), false);
      } 
    } 
  }
}
