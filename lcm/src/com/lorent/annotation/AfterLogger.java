package com.lorent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 执行方法后记录日志
 */
public @interface AfterLogger {
	//方法对应的日志信息key
	String msgKey();
}
