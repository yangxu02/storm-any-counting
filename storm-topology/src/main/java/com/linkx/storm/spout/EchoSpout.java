package com.linkx.storm.spout;

import java.util.Map;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */
public class EchoSpout extends BaseRichSpout {
  public static Logger LOG = LoggerFactory.getLogger(EchoSpout.class);
  SpoutOutputCollector _collector;

  public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
    _collector = collector;
  }

  public void close() {

  }

  public void nextTuple() {
    RandomGenerator random = new JDKRandomGenerator();
    String line = "search_id=" + random.nextLong() + " ad_id=" + random.nextInt() + " country=" + random.nextInt(10);
    _collector.emit(new Values(line));
  }

  public void ack(Object msgId) {

  }

  public void fail(Object msgId) {

  }

  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("line"));
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }

}
