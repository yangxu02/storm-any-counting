package com.linkx.storm.guice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.inject.Provider;
import com.linkx.storm.context.Context;
import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */
public class ContextProvider implements Provider<Context> {
  final static Logger logger = Logger.getLogger(ContextProvider.class);

  @Override public Context get() {
    String cfgFile = System.getProperty("storm.conf", "topology.prop");
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream stream = classLoader.getResourceAsStream(cfgFile);
    Context context = null;
    try {
      byte[] buffer = new byte[4096];
      stream.read(buffer);
      System.out.println(new String(buffer));
      //context = new ObjectMapper().readValue(stream, Context.class);
      context = new ObjectMapper().readValue(buffer, Context.class);
    } catch (JsonMappingException e) {
      e.printStackTrace();
      logger.error("", e);
    } catch (JsonParseException e) {
      e.printStackTrace();
      logger.error("", e);
    } catch (IOException e) {
      e.printStackTrace();
      logger.error("", e);
    }

    Preconditions.checkNotNull(context, "Mapping Context loading error with file:" + cfgFile);

    logger.info("successfully loading Mapping Context from file:" + cfgFile);

    return context;
  }
}
