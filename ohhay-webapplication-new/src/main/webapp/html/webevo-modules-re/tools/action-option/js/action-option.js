/**
 * @author ThienND
 *
 * created Jan 28, 2016
 */
(function(){
	actionOption = {
		open: function(option){
			//init when first call
			if(!actionOption.inited){
				actionOption.init();
				actionOption.inited = true;
			}
			
			actionOption.callBack = option.callBack;
			actionOption.target = option.target;
			actionOption.link = option.target.attr('data-action-link');
			actionOption.type = actionOption.target.attr('data-action-type');
			actionOption.linkTarget = actionOption.target.attr('data-action-link-target');
			// lấy danh sách các box
			var tempHTML = "";
			for(var id in bigBoxModelManager.listBox) {
				var status = bigBoxModelManager.listBox[id][WEB_PRO_STT];
				var name = bigBoxModelManager.getName(id);
				if(status == WEB_PRO_STT_UPDATE || status == WEB_PRO_STT_NO_CHANGE) {
					if (name && name != 'null')
						tempHTML += '<option value="'+WEB_PREFIX_BOX+name+'">'+ name +'</option>';
//					else
//						tempHTML += '<option value="qb-box-'+ id +'"> Section '+ id +'</option>';
				}
			}
			var tempHTML2 = "";
			var listAnchorName = componentModelManager.getListName(actionOption.target.closest('.grid-stack-item-content').attr('qb-component-id'));
			$.each(listAnchorName,function(k,v){
				if (v != 'null')
					tempHTML2 += '<option value="'+WEB_PREFIX_COMP+v+'">'+v+'</option>';
			});
			// lấy danh sách các page 
			actionOption.getListPage();
			$('li.menu-left-label').removeClass('selected');
			$('div.action-content').hide();
			$('#ao-list-box').html(tempHTML);
			$('#ao-list-component').html(tempHTML2);
			actionOption.type = actionOption.type ? actionOption.type : 'none';
			actionOption.showCurrentSelected(actionOption.type);
			$('#ao-web-address-field').val('');
			actionOption.linkTarget = actionOption.linkTarget ? actionOption.linkTarget : '_self';
			
			if (actionOption.type == 'web-address'){
				$('#ao-web-address-field').val(actionOption.link);
				$('.ao-web-address .ao-link-target').val(actionOption.linkTarget);
			}
			else if (actionOption.type == 'go-to-page'){
				$('.ao-go-to-page .ao-link-target').val(actionOption.linkTarget);
			}
			else if (actionOption.type == 'scroll-to-box'){
				$('#ao-list-box').val(actionOption.link);
			}
			else if (actionOption.type == 'scroll-to-component'){
				$('#ao-list-component').val(actionOption.link);
			}
			$('#qb-dlg-action-option .menu .menu-left li').show();
			if (option.del) {
				var del = option.del.split(" ");
				$.each(del,function(k,v) {
					$(v).hide();
				});
			}
			$('#qb-dlg-action-option').dialog('open');
		},
		close: function(){
			$('#qb-dlg-action-option').dialog('close');
		},
		init: function(){
			actionOption.eventListener();
			$('#qb-dlg-action-option').webToolDialog(600);
			actionOption.inited = true;
		},
		eventListener: function(){
			$(document).on('click','#qb-dlg-action-option .ao-none',function(){
				actionOption.type = 'none';
				actionOption.showCurrentSelected(actionOption.type);
			});
			$(document).on('click','#qb-dlg-action-option .ao-web-address',function(){
				actionOption.type = 'web-address';
				actionOption.showCurrentSelected(actionOption.type);
			});
			$(document).on('click','#qb-dlg-action-option .ao-go-to-page',function(){
				actionOption.type = 'go-to-page';
				actionOption.showCurrentSelected(actionOption.type);
			});
			$(document).on('click','#qb-dlg-action-option .ao-download-document',function(){
				actionOption.type = 'download-document';
				actionOption.showCurrentSelected(actionOption.type);
			});
			$(document).on('click','#qb-dlg-action-option .ao-scroll-to-top',function(){
				actionOption.type = 'scroll-to-top';
				actionOption.showCurrentSelected(actionOption.type);
			});
			$(document).on('click','#qb-dlg-action-option .ao-scroll-to-bottom',function(){
				actionOption.type = 'scroll-to-bottom';
				actionOption.showCurrentSelected(actionOption.type);
			});
			$(document).on('click','#qb-dlg-action-option .ao-scroll-to-component',function(){
				actionOption.type = 'scroll-to-component';
				actionOption.showCurrentSelected(actionOption.type);
				actionOption.link = '#'+$('#ao-list-component').val();
			});
			$(document).on('click','#qb-dlg-action-option .ao-scroll-to-box',function(){
				actionOption.type = 'scroll-to-box';
				actionOption.showCurrentSelected(actionOption.type);
				actionOption.link = '#'+$('#ao-list-box').val();
			});
			
			$(document).on('input','#ao-web-address-field',function(){
				actionOption.link = $(this).val();
				if (actionOption.validUrl(actionOption.link)){
					$(this).css('border','1px solid #999');
					if (!(actionOption.link.indexOf('http://') > -1 || actionOption.link.indexOf('https://') > -1)){
						actionOption.link = 'http://'+actionOption.link;
					}
				} else
					$(this).css('border','1px solid red');
			});
			
			$(document).on('click','#qb-dlg-action-option .btn-save',function(){
				if (actionOption.type == 'web-address'){
					actionOption.link = $('#ao-web-address-field').val();
					actionOption.linkTarget = $('.ao-web-address .ao-link-target').val();
				} else if (actionOption.type == 'go-to-page') {
					actionOption.link = $('#ao-list-page').val();
					actionOption.linkTarget = $('.ao-go-to-page .ao-link-target').val();
				} else if (actionOption.type == 'scroll-to-box') {
					actionOption.link = $('#ao-list-box').val();
					actionOption.linkTarget = '';
				} else if (actionOption.type == 'scroll-to-component') {
					actionOption.link = $('#ao-list-component').val();
					actionOption.linkTarget = '';
				} else {
					actionOption.link = '';
					actionOption.linkTarget = '';
				}
				if (actionOption.type == 'web-address'){
					if (actionOption.validUrl(actionOption.link)){
						actionOption.callBack({
							type : "action",	
							dataType : actionOption.type,
							dataLink : actionOption.link,
							dataLinkTarget: actionOption.linkTarget,
						});
						actionOption.close();
						showGrowlMessageSuccess();
					} else {
						showGrowlMessage('Invalid URL', 2000);
					}
				} else {
					actionOption.callBack({
						type : "action",	
						dataType : actionOption.type,
						dataLink : actionOption.link,
						dataLinkTarget: actionOption.linkTarget,
					});
					actionOption.close();
				}
			});
		},
		showCurrentSelected: function(a){
			$('#qb-dlg-action-option .menu .menu-left .ao-'+a+'').addClass('selected').siblings().removeClass('selected');
			$('#qb-dlg-action-option .menu .menu-right .ao-'+a+'').show().siblings().hide();
		},
		goToEditMode : function() {
			$('a[data-action-type]').each(function(){
				var link = $(this);
				switch (link.attr('data-action-type')) {
				case 'web-address':
				case 'go-to-page':
					link.attr('data-action-link', link.attr('href'));
					link.attr('data-action-link-target', link.attr('target'));
					break;
				case 'scroll-to-box':
					link.removeClass('scroll-to-box');
					link.attr('data-action-link', link.attr('href'));
					link.removeAttr('onclink');
					break;
				case 'scroll-to-component':
					link.removeClass('scroll-to-component');
					link.attr('data-action-link', link.attr('href'));
					link.removeAttr('onclink');
					break;
				case 'scroll-to-top':
					link.removeClass('scroll-to-top');
					break;
				case 'scroll-to-bottom':
					link.removeClass('scroll-to-bottom');
					link.removeAttr('onclick');
					break;
				case 'none':
					link.removeClass('none');
					break;
				default:
					break;
				}
				link.removeAttr('href');
				link.removeAttr('target');
				link.removeClass(link.attr('ho-effect'));
			});
		},
		goToPreviewMode : function() {
			$('a[data-action-type]').each(function(){
				var link = $(this);
				link.removeAttr('href');
				link.removeAttr('target');
				link.removeClass('none');
				switch (link.attr('data-action-type')) {
				case 'web-address': 
				case 'go-to-page':
					link.attr('href', link.attr('data-action-link'));
					link.attr('target', link.attr('data-action-link-target'));
					link.removeAttr('data-action-link');
					link.removeAttr('data-action-link-target');
					break;
				case 'scroll-to-box':
					link.addClass('scroll-to-box');
					var box = link.attr('data-action-link');
					if (box) {
						link.attr('href', box);
						var onclick = 'smoothScroll("'+box+'");return false;';
						link.attr('onclick',onclick);
						link.removeAttr('data-action-link');
					}
					break;
				case 'scroll-to-component':
					link.addClass('scroll-to-component');
					var component = link.attr('data-action-link');
					if (component){
						link.attr('href', component);
						var onclickComponent = 'smoothScroll("'+component+'");return false;';
						link.attr('onclick',onclickComponent);
						link.removeAttr('data-action-link');
					}
					
					break;
				case 'scroll-to-top':
					link.addClass('scroll-to-top');
					link.attr('href', '#top');
					link.removeAttr('data-action-link');
					break;
				case 'scroll-to-bottom':
					link.addClass('scroll-to-bottom');
					link.attr('onclick', 'window.scrollTo(0,document.body.scrollHeight);');
					link.removeAttr('data-action-link');
					break;
				case 'none':
					link.addClass('none');
					break;
				default:
					break;
				}
			});
		},
		getListPage : function() {
			$.ajax({
				url : encodeUrl('mysitesBean.load'),
				type: 'GET',
			    data : {},
			    success : function(response) {
			    	console.log(response);
			    	var tempHTML = '';
			    	var listPages = response.result;
			    	$.each(listPages,function(k,v){
			    		tempHTML += '<option value="/e'+v.id+'/evo-redirect-page">'+ v.ev405 +'</option>';
			    	});
			    	$('#ao-list-page').html(tempHTML);
			    	if (actionOption.type == 'go-to-page')
			    		$('#ao-list-page').val(actionOption.link);
			    },
			    error : function(error) {
			    	
			    }
			});
		},
		validUrl: function(url){
			var pattern = /[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)/ig;
			return pattern.test(url);
		},
	};
}());
