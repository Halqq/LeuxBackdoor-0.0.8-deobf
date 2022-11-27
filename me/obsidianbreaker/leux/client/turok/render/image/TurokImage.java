package me.obsidianbreaker.leux.client.turok.render.image;

import give up;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class TurokImage {
  public BufferedImage bufferedImage;
  
  public ResourceLocation resourceLocation;
  
  public String path;
  
  public DynamicTexture dynamicTexture;
  
  public TurokImage(String paramString) {
    this.path = paramString;
    this.bufferedImage = ImageIO.read(TurokImage.class.getResourceAsStream(this.path));
    this.dynamicTexture = new DynamicTexture(this.bufferedImage);
    this.resourceLocation = Minecraft.func_71410_x().func_110434_K().func_110578_a("turok/images", this.dynamicTexture);
  }
  
  public String getPath() {
    (give up)null;
    return this.path;
  }
  
  public ResourceLocation getResourceLocation() {
    (give up)null;
    return this.resourceLocation;
  }
  
  public BufferedImage getBufferedImage() {
    (give up)null;
    return this.bufferedImage;
  }
  
  public int getWidth() {
    (give up)null;
    return this.bufferedImage.getWidth();
  }
  
  public int getHeight() {
    (give up)null;
    return this.bufferedImage.getHeight();
  }
  
  public DynamicTexture getDynamicTexture() {
    (give up)null;
    return this.dynamicTexture;
  }
}
