package com.ohhay.web.lego.entities.mongo.topic;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.lego.entities.mongo.base.component.Component;

/**
 * @author ThoaiNH
 * create Nov 19, 2015
 */
@Document(collection = QbMongoCollectionsName.ET900)
public class ET900MG extends Component implements Serializable{
	
}
