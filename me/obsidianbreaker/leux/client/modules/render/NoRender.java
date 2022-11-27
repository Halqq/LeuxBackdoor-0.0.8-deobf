package me.obsidianbreaker.leux.client.modules.render;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.event.events.EventRenderArmorOverlay;
import me.obsidianbreaker.leux.client.event.events.EventRenderBossHealth;
import me.obsidianbreaker.leux.client.event.events.EventSetupFog;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoRender extends Module {
  public Setting falling_blocks = create("Falling Blocks", "FallingBlocks", true);
  
  public Setting blind = create("Blind", "Blind", true);
  
  public Setting weather = create("Weather", "Weather", true);
  
  public Setting potion = create("Potion Icons", "PotionIcons", true);
  
  @EventHandler
  public Listener setup_fog = new Listener(this::lambda$new$6, new java.util.function.Predicate[0]);
  
  public Setting advancements = create("Advancements", "Advancements", true);
  
  @EventHandler
  public Listener renderBlockOverlayEventListener = new Listener(this::lambda$new$2, new java.util.function.Predicate[0]);
  
  public Setting pumpkin = create("Pumpkin", "Pumpkin", true);
  
  public Setting bossbar = create("BossBar", "BossBar", true);
  
  public Setting portaloverlay = create("Portal Overlay", "PortalOverlay", true);
  
  public Setting totemanimation = create("TotemAnimation", "TotemAnimation", false);
  
  public Setting nausea = create("Nausea", "Nausea", true);
  
  public Setting fireworks = create("Fireworks", "Fireworks", true);
  
  public Setting armor = create("Armor", "Armor", true);
  
  @EventHandler
  public Listener OnRenderBossHealth = new Listener(this::lambda$new$4, new java.util.function.Predicate[0]);
  
  @EventHandler
  public Listener onServerPacket = new Listener(this::lambda$new$3, new java.util.function.Predicate[0]);
  
  public Setting hurtcam = create("HurtCam", "NoHurtCam", true);
  
  @EventHandler
  public Listener eventarmor = new Listener(this::lambda$new$5, new java.util.function.Predicate[0]);
  
  public Setting fire = create("Fire", "Fire", true);
  
  public Setting noclip = create("Camera Clip", "CameraClip", false);
  
  public Setting fog = create("Fog", "Fog", true);
  
  public static boolean lambda$update$0(Entity paramEntity) {
    (give up)null;
    return paramEntity instanceof net.minecraft.entity.item.EntityFallingBlock;
  }
  
  public void lambda$new$6(EventSetupFog paramEventSetupFog) {
    (give up)null;
    if (this.fog.get_value(true)) {
      paramEventSetupFog.cancel();
      mc.field_71460_t.func_191514_d(false);
      GlStateManager.func_187432_a(Float.intBitsToFloat(2.13005594E9F ^ 0x7EF61301), Float.intBitsToFloat(-1.05921178E9F ^ 0x7F5DB61A), Float.intBitsToFloat(2.13783283E9F ^ 0x7F6CBD90));
      GlStateManager.func_179131_c(Float.intBitsToFloat(1.10314035E9F ^ 0x7E40960B), Float.intBitsToFloat(1.08590003E9F ^ 0x7F398539), Float.intBitsToFloat(1.08583667E9F ^ 0x7F388DAF), Float.intBitsToFloat(1.12680896E9F ^ 0x7CA9BD9F));
      GlStateManager.func_179104_a(1028, 4608);
    } 
  }
  
  public static void lambda$update$1(Entity paramEntity) {
    (give up)null;
    mc.field_71441_e.func_72900_e(paramEntity);
  }
  
  public void enable() {
    (give up)null;
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  public void lambda$new$4(EventRenderBossHealth paramEventRenderBossHealth) {
    (give up)null;
    if (mc.field_71441_e == null)
      return; 
    if (this.bossbar.get_value(true))
      paramEventRenderBossHealth.cancel(); 
  }
  
  public void lambda$new$3(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    if (mc.field_71441_e == null)
      return; 
    if (paramReceivePacket.get_era() != EventCancellable.Era.EVENT_PRE)
      return; 
    if (paramReceivePacket.get_packet() instanceof SPacketEntityStatus && this.totemanimation.get_value(true)) {
      SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)paramReceivePacket.get_packet();
      if (sPacketEntityStatus.func_149160_c() == 35)
        paramReceivePacket.cancel(); 
    } 
  }
  
  public void update() {
    (give up)null;
    if (this.blind.get_value(true) && mc.field_71439_g.func_70644_a(MobEffects.field_76440_q))
      mc.field_71439_g.func_184589_d(MobEffects.field_76440_q); 
    if (this.nausea.get_value(true) && mc.field_71439_g.func_70644_a(MobEffects.field_76431_k))
      mc.field_71439_g.func_184589_d(MobEffects.field_76431_k); 
    if (this.falling_blocks.get_value(true))
      mc.field_71441_e.field_72996_f.stream().filter(NoRender::lambda$update$0).forEach(NoRender::lambda$update$1); 
    if (this.weather.get_value(true)) {
      if (mc.field_71441_e == null)
        return; 
      if (mc.field_71441_e.func_72896_J())
        mc.field_71441_e.func_72894_k(Float.intBitsToFloat(2.13196237E9F ^ 0x7F1329DD)); 
      if (mc.field_71441_e.func_72911_I())
        mc.field_71441_e.func_147442_i(Float.intBitsToFloat(2.12633088E9F ^ 0x7EBD3BC3)); 
    } 
  }
  
  @SubscribeEvent
  @SubscribeEvent
  public void fog_density(EntityViewRenderEvent.FogDensity paramFogDensity) {
    (give up)null;
    if (this.fog.get_value(true)) {
      paramFogDensity.setDensity(0.0F);
      paramFogDensity.setCanceled(true);
    } 
  }
  
  public void lambda$new$2(RenderBlockOverlayEvent paramRenderBlockOverlayEvent) {
    (give up)null;
    if (this.fire.get_value(true) && paramRenderBlockOverlayEvent.getOverlayType() == RenderBlockOverlayEvent.OverlayType.FIRE)
      paramRenderBlockOverlayEvent.setCanceled(true); 
    if (this.pumpkin.get_value(true) && paramRenderBlockOverlayEvent.getOverlayType() == RenderBlockOverlayEvent.OverlayType.BLOCK)
      paramRenderBlockOverlayEvent.setCanceled(true); 
  }
  
  public void disable() {
    (give up)null;
    MinecraftForge.EVENT_BUS.unregister(this);
  }
  
  public NoRender() {
    super(Category.render);
  }
  
  public void lambda$new$5(EventRenderArmorOverlay paramEventRenderArmorOverlay) {
    (give up)null;
    if (this.armor.get_value(true) && paramEventRenderArmorOverlay.entity instanceof net.minecraft.entity.player.EntityPlayer)
      paramEventRenderArmorOverlay.cancel(); 
    if (Leux.get_module_manager().get_module_with_tag("Chams").is_active())
      this.armor.set_value(true); 
  }
}
