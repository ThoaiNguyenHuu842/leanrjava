/*
 * author: ThoaiNH
 * date create: 03/11/2015
 */
(function() {
	bigBoxModelManager = {
		listBox : {},
		crId : 0, // current box id (b + number id)
		crIndex : 1,
		length : function() {
			var l = 0;
			for ( var id in this.listBox)
				l++;
			return l;
		},
		/*
		 * get total number box is show on web
		 */
		totalVisibleBox : function() {
			var l = 0;
			for ( var id in this.listBox) {
				console.log('---box STT:' + bigBoxModelManager.listBox[id][WEB_PRO_STT]);
				if (bigBoxModelManager.listBox[id][WEB_PRO_STT] != WEB_PRO_STT_REMOVED)
					l++;
			}
			return l;
		},
		/*
		 * get new box id
		 */
		getNewId : function() {
			this.crId++;
			return "b" + this.crId;
		},
		/*
		 * add box data to model
		 */
		addItemToModel : function(boxData) {
			this.listBox[boxData.id] = boxData;
			bigBoxModelManager.listBox[boxData.id][WEB_PRO_STT] = WEB_PRO_STT_NEW;
			// render web tools
			webMenuLeft.render();
		},
		/*
		 * update item field name
		 */
		updateItemField : function(id, fieldName, value) {
			console.log("--updateItemField:" + id + "," + fieldName + "," + value);
			// update field of mData (mobile edit data)
			if (web.MOBILE_EDITOR == 'on' && fieldName != WEB_PRO_MDATA) {
				var mData = this.listBox[id][WEB_PRO_MDATA] ? this.listBox[id][WEB_PRO_MDATA] : {};
				mData[fieldName] = value;
				bigBoxModelManager.listBox[id][WEB_PRO_MDATA] = mData;
			} else
				bigBoxModelManager.listBox[id][fieldName] = value;
			// set status
			if (bigBoxModelManager.listBox[id][WEB_PRO_STT] == WEB_PRO_STT_NO_CHANGE)
				bigBoxModelManager.listBox[id][WEB_PRO_STT] = WEB_PRO_STT_UPDATE;
		},
		/*
		 * remove box data from model
		 */
		removeItemFromModel : function(id) {
			if (bigBoxModelManager.listBox[id][WEB_PRO_STT] == WEB_PRO_STT_NEW)
				delete bigBoxModelManager.listBox[id];
			else
				bigBoxModelManager.listBox[id][WEB_PRO_STT] = WEB_PRO_STT_REMOVED;
			componentModelManager.removeItemByBoxId(id, bigBoxModelManager.listBox[id][WEB_PRO_STT]);
		},
		/*
		 * reset id and stt for box when save complete
		 */
		syncServerData : function(returnData) {
			// remove un save data
			for ( var id in this.listBox) {
				if (bigBoxModelManager.listBox[id][WEB_PRO_STT] == WEB_PRO_STT_REMOVED)
					delete bigBoxModelManager.listBox[id];
				else
					bigBoxModelManager.listBox[id][WEB_PRO_STT] = WEB_PRO_STT_NO_CHANGE;
			}
			// reload save data
			for ( var id in returnData) {
				try {
					console.log(id + ":" + returnData[id])
					var newId = returnData[id];
					$(".content-box[qb-box-id='" + id + "']").attr("qb-box-id", newId);
					$(".content-box[qb-box-id='" + newId + "']").attr("id", "qb-box-" + newId);
					var boxData = bigBoxModelManager.listBox[id];
					boxData[WEB_PRO_ID] = newId;
					delete bigBoxModelManager.listBox[id];
					bigBoxModelManager.listBox[newId] = boxData;
				} catch (e) {
					// TODO: handle exception
					console.log(e);
				}
			}
		},
		/*
		 * get content height of box
		 */
		getContentHeight : function(boxId) {
			var maxH = 0;
			if (web.MOBILE_EDITOR == 'on') {
				for ( var id in componentModelManager.listComponent) {
					var component = componentModelManager.listComponent[id];
					if (component[WEB_PRO_STT] != WEB_PRO_STT_REMOVED && component[WEB_PRO_BOXID] == boxId) {
						console.log(component[WEB_PRO_MDATA]);
						if (component[WEB_PRO_MDATA]) {
							if ((!component[WEB_PRO_MDATA][WEB_PRO_HIDE] || component[WEB_PRO_MDATA][WEB_PRO_HIDE] != true) && (component[WEB_PRO_MDATA][WEB_PRO_Y] + component[WEB_PRO_MDATA][WEB_PRO_H]) > maxH) {
								maxH = component[WEB_PRO_MDATA][WEB_PRO_Y] + component[WEB_PRO_MDATA][WEB_PRO_H];
								console.log(component[WEB_PRO_BOXID] + ',' + component[WEB_PRO_ID] + ',' + maxH);
							}
						} else {
							if ((component[WEB_PRO_Y] + component[WEB_PRO_H]) > maxH) {
								maxH = component[WEB_PRO_Y] + component[WEB_PRO_H];
								console.log(component[WEB_PRO_BOXID] + ',' + component[WEB_PRO_ID] + ',' + maxH);
							}
						}
					}
				}
				return gridConfig.cell_height * maxH;
			} else {
				for ( var id in componentModelManager.listComponent) {
					var component = componentModelManager.listComponent[id];
					if (component[WEB_PRO_STT] != WEB_PRO_STT_REMOVED && component[WEB_PRO_BOXID] == boxId && (component[WEB_PRO_Y] + component[WEB_PRO_H]) > maxH) {
						maxH = component[WEB_PRO_Y] + component[WEB_PRO_H];
					}
				}
			}
			console.log(maxH);
			return maxH * gridConfig.cell_height;
		},
		setName : function(boxID, name) {
			if (this.listBox[boxID][WEB_PRO_STT] == WEB_PRO_STT_NO_CHANGE)
				this.listBox[boxID][WEB_PRO_STT] = WEB_PRO_STT_UPDATE;
			this.listBox[boxID][WEB_PRO_NAME] = name.length > 0 ? name : "";

		},
		getName : function(boxID) {
			return this.listBox[boxID][WEB_PRO_NAME];
		},
		getListName : function(boxID) {
			var nameArray = [];
			for ( var tempBoxID in this.listBox) {
				if (boxID == tempBoxID) {
					continue;
				}
				if (this.getName(tempBoxID) != undefined) {
					nameArray.push(this.getName(tempBoxID));
				}
			}
			return nameArray.sort();
		},
		/*
		 * duplicate box
		 */
		duplicate : function(boxId) {
			/*
			 * copy box model
			 */
			componentModelManager.syncHtmlData();
			var box = jQuery.extend(true, {}, bigBoxModelManager.listBox[boxId]);
			// remove anchor name
			delete box[WEB_PRO_NAME];
			box[WEB_PRO_ID] = this.getNewId();
			box[WEB_PRO_INDEX] = ++bigBoxModelManager.crIndex;
			bigBoxModelManager.addItemToModel(box);
			bigbox.add(box);
			console.log(box);
			/*
			 * copy components
			 */
			for ( var id in componentModelManager.listComponent) {
				var component = componentModelManager.listComponent[id];
				if (component.boxId == boxId && component[WEB_PRO_STT] != WEB_PRO_STT_REMOVED) {
					var newComponent = jQuery.extend(true, {}, component);
					newComponent[WEB_PRO_BOXID] = box[WEB_PRO_ID];
					// create new id for component
					newComponent[WEB_PRO_ID] = componentModelManager.getNewId();
					componentModelManager.addItemToModel(newComponent);
					componentModelManager.loadComponent(newComponent);
				}
			}
		},
		/*
		 * update box height
		 */
		updateBoxHeight : function(id, h) {
			if (web.MOBILE_EDITOR == 'on') {
				var mData = this.listBox[id][WEB_PRO_MDATA] ? this.listBox[id][WEB_PRO_MDATA] : {};
				mData[WEB_PRO_H] = h;
				bigBoxModelManager.updateItemField(id, WEB_PRO_MDATA, mData);
			} else
				bigBoxModelManager.updateItemField(id, WEB_PRO_H, h);
		},
		/*
		 * get box height
		 */
		getBoxHeight : function(id) {
			if (web.MOBILE_EDITOR == 'on' && bigBoxModelManager.listBox[id][WEB_PRO_MDATA])
				return bigBoxModelManager.listBox[id][WEB_PRO_MDATA][WEB_PRO_H];
			return bigBoxModelManager.listBox[id][WEB_PRO_H];
		},
		/*
		 * get max id of list box
		 */
		getMaxId : function() {
			var maxId = 0;
			for ( var id in this.listBox) {
				var box = this.listBox[id][WEB_PRO_ID];
				var realId = parseInt(box.slice(1, box.lenght));
				if (realId > maxId)
					maxId = realId;
			}
			console.log("maxId:" + maxId);
			return maxId;
		},
		/*
		 * updateListStatus lib status
		 */
		updateLibStatus : function(id, libType) {
			this.listBox[id][WEB_PRO_LIBTYPE] = libType;
			var crBox = $('.content-box[qb-box-id="' + id + '"]');
			if (libType == 1) {
				crBox.find('.btn-add-to-lib').html('<a><i class="fa fa-folder-o"></i>Private in library</a>');
				crBox.find('.btn-add-to-lib').removeClass('btn-add-to-lib');
			} else if (libType == 2) {
				crBox.find('.btn-add-to-lib').html('<a><i class="fa fa-folder-o"></i>Public in library</a>');
				crBox.find('.btn-add-to-lib').removeClass('btn-add-to-lib');
			} else {
				crBox.find('.btn-lib').addClass('btn-add-to-lib');
				crBox.find('.btn-lib').html('<a><i class="fa fa-folder-o"></i>Share</a>');
			}
		},
		/*
		 * disable editor of this section
		 */
		disableEdit : function(id) {
			var box = $('.content-box[qb-box-id="' + id + '"]');
			box.removeClass("grid-style-onedit");
			box.find("[contenteditable=true]").attr("contenteditable", "false");
			box.find(".grid-stack-item-content").addClass('disabled');
			try {
				box.find('.grid-stack').data('gridstack').disable();
			} catch (e) {
				console.log(e);
			}
			box.find('.grid-stack').droppable({
				disabled : true
			});
		}
	}
}());