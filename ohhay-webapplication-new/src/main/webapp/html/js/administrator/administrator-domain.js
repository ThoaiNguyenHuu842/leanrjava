/**
 * @author TuNt create date Mar 28, 2016 ohhay-webapplication-new
 */
(function() {
	adminDomainManagement = {
		/* init */
		init : function() {
			adminDomainManagement.eventListener();
		},
		/* eventlistener */
		eventListener : function() {
			/*event select filter*/
			$(document).on('click','.domain-filter .domain-filter-content li',function(){
				var data = $(this).attr('data');
				$('.domain-filter .domain-filter-content').attr('type-filter',data);
				var content = $(this).find('span').html();
				$('.domain-filter .domain-filter-content input').attr('placeholder',content);
				adminDomainManagement.loadListDomain(adminSetting.limit,0);
				
			})

		},
		/* load list domain */
		loadListDomain : function(limit,offset) {
			var status = $('.domain-filter .domain-filter-content').attr('type-filter');
			$.ajax({
						url : encodeUrl("adminAccountBean.loadListDomain"),
						data : {
							"offset" : offset,
							"limit": limit,
							"status": status
							},
						success : function(response) {
							var data = response.result;
							var html = "";
							var count = 0;
							if(data.length == 0){
								html ='<h2>No Data</h2>';
								util.messageDialog("Page not found !");
							}else{
							for (var i = (data.length-1); i >= 0; i--) {
									
								if(offset==0 && i==0){
									adminSetting.sizePage = data[i].rowss;
									count = data[i].rowss;
								}
								
								((offset==0)? adminSetting.padingTionPage = "<div class='data-padingtion'>"+adminSetting.genericPadingtion(adminSetting.getPadingtionPage(adminSetting.sizePage, adminSetting.limit),adminSetting.limit)+"</div>" : "" );
								((offset==0)? localStorage.setItem("pagination-page",adminSetting.padingTionPage) : "");
								html += '<div class="col-md-12 col-sm-12 col-lg-12 col-xs-12 item">';
								html += '	<div class="row">';
								html += 		'<div class="col-md-3">';
								html += 			'<div class="row">';
								html += 				'<ul>';
								html += 					'<li><span>Name :</span></li>';
								html += 					'<li><span>Verification code :</span></li>';
								html += 					'<li><span>Owner :</span></li>';
								html += 					'<li><span>Status :</span></li>';
								html += 				'</ul>';
								html += 			'</div>';
								html += 		'</div>';
								html += 		'<div class="col-md-9">';
								html +=				'<div class="row">';
								html += 				'<ul>';
								html += 					'<li><a href="http://'+data[i].uv921+'" target="_blank">'+data[i].uv921+'</a></li>';
								html += 					'<li><a>'+data[i].uv922+'</a></li>';
								html += 					'<li><a>'+data[i].hoten+'</a></li>';
												if(data[i].un923==1){
								html += 					'<li><p class="verified"><a>Confirmed</a></p></li>';
												}else{
								html += 					'<li><p class="waiting"><a>Waiting</a></p></li>';					
												}
								html += 				'</ul>';
								html += 			'</div>';
								html += 		'</div>';
								html +='</div>';
								html +='</div>';
								}
							$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child.qb-administrator-management-domain .domain-content').html(html);
							var paginationData = localStorage.getItem("pagination-page");
							$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page').html(paginationData);
							adminSetting.findActiveClassPagintion(offset);
							((offset==0)?$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child.qb-administrator-management-domain span.total-domain').html(count) :"");
						}}
					});
		}
	};
}())