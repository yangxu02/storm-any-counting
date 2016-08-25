package com.linkx.storm.spout.scheme;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * Created by ulyx.yang@ndpmedia.com on 2016/5/30.
 */
// TODO
public class LineBasedScheme extends AbstractScheme {

	@Override public Iterable<List<Object>> deserialize(ByteBuffer byteBuffer) {
    	try {
			if (byteBuffer == null) {
				return null;
			}
			String msg = new String(byteBuffer.array(), "UTF-8");
			String[] lines = msg.split("\n");
			ArrayList<List<Object>> keys = new ArrayList<>();
			for (String line : lines) {
				//LOG.info("click-->{}", line);
				keys.add(new Values(line));
			}
			return keys;
		} catch (Exception e) {

		}
    return null;
  }

  @Override public Fields getOutputFields() {
		return new Fields("str");
  }
}
