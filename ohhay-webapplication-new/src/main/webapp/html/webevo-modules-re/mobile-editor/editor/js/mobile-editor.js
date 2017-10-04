/**
 * @author ThoaiNH
 * create Dec 12, 2015
 * module mobile font scale
 */
(function() {
	 mobileEditor = {
		init : function() {
			$('#qb-dlg-mobile-editor .spinner-mb-font-size').spinner({
			    stop: function (event, ui) {
			    	var ob = $(event.currentTarget).siblings('input');
					var val = ob.val();
					mobileEditor.callBack(val);
		 		}
			});
			$('#qb-dlg-mobile-editor').webToolDialog(254);
			mobileEditor.eventListener();
		},
		eventListener : function() {
			$(document).on('keyup','#qb-dlg-mobile-editor .spinner-mb-font-size', function(){
				var val = $(this).val();
				mobileEditor.callBack(val);
			});
		},
		open: function(option){
			//init when first call
			if(!mobileEditor.inited)
			{
				mobileEditor.init();
				mobileEditor.inited = true;
			}
			mobileEditor.callBack = option.callBack;
			$('#qb-dlg-mobile-editor').dialog('open');
			$('#qb-dlg-mobile-editor .spinner-mb-font-size').val(option.value);
		}
	}
}());