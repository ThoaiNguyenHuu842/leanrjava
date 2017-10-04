package com.ohhay.core.filesutil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.ohhay.base.constant.ApplicationConstant;
/**
 * @author ThoaiNH
 * create Oct 28, 2014
 * aws file upload
 */
public class EvoAWSFileUtils {
	private static Logger log = Logger.getLogger(EvoAWSFileUtils.class);
	public static String TEMPLATE_PATH = "template";
	private AmazonS3 s3;
	private int fo100;
	private String region;
	//current user directory
	public EvoAWSFileUtils(String region, int fo100){
		// TODO Auto-generated constructor stub
		this.fo100 = fo100;
		this.region = region;
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(ApplicationConstant.AWS_KEY, ApplicationConstant.AWS_KEY_SECRECT);
		s3 = new AmazonS3Client(awsCreds);
		Region usWest2 = null;
		if(region == null)
			usWest2 = Region.getRegion(Regions.AP_SOUTHEAST_1);
		else 
			switch (region) {
			case "DE":
				usWest2 = Region.getRegion(Regions.EU_CENTRAL_1);
				break;
			case "AS":
				usWest2 = Region.getRegion(Regions.AP_SOUTHEAST_1);
				break;
			case "EU":
				usWest2 = Region.getRegion(Regions.EU_WEST_1);
				break;
			case "NA":
				usWest2 = Region.getRegion(Regions.SA_EAST_1);
				break;
			case "US":
				usWest2 = Region.getRegion(Regions.US_WEST_1);
				break;
			default:
				try {
					throw new java.lang.Exception("---ERROR REGION_TYPE");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		s3.setRegion(usWest2);
	}
	/**
	 * create Oct 28, 2015
	 * using for web evo function
	 * upload img to image album
	 * if webid = 0 -> upload to fo100/fileName
	 * else upload to fo100/webid/filename
	 * @param webId: id of web evo
	 * @param fileName
	 * @param bs
	 * @return null if upload fail, return full url of image uploaded
	 */
	public String uploadObjectMutilPartImgAlbum(int webId, String fileName, byte[] bs) {
		try {
			try {
				String subKey = fileName;
				if(webId > 0)
					subKey = webId + "/" + fileName;
				ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
				Long contentLength = Long.valueOf(IOUtils
						.toByteArray(inputStream).length);
				log.info("---AWS upload file:"+region+","+ fo100 + "/" +  subKey +","+contentLength);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(contentLength);
				//phai goi 2 lan moi chay dc
				ByteArrayInputStream is = new ByteArrayInputStream(bs);
				PutObjectRequest objectRequest = new PutObjectRequest(this.getAwsBucket(), fo100 + "/" + subKey, is,metadata);
				// set public
				objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(objectRequest);
				return this.getImgLink() + subKey;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		} catch (AmazonServiceException ex) {
			ex.printStackTrace();
			return null;
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
			return null;
		}
	}
	/**
	 * create 15/04/2016
	 * update free image
	 * @param fileName
	 * @param bs
	 * @return
	 */
	public String uploadObjectMutilPart(String fileName, byte[] bs) {
		try {
			try {
				String subKey = ((fo100!=0) ? fo100+"/" : "")+fileName;
				ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
				Long contentLength = Long.valueOf(IOUtils
						.toByteArray(inputStream).length);
				log.info("---AWS upload file:"+fileName +","+contentLength);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(contentLength);
				//phai goi 2 lan moi chay dc
				ByteArrayInputStream is = new ByteArrayInputStream(bs);
				PutObjectRequest objectRequest = new PutObjectRequest(this.getAwsBucket(),subKey, is,metadata);
				// set public
				objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(objectRequest);
				log.info("Update LOAD: ");
				return this.getImgLink() + URLEncoder.encode(fileName,"UTF-8");
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		} catch (AmazonServiceException ex) {
			ex.printStackTrace();
			return null;
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
			return null;
		}
	}
	public String getAwsBucket()
	{
		if(region != null)
			return region.toLowerCase()+"-oohhay";
		else
			return "oohhay";
	}
	/**
	 * update 01/07/2016
	 * change s3 link to cloundfront link
	 * @return
	 */
	public String getImgLink()
	{
		if(region != null)
		{
			switch (region.trim().toLowerCase()) {
			case "as":
				return "https://d2g7dc0hcuz3eo.cloudfront.net/"+((fo100!=0) ? fo100+"/" : "");
			case "au":
				return "https://d2p729ugpxtyfh.cloudfront.net/"+((fo100!=0) ? fo100+"/" : "");	
			case "de":
				return "https://dwb8bma0pr0to.cloudfront.net/"+((fo100!=0) ? fo100+"/" : "");
			case "eu":
				return "https://d3da30y70ds7j5.cloudfront.net/"+((fo100!=0) ? fo100+"/" : "");
			case "na":
				return "https://d6c4vop3h18s9.cloudfront.net/"+((fo100!=0) ? fo100+"/" : "");
			case "us":
				return "https://d3376tpt4fcj7g.cloudfront.net/"+((fo100!=0) ? fo100+"/" : "");
			}
			return "https://"+region.toLowerCase()+"-oohhay.s3.amazonaws.com/"+fo100+"/";
		}
		else
			return "https://oohhay.s3.amazonaws.com/"+fo100+"/";
	}
}
