package com.adrian.work.aredis.protocol;

public interface Commandable {

	byte[] toCommand();
	
	RedisResponse handleResponse();

}
