/**
 * @author ThoaiNH 
 * create Feb 25, 2016
 * designer event on designer packet module
 */
(function() {
	designerSite = {
		init : function() {
			designerSite.eventListener();
		},
		eventListener : function() {
			$(document).on('click','.item-data-cus .btn-load-site-of-cus', function(){
				var pe100 = $(this).parents('.item-data-cus').attr('pe100');
				designerSite.pe100 = pe100;
				if($(this).hasClass('showing'))
				{
					$('.item-data-cus .btn-load-site-of-cus').removeClass('showing');
					$('.data-compoment .panel-web-cus[pe100="'+pe100+'"]').slideUp();
				}
				else
				{
					$(this).addClass('showing');
					designerSite.loadListWebOfCus(pe100);
				}
			});
			//update site customer
			$(document).on('click','.list-site-cus .info_function .btn-update-site', function(){
				var siteId = $(this).attr('siteIdc');
				util.confirmDialog(getLocalize("mys_title14"), function(){
					designerSite.updateVersionSite(siteId);
				});
			});
			$(document).on('click','.item-data .btn-des-edit', function(){
				var siteId = $(this).parents('.item-data').attr('siteId');
				designerSite.getVersionSiteID(siteId);
			});
			$(document).on('click','.item-data .btn-des-backup', function(){
				var siteId = $(this).parents('.item-data').attr('siteId');
				var pe150 = $(this).parents('.item-data').attr('pe150');
				var title = $(this).parents('.data').find('.title_page').html();
				$('#qb-dlg-list-backup .title').html(title);
				backUpList.showBackupList(siteId,pe150);
			});
			//event click to load list site customer
			$(document).on('click','.body-mysite .data-compoment .content .content-item-cus .cus-item-total-page i.detail-site-cus', function(){
				$(this).parents('li').siblings().slideUp();
				$(this).parents('.wapper-cus').removeClass('container');
				$(this).parents('.content-item-cus').find('.icon-back').html('<i class="fa fa-angle-left back-list"></i>');
				$(this).hide();
				designerSite.loadListSiteOfCus($(this).attr('cusid'));
				
			});
			$(document).on('click','.body-mysite .data-compoment .content .content-item-cus .icon-back i.back-list-detail', function(){
				designerSite.loadCussite();
			});
			$(document).on('click','.body-mysite .data-compoment .content .content-item-cus .icon-back i.back-list', function(){
				$(this).parents('li').siblings().slideDown();
				$(this).parents('.wapper-cus').addClass('container');
				$(this).parents('.content-item-cus').find('.detail-site-cus').show();
				$('.body-mysite .data-compoment .content .list-site-cus').html("");
				$(this).remove();
			});
			//event confirm request customer
			$(document).on('click','.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm',function(){
				designerSite.confirmRequestOfCus($(this).attr('fe150'),$(this).attr('fe400'));
			});
			//reject
			$(document).on('click','.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-reject',function(){
				designerSite.rejectSiteOfCus($(this).attr('fe150'),$(this).attr('fe400'));
			});
			//send site to customer
			$(document).on('click','.btn-send-to-cus', function(){
				var fe150 = $(this).attr('fe150');
				var siteId = $(this).attr('siteidd');
				util.confirmDialog("You can only send once. Are you sure want to send?", function(){
					designerSite.openDialogSubmitCus(fe150,siteId);
				})
			})
		},
		/*
		 * load customer sites
		 */
		loadCussite : function(pvSearch) {
			$.ajax({
				url : encodeUrl("designerBean.loadListCus"),
				data : {
					pvSearch:((pvSearch == undefined)?"":pvSearch),
					offset:0,
					limit:100
				},
				success : function(response) {
					var htmlString = "";
					if(response.result.length > 1)
						$('.body-mysite .content-status .content .row .info-detail .status-name').html(getLocalize("mys_customer_2"));
					else 
						$('.body-mysite .content-status .content .row .info-detail .status-name').html(getLocalize("mys_customer"));
					if (response.result && response.result.length > 0) {
						htmlString +='<div class="site-designer-detail">';
						htmlString +='<ul>';
						htmlString +=	'<li>';
						htmlString +=		'<div class="content-item-header clearfix container">';
						htmlString +=			'<div class="cus-name col-sm-4">';
						htmlString +=				'<span class="detail-cus-stie">'+getLocalize("des_title8")+'<i class="fa fa-caret-down"></i></span>';
						htmlString +=			'</div>';
						htmlString +=			'<div class="cus-start-date col-sm-2">';
//						htmlString +=				'<span class="detail-cus-stie">'+getLocalize("des_title9")+'<i class="fa fa-caret-down"></i></span>';
						htmlString +=			'</div>';
						htmlString +=			'<div class="cus-status col-sm-4">';
						htmlString +=				'<span class="detail-cus-stie">'+getLocalize("des_title10")+'<i class="fa fa-caret-down"></i></span>';
						htmlString +=			'</div>';
						htmlString +=			'<div class="cus-total-page col-sm-2">';
						htmlString +=				'<span class="detail-cus-stie">'+getLocalize("des_title11")+'<i class="fa fa-caret-down"></i></span>';
						htmlString +=			'</div>';
						htmlString +=		'</div>';
						htmlString +=	'</li>';
						for (index = 0; index < response.result.length; index++) {
							var item = response.result[index];
							htmlString +='<li>';
							htmlString +=	'<div class="wapper-cus container">';
							htmlString +=		'<div class="content-item-cus clearfix container">';
							htmlString +=			'<div class="cus-name col-sm-4">';
							htmlString +=				'<div class="icon-back">';
							htmlString +=				'</div>';
							htmlString +=				'<div class="cus-item-avatar">';
							htmlString +=					'<img alt="" src="'+item.m900c.urlAvarta+'">';
							htmlString +=				'</div>';
							htmlString +=				'<div class="cus-item-name">';
							htmlString +=					'<p>'+item.m900c.nv100+' <span>'+((item.m900c.mv903Decrypt)?"("+item.m900c.mv903Decrypt+")":"")+'</span></p>';
							htmlString +=				'</div>';
							htmlString +=			'</div>';
							htmlString +=			'<div class="cus-item-date col-sm-2">';
							htmlString +=				'<span></span>';
							htmlString +=			'</div>';
							htmlString +=			'<div class="cus-item-status col-sm-4">';
							var sttNew = JSON.parse(designerSite.setStatusForDes('NEW'))
							var htmlStt1 = '<span class="label-stt" style="background-color: '+sttNew.color+';display:none" >'+sttNew.text+'<span class="notify-page-waiting NEW">0</span></span>';
							var sttDec = JSON.parse(designerSite.setStatusForDes('DEC'))
							var htmlStt2 = '<span class="label-stt" style="background-color: '+sttDec.color+';display:none" >'+sttDec.text+'<span class="notify-page-waiting DEC">0</span></span>';
							var sttWai = JSON.parse(designerSite.setStatusForDes('WAI'))
							var htmlStt3 ='<span class="label-stt" style="background-color: '+sttWai.color+';display:none" >'+sttWai.text+'<span class="notify-page-waiting WAI">0</span></span>';
							var sttCon = JSON.parse(designerSite.setStatusForDes('CON'))
							var htmlStt4 ='<span class="label-stt" style="background-color: '+sttCon.color+';display:none" >'+sttCon.text+'<span class="notify-page-waiting CON">0</span></span>';
							for(var j = 0 ; j< item.status.length;  j++)
							{
								if(item.status[j].id=='NEW'){
									htmlStt1 = '<span class="label-stt" style="background-color: '+sttNew.color+';display:inline-block" >'+sttNew.text+'<span class="notify-page-waiting NEW">'+item.status[j].total+'</span></span>';
								}
								if(item.status[j].id=='DEC'){
									htmlStt2 = '<span class="label-stt" style="background-color: '+sttDec.color+';display:inline-block" >'+sttDec.text+'<span class="notify-page-waiting DEC">'+item.status[j].total+'</span></span>';
								}
								if(item.status[j].id=='WAI'){
									htmlStt3 ='<span class="label-stt" style="background-color: '+sttWai.color+';display:inline-block" >'+sttWai.text+'<span class="notify-page-waiting WAI">'+item.status[j].total+'</span></span>';
								}
								if(item.status[j].id=='CON'){
									htmlStt4 ='<span class="label-stt" style="background-color: '+sttCon.color+';display:inline-block" >'+sttCon.text+'<span class="notify-page-waiting CON">'+item.status[j].total+'</span></span>';
								}
							}	
							htmlString += htmlStt1;
							htmlString += htmlStt2;
							htmlString += htmlStt3;
							htmlString += htmlStt4;
							htmlString +=			'</div>';
							htmlString +=			'<div class="cus-item-total-page col-sm-2">';
							htmlString +=				'<div class="total-page-content">';
							htmlString +=				'<span>'+item.totalSites+'</span>';
							htmlString +=				'<img alt="" src="html/images/my-site-total-page.png">';
							htmlString +=				'</div>';
							htmlString +=				'<i class="fa fa-angle-right detail-site-cus" cusid="'+item.m900c.id+'"></i>';
							htmlString +=			'</div>';
							htmlString +=		'</div>';
							htmlString +=	'</div>';
							htmlString +=	'<div class="list-site-cus" cusid="'+item.m900c.id+'"></div>';
							htmlString +='</li>';
						}
						htmlString +='</ul>';
						htmlString +='</div>';
						$(".body-mysite .data-compoment .content .data-content").html(htmlString);
						$(".body-mysite .data-compoment .content .pagination-content").html("");
						$('.status-value').html(response.result.length);
					}
					else {
						$('.status-value').html(0);
						var nodata = '<span class="no-data-found">'+ getLocalize('no_data') + '</span>';
						$(".body-mysite .data-compoment .content .data-content").html(nodata);
						$(".body-mysite .data-compoment .content .pagination-content").html("");

					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * load website of customer
		 */
		loadListWebOfCus : function(pe100) {
			$.ajax({
				url : encodeUrl("designerBean.loadListWebOfCus"),
				data : {
					fe100:pe100
				},
				success : function(response) {
					var htmlString = "";
					if (response.result && response.result.length > 0) {
						for (index = response.result.length - 1; index >= 0; index--) {
							var item = response.result[index];
							var siteName = 'evo-site-'+item.ev151;
							var sitePath = item.ev151;
							var url =  util.contextPath()+'/e'+item.ev151+'/customer-site';
							var thumb = util.contextPath()+ '/html/images/no-image.gif';
							htmlString +='<div class="item-data" pe150="'+item.pe150+'" siteId="'+ item.ev151 + '">';
							htmlString += 	'<div class="col-sm-3 content-img hvr-shrink">';
							htmlString += 		'<img class="img_page"  style="cursor: pointer;" src="'+ thumb + '" urledit="'+ $("#contextPath").val()+ '/e'+ item.ev151 + "/" + sitePath + '" />';
							htmlString += 	'</div>';
							htmlString +=	'<div class="col-sm-9 content-data data_page">';
							htmlString += 		'<div class="data">';
							htmlString += 			'<div class="title_page item">'+ siteName + '</div>';
							htmlString += 			'<div class="info_page item">';
							htmlString += 				'<p>';
							htmlString += 					'URL: <a target="_blank" href='+url+' style="color: #EA9138; cursor: pointer;">'+url+'</a>';
							htmlString += 				'</p>';
							htmlString += 			'</div>';
							htmlString += 			'<div class="info_language_page item">';
							htmlString += 			'</div>';
							htmlString += 			'<div class="info_function item">';
							if(item.ed162String.trim().length > 0)
								htmlString += '<p title="Applied at '+item.ed162String+'">Applied</p>';
							else if(item.ev155 > 0)
								htmlString += '<p>Waitting customer confirm</p>';
							else {
								htmlString += 				'<ul class="function">';
								htmlString += 					'<li class="item">';
								htmlString += 						'<button class="btn-des-update obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="Update">';
								htmlString += 							'<i class="fa  fa-refresh"></i>';
								htmlString += 						'</button>';
								htmlString += 						'<button class="btn-des-backup obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="Backup">';
								htmlString += 							'<i class="fa  fa-save"></i>';
								htmlString += 						'</button>';
								htmlString += 						'<button class="btn-des-edit obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="Edit">';
								htmlString += 							'<i class="fa  fa-pencil"></i>';
								htmlString += 						'</button>';
								htmlString += 					'</li>';
								htmlString += 				'</ul>';
							}
							htmlString += 			'</div>';
							htmlString += 		'</div>';
							htmlString += 	'</div>';
							htmlString += '</div>';
						}
						$('.data-compoment .panel-web-cus[pe100="'+pe100+'"]').html(htmlString);
					}
					else {
						var nodata = '<span class="no-data-found">'+ getLocalize('no_data') + '</span>';
						$('.data-compoment .panel-web-cus[pe100="'+pe100+'"]').html(nodata);
					}
					$('.data-compoment .panel-web-cus').slideUp();
					$('.data-compoment .panel-web-cus[pe100="'+pe100+'"]').slideDown();
					
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * get version-site id
		 */
		getVersionSiteID : function(fe400) {
			$.ajax({
				type : "POST",
				url : encodeUrl("designerBean.getVersionSiteID"),
				data : {
					fe400 : fe400
				},
				success : function(response) {
					window.open(util.contextPath()+"/e"+response.result+"/evo-editer?editmode=element", '_blank');
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * delete site
		 */
		updateVersionSite : function(fe400) {
			$.ajax({
				type : "POST",
				url : encodeUrl("designerBean.updateVersionSite"),
				data : {
					fe400 : fe400
				},
				success : function(response) {
					if(response.result > 0)
						{
							$('.btn-update-site[siteidc="'+fe400+'"]').parents('ul.function').find('.link-edit-of-cus').attr('href',$("#contextPath").val()+ "/e"+ response.result +"/evo-editer"+ '?editmode=element');
							$('.btn-update-site[siteidc="'+fe400+'"]').parents('ul.function').find('.btn-send-to-cus').attr('siteidd',response.result);
							showGrowlMessageSuccess();
						}
					else
						showGrowlMessageError();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		setStatus :function(status){
			
			switch(status){
				case 'NEW':
					return '{"text":"'+getLocalize('des_stt1')+'","color":"#607d8b"}';
					break;
				case 'DEC':
					text = '';
					return '{"text":"'+getLocalize('des_stt2')+'","color":"#00bcd4"}';
					break;
				case 'WAI':
					text = '';
					return '{"text":"'+getLocalize('des_stt3')+'","color":"#ff5722"}';
					break;
				case 'CON':
					return '{"text":"'+getLocalize('des_stt4')+'","color":"#7cb342"}';
					break;
					
			}
		},
		setStatusForDes :function(status){			
			switch(status){
				case 'NEW':
					return '{"text":"'+getLocalize('des_stt5')+'","color":"#607d8b"}';
					break;
				case 'DEC':
					text = '';
					return '{"text":"'+getLocalize('des_stt6')+'","color":"#00bcd4"}';
					break;
				case 'WAI':
					text = '';
					return '{"text":"'+getLocalize('des_stt7')+'","color":"#ff5722"}';
					break;
				case 'CON':
					return '{"text":"'+getLocalize('des_stt8')+'","color":"#7cb342"}';
					break;
					
			}
		},
		loadListSiteOfCus : function(fo100c){
			$.ajax({
				type : "GET",
				url : encodeUrl("designerBean.loadListSiteOfCus"),
				data:{
					fo100c:fo100c,
					offset:0,
					limit:100
				},
				success : function(response) {
					var htmlString = "";
					if (response.result && response.result.length > 0) {
						var stt1 =0 , stt2 = 0, stt3 =0, stt4=0;
						htmlString+= '<div class="container site-detail">'
						for (index = 0 ; index<response.result.length; index++) {
							var item = response.result[index];
							if(item.ev152=='NEW')
								stt1++;
							if(item.ev152=='DEC')
								stt2++;
							if(item.ev152=='WAI')
								stt3++;
							if(item.ev152=='CON')
								stt4++;
							
							var siteName = item.e400c.ev405;
							var sitePath = item.e400c.ev405;
							var url =  util.contextPath()+'/'+sitePath;
							var thumb = 'html/images/noimage_03.png';
							if(item.e400c.ev403 && item.e400c.ev403.length > 0){
								thumb = item.e400c.ev403;
							}
							htmlString +='<div class="item-data" siteId="'+ item.fe400 + '">';
							htmlString +=	'<a class="cover-a-goto" href='+ $("#contextPath").val()+ "/e"+ item.fe400 +"/evo-editer"+ '?editmode=element'+ '>';
							htmlString += 		'<div class="col-sm-3 content-img hvr-shrink full-width-mysite-on-mobile">';
							htmlString += 			'<img class="img_page"  style="cursor: pointer;" src="'+ thumb + '" urledit="'+ $("#contextPath").val()+ '/e'+ item.fe400 + "/" + sitePath + '" />';
							htmlString += 		'</div>';
							htmlString += 	'</a>';
							htmlString +=	'<div class="col-sm-9 content-data data_page full-width-mysite-on-mobile">';
							htmlString += 		'<div class="data">';
							htmlString += 			'<div class="title_page item">'+ siteName + '</div>';
							htmlString += 			'<div class="info_page item">';
							htmlString += 				'<p>';
							htmlString += 					'<span class="span-date-create" style="display:none;">'+getLocalize("mys_create")+'&nbsp: '+item.e400c.el448String+'&nbsp&nbsp</span><span class="span-space" style="display:none;"> | </span><span class="span-date-update">'+getLocalize("mys_lmodify")+'&nbsp: '+item.e400c.el446String+'&nbsp&nbsp </span>';
							htmlString += 				'</p>';
							htmlString += 				'<p>';
							htmlString += 					'URL: <a href='+url+' style="color: #EA9138; cursor: pointer;">'+url+'</a>';
							htmlString += 				'</p>';
							htmlString += 			'</div>';
							htmlString += 			'<div class="stt-site-cus item">';

							var stt = JSON.parse(designerSite.setStatusForDes(item.ev152))
							htmlString +='<button class="dropdown-toggle" type="button" id="dropdown-cus" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="background-color: '+stt.color+';">'+stt.text+
											 ' <span class="caret"></span>'+
										 '</button>'+
										 '<ul class="dropdown-menu dropdown-site-custom" aria-labelledby="dropdown-cus">';
							htmlString += ((item.ev152=='NEW')?'<li><a fe150="'+item.id +'" fe400="'+item.fe400 +'" class="select-confirm">'+getLocalize('des_title14')+'</a></li>':'');
							htmlString += ((item.ev152=='NEW' || item.ev152=='DEC')?'<li><a fe150="'+item.id +'" fe400="'+item.fe400 +'" class="select-reject">'+getLocalize('des_title22')+'</a></li>':'');
							htmlString +='</ul>';
							htmlString += 			'</div>';
							htmlString += '<div class="date-content item">';

							if(item.ev152==='DEC'||item.ev152==='WAI'||item.ev152==='CON'){
								htmlString += 				'<p>';
								htmlString += 					'<span class="span-date-create">'+getLocalize('des_title9')+'&nbsp: '+item.el159String+'&nbsp&nbsp</span>';
								htmlString += 				'</p>';
							}
							if(item.ev152==='WAI'||item.ev152==='CON'){
								htmlString += 				'<p>';
								htmlString += 					'<span class="span-date-create">'+getLocalize('des_title13')+'&nbsp: '+item.e200mg.el208String+'&nbsp&nbsp</span>';
								htmlString += 				'</p>';
							}
							if(item.ev152==='CON'){
								htmlString += 				'<p>';
								htmlString += 					'<span class="span-date-create">'+getLocalize('des_title20')+'&nbsp: '+item.el160String+'&nbsp&nbsp</span>';
								htmlString += 				'</p>';
							}
							htmlString +='</div>'
							htmlString += 			'<div class="info_function item">';

								htmlString += 				'<ul class="function" '+((item.ev152==='NEW' || item.ev152==='WAI' || item.ev152=='CON')?"style='display:none;'":"")+'>';
								htmlString += 					'<li class="item pc-editor-on-mysite">';
								htmlString += 						'<a class="cover-a-goto link-edit-of-cus" href='+ $("#contextPath").val()+ "/e"+ item.fe400d +"/evo-editer"+ '?editmode=element'+ '>';
								htmlString += 							'<div class="obutton-link-small-mysites hvr-shrink btn-funct-cover-new-page" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("edit")+'">';
								htmlString += 								'<i class="enjh-step2 fa fa-pencil"></i>';
								htmlString += 							'</div>';
								htmlString += 						'</a>';
								htmlString += 					'</li>';
								htmlString += 					'<li class="item">';
								htmlString += 							'<div siteIdd="'+ item.fe400d + '" fe150="'+item.id+'" class="btn-send-to-cus obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("des_title12")+'">';
								htmlString += 								'<i class="fa fa-paper-plane-o"></i>';
								htmlString += 							'</div>';
								htmlString += 					'</li>';
								htmlString += 					'<li class="item">';
								htmlString += 							'<div siteIdc="'+ item.fe400 + '" class="btn-update-site obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("update")+'">';
								htmlString += 								'<i class="fa fa-refresh"></i>';
								htmlString += 							'</div>';
								htmlString += 					'</li>';
								
								htmlString += 				'</ul>';
								
							htmlString += 			'</div>';
							htmlString += 		'</div>';
							htmlString += 	'</div>';
							htmlString += '</div>';
						}
						htmlString += '</div>';
						$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').html(htmlString);
						if(parseInt($('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.total-page-content span').html())!== response.result.length){
							$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.total-page-content span').html(response.result.length);
						}
						
						if(parseInt($('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.NEW').html())!=stt1){
							$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.NEW').html(stt1);
							$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.NEW').parent().css('display','inline-block')
							if(stt1==0){
								$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.NEW').parent().hide();
							}
						}
						if(parseInt($('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.DEC').html())!=stt2){
							$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.DEC').html(stt2);
							$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.DEC').parent().css('display','inline-block');
							if(stt2==0){
								$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.DEC').parent().hide();
							}
						}
						if(parseInt($('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.WAI').html())!=stt3){
							$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.WAI').html(stt3);
							$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.WAI').parent().css('display','inline-block');
							if(stt3==0){
								$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.WAI').parent().hide();
							}
							
						}
						if(parseInt($('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.CON').html())!=stt4){
							$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.CON').html(stt4);
							$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.CON').parent().css('display','inline-block');
							if(stt4==0){
								$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').parent('li').find('.CON').parent().hide();
							}
							
						}
					}
					else {
						$('.status-value').html(0);
						var nodata = '<span class="no-data-found">'+ getLocalize('no_data') + '</span>';
						$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result[0].fo100c+'"]').html(nodata);
					}
				},
				error : function(e){
					showGrowlMessageAjaxError();
				}
			});
		},
		confirmRequestOfCus: function(pe150, pe400){
			$.ajax({
				type : "POST",
				url : encodeURI("designerBean.confirmRequestOfCus"),
				data : {
					pe150 : pe150,
					pe400 : pe400
				},
				success : function(respone){
					if(respone.result){
						var item = JSON.parse(respone.result);
						showGrowlMessageSuccess();
						var stt = JSON.parse(designerSite.setStatusForDes('DEC'));
						var total = parseInt($('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('li').find('.DEC').html());
						$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('li').find('.DEC').html(total+1);
						if(total==0){
							$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('li').find('.DEC').parent('.label-stt').css('display','inline-block');
						}
						var total = parseInt($('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('li').find('.NEW').html());
						if(total == 1)
						{
							$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('li').find('.NEW').parent('.label-stt').hide();
						}
						$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('li').find('.NEW').html(total-1);
							
						$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('.item-data').find('ul').show();
						$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('.item-data').find('a.cover-a-goto').attr('href',$("#contextPath").val()+ "/e"+ item.webIdNew +"/evo-editer"+ '?editmode=element');
						$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('.item-data').find('.btn-send-to-cus').attr('siteidd',item.webIdNew);
						var htmlStr= $('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('.item-data').find('.date-content').html();
						htmlStr += 				'<p>';
						htmlStr += 					'<span class="span-date-create">'+getLocalize("des_title9")+'&nbsp: '+item.startDate+'&nbsp&nbsp</span>';
						htmlStr += 				'</p>';
						$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('.item-data').find('.date-content').html(htmlStr);
						var htmlString ='<button class="dropdown-toggle" type="button" id="dropdown-cus" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="background-color: '+stt.color+';">'+stt.text+
												'<span class="caret"></span>'+
										 '</button>'+
										 '<ul class="dropdown-menu dropdown-site-custom" aria-labelledby="dropdown-cus">'+
											 '<li><a fe150="'+pe150 +'" fe400="'+pe400 +'" class="select-reject">'+getLocalize('des_title22')+'</a></li>'+
										 '</ul>';
						$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-confirm[fe150="'+pe150+'"]').parents('.stt-site-cus').html(htmlString);
						var fo100d = $('.data-compoment .content .site-designer-detail ul li:visible .list-site-cus').attr('cusid');
						var nameSite = $('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-reject[fe150="'+pe150+'"]').closest('.item-data').find('.title_page').html();
						notification.insertNotify(fo100d, notificationKey.BONEVO_DES_CONFIRMED_REQUEST, pe150, nameSite);
					}else{
						showGrowlMessageAjaxError();
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
			
			

		},
		submitToCustomer: function(webId, ev202, fe150){
			$.ajax({
				type : "POST",
				url : encodeURI("designerBean.submitToCustomer"),
				data : {
					fe400s : webId,
					ev202 : ev202,
					fe150 : fe150,
				},
				success : function(response) {
					if(response.result)
						{
							var stt = JSON.parse(designerSite.setStatusForDes('WAI'));
							var total = parseInt($('.btn-send-to-cus[siteidd="'+webId+'"]').parents('li').find('.DEC').html());
							if(total == 1)
							{
								$('.btn-send-to-cus[siteidd="'+webId+'"]').parents('li').find('.DEC').parent('.label-stt').hide();
							}
							$('.btn-send-to-cus[siteidd="'+webId+'"]').parents('li').find('.DEC').html(total-1);
							
							var total = parseInt($('.btn-send-to-cus[siteidd="'+webId+'"]').parents('li').find('.WAI').html());
							$('.btn-send-to-cus[siteidd="'+webId+'"]').parents('li').find('.WAI').html(total+1);
							if(total == 0){
								$('.btn-send-to-cus[siteidd="'+webId+'"]').parents('li').find('.WAI').parent('.label-stt').css('display','inline-block');
							}
							var htmlStr= $('.btn-send-to-cus[siteidd="'+webId+'"]').parents('.data').find('.date-content').html();
							htmlStr += 				'<p>';
							htmlStr += 					'<span class="span-date-create">'+getLocalize('des_title13')+'&nbsp: '+response.result+'&nbsp&nbsp</span>';
							htmlStr += 				'</p>';
							$('.btn-send-to-cus[siteidd="'+webId+'"]').parents('.data').find('.date-content').html(htmlStr);
							var htmlString ='<button class="dropdown-toggle" type="button" id="dropdown-cus" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="background-color: '+stt.color+';">'+stt.text+
													'<span class="caret"></span>'+
											 '</button>'+
											 '<ul class="dropdown-menu dropdown-site-custom" aria-labelledby="dropdown-cus">'+
												 //'<li><a fe150="'+fe150 +'" fe400="" class="select-confirm">'+getLocalize('des_title14')+'</a></li>'+
											 '</ul>';
							$('.btn-send-to-cus[siteidd="'+webId+'"]').parents('.data').find('.stt-site-cus').html(htmlString);
							$('.btn-send-to-cus[siteidd="'+webId+'"]').parents('.info_function').hide();
							$('#qb-dlg-content-text-send').dialog('close');
							showGrowlMessageSuccess();
							var fo100d = $('.data-compoment .content .site-designer-detail ul li:visible .list-site-cus').attr('cusid');
							var nameSite = $('.btn-send-to-cus[siteidd="'+webId+'"]').parents('.data').find('.title_page').html();
							notification.insertNotify(fo100d, notificationKey.BONEVO_DES_SEND_DESIGN, fe150, nameSite);
						}
					else
						showGrowlMessageError();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			})
		},
		openDialogSubmitCus: function(fe150,siteId){
			designerSite.fe150=fe150;
			designerSite.siteId=siteId;
			if(!this.inited)
			{
//				$('#qb-dlg-content-text-send').webDialog(450);
//				$(document).on('click','#qb-dlg-content-text-send .dialog-send-text-content .bottom-menu .btn-send-text',function(){
//					var ev202 = $('#comment').val();
//					var pe150 = designerSite.fe150;
//					var pe400 = designerSite.siteId;
//					designerSite.submitToCustomer(pe400, ev202, pe150);
//				})
				$('#qb-dlg-content-text-send').webDialog(700);
				$('#qb-dlg-content-text-send').parent().find('.ui-dialog-titlebar').remove();
				$(document).on('click','#qb-dlg-content-text-send .cmt-of-cus-bottom-menu .btn-send-text-cus',function(){
						var ev202 = $('#qb-dlg-content-text-send .cmt-of-cus-wapper .main-content .ip-comment-of-cus').val();
						var pe150 = designerSite.fe150;
						var pe400 = designerSite.siteId;
						designerSite.submitToCustomer(pe400, ev202, pe150);
				});
				$(document).on('click','#qb-dlg-content-text-send .cmt-of-cus-bottom-menu .btn-cancel-send',function(){
					$('#qb-dlg-content-text-send').dialog('close');
				});
				this.inited = true;
			}
			var image = $('.site-designer-detail li:visible .content-item-cus .cus-item-avatar img').attr('src');
			var name = $('.site-designer-detail li:visible .content-item-cus .cus-item-name p').html();
			name = name.split("<span>")[0];
			var email = $('.site-designer-detail li:visible .content-item-cus .cus-item-name p span').html();
			email = email.replace("(","").replace(")","")
			$('#qb-dlg-content-text-send .cmt-of-cus-wapper .designer-item .designer-avatar img').attr('src',image);
			$('#qb-dlg-content-text-send .cmt-of-cus-wapper .designer-item .designer-info p.designer-name').html(name);
			$('#qb-dlg-content-text-send .cmt-of-cus-wapper .designer-item .designer-info p.designer-email').html(email);
			
			$('#qb-dlg-content-text-send').dialog('open');
		},
		rejectSiteOfCus : function(pe150,pe400){
			$.ajax({
				type: "POST",
				url : encodeURI("designerBean.rejectSiteOfCus"),
				data: {pe150 : pe150},
				success : function(respone){
					var fo100d = $('.data-compoment .content .site-designer-detail ul li:visible .list-site-cus').attr('cusid');
					var nameSite = $('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-reject[fe150="'+pe150+'"]').closest('.item-data').find('.title_page').html();
					notification.insertNotify(fo100d, notificationKey.BONEVO_DES_REJECT_CUS, pe150, nameSite);
					var total = parseInt($('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-reject[fe150="'+pe150+'"]').parents('li').find('.NEW').html());
					if(total == 1)
					{
						$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-reject[fe150="'+pe150+'"]').parents('li').find('.NEW').parent('.label-stt').hide();
					}
					$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-reject[fe150="'+pe150+'"]').parents('li').find('.NEW').html(total-1);
					total = parseInt($('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-reject[fe150="'+pe150+'"]').parents('li').find('.total-page-content span').html());
					$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-reject[fe150="'+pe150+'"]').parents('li').find('.total-page-content span').html(total-1)
					$('.body-mysite .data-compoment .data_page .stt-site-cus .dropdown-site-custom a.select-reject[fe150="'+pe150+'"]').parents('.item-data').remove();
					showGrowlMessageSuccess();
				},
				error : function(err){
					showGrowlMessageAjaxError();
				}
			});
		},
		loadSiteOfCusDetail :function(pe150){
			$.ajax({
				type: 'GET',
				url: encodeUrl('notifyBean.listOfTabSitesOfCusDetail'),
				data:{pe150:pe150},
				success : function(response) {
					var item = response.result;
					if(item.m900c===null){
						mySites.loadMysite();
						return
					}
					$('.body-mysite .obutton-link .icon-customer-icon').show();
					$('.body-mysite .obutton-link .icon-item-funct-2').hide();
					mySites.isLoad = 'cs';
					$('.panel-des-function .tab-item').removeClass('selected');
					$('.panel-des-function .tab-item.cussite').addClass('selected');
					
					if(item.totalCus > 1)
						$('.body-mysite .content-status .content .row .info-detail .status-name').html(getLocalize("mys_customer_2"));
					else 
						$('.body-mysite .content-status .content .row .info-detail .status-name').html(getLocalize("mys_customer"));
					var htmlString ='';
					htmlString +='<div class="site-designer-detail">';
					htmlString +='<ul>';
					htmlString +='<li>';
					htmlString +=	'<div class="wapper-cus">';
					htmlString +=		'<div class="content-item-cus clearfix container">';
					htmlString +=			'<div class="cus-name col-sm-4">';
					htmlString +=				'<div class="icon-back">';
					htmlString +=				'<i class="fa fa-angle-left back-list-detail"></i>';
					htmlString +=				'</div>';
					htmlString +=				'<div class="cus-item-avatar">';
					htmlString +=					'<img alt="" src="'+item.m900c.urlAvarta+'">';
					htmlString +=				'</div>';
					htmlString +=				'<div class="cus-item-name">';
					htmlString +=					'<p>'+item.m900c.nv100+' <span>'+((item.m900c.mv903Decrypt)?"("+item.m900c.mv903Decrypt+")":"")+'</span></p>';
					htmlString +=				'</div>';
					htmlString +=			'</div>';
					htmlString +=			'<div class="cus-item-date col-sm-2">';
					htmlString +=				'<span></span>';
					htmlString +=			'</div>';
					htmlString +=			'<div class="cus-item-status col-sm-4">';
					var sttNew = JSON.parse(designerSite.setStatusForDes('NEW'))
					var htmlStt1 = '<span class="label-stt" style="background-color: '+sttNew.color+';display:none" >'+sttNew.text+'<span class="notify-page-waiting NEW">0</span></span>';
					var sttDec = JSON.parse(designerSite.setStatusForDes('DEC'))
					var htmlStt2 = '<span class="label-stt" style="background-color: '+sttDec.color+';display:none" >'+sttDec.text+'<span class="notify-page-waiting DEC">0</span></span>';
					var sttWai = JSON.parse(designerSite.setStatusForDes('WAI'))
					var htmlStt3 ='<span class="label-stt" style="background-color: '+sttWai.color+';display:none" >'+sttWai.text+'<span class="notify-page-waiting WAI">0</span></span>';
					var sttCon = JSON.parse(designerSite.setStatusForDes('CON'))
					var htmlStt4 ='<span class="label-stt" style="background-color: '+sttCon.color+';display:none" >'+sttCon.text+'<span class="notify-page-waiting CON">0</span></span>';
					for(var j = 0 ; j< item.status.length;  j++)
					{
						if(item.status[j].id=='NEW'){
							htmlStt1 = '<span class="label-stt" style="background-color: '+sttNew.color+';display:inline-block" >'+sttNew.text+'<span class="notify-page-waiting NEW">'+item.status[j].total+'</span></span>';
						}
						if(item.status[j].id=='DEC'){
							htmlStt2 = '<span class="label-stt" style="background-color: '+sttDec.color+';display:inline-block" >'+sttDec.text+'<span class="notify-page-waiting DEC">'+item.status[j].total+'</span></span>';
						}
						if(item.status[j].id=='WAI'){
							htmlStt3 ='<span class="label-stt" style="background-color: '+sttWai.color+';display:inline-block" >'+sttWai.text+'<span class="notify-page-waiting WAI">'+item.status[j].total+'</span></span>';
						}
						if(item.status[j].id=='CON'){
							htmlStt4 ='<span class="label-stt" style="background-color: '+sttCon.color+';display:inline-block" >'+sttCon.text+'<span class="notify-page-waiting CON">'+item.status[j].total+'</span></span>';
						}
					}	
					htmlString += htmlStt1;
					htmlString += htmlStt2;
					htmlString += htmlStt3;
					htmlString += htmlStt4;
					htmlString +=			'</div>';
					htmlString +=			'<div class="cus-item-total-page col-sm-2">';
					htmlString +=				'<div class="total-page-content">';
					htmlString +=				'<span>'+item.totalSites+'</span>';
					htmlString +=				'<img alt="" src="html/images/my-site-total-page.png">';
					htmlString +=				'</div>';
					
					htmlString +=			'</div>';
					htmlString +=		'</div>';
					htmlString +=	'</div>';
					htmlString +=	'<div class="list-site-cus" cusid="'+item.m900c.id+'"></div>';
					htmlString +='</li>';
					htmlString +='</ul>';
					htmlString +='</div>';
					$(".body-mysite .data-compoment .content .data-content").html(htmlString);
					$('.status-value').html(item.totalCus);
					htmlString ='';
					
					
					var siteName = item.e400c.ev405;
					var sitePath = item.e400c.ev405;
					var url =  util.contextPath()+'/'+sitePath;
					var thumb = 'html/images/noimage_03.png';
					if(item.e400c.ev403 && item.e400c.ev403.length > 0){
						thumb = item.e400c.ev403;
					}
					htmlString+= '<div class="container site-detail">'
					htmlString +='<div class="item-data" siteId="'+ item.fe400 + '">';
					htmlString +=	'<a class="cover-a-goto" href='+ $("#contextPath").val()+ "/e"+ item.fe400 +"/evo-editer"+ '?editmode=element'+ '>';
					htmlString += 		'<div class="col-sm-3 content-img hvr-shrink full-width-mysite-on-mobile">';
					htmlString += 			'<img class="img_page"  style="cursor: pointer;" src="'+ thumb + '" urledit="'+ $("#contextPath").val()+ '/e'+ item.fe400 + "/" + sitePath + '" />';
					htmlString += 		'</div>';
					htmlString += 	'</a>';
					htmlString +=	'<div class="col-sm-9 content-data data_page full-width-mysite-on-mobile">';
					htmlString += 		'<div class="data">';
					htmlString += 			'<div class="title_page item">'+ siteName + '</div>';
					htmlString += 			'<div class="info_page item">';
					htmlString += 				'<p>';
					htmlString += 					'<span class="span-date-create" style="display:none;">'+getLocalize("mys_create")+'&nbsp: '+item.e400c.el448String+'&nbsp&nbsp</span><span class="span-space" style="display:none;"> | </span><span class="span-date-update">'+getLocalize("mys_lmodify")+'&nbsp: '+item.e400c.el446String+'&nbsp&nbsp </span>';
					htmlString += 				'</p>';
					htmlString += 				'<p>';
					htmlString += 					'URL: <a href='+url+' style="color: #EA9138; cursor: pointer;">'+url+'</a>';
					htmlString += 				'</p>';
					htmlString += 			'</div>';
					htmlString += 			'<div class="stt-site-cus item">';

					var stt = JSON.parse(designerSite.setStatusForDes(item.ev152))
					htmlString +='<button class="dropdown-toggle" type="button" id="dropdown-cus" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="background-color: '+stt.color+';">'+stt.text+
									 ' <span class="caret"></span>'+
								 '</button>'+
								 '<ul class="dropdown-menu dropdown-site-custom" aria-labelledby="dropdown-cus">';
					htmlString += ((item.ev152=='NEW')?'<li><a fe150="'+item.id +'" fe400="'+item.fe400 +'" class="select-confirm">'+getLocalize('des_title14')+'</a></li>':'');
					htmlString += ((item.ev152=='NEW' || item.ev152=='DEC')?'<li><a fe150="'+item.id +'" fe400="'+item.fe400 +'" class="select-reject">'+getLocalize('des_title22')+'</a></li>':'');
					htmlString +='</ul>';
					htmlString += 			'</div>';
					htmlString += '<div class="date-content item">';

					if(item.ev152==='DEC'||item.ev152==='WAI'||item.ev152==='CON'){
						htmlString += 				'<p>';
						htmlString += 					'<span class="span-date-create">'+getLocalize('des_title9')+'&nbsp: '+item.el159String+'&nbsp&nbsp</span>';
						htmlString += 				'</p>';
					}
					if(item.ev152==='WAI'||item.ev152==='CON'){
						htmlString += 				'<p>';
						htmlString += 					'<span class="span-date-create">'+getLocalize('des_title13')+'&nbsp: '+item.e200mg.el208String+'&nbsp&nbsp</span>';
						htmlString += 				'</p>';
					}
					if(item.ev152==='CON'){
						htmlString += 				'<p>';
						htmlString += 					'<span class="span-date-create">'+getLocalize('des_title20')+'&nbsp: '+item.el160String+'&nbsp&nbsp</span>';
						htmlString += 				'</p>';
					}
					htmlString +='</div>'
					htmlString += 			'<div class="info_function item">';

						htmlString += 				'<ul class="function" '+((item.ev152==='NEW' || item.ev152==='WAI' || item.ev152=='CON')?"style='display:none;'":"")+'>';
						htmlString += 					'<li class="item pc-editor-on-mysite">';
						htmlString += 						'<a class="cover-a-goto link-edit-of-cus" href='+ $("#contextPath").val()+ "/e"+ item.fe400d +"/evo-editer"+ '?editmode=element'+ '>';
						htmlString += 							'<div class="obutton-link-small-mysites hvr-shrink btn-funct-cover-new-page" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("edit")+'">';
						htmlString += 								'<i class="enjh-step2 fa fa-pencil"></i>';
						htmlString += 							'</div>';
						htmlString += 						'</a>';
						htmlString += 					'</li>';
						htmlString += 					'<li class="item">';
						htmlString += 							'<div siteIdd="'+ item.fe400d + '" fe150="'+item.id+'" class="btn-send-to-cus obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("des_title12")+'">';
						htmlString += 								'<i class="fa fa-paper-plane-o"></i>';
						htmlString += 							'</div>';
						htmlString += 					'</li>';
						htmlString += 					'<li class="item">';
						htmlString += 							'<div siteIdc="'+ item.fe400 + '" class="btn-update-site obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("update")+'">';
						htmlString += 								'<i class="fa fa-refresh"></i>';
						htmlString += 							'</div>';
						htmlString += 					'</li>';
						
						htmlString += 				'</ul>';
						
					htmlString += 			'</div>';
					htmlString += 		'</div>';
					htmlString += 	'</div>';
					htmlString += '</div>';
				htmlString += '</div>';
				$('.body-mysite .data-compoment .content .list-site-cus[cusid="'+response.result.fo100c+'"]').html(htmlString);
					
					
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		}
	}
}());