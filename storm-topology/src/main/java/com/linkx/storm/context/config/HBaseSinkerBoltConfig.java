package com.linkx.storm.context.config;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/14.
 */
public class HBaseSinkerBoltConfig extends AbstractConfig {
  int flush_interval;
  int batch_size;
  String table;

  public int getFlush_interval() {
    return flush_interval;
  }

  public int getBatch_size() {
    return batch_size;
  }

  public String getTable() {
    return table;
  }

}
