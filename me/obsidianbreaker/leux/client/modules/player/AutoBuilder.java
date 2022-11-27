package me.obsidianbreaker.leux.client.modules.player;

import give up;
import java.util.ArrayList;
import java.util.Objects;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventMotionUpdate;
import me.obsidianbreaker.leux.client.event.events.EventRender2;
import me.obsidianbreaker.leux.client.event.events.EventRenderLayers;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.BlockInteractHelper;
import me.obsidianbreaker.leux.client.util.MathUtil;
import me.obsidianbreaker.leux.client.util.Pair2;
import me.obsidianbreaker.leux.client.util.RenderUtil;
import me.obsidianbreaker.leux.client.util.Timer;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class AutoBuilder extends Module {
  public Setting Mode = create("Mode", "Mode", "Highway", combobox(new String[] { "Highway", "HighwayTunnel", "Portal", "Flat", "Wall" }));
  
  @EventHandler
  public Listener OnRender = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Timer timer = new Timer();
  
  public boolean SentPacket = false;
  
  public Vec3d Center = Vec3d.field_186680_a;
  
  @EventHandler
  public Listener OnRenderEvent = new Listener(this::lambda$new$2, new java.util.function.Predicate[0]);
  
  public float PitchHead = Float.intBitsToFloat(2.13229325E9F ^ 0x7F18365D);
  
  public ICamera camera = (ICamera)new Frustum();
  
  public Setting BlocksPerTick = create("BlocksPerTick", "BlocksPerTick", 1.0D, 1.0D, 10.0D);
  
  @EventHandler
  public Listener OnPlayerUpdate = new Listener(this::lambda$new$1, new java.util.function.Predicate[0]);
  
  public Setting Rotate = create("Rotate", "Rotate", true);
  
  public Setting Delay = create("Delay", "Delay", 0.0D, 0.0D, 1.0D);
  
  public ArrayList l_Array = new ArrayList();
  
  public AutoBuilder() {
    super(Category.player);
  }
  
  public void disable() {
    (give up)null;
    EventClientBus.EVENT_BUS.subscribe((Listenable)this);
  }
  
  public String array_detail() {
    (give up)null;
    return this.Mode.get_current_value();
  }
  
  public void lambda$new$1(EventMotionUpdate paramEventMotionUpdate) {
    boolean bool;
    (give up)null;
    if (paramEventMotionUpdate.get_era() != EventCancellable.Era.EVENT_PRE)
      return; 
    if (!this.timer.passed(this.Delay.get_value(1.0D) * 1000.0D))
      return; 
    this.timer.reset();
    Vec3d vec3d = MathUtil.interpolateEntity((Entity)mc.field_71439_g, mc.func_184121_ak());
    BlockPos blockPos1 = new BlockPos(vec3d.field_72450_a, vec3d.field_72448_b + 0.5D, vec3d.field_72449_c);
    BlockPos blockPos2 = (new BlockPos(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c)).func_177978_c().func_177978_c();
    this.l_Array.clear();
    Pair2 pair2 = findStackHotbar();
    int i = -1;
    double d = vec3d.field_72448_b - blockPos1.func_177956_o();
    i = ((Integer)pair2.getFirst()).intValue();
    if (pair2.getSecond() instanceof net.minecraft.block.BlockSlab && d == 0.5D) {
      blockPos1 = new BlockPos(vec3d.field_72450_a, vec3d.field_72448_b + 0.5D, vec3d.field_72449_c);
      blockPos2 = (new BlockPos(vec3d.field_72450_a, vec3d.field_72448_b + 1.0D, vec3d.field_72449_c)).func_177978_c().func_177978_c();
    } 
    if (this.Mode.in("Wall")) {
      this.l_Array.add(blockPos1.func_177978_c().func_177978_c().func_177974_f().func_177974_f());
      this.l_Array.add(blockPos1.func_177978_c().func_177978_c().func_177974_f());
      this.l_Array.add(blockPos1.func_177978_c().func_177978_c());
      this.l_Array.add(blockPos1.func_177978_c().func_177978_c().func_177976_e());
      this.l_Array.add(blockPos1.func_177978_c().func_177978_c().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos1.func_177978_c().func_177978_c().func_177976_e().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos1.func_177984_a().func_177978_c().func_177978_c().func_177974_f().func_177974_f());
      this.l_Array.add(blockPos1.func_177984_a().func_177978_c().func_177978_c().func_177974_f());
      this.l_Array.add(blockPos1.func_177984_a().func_177978_c().func_177978_c());
      this.l_Array.add(blockPos1.func_177984_a().func_177978_c().func_177978_c().func_177976_e());
      this.l_Array.add(blockPos1.func_177984_a().func_177978_c().func_177978_c().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos1.func_177984_a().func_177978_c().func_177978_c().func_177976_e().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos1.func_177984_a().func_177984_a().func_177978_c().func_177978_c().func_177974_f().func_177974_f());
      this.l_Array.add(blockPos1.func_177984_a().func_177984_a().func_177978_c().func_177978_c().func_177974_f());
      this.l_Array.add(blockPos1.func_177984_a().func_177984_a().func_177978_c().func_177978_c());
      this.l_Array.add(blockPos1.func_177984_a().func_177984_a().func_177978_c().func_177978_c().func_177976_e());
      this.l_Array.add(blockPos1.func_177984_a().func_177984_a().func_177978_c().func_177978_c().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos1.func_177984_a().func_177984_a().func_177978_c().func_177978_c().func_177976_e().func_177976_e().func_177976_e());
    } 
    if (this.Mode.in("Highway")) {
      this.l_Array.add(blockPos1.func_177977_b());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f().func_177974_f());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f().func_177974_f().func_177984_a());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e().func_177976_e().func_177984_a());
    } 
    if (this.Mode.in("HighwayTunnel")) {
      this.l_Array.add(blockPos1.func_177977_b());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f().func_177974_f());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f().func_177974_f().func_177984_a());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e().func_177976_e().func_177984_a());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f().func_177974_f().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e().func_177976_e().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e().func_177976_e().func_177984_a().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e().func_177976_e().func_177984_a().func_177984_a().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177976_e());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e().func_177976_e().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177974_f());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e().func_177976_e().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177974_f().func_177974_f());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177974_f().func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177976_e().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos1.func_177977_b().func_177978_c().func_177976_e().func_177976_e().func_177976_e().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177974_f().func_177974_f().func_177974_f());
    } 
    if (this.Mode.in("Swastika")) {
      this.l_Array.add(blockPos2);
      this.l_Array.add(blockPos2.func_177976_e());
      this.l_Array.add(blockPos2.func_177976_e().func_177976_e());
      this.l_Array.add(blockPos2.func_177984_a());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177976_e());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177976_e().func_177976_e().func_177984_a());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177976_e().func_177976_e().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177974_f());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177974_f().func_177974_f());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177974_f().func_177974_f().func_177977_b());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177974_f().func_177974_f().func_177977_b().func_177977_b());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177974_f());
      this.l_Array.add(blockPos2.func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177974_f().func_177974_f());
    } 
    if (this.Mode.in("Portal")) {
      this.l_Array.add(blockPos2.func_177974_f());
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f());
      this.l_Array.add(blockPos2);
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f().func_177984_a());
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a());
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177976_e());
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177976_e().func_177976_e().func_177976_e());
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177976_e().func_177976_e().func_177976_e().func_177977_b());
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177976_e().func_177976_e().func_177976_e().func_177977_b().func_177977_b());
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177976_e().func_177976_e().func_177976_e().func_177977_b().func_177977_b().func_177977_b());
      this.l_Array.add(blockPos2.func_177974_f().func_177974_f().func_177984_a().func_177984_a().func_177984_a().func_177984_a().func_177976_e().func_177976_e().func_177976_e().func_177977_b().func_177977_b().func_177977_b().func_177977_b());
    } 
    if (this.Mode.in("Flat")) {
      bool = true;
      3;
    } else {
      bool = false;
      float[] arrayOfFloat = null;
      -1;
      if (this.Mode.in("Portal")) {
        if (mc.field_71441_e.func_180495_p(((BlockPos)this.l_Array.get(0)).func_177984_a()).func_177230_c() == Blocks.field_150427_aO)
          return; 
        byte b1 = 0;
        while (true) {
          9;
          ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b1);
          if (!itemStack.func_190926_b() && itemStack.func_77973_b() == Items.field_151033_d) {
            mc.field_71439_g.field_71071_by.field_70461_c = b1;
            mc.field_71442_b.func_78765_e();
          } else {
            continue;
          } 
          if (this.SentPacket)
            ((NetHandlerPlayClient)Objects.<NetHandlerPlayClient>requireNonNull(mc.func_147114_u())).func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(this.l_Array.get(0), EnumFacing.UP, EnumHand.MAIN_HAND, Float.intBitsToFloat(2.07685107E9F ^ 0x7BCA3B7F), Float.intBitsToFloat(2.13732851E9F ^ 0x7F650B6E), Float.intBitsToFloat(2.12623923E9F ^ 0x7EBBD607))); 
          arrayOfFloat = BlockInteractHelper.getLegitRotations(new Vec3d(((BlockPos)this.l_Array.get(0)).func_177958_n(), ((BlockPos)this.l_Array.get(0)).func_177956_o(), ((BlockPos)this.l_Array.get(0)).func_177952_p()));
          bool = true;
          break;
        } 
      } 
      if (this.Rotate.get_value(true));
      this.PitchHead = Float.intBitsToFloat(-1.12738509E9F ^ 0x7F1F7812);
      this.SentPacket = false;
      return;
    } 
    byte b = -3;
    while (true) {
      3;
      this.l_Array.add(blockPos1.func_177977_b().func_177982_a(bool, 0, b));
    } 
  }
  
  public void lambda$new$0(EventRenderLayers paramEventRenderLayers) {
    (give up)null;
    if (paramEventRenderLayers.getEntityLivingBase() == mc.field_71439_g)
      paramEventRenderLayers.SetHeadPitch((this.PitchHead == Float.intBitsToFloat(-1.12532301E9F ^ 0x7F3EEEF8)) ? mc.field_71439_g.field_70125_A : this.PitchHead); 
  }
  
  public boolean slotEqualsBlock(int paramInt, Block paramBlock) {
    // Byte code:
    //   0: aconst_null
    //   1: checkcast give up
    //   4: pop
    //   5: getstatic me/obsidianbreaker/leux/client/modules/player/AutoBuilder.mc : Lnet/minecraft/client/Minecraft;
    //   8: getfield field_71439_g : Lnet/minecraft/client/entity/EntityPlayerSP;
    //   11: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
    //   14: iload_1
    //   15: invokevirtual func_70301_a : (I)Lnet/minecraft/item/ItemStack;
    //   18: invokevirtual func_77973_b : ()Lnet/minecraft/item/Item;
    //   21: instanceof net/minecraft/item/ItemBlock
    //   24: ifeq -> 59
    //   27: getstatic me/obsidianbreaker/leux/client/modules/player/AutoBuilder.mc : Lnet/minecraft/client/Minecraft;
    //   30: getfield field_71439_g : Lnet/minecraft/client/entity/EntityPlayerSP;
    //   33: getfield field_71071_by : Lnet/minecraft/entity/player/InventoryPlayer;
    //   36: iload_1
    //   37: invokevirtual func_70301_a : (I)Lnet/minecraft/item/ItemStack;
    //   40: invokevirtual func_77973_b : ()Lnet/minecraft/item/Item;
    //   43: checkcast net/minecraft/item/ItemBlock
    //   46: astore_3
    //   47: aload_3
    //   48: invokevirtual func_179223_d : ()Lnet/minecraft/block/Block;
    //   51: aload_2
    //   52: if_acmpne -> 61
    //   55: iconst_1
    //   56: goto -> 62
    //   59: iconst_0
    //   60: ireturn
    //   61: iconst_0
    //   62: ireturn
    //   63: nop
    //   64: nop
    //   65: nop
    //   66: nop
    //   67: nop
    //   68: nop
    //   69: nop
    //   70: nop
    //   71: nop
    //   72: nop
    //   73: nop
    //   74: nop
    //   75: nop
    //   76: nop
    //   77: nop
    //   78: nop
    //   79: nop
    //   80: nop
    //   81: nop
    //   82: nop
    //   83: nop
    //   84: nop
    //   85: nop
    //   86: nop
    //   87: nop
    //   88: nop
    //   89: athrow
    //   90: nop
    //   91: nop
    //   92: nop
    //   93: nop
    //   94: nop
    //   95: nop
    //   96: nop
    //   97: nop
    //   98: nop
    //   99: nop
    //   100: nop
    //   101: nop
    //   102: nop
    //   103: nop
    //   104: nop
    //   105: nop
    //   106: nop
    //   107: nop
    //   108: nop
    //   109: nop
    //   110: nop
    //   111: nop
    //   112: nop
    //   113: nop
    //   114: nop
    //   115: nop
    //   116: nop
    //   117: athrow
    //   118: nop
    //   119: nop
    //   120: nop
    //   121: athrow
    //   122: nop
    //   123: athrow
    //   124: athrow
    //   125: athrow
  }
  
  public Pair2 findStackHotbar() {
    (give up)null;
    if (mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemBlock)
      return new Pair2(Integer.valueOf(mc.field_71439_g.field_71071_by.field_70461_c), ((ItemBlock)mc.field_71439_g.func_184614_ca().func_77973_b()).func_179223_d()); 
    byte b = 0;
    while (true) {
      9;
      ItemStack itemStack = (Minecraft.func_71410_x()).field_71439_g.field_71071_by.func_70301_a(b);
      if (itemStack.func_77973_b() instanceof ItemBlock) {
        ItemBlock itemBlock = (ItemBlock)itemStack.func_77973_b();
        return new Pair2(Integer.valueOf(b), itemBlock.func_179223_d());
      } 
    } 
  }
  
  public void enable() {
    (give up)null;
    EventClientBus.EVENT_BUS.subscribe((Listenable)this);
    if (mc.field_71439_g == null) {
      toggle();
      return;
    } 
    this.timer.reset();
  }
  
  public void lambda$new$2(EventRender2 paramEventRender2) {
    (give up)null;
    for (BlockPos blockPos : this.l_Array) {
      AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.func_177958_n() - (mc.func_175598_ae()).field_78730_l, blockPos.func_177956_o() - (mc.func_175598_ae()).field_78731_m, blockPos.func_177952_p() - (mc.func_175598_ae()).field_78728_n, (blockPos.func_177958_n() + 1) - (mc.func_175598_ae()).field_78730_l, (blockPos.func_177956_o() + 1) - (mc.func_175598_ae()).field_78731_m, (blockPos.func_177952_p() + 1) - (mc.func_175598_ae()).field_78728_n);
      this.camera.func_78547_a(((Entity)Objects.requireNonNull((T)mc.func_175606_aa())).field_70165_t, (mc.func_175606_aa()).field_70163_u, (mc.func_175606_aa()).field_70161_v);
      if (this.camera.func_78546_a(new AxisAlignedBB(axisAlignedBB.field_72340_a + (mc.func_175598_ae()).field_78730_l, axisAlignedBB.field_72338_b + (mc.func_175598_ae()).field_78731_m, axisAlignedBB.field_72339_c + (mc.func_175598_ae()).field_78728_n, axisAlignedBB.field_72336_d + (mc.func_175598_ae()).field_78730_l, axisAlignedBB.field_72337_e + (mc.func_175598_ae()).field_78731_m, axisAlignedBB.field_72334_f + (mc.func_175598_ae()).field_78728_n))) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(Float.intBitsToFloat(1.08792218E9F ^ 0x7F186032));
        double d = mc.field_71439_g.func_70011_f((blockPos.func_177958_n() + Float.intBitsToFloat(1.07647232E9F ^ 0x7F29A9F8)), (blockPos.func_177956_o() + Float.intBitsToFloat(1.11997542E9F ^ 0x7DC177C7)), (blockPos.func_177952_p() + Float.intBitsToFloat(1.12073229E9F ^ 0x7DCD049F))) * 0.75D;
        MathUtil.clamp((float)(d * 255.0D / 5.0D / 255.0D), Float.intBitsToFloat(2.13092083E9F ^ 0x7F034542), Float.intBitsToFloat(1.10674752E9F ^ 0x7F6E3904));
        int i = 268500991;
        RenderUtil.drawBoundingBox(axisAlignedBB, Float.intBitsToFloat(1.09407552E9F ^ 0x7EB64499), i);
        RenderUtil.drawFilledBox(axisAlignedBB, i);
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
      } 
    } 
  }
}
