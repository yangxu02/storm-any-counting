package com.linkx.storm.function.tuple;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import org.apache.storm.tuple.Tuple;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/16.
 */
public class SingleFieldTupleAccessor extends AbstractTupleAccessor {
  String field;

  @JsonCreator
  public SingleFieldTupleAccessor(@JsonProperty("field") String field) {
    this.field = field;
  }

  @JsonProperty("field")
  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  @Override public String get(Tuple tuple) {
    if (Strings.isNullOrEmpty(field)) {
      return "";
    }
    return tuple.getStringByField(field);
  }
}
