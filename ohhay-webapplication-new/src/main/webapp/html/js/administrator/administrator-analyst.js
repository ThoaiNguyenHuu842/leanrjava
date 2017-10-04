/**
 * ThoaiVt
 * create Mar 16, 2016
 */

(function(){
	administratorAnalyst = {
		/*
		 * init
		 */
		init : function(){
			console.log("Load analyst !!!");
			administratorAnalyst.eventListener();
			administratorAnalyst.dialogShowPieCharAllComponent = $('#dialog-confirm-dialog-pie-chart-component').dialog({
				 autoOpen : false,
				 modal: true,  
				 resizable: false,
				 closeOnEscape: true,
				 draggable: false,
				 dialogClass : "dialog-confirm-login",
				 width : 610,
				 height : 495,
				 position: {
			         my: 'center', 
			         at: 'center'
				 },
				 show : 'fade',
				 hide : 'fade',
			     buttons : {
			    	 "Ok" : {
			    		 click :  function(){
			    			 administratorAnalyst.dialogShowPieCharAllComponent.dialog("close");
			    		 },text : "Ok",
			    		 class : 'btn-ok-confirm'
			    	 }
			     }
			});
		},
		/*
		 * eventListener
		 */
		eventListener : function(){
			
			$('.qb-content-administrator-child.qb-administrator-analyst-repeat .statistic-data .pie-chart-all-compoment').on('click',function(){
				var check=administratorAnalyst.ajaxLoadChart("adminWebAnalyst.loadPieChart", "","Statistic All Component Using on Bonevo","Analyst All Component");
			});
			
			/*
			 * analyst each web
			 */
			$(document).on('click','.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child.qb-administrator-analyst-repeat .item-data-webanalyst .item-web-analyst .data-content .data-value .function-content .function-data .btn-pie-chart',function(){
				//get idweb
				var webId = $(this).attr("webid");
				//get name set for hight chart
				var name = $(this).closest(".data-value").find(".title-content .name-content-template").html();
				//init form
				formData = new FormData();
				formData.append('nameAccount', webId);
				//check 
				administratorAnalyst.ajaxLoadChart("adminWebAnalyst.loadPieChartWebid",formData,"Analyst for "+name,"Component "+name);
			});
			
			/*
			 * analyst get to mysites
			 */
			$(document).on('click','.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child.qb-administrator-analyst-repeat .item-data-webanalyst .item-web-analyst .data-content .data-value .function-content .function-data .btn-getto-mysites',function(){
				//get idweb
				var webId = $(this).attr("webid");
				console.log("WEBID : "+webId);
				//init form
				formData = new FormData();
				formData.append('id', webId);
				//check 
				administratorAnalyst.createToMysites(formData);
			});
			
			/*
			 * event search
			 */
			$(document).on('keydown','.seach-analyst .box-analyst-search',function(event){
				if(event.which==13){
					administratorAnalyst.loadAnalyst();
				}
			});
		}
		,
		/*
		 * load list web analyst
		 */
		loadAnalyst : function(limit,offset){
			$.ajax({
				type : "GET",
				url : encodeUrl("adminWebAnalyst.loadWebAllWeb"),
				data : {
					offset: offset,
					limit: limit,
					pvSearch:$('.seach-analyst .box-analyst-search').val()
				},
				success : function(response) {
					var data= response.result;
					var infoStatisticWeb = response.result2;
					//set value total Page
					$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child.qb-administrator-analyst-repeat .statistic-info .text-item.total-size span').html(infoStatisticWeb.totalSize);
					//set value total Domain
					$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child.qb-administrator-analyst-repeat .statistic-info .text-item.total-domain span').html(infoStatisticWeb.totalDomain);
					//set value total Section
					$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child.qb-administrator-analyst-repeat .statistic-info .text-item.total-section span').html(infoStatisticWeb.totalSection);
					var html='';
						if(data.length == 0){
							html ='<h2>No Data</h2>';
							util.messageDialog("Page not found !");
						}
						else{
							for (var i = 0; i < data.length; i++) {		
							if(offset == 0 && i == 0){
								$('.total-number-site').html(data[i].sizeRowss);
								adminSetting.sizePage = data[i].sizeRowss;
								console.log("sshh : "+adminSetting.sizePage);
								
							}
						
							((offset==0)? adminSetting.padingTionPage = "<div class='data-padingtion'>"+adminSetting.genericPadingtion(adminSetting.getPadingtionPage(adminSetting.sizePage, adminSetting.limit),adminSetting.limit)+"</div>" : "" );
							((offset==0)? localStorage.setItem("pagination-page",adminSetting.padingTionPage) : "");
							var url =  util.contextPath()+'/'+data[i].ev405;
							html += '<div class="row item-web-analyst">';
							html += '	<div class="col-xs-3 img-content hvr-shrink">';
							html += '		<img src="'+data[i].ev403+'" class="check-imag-eerror" idfor="'+data[i].id+'">';
							html += '	</div>';
							html += '	<div class="col-xs-9 data-content">';
							html += '		<div class="data-value">';
							html +=	'			<div class="title-content">';
							html +=	'				<p class="name-content-template">'+data[i].ev405+'</p>';
							html += '				<p class="detail-date"> Create : '+data[i].el446String+' ( by <span><i>'+data[i].nv100+'</i></span> ) | Modify : '+data[i].el448String+' </p>';
							html += 				'<p>';
			     			html += 					'URL: <a target="_blank" href='+url+' style="color: #EA9138; cursor: pointer;">'+url+'</a>';
			     			html += 				'</p>';
							html +=	'			</div>';
							html +=	'			<div class="function-content">';
							html +=	'				<ul class="function-data">';
							html +=	'					<li title="Analyst" data-toggle="tooltip" class="btn-pie-chart" data-original-title="Pie chart" webid="'+data[i].id+'" data-toggle="tooltip"><i class="fa fa-pie-chart"></i></li>';
							html +=	'					<li title="Get to mysites" data-toggle="tooltip" class="btn-getto-mysites" data-original-title="Pie chart" webid="'+data[i].id+'" data-toggle="tooltip"><i class="fa fa-arrow-right"></i></li>';
							html +=	'				</ul>';
							html +=	'			</div>';
							html +=	'		</div>';
							html +='	</div>';
							html +='</div>';
						}
						$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page').html("");
						var paginationData = localStorage.getItem("pagination-page");
						$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page').html(paginationData);				
						adminSetting.findActiveClassPagintion(offset);
					}
				
					if(offset == 0)
						$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child.qb-administrator-analyst-repeat .item-data-webanalyst').html(html);
					else
						$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child.qb-administrator-analyst-repeat .item-data-webanalyst').html(html);
					adminSetting.imageErrorUpdate();	
					$('[data-toggle="tooltip"]').tooltip();   
					
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},/*
		 	* call ajax load chart
			*/
		ajaxLoadChart: function(ajaxName,data,nameTitle,shortCutName){
			$.ajax({
				type : "POST",
				url : encodeUrl(ajaxName),
				cache: false,
                contentType: false,
                processData: false,
				data : data,
				success : function(response) {
					var data=response.result;
					if(data === undefined || data.length == 0){
						util.messageDialog("Page blank ");
						return;
					}
					var arrayData=[];
					var element=$('#dialog-confirm-dialog-pie-chart-component .data-load-of-pie-chart');
					console.log(data);
					for (var i = 0; i < data.length; i++) {
						var temp = {
								 name: administratorAnalyst.stringReplace(data[i].nameComponent),
					             y:  data[i].rate,
					             rateReal: data[i].rateReal
						};
						arrayData.push(temp);
						console.log("NAME : "+temp.name);
					}
					console.log(arrayData);
					administratorAnalyst.drawHightChart(arrayData, element,nameTitle,shortCutName);
					administratorAnalyst.dialogShowPieCharAllComponent.dialog("open");
				}
			});
		},
		/*
		*draw chart
		*/
		drawHightChart : function(data,element,nameTitle,shortCutName){
		    element.highcharts({
		        chart: {
		            type: 'pie'
		        },
		        title: {
		            text: nameTitle
		        },
		        subtitle: {
		            text: ''
		        },
		        plotOptions: {
		            series: {
		                dataLabels: {
		                    enabled: true,
		                    format: '{point.name} : {point.y:.2f}% ({point.rateReal})'
		                }
		            }
		        },

		        tooltip: {
		            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
		            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
		        },
		        series: [{
		            name: shortCutName,
		            colorByPoint: true,
		            data: data
		        }]
		       
		    });
		},
		/*
		 * 
		 */
		createToMysites : function(administratorCriteria){
			$.ajax({
				type : "POST",
				url : encodeUrl("adminWebAnalyst.createToMysites"),
				cache: false,
                contentType: false,
                processData: false,
				data : administratorCriteria,
				success : function(response) {
					if (response.status == AJAX_SUCESS) {
							console.log("status : "+response.result);
							util.messageDialog("Create to mysites Success id on Mysites : "+response.result)
						}
					}
			});
		}
		,
		/*
			* - replace space and capitalize
			*/
		stringReplace : function(a){
			a=a.replace(/-|_/g, " ");
			return a.replace(/(?:^|\s)\S/g, function(a) { return a.toUpperCase(); });
		}
	}
}())
