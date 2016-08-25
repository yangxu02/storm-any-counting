package com.linkx.storm.bolt.impl;

import com.linkx.storm.bolt.field.Field;
import com.linkx.storm.bolt.field.FieldType;
import com.linkx.storm.context.BoltContext;
import com.linkx.storm.context.config.ParsingBoltConfig;
import com.linkx.storm.context.stream.Stream;
import com.linkx.storm.function.dimension.EchoDimExtractionFn;
import com.linkx.storm.row.formater.CsvFormatter;
import java.util.Collection;
import java.util.List;
import org.apache.storm.shade.com.google.common.base.Joiner;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.tuple.Tuple;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * ParsingBolt Tester.
 *
 * @author ulyx.yang@ndpmedia.com
 * @since 06/15/2016
 * @version 1.0
 */
public class ParsingBoltTest {


  @Before
  public void before() throws Exception {
  }

  @After
  public void after() throws Exception {
  }

  /**
   *
   * Method: prepare(Map conf, TopologyContext topologyContext, OutputCollector outputCollector)
   *
   */
  @Test
  public void testPrepare() throws Exception {
    //TODO: Test goes here...
  }

  /**
   *
   * Method: execute(Tuple inputTuple)
   *
   */
  @Test
  public void testExecute() throws Exception {

    BoltContext ctx = new BoltContext();
    Field[] fields = new Field[] {
        new Field("ccode", "country", FieldType.STR, "", "", new EchoDimExtractionFn()),
        new Field("slot_id", "slot", FieldType.INT, "", "", new EchoDimExtractionFn()),
        new Field("ad_id", "ad", FieldType.ARRAY, ",", "", new EchoDimExtractionFn()),
        new Field("log_time", "timestamp", FieldType.STR, "", "", new EchoDimExtractionFn()),
    };

    Stream[] streams = new Stream[] {
        new Stream("stream:slot-country", "slot", new String[]{"country", "timestamp"}),
        new Stream("stream:ad-country", "ad", new String[]{"country", "timestamp"}),
        new Stream("stream:ad", "ad", new String[]{"timestamp"}),
    };

    ParsingBoltConfig config = new ParsingBoltConfig(new CsvFormatter("" + '\001', new String[] {"log_time", "ccode", "slot_id", "ad_id", "udid"} ));

    ctx.setConfig(config);
    ctx.setFields(fields);
    ctx.setStreams(streams);

    ParsingBolt bolt = new ParsingBolt(ctx);
    OutputCollector collector = new OutputCollector(new TestBaseOutputCollector() {
      @Override
      public List<Integer> emit(String s, Collection<Tuple> collection, List<Object> list) {
        for (Object obj : list) {
          System.out.println("stream=" + s + ", tuple=" + obj);
        }
        return null;
      }
    });

    bolt.prepare(null, null, collector);

    Tuple tuple = new TestBaseTuple() {
      @Override public String getString(int i) {
        return Joiner.on('\001').join("2016-06-27 00:00:00", "CN", "46", "123", "user123");
      }
    };
    bolt.execute(tuple);

    tuple = new TestBaseTuple() {
      @Override public String getString(int i) {
        return Joiner.on('\001').join("2016-06-27 00:00:00", "CN", "46", "123,789", "user123");
      }
    };
    bolt.execute(tuple);

  }

  /**
   *
   * Method: declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer)
   *
   */
  @Test
  public void testDeclareOutputFields() throws Exception {
    //TODO: Test goes here...
  }

  /**
   *
   * Method: getField(Field[] fields, String outputName)
   *
   */
  @Test
  public void testGetField() throws Exception {
    //TODO: Test goes here...
  }


} 
