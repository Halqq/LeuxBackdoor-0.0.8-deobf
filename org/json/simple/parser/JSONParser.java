package org.json.simple.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONParser {
  public static final int S_INIT = 0;
  
  public static final int S_IN_FINISHED_VALUE = 1;
  
  public static final int S_IN_OBJECT = 2;
  
  public static final int S_IN_ARRAY = 3;
  
  public static final int S_PASSED_PAIR_KEY = 4;
  
  public static final int S_IN_PAIR_VALUE = 5;
  
  public static final int S_END = 6;
  
  public static final int S_IN_ERROR = -1;
  
  private LinkedList handlerStatusStack;
  
  private Yylex lexer = new Yylex((Reader)null);
  
  private Yytoken token = null;
  
  private int status = 0;
  
  private int peekStatus(LinkedList paramLinkedList) {
    if (paramLinkedList.size() == 0)
      return -1; 
    Integer integer = paramLinkedList.getFirst();
    return integer.intValue();
  }
  
  public void reset() {
    this.token = null;
    this.status = 0;
    this.handlerStatusStack = null;
  }
  
  public void reset(Reader paramReader) {
    this.lexer.yyreset(paramReader);
    reset();
  }
  
  public int getPosition() {
    return this.lexer.getPosition();
  }
  
  public Object parse(String paramString) throws ParseException {
    return parse(paramString, (ContainerFactory)null);
  }
  
  public Object parse(String paramString, ContainerFactory paramContainerFactory) throws ParseException {
    StringReader stringReader = new StringReader(paramString);
    return parse(stringReader, paramContainerFactory);
  }
  
  public Object parse(Reader paramReader) throws IOException, ParseException {
    return parse(paramReader, (ContainerFactory)null);
  }
  
  public Object parse(Reader paramReader, ContainerFactory paramContainerFactory) throws IOException, ParseException {
    reset(paramReader);
    LinkedList linkedList = new LinkedList();
    LinkedList linkedList1 = new LinkedList();
    while (true) {
      String str;
      List list;
      Map map;
      List list1;
      Map map1;
      nextToken();
      switch (this.status) {
        case 0:
          switch (this.token.type) {
            case 0:
              this.status = 1;
              linkedList.addFirst(new Integer(this.status));
              linkedList1.addFirst(this.token.value);
              break;
            case 1:
              this.status = 2;
              linkedList.addFirst(new Integer(this.status));
              linkedList1.addFirst(createObjectContainer(paramContainerFactory));
              break;
            case 3:
              this.status = 3;
              linkedList.addFirst(new Integer(this.status));
              linkedList1.addFirst(createArrayContainer(paramContainerFactory));
              break;
          } 
          this.status = -1;
          break;
        case 1:
          if (this.token.type == -1)
            return linkedList1.removeFirst(); 
          throw new ParseException(getPosition(), 1, this.token);
        case 2:
          switch (this.token.type) {
            case 5:
              break;
            case 0:
              if (this.token.value instanceof String) {
                String str1 = (String)this.token.value;
                linkedList1.addFirst(str1);
                this.status = 4;
                linkedList.addFirst(new Integer(this.status));
                break;
              } 
              this.status = -1;
              break;
            case 2:
              if (linkedList1.size() > 1) {
                linkedList.removeFirst();
                linkedList1.removeFirst();
                this.status = peekStatus(linkedList);
                break;
              } 
              this.status = 1;
              break;
          } 
          this.status = -1;
          break;
        case 4:
          switch (this.token.type) {
            case 6:
              break;
            case 0:
              linkedList.removeFirst();
              str = (String)linkedList1.removeFirst();
              map = (Map)linkedList1.getFirst();
              map.put(str, this.token.value);
              this.status = peekStatus(linkedList);
              break;
            case 3:
              linkedList.removeFirst();
              str = (String)linkedList1.removeFirst();
              map = (Map)linkedList1.getFirst();
              list1 = createArrayContainer(paramContainerFactory);
              map.put(str, list1);
              this.status = 3;
              linkedList.addFirst(new Integer(this.status));
              linkedList1.addFirst(list1);
              break;
            case 1:
              linkedList.removeFirst();
              str = (String)linkedList1.removeFirst();
              map = (Map)linkedList1.getFirst();
              map1 = createObjectContainer(paramContainerFactory);
              map.put(str, map1);
              this.status = 2;
              linkedList.addFirst(new Integer(this.status));
              linkedList1.addFirst(map1);
              break;
          } 
          this.status = -1;
          break;
        case 3:
          switch (this.token.type) {
            case 5:
              break;
            case 0:
              list = (List)linkedList1.getFirst();
              list.add(this.token.value);
              break;
            case 4:
              if (linkedList1.size() > 1) {
                linkedList.removeFirst();
                linkedList1.removeFirst();
                this.status = peekStatus(linkedList);
                break;
              } 
              this.status = 1;
              break;
            case 1:
              list = (List)linkedList1.getFirst();
              map = createObjectContainer(paramContainerFactory);
              list.add(map);
              this.status = 2;
              linkedList.addFirst(new Integer(this.status));
              linkedList1.addFirst(map);
              break;
            case 3:
              list = (List)linkedList1.getFirst();
              list1 = createArrayContainer(paramContainerFactory);
              list.add(list1);
              this.status = 3;
              linkedList.addFirst(new Integer(this.status));
              linkedList1.addFirst(list1);
              break;
          } 
          this.status = -1;
          break;
        case -1:
          throw new ParseException(getPosition(), 1, this.token);
      } 
      if (this.status == -1)
        throw new ParseException(getPosition(), 1, this.token); 
      if (this.token.type == -1)
        throw new ParseException(getPosition(), 1, this.token); 
    } 
  }
  
  private void nextToken() throws ParseException, IOException {
    this.token = this.lexer.yylex();
    if (this.token == null)
      this.token = new Yytoken(-1, null); 
  }
  
  private Map createObjectContainer(ContainerFactory paramContainerFactory) {
    return (Map)new JSONObject();
  }
  
  private List createArrayContainer(ContainerFactory paramContainerFactory) {
    return (List)new JSONArray();
  }
  
  public void parse(String paramString, ContentHandler paramContentHandler) throws ParseException {
    parse(paramString, paramContentHandler, false);
  }
  
  public void parse(String paramString, ContentHandler paramContentHandler, boolean paramBoolean) throws ParseException {
    paramBoolean = false;
    StringReader stringReader = new StringReader(paramString);
    parse(stringReader, paramContentHandler, paramBoolean);
  }
  
  public void parse(Reader paramReader, ContentHandler paramContentHandler) throws IOException, ParseException {
    parse(paramReader, paramContentHandler, false);
  }
  
  public void parse(Reader paramReader, ContentHandler paramContentHandler, boolean paramBoolean) throws IOException, ParseException {
    paramBoolean = false;
    reset(paramReader);
    this.handlerStatusStack = new LinkedList();
    LinkedList linkedList = this.handlerStatusStack;
    while (true) {
      switch (this.status) {
        case 0:
          paramContentHandler.startJSON();
          nextToken();
          switch (this.token.type) {
            case 0:
              this.status = 1;
              linkedList.addFirst(new Integer(this.status));
              if (!paramContentHandler.primitive(this.token.value))
                return; 
              break;
            case 1:
              this.status = 2;
              linkedList.addFirst(new Integer(this.status));
              if (!paramContentHandler.startObject())
                return; 
              break;
            case 3:
              this.status = 3;
              linkedList.addFirst(new Integer(this.status));
              if (!paramContentHandler.startArray())
                return; 
              break;
          } 
          this.status = -1;
          break;
        case 1:
          nextToken();
          if (this.token.type == -1) {
            paramContentHandler.endJSON();
            this.status = 6;
            return;
          } 
          this.status = -1;
          throw new ParseException(getPosition(), 1, this.token);
        case 2:
          nextToken();
          switch (this.token.type) {
            case 5:
              break;
            case 0:
              if (this.token.value instanceof String) {
                String str = (String)this.token.value;
                this.status = 4;
                linkedList.addFirst(new Integer(this.status));
                if (!paramContentHandler.startObjectEntry(str))
                  return; 
                break;
              } 
              this.status = -1;
              break;
            case 2:
              if (linkedList.size() > 1) {
                linkedList.removeFirst();
                this.status = peekStatus(linkedList);
              } else {
                this.status = 1;
              } 
              if (!paramContentHandler.endObject())
                return; 
              break;
          } 
          this.status = -1;
          break;
        case 4:
          nextToken();
          switch (this.token.type) {
            case 6:
              break;
            case 0:
              linkedList.removeFirst();
              this.status = peekStatus(linkedList);
              if (!paramContentHandler.primitive(this.token.value))
                return; 
              if (!paramContentHandler.endObjectEntry())
                return; 
              break;
            case 3:
              linkedList.removeFirst();
              linkedList.addFirst(new Integer(5));
              this.status = 3;
              linkedList.addFirst(new Integer(this.status));
              if (!paramContentHandler.startArray())
                return; 
              break;
            case 1:
              linkedList.removeFirst();
              linkedList.addFirst(new Integer(5));
              this.status = 2;
              linkedList.addFirst(new Integer(this.status));
              if (!paramContentHandler.startObject())
                return; 
              break;
          } 
          this.status = -1;
          break;
        case 5:
          linkedList.removeFirst();
          this.status = peekStatus(linkedList);
          if (!paramContentHandler.endObjectEntry())
            return; 
          break;
        case 3:
          nextToken();
          switch (this.token.type) {
            case 5:
              break;
            case 0:
              if (!paramContentHandler.primitive(this.token.value))
                return; 
              break;
            case 4:
              if (linkedList.size() > 1) {
                linkedList.removeFirst();
                this.status = peekStatus(linkedList);
              } else {
                this.status = 1;
              } 
              if (!paramContentHandler.endArray())
                return; 
              break;
            case 1:
              this.status = 2;
              linkedList.addFirst(new Integer(this.status));
              if (!paramContentHandler.startObject())
                return; 
              break;
            case 3:
              this.status = 3;
              linkedList.addFirst(new Integer(this.status));
              if (!paramContentHandler.startArray())
                return; 
              break;
          } 
          this.status = -1;
          break;
        case 6:
          return;
        case -1:
          throw new ParseException(getPosition(), 1, this.token);
      } 
      if (this.status == -1)
        throw new ParseException(getPosition(), 1, this.token); 
      if (this.token.type == -1) {
        this.status = -1;
        throw new ParseException(getPosition(), 1, this.token);
      } 
    } 
  }
}
