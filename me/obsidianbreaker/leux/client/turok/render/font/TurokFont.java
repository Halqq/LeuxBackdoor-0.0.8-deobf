package me.obsidianbreaker.leux.client.turok.render.font;

import give up;
import java.awt.Font;
import me.obsidianbreaker.leux.client.turok.render.font.hal.CFontRenderer;

public class TurokFont extends CFontRenderer {
  public boolean isRenderingCustomFont;
  
  public Font font;
  
  public boolean isRenderingCustomFont() {
    (give up)null;
    return this.isRenderingCustomFont;
  }
  
  public void setRenderingCustomFont(boolean paramBoolean) {
    (give up)null;
    this.isRenderingCustomFont = paramBoolean;
  }
  
  public TurokFont(Font paramFont, boolean paramBoolean1, boolean paramBoolean2) {
    super(paramFont, paramBoolean1, paramBoolean2);
    this.font = paramFont;
    this.isRenderingCustomFont = true;
  }
}
