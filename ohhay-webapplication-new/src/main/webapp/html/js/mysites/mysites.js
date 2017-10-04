/**
 * @author ThoaiNH create Nov 20, 2015 site manager
 */
(function() {
	mySites = {
		isLoad : "ms",// kiem tra xem dang load mysties(ms) hay customer
						// sites(cs)
		totalPage : 0,
		offsetPage : 0,
		limitPage : 6,
		init : function() {
			mySites.onloadMySite();
			mySites.paginationMySite = new PaginationObj("mysite");
			mySites.eventListener();
		},
		/*
		 * onload page
		 */
		onloadMySite : function(){
			window.onload = function(e) {
				var pe150 = (util.getUrlParameter('pe150',document.URL));
				if(pe150){
					designerSite.loadSiteOfCusDetail(pe150);
					return;
				}
				// read hash from url
				var hash = window.location.hash;
				var language = mySites.getURLParameter("language");
				// search query search
                var querySearch = mySites.getURLParameter("query-search");
                /* case page */
                // check hash url have pagination
                if(hash.indexOf("=")==-1){
                	// load mysite with pvsearch
                	mySites.loadMysite(querySearch,function(){
                		console.log("Done load");
                		((querySearch!=null && querySearch!="" ? "" : querySearch=""));
    					window.history.replaceState("object or string", "Title", "mysites?language="+language+((querySearch!="" ? "&query-search="+querySearch : ""))+((hash!="")? hash : ""));
                    	((querySearch!=null && querySearch!="" ? $('.panel-des-function .ip-search-site').val(querySearch) : ""));
                    	mySites.timeOutPage();
                	});
                	return;
                }
                /* case not page */
                // get number page
                var numberPage = hash.substring(hash.indexOf("=")+1,hash.length);
                // set offset page
                mySites.offsetPage = (parseInt(numberPage-1)==0 ? 0 : parseInt(numberPage-1)*6);
            	window.location.hash = '#page=1';
                ((querySearch!=null && querySearch!="" ? "" : querySearch=""));
				window.history.replaceState("object or string", "Title", "mysites?language="+language+((querySearch!="" ? "&query-search="+querySearch : ""))+((hash!="")? hash : ""));
                mySites.loadMysite(querySearch,function(){
                	// console.log(mySites.totalPage);
                    ((querySearch!=null && querySearch!="" ? $('.panel-des-function .ip-search-site').val(querySearch) : ""));
                	/*
					 * pagination page
					 */
                     mySites.timeOutPage();
                });
            };
		},
		eventListener : function() {
			$(document).on('click', '#btn-create-new', function() {
				mySites.checkRightCreateWeb({
					action: function(){
						mySites.create();
					}
				});
			});
			/*
			 * $(document).on('click', '#btn-create-new-re', function() {
			 * mySites.checkRightCreateWeb({ action: function(){
			 * mySites.createResponsiveSite(); } }); });
			 */
			$(document).on('click', '.item .btn-delete-site', function() {
				var self = $(this).closest('.item-data');
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					mySites.deleteSite(self);
				});
			});
			// click outside dialog will close
			$(document).on('click','.ui-widget-overlay',function(){
				var zIndex = parseInt($(this).css('z-index'));
				$(".qb-dlg-tool").each(function(){
					if(parseInt($(this).parent('.ui-dialog.ui-widget-content').css('z-index'))==(zIndex+1)){
						$(this).dialog('close');
					}
				});
			});
			/*
			 * event click cover image
			 */
			
			$(document).on('click', '.btn-duplicate', function() {
				var webId = $(this).attr('siteId');
				mySites.checkRightCreateWeb({
					action: function(){
						mySites.duplicate(webId);
					}
				});
			});
			
			$(document).on('click', '.btn-view-chart', function() {
				var webId = $(this).attr('siteId');
				var webName = $(this).closest('.item-data').find('.data_page .data .title_page').html();
				chartAjaxNew.open({
					webId:webId,
					webName:webName,
				});
			});
			$(document).on('click', '.btn-share-for-designer', function() {
				if($(this).attr('isShared')==='false'){
					var webId = $(this).parents('.item-data').attr('siteId');
					customerSite.showDialogChooseDesigner(webId);
				}
			});
			// on click mysite or customer sites
			$(document).on('click', '.panel-des-function .tab-item', function() {
				$('.panel-des-function .tab-item').removeClass('selected');
				$(this).addClass('selected');
				if($(this).hasClass('mysite'))
				{
					mySites.loadMysite(undefined,function(){
						$('.body-mysite .obutton-link .icon-customer-icon').hide();
						$('.body-mysite .obutton-link .icon-item-funct-2').show();
						mySites.isLoad= 'ms';
						mySites.timeOutPage();
					});
				}
				else
				{
					designerSite.loadCussite();
					$('.body-mysite .obutton-link .icon-customer-icon').show();
					$('.body-mysite .obutton-link .icon-item-funct-2').hide();
					mySites.isLoad = 'cs';
				}
					
			});
			$(document).ajaxComplete(function(){
				$('[data-toggle="tooltip"]').tooltip({
				    trigger : 'hover'
				})
			});
			
			// static all site of user
			$(document).on('click','.body-mysite .content-status .content .row .btn-funct-data .static-all',function(){
				var webId = 0;
				chartAjaxNew.open({
					webId:webId,
				});
			});
			
			// search site
			$(document).on('click','.panel-dserializees-function .btn-search-site',function(){
				var hash = window.location.hash;
				var valSearch = $('.panel-des-function .ip-search-site').val();
				if(hash.indexOf("=")!=-1){
					// get number page
					var numberPage = hash.substring(hash.indexOf("=")+1,hash.length);
		            // set offset page
// console.log(numberPage);
		            mySites.offsetPage = (parseInt(numberPage-1)==0 ? 0 : parseInt(numberPage-1)*6);
				}else
					mySites.offsetPage=0;
				
				var language = mySites.getURLParameter("language");
				var gethash = window.location.hash;
				if(mySites.isLoad==='ms')
					mySites.loadMysite($('.panel-des-function .ip-search-site').val());
				if(mySites.isLoad==='cs')
					designerSite.loadCussite($('.panel-des-function .ip-search-site').val());
				window.history.replaceState("object or string", "Title", "mysites?language="+language+((valSearch!="" ? "&query-search="+valSearch : ""))+((gethash!="")? gethash : ""));
				mySites.timeOutPage();
			});
			$(document).on('keypress','.panel-des-function .ip-search-site',function(e){
				var hash = window.location.hash;
				var valSearch = $('.panel-des-function .ip-search-site').val();
				if(valSearch!=null && valSearch!=""){
					 mySites.offsetPage = 0;
					 if(hash.indexOf("=")!=-1)
						 window.location.hash = '#page=1';
				}
				var language = mySites.getURLParameter("language");
				var gethash = window.location.hash;
				if(e.which==13){
					if(mySites.isLoad==='ms'){
						mySites.loadMysite($('.panel-des-function .ip-search-site').val(),function(){
							console.log("total page : "+mySites.totalPage);
							mySites.timeOutPage();
						});
					}
					if(mySites.isLoad==='cs')
						designerSite.loadCussite($('.panel-des-function .ip-search-site').val());
					window.history.replaceState("object or string", "Title", "mysites?language="+language+((valSearch!="" ? "&query-search="+valSearch : ""))+((gethash!="")? gethash : ""));
					
				}
				
			});
			/**
			 * Tunt 22/11/2016 event remove designer from web
			 */
			$(document).on('click','.body-mysite .data-compoment .data_page .data .designer-content .info-status .dropdown-custom a.select-remove-des',function(){
				var fe150 = ($(this).attr('fe150'))
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					mySites.removeDesignerFromWeb(fe150);
				});
			});
			$(document).on('click','.body-mysite .data-compoment .data_page .data .designer-content .info-des',function(){
				mySites.openDialogDesignerInfo($(this).attr('fo100'));
			})
		},addUrlParam: function(param, value){
		   var url = location.href;
		   if (location.search.indexOf(param) != -1) return url;
		   var hash = location.hash, sep = url.indexOf('?') == -1 ? '?' : '&';
		   return url.replace(hash,'') + sep + encodeURIComponent(param) + (value ? '=' + encodeURIComponent(value) : '') + hash;
		},
		/*
		 * 
		 */
		getURLParameter: function(name) {
			  return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [null, ''])[1].replace(/\+/g, '%20')) || null;
		},
		/*
		 * load my sites
		 */
		loadMysite : function(pvSearch,callback) {
			$.ajax({
				url : encodeUrl("mysitesBean.load"),
				data : {
					pvSearch: ((pvSearch==undefined) ? "" : pvSearch),
					offset: mySites.offsetPage,
					limit: mySites.limitPage
				},
				success : function(response) {
					var htmlString = "";
					if(response.result && response.result.length > 1)
						$('.body-mysite .content-status .content .row .info-detail .status-name').html(getLocalize("mys_site_2"));
					else 
						$('.body-mysite .content-status .content .row .info-detail .status-name').html(getLocalize("mys_site"));
					if (response.result && response.result.length > 0) {
						mySites.totalPage = response.result[0].sizeRowss;
//						console.log("total page : "+mySites.totalPage);
						htmlString+= '<div class="container site-detail">'
						for (index = 0 ; index<response.result.length; index++) {
							var width = $(window).width();
							var item = response.result[index];
							var siteName = item.ev405;
							var sitePath = item.ev405;
							var url =  util.contextPath()+'/'+sitePath;
							var thumb = 'html/images/noimage_03.png';
							if(item.ev403 && item.ev403.length > 0){
								thumb = item.ev403;
							}
							// alert(thumb);serialize
							htmlString +='<div class="item-data" siteId="'+ item.id + '">';
							if(width>768)
								htmlString +=	'<a class="cover-a-goto" href='+ $("#contextPath").val()+ "/e"+ item.id +"/evo-editer"+ '?editmode=element'+ '>';
							else
								htmlString +=	'<a class="cover-a-goto" href='+ $("#contextPath").val()+ "/e"+ item.id +"/evo-editer"+ '>';
							htmlString += 		'<div class="col-sm-3 content-img hvr-shrink full-width-mysite-on-mobile">';
							htmlString += 				'<img class="img_page"  style="cursor: pointer;" src="'+ thumb + '" urledit="'+ $("#contextPath").val()+ '/e'+ item.id + "/" + sitePath + '" />';
							htmlString += 		'</div>';
							htmlString += 	'</a>';
							htmlString +=	'<div class="col-sm-9 content-data data_page full-width-mysite-on-mobile">';
							htmlString += 		'<div class="data">';
							htmlString += 			'<div class="title_page item">'+ siteName + '</div>';
							htmlString += 			'<div class="info_page item">';
							htmlString += 				'<p>';
							htmlString += 					'<span class="span-date-create">'+getLocalize("mys_create")+'&nbsp: '+item.el448String+'&nbsp&nbsp</span><span class="span-space"> | </span><span class="span-date-update">&nbsp&nbsp'+getLocalize("mys_modify")+'&nbsp: '+item.el446String+'&nbsp&nbsp </span>';
							htmlString += 				'</p>';
							htmlString += 				'<p>';
							htmlString += 					'URL: <a href='+url+' style="color: #EA9138; cursor: pointer;">'+url+'</a>';
							htmlString += 				'</p>';
							htmlString += 			'</div>';
							htmlString += 			'<div class="info_language_page item">';
							htmlString += 			'</div>';
							htmlString += 			'<div class="info_function item">';
							htmlString += 				'<ul class="function">';
							htmlString += 					'<li class="item">';
							htmlString += 						'<a class="cover-a-goto" href='+ $("#contextPath").val()+ "/e"+ item.id +"/evo-editer"+ '?editmode=element'+ '>';
							htmlString += 							'<div class="goto-pc-editor-on-mysite obutton-link-small-mysites hvr-shrink btn-funct-cover-new-page" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("edit")+'">';
							htmlString += 								'<i class="enjh-step2 fa fa-pencil"></i>';
							htmlString += 							'</div>';
							htmlString += 						'</a>';
							htmlString += 					'</li>';
							htmlString +=					'<li class="item">';
							htmlString += 						'<a href='+ $("#contextPath").val()+ '/websetting/'+ item.id+ '>';
							htmlString += 							'<div class="goto-seo-on-mysite btn-funct-cover-new-page obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("setting")+'">';
							htmlString += 								'<i class="enjh-step3 fa fa-cogs"></i>';
							htmlString += 							'</div>';
							htmlString += 						'</a>';
							htmlString += 					'</li>';
							htmlString += 					'<li class="item ">';
							htmlString += 							'<div siteId="'+ item.id + '" class="duplicate-on-mysite btn-duplicate obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("setting_duplicate")+'">';
							htmlString += 								'<i class="fa fa-files-o"></i>';
							htmlString += 							'</div>';
							htmlString += 					'</li>';
							htmlString += 					'<li class="item">';
							htmlString += 							'<div siteId="'+ item.id + '" class="view-chart-on-mysite btn-view-chart obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("chart_title")+'">';
							htmlString += 								'<i class="enjh-step4 fa fa-line-chart"></i>';
							htmlString += 							'</div>';
							htmlString += 					'</li>';
							htmlString += 					'</li>';
							htmlString += 					'<li class="item">';
							htmlString += 						'<div class="delete-on-mysite obutton-link-small-mysites btn-delete-site hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("delete")+'">';
							htmlString += 							'<i class="fa fa-trash-o"></i>';
							htmlString += 						'</div>';
							htmlString += 					'</li>';
							htmlString += 				'</ul>';
							htmlString += 			'</div>';
							htmlString += 			'<div class="wapper-designer">';
							if(item.m900d){
								var stt = JSON.parse(designerSite.setStatus(item.ev152));
								htmlString += 	'<div class="designer-content current">'+
													'<div class="info-des" fo100="'+item.m900d.id+'">'+
													'<span class="avt-des"><img alt="" src="'+item.m900d.urlAvarta+'"></span>'+
													'<div class="info-des-content">';
								htmlString +=		'<h5>'+
															'<span class="name-des">'+item.m900d.nv100+'</span><span class="info-des-name">'+((item.m900d.timeLastEdit==null)?'':getLocalize("des_title6"))+'</span>'+
														'</h5>';
								var str = "";
								if(item.m900d.timeLastEdit != null){
									str = item.m900d.timeLastEdit;
									var arr = str.split('h');
									if(arr[0]!=undefined && arr[0]=='0'){
										str = arr[1];
									}
								}
								htmlString += 		'<h6>'+((item.m900d.timeLastEdit==null || str=='')?'':getLocalize("des_title7")+' '+str)+'</h6>';
								htmlString += 		'<h6 style="color: rgb(0, 0, 0); font-weight: bold; padding-top: 12px; padding-bottom: 10px; '+((item.totalDesignerHistory>0)?'':'display: none;')+'"> '+getLocalize("mys_title15")+'  <span class="site-ver">'+item.totalDesignerHistory+'</span>.0</h6>';
								htmlString +=	'</div>'+
												  '</div>'+
												  '<div class="info-status dropdown">'+
													'<button class="dropdown-toggle" type="button" id="dropdownStt" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="background-color: '+stt.color+';">'+stt.text+
														' <span class="caret"></span>'+
													'</button>'+
													'<ul class="dropdown-menu dropdown-custom" aria-labelledby="dropdownStt">'+
														'<li><a fe150="'+item.fe150+'" class="select-remove-des">'+getLocalize("des_title15")+'</a></li>';
								htmlString +=		'</ul>';
								
								htmlString +=	'</div>' ;
									htmlString += 	'<div class="function-customer">'+
														'<ul>';
									if(item.totalDesignerHistory>0){
										htmlString += 					'<li class="item">';
										htmlString += 						'<div class="obutton-link-small-mysites hvr-shrink btn-show-history" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("mys_title21")+'">';
										htmlString += 							'<i class="fa fa-list-ul"></i>';
										htmlString += 						'</div>';
										htmlString += 					'</li>';
										
									}
									if(item.ev152=='WAI'){
										htmlString +=		'<li>'+
																'<div class="fun-cus-item">'+
																	'<a href="'+ $("#contextPath").val()+'/e'+ item.fe400d +'/preview">'+
																		'<div class="obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("setting_preview")+'">'+
																			'<i class="enjh-step2 fa fa-eye"></i>'+
																		'</div>'+
																	'</a>'+
																'</div>'+
															'</li>'+
															'<li>'+
																'<div class="fun-cus-item">'+
																		'<div class="obutton-link-small-mysites hvr-shrink btn-cus-apply" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("des_title18")+'" fo100Des="'+item.m900d.id+'" fe400Cus="'+item.id+'" fe400Des="'+item.fe400d+'" fe150="'+item.fe150+'">'+
																			'<i class="enjh-step2 fa fa-check"></i>'+
																		'</div>'+
																'</div>'+
															'</li>'+
															'<li>'+
																'<div class="fun-cus-item">'+
																		'<div class="obutton-link-small-mysites hvr-shrink btn-cus-reject" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("des_title19")+'" fe150="'+item.fe150+'">'+
																			'<i class="enjh-step2 icon-light-bulb"></i>'+
																		'</div>'+
																'</div>'+
															'</li>';
									}
								htmlString +=						'</ul>';
								htmlString +=					'</div>';
								htmlString +='</div>';
							}
							if(item.m900l){
								var stt = JSON.parse(designerSite.setStatus(item.ev152l));
								htmlString += 	'<div class="designer-content past" style="display: '+((item.m900d)?"none":"block")+';">'+
													'<div class="info-des" fo100="'+item.m900l.id+'">'+
													'<span class="avt-des"><img alt="" src="'+item.m900l.urlAvarta+'"></span>'+
													'<div class="info-des-content">';
								htmlString +=		'<h5>'+
															'<span class="name-des">'+item.m900l.nv100+'</span><span class="info-des-name">'+((item.m900l.timeLastEdit==null)?'':getLocalize("des_title6"))+'</span>'+
														'</h5>';
								var str = "";
								if(item.m900l.timeLastEdit != null){
									str = item.m900l.timeLastEdit;
									var arr = str.split('h');
									if(arr[0]!=undefined && arr[0]=='0'){
										str = arr[1];
									}
								}
								htmlString += 		'<h6>'+((item.m900l.timeLastEdit==null || str=='')?'':getLocalize("des_title7")+' '+str)+'</h6>';
								htmlString += 		'<h6 style="color: rgb(0, 0, 0); font-weight: bold; padding-top: 12px; padding-bottom: 10px; '+((item.totalDesignerHistory>0)?'':'display: none;')+'"> '+getLocalize("mys_title15")+'  <span class="site-ver">'+item.totalDesignerHistory+'</span>.0</h6>';
								htmlString +=	'</div>'+
												  '</div>'+
												  '<div class="info-status dropdown">'+
													'<button class="dropdown-toggle" type="button" id="dropdownStt" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="background-color: '+stt.color+';">'+stt.text+
														' <span class="caret"></span>'+
													'</button>'+
													'<ul class="dropdown-menu dropdown-custom" aria-labelledby="dropdownStt">';
								htmlString +=		'</ul>';
								
								htmlString +=	'</div>' ;
									htmlString += 	'<div class="function-customer">'+
														'<ul>';
									if(item.totalDesignerHistory>0){
										htmlString += 					'<li class="item">';
										htmlString += 						'<div class="obutton-link-small-mysites hvr-shrink btn-show-history" data-placement="bottom" data-toggle="tooltip" title="">';
										htmlString += 							'<i class="fa fa-list-ul"></i>';
										htmlString += 						'</div>';
										htmlString += 					'</li>';
										
									}
									if(item.ev152=='WAI'){
										htmlString +=		'<li>'+
																'<div class="fun-cus-item">'+
																	'<a href="'+ $("#contextPath").val()+'/e'+ item.fe400l +'/preview">'+
																		'<div class="obutton-link-small-mysites hvr-shrink" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("setting_preview")+'">'+
																			'<i class="enjh-step2 fa fa-eye"></i>'+
																		'</div>'+
																	'</a>'+
																'</div>'+
															'</li>'+
															'<li>'+
																'<div class="fun-cus-item">'+
																		'<div class="obutton-link-small-mysites hvr-shrink btn-cus-apply" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("des_title18")+'" fo100Des="'+item.m900d.id+'" fe400Cus="'+item.id+'" fe400Des="'+item.fe400l+'" fe150="'+item.fe150l+'">'+
																			'<i class="enjh-step2 fa fa-check"></i>'+
																		'</div>'+
																'</div>'+
															'</li>'+
															'<li>'+
																'<div class="fun-cus-item">'+
																		'<div class="obutton-link-small-mysites hvr-shrink btn-cus-reject" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("des_title19")+'" fe150="'+item.fe150l+'">'+
																			'<i class="enjh-step2 icon-light-bulb"></i>'+
																		'</div>'+
																'</div>'+
															'</li>';
									}
								htmlString +=			'</ul>';
								htmlString +=		'</div>';
								htmlString +='</div>';
							}
							
							htmlString += 			'</div>';
							htmlString += 		'</div>';
							htmlString += 	'</div>';
							htmlString += '</div>';
						}
						htmlString += '</div>';
						$(".body-mysite .data-compoment .content .data-content").html(htmlString);
						$('.status-value').html(mySites.totalPage);
						/*
						 * show tour guide
						 */
						if($('#flag-show-tour-guide').val() == 1)
						{
							$('#flag-show-tour-guide').val(0);
							mySitesTourGuide.init();
						}
					}
					else {
						$('.status-value').html(0);
						var nodata = '<span class="no-data-found">'+ getLocalize('no_data') + '</span>';
						$(".body-mysite .data-compoment .content .data-content").html(nodata);
						mySites.totalPage = 0;
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			}).done(function() { // use this
				console.log("total page : "+mySites.totalPage);
				if(callback!=undefined){
					callback(callback);
				}
			});
		},
		/*
		 * create new site
		 */
		create : function() {
			$.ajax({
				type : "POST",
				url : encodeUrl("mysitesBean.create"),
				data : {},
				success : function(response) {
					if(response.result.id > 0)
					{
						window.open(util.contextPath()+"/e"+response.result.id+"/evo-editer?editmode=element");
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * create responsive site
		 */
		createResponsiveSite : function() {
			$.ajax({
				type : "POST",
				url : encodeUrl("mysitesBean.createResponsiveSite"),
				data : {},
				success : function(response) {
					if(response.result.id > 0)
					{
						mySites.loadMysite();
						window.open(util.contextPath()+"/e"+response.result.id+"/evo-editer?editmode=element", '_blank');
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * delete site
		 */
		deleteSite : function(item) {
			var pe400 = item.attr("siteId");
			$.ajax({
				type : "POST",
				url : encodeUrl("mysitesBean.remove"),
				data : {
					pe400 : pe400
				},
				success : function(response) {
					item.slideUp();
					var total = parseInt($('.status-value').html());
					$('.status-value').html(--total);
					if(total == 0){
						var nodata = '<span class="no-data-found">'+ getLocalize('no_data') + '</span>';
						$(".body-mysite .data-compoment .content .data-content").html(nodata);
					}
					if(total > 1)
						$('.body-mysite .content-status .content .row .info-detail .status-name').html(getLocalize("mys_site_2"));
					else 
						$('.body-mysite .content-status .content .row .info-detail .status-name').html(getLocalize("mys_site"));
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * delete site
		 */
		duplicate : function(pe400) {
			$.ajax({
				type : "POST",
				url : encodeUrl("mysitesBean.duplicate"),
				data : {
					pe400 : pe400
				},
				success : function(response) {
					mySites.loadMysite();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * check right create web
		 */
		checkRightCreateWeb: function(event){
			$.ajaxSetup({
				beforeSend : function() {
					
				},
				complete : function() {
					setDefaultAjaxStatus();
				}
			});
			$.ajax({
				type: 'POST',
				url: encodeUrl('mysitesBean.checkRightCreateWeb'),
				data : {},
				success : function(response) {
					if(response.result == "RE_VAILD_RIGHT"){
						event.action();
					}
					else
						util.confirmDialog(response.result, function(){
							window.location = util.contextPath()+"/pricing";
						});
				},
				error : function(e){
					showGrowlMessageAjaxError();
				}
			})
		},
		removeDesignerFromWeb : function(fe150){
			$.ajax({
				type: 'POST',
				url: encodeUrl('customerBean.removeDesFromWeb'),
				data : {fe150 : fe150},
				success : function(response) {
					$('.select-remove-des[fe150="'+fe150+'"]').closest('.data').find('.btn-share-for-designer').attr('isshared','false');
					$('.select-remove-des[fe150="'+fe150+'"]').closest('.data').find('.btn-share-for-designer').removeClass('shared')
					$('.select-remove-des[fe150="'+fe150+'"]').closest('.designer-content.current').hide();
					$('.select-remove-des[fe150="'+fe150+'"]').closest('.wapper-designer').find('.designer-content.past').show();
				},
				error : function(e){
					showGrowlMessageAjaxError();
				}
			})
		},
		loadDesignerInfo : function(fo100){
			$.ajax({
				type: 'GET',
				url: encodeUrl('userInfoBean.loadDesignerInfo'),
				data : {fo100 : fo100},
				success : function(response) {
					var item = response.result
					$('.qb-dig-tools-edit .wapper-dia-info .info-avt .name-for-des').html(item.nv100);
					$('.qb-dig-tools-edit .wapper-dia-info .info-des .des-order .data-static span.des-mail').html(item.mv903Decrypt);
					var sex;
					switch (item.mv905) {
					case "M":
						sex = getLocalize("male");
						break;
					case "F":
						sex = getLocalize("female");
						break;
					case "N":
						sex = getLocalize("undefined");
						break;
					}
					$('.qb-dig-tools-edit .wapper-dia-info .info-des .des-order .data-static span.des-sex').html(sex);
					$('.qb-dig-tools-edit .wapper-dia-info .info-des .des-order .data-static span.des-bd').html((item.md904String==null)?getLocalize("noupdate"):item.md904String);
					$('.qb-dig-tools-edit .wapper-dia-info .info-des .des-order .data-static span.des-total-web').html(item.totalStie+' pages');
					if(item.totalStie==0){
						$('.qb-dig-tools-edit .wapper-dia-info .info-des .des-order li:nth-last-child(1)').hide();
					}
					$('.qb-dig-tools-edit .wapper-dia-info .info-avt .wapper-img-avt img').attr("src",item.urlAvarta);
					$('#qb-dlg-designer-info').dialog('open');
				},
				error : function(e){
					showGrowlMessageAjaxError();
				}
			});
		},
		timeOutPage : function(){
			console.log("Total : "+mySites.totalPage);
			mySites.paginationMySite.clean();
			mySites.paginationMySite.genericPadingtion($('.pagination-testvtt'), function(data) {
				mySites.offsetPage = data.offset;
				mySites.limitPage = data.limit;
				var search = $('.panel-des-function .ip-search-site').val();
				mySites.loadMysite(search);
				$("html, body").animate({scrollTop: 0}, 1000);
			}, mySites.totalPage, mySites.limitPage,true,mySites.offsetPage,mySites.limitPage);
			mySites.paginationMySite.autoScrollPagination($(".pagination-full-page .page-padingtion[offset="+mySites.offsetPage+"]"));
		},openDialogDesignerInfo :function(fo100){
			if(!this.inited)
			{
				$('#qb-dlg-designer-info').webDialog(350);
				$('#qb-dlg-designer-info').parent().find('.ui-dialog-titlebar').remove();
				$(document).on('click','.qb-dig-tools-edit .wapper-dia-info .info-avt .info-designer-close',function(){
					$('#qb-dlg-designer-info').dialog('close');
				});
				this.inited = true;
			}
			mySites.loadDesignerInfo(fo100);
		},
	}
}());