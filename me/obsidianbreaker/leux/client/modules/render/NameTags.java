package me.obsidianbreaker.leux.client.modules.render;

import give up;
import java.awt.Color;
import java.util.Objects;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.event.events.EventRenderName;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.modules.combat.TotemPopCounter;
import me.obsidianbreaker.leux.client.util.DamageUtil;
import me.obsidianbreaker.leux.client.util.EnemyUtil;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import me.obsidianbreaker.leux.client.util.RenderUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

public class NameTags extends Module {
  public Setting r = create("R", "NametagR", 120, 0, 255);
  
  public Setting g = create("G", "NametagG", 120, 0, 255);
  
  public static boolean $assertionsDisabled;
  
  public Setting b = create("B", "NametagB", 240, 0, 255);
  
  public Setting range = create("Range", "NametagRange", 150, 1, 250);
  
  public Setting rainbow_mode = create("Rainbow", "NametagRainbow", false);
  
  public Setting simplify = create("Simplify", "NametagSimp", true);
  
  public Setting show_invis = create("Invis", "NametagInvis", true);
  
  public Setting show_totems = create("Totem Count", "NametagTotems", false);
  
  public Setting m_scale = create("Scale", "NametagScale", 15, 1, 15);
  
  @EventHandler
  public Listener on_render_name = new Listener(NameTags::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting show_ping = create("Ping", "NametagPing", true);
  
  public Setting show_health = create("Health", "NametagHealth", true);
  
  public Setting reverse = create("Armour Reverse", "NametagReverse", false);
  
  public Setting show_armor = create("Armor", "NametagArmor", true);
  
  public Setting sat = create("Saturation", "NametagSatiation", 0.8D, 0.0D, 1.0D);
  
  public Setting brightness = create("Brightness", "NametagBrightness", 0.8D, 0.0D, 1.0D);
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    for (EntityPlayer entityPlayer : mc.field_71441_e.field_73010_i) {
      if (!entityPlayer.equals(mc.field_71439_g) && entityPlayer.func_70089_S() && (!entityPlayer.func_82150_aj() || this.show_invis.get_value(true)) && mc.field_71439_g.func_70032_d((Entity)entityPlayer) < this.range.get_value(1)) {
        double d1 = interpolate(entityPlayer.field_70142_S, entityPlayer.field_70165_t, paramEventRender.get_partial_ticks()) - (mc.func_175598_ae()).field_78725_b;
        double d2 = interpolate(entityPlayer.field_70137_T, entityPlayer.field_70163_u, paramEventRender.get_partial_ticks()) - (mc.func_175598_ae()).field_78726_c;
        double d3 = interpolate(entityPlayer.field_70136_U, entityPlayer.field_70161_v, paramEventRender.get_partial_ticks()) - (mc.func_175598_ae()).field_78723_d;
        renderNameTag(entityPlayer, d1, d2, d3, paramEventRender.get_partial_ticks());
      } 
    } 
  }
  
  public int getDisplayColour(EntityPlayer paramEntityPlayer) {
    (give up)null;
    int i = -5592406;
    return FriendUtil.isFriend(paramEntityPlayer.func_70005_c_()) ? -11157267 : (EnemyUtil.isEnemy(paramEntityPlayer.func_70005_c_()) ? -49632 : i);
  }
  
  public static void lambda$new$0(EventRenderName paramEventRenderName) {
    (give up)null;
    paramEventRenderName.cancel();
  }
  
  public void renderItemStack(ItemStack paramItemStack, int paramInt) {
    (give up)null;
    GlStateManager.func_179094_E();
    GlStateManager.func_179132_a(true);
    GlStateManager.func_179086_m(256);
    RenderHelper.func_74519_b();
    (mc.func_175599_af()).field_77023_b = Float.intBitsToFloat(-1.09078733E9F ^ 0x7DEDE7D7);
    GlStateManager.func_179118_c();
    GlStateManager.func_179126_j();
    GlStateManager.func_179129_p();
    mc.func_175599_af().func_180450_b(paramItemStack, paramInt, -29);
    mc.func_175599_af().func_175030_a(mc.field_71466_p, paramItemStack, paramInt, -29);
    (mc.func_175599_af()).field_77023_b = Float.intBitsToFloat(2.13509286E9F ^ 0x7F42EE58);
    RenderHelper.func_74518_a();
    GlStateManager.func_179089_o();
    GlStateManager.func_179141_d();
    GlStateManager.func_179152_a(Float.intBitsToFloat(1.07648512E9F ^ 0x7F29DC01), Float.intBitsToFloat(1.09972864E9F ^ 0x7E8C8705), Float.intBitsToFloat(1.1110057E9F ^ 0x7D3899FF));
    GlStateManager.func_179097_i();
    renderEnchantmentText(paramItemStack, paramInt);
    GlStateManager.func_179126_j();
    GlStateManager.func_179152_a(Float.intBitsToFloat(1.05840461E9F ^ 0x7F15F91F), Float.intBitsToFloat(1.04615008E9F ^ 0x7E5AFBC3), Float.intBitsToFloat(1.05666118E9F ^ 0x7EFB5EB9));
    GlStateManager.func_179121_F();
  }
  
  public float getBiggestArmorTag(EntityPlayer paramEntityPlayer) {
    (give up)null;
    float f = Float.intBitsToFloat(2.12995878E9F ^ 0x7EF497A7);
    boolean bool = false;
    for (ItemStack itemStack : paramEntityPlayer.field_71071_by.field_70460_b) {
      float f1 = Float.intBitsToFloat(2.09525466E9F ^ 0x7CE30C9F);
      NBTTagList nBTTagList = itemStack.func_77986_q();
      for (byte b = 0; b < nBTTagList.func_74745_c(); b++) {
        short s = nBTTagList.func_150305_b(b).func_74765_d("id");
        Enchantment enchantment = Enchantment.func_185262_c(s);
        f1 += Float.intBitsToFloat(1.04541555E9F ^ 0x7F4FC689);
        bool = true;
      } 
      Float.compare(f1, f);
    } 
    ItemStack itemStack1 = paramEntityPlayer.func_184614_ca().func_77946_l();
    if (itemStack1.func_77962_s()) {
      float f1 = Float.intBitsToFloat(2.13582221E9F ^ 0x7F4E0FB6);
      NBTTagList nBTTagList = itemStack1.func_77986_q();
      for (byte b = 0; b < nBTTagList.func_74745_c(); b++) {
        short s = nBTTagList.func_150305_b(b).func_74765_d("id");
        Enchantment enchantment = Enchantment.func_185262_c(s);
        f1 += Float.intBitsToFloat(1.04060269E9F ^ 0x7F06562F);
        bool = true;
      } 
      Float.compare(f1, f);
    } 
    ItemStack itemStack2 = paramEntityPlayer.func_184592_cb().func_77946_l();
    if (itemStack2.func_77962_s()) {
      float f1 = Float.intBitsToFloat(2.12231309E9F ^ 0x7E7FED63);
      NBTTagList nBTTagList = itemStack2.func_77986_q();
      for (byte b = 0; b < nBTTagList.func_74745_c(); b++) {
        short s = nBTTagList.func_150305_b(b).func_74765_d("id");
        Enchantment enchantment = Enchantment.func_185262_c(s);
        f1 += Float.intBitsToFloat(1.01897146E9F ^ 0x7DBC453F);
        bool = true;
      } 
      Float.compare(f1, f);
    } 
    return 20 + f;
  }
  
  public String section_sign() {
    (give up)null;
    return "§";
  }
  
  public String getDisplayTag(EntityPlayer paramEntityPlayer) {
    String str2;
    (give up)null;
    String str1 = paramEntityPlayer.getDisplayNameString();
    if (!this.show_health.get_value(true))
      return str1; 
    float f = paramEntityPlayer.func_110143_aJ() + paramEntityPlayer.func_110139_bj();
    if (f <= Float.intBitsToFloat(2.08382029E9F ^ 0x7C34933F))
      return str1 + " - DEAD"; 
    if (f > Float.intBitsToFloat(1.04928243E9F ^ 0x7F42C799)) {
      str2 = section_sign() + "a";
    } else if (f > Float.intBitsToFloat(1.05465517E9F ^ 0x7F7CC2C9)) {
      str2 = section_sign() + "2";
    } else if (f > Float.intBitsToFloat(1.07320902E9F ^ 0x7E87DEB5)) {
      str2 = section_sign() + "e";
    } else if (f > Float.intBitsToFloat(1.06138752E9F ^ 0x7E637CE7)) {
      str2 = section_sign() + "6";
    } else if (f > Float.intBitsToFloat(1.03105562E9F ^ 0x7DD4A917)) {
      str2 = section_sign() + "c";
    } else {
      str2 = section_sign() + "4";
    } 
    String str3 = "";
    if (this.show_ping.get_value(true)) {
      int i = ((NetHandlerPlayClient)Objects.<NetHandlerPlayClient>requireNonNull(mc.func_147114_u())).func_175102_a(paramEntityPlayer.func_110124_au()).func_178853_c();
      if (i > 300) {
        str3 = str3 + section_sign() + "c";
      } else if (i > 150) {
        str3 = str3 + section_sign() + "e";
      } else {
        str3 = str3 + section_sign() + "a";
      } 
      str3 = str3 + i + "ms ";
    } 
    String str4 = " ";
    if (this.show_totems.get_value(true))
      str4 = str4 + ((TotemPopCounter.totem_pop_counter.get(paramEntityPlayer.func_70005_c_()) == null) ? (section_sign() + "70") : (section_sign() + "c -" + TotemPopCounter.totem_pop_counter.get(paramEntityPlayer.func_70005_c_()))); 
    if (Math.floor(f) == f) {
      str1 = str1 + str2 + " " + ((f > Float.intBitsToFloat(2.13872026E9F ^ 0x7F7A4830)) ? (String)Integer.valueOf((int)Math.floor(f)) : "dead");
    } else {
      str1 = str1 + str2 + " " + ((f > Float.intBitsToFloat(2.131584E9F ^ 0x7F0D63FB)) ? (String)Integer.valueOf((int)f) : "dead");
    } 
    return str3 + section_sign() + "r" + str1 + section_sign() + "r" + str4;
  }
  
  public void renderEnchantmentText(ItemStack paramItemStack, int paramInt) {
    (give up)null;
    byte b = -37;
    NBTTagList nBTTagList = paramItemStack.func_77986_q();
    if (nBTTagList.func_74745_c() > 2 && this.simplify.get_value(true)) {
      mc.field_71466_p.func_175063_a("god", (paramInt * 2), b, -3977919);
      b -= 8;
    } else {
      for (byte b1 = 0; b1 < nBTTagList.func_74745_c(); b1++) {
        short s1 = nBTTagList.func_150305_b(b1).func_74765_d("id");
        short s2 = nBTTagList.func_150305_b(b1).func_74765_d("lvl");
        Enchantment enchantment = Enchantment.func_185262_c(s1);
        String str = enchantment.func_190936_d() ? (TextFormatting.RED + enchantment.func_77316_c(s2).substring(11).substring(0, 1).toLowerCase()) : enchantment.func_77316_c(s2).substring(0, 1).toLowerCase();
        str = str + s2;
        mc.field_71466_p.func_175063_a(str, (paramInt * 2), b, -1);
        b -= 8;
      } 
    } 
    if (DamageUtil.hasDurability(paramItemStack)) {
      String str;
      int i = DamageUtil.getRoundedDamage(paramItemStack);
      if (i >= 60) {
        str = section_sign() + "a";
      } else if (i >= 25) {
        str = section_sign() + "e";
      } else {
        str = section_sign() + "c";
      } 
      -62;
    } 
  }
  
  public void renderNameTag(EntityPlayer paramEntityPlayer, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat) {
    (give up)null;
    double d1 = paramDouble2;
    d1 += paramEntityPlayer.func_70093_af() ? 0.5D : 0.7D;
    Entity entity = mc.func_175606_aa();
    assert false;
    double d2 = entity.field_70165_t;
    double d3 = entity.field_70163_u;
    double d4 = entity.field_70161_v;
    entity.field_70165_t = interpolate(entity.field_70169_q, entity.field_70165_t, paramFloat);
    entity.field_70163_u = interpolate(entity.field_70167_r, entity.field_70163_u, paramFloat);
    entity.field_70161_v = interpolate(entity.field_70166_s, entity.field_70161_v, paramFloat);
    String str = getDisplayTag(paramEntityPlayer);
    double d5 = entity.func_70011_f(paramDouble1 + (mc.func_175598_ae()).field_78730_l, paramDouble2 + (mc.func_175598_ae()).field_78731_m, paramDouble3 + (mc.func_175598_ae()).field_78728_n);
    int i = mc.field_71466_p.func_78256_a(str) / 2;
    double d6 = (0.0018D + this.m_scale.get_value(1) * d5 * 0.3D) / 1000.0D;
    if (d5 <= 8.0D)
      d6 = 0.0245D; 
    GlStateManager.func_179094_E();
    RenderHelper.func_74519_b();
    GlStateManager.func_179088_q();
    GlStateManager.func_179136_a(Float.intBitsToFloat(1.08307264E9F ^ 0x7F0E606F), Float.intBitsToFloat(-1.21428928E9F ^ 0x7E287193));
    GlStateManager.func_179140_f();
    GlStateManager.func_179109_b((float)paramDouble1, (float)d1 + Float.intBitsToFloat(1.08619418E9F ^ 0x7F0D32E8), (float)paramDouble3);
    GlStateManager.func_179114_b(-(mc.func_175598_ae()).field_78735_i, Float.intBitsToFloat(2.11803174E9F ^ 0x7E3E997F), Float.intBitsToFloat(1.09992883E9F ^ 0x7E0F951B), Float.intBitsToFloat(2.11949082E9F ^ 0x7E54DD2B));
    GlStateManager.func_179114_b((mc.func_175598_ae()).field_78732_j, (mc.field_71474_y.field_74320_O == 2) ? Float.intBitsToFloat(-1.04970771E9F ^ 0x7EEEBB4B) : Float.intBitsToFloat(1.08273549E9F ^ 0x7F093BA5), Float.intBitsToFloat(2.13497088E9F ^ 0x7F411216), Float.intBitsToFloat(2.13301069E9F ^ 0x7F232933));
    GlStateManager.func_179139_a(-d6, -d6, d6);
    GlStateManager.func_179097_i();
    GlStateManager.func_179147_l();
    GlStateManager.func_179147_l();
    boolean bool1 = FriendUtil.isFriend(paramEntityPlayer.func_70005_c_());
    boolean bool2 = EnemyUtil.isEnemy(paramEntityPlayer.func_70005_c_());
    int j = this.r.get_value(1);
    int k = this.g.get_value(1);
    int m = this.b.get_value(1);
    j = 157;
    k = 99;
    m = 255;
    j = 255;
    k = 40;
    m = 7;
    RenderUtil.drawRect((-i - 2) - Float.intBitsToFloat(1.11773402E9F ^ 0x7D1F448F), -(mc.field_71466_p.field_78288_b + 1) - Float.intBitsToFloat(1.09678387E9F ^ 0x7EDF9805), i + Float.intBitsToFloat(1.05489926E9F ^ 0x7EA07C2B), Float.intBitsToFloat(1.06188346E9F ^ 0x7F6B0E34), j, k, m, Float.intBitsToFloat(2.13741094E9F ^ 0x7F664D57));
    RenderUtil.drawRect((-i - 2), -(mc.field_71466_p.field_78288_b + 1), i + Float.intBitsToFloat(1.05722976E9F ^ 0x7F040BA3), Float.intBitsToFloat(1.10687437E9F ^ 0x7E399003), 1426063360);
    GlStateManager.func_179084_k();
    ItemStack itemStack = paramEntityPlayer.func_184614_ca().func_77946_l();
    if (itemStack.func_77962_s() && (itemStack.func_77973_b() instanceof net.minecraft.item.ItemTool || itemStack.func_77973_b() instanceof net.minecraft.item.ItemArmor))
      itemStack.field_77994_a = 1; 
    if (!itemStack.field_190928_g && itemStack.func_77973_b() != Items.field_190931_a) {
      String str1 = itemStack.func_82833_r();
      int n = mc.field_71466_p.func_78256_a(str1) / 2;
      GL11.glPushMatrix();
      GL11.glScalef(Float.intBitsToFloat(1.08008845E9F ^ 0x7F20D791), Float.intBitsToFloat(1.07407155E9F ^ 0x7F450833), Float.intBitsToFloat(2.12978854E9F ^ 0x7EF1FE49));
      mc.field_71466_p.func_175063_a(str1, -n, -(getBiggestArmorTag(paramEntityPlayer) + Float.intBitsToFloat(1.0635801E9F ^ 0x7EF4F1B9)), -1);
      GL11.glScalef(Float.intBitsToFloat(1.08217933E9F ^ 0x7F40BF3A), Float.intBitsToFloat(1.11201139E9F ^ 0x7D87F2B7), Float.intBitsToFloat(1.0849335E9F ^ 0x7F2AC5A0));
      GL11.glPopMatrix();
    } 
    if (this.show_armor.get_value(true)) {
      GlStateManager.func_179094_E();
      byte b = -8;
      for (ItemStack itemStack2 : paramEntityPlayer.field_71071_by.field_70460_b)
        b -= 8; 
      b -= 8;
      ItemStack itemStack1 = paramEntityPlayer.func_184592_cb().func_77946_l();
      if (itemStack1.func_77962_s() && (itemStack1.func_77973_b() instanceof net.minecraft.item.ItemTool || itemStack1.func_77973_b() instanceof net.minecraft.item.ItemArmor))
        itemStack1.field_77994_a = 1; 
      renderItemStack(itemStack1, b);
      b += 16;
      if (this.reverse.get_value(true)) {
        for (ItemStack itemStack2 : paramEntityPlayer.field_71071_by.field_70460_b) {
          ItemStack itemStack3 = itemStack2.func_77946_l();
          if (itemStack3.func_77962_s() && (itemStack3.func_77973_b() instanceof net.minecraft.item.ItemTool || itemStack3.func_77973_b() instanceof net.minecraft.item.ItemArmor))
            itemStack3.field_77994_a = 1; 
          renderItemStack(itemStack3, b);
          b += 16;
        } 
      } else {
        for (int n = paramEntityPlayer.field_71071_by.field_70460_b.size();; n--) {
          ItemStack itemStack2 = (ItemStack)paramEntityPlayer.field_71071_by.field_70460_b.get(n - 1);
          ItemStack itemStack3 = itemStack2.func_77946_l();
          if (itemStack3.func_77962_s() && (itemStack3.func_77973_b() instanceof net.minecraft.item.ItemTool || itemStack3.func_77973_b() instanceof net.minecraft.item.ItemArmor))
            itemStack3.field_77994_a = 1; 
          renderItemStack(itemStack3, b);
          b += 16;
        } 
      } 
      renderItemStack(itemStack, b);
      GlStateManager.func_179121_F();
    } 
    mc.field_71466_p.func_175063_a(str, -i, -(mc.field_71466_p.field_78288_b - 1), getDisplayColour(paramEntityPlayer));
    entity.field_70165_t = d2;
    entity.field_70163_u = d3;
    entity.field_70161_v = d4;
    GlStateManager.func_179126_j();
    GlStateManager.func_179084_k();
    GlStateManager.func_179113_r();
    GlStateManager.func_179136_a(Float.intBitsToFloat(1.08882163E9F ^ 0x7F6619BE), Float.intBitsToFloat(9.2523398E8F ^ 0x7E92E82D));
    GlStateManager.func_179121_F();
  }
  
  public void cycle_rainbow() {
    (give up)null;
    float[] arrayOfFloat = { (float)(System.currentTimeMillis() % 11520L) / 11520.0F };
    int i = Color.HSBtoRGB(arrayOfFloat[0], this.sat.get_value(1), this.brightness.get_value(1));
    this.r.set_value(i >> 16 & 0xFF);
    this.g.set_value(i >> 8 & 0xFF);
    this.b.set_value(i & 0xFF);
  }
  
  public double interpolate(double paramDouble1, double paramDouble2, float paramFloat) {
    (give up)null;
    return paramDouble1 + (paramDouble2 - paramDouble1) * paramFloat;
  }
  
  public NameTags() {
    super(Category.render);
  }
  
  public void update() {
    (give up)null;
    if (this.rainbow_mode.get_value(true))
      cycle_rainbow(); 
  }
}
