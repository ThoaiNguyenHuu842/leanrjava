/**
 * @author ThoaiNH
 * create Dec 11, 2015
 * module hide element on mobile
 */
(function() {
	 hiddenElements = {
		init : function() {
			$('#qb-dlg-mb-hidden-element').webToolDialog(390);
			$('#qb-dlg-mb-hidden-element .panel-hidden-elements').html(this.loadHTML());
			hiddenElements.eventListener();
		},
		eventListener : function() {
			//on click show hidden elements
			$(document).on('click','.btn-show-element',function(){
				$(this).parents('li').remove();
				var compId = $(this).attr('compId');
				componentModelManager.listComponent[compId][WEB_PRO_MDATA][WEB_PRO_HIDE] = false;
				var widget = componentModelManager.loadComponent(componentModelManager.listComponent[compId]);
				widget.find(".btn-dropdown-menu ul").append('<li class="btn-comp-hidemb"><a><i class="fa fa-eye-slash"></i>'+getLocalize("grid_title5")+'</a></li>');
				if(componentModelManager.listComponent[compId][WEB_PRO_TYPE] == WEB_COMP_TYPE_TEXT)
					widget.find(".btn-dropdown-menu ul").append('<li class="btn-mb-font-style"><a><i class="fa fa-font"></i>'+getLocalize("weh_font_scale")+'</a></li>');
				widget.goTo();
			});
		},
		open: function(){
			//init when first call
			if(!hiddenElements.inited)
			{
				hiddenElements.init();
				hiddenElements.inited = true;
			}
			$('#qb-dlg-mb-hidden-element').dialog('close');
			$('#qb-dlg-mb-hidden-element').dialog('open');
		},
		hide: function(widget){
			smallbox.remove.apply(widget);
			var compId = widget.closest('.grid-stack-item-content').attr('qb-component-id');
			var mData = componentModelManager.listComponent[compId][WEB_PRO_MDATA]?componentModelManager.listComponent[compId][WEB_PRO_MDATA]:{};
			mData[WEB_PRO_HIDE] = true;
			componentModelManager.updateItemField(compId, WEB_PRO_MDATA, mData);
			hiddenElements.open();
			$('#qb-dlg-mb-hidden-element .panel-hidden-elements').html(this.loadHTML());
		},
		/*
		 * load list HTML hidden element
		 */
		loadHTML:function(){
			var htmlString = '<ul>';
			for(var id in componentModelManager.listComponent){
				var component = componentModelManager.listComponent[id];
				if(component[WEB_PRO_MDATA] && component[WEB_PRO_MDATA][WEB_PRO_HIDE] == true)
				{
					var componentType = component[WEB_PRO_TYPE];
					var icon;
					switch(componentType)
					{
						case WEB_COMP_TYPE_TEXT:
							icon = "fa-text-width";
							break;
						case WEB_COMP_TYPE_IMG:
							icon = "fa-picture-o";
							break;
						case WEB_COMP_TYPE_BTN:
							icon = "fa-square";
							break;
						case WEB_COMP_TYPE_IFRAME:
							icon = "fa-video-camera";
							break;
						case WEB_COMP_TYPE_GMAP:
							icon = "fa-map-marker";
							break;
						case WEB_COMP_TYPE_MENU:
							icon = "fa-navicon";
							break;
						case WEB_COMP_TYPE_CONTACT_FORM:
							icon = "fa-envelope";
							break;
						default:
							break;
					}
			        var anchorName = component[WEB_PRO_ID];
			        if(component[WEB_PRO_NAME] && component[WEB_PRO_NAME] != 'null')
			        	anchorName = component[WEB_PRO_NAME];
					htmlString += "<li>" +
									"<i class='fa "+icon+"'></i>" +
									"<label title='"+anchorName+"' >Anchor name: "+anchorName+"</label>" +
									"<span compId='"+component[WEB_PRO_ID]+"'  title='Show element' class='fa fa-eye btn-show-element'></span>"+
								   "</li>";
				}
			}
			htmlString += '</ul>';
			return htmlString;
		}
	}
}());