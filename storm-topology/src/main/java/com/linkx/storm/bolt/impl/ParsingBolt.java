package com.linkx.storm.bolt.impl;

import com.google.common.base.Strings;
import com.linkx.storm.context.BoltContext;
import com.linkx.storm.context.stream.Stream;
import com.linkx.storm.row.Row;
import com.linkx.storm.row.formater.AbstractFormatter;
import com.linkx.storm.bolt.field.Field;
import com.linkx.storm.bolt.field.FieldType;
import com.linkx.storm.context.config.ParsingBoltConfig;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/31.
 */
public class ParsingBolt extends BaseBolt {

  public static Logger logger = LoggerFactory.getLogger(ParsingBolt.class);

  public ParsingBolt(BoltContext boltContext) {
    super(boltContext);
  }

  /**
   * 目前每个stream仅支持拆分单个字段
   * @param inputTuple
   */
  @Override public boolean handleTuple(Tuple inputTuple) {
    ParsingBoltConfig cfg = (ParsingBoltConfig) boltContext.getConfig();
    AbstractFormatter formatter = cfg.getFormatter();
    String source = inputTuple.getSourceComponent();
    String inputData = inputTuple.getString(0);
    Row row = formatter.format(inputData);

    Field[] fields = boltContext.getFields();
    for (Field field : fields) {
      field.setRaw_data(row.getDimensionVal(field.getInput_name()));
    }

    Stream[] streams = boltContext.getStreams();
    for (int k = 0; k < streams.length; ++k) {
      Stream stream = streams[k];
      String majorFieldName = stream.getMajor();
      String[] minorFieldNames = stream.getMinors();
      Field majorField = getField(fields, majorFieldName);
      if (null == majorField) {
        logger.error("field with name " + majorFieldName + " no defined");
        continue;
      }

      Object[] majorValues;
      if (majorField.getType() == FieldType.ARRAY) {
         majorValues = majorField.values();
        if (null == majorValues) {
          logger.error("get major field value failed with name " + majorFieldName);
          continue;
        }
      } else {
        majorValues = new Object[] {majorField.value()};
      }

      Object[] minorValues = new Object[minorFieldNames.length];
      for (int i = 0; i < minorFieldNames.length; ++i) {
        Field field = getField(fields, minorFieldNames[i]);
        if (null == field) {
          logger.error("field with name " + minorFieldNames[i] + " no defined");
          continue;
        }
        minorValues[i] = field.value();
      }

      // split to multiple records
      for (int i = 0; i < majorValues.length; ++i) {
        Values values = new Values(minorValues);
        values.add(0, majorValues[i]);
        collector.emit(stream.getId(), values);
      }
    }
    return true;
  }

  Field getField(Field[] fields, String outputName) {
    if (Strings.isNullOrEmpty(outputName)
        || null == fields || 0 == fields.length) {
      return null;
    }

    for (Field field : fields) {
      if (outputName.equals(field.getOutput_name())) {
        return field;
      }
    }

    return null;
  }

}
