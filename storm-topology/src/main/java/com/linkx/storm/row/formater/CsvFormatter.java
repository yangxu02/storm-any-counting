package com.linkx.storm.row.formater;

import com.linkx.storm.row.Row;
import com.linkx.storm.row.MapBasedRow;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.storm.shade.com.google.common.base.Preconditions;
import org.apache.storm.shade.com.google.common.base.Splitter;
import org.apache.storm.shade.com.google.common.base.Strings;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */
public class CsvFormatter extends AbstractFormatter {

  String delim = "" + '\001';
  String[] dimensions;

  public CsvFormatter(String delim, String[] dimensions) {
    this.delim = delim;
    this.dimensions = dimensions;
  }

  @Override public Row format(String input) {
    if (Strings.isNullOrEmpty(input)) return null;
    Preconditions.checkArgument(dimensions != null && 0 != dimensions.length, "no dimension info");
    Iterator<String> it = Splitter.on(delim).split(input).iterator();
    Map<String, String> data = new HashMap<>();
    for (int i = 0; it.hasNext() && i < dimensions.length; ++i) {
      data.put(dimensions[i], it.next());
    }
    if (it.hasNext()) { // additional data provided
      //logger.info("[method=loadFromCsv],[data=" + new Gson().toJson(data) + "],[left=" + it.next() + "]");
    }
    return new MapBasedRow(data);
  }

  public String getDelim() {
    return delim;
  }

  public void setDelim(String delim) {
    this.delim = delim;
  }

  public String[] getDimensions() {
    return dimensions;
  }

  public void setDimensions(String[] dimensions) {
    this.dimensions = dimensions;
  }

}
