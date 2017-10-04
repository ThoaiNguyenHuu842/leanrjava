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
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.entities.Q100;
/**
 * @author ThoaiNH
 * create Oct 28, 2014
 * aws file upload
 */
public class AWSFileUtils {
	private static Logger log = Logger.getLogger(AWSFileUtils.class);
	public static String TEMPLATE_PATH = "template";
	private AmazonS3 s3;
	private String rootPath;
	//current user directory
	public AWSFileUtils(){
		// TODO Auto-generated constructor stub
		Q100 q100 = (Q100)AuthenticationHelper.getUserLogin();
		this.rootPath = String.valueOf(q100.getM900mg().getId());
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(ApplicationConstant.AWS_KEY, ApplicationConstant.AWS_KEY_SECRECT);
		s3 = new AmazonS3Client(awsCreds);
		Region usWest2 = null;
		if(q100.getM900mg().getRegion() == null)
			usWest2 = Region.getRegion(Regions.AP_SOUTHEAST_1);
		else 
			switch (q100.getM900mg().getRegion()) {
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
			Q100 q100 = AuthenticationHelper.getUserLogin();
			PutObjectRequest objectRequest = new PutObjectRequest(q100.getM900mg().getAwsBucket(),
					rootPath + "/" + fileName, file);
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
			Q100 q100 = AuthenticationHelper.getUserLogin();
			String awsKey  = po100 + "/" + fileName;
			log.info("---aws key to delete:"+awsKey);
			s3.deleteObject(q100.getM900mg().getAwsBucket(),awsKey);
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
			Q100 q100 = AuthenticationHelper.getUserLogin();
			String awsKey  = rootPath + "/" + fileName;
			log.info("---aws key to delete:"+awsKey);
			s3.deleteObject(q100.getM900mg().getAwsBucket(),awsKey);
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
				Q100 q100 = AuthenticationHelper.getUserLogin();
				InputStream inputStream = multipartFile.getInputStream();
				Long contentLength = Long.valueOf(IOUtils
						.toByteArray(inputStream).length);
				ObjectMetadata metadata = new ObjectMetadata();
				//byte[] resultByte = DigestUtils.md5(inputStream);
				metadata.setContentLength(contentLength);
				//phai goi 2 lan moi chay dc
				InputStream is = multipartFile.getInputStream();
				PutObjectRequest objectRequest = new PutObjectRequest(
						q100.getM900mg().getAwsBucket(), rootPath + "/" + fileName, is,
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
				Q100 q100 = AuthenticationHelper.getUserLogin();
				ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
				Long contentLength = Long.valueOf(IOUtils
						.toByteArray(inputStream).length);
				log.info("---AWS upload file:"+q100.getM900mg().getAwsBucket()+","+rootPath + "/" + fileName+","+contentLength);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(contentLength);
				//phai goi 2 lan moi chay dc
				ByteArrayInputStream is = new ByteArrayInputStream(bs);
				PutObjectRequest objectRequest = new PutObjectRequest(q100.getM900mg().getAwsBucket(), rootPath + "/" + fileName, is,metadata);
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
				Q100 q100 = AuthenticationHelper.getUserLogin();
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
				Q100 q100 = AuthenticationHelper.getUserLogin();
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
				Q100 q100 = AuthenticationHelper.getUserLogin();
				ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
				Long contentLength = Long.valueOf(IOUtils
						.toByteArray(inputStream).length);
				log.info("---AWS upload file:"+q100.getM900mg().getAwsBucket()+","+ rootPath + "/" +  subKey +","+contentLength);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(contentLength);
				//phai goi 2 lan moi chay dc
				ByteArrayInputStream is = new ByteArrayInputStream(bs);
				PutObjectRequest objectRequest = new PutObjectRequest(q100.getM900mg().getAwsBucket(), rootPath + "/" + subKey, is,metadata);
				// set public
				objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(objectRequest);
				return q100.getM900mg().getImgLinkCloudFront() + subKey;
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
}
