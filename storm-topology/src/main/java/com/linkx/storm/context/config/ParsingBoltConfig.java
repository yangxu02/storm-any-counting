package com.linkx.storm.context.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linkx.storm.row.formater.AbstractFormatter;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */
public class ParsingBoltConfig extends AbstractConfig {

  AbstractFormatter formatter;

  @JsonCreator
  public ParsingBoltConfig(@JsonProperty("formatter") AbstractFormatter formatter) {
    this.formatter = formatter;
  }

  @JsonProperty("formatter")
  public AbstractFormatter getFormatter() {
    return formatter;
  }

}
