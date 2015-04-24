package com.adrian.work.aredis.protocol.builder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import com.adrian.work.aredis.commom.Version;
import com.adrian.work.aredis.exception.AredisRuntimeException;
import com.adrian.work.aredis.protocol.CommandProtocol;
import com.adrian.work.aredis.util.ReflectUtil;

public abstract class AbstractCommandBuilder implements CommandProtocol {
	
	private static final ConcurrentHashMap<Version, CommandProtocol> commandProtocolMap = new ConcurrentHashMap<Version, CommandProtocol>(10);
	
	private static final String BUILDER_PREFIX = "CommandBuilder";
	
	public static CommandProtocol getInstance() {
		return getInstance(Version.V3);
	}
	
	public static CommandProtocol getInstance(Version version) {
		
		if (commandProtocolMap.contains(version)) {
			return commandProtocolMap.get(version);
		}
		
		CommandProtocol commandProtocol = null;
		try {
			String builderClassName = ReflectUtil.getRefrenceClassName(AbstractCommandBuilder.class, BUILDER_PREFIX + version.name());
			Class<?> builderClass = Class.forName(builderClassName);
			Method getInstanceMethod = builderClass.getMethod("getInstance");
			getInstanceMethod.setAccessible(true);
			Object builder = getInstanceMethod.invoke(builderClass);
			
			if (builder instanceof CommandProtocol) {
				commandProtocol = (CommandProtocol) builder;
				commandProtocolMap.put(version, commandProtocol);
			}
		} catch (ClassNotFoundException e) {
			throw new AredisRuntimeException("不支持的版本");
		} catch (NoSuchMethodException e) {
			throw new AredisRuntimeException("不支持的版本");
		} catch (SecurityException e) {
			throw new AredisRuntimeException("不支持的版本");
		} catch (IllegalAccessException e) {
			throw new AredisRuntimeException("不支持的版本");
		} catch (IllegalArgumentException e) {
			throw new AredisRuntimeException("不支持的版本");
		} catch (InvocationTargetException e) {
			throw new AredisRuntimeException("不支持的版本");
		}
		
		return commandProtocol;
	}
}
