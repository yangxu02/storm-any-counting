package com.linkx.storm.function.tuple;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linkx.storm.function.value.ValueJoinFn;
import org.apache.storm.shade.com.google.common.base.Joiner;
import org.apache.storm.tuple.Tuple;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/16.
 */
public class MultiFieldsTupleAccessor extends AbstractTupleAccessor {
  String[] fields;
  ValueJoinFn joiner;

  @JsonCreator
  public MultiFieldsTupleAccessor(
      @JsonProperty("fields") String[] fields,
      @JsonProperty("joiner") ValueJoinFn joiner) {
    this.fields = fields;
    this.joiner = joiner;
  }

  @JsonProperty("fields")
  public String[] getFields() {
    return fields;
  }

  public void setFields(String[] fields) {
    this.fields = fields;
  }

  @JsonProperty("joiner")
  public ValueJoinFn getJoiner() {
    return joiner;
  }

  public void setJoiner(ValueJoinFn joiner) {
    this.joiner = joiner;
  }

  @Override public String get(Tuple tuple) {
    if (null == fields || 0 == fields.length) {
      return "";
    }
    if (1 == fields.length) {
      return tuple.getStringByField(fields[0]);
    }

    String[] keySlices = new String[fields.length];
    int i = 0;
    for (String keyField : fields) {
      keySlices[i++] = tuple.getStringByField(keyField);
    }

    if (null == joiner) {
      return Joiner.on('|').join(keySlices);
    }

    return joiner.apply(keySlices);
  }
}
