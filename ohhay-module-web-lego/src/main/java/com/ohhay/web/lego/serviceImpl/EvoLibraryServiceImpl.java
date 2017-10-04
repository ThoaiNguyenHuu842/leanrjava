package com.ohhay.web.lego.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.EvoAddLibCriteria;
import com.ohhay.core.filesutil.EvoAWSFileUtils;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.mongo.dao.E920MGDao;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.entities.mongo.web.E920MG;
import com.ohhay.web.lego.service.EvoLibraryService;
import com.oohhay.web.lego.utils.EvoWebType;
import com.oohhay.web.lego.utils.WebRule;

/**
 * @author ThoaiNH
 * create Jan 12, 2016
 */
@Service(value = SpringBeanNames.SERVICE_NAME_EVOLIBRARY)
@Scope("prototype")
public class EvoLibraryServiceImpl implements EvoLibraryService {
	private static Logger log = Logger.getLogger(EvoLibraryServiceImpl.class);
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private TemplateService templateService;
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_E920MG)
	E920MGDao e920mgDao;
	@Override
	public int addToLib(int fo100, EvoAddLibCriteria evoAddLibCriteria, String region) {
		// TODO Auto-generated method stub
		try {
			E920MG e920mg = templateService.findDocumentById(fo100, evoAddLibCriteria.getPe920(), E920MG.class);
			if(e920mg != null){
				e920mg.setLibTitle(evoAddLibCriteria.getTitle());
				e920mg.setEditAble(evoAddLibCriteria.getEditAble());
				e920mg.setLibType(evoAddLibCriteria.getLibType());
				e920mg.setBl949(new Date());
				//set default is approved to test
				if(evoAddLibCriteria.getLibType() == 2)
				{
					e920mg.setApprStt(1);
					e920mg.setBl950(new Date());
				}
				if(evoAddLibCriteria.getOtags() != null && evoAddLibCriteria.getOtags().trim().length() > 0){
					String tags[] = evoAddLibCriteria.getOtags().split(",");
					List<String> listOtags = new ArrayList<String>();
					for(String tag: tags){
						listOtags.add(tag.trim().toLowerCase());
					}
					e920mg.setOtags(listOtags);
				}
				//upload thumb
				if(evoAddLibCriteria.getImgBase64().trim().length() > 0){
					String imgContent = evoAddLibCriteria.getImgBase64().split("\\,")[1];
					byte[] btDataFil = new sun.misc.BASE64Decoder().decodeBuffer(imgContent);
					EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils(region, fo100);
					String fileName = ApplicationHelper.generateFileName();
					String url = awsFileUtils.uploadObjectMutilPartImgAlbum(0, fileName, btDataFil);
					e920mg.setLibThumb(url);
				}
				return templateService.saveDocument(fo100, e920mg, QbMongoCollectionsName.E920);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<E920MG> load(int fo100, int libType, String textSearch, int offset, int limit) {
		// TODO Auto-generated method stub
		return e920mgDao.listOfTabLibrary(fo100, libType, textSearch, offset, limit);
	}
	@Override
	public int approve(int fo100, int pe920) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int remove(int fo100, int pe920, String itemtype) {
		// TODO Auto-generated method stub
		E920MG e920mg = null;
		//remove library the get from other library
		if(itemtype.equals("ref"))
		{
			e920mg = templateService.findDocument(fo100, E920MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100),
												  new QbCriteria(QbMongoFiledsName.FE920, pe920));
			return templateService.removeDocumentById(fo100, (int)e920mg.getId(), E920MG.class);
		}
		else
		{
			e920mg = templateService.findDocument(fo100, E920MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100),
					  							 new QbCriteria(QbMongoFiledsName.ID, pe920));
			e920mg.setBl951(new Date());
			e920mg.setLibType(0);
		}
		return templateService.saveDocument(fo100, e920mg, QbMongoCollectionsName.E920);
	}
	@Override
	public E920MG addLibToWeb(int fo100, int pe920, int webId) {
		// TODO Auto-generated method stub
		try {
			E920MG e920mg = templateService.findDocumentById(fo100, pe920, E920MG.class);
			E400MG e400mg = templateService.findDocument(fo100, E400MG.class, new QbCriteria(QbMongoFiledsName.ID, webId), new QbCriteria(QbMongoFiledsName.FO100, fo100));
			int role = 0;
			if((e920mg.getFo100() == fo100 || e920mg.getApprStt() == 1) && e400mg != null)
				role = 1;
			if(role == 1){
				E920MG e920mgNew = new E920MG(e920mg);
				long newBoxId = sequenceService.getNextSequenceId(fo100, WebRule.getBoxMongoColectionFromExtend(EvoWebType.EVOTYPE_WEB));
				e920mgNew.setId(newBoxId);
				e920mgNew.setBl948(new Date());
				e920mgNew.setBl946(new Date());
				e920mgNew.setWebId(webId);
				e920mgNew.setFo100(fo100);
				e920mgNew.setFe920(pe920);
				e920mg.setLibType(3);
				if(templateService.saveDocument(fo100, e920mgNew, QbMongoCollectionsName.E920) > 0)
					return e920mgNew;
			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return  null;
	}
	@Override
	public int getToMyLib(int fo100, int pe920) {
		// TODO Auto-generated method stub
		E920MG e920mg = templateService.findDocument(fo100, E920MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), 
																		  new QbCriteria(QbMongoFiledsName.FE920, pe920),
																		  new QbCriteria(QbMongoFiledsName.TYPE, "lib"));
		if(e920mg != null)
			return -1; //this section is on my lib
		else {
			try {
				E920MG e920mgNew = new E920MG();
				long newBoxId = sequenceService.getNextSequenceId(fo100, WebRule.getBoxMongoColectionFromExtend(EvoWebType.EVOTYPE_WEB));
				e920mgNew.setId(newBoxId);
				e920mgNew.setFo100(fo100);
				e920mgNew.setType("lib");
				e920mgNew.setFe920(pe920);
				e920mgNew.setBl948(new Date());
				e920mgNew.setBl949(new Date());
				e920mgNew.setBl946(new Date());
				return templateService.saveDocument(fo100, e920mgNew, QbMongoCollectionsName.E920);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return 0;
	}
}
