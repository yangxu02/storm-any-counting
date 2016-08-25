package com.linkx.storm.row;
/**
 * Created by yangxu on 1/20/15.
 */

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class SimpleMapBasedRow extends Row {

    private static Logger logger = Logger.getLogger(SimpleMapBasedRow.class);

    private final Map<String, String> data = new HashMap<>();

    public void set(String key, String val) {
        data.put(key, val);
    }

      @Override
    public String getDimensionVal(String dim) {
        if (null == data || data.isEmpty())
            return "";
        return Strings.nullToEmpty(data.get(dim));
    }

    @Override
    public long getLongVal(String dim) {
        if (null == data || data.isEmpty())
            return 0;
        try {
            return Long.parseLong(data.get(dim));
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public Collection<String> getValues() {
        return data.values();
    }

    @Override
    public List<String> getValues(String[] dims) {
        if (null == dims || 0 == dims.length) {
            return Lists.newArrayList(data.values());
        }
        List<String> values = new ArrayList<>(dims.length);
        for (int i = 0; i < dims.length; ++i) {
            values.add(data.get(dims[i]));
        }
        return values;
    }

}
