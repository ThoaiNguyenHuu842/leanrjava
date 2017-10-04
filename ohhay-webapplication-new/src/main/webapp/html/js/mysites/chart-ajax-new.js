/**
 * @author TuNt create date Aug 25, 2016 ohhay-webapplication-new
 */
(function() {
	chartAjaxNew = {
		pnINTER : 0,// time of statistic
		TYPE : 0,// type of statistic
		init : function() {
			chartAjaxNew.eventListener();
			$('#qb-dlg-chart').webDialog(800);
			$('#qb-dlg-chart').parent().find('.ui-dialog-titlebar').remove();
			chartAjaxNew.inited = true;
		},
		open : function(option) {
			if (!chartAjaxNew.inited) {
				chartAjaxNew.init();
				chartAjaxNew.inited = true;
				// add date picker
				$('.chart-type-total .chart-date .date-select').datepicker({
					dateFormat : 'dd/mm/yy',
					changeMonth : true,
					changeYear : true,
					yearRange : '-70:+0'

				});
				// add class format
				$("#ui-datepicker-div").addClass("ui-datepicker-chart");
				// reset date input
				chartAjaxNew.resetDateChart();
				$(document).on('click','#qb-dlg-chart .chart-main .info-designer-close',function(){
					$('#qb-dlg-chart').dialog('close');
				})

			}
			chartAjaxNew.WEBID = option.webId;
			chartAjaxNew.WEBNAME = option.webName;
			$('#qb-dlg-chart .chart-main .container').html('');
			$('#qb-dlg-chart .chart-main .head .head-bg .statistics-select').val(0);
			$('#qb-dlg-chart').dialog('open');
			// set label time (a week , 1 month , 3 month, 6 month, a year)
			chartAjaxNew.selectChartTypeLabel(0);
			chartAjaxNew.loadChart(7, 0);
		},
		close : function() {
			$('#qb-dlg-chart').dialog('close');
		},
		eventListener : function() {
			$(document).on('change', '.chart-main .head .chart-type-total .chart-select', function() {
				var pnInter = $(this).val();
				if (pnInter == 0) {
					$('.chart-type-total .chart-date').removeClass('hidden')
				} else {
					$('.chart-type-total .chart-date').addClass('hidden')
					chartAjaxNew.resetDateChart();
					chartAjaxNew.loadChart(pnInter, -1);
				}

			});
			$(document).on('click', '.chart-main .head .chart-type .chart-item', function() {
				var type = $(this).attr('value');
				chartAjaxNew.loadChart(-1, type);
			});
			/*
			 * change date
			 */
			$(document).on('change paste', '.chart-type-total .chart-date .date-select', function() {
				var pdFrDat = $('.chart-type-total .chart-date .date-from').val();
				var pdToDat = $('.chart-type-total .chart-date .date-to').val();
				if(pdFrDat !== '' && pdToDat !=='')
					chartAjaxNew.loadChart(0, -1);
			});

		},
		selectChartTypeLabel : function(n) {
			$('#li' + n).addClass('selected-chart').siblings().removeClass('selected-chart');
		},
		resetDateChart : function() {
			$('.chart-type-total .chart-date .date-from').val("");
			$('.chart-type-total .chart-date .date-to').val("");
		},
		/*
		 * total view != 1 month: chart line total view 1 month: chart area
		 * other: chart column
		 */
		loadChart : function(pnInter, type) {
			var pdFrDat = $('.chart-type-total .chart-date .date-from').val();
			var pdToDat = $('.chart-type-total .chart-date .date-to').val();
			if (type != -1)
				chartAjaxNew.TYPE = type;
			if (pnInter != -1)
				chartAjaxNew.pnINTER = pnInter;
			chartAjaxNew.selectChartTypeLabel(chartAjaxNew.TYPE);

			$.ajax({
				type : 'GET',
				url : encodeUrl('statisticBean.getchartEvo'),
				data : {
					type : chartAjaxNew.TYPE,
					webId : chartAjaxNew.WEBID,
					dateCod : chartAjaxNew.pnINTER,
					dateFromString : pdFrDat,
					dateToString : pdToDat
				},
				success : function(response) {
					// we have the response
					if (response.status == AJAX_SUCESS) {
						if (response.result.length > 0) {
							$('#qb-dlg-chart .chart-main .no-data').addClass('hidden');
							switch (parseInt(chartAjaxNew.TYPE)) {
							case 0:
								chartAjaxNew.chartAll(response.result);								
								break;
							case 1:
								chartAjaxNew.chartBrowser(response.result);				
								break;
							case 2:
								chartAjaxNew.chartBrowser(response.result);				
								break;
							case 3:
								chartAjaxNew.chartBrowser(response.result);				
								break;
							case 4:
								chartAjaxNew.chartCountry(response.result);				
								break;
							case 5:
								chartAjaxNew.chartCountry(response.result);				
								break;	
							default:
								break;
							}
							
						}
						else{
							$('#qb-dlg-chart .chart-main .no-data').removeClass('hidden');
							$('#qb-dlg-chart .chart-main .container').html('');
							$('.chart-main .head .chart-type-total #total-views-chart').html('');
							$('.chart-main .head .chart-type-total #total-views-name').html('');
						}
					} else {
						showGrowlMessageAjaxError();
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		chartAll : function(e){
			var data = new Array();
			if(chartAjaxNew.pnINTER == 0){
				var pdFrDat = $('.chart-type-total .chart-date .date-from').val();
				var pdToDat = $('.chart-type-total .chart-date .date-to').val();
				
				var pdFrDatSlit = pdFrDat.split("/");
				var pdFrDatInt = parseInt(Date.UTC(pdFrDatSlit[2],pdFrDatSlit[1]-1,pdFrDatSlit[0]));
				
				var pdToDatSlit = pdToDat.split("/");
				var pdToDatInt = parseInt(Date.UTC(pdToDatSlit[2],pdToDatSlit[1]-1,pdToDatSlit[0]));
				
				var lengArr = (pdToDatInt-pdFrDatInt)/86400000
				
				
				for(var i=0; i<=parseInt(lengArr); i++){
					var arr = new Array();
					var dateHere = pdToDatInt-86400000*(parseInt(lengArr)-i);
					arr.push(dateHere);
					for (var j = 0; j < e.length; j++) {
						var d = e[j].id;
						var dSlit = d.split("/");
						var dInt = parseInt(Date.UTC(dSlit[2],dSlit[1]-1,dSlit[0]));
						if(dateHere === dInt){
							arr.push(parseInt(e[j].total));
							break;
						}else if(j===(e.length-1)){
							arr.push(0);
						}
					}
					data.push(arr);
				}	
				
			}
			else{
				var today = new Date();
				//get todate using UTC
				var date = Date.UTC(today.getFullYear(), today.getMonth(), today.getDate()+1);
				//convert UTC to int
				var dayInt = parseInt(date);
				for(var i=0; i<parseInt(chartAjaxNew.pnINTER); i++){
					var arr = new Array();
					var dateHere = dayInt-86400000*(parseInt(chartAjaxNew.pnINTER)-i);
					arr.push(dateHere);
					for (var j = 0; j < e.length; j++) {
						var d = e[j].id;
						var dSlit = d.split("/");
						var dInt = parseInt(Date.UTC(dSlit[2],dSlit[1]-1,dSlit[0]));
						if(dateHere === dInt){
							arr.push(parseInt(e[j].total));
							break;
						}else if(j===(e.length-1)){
							arr.push(0);
						}
					}
					data.push(arr);
				}	
			}
			
			$('#qb-dlg-chart .chart-main .container').highcharts({
				   chart: {
			            zoomType: 'x'
			        },
			        title: {
			            text: ''
			        },
			        subtitle: {
			            text: document.ontouchstart === undefined ?
			                    'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
			        },
			        xAxis: {
			            type: 'datetime'
			        },
			        yAxis: {
			            title: {
			                text: 'Viewer'
			            }
			        },
			        legend: {
			            enabled: false
			        },
			        plotOptions: {
			            area: {
			                fillColor: {
			                    linearGradient: {
			                        x1: 0,
			                        y1: 0,
			                        x2: 0,
			                        y2: 1
			                    },
			                    stops: [
			                        [0, Highcharts.getOptions().colors[0]],
			                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
			                    ]
			                },
			                marker: {
			                    radius: 2
			                },
			                lineWidth: 1,
			                states: {
			                    hover: {
			                        lineWidth: 1
			                    }
			                },
			                threshold: null
			            }
			        },

			        series: [{
			            type: 'area',
			            name: 'Viewer',
			            data: data
			        }]
			});
		},
		chartBrowser : function(e){
			var data = new Array();
			for (var i = 0; i < e.length; i++) {
				var dataItem = new Object();
				dataItem.name = e[i].id;
				dataItem.y = e[i].total;
				data.push(dataItem)
			}
			$('#qb-dlg-chart .chart-main .container').highcharts({
				 chart: {
				        plotBackgroundColor: null,
				        plotBorderWidth: null,
				        plotShadow: false,
				        type: 'pie'
				    },
				    title: {
				        text: ''
				    },
				    tooltip: {
				        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
				    },
				    plotOptions: {
				        pie: {
				            allowPointSelect: true,
				            cursor: 'pointer',
				            dataLabels: {
				                enabled: true,
				                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
				                style: {
				                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
				                }
				            }
				        }
				    },
				    series: [{
				        name: 'Viewer: ',
				        colorByPoint: true,
				        data: data
				    }]
			})
		},
		chartCountry : function(e){
			var dataX = new Array();
			var dataY = new Array();
			for (var i = 0; i < e.length; i++) {
				var dataItem = new Object();
				dataX.push(e[i].id);
				dataY.push(e[i].total);
			}
			$('#qb-dlg-chart .chart-main .container').highcharts({
				chart: {
			        type: 'column'
			    },
			    title: {
			        text: ''
			    },
			    xAxis: {
			        categories: dataX
			    },
			    yAxis: {
			        min: 0,
			        title: {
			            text: 'Viewer'
			        },
			        stackLabels: {
			            enabled: true,
			            style: {
			                fontWeight: 'bold',
			                color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
			            }
			        }
			    },
			    legend: {
			        align: 'right',
			        x: -30,
			        verticalAlign: 'top',
			        y: 25,
			        floating: true,
			        backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
			        borderColor: '#CCC',
			        borderWidth: 1,
			        shadow: false
			    },
			    tooltip: {
			        headerFormat: '<b>{point.x}</b><br/>',
			        pointFormat: '{series.name}: {point.y}<br/>'
			    },
			    plotOptions: {
			        column: {
			            stacking: 'normal',
			            dataLabels: {
			                enabled: true,
			                color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
			            }
			        }
			    },
			    series: [{
			        name: 'Viewer',
			        data: dataY
			    }]
			});
		}
	}
}());
