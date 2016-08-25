package com.linkx.storm.utils;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/31.
 */
public class Numbers {
  public static int tryParseInt(String val) {
    try {
      return Integer.parseInt(val);
    } catch (Exception e) {
    }
    return 0;
  }

  public static long tryParseLong(String val) {
    try {
      return Long.parseLong(val);
    } catch (Exception e) {
    }
    return 0;
  }
}
