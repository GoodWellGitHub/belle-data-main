package com.belle.util;

import com.belle.util.annotation.FieldKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

  public static Map<String,Object> getBeanMap(Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Class clazz = o.getClass();
    Field [] fileds = clazz.getDeclaredFields();
    Map<String,Object> map = new HashMap<>();
    for(Field f:fileds){
      Method m = clazz.getDeclaredMethod("get"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1));
      FieldKey fieldKey = f.getDeclaredAnnotation(FieldKey.class);
      String key = f.getName();
      if(fieldKey !=null){
        key = fieldKey.value().equals("")?key: fieldKey.value();
      }else {
        key = f.getName();
      }
      Object value = m.invoke(o);
      if(value!=null) {
        map.put(key, value);
      }
    }
    return map;
  }

  public static void setBeanMap(Map<String,Object> map,Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Class clazz = o.getClass();
    Field [] fileds = clazz.getDeclaredFields();
    for(Field f:fileds){
      String key = f.getName();
      Method [] ms = clazz.getDeclaredMethods();

      Method m = clazz.getDeclaredMethod("set"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1),String.class);
      if(map.containsKey(key)){
        m.invoke(o,map.get(key));
      }else {
        FieldKey fieldKey = f.getDeclaredAnnotation(FieldKey.class);
        if(fieldKey !=null){
          key = fieldKey.value().equals("")?key: fieldKey.value();
        }
        if(map.containsKey(key)){
          m.invoke(o,map.get(key));
        }
      }
    }
  }

  public static Map<String,BeanWithAttr> getBeanMapWithAttr(Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Class clazz = o.getClass();
    Field [] fileds = clazz.getDeclaredFields();
    Map<String,BeanWithAttr> map = new HashMap<>();
    for(Field f:fileds){
      Method m = clazz.getDeclaredMethod("get"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1));
      FieldKey fieldKey = f.getDeclaredAnnotation(FieldKey.class);
      String key = f.getName();
      if(fieldKey !=null){
        key = fieldKey.value().equals("")?key: fieldKey.value();
      }else {
        key = f.getName();
      }
      Object value = m.invoke(o);
      if(value!=null) {
        map.put(key, new BeanWithAttr(value,fieldKey));
      }
    }
    return map;
  }
}
