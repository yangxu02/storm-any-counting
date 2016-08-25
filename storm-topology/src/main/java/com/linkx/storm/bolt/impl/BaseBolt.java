package com.linkx.storm.bolt.impl;

import com.linkx.storm.context.BoltContext;
import com.linkx.storm.context.stream.Stream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/14.
 */
public abstract class BaseBolt extends BaseRichBolt {

  protected final BoltContext boltContext;

  public BaseBolt(BoltContext boltContext) {
    this.boltContext = boltContext;
  }

  protected OutputCollector collector;

  @Override
  public void prepare(Map conf, TopologyContext topologyContext, OutputCollector outputCollector) {
    this.collector = outputCollector;
  }

  @Override public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    Stream[] streams = boltContext.getStreams();
    for (Stream stream : streams) {
      List<String> filedList = Arrays.asList(stream.getMinors());
      filedList.add(0, stream.getMajor());
      outputFieldsDeclarer.declareStream(stream.getId(), new Fields(filedList));
    }
  }

  @Override public void execute(Tuple tuple) {
    if (handleTuple(tuple)) {
      collector.ack(tuple);
    } else {
      collector.fail(tuple);
    }
  }

  public abstract boolean handleTuple(Tuple tuple);
}
