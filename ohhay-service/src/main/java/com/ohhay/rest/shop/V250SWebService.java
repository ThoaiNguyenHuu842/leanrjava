package com.ohhay.rest.shop;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.entities.mongo.shop.V250SMG;
import com.ohhay.core.mongo.dao.SequenceDao;
import com.ohhay.other.lucene.KeySearch;
import com.ohhay.other.lucene.OhhayScoreDoc;
import com.ohhay.other.lucene.QbLuceneBase;
import com.ohhay.other.lucene.QueryFactory;
import com.ohhay.other.lucene.V250SLuncene;

/**
 * @author ThoaiNH
 * create Jun 17, 2016
 * search service for shop online
 */
@Path("v250sWebService")
public class V250SWebService {
	/**
	 * @param txtSearch
	 * @param fo100
	 * @return
	 */
	@GET
	@Path("searchV250")
	@Produces("application/json")
	public String searchV250(@QueryParam("TXTSEARCH") String txtSearch, @QueryParam("FO100")int fo100) {
		try {
			V250SLuncene v250sLuncene = new V250SLuncene(ApplicationConstant.INDEXPATH_V250);
			KeySearch keySearch = new KeySearch(txtSearch, 1);
			List<OhhayScoreDoc> listScoreDocResult = null;
			listScoreDocResult = v250sLuncene.search(QueryFactory.createV250Search(keySearch,fo100), 10000, QbLuceneBase.MODE_SEARCH_WEB);
			System.out.println(listScoreDocResult.size());
			List<V250SMG> list = v250sLuncene.getResult(0, 10000, listScoreDocResult);
			List<Integer> listResult = new ArrayList<Integer>();
			for(V250SMG v250smg: list)
				listResult.add((int)v250smg.getId());
			return QbRestUtils.convertToJsonStringForProc(listResult);
			
				} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	
}
