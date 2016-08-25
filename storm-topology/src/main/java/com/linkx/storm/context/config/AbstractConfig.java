package com.linkx.storm.context.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.storm.kafka.SpoutConfig;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "kafka", value = KafkaSpoutConfig.class),
    @JsonSubTypes.Type(name = "parser", value = ParsingBoltConfig.class),
    @JsonSubTypes.Type(name = "accum", value = AccumulatingBoltConfig.class),
})

public abstract class AbstractConfig {

  public SpoutConfig get() { return null; };

}
