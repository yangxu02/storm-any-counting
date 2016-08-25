package com.linkx.storm.function.tuple;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.storm.tuple.Tuple;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/16.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "multi", value = MultiFieldsTupleAccessor.class),
    @JsonSubTypes.Type(name = "single", value = SingleFieldTupleAccessor.class),
    @JsonSubTypes.Type(name = "table", value = TableLookingTupleAccessor.class),
})

public abstract class AbstractTupleAccessor {
  public abstract String get(Tuple tuple);
}
