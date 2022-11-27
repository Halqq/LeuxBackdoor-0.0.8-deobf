package me.obsidianbreaker.leux.client.manager;

import give up;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.modules.client.ClickGUI;
import me.obsidianbreaker.leux.client.modules.client.ClickHUD;
import me.obsidianbreaker.leux.client.modules.client.RPC;
import me.obsidianbreaker.leux.client.modules.client.Settings;
import me.obsidianbreaker.leux.client.modules.client.VanillaPayload;
import me.obsidianbreaker.leux.client.modules.combat.AntiChainPop;
import me.obsidianbreaker.leux.client.modules.combat.AntiCityBoss;
import me.obsidianbreaker.leux.client.modules.combat.AntiCrystal;
import me.obsidianbreaker.leux.client.modules.combat.AnvilAura;
import me.obsidianbreaker.leux.client.modules.combat.Auto32k;
import me.obsidianbreaker.leux.client.modules.combat.AutoObiBreaker;
import me.obsidianbreaker.leux.client.modules.combat.AutoTotem;
import me.obsidianbreaker.leux.client.modules.combat.AutoTrap;
import me.obsidianbreaker.leux.client.modules.combat.AutoWeb;
import me.obsidianbreaker.leux.client.modules.combat.BedAura;
import me.obsidianbreaker.leux.client.modules.combat.CevBreaker;
import me.obsidianbreaker.leux.client.modules.combat.Criticals;
import me.obsidianbreaker.leux.client.modules.combat.CrystalAuraNew;
import me.obsidianbreaker.leux.client.modules.combat.CrystalAuraOld;
import me.obsidianbreaker.leux.client.modules.combat.HoleFill;
import me.obsidianbreaker.leux.client.modules.combat.KillAura;
import me.obsidianbreaker.leux.client.modules.combat.Offhand;
import me.obsidianbreaker.leux.client.modules.combat.OffhandByPassTest;
import me.obsidianbreaker.leux.client.modules.combat.PistonAura;
import me.obsidianbreaker.leux.client.modules.combat.Quiver;
import me.obsidianbreaker.leux.client.modules.combat.SelfTrap;
import me.obsidianbreaker.leux.client.modules.combat.Surround;
import me.obsidianbreaker.leux.client.modules.combat.TotemPopCounter;
import me.obsidianbreaker.leux.client.modules.combat.Webfill;
import me.obsidianbreaker.leux.client.modules.exploit.Auto8B8TDupe;
import me.obsidianbreaker.leux.client.modules.exploit.AutoSalDupe;
import me.obsidianbreaker.leux.client.modules.exploit.BuildHeight;
import me.obsidianbreaker.leux.client.modules.exploit.Burrow;
import me.obsidianbreaker.leux.client.modules.exploit.EffectsSide;
import me.obsidianbreaker.leux.client.modules.exploit.EntityRide;
import me.obsidianbreaker.leux.client.modules.exploit.FakeCreative;
import me.obsidianbreaker.leux.client.modules.exploit.GlobalLocation;
import me.obsidianbreaker.leux.client.modules.exploit.InstantBurrow;
import me.obsidianbreaker.leux.client.modules.exploit.MultiTask;
import me.obsidianbreaker.leux.client.modules.exploit.NoFall;
import me.obsidianbreaker.leux.client.modules.exploit.PacketFly;
import me.obsidianbreaker.leux.client.modules.exploit.PacketMine;
import me.obsidianbreaker.leux.client.modules.exploit.PacketXP;
import me.obsidianbreaker.leux.client.modules.exploit.PingSpoof;
import me.obsidianbreaker.leux.client.modules.exploit.PortalGodMode;
import me.obsidianbreaker.leux.client.modules.exploit.ShulkerPreview;
import me.obsidianbreaker.leux.client.modules.exploit.SpeedMine;
import me.obsidianbreaker.leux.client.modules.exploit.XCarry;
import me.obsidianbreaker.leux.client.modules.misc.Announcer;
import me.obsidianbreaker.leux.client.modules.misc.AutoGG;
import me.obsidianbreaker.leux.client.modules.misc.AutoRacist;
import me.obsidianbreaker.leux.client.modules.misc.AutoRespawn;
import me.obsidianbreaker.leux.client.modules.misc.AutoWither;
import me.obsidianbreaker.leux.client.modules.misc.BetterChat;
import me.obsidianbreaker.leux.client.modules.misc.ChatColors;
import me.obsidianbreaker.leux.client.modules.misc.ChatSuffix;
import me.obsidianbreaker.leux.client.modules.misc.EntitySearch;
import me.obsidianbreaker.leux.client.modules.misc.FactSpammer;
import me.obsidianbreaker.leux.client.modules.misc.FakePlayer;
import me.obsidianbreaker.leux.client.modules.misc.MiddleClickGang;
import me.obsidianbreaker.leux.client.modules.misc.MiddleClickPearl;
import me.obsidianbreaker.leux.client.modules.misc.MiddleClickXP;
import me.obsidianbreaker.leux.client.modules.misc.NoEntityTrace;
import me.obsidianbreaker.leux.client.modules.misc.Scaffold;
import me.obsidianbreaker.leux.client.modules.misc.SexDupe;
import me.obsidianbreaker.leux.client.modules.misc.StashFinder;
import me.obsidianbreaker.leux.client.modules.misc.Yaw;
import me.obsidianbreaker.leux.client.modules.movement.Anchor;
import me.obsidianbreaker.leux.client.modules.movement.AutoWalk;
import me.obsidianbreaker.leux.client.modules.movement.BoatFly;
import me.obsidianbreaker.leux.client.modules.movement.ElytraFly;
import me.obsidianbreaker.leux.client.modules.movement.Jesus;
import me.obsidianbreaker.leux.client.modules.movement.LongJump;
import me.obsidianbreaker.leux.client.modules.movement.NoSlow;
import me.obsidianbreaker.leux.client.modules.movement.NoSlowByPass;
import me.obsidianbreaker.leux.client.modules.movement.Speed;
import me.obsidianbreaker.leux.client.modules.movement.Sprint;
import me.obsidianbreaker.leux.client.modules.movement.Velocity;
import me.obsidianbreaker.leux.client.modules.player.AntiAFK;
import me.obsidianbreaker.leux.client.modules.player.AutoArmor;
import me.obsidianbreaker.leux.client.modules.player.AutoBuilder;
import me.obsidianbreaker.leux.client.modules.player.AutoReplenish;
import me.obsidianbreaker.leux.client.modules.player.FastSuicide;
import me.obsidianbreaker.leux.client.modules.player.FastUse;
import me.obsidianbreaker.leux.client.modules.player.Freecam;
import me.obsidianbreaker.leux.client.modules.player.InventoryMove;
import me.obsidianbreaker.leux.client.modules.player.NoForceLook;
import me.obsidianbreaker.leux.client.modules.player.Notifications;
import me.obsidianbreaker.leux.client.modules.player.ReverseStep;
import me.obsidianbreaker.leux.client.modules.player.Step;
import me.obsidianbreaker.leux.client.modules.player.Timer;
import me.obsidianbreaker.leux.client.modules.render.AlwaysNight;
import me.obsidianbreaker.leux.client.modules.render.BlockHighlight;
import me.obsidianbreaker.leux.client.modules.render.BreakHighlight;
import me.obsidianbreaker.leux.client.modules.render.Brightness;
import me.obsidianbreaker.leux.client.modules.render.BurrowESP;
import me.obsidianbreaker.leux.client.modules.render.Capes;
import me.obsidianbreaker.leux.client.modules.render.Chams;
import me.obsidianbreaker.leux.client.modules.render.CityEsp;
import me.obsidianbreaker.leux.client.modules.render.ClearChat;
import me.obsidianbreaker.leux.client.modules.render.FuckedDetector;
import me.obsidianbreaker.leux.client.modules.render.HoleESP;
import me.obsidianbreaker.leux.client.modules.render.NameTags;
import me.obsidianbreaker.leux.client.modules.render.NoRender;
import me.obsidianbreaker.leux.client.modules.render.NoSwing;
import me.obsidianbreaker.leux.client.modules.render.SkyColour;
import me.obsidianbreaker.leux.client.modules.render.StorageESP;
import me.obsidianbreaker.leux.client.modules.render.Tracers;
import me.obsidianbreaker.leux.client.modules.render.ViewModel;
import me.obsidianbreaker.leux.client.modules.render.VoidESP;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import me.obsidianbreaker.leux.client.util.BurrowUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class ModuleManager {
  public static ArrayList array_hacks = new ArrayList();
  
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public ArrayList get_array_active_hacks() {
    (give up)null;
    ArrayList<Module> arrayList = new ArrayList();
    for (Module module : get_array_hacks()) {
      if (module.is_active())
        arrayList.add(module); 
    } 
    return arrayList;
  }
  
  public Module get_module_with_tag(String paramString) {
    (give up)null;
    Module module = null;
    for (Module module1 : get_array_hacks()) {
      if (module1.get_tag().equalsIgnoreCase(paramString))
        module = module1; 
    } 
    return module;
  }
  
  public void bind(int paramInt) {
    (give up)null;
  }
  
  public void render() {
    (give up)null;
    for (Module module : get_array_hacks()) {
      if (module.is_active())
        module.render(); 
    } 
  }
  
  public ArrayList get_array_hacks() {
    (give up)null;
    return array_hacks;
  }
  
  public void update() {
    (give up)null;
    for (Module module : get_array_hacks()) {
      if (module.is_active())
        module.update(); 
    } 
  }
  
  public Vec3d process(Entity paramEntity, double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    return new Vec3d((paramEntity.field_70165_t - paramEntity.field_70142_S) * paramDouble1, (paramEntity.field_70163_u - paramEntity.field_70137_T) * paramDouble2, (paramEntity.field_70161_v - paramEntity.field_70136_U) * paramDouble3);
  }
  
  public Vec3d get_interpolated_pos(Entity paramEntity, double paramDouble) {
    (give up)null;
    return (new Vec3d(paramEntity.field_70142_S, paramEntity.field_70137_T, paramEntity.field_70136_U)).func_178787_e(process(paramEntity, paramDouble, paramDouble, paramDouble));
  }
  
  public ModuleManager() {
    if (!CrystalAuraNew.isEntity()) {
      Leux.load_client();
      throw new BurrowUtil("");
    } 
    add_hack((Module)new ClickGUI());
    add_hack((Module)new ClickHUD());
    add_hack((Module)new Announcer());
    add_hack((Module)new AutoRacist());
    add_hack((Module)new ChatColors());
    add_hack((Module)new ChatSuffix());
    add_hack((Module)new ClearChat());
    add_hack((Module)new FastSuicide());
    add_hack((Module)new Notifications());
    add_hack((Module)new SexDupe());
    add_hack((Module)new BetterChat());
    add_hack((Module)new AutoGG());
    add_hack((Module)new GlobalLocation());
    add_hack((Module)new EntitySearch());
    add_hack((Module)new FactSpammer());
    add_hack((Module)new StashFinder());
    add_hack((Module)new AutoBuilder());
    add_hack((Module)new AntiChainPop());
    add_hack((Module)new AnvilAura());
    add_hack((Module)new Auto32k());
    add_hack((Module)new AutoArmor());
    add_hack((Module)new CrystalAuraNew());
    add_hack((Module)new CrystalAuraOld());
    add_hack((Module)new KillAura());
    add_hack((Module)new Surround());
    add_hack((Module)new HoleFill());
    add_hack((Module)new AutoTrap());
    add_hack((Module)new AntiCrystal());
    add_hack((Module)new AntiCityBoss());
    add_hack((Module)new SelfTrap());
    add_hack((Module)new Webfill());
    add_hack((Module)new AutoWeb());
    add_hack((Module)new BedAura());
    add_hack((Module)new Offhand());
    add_hack((Module)new OffhandByPassTest());
    add_hack((Module)new AutoTotem());
    add_hack((Module)new AutoObiBreaker());
    add_hack((Module)new Criticals());
    add_hack((Module)new TotemPopCounter());
    add_hack((Module)new Quiver());
    add_hack((Module)new CevBreaker());
    add_hack((Module)new PistonAura());
    add_hack((Module)new XCarry());
    add_hack((Module)new NoSwing());
    add_hack((Module)new Burrow());
    add_hack((Module)new PortalGodMode());
    add_hack((Module)new PacketMine());
    add_hack((Module)new NoEntityTrace());
    add_hack((Module)new BuildHeight());
    add_hack((Module)new VanillaPayload());
    add_hack((Module)new SpeedMine());
    add_hack((Module)new MultiTask());
    add_hack((Module)new Timer());
    add_hack((Module)new PacketXP());
    add_hack((Module)new NoForceLook());
    add_hack((Module)new AutoSalDupe());
    add_hack((Module)new Auto8B8TDupe());
    add_hack((Module)new PingSpoof());
    add_hack((Module)new EntityRide());
    add_hack((Module)new InstantBurrow());
    add_hack((Module)new Speed());
    add_hack((Module)new Step());
    add_hack((Module)new ReverseStep());
    add_hack((Module)new Sprint());
    add_hack((Module)new Freecam());
    add_hack((Module)new Anchor());
    add_hack((Module)new NoSlow());
    add_hack((Module)new InventoryMove());
    add_hack((Module)new Velocity());
    add_hack((Module)new ElytraFly());
    add_hack((Module)new PacketFly());
    add_hack((Module)new Yaw());
    add_hack((Module)new AutoWalk());
    add_hack((Module)new NoFall());
    add_hack((Module)new Scaffold());
    add_hack((Module)new Jesus());
    add_hack((Module)new LongJump());
    add_hack((Module)new BoatFly());
    add_hack((Module)new NoSlowByPass());
    add_hack((Module)new BlockHighlight());
    add_hack((Module)new HoleESP());
    add_hack((Module)new ShulkerPreview());
    add_hack((Module)new ViewModel());
    add_hack((Module)new VoidESP());
    add_hack((Module)new NameTags());
    add_hack((Module)new FuckedDetector());
    add_hack((Module)new StorageESP());
    add_hack((Module)new BreakHighlight());
    add_hack((Module)new Tracers());
    add_hack((Module)new SkyColour());
    add_hack((Module)new NoRender());
    add_hack((Module)new Chams());
    add_hack((Module)new Capes());
    add_hack((Module)new AlwaysNight());
    add_hack((Module)new CityEsp());
    add_hack((Module)new Brightness());
    add_hack((Module)new Settings());
    add_hack((Module)new BurrowESP());
    add_hack((Module)new MiddleClickGang());
    add_hack((Module)new AutoReplenish());
    add_hack((Module)new FastUse());
    add_hack((Module)new AutoRespawn());
    add_hack((Module)new FakeCreative());
    add_hack((Module)new FakePlayer());
    add_hack((Module)new RPC());
    add_hack((Module)new EffectsSide());
    add_hack((Module)new AntiAFK());
    add_hack((Module)new AutoWither());
    add_hack((Module)new MiddleClickPearl());
    add_hack((Module)new MiddleClickXP());
    array_hacks.sort(Comparator.comparing(Module::get_name));
  }
  
  public ArrayList get_modules_with_category(Category paramCategory) {
    (give up)null;
    ArrayList<Module> arrayList = new ArrayList();
    for (Module module : get_array_hacks()) {
      if (module.get_category().equals(paramCategory))
        arrayList.add(module); 
    } 
    return arrayList;
  }
  
  public void render(RenderWorldLastEvent paramRenderWorldLastEvent) {
    (give up)null;
    mc.field_71424_I.func_76320_a("leux");
    mc.field_71424_I.func_76320_a("setup");
    GlStateManager.func_179090_x();
    GlStateManager.func_179147_l();
    GlStateManager.func_179118_c();
    GlStateManager.func_179120_a(770, 771, 1, 0);
    GlStateManager.func_179103_j(7425);
    GlStateManager.func_179097_i();
    GlStateManager.func_187441_d(Float.intBitsToFloat(1.08950106E9F ^ 0x7F707763));
    Vec3d vec3d = get_interpolated_pos((Entity)mc.field_71439_g, paramRenderWorldLastEvent.getPartialTicks());
    EventRender eventRender = new EventRender((Tessellator)RenderHelp.INSTANCE, vec3d);
    eventRender.reset_translation();
    mc.field_71424_I.func_76319_b();
    Iterator<Module> iterator = get_array_hacks().iterator();
    while (true) {
      if (iterator.hasNext()) {
        Module module = iterator.next();
        if (module.is_active()) {
          mc.field_71424_I.func_76320_a(module.get_tag());
          module.render(eventRender);
          mc.field_71424_I.func_76319_b();
        } 
        continue;
      } 
      mc.field_71424_I.func_76320_a("release");
      GlStateManager.func_187441_d(Float.intBitsToFloat(1.1053824E9F ^ 0x7E62CC13));
      GlStateManager.func_179103_j(7424);
      GlStateManager.func_179084_k();
      GlStateManager.func_179141_d();
      GlStateManager.func_179098_w();
      GlStateManager.func_179126_j();
      GlStateManager.func_179089_o();
      RenderHelp.release_gl();
      mc.field_71424_I.func_76319_b();
      mc.field_71424_I.func_76319_b();
      return;
    } 
  }
  
  public void add_hack(Module paramModule) {
    (give up)null;
    array_hacks.add(paramModule);
  }
}
