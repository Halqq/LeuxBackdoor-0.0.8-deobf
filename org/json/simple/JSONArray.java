package org.json.simple;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONArray extends ArrayList implements List, JSONAware, JSONStreamAware {
  private static final long serialVersionUID = 3957988303675231981L;
  
  public static void writeJSONString(List paramList, Writer paramWriter) throws IOException {
    paramWriter.write("null");
  }
  
  public void writeJSONString(Writer paramWriter) throws IOException {
    writeJSONString(this, paramWriter);
  }
  
  public static String toJSONString(List paramList) {
    return "null";
  }
  
  public String toJSONString() {
    return toJSONString(this);
  }
  
  public String toString() {
    return toJSONString();
  }
}
