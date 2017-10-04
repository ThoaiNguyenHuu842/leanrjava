/**
 * @author ThoaiNH create Dec 11, 2015 module hide element on mobile
 */
(function() {
	hiddenElements = {
		init : function() {
			$('#qb-dlg-mb-hidden-element').webToolDialog(450);
			$('#qb-dlg-mb-hidden-element .panel-hidden-elements').html(this.loadHTML());
			hiddenElements.eventListener();
		},
		eventListener : function() {
			$("#qb-dlg-mb-hidden-element .panel-hidden-elements ul").niceScroll();
			// listener change element hidden
			$('#menu-tools .item-center p .element-hidden').attrchange({
				trackValues : true,
				callback : function(event) {
					if (event.attributeName == 'hiddennumb'){
						if(event.newValue>9)
							$(this).removeClass("lt-element-hidden").addClass("gt-element-hidden");
						else
							$(this).removeClass("gt-element-hidden");
					}
				}
			});
			// on click show hidden elements
			$(document).on('click', '.btn-show-element', function() {
				if ($(this).hasClass('component')) {
					var compId = $(this).attr('compId');
					var boxId = componentModelManager.listComponent[compId][WEB_PRO_BOXID];
					// process when box include this component is hidden
					if (bigBoxModelManager.listBox[boxId][WEB_PRO_MDATA] && bigBoxModelManager.listBox[boxId][WEB_PRO_MDATA][WEB_PRO_HIDE] == true) {
						util.confirmDialog("The section wraps this component is being hidden, Do you want to show it?", function() {
							$('.btn-show-element.box[boxid="' + boxId + '"]').trigger('click');
							hiddenElements.showComponent(compId);
						});
					} else
						hiddenElements.showComponent(compId);
				} else {
					var boxId = $(this).attr('boxId');
					hiddenElements.showBox(boxId);
				}
				$(this).parents('li').remove();
				var size = $("#qb-dlg-mb-hidden-element .panel-hidden-elements ul li").size();
				$("#menu-tools .item-center p .element-hidden").html(size).attr('hiddennumb', size), ((size == 0) ? $("#qb-dlg-mb-hidden-element .panel-hidden-elements ul").html('<p class="no-element">No element hidden</p>') : "");
			});
			$(document).on('input', '#qb-dlg-mb-hidden-element .hidden-find-elements .elements-text-find', function() {
				var filter = $(this).val();
				var list = $('#qb-dlg-mb-hidden-element .panel-hidden-elements ul')
				if (filter) {
					list.find("label:not(:Contains(" + filter + "))").parent().slideUp();
					list.find("label:Contains(" + filter + ")").parent().slideDown();
				} else {
					list.find("li").slideDown();
				}
			})
		},
		showComponent : function(compId) {
			componentModelManager.listComponent[compId][WEB_PRO_MDATA][WEB_PRO_HIDE] = false;
			var widget = componentModelManager.loadComponent(componentModelManager.listComponent[compId]);
			widget.find(".btn-dropdown-menu ul").append('<li class="btn-comp-hidemb"><a><i class="fa fa-eye-slash"></i>' + getLocalize("grid_title5") + '</a></li>');
			if (componentModelManager.listComponent[compId][WEB_PRO_TYPE] == WEB_COMP_TYPE_TEXT || componentModelManager.listComponent[compId][WEB_PRO_TYPE] == WEB_COMP_TYPE_BTN)
				widget.find(".btn-dropdown-menu ul").append('<li class="btn-mb-font-style"><a><i class="fa fa-font"></i>' + getLocalize("weh_font_scale") + '</a></li>');
			widget.goTo();
		},
		showBox : function(boxId) {
			// update model
			bigBoxModelManager.listBox[boxId][WEB_PRO_MDATA][WEB_PRO_HIDE] = false;
			$("#content-wrapper .content-box[qb-box-id='" + boxId + "']").removeClass('qb-box-hidden');
			$("#content-wrapper .content-box[qb-box-id='" + boxId + "']").goTo();
		},
		open : function() {
			// init when first call
			if (!hiddenElements.inited) {
				hiddenElements.init();
				hiddenElements.inited = true;
			}
			$('#qb-dlg-mb-hidden-element').dialog('close');
			$('#qb-dlg-mb-hidden-element').dialog('open');
		},
		hide : function(widget) {
			smallbox.remove.apply(widget);
			var compId = widget.closest('.grid-stack-item-content').attr('qb-component-id');
			var mData = componentModelManager.listComponent[compId][WEB_PRO_MDATA] ? componentModelManager.listComponent[compId][WEB_PRO_MDATA] : {};
			mData[WEB_PRO_HIDE] = true;
			componentModelManager.updateItemField(compId, WEB_PRO_MDATA, mData);
			hiddenElements.open();
			$('#qb-dlg-mb-hidden-element .panel-hidden-elements').html(this.loadHTML());
		},
		hideBigBox : function(widget) {
			var boxID = widget.closest('.qb-box-component').attr('qb-box-id');
			widget.addClass('qb-box-hidden');
			var mData = bigBoxModelManager.listBox[boxID][WEB_PRO_MDATA] ? bigBoxModelManager.listBox[boxID][WEB_PRO_MDATA] : {};
			mData[WEB_PRO_HIDE] = true;
			bigBoxModelManager.updateItemField(boxID, WEB_PRO_MDATA, mData);
			hiddenElements.open();
			$('#qb-dlg-mb-hidden-element .panel-hidden-elements').html(this.loadHTML());
		},
		/*
		 * load list HTML hidden element
		 */
		loadHTML : function() {
			var htmlString = '<ul>', noelement = "<p class='no-element'>No element hidden</p>";
			var hasHiddenElements = false;
			for ( var id in componentModelManager.listComponent) {
				var component = componentModelManager.listComponent[id];
				if (component[WEB_PRO_MDATA] && component[WEB_PRO_MDATA][WEB_PRO_HIDE] == true) {
					hasHiddenElements = true;
					var componentType = component[WEB_PRO_TYPE];
					var icon;
					var bg;
					switch (componentType) {
					case WEB_COMP_TYPE_TEXT:
						icon = "fa-text-width";
						bg = "#8f9aa4";
						break;
					case WEB_COMP_TYPE_IMG:
						icon = "fa-picture-o";
						bg = "#f26245";
						break;
					case WEB_COMP_TYPE_BTN:
						icon = "fa-square";
						bg = "#2a91d8";
						break;
					case WEB_COMP_TYPE_IFRAME:
						icon = "fa-video-camera";
						bg = "#e43f50";
						break;
					case WEB_COMP_TYPE_GMAP:
						icon = "fa-map-marker";
						bg = "#00bdaf";
						break;
					case WEB_COMP_TYPE_MENU:
						icon = "fa-navicon";
						bg = "#b47dd5";
						break;
					case WEB_COMP_TYPE_CONTACT_FORM:
						icon = "fa-envelope";
						bg = "#00b4d6";
						break;
					default:
						break;
					}
					var anchorName = component[WEB_PRO_ID];
					if (component[WEB_PRO_NAME] && component[WEB_PRO_NAME] != 'null')
						anchorName = component[WEB_PRO_NAME];
					htmlString += "<li>" + "<i class='fa " + icon + "'style='background: " + bg + ";'></i>" + "<label title='" + anchorName + "'>" + anchorName + "</label>" + "<span compId='" + component[WEB_PRO_ID] + "'  title='Show element' class='fa fa-eye btn-show-element component'></span>" + "</li>";
				}
			}
			for ( var id in bigBoxModelManager.listBox) {
				var box = bigBoxModelManager.listBox[id];
				if (box[WEB_PRO_MDATA] && box[WEB_PRO_MDATA][WEB_PRO_HIDE] == true) {
					hasHiddenElements = true;
					var icon = "fa-minus";
					var bg = "#697dd1";
					var anchorName = box[WEB_PRO_ID];
					if (box[WEB_PRO_NAME] && box[WEB_PRO_NAME] != 'null')
						anchorName = box[WEB_PRO_NAME];
					htmlString += "<li>" + "<i class='fa " + icon + "'style='background: " + bg + ";'></i>" + "<label title='" + anchorName + "' >Anchor name: " + anchorName + "</label>" + "<span boxId='" + box[WEB_PRO_ID] + "'  title='Show element' class='fa fa-eye btn-show-element box'></span>" + "</li>";
				}
			}
			if (hasHiddenElements)
				htmlString += '</ul>';
			else
				htmlString += noelement + '</ul>';
			return htmlString;
		}
	}
}());