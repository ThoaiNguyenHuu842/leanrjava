/**
 * @author ThoaiNH create Oct 9, 2015
 */
(function() {
	subbox = {
		init : function() {
			this.eventListener();
		},
		eventListener : function() {
			$(document).on('click', '.btn-add-sub-box', function() {
				var boxId = $(this).parents('.content-box').attr('qb-box-id');
				alert(boxId);
			});
		},
		
	}
}());