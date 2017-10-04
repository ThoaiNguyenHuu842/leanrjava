/**
 * @author ThoaiNH
 * create Oct 10, 2015
 * setup grid for box
 */
$.fn.initBoxRe = function(){
		var boxId = $(this).closest(".content-box").attr("qb-box-id");
		console.log('--init grid:'+ boxId);
		console.log($(this));
		//grid
		$(this).find('.grid-stack').each(function(){
			$(this).initOGrid();
		});
		$(this).height(300);
		//grid resizable width cell_height unit
		$(this).closest(".content-box").addClass('grid-style-onedit');
		$(this).resizable({
			handles: {'n,s':'#content-wrapper .content-box .qb-gird-handle-custom'},
			resize: function(event, ui) {
				
			    ui.size.width = ui.originalSize.width;
			    var a = $(this)/*.find(".grid-stack-re")*/.find('.grid-stack:last-child').attr('index');
				var i ;
				var heightEnd = parseInt(0);
				for(i=1;i<=a;i++){
					var comp = $(this)/*.find(".grid-stack-re")*/.find('div[index='+i+']');
					var y = parseInt(comp.find('.grid-stack-item-content').last().attr('data-gs-y'));
					var height = parseInt(comp.find('.grid-stack-item-content').last().attr('data-gs-height'));
					console.log("item width : "+height);
					if(heightEnd < ((y+height)*15))
						heightEnd = ((y+height)*15);
				}
					if(parseInt($(this).height())<heightEnd){
						$(this).find('.grid-stack').height(heightEnd)
						$(this).height(heightEnd)
					}
					else
						$(this).find('.grid-stack').height($(this).height())
			},
			grid: gridConfig.cell_height,
			stop: function(event,ui){
				bigbox.onBoxHeightChange($(this).closest(".content-box").attr("qb-box-id"),$(this).height());
				grid.refreshGridLineY($(this).find('.grid-stack'));
			}
		});		
		
};