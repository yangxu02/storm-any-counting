package com.linkx.storm.context.config;

import com.linkx.storm.function.tuple.AbstractTupleAccessor;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */
public class AccumulatingMetricConfig {
  String name;
  AbstractTupleAccessor l1_key;
  AbstractTupleAccessor l2_key;
  AccumulatingType type;
  AbstractTupleAccessor acc_val;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AccumulatingType getType() {
    return type;
  }

  public void setType(AccumulatingType type) {
    this.type = type;
  }

  public AbstractTupleAccessor getL1_key() {
    return l1_key;
  }

  public void setL1_key(AbstractTupleAccessor l1_key) {
    this.l1_key = l1_key;
  }

  public AbstractTupleAccessor getL2_key() {
    return l2_key;
  }

  public void setL2_key(AbstractTupleAccessor l2_key) {
    this.l2_key = l2_key;
  }

  public AbstractTupleAccessor getAcc_val() {
    return acc_val;
  }

  public void setAcc_val(AbstractTupleAccessor acc_val) {
    this.acc_val = acc_val;
  }
}

