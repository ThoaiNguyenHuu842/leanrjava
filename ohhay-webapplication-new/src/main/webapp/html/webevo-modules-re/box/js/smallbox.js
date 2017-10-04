/**
 * @author ThoaiNH create Oct 9, 2015
 */
(function() {
	smallbox = {
		isShowToolbar : false,
		targetBox : null,
		KEY_CTRL : false,
		KEY_ALT : false,
		CLIPBOARD_ID : 0,
		init : function() {
			this.eventListener();
		},
		eventListener : function() {
			/*
			 * ThoaiVt 17/12/2015: onload effect for text, button, menu
			 * 
			 */
			$(document).on('click', '.grid-stack-item-content .btn-set-onload-effect', function() {
				var component = $(this).closest('.grid-stack-item-content').find(".qb-component");
				var compId = $(this).parents('.grid-stack-item-content').attr('qb-component-id');
				animationOption.open({
					target : component,
					callBack : function(result) {
						if (result.type == 'load') {// khi nào chạy cái này?
							var crEffect = component.attr("onload-effect");
							component.attr("onload-effect", result.attr_val);
							// remove old class Effect
							if (crEffect != undefined)
								component.removeClass(crEffect);
							// add class effect
							component.addClass(result.attr_val);
							component.addClass('animated ');
							// get value data
							var mData = componentModelManager.listComponent[compId][WEB_PRO_DATA] ? componentModelManager.listComponent[compId][WEB_PRO_DATA] : {};
							componentModelManager.listComponent[compId][WEB_PRO_ONLOAD_EFFECT] = result.attr_val;
							// update field
							componentModelManager.updateItemField(compId, WEB_PRO_DATA, mData);
						} else if (result.type == 'removeEffect') {// khi nào chạy cái này?
							// remove Effect
							var crEffect = component.attr("onload-effect");
							component.removeAttr("onload-effect");
							component.removeClass(crEffect);
							component.removeClass('animated ');
							componentModelManager.listComponent[compId][WEB_PRO_ONLOAD_EFFECT] = '';
							// update field
							componentModelManager.updateItemField(compId, WEB_PRO_DATA, mData);
						}/*
							 * else if(result.type == 'duratime'){ //set time
							 * animated in element
							 * component.attr("style","animation-duration:"+result.data['time']);
							 * componentModelManager.listComponent[compId][WEB_PRO_ONLOAD_EFFECT] =
							 * ''; }
							 */

					}
				});
			});
			/*
			 * key/event action grid item
			 */
			window.addEventListener("keydown", function(e) {
				console.log('down with key:' + e.keyCode);
				if (smallbox.KEY_CTRL == true)
					smallbox.keyListenerWithCtrl(e);
				else if (smallbox.KEY_ALT == true)
					smallbox.keyListenerWithAlt(e);
				else
					smallbox.keyListener(e);

				if (e.keyCode == 17)
					smallbox.KEY_CTRL = true;
				else if (e.keyCode == 18)
					smallbox.KEY_ALT = true;
			}, true);
			window.addEventListener("keyup", function(e) {
				if (e.keyCode == 17)
					smallbox.KEY_CTRL = false;
				else if (e.keyCode == 18)
					smallbox.KEY_ALT = false;
			}, true);
			/*
			 * hide on mobile
			 */
			$(document).on('click', '.grid-stack-item-content .btn-comp-hidemb', function() {
				hiddenElements.hide($(this).parents('.grid-stack-item-content'));
			});
			/*
			 * copy small box
			 */
			$(document).on('click', '.grid-stack-item-content .btn-comp-duplicate', function() {
				componentModelManager.duplicate($(this).parents('.grid-stack-item-content').attr('qb-component-id'));
			});
			/*
			 * delete small box
			 */
			$(document).on('click', '.grid-stack-item-content .btn-del', function() {
				var self = $(this).parents('.grid-stack-item-content');
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					if (self.parents('.grid-stack-item-content').hasClass('comp-image'))
						web.endModeUploadImg();
					smallbox.remove.apply(self);
					var componentId = self.attr('qb-component-id');
					componentModelManager.removeItemFromModel(componentId);
				});
			});
			/*
			 * change option of smallbox
			 */
			$(document).on('click', '.grid-stack-item-content .btn-option', function() {
				var ob = $(this).closest('.grid-stack-item-content').find('.qb-component');
				var compId = $(this).closest('.grid-stack-item-content').attr('qb-component-id');
				var type = componentModelManager.listComponent[compId][WEB_PRO_TYPE];
				var rotate = '#cp-ot-rotate';
				var mode = $(this).closest('.grid-stack-item-content').attr('data-mode');
				if (type === WEB_COMP_TYPE_TEXT || type === WEB_COMP_TYPE_IFRAME || (type === WEB_COMP_TYPE_IMG && mode === "ONE"))
					rotate += ' div label';
				cp_Option.open({
					callBack : function(result) {
						result.style === 'rotate' ? webUtils.setTransformRotate(ob, result.value) : ob.css(result.style, result.value);
						// update model
						if (web.MOBILE_EDITOR == 'on') {
							var mData = componentModelManager.listComponent[compId][WEB_PRO_MDATA] ? componentModelManager.listComponent[compId][WEB_PRO_MDATA] : {};
							mData[WEB_PRO_CSS] = webUtils.getCssEffect(ob);
							componentModelManager.updateItemField(compId, WEB_PRO_MDATA, mData);
						} else {
							componentModelManager.updateItemField(compId, WEB_PRO_CSS, webUtils.getCssEffectV2(ob));
						}
					},
					self : ob,
					del : [ '#cp-ot-border-inner', '#cp-ot-opacity', '#cp-ot-blur', '#cp-ot-width', '#cp-ot-height', rotate ]
				});
			});
			/*
			 * set background small box
			 */
			$(document).on('click', '.grid-stack-item-content .btn-set-background', function(event) {

				var $self = $(event.currentTarget), 
				//
				targetBox = $self.closest('.grid-stack-item-content').find('.layer-background'), 
				//
				type = $(this).hasClass('img-comp') ? 'I' : 'S';
				// ANPH: new function
				backgroundSrc.setBackground(targetBox, type);
			});
			$(document).on('click', '.grid-stack-item-content .btn-set-anchor', function() {
				var compId = $(this).closest('.grid-stack-item-content').attr('qb-component-id');
				anchorOption.open({
					targetID : compId,
					isBox : false,
				});
			});

			$(document).on('click', '.grid-stack-item-content .btn-set-writing-mode', function() {
				var ob = $(this).closest('.grid-stack-item-content').find('.qb-component');
				var compId = $(this).closest('.grid-stack-item-content').attr('qb-component-id');
				var data = componentModelManager.getDataFromID(compId);
				if (data['writingMode'] && data['writingMode'] === 'vertical') {
					data['writingMode'] = 'horizontal';
					ob.removeClass('vertical-rl');
					$(this).find('span.title.writing-mode').text(getLocalize("jsott_title1"));
				} else {
					data['writingMode'] = 'vertical';
					ob.addClass('vertical-rl');
					$(this).find('span.title.writing-mode').text(getLocalize("jsott_title3"));
				}
				componentModelManager.updateItemField(compId, WEB_PRO_DATA, data);
			});
		},
		/*
		 * remove small box
		 */
		remove : function() {
			$(this).closest('.grid-stack').data('gridstack').remove_widget($(this), true);
		},
		/*
		 * key handle with alt
		 */
		keyListenerWithAlt : function(e) {
			console.log('action with key:' + e.keyCode);
			var focusingItem = $('.gird-stack-item-hover');
			if ([ 37, 38, 39, 40 ].indexOf(e.keyCode) > -1 && typeof (focusingItem) != 'undefined' && focusingItem != null && typeof (focusingItem.attr('class')) != 'undefined') {
				e.preventDefault();
				var x = parseInt(focusingItem.attr('data-gs-x'));
				var y = parseInt(focusingItem.attr('data-gs-y'));
				var w = parseInt(focusingItem.attr('data-gs-width'));
				var h = parseInt(focusingItem.attr('data-gs-height'));
				switch (e.keyCode) {
				// left
				case 37:
					if (x != 0)
						focusingItem.parents('.grid-stack').data('gridstack').update(focusingItem, x - 1, y, w, h);
					break;
				// up
				case 38:
					if (y != 0)
						focusingItem.parents('.grid-stack').data('gridstack').update(focusingItem, x, y - 1, w, h);
					break;
				// right
				case 39:
					if (x + w != gridConfig.width)
						focusingItem.parents('.grid-stack').data('gridstack').update(focusingItem, x + 1, y, w, h);
					break;
				// down
				case 40:
					focusingItem.parents('.grid-stack').data('gridstack').update(focusingItem, x, y + 1, w, h);
					break;
				default:
					break;
				}

			}

		},
		/*
		 * key handle
		 */
		keyListener : function(e) {
			// console.log('action with key:' + e.keyCode);
			var focusingItem = $('.gird-stack-item-hover');
			if ([ 46 ].indexOf(e.keyCode) > -1 && typeof (focusingItem) != 'undefined' && focusingItem != null && typeof (focusingItem.attr('class')) != 'undefined') {
				e.preventDefault();
				switch (e.keyCode) {
				// delete
				case 46:
					focusingItem.find('.btn-del').trigger('click');
					break;
				default:
					break;
				}

			}

		},
		/*
		 * key handle with ctrl
		 */
		/*
		 * key handle with ctrl
		 */
		keyListenerWithCtrl : function(e) {
			// console.log('action with key:'+e.keyCode);
			var focusingItem = $('.gird-stack-item-hover');
			if ([ 173, 61, 67, 86, 83 ].indexOf(e.keyCode) > -1) {
				// save
				if (e.keyCode == 83) {
					e.preventDefault();
					$('#menu-tools #save').trigger('click');
				} else if (typeof (focusingItem) != 'undefined' && focusingItem != null && typeof (focusingItem.attr('class')) != 'undefined') {
					e.preventDefault();
					var x = parseInt(focusingItem.attr('data-gs-x'));
					var y = parseInt(focusingItem.attr('data-gs-y'));
					var w = parseInt(focusingItem.attr('data-gs-width'));
					var h = parseInt(focusingItem.attr('data-gs-height'));
					switch (e.keyCode) {
					// zoom out
					case 61:
						var temp = parseInt(focusingItem.attr('data-gs-x')) + parseInt(focusingItem.attr('data-gs-width'));
						if (temp < gridConfig.width)
							focusingItem.parents('.grid-stack').data('gridstack').update(focusingItem, x, y, w + 1, h + 1);
						break;
					// zoom in
					case 173:
						var minSize = componentModelManager.getMinSize(focusingItem);
						if (w - 1 >= minSize.minW && h - 1 >= minSize.minH)
							focusingItem.parents('.grid-stack').data('gridstack').update(focusingItem, x, y, w - 1, h - 1);
						break;
					// c
					case 67:
						smallbox.CLIPBOARD_ID = focusingItem.attr('qb-component-id');
						break;
					// v
					case 86:
						if (smallbox.CLIPBOARD_ID != 0 && componentModelManager.listComponent[smallbox.CLIPBOARD_ID][WEB_PRO_STT] != WEB_PRO_STT_REMOVED)
							componentModelManager.duplicate(smallbox.CLIPBOARD_ID);
						break;
					default:
						break;
					}

				}
				// paste to another box
				else if (e.keyCode == 86) {
					if (smallbox.CLIPBOARD_ID != 0) {
						e.preventDefault();
						var selectedBoxId = 0;
						if ($('.box-selected').length > 0)
							selectedBoxId = $('.box-selected').attr('qb-box-id');
						// console.log('boxid:'+selectedBoxId);
						if (componentModelManager.listComponent[smallbox.CLIPBOARD_ID][WEB_PRO_STT] != WEB_PRO_STT_REMOVED)
							componentModelManager.duplicate(smallbox.CLIPBOARD_ID, selectedBoxId);
					}
				}
			}

		}
	}
}());