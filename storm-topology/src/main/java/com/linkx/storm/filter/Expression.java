package com.linkx.storm.filter;

import com.linkx.storm.row.Row;

/**
 * Created by yangxu on 1/20/15.
 */


public interface Expression {

    public boolean match(Row row);

    public String getExpr();

    public String getKey();

    public String getCacheKey(Row row);

}
