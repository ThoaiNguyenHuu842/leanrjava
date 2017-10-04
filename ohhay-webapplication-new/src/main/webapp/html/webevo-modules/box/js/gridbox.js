/**
 * @author ThoaiNH create Oct 9, 2015
 */
(function() {
	gridbox = {
		init : function() {
			this.eventListener();
		},
		eventListener : function() {
			$(document).on('click','.btn-menu-change-smart-layout',function(){
				var boxId = $(this).parents('.content-box').attr('qb-box-id');
				if(web.SMART_LAYOUT)
        		{
					bigBoxModelManager.listBox[boxId]['SMART_LAYOUT'] = 'OFF';
					web.SMART_LAYOUT = false;
					$(this).find('.title').html('Smart layout: OFF');
        		}
        		else
        		{
        			bigBoxModelManager.listBox[boxId]['SMART_LAYOUT'] = 'ON';
        			web.SMART_LAYOUT = true;
        			$(this).find('.title').html('Smart layout: ON');
        		}
			});
			/*
			 * set background small box
			 */
			$(document).on('click', '#content-wrapper .content-box .menu-grid-right .btn-upload-bg-grid', function(event) {
				var $self = $(event.currentTarget);
				var targetBox = $self.parents('.grid-stack').find('.layer-background').first();
				//ANPH: new function
				backGroundSection.setBackground(targetBox, 'G');
			});
			/*
			 * grid effect
			 */
			$(document).on('click', '#content-wrapper .content-box .menu-grid-right .btn-grid-option', function(event) {
				var ob = $(this).closest('.grid-stack')
				var boxId = $(this).closest('.content-box').attr("qb-box-id");
				cp_Option.open({
					callBack : function(result) {
						if (result.style == 'box-shadow') {
							ob.find('.layer-background').first().css(result.style, result.value);
							bigBoxModelManager.updateItemField(boxId, WEB_PRO_GRID_BGCSS, webUtils.getCssEffect(ob.find('.layer-background').first()));
						} else {
							if (result.style == 'border-width') {
								result.style = 'outline-width';
								if (ob.css('outline-style') == 'none')
									ob.css('outline-style', 'solid');
							} else if (result.style == 'border-color')
								result.style = 'outline-color';
							else if (result.style == 'border-style')
								result.style = 'outline-style';
							ob.css(result.style, result.value);
						}
						// update model
						if (web.MOBILE_EDITOR == 'on') {
							var mData = bigBoxModelManager.listBox[boxId][WEB_PRO_MDATA] ? bigBoxModelManager.listBox[boxId][WEB_PRO_MDATA] : {};
							mData[WEB_PRO_GRID_CSS] = webUtils.getCssEffect(ob);
							bigBoxModelManager.updateItemField(boxId, WEB_PRO_MDATA, mData);
						} else
							bigBoxModelManager.updateItemField(boxId, WEB_PRO_GRID_CSS, webUtils.getCssEffect(ob));
					},
					self : ob,
					del : ["#cp-ot-border-inner","#cp-ot-margin","#cp-ot-padding","#cp-ot-opacity","#cp-ot-blur","#cp-ot-width","#cp-ot-height","#cp-ot-rotate"]
				});
			});
		},
		load : function(gridObject, boxData) {
			if (web.MOBILE_EDITOR == 'on' && boxData[WEB_PRO_MDATA] && boxData[WEB_PRO_MDATA][WEB_PRO_GRID_CSS])
				gridObject.attr('style', boxData[WEB_PRO_MDATA][WEB_PRO_GRID_CSS]);
			else if (boxData[WEB_PRO_GRID_CSS]) 
				gridObject.attr('style', boxData[WEB_PRO_GRID_CSS]);
			
			if (web.MOBILE_EDITOR == 'on' && boxData[WEB_PRO_MDATA] && boxData[WEB_PRO_MDATA][WEB_PRO_GRID_BGCSS])
				gridObject.find('.layer-background.girdbox').first().attr('style', boxData[WEB_PRO_MDATA][WEB_PRO_GRID_BGCSS]);
			else if (boxData[WEB_PRO_GRID_BGCSS]) 
				gridObject.find('.layer-background.girdbox').first().attr('style', boxData[WEB_PRO_GRID_BGCSS]);
			/*
			 * if(boxData[WEB_PRO_GRID_BG_VID])
			 * webUtils.setBackgroundVideo(gridObject,
			 * boxData[WEB_PRO_GRID_BG_VID]);
			 */
			gridObject.height(boxData.h * gridConfig.cell_height);
		}
	}
}());