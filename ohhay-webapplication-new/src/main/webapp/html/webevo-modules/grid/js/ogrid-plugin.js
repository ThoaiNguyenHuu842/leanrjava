/**
 * @author ThoaiNH
 * create Oct 10, 2015
 * setup grid for box
 */
$.fn.initOGrid = function(){
		var boxId = $(this).closest(".content-box").attr("qb-box-id");
		console.log('--init grid:'+ boxId);
		//grid
		$(this).gridstack(gridConfig);
		$(this).height(240);
		$(this).droppable({
			accept : "#web-tools .item-component, #web-tools .single-comp-item, .admin-library-load img",
			activeClass : "ui-state-hover",
			hoverClass : "ui-state-active",
			drop : function(e, ui) {
				$(this).addComponent(ui.draggable);
			}
		});
		//grid resizable width cell_height unit
		$(this).resizable({
		      grid: gridConfig.cell_height,
			  handles: {'n,s':'.qb-gird-handle-custom'},
			  resize: function(event, ui) {
				  ui.size.width = ui.originalSize.width+2;
				  var heightEnd = parseInt(0);
				  $(this).find('.grid-stack-item-content').each(function(){
					  var y = parseInt($(this).attr('data-gs-y'));
					  var height = parseInt($(this).attr('data-gs-height'));
					  console.log("item width : "+height);
					  if(heightEnd < ((y+height)*gridConfig.cell_height))
						  heightEnd = ((y+height)*gridConfig.cell_height);
				  });
				  if(parseInt($(this).height())<heightEnd){
						$(this).height(heightEnd)
					}
				  else
						$(this).height($(this).height())
			  },
			  stop: function(event,ui){
				  //not allow resize width
				  if(PAGE_TOPIC_CONTANTS != 0){
					  $('#content-wrapper .content-box .grid-stack').css({"width":PAGE_TOPIC_CONTANTS+"px"});
				  }else{
					  $('#content-wrapper .content-box .grid-stack').css({"width":webConfig.data.w+"px"});
				  }
				  //box height must more than content height
				  var contentH = bigBoxModelManager.getContentHeight($(this).parents('.content-box').attr('qb-box-id'));
				  if($(this).height() < contentH){
					  $(this).height(contentH);
				  }
				  bigbox.onBoxHeightChange($(this).closest(".content-box").attr("qb-box-id"),$(this).height());
				  grid.refreshGridLineY($(this));
			  }
	    });
		//on grid widget change (resize, remove, add...)
		$(this).on('change', function (e, items) {
			$(this).closest(".content-box").attr("qb-gird-h", $(this).height()/gridConfig.cell_height);
			if($(this).find('.grid-stack-item-content').length == 0)
				$(this).append('<div class="panel-no-eleme"><p>'+getLocalize("grid_title4")+'</p></div');
			else
				$(this).find('.panel-no-eleme').remove();
			if(componentModelManager['ROLE_UPDATE'] == true || web.MOBILE_EDITOR == 'on'){
				try{
					//update component model
					for(var id in items){
						var componentId = items[id].el.attr('qb-component-id');
						if(typeof componentId !== 'undefined'){
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
					}
					if(web.MOBILE_EDITOR == 'off'){
						//update model box height
						var boxId = $(this).closest(".content-box").attr("qb-box-id");
						var h = Math.floor($(this).closest(".content-box").height()/gridConfig.cell_height);
						//var currentBoxH = bigBoxModelManager.listBox[boxId][WEB_PRO_H];
						bigBoxModelManager.updateBoxHeight(boxId, h);
						//refresh grid line
						grid.refreshGridLineY($(this));
					}
				}
				catch(err){
					console.log(err);
				}
			}
		});
		$(this).closest(".content-box").addClass('grid-style-onedit');
		grid.setUpGridLine($(this));
		grid.setUpCrosshair($(this));
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
	if(componentItem.parent().parent().hasClass('data-content')){
		var type = componentItem.parents('.item').attr('id');
		var id = componentItem.attr('compId');
		var component = jQuery.extend(true, {}, evoComLib.LIB_COMP_DATA[type][id]);
//		var component = evoComLib.LIB_COMP_DATA[type][id];
		console.log(component);
		
		var selectedBoxId =$(this).parents('.content-box').attr('qb-box-id');
		delete component[WEB_PRO_NAME];
		component[WEB_PRO_X] = -1;
		component[WEB_PRO_FE900] = id;
		component[WEB_PRO_Y] = -1;
		component[WEB_PRO_BOXID] = selectedBoxId;
		component[WEB_PRO_ID] = componentModelManager.getNewId();
		componentModelManager.addItemToModel(component);
		componentModelManager.loadComponent(component).goTo();
	}
	else {
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
				bgCss: widget[WEB_PRO_BGCSS]
			};
			widget.attr('qb-component-id',component.id);
			//add to model
			componentModelManager.addItemToModel(component);
			//scroll to widget after added
			setTimeout(function(){
				widget.goTo();
				//effect 
				if(!widget.find('.qb-component').hasClass('animated')){
					widget.find('.qb-component').addClass('animated pulse');
					setTimeout(function(){
						widget.find('.qb-component').removeClass('animated pulse');
					}, 1000);
				}
			}, 200);
			console.log(componentModelManager.listComponent, 'ogird---');
		}
		catch (e) {
			// TODO: handle exception
			console.log(e);
		}
		}
};