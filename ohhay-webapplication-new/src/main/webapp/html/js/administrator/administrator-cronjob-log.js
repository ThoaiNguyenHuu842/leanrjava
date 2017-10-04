/**
 * @author TuNt create date Apr 22, 2016 ohhay-webapplication-new
 */
(function() {
	adminCronjobLog = {
		init : function() {
			adminCronjobLog.eventListener();
		},
		loadCronjobLog : function() {
			$.ajax({
				url : encodeUrl("adminCronjobLog.loadCronjobLog"),
				data : {

				},
				success : function(response) {
					var html = '';
					var data = response.result;
					/*
					 * check on off logger
					 */
					((response.result2.sn001==1) ? $('.card-switch-logger #switch-logger-server').prop('checked', true) : $('.card-switch-logger #switch-logger-server').prop('checked', false));
					if (!data)
						return;
					// for(var i = 0 ; i<data.length;i++)
					for (var i = 0; i < data.length; i++) {
						html += '<div  class="col-lg-6">';
						html += '<h3>' + data[i] + '</h3>';
						html += '<input type="button" class="pull-right" value="Clear">'
						html += '<textarea rows="10" class="form-control" typelog="' + data[i] + '" readonly>';
						html += '</textarea>';
						html += '</div> ';
						adminCronjobLog.loadCronjobLogContent(data[i]);
					}
					$('.qb-administrator-cronjob-log .cronjob-wapper').html(html);
					
					
				},
				error : function() {

				}
			})
		},
		loadCronjobLogContent : function(typelog) {
			$.ajaxSetup({
				beforeSend : function() {

				},
				complete : function() {

					setDefaultAjaxStatus();
				}
			});
			$.ajax({
				url : encodeUrl("adminCronjobLog.loadCronjobLogContent"),
				data : {
					'typelog' : typelog
				},
				success : function(response) {
					var data = response.result;
					var htmlString = '';
					for (var i = 0; i < data.length; i = i + 2) {
						htmlString += '' + data[i];
						htmlString += '.............';
						htmlString += '' + data[i + 1] + '\n';
					}

					$('textarea[typelog="' + typelog + '"]').append(htmlString);
					setTimeout(function() {
						adminCronjobLog.loadCronjobLogContent(typelog);
					}, 3000);
				},
				error : function() {

				}

			})
		},
		/*
		 * change Logger
		 */
		changeLogger : function() {
			var turnOnOff = ($(".card-switch-logger #switch-logger-server").is(':checked') ? 0 : 1);
			console.log(turnOnOff);
			$.ajax({
				url : encodeUrl("adminCronjobLog.changeLogger"),
				method : "POST",
				data : {
					'on' : turnOnOff
				},
				success : function(response) {
					var data = response.result;
					
				},
				error : function() {

				}

			});
		},
		eventListener : function() {
			$(document).on('click', '.qb-administrator-cronjob-log input[type="button"]', function() {
				$(this).parent().find("textarea.form-control").empty();
			});
			
			$(document).on('click','.toggle',function(){
				adminCronjobLog.changeLogger();
			});
		}

	}
}())