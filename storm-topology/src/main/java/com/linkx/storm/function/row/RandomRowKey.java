package com.linkx.storm.function.row;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.linkx.storm.row.Row;
import java.util.UUID;

/**
 * Created by ulyx.yang@ndpmedia.com on 2014/11/12.
 */
public class RandomRowKey extends RowKeyExtractionFn {
    @JsonCreator
    public RandomRowKey(
    ) {
    }

    @Override
    public String apply(Row row) {
        return UUID.randomUUID().toString();
    }

    @Override
    public String apply(Row row, String postfix) {
        return UUID.randomUUID().toString();
    }

    @Override
    public String apply(String prefix, Row row) {
        return UUID.randomUUID().toString();
    }
}
