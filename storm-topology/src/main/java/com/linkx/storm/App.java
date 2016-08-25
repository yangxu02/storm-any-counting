package com.linkx.storm;

import java.io.IOException;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */
public class App {
  public static void main(String[] args) throws IOException, AuthorizationException {
    Topology topology = new Topology().setUp();
    try {
      topology.start();
    } catch (AlreadyAliveException e) {
      e.printStackTrace();
    } catch (InvalidTopologyException e) {
      e.printStackTrace();
    }
  }
}
