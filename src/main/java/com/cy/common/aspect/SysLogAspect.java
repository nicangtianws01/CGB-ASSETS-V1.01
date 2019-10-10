package com.cy.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cy.common.annotation.RequiredLog;
import com.cy.common.util.IPUtils;
import com.cy.sys.entity.SysLog;
import com.cy.sys.service.SysLogService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("@annotation(com.cy.common.annotation.RequiredLog)")
	public void logPointCut() {
		
	}
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("start = "+start);
		Object result = pjp.proceed();
		long end = System.currentTimeMillis();
		System.out.println("end = "+end);
		long time = end - start;
		saveLog(pjp,time);
		return result;
	}
	private void saveLog(ProceedingJoinPoint pjp, long time) throws Exception{
		//1.获取目标类名
		Class<?> cls = pjp.getTarget().getClass();
		String clsName = cls.getName();
		
		//2.获取方法名
		//2.1.通过方法签名获取方法
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		//2.1获取方法名
		String methodName = method.getName();
		String clsMethodName = clsName+"."+methodName;
		//3.获取方法参数
		ObjectMapper om = new ObjectMapper();
		String params = om.writeValueAsString(pjp.getArgs());
		//4.获取日志注解
		Method targetMethod = cls.getMethod(methodName, signature.getParameterTypes());
		RequiredLog requiredLog = targetMethod.getAnnotation(RequiredLog.class);
		String operation = "";
		if(requiredLog != null) {
			operation = requiredLog.value();
		}
		//5.封装日志
		SysLog entity=new SysLog();
		entity.setUsername("admin");//登录使用的用户名
		entity.setOperation(operation);
		entity.setMethod(clsMethodName);//method=类全名+方法名
		entity.setParams(params);
		entity.setTime(time);
		entity.setIp(IPUtils.getIpAddr());
		entity.setCreatedTime(new Date());
		System.out.println("log.aspect:"+Thread.currentThread().getName());
		//6.保存日志
		sysLogService.saveObject(entity);
	}
}
