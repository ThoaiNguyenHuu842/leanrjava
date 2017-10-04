/*
 * author: ThoaiNH
 * date create: 09/10/2015
 * all grid event and config
 */
(function() {
	grid = {
		init : function() {
			$('.grid-stack').each(function() {
				$(this).initOGrid();
			});
			// drag item
			$("#web-tools .item-component, #web-tools .single-comp-item").draggable({
				// revert: true,
				appendTo : "body",
				zIndex : 999999999,
				cursor : "move",
				opacity : 0.35,
				helper : function() {
					var $self = $(this);
					var compType = $self.hasClass('single-comp-item') ? $self.attr('id') : $self.parent().parent().parent().attr('id');
					switch (compType) {
					case WEB_COMP_TYPE_TEXT:
						return $('<div class="drappable-content normal-text">' + $self.html() + '</div>');
						break;
					case WEB_COMP_TYPE_IMG:
						var type_image = $self.children();
						var $result = $('<div class="drappable-content normal-image"><img src="' + util.contextPath() + '/ohhay-webapplication-new/html/images/upimage.png"></div>');
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
					case WEB_COMP_TYPE_EMBED_CODE:
						return $('<div class="drappable-content normal-embed-code">' + $self.html() + '</div>');
						break;
					case SHOP_COMP_TYPE_OPTION_LIST:
						return $('<div class="drappable-content shop-option-list">' + $self.html() + '</div>');
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
		onGridBoxChange : function() {
			if ($("#content-wrapper").find(".content-box").length == 0)
				$("#content-wrapper").append('<div id="panel-no-box" class="qb-edit-tool"><label>'+getLocalize("grid_title2")+' '+'<span class="btn-click-add-box">+</span>'+' '+getLocalize("grid_title3")+'</label></div>');
			else
				$("#content-wrapper #panel-no-box").remove();
		},
		/*
		 * show grid line
		 */
		showGridLine : function() {
			$('#content-wrapper .grid-stack').each(function() {
				grid.setUpGridLine($(this));
			});
		},
		/*
		 * hide grid line
		 */
		hideGridLine : function() {
			$('#content-wrapper .evo-grid-line').remove();
		},
		/*
		 * setup grid line
		 */
		setUpGridLine : function(gridObject) {
			if (web.GRID_LINE == 'on') {
				index = parseInt(gridObject.closest('#content-wrapper .grid-stack-re').find('.grid-stack:last-child').attr('index'));
				for (i = 1; i < 48/index; i++) {
					gridObject.append('<div class="evo-grid-line evo-grid-line-x evo-grid-line-x-' + i + '"></div>');
					var left = 2.083333333333334 * i * index;
					gridObject.find('.evo-grid-line-x-' + i).css({
						'left' : left + '%'
					});
				}
				var boxId = gridObject.closest(".content-box").attr("qb-box-id");
				var h = gridObject.closest('.content-box').attr('qb-gird-h');
				for (i = 1; i < h; i++) {
					gridObject.append('<div class="evo-grid-line evo-grid-line-y evo-grid-line-y-' + i + '"></div>');
					var top = gridConfig.cell_height * i;
					gridObject.find('.evo-grid-line-y-' + i).css({
						'top' : top + 'px'
					});
				}
			}
		},
		/*
		 * setup cross hair
		 */
		setUpCrosshair : function(gridObject) {
			gridObject.append('<div class="evo-hair evo-hair-vertical-left"></div>');
			gridObject.append('<div class="evo-hair evo-hair-vertical-right"></div>');
			gridObject.append('<div class="evo-hair evo-hair-horizontal-top"></div>');
			gridObject.append('<div class="evo-hair evo-hair-horizontal-bottom"></div>');
		},
		/*
		 * refresh grid line Y when resize box height (old)
		 */
		refreshGridLineY : function(gridObject) {
			if (web.GRID_LINE == 'on') {
				gridObject.closest('.grid-stack-re').find('.evo-grid-line-y').remove();
				var boxId = gridObject.closest(".content-box").attr("qb-box-id");
				var h = gridObject.closest('.content-box').attr('qb-gird-h');
				gridObject.closest('.grid-stack-re').find('.grid-stack').each(function() {
					for (i = 1; i < h; i++) {
						$(this).append('<div class="evo-grid-line evo-grid-line-y evo-grid-line-y-' + i + '"></div>');
						var top = gridConfig.cell_height * i;
						$(this).find('.evo-grid-line-y-' + i).css({
							'top' : top + 'px'
						});
					}
				});
			}
		},
	}
}());
