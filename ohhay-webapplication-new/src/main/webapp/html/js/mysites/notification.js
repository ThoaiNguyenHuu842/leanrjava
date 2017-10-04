/**
 * @author TuNt
 * create date Dec 13, 2016
 * ohhay-webapplication-new
 */
(function(){
	notificationKey = {
		//customer sent request to designer
		BONEVO_CUS_SEND_REQUEST_DES : "BONEVO_CUS_SEND_REQUEST_DES",
		//designer confirmed request
		BONEVO_DES_CONFIRMED_REQUEST : "BONEVO_DES_CONFIRMED_REQUEST",
		//designer reject cus
		BONEVO_DES_REJECT_CUS: "BONEVO_DES_REJECT_CUS",
		//designer sent a design to customer
		BONEVO_DES_SEND_DESIGN : "BONEVO_DES_SEND_DESIGN",
		//customer applied design
		BONEVO_CUS_APPLY_DESIGN : "BONEVO_CUS_APPLY_DESIGN",
		//customer rejected design
		BONEVO_CUS_REJECT_DESIGN : "BONEVO_CUS_REJECT_DESIGN"	
	}
}());
(function(){
	notification ={
		init: function(){
			notification.eventListener();
			notification.loadCountNotify();
			$('.notifi-content-main').niceScroll();
			notification.fristOpen = true;
			notification.limit  = 7;
			notification.doneLoad = false;
		},
		eventListener : function(){
			$(document).on('click','.qb-notification i.fa-globe',function(){
				if(parseInt($('.qb-notification .notify-content .count-notifi').html())>0){
					notification.fristOpen = true;
				}
				if(notification.fristOpen){
					notification.loadNotify(0,notification.limit);
					notification.fristOpen = false;
				}
				$('.form-notifi').toggle();
				
			});
			$(document).mouseup(function (e){
			    var container = $(".form-notifi");
			    var container2= $('.qb-notification i.fa-globe');
			    if (!container.is(e.target) // if the target of the click isn't the container...
			        && container.has(e.target).length === 0) // ... nor a descendant of the container
			    {
			    	if(!container2.is(e.target) && container2.has(e.target).length === 0){
			    		container.hide();
			    	}
			        
			    }
			});
			$(".notifi-content-main").niceScroll().scrollend(function(info){
				if(notification.doneLoad == false){
					var dataNiceScroll = $('.notifi-content-main').getNiceScroll().resize();
					if(info.end.y==dataNiceScroll[0]["page"]["maxh"]){
						notification.limit=notification.limit+4;
						notification.loadNotify(0,notification.limit);
					};
				}
			  })
		},
		loadCountNotify : function(){
			$.ajax({
				type: "GET",
				url: encodeUrl("notifyBean.listOfTabN700"),
				data : {},
				success : function(response) {
					
					if(response.result && response.result.mapNotifyType){
						var count = parseInt(response.result.mapNotifyType.BONEVO_NOTIFICATION_KEY);
						if(count > 0){
							
							var htmlString = '<span class="count-notifi">'+count+'</span>';
							$('.qb-notification .notify-content').html(htmlString);
						}
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			})
		},
		resetNotifyCount : function(objectName){
			$.ajax({
				type: "POST",
				url: encodeUrl("notifyBean.updateTabN700"),
				data : {objectName:objectName},
				success : function(response) {
					if(response.result >0){
						
					}
				}
			})
		},
		insertNotify : function(fo100n, nv801, nn802, nv803){
			$.ajaxSetup({
			    beforeSend : function() {
			    },
			    complete : function() {
			    	setDefaultAjaxStatus();
			    }
			});
			$.ajax({
				type: "POST",
				url: encodeUrl("notifyBean.insertTabN800"),
				data : {
					fo100n:fo100n,
					nv801:nv801,
					nn802:nn802,
					nv803:nv803
				},
				success : function(response) {
				}
			})
		},
		loadNotify : function(offset,limit){
			$.ajaxSetup({
				beforeSend : function() {
					try {
						//NProgress.start();
						if ($('.notify-ajax-loader').css('display') == 'none')
							$('.notify-ajax-loader').css('display', 'block');
					} catch (e) {
						// TODO: handle exception
						console.log(e);
					}
				},
				complete : function() {
					try {
						$('.notify-ajax-loader').css('display', 'none');
					} catch (e) {
						console.log(e);
					}
				}
			   });
			
			$.ajax({
				type: "GET",
				url: encodeUrl("notifyBean.listOfTabN800"),
				data : {
					offset:offset,
					limit:limit
				},
				success : function(response) {
					if(response.result.length>0){
						var html = '';
						for(var i=0; i<response.result.length; i++){
							var item = response.result[i];
							html += '<li>';
							html +=	'<a href="'+notification.converKeytoLink(item)+'">'
							html +=		'<div class="item-notifi-content" fo100="'+item.fo100+'">';
							html +=			'<div class="item-left">';
							html +=				'<img alt="" src="'+item.avarta+'">';
							html +=			'</div>';
							html +=			'<div class="item-mid">';
							html +=				'<span class="item-mid-name">'+item.hoTen+'</span>';
							html +=				'<span class="item-mid-notifi">'+notification.convertKeytoText(item.nv801,item.nv803)+'</span>';
							
								str = item.secondLastAction;
								var arr = str.split(' hours ');
								if(arr[0]!=undefined && arr[0]=='0'){
									str = arr[1];
								}
							
							html +=				'<span class="item-mid-time"><span class="time-past">'+str+'</span>ago</span>';
							html +=			'</div>';
							html +=			'<div class="item-right">';
							html +=				'<span class="'+notification.convertKeytoIcon(item.nv801)+'"></span>';
							html +=			'</div>';
							html +=		'</div>';
							html +='</a>';
							html +=	'</li>';
						}
						$('.wrapper-nav.active .qb-notification .notifi-content .notifi-content-main ul').html(html);
						if(limit==7){
							$('.qb-notification .notify-content').html("");
						}
						if(response.result.length !==(limit)){
							notification.doneLoad = true ; 
						}
					}else{
						$('.wrapper-nav.active .qb-notification .notifi-content .notifi-content-main ul').html('<li><div class="notifi-nodata"><span>'+getLocalize("no_data")+'</span></div></li>');
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			})
		},
		convertKeytoText: function(key, ref){
			switch (key) {
			case notificationKey.BONEVO_CUS_SEND_REQUEST_DES:
				return getLocalize("ntf_sst1");
			case notificationKey.BONEVO_DES_CONFIRMED_REQUEST:
				return getLocalize("ntf_title1")+" <span class='site-name'>"+ref+"</span> "+getLocalize("ntf_sst2");
			case notificationKey.BONEVO_DES_REJECT_CUS:
				return getLocalize("ntf_title1")+" <span class='site-name'>"+ref+"</span> "+getLocalize("ntf_sst3");
			case notificationKey.BONEVO_DES_SEND_DESIGN:
				return getLocalize("ntf_sst4");
			case notificationKey.BONEVO_CUS_APPLY_DESIGN:
				return getLocalize("ntf_sst5");
			case notificationKey.BONEVO_CUS_REJECT_DESIGN:
				return getLocalize("ntf_sst6");
			}
		},
		convertKeytoIcon: function(key){
			switch (key) {
			case notificationKey.BONEVO_CUS_SEND_REQUEST_DES:
				return "icon-handshake";
			case notificationKey.BONEVO_DES_CONFIRMED_REQUEST:
				return "icon-more-idea";
			case notificationKey.BONEVO_DES_REJECT_CUS:
				return "icon-more-idea";
			case notificationKey.BONEVO_DES_SEND_DESIGN:
				return "icon-handshake";
			case notificationKey.BONEVO_CUS_APPLY_DESIGN:
				return "icon-apply-web";
			case notificationKey.BONEVO_CUS_REJECT_DESIGN:
				return "icon-apply-web";
			}
		},
		converKeytoLink: function(item){
			var key = item.nv801;
			var language = $('#evo-current-lang').val();
			switch (key) {
			case notificationKey.BONEVO_CUS_SEND_REQUEST_DES:
				return util.contextPath()+"/mysites?language="+language+"&pe150="+item.nn802;
			case notificationKey.BONEVO_DES_CONFIRMED_REQUEST:
				return util.contextPath()+"/mysites?language="+language+"&query-search="+item.nv803;
			case notificationKey.BONEVO_DES_REJECT_CUS:
				return util.contextPath()+"/mysites?language="+language+"&query-search="+item.nv803;
			case notificationKey.BONEVO_DES_SEND_DESIGN:
				return util.contextPath()+"/mysites?language="+language+"&query-search="+item.nv803;
			case notificationKey.BONEVO_CUS_APPLY_DESIGN:
				return util.contextPath()+"/mysites?language="+language+"&pe150="+item.nn802;
			case notificationKey.BONEVO_CUS_REJECT_DESIGN:
				return util.contextPath()+"/mysites?language="+language+"&pe150="+item.nn802;
			}
		}
	}
}());