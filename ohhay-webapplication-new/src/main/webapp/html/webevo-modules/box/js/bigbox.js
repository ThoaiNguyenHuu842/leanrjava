/**
 * @author ThoaiNH create Oct 9, 2015
 */
(function() {
	bigbox = {
		listBox : {},
		isShowToolbar : false,
		targetBox : null,
		init : function() {
			this.eventListener();
		},
		eventListener : function() {
			/*
			 * on click edit webinaris project
			 */
			$(document).on('click', '.btn-edit-widget-data', function() {
				var boxId = $(this).parents('.content-box').attr('qb-box-id');
				regisWebinarisEdit.open({
					boxId : boxId
				});
			});
			/*
			 * on click add box to library
			 */
			$(document).on('click', '.btn-add-to-lib', function() {
				var boxId = $(this).parents('.content-box').attr('qb-box-id');
				if (bigBoxModelManager.listBox[boxId][WEB_PRO_STT] == WEB_PRO_STT_NEW)
					util.messageDialog(getLocalize("jsbgb_title1"));
				else {
					evoAddToLib.open({
						boxId : boxId
					});
				}
			});
			/*
			 * set selected box
			 */
			$(document).on('click', '.content-box', function() {
				bigbox.selectedBoxID = $(this).attr("qb-box-id");
			});
			/*
			 * duplicate box
			 */
			$(document).on('click', '.btn-box-duplicate', function() {
				bigBoxModelManager.duplicate($(this).parents('.content-box').attr('qb-box-id'));
			});
			/*
			 * on click effect option
			 */
			$(document).on('click', '.btn-box-option', function() {
				var ob = $(this).closest('.qb-box-component');
				var boxId = $(this).closest('.qb-box-component').attr("qb-box-id");
				cp_Option.open({
					callBack : function(result) {
						if (result.style == 'box-shadow') {
							ob.find('.layer-background').first().css(result.style, result.value);						
							// update model
							if (web.MOBILE_EDITOR == 'on') {
								var mData = bigBoxModelManager.listBox[boxId][WEB_PRO_MDATA] ? bigBoxModelManager.listBox[boxId][WEB_PRO_MDATA] : {};
								mData[WEB_PRO_BGCSS] = webUtils.getCssEffect(ob.find('.layer-background').first());
								bigBoxModelManager.updateItemField(boxId, WEB_PRO_MDATA, mData);
							} else {
								bigBoxModelManager.updateItemField(boxId, WEB_PRO_BGCSS, webUtils.getCssEffect(ob.find('.layer-background').first()));
							}
						} else {
							ob.css(result.style, result.value);
							// update model
							bigBoxModelManager.updateItemField(boxId, WEB_PRO_CSS, webUtils.getCssEffect(ob));
							if (web.MOBILE_EDITOR == 'on') {
								var mData = bigBoxModelManager.listBox[boxId][WEB_PRO_MDATA] ? bigBoxModelManager.listBox[boxId][WEB_PRO_MDATA] : {};
								mData[WEB_PRO_CSS] = webUtils.getCssEffect(ob);
								bigBoxModelManager.updateItemField(boxId, WEB_PRO_MDATA, mData);
							} else {
								bigBoxModelManager.updateItemField(boxId, WEB_PRO_CSS, webUtils.getCssEffect(ob));
							}
						}
					},
					self : ob,
					del : [ "#cp-ot-border-inner", "#cp-ot-radius", "#cp-ot-opacity", "#cp-ot-blur", "#cp-ot-width", "#cp-ot-height", "#cp-ot-width", "#cp-ot-height", "#cp-ot-rotate" ]
				});
			});
			/*
			 * on click add body box
			 */
			$(document).on('click', '#dia-add-box .btn-add-body-box', function() {
				bigbox.onClickAddBox();
				var boxData = {
					id : bigBoxModelManager.getNewId(),
					h : 20,
					type : "body",
					index : ++bigBoxModelManager.crIndex
				};
				bigBoxModelManager.addItemToModel(boxData);
				bigbox.add(boxData);
				//ThoaiVt : 30/10/2016 add event add for bigbox
				try {
					undoMgr.add({
						undo : function() {
							$(".content-box[qb-box-id='" + boxData.id + "']").fadeOut(500);
						},
						redo : function() {
							bigbox.add(boxData);
						}
					});
				} catch (e) {
					console.log(e);
				}
			});
			/*
			 * on click add header box
			 */
			$(document).on('click', '#dia-add-box .btn-add-top-box', function() {
				bigbox.onClickAddBox();
				if (!$("#content-wrapper").find(".qb-header").length) {
					var boxData = {
						id : bigBoxModelManager.getNewId(),
						h : 5,
						type : "header",
						index : ++bigBoxModelManager.crIndex
					};
					bigBoxModelManager.addItemToModel(boxData);
					bigbox.addHeader(boxData, "create");
				} else
					util.messageDialog(getLocalize("weh_message_addheader"));
			});
			/*
			 * on click add footer box
			 */
			$(document).on('click', '#dia-add-box .btn-add-footer-box', function() {
				bigbox.onClickAddBox();
				if (!$("#content-wrapper").find(".qb-footer").length) {
					var boxData = {
						id : bigBoxModelManager.getNewId(),
						h : 10,
						type : "footer",
						index : ++bigBoxModelManager.crIndex
					};
					bigBoxModelManager.addItemToModel(boxData);
					bigbox.addFooter(boxData);
				} else
					util.messageDialog(getLocalize("weh_message_addfooter"));
			});
			/*
			 * on click remove box
			 */
			$(document).on('click', '#content-wrapper .content-box .btn-del-box', function(event) {
				var id = $(this).parents('.content-box').attr('qb-box-id');
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					bigbox.remove(id);
				})

			});
			/*
			 * set image background for box
			 */
			var resetData = function() {
				$('.cropbox_bigbox').html('');
			};
			$(document).on('click', '.content-box .btn-upload-big-box', function(event) {
				var $self = $(event.currentTarget);
				var targetBox = $self.parents('.content-box').find('.layer-background').first();
				// ANPH: new function
				backGroundBigBox.setBackground(targetBox, 'B');
			});
			$(document).on('click', '.content-box .btn-set-anchor-box', function() {
				var boxID = $(this).parents('.content-box.qb-box-component').attr('qb-box-id');
				anchorOption.open({
					targetID : boxID,
					isBox : true,
				});
			});
			/*
			 * on click remove box
			 */
			$(document).on('click', '#content-wrapper .content-box .btn-box-hidemb', function(event) {
				hiddenElements.hideBigBox($(this).parents('.qb-box-component'));
				$("#qb-dlg-mb-hidden-element .panel-hidden-elements ul").niceScroll();
				var size = $("#qb-dlg-mb-hidden-element .panel-hidden-elements ul li").size();
				$("#menu-tools .item-center p .element-hidden").html(size).attr('hiddennumb', size);
			});
		},
		compress : function(source_img_obj, quality, output_format) {

			var mime_type = "image/jpeg";
			if (typeof output_format !== "undefined" && output_format == "png") {
				mime_type = "image/png";
			}

			var cvs = document.createElement('canvas');
			cvs.width = source_img_obj.naturalWidth;
			cvs.height = source_img_obj.naturalHeight;

			/*
			 * if(cvs.width > 3000){ cvs.width = cvs.width/7; cvs.height =
			 * source_img_obj.naturalHeight/7; }else if(cvs.width > 700){
			 * cvs.width = cvs.width/2; cvs.height =
			 * source_img_obj.naturalHeight/2; }else{ cvs.height =
			 * source_img_obj.naturalHeight; }
			 */

			// 1366x1588
			if (quality > 50) {
				var maxHeight = 312;
				var maxWidth = 678 * 2;
			} else {
				var maxHeight = 312 * 3;
				var maxWidth = 678 * 3;
			}

			var width = source_img_obj.naturalWidth;
			var height = source_img_obj.naturalHeight;

			var ratio = 0;

			if (width > maxWidth) {
				ratio = maxWidth / width;
				height = height * ratio;
				width = maxWidth;
			} else if (height > maxHeight) {
				ratio = maxHeight / height;
				width = width * ratio;
				height = maxHeight;
			}

			cvs.width = width;
			cvs.height = height;

			/*
			 * if(source_img_obj.naturalWidth > max_width){ cvs.width =
			 * max_width; cvs.height = cvs.width * ratio; }else
			 * if(source_img_obj.naturalHeight > max_height){ cvs.height =
			 * max_height; cvs.width = cvs.height / ratio; }
			 */

			var ctx = cvs.getContext("2d").drawImage(source_img_obj, 0, 0, cvs.width, cvs.height);
			var newImageData = cvs.toDataURL(mime_type, quality / 100);
			var result_image_obj = new Image();
			result_image_obj.src = newImageData;
			return result_image_obj;
		},
		/*
		 * load a box from box data
		 */
		initForBox : function(boxObject, boxData) {
			// setup grid stack
			/*
			 * if(boxData["fe920"] > 0) {
			 * boxObject.addClass('content-box-from-lib');
			 * boxObject.find('.grid-stack').initOGridFromLib(); } else
			 * boxObject.find('.grid-stack').initOGrid();
			 */
			boxObject.find('.grid-stack').initOGrid();
			// ThoaiNH(28/10/2016): CSS for mobile version
			if (web.MOBILE_EDITOR == 'on' && boxData[WEB_PRO_MDATA] && boxData[WEB_PRO_MDATA][WEB_PRO_CSS] && boxData[WEB_PRO_MDATA][WEB_PRO_CSS] != null && boxData[WEB_PRO_MDATA][WEB_PRO_CSS].length > 0)
				boxObject.attr('style', boxData[WEB_PRO_MDATA][WEB_PRO_CSS]);
			else
				boxObject.attr('style', boxData[WEB_PRO_CSS]);
			// set video bg
			if (boxData[WEB_PRO_BG_VID] && web.MOBILE_EDITOR != 'on')
				webUtils.setBackgroundVideo(boxObject.find('.layer-background').first(), boxData[WEB_PRO_BG_VID]);
			// set css for gird
			gridbox.load($(boxObject.find('.grid-stack')), boxData);
			grid.onGridBoxChange();
			if (PAGE_TOPIC_CONTANTS != 0) {
				boxObject.find('.grid-stack').css({
					"width" : PAGE_TOPIC_CONTANTS + "px"
				});
			} else {
				// set web config width
				boxObject.find('.grid-stack').css({
					"width" : webConfig.data.w + "px"
				});
			}
			// set web config margin left
			if (webConfig.data.mgL >= 0 && web.MOBILE_EDITOR != 'on')
				boxObject.find('.grid-stack').css({
					"margin-left" : webConfig.data.mgL + "px"
				});
			else
				boxObject.find('.grid-stack').css({
					"margin-left" : ""
				});
			// set anchor name
			if (boxData[WEB_PRO_NAME] && boxData[WEB_PRO_NAME] != null && boxData[WEB_PRO_NAME].length > 0)
				boxObject.attr('id', WEB_PREFIX_BOX + boxData[WEB_PRO_NAME]);
			// set background for mobile and PC case
			if (web.MOBILE_EDITOR == 'on' && boxData[WEB_PRO_MDATA] && boxData[WEB_PRO_MDATA][WEB_PRO_BGCSS] && boxData[WEB_PRO_MDATA][WEB_PRO_BGCSS] != null && boxData[WEB_PRO_MDATA][WEB_PRO_BGCSS].length > 0)
				boxObject.find('.layer-background').first().attr('style', boxData[WEB_PRO_MDATA][WEB_PRO_BGCSS]);
			else if (boxData[WEB_PRO_BGCSS] && boxData[WEB_PRO_BGCSS] != null && boxData[WEB_PRO_BGCSS].length > 0)
				boxObject.find('.layer-background').first().attr('style', boxData[WEB_PRO_BGCSS]);
		},
		/*
		 * add body box
		 */
		add : function(boxData) {
			if ($("#content-wrapper").find(".qb-footer").length > 0)
				$("#content-wrapper .qb-footer").before(buildTemplateBigBox(boxData));
			else
				$('#content-wrapper').append(buildTemplateBigBox(boxData));
			// init for new box
			bigbox.initForBox($('#content-wrapper .qb-body-box').last(), boxData);
			// reset sortable
			$("#content-wrapper").sortable({
				handle : '.btn-sort',
				items : ".qb-body-box",
				helper : "clone",
				forceHelperSize : true,
				opacity : 0.5,
				axis : "y",
				placeholder : 'box-sortable-placeholder',
				forcePlaceholderSize : true,
				start : function() {
					try {
						$('#content-wrapper').css('zoom', '80%'); // Webkit
						// browsers
						$('#content-wrapper').css('zoom', '0.8'); // Other
						// non-webkit
						// browsers
						$('#content-wrapper').css('-moz-transform', 'scale(0.8, 0.8)'); // Moz-browsers
						$('#content-wrapper .content-box').addClass('content-on-resize');
					} catch (e) {
						// TODO: handle exception
					}
				},
				stop : function(event, ui) {
					try {
						$('#content-wrapper').css('zoom', '100%'); // Webkit
						// browsers
						$('#content-wrapper').css('zoom', '1'); // Other
						// non-webkit
						// browsers
						$('#content-wrapper').css('-moz-transform', 'scale(1,1)'); // Moz-browsers
						$('#content-wrapper .content-box').removeClass('content-on-resize');
					} catch (e) {
						// TODO: handle exception
					}
					// update lai index (se cai tien sau)
					var i = 1;
					$("#content-wrapper .content-box").each(function() {
						var boxId = $(this).attr('qb-box-id');
						bigBoxModelManager.updateItemField(boxId, WEB_PRO_INDEX, i);
						i++;
					});
				}
			});
			// effect
			$('#content-wrapper .qb-body-box').last().find('.grid-stack').hide();
			$("html, body").animate({
				scrollTop : $(document).height()
			}, 200);
			$('#content-wrapper .qb-body-box').last().find('.grid-stack').show(500);
			if (web.MOBILE_EDITOR == 'off')
				$('#content-wrapper .qb-body-box').last().find('.grid-stack').append('<div class="panel-no-eleme"><p>'+getLocalize("grid_title4")+'</p></div');
			/*
			 * show tour guide
			 */
			if($('#flag-show-tour-guide').val() == 1)
			{
				$('#flag-show-tour-guide').val(0);
				editorTourGuide.init();
			}
		},
		/*
		 * add header box
		 */
		addHeader : function(boxData, type) {
			$('#content-wrapper').prepend(buildTemplateBigBoxHeader(boxData));
			// init for new box
			bigbox.initForBox($('#content-wrapper .qb-header'), boxData);
			// effect
			$('#content-wrapper .qb-header').hide();
			$("html, body").animate({
				scrollTop : $(document).height()
			}, 200);
			$('#content-wrapper .qb-header').show(500);
			var node = {
				x : 0,
				y : 0,
				width : 12,
				height : 1
			};
			if (type === 'create') {
				var gridStack = $('#content-wrapper .qb-header .grid-stack').data('gridstack');
				var menu_template = $('<div class="drappable-content normal-menu"><ul class="ohay-menu ohay-menu-3"><li class="menu-item"><a class="menu-item-link" data-type="none" data-link="">Home</a></li><li class="menu-item"><a class="menu-item-link" data-type="none" data-link="">About</a></li><li class="menu-item"><a class="menu-item-link" data-type="none" data-link="">Tutorial</a></li></ul></div>');
				var widget_menu = omenu.add(gridStack, 0, 0);
				bigbox.createIDHeader(widget_menu, WEB_COMP_TYPE_MENU);
			}
			/*
			 * show tour guide
			 */
			if($('#flag-show-tour-guide').val() == 1)
			{
				$('#flag-show-tour-guide').val(0);
				editorTourGuide.init();
			}
		},
		createIDHeader : function(widget, componentType) {
			try {
				var component = {
					id : componentModelManager.getNewId(),
					boxId : widget.parents('.content-box').attr('qb-box-id'),
					x : parseInt(widget.attr('data-gs-x')),
					y : parseInt(widget.attr('data-gs-y')),
					w : parseInt(widget.attr('data-gs-width')),
					h : parseInt(widget.attr('data-gs-height')),
					type : componentType,
					data : widget[WEB_PRO_DATA]
				};
				widget.attr('qb-component-id', component.id);
				componentModelManager.addItemToModel(component);
				console.log(componentModelManager.listComponent);
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
		},
		/*
		 * add footer box
		 */
		addFooter : function(boxData) {
			$('#content-wrapper').append(buildTemplateBigBoxFooter(boxData));
			// init for new box
			bigbox.initForBox($('#content-wrapper .qb-footer'), boxData);
			// effect
			$('#content-wrapper .qb-footer').hide();
			$("html, body").animate({
				scrollTop : $(document).height()
			}, 200);
			$('#content-wrapper .qb-footer').show(500);
			/*
			 * var node = { x : 0, y : 0, width : 8, height : 1 }; var gridStack =
			 * $('#content-wrapper .qb-footer .grid-stack').data('gridstack');
			 * gridStack.add_widget($("<ul class='grid-stack-item-content'><li contenteditable='true'><a>@2015
			 * O!hay - QueenB JSC</a></li><li contenteditable='true'><a>CONTACT</a></li><li contenteditable='true'><a>TERM
			 * OF USE</a></li></ul>"), 0, 0, 12, 1);
			 */
			/*
			 * show tour guide
			 */
			if($('#flag-show-tour-guide').val() == 1)
			{
				$('#flag-show-tour-guide').val(0);
				editorTourGuide.init();
			}
		},
		/*
		 * on Box height change
		 */
		onBoxHeightChange : function(id, height) {
			var hUnit = height / gridConfig.cell_height;
			$(".content-box[qb-box-id='" + id + "']").attr("qb-gird-h", hUnit);
			bigBoxModelManager.updateBoxHeight(id, hUnit);
		},
		/*
		 * remove box
		 */
		remove : function(id) {
			try {
				bigBoxModelManager.removeItemFromModel(id);
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
			$(".content-box[qb-box-id='" + id + "']").fadeOut(500);
			var self = $(".content-box[qb-box-id='" + id + "']");
			setTimeout(function() {
				self.remove();
				grid.onGridBoxChange();
				// render web tools
				webMenuLeft.render();
			}, 1000);
		},
		/*
		 * update box data model
		 */
		update : function() {
			/*
			 * sort box by index before add (se doi lai) phai dung list tam do
			 * height cua box bi set khi add
			 */
			var listBox = [];
			for ( var id in bigBoxModelManager.listBox)
				listBox.push(bigBoxModelManager.listBox[id]);
			listBox.sort(function(a, b) {
				return a[WEB_PRO_INDEX] - b[WEB_PRO_INDEX];
			});
			listBox.forEach(function(boxData) {
				bigbox.updateOneBox(boxData);
			});
		},
		/*
		 * update one box, add new HTML if box was'nt added
		 */
		updateOneBox : function(boxData) {
			bigBoxModelManager.crIndex = boxData.index;
			if ($("#content-wrapper").find(".content-box[qb-box-id='" + boxData.id + "']").length == 0) {
				if (boxData.type == 'footer')
					this.addFooter(boxData);
				else if (boxData.type == 'header')
					this.addHeader(boxData);
				else
					this.add(boxData);
			}
		},
		/*
		 * check if web has only one box
		 */
		hasOneBox : function() {
			var crBox;
			var i = 0;
			for ( var id in bigBoxModelManager.listBox) {
				if (bigBoxModelManager.listBox[id][WEB_PRO_STT] != WEB_PRO_STT_REMOVED) {
					i++;
					crBox = bigBoxModelManager.listBox[id];
				}
			}
			if (i == 1 && crBox)
				return crBox;
			return null;
		},
		/*
		 * on click add new box
		 */
		onClickAddBox : function() {
			if (web.START_WITH_NOBOX == true) {
				$('#menu-tools').show();
				web.START_WITH_NOBOX = false;
			}
			$('#dia-add-box').fadeOut();
		}
	}
}());