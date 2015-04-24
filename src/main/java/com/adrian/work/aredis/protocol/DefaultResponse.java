package com.adrian.work.aredis.protocol;

public class DefaultResponse {

	private byte[] nativeResponse;
	
	private Exception e;
	
	private DefaultResponse(final byte[] nativeResponse) {
		this.nativeResponse = nativeResponse;
	}
	
	public boolean hasException() {
		return e != null;
	}
}
