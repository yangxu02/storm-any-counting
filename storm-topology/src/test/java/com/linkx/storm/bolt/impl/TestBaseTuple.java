package com.linkx.storm.bolt.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.storm.generated.GlobalStreamId;
import org.apache.storm.shade.com.google.common.collect.Lists;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.MessageId;
import org.apache.storm.tuple.Tuple;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/15.
 */
public class TestBaseTuple implements Tuple {

  Map<String, Object> valueMap = new HashMap<>();

  public void addValues(Map<String, Object> values) {
    valueMap.putAll(values);
  }

  @Override public GlobalStreamId getSourceGlobalStreamid() {
    return null;
  }

  @Override public GlobalStreamId getSourceGlobalStreamId() {
    return null;
  }

  @Override public String getSourceComponent() {
    return null;
  }

  @Override public int getSourceTask() {
    return 0;
  }

  @Override public String getSourceStreamId() {
    return null;
  }

  @Override public MessageId getMessageId() {
    return null;
  }

  @Override public int size() {
    return 0;
  }

  @Override public boolean contains(String s) {
    return false;
  }

  @Override public Fields getFields() {
    return null;
  }

  @Override public int fieldIndex(String s) {
    return 0;
  }

  @Override public List<Object> select(Fields fields) {
    return null;
  }

  @Override public Object getValue(int i) {
    return null;
  }

  @Override public String getString(int i) {
    return null;
  }

  @Override public Integer getInteger(int i) {
    return null;
  }

  @Override public Long getLong(int i) {
    return null;
  }

  @Override public Boolean getBoolean(int i) {
    return null;
  }

  @Override public Short getShort(int i) {
    return null;
  }

  @Override public Byte getByte(int i) {
    return null;
  }

  @Override public Double getDouble(int i) {
    return null;
  }

  @Override public Float getFloat(int i) {
    return null;
  }

  @Override public byte[] getBinary(int i) {
    return new byte[0];
  }

  @Override public Object getValueByField(String s) {
    return valueMap.get(s);
  }

  @Override public String getStringByField(String s) {
    return (String)valueMap.get(s);
  }

  @Override public Integer getIntegerByField(String s) {
    return (int)valueMap.get(s);
  }

  @Override public Long getLongByField(String s) {
    return (long)valueMap.get(s);
  }

  @Override public Boolean getBooleanByField(String s) {
    return (boolean)valueMap.get(s);
  }

  @Override public Short getShortByField(String s) {
    return (short)valueMap.get(s);
  }

  @Override public Byte getByteByField(String s) {
    return (Byte)valueMap.get(s);
  }

  @Override public Double getDoubleByField(String s) {
    return (double)valueMap.get(s);
  }

  @Override public Float getFloatByField(String s) {
    return (float)valueMap.get(s);
  }

  @Override public byte[] getBinaryByField(String s) {
    return (byte[])valueMap.get(s);
  }

  @Override public List<Object> getValues() {
    return Lists.newArrayList(valueMap.values());
  }
}
