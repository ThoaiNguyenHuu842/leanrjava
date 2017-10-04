/**
 * @author ThoaiNH
 * created Dec 13, 2016
 */
(function() {
	contextMenu = {
		selectedBoxId: 0,
		init : function() {
			contextMenu.eventListener();
		},
		eventListener : function() {
			$(document).on("contextmenu",".content-box",function(e){
				if (!($('.gird-stack-item-hover').length > 0)){ 
					if(web.MODE != 'preview' && web.MOBILE_EDITOR != 'on'){
						if(smallbox.CLIPBOARD_ID  != 0 && componentModelManager.listComponent[smallbox.CLIPBOARD_ID] && componentModelManager.listComponent[smallbox.CLIPBOARD_ID][WEB_PRO_STT] != WEB_PRO_STT_REMOVED)
							$('#bonevo-context-menu .btn-paste').removeClass('de-active');
						else
							$('#bonevo-context-menu .btn-paste').addClass('de-active');
						contextMenu.selectedBoxId = $(this).attr("qb-box-id");
						$("#bonevo-context-menu").css( {top:e.pageY, left: e.pageX});
						$("#bonevo-context-menu").show();
			            e.preventDefault();
					}
				}
			});
			$(document).on("click","body",function(e){
				$("#bonevo-context-menu").hide();
			});
			$(document).on("click","#bonevo-context-menu .btn-paste",function(e){
				if(!$(this).hasClass('de-active'))
					componentModelManager.duplicate(smallbox.CLIPBOARD_ID, contextMenu.selectedBoxId);
			});
			$(document).on("click","#bonevo-context-menu .btn-delete",function(e){
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					bigbox.remove(contextMenu.selectedBoxId);
				})
			});
		}
	};
}());
