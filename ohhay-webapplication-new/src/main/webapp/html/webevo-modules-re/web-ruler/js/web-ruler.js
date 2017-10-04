/*
 * author: ThoaiNH
 * date create: 22/08/2015
 * element web ruler
 */
 (function() {
	webRuler = {
		init : function() {
			webRuler.eventListener();
		},
		/*
		 * show ruler tools
		 */
		showRuler: function(){
			web.RULER = 'on';
			$('.qb-oohhay-out-wrapper-main').ruler({
				showCrosshair : true,
		        showMousePos: true
			}); 
			$('.qb-oohhay-out-wrapper-main').css('display','inline');
			$('.btn-mouse-pos').css('display','inline-block');
			$('.qb-oohhay-out-wrapper-main .vRule,.hRule,.btn-mouse-pos').css("display","block");
			$('#web-tools').css('left','19px');
		},
		/*
		 * hide ruler tools
		 */
		hideRuler: function(){
			web.RULER = 'off';
			$('body .vMouse').css('border-bottom','none');
			$('body .hMouse').css('border-left','none');
			$('.qb-oohhay-out-wrapper-main').unbind();
			$('.qb-oohhay-out-wrapper-main .vRule,.hRule,.btn-mouse-pos').css("display","none");
			$('#web-tools').css('left','0px');
			$("#content-wrapper").css("padding-left","0px");
		},
		eventListener : function() {
		},
	}
}());