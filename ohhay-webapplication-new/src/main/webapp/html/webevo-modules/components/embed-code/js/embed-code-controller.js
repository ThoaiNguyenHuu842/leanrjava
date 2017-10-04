/**
 * @author ThoaiVt 13/01/2016
 */

(function(){
	embedCodeController = {
			COMP_MIN_H: 2,
			COMP_MIN_W: 2,
			crCompId : "",
			idEmbed : "",
			add : function(grid, x, y, $self) {
				var node = {
					x : x,
					y : y,
					width : 24,
					height : 12
				};
				var widget = grid.add_widget($(buildTemplateEmbed($self.html())),
						node.x, node.y, node.width, node.height, true);
				widget[WEB_PRO_DATA] = '{"html":"Your embed code"}';
				var ifr = document.createElement("iframe");
				  ifr.style.width= "100%";
				  ifr.style.height= "100%";
				  ifr.className = 'qb-iframe-embed';
				  ifr.setAttribute("frameborder", "0");
				  ifr.setAttribute("id", "iframeResult");  
				  widget.find('.qb-component').prepend(ifr);
				  var ifrw = (ifr.contentWindow) ? ifr.contentWindow : (ifr.contentDocument.document) ? ifr.contentDocument.document : ifr.contentDocument;
				  ifrw.document.open();
				  ifrw.document.write('Your embed code');  
				  ifrw.document.close();
				  // update 17/11/2016 - call to action
				  /*setTimeout(() => {
					  widget.find('.btn-option-embedcode').trigger('click');
				  }, 500);*/
				 
				return widget;
			},/*
				 * open option
				 */
			open : function(){
				// init when first call
				if(!embedCodeController.inited)
				{
					$('#qb-option-embedcode').webToolDialog(500);
					embedCodeController.inited = true;
				}
				$('#qb-option-embedcode').dialog("open");
			},
			/*
			 * close
			 */
			close : function(){
				 $('#qb-option-embedcode').dialog("close");
			},
			/*
			 * init Embed code
			 */
			init : function() {
				embedCodeController.eventListener();
			},
			/*
			 * event Listener
			 */
			eventListener : function(){
				$(document).on('click','.qb-component-iframe-embed .btn-option-embedcode',function(){
					embedCodeController.crCompId = $(this).closest(".grid-stack-item-content").attr("qb-component-id");
					var html = $('.grid-stack-item-content[qb-component-id="'+embedCodeController.crCompId+'"] .qb-iframe-embed').contents().find('body').html();
					$('#qb-option-embedcode .embed-option #content-data-html').val(html);
					embedCodeController.open();
				});
				
				$(document).on('click','#qb-option-embedcode .embed-option #btn-save-html',function(){
					var data=$('#qb-option-embedcode .embed-option #content-data-html').val();
					var dataHtml={html : data}
					componentModelManager.updateItemField(embedCodeController.crCompId,WEB_PRO_DATA,dataHtml);
					
					var ifr = document.createElement("iframe");
					  ifr.style.width= "100%";
					  ifr.style.height= "100%";
					  ifr.className = 'qb-iframe-embed';
					  ifr.setAttribute("frameborder", "0");
					  ifr.setAttribute("id", "iframeResult");  
					  $('.grid-stack-item-content[qb-component-id="'+embedCodeController.crCompId+'"] .qb-iframe-embed').remove();
					  $('.grid-stack-item-content[qb-component-id="'+embedCodeController.crCompId+'"] .qb-component').prepend(ifr);
					  var ifrw = (ifr.contentWindow) ? ifr.contentWindow : (ifr.contentDocument.document) ? ifr.contentDocument.document : ifr.contentDocument;
					  ifrw.document.open();
					  ifrw.document.write(data);  
					  ifrw.document.close();
					
					$('#qb-option-embedcode').dialog("close");
				});
			},
			/*
			 * load data
			 */
			load : function(grid, node, data, compId){
				var widget = grid.add_widget($(buildTemplateEmbed()), node.x,
						node.y, node.width, node.height);
				widget.attr('qb-component-id', compId);
				var ifr = document.createElement("iframe");
				  ifr.style.width= "100%";
				  ifr.style.height= "100%";
				  ifr.className = 'qb-iframe-embed';
				  ifr.setAttribute("frameborder", "0");
				  ifr.setAttribute("id", "iframeResult");  
				  widget.find('.qb-component').prepend(ifr);
				  var ifrw = (ifr.contentWindow) ? ifr.contentWindow : (ifr.contentDocument.document) ? ifr.contentDocument.document : ifr.contentDocument;
				  ifrw.document.open();
				  if(data)
					  ifrw.document.write(data.html);  
				  ifrw.document.close();
				return widget;
			}
	}
}());