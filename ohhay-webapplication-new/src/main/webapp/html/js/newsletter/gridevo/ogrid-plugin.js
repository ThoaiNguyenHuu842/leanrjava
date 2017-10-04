/**
 * @author ThoaiNH
 * create Oct 10, 2015
 * setup grid for box
 */
$.fn.initOGrid = function(){
		console.log('--init grid:'+ $(this).closest(".content-box").attr("qb-box-id"))
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
		//grid resizable width cell_height unit
		$(this).resizable({
		      grid: gridConfig.cell_height,
			  handles: {'s':'.qb-gird-handle-custom'},
			  axis:'y',
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
			  },
			  resize: function(event, ui) {
			        ui.size.width = ui.originalSize.width;
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
					//update model box height
					var boxId = $(this).closest(".content-box").attr("qb-box-id");
					var h = Math.floor($(this).closest(".content-box").height()/gridConfig.cell_height);
					//var currentBoxH = bigBoxModelManager.listBox[boxId][WEB_PRO_H];
					bigBoxModelManager.updateBoxHeight(boxId, h);
					//refresh grid line
					grid.refreshGridLineY($(this));
				}
				catch(err){
					console.log(err);
				}
			}
			
		});
		$(this).closest(".content-box").addClass('grid-style-onedit');
		grid.setUpGridLine($(this));
		
		$(this).width(600);
};
/**
 * @author ThoaiNH
 * create Nov, 30 2015
 * add component to box
 * @param: component item in menu tools
 */
$.fn.addComponent = function(componentItem){
	$('#web-tools .sub-menu').fadeOut();
	var widget;
	var componentType = componentItem.parent().parent().parent().attr("id");
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
			widget = iframe.add($(this).data('gridstack'),0,0, componentItem);
		break;
		case WEB_COMP_TYPE_GMAP:
			widget = gmapController.add($(this).data('gridstack'),0,0, componentItem);
			break;
		case WEB_COMP_TYPE_MENU:
			if($(this).parent().hasClass('qb-header') && $('.qb-header').find('.qb-component-menu').length){
				// do not process
			} else
				widget = omenu.add($(this).data('gridstack'),0,0, componentItem);
			break;
		case WEB_COMP_TYPE_CONTACT_FORM:
			widget = contactForm.add($(this).data('gridstack'),0,0, componentItem);
			break;
		default:
			break;
	}
	try{
		var component = {
			id: componentModelManager.getNewId(),
			boxId: widget.parents('.content-box').attr('qb-box-id'),
			x : parseInt(widget.attr('data-gs-x')),
			y : parseInt(widget.attr('data-gs-y')),
			w : parseInt(widget.attr('data-gs-width')),
			h : parseInt(widget.attr('data-gs-height')),
			type: componentType,
			data: widget[WEB_PRO_DATA],
			css: widget[WEB_PRO_CSS]
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
		console.log(componentModelManager.listComponent);
	}
	catch (e) {
		// TODO: handle exception
		console.log(e);
	}
};