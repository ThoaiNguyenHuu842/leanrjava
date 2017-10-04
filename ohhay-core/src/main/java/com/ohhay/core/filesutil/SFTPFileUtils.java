/*package com.ohhay.core.filesutil;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.ohhay.base.constant.PropertiesLoader;

*//**
 * @author ThoaiNH
 *
 *//*
public class SFTPFileUtils implements Serializable {
	private static Logger log = Logger.getLogger(SFTPFileUtils.class);
	// cau hinh upload server
	private String SFTPUSER;// user
	private String SFTPHOST;// host
	private int FTPPORT; // port
	private String SFTPPASS;// pass
	private String SFTPWORKINGDIR;// thu muc
	private Channel channel;
	private Session session;
	transient private ChannelSftp channelSftp;

	public SFTPFileUtils() {
		// TODO Auto-generated constructor stub
		log.info("---conecting to server sftp");
		PropertiesLoader prop = new PropertiesLoader(PropertiesLoader.CONFIG);
		try {
			this.SFTPUSER = prop.getProperty("SFTPUSER");
			this.SFTPHOST = prop.getProperty("SFTPHOST");
			log.info("--------------SFTPUSER: " + SFTPUSER);
			this.FTPPORT = Integer.parseInt(prop.getProperty("SFTPPORT"));
			this.SFTPPASS = prop.getProperty("SFTPPASS");
			this.SFTPWORKINGDIR = prop.getProperty("SFTPWORKINGDIR");
			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER, SFTPHOST, FTPPORT);
			session.setPassword(SFTPPASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(SFTPWORKINGDIR);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int uploadFileToServer(String fileName, InputStream file) {
		try {
			log.info("---filename upload:" + fileName);
			channelSftp.put(file, fileName);
			log.info("New file created!");
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public int removeFileOnServer(String fileName) {
		try {
			channelSftp.rm(fileName);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public InputStream getFileInputStream(String fileName) {
		InputStream stream = null;
		try {
			stream = channelSftp.get(fileName);
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
	}
}
*/