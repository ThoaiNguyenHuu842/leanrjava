/**
 * @author ThoaiNH
 */
(function() {
	regisWebinarisEdit = {
		boxId: 0,
		open: function(option){
			regisWebinarisEdit.boxId = option.boxId;
			if(!regisWebinarisEdit.inited)
			{
				regisWebinarisEdit.init();
				$('#qb-dlg-edit-webinaris-project').webToolDialog(396);
				regisWebinarisEdit.inited = true;
			}
			$('#qb-dlg-edit-webinaris-project').dialog("open");
			var projectId = bigBoxModelManager.listBox[regisWebinarisEdit.boxId][WEB_PRO_DATA];
			$('#qb-dlg-edit-webinaris-project .input-webinaris-project-id').val(projectId);
		},
		init : function() {
			regisWebinarisEdit.eventListenerNew();
		},
		eventListenerNew : function() {
			$(document).on('click', '#btn-save-widget-regis-webinaris', function() {
				try {
					var projectId = parseInt($('#qb-dlg-edit-webinaris-project .input-webinaris-project-id').val());
					bigBoxModelManager.updateItemField(regisWebinarisEdit.boxId, WEB_PRO_DATA, projectId+"");
					$('#qb-dlg-edit-webinaris-project').dialog('close');
				} catch (e) {
					// TODO: handle exception
					util.messageDialog(getLocalize("jsgwe_title1"));
				}
			});
		}
	}
}());