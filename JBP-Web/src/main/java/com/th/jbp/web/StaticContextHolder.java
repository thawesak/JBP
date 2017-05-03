package com.th.jbp.web;

import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Service;

@Service
public class StaticContextHolder implements BeanFactoryAware {
	private static final Logger LOGGER = Logger.getLogger("com.th.jbp.web.StaticContextHolder");
	public static BeanFactory CONTEXT;

	public StaticContextHolder() {
	}

	public static Object getBean(String s) throws BeansException {
		return CONTEXT.getBean(s);
	}

	public static <T> T getBean(String s, Class<T> tClass) throws BeansException {
		return CONTEXT.getBean(s, tClass);
	}

	public static <T> T getBean(Class<T> tClass) throws BeansException {
		return CONTEXT.getBean(tClass);
	}

	public static Object getBean(String s, Object... objects) throws BeansException {
		return CONTEXT.getBean(s, objects);
	}

	public boolean containsBean(String s) {
		return false; // I don't need it
	}

	public boolean isSingleton(String s) throws NoSuchBeanDefinitionException {
		return false; // I don't need it
	}

	public boolean isPrototype(String s) throws NoSuchBeanDefinitionException {
		return false; // I don't need it
	}

	@Override
	public void setBeanFactory(BeanFactory applicationContext) throws BeansException {
		LOGGER.info("StaticContextHolder CONTEXT is created.");
		CONTEXT = applicationContext;
	}
}