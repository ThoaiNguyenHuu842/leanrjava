package com.ohhay.core.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author ThoaiNH
 * create 09/12/2014
 * util for resource bundle
 */
public class MVCResourceBundleUtil {
	public static String getResourceBundle(String code){
		MessageSource messageSource = (MessageSource) ApplicationHelper.findBean("messageSource");
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, null, locale);
	}
	public static Locale getCurrentLocale()
	{
		MessageSource messageSource = (MessageSource) ApplicationHelper.findBean("messageSource");
		Locale locale = LocaleContextHolder.getLocale();
		return locale;
	}
}
