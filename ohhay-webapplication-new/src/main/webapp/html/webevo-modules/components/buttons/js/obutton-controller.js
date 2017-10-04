/**
 * @author: ThienND date created: 3/11/2015
 */
(function() {
	obutton = {
		COMP_MIN_H : 2,
		COMP_MIN_W : 2,
		init : function() {
			if(!obutton.inited){
				this.eventListener();
				obutton.inited = true;
			}
		},
		eventListener : function() {
			console.log("init dt");
			$(document).on('click', '.qb-component-button .btn-set-style', function() {
				var button = $(this).parents('.grid-stack-item-content').find('.qb-component');
				var buttonLabel = button.find('.obutton-label');
				var buttonID = $(this).parents('.grid-stack-item-content').attr('qb-component-id');
				var data = componentModelManager.getDataFromID(buttonID);
				cp_Option.open({
					self : button,
					callBack : function(result) {
						if (result.style === 'rotate') {
							webUtils.setTransformRotate(button, result.value);
							webUtils.setTransformRotate(buttonLabel, result.value1 ? -parseInt(result.value) : 0);
							// data['obuttonHover']['onmouseout']['this.style.transform']
							// = ['rotate('+result.value+'deg)'];
							// data['obuttonHover']['onmouseout']['this.style.msTransform']
							// = ['rotate('+result.value+'deg)'];
							// data['obuttonHover']['onmouseout']['this.style.WebkitTransform']
							// = ['rotate('+result.value+'deg)'];
							// data['obuttonTitleHover']['onmouseout']['this.style.transform']
							// = ['rotate('+(result.value1 ?
							// -parseInt(result.value) : 0)+'deg)'];
							// data['obuttonTitleHover']['onmouseout']['this.style.msTransform']
							// = ['rotate('+(result.value1 ?
							// -parseInt(result.value) : 0)+'deg)'];
							// data['obuttonTitleHover']['onmouseout']['this.style.WebkitTransform']
							// = ['rotate('+(result.value1 ?
							// -parseInt(result.value) : 0)+'deg)'];
						} else {
							button.css(result.style, result.value);
						}
						// webUtils.addAttributeEffect(button,
						// data.obuttonHover.onmouseout, 'onmouseout');
						// webUtils.addAttributeEffect(buttonLabel,
						// data.obuttonTitleHover.onmouseout, 'onmouseout');
						componentModelManager.updateItemField(buttonID, WEB_PRO_DATA, data);
						if (web.MOBILE_EDITOR == 'on') {
							var mData = componentModelManager.listComponent[buttonID][WEB_PRO_MDATA] ? componentModelManager.listComponent[buttonID][WEB_PRO_MDATA] : {};
							mData[WEB_PRO_CSS] = webUtils.getCssEffectV2(button);
							componentModelManager.updateItemField(buttonID, WEB_PRO_MDATA, mData);
						} else {
							componentModelManager.updateItemField(buttonID, WEB_PRO_CSS, button.attr('style'));
						}
					},
					del : [ '#cp-ot-border-inner' ]
				});
			});
			$(document).on('click', '.qb-component-button .btn-set-action', function() {
				var button = $(this).parents('.grid-stack-item-content.qb-component-button.obutton').find('a.obutton-link');
				var buttonID = $(this).parents('.grid-stack-item-content.qb-component-button.obutton').attr('qb-component-id');
				var data = componentModelManager.getDataFromID(buttonID);
				actionOption.open({
					target : button,
					callBack : function(result) {
						if (result.type == 'action') {
							data.actionType = result.dataType;
							data.actionLink = result.dataLink;
							data.actionLinkTarget = result.dataLinkTarget;
							data.dataDocId = result.dataDocId;
							button.attr('data-action-type', result.dataType);
							button.attr('data-action-link', result.dataLink);
							button.attr('data-doc-id', result.dataDocId);
							button.attr('data-action-link-target', result.dataLinkTarget);
						}
						componentModelManager.updateItemField(buttonID, WEB_PRO_DATA, data);
					},
				});
			});
			$(document).on('click', '.qb-component-button .btn-set-effect', function() {
				var button = $(this).parents('.grid-stack-item-content').find('.qb-component');
				var buttonLabel = button.find('span.obutton-label');
				var buttonID = $(this).parents('.grid-stack-item-content.qb-component-button.obutton').attr('qb-component-id');
				var data = componentModelManager.getDataFromID(buttonID);
				hoverOption.open({
					target : button,
					targetTitle : buttonLabel,
					callBack : function(result) {
						if (result.type === 'hover') {
							if (result.attr_label !== 'ho-effect') {
								if (result.attr_label === 'ho-transition' || result.attr_label === 'ho-background') {
									$.each(result.data2, function(k, v) {
										var pa=data['obuttonBGHover']['onmouseout']['background-attachment'];
										var pc=data['obuttonBGHover']['onmouseout']['background-clip'];
										var po=data['obuttonBGHover']['onmouseout']['background-origin'];
										var pr=data['obuttonBGHover']['onmouseout']['background-repeat'];
										var pp=data['obuttonBGHover']['onmouseout']['background-position'];
										var ps=data['obuttonBGHover']['onmouseout']['background-size'];
										data['obuttonBGHover']['onmouseover']['background-attachment'] = pa;
										data['obuttonBGHover']['onmouseover']['background-clip'] = pc;
										data['obuttonBGHover']['onmouseover']['background-origin'] = po;
										data['obuttonBGHover']['onmouseover']['background-repeat'] = pr;
										data['obuttonBGHover']['onmouseover']['background-position'] = pp;
										data['obuttonBGHover']['onmouseover']['background-size'] = ps;
										data['obuttonBGHover']['onmouseover'][k] = v;
										
									});
								} else if (result.attr_label === 'ho-title-transition' || result.attr_label === 'ho-title') {
									$.each(result.data, function(k, v) {
										data['obuttonTitleHover']['onmouseover'][k] = v;
									});
								}
							} else {
								data['obuttonAnimation'] = result.attr_val;
							}
							try{
//								button.attr('class', button.get(0).className.replace(/\hvr-\S+/g, result.attr_val));
								button.removeClass (function (index, css) {
								    return (css.match (/(^|\s)hvr-\S+/g) || []).join(' ');
								});
								button.addClass(result.attr_val);
							}catch(e){
								console.log(e);
							}
							console.log("go");
							button.attr(result.attr_label, result.attr_val);
						} else if (result.type === 'remove-hover') {
							data['obuttonBGHover']['onmouseover'] = {};
							button.attr('onmouseover', '');
						}
						componentModelManager.updateItemField(buttonID, WEB_PRO_DATA, data);
					},
				});
			});
			$(document).on('click', '.grid-stack-item-content .btn-tracking-switch', function() {
				var button = $(this).parents('.grid-stack-item-content.qb-component-button.obutton').find('a.obutton-link');
				var buttonID = $(this).parents('.grid-stack-item-content.qb-component-button.obutton').attr('qb-component-id');
				var name = componentModelManager.getName(buttonID);
				if (name && name != 'null') {
					var data = componentModelManager.getDataFromID(buttonID);
					if (data['actionTracking'] && data['actionTracking'] === '1') {
						data['actionTracking'] = '0';
						$(this).find('.title.tracking-title').html('Tracking: OFF');
					} else {
						data['actionTracking'] = '1';
						$(this).find('.title.tracking-title').html('Tracking: ON');
					}
					button.attr('data-action-tracking', data['actionTracking']);
					componentModelManager.updateItemField(buttonID, WEB_PRO_DATA, data);
				} else{
					showGrowlMessage(getLocalize("webho_error1"));
					$(this).closest('.dropdown-menu').find('.btn-set-anchor').trigger('click');
				}
			});
			$(document).on('mouseover', '.qb-component-button .qb-component', function() {
				var layerBG = $(this).find('.layer-background');
				var buttonID = $(this).parents('.grid-stack-item-content.qb-component-button.obutton').attr('qb-component-id');
				var data = componentModelManager.getDataFromID(buttonID);
				if (data['obuttonBGHover']) {
					if (!$.isEmptyObject(data['obuttonBGHover']['onmouseover'])) {
						layerBG.attr('style','');
						layerBG.css(data['obuttonBGHover']['onmouseover']);
					}
				}
			});
			$(document).on('mouseout', '.qb-component-button .qb-component', function() {
				var layerBG = $(this).find('.layer-background');
				var buttonID = $(this).parents('.grid-stack-item-content.qb-component-button.obutton').attr('qb-component-id');
				var data = componentModelManager.getDataFromID(buttonID);
				if (data['obuttonBGHover']) {
					if (!$.isEmptyObject(data['obuttonBGHover']['onmouseout'])) {
						layerBG.attr('style','');
						layerBG.css(data['obuttonBGHover']['onmouseout']);
					}
				}
			});

			/*
			 * change font scale for button
			 */
			$(document).on('click', '.qb-component-button .btn-mb-font-style', function() {
				var self = $(this);
				var compId = $(this).closest('.grid-stack-item-content').attr('qb-component-id');
				var value = 0;
				// load scale size data
				if (componentModelManager.listComponent[compId][WEB_PRO_MDATA] && componentModelManager.listComponent[compId][WEB_PRO_MDATA][WEB_PRO_DATA])
					value = componentModelManager.listComponent[compId][WEB_PRO_MDATA][WEB_PRO_DATA][WEB_PRO_FONTSCALE];
				mobileEditor.open({
					value : value,
					callBack : function(result) {
						console.log(result);
						$(self.parents(".qb-component-button").find(".obutton-link *").get().reverse()).each(function() {
							// store PC font size
							if (!$(this).attr('cr-fontsize')) {
								var crFontSize = $(this).css('font-size');
								$(this).attr('cr-fontsize', parseInt(crFontSize.substring(0, crFontSize.indexOf('px'))));
							}
							// calculate scale font size
							var fontSizeNumber = parseInt($(this).attr('cr-fontsize')) + parseInt(result);
							console.log(fontSizeNumber + 'px');
							$(this).css('font-size', fontSizeNumber + 'px');
							// update model
							var mData = componentModelManager.listComponent[compId][WEB_PRO_MDATA] ? componentModelManager.listComponent[compId][WEB_PRO_MDATA] : {};
							mData[WEB_PRO_DATA] = {};
							mData[WEB_PRO_DATA][WEB_PRO_FONTSCALE] = parseInt(result);
							console.log("FONTSCALE : " + mData[WEB_PRO_DATA][WEB_PRO_FONTSCALE]);
							componentModelManager.updateItemField(compId, WEB_PRO_MDATA, mData);
						});
					}
				});
			});
		},
		add : function(grid, x, y, $self) {
			var node = {
				x : x,
				y : y,
				width : 6,
				height : 6
			};
			var tempClass = $self.find('[type]').attr('type');
			var width, height;
			var title = '';
			var tempStyle = '';
			var tempBG = '';
			var tempEffect = {};
			if (tempClass == 'normal-button-1') {
				width = 6;
				height = 4;
				title = 'Button';
				tempStyle = 'text-align: center; width: calc(100% - 10px); height: calc(100% - 10px); margin: 5px;';
				tempBG = 'background-color: orange;';
			} else if (tempClass == 'normal-button-2') {
				width = 6;
				height = 4;
				title = 'Click me';
				tempStyle = 'text-align: center; width: calc(100% - 10px); height: calc(100% - 10px); border-radius: 30px; margin: 5px;';
				tempBG = 'background-color: orange;';
			} else if (tempClass == 'normal-button-3') {
				width = 4;
				height = 6;
				title = 'Ok';
				tempStyle = 'text-align: center; width: calc(100% - 10px); height: calc(100% - 10px); border-radius: 45px; margin: 5px;';
				tempBG = 'background-color: orange;';
			}
			var data = {
				actionType : 'none',
				actionLink : '',
				actionLinkTarget : '_self',
				actionTracking : '0',
				obuttonAnimation : '',
				obuttonBGHover : {
					onmouseover : {},
					onmouseout : {
						'background-color' : 'orange'
					}
				},
				obuttonTitle : title,
				obuttonTitleHover : {
					onmouseover : {},
					onmouseout : {},
				},
			};
			var widget = grid.add_widget(buildTemplateOButton(data, tempStyle, tempBG), 0, 0, width, height, true);
			widget[WEB_PRO_DATA] = data;
			widget[WEB_PRO_CSS] = tempStyle;
			widget[WEB_PRO_BGCSS] = tempBG;
			//update 17/11/2016 - call to action
			//widget.find('.btn-set-style').trigger('click');
			return widget;
		},
		load : function(grid, node, data) {
			// data = obutton.convertBGHoverDataV1(data);
			return grid.add_widget(buildTemplateOButton(data, ''), node.x, node.y, node.width, node.height);
		},
		getData : function(id) {
			var data = componentModelManager.getDataFromID(id);
			data.obuttonTitle = $('.grid-stack-item-content[qb-component-id="' + id + '"] .qb-component .obutton-link .obutton-label').html();
			data.obuttonTitleStyle = $('.grid-stack-item-content[qb-component-id="' + id + '"] .qb-component .obutton-link .obutton-label').attr('style');
			return data;
		},
		goToEditMode : function() {
			$('#content-wrapper .content-box:not(".content-box-from-lib")').find('.obutton-label').each(function() {
				$(this).attr('contenteditable', 'true');
			});
		},
		goToPreviewMode : function() {
			$('#content-wrapper .content-box:not(".content-box-from-lib")').find('.obutton-label').each(function() {
				$(this).removeAttr('contenteditable');
			});
		},
		convertBGHoverDataV1 : function(data) {
			if (data['obuttonHover']) {
				if (!data['obuttonBGHover']) {
					data['obuttonBGHover'] = {
						onmouseout : '',
						onmouseover : ''
					};
				}
				delete data['obuttonHover'];
			}
			return data;
		},
	}
}());