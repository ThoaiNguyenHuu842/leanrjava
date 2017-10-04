package com.ohhay.web.core.entities.mongo.webshort;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebShortBase;

/**
 * @author ThoaiNH
 * web proccessed
 */
@Document(collection = QbMongoCollectionsName.T550)
public class T550MG extends OhhayWebShortBase implements Serializable{
	
}
