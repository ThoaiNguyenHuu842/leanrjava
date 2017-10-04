/**
 * author: ThoaiNH date create: 05/11/2015
 */
(function() {
	componentModelManager = {
		listComponent : {},
		crId : 0, // current component id (c + number id)
		length : function() {
			var l = 0;
			for ( var id in this.listComponent)
				l++;
			return l;
		},
		/*
		 * get new component id
		 */
		getNewId : function() {
			this.crId++;
			return "c" + this.crId;
		},
		/*
		 * add component data to model
		 */
		addItemToModel : function(componentData) {
			this.listComponent[componentData.id] = componentData;
			componentModelManager.listComponent[componentData.id][WEB_PRO_STT] = WEB_PRO_STT_NEW;
			console.log(this.listComponent);
		},
		/*
		 * update item field name
		 */
		updateItemField : function(id, fieldName, value) {
			console.log("updateItemField:" + id)
			console.log(fieldName);
			console.log(value);
			try {
				// append
				// componentModelManager.listComponent[id][fieldName] =
				// 'css'.indexOf(fieldName.toLowerCase()) > -1 ?
				// componentModelManager.listComponent[id][fieldName] + value :
				// value;

				// replace
				componentModelManager.listComponent[id][fieldName] = value;

				if (componentModelManager.listComponent[id][WEB_PRO_STT] == WEB_PRO_STT_NO_CHANGE)
					componentModelManager.listComponent[id][WEB_PRO_STT] = WEB_PRO_STT_UPDATE;
			} catch (e) {
				console.log(e);
			}

		},
		/*
		 * remove component data from model
		 */
		removeItemFromModel : function(id) {
			if (componentModelManager.listComponent[id][WEB_PRO_STT] == WEB_PRO_STT_NEW)
				delete componentModelManager.listComponent[id];
			else
				componentModelManager.listComponent[id][WEB_PRO_STT] = WEB_PRO_STT_REMOVED;
		},
		/*
		 * remove component by box id and box stt
		 */
		removeItemByBoxId : function(boxId, boxStt) {
			for ( var id in this.listComponent) {
				var component = componentModelManager.listComponent[id];
				if (component.boxId == boxId) {
					if (boxStt == WEB_PRO_STT_NEW)
						delete componentModelManager.listComponent[id];
					else {
						if (component.stt == WEB_PRO_STT_NEW)
							delete componentModelManager.listComponent[id];
						else
							componentModelManager.listComponent[id][WEB_PRO_STT] = WEB_PRO_STT_REMOVED;
					}
				}
			}
		},
		/*
		 * re-set data from html for text component
		 */
		syncHtmlData : function() {
			for ( var id in this.listComponent) {
				var component = componentModelManager.listComponent[id];
				var data = '';
				if (component[WEB_PRO_TYPE] == WEB_COMP_TYPE_TEXT && component[WEB_PRO_STT] != WEB_PRO_STT_NO_CHANGE) {
					try {
						data = otext.getData(component.id);
						if (component[WEB_PRO_STT] == WEB_PRO_STT_NO_CHANGE)
							component[WEB_PRO_STT] = WEB_PRO_STT_UPDATE;
						if (component[WEB_PRO_DATA]['writingMode'])
							data['writingMode'] = component[WEB_PRO_DATA]['writingMode'];
						component[WEB_PRO_DATA] = data;
					} catch (e) {
						// TODO: handle exception
						console.log(e);
					}
				} else if (component[WEB_PRO_TYPE] == WEB_COMP_TYPE_BTN && component[WEB_PRO_STT] != WEB_PRO_STT_NO_CHANGE) {
					try {
						data = obutton.getData(component.id);
						if (component[WEB_PRO_STT] == WEB_PRO_STT_NO_CHANGE)
							component[WEB_PRO_STT] = WEB_PRO_STT_UPDATE;
						component[WEB_PRO_DATA] = data;
					} catch (e) {
						// TODO: handle exception
						console.log(e);
					}
				} else if (component[WEB_PRO_TYPE] == WEB_COMP_TYPE_MENU) {
					try {
						data = omenu.getData(component.id);
						if (component[WEB_PRO_STT] == WEB_PRO_STT_NO_CHANGE)
							component[WEB_PRO_STT] = WEB_PRO_STT_UPDATE;
						component[WEB_PRO_DATA] = data;
					} catch (e) {
						// TODO: handle exception
						console.log(e);
					}
				}
			}
		},
		/*
		 * update raw box id to db box id
		 */
		syncBoxId : function(rawId, newId) {
			for ( var id in this.listComponent) {
				var compData = componentModelManager.listComponent[id];
				if (compData[WEB_PRO_BOXID] == rawId)
					compData[WEB_PRO_BOXID] = newId;
			}
		},
		/*
		 * reset id and stt for component when save complete
		 */
		syncServerData : function(returnData) {
			// remove un save data
			for ( var id in this.listComponent) {
				// remove component deleted in model
				if (componentModelManager.listComponent[id][WEB_PRO_STT] == WEB_PRO_STT_REMOVED)
					delete componentModelManager.listComponent[id];
				else {
					// reload boxId of new component
					if (componentModelManager.listComponent[id][WEB_PRO_STT] == WEB_PRO_STT_NEW) {
						for ( var id2 in returnData) {
							if (id2 == componentModelManager.listComponent[id][WEB_PRO_BOXID]) {
								componentModelManager.listComponent[id][WEB_PRO_BOXID] = returnData[id2];
								break;
							}
						}
					}
				}
			}
			// reload save data
			for ( var id in returnData) {
				try {
					console.log(id + ":" + returnData[id])
					var newId = returnData[id];
					$(".grid-stack-item-content[qb-component-id='" + id + "']").attr("qb-component-id", newId);
					var compData = componentModelManager.listComponent[id];
					compData[WEB_PRO_ID] = newId;
					delete componentModelManager.listComponent[id];
					compData[WEB_PRO_STT] = WEB_PRO_STT_NO_CHANGE;
					componentModelManager.listComponent[newId] = compData;
				} catch (e) {
					// TODO: handle exception
					console.log(e);
				}
			}
		},
		loadComponent : function(component) {
			// get component data
			try {
				var data = JSON.parse(component[WEB_PRO_DATA]);
				component[WEB_PRO_DATA] = data;
			} catch (e) {
				data = component[WEB_PRO_DATA];
				console.log(e);
			}
			// declare node
			var node = {
				x : component[WEB_PRO_X],
				y : component[WEB_PRO_Y],
				width : component[WEB_PRO_W],
				height : component[WEB_PRO_H]
			};
			if (web.MOBILE_EDITOR == 'on') {
				if (component[WEB_PRO_MDATA] && component[WEB_PRO_MDATA][WEB_PRO_X] >= 0 && component[WEB_PRO_MDATA][WEB_PRO_W] > 0 && component[WEB_PRO_MDATA][WEB_PRO_H] > 0)
					node = {
						x : component[WEB_PRO_MDATA][WEB_PRO_X],
						y : component[WEB_PRO_MDATA][WEB_PRO_Y],
						width : component[WEB_PRO_MDATA][WEB_PRO_W],
						height : component[WEB_PRO_MDATA][WEB_PRO_H]
					};
				else
					node = {
						x : 0,
						y : component[WEB_PRO_Y],
						width : gridConfig.width,
						height : component[WEB_PRO_H]
					};
			}
			// find grid from boxId
			var grid = $(".content-box[qb-box-id='" + component[WEB_PRO_BOXID] + "'] .grid-stack[index='"+component[WEB_PRO_GRID_INDEX]+"'").data('gridstack');
			switch (component[WEB_PRO_TYPE]) {
			case WEB_COMP_TYPE_TEXT:
				widget = otext.load(grid, node, data);
				// scale mobile font size
				if (web.MOBILE_EDITOR == 'on' && component[WEB_PRO_MDATA] && component[WEB_PRO_MDATA][WEB_PRO_DATA]) {
					var data = '';
					try {
						data = JSON.parse(component[WEB_PRO_MDATA][WEB_PRO_DATA]);
					} catch (e) {
						data = component[WEB_PRO_MDATA][WEB_PRO_DATA];
					}
					component[WEB_PRO_MDATA][WEB_PRO_DATA] = data;
					if (data[WEB_PRO_FONTSCALE]) {
						$(widget.find(".otext-wrapper-content *").get().reverse()).each(function() {
							util.scaleFontSize($(this), parseInt(data[WEB_PRO_FONTSCALE]));
							$(this).css('line-height', '23px');
						});
					}
					if (data["line-height"]) {
						$(widget.find(".otext-wrapper-content *").get().reverse()).each(function() {
							$(this).css('line-height', data["line-height"] + 'px');
						});
					}
				}
				break;
			case WEB_COMP_TYPE_IMG:
				widget = imageController.load(grid, node, data);
				break;
			case WEB_COMP_TYPE_IFRAME:
				widget = iframe.load(grid, node, data);
				break;
			case WEB_COMP_TYPE_BTN:
				if (data['obuttonStyle']) {
					componentModelManager.updateItemField(component[WEB_PRO_ID], WEB_PRO_CSS, data['obuttonStyle']);
					delete data['obuttonStyle'];
				}
				widget = obutton.load(grid, node, data);
				break;
			case WEB_COMP_TYPE_GMAP:
				widget = gmapController.load(grid, node, data, component[WEB_PRO_ID]);
				break;
			case WEB_COMP_TYPE_MENU:
				widget = omenu.load(grid, node, data);
				break;
			case WEB_COMP_TYPE_CONTACT_FORM:
				widget = contactForm.load(grid, node, data);
				break;
			case WEB_COMP_TYPE_EMBED_CODE:
				widget = embedCodeController.load(grid, node, data, component[WEB_PRO_ID]);
				break;
			case WEB_COMP_TYPE_YOUTUBE_PLAYLIST:
				widget = youtubePlaylist.load(grid, node, data, component[WEB_PRO_ID]);
				break;
			default:
				break;
			}
			// set component id attribute for widget
			widget.attr('qb-component-id', component[WEB_PRO_ID]);
			// set CSS for widget change <qb-component> to <layer-background>
			if (web.MOBILE_EDITOR == 'on')
			{
				if(component[WEB_PRO_MDATA] && component[WEB_PRO_MDATA][WEB_PRO_CSS] && component[WEB_PRO_MDATA][WEB_PRO_CSS] != null && component[WEB_PRO_MDATA][WEB_PRO_CSS].trim().length > 0)
					widget.find(".qb-component").attr('style', component[WEB_PRO_MDATA][WEB_PRO_CSS]);
				if(component[WEB_PRO_BGCSS] && component[WEB_PRO_BGCSS].length > 0)
					widget.find(".layer-background").attr('style', component[WEB_PRO_BGCSS]);
			}
			else if ((typeof component[WEB_PRO_CSS] != 'undefined' && component[WEB_PRO_CSS]) || (typeof component[WEB_PRO_BGCSS] != 'undefined' && component[WEB_PRO_BGCSS])) {
				// ANPH: tách os vs bg từ WEB_PRO_CSS
				var objCss = imageUtil.filterBackgroundStyle(component[WEB_PRO_CSS]);
				// version mới hoặc version cũ có data kiểu mới (css và bgCss
				// riêng) và BGCSS k có dữ liệu
				if (webCreater.data['bonevoVersion'] != '1.0.0' || $.isEmptyObject(objCss.bg) || (typeof component[WEB_PRO_BGCSS] != 'undefined' && component[WEB_PRO_BGCSS])) {
					// chỉ giữ lại phần os k cần bg
					widget.find(".qb-component").css(objCss.os);
					//update lại luôn
					this.updateItemField(component[WEB_PRO_ID], WEB_PRO_CSS, widget.find(".qb-component").attr('style'));
					widget.find(".layer-background").attr('style', component[WEB_PRO_BGCSS]);
				} else {// cai nay da cu
					widget.find(".qb-component").css(objCss.os);
					widget.find(".layer-background").css(objCss.bg);
					if (typeof objCss.bg != 'undefined' && !$.isEmptyObject(objCss.bg)) {
						// update data? -> upgrade version
						this.updateItemField(component[WEB_PRO_ID], WEB_PRO_CSS, widget.find(".qb-component").attr('style'));
						this.updateItemField(component[WEB_PRO_ID], WEB_PRO_BGCSS, widget.find(".layer-background").attr('style'));
					}
				}
			}
			// set background video
			/*
			 * if(component[WEB_PRO_BG_VID]) webUtils.setBackgroundVideo(widget,
			 * component[WEB_PRO_BG_VID]);
			 */
			// set id(anchor name) for widget
			if (component[WEB_PRO_NAME] && component[WEB_PRO_NAME] != 'null') {
				widget.attr('id', WEB_PREFIX_COMP + component[WEB_PRO_NAME]);
				widget.find('li.btn-set-anchor label').html(component[WEB_PRO_NAME]);
			} else {
				widget.find('li.btn-set-anchor label').html(component[WEB_PRO_ID]);
			}
			// setup onload effect
			if (component[WEB_PRO_ONLOAD_EFFECT] && component[WEB_PRO_ONLOAD_EFFECT].trim().length > 0) {
				widget.find('.qb-component').attr("onload-effect", component[WEB_PRO_ONLOAD_EFFECT]);
				widget.find('.qb-component').addClass('animated');
				widget.find('.qb-component').addClass(component[WEB_PRO_ONLOAD_EFFECT]);
			}
			if (component[WEB_PRO_DATA]['writingMode'] && component[WEB_PRO_DATA]['writingMode'] === 'vertical') {
				widget.find('.qb-component').addClass('vertical-rl');
				widget.find('.btn-set-writing-mode span.title.writing-mode').text('Writing mode: Vertical');
			}
			// add widget element id
			if (component[WEB_PRO_WIGET_ELEMENT_ID] && component[WEB_PRO_WIGET_ELEMENT_ID].trim().length > 0 && component[WEB_PRO_WIGET_ELEMENT_ID] != 'null')
				widget.attr(WEB_PRO_WIGET_ELEMENT_ID, component[WEB_PRO_WIGET_ELEMENT_ID]);
			return widget;
		},
		/*
		 * update model to html
		 */
		update : function() {
			for ( var id in componentModelManager.listComponent) {
				try {
					var widget;
					var component = componentModelManager.listComponent[id];
					//dont load components hided in mobile editor
					if (web.MOBILE_EDITOR != 'on' || (!component[WEB_PRO_MDATA] || component[WEB_PRO_MDATA][WEB_PRO_HIDE] != true))
						componentModelManager.loadComponent(component);
				} catch (e) {
					console.log(e);
				}
			}
		},
		/*
		 * get data object from component id
		 */
		getDataFromID : function(componentID) {
			if (componentID && componentModelManager.listComponent[componentID]) {
				var data = componentModelManager.listComponent[componentID][WEB_PRO_DATA];
				if (data && data.length)
					data = JSON.parse(data);
				return data;
			}
			return {};
		},
		getInfoFromID: function(componentID, fieldName){
			if (componentID && componentModelManager.listComponent[componentID]) {
				var data = componentModelManager.listComponent[componentID][fieldName];
				if (data && data.length)
					data = JSON.parse(data);
				return data;
			}
			return {};
		},
		setName : function(componentID, name) {
			if (this.listComponent[componentID][WEB_PRO_STT] == WEB_PRO_STT_NO_CHANGE)
				this.listComponent[componentID][WEB_PRO_STT] = WEB_PRO_STT_UPDATE;
			this.listComponent[componentID][WEB_PRO_NAME] = name.length > 0 ? name : "";
		},
		getName : function(componentID) {
			return this.listComponent[componentID][WEB_PRO_NAME];
		},
		getListName : function(componentID) {
			alert(this.listComponent[componentID][WEB_PRO_NAME]);
			var nameArray = [];
			for ( var tempComponentID in this.listComponent) {
				if (componentID == tempComponentID) {
					continue;
				}
				if (this.getName(tempComponentID) != undefined) {
					nameArray.push(this.getName(tempComponentID));
				}
			}
			return nameArray.sort();
		},
		/*
		 * duplicate box @param: id of component
		 */
		duplicate : function(componentID, selectedBoxId) {
			// prevent action in mobile editor and section in library follow
			// only
			if (selectedBoxId && bigBoxModelManager.listBox[selectedBoxId][WEB_PRO_LIBTYPE] == 3)
				return;
			else if (bigBoxModelManager.listBox[componentModelManager.listComponent[componentID][WEB_PRO_BOXID]][WEB_PRO_LIBTYPE] == 3)
				return;
			else if (web.MOBILE_EDITOR == 'off') {
				console.log('selectedBoxId:' + selectedBoxId)
				componentModelManager.syncHtmlData();
				var component = jQuery.extend(true, {}, componentModelManager.listComponent[componentID]);
				console.log(component[WEB_PRO_CSS]);
				component[WEB_PRO_Y] = component[WEB_PRO_Y] + component[WEB_PRO_H];
				component[WEB_PRO_ID] = this.getNewId();
				// duplicate to another box 12/23/2015
				if (selectedBoxId && selectedBoxId != 0 && selectedBoxId != component[WEB_PRO_BOXID]) {
					console.log('selectedBoxId:' + selectedBoxId)
					component[WEB_PRO_BOXID] = selectedBoxId;
					component[WEB_PRO_X] = -1;
					component[WEB_PRO_Y] = -1;
				}
				// remove anchor name
				delete component[WEB_PRO_NAME];
				componentModelManager.addItemToModel(component);
				componentModelManager.loadComponent(component).goTo();
			}
		},
		/*
		 * get component min size
		 */
		getMinSize : function(gridItem) {
			var minH, minW;
			if (gridItem.hasClass('qb-component-text')) {
				minH = otext.COMP_MIN_H;
				minW = otext.COMP_MIN_W;
			} else if (gridItem.hasClass('qb-component-contact-form')) {
				minH = contactForm.COMP_MIN_H;
				minW = contactForm.COMP_MIN_W;
			} else if (gridItem.hasClass('qb-component-iframe')) {
				minH = iframe.COMP_MIN_H;
				minW = iframe.COMP_MIN_W;
			} else if (gridItem.hasClass('comp-image')) {
				minH = imageController.COMP_MUL_MIN_H;
				minW = imageController.COMP_MUL_MIN_W;
				if (gridItem.data('mode') == 'ONE' || gridItem.data('mode') == 'GALLERY') {
					minH = imageController.COMP_MIN_H;
					minW = imageController.COMP_MIN_W;
				}
			} else if (gridItem.hasClass('qb-component-gmap')) {
				minH = gmapController.COMP_MIN_H;
				minW = gmapController.COMP_MIN_W;
			} else if (gridItem.hasClass('qb-component-menu')) {
				minH = omenu.COMP_MIN_H;
				minW = omenu.COMP_MIN_W;
			} else if (gridItem.hasClass('qb-component-button')) {
				minH = obutton.COMP_MIN_H;
				minW = obutton.COMP_MIN_W;
			} else if (gridItem.hasClass('qb-component-iframe-embed')) {
				minH = embedCodeController.COMP_MIN_H;
				minW = embedCodeController.COMP_MIN_W;
			} else if (gridItem.hasClass('qb-component-youtube-playlist')) {
				minH = youtubePlaylist.COMP_MIN_H;
				minW = youtubePlaylist.COMP_MIN_W;
			}
			if (web.MOBILE_EDITOR == 'on') {
				minW = minW * 2;
			}
			return {
				minH : minH,
				minW : minW
			};
		},
		/*
		 * get max id of list component
		 */
		getMaxId : function() {
			var maxId = 0;
			for ( var id in this.listComponent) {
				var comp = this.listComponent[id][WEB_PRO_ID];
				var realId = parseInt(comp.slice(1, comp.lenght));
				if (realId > maxId)
					maxId = realId;
			}
			console.log("maxId:" + maxId);
			return maxId;
		},
		/*
		 * 
		 */
		getTotalComponent : function(type) {
			var count = 0;
			for ( var id in this.listComponent) {
				var comp = this.listComponent[id];
				console.log(comp[WEB_PRO_TYPE]);
				if (comp[WEB_PRO_TYPE] == type && comp[WEB_PRO_STT] != WEB_PRO_STT_REMOVED)
					count++;
			}
			return count;
		}
	}
}());