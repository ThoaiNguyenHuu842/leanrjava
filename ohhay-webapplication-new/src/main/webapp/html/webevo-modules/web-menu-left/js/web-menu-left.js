/**
 * author: ThoaiNH
 * date create: 22/08/2015
 * element web ruler
 */
(function() {
	webMenuLeft = {
		init : function() {
			webMenuLeft.eventListener();
			$('.sub-menu-body').niceScroll();
		},
		eventListener : function() {
			$("#menu-toggle").click(function(e) {
				e.preventDefault();
				$("#wrapper").toggleClass("toggled");
			});
			$("#menu-toggle-2").click(function(e) {
				e.preventDefault();
				$("#wrapper").toggleClass("toggled-2");
				$('#menu ul').hide();
			});
			$('#web-tools .item-icon-panel').on('click',function(e){
				var $self = $(this).parent();
				$('#web-tools .sub-menu').fadeOut();
				$(this).next().slideDown();
			});
			$(document).on('click','#content-wrapper',function(){
				$('#web-tools .sub-menu').fadeOut();
			});
			
			$(document).on('click','#web-tools .menu-extend',function(){
				$('#web-tools .web-tools-content').slideDown();
				$('#web-tools .menu-extend').hide();
				$('#web-tools .menu-collapse').show();
			});
			$(document).on('click','#web-tools .menu-collapse',function(){
				$('#web-tools .web-tools-content').slideUp();
				$('#web-tools .menu-extend').show();
				$('#web-tools .menu-collapse').hide();
				
			});
			/*
			 * click add item
			 */
			$(document).on('click','#web-tools .item-component, #web-tools .single-comp-item,.admin-library-load img.item-img-lbadmin', function(){
				var oneBox = bigbox.hasOneBox();
				console.log(oneBox);
				if(oneBox != null)
				{
					//not allow add to section that reference from library
					if(oneBox[WEB_PRO_LIBTYPE] != 3)
						$('.content-box[qb-box-id="'+oneBox[WEB_PRO_ID]+'"] .grid-stack').addComponent($(this));
					return;
				}
				if(bigbox.selectedBoxID && $('.content-box[qb-box-id="'+bigbox.selectedBoxID+'"] .grid-stack').length > 0)
				{
					//not allow add to section that reference from library
					if(bigBoxModelManager.listBox[bigbox.selectedBoxID][WEB_PRO_LIBTYPE] != 3)
						$('.content-box[qb-box-id="'+bigbox.selectedBoxID+'"] .grid-stack').addComponent($(this));
				}
				else
					util.messageDialog(getLocalize("jswml_title1"));
			});
		},
		/*
		 * hide web tools
		 */
		render: function(){
			console.log('---total visible box:'+bigBoxModelManager.totalVisibleBox());
			if(bigBoxModelManager.totalVisibleBox() > 0)
				$('#web-tools').show();
			else
				$('#web-tools').hide();
		}
	}
}());