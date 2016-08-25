package com.linkx.storm.row.formater;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.linkx.storm.row.Row;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "csv", value = CsvFormatter.class),
    @JsonSubTypes.Type(name = "json", value = JsonFormatter.class),
    @JsonSubTypes.Type(name = "kv", value = KeyValueFormatter.class),
    @JsonSubTypes.Type(name = "tag-kv", value = TaggedKeyValueFormatter.class),
})

public abstract class AbstractFormatter {
  public abstract Row format(String input);
}
