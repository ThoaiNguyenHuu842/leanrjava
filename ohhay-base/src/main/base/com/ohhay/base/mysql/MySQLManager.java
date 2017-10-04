package com.ohhay.base.mysql;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.constant.SpringBeanNames;
import com.ohhay.base.mysql.service.O100CentService;
import com.ohhay.base.util.BaseHelper;

/**
 * @author ThoaiNH
 * create 22/09/2015
 * manage all MySQL connection
 */
public class MySQLManager {
	private static Logger log = Logger.getLogger(MySQLManager.class);
	/**
	 * query type to access user' db
	 */
	public static final int QUERY_TYPE_IGNORE = 0;
	public static final int QUERY_TYPE_FO100 = 1;
	public static final int QUERY_TYPE_MO = 2;
	public static final int QUERY_TYPE_MAIL = 3;
	private static DriverManagerDataSource dataSourceCenter;//center db
	public static Map<Integer, String> mapFO100DB = new HashMap<Integer, String>();//store all connection of fo100
	public static Map<String, String> mapMODB = new HashMap<String, String>();//store all connection of phone
	public static Map<String, DriverManagerDataSource> mapMySQLInstance = new HashMap<String, DriverManagerDataSource>();// store all MYSQL connection
	/**
	 * @param objectID fo100, qv101 or email
	 * @param queryType
	 * @return
	 * @throws Exception
	 */
	public static DriverManagerDataSource getDBInstance(Object objectID, int queryType) throws Exception{
		log.info("---map mysql userDB:" + mapFO100DB);
		log.info("---map mySQLInstance:" + mapMySQLInstance);
		log.info("---map mysql mapMODB:" + mapMODB);
		String uri = null;
		if(queryType == QUERY_TYPE_FO100)
		{
			uri = mapFO100DB.get(objectID);
			if(uri == null)
			{
				uri = getUserUriByFo100((int)objectID);
				//save user db info to map
				mapFO100DB.put((Integer) objectID,uri);
			}
		}
		else if(queryType == QUERY_TYPE_MO)
		{
			uri = mapMODB.get(objectID);
			if(uri == null)
			{
				uri = getUserUriByMo(objectID.toString());
				//save user db info to map
				mapMODB.put(objectID.toString(),uri);
			}
		}
		else
			throw new Exception("---ERROR DB_TYPE");
		try {
			log.info("---uri:"+uri);
			String ss1[] = uri.split("#");
			//for test
			if(ss1.length < 2)
			{
				log.info("---uri invaidl:"+uri);
				uri = "webmydb01#mysql.oohhay.com;22;ohhay;Ohhay890;Ohhay";
				ss1 = uri.split("#");
			}				
			String dbName = ss1[0];
			//find mysql instance in mapMySQLInstance  webmydb01#mysql.oohhay.com;22;ohhay;Ohhay890;ohhay
			DriverManagerDataSource driverManagerDataSource = mapMySQLInstance.get(dbName);
			//open new connection if not found instance
			if (driverManagerDataSource == null) {
				log.info("---try to connect to db:" + uri);
				String ss2[] = ss1[1].split(";");
				String sqlURI = "jdbc:mysql://"+ss2[0]+"/"+ss2[4]+"?characterEncoding=UTF-8";
				driverManagerDataSource = new DriverManagerDataSource(sqlURI,ss2[2],ss2[3]);
				driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
				//save this connection to map.
				mapMySQLInstance.put(dbName, driverManagerDataSource);
			}
			log.info("---access mysql, dbName:"+dbName);
			return driverManagerDataSource;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	public static DriverManagerDataSource getDBInstance(String uri) throws Exception{
		try{
			String ss1[] = uri.split("#");
			String dbName = ss1[0];
			DriverManagerDataSource driverManagerDataSource = mapMySQLInstance.get(dbName);
			if (driverManagerDataSource == null) {
				log.info("---try to connect to db:" + uri);
				String ss2[] = ss1[1].split(";");
				String sqlURI = "jdbc:mysql://"+ss2[0]+"/"+ss2[4]+"?characterEncoding=UTF-8";
				driverManagerDataSource = new DriverManagerDataSource(sqlURI,ss2[2],ss2[3]);
				driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
				//save this connection to map.
				mapMySQLInstance.put(dbName, driverManagerDataSource);
			}
			return driverManagerDataSource;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public static DriverManagerDataSource getDBCenterInstance() throws Exception {
		dataSourceCenter = (DriverManagerDataSource) BaseHelper.context.getBean("mysqlDataSourceDBCenter");
		return dataSourceCenter;
	}
	/**
	 * get URI mongoDB web by fo100
	 * @param fo100
	 * @return
	 */
	private static String getUserUriByFo100(int fo100) {
		log.info("---find URI mysql of fo100:"+fo100);
		O100CentService centService = (O100CentService) BaseHelper.context.getBean(SpringBeanNames.SERVICE_NAME_O100CENT);
		String kq = centService.getValTabO100MySql(fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
		return kq;
	}
	/**
	 * get URI mongoDB web by ov101
	 * @param ov101
	 * @return
	 */
	private static String getUserUriByMo(String objectID) {
		log.info("---find URI mysql of objectID:"+objectID);
		String ss[] = objectID.split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
		O100CentService centService = (O100CentService) BaseHelper.context.getBean(SpringBeanNames.SERVICE_NAME_O100CENT);
		String kq = centService.getMoTabO100MySql(ss[1],ss[0],ApplicationConstant.PVLOGIN_ANONYMOUS);
		return kq;
	}
}
