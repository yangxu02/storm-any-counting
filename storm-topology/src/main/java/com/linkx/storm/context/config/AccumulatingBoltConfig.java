package com.linkx.storm.context.config;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */
public class AccumulatingBoltConfig extends AbstractConfig {

  AccumulatingMetricConfig[] metrics;

  public AccumulatingMetricConfig[] getMetrics() {
    return metrics;
  }

  public void setMetrics(AccumulatingMetricConfig[] metrics) {
    this.metrics = metrics;
  }
}

