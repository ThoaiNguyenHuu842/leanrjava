package com.ohhay.base.mongo;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.constant.SpringBeanNames;
import com.ohhay.base.entities.N100CentPMG;
import com.ohhay.base.mysql.service.O100CentService;
import com.ohhay.base.util.AESUtils;
import com.ohhay.base.util.BaseHelper;

/**
 * @author ThoaiNH
 * create 18/09/2015 
 * manage all mongoDB connection
 * update 25/04/2016 conection to shop onine DB
 * update 01/12/2016 conection to piepme center
 */
public class MongoDBManager {
	private static Logger log = Logger.getLogger(MongoDBManager.class);
	public static Map<Integer, String> mapFO100TopicDB = new HashMap<Integer, String>();//store all connection of fo100 = topicDb
	public static Map<Integer, String> mapFO100ShopDB = new HashMap<Integer, String>();//store all connection of fo100 = shopDb
	public static Map<Integer, String> mapFO100OhhayDB = new HashMap<Integer, String>();//store all connection of fo100 = webDb
	public static Map<Integer, String> mapFO100PiepmeDB = new HashMap<Integer, String>();//store all connection of fo100 = PiepmeDB
	public static Map<String, MongoOperations> mapMongoInstance = new HashMap<String, MongoOperations>();// store all mongoDB connection
	public static Map<String, MongoOperations> mapMongoInstancePiepmeCent = new HashMap<String, MongoOperations>();// store all mongoDB connection
	/**
	 * get MongoOperation by URI
	 * @param uri ex:webmydb01#mysql.oohhay.com;22;ohhay;AASE;ohhay
	 * @return
	 * @throws Exception 
	 */
	public static MongoOperations getDBInstance(int fo100, int type) throws Exception {
		//log.info("---map userDB:"+mapUserDB);
		//log.info("---map MongoInstance:"+mapMongoInstance);
		String uri = null;
		if(type == ApplicationConstant.DB_TYPE_OHHAY)
		{
			uri = mapFO100OhhayDB.get(fo100);
			if(uri == null){
				uri = getUserURIMongoWeb(fo100);
				//save user db info to map
				mapFO100OhhayDB.put(fo100,uri);
			}
		}
		else if(type == ApplicationConstant.DB_TYPE_TOPIC){
			uri = mapFO100TopicDB.get(fo100);
			if(uri == null){
				uri = getUserURIMongoTopic(fo100);
				//save user db info to map
				mapFO100TopicDB.put(fo100,uri);
			}
		}
		else if(type == ApplicationConstant.DB_TYPE_SHOP){
			uri = mapFO100ShopDB.get(fo100);
			if(uri == null){
				uri = getUserURIMongoShop(fo100);
				//save user db info to map
				mapFO100ShopDB.put(fo100,uri);
			}
		}
		else if(type == ApplicationConstant.DB_TYPE_PIEPME){
			uri = mapFO100PiepmeDB.get(fo100);
			if(uri == null){
				uri = getUserURIMongoPiepmeOld(fo100);
				//save user db info to map
				mapFO100PiepmeDB.put(fo100,uri);
			}
		}
		else
			throw new java.lang.Exception("Database type  must be ApplicationConstant.OPERATION_TOPIC, ApplicationConstant.DB_TYPE_SHOP, ApplicationConstant.DB_TYPE_PIEPME or ApplicationConstant.OPERATION_OHHAY");
		try {
			MongoOperations mongoOperations = null;
			if(type == ApplicationConstant.DB_TYPE_PIEPME){
				String ss1[] = uri.split("#");
				String dbName = ss1[0];
				//find mongoDB instance in mapMongoInstance
				mongoOperations = mapMongoInstance.get(dbName + fo100);
				//open new connection if not found instance
				if (mongoOperations == null) {
					log.info("---try to connect to db:" + uri);
					String ss2[] = ss1[1].split(";");
					Mongo mongo = new Mongo(ss2[0], Integer.parseInt(ss2[1]));
					UserCredentials credentials = new UserCredentials("user" + fo100,"user" + fo100);
					mongoOperations = new MongoTemplate(mongo, ss2[4], credentials);
					mongo.getMongoOptions().setConnectionsPerHost(250);
					//save this connection to map
					mapMongoInstance.put(dbName + fo100, mongoOperations);
				}
				log.info("---access mongoDB type:"+type+", dbName:"+dbName +", uri:"+uri);
			}
			else {
				String ss1[] = uri.split("#");
				String dbName = ss1[0];
				//find mongoDB instance in mapMongoInstance
				mongoOperations = mapMongoInstance.get(dbName);
				//open new connection if not found instance
				if (mongoOperations == null) {
					log.info("---try to connect to db:" + uri);
					String ss2[] = ss1[1].split(";");
					Mongo mongo = new Mongo(ss2[0], Integer.parseInt(ss2[1]));
					UserCredentials credentials = new UserCredentials(ss2[2],ss2[3]);
					mongoOperations = new MongoTemplate(mongo, ss2[4], credentials);
					//save this connection to map
					mapMongoInstance.put(dbName, mongoOperations);
				}
				log.info("---access mongoDB type:"+type+", dbName:"+dbName +", uri:"+uri);
			}
			return mongoOperations;
		} catch (Exception ex) {
			log.info("---failed to connect to db:" + uri);
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static MongoOperations getDBOhhayCenterInstance(int type, int... fo100) throws Exception {
		if(type == ApplicationConstant.DB_TYPE_OHHAY)
		{
			log.info("---access mongoDB web center");
			return getDBWebCenter();
		}
		else if(type == ApplicationConstant.DB_TYPE_TOPIC)
		{
			log.info("---access mongoDB topic center");
			return getDBTopicCenter();
		}
		else if(type == ApplicationConstant.DB_TYPE_PIEPME)
		{
			return getDBInstance(775, type);
//			log.info("---access mongoDB piepme center");
//			if(fo100[0] == 0)
//				throw new java.lang.Exception("Database PiepMe center must have a country code to identify");
//			else
//			{
//				//get country code of fo100
//				String countryCode = "vn";
//				return getDBPiepmeCenter(countryCode);
//			}
		}
		else
			throw new java.lang.Exception("Database type  must be ApplicationConstant.OPERATION_TOPIC, ApplicationConstant.OPERATION_OHHAY or ApplicationConstant.OPERATION_PIEPME");
	}
	/**
	 * get URI mongoDB web by fo100
	 * @param fo100
	 * @return
	 */
	private static String getUserURIMongoWeb(int fo100) {
		//call function mysql to get uri
		log.info("---find URI mongo web of fo100:"+fo100);
		O100CentService centService = (O100CentService) BaseHelper.context.getBean(SpringBeanNames.SERVICE_NAME_O100CENT);
		return centService.getValTabO100Web(fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
	}
	/**
	 * get URI mongoDB topic by fo100
	 * @param fo100
	 * @return
	 */
	private static String getUserURIMongoTopic(int fo100) {
		//call function mysql to get uri
		log.info("---find URI mongo topic of fo100:"+fo100);
		O100CentService centService = (O100CentService) BaseHelper.context.getBean(SpringBeanNames.SERVICE_NAME_O100CENT);
		return centService.getValTabO100Topic(fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
	}
	/**
	 * @param fo100
	 * @return
	 */
	private static String getUserURIMongoShop(int fo100) {
		//call function mysql to get uri
		log.info("---find URI mongo shop of fo100:"+fo100);
		O100CentService centService = (O100CentService) BaseHelper.context.getBean(SpringBeanNames.SERVICE_NAME_O100CENT);
		return centService.getValTabO100Shop(fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
	}
	/**
	 * get URI by DB center location
	 * update 27/12/2016
	 * @param fo100
	 * @return
	 */
	public static String getUserURIMongoPiepme(int fo100) {
		//call function mysql to get uri
		log.info("---find URI mongo piepme of fo100:"+fo100);
		/*O100CentService centService = (O100CentService) BaseHelper.context.getBean(SpringBeanNames.SERVICE_NAME_O100CENT);
		return centService.getValTabO100Piepme(fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);*/
		try {
			MongoOperations mongoOperations = getDBOhhayCenterInstance(ApplicationConstant.DB_TYPE_PIEPME, fo100);
			Query query2 = new Query();
			query2.addCriteria(Criteria.where("FO100").is(fo100));
			query2.addCriteria(Criteria.where("DATE_DELETE").exists(false));
			N100CentPMG n100CentPMG = mongoOperations.findOne(query2, N100CentPMG.class);
			if(n100CentPMG != null)
				return AESUtils.decrypt(n100CentPMG.getNv121());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param fo100
	 * @return
	 */
	public static String getUserURIMongoPiepmeOld(int fo100) {
		//call function mysql to get uri
		log.info("---find URI mongo piepme of fo100:"+fo100);
		O100CentService centService = (O100CentService) BaseHelper.context.getBean(SpringBeanNames.SERVICE_NAME_O100CENT);
		return centService.getValTabO100Piepme(fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
	}
	/**
	 * @return
	 */
	private static MongoOperations getDBWebCenter() {
		MongoOperations mongoOperation = (MongoOperations) BaseHelper.context.getBean("mongoOhhayTemplateDBCenter");
		return mongoOperation;
	}
	/**
	 * @return
	 */
	private static MongoOperations getDBTopicCenter() {
		MongoOperations mongoOperation = (MongoOperations) BaseHelper.context.getBean("mongoTopicTemplateDBCenter");
		return mongoOperation;
	}
	/**
	 * connect to DB center of PIEPME in specific location
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private static MongoOperations getDBPiepmeCenter(String countryCode) throws NumberFormatException, Exception {
		String uri = PiepmeDBManager.getURI(countryCode);
		//find mongoDB instance in mapMongoInstance
		MongoOperations mongoOperations = mapMongoInstancePiepmeCent.get(countryCode);
		//open new connection if not found instance
		if (mongoOperations == null) {
			String ss1[] = uri.split("#");
			String dbName = ss1[0];
			log.info("---try to connect to db:" + uri);
			String ss2[] = ss1[1].split(";");
			Mongo mongo = new Mongo(ss2[0], Integer.parseInt(ss2[1]));
			UserCredentials credentials = new UserCredentials(ss2[2],ss2[3]);
			mongoOperations = new MongoTemplate(mongo, ss2[4], credentials);
			//save this connection to map
			mapMongoInstancePiepmeCent.put(dbName, mongoOperations);
		}
		return mongoOperations;
	}
	/**
	 * @param fo100A
	 * @param fo100B
	 * @return
	 */
	public static boolean hasTheSamePiepmeServer(int fo100A, int fo100B){
		String urlA = mapFO100PiepmeDB.get(fo100A);
		if(urlA == null)
			urlA = getUserURIMongoPiepme(fo100A);
		String urlB = mapFO100PiepmeDB.get(fo100B);
		if(urlB == null)
			urlB = getUserURIMongoPiepme(fo100B);
		return urlA.equalsIgnoreCase(urlB);
	}
}
