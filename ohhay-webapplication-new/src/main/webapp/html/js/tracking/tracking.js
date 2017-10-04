/**
 *
 * 
 */
(function(){
	trackingAjax = {
			init: function(option){
				trackingAjax.eventListener();
				trackingAjax.FO100 = option.fo100;
				trackingAjax.WEBID = option.webId;
				trackingAjax.currentLanguage = option.currentLanguage
			},
			eventListener:function(){
				$(document).on('click','a.obutton-link[data-action-tracking="1"]',function(){
					var objectName = $(this).parents('.grid-stack-item-content').attr('id');
					//var objectName = $(this).parents('.grid-stack-item-content.qb-component-button.obutton').attr('qb-component-id');
					console.log(objectName);
				    $.ajaxSetup({
					    beforeSend : function()  {
					    },
					    complete : function() {
					     setDefaultAjaxStatus();
					    }
					   });
				    $.ajax({
					    type : "POST",
					    url : encodeUrl("webTrackingBean.tracking"),
					    data : "fo100=" + encodeURIComponent(trackingAjax.FO100) + 
					        "&objectName=" + encodeURIComponent(objectName)+
					        "&languageCode=" + encodeURIComponent(trackingAjax.currentLanguage)+
					        "&webId=" + encodeURIComponent(trackingAjax.WEBID),
					    success : function(response) {
					    },
					    error : function(e) {
					    }
					   });
				});
			},
			trackingViewer:function(fo100,webId,currentLanguage){
				$.ajaxSetup({
					beforeSend : function()  {
					},
					complete : function() {
						setDefaultAjaxStatus();
					}
				});
				$.ajax({
					type : "POST",
					url : encodeUrl("webTrackingBean.trackingViewer"),
					data : "fo100=" + encodeURIComponent(trackingAjax.FO100) + 
						   "&languageCode=" + encodeURIComponent(trackingAjax.currentLanguage)+
						   "&webId=" + encodeURIComponent(trackingAjax.WEBID),
					success : function(response) {
						
					},
					error : function(e) {
					
					}
				});
			},
	}
}());