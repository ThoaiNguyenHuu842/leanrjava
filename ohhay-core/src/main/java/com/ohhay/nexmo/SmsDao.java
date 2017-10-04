package com.ohhay.nexmo;

public interface SmsDao {
    /**
     * @param api_key
     * @param api_secret
     * @param from
     * @param to
     * @param text
     * @return
     */
    int sendMessageNexmo(String api_key, String api_secret, String from, String to, String text);
    /**
     * send SMS O!HAY to phone in US,CANADA
     * @param api_key
     * @param api_secret
     * @param from
     * @param to
     * @param text
     * @return
     */
    int sendMessageNexmo2(String api_key, String api_secret, String from, String to, String o050Code);
}
