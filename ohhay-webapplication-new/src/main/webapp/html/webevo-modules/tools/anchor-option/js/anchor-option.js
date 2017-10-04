/**
 * @author ThienND
 *
 * created Dec 2, 2015
 */
(function(){
	anchorOption = {
			open: function(option){
				anchorOption.targetID = option.targetID;
				anchorOption.isBox = option.isBox;
				anchorOption.listAnchorName = anchorOption.isBox ? bigBoxModelManager.getListName() : componentModelManager.getListName(anchorOption.targetID);
				anchorOption.anchorName = anchorOption.isBox ? bigBoxModelManager.getName(anchorOption.targetID) : componentModelManager.getName(anchorOption.targetID);
				anchorOption.isAnchorNameRightFormat = anchorOption.isAnchorNameWithoutSpace(anchorOption.anchorName);
//				anchorOption.isAnchorNameNotExist    = anchorOption.isAnchorNameAlreadyExist(anchorOption.anchorName);				
				anchorOption.isAnchorNameNotExist    = true;				
				$('#qb-dlg-anchor-option .anchor-text').val(anchorOption.anchorName != 'null' ? anchorOption.anchorName :'');
				$('#qb-dlg-anchor-option i').hide();
				$('#qb-dlg-anchor-option li').removeClass('anchor-name-error');
				$('#qb-dlg-anchor-option').dialog('open');
			},
			close: function(){
				$('#qb-dlg-anchor-option').dialog('close');
			},
			init: function(){
				anchorOption.eventListener();
				$('#qb-dlg-anchor-option').webToolDialog(500);
				anchorOption.inited = true
			},
			eventListener: function(){
				$(document).on('input','#qb-dlg-anchor-option .anchor-text',function(){
					anchorOption.anchorName = $(this).val();
					if (anchorOption.anchorName.length > 0) {
						anchorOption.isAnchorNameRightFormat = anchorOption.isAnchorNameWithoutSpace(anchorOption.anchorName);
						anchorOption.isAnchorNameNotExist    = anchorOption.isAnchorNameAlreadyExist(anchorOption.anchorName);
						$('#qb-dlg-anchor-option li.anchor-name-rule').removeClass('anchor-name-error');
						if (anchorOption.isAnchorNameRightFormat) {
							$('#qb-dlg-anchor-option li.anchor-name-space').addClass('anchor-name-right');
							$('#qb-dlg-anchor-option li.anchor-name-space').removeClass('anchor-name-error');
						}
						else {
							$('#qb-dlg-anchor-option li.anchor-name-space').removeClass('anchor-name-right');
							$('#qb-dlg-anchor-option li.anchor-name-space').addClass('anchor-name-error');
						}
						if (anchorOption.isAnchorNameNotExist) {
							$('#qb-dlg-anchor-option li.anchor-name-unique').addClass('anchor-name-right');
							$('#qb-dlg-anchor-option li.anchor-name-unique').removeClass('anchor-name-error');
						}
						else {
							$('#qb-dlg-anchor-option li.anchor-name-unique').removeClass('anchor-name-right');
							$('#qb-dlg-anchor-option li.anchor-name-unique').addClass('anchor-name-error');
						}
					}
					else {
						$('#qb-dlg-anchor-option li.anchor-name-rule').removeClass('anchor-name-error');
						$('#qb-dlg-anchor-option li.anchor-name-rule').removeClass('anchor-name-right');
					}
				});
				$(document).on('click','#qb-dlg-anchor-option button.btn-save',function(){
					if ((anchorOption.isAnchorNameRightFormat && anchorOption.isAnchorNameNotExist) || anchorOption.anchorName.length == 0){
						if (anchorOption.isBox){
							bigBoxModelManager.setName(anchorOption.targetID, anchorOption.anchorName);
							$('.qb-box-component[qb-box-id="'+anchorOption.targetID+'"').attr('id',WEB_PREFIX_BOX+anchorOption.anchorName);
							$('.qb-box-component[qb-box-id="'+anchorOption.targetID+'"').find('.btn-set-anchor-box label').html(getLocalize("jsmtp_title3")+' '+anchorOption.anchorName);
						}
						else {
							componentModelManager.setName(anchorOption.targetID, anchorOption.anchorName);	
							$('.grid-stack-item-content[qb-component-id="'+anchorOption.targetID+'"]').attr('id',WEB_PREFIX_COMP+anchorOption.anchorName);
							$('.grid-stack-item-content[qb-component-id="'+anchorOption.targetID+'"]').find('.btn-set-anchor label').html(getLocalize("jsmtp_title3")+' '+anchorOption.anchorName);
						}
						anchorOption.close();
					}
				});
			},
			isAnchorNameWithoutSpace: function(a){
				return !/\s/.test(a)
			},
			isAnchorNameAlreadyExist:function(a){
				return anchorOption.listAnchorName.indexOf(a) == -1;
			}
	}
}());