package com.linkx.storm.bolt.impl;

import java.util.Collection;
import java.util.List;
import org.apache.storm.task.IOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.tuple.Tuple;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/15.
 */
public class TestBaseOutputCollector implements IOutputCollector {

  @Override public List<Integer> emit(String s, Collection<Tuple> collection, List<Object> list) {
    return null;
  }

  @Override
  public void emitDirect(int i, String s, Collection<Tuple> collection, List<Object> list) {

  }

  @Override public void ack(Tuple tuple) {

  }

  @Override public void fail(Tuple tuple) {

  }

  @Override public void resetTimeout(Tuple tuple) {

  }

  @Override public void reportError(Throwable throwable) {

  }
}
