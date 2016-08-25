package com.linkx.storm.bolt.field;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linkx.storm.function.dimension.DimensionExtractionFn;
import com.linkx.storm.utils.Numbers;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */
public class Field {

  String input_name;
  String output_name;
  FieldType type;
  String delim;

  String name;
  DimensionExtractionFn fn;

  @JsonIgnore
  String raw_data;

  @JsonCreator
  public Field(String input_name, String output_name, FieldType type, String delim, String name,
      DimensionExtractionFn fn) {
    this.input_name = input_name;
    this.output_name = output_name;
    this.type = type;
    this.delim = delim;
    this.name = name;
    this.fn = fn;
  }

  public String getName() {
    return name;
  }

  public DimensionExtractionFn getFn() {
    return fn;
  }

  public String getInput_name() {
    return input_name;
  }

  public void setInput_name(String input_name) {
    this.input_name = input_name;
  }

  public String getOutput_name() {
    return output_name;
  }

  public void setOutput_name(String output_name) {
    this.output_name = output_name;
  }

  public FieldType getType() {
    return type;
  }

  public void setType(FieldType type) {
    this.type = type;
  }

  public String getDelim() {
    return delim;
  }

  public void setDelim(String delim) {
    this.delim = delim;
  }

  public void setRaw_data(String raw_data) {
    this.raw_data = raw_data;
  }

  public Object value() {
    switch (type) {
      case ARRAY:
      case STR:
        return raw_data;
      case INT:
        return Numbers.tryParseInt(raw_data);
      case LONG:
        return Numbers.tryParseLong(raw_data);
      default:
        return raw_data;
    }
  }

   public Object[] values() {
    switch (type) {
      case ARRAY:
        return raw_data.split(delim);
      default:
        //("field is not array type");
        return null;
    }
  }

}
