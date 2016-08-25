package com.linkx.storm.row.formater;

import com.linkx.storm.row.Row;
import com.linkx.storm.row.MapBasedRow;
import java.util.Map;
import org.apache.storm.shade.com.google.common.base.Joiner;
import org.apache.storm.shade.com.google.common.base.Splitter;
import org.apache.storm.shade.com.google.common.base.Strings;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */
public class KeyValueFormatter extends AbstractFormatter {

  @Override public Row format(String input) {
    if (Strings.isNullOrEmpty(input)) return null;

    try {
      Map<String, String> data = Splitter.on(" ")
          .omitEmptyStrings()
          .withKeyValueSeparator('=')
          .split(input);
      System.out.println("[size=" + data.size() + "],[keys=" + Joiner.on('|').join(data.keySet())
          + "],[values=" + Joiner.on('|').join(data.values()));
      return new MapBasedRow(data);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
