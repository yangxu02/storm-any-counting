package com.linkx.storm.context.stream;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 *
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */
public class Stream {
  // stream id
  String id;
  // major field of this stream, field can be type of string or string array
  String major;
  // minor fields of this stream, field can be only simple string
  String[] minors;

  @JsonCreator
  public Stream(String id, String major, String[] minors) {
    this.id = id;
    this.major = major;
    this.minors = minors;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  public String[] getMinors() {
    return minors;
  }

  public void setMinors(String[] minors) {
    this.minors = minors;
  }
}
