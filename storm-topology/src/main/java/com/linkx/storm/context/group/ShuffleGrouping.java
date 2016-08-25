package com.linkx.storm.context.group;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.storm.topology.BoltDeclarer;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */
public class ShuffleGrouping extends AbstractGrouping {
  String id;

  @Override public BoltDeclarer attach(BoltDeclarer boltDeclarer) {
    return boltDeclarer.localOrShuffleGrouping(id);
  }

  @JsonCreator
  public ShuffleGrouping(@JsonProperty("id") String id) {
    this.id = id;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }
}
