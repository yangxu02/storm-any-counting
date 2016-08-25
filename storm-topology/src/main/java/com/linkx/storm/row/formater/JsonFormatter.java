package com.linkx.storm.row.formater;

import com.linkx.storm.row.Row;
import com.linkx.storm.row.MapBasedRow;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.htrace.fasterxml.jackson.core.type.TypeReference;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.apache.storm.shade.com.google.common.base.Joiner;
import org.apache.storm.shade.com.google.common.base.Strings;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */
public class JsonFormatter extends AbstractFormatter {

  private final static ObjectMapper objectMapper = new ObjectMapper();
  private final static TypeReference jsonType = new TypeReference<HashMap<String, String>>() {
  };
  @Override public Row format(String input) {
    if (Strings.isNullOrEmpty(input)) return null;

    try {
      Map<String, String> data = objectMapper.readValue(input, jsonType);
      System.out.println("[size=" + data.size() + "],[keys=" + Joiner.on('|').join(data.keySet())
          + "],[values=" + Joiner.on('|').join(data.values()));
      return new MapBasedRow(data);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
