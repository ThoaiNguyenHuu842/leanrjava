/*
 * author: ThoaiNH
 * date create: 09/10/2015
 * all grid event and config
 */
 (function() {
	gridConfig = {
		width : 48,
		cell_height: 15,
		float : true,
		vertical_margin: 0,
		draggable : {
			handle: '.btn-draggable'
		},
		getCellWidth: function(){
			return webConfig.data.w/gridConfig.width;
		}
	}
}()); 
 