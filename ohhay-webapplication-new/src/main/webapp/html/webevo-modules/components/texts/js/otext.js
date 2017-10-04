/*
 * author: ThoaiNH
 * date create: 09/10/2015
 */
(function() {
	otext = {
		COMP_MIN_H : 2,
		COMP_MIN_W : 2,
		isShowToolbar : false,
		targetEditor : null,
		init : function() {
			this.eventListener();
			this.showToolbar();
		},
		eventListener : function() {
			/*
			 * mobile font scale
			 */
			$(document).on('click', '.qb-component-text .btn-mb-font-style', function() {
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
						$(self.parents(".qb-component-text").find(".otext-wrapper-content *").get().reverse()).each(function() {
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
							console.log("FONTSCALE : "+mData[WEB_PRO_DATA][WEB_PRO_FONTSCALE]);
						});
					}
				});
			});
			
		

		},
		/*
		 * add otext
		 */
		add : function(grid, x, y, $self) {
			var node = {
				x : x,
				y : y,
				width : 24,
				height : 12,
				css : ''
			};
			if ($($self).hasClass('text-full')) {
				var textTemp = '<h2>Header</h2><p>Lorem ipsum dolor sit amet, ei nec omnis nonumes deseruisse. An pro quas percipitur, pri in hinc menandri platonem, vel magna invidunt no. No nam harum viris intellegat, ei esse percipitur vel. Ad his hinc tamquam, munere eirmod regione et per. Labore recteque te has.</p>';
				node.css = "border-width: 0px; border-style: solid; border-color: rgb(0, 0, 0); padding: 15px 30px 30px;";
				node.bgCss = "background-color: rgb(235, 235, 235); background-image: initial;";
			} else if ($($self).hasClass('text-header')) {
				var textTemp = '<h2>Header</h2>';
				node.width = 12;
				node.height = 4;
			}
			var widget = grid.add_widget($(buildTemplateOtextAdd(textTemp, node)), node.x, node.y, node.width, node.height, true);
			// setup default data
			var data = {};
			widget[WEB_PRO_DATA] = data;
			widget[WEB_PRO_CSS] = node.css;
			widget[WEB_PRO_BGCSS] = node.bgCss;
			// cp_Editor.reload();
			return widget;
		},
		/*
		 * get data from html
		 */
		getData : function(id) {
			return {
				text : $(".grid-stack-item-content[qb-component-id='" + id + "'] .qb-component .otext-wrapper-content").html()
			};
		},
		/*
		 * set data to html
		 */
		load : function(grid, node, data) {
			var widget = grid.add_widget($(buildTemplateOtextLoad(data[WEB_PRO_COMPDATA_TEXT])), node.x, node.y, node.width, node.height);
			// cp_Editor.reload();
			return widget;
		},
		showToolbar : function() {
			/*
			 * var editor = new MediumEditor('[contenteditable]');
			 * 
			 * $('[contenteditable]').mediumInsert({ editor : editor });
			 */
			/*
			 * $(document).on('click','.grid-stack-item-content.otext',function(event){
			 * var $self = $(event.currentTarget); event.preventDefault();
			 * 
			 * if(!otext.isShowToolbar){
			 * $self.children('[contenteditable]').jqte(); var $toolbar =
			 * $self.find('.jqte_toolbar');
			 * $toolbar.draggable({handle:".jqte_heading"}); $toolbar.prepend('<p class="jqte_heading">Text
			 * Settings <span class="pull-right"><i class="fa fa-times-circle"></i></span></p>');
			 * otext.isShowToolbar = !otext.isShowToolbar; otext.targetEditor =
			 * $self; }
			 *  })
			 */

			/*
			 * $(document).on('blur','.jqte_editor[contenteditable]',function(){
			 * if(otext.isShowToolbar){ otext.isShowToolbar =
			 * !otext.isShowToolbar; otext.hideToolbar.apply(this); } })
			 */

			$(document).on('click', function() {
				if (otext.isShowToolbar) {
					// otext.isShowToolbar = !otext.isShowToolbar;
					// otext.hideToolbar.apply(this);
				}
			})

		},
		toggleToolbar : function() {
			otext.targetEditor != otext.targetEditor;
		},
		addHeaderToolbar : function($toolbar) {
			$toolbar.prepend('<p>Text setting</p>');
		},
		hideToolbar : function() {
			var contents = otext.targetEditor.children().find('.jqte_editor').html();
			console.log(otext.targetEditor.clone());
			var editor_content = '<div contenteditable="true" class="jqte_editor">';
			editor_content += contents;
			editor_content += '</div>';
			otext.targetEditor.prepend(editor_content);
			otext.targetEditor.children('.jqte').remove();
		}
	}
}());