package com.linkx.storm.bolt.impl;

import com.linkx.storm.context.BoltContext;
import com.linkx.storm.context.config.AccumulatingMetricConfig;
import com.linkx.storm.context.stream.Stream;
import com.linkx.storm.context.config.AccumulatingBoltConfig;
import com.linkx.storm.context.config.AccumulatingType;
import com.linkx.storm.metric.AccumulateMetrics;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/16.
 */
public class AccumulatingBolt extends BaseBolt {

  public static Logger logger = LoggerFactory.getLogger(ParsingBolt.class);

  AccumulateMetrics[] metrics = null;

  public AccumulatingBolt(BoltContext boltContext) {
    super(boltContext);
  }

  public void registerMetrics(BoltContext ctx) {

  }

  @Override public boolean handleTuple(Tuple tuple) {
    AccumulatingBoltConfig conf = (AccumulatingBoltConfig) boltContext.getConfig();
    AccumulatingMetricConfig[] metricConfigs = conf.getMetrics();

    updateMetrics(tuple, metricConfigs);

    if (!shouldEmit()) {
      return true;
    }

    Stream[] streams = boltContext.getStreams();

    emitMetrics(metricConfigs, streams);

    return true;
  }

  // TODO
  boolean shouldEmit() {
    return true;
  }

  void emitMetrics(AccumulatingMetricConfig[] metricConfigs, Stream[] streams) {
    for (Stream stream : streams) {
      String major = stream.getMajor();
      int index = getMetricIndex(major, metricConfigs);
      if (-1 == index) {
        continue;
      }
      collector.emit(stream.getId(), new Values(metrics[index].getValueAndReset()));
    }
  }

  int getMetricIndex(String field, AccumulatingMetricConfig[] metricConfigs) {
    for (int i = 0; i < metricConfigs.length; ++i) {
      if (field.equals(metricConfigs[i].getName())) {
        return i;
      }
    }
    return -1;
  }

  void updateMetrics(Tuple tuple, AccumulatingMetricConfig[] metricConfigs) {
    if (null == metricConfigs || 0 == metricConfigs.length) {
      return;
    }
    if (null == metrics) {
      metrics = new AccumulateMetrics[metricConfigs.length];
      for (int i = 0; i < metrics.length; ++i) {
        metrics[i] = new AccumulateMetrics();
      }
    }

    int i = 0;
    for (AccumulatingMetricConfig cfg : metricConfigs) {
      String l1Key = cfg.getL1_key().get(tuple);
      String l2Key = cfg.getL2_key().get(tuple);
      metrics[i++].scope(l1Key, l2Key).incrBy(getAccumulatingValue(tuple, cfg));
    }
  }

  long getAccumulatingValue(Tuple tuple, AccumulatingMetricConfig conf) {
    if (conf.getType() == AccumulatingType.INC) {
      return 1;
    }
    return Long.parseLong(conf.getAcc_val().get(tuple));
  }

}
