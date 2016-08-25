package com.linkx.storm.context;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */
public class Context {
  String id;
  int acker_num;
  int worker_num;
  int batch_emit_interval;
  SpoutContext[] spouts;
  BoltContext[] bolts;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getAcker_num() {
    return acker_num;
  }

  public void setAcker_num(int acker_num) {
    this.acker_num = acker_num;
  }

  public int getWorker_num() {
    return worker_num;
  }

  public void setWorker_num(int worker_num) {
    this.worker_num = worker_num;
  }

  public int getBatch_emit_interval() {
    return batch_emit_interval;
  }

  public void setBatch_emit_interval(int batch_emit_interval) {
    this.batch_emit_interval = batch_emit_interval;
  }

  public SpoutContext[] getSpouts() {
    return spouts;
  }

  public void setSpouts(SpoutContext[] spouts) {
    this.spouts = spouts;
  }

  public BoltContext[] getBolts() {
    return bolts;
  }

  public void setBolts(BoltContext[] bolts) {
    this.bolts = bolts;
  }
}
