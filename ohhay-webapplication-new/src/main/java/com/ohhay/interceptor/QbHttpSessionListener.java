package com.ohhay.interceptor;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.utils.SessionManager;

public class QbHttpSessionListener implements HttpSessionListener {
	private static Logger log = Logger.getLogger(QbHttpSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		log.info("---sessionCreated");
		/*String sessionId = se.getSession().getId();
		Q100WebService.mapAllUser.put(sessionId, 0);
		log.info("----add session for new user");*/
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		log.info("----remove session");
		SessionManager.remove(se.getSession().getId());
	}
}
