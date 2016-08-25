package com.linkx.storm.spout.scheme;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.storm.spout.MultiScheme;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "line", value = LineBasedScheme.class),
})

public abstract class AbstractScheme implements MultiScheme {

}
