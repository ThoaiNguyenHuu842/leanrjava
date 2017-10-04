package com.ohhay.web.lego.entities.mongo.topic;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.lego.entities.mongo.base.box.Box;

/**
 * @author ThoaiNH
 * create Nov 19, 2015
 */
@Document(collection = QbMongoCollectionsName.ET920)
public class ET920MG extends Box implements Serializable{

}
