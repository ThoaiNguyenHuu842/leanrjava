package com.ohhay.web.editor.tools;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.entities.Q100;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractEditor;
import com.ohhay.web.core.utils.WebTemplateRule;

/**
 * @author ThoaiNH
 * create 06/10/2015
 */
@Service
@Scope("prototype")
public class TextEditor extends AbstractEditor{
	private static Logger log = Logger.getLogger(TextEditor.class);
	/**
	 * save text C900
	 */
	public boolean saveC900MGText(C900MG c900mg) {
		log.info("- extend:" + c900mg.getExtend());
		log.info(" pc900:" + c900mg.getPc900());
		if(updateC900MG(c900mg))
		{
			String[] ids = c900mg.getPc900().split("#");
			int webId = Integer.parseInt(ids[0]);
			Q100 q100 = AuthenticationHelper.getUserLogin();
			onEditWebSuccess(WebTemplateRule.getWebClassFromExtend(c900mg.getExtend()),webId, c900mg.getLanguageCode(), q100.getPo100());
			return true;
		}
		return false;
	}
}
