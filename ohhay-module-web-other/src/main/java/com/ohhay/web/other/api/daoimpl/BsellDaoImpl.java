package com.ohhay.web.other.api.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.wsclient.QbWsClientSupport;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.WsClinentName;
import com.ohhay.web.core.entities.V250;
import com.ohhay.web.other.api.dao.BsellDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_BSELLDAO)
@Scope("prototype")
public class BsellDaoImpl extends QbWsClientSupport implements BsellDao {

	@Override
	public int checkTabV050Key(String key) {
		// TODO Auto-generated method stub
		try {
			//4EA7DFFF5C5F08AD60315170F15F6305
			JSONObject jsonObject = callGet(WsClinentName.BSELL_V050_CHECKTABV050_KEY_SET+"?key="+key+"&login=tungns");
			int fv050 = 0;
			if (jsonObject.getString("elements") != null)
				fv050 = Integer.parseInt(jsonObject.getString("elements"));
			if (fv050 > 0)
				return fv050;
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<V250> listOfTabV250KEY(String key) {
		// TODO Auto-generated method stub
				List<V250> list = new ArrayList<V250>();
				try {
					JSONObject jsonObject = callGet(WsClinentName.BSELL_V250_LISTOFTABV250KEY+"?pvVV055="+key+"&pnSORT=0&pvSEARC=&pnDIRECT=0&pnOFFSET=1&pnLIMIT=10&pvLOGIN=tungns");
					// get element
					JSONArray jsonArray = jsonObject.getJSONArray("result");
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject2 = jsonArray.getJSONObject(i);
						V250 v250 = new V250();
						v250.setVv501(jsonObject2.getString("VV501"));
						v250.setVv502(jsonObject2.getString("VV502"));
						v250.setVn254(jsonObject2.getString("VN254"));
						v250.setPv250(jsonObject2.getString("pv250"));
						v250.setUrlimg("https://my.leadpages.net/template/5250926118961152/raw/img/98kkjd-guy-bkg-img.jpg");
						list.add(v250);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
	}
	@Override
	public String printJavaScript() {
		// TODO Auto-generated method stub

		String script = "<script type='text/javascript'>"
				+ "jQuery(document).ready(function(){"//bat dau jquery
				
				+ "$('.infoProduct').on('click',function(){"// clcivk
				+ "	var id = $(this).attr('lang');"// tim kiem id
				+ "	var namesp = $(this).find('h4').text();"// tim kiem san pham ten
				+ " var price = $(this).find('.pull-left').text();"// timkiem gia san
					//replace
				+"	$('.key-success').find('h3').text(namesp);"// dua ten vao cai moi
				+ "	$('.key-success').find('h1').text(price);"// dua gia tien vao cai moi
				
		        + " var sectionID = $(this).attr('data-id');"// effect jquery
		        + " scrollToID('#' + sectionID, 750);"
		        + " $('.navbar-nav li').each(function(){"
		        + " $(this).removeClass('active');"
		        + " });"
		        + " $(this).parent().addClass('active');"
				+ " });"// close fucntion click
				
				+ "});"// close query
				//function gete id de tro toi
				+ "function scrollToID(id, speed){"
			    + " var offSet = -200;"
			    + " var targetOffset = $(id).offset().top - offSet;"
			    + " var mainNav = $('#main-nav');"
			    + " $('html,body').animate({scrollTop:targetOffset}, speed);"
			    + "}"
				+ "</script>";
		return script;
	}
	
	
	
}