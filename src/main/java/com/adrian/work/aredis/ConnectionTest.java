package com.adrian.work.aredis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import org.junit.Test;

import com.adrian.work.aredis.protocol.builder.CommandBuilderV3;

public class ConnectionTest {
	
	public void set() throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.215.131", 6379);
		
		OutputStream os = socket.getOutputStream();
		
		InputStream is = socket.getInputStream();
		
		os.write("*3\r\n$3\r\nSET\r\n$5\r\nmykey\r\n$7\r\nmyvalue\r\n".getBytes());
		
		byte[] bytes = new byte[1024];
		int index = is.read(bytes);
		
		System.out.println(new String(bytes, 0, index));
		
		os.close();
		is.close();
		socket.close();
	}
	
	public void get() throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.215.131", 6379);
		
		OutputStream os = socket.getOutputStream();
		
		InputStream is = socket.getInputStream();
		
		os.write("*2\r\n$3\r\nGET\r\n$5\r\nmykey\r\n".getBytes());
		
		byte[] bytes = new byte[1024];
		int index = is.read(bytes);
		
		System.out.println(new String(bytes, 0, index));
		
		os.close();
		is.close();
		socket.close();
	}

//	@Test
	public void get1() throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.215.131", 6379);
		
		OutputStream os = socket.getOutputStream();
		
		InputStream is = socket.getInputStream();
		
		os.write(CommandBuilderV3.getInstance().toCommand("GET".getBytes(), "mykey".getBytes()));
		
		byte[] bytes = new byte[1024];
		int index = is.read(bytes);
		
		System.out.println(new String(bytes, 0, index));
		
		os.close();
		is.close();
		socket.close();
	}
	
//	@Test
	public void set1() throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.215.131", 6379);
		
		OutputStream os = socket.getOutputStream();
		
		InputStream is = socket.getInputStream();
		
		os.write(CommandBuilderV3.getInstance().toCommand("SET".getBytes(), "mykey".getBytes(), new Date().toGMTString().getBytes()));
		
		byte[] bytes = new byte[1024];
		int index = is.read(bytes);
		
		System.out.println(new String(bytes, 0, index));
		
		os.close();
		is.close();
		socket.close();
	}
	
	@Test
	public void ping() throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.215.131", 6379);
		
		OutputStream os = socket.getOutputStream();
		
		InputStream is = socket.getInputStream();
		
		os.write(CommandBuilderV3.getInstance().toCommand("PING".getBytes()));
		
		byte[] bytes = new byte[1024];
		int index = is.read(bytes);
		
		System.out.println(new String(bytes, 0, index));
		
		os.close();
		is.close();
		socket.close();
	}
	
}
