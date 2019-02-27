package com.org.product.logging;


import org.aspectj.lang.annotation.Pointcut;


public class PointCuts {
	@Pointcut("within(com.org.product.*.*)")
	public void logEntryExit() {
	}

}