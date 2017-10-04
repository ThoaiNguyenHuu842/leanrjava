package com.ohhay.core.filesutil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

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
 * create Jun 15, 2016
 * aws file upload with out web context
 */
public class AWSFileUtilsWithoutSession {
	private static Logger log = Logger.getLogger(AWSFileUtilsWithoutSession.class);
	public static String TEMPLATE_PATH = "template";
	private AmazonS3 s3;
	private String rootPath;
	private int fo100;
	private String region;
	public AWSFileUtilsWithoutSession(String rootPath, String region){

		// TODO Auto-generated constructor stub
		this.region = region;
		this.rootPath = rootPath;
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
		log.info("---AES root path:"+rootPath);
	
	}
	//current user directory
	public AWSFileUtilsWithoutSession(int fo100, String region){
		// TODO Auto-generated constructor stub
		this.fo100 = fo100;
		this.region = region;
		if(fo100 != 0)
			this.rootPath = String.valueOf(fo100);
		else
			this.rootPath = null;
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
		log.info("---AES root path:"+rootPath);
	}
	/**
	 * @param fileName
	 * @param file
	 * @return
	 */
	public int uploadObject(String fileName, File file) {
		try {
			PutObjectRequest objectRequest = new PutObjectRequest(getAwsBucket(),rootPath!=null?rootPath + "/" + fileName:fileName, file);
			log.info("--aws link:"+getImgLink()+(rootPath!=null?rootPath + "/" + fileName:fileName));
			// set public
			objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
			s3.putObject(objectRequest);
			return 1;
		} catch (AmazonServiceException ex) {
			ex.printStackTrace();
			return 0;
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
			return 0;
		}
	}
	/**
	 * @param fileName
	 * @param po100
	 * @return
	 */
	public int deleteObject(String fileName, int po100){
		try{
			String awsKey  = fo100 + "/" + fileName;
			log.info("---aws key to delete:"+awsKey);
			s3.deleteObject(getAwsBucket(),awsKey);
			return 1;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	/**
	 * @param fileName
	 * @return
	 */
	public int deleteObject(String fileName){
		try{
			String awsKey  = rootPath + "/" + fileName;
			log.info("---aws key to delete:"+awsKey);
			s3.deleteObject(getAwsBucket(),awsKey);
			return 1;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	/**
	 * @param fileName
	 * @param multipartFile
	 * @return
	 */
	public int uploadObjectMutilPart(String fileName, MultipartFile multipartFile) {
		try {
			try {
				InputStream inputStream = multipartFile.getInputStream();
				Long contentLength = Long.valueOf(IOUtils
						.toByteArray(inputStream).length);
				ObjectMetadata metadata = new ObjectMetadata();
				//byte[] resultByte = DigestUtils.md5(inputStream);
				metadata.setContentLength(contentLength);
				//phai goi 2 lan moi chay dc
				InputStream is = multipartFile.getInputStream();
				PutObjectRequest objectRequest = new PutObjectRequest(
						getAwsBucket(), rootPath + "/" + fileName, is,
						metadata);
				// set public
				objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(objectRequest);
				return 1;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		} catch (AmazonServiceException ex) {
			ex.printStackTrace();
			return 0;
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
			return 0;
		}
	}
	/**
	 * upload file to aws/fo100/filename
	 * @param fileName
	 * @param bs
	 * @return
	 */
	public int uploadObjectMutilPart(String fileName, byte[] bs) {
		try {
			try {
				ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
				Long contentLength = Long.valueOf(IOUtils
						.toByteArray(inputStream).length);
				log.info("---AWS upload file:"+getAwsBucket()+","+rootPath + "/" + fileName+","+contentLength);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(contentLength);
				//phai goi 2 lan moi chay dc
				ByteArrayInputStream is = new ByteArrayInputStream(bs);
				PutObjectRequest objectRequest = new PutObjectRequest(getAwsBucket(), rootPath + "/" + fileName, is,metadata);
				// set public
				objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(objectRequest);
				return 1;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		} catch (AmazonServiceException ex) {
			ex.printStackTrace();
			return 0;
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
			return 0;
		}
	}
	/**
	 * upload img when edit template A900, upload to imgtemplates/fa900/filename
	 * @param fileName
	 * @param bs
	 * @return
	 */
	public int uploadObjectMutilPartA900(int webId, String fileName, byte[] bs) {
		try {
			try {
				ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
				Long contentLength = Long.valueOf(IOUtils
						.toByteArray(inputStream).length);
				log.info("---AWS upload file for a900 webid:"+webId);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(contentLength);
				//phai goi 2 lan moi chay dc
				ByteArrayInputStream is = new ByteArrayInputStream(bs);
				PutObjectRequest objectRequest = new PutObjectRequest("oohhay", "imgtemplates/"+webId+"/" + fileName, is,metadata);
				// set public
				objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(objectRequest);
				return 1;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		} catch (AmazonServiceException ex) {
			ex.printStackTrace();
			return 0;
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
			return 0;
		}
	}
	/**
	 * upload img when edit template A500, upload to imgtemplates/cfa900/filename
	 * @param webId
	 * @param fileName
	 * @param bs
	 * @return
	 */
	public int uploadObjectMutilPartA500(int webId, String fileName, byte[] bs) {
		try {
			try {
				ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
				Long contentLength = Long.valueOf(IOUtils
						.toByteArray(inputStream).length);
				log.info("---AWS upload file for a500 webid:"+webId);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(contentLength);
				//phai goi 2 lan moi chay dc
				ByteArrayInputStream is = new ByteArrayInputStream(bs);
				PutObjectRequest objectRequest = new PutObjectRequest("oohhay", "imgtemplates/c"+webId+"/" + fileName, is,metadata);
				// set public
				objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(objectRequest);
				return 1;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		} catch (AmazonServiceException ex) {
			ex.printStackTrace();
			return 0;
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
			return 0;
		}
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
				log.info("---AWS upload file:"+getAwsBucket()+","+ rootPath + "/" +  subKey +","+contentLength);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(contentLength);
				//phai goi 2 lan moi chay dc
				ByteArrayInputStream is = new ByteArrayInputStream(bs);
				PutObjectRequest objectRequest = new PutObjectRequest(getAwsBucket(), rootPath + "/" + subKey, is,metadata);
				// set public
				objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(objectRequest);
				return getImgLink() + subKey;
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
	 * @return
	 */
	public String getAwsBucket()
	{
		if(region != null)
			return region.toLowerCase()+"-oohhay";
		else
			return "oohhay";
	}
	/**
	 * @return
	 */
	public String getImgLink()
	{
		if(region != null)
		{
			switch (region.trim().toLowerCase()) {
			case "as":
				return "https://d2g7dc0hcuz3eo.cloudfront.net/"+fo100+"/";
			case "au":
				return "https://d2p729ugpxtyfh.cloudfront.net/"+fo100+"/";	
			case "de":
				return "https://dwb8bma0pr0to.cloudfront.net/"+fo100+"/";
			case "eu":
				return "https://d3da30y70ds7j5.cloudfront.net/"+fo100+"/";
			case "na":
				return "https://d6c4vop3h18s9.cloudfront.net/"+fo100+"/";
			case "us":
				return "https://d3376tpt4fcj7g.cloudfront.net/"+fo100+"/";
			}
			return "https://"+region.toLowerCase()+"-oohhay.s3.amazonaws.com/"+fo100+"/";
		}
		else
			return "https://oohhay.s3.amazonaws.com/"+fo100+"/";
	}
	public int uploadObjectMutilPart(String fileName, byte[] bs, String contentType) {
		try {
			try {
				ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
				Long contentLength = Long.valueOf(IOUtils
						.toByteArray(inputStream).length);
				log.info("---AWS upload file:"+getAwsBucket()+","+rootPath + "/" + fileName+","+contentLength);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(contentType);
				metadata.setContentLength(contentLength);
				//phai goi 2 lan moi chay dc
				ByteArrayInputStream is = new ByteArrayInputStream(bs);
				PutObjectRequest objectRequest = new PutObjectRequest(getAwsBucket(), rootPath + "/" + fileName, is,metadata);
				// set public
				objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(objectRequest);
				return 1;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		} catch (AmazonServiceException ex) {
			ex.printStackTrace();
			return 0;
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
			return 0;
		}
	}
}
