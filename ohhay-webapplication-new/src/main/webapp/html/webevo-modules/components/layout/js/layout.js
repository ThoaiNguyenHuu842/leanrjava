/*
 * Thong
 * date create: 30/10/2015
 */
(function() {
	layout = {
		isShowToolbar : false,
		targetBox : null,
		init : function() {
			this.eventListener();
		},
		eventListener: function(){
			$(document).on('click','.btn-layout-del', function(){
				$(this).closest('.normal_layout_1').remove();
			});
		},
		add : function(grid, x, y , $self) {
			console.log("grid:"+grid.attr("class"));
			var type_layout = $self.children();
			var template = '';
			
			if(type_layout.hasClass('normal_layout_1')){
				template = $(type_layout.get(0).outerHTML);
			}
			grid.append(template);
			
			var box = grid.find('.btn-layout-move');
			grid.find('.normal_layout_1').resizable();
			
			var drag = {
			    elem: null,
			    x: 0,
			    y: 0,
			    state: false
			};

			var delta = {
			    x: 0,
			    y: 0
			};

			box.mousedown(function(e) {
				if (!drag.state) {
			        drag.elem = this;
			        drag.x = e.pageX;
			        drag.y = e.pageY;
			        drag.state = true;
			    }
			    return false;
			});

			$(document).mouseup(function(e) {
				if (drag.state) {
			        drag.state = false;
			    }
			});

			$(document).mousemove(function(e) {
				if (drag.state) {
			        delta.x = e.pageX - drag.x;
			        delta.y = e.pageY - drag.y;
			        var cur_offset = $(drag.elem).closest('.normal_layout_1').offset();
			        $(drag.elem).closest('.normal_layout_1').offset({
			            left: (cur_offset.left + delta.x),
			            top: (cur_offset.top + delta.y)
			        });
			       drag.x = e.pageX;
			       drag.y = e.pageY;
				}
			});
		},
		load : function(component) {
			
		}
	}
}());