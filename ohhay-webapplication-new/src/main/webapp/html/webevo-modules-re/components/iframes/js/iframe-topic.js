/**
 * @author ThoaiNH
 * create May 13, 2015
 */
(function() {
	iframeTopic = {
		init : function() {
			iframeTopic.eventListener();
		},
		eventListener: function(){
			$(document).on('click','.btn-change-topic-src',function(){
				var iframe = $(this).parents(".qb-component-iframe").find("iframe");
				iframeTopic.open(
					{
						callBack: function(newUrl){
							iframe.attr('src',newUrl);
						}
					}
				);
			});
		},
		/*
		 * open edit iframe
		 */
		open: function(option){
			iframeTopic.callBack = option.callBack;
			
			if(!iframeTopic.inited)
			{
				$('#qb-dlg-edit-number-of-topic').webToolDialog(396);
				$(document).on('click','#btn-save-number-of-topic',function(){
					var newURL = "https://topic.bonevo.net/"+$("#qb-input-qv101").val()+"?npage="+$("#input-number-topic-rows").val()+"&transparent=true&col=1&source=bonevo";
					$('#qb-dlg-edit-number-of-topic').dialog("close");
					iframeTopic.callBack(newURL);
				});
				iframeTopic.inited = true;
			}
		
			$('#qb-dlg-edit-number-of-topic').dialog("open");
		}
	}
}());