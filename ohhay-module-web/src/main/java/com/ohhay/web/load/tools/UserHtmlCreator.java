package com.ohhay.web.load.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.OhhayWebType;
import com.ohhay.core.constant.RequestParamRule;
import com.ohhay.web.core.entities.mongo.webbase.C920MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayLibraryJS;
import com.ohhay.web.core.utils.WebCreateParam;

/**
 * @author ThoaiNH
 * create 23/10/2014 
 * lay user html trong mongodb tu list C920 va list C900
 */
public class UserHtmlCreator {
	private static Logger log = Logger.getLogger(UserHtmlCreator.class);
	private WebCreateParam webParam;
	private String userHtml;
	private Set<OhhayLibraryJS> setOhhayLibraryJS = new java.util.HashSet<OhhayLibraryJS>();
	public UserHtmlCreator(WebCreateParam webParam,  Set<OhhayLibraryJS> setOhhayLibraryJS) {
		super();
		this.webParam = webParam;
		this.setOhhayLibraryJS = setOhhayLibraryJS;
		run();
	}
	/**
	 * set type for box to render edit box panel
	 * @param liC920mgs
	 */
	private void setTypeForBox(List<C920MG> liC920mgs){
		List<Integer> listTemp = new ArrayList<Integer>();
		for(int i= 0;i< liC920mgs.size(); i++){
			C920MG c920mg = liC920mgs.get(i);
			if(c920mg.getCd925() == null && (c920mg.getVisible() != -1 || 
										    (webParam.getRole() == ApplicationConstant.ROLE_OWNER && 
										    		webParam.getEditMode()!= null && webParam.getEditMode().equals(RequestParamRule.WEB_PARAM_EDIT_VALUE_BOX)) ||
										    (webParam.getBoxVisible() == 1 && 
										    		webParam.getRole() == ApplicationConstant.ROLE_OWNER && 
										    		webParam.getEditMode()!= null && 
										    		webParam.getEditMode().equals(RequestParamRule.WEB_PARAM_EDIT_VALUE_ELEMENT)
										   )))
			{
				c920mg.setType(3);
				listTemp.add(i);
			}
		}
		// footer:0,header:0, near footer:1,near header:2, other :3, near footer and header (when 3 box): 4
		try{
			liC920mgs.get(listTemp.get(0)).setType(0);//set type for header
			liC920mgs.get(listTemp.get(listTemp.size()-1)).setType(0);//set type for footer
			liC920mgs.get(listTemp.get(1)).setType(2);//set type for  near header
			liC920mgs.get(listTemp.get(listTemp.size()-2)).setType(1); //set type for  near footer
			if(listTemp.size() == 3)
				liC920mgs.get(listTemp.get(1)).setType(4);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void run() {
		StringBuilder usertHtmlBuilder = new StringBuilder("");
		int totalSize = webParam.getOhhayWebBase().getListC920mg().size();
		//set type for box
		setTypeForBox(webParam.getOhhayWebBase().getListC920mg());
		for (int i=0;i<totalSize;i++) {
			C920MG c920mg = webParam.getOhhayWebBase().getListC920mg().get(i);
				//invisible when viewer if box visible = -1
				log.info("---webparam edit mode:"+webParam.getEditMode());
				log.info("---webparam role:"+webParam.getRole());
				if(c920mg.getCd925() == null && 
				   (c920mg.getVisible() != -1 || 
				    (webParam.getRole() == ApplicationConstant.ROLE_OWNER && webParam.getEditMode()!= null && webParam.getEditMode().equals(RequestParamRule.WEB_PARAM_EDIT_VALUE_BOX)) ||
				    (webParam.getBoxVisible() == 1 && webParam.getRole() == ApplicationConstant.ROLE_OWNER && webParam.getEditMode()!= null && webParam.getEditMode().equals(RequestParamRule.WEB_PARAM_EDIT_VALUE_ELEMENT)
				   ))){
					log.info("------BOX NAME:" + c920mg.getCv921());
					BoxCreator boxCreator = null;
					/*
					 * box for extend page
					 */
					if (c920mg.getFc820() == 34 && webParam.getExtendPage() == OhhayWebType.WEBTYPE_OHHAY_WEBINAR
					 || c920mg.getFc820() == 37 && webParam.getExtendPage() == OhhayWebType.WEBTYPE_OHHAY_BSELL 
					 || c920mg.getFc820() == 38 && webParam.getExtendPage() == OhhayWebType.WEBTYPE_OHHAY_BSELL)
						/*boxCreator = new BoxCreator(webParam.getOhhayWebBase().getId(), c920mg.getRealIndex(),
								c920mg, webParam.getRole(), webParam.getExtendPage(),
								webParam.getKey(), webParam.getMapProperties(), type, webParam.getOhhayWebBase().getFo100(), setOhhayLibraryJS);*/
						boxCreator = new BoxCreator();//bo qua, chua can xu ly
					/*
					 * normal box
					 */
					else
						boxCreator = new BoxCreator(webParam,setOhhayLibraryJS,c920mg,c920mg.getType());
					log.info("---pc920:" + c920mg.getFc920());
					// log.info("---cv820:" + c920.getCv823());
					usertHtmlBuilder.append(boxCreator.loadC900ToC920());
				}
		}
		this.userHtml = usertHtmlBuilder.toString();
	}

	public String getUserHtml() {
		return userHtml;
	}

	public void setUserHtml(String userHtml) {
		this.userHtml = userHtml;
	}

}
