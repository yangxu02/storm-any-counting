package com.linkx.storm.context;

import com.linkx.storm.bolt.field.Field;
import com.linkx.storm.context.config.AbstractConfig;
import com.linkx.storm.context.group.AbstractGrouping;
import com.linkx.storm.context.stream.Stream;
import org.apache.storm.topology.IRichBolt;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */
public class BoltContext {
  String id;
  String type;
  int parallel;
  String grouping;
  AbstractGrouping[] groupings;

  Field[] fields;
  Stream[] streams;

  AbstractConfig config;

  public IRichBolt createBolt() {
    // TODO
    return null;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getParallel() {
    return parallel;
  }

  public void setParallel(int parallel) {
    this.parallel = parallel;
  }

  public String getGrouping() {
    return grouping;
  }

  public void setGrouping(String grouping) {
    this.grouping = grouping;
  }

  public AbstractGrouping[] getGroupings() {
    return groupings;
  }

  public void setGroupings(AbstractGrouping[] groupings) {
    this.groupings = groupings;
  }

  public AbstractConfig getConfig() {
    return config;
  }

  public void setConfig(AbstractConfig config) {
    this.config = config;
  }

  public Field[] getFields() {
    return fields;
  }

  public Stream[] getStreams() {
    return streams;
  }

  public void setFields(Field[] fields) {
    this.fields = fields;
  }

  public void setStreams(Stream[] streams) {
    this.streams = streams;
  }
}
