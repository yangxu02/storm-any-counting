package com.linkx.storm.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.linkx.storm.context.Context;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */
public class ContextModule extends AbstractModule {
  @Override protected void configure() {
    bind(Context.class).toProvider(ContextProvider.class).in(Singleton.class);
  }
}
