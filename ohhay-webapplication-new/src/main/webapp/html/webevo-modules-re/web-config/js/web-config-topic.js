/*
 * author ThoaiNH
 * date create 20/10/2015
 */
(function() {
	webConfig = {
		data: {
			w: PAGE_TOPIC_CONTANTS,
			id:0
		},
		init : function() {
			$('#qb-web-config').webToolDialog(396);
			/*
			 * edit web width
			 */
			var select = $("#qb-web-config #select-web-width");
			var slider = $("<div id='slider'></div>").insertAfter( select ).slider({
			      min: 1,
			      max: 5,
			      range: "min",
			      value: $("#select-web-width").find("option[label="+webConfig.data.w+"]").attr('val'),
			      slide: function( event, ui ) {
			    	  $("#qb-panel-web-config-lbl-value-width").html($("#select-web-width").find("option[val="+ui.value+"]").html() + "px");
				      webConfig.data.w = $("#select-web-width").find("option[val="+ui.value+"]").html();
			    	 
			    	  webConfig.updateWidth();
			      }
			});
			
			if(PAGE_TOPIC_CONTANTS != 0){
				$("#qb-panel-web-config-lbl-value-width").html(PAGE_TOPIC_CONTANTS + "px");
			}else{
				$("#qb-panel-web-config-lbl-value-width").html(webConfig.data.w + "px");
			}
			
			/*
			 * edit web Horizontal Align
			 */
			$("#qb-web-config #qb-slider-margin-left").slider({
			      min: 0,
			      max: 500,
			      range: "min",
			      value: webConfig.data.mgL,
			      slide: function( event, ui ) {
			    	  $('#qb-web-config #qb-panel-web-config-lbl-value-margin-left').html(ui.value);
			    	  webConfig.data.mgL = ui.value;
			    	  webConfig.updateMgL();
			      }
			});
			$(document).on('click','#qb-web-config #qb-check-web-center', function(){
				if($(this).is(":checked"))
				{
					$("#qb-web-config #qb-slider-margin-left").slider("disable");
					$("#qb-web-config .input-fied-margin-left").addClass("disable");
					webConfig.data.mgL = -1;
					webConfig.updateMgL();
				}
				else
				{
					$("#qb-web-config .input-fied-margin-left").removeClass("disable");
					$("#qb-web-config #qb-slider-margin-left").slider("enable");
					$("#qb-web-config #qb-slider-margin-left").slider("value",0);
					$('#qb-web-config #qb-panel-web-config-lbl-value-margin-left').html(0);
					webConfig.data.mgL = 0;
					webConfig.updateMgL();
				}
			});
			if(webConfig.data.mgL == -1)
			{
				$('#qb-web-config #qb-check-web-center').prop('checked', true);
				$("#qb-web-config #qb-slider-margin-left").slider("disable");
				$("#qb-web-config .input-fied-margin-left").addClass("disable");
			}
			/*
			 * edit web Vertical Align top
			 */
			$("#qb-web-config #qb-slider-margin-top").slider({
			      min: 0,
			      max: 500,
			      range: "min",
			      value: webConfig.data.mgT,
			      slide: function( event, ui ) {
			    	  $('#qb-web-config #qb-panel-web-config-lbl-value-margin-top').html(ui.value + 'px');
			    	  webConfig.data.mgT = ui.value;
			    	  webConfig.updateMgT();
			      }
			});
			$('#qb-web-config #qb-panel-web-config-lbl-value-margin-top').html(webConfig.data.mgT + 'px');
			/*
			 * edit web Vertical Align bottom
			 */
			$("#qb-web-config #qb-slider-margin-bottom").slider({
			      min: 0,
			      max: 500,
			      range: "min",
			      value: webConfig.data.mgB,
			      slide: function( event, ui ) {
			    	  $('#qb-web-config #qb-panel-web-config-lbl-value-margin-bottom').html(ui.value + 'px');
			    	  webConfig.data.mgB = ui.value;
			    	  webConfig.updateMgB();
			      }
			});
			$('#qb-web-config #qb-panel-web-config-lbl-value-margin-bottom').html( webConfig.data.mgB + 'px');
			webConfig.eventListener();
		},
		open: function(){
			if(!webConfig.inited)
			{
				webConfig.init();
				webConfig.inited = true;
			}
			$('#qb-web-config').dialog('open');
		
		},
		eventListener : function() {
			
		},
		updateWidth: function(){
			if(PAGE_TOPIC_CONTANTS != 0){
				$('#content-wrapper .content-box .grid-stack-re').css({"width":PAGE_TOPIC_CONTANTS+"px"});
			}else{
				$('#content-wrapper .content-box .grid-stack-re').css({"width":webConfig.data.w+"px"});
			}
			
		},
		updateMgL: function(){
			 if(webConfig.data.mgL >= 0)
				 $('#content-wrapper .content-box .grid-stack-re').css({
		    		  'margin-left': webConfig.data.mgL
		    	 });
			 else
				 $('#content-wrapper .content-box .grid-stack-re').css({
					 'margin-left': ''
		    	 });
		},
		updateMgT: function(){
			$('#content-wrapper').css({
				'padding-top':webConfig.data.mgT
			});
		},
		updateMgB: function(){
			$('#content-wrapper').css({
				'padding-bottom':webConfig.data.mgB
			});
		},
		update: function(){
			this.updateWidth();
			this.updateMgL();
			this.updateMgT();
			this.updateMgB();
		}
	}
}());