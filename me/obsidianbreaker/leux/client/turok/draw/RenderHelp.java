package me.obsidianbreaker.leux.client.turok.draw;

import give up;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class RenderHelp extends Tessellator {
  public static RenderHelp INSTANCE = new RenderHelp();
  
  public static void draw_gradiant_outline(BufferBuilder paramBufferBuilder, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, Color paramColor1, Color paramColor2, String paramString) {
    (give up)null;
    List<String> list = Arrays.asList(paramString.split("-"));
    boolean bool = paramString.equalsIgnoreCase("all");
    if (!list.contains("northwest"));
    draw_gradiant_line(paramBufferBuilder, paramDouble1, paramDouble2, paramDouble3, paramDouble1, paramDouble2 + paramDouble4, paramDouble3, paramColor1, paramColor2);
    if (!list.contains("northeast"));
    draw_gradiant_line(paramBufferBuilder, paramDouble1 + 1.0D, paramDouble2, paramDouble3, paramDouble1 + 1.0D, paramDouble2 + paramDouble4, paramDouble3, paramColor1, paramColor2);
    if (!list.contains("southwest"));
    draw_gradiant_line(paramBufferBuilder, paramDouble1, paramDouble2, paramDouble3 + 1.0D, paramDouble1, paramDouble2 + paramDouble4, paramDouble3 + 1.0D, paramColor1, paramColor2);
    if (!list.contains("southeast"));
    draw_gradiant_line(paramBufferBuilder, paramDouble1 + 1.0D, paramDouble2, paramDouble3 + 1.0D, paramDouble1 + 1.0D, paramDouble2 + paramDouble4, paramDouble3 + 1.0D, paramColor1, paramColor2);
  }
  
  public static void render() {
    (give up)null;
    INSTANCE.func_78381_a();
  }
  
  public static void release_gl() {
    (give up)null;
    GlStateManager.func_179089_o();
    GlStateManager.func_179132_a(true);
    GlStateManager.func_179098_w();
    GlStateManager.func_179147_l();
    GlStateManager.func_179126_j();
  }
  
  public static void draw_cube_line(BlockPos paramBlockPos, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString) {
    paramString = "all";
    (give up)null;
    draw_cube_line(INSTANCE.func_178180_c(), paramBlockPos.func_177958_n(), paramBlockPos.func_177956_o(), paramBlockPos.func_177952_p(), 1.0F, 1.0F, 1.0F, paramInt1, paramInt2, paramInt3, paramInt4, paramString);
  }
  
  public static void prepare_gl() {
    (give up)null;
    GL11.glBlendFunc(770, 771);
    GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    GlStateManager.func_187441_d(1.5F);
    GlStateManager.func_179090_x();
    GlStateManager.func_179132_a(false);
    GlStateManager.func_179147_l();
    GlStateManager.func_179097_i();
    GlStateManager.func_179140_f();
    GlStateManager.func_179129_p();
    GlStateManager.func_179141_d();
    GlStateManager.func_179124_c(1.0F, 1.0F, 1.0F);
  }
  
  public static void prepare(String paramString) {
    (give up)null;
    byte b = 0;
    if (paramString.equalsIgnoreCase("quads")) {
      b = 7;
    } else if (paramString.equalsIgnoreCase("lines")) {
      b = 1;
    } else if (paramString.equalsIgnoreCase("triangles")) {
      b = 4;
    } 
    prepare_gl();
    begin(b);
  }
  
  public static void begin(int paramInt) {
    paramInt = 0;
    (give up)null;
    INSTANCE.func_178180_c().func_181668_a(paramInt, DefaultVertexFormats.field_181706_f);
  }
  
  public static void draw_gradiant_cube(BufferBuilder paramBufferBuilder, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, Color paramColor1, Color paramColor2, String paramString) {
    (give up)null;
    int i = paramColor1.getRed();
    int j = paramColor1.getGreen();
    int k = paramColor1.getBlue();
    int m = paramColor1.getAlpha();
    int n = paramColor2.getRed();
    int i1 = paramColor2.getGreen();
    int i2 = paramColor2.getBlue();
    int i3 = paramColor2.getAlpha();
    List<String> list = Arrays.asList(paramString.split("-"));
    if (list.contains("north") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, paramFloat3).func_181669_b(i, j, k, m).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, paramFloat3).func_181669_b(i, j, k, m).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(n, i1, i2, i3).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(n, i1, i2, i3).func_181675_d();
    } 
    if (list.contains("south") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(i, j, k, m).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(i, j, k, m).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(n, i1, i2, i3).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(n, i1, i2, i3).func_181675_d();
    } 
    if (list.contains("west") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, paramFloat3).func_181669_b(i, j, k, m).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(i, j, k, m).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(n, i1, i2, i3).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(n, i1, i2, i3).func_181675_d();
    } 
    if (list.contains("east") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(i, j, k, m).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, paramFloat3).func_181669_b(i, j, k, m).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(n, i1, i2, i3).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(n, i1, i2, i3).func_181675_d();
    } 
  }
  
  public static BufferBuilder get_buffer_build() {
    (give up)null;
    return INSTANCE.func_178180_c();
  }
  
  public static void draw_gradiant_line(BufferBuilder paramBufferBuilder, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, Color paramColor1, Color paramColor2) {
    (give up)null;
    paramBufferBuilder.func_181662_b(paramDouble1, paramDouble2, paramDouble3).func_181669_b(paramColor1.getRed(), paramColor1.getGreen(), paramColor1.getBlue(), paramColor1.getAlpha()).func_181675_d();
    paramBufferBuilder.func_181662_b(paramDouble4, paramDouble5, paramDouble6).func_181669_b(paramColor2.getRed(), paramColor2.getGreen(), paramColor2.getBlue(), paramColor2.getAlpha()).func_181675_d();
  }
  
  public static void release() {
    (give up)null;
    render();
    release_gl();
  }
  
  public static void draw_cube_line(BufferBuilder paramBufferBuilder, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString) {
    (give up)null;
    List<String> list = Arrays.asList(paramString.split("-"));
    if (list.contains("downwest") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("upwest") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("downeast") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("upeast") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("downnorth") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("upnorth") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("downsouth") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("upsouth") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("northwest") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("northeast") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("southwest") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("southeast") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
  }
  
  public RenderHelp() {
    super(2097152);
  }
  
  public static void draw_cube(BlockPos paramBlockPos, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString) {
    paramString = "all";
    (give up)null;
    draw_cube(INSTANCE.func_178180_c(), paramBlockPos.func_177958_n(), paramBlockPos.func_177956_o(), paramBlockPos.func_177952_p(), 1.0F, 1.0F, 1.0F, paramInt1, paramInt2, paramInt3, paramInt4, paramString);
  }
  
  public static void draw_cube(BufferBuilder paramBufferBuilder, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString) {
    (give up)null;
    List<String> list = Arrays.asList(paramString.split("-"));
    if (list.contains("down") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("up") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("north") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("south") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("west") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("east") || paramString.equalsIgnoreCase("all")) {
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("top")) {
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, (paramFloat2 + paramFloat5), (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
    if (list.contains("bottom")) {
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, paramFloat3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b((paramFloat1 + paramFloat4), paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
      paramBufferBuilder.func_181662_b(paramFloat1, paramFloat2, (paramFloat3 + paramFloat6)).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    } 
  }
}
