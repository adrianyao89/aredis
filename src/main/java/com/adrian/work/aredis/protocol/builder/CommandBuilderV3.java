package com.adrian.work.aredis.protocol.builder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CommandBuilderV3 extends AbstractCommandBuilder {
	
	private static final byte[] ARGS_LENGTH = "*".getBytes();

	private static final byte[] END = "\r\n".getBytes();
	
	private static final byte[] ARG_LENGTH = "$".getBytes();
	
	public static final CommandBuilderV3 commandProtocol = new CommandBuilderV3();
	
	private CommandBuilderV3() {
	}
	
	public static final CommandBuilderV3 getInstance() {
		return commandProtocol;
	}

	public byte[] toCommand(byte[] command, byte[]... args) {
		if (command == null) {
			throw new NullPointerException("command is null");
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			if (args != null) {
				int seq = args.length + 1;
				os.write(ARGS_LENGTH);
				os.write(String.valueOf(seq).getBytes());
				os.write(END);
				
				os.write(ARG_LENGTH);
				os.write(String.valueOf(command.length).getBytes());
				os.write(END);
				os.write(command);
				os.write(END);
				
				for (byte[] arg : args) {
					os.write(ARG_LENGTH);
					os.write(String.valueOf(arg.length).getBytes());
					os.write(END);
					os.write(arg);
					os.write(END);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return os.toByteArray();
	}
}
