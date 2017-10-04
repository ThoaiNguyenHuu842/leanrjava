package com.ohhay.rest.bonevo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.other.M150MG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.filesutil.AWSFileUtils;
import com.ohhay.other.lucene.KeySearch;
import com.ohhay.other.lucene.M150Luncene;
import com.ohhay.other.lucene.M900Luncene;
import com.ohhay.other.lucene.OhhayScoreDoc;
import com.ohhay.other.lucene.QbLuceneBase;
import com.ohhay.other.lucene.QueryFactory;

/**
 * @author ThoaiNH
 *
 */
@Path("searchWebService")
public class SearchWebService {
	private static Logger log = Logger.getLogger(SearchWebService.class);

	@GET
	@Path("searchM150")
	@Produces("application/json")
	public String search150(@QueryParam("textSearch") String textSearch, @QueryParam("fo100") int fo100) {
		try {
			log.info("---ation search m150 textSearch:" + textSearch);
			log.info("---fo100:" + fo100);
			KeySearch keySearch = new KeySearch(textSearch.trim().replace("+", " "), 1);
			Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
			if (q100 != null)
				keySearch.setFo100Request(q100.getPo100());
			M150Luncene searcher = new M150Luncene(
					ApplicationConstant.INDEXPATH_M150);
			List<OhhayScoreDoc> listScoreDocResult = searcher
					.search(QueryFactory
							.createM150SearchOtags(keySearch, fo100), 10000, QbLuceneBase.MODE_SEARCH_SERVICE);
			List<M150MG> list = searcher
					.getResult(0, 10000, listScoreDocResult);
			StringBuilder docArray = new StringBuilder("");
			for (M150MG m150mg : list) {
				log.info("---id m150:" + m150mg.getId());
				docArray.append(m150mg.getId());
				docArray.append(";");
			}
			return QbRestUtils.convertToJsonStringForFunc(docArray);
		} catch (Exception ex) {
			return QbRestUtils.getErrorStatus();
		}
	}

	/*
	 * search m900
	 */
	@GET
	@Path("searchM900")
	@Produces("application/json")
	public String searchM900(@QueryParam("textSearch") String textSearch) {
		try {
			M900Luncene searcher = new M900Luncene(
					ApplicationConstant.INDEXPATH_M900);
			int limit = 10;
			log.info("---textSearch:" + textSearch);
			String docArray = "";
			List<OhhayScoreDoc> listScoreDocs = new ArrayList<OhhayScoreDoc>();
			KeySearch keySearch = new KeySearch(textSearch, 1);
			listScoreDocs = searcher
					.search(QueryFactory.createM900QuerySearchAll(keySearch), 10000, M900Luncene.MODE_SEARCH_SERVICE);
			List<M900MG> listM900mgs = searcher
					.getResult(0, limit, listScoreDocs);
			for (M900MG m900mg : listM900mgs) {
				m900mg.setUrlAvarta(m900mg.getUrlAvarta());
				log.info(m900mg.getHv101());
			}
			log.info("---list m900mg size:" + listM900mgs.size());
			// all doc id result
			if (listScoreDocs.size() > limit && listM900mgs.size() > 0) {
				// xoa 10 phan tu dau tien da lay roi
				Iterator<OhhayScoreDoc> iterator = listScoreDocs.iterator();
				while (iterator.hasNext() && limit > 0) {
					iterator.next();
					iterator.remove();
					limit --;
				}
				docArray = getDocIdArray(listScoreDocs);
			}
			return QbRestUtils
					.convertToJsonStringForProc(docArray, listM900mgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * load more m900 when scroll
	 */
	@GET
	@Path("loadMoreM900")
	@Produces("application/json")
	public String loadMoreM900(@QueryParam("docarray") String docArray) {
		try {
			M900Luncene searcher = new M900Luncene(
					ApplicationConstant.INDEXPATH_M900);
			log.info("---docarray:" + docArray);
			List<M900MG> listM900mgs = new ArrayList<M900MG>();
			String[] docIds = docArray.split(";");
			if (docIds.length > 0) {
				for (String docId : docIds) {
					log.info("---docid:" + docId);
					M900MG m900mg = searcher.getM900ByDocId(Integer
							.parseInt(docId));
					listM900mgs.add(m900mg);
				}
			}
			return QbRestUtils.convertToJsonStringForProc(listM900mgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	public String getDocIdArray(List<OhhayScoreDoc> listScoreDocs) {
		StringBuilder builder = new StringBuilder();
		for (OhhayScoreDoc doc : listScoreDocs) {
			builder.append(doc.getScoreDoc().doc);
			builder.append(";");
		}
		return builder.toString();
	}
}
