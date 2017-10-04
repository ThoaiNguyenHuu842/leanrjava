/**
 * @author ThoaiNH
 * create Jan 5, 2016
 */
(function() {
	settingSEO = {
		init : function() {
			settingSEO.eventListenerNew();
		},
		eventListenerNew : function() {
			$(document).on('click','.panel-seo .btn-save-seo', function(){
				settingSEO.save();
			});
			$(document).on('click','.panel-seo .feauture-image', function(){
				$('.panel-seo .upload_seo_thumb').trigger('click');
			});
			$('.panel-seo .upload_seo_thumb').on('change', function(event) {
				var input = $('.panel-seo .upload_seo_thumb');
				var reader = new FileReader();
				reader.onload = function(e) {
					var base64 = e.target.result;
					$('.panel-seo .upload_seo_thumb').attr('src',base64);
					$('.qb-setting-input-panel .feauture-image').css('background-image', 'url(' + base64 + ')');
				}
				reader.readAsDataURL(this.files[0]);
			});
			$(document).on('click','.panel-seo .fav-icon', function(){
				$('.panel-seo .upload_seo_fav_icon').trigger('click');
			});
			$('.panel-seo .upload_seo_fav_icon').on('change', function(event) {
				var input = $('.panel-seo .upload_seo_fav_icon');
				var reader = new FileReader();
				reader.onload = function(e) {
					var base64 = e.target.result;
					$('.panel-seo .upload_seo_fav_icon').attr('src',base64);
					$('.qb-setting-input-panel .fav-icon').css('background-image', 'url(' + base64 + ')');
				}
				reader.readAsDataURL(this.files[0]);
			});
			//	total character
			$(document).on('keydown click keyup','.qb-setting-input-panel textarea.seo-description',function(e){
				var count = $(this).val().length;
				var htmltotal='<label>'+count+'/180</label><span>character</span>';
				$('.text-limit-description .content').html(htmltotal);
				if(count > 180){
					$('.text-limit-description .content').html('<span style="color:red; font-size:16px ">'+getLocalize('isseo_title2')+'</span>');
				}
				
			});
		},
		/*
		 * on
		 */
		save : function() {
			var imgBase64 = $('.panel-seo .upload_seo_thumb').attr('src')?$('.panel-seo .upload_seo_thumb').attr('src'):'';
			var imgBase64Fav = $('.panel-seo .upload_seo_fav_icon').attr('src')?$('.panel-seo .upload_seo_fav_icon').attr('src'):'';
			var title =  $('.panel-seo .seo-title').val();
			var description =  $('.panel-seo .seo-description').val();
			var trackingScript =  $('.panel-seo .seo-tracking-script').val();
			var formData = new FormData();
            formData.append('webId', $('#current-web-id').val());
            formData.append('title', title);
            formData.append('description', description);
            formData.append('googleTrackingScript', trackingScript);
            formData.append('imgBase64', imgBase64);
            formData.append('imgBase64Fav', imgBase64Fav);
            $.ajax({
                type: "POST",
                url: encodeUrl("seo.save"),
                cache: false,
                contentType: false,
                processData: false,
                data: formData,
                success: function (response) {
                	showGrowlMessageSuccess();
                },
                error: function (e) {
                    showGrowlMessageAjaxError();
                }
            });
		},
	}
}());