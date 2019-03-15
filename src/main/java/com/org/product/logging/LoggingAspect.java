package com.org.product.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.org.product.exception.ProductNotFoundException;



@Configuration
@Aspect
public class LoggingAspect {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	
//	@Around("com.org.product.logging.PointCuts.logEntryExit()")
//	public void logEntryExitAdvice(ProceedingJoinPoint proceedJoinPoint) throws Throwable
//	{
//		LOG.info("Entered Class--- {}----method-----{}----with request arguments -- {}", proceedJoinPoint.getSignature().getDeclaringType(),proceedJoinPoint.getSignature().getName(),proceedJoinPoint.getArgs());
//		proceedJoinPoint.proceed();
//		LOG.info("Exited Class--- {}----method-----{}----with response arguments -- {}", proceedJoinPoint.getSignature().getDeclaringType(),proceedJoinPoint.getSignature().getName(),proceedJoinPoint.getArgs());
//	}
//	
	
	
	@Before("com.org.product.logging.PointCuts.logEntryExit()")
	public void entryAdvice(JoinPoint joinPoint) {
		LOG.info("Entered Class--- {}----method-----{}----with request arguments -- {}", joinPoint.getSignature().getDeclaringType(),joinPoint.getSignature().getName(),joinPoint.getArgs());
	}
	@After("com.org.product.logging.PointCuts.logEntryExit()")
	public void exitAdvice(JoinPoint joinPoint) {
		LOG.info("Exited Class--- {}----method-----{}----with response arguments -- {}", joinPoint.getSignature().getDeclaringType(),joinPoint.getSignature().getName(),joinPoint.getArgs());
	}
	
//	@AfterThrowing(pointcut = "com.org.product.logging.PointCuts.logEntryExit()", throwing = "ex")
//	public void logAfterException(ProductNotFoundException ex) throws Throwable {
//		LOG.info("Product not found in DB");
//	}

}
