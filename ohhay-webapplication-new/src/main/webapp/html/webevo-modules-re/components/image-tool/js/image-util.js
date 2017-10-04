/**
 * @author Anph create date: 26/10/2015 save original source img always greater
 *         frame
 */
(function() {
	/*
	 * 
	 * ANPH: imageUtil
	 * 
	 */
	imageUtil = {
		extTypes : {
			"gif" : "image/gif",
			"jpeg" : "image/jpeg",
			"jpg" : "image/jpeg",
			"png" : "image/png",
		},

		registSingleImage : function(boxImg, imgSrc, realSrc) {
			var self = this,
			//
			girdbox = boxImg.parents('.grid-stack-item-content'),
			//
			$img = boxImg.find('img.main-img');
			boxImg.parents('.grid-stack-item-content').addClass('processing');
			boxImg.attr('def-bg', boxImg.attr('style')).attr('style', '');
			web.goToModeUploadImg();
			$('.qb-ohhay-ajax-content').fadeIn();

			// attr nhan dang de cancel tra lai
			if (girdbox.hasClass('new'))
				girdbox.attr('data-old-type', 'new');
			else
				girdbox.attr('data-old-type', 'saved');

			girdbox.removeClass('saved new').addClass('editting');
			$img.attr('src', imgSrc).on('load', function() {
				$('.qb-ohhay-ajax-content').fadeOut();
			}).on('error', function() {
				$('.qb-ohhay-ajax-content').fadeOut();
			});
			var $smallBox = boxImg.parents('.grid-stack-item-content');
			$img.addClass('cropbox-enable');
			self.initCropbox($img, $smallBox.width(), $smallBox.height(), true);
			$img.css({
				width : 'auto',
				height : 'auto'
			});
			$img.attr('data-real-src', realSrc);
			imageTool.updateImageCropSize($smallBox);
		},

		registMutiImage : function(boxImg, imgSrc, realSrc) {
			var self = this, $smallBox = boxImg.parents('.grid-stack-item-content'), $img = boxImg.find('img'), mode = $smallBox.attr('data-mode'), layout = $smallBox.attr('data-layout') || '', index = boxImg.attr('data-index') || '';

			boxImg.find('.thumb-img-box').attr('def-bg', boxImg.find('.thumb-img-box').attr('style')).attr('style', '');
			// all image exist -> show tool
			if (boxImg.siblings('.box_thumb_img.exist-img').length == boxImg.siblings('.box_thumb_img').length)
				$smallBox.addClass('full');
			$smallBox.addClass('processing');
			web.goToModeUploadImg();
			$('.qb-ohhay-ajax-content').fadeIn();

			// attr nhan dang de cancel tra lai
			if ($smallBox.hasClass('new'))
				$smallBox.attr('data-old-type', 'new');
			else
				$smallBox.attr('data-old-type', 'saved');

			// class saved to show/height rotate btn && save btn
			$smallBox.removeClass('saved new').addClass('editting');

			$img.attr('src', imgSrc).on('load', function() {
				$('.qb-ohhay-ajax-content').fadeOut();
			}).on('error', function() {
				$('.qb-ohhay-ajax-content').fadeOut();
			});
			var ratio = self.getTrueRatioImage(mode + '##' + layout + '##' + index);
			$img.addClass('cropbox-enable');
			self.initCropbox($img, $smallBox.width() * ratio['ratioW'], $smallBox.height() * ratio['ratioH'], true);
			$img.css({
				width : 'auto',
				height : 'auto'
			});

			$img.attr('data-real-src', realSrc);
			imageTool.updateImageCropSize($smallBox);
		},

		resizeSingleImageNinitCropbox : function($img, originalSrc, boxImg) {
			var self = this,
			//
			$smallBox = boxImg.parents('.grid-stack-item-content'),
			// stub image
			img = new Image();
			img.crossOrigin = "Anonymous"; // cross support
			img.onload = function() {
				var naW = img.naturalWidth, naH = img.naturalHeight,
				// set width to max width allow = webConfig.CURRENT_WIDTH
				resizedW = (naW > webConfig.data.w) ? webConfig.data.w : naW,
				// new height = (original height / original width) x new width
				resizedH = (naW > webConfig.data.w) ? (naH / naW) * resizedW : naH,
				//
				gsW = $smallBox.data('gsWidth'),
				//
				gsH = $smallBox.data('gsHeight'),
				//
				boxW = gsW * gridConfig.getCellWidth(),
				//
				boxH = gsH * gridConfig.cell_height,
				//
				boxId = $smallBox.attr('qb-component-id');
				$img.attr('src', self.resizeImage(img, naW, naH));
				// class to process cropbox
				$img.addClass('cropbox-enable');
				// process width img follow box or box follow img
				// not resize small box follow condition
				if (resizedW >= boxW || resizedH >= boxH)
					imageTool.updateImageCropSize($smallBox);
				else {
					// CALC WIDTH box in grid
					gridW = Math.floor(resizedW / gridConfig.getCellWidth());
					// CALC HEIGHT box in grid
					gridH = Math.floor(resizedH / gridConfig.cell_height);
					// update parent box size with size image
					var x = boxImg.parents('.grid-stack').data('gsX'),
					//
					y = boxImg.parents('.grid-stack').data('gsY');
					boxImg.parents('.grid-stack').data('gridstack').update($smallBox, x, y, gridW, gridH);
					// init grid with floor size
					self.initCropbox($img, resizedW, resizedH, true);
				}
				// css image parent box on success image loaded
				$img.css({
					width : 'auto',
					height : 'auto'
				});
				$('.qb-ohhay-ajax-content').fadeOut();
			}
			// set original src img to stub image
			img.src = originalSrc;
		},

		resizeMultiImageNinitCropbox : function($img, originalSrc, boxImg, modeLayoutIndex) {
			var self = this,
			//
			$smallBox = boxImg.parents('.grid-stack-item-content'),
			// stub image
			img = new Image();
			img.crossOrigin = "Anonymous"; // cross support
			img.onload = function() {
				var naW = img.naturalWidth, naH = img.naturalHeight,
				//
				gsW = $smallBox.attr('data-gs-width'),
				//
				gsH = $smallBox.attr('data-gs-height'),
				//
				resizedW = (naW > webConfig.data.w) ? webConfig.data.w : naW,
				//
				resizedH = (naW > webConfig.data.w) ? (naH / naW) * resizedW : naH,
				// get true ratio for resize image
				ratio = self.getTrueRatioImage(modeLayoutIndex),
				//
				wToUse = resizedW * ratio['ratioW'], hToUse = resizedH * ratio['ratioH'];
				// get image resized
				$img.attr('src', self.resizeImage(img, naW, naH));
				$img.addClass('cropbox-enable');
				// process width img follow box or box follow img
				self.initCropbox($img, $smallBox.width() * ratio['ratioW'], $smallBox.height() * ratio['ratioH'], true);

				// css image parent box on success image loaded
				$img.css({
					width : 'auto',
					height : 'auto'
				});
				$('.qb-ohhay-ajax-content').fadeOut();

			}
			// set original src img to stub image
			img.src = originalSrc;
		},
		// get true ratio to render image inner a box
		getTrueRatioImage : function(modeLayoutIndex) {

			var isSameY = isSameY || false, mode = modeLayoutIndex.split('##')[0],
			//
			layout = modeLayoutIndex.split('##')[1];
			index = modeLayoutIndex.split('##')[2] || '', ratioW = ratioH = 0;
			if (mode == 'TWO') {
				if (layout == 'landscape') {
					ratioW = 1;
					ratioH = 1 / 2;
				} else if (layout == 'portrait') {
					ratioW = 1 / 2;
					ratioH = 1;
				}
			} else if (mode == 'THREE') {
				switch (layout) {
				case 'landscape-1':// big first
					if (index == 1) {
						ratioW = 1;
						ratioH = 1 / 2;
					} else if (index == 2 || index == 3) {
						ratioW = ratioH = 1 / 2;
					}
					break;
				case 'landscape-2':// big last
					if (index == 2 || index == 3) {
						ratioW = ratioH = 1 / 2;
					} else if (index == 1) {
						ratioW = 1;
						ratioH = 1 / 2;
					}
					break;
				case 'portrait-1':// big first
					if (index == 1) {
						ratioW = 1 / 2;
						ratioH = 1;
					} else if (index == 2 || index == 3) {
						ratioW = ratioH = 1 / 2;
					}
					break;
				case 'portrait-2':// big last
					if (index == 2 || index == 3) {
						ratioW = ratioH = 1 / 2;
					} else if (index == 1) {
						ratioW = 1 / 2;
						ratioH = 1;
					}
					break;
				case 'portrait-3':// all portrait
					ratioW = 1 / 3;
					ratioH = 1;
					break;
				}
			} else if (mode == 'FOUR') {
				if (layout == 'landscape') {
					ratioW = ratioH = 1 / 2;
				} else if (layout == 'portrait') {
					ratioW = 1 / 4;
					ratioH = 1;
				}
			}
			var ratio = {
				ratioW : ratioW,
				ratioH : ratioH
			};
			return ratio;
		},
		initCropbox : function($img, w, h, isReRatio, showControls) {
			showControls = showControls || 'hover';
			if ($img.hasClass('cropbox-enable')) {
				$img.cropbox({
					width : w,
					height : h,
					maxZoom : 2,
					showControls : showControls,
					callback : function() {
						if (isReRatio)
							setTimeout(function() {
								var crop = $img.data('cropbox');
								crop.disableDrag(false);
								crop.zoom(0);
							}, 150)
					}
				});
			}
		},
		getBase64FromUrlNode : function(imgUrl, callback) {
			if ($('.qb-ohhay-ajax-content').css('display') == 'none')
				$('.qb-ohhay-ajax-content').css('display', 'block');
			var d = new Date(), n = d.getTime();
			imageController.callMap[n] = callback;
			imageController.socket.emit('getBase64FromUrl', {
				url : imgUrl,
				id : n
			});
		},
		getBase64FromUrl : function(imgUrl, callback) {
			formData = new FormData();
			formData.append('imgUrl', imgUrl);
			$.ajax({
				type : "POST",
				url : encodeUrl("myCollectionBean.getBase64FromUrl"),
				cache : false,
				contentType : false,
				processData : false,
				data : formData,
				success : function(response) {
					if (response.status == AJAX_SUCESS) {
						callback(response['result'][0]);
					} else {
						showGrowlMessageError();
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		saveCropboxNode : function($img, callback) {
			var self = this, crop = $img.data('cropbox');
			if (typeof crop != 'undefined') {
				var d = new Date(), n = d.getTime(), url = $img.attr('data-real-src'), id = n + self.rand_string();
				imageController.saveCropMap[id] = callback;
				imageController.imgItemMap[id] = $img;
				crop.disableDrag(true);
				imageController.socket.emit('cropImgAndSaveImageToS3', {
					type : 'NORMAL',
					left : crop['result']['cropX'] * crop['percent'],
					top : crop['result']['cropY'] * crop['percent'],
					right : (crop['result']['cropX'] + crop['result']['cropW'] - 1) * crop['percent'],
					bot : (crop['result']['cropY'] + crop['result']['cropH'] - 1) * crop['percent'],
					percent : crop['percent'],
					deg : $img.attr('data-rotate'),
					url : url,
					siteid : webCreater.data.id,
					fo100 : $('#qb-userlogin-fo100').val(),
					region : $('#qb-input-region').val(),
					id : id
				});
			}
		},
		getFingerPrint : function(callback) {
			privateBrowerChecker.detectPrivateMode(function(is_private) {//
				new Fingerprint2().get(function(result, components) {//
					var token = result + '_' + (is_private ? 'pri' : 'pub');
					callback(null, token);
				});
			});
		},
		rand_string : function(len) {
			len = len || 8;
			var $chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', chars = $chars.split(''), str = "", size = chars.length;
			for (var i = 0; i < len; i++) {
				var lastNumber = Math.floor(Math.random() * size);
				str += chars[lastNumber];
			}
			return str;
		},
		saveCropbox : function($img, callback) {
			var self = this, crop = $img.data('cropbox'),
			//
			contentType = self.getContentTypeFromIMG($img);

			if (typeof crop != 'undefined') {
				var base64 = crop.getDataURL(contentType),
				//
				webId = webCreater.data.id,
				//
				formData = new FormData();
				formData.append('webId', webId);
				formData.append('imgBase64', base64);
				formData.append('src', 'image');
				$.ajax({
					type : "POST",
					url : encodeUrl("myCollectionBean.upLoad"),
					cache : false,
					contentType : false,
					processData : false,
					data : formData,
					success : function(response) {
						if (response.status == AJAX_SUCESS) {
							$img.data('cropbox').remove();
							$img.removeClass('cropbox-enable');
							$img.attr('src', response['result']['imgUrl']);
							$img.data('base64', '');
							callback(response['result']['imgUrl']);
						} else {
							showGrowlMessageError();
						}
					},
					error : function(e) {
						showGrowlMessageAjaxError();
					}
				});
			}
		},
		// same y->return true
		checkSameY : function(boxImg) {
			var $smallBox = boxImg.parents('.grid-stack-item-content'), elemPar = $smallBox[0],
			// this position
			elemParRect = (typeof elemPar != 'undefined') ? elemPar.getBoundingClientRect() : '', yFrom = elemParRect['y'], yTo = yFrom + elemParRect['height'],
			// all siblings position
			arrSParRect = $smallBox.siblings('.grid-stack-item-content').map(function() {
				return (typeof this != 'undefined') ? this.getBoundingClientRect() : '';
			}).get();
			for (var i = 0; i < arrSParRect.length; i++) {// loop all sibling
				var ySFrom = arrSParRect[i]['y'], // ySiblingsFrom
				ySTo = ySFrom + arrSParRect[i]['height'];// ySiblingsTo
				// check same yTo vs ySFrom || ySTo vs yFrom
				if (yTo == ySFrom)
					ySFrom += 1;
				else if (ySTo == yFrom)
					yFrom += 1;

				// if parElemt has y same any siblings y return true
				return (ySFrom <= yFrom && yFrom <= ySTo) || (ySFrom <= yTo && yTo <= ySTo) || (yTo <= ySFrom && ySFrom <= yFrom) || (yTo <= ySTo && ySTo <= yFrom);
			}
			return false;
		},
		// same x->return true
		checkSameX : function(boxImg) {
			var $smallBox = boxImg.parents('.grid-stack-item-content'), elemPar = $smallBox[0],
			// this position
			elemParRect = (typeof elemPar != 'undefined') ? elemPar.getBoundingClientRect() : '', xFrom = elemParRect['x'], xTo = xFrom + elemParRect['width'],
			// all siblings position
			arrSParRect = $smallBox.siblings('.grid-stack-item-content').map(function() {
				return (typeof this != 'undefined') ? this.getBoundingClientRect() : '';
			}).get();
			for (var i = 0; i < arrSParRect.length; i++) {// loop all sibling
				var xSFrom = arrSParRect[i]['x'], // ySiblingsFrom
				xSTo = xSFrom + arrSParRect[i]['width'];// ySiblingsTo
				// check same yTo vs ySFrom || ySTo vs yFrom
				if (xTo == xSFrom)
					ySFrom += 1;
				else if (xSTo == xFrom)
					xFrom += 1;

				// if parElemt has y same any siblings y return true
				return (xSFrom <= xFrom && xFrom <= xSTo) || (xSFrom <= xTo && xTo <= xSTo) || (xTo <= xSFrom && xSFrom <= xFrom) || (xTo <= xSTo && xSTo <= xFrom);
			}
			return false;
		},
		resizeImage : function(img, resizedW, resizedH) {
			var canvas = document.createElement("canvas"), ctx = canvas.getContext("2d");
			canvas.setAttribute('width', resizedW);
			canvas.setAttribute('height', resizedH);
			ctx.drawImage(img, 0, 0, parseInt(resizedW), parseInt(resizedH));
			var dataURL = canvas.toDataURL();
			canvas = null;
			return dataURL;
		},
		rotateImageNode : function($img) {
			var curRotateVal = parseInt($img.attr('data-rotate')) || 0,
			//
			newRotateVal = curRotateVal == 270 ? 0 : (curRotateVal + 90);

			$img.attr('data-rotate', newRotateVal);
			$img.css({
				'-ms-transform' : 'rotate(' + newRotateVal + 'deg)',
				'-webkit-transform' : 'rotate(' + newRotateVal + 'deg)',
				'transform' : 'rotate(' + newRotateVal + 'deg)'
			});
		},
		rotateImage : function($img) {
			var canvas = document.createElement("canvas"), ctx = canvas.getContext("2d"), img = new Image();
			img.crossOrigin = 'anonymous';
			img.onload = function() {
				canvas.width = img.height;
				canvas.height = img.width;
				ctx.translate(canvas.width / 2, canvas.height / 2);
				ctx.rotate(Math.PI / 2);
				ctx.drawImage(img, -img.width / 2, -img.height / 2);
				ctx.rotate(-Math.PI / 2);
				ctx.translate(-canvas.width / 2, -canvas.height / 2);
				var dataUrl = canvas.toDataURL(imageUtil.getContentTypeFromIMG($img));
				canvas = null;
				$img.attr('src', dataUrl);

				var curRotateVal = parseInt($img.attr('data-rotate')) || 0,
				//
				newRotateVal = curRotateVal == 270 ? 0 : (curRotateVal + 90);
				$img.attr('data-rotate', newRotateVal);
				imageTool.updateImageCropSize($img.closest('.grid-stack-item-content'));
			};
			img.src = $img[0].src;

		},
		filterBackgroundStyle : function(css) {
			var s = {
				bg : {},
				os : {}
			};
			if (!css)
				return s;
			// OBJ CSSStyleDeclaration
			if (css instanceof CSSStyleDeclaration) {
				for ( var i in css) {
					if ((css[i]).toLowerCase) {
						// background css
						if (css[i].indexOf('background') === 0 || css[i].indexOf('filter') > -1) {
							s.bg[(css[i]).toLowerCase()] = (css[css[i]]);
						} else {// object style css
							s.os[(css[i]).toLowerCase()] = (css[css[i]]);
						}
						s[(css[i]).toLowerCase()] = (css[css[i]]);
					}
				}
				// CSS string from style attr
			} else if (typeof css == "string") {
				css = css.split(";");
				for ( var j in css) {
					if ((css[j]).length > 0) {
						var cssProp = css[j].trim(), l = cssProp.split(":");
						// background css
						if (l[0].indexOf('background') === 0 || l[0].indexOf('filter') > -1) {
							s.bg[l[0].toLowerCase().trim()] = cssProp.replace(l[0] + ':', '');
						} else {// object style css
							s.os[l[0].toLowerCase().trim()] = cssProp.replace(l[0] + ':', '');
						}
					}
				}
			}
			return s;
		},
		getCss : function(e) {
			var self = this, sheets = document.styleSheets, o = {};
			for ( var i in sheets) {
				var rules = sheets[i].rules || sheets[i].cssRules;
				for ( var r in rules)
					if (e.is(rules[r].selectorText))
						o = $.extend(o, self.css2json(rules[r].style), self.css2json(e.attr('style')));

			}
			return o;
		},
		css2json : function(css) {
			var s = {};
			if (!css)
				return s;
			if (css instanceof CSSStyleDeclaration) {
				for ( var i in css) {
					if ((css[i]).toLowerCase) {
						s[(css[i]).toLowerCase()] = (css[css[i]]);
					}
				}
			} else if (typeof css == "string") {
				css = css.split(";");
				for ( var i in css) {
					var l = css[i].split(":");
					s[l[0].toLowerCase().trim()] = (l[1].trim());
				}
			}
			return s;
		},
		getContentTypeFromIMG : function($img) {
			var src = $img.attr('src');
			if (src.indexOf('data:') > -1 && src.indexOf(';base64,') > -1) {// base64
				return (src.split(';base64,')[0]).split(':')[1];
			} else {// url
				return this.getContentType(this.getExt(src));
			}
		},
		getExt : function(path) {
			var i = path.lastIndexOf('.');
			return (i < 0) ? '' : path.substr(i + 1);
		},
		getContentType : function(ext) {
			return this.extTypes[ext.toLowerCase()] || 'image/jpeg';
		},
		checkCompSaved : function($id) {
			stat = componentModelManager.listComponent[$id][WEB_PRO_STT];

			if (stat == WEB_PRO_STT_UPDATE || stat == WEB_PRO_STT_NO_CHANGE)
				return true;
			else if (stat == WEB_PRO_STT_NEW || stat == WEB_PRO_STT_REMOVED)
				return false;
		}
	},
	/*
	 * ANPH: imageTool
	 * 
	 * v All tool for process image
	 */
	imageTool = {
		actionSaveOnclick : function($box) {
			// ALL ASYN
			var mode = $box.attr('data-mode'), data = {},
			//
			layout = $box.attr('data-layout') || '',
			//
			isFirefox = typeof InstallTrigger !== 'undefined';
			$box.removeClass('editting').addClass('saved');
			if (mode == 'GALLERY') {
				var imgAr = {}, thumbAr = {}, filterAr = {}, $objGal = $box.find('.gallery-list'),
				//
				thumbImgAr = $box.find('.thumbnail-list').children();
				$.each($objGal.children(), function(i) {
					var filter = isFirefox ? $(this).css('filter') : $(this).css('-webkit-filter');
					imgAr[i] = $(this).css('background-image').replace('url("', '').replace('")', '');
					thumbAr[i] = $(thumbImgAr[i]).css('background-image').replace('url("', '').replace('")', '');
					filterAr[i] = filter;
				});
				data = {
					mode : mode,
					layout : $box.attr('data-layout'),
					isOne : $box.hasClass('one'),
					hasCounter : $box.hasClass('use-counter'),
					hasThumb : $box.hasClass('use-thumbnail'),
					hasNavigator : $box.find('.gal-controller').hasClass('use-navigator'),
					hasAutoPlay : $box.hasClass('auto-play'),
					isFill : $objGal.hasClass('fill'),
					isFullScr : $box.find('.fullscreen-action').hasClass('use-fullscreen'),
					lstImg : imgAr,
					filter : filterAr,
					lstThumb : thumbAr
				};
				componentModelManager.updateItemField($box.attr('qb-component-id'), WEB_PRO_DATA, data);
			} else {// need loading on
				if (!$('.qb-ohhay-ajax-content').is(":visible"))
					$('.qb-ohhay-ajax-content').show();
				if (mode == 'ONE') {
					var $img = $box.find('img'),
					//
					filter = isFirefox ? $img.css('filter') : $img.css('-webkit-filter');
					data = {
						mode : mode,
						filter : [ filter ],
						img : [],
						realSrc : [ $img.attr('data-real-src') ]
					};
					var linkData = componentModelManager.listComponent[$box.attr('qb-component-id')][WEB_PRO_DATA];
					data['actionLink'] = linkData['actionLink'] ? linkData['actionLink'] : '';
					data['actionLinkTarget'] = linkData['actionLinkTarget'] ? linkData['actionLinkTarget'] : '';
					data['actionType'] = linkData['actionType'] ? linkData['actionType'] : '';
					imageUtil.saveCropboxNode($img, function(src) {
						data['img'][0] = src;
						componentModelManager.updateItemField($box.attr('qb-component-id'), WEB_PRO_DATA, data);
					});
					$img.removeClass('new');
				} else {
					var imgAr = {}, realSrcAr = {}, filterAr = {};
					$.each($box.find('.qb-component').first().children('.box_thumb_img'), function(i) {
						var $img = $(this).find('img'),
						//
						filter = isFirefox ? $img.css('filter') : $img.css('-webkit-filter');
						imageUtil.saveCropboxNode($img, function(src) {
							imgAr[i] = src;
							realSrcAr[i] = $img.attr('data-real-src');
							filterAr[i] = filter;
						});
						$img.removeClass('new');
					});
					data = {
						mode : mode,
						layout : layout,
						img : imgAr,
						filter : filterAr,
						realSrc : realSrcAr
					};
					componentModelManager.updateItemField($box.attr('qb-component-id'), WEB_PRO_DATA, data);
				}
			}
		},
		actionCancelOnclick : function($box) {
			var mode = $box.data('mode'), x = $box.attr('data-gs-x'), y = $box.attr('data-gs-y'),
			//
			savedData = componentModelManager.getDataFromID($box.attr('qb-component-id')),
			//
			H = componentModelManager.listComponent[$box.attr('qb-component-id')][WEB_PRO_H],
			//
			W = componentModelManager.listComponent[$box.attr('qb-component-id')][WEB_PRO_W];
			if (mode == 'GALLERY') {
			} else if (mode == 'ONE') {
				var $img = $box.find('img');
				if ($img.hasClass('new')) {
					var bxImg = $img.parents('.thumb-img-box');
					bxImg.attr('style', bxImg.attr('def-bg'));
					$img.hide();
				}
				if (typeof savedData['img'] != 'undefined')
					$img.attr('src', savedData['img'][0]).removeClass('cropbox-enable').css({
						'height' : '100%',
						'width' : '100%'
					});
				if (typeof $img.data('cropbox') != 'undefined')
					$img.data('cropbox').remove();
			} else {
				var i = 0;
				$.each($box.find('.qb-component').first().children('.box_thumb_img'), function(i) {
					var $img = $(this).find('img');
					if ($img.hasClass('new')) {
						bxImg = $img.parents('.thumb-img-box');
						bxImg.attr('style', bxImg.attr('def-bg'));
						$img.hide();
					}
					$img.attr('src', savedData['img'][i++]).removeClass('cropbox-enable').css({
						'height' : '100%',
						'width' : '100%'
					});

					if (typeof $img.data('cropbox') != 'undefined')
						$img.data('cropbox').remove();
				});

				// reset layout
				if ($box.attr('data-old-type') == 'new') {
					var oldLayout = '';
					if (mode == 'TWO' || mode == 'FOUR')
						oldLayout = 'landscape';
					else if (mode == 'THREE')
						oldLayout = 'portrait-2';
					$box.attr('data-layout', oldLayout);
				}
			}
			defSize = imageController.getDefaultSize(mode);
			$box.parents('.grid-stack').data('gridstack').update($box, x, y, W, H);
			// revert type where cancel click
			$box.removeClass('editting full').addClass($box.attr('data-old-type'));
			$box.removeAttr('data-old-type');
			$('.box_thumb_img.exist-img').removeClass('exist-img');
			web.endModeUploadImg();
		},
		rotateRight : function($boxPar) {
			if ($boxPar.data('mode') == 'TWO' || $boxPar.data('mode') == 'FOUR') {
				var oldLayout = $boxPar.attr('data-layout'),
				//
				newLayout = oldLayout == 'landscape' ? 'portrait' : 'landscape';
				$boxPar.attr('data-layout', newLayout);
			} else if ($boxPar.data('mode') == 'THREE') {
				var oldLayout = $boxPar.attr('data-layout'), newLayout;
				switch (oldLayout) {
				case 'landscape-1':
					newLayout = 'portrait-2';
					break;
				case 'landscape-2':
					newLayout = 'portrait-1';
					break;
				case 'portrait-1':
					newLayout = 'portrait-3';
					break;
				case 'portrait-2':
					newLayout = 'landscape-2';
					break;
				case 'portrait-3':
					newLayout = 'landscape-1';
					break;
				}
				$boxPar.attr('data-layout', newLayout);
			}
			// resize cropbox
			imageTool.updateImageCropSize($boxPar);
		},
		// resize image and reinit cropbox
		updateImageCropSize : function($elem) {
			var mode = $elem.data('mode'),
			//
			layout = $elem.attr('data-layout') || '',
			//
			gsW = Math.round($elem.width() / gridConfig.getCellWidth()),
			//
			gsH = Math.round($elem.height() / gridConfig.cell_height);

			// find class => hide outer btn edit
			if ($elem.data('mode') == 'ONE')
				if (gsW <= 10) {
					if (!$elem.hasClass('small-size'))
						$elem.addClass('small-size');
				} else
					$elem.removeClass('small-size');

			// find position component tool
			if ($elem.hasClass('small-size')) {
				var wS = ($elem.find('.function-center-z').width() + $elem.find('.thumb-img-tool').width()) - $elem.width();
				if (wS > 0) {
					var spaceX = $elem.attr('data-gs-x') * gridConfig.getCellWidth();
					if (wS >= spaceX)
						$elem.addClass('menu-pos-left');
					else
						$elem.removeClass('menu-pos-left');
				} else
					$elem.removeClass('menu-pos-left');
			} else
				$elem.removeClass('menu-pos-left');

			if (mode != 'GALLERY') {
				if (mode == 'ONE') {
					imageUtil.initCropbox($elem.find('img'), $elem.width(), $elem.height(), true);
				} else {
					var $childArr = $elem.find('.qb-component').first().children('.box_thumb_img');
					$.each($childArr, function() {
						var index = $(this).data('index'),
						//
						modeLayoutIndex = mode + '##' + layout + '##' + index,
						//
						ratio = imageUtil.getTrueRatioImage(modeLayoutIndex);
						imageUtil.initCropbox($(this).find('img'), $elem.width() * ratio['ratioW'], $elem.height() * ratio['ratioH'], true);
					});
				}
			}
		},
	},
	/*
	 * ANPH: imageBGUtil
	 * 
	 * BACKGROUND Controller
	 * 
	 */
	imageBGUtil = {
		targetBG : null,
		init : function() {
		},

		/*
		 * function($box : elem need set background, imgSrc : http source,
		 * type:{ G:gridbox, B: bigbox }, callBackSave : function after save)
		 */
		generateBGImage : function(target, imgSrc, type, callBackSave) {
			this.targetBG = target;
			var self = this, d = new Date(), n = d.getTime();
			self.stubId = n + imageUtil.rand_string();

			$('#baseBackground').append('<div id="' + self.stubId + '">');

			$divPsuede = $('#baseBackground #' + self.stubId);

			var top = target.offset().top + 1, left = target.offset().left + 1;
			web.goToModeUploadImg();
			$('.qb-ohhay-ajax-content').fadeIn();
			if (!$divPsuede.hasClass('existed-stub-bg')) {
				var contentHTML = '<img class="baseImgBG cropbox-enable need-effect" src="" style="z-index:99"/>';
				contentHTML += '<div class="background-edit-tool">';
				contentHTML += '	<div class="item btn-zoom-in">';
				contentHTML += '		<i class="fa fa-plus"></i>';
				contentHTML += '	</div>';
				contentHTML += '	<div class="item btn-zoom-out">';
				contentHTML += '		<i class="fa fa-minus"></i>';
				contentHTML += '	</div>';
				contentHTML += '	<div class="item btn-bg-effect">';
				contentHTML += '		<i class="fa fa-magic"></i>';
				contentHTML += '	</div>';
				contentHTML += '	<div class="item cutImage">';
				contentHTML += '		<i class="fa fa-check"></i>';
				contentHTML += '	</div>';
				contentHTML += '	<div class="item cropCancel">';
				contentHTML += '		<i class="fa fa-close"></i>';
				contentHTML += '	</div>';
				contentHTML += '</div>';
				$divPsuede.css({
					'top' : top,
					'left' : left,
					'position' : 'absolute'
				}).append(contentHTML).addClass('existed-stub-bg');
			}
			var $img = $('#' + self.stubId + '>.baseImgBG');
			$img.attr('src', imgSrc).on('load', function() {
				$('.qb-ohhay-ajax-content').fadeOut();
			}).on('error', function() {
				$('.qb-ohhay-ajax-content').fadeOut();
			});
			var w = target.outerWidth(), h = target.outerHeight();
			imageUtil.initCropbox($img, w, h, true);
			$img.css({
				width : 'auto',
				height : 'auto'
			});
			imageEffect.init();
			// ZOOM IN
			$('#' + self.stubId + ' .btn-zoom-in').unbind('click');
			$('#' + self.stubId + ' .btn-zoom-in').on('click', function(event) {
				$(this).parent().prev().find('.cropControls .cropZoomIn').trigger('click');
			});
			// ZOOM OUT
			$('#' + self.stubId + ' .btn-zoom-out').unbind('click');
			$('#' + self.stubId + ' .btn-zoom-out').on('click', function(event) {
				$(this).parent().prev().find('.cropControls .cropZoomOut').trigger('click');
			});
			// registered save action
			$('#' + self.stubId + ' .cutImage').unbind('click');
			$('#' + self.stubId + ' .cutImage').on('click', function(event) {
				// call save background for registered target
				self.saveCropboxBGNode(callBackSave, $img);
			});
			$('#' + self.stubId + ' .btn-bg-effect').unbind('click');
			$('#' + self.stubId + ' .btn-bg-effect').on('click', function(event) {
				$('#qb-dlg-img-efect').dialog('open');
				$($divPsuede).data('mode', 'BG');
				imageEffect.setImgNeedEffect($divPsuede);
			});
			// registered cancel action
			$('#' + self.stubId + ' .cropCancel').unbind('click');
			$('#' + self.stubId + ' .cropCancel').on('click', function(event) {
				util.confirmDialog(getLocalize("jsimc_title1"), function() {
					$divPsuede.remove();
					web.endModeUploadImg();
				});
			});
		},

		saveCropboxBGNode : function(callback, $img) {

			var self = this, crop = $img.data('cropbox'), url = $img.attr('src');
			if (typeof crop != 'undefined') {

				imageController.saveCropBGMap[self.stubId] = callback;
				imageController.elementNeedBGMap[self.stubId] = this.targetBG;
				// imageController.bgFilterMap[self.stubId] = this.targetBG.;
				if (!$('.qb-ohhay-ajax-content').is(":visible"))
					$('.qb-ohhay-ajax-content').show();
				imageController.socket.emit('cropImgAndSaveImageToS3', {
					type : 'BG',
					left : crop['result']['cropX'] * crop['percent'],
					top : crop['result']['cropY'] * crop['percent'],
					right : (crop['result']['cropX'] + crop['result']['cropW'] - 1) * crop['percent'],
					bot : (crop['result']['cropY'] + crop['result']['cropH'] - 1) * crop['percent'],
					percent : crop['percent'],
					deg : $img.attr('data-rotate'),
					url : url,
					siteid : webCreater.data.id,
					fo100 : $('#qb-userlogin-fo100').val(),
					region : $('#qb-input-region').val(),
					id : self.stubId
				});
				crop.disableDrag(true);
				web.endModeUploadImg();
				webUtils.removeBackground(this.targetBG);
				$img.css('z-index', '0').next().remove();
			}
		}
	},

	/*
	 * ANPH: galleryManager
	 */
	galleryManager = {
		option : {
			numberImgToView : 1,
			autoPlay : false,
			timeAutoPlay : 0,
			useNavigator : true,
			navigatorPosition : 'top',// mid,bot
			useThumnail : false,
			typeThumbnail : 'none',// image, orderList(square, circle),
			// unOrderList(square, circle),...
			thumbnailSize : 60
		},
		//
		addImageToGallery : function($boxImg, imgSrc) {
			var self = this;
			web.goToModeUploadImg();
			$('.qb-ohhay-ajax-content').fadeIn();
			// attr nhan dang de cancel tra lai
			$boxImg.attr('data-old-type', 'new');
			if ($boxImg.hasClass('new')) {// new mode
				var liGal = '<li class="need-effect" data-index="1" style="background-image: url(\'' + imgSrc + '\');">';
				liGal += '		<div class="gal-item-tool">';
				liGal += '			<span class="qb-img-tool replace"><i class="fa fa-refresh"/></span>';
				liGal += '			<span class="qb-img-tool remove"><i class="fa fa-close"/></span>';
				liGal += '		</div>';
				liGal += '</li>';
				$boxImg.find('ul.gallery-list').html(liGal);
				// append thumbnail-dotted list
				$boxImg.find('ul.thumbnail-list-dotted').html('<li class="active" data-index="1"></li>');
				// append thumbnail list
				var liThumb = '<li class="active loading" data-index="1" style="width:' + self['option']['thumbnailSize'] + 'px">';
				liThumb += '		<i class="fa fa-spinner fa-pulse"></i>';
				liThumb += '</li>';
				$boxImg.find('ul.thumbnail-list').html(liThumb);

				// set image to thumbnail
				self.generateThumbnailFromImageNode(imgSrc, function(newImgSrc) {
					// active will high light
					$boxImg.find('ul.thumbnail-list>li[data-index="1"]').removeClass('loading').addClass('active').siblings().removeClass('active');
					$boxImg.find('ul.thumbnail-list>li[data-index="1"]').addClass('active').css('background-image', 'url(\'' + newImgSrc + '\')');
				});
				$boxImg.removeClass('new').addClass('editting one');
			} else {// !new mode
				var newIndex = $boxImg.find('ul.gallery-list').children().length + 1;
				var liGal = '<li class="need-effect" data-index="' + newIndex + '" style="background-image: url(\'' + imgSrc + '\');">';
				liGal += '		<div class="gal-item-tool">';
				liGal += '			<span class="qb-img-tool replace"><i class="fa fa-refresh"/></span>';
				liGal += '			<span class="qb-img-tool remove"><i class="fa fa-close"/></span>';
				liGal += '		</div>';
				liGal += '</li>';
				$boxImg.find('ul.gallery-list').append(liGal);
				// append thumbnail-dotted list
				$boxImg.find('ul.thumbnail-list-dotted>li.active').removeClass('active');
				$boxImg.find('ul.thumbnail-list-dotted').append('<li class="active" data-index="' + newIndex + '"></li>');
				// append thumbnail list
				var liThumb = '<li class="active loading" data-index="' + newIndex + '" style="width:' + self['option']['thumbnailSize'] + 'px">';
				liThumb += '		<i class="fa fa-spinner fa-pulse"></i>';
				liThumb += '</li>';
				$boxImg.find('ul.thumbnail-list').append(liThumb);
				// set image to thumbnail
				self.generateThumbnailFromImageNode(imgSrc, function(newImgSrc) {
					// active will high light
					$boxImg.find('ul.thumbnail-list>li[data-index="' + newIndex + '"]').removeClass('loading').addClass('active').siblings().removeClass('active');
					$boxImg.find('ul.thumbnail-list>li[data-index="' + newIndex + '"]').addClass('active').css('background-image', 'url(\'' + newImgSrc + '\')');
					self.registerEditTool($boxImg);
				});
				$boxImg.removeClass('one');
				/* update counter */
				var currInum = $boxImg.find('.number').html().split('/');
				// refresh currInum : index/count
				$boxImg.find('.number').html((parseInt(currInum[1]) + 1) + '/' + (parseInt(currInum[1]) + 1))
				// refresh position
				self.comeToNewPositionInThisGal($boxImg, newIndex, 'transition5');
			}
			self.registerEditTool($boxImg);
		},
		comeToNewPositionInThisGal : function($boxImg, newIndex, transition) {
			var currInum = $boxImg.find('.number').html().split('/');
			// counter refresh
			$boxImg.find('.number').html(newIndex + '/' + parseInt(currInum[1]));
			// change speed
			$boxImg.find('.gallery-list').removeClass('transition5 transition2').addClass(transition).css('right', (newIndex - 1) + '00%');
			// active thumbnail 5 this margin + 5 last margin

			// last item newIndex === parseInt(currInum[1])
			var useIndex = newIndex === parseInt(currInum[1]) ? newIndex : newIndex + 1,
			//
			thisThumbPos = ((parseInt(this['option']['thumbnailSize']) + 5) * useIndex + 5) / $boxImg.width(),
			//
			pos = (thisThumbPos - 1) * 100;
			// update position thumbnail list
			if (pos > 0) {
				$('.thumbnail-list').css('left', '-' + pos + '%');
			} else
				$('.thumbnail-list').css('left', 0);
			$('.thumbnail-list>li[data-index="' + newIndex + '"]').addClass('active').siblings().removeClass('active');
			$('.thumbnail-list-dotted>li[data-index="' + newIndex + '"]').addClass('active').siblings().removeClass('active');

			// chosen ready filter
			$('.gallery-list>li[data-index="' + newIndex + '"]').addClass('choosed').siblings().removeClass('choosed');
			imageEffect.imgNeedEffect = $('.gallery-list>li[data-index="' + newIndex + '"]');
			imageEffect.evenListener();
			// reset timer
			if ($boxImg.hasClass('auto-play')) {
				clearInterval(self.autoPlayInter);
				self.autoPlayInter = setInterval(function() {
					$boxImg.find('.gal-controller>.tool.next').trigger('click');
				}, 5000);
			}
		},
		registerFormOption : function($boxImg) {
			// fullScreen
			$('.gallery-panel #fullScreen').prop('checked', $boxImg.find('.fullscreen-action').hasClass('use-fullscreen'));
			$('.gallery-panel #fullScreen').unbind("change");
			$('.gallery-panel #fullScreen').on("change", function() {
				if ($(this).prop("checked") === true) {
					$(this).val("Y");
					$boxImg.find('.fullscreen-action').addClass('use-fullscreen');
				} else {
					$(this).val("N");
					$boxImg.find('.fullscreen-action').removeClass('use-fullscreen');
				}
			});
			// thumbnail
			$('.gallery-panel #thumbnail').prop('checked', $boxImg.hasClass('use-thumbnail'));
			$('.gallery-panel #thumbnail').unbind("change");
			$('.gallery-panel #thumbnail').on("change", function() {
				if ($(this).prop("checked") === true) {
					$(this).val("Y");
					$boxImg.addClass('use-thumbnail');
				} else {
					$(this).val("N");
					$boxImg.removeClass('use-thumbnail');
				}
			});
			// fill
			$('.gallery-panel #fill').prop('checked', $boxImg.find('ul.gallery-list').hasClass('fill'));
			$('.gallery-panel #fill').unbind("change");
			$('.gallery-panel #fill').on("change", function() {
				if ($(this).prop("checked") === true) {
					$(this).val("Y");
					$boxImg.find('ul.gallery-list').addClass('fill');
				} else {
					$(this).val("N");
					$boxImg.find('ul.gallery-list').removeClass('fill');
				}
			});
			// autoPlay
			$('.gallery-panel #autoPlay').prop('checked', $boxImg.hasClass('auto-play'));
			$('.gallery-panel #autoPlay').unbind("change");
			$('.gallery-panel #autoPlay').on("change", function() {
				if ($(this).prop("checked") === true) {
					$(this).val("Y");
					$boxImg.addClass('auto-play');
				} else {
					$(this).val("N");
					$boxImg.removeClass('auto-play');
					clearInterval(self.autoPlayInter);
				}
			});
			// navigator
			$('.gallery-panel #navigator').prop('checked', $boxImg.find('.gal-controller').hasClass('use-navigator'));
			$('.gallery-panel #navigator').unbind("change");
			$('.gallery-panel #navigator').on("change", function() {
				if ($(this).prop("checked") === true) {
					$(this).val("Y");
					$boxImg.find('.gal-controller').addClass('use-navigator');
				} else {
					$(this).val("N");
					$boxImg.find('.gal-controller').removeClass('use-navigator');
				}
			});
			// counter
			$('.gallery-panel #counter').prop('checked', $boxImg.hasClass('use-counter'));
			$('.gallery-panel #counter').unbind("change");
			$('.gallery-panel #counter').on("change", function() {
				if ($(this).prop("checked") === true) {
					$(this).val("Y");
					$boxImg.addClass('use-counter');
				} else {
					$boxImg.removeClass('use-counter');
					$(this).val("N");
				}
			});
		},
		registerEditTool : function($boxImg) {
			self = this;
			self.registerNavigatorVsTool($boxImg);
			// replace image in this position selected
			$boxImg.find('.gallery-list>li>.gal-item-tool span.replace').unbind('click');
			$boxImg.find('.gallery-list>li>.gal-item-tool span.replace').on('click', function() {
				var $self = $(this).closest('li');
				imageAlbum.open({
					callBack : function(result) {
						$self.css('background-image', 'url(\'' + result.data + '\')');
						var currIndex = $self.attr('data-index');
						$boxImg.find('ul.thumbnail-list>li[data-index="' + currIndex + '"]').addClass('loading');
						// set image to thumbnail
						self.generateThumbnailFromImageNode(result.data, function(newImgSrc) {
							$boxImg.find('ul.thumbnail-list>li[data-index="' + currIndex + '"]').removeClass('loading').addClass('active').css('background-image', 'url(\'' + newImgSrc + '\')');
							self.registerEditTool($boxImg);
						});
					}
				});
			});
			// remove image in this position selected
			$boxImg.find('.gallery-list>li>.gal-item-tool span.remove').unbind('click');
			$boxImg.find('.gallery-list>li>.gal-item-tool span.remove').on('click', function() {
				var $self = $(this).closest('li');
				util.confirmDialog(getLocalize("jsimu_title1"), function() {
					var currIndex = $self.attr('data-index'),
					//
					$thumbImg = $boxImg.find('ul.thumbnail-list>li[data-index="' + currIndex + '"]'),
					//
					$number = $self.closest('.qb-component').find('.gal-controller>.number');
					// img list
					$self.nextAll().each(function() {
						var newIndex = parseInt($(this).attr('data-index')) - 1;
						$(this).attr('data-index', newIndex);
					});
					// thumb img list
					$thumbImg.nextAll().each(function() {
						var newIndex = parseInt($(this).attr('data-index')) - 1;
						$(this).attr('data-index', newIndex);
					});
					// update number
					var curNum = $number.html().split('/');
					// last image or only 1 image in this gallery
					if (parseInt(curNum[0]) === parseInt(curNum[1])) {
						if (parseInt(curNum[0]) === 1) {// only 1 in gallery
							var li = '<li style="background-image: url(\'' + imageController.defaultImg(1) + '\');" data-index="0"></li>';
							$self.parent().append(li);
							$boxImg.addClass('new').removeClass('editting');
						} else {// last image
							$number.html((parseInt(curNum[0]) - 1) + '/' + (parseInt(curNum[1]) - 1));
							$thumbImg.prev().trigger('click');
						}
					} else {// this not last
						$number.html(curNum[0] + '/' + (parseInt(curNum[1]) - 1));
						$thumbImg.next().addClass('active');
					}
					setTimeout(function() {
						$self.remove();
						$thumbImg.remove();
						$boxImg.find('ul.thumbnail-list-dotted>li').last().remove();
					}, 100);
				});
			});
		},

		registerNavigatorVsTool : function($boxImg, from) {
			var self = this;
			// auto play
			if (from === 'pub') {
				if ($boxImg.hasClass('auto-play')) {
					self.autoPlayInter = setInterval(function() {
						$boxImg.find('.gal-controller>.tool.next').trigger('click');
					}, 5000);
				}
				// fullscreen click
				$boxImg.find('.fullscreen-action.use-fullscreen').unbind('click');
				$boxImg.find('.fullscreen-action.use-fullscreen').on('click', function() {
					if ($boxImg.hasClass('fullscreen')) {
						$boxImg.removeClass('fullscreen');
						$(this).find('i').removeClass('fa-compress').addClass('fa-expand');
						$('html').removeAttr('style');
					} else {
						$boxImg.addClass('fullscreen');
						$('html').css('overflow', 'hidden');
						$boxImg.addClass('transition5');
						$(this).find('i').removeClass('fa-expand').addClass('fa-compress');
					}
					setTimeout(function() {
						$boxImg.removeClass('transition5');
					}, 1000);
				});
			}
			// previous in gallery
			$boxImg.find('.tool.prev').unbind('click');
			$boxImg.find('.tool.prev').on('click', function() {
				var currInum = $boxImg.find('.number').html().split('/'), newIndex, transition;
				if (parseInt(currInum[0]) - 1 > 0) {// min 1
					newIndex = parseInt(currInum[0]) - 1;
					transition = 'transition5';
				} else {// prev to last
					newIndex = parseInt(currInum[1]);
					transition = 'transition2';
				}
				self.comeToNewPositionInThisGal($boxImg, newIndex, transition);
			});
			// next in gallery
			$boxImg.find('.tool.next').unbind('click');
			$boxImg.find('.tool.next').on('click', function() {
				var currInum = $boxImg.find('.number').html().split('/'), newIndex;
				// max = gallery length
				if ((parseInt(currInum[0]) + 1) <= parseInt(currInum[1])) {
					newIndex = parseInt(currInum[0]) + 1;
					transition = 'transition5';
				} else {// next to 1
					newIndex = 1;
					transition = 'transition2';
				}
				self.comeToNewPositionInThisGal($boxImg, newIndex, transition);
			});
			// thumbnail img && thumbnail dot click
			$boxImg.find('.thumbnail-list>li, .thumbnail-list-dotted>li').unbind('click');
			$boxImg.find('.thumbnail-list>li, .thumbnail-list-dotted>li').on('click', function() {
				var currInum = $boxImg.find('.number').html().split('/'), newIndex = parseInt($(this).attr('data-index'));
				self.comeToNewPositionInThisGal($boxImg, newIndex, 'transition2');
			});
		},
		generateThumbnailFromImageNode : function(imgSrc, callback) {
			var d = new Date(), n = d.getTime(), id = n + imageUtil.rand_string();
			imageController.sliderThumbCallbackMap[id] = callback;
			imageController.socket.emit('makeThumbnailSlider', {
				rId : imageController.rId,
				siteid : $("#qb-input-pe400").val(),
				fo100 : $('#qb-userlogin-fo100').val(),
				region : $('#qb-input-region').val(),
				trial : web.TRIAL,
				url : imgSrc,
				id : id
			});
		},
		generateThumbnailFromImage : function(imgSrc, callback) {
			var self = this, img = new Image(), imgSize = self['option']['thumbnailSize'] * 2;
			// img.crossOrigin = "Anonymous"; // cross support
			img.setAttribute('crossOrigin', 'anonymous');
			img.onload = function() {
				var canvas = document.createElement("canvas"), ctx = canvas.getContext("2d"),
				// natural size
				naW = img.naturalWidth, naH = img.naturalHeight,
				// calc width and Horizontal position
				newW = naW < naH ? imgSize : parseInt((naW / naH) * imgSize), posW = parseInt((newW - imgSize) / 2),
				// calc width and vertical position
				newH = naH < naW ? imgSize : parseInt((naH / naW) * imgSize), posH = parseInt((newH - imgSize) / 2);
				// resize image follow canvas
				canvas.setAttribute('width', imgSize);
				canvas.setAttribute('height', imgSize);
				ctx.drawImage(img, posW * -1, posH * -1, newW, newH);
				// save to S3
				var formData = new FormData();
				formData.append('webId', webCreater.data.id);
				formData.append('imgBase64', canvas.toDataURL());
				formData.append('src', 'image');
				self.saveBase64toS3ReturnUrl(formData, callback);
			};
			img.src = imgSrc;
		},
		saveBase64toS3ReturnUrl : function(formData, callBack) {
			$.ajax({
				type : "POST",
				url : encodeUrl("myCollectionBean.upLoad"),
				cache : false,
				contentType : false,
				processData : false,
				data : formData,
				success : function(response) {
					if (response.status == AJAX_SUCESS) {
						// add img to galery here
						callBack(response['result']['thumb']);
					} else {
						showGrowlMessageError();
					}
					web.endModeUploadImg();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		// save amazon -> add BG to target box
		saveCropboxGalleryIMG : function(callBack) {
			var formData = new FormData(),
			//
			crop = $('#baseImgGallery').data('cropbox'), base64,
			//
			contentType = imageUtil.getContentTypeFromIMG($('#baseImgGallery'));
			base64 = crop.getDataURL(contentType);
			formData.append('webId', webCreater.data.id);
			formData.append('imgBase64', base64);
			formData.append('src', 'image');
			this.saveBase64toS3ReturnUrl(formData, callBack);
		}
	},
	/*
	 * ANPH: imageEffect
	 */
	imageEffect = {
		imgNeedEffect : null,
		FILTER_VALS : {},
		init : function() {
			$('#qb-dlg-img-efect').dialog({
				autoOpen : false,
				modal : false,
				open : function() {

				},
				close : function() {
					$('.grid-stack-item-content .box_thumb_img.ready-effect.choosed').removeClass('choosed');
					$('.grid-stack-item-content .box_thumb_img.ready-effect').removeClass('ready-effect');

					$.each($('#lstEffect').children(), function() {
						$range = $(this).find('.effect-attr');
						$range.val($range.attr('min'));
					});
				}
			});
			;
		},
		setImgNeedEffect : function($box) {
			var self = this, mode = $box.data('mode');
			if (mode == 'GALLERY') {
				$box.find('ul.gallery-list>li').first().addClass('choosed');
				self.imgNeedEffect = $box.find('ul.gallery-list>li').first();
				self.evenListener();
			} else if (mode == 'ONE' || mode == 'BG') {
				$('.all-effect').removeClass('show');
				self.imgNeedEffect = $box.find('.need-effect');
				self.evenListener();
			} else {// multi img
				$('.all-effect').addClass('show');
				$box.find('.box_thumb_img').addClass('ready-effect');
				$box.find('.box_thumb_img.number_img_1').addClass('choosed');
				self.imgNeedEffect = $box.find('.box_thumb_img.number_img_1').find('img.need-effect');
				self.evenListener();
				$(document).on('click', '.box_thumb_img.ready-effect', function() {
					$(this).addClass('choosed').siblings().removeClass('choosed');
					self.imgNeedEffect = $(this).find('img.need-effect');
					self.evenListener();
				});
			}
		},
		evenListener : function() {
			var self = this, filterAttr, isFirefox = typeof InstallTrigger !== 'undefined';
			// get current filter
			if (isFirefox) {
				filterAttr = self.imgNeedEffect[0].style.filter || '';
			} else {
				filterAttr = self.imgNeedEffect[0].style.webkitFilter || '';
			}
			if (filterAttr.trim().length > 0) {
				var filterAr = filterAttr.length > 0 ? filterAttr.split(' ') : [];
				for ( var i in filterAr) {
					if (filterAr[i].indexOf('(') > -1) {
						var itemAr = filterAr[i].split('('),
						//
						attrName = itemAr[0],
						//
						attrVal = parseFloat(itemAr[1]);
						self.FILTER_VALS[itemAr[0]] = itemAr[1].slice(0, -1);
						$('#comp-effect').find('.effect-attr[data-name="' + attrName + '"]').val(attrVal);
					}
				}
			} else {
				$.each($('#lstEffect').children(), function() {
					$range = $(this).find('.effect-attr');
					$range.val($range.attr('min'));
				});
			}

			$(document).on('input', '.effect-attr', function() {
				var effectName = $(this).data('name'), outputEffect;
				if (effectName === 'blur')
					outputEffect = this.value + 'px';
				else if (effectName === 'hue-rotate')
					outputEffect = this.value + 'deg';
				else
					outputEffect = this.valueAsNumber;

				self.setFilterAttr(effectName, outputEffect);
			});
			// Accept and close filter panel
			$(document).on('click', '.btnOkEffect', function() {
				$('#qb-dlg-img-efect').dialog('close');
			});
			// Reset filter
			$(document).on('click', '.btnResetEffect', function() {
				self.reset();
			});
			// change Apply to all
			$(document).on('change', '#applyEfectToAll', function() {
				self.onChangeApplyToAll();
			});
		},
		onChangeApplyToAll : function() {
			var self = this;
			if ($('#applyEfectToAll').is(':checked')) {
				$.each($('.ready-effect.choosed').siblings('.ready-effect'), function() {
					self.render($(this).find('.need-effect'));
				});
			}
		},
		setFilterAttr : function(filter, value) {
			var self = this;

			// opacity value as small as opacity
			if (filter === 'opacity')
				value = Math.abs(value);

			self.FILTER_VALS[filter] = typeof value == 'number' ? Math.round(value * 10) / 10 : value;
			if (value == 0 || (typeof value == 'string' && value.indexOf('0') == 0)) {
				delete self.FILTER_VALS[filter];
			}
			if ($('#applyEfectToAll').is(':checked'))
				$.each($('.ready-effect.choosed').siblings('.ready-effect'), function() {
					self.render($(this).find('.need-effect'));
				});
			self.render();
		},

		render : function(targetImg) {
			var self = this, vals = [];
			targetImg = targetImg || self.imgNeedEffect;
			//
			Object.keys(self.FILTER_VALS).sort().forEach(function(key, i) {
				vals.push(key + '(' + self.FILTER_VALS[key] + ')');
			});
			var val = vals.join(' ');
			targetImg[0].style['-webkit-filter'] = val;
			targetImg[0].style['filter'] = val;
			targetImg.attr('data-filter', val);
			targetImg.removeClass('has-effect').addClass('has-effect');
		},
		reset : function() {
			var self = this;
			self.FILTER_VALS = {};
			if ($('#applyEfectToAll').is(':checked'))
				$.each($('.ready-effect.choosed').siblings('.ready-effect'), function() {
					self.render($(this).find('.need-effect'));
				});
			self.render();

			var ranges = document.getElementById('comp-effect').querySelectorAll('input[type="range"]');
			for (var i = 0, r; r = ranges[i]; i++) {
				r.value = r.min;
			}
		}
	}
}());