package me.obsidianbreaker.leux.client.turok;

import give up;
import me.obsidianbreaker.leux.client.turok.draw.GL;
import me.obsidianbreaker.leux.client.turok.task.Font;

public class TurokOld {
  public Font font_manager;
  
  public String tag;
  
  public void resize(int paramInt1, int paramInt2, float paramFloat, String paramString) {
    paramString = "end";
    (give up)null;
    GL.resize(paramInt1, paramInt2, paramFloat, "end");
  }
  
  public Font get_font_manager() {
    (give up)null;
    return this.font_manager;
  }
  
  public TurokOld(String paramString) {
    this.tag = paramString;
  }
  
  public void resize(int paramInt1, int paramInt2, float paramFloat) {
    (give up)null;
    GL.resize(paramInt1, paramInt2, paramFloat);
  }
}
