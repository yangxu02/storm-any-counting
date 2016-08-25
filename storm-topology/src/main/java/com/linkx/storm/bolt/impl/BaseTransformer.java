package com.linkx.storm.bolt.impl;

import org.apache.storm.tuple.Tuple;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/14.
 */
public interface BaseTransformer {
  Tuple transform(Tuple tuple);
}
