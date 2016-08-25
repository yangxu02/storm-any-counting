package com.linkx.storm.context.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linkx.storm.spout.scheme.AbstractScheme;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */
public class KafkaSpoutConfig extends AbstractConfig {
  int flag;
  String topic;
  String consumer;
  String zk_hosts;
  String zk_root;
  AbstractScheme scheme;

  @JsonCreator
  public KafkaSpoutConfig(
      @JsonProperty("flag") int flag,
      @JsonProperty("topic") String topic,
      @JsonProperty("consumer") String consumer,
      @JsonProperty("zk_hosts") String zk_hosts,
      @JsonProperty("zk_root") String zk_path,
      @JsonProperty("scheme") AbstractScheme scheme
      ) {
    this.flag = flag;
    this.topic = topic;
    this.consumer = consumer;
    this.zk_hosts = zk_hosts;
    this.zk_root = zk_path;
    this.scheme = scheme;
  }

  @JsonProperty("flag")
  public int getFlag() {
    return flag;
  }

  @JsonProperty("topic")
  public String getTopic() {
    return topic;
  }

  @JsonProperty("consumer")
  public String getConsumer() {
    return consumer;
  }

  @JsonProperty("zk_hosts")
  public String getZk_hosts() {
    return zk_hosts;
  }

  @JsonProperty("zk_root")
  public String getZk_root() {
    return zk_root;
  }

  @JsonProperty("scheme")
  public AbstractScheme getScheme() {
    return scheme;
  }

  @Override
  public SpoutConfig get() {
    ZkHosts zkHosts = new ZkHosts(zk_hosts);
    //        ZkHosts zkHosts = new ZkHosts("localhost");
    SpoutConfig spoutConfig = new SpoutConfig(zkHosts, topic, zk_root, consumer);
    if (flag != 0) {
      spoutConfig.startOffsetTime = flag;
      //spoutConfig.forceFromStart = true;
    }
    // 如何配置kafka连接zk的超时时间
    // TODO
    spoutConfig.scheme = scheme;
    return spoutConfig;
  }

}
