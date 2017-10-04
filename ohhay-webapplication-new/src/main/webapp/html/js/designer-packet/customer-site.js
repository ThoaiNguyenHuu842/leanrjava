/**
 * @author ThoaiNH 
 * create Feb 25, 2016
 * customer event on designer packet module
 */
(function() {
	customerSite = {
		
		init : function() {
			customerSite.eventListener();
		},
		eventListener : function() {
			$(document).on('click','.btn-cus-apply',function(){
				var fo100Des = $(this).attr('fo100des');
				var fe400Cus = $(this).attr('fe400cus');
				var fe400Des = $(this).attr('fe400des');
				var fe150 = $(this).attr('fe150');
				customerSite.applySiteOfDes(fo100Des,fe400Cus,fe400Des,fe150);
			}),
			$(document).on('click','.btn-cus-reject',function(){
				var fe150 = $(this).attr('fe150');
				customerSite.rejectSiteOfDes(fe150,'');
			})
		},
		showDialogChooseDesigner: function(webID){
			customerSite.fo100d = 0;
			customerSite.webID = webID;
			//init when first call
			if(!this.inited)
			{
				$('#qb-dlg-share-for-designer').webDialog(900);
				//remove header dialog
				$('#qb-dlg-share-for-designer').parent().find('.ui-dialog-titlebar').remove();
				$(document).on('click','#qb-dlg-share-for-designer .qb-dig-content .share-designer .share-designer-close',function(){
					$('#qb-dlg-share-for-designer').dialog('close');
				}),
				
				$(document).on('click','#qb-dlg-share-for-designer .qb-dig-content .share-designer .group-designers ul li .designer-item .designer-info span', function(){
					customerSite.fo100d = $(this).parents('.designer-item').attr('po100');
					$('#qb-dlg-share-for-designer .qb-dig-content .share-designer .group-designers ul li .designer-item .designer-info span').html('');
					$(this).html('<i class="fa fa-check"></i>');
					customerSite.showDialogCmtOfCus();
				});
				$(document).on('keypress','#qb-dlg-share-for-designer .qb-dig-content .share-designer .share-designer-find input',function(e){
					if(e.which===13)
						customerSite.loadListDes($(this).val());
				}),
				$(document).on('click','#qb-dlg-share-for-designer .qb-dig-content .share-designer .share-designer-find span.find-cus',function(){
					customerSite.loadListDes($('#qb-dlg-share-for-designer .qb-dig-content .share-designer .share-designer-find input').val());
				})
				
				$(document).on('click','#qb-dlg-share-for-designer .qb-dig-content .share-designer  ul li .designer-item .designer-avatar',function(){
					var fo100  = $(this).parents('.designer-item').attr('po100');
					mySites.openDialogDesignerInfo(fo100);
				})
				$(document).on('click','#qb-dlg-share-for-designer .qb-dig-content .share-designer ul li .designer-item .designer-info p',function(){
					var fo100  = $(this).parents('.designer-item').attr('po100');
					mySites.openDialogDesignerInfo(fo100);
				})
				this.inited = true;
			}
			
			customerSite.loadListDes();
		},
		showDialogDesignerOfWeb: function(webID){
			customerSite.fo100d = 0;
			customerSite.webID = webID;
			$("#qb-dlg-share-for-designer .qb-dig-content .place-add-data").html("");
			//init when first call
			if(!this.diDesInited)
			{
				$('#qb-dlg-designer-of-web').webDialog(900);
				$('#qb-dlg-designer-of-web').parent().find('.ui-dialog-titlebar').remove();
				$('#qb-dlg-designer-of-web .qb-dig-content .share-designer .share-designer-find span').removeClass('des-choose');
				$('#qb-dlg-designer-of-web .qb-dig-content .share-designer .share-designer-find span').html(getLocalize("des_title1"));
				//remove designer
				$(document).on('click','#qb-dlg-designer-of-web .btn-remove-des', function(){
					 var pe150 = $(this).parents('.item').attr('pe150');
					 var self = $(this);
					 util.confirmDialog(getLocalize("jsstd_title1"), function(){
						 customerSite.removeDes(pe150);
					 });
				});
				$(document).on('click','#qb-dlg-designer-of-web .btn-confirmBackup', function(){
					 var pe150 = $(this).parents('.item').attr('pe150');
					 var fo100d = $(this).parents('.item').attr('fo100d');
					 util.confirmDialog('Do you really want to apply this version to your site?', function(){
						 //fo100Des, pe150, fe400C
						 customerSite.confirmBackup(fo100d, pe150, customerSite.webID);
					 });
				});
				$(document).on('click', '#qb-dlg-designer-of-web .btn-add-designer', function() {
					if(customerSite.fo100d == 0)
						alert("Please select one designer");
					else {
						customerSite.addDes();
					}
				});
				
				/*NEW
				 * TuNt
				 * 10/11/2016
				 * */
				 	
				/*
				 * on click check designer
				 */
				$(document).on('click','#qb-dlg-designer-of-web .qb-dig-content .share-designer .group-designers ul li .designer-item .designer-info span', function(){
					customerSite.fo100d = $(this).parents('.designer-item').attr('po100');
					$('#qb-dlg-designer-of-web .qb-dig-content .share-designer .group-designers ul li .designer-item .designer-info span').html('');
					$(this).html('<i class="fa fa-check"></i>');
					$('#qb-dlg-designer-of-web .qb-dig-content .share-designer .share-designer-find span').addClass('des-choose');
					$('#qb-dlg-designer-of-web .qb-dig-content .share-designer .share-designer-find span.des-choose').html(getLocalize("done"));
				});
				$(document).on('click','#qb-dlg-designer-of-web .qb-dig-content .share-designer .share-designer-close',function(){
					$('#qb-dlg-designer-of-web').dialog('close');
				})
				$(document).on('click','#qb-dlg-designer-of-web .qb-dig-content .share-designer .share-designer-find span.des-choose',function(){
					if(customerSite.fo100d == 0)
						alert("Please select one designer");
					else {
						customerSite.addDes();
					}
				}),	
				$(document).on('input','#qb-dlg-designer-of-web .qb-dig-content .share-designer .share-designer-find input',function(){
					var filter = $(this).val();
					var list = $('#qb-dlg-designer-of-web .qb-dig-content .share-designer .group-designers ul');
					if (filter) {
						list.find("p.designer-email:not(:Contains(" + filter + "))").parents('li').slideUp();
						list.find("p.designer-email:Contains(" + filter + ")").parents('li').slideDown();
					} else {
						list.find("li").slideDown();
					}
				})
				this.diDesInited = true;
			}

			customerSite.loadListDes();
		},
		/*
		 * load all designer
		 */
		loadListDes : function(search) {
			$.ajax({
				url : encodeUrl("customerBean.loadListDes"),
				data : {
					webID: customerSite.webID,
					pvSearch: (search==undefined)? "" : search,
					offset: 0,
					limit: 100
				},
				success : function(response) {
					var htmlString = "";
					if (response.result && response.result.length > 0) {
						for(i = 0;i<response.result.length;i++){
							var item = response.result[i];
							//designer chua add truoc do
							
								htmlString +='<li>'
								htmlString +=	'<div po100="'+item.id+'" class="designer-item">'
								htmlString +=		'<div class="designer-avatar">'
								htmlString +=			'<img src="'+item.urlAvarta+'" />';
								htmlString +=		'</div>'
								htmlString +=		'<div class="designer-info">'
								htmlString +=			'<p class="designer-name">'+item.nv100+'</p>'
								htmlString +=			'<p class="designer-email">'+item.mv903Decrypt+'</p>'
								htmlString +=			'<span class="choose-designer">'
								htmlString +=			'</span>'
								htmlString +=		'</div>'
								htmlString +=	'</div>'
								htmlString +='</li>'
							
						}
						$("#qb-dlg-share-for-designer .qb-dig-content .share-designer .group-designers ul").html(htmlString);
						$('#qb-dlg-share-for-designer').dialog('close');
						$('#qb-dlg-share-for-designer').dialog('open');
						$("#qb-dlg-share-for-designer .qb-dig-content .share-designer .group-designers ul").niceScroll();
					}else{
						$("#qb-dlg-share-for-designer .qb-dig-content .share-designer .group-designers ul").html("");
						htmlString = '<li style="text-align: center" class="no-hover">'+getLocalize("no_data")+'</li>';
						$("#qb-dlg-share-for-designer .qb-dig-content .share-designer .group-designers ul").html(htmlString);
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * share web for designer
		 */
		addDes : function(note) {
			$.ajax({
				type : "POST",
				url : encodeUrl("customerBean.addDes"),
				data : {
					fo100d : customerSite.fo100d,
					webID: customerSite.webID,
					boxIDPattern: 'ALL',
					note: ((note==undefined)?"":note)
				},
				success : function(response) {
					showGrowlMessageSuccess();
					$('#qb-dlg-share-for-designer').dialog('close');
					$('.item-data[siteid="'+customerSite.webID+'"]').find('.btn-share-for-designer').attr('isShared','true');
					var version = parseInt($('.item-data[siteid="'+customerSite.webID+'"]').find('.site-ver').html());
					var urlAvarta = $('#qb-dlg-share-for-designer .qb-dig-content .share-designer ul li .designer-item[po100="'+customerSite.fo100d+'"]').find('.designer-avatar img').attr('src');
					var nv100 = $('#qb-dlg-share-for-designer .qb-dig-content .share-designer ul li .designer-item[po100="'+customerSite.fo100d+'"]').find('.designer-info .designer-name').html();
					var stt = JSON.parse(designerSite.setStatus('NEW'));
					var htmlStringd = 	'<div class="designer-content current">'+
											'<div class="info-des" fo100="'+customerSite.fo100d+'">'+
											'<span class="avt-des"><img alt="" src="'+urlAvarta+'"></span>'+
											'<div class="info-des-content">'+
												'<h5>'+
													'<span class="name-des">'+nv100+'</span> <span class="info-des-name"></span>'+
												'</h5>'+
												'<h6></h6>'+
												'<h6 style="color: rgb(0, 0, 0); font-weight: bold; padding-top: 12px; padding-bottom: 10px; '+((version>0)?'':'display: none;')+'"> '+getLocalize("mys_title15")+'  <span class="site-ver">'+version+'</span>.0</h6>'+
											'</div>'+
										  '</div>'+
										  '<div class="info-status dropdown">'+
										  '<button class="dropdown-toggle" type="button" id="dropdownStt" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="background-color: '+stt.color+';">'+stt.text+
											' <span class="caret"></span>'+
										'</button>'+
											'<ul class="dropdown-menu dropdown-custom" aria-labelledby="dropdownStt">'+
												'<li><a fe150="'+response.result+'" class="select-remove-des">'+getLocalize("des_title15")+'</a></li>'+
											'</ul>'+
										'</div>' ;
						if($('.item-data[siteid="'+customerSite.webID+'"]').find('.wapper-designer .designer-content.past .btn-show-history').length){
							htmlStringd += 	'<div class="function-customer">'+
												'<ul>';
							htmlStringd += 			'<li class="item">';
							htmlStringd += 				'<div class="obutton-link-small-mysites hvr-shrink btn-show-history" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("mys_title21")+'">';
							htmlStringd += 				'<i class="fa fa-list-ul"></i>';
							htmlStringd += 				'</div>';
							htmlStringd += 			'</li>';
							htmlStringd +=		'</ul>';
							htmlStringd +=	'</div>';
						};
					htmlStringd+= '</div>';
					htmlStringd += $('.item-data[siteid="'+customerSite.webID+'"]').find('.wapper-designer').html();
					$('.item-data[siteid="'+customerSite.webID+'"]').find('.btn-share-for-designer').addClass('shared');
					$('.item-data[siteid="'+customerSite.webID+'"]').find('.wapper-designer').html(htmlStringd);
					$('.item-data[siteid="'+customerSite.webID+'"]').find('.wapper-designer .designer-content.past').hide();
					$('#qb-dlg-cmt-of-cus').dialog('close');
					notification.insertNotify(customerSite.fo100d, notificationKey.BONEVO_CUS_SEND_REQUEST_DES, response.result, customerSite.webID);
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * load list designer of web
		 */
		loadListDesOfWeb : function() {
			$.ajax({
				url : encodeUrl("customerBean.loadListDesOfWeb"),
				data : {
					fe400: customerSite.webID
				},
				success : function(response) {
					var htmlString = "";
					if (response.result && response.result.length > 0) {
						var htmlString2 = '<div class="share-designer">'+
											'<span class="share-designer-close"></span>'+
											'<div class="group-designers-of-web">'+
								 				'<ul>'+
												 	
												'</ul>'+
												'<span class="list-designer-text"><i class="fa fa-share-alt" aria-hidden="true"></i>Designer</span>'+
											'</div>'+
										'</div>';
						$("#qb-dlg-share-for-designer .qb-dig-content .place-add-data").html(htmlString2);
						for (index = response.result.length - 1; index >= 0; index--) {
							var item = response.result[index];
							htmlString +='<li>'
							htmlString +=	'<div fo100d="'+item.fo100d+'" pe150="'+item.pe150+'" class="designer-item">'
							htmlString +=		'<div class="designer-avatar">'
							htmlString +=			'<img src="'+item.profile.urlAvarta+'">'
							htmlString +=		'</div>'
							htmlString +=		'<div class="designer-info">'
							htmlString +=			'<p class="designer-name">'+item.profile.nv100Decrypt+'</p> '
							htmlString +=			'<p class="designer-email">'+item.profile.mv903Decrypt+'</p>'
							htmlString +=			'<span class="choose-designer-remove">'
							htmlString +=			'<i class="fa fa-trash-o" aria-hidden="true"></i>'
							htmlString +=			'</span>'
							htmlString +=		'</div>'
							htmlString +=	'</div>'
							htmlString +='</li>'
						}
						$("#qb-dlg-share-for-designer .qb-dig-content .share-designer .group-designers-of-web ul").html(htmlString);
						$('#qb-dlg-share-for-designer').dialog('close');
						$('#qb-dlg-share-for-designer').dialog('open');
						
					}
					else {
						customerSite.loadListDes();
					}

				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		removeDes: function(pe150){
			$.ajax({
				type : "POST",
				url : encodeUrl("customerBean.removeDesFromWeb"),
				data : {
					fe150:pe150
				},
				success : function(response) {
					customerSite.loadListDesOfWeb();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		confirmBackup: function(fo100Des, pe150, fe400C){
			$.ajax({
				type : "POST",
				url : encodeUrl("customerBean.confirmBackup"),
				data : {
					fo100Des:fo100Des,
					pe150: pe150,
					fe400C: fe400C
				},
				success : function(response) {
					showGrowlMessageSuccess();
					$('#qb-dlg-designer-of-web').dialog('close');
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		applySiteOfDes: function(fo100Des,fe400Cus,fe400Des,fe150){
			$.ajax({
				type : "POST",
				url : encodeUrl("customerBean.applySiteOfDes"),
				data : {
					fo100Des:fo100Des,
					fe400Des: fe400Des,
					fe400Cus:fe400Cus,
					fe150:fe150
				},
				success : function(response) {
					var urlAvarta = $('.btn-cus-apply[fe150="'+fe150+'"]').parents('.designer-content.current').find('.avt-des img').attr('src');
					var nameDes = $('.btn-cus-apply[fe150="'+fe150+'"]').parents('.designer-content.current').find('h5 .name-des').html();
					var version = parseInt($('.btn-cus-apply[fe150="'+fe150+'"]').parents('.designer-content.current').find('h6 .site-ver').html());
					if($('.btn-cus-apply[fe150="'+fe150+'"]').parents('.designer-content.last').length){
						htmlString += 	'<div class="info-des" fo100="'+fo100Des+'">'+
											'<span class="avt-des"><img alt="" src="'+urlAvarta+'"></span>'+
											'<div class="info-des-content">';
						htmlString +=		'<h5>'+
												'<span class="name-des">'+nameDes+'</span>'+
											'</h5>';
						htmlString += 		'<h6 style="color: rgb(0, 0, 0); font-weight: bold; padding-top: 12px; padding-bottom: 10px;"> '+getLocalize("mys_title15")+'  <span class="site-ver">'+(version+1)+'</span>.0</h6>';
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
						htmlString += 					'<li class="item">';
						htmlString += 						'<div class="obutton-link-small-mysites hvr-shrink btn-show-history" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("mys_title21")+'">';
						htmlString += 							'<i class="fa fa-list-ul"></i>';
						htmlString += 						'</div>';
						htmlString += 					'</li>';
						htmlString +=			'</ul>';
						htmlString +=		'</div>';
						htmlString +='</div>';
						htmlString += 			'</div>';
						htmlString += 		'</div>';
						htmlString += 	'</div>';
						htmlString += '</div>';
						$('.btn-cus-apply[fe150="'+fe150+'"]').parents('.designer-content.last').html(htmlString);
						$('.btn-cus-apply[fe150="'+fe150+'"]').parents('.designer-content.current').hide();
						$('.btn-cus-apply[fe150="'+fe150+'"]').parents('.data').find('.btn-share-for-designer').removeClass('shared');
						$('.btn-cus-apply[fe150="'+fe150+'"]').parents('.data').find('.btn-share-for-designer').attr('isshared',false);
						$('.btn-cus-apply[fe150="'+fe150+'"]').parents('.designer-content.last').show();
					}else{
						var stt = JSON.parse(designerSite.setStatus('CON'));
						var htmlString = $('.btn-cus-apply[fe150="'+fe150+'"]').parents('.wapper-designer').html();;
						htmlString += 	'<div class="designer-content last">'+
											'<div class="info-des" fo100="'+fo100Des+'">'+
											'<span class="avt-des"><img alt="" src="'+urlAvarta+'"></span>'+
											'<div class="info-des-content">';
						htmlString +=		'<h5>'+
													'<span class="name-des">'+nameDes+'</span>'+
												'</h5>';
						htmlString += 		'<h6 style="color: rgb(0, 0, 0); font-weight: bold; padding-top: 12px; padding-bottom: 10px;"> '+getLocalize("mys_title15")+'  <span class="site-ver">'+(version+1)+'</span>.0</h6>';
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
						htmlString += 					'<li class="item">';
						htmlString += 						'<div class="obutton-link-small-mysites hvr-shrink btn-show-history" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("mys_title21")+'">';
						htmlString += 							'<i class="fa fa-list-ul"></i>';
						htmlString += 						'</div>';
						htmlString += 					'</li>';
						htmlString +=			'</ul>';
						htmlString +=		'</div>';
						htmlString +='</div>';
						htmlString += 			'</div>';
						htmlString += 		'</div>';
						htmlString += 	'</div>';
						htmlString += '</div>';
						htmlString += '</div>';
						$('.btn-cus-apply[fe150="'+fe150+'"]').parents('.wapper-designer').html(htmlString);
						$('.btn-cus-apply[fe150="'+fe150+'"]').parents('.designer-content.current').hide();
						$('.btn-cus-apply[fe150="'+fe150+'"]').parents('.data').find('.btn-share-for-designer').removeClass('shared');
						$('.btn-cus-apply[fe150="'+fe150+'"]').parents('.data').find('.btn-share-for-designer').attr('isshared',false);
						$('.btn-cus-apply[fe150="'+fe150+'"]').parents('.designer-content.last').show();
					}
					showGrowlMessageSuccess();
					notification.insertNotify(fo100Des, notificationKey.BONEVO_CUS_APPLY_DESIGN, fe150, fe400Cus);
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
			
		},
		rejectSiteOfDes: function(fe150, ev161){
			$.ajax({
				type : "POST",
				url : encodeUrl("customerBean.rejectSiteOfDes"),
				data : {
					fe150:fe150,
					ev161: ev161
				},
				success : function(response) {
					var fo100Des = $(".btn-cus-reject[fe150="+fe150+"]").closest(".designer-content").find(".btn-cus-apply").attr("fo100des");
					var fe400Cus = $(".btn-cus-reject[fe150="+fe150+"]").closest(".designer-content").find(".btn-cus-apply").attr("fe400Cus");
					notification.insertNotify(fo100Des, notificationKey.BONEVO_CUS_REJECT_DESIGN, fe150, fe400Cus);
					var stt = JSON.parse(designerSite.setStatus('DEC'));
					$('.btn-cus-reject[fe150="'+fe150+'"]').parents('.designer-content').find('.info-status button').html(stt.text);
					$('.btn-cus-reject[fe150="'+fe150+'"]').parents('.designer-content').find('.info-status button').css('background-color',stt.color);
					var htmlString ='';
					if($('.btn-cus-reject[fe150="'+fe150+'"]').parents('.designer-content').find('.btn-show-history').length){
						htmlString +='<ul>';
						htmlString += 					'<li class="item">';
						htmlString += 						'<div class="obutton-link-small-mysites hvr-shrink btn-show-history" data-placement="bottom" data-toggle="tooltip" title="'+getLocalize("mys_title21")+'">';
						htmlString += 							'<i class="fa fa-list-ul"></i>';
						htmlString += 						'</div>';
						htmlString += 					'</li>';
						htmlString +=			'</ul>';
					}
					$('.btn-cus-reject[fe150="'+fe150+'"]').parents('.designer-content').find('.function-customer').html(htmlString);
					showGrowlMessageSuccess();
					
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			
			});
		},
		showDialogCmtOfCus : function(){
			if(!this.inited2)
			{
				$('#qb-dlg-cmt-of-cus').webDialog(700);
				$('#qb-dlg-cmt-of-cus').parent().find('.ui-dialog-titlebar').remove();
				$(document).on('click','#qb-dlg-cmt-of-cus .cmt-of-cus-bottom-menu .btn-send-text',function(){
					if(customerSite.fo100d == 0)
					{
						
					}
					else {
						var note = $('#qb-dlg-cmt-of-cus .cmt-of-cus-wapper .main-content .ip-comment-of-cus').val();
						customerSite.addDes(note);
					}
				});
				$(document).on('click','#qb-dlg-cmt-of-cus .cmt-of-cus-bottom-menu .btn-cancel-send',function(){
					$('#qb-dlg-cmt-of-cus').dialog('close');
				});
				this.inited2 = true;
			}
			var image = $('#qb-dlg-share-for-designer .qb-dig-content .share-designer .group-designers ul li .designer-item .designer-info span i').parents('.designer-item').find('img').attr('src');
			var name = $('#qb-dlg-share-for-designer .qb-dig-content .share-designer .group-designers ul li .designer-item .designer-info span i').parents('.designer-item').find('.designer-name').html();
			var email = $('#qb-dlg-share-for-designer .qb-dig-content .share-designer .group-designers ul li .designer-item .designer-info span i').parents('.designer-item').find('.designer-email').html();
			$('#qb-dlg-cmt-of-cus .cmt-of-cus-wapper .designer-item .designer-avatar img').attr('src',image);
			$('#qb-dlg-cmt-of-cus .cmt-of-cus-wapper .designer-item .designer-info p.designer-name').html(name);
			$('#qb-dlg-cmt-of-cus .cmt-of-cus-wapper .designer-item .designer-info p.designer-email').html(email);
			
			$('#qb-dlg-cmt-of-cus').dialog('open');
		}
	}
}());