package com.ohhay.nexmo;

public interface SmsService {
	//send sms
    /**
     * @param to
     * @param text
     * @return 0: success, other value is nexmo error status code
     */
    int sendMessageNexmo(String to, String text);
    /**
     * send SMS O!HAY to phone in US,CANADA
     * @param api_key
     * @param api_secret
     * @param from
     * @param to
     * @param o050Code
     * @return
     */
    int sendMessageNexmo2(String to, String o050Code);
}
