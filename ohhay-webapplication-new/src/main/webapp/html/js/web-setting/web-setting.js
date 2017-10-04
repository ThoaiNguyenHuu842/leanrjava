
/**
 * @author ThoaiNH
 * create Dec 31, 2015
 */
(function() {
	webSetting = {
		init : function() {
			webSetting.eventListenerNew();
		},
		eventListenerNew : function() {
			$(document).on('click','.qb-setting-tab-item', function(){
				$('.qb-setting-tab-item').removeClass('active');
				$('.qb-web-setting-content-body-panel').hide();
				$(this).addClass('active');
				$('.'+$(this).attr('tab')).show();
				location.hash = $(this).attr('name');
			});
			//close input panel
			$(document).on('click',".qb-setting-input-header-back", function(){
				location.hash = "";
				$('.qb-setting-input-content').fadeOut();
				$('body').css('overflow','auto');
			});
			//render hash
			if(location.hash && location.hash.length > 0)
			{
				var hash = location.hash.substring(1,location.hash.length);
				webSetting.renderUIByHash(hash);
			}
			else
				webSetting.renderUIByHash("");
		},
		renderUIByHash:function(hash)
		{			
			try{
				switch (hash) {
				case 'domain':
					$('.qb-setting-tab-item[name="domain"]').trigger('click');
					break;
				case 'seo':
					$('.qb-setting-tab-item[name="seo"]').trigger('click');
					break;	
				case 'add-domain':
					$('.btn-add-domain').trigger('click');
					break;	
				default:
					hash = "domain";
					location.hash = "domain";
					$('.qb-setting-tab-item[name="domain"]').trigger('click');
					break;
				}
			}
			catch(err){
				hash = "domain";
				location.hash = "domain";
				$('.qb-setting-tab-item[name="domain"]').trigger('click');
			}
		}
	}
}());