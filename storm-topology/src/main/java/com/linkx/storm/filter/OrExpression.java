package com.linkx.storm.filter;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.linkx.storm.row.Row;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.storm.shade.com.google.common.base.Splitter;

/**
 * Created by yangxu on 1/20/15.
 */

public class OrExpression implements Expression {
    String expr;
    List<Expression> expressions = new ArrayList<>();

    OrExpression(String expr) {
        this.expr = expr;
        Iterator<String> it = Splitter.on("||").omitEmptyStrings()
                .trimResults().split(expr).iterator();
        while (it.hasNext()) {
            expressions.add(new SimpleExpression(it.next()));
        }
    }

    @Override
    public boolean match(Row row) {
        for (Expression expression : expressions) {
            if (expression.match(row)) return true;
        }
        return false;
    }

    @Override
    public String getCacheKey(Row row) {
        StringBuilder builder = new StringBuilder();
        for (Expression expression : expressions) {
            builder.append("||");
            builder.append(expression.getCacheKey(row));
        }

        return builder.substring(2);
    }

    @Override
    public String getExpr() {
        return expr;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("expr", expr)
                .add("expressions", Joiner.on(',').useForNull("").join(expressions))
                .toString();
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
