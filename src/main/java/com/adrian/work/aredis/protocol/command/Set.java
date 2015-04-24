package com.adrian.work.aredis.protocol.command;

import com.adrian.work.aredis.protocol.RedisResponse;
import com.adrian.work.aredis.protocol.Commandable;
import com.adrian.work.aredis.protocol.builder.CommandBuilderV3;

public class Set implements Commandable {
	
	private static final String SET_STRING = "SET";
	
	private static final byte[] SET_BYTES = SET_STRING.getBytes();
	
	private byte[] key;
	
	private byte[] value;
	
	public Set(byte[] key, byte[] value) {
		this.key = key;
		this.value = value;
	}
	
	public byte[] getKey() {
		return key;
	}

	public void setKey(byte[] key) {
		this.key = key;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	public byte[] toCommand() {
		return CommandBuilderV3.getInstance().toCommand(SET_BYTES, key, value);
	}

	public RedisResponse handleResponse() {
		return null;
	}

}
