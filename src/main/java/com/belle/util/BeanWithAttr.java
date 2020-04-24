package com.belle.util;


import com.belle.util.annotation.FieldKey;

public class BeanWithAttr {
  private Object value;
  private FieldKey fieldKey;

  public BeanWithAttr() {
  }

  public BeanWithAttr(Object value, FieldKey fieldKey) {
    this.value = value;
    this.fieldKey = fieldKey;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public FieldKey getFieldKey() {
    return fieldKey;
  }

  public void setFieldKey(FieldKey fieldKey) {
    this.fieldKey = fieldKey;
  }

  @Override
  public String toString() {
    return "BeanWithAttr{" +
        "value=" + value +
        ", fieldKey=" + fieldKey +
        '}';
  }
}
