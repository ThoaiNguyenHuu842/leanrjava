/*
 * author: ThoaiNH
 * date create: 09/10/2015
 * all grid event and config
 */
 (function() {
	grid = {
		init : function() {
			$('.grid-stack').each(function(){
				$(this).initOGrid();
			});
			//drag item
			$("#web-tools .item-component").draggable({ 
				//revert: true,
				appendTo: "body",
				zIndex: 999999999,
				cursor: "move",
				opacity: 0.35,
				helper: function(){
					var $self = $(this);
					switch($self.parent().parent().parent().attr('id'))
					{
						case WEB_COMP_TYPE_TEXT:
							return $('<div class="drappable-content normal-text">' + $self.html() + '</div>');
							break;
						case WEB_COMP_TYPE_IMG:
							var type_image = $self.children();
							var $result = $('<div class="drappable-content normal-image"><img src="' + util.contextPath() +'/ohhay-webapplication-new/html/images/upimage.png"></div>');;
//							if(type_image.hasClass('normal_image_1')){
//								$result = $('<div class="drappable-content normal-image"><img src="' + util.contextPath() +'/html/images/upimage.png"></div>');
//							}else if(type_image.hasClass('normal_image_2')){
//								$result = $('<div class="drappable-content normal-image"><img src="' + util.contextPath() +'/html/images/upimage.png"></div>');
//							}else if(type_image.hasClass('normal_image_3')){
//								$result = $('<div class="drappable-content normal-image"><img src="' + util.contextPath() +'/html/images/upimage.png"></div>');
//							}else if(type_image.hasClass('normal_image_4')){
//								$result = $('<div class="drappable-content normal-image"><img src="' + util.contextPath() +'/html/images/upimage.png"></div>');
//							}else if(type_image.hasClass('normal_image_5')){
//								$result = $('<div class="drappable-content normal-image"><img src="' + util.contextPath() +'/html/images/upimage.png"></div>');
//							}
							return $result;
							break;
						case WEB_COMP_TYPE_IFRAME:
							return $('<div class="drappable-content normal-text">' + $self.html() + '</div>');
							break;
						case WEB_COMP_TYPE_BTN:
							return $('<div class="drappable-content normal-button">' + $self.html() + '</div>');
							break;
						case WEB_COMP_TYPE_GMAP:
							return $('<div class="drappable-content normal-text">' + $self.html() + '</div>');
							break;
						case WEB_COMP_TYPE_MENU:
							return $('<div class="drappable-content normal-menu">' + $self.html() + '</div>');
							break;
						case WEB_COMP_TYPE_CONTACT_FORM:
							return $('<div class="drappable-content normal-text">' + $self.html() + '</div>');
							break;
						default:
							break;
					}				
				}
			});	
			grid.eventListener();
		},
		eventListener : function() {
			
		},
		onGridBoxChange: function(){
			if($("#content-wrapper").find(".content-box").length == 0)
				$("#content-wrapper").append('<div id="panel-no-box" class="qb-edit-tool"><label>'+getLocalize("grid_title1")+'</label></div>');
			else
				$("#content-wrapper #panel-no-box").remove();
		},
		/*
		 * show grid line
		 */
		showGridLine: function(){
			 $('#content-wrapper .grid-stack').each(function(){
				 grid.setUpGridLine($(this));
			 });
		},
		/*
		 * hide grid line
		 */
		hideGridLine: function(){
			$('#content-wrapper .evo-grid-line').remove();
		},
		/*
		 * setup grid line
		 */
		setUpGridLine: function(gridObject){
			if(web.GRID_LINE == 'on'){
				for(i = 1; i < gridConfig.width; i++)
				{
					gridObject.append('<div class="evo-grid-line evo-grid-line-x evo-grid-line-x-'+i+'"></div>');
					var left = 2.083333333333334 * i;
					gridObject.find('.evo-grid-line-x-'+i).css({'left':left+'%'});
				}
				var boxId = gridObject.closest(".content-box").attr("qb-box-id");
				var h = bigBoxModelManager.getBoxHeight(boxId);
				for(i = 1;i < h;i++){
					gridObject.append('<div class="evo-grid-line evo-grid-line-y evo-grid-line-y-'+i+'"></div>');
					var top = gridConfig.cell_height * i;
					gridObject.find('.evo-grid-line-y-'+i).css({'top':top+'px'});
				}
			}
		},
		/*
		 * setup cross hair
		 */
		setUpCrosshair: function(gridObject){
			gridObject.append('<div class="evo-hair evo-hair-vertical-left"></div>');
			gridObject.append('<div class="evo-hair evo-hair-vertical-right"></div>');
			gridObject.append('<div class="evo-hair evo-hair-horizontal-top"></div>');
			gridObject.append('<div class="evo-hair evo-hair-horizontal-bottom"></div>');
		},
		/*
		 * refresh grid line Y when resize box height
		 */
		refreshGridLineY: function(gridObject){
			if(web.GRID_LINE == 'on'){
				gridObject.find('.evo-grid-line-y').remove();
				var boxId = gridObject.closest(".content-box").attr("qb-box-id");
				var h = bigBoxModelManager.getBoxHeight(boxId);
				for(i = 1;i < h;i++){
					gridObject.append('<div class="evo-grid-line evo-grid-line-y evo-grid-line-y-'+i+'"></div>');
					var top = gridConfig.cell_height * i;
					gridObject.find('.evo-grid-line-y-'+i).css({'top':top+'px'});
				}
			}
		}
	}
}());
 