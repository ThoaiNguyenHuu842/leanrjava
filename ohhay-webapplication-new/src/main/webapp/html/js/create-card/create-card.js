/**
 * @author ThienND created Jan 30, 2016
 */
(function() {
	createCard = {
		tempId : 0,
		cardType : 4,
		cardPath : "chucxuan",
		currentTemplateId : 0,// id template dang slide, khoi tao khi load
								// list
		init : function(cardType, cardPath) {
			if (cardType)
				createCard.cardType = cardType;
			if (cardPath)
				createCard.cardPath = cardPath;
			this.loadListTemplate();
			createCard.eventListener();
		},
		eventListener : function() {
			var self = this;
			$('.card-content')
			$(document).on('keydown', '#qb-dlg-card-info .card-content', function() {
				var count = 220 - $(this).val().length;
				$('#qb-dlg-card-info .card-content-tip').html(count + ' ký tự');
			});

			/*
			 * btn-choose-card
			 */
			$(document).on('click', '.btn-choose-card', function() {
				// alert(createCard.currentTemplateId);
				$('.data_evotemplate .item[templid="' + createCard.currentTemplateId + '"] img').trigger('click');
			});
			/*
			 * on click template item
			 */
			$(document).on('click', '.data_evotemplate .item img', function() {
				var tempId = $(this).parents('.item').attr('templid');
				createCard.openDialogInputCard();
				createCard.tempId = tempId;
				$('.card-content').val($(this).parents('.item').attr('ev406'));
				var count = 220 - $('.card-content').val().length;
				$('#qb-dlg-card-info .card-content-tip').html(count + ' ký tự');
			});
			/*
			 * on click submit create card
			 */
			$(document).on('click', '#qb-dlg-card-info .btn-done-create-card', function() {
				createCard.create();
			});
			/*
			 * on change click upload background card
			 */
			$(document).on('click', '#qb-dlg-card-info .btn-upload-img-card', function() {
				$('#qb-dlg-card-info .input-upload-card-background').trigger('click');
			});
			$('#qb-dlg-card-info .input-upload-card-background').on('change', function(event) {
				var reader = new FileReader(), img = document.createElement("img"), canvas = document.createElement("canvas");
				reader.onload = function(e) {
					self.realSrc = img.src = e.target.result;
				}
				reader.readAsDataURL(this.files[0]);
				// img.crossOrigin = "Anonymous"; // cross support
				img.onload = function() {
					var ctx = canvas.getContext("2d");
					ctx.drawImage(img, 0, 0);
					var imgSize = 1350, width = img.width, height = img.height, ctx = canvas.getContext("2d");
					// calc width and Horizontal position
					var newW = width < height ? imgSize : parseInt((width / height) * imgSize), posW = parseInt((newW - imgSize) / 2),
					// calc width and vertical position
					newH = height < width ? imgSize : parseInt((height / width) * imgSize), posH = parseInt((newH - imgSize) / 2);
					// resize image follow canvas
					canvas.setAttribute('width', imgSize);
					canvas.setAttribute('height', imgSize);
					ctx.drawImage(img, posW * -1, posH * -1, newW, newH);

					var base64 = canvas.toDataURL(imageUtil.getContentTypeFromIMG($(img)));
					$('.panel-img-card-preview>img').attr('src', base64);
					$('.panel-img-card-preview').show();
					$('.btn-rotate-img-card').show();
					$('#qb-dlg-card-info .img-align').show();
				}
			});

			$('.btn-rotate-img-card').unbind('click');
			$('.btn-rotate-img-card').on('click', function() {
				var $img = $('.panel-img-card-preview>img');
				self.rotateImage($img);
			});

			$('#qb-dlg-card-info .img-align>span').unbind('click');
			$('#qb-dlg-card-info .img-align>span').on('click', function() {
				self.reCropImgWithPos($(this).attr('class').trim());
			});

		},

		rotateImage : function($img) {
			var canvas = document.createElement("canvas"),
			//
			ctx = canvas.getContext("2d"),
			//
			img = new Image();
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
			};
			img.src = $img[0].src;
		},

		reCropImgWithPos : function(pos) {
			var img = document.createElement("img"), canvas = document.createElement("canvas");
			img.onload = function() {
				var ctx = canvas.getContext("2d");
				ctx.drawImage(img, 0, 0);
				var imgSize = 1350, width = img.width, height = img.height, ctx = canvas.getContext("2d");
				// calc width and Horizontal position
				var newW = width < height ? imgSize : parseInt((width / height) * imgSize),
				// calc width and vertical position
				newH = height < width ? imgSize : parseInt((height / width) * imgSize),
				// postion
				posW, posH;
				if (pos == 'left') {
					posW = 0;
					posH = 0;
				} else if (pos == 'center') {
					posW = parseInt((newW - imgSize) / 2);
					posH = parseInt((newH - imgSize) / 2);
				} else if (pos == 'right') {
					posW = parseInt((newW - imgSize));
					posH = parseInt((newH - imgSize));
				}
				// resize image follow canvas
				canvas.setAttribute('width', imgSize);
				canvas.setAttribute('height', imgSize);
				ctx.drawImage(img, posW * -1, posH * -1, newW, newH);
				var base64 = canvas.toDataURL(imageUtil.getContentTypeFromIMG($(img)));
				$('.panel-img-card-preview>img').attr('src', base64);
			}
			img.src = this.realSrc;
		},
		/*
		 * open dialog input card
		 */
		openDialogInputCard : function() {
			if (!createCard.inited) {
				$('#qb-dlg-card-info').webDialog(450);
				createCard.inited = true;
			}
			$('#qb-dlg-card-info').dialog("open");
		},
		/*
		 * load list template
		 */
		loadListTemplate : function() {
			$.ajax({
				url : encodeUrl("evoTemplateBean.loadListTemplateAdvanced"),
				data : {
					en404 : createCard.cardType
				},
				success : function(response) {
					var htmlString = "";
					if (response.result && response.result.length > 0) {
						createCard.currentTemplateId = response.result[response.result.length - 1].id;
						for (index = response.result.length - 1; index >= 0; index--) {
							var item = response.result[index];
							htmlString += '<div ev406="' + item.ev406 + '" templid="' + item.id + '" class="item"><img src="' + item.ev403 + '"></div>';
						}
						$(".data_evotemplate").html(htmlString);
						setTimeout(function() {
							$(".data_evotemplate").owlCarousel({
								navigation : true, // Show next and prev
								// buttons
								slideSpeed : 300,
								paginationSpeed : 400,
								singleItem : true,
								navigationText : [ "Trước", "Tiếp" ],
								afterMove : function(elem) {
									var current = this.currentItem;
									createCard.currentTemplateId = elem.find(".owl-item").eq(current).find('.item').attr('templid');
								}
							});
							$(".data_evotemplate").show();
						}, 100);
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * create card
		 */
		create : function() {
			if ($('.card-content').val().trim().length == 0)
				util.messageDialog("Vui lòng nhập lời chúc");
			else {
				var formData = new FormData();
				formData.append('templateId', createCard.tempId);
				formData.append('cardContent', $('.card-content').val());
				var imgBase64 = '';
				if ($('.panel-img-card-preview>img').attr('src'))
					imgBase64 = $('.panel-img-card-preview>img').attr('src');
				formData.append('imgBase64', imgBase64);
				$.ajax({
					type : "POST",
					url : encodeUrl("createCardBean.create"),
					cache : false,
					contentType : false,
					processData : false,
					data : formData,
					success : function(response) {
						if (response.status == 'SUCCESS') {
							window.location = util.contextPath() + "/" + createCard.cardPath + "-evo-site-" + response.result;
						} else
							util.messageDialog(response.result[0].defaultMessage);
					},
					error : function(e) {
						showGrowlMessageAjaxError();
					}
				});
			}
		}
	}
}());