package com.linkx.storm.context.group;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.storm.topology.BoltDeclarer;
import org.apache.storm.tuple.Fields;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */
public class FieldsGrouping extends AbstractGrouping {
  String id;
  String stream;
  String[] fields;


  @Override public BoltDeclarer attach(BoltDeclarer boltDeclarer) {
    return boltDeclarer.fieldsGrouping(id, stream, new Fields(fields));
  }

  @JsonCreator
  public FieldsGrouping(
      @JsonProperty("id") String id,
      @JsonProperty("stream") String stream,
      @JsonProperty("fields") String[] fields) {
    this.id = id;
    this.stream = stream;
    this.fields = fields;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("stream")
  public String getStream() {
    return stream;
  }

  @JsonProperty("fields")
  public String[] getFields() {
    return fields;
  }
}
