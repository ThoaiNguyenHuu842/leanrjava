package com.ohhay.other.mongo.service;

import com.ohhay.core.criteria.WebSendMailCriteria;

/**
 * @author ThoaiNH
 * date create 18/03/2015
 */
public interface M150MGEmailService {
	/**
	 * @param fo100: fo100 of sender user, = 0 in web app (get from session), != 0 in app
	 * @param listMails: ex: thoainguyen842@gmail.com, sdsdi@gmail.com
	 * @param fm150: topicid
	 * @return -3: user is not enter smtp mail, -2: email not invaild, return 0: has error, return 1: success
	 */
	int send(int fo100, String [] listMails, int fm150);
	/**
	 * @param fo100
	 * @param fm150
	 * @return
	 */
	String getTopicMailHtml(int fo100, int fm150);
	/**
	 * send mail cho web form element
	 * @param webSendMailCriteria
	 * @return -1: chinh chu chua setup email nhan, -2: chinh chu chua setup smtp email, -3: loi khac, 1: thanh cong, 0: that bai
	 */
	int sendWebStmp(WebSendMailCriteria webSendMailCriteria);
}
