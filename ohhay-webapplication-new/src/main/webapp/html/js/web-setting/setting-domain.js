/*
 * author: ThoaiNH
 * cerate: 02/04/2015
 * setting for add domain module
 */
(function() {
	settingDomain = {
		init : function() {
			settingDomain.eventListenerNew();
		},
		/*======================================NEW UI: 16/04/2015 ThoaiNH================================*/
		eventListenerNew : function() {
			settingDomain.getListDomainNew($('#current-web-id').val());
			$(document).on('click', '#qb-btn-save-domain',function(){
				var dataUrl = $('#qb-input-domain-name').val();
				if(settingDomain.checkValidate2(dataUrl))
//					settingDomain.saveDomainNew($('#current-web-id').val());
					settingDomain.insertDomain($('#current-web-id').val());
				else
					showGrowlMessage(getLocalize("setting_ohaydomain_error1"));
			});
			$(document).on('keydown','#qb-input-domain-name',function(e){
				if (e.keyCode == 13) {
				var dataUrl = $('#qb-input-domain-name').val();
				if(settingDomain.checkValidate2(dataUrl))
//					settingDomain.saveDomainNew($('#current-web-id').val());
					settingDomain.insertDomain($('#current-web-id').val());
				else
					showGrowlMessage(getLocalize("setting_ohaydomain_error1"));
				}
			});
			//show input domain
			$(document).on('click','.btn-add-domain', function(){
				settingDomain.checkRightAddDomain();
			});
			$(document).on('click','.btn-delete-domain', function(){
				var ov202 = $(this).attr('uv922');
				var id = $(this).attr('id');
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					settingDomain.deleteDomainSQL(ov202,id);
				});
			});
			$(document).on('click','.btn-reload-list-domain', function(){
				settingDomain.getListDomainNew($('#current-web-id').val());
			});
			
			//coppy
			$(document).on('click','.qb-ohhay-setting-wrapper .qb-setting-wrapper .qb-setting-content .qb-sett-list.qb-sett-list-domain li .qb-sett-list-label span',function(){
				var text = $(this).parent().find('p').html();
				var $temp = $('<input>');
				$('body').append($temp);
				$temp.val(text).select();
				document.execCommand('copy');
				$temp.remove();
				$('#growl-message')
				showGrowlMessage('Copied',50);
			});
		},
		/*
		 * delete domain SQL
		 */
		deleteDomainSQL : function(ov202,id) {
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.deleteDomainSQL"),
				data : { 'ov202': ov202, 'id':id},
				success : function(response) {
					if (response.result >= 1) {
						$(".dialogconfirm").jsOhhayDialog('close');
						settingDomain.getListDomainNew($('#current-web-id').val());
					} else
						showGrowlMessageError();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
			
		},
		insertDomain: function(fe400){
			var domainName = $('#qb-input-domain-name').val();
			if (domainName && domainName.length > 0) {
				$.ajax({
					type: 'POST',
					url: encodeUrl('setting.insertDomain'),
					data : {'ov201': domainName, 'fe400':fe400},
					success : function(response) {
						var verificationCode = response.result;
						console.log(verificationCode);
						if (response.result >= 1) {
							settingDomain.getListDomainNew($('#current-web-id').val());
							$('#qb-input-domain-name').val('');
							$('.qb-setting-input-content').fadeOut();
							//$('.qb-ohhay-setting-wrapper .qb-setting-wrapper .qb-setting-content .qb-sett-list').niceScroll();
						} else if (response.result == -1)
							showGrowlMessage(getLocalize('setting_domain_message_error1'));
						else
							showGrowlMessageError();
					},
					error : function(e){
						showGrowlMessageAjaxError();
					}
				})
			}
		},
		/*
		 * get list domain
		 */
		getListDomainNew : function(pe400) {
			$('.qb-ohhay-setting-wrapper .qb-setting-wrapper .qb-setting-content .qb-sett-list').getNiceScroll().hide();
			$.ajax({
						url : encodeUrl("setting.getListDomain"),
						data : {pe400:pe400},
						success : function(response) {
							var listU920mgs = response.result;
							console.log(response.result);
							var htmlString = "";
							if(listU920mgs && listU920mgs.length > 0){
								for (i = listU920mgs.length - 1; i >= 0; i--) {
									var u920 = listU920mgs[i];
									var verificationClass = "";
									var verificationText = "";
									if (u920.un923 && u920.un923 === 1) {
										verificationClass = 'verified';
										verificationText = "Verified";
									} else {
										verificationClass = 'waiting';
										verificationText = 'Waiting';
									}
									htmlString +=    '<li class="item-domain-list">'+
														'<div class="qb-sett-list-left">'+
															'<div style="float:left;width:25%;">'+
																'<label class="qb-sett-list-label">'+u920.uv921+'</label>'+
																'<label class="qb-sett-list-label">'+getLocalize("isseo_title3")+': </label>'+
																'<label class="qb-sett-list-label">Verification code: </label>'+
																
															'</div>'+
															'<div style="float:left;width:75%;">'+
																'<div class="domain-verification-status '+verificationClass+'"><label>'+verificationText+'</label></div>'+
																'<label class="qb-sett-list-label domain-ipv4" ><p>169.53.141.72</p><span class="btn btn-coppy"><p>Copy</p></span></label>'+
																'<label class="qb-sett-list-label domain-verification-code" ><p>'+u920.uv922+'</p><i class="fa fa-question-circle icon-qs"></i><div class="qb-sett-verif-tooltip"><p>'+getLocalize("isseo_title4")+'</p></div><span class="btn btn-coppy"><p>Copy</p></span></label>'+
															'</div>'+
														'</div>'+
														'<div class="qb-sett-list-right">'+
															'<p id="'+u920.id +'" uv922="'+u920.uv922+'" class="qb-sett-list-right-btn btn-delete-domain"><i class="fa fa-trash-o"></i></p>'+
													'</li>';
								}
								$('.qb-setting-content .qb-setting-content-property .qb-sett-list-domain').html(htmlString);
								$('.qb-ohhay-setting-wrapper .qb-setting-wrapper .qb-setting-content .qb-sett-list').niceScroll();
							} else {
								var nodata = '<span class="no-data-found">'
									+ getLocalize('no_data') + '</span>';
								$('.qb-setting-content .qb-setting-content-property .qb-sett-list-domain').html(nodata);
							}
						},
						error : function(e) {
							showGrowlMessageAjaxError();
						}
					});
			$('.qb-ohhay-setting-wrapper .qb-setting-wrapper .qb-setting-content .qb-sett-list').getNiceScroll().show();
			
		},
		/*
		 * ThoaiVt
		 * 28/01/2016
		 * check validate domain 
		*/
		checkValidate : function(url){
			var partReg = /^[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9](?:\.[a-zA-Z]{2,})+/;
			return partReg.test(url);
		},
		/**
		 * @author ThienND
		 * created Feb 17, 2016
		 * check validate domain 2, chỉ được insert 1 domain mỗi lần
		 */
		checkValidate2 : function(url){
			var pattern = /^[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)$/ig;
			return pattern.test(url);
		},
		checkRightAddDomain: function(){
			$.ajax({
				type: 'POST',
				url: encodeUrl('setting.checkRightAddDomain'),
				data : {},
				success : function(response) {
					if(response.result == "RE_VAILD_RIGHT"){
						location.hash = 'add-domain';
						$('.qb-setting-input-domain').fadeIn();
					}
					else
						util.confirmDialog(response.result, function(){
							window.location = util.contextPath()+"/pricing";
						});
				},
				error : function(e){
					showGrowlMessageAjaxError();
				}
			})
		},
	}
}());