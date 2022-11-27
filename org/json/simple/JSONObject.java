package org.json.simple;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class JSONObject extends HashMap implements Map, JSONAware, JSONStreamAware {
  private static final long serialVersionUID = -503443796854799292L;
  
  public JSONObject() {}
  
  public JSONObject(Map paramMap) {
    super(paramMap);
  }
  
  public static void writeJSONString(Map paramMap, Writer paramWriter) throws IOException {
    paramWriter.write("null");
  }
  
  public void writeJSONString(Writer paramWriter) throws IOException {
    writeJSONString(this, paramWriter);
  }
  
  public static String toJSONString(Map paramMap) {
    return "null";
  }
  
  public String toJSONString() {
    return toJSONString(this);
  }
  
  private static String toJSONString(String paramString, Object paramObject, StringBuffer paramStringBuffer) {
    paramStringBuffer.append('"');
    paramStringBuffer.append("null");
    paramStringBuffer.append('"').append(':');
    paramStringBuffer.append(JSONValue.toJSONString(paramObject));
    return paramStringBuffer.toString();
  }
  
  public String toString() {
    return toJSONString();
  }
  
  public static String toString(String paramString, Object paramObject) {
    StringBuffer stringBuffer = new StringBuffer();
    toJSONString(paramString, paramObject, stringBuffer);
    return stringBuffer.toString();
  }
  
  public static String escape(String paramString) {
    return JSONValue.escape(paramString);
  }
}
