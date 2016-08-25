package com.linkx.storm.context;

import com.linkx.storm.context.config.AbstractConfig;
import com.linkx.storm.spout.EchoSpout;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.topology.IRichSpout;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */


public class SpoutContext {
  String id;
  int parallel;
  String type;
  AbstractConfig config;

  public IRichSpout createSpout() {
    // TODO
    if ("kafka".equalsIgnoreCase(type)) {
      return new KafkaSpout(config.get());
    } else if ("echo".equalsIgnoreCase(type)) {
      return new EchoSpout();
    }
    return null;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getParallel() {
    return parallel;
  }

  public void setParallel(int parallel) {
    this.parallel = parallel;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public AbstractConfig getConfig() {
    return config;
  }

  public void setConfig(AbstractConfig config) {
    this.config = config;
  }

}
