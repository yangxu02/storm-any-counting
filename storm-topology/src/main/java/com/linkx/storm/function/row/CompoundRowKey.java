package com.linkx.storm.function.row;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import com.linkx.storm.bolt.field.Field;
import com.linkx.storm.row.Row;
import java.util.UUID;

/**
 * Created by ulyx.yang@ndpmedia.com on 2014/11/12.
 */
public class CompoundRowKey extends RowKeyExtractionFn {
    Field primary;
    Field[] dims;
    char connector;

    @JsonCreator
    public CompoundRowKey(
            @JsonProperty("primary") Field primary,
            @JsonProperty("dims") Field[] dims,
            @JsonProperty("connector") char connector
    ) {
        this.primary = primary;
        this.dims = dims;
        this.connector = connector;
    }

    @JsonProperty("primary")
    public Field getPrimary() {
        return primary;
    }

    @JsonProperty("dims")
    public Field[] getDims() {
        return dims;
    }

    @JsonProperty("connector")
    public char getConnector() {
        return connector;
    }

    @Override
    public String apply(Row row) {
        if (null == row) return "";
        String key = "";
        if (null != dims && 0 != dims.length) {
            key = dims[0].getFn().apply(row, dims[0].getName());
            for (int i = 1; i < dims.length; ++i) {
                key += connector;
                key += dims[i].getFn().apply(row, dims[i].getName());
            }
        }
//        String primaryKey = row.getDimensionVal(primary);
        String primaryKey = primary.getFn().apply(row, primary.getName());
        if (Strings.isNullOrEmpty(primaryKey)) {
            primaryKey = UUID.randomUUID().toString();
        }
        if (!Strings.isNullOrEmpty(key)) {
            return key + connector + primaryKey;
        }
        return primaryKey;
    }

    @Override
    public String apply(Row row, String postfix) {
        String base = apply(row);
        if (Strings.isNullOrEmpty(base)) return "";
        return base + connector + postfix;
    }

    @Override
    public String apply(String prefix, Row row) {
        String base = apply(row);
        if (Strings.isNullOrEmpty(base)) return "";
        return prefix + connector + row;
    }
}
