package com.linkx.storm.metric;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.storm.metric.api.CountMetric;
import org.apache.storm.metric.api.IMetric;
import org.apache.storm.metric.api.MultiCountMetric;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/16.
 */
public class AccumulateMetrics implements IMetric {
  Map<String, MultiCountMetric> value = new HashMap<>();

  public CountMetric scope(String l1Key, String l2Key) {
    MultiCountMetric mc = this.value.get(l1Key);
    if (null == mc) {
      mc = new MultiCountMetric();
      this.value.put(l1Key, mc);
    }
    return mc.scope(l2Key);
  }

  public Object getValueAndReset() {
    Map<String, MultiCountMetric> ret = value;
    this.value = new HashMap<>();
    return ret;
  }

}
