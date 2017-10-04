$(function() {
	avatarImage = {
		// locnt
		$image_edit_avarta : '',
		isfile : false,
		validFileType : ".png",
		fileType : 'image/jpeg',
		init : function() {
			
			this.eventListener();
		},
		eventListener : function() {
			
			// su kien cho chon file hinh
			$('#dialog-image-avarta .qb-input-img-upload-avarta').on('change',
					this.chooseFile);
			
			// su kien cat file hinh
			$('#container-crop-gallery-item-edit-image-avarta  #btnCrop').on(
					'click', this.cropImage);
			
			// su kien phong to anh
			$('#container-crop-gallery-item-edit-image-avarta #btnZoomIn').on(
					'click', this.zoomIn);	
			
			// su kien thu nho anh
			$('#container-crop-gallery-item-edit-image-avarta #btnZoomOut').on(
					'click', this.zoomOut);
			
			//xoay trai
			$('#container-crop-gallery-item-edit-image-avarta #rotateLeft').on(
					'click', this.rotateLeft);
			
			//xoay phai
			$('#container-crop-gallery-item-edit-image-avarta #rotateLeft').on(
					'click', this.rotateRight);
		},
		rotateRight: function(){
			avatarImage.$image_edit_avarta.cropper("rotate", 90);
		},
		rotateLeft: function(){
			avatarImage.$image_edit_avarta.cropper("rotate", -90);
		},
		zoomOut: function(){
			avatarImage.$image_edit_avarta.cropper("zoom", -0.1);
		},
		zoomIn: function(){
			avatarImage.$image_edit_avarta.cropper("zoom", 0.1);
		},
		cropImage : function() {	
			try{
				var currentLanguage = $('#qb-input-currentLanguage').val();
				var extend = $('#qb-input-extend').val();
				var cv905 = $('#dialog-image-avarta #inputcv905ImageHome').val();
            	var pc900 = $('#dialog-image-avarta #inputpc900ImageHome').val();
            	var cv902 = $('#dialog-image-avarta #inputcv902ImageHome').val();
            	var imgBase64 = avatarImage.$image_edit_avarta.cropper("getDataURL", avatarImage.fileType, 0.5);
            	var data = new FormData();
            	data.append('avartaBase64', imgBase64);
            	data.append('pc900', pc900);
            	data.append('cv902', cv902);
            	data.append('cv905', cv905);
            	data.append('languageCode', currentLanguage);
            	if(imgBase64=='')
            		return;
            	$.ajax({
            		type: "POST",
            		url: encodeUrl("setting.saveAvarta"),
            		cache: false,
            		contentType: false,
            		processData: false,
            		data: data,
            		success: function (response) {
                    if (response.status == AJAX_SUCESS) {
                    	$("#dialog-image-avarta").dialog("close");            			
            			$('.sett-tab-account-info-img #image_account').attr('src',imgBase64);
            			console.log("UPDATE SUCESS");
            			$('.wrapper-nav .nav-avatar button .fix-padding img').attr('src',imgBase64);
                    } else {
                    	
                    }
                },
                error: function (error) {
//                    $("#qb-dialog-message").jsOhhayDialog();
                }
            });
			}catch(e){
				console.log(e);
				//util.messageDialog(getLocalize("choose_image"));
			}

		},
		chooseFile : function(evt) {
			
			var input = $(evt.currentTarget);
			var file = input[0].files[0];

			var extension = file.name.substring(file.name.lastIndexOf('.'));

			avatarImage.validFileType = ".png";
			avatarImage.fileType = 'image/jpeg';

			if (extension.toLowerCase() === avatarImage.validFileType) {
				avatarImage.fileType = 'image/png';
			}

			$('#container-crop-gallery-item-edit-image-avarta').css('display',
					'block');
			$('.img-cropped').hide();
			$('#dialog-image-avarta .qb-form-action').show();

			isfile = false;

			var $fileupload = $(this);

			var reader = new FileReader();

			avatarImage.$image_edit_avarta = $(".cropper-edit-img-avarta");

			console.log(avatarImage.$image_edit_avarta);
			
			avatarImage.$image_edit_avarta
					.cropper({
						guides: true,
						  highlight: false,
						  dragCrop: true,
						  movable: true,
						  resizable: true,
						  autoCrop: true,
						 // background:true,
						data : {
							x : $('.eg-wrapper').width() / 2,
							y : $('.eg-wrapper').height() / 2,
							width : 90,
							height : 90

						},
						built : function() {
							var img = avatarImage.$image_edit_avarta.cropper(
									"getDataURL", avatarImage.fileType, 0.5);

							$('#container-crop-gallery-item-edit-image-avarta .img-base64-edit-img-avarta>input[type="hidden"]')
									.val(img);
						},
						done : function() {
							var img = avatarImage.$image_edit_avarta.cropper(
									"getDataURL", avatarImage.fileType, 0.5);

							$('#container-crop-gallery-item-edit-image-avarta .img-base64-edit-img-avarta>input[type="hidden"]')
									.val(img);
						},
						dragend : function() {
							var img = avatarImage.$image_edit_avarta.cropper(
									"getDataURL", avatarImage.fileType, 0.5);

							$(
									'#container-crop-gallery-item-edit-image-avarta .img-base64-edit-img-avarta>input[type="hidden"]')
									.val(img);
						}
					});

			reader.onload = function(e) {
				avatarImage.$image_edit_avarta.cropper("replace",
						e.target.result);
			}

			var file = this.files;
			window.setTimeout(function() {
				reader.readAsDataURL(file[0]);
				this.files = [];

			}, 600);

			window.setTimeout(function() {

				avatarImage.$image_edit_avarta.cropper("setAspectRatio", 1);

				avatarImage.$image_edit_avarta.cropper('setData', {
					width : 180,
					height : 180

				});

				$(window).trigger('resize');

			}, 2000);

			$('#dialog-image-avarta').children().find('.action').show();

			
			//web.showDialogImg(true);
			
		}
	};
});