package com.linkx.storm.context.group;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.storm.topology.BoltDeclarer;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */
public class AllGrouping extends AbstractGrouping {
  String id;
  String stream;

  @Override public BoltDeclarer attach(BoltDeclarer boltDeclarer) {
    return boltDeclarer.allGrouping(id, stream);
  }

  @JsonCreator
  public AllGrouping(
      @JsonProperty("id") String id,
      @JsonProperty("stream") String stream) {
    this.id = id;
    this.stream = stream;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("stream")
  public String getStream() {
    return stream;
  }
}
