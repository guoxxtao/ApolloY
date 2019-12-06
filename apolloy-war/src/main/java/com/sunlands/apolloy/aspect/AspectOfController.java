package com.sunlands.apolloy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * 统一打印接口日志信息
 * 1、接口名
 * 2、入参
 * 3、执行时间
 * User: guotao
 * Date: 2019/12/4
 * Time: 14:31
 */
@Aspect
@Component
public class AspectOfController {

	private static final Logger logger = LoggerFactory.getLogger(AspectOfController.class);

	@Around("execution(* com.sunlands.apolloy.controller..*.*(..))")
	public Object printControllerInfoLog(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.nanoTime();
		//获取接口api方法
		Signature s = joinPoint.getSignature();
		//获取接口入参
		Object result = joinPoint.proceed();
		long runTime = (System.nanoTime() - startTime) / 1000000;
		try {
			logger.info("Controller info ------ {}, reqDto = {}, runTime = {}ms", s.toString(), joinPoint.getArgs(), runTime);
		} catch (Exception e) {
			logger.info("Controller info ------ {}, runTime = {}ms", s.toString(), runTime);
		} finally {
			return result;
		}
	}
}
