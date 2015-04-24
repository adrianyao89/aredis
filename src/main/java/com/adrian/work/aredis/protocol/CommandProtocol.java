package com.adrian.work.aredis.protocol;

public interface CommandProtocol {
	
	byte[] toCommand(byte[] command, byte[]... args);
	
}
