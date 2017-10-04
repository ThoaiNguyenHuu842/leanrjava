/**
 * @author ThoaiNH
 * create Nov 24, 2015
 */
(function() {
	contactFormEditField = {
		init : function() {
			contactFormEditField.eventListener();
		},
		eventListener: function(){
			$(document).on('click','#qb-dlg-contact-form-field .btn-save-field',function(){
				$('#qb-dlg-contact-form-field').dialog("close");
				contactFormEditField.callBack($('#qb-dlg-contact-form-field textarea').val());
			});
		},
		open: function(option){
			contactFormEditField.callBack = option.callBack;
			if(!contactFormEditField.inited)
			{
				contactFormEditField.init();
				$('#qb-dlg-contact-form-field').webToolDialog(396);
				contactFormEditField.inited = true;
			}
			$('#qb-dlg-contact-form-field textarea').val(option.data);
			$('#qb-dlg-contact-form-field').dialog("open");
		}
	}
}());