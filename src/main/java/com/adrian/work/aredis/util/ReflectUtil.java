package com.adrian.work.aredis.util;

public class ReflectUtil {

	public static String getRefrenceClassName(Class<?> classz, String className) {
		return classz.getName().substring(0, classz.getName().lastIndexOf(".") + 1).concat(className);
	}
}
