package com.linkx.storm.bolt.impl;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * AccumulatingBolt Tester.
 *
 * @author ulyx.yang@ndpmedia.com
 * @since 06/16/2016
 * @version 1.0
 */
public class AccumulatingBoltTest {

  @Before
  public void before() throws Exception {
  }

  @After
  public void after() throws Exception {
  }

  /**
   *
   * Method: registerMetrics(BoltContext ctx)
   *
   */
  @Test
  public void testRegisterMetrics() throws Exception {
    //TODO: Test goes here...
  }

  /**
   *
   * Method: handleTuple(Tuple tuple)
   *
   */
  @Test
  public void testHandleTuple() throws Exception {
  }

  /**
   *
   * Method: getKeyValue(Tuple tuple, AccumulatingMetricConfig conf)
   *
   */
  @Test
  public void testGetKeyValue() throws Exception {
    TestBaseTuple tuple = new TestBaseTuple();
    Map<String, Object> values = new HashMap<>();
    values.put("slot", 123);
    values.put("country", "CN");
    values.put("count", 3);
    tuple.addValues(values);

  }

  /**
   *
   * Method: getAccumulatingValue(Tuple tuple, AccumulatingMetricConfig conf)
   *
   */
  @Test
  public void testGetAccumulatingValue() throws Exception {
  }


} 
