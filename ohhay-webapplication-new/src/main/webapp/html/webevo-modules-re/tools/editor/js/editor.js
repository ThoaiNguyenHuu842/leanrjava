/**
 * @author huynh
 * 2/11/2015 
 */
(function(){
	 cp_Editor ={
	 	init : function() {
	 		$('.qb-component .otext-wrapper-content').jqte({source: false});
			$('.qb-editor-body[data-type="qb-editor"]').jqte({source: false});
	 		cp_Editor.moves();
	 		this.eventListener();
		},
		open : function(){
			if(!$('#qb-editor').hasClass('open')){
				$('#qb-editor').show();
				$('#qb-editor').addClass('fadeIn').addClass('open');
			}
		},
		close : function(){
			if($('#qb-editor').hasClass('open')){
				$('#qb-editor').hide();
				$('#qb-editor').addClass('fadeIn').removeClass('open');
				$('.otext-wrapper-content[contenteditable="true"], .grid-style-onedit .obutton-label[contenteditable="true"]').blur();
			}
		},
		//set event
		eventListener: function(){
			$(document).on('click','.qb-editor-header button', this.close);
			$(document).on('focus','.otext-wrapper-content[contenteditable="true"]', function(){
				$('.setattr.jqte_linkform').removeClass('hidden');
				cp_Editor.open();
				/*set update status for it's model data*/				
				var compId = $(this).closest(".grid-stack-item-content").attr('qb-component-id');
				var component = componentModelManager.listComponent[compId];
				if(component[WEB_PRO_STT] == WEB_PRO_STT_NO_CHANGE)
					component[WEB_PRO_STT] = WEB_PRO_STT_UPDATE;
			});
			$(document).on('focus','.grid-style-onedit .obutton-label[contenteditable="true"]', function(){
				$('.setattr.jqte_linkform').addClass('hidden');
				cp_Editor.open();
				/*set update status for it's model data*/				
				var compId = $(this).closest(".grid-stack-item-content").attr('qb-component-id');
				var component = componentModelManager.listComponent[compId];
				if(component[WEB_PRO_STT] == WEB_PRO_STT_NO_CHANGE)
					component[WEB_PRO_STT] = WEB_PRO_STT_UPDATE;
			});
			 
			/*$(document).on('focus','.field-title', function(){
				cp_Editor.open();
			});*/
			$('.qty-1').click(function () {
				var val = $(this).siblings('.qty').val();
				var val2 = parseInt(val) + 1;
				$(this).siblings('.qty').val(val2).trigger("change");
			});

			$('.qty_1').click(function () {
				var val = $(this).siblings('.qty').val();
				var val2 = parseInt(val) - 1;
				if (val2 < 0) {
					val2 = 0;
				}
				$(this).siblings('.qty').val(val2).trigger("change");
			});

			var timenumber = 160;
			var timeout;
			$('.qty-1').mousedown(function () {
				var self = this;
				timeout = setInterval(function () {
					var val = $(self).siblings('.qty').val();
					var val2 = parseInt(val) + 1;
					$(self).siblings('.qty').val(val2).trigger("change");
				}, timenumber);
			});
			$(document).on('mouseup mouseleave', '.qty-1', function () {
				clearInterval(timeout);
				timenumber = 160;
			});

			$('.qty_1').mousedown(function () {
				var self = this;
				timeout = setInterval(function () {
					var val = $(self).siblings('.qty').val();
					var val2 = parseInt(val) - 1;
					if (val2 < 0) {
						val2 = 0;
					}
					$(self).siblings('.qty').val(val2).trigger("change");
				}, timenumber);
			});
			$(document).on('mouseup mouseleave', '.qty_1', function () {
				clearInterval(timeout);
				timenumber = 160;
			});
		},
		//re init editor
		reload:function(){
			$('.qb-component .otext-wrapper-content').jqte({source: false});
		},
		goToPreviewMode:function(){
			cp_Editor.close();
			$('.content-box:not(".content-box-from-lib") .qb-component .jqte_editor').attr('contenteditable','false');
			$('.content-box:not(".content-box-from-lib") .qb-component .otext-wrapper-content').attr('contenteditable','false');
			$('.content-box:not(".content-box-from-lib") .qb-component').find('[jqte-setflag]').attr('jqte-preview','').removeAttr('jqte-setflag');
		},
		goToEditMode:function(){
			$('.content-box:not(".content-box-from-lib") .qb-component .jqte_editor').attr('contenteditable','true');
			$('.content-box:not(".content-box-from-lib") .qb-component .otext-wrapper-content').attr('contenteditable','true');
			$('.content-box:not(".content-box-from-lib") .qb-component').find('[jqte-preview]').attr('jqte-setflag','').removeAttr('jqte-preview');
		},
		/*function move box*/
		moves:function(){
			var box = $('.qb-editor-header');
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
			        var cur_offset = $(drag.elem).parents('#qb-editor').offset();
			        $(drag.elem).parents('#qb-editor').offset({
			            left: (cur_offset.left + delta.x),
			            top: (cur_offset.top + delta.y)
			        });
			       drag.x = e.pageX;
			       drag.y = e.pageY;
				}
			});
		},
		
	 } 
}());