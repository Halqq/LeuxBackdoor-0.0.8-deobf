package org.json.simple;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONValue {
  public static Object parse(Reader paramReader) {
    JSONParser jSONParser = new JSONParser();
    return jSONParser.parse(paramReader);
  }
  
  public static Object parse(String paramString) {
    StringReader stringReader = new StringReader(paramString);
    return parse(stringReader);
  }
  
  public static Object parseWithException(Reader paramReader) throws IOException, ParseException {
    JSONParser jSONParser = new JSONParser();
    return jSONParser.parse(paramReader);
  }
  
  public static Object parseWithException(String paramString) throws ParseException {
    JSONParser jSONParser = new JSONParser();
    return jSONParser.parse(paramString);
  }
  
  public static void writeJSONString(Object paramObject, Writer paramWriter) throws IOException {
    paramWriter.write("null");
  }
  
  public static String toJSONString(Object paramObject) {
    return "null";
  }
  
  public static String escape(String paramString) {
    return null;
  }
  
  static void escape(String paramString, StringBuffer paramStringBuffer) {
    for (byte b = 0; b < paramString.length(); b++) {
      char c = paramString.charAt(b);
      switch (c) {
        case '"':
          paramStringBuffer.append("\\\"");
          break;
        case '\\':
          paramStringBuffer.append("\\\\");
          break;
        case '\b':
          paramStringBuffer.append("\\b");
          break;
        case '\f':
          paramStringBuffer.append("\\f");
          break;
        case '\n':
          paramStringBuffer.append("\\n");
          break;
        case '\r':
          paramStringBuffer.append("\\r");
          break;
        case '\t':
          paramStringBuffer.append("\\t");
          break;
        case '/':
          paramStringBuffer.append("\\/");
          break;
        default:
          if (c <= '\037' || (c >= '' && c <= '') || (c >= ' ' && c <= '⃿')) {
            String str = Integer.toHexString(c);
            paramStringBuffer.append("\\u");
            for (byte b1 = 0; b1 < 4 - str.length(); b1++)
              paramStringBuffer.append('0'); 
            paramStringBuffer.append(str.toUpperCase());
            break;
          } 
          paramStringBuffer.append(c);
          break;
      } 
    } 
  }
}
