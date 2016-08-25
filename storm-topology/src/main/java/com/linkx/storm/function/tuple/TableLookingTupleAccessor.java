package com.linkx.storm.function.tuple;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.apache.storm.shade.com.google.common.base.Strings;
import org.apache.storm.tuple.Tuple;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/6/16.
 */
public class TableLookingTupleAccessor extends AbstractTupleAccessor {
  String field;
  Map<String, String> table;

  @JsonCreator
  public TableLookingTupleAccessor(
      @JsonProperty("field") String field,
      @JsonProperty("table") Map<String, String> table) {
    this.field = field;
    this.table = table;
  }

  @JsonProperty("field")
  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  @JsonProperty("table")
  public Map<String, String> getTable() {
    return table;
  }

  public void setTable(Map<String, String> table) {
    this.table = table;
  }

  @Override public String get(Tuple tuple) {

    String key = "";
    if (!Strings.isNullOrEmpty(field)) {
      key = tuple.getStringByField(field);
    }

    if (null == table) {
      return key;
    }

    return Strings.nullToEmpty(table.get(key));
  }
}
