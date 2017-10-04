/**
 * @author ThoaiVt - 22/07/2016
 */
(function(){
	demoAutoResonsive = {
			/*
			 * init
			 */
			init : function(){
				this.eventListener();
			},
			/*
			 * eventListener
			 */
			eventListener : function(){
				$(document).on('click','.qb-evo-autoresponsive-wrapper #add-section-autoresponsive',function(){
					console.log("Show");
					var html = '<section class="bonevo-section-autoresponsive">';
						html += '	<div class="function-panel-autoresponsive">';
						html += '		<div class="item btn-dropdown-menu dropdown">';
						html += '			<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><label>...</label></button>';
						html += '				<ul class="dropdown-menu function-autoresp" aria-labelledby="dropdownMenu1">';
						html += '					<li class="btn-set-layout-autoresp"><a><i class="fa fa-bars" aria-hidden="true"></i>  Layout</a></li></ul></div>';
						html += '	</div>';
						html += '</section>';
					$(".qb-evo-autoresponsive-wrapper .content-data-autoresponsive").append(html);
				});
				/*
				 * event add component
				 */
				$(document).on('click',".qb-evo-autoresponsive-wrapper #add-component-autoresponsive",function(){
					console.log("Add component go");
					var html = '<div class="otext-wrapper-content-autoresponsive auto-responsive">';
						html += $(".qb-evo-autoresponsive-wrapper .otext-wrapper-content-autoresponsive").html();
						html += '</div>';
					$(".qb-evo-autoresponsive-wrapper .bonevo-section-autoresponsive").append(html);
				});
				
				/*
				 * event set layout
				 */
				$(document).on('click',".qb-evo-autoresponsive-wrapper .btn-set-layout-autoresp",function(){
					console.log("Set layout");
					$('.dialog-auto-response-text').dialog({
				        autoOpen: false,
				        hide: 'fold',
				        show: 'blind',
				        title : "Choice Layout"
					});
					$('.dialog-auto-response-text').dialog("open");
				});
				/*
				 * event choise layout
				 */
				$(document).on('click',".dialog-auto-response-text .btn-text-layout-autors",function(){
					var layout = $(this).attr("layout");
					console.log("s111 : "+layout);
					$(".qb-evo-autoresponsive-wrapper .otext-wrapper-content-autoresponsive").attr("layout",layout);
				});
			}
	};
}())