package com.linkx.storm.row;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.storm.shade.com.google.common.base.Splitter;

/**
 * Created by ulyx.yang@ndpmedia.com on 2014/11/10.
 */
public class MapBasedRow extends Row {
    private final static Log logger = LogFactory.getLog(MapBasedRow.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static TypeReference jsonType = new TypeReference<HashMap<String, String>>() {
    };
    private Map<String, String> data;

    public MapBasedRow(Map<String, String> data) {
        this.data = data;
    }

    //
    //public MapBasedRow(String input, String format) {
    //
    //    if (!Strings.isNullOrEmpty(input)) {
    //        if (logger.isDebugEnabled()) {
    //            logger.debug("[input=" + input + "]");
    //        }
    //        if ("csv".equalsIgnoreCase(format)) {
    //            loadFromCsv(input);
    //        } else {
    //            loadFromJson(input);
    //        }
    //
    //        Map<String, String> refs = new HashMap<>();
    //        if (null != refs && !refs.isEmpty()) {
    //            data.putAll(refs);
    //        }
    //    }
    //}
    //

    /**
     * load row from csv formatted data, currently not support delimiter in field itself
     *
     * @param input
     * @param context
     */
    private void loadFromCsv(String input) {

//        Preconditions.checkArgument(!Strings.isNullOrEmpty(input), "input must not be empty or null");
//        Preconditions.checkNotNull(context, "context must not be null");

        //String delim = context.getInput().getDelim();
        //String[] dimensions = context.getInput().getDimensions();

        String delim = "";
        String[] dimensions = new String[] {};
        Preconditions.checkArgument(dimensions != null && 0 != dimensions.length, "no dimension info");
        Iterator<String> it = Splitter.on(delim).split(input).iterator();
        data = new HashMap<>();
        int i;
        for (i = 0; it.hasNext() && i < dimensions.length; ++i) {
            data.put(dimensions[i], it.next());
        }
        if (it.hasNext()) { // additional data provided
            //logger.info("[method=loadFromCsv],[data=" + new Gson().toJson(data) + "],[left=" + it.next() + "]");
        }

//        Preconditions.checkArgument(i == dimensions.length, "not enough data for given dimensions("
//                + i + "!=" + dimensions.length + ")@" + delim + ":" + input);
    }

    /**
     * load row from json data
     *
     * @param input
     * @param context
     */
    private void loadFromJson(String input) {

//        Preconditions.checkArgument(!Strings.isNullOrEmpty(input), "input must not be empty or null");
//        Preconditions.checkNotNull(context, "context must not be null");

        try {
            data = objectMapper.readValue(input, jsonType);
            logger.info("[size=" + data.size() + "],[keys=" + Joiner.on('|').join(data.keySet())
                    + "],[values=" + Joiner.on('|').join(data.values()));
        } catch (IOException e) {
            logger.error(input, e);
        }

        if (null == data) data = Collections.EMPTY_MAP;

//        Preconditions.checkArgument(data != null, "read json data failed:" + input);

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

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
