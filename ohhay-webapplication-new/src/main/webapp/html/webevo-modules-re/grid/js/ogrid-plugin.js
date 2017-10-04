/**
 * @author ThoaiNH
 * create Oct 10, 2015
 * setup grid for box
 */
$.fn.initOGrid = function(){
		var boxId = $(this).closest(".content-box").attr("qb-box-id");
		console.log('--init grid:'+ boxId);
		//grid
		gridConfig.width = 48/bigBoxModelManager.listBox[boxId]["reType"];
		$(this).gridstack(gridConfig);
		$(this).height(240);
		$(this).droppable({
			accept : "#web-tools .item-component, #web-tools .single-comp-item",
			activeClass : "ui-state-hover",
			hoverClass : "ui-state-active",
			drop : function(e, ui) {
				$(this).addComponent(ui.draggable);
			}
		});
		//on grid widget change (resize, remove, add...)
		$(this).on('change', function (e, items) {
			$(this).closest(".content-box").attr("qb-gird-h", $(this).height()/gridConfig.cell_height);
			if($(this).find('.grid-stack-item-content').length == 0)
				$(this).append('<div class="panel-no-eleme"><p>'+getLocalize("grid_title4")+'</p></div');
			else
				$(this).find('.panel-no-eleme').remove();
			if(componentModelManager['ROLE_UPDATE'] == true){
				try{
					var maxH = 0;
					//update component model
					for(var id in items){
						var componentId = items[id].el.attr('qb-component-id');
//						if(typeof componentId !== 'undefined'){
							if(items[id].height + items[id].y > maxH){
								maxH = items[id].height + items[id].y;
							}
							if(web.MOBILE_EDITOR == 'on'){
								var mData = componentModelManager.listComponent[componentId][WEB_PRO_MDATA]?componentModelManager.listComponent[componentId][WEB_PRO_MDATA]:{};
								mData[WEB_PRO_X] = 	items[id].x;
								mData[WEB_PRO_Y] = 	items[id].y;
								mData[WEB_PRO_W] = 	items[id].width;
								mData[WEB_PRO_H] = 	items[id].height;
								componentModelManager.updateItemField(componentId, WEB_PRO_MDATA, mData);
							}
							else {
								componentModelManager.updateItemField(componentId, WEB_PRO_X, items[id].x);
								componentModelManager.updateItemField(componentId, WEB_PRO_Y, items[id].y);
								componentModelManager.updateItemField(componentId, WEB_PRO_W, items[id].width);
								componentModelManager.updateItemField(componentId, WEB_PRO_H, items[id].height);
							}
						}
//					}
					//update model box height
					var boxId = $(this).closest(".content-box").attr("qb-box-id");
					var h = maxH;//Math.floor($(this).closest(".content-box").height()/gridConfig.cell_height);
					var currentBoxH = 	$(this).closest(".content-box").find(".grid-stack-re").height();
					//update height of all grid
					if(h*gridConfig.cell_height > parseInt(currentBoxH)){
						$(this).closest(".content-box").find(".grid-stack-re").height(h*gridConfig.cell_height);
						$(this).closest(".content-box").find(".grid-stack").height(h*gridConfig.cell_height);
					}
					//update height grid when change only one box
					var newH = parseInt($(this).closest('.grid-stack').height());
					if(h*gridConfig.cell_height < newH){
						$(this).closest(".content-box").find(".grid-stack-re").height(newH);
						$(this).closest(".content-box").find(".grid-stack").height(newH);
					}
					bigBoxModelManager.updateBoxHeight(boxId, h);
					//refresh grid line
					grid.refreshGridLineY($(this));
				}
				catch(err){
					console.log(err);
				}
			}
			
		});
		
};
$.fn.initOGridFromLib = function(){
	var boxId = $(this).closest(".content-box").attr("qb-box-id");
	console.log('--init grid:'+ boxId);
	gridConfig['static_grid'] = true;
	//grid
	$(this).gridstack(gridConfig);
	$(this).height(240);
	$(this).droppable({
		accept : "#web-tools .item-component",
		activeClass : "ui-state-hover",
		hoverClass : "ui-state-active",
		drop : function(e, ui) {
			$(this).addComponent(ui.draggable);
		}
	});
	grid.setUpGridLine($(this));
	grid.setUpCrosshair($(this));
};
/**
 * @author ThoaiNH
 * create Nov, 30 2015
 * add component to box
 * @param: component item in menu tools
 */
$.fn.addComponent = function(componentItem){
	var newID = componentModelManager.getNewId();
	$('#web-tools .sub-menu').fadeOut();
	var widget;
	var componentType = componentItem.hasClass('single-comp-item')?componentItem.attr('id'):componentItem.parent().parent().parent().attr('id');
	switch(componentType)
	{
		case WEB_COMP_TYPE_TEXT:
			widget = otext.add($(this).data('gridstack'),0,0, componentItem);
		break;
		case WEB_COMP_TYPE_IMG:
			widget = imageController.add($(this).data('gridstack'),0,0,componentItem);
		break;
		case WEB_COMP_TYPE_BTN:
			widget = obutton.add($(this).data('gridstack'),0,0,componentItem);
		break;
		case WEB_COMP_TYPE_IFRAME:
			if(componentItem.attr('id') == WEB_COMP_TYPE_YOUTUBE_PLAYLIST)
			{
				/*if(componentModelManager.getTotalComponent(WEB_COMP_TYPE_YOUTUBE_PLAYLIST) > 0)
				{
					util.messageDialog(getLocalize("jsypl_mess1"))
					return;
				}*/
				componentType = WEB_COMP_TYPE_YOUTUBE_PLAYLIST;
				widget = youtubePlaylist.add($(this).data('gridstack'),0,0, componentItem);
				
			}
			else
				widget = iframe.add($(this).data('gridstack'),0,0, componentItem);
		break;
		case WEB_COMP_TYPE_GMAP:
			widget = gmapController.add($(this).data('gridstack'),0,0, componentItem);
			break;
		case WEB_COMP_TYPE_MENU:
			if($(this).parent().hasClass('qb-header') && $('.qb-header').find('.qb-component-menu').length){
				// do not process
			} else
				widget = omenu.add($(this).data('gridstack'),0,0, componentItem, newID);
			break;
		case WEB_COMP_TYPE_CONTACT_FORM:
			widget = contactForm.add($(this).data('gridstack'),0,0, componentItem);
			break;
		case WEB_COMP_TYPE_EMBED_CODE:
			widget = embedCodeController.add($(this).data('gridstack'),0,0, componentItem);
			break;
		default:
			break;
	}
	try{
		var gridIndex = this.attr('index');
		var component = {
			id: newID,
			boxId: widget.parents('.content-box').attr('qb-box-id'),
			x : parseInt(widget.attr('data-gs-x')),
			y : parseInt(widget.attr('data-gs-y')),
			w : parseInt(widget.attr('data-gs-width')),
			h : parseInt(widget.attr('data-gs-height')),
			type: componentType,
			data: widget[WEB_PRO_DATA],
			css: widget[WEB_PRO_CSS],
			bgCss: widget[WEB_PRO_BGCSS],
			gridIndex: gridIndex
		};
		widget.attr('qb-component-id',component.id);
		//add to model
		componentModelManager.addItemToModel(component);
		bigbox.onAfterAddComp(widget);
	}
	catch (e) {
		// TODO: handle exception
		console.log(e);
	}
};