package com.pingan.ph.cis.appbusiness.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<?> classType) throws BeansException {
		return (T) applicationContext.getBean(classType);
	}

	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}
}
