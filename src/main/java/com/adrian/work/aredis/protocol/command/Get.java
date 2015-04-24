package com.adrian.work.aredis.protocol.command;

import com.adrian.work.aredis.protocol.RedisResponse;
import com.adrian.work.aredis.protocol.Commandable;
import com.adrian.work.aredis.protocol.builder.CommandBuilderV3;

public class Get implements Commandable {
	
	private static final String GET_STRING = "GET";
	
	private static final byte[] GET_BYTES = GET_STRING.getBytes();
	
	private byte[] key;
	
	public byte[] getKey() {
		return key;
	}

	public void setKey(byte[] key) {
		this.key = key;
	}

	public byte[] toCommand() {
		return CommandBuilderV3.getInstance().toCommand(GET_BYTES, key);
	}

	public RedisResponse handleResponse() {
		return null;
	}

}
