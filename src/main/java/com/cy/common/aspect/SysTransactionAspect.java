package com.cy.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.cy.common.exception.ServiceException;

@Aspect
@Component
public class SysTransactionAspect {
	@Pointcut("execution(* com.cy.sys.service.impl..save*(..))")
	public void transPointService() {}
	
	@Around("transPointService()")
	public Object aroundTrans(ProceedingJoinPoint pjp) {
		System.out.println("start");
		Object result = null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ServiceException("服务器维护中...");
		}
		System.out.println("end");
		return result;
	}
}
