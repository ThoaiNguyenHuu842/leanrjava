/*
 * author ThoaiNH
 * create 11/09/2015
 * user history manager
 */
(function() {
	historyManager = {
		init : function() {
			this.eventListener();			
		},
		eventListener : function() {
			
		},
		updateKey: function(key){
			$.ajaxSetup({
				beforeSend : function()  {
				},
				complete : function() {
					setDefaultAjaxStatus();
				}
			});
			$.ajax({
                type: "POST",
                url: encodeUrl("m930Bean.updateKey"),
                data: {
                	key:key
                },
                success: function (response) {
                },
                error: function (error) {
                    
                }
            });
		}
	}
}());
