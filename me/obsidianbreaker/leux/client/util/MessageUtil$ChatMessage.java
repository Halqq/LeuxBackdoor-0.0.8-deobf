package me.obsidianbreaker.leux.client.util;

import give up;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;

public class MessageUtil$ChatMessage extends TextComponentBase {
  public String message_input;
  
  public String func_150261_e() {
    (give up)null;
    return this.message_input;
  }
  
  public MessageUtil$ChatMessage(String paramString) {
    Pattern pattern = Pattern.compile("&[0123456789abcdefrlosmk]");
    Matcher matcher = pattern.matcher(paramString);
    StringBuffer stringBuffer = new StringBuffer();
    while (matcher.find()) {
      String str = "§" + matcher.group().substring(1);
      matcher.appendReplacement(stringBuffer, str);
    } 
    matcher.appendTail(stringBuffer);
    this.message_input = stringBuffer.toString();
  }
  
  public ITextComponent func_150259_f() {
    (give up)null;
    return (ITextComponent)new MessageUtil$ChatMessage(this.message_input);
  }
}
