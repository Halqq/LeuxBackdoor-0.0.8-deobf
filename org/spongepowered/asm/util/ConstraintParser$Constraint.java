package org.spongepowered.asm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.spongepowered.asm.util.throwables.ConstraintViolationException;
import org.spongepowered.asm.util.throwables.InvalidConstraintException;

public class ConstraintParser$Constraint {
  public static final ConstraintParser$Constraint NONE = new ConstraintParser$Constraint();
  
  private static final Pattern pattern = Pattern.compile("^([A-Z0-9\\-_\\.]+)\\((?:(<|<=|>|>=|=)?([0-9]+)(<|(-)([0-9]+)?|>|(\\+)([0-9]+)?)?)?\\)$");
  
  private final String expr;
  
  private String token;
  
  private String[] constraint;
  
  private int min = Integer.MIN_VALUE;
  
  private int max = Integer.MAX_VALUE;
  
  private ConstraintParser$Constraint next;
  
  ConstraintParser$Constraint(String paramString) {
    this.expr = paramString;
    Matcher matcher = pattern.matcher(paramString);
    if (!matcher.matches())
      throw new InvalidConstraintException("Constraint syntax was invalid parsing: " + this.expr); 
    this.token = matcher.group(1);
    this.constraint = new String[] { matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5), matcher.group(6), matcher.group(7), matcher.group(8) };
    parse();
  }
  
  private ConstraintParser$Constraint() {
    this.expr = null;
    this.token = "*";
    this.constraint = new String[0];
  }
  
  private void parse() {
    if (!has(1))
      return; 
    this.max = this.min = val(1);
    boolean bool = has(0);
    if (has(4))
      throw new InvalidConstraintException("Unexpected modifier '" + elem(0) + "' in " + this.expr + " parsing range"); 
    if (has(6))
      throw new InvalidConstraintException("Unexpected modifier '" + elem(0) + "' in " + this.expr + " parsing range"); 
    if (has(3))
      throw new InvalidConstraintException("Unexpected trailing modifier '" + elem(3) + "' in " + this.expr); 
    String str = elem(0);
    if (">".equals(str)) {
      this.min++;
      this.max = Integer.MAX_VALUE;
    } else if (">=".equals(str)) {
      this.max = Integer.MAX_VALUE;
    } else if ("<".equals(str)) {
      this.max = --this.min;
      this.min = Integer.MIN_VALUE;
    } else if ("<=".equals(str)) {
      this.max = this.min;
      this.min = Integer.MIN_VALUE;
    } 
  }
  
  private boolean has(int paramInt) {
    return (this.constraint[paramInt] != null);
  }
  
  private String elem(int paramInt) {
    return this.constraint[paramInt];
  }
  
  private int val(int paramInt) {
    return (this.constraint[paramInt] != null) ? Integer.parseInt(this.constraint[paramInt]) : 0;
  }
  
  void append(ConstraintParser$Constraint paramConstraintParser$Constraint) {
    if (this.next != null) {
      this.next.append(paramConstraintParser$Constraint);
      return;
    } 
    this.next = paramConstraintParser$Constraint;
  }
  
  public String getToken() {
    return this.token;
  }
  
  public int getMin() {
    return this.min;
  }
  
  public int getMax() {
    return this.max;
  }
  
  public void check(ITokenProvider paramITokenProvider) throws ConstraintViolationException {
    if (this != NONE) {
      Integer integer = paramITokenProvider.getToken(this.token);
      throw new ConstraintViolationException("The token '" + this.token + "' could not be resolved in " + paramITokenProvider, this);
    } 
    if (this.next != null)
      this.next.check(paramITokenProvider); 
  }
  
  public String getRangeHumanReadable() {
    return (this.min == Integer.MIN_VALUE && this.max == Integer.MAX_VALUE) ? "ANY VALUE" : ((this.min == Integer.MIN_VALUE) ? String.format("less than or equal to %d", new Object[] { Integer.valueOf(this.max) }) : ((this.max == Integer.MAX_VALUE) ? String.format("greater than or equal to %d", new Object[] { Integer.valueOf(this.min) }) : ((this.min == this.max) ? String.format("%d", new Object[] { Integer.valueOf(this.min) }) : String.format("between %d and %d", new Object[] { Integer.valueOf(this.min), Integer.valueOf(this.max) }))));
  }
  
  public String toString() {
    return String.format("Constraint(%s [%d-%d])", new Object[] { this.token, Integer.valueOf(this.min), Integer.valueOf(this.max) });
  }
}
