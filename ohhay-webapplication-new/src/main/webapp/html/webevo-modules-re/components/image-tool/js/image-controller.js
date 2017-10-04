(function() {
	imageController = {
		inited : false,
		COMP_MIN_H : 4,
		COMP_MIN_W : 4,
		COMP_MUL_MIN_H : 13,
		COMP_MUL_MIN_W : 18,
		callMap : {},
		socket : null,
		// normal image
		imgItemMap : {},
		saveCropMap : {},
		// BG image
		elementNeedBGMap : {},
		saveCropBGMap : {},
		bgFilterMap : {},
		sliderThumbCallbackMap : {},
		defaultImg : function(num) {
			return util.contextPath() + '/html/images/EVO-dummy-pic0' + num + '.jpg';
		},
		isShowToolbar : false,
		targetBox : null,
		rId : '',
		init : function() {
			var self = this;
			if (!self.inited) {
				self.eventListener();
				self.registerSocket();
				imageEffect.init();
				$('#qb-dlg-gallery').webToolDialog(295);
				//
				self.rId = $('#qb-userlogin-fo100').val().replace(/=/g, '') + 'BoN' + imageUtil.rand_string(8);
				self.inited = true;
			}
		},
		registerSocket : function() {
			var self = this;
			try {
				self.socket = io.connect(SOCKET_LINK + '?stateLog=loged');
				self.socket.on('connect', function() {
					self.socket.emit('uInfo', {
						siteid : $("#qb-input-pe400").val(),
						fo100 : $('#qb-userlogin-fo100').val(),
						region : $('#qb-input-region').val(),
						trial : web.TRIAL,
						rId : self.rId
					});
				});

				self.socket.on('reponseMakeThumbnailSlider', function(response) {
					self.sliderThumbCallbackMap[response['id']](response['thumbUrl']);
					delete self.sliderThumbCallbackMap[response['id']];
					if ($.isEmptyObject(self.sliderThumbCallbackMap)) {
						$('.qb-ohhay-ajax-content').hide();
						web.endModeUploadImg();
					}
				});

				self.socket.on('reponseS3UploadBGRepeat', function(response) {
					var tempData = response['imgUrl'].replace(/\./g, ''),
					//
					tempClass = tempData.substr(tempData.length - 10),
					//
					imgHtml = '<div class="myimg-warrper ' + tempClass + '">';
					imgHtml += '		<li lang="' + response['imgUrl'] + '" class="new animated bounceIn"></li>';
					imgHtml += '		<div class="function-panel"><span class="fa fa-remove"></span></div>';
					imgHtml += '	</div>';
					$(imgHtml).insertAfter("#qb-dlg-bg-repeat-col .qb-my-image-panel .btn-add-img");
					$("#qb-dlg-bg-repeat-col .qb-my-image-panel li.new").css('background-image', 'url(' + response['thumbUrl'] + ')').removeClass('new');
					$.ajaxSetup({
						beforeSend : function() {
						},
						complete : function() {
							setDefaultAjaxStatus();
						}
					});
					$.ajax({
						type : "POST",
						url : encodeUrl("myCollectionBean.uploadToMyCollection"),
						data : {
							src : 'background',
							imgUrl : response['imgUrl'],
							thumbUrl : response['thumbUrl']
						},
						success : function(data) {
							if (data.status == AJAX_SUCESS) {
								$('.myimg-warrper.' + tempClass).attr('imgId', data.result.id).removeClass(tempClass);
							} else {
								showGrowlMessageError();
							}
						}
					});
				});
				self.socket.on('reponseS3UploadLibrary', function(response) {
					var tempData = response['imgUrl'].replace(/\./g, '');
					//
					tempClass = tempData.substr(tempData.length - 10),
					//
					imgHtml = "<div class='myimg-warrper " + tempClass + "'>";
					imgHtml += "	<img src='" + response['imgUrl'] + "' data-real-src='" + response['imgUrl'] + "'/>";
					imgHtml += "	<div class='function-panel'>";
					imgHtml += "		<span class='fa fa-remove'></span>";
					imgHtml += "	</div>";
					imgHtml += "</div>";
					$(imgHtml).insertAfter(".qb-my-image-panel .btn-add-img");
					$.ajaxSetup({
						beforeSend : function() {
						},
						complete : function() {
							setDefaultAjaxStatus();
						}
					});
					$.ajax({
						type : "POST",
						url : encodeUrl("myCollectionBean.uploadToMyCollection"),
						data : {
							src : 'image',
							imgUrl : response['imgUrl'],
							thumbUrl : response['thumbUrl']
						},
						success : function(data) {
							if (data.status == AJAX_SUCESS) {
								$('.myimg-warrper.' + tempClass).attr('imgId', data.result.id).removeClass(tempClass);
							} else {
								showGrowlMessageError();
							}
						}
					});
				});

				// get base64 from response node
				self.socket.on('responseBase64', function(response) {
					self.callMap[response['id']](response['base64']);
					delete self.callMap[response['id']];
					$('.qb-ohhay-ajax-content').css('display', 'none');
				});
				// get s3Url from response node
				self.socket.on('reponseS3UploadImage', function(response) {
					if (typeof response['type'] != 'undefined')
						if (response['type'] == 'NORMAL') {
							var $img = self.imgItemMap[response['id']];
							$img.removeClass('cropbox-enable');
							$img.attr('src', response['s3Url']);
							setTimeout(function() {
								$img.data('cropbox').remove();
							}, 300);
							self.saveCropMap[response['id']](response['s3Url']);
							delete self.saveCropMap[response['id']];
							delete self.imgItemMap[response['id']];

							// hide loading when stack empty
							if ($.isEmptyObject(self.imgItemMap)) {
								$('.qb-ohhay-ajax-content').hide();
								$img.parents('.grid-stack-item-content').removeClass('processing');
								web.endModeUploadImg();
							}
						} else if (response['type'] == 'BG') {
							var $box = self.elementNeedBGMap[response['id']],
							//
							thatFilterVal = $('#baseBackground #' + response['id'] + ' .baseImgBG').attr('data-filter');
							webUtils.setBackgroundImg($box, response['s3Url'], imageUtil.bgSize);
							$box.css({
								'filter' : thatFilterVal,
								'webkitFilter' : thatFilterVal,
								'mozFilter' : thatFilterVal,
								'oFilter' : thatFilterVal,
								'msFilter' : thatFilterVal
							});
							$box.attr('data-filter', thatFilterVal);
							$box.addClass('has-effect');
							self.saveCropBGMap[response['id']](response['s3Url'], $box.attr('style'));
							setTimeout(function() {
								$('#baseBackground #' + response['id']).fadeOut('slow').remove();
							}, 1000);
							delete self.saveCropBGMap[response['id']];
							delete self.elementNeedBGMap[response['id']];
							delete self.bgFilterMap[response['id']];

							if ($.isEmptyObject(self.saveCropBGMap)) {
								$('.qb-ohhay-ajax-content').hide();
							}
						}
				});
			} catch (e) {
				console.error(e.message);
			}
		},
		eventListener : function() {
			// btn gallery option click
			$(document).on('click', '.btn-gallery-option', function() {
				$('#qb-dlg-gallery').dialog('open');
				var self = $(this).parents('.grid-stack-item-content');
				galleryManager.registerFormOption(self);
			});
			// add image to gallery
			$(document).on('click', '.gallery-image-add', function() {
				var self = $(this).parents('.grid-stack-item-content');
				imageAlbum.open({
					callBack : function(result) {
						$.each(result.data, function(k, v) {
							galleryManager.addImageToGallery(self, v);
						});
					},
					selectMode : "multiple"
				});
			});
			// edit
			$(document).on('click', '.btn-edit-img-cuted', function() {
				var $box = $(this).parents('.grid-stack-item-content'), mode = $box.attr('data-mode');
				if (mode == 'GALLERY') {
					$box.attr('data-old-type', 'saved');
					$box.removeClass('saved').addClass('editting');
				} else if (mode == 'ONE') {
					var imgBox = $box.find('.thumb-img-box'),
					//
					imgSrc = imgBox.find('.main-img').attr('data-real-src') || imgBox.find('.main-img').attr('src');
					imgBox.addClass('exist-img').find('.main-img').addClass('cropbox-enable');
					imgBox.find('.main-img').removeAttr('data-rotate');
					imageUtil.registSingleImage(imgBox, imgSrc, imgSrc);

				} else {
					var $arr = $(this).closest('.function-center-z').prev().children('.box_thumb_img');
					$.each($arr, function() {
						var self = $(this),
						//
						imgSrc = self.find('img').attr('data-real-src') || self.find('img').attr('src');
						self.addClass('exist-img').find('img').addClass('cropbox-enable exist-img');
						self.find('img').removeAttr('data-rotate');
						imageUtil.registMutiImage(self, imgSrc, imgSrc);
					});
				}
				$box.removeClass('saved').addClass('editting');

			});
			// single image upload
			$(document).on('click', '.btn-upload-small-box.qb-img-tool', function() {
				var imgBox = $(this).parents('.thumb-img-box');
				imgBox.addClass('exist-img');
				imageAlbum.open({
					callBack : function(result) {
						imageUtil.registSingleImage(imgBox, result['data'], result['data']);
					}
				});
			});
			// single image upload-s
			$(document).on('click', '.btn-upload-small-box-s.qb-img-tool', function() {
				$(this).parent().prev().find('.thumb-img-tool .btn-upload-small-box.qb-img-tool').trigger('click');
			});
			// multi image upload
			$(document).on('click', '.edit_box_thumb_img.qb-img-tool', function() {
				var imgBox = $(this).parents('.box_thumb_img');
				imgBox.addClass('exist-img');
				imageAlbum.open({
					callBack : function(result) {
						imageUtil.registMutiImage(imgBox, result['data'], result['data']);
					}
				});
			});
			// ZOOM IN
			$(document).on('click', '.btn-zoom-in', function(event) {
				$(this).closest('.thumb-img-box').find('.cropControls>.cropZoomIn').trigger('click');
			});
			// ZOOM IN-s
			$(document).on('click', '.btn-zoom-in-s', function(event) {
				$(this).parent().prev().find('.thumb-img-box .cropControls>.cropZoomIn').trigger('click');
			});
			// ZOOM OUT
			$(document).on('click', '.btn-zoom-out', function(event) {
				$(this).closest('.thumb-img-box').find('.cropControls>.cropZoomOut').trigger('click');
			});
			// ZOOM OUT-s
			$(document).on('click', '.btn-zoom-out-s', function(event) {
				$(this).parent().prev().find('.thumb-img-box .cropControls>.cropZoomOut').trigger('click');
			});
			// rotate-image
			$(document).on('click', '.btn-rotate-img.qb-img-tool', function() {
				var $img = $(this).parents('.thumb-img-box').find('img.need-effect');
				imageUtil.rotateImage($img);
			});
			// rotate-image-s
			$(document).on('click', '.btn-rotate-img-s', function() {
				$(this).parent().prev().find('.thumb-img-tool .btn-rotate-img.qb-img-tool').trigger('click');
			});
			// set image effect
			$(document).on('click', '.btn-image-effect', function() {
				var $box = $(this).parents('.grid-stack-item-content');
				$('#qb-dlg-img-efect').dialog('open');
				imageEffect.setImgNeedEffect($box);

			});
			// rotate-right
			$(document).on('click', '.btn-rotate-right.qb-img-tool', function() {
				var $boxPar = $(this).parents('.grid-stack-item-content');
				imageTool.rotateRight($boxPar);
			});
			// save
			$(document).on('click', '.btn-save-croped-img', function() {
				var $box = $(this).parents('.grid-stack-item-content');
				imageTool.actionSaveOnclick($box);
			});
			// cancel upload
			$(document).on('click', '.btn-cancel-crop-img', function() {
				var self = $(this);
				util.confirmDialog(getLocalize("jsimc_title1"), function() {
					var $box = self.parents('.grid-stack-item-content');
					imageTool.actionCancelOnclick($box);
					$('#qb-dlg-img-efect').dialog('close');
				});
			});
			// BTN action set link to img
			$(document).on('click', '.comp-image .btn-set-action', function() {
				var imageLink = $(this).parents('.grid-stack-item-content.comp-image').find('a.obutton-link');
				var imageID = $(this).parents('.grid-stack-item-content.comp-image').attr('qb-component-id');
				console.log(imageID);
				var data = componentModelManager.getDataFromID(imageID);
				actionOption.open({
					target : imageLink,
					// del: ".ao-document .ao-page",
					callBack : function(result) {
						if (result.type == 'action') {
							data.actionType = result.dataType;
							data.actionLink = result.dataLink;
							data.actionLinkTarget = result.dataLinkTarget;
							imageLink.attr('data-action-type', result.dataType);
							imageLink.attr('data-action-link', result.dataLink);
							imageLink.attr('data-action-link-target', result.dataLinkTarget);
						}
						componentModelManager.updateItemField(imageID, WEB_PRO_DATA, data);
					},
				});
			});
		},
		getDefaultSize : function(mode) {
			defSize = {
				defWidth : 24,
				defHeight : 20
			};
			if (mode == 'FOUR')
				defSize = {
					defWidth : 48,
					defHeight : 24
				};
			else if (mode == 'GALLERY')
				defSize = {
					defWidth : 48,
					defHeight : 36
				};
			return defSize;
		},
		add : function(grid, x, y, $self) {
			var self = this, imgContentBox = $self.children(), template = '',
			//
			data = {
				stat : 'new',
				mode : imgContentBox.data('mode'),
				img : [ self.defaultImg(1) ]
			};
			if (imgContentBox.data('mode') == 'GALLERY') {
				data['layout'] = 'single';
				data['isOne'] = false;
				data['hasAutoPlay'] = data['hasNavigator'] = data['hasCounter'] = data['hasThumb'] = data['isFill'] = data['isFullScr'] = true;
				data['lstImg'] = data['thumb'] = [];
				data['img'] = [ self.defaultImg(0) ];
				data['thumb'].push(self.defaultImg(0));
				template = $(imageTemplate.buildTemplateGalleryImage(data));
			} else if (imgContentBox.data('mode') == 'ONE') {
				// get random from string 4-> array length
				var str = '1234'.split('')[Math.floor(Math.random() * 4)];
				data['img'] = [ self.defaultImg(str) ];
				template = $(imageTemplate.buildTemplateSingleImage(data));
			} else {
				if (imgContentBox.data('mode') == 'TWO') {
					data['img'] = [];
					data['img'].push(self.defaultImg(3), self.defaultImg(4));
					data['layout'] = 'landscape';
				} else if (imgContentBox.data('mode') == 'THREE') {
					data['img'] = [];
					data['img'].push(self.defaultImg(2), self.defaultImg(3), self.defaultImg(4));
					data['layout'] = 'landscape';
					data['layout'] = 'portrait-2';
				} else if (imgContentBox.data('mode') == 'FOUR') {
					data['layout'] = 'landscape';
					data['img'].push(self.defaultImg(4), self.defaultImg(3), self.defaultImg(2));
				}
				template = $(imageTemplate.buildTemplateMultiImage(data));
			}

			var defSize = self.getDefaultSize(imgContentBox.data('mode')),
			//
			widget = grid.add_widget(template, x, y, defSize['defWidth'], defSize['defHeight'], true);

			// resize small box
			template.on("resizestop", function(event, ui) {
				var $elem = $(ui['element']);
				setTimeout(function() {
					imageTool.updateImageCropSize($elem);
				}, 50);
			});
			widget[WEB_PRO_DATA] = data;
			return widget;
		},
		load : function(grid, node, data) {
			data['stat'] = 'saved';// ?
			if (data['mode'] == 'GALLERY') {
				template = $(imageTemplate.buildTemplateGalleryImage(data));
				galleryManager.registerEditTool(template);
			} else if (data['mode'] == 'ONE') {
				template = $(imageTemplate.buildTemplateSingleImage(data));
			} else {
				template = $(imageTemplate.buildTemplateMultiImage(data));
			}

			// resize small box
			template.on("resizestop", function(event, ui) {
				var $elem = $(ui['element']);
				setTimeout(function() {
					imageTool.updateImageCropSize($elem);
				}, 50);
			});
			var comp = grid.add_widget(template, node.x, node.y, node.width, node.height, false);
			return comp;
		}
	}
}());