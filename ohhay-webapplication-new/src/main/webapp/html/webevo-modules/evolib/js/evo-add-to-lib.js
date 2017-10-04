 
 /**
 * @author ThoaiNH
 * create Jan 12, 2016
 */
(function() {
	evoAddToLib = {
	    boxId: 0,
		init : function() {
			$('#qb-dlg-add-to-lib').webDialog(590);
			this.eventListener();
		},
		eventListener : function() {
			$(document).on('click', '#qb-dlg-add-to-lib .btn-done-add-to-lib', function() {
				evoAddToLib.addToLib();
			});
			$(document).on('click','#qb-dlg-add-to-lib .btn-upload-thumb-add-to-lib', function(){
				$('#qb-dlg-add-to-lib .upload_thumb-add-to-lib').trigger('click');
			});
			$('#qb-dlg-add-to-lib .upload_thumb-add-to-lib').on('change', function(event) {
				var reader = new FileReader();
				reader.onload = function(e) {
					var base64 = e.target.result;
					$('#qb-dlg-add-to-lib .thumb-lib-preview').attr('src',base64);
					$('#qb-dlg-add-to-lib .thumb-lib-preview').css('background-image', 'url(' + base64 + ')');
				}
				reader.readAsDataURL(this.files[0]);
			});
		},
		open: function(option){
			evoAddToLib.boxId = option.boxId;
			$("#qb-dlg-add-to-lib .input-lib-tags").val("");
			$("#qb-dlg-add-to-lib .input-lib-title").val("");
			//init when first call
			if(!this.inited)
			{
				this.init();
				this.inited = true;
			}
			$('#qb-dlg-add-to-lib').dialog('close');
			$('#qb-dlg-add-to-lib').dialog('open');
		},
		/*
		 * add box to lib
		 */
		addToLib: function(){
			var imgBase64 = $('#qb-dlg-add-to-lib .thumb-lib-preview').attr('src')?$('#qb-dlg-add-to-lib .thumb-lib-preview').attr('src'):'';
			var libType = $("#qb-dlg-add-to-lib input[type='radio']:checked").val();
			var otags = $("#qb-dlg-add-to-lib .input-lib-tags").val();
			var editAble = $('#qb-dlg-add-to-lib .checkbox-editable').is(":checked")? 0: 1;
			var title = $("#qb-dlg-add-to-lib .input-lib-title").val();
			var formData = new FormData();
			formData.append('libType', libType);
            formData.append('otags', otags);
            formData.append('title', title);
            formData.append('editAble', editAble);
			formData.append('pe920', evoAddToLib.boxId);
            formData.append('imgBase64', imgBase64);
			$.ajax({
				type : "POST",
				url : encodeUrl("libraryBean.addToLib"),
				cache: false,
                contentType: false,
                processData: false,
                data: formData,
				success : function(response) {
					if(response.status == 'SUCCESS'){
						$("#qb-dlg-add-to-lib .input-lib-tags").val('');
						$('#qb-dlg-add-to-lib').dialog('close');
						bigBoxModelManager.updateLibStatus(evoAddToLib.boxId, libType)
						evoLib.open(1);
						$('#qb-dlg-add-to-lib .upload_thumb-add-to-lib').val("");
						$('#qb-dlg-add-to-lib .thumb-lib-preview').removeAttr('src');
						$('#qb-dlg-add-to-lib .thumb-lib-preview').removeAttr('style');
					}
					else
						util.messageDialog(response.result[0].defaultMessage);
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		}
	}
}());
 