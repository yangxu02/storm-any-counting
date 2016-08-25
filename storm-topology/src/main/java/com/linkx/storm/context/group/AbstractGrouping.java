package com.linkx.storm.context.group;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.storm.topology.BoltDeclarer;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/27.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "shuffle", value = ShuffleGrouping.class),
    @JsonSubTypes.Type(name = "all", value = AllGrouping.class),
    @JsonSubTypes.Type(name = "field", value = FieldsGrouping.class),
})
public abstract class AbstractGrouping {

  public abstract BoltDeclarer attach(BoltDeclarer boltDeclarer);

}
