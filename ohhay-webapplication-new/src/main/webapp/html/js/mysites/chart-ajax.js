(function() {
	chartAjax = {
		RN120: 0,//time of statistic
		TYPE: 0,//type of statistic
		init: function(){
			chartAjax.eventListener();
			$('#qb-dlg-chart').webDialog(800);
			chartAjax.inited = true;
		},
		open:function(option){
			if(!chartAjax.inited){
				chartAjax.init();
				chartAjax.inited = true;
			}
			chartAjax.WEBID = option.webId;
			$('#qb-dlg-chart .chart-main .container').html('');
			$('#qb-dlg-chart .chart-main .head .head-bg .statistics-select').val(1);
			$('#qb-dlg-chart').dialog('open');
			chartAjax.selectChartTimeLabel(0);
			chartAjax.loadChart(0,0);
		},
		close:function(){
			$('#qb-dlg-chart').dialog('close');
		},
		eventListener: function(){
			$(document).on('change','#qb-dlg-chart .chart-main .head .head-bg .statistics-select', function(){
				var type = $(this).val();
				chartAjax.loadChart(-1,type-1);
			});
			$(document).on('click','#qb-dlg-chart .chart-main .head .head-bg .label-time .labl',function(){
				var rn120 = $(this).attr('data-chart-time');
				chartAjax.loadChart(rn120,-1);
			});
		},
		selectChartTimeLabel:function(n){
			$('#labl'+n).addClass('labl-selected').siblings().removeClass('labl-selected');
		},
		/*
		 * total view != 1 month: chart line
		 * total view 1 month: chart area
		 * other: chart column 
		 */
		loadChart : function(rn120, type) {
			if(type != -1)
				chartAjax.TYPE = type;
			if(rn120 != -1)
				chartAjax.RN120 = rn120;
			chartAjax.selectChartTimeLabel(chartAjax.RN120);
			var chartType = 'line';
			if(chartAjax.TYPE != 0)
				chartType  = 'column'; 
			$.ajax({
						type : 'POST',
						url : encodeUrl('statisticBean.getchart1'),
						data : 'rn120=' + encodeURIComponent(chartAjax.RN120)+
							   '&type=' + encodeURIComponent(chartAjax.TYPE)+
							   '&webId='+ encodeURIComponent(chartAjax.WEBID)+
							   '&rv957=E400',
						success : function(response) {
							// we have the response
							if (response.status == AJAX_SUCESS) {
								var listR100 = response.result;
								var arrayXX = [];
								var arrayYY = [];
								var fistDate; // first date to create area
								// chart
								for (var i = 0; i < listR100.length; i++) {
									if (i == 0)
										fistDate = listR100[i].xx;
									if (listR100[i].xm)
										arrayXX.push(listR100[i].xm);
									else
										arrayXX.push(listR100[i].xx);
									arrayYY.push(listR100[i].yy);

								}
								if (chartAjax.RN120 != 1 || chartAjax.TYPE != 0) {
									$('#qb-dlg-chart .chart-main .container').highcharts({
										chart : {
											type : chartType
										},
										xAxis : {
											categories : arrayXX
										},
										yAxis : {
											title : {
												text : 'Total view'
											}
										},
										title : {
											text : ''
										},
										plotOptions : {
											line : {
												dataLabels : {
													enabled : true
												},
												enableMouseTracking : false
											}
										},
										series : [ {
											name : 'Website',
											data : arrayYY
										} ]
									});
								} else {
									// get first date dataset
									var res = fistDate.split('-');
									var y = res[0];
									var m = res[1] - 1;
									var d = res[2];
									$('#qb-dlg-chart .chart-main .container').highcharts({
														chart : {
															zoomType : 'x'
														},
														title : {
															text : ''
														},
														subtitle : {
															text : document.ontouchstart === undefined ? getLocalize('chart_message')
																	: getLocalize('chart_message')
														},
														xAxis : {
															type : 'datetime',
															minRange : 14 * 24 * 3600000
														// fourteen days
														},
														yAxis : {
															title : {
																text : getLocalize('chart_totalview')
															}
														},
														legend : {
															enabled : false
														},
														plotOptions : {
															area : {
																fillColor : {
																	linearGradient : {
																		x1 : 0,
																		y1 : 0,
																		x2 : 0,
																		y2 : 1
																	},
																	stops : [
																			[ 0, Highcharts.getOptions().colors[0] ],
																			[ 1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba') ] 
																			]
																},
																marker : {
																	radius : 2
																},
																lineWidth : 1,
																states : {
																	hover : {
																		lineWidth : 1
																	}
																},
																threshold : null
															}
														},

														series : [ {
															type : 'area',
															name : getLocalize('chart_totalview'),
															pointInterval : 24 * 3600 * 1000,
															pointStart : Date.UTC(y, m,d),
															data : arrayYY
														} ]
													});
								}
							} else {
								showGrowlMessageAjaxError();
							}
						},
						error : function(e) {
							showGrowlMessageAjaxError();
						}
					});
		}
	}
}());
