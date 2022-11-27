package me.obsidianbreaker.leux.client.manager;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.command.Commands;
import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventGameOverlay;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class EventManager {
  public Minecraft mc = Minecraft.func_71410_x();
  
  @SubscribeEvent
  @SubscribeEvent
  public void onRender(RenderGameOverlayEvent.Post paramPost) {
    (give up)null;
    if (paramPost.isCanceled())
      return; 
    EventClientBus.EVENT_BUS.post(new EventGameOverlay(paramPost.getPartialTicks(), new ScaledResolution(this.mc)));
    RenderGameOverlayEvent.ElementType elementType = RenderGameOverlayEvent.ElementType.EXPERIENCE;
    if (!this.mc.field_71439_g.func_184812_l_() && this.mc.field_71439_g.func_184187_bx() instanceof net.minecraft.entity.passive.AbstractHorse)
      elementType = RenderGameOverlayEvent.ElementType.HEALTHMOUNT; 
    if (paramPost.getType() == elementType) {
      Leux.get_hack_manager().render();
      if (!Leux.get_hack_manager().get_module_with_tag("GUI").is_active())
        Leux.get_hud_manager().render(); 
      GL11.glPushMatrix();
      GL11.glEnable(3553);
      GL11.glEnable(3042);
      GlStateManager.func_179147_l();
      GL11.glPopMatrix();
      RenderHelp.release_gl();
    } 
  }
  
  @SubscribeEvent
  @SubscribeEvent
  public void onUpdate(LivingEvent.LivingUpdateEvent paramLivingUpdateEvent) {
    (give up)null;
    if (paramLivingUpdateEvent.isCanceled())
      return; 
  }
  
  @SubscribeEvent
  @SubscribeEvent
  public void onNoRender(RenderBlockOverlayEvent paramRenderBlockOverlayEvent) {
    (give up)null;
    EventClientBus.EVENT_BUS.post(paramRenderBlockOverlayEvent);
  }
  
  @SubscribeEvent
  @SubscribeEvent
  public void onInputUpdate(InputUpdateEvent paramInputUpdateEvent) {
    (give up)null;
    EventClientBus.EVENT_BUS.post(paramInputUpdateEvent);
  }
  
  @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
  @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
  public void onKeyInput(InputEvent.KeyInputEvent paramKeyInputEvent) {
    (give up)null;
    if (Keyboard.getEventKeyState())
      Leux.get_hack_manager().bind(Keyboard.getEventKey()); 
  }
  
  @SubscribeEvent
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent paramClientTickEvent) {
    (give up)null;
    if (this.mc.field_71439_g == null)
      return; 
    Leux.get_hack_manager().update();
  }
  
  @SubscribeEvent(priority = EventPriority.NORMAL)
  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void onChat(ClientChatEvent paramClientChatEvent) {
    (give up)null;
    paramClientChatEvent.getMessage();
    String[] arrayOfString = CommandManager.command_list.get_message(paramClientChatEvent.getMessage());
    boolean bool = false;
    if (arrayOfString.length > 0) {
      paramClientChatEvent.setCanceled(true);
      this.mc.field_71456_v.func_146158_b().func_146239_a(paramClientChatEvent.getMessage());
      for (Command command : Commands.get_pure_command_list()) {
        if (CommandManager.command_list.get_message(paramClientChatEvent.getMessage())[0].equalsIgnoreCase(command.get_name()))
          bool = command.get_message(CommandManager.command_list.get_message(paramClientChatEvent.getMessage())); 
      } 
      if (CommandManager.command_list.has_prefix(paramClientChatEvent.getMessage())) {
        MessageUtil.send_client_message("Try using " + CommandManager.get_prefix() + "help list to see all commands");
        bool = false;
      } 
    } 
  }
  
  @SubscribeEvent
  @SubscribeEvent
  public void onWorldRender(RenderWorldLastEvent paramRenderWorldLastEvent) {
    (give up)null;
    if (paramRenderWorldLastEvent.isCanceled())
      return; 
    Leux.get_hack_manager().render(paramRenderWorldLastEvent);
  }
  
  @SubscribeEvent
  @SubscribeEvent
  public void onPlayerPush(PlayerSPPushOutOfBlocksEvent paramPlayerSPPushOutOfBlocksEvent) {
    (give up)null;
    EventClientBus.EVENT_BUS.post(paramPlayerSPPushOutOfBlocksEvent);
  }
}
