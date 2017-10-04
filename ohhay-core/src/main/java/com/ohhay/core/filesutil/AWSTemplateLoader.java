package com.ohhay.core.filesutil;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

/*
 * dua tat ca cac thu muc co ten name trong thu muc path len amazon, la con thu muc TEMPLATE_PATH.
 * path cua file co root la name.
 * muon dua teamplate moi thi phai bo vao thu muc html/landing
 */
/**
 * @author ThoaiNH
 *
 */
public class AWSTemplateLoader {
	private static Logger log = Logger.getLogger(AWSTemplateLoader.class);
	private String path;
	private File dir;
	private String name;
	private String awsRootPath;
	public AWSTemplateLoader(String path, String name, String awsRootPath) {
		super();
		this.path = path;
		this.name = name;
		this.awsRootPath = awsRootPath;
		dir = new File(path);
	}

	public void run() {
		load(dir);
		log.info("--------------------------------------------FINISHED--------------------------------------------");
	}

	private void load(File dir) {
		try {

			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					log.info("Directory:" + file.getCanonicalPath());
					load(file);
				} else {
					String fileNameOrginal = file.getCanonicalPath();
					if (fileNameOrginal.indexOf(name) > 0) {
						String key = fileNameOrginal.substring(
								fileNameOrginal.indexOf(name),
								fileNameOrginal.length()).replace("\\", "/");
						log.info("---index:" + key);
						// save to aws
						AWSFileUtilsWithoutSession awsFileUtils = new AWSFileUtilsWithoutSession(awsRootPath, "AS");
						awsFileUtils.deleteObject(key);
						awsFileUtils.uploadObject(key, file);
					} else
						log.info("***ERROR READ FILE:"
								+ fileNameOrginal);
					log.info("---File:" + file.getCanonicalPath());
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
