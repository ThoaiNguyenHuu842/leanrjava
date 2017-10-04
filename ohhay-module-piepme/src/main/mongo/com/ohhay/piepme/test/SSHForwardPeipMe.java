package com.ohhay.piepme.test;

import com.jcraft.jsch.JSch;
public class SSHForwardPeipMe {
	public static void main(String[] args) {
		String user = "thoainh";
		String password = "qbThoai387";
		String host = "169.50.15.13";
		int port = 22;
		try {
			JSch jsch = new JSch();
			com.jcraft.jsch.Session session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing Connection...");
			session.connect();
			session.setPortForwardingL(4004, "localhost", 3306);
			session.setPortForwardingL(4005, "localhost", 27017);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---xong---");
	}
}