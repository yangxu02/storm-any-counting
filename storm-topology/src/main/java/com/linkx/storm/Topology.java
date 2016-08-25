package com.linkx.storm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.linkx.storm.context.BoltContext;
import com.linkx.storm.context.Context;
import com.linkx.storm.context.SpoutContext;
import com.linkx.storm.context.group.AbstractGrouping;
import com.linkx.storm.guice.ContextModule;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.BoltDeclarer;
import org.apache.storm.topology.TopologyBuilder;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */
public class Topology {
  private Context ctx;

  public Topology setUp() {

    Injector injector = Guice.createInjector(new ContextModule());
    ctx = injector.getInstance(Context.class);

    Preconditions.checkNotNull(ctx, "context must not be null");

    return this;
  }

  public void start()
      throws JsonProcessingException, AlreadyAliveException, InvalidTopologyException,
      AuthorizationException {

    System.out.println(new ObjectMapper().writeValueAsString(ctx));

    createTopology();

  }

  void createTopology()
      throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {

    // topology configuration
    Config config = new Config();
    config.put(Config.TOPOLOGY_TRIDENT_BATCH_EMIT_INTERVAL_MILLIS,
        ctx.getBatch_emit_interval());
    config.setNumAckers(ctx.getAcker_num());
    config.setNumWorkers(ctx.getWorker_num());
    TopologyBuilder builder = new TopologyBuilder();

    // set up spouts
    SpoutContext[] spoutContexts = ctx.getSpouts();
    for (SpoutContext spoutContext : spoutContexts) {
      builder.setSpout(spoutContext.getId(),
          spoutContext.createSpout(),
          spoutContext.getParallel());
    }

    // set up bolts
    BoltContext[] boltContexts = ctx.getBolts();
    for (BoltContext boltContext : boltContexts) {
      System.out.println("boltContext:id=" + boltContext.getId());
      System.out.println("boltContext:parallel=" + boltContext.getParallel());
      System.out.println("boltContext:instance=" + boltContext.createBolt());
      BoltDeclarer
          boltDeclarer = builder.setBolt(boltContext.getId(), boltContext.createBolt(), boltContext.getParallel());
      AbstractGrouping[] groupings = boltContext.getGroupings();
      for (AbstractGrouping grouping : groupings) {
        grouping.attach(boltDeclarer);
      }
    }
    StormSubmitter.submitTopology(ctx.getId(), config, builder.createTopology());
  }

  public void cleanUp() {

  }

}
