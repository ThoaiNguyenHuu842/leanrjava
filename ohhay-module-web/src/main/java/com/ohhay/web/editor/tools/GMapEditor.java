package com.ohhay.web.editor.tools;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.OhhayWebType;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.criteria.GMapCriteria;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.filesutil.AWSFileUtils;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.G920MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.history.WebEditedInfo;
import com.ohhay.web.core.history.WebHistoryManager;
import com.ohhay.web.core.load.util.AbstractEditor;
import com.ohhay.web.core.mongo.service.C900MGService;
import com.ohhay.web.core.utils.WebTemplateRule;
/**
 * @author ThoaiNH
 * create 06/10/2015
 */
@Service
@Scope("prototype")
public class GMapEditor extends AbstractEditor{
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_C900MG)
	private C900MGService c900mgService;
	/**
	 * save all type gmap
	 */
	public boolean saveGMap(GMapCriteria gMapCriteria, int fo100) {
		//fo100 user to know insert db center or user db
		int fo100i = (gMapCriteria.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || gMapCriteria.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100;
		if(gMapCriteria.getImgBase64()!=null && gMapCriteria.getImgBase64().length()>0){
			/*
			 * 1) upload marker
			 */
			String imgContent = gMapCriteria.getImgBase64().split("\\,")[1];
			byte[] btDataFile;
			try {
				btDataFile = new sun.misc.BASE64Decoder()
						.decodeBuffer(imgContent);
				AWSFileUtils awsFileUtils = new AWSFileUtils();
				String fileName = ApplicationHelper.generateFileName();
				if(gMapCriteria.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE)
				{
					awsFileUtils.uploadObjectMutilPartA900(gMapCriteria.getWebId(), fileName, btDataFile);
					fileName =  TemplateRule.OHHAY_TEMPLATE_LINK+ fileName;
				}
				else
				{
					awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
					fileName =  TemplateRule.OHHAY_SERVER_LINK+ fileName;
				}
				awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
				//set img name to criteria
				gMapCriteria.setMarkerImg(fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * 2) save content
		 */
		OhhayWebBase ohhayWebBase = c900mgService.saveGmap(gMapCriteria,WebTemplateRule.getWebClassFromExtend(gMapCriteria.getExtend()),fo100, fo100i);
		if(ohhayWebBase != null){
			/*
			 * 3) save to history stack
			 * gmap is not store in language so must save to history for all language , save index part to index property
			 */
			for(LanguageMG languageMG: ohhayWebBase.getListC500mg()){
				WebEditedInfo webEditedInfo = new WebEditedInfo(3,ohhayWebBase.getListC920mg().get(gMapCriteria.getIndexPart()).getG920mg(),
																gMapCriteria.getWebId(), gMapCriteria.getExtend(),gMapCriteria.getIndexPart(),languageMG.getCode());
				G920MG g920mg = new G920MG();
				g920mg.setGv921(gMapCriteria.getAddress());
				g920mg.setGv922(gMapCriteria.getMarkerImg());
				g920mg.setGn923(Double.parseDouble(gMapCriteria.getLa()));
				g920mg.setGn924(Double.parseDouble(gMapCriteria.getLng()));
				WebEditedInfo webNewInfo = new WebEditedInfo(3,g920mg,gMapCriteria.getWebId(), gMapCriteria.getExtend(),gMapCriteria.getIndexPart(),languageMG.getCode());
				WebHistoryManager.addNewItem(webEditedInfo, webNewInfo);
			}
			WebHistoryManager.test();
			onEditWebSuccess(WebTemplateRule.getWebClassFromExtend(gMapCriteria.getExtend()), gMapCriteria.getWebId(), null, fo100i);
			return true;
		}
		return false;
	}
}
