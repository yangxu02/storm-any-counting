package com.linkx.storm.bolt.impl.hbase;

import com.linkx.storm.bolt.impl.BaseBolt;
import com.linkx.storm.context.BoltContext;
import com.linkx.storm.context.config.HBaseSinkerBoltConfig;
import com.linkx.storm.bolt.impl.BaseSinker;
import java.util.Map;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/14.
 */
public class HBaseSinkerBolt extends BaseBolt implements BaseSinker  {

  private static Logger logger = LoggerFactory.getLogger(HBaseSinkerBolt.class);


  private final HBaseSinkerBoltConfig conf;

  public HBaseSinkerBolt(BoltContext boltContext) {
    super(boltContext);
    conf = (HBaseSinkerBoltConfig)boltContext.getConfig();
  }

  @Override public void sink(Tuple tuple) {
  }

  @Override
  public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {


  }

  @Override public void execute(Tuple tuple) {

  }

  @Override public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

  }

  @Override public boolean handleTuple(Tuple tuple) {
    return false;
  }
}
