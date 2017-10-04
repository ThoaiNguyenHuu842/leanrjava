package com.ohhay.core.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author ThoaiNH
 * create 24/11/2014
 */
public class OhhayMessageSource implements MessageSourceAware{
	private MessageSource messageSource;
	@Override
	public void setMessageSource(MessageSource messageSource) {
		// TODO Auto-generated method stub
		this.messageSource = messageSource;
	}
	public String getLocalize(String key){
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}
}
