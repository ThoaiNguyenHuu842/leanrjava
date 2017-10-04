/*
 * ThoaiNH
 * create 12/09/2014
 */
(function() {		
	settingProfile = {
		init : function() {
			// settingProfile.eventListener();
			settingProfile.eventListenerNew();
			settingProfile.checkGoogleLoginState();
		},
		/*
		 * event listener new
		 */
		eventListenerNew : function() {
			$(document).on('click','#btn-set-account-fb', settingProfile.checkFacebookLoginState); 
			//init date picker
			$( ".qb-setting-input-content.qb-setting-input-account #qb-input-birthdate" ).datepicker(
				{
					dateFormat: 'dd/mm/yy',
					changeMonth: true,
		            changeYear: true,
		            yearRange: '-70:+0',
		            maxDate: new Date()
				}
			);
			//init avarta editor
			avatarImage.init();			
			$("#dialog-image-avarta").dialog({
				width: 428,
			    height: 428,
			    autoOpen: false,
			    modal: true,  
			    resizable: false,
			    closeOnEscape: true,
			    draggable: false,
			     position: {
			            my: 'top', 
			            at: 'top'
			     },
			     show : 'fade',
			     hide : 'fade'
			});
			$("#dialog-image-avarta").dialog("close");
			$(document).on('click','.qb-setting-content-child .btn-button-upload', function(){
				$("#dialog-image-avarta").dialog("close");
	        	$("#dialog-image-avarta").dialog("open");
	        	$("#dialog-image-avarta").dialog({resizable:false});
	        	if(avatarImage.$image_edit_avarta == ''){
	        		$('.cropper-edit-img-avarta').attr('src', $('#image_account').attr('src'));
	        		$('.wrapper-nav .nav-avatar button .fix-padding img').attr('src',imgBase64);
	        		$('.cropper-edit-img-avarta').css({
	        			width: '428',
	        			height: '316'
	        		})
	        		avatarImage.$image_edit_avarta = $('.cropper-edit-img-avarta');
		        	avatarImage.cropImage();
	        	}
			})
			
			//close input panel
			$(document).on('click',".qb-setting-input-content .qb-setting-input-header-back", function(){
				$('.qb-setting-input-content').fadeOut();
				$('body').css('overflow','auto');
			});
			// event khi over khoi confirm email smtp
			$("#qb-input-confirm-email-smtp").blur(function(){
				var email = $("#qb-input-confirm-email-smtp").val();
				if (email != '')
				settingProfile.autoFillProfileEmail(email);
		    });
			// show input keyword
			$(document).on('click',".qb-setting-content-property .btn-add-keyword", function(){
				$('.qb-setting-input-content.qb-setting-input-keyword').fadeIn();
			});
			// show input account first name
			$(document).on('click',".qb-setting-content-account .btn-edit-firstname", function(){
				var fname = $('.qb-setting-content-account #qb-account-firstname').text();
				$('.qb-setting-input-account #qb-input-firstname-name').val(fname);
				settingProfile.showTabInputAccount('qb-setting-input-panel-firstname', getLocalize("home_first_name"));
			});
			// show input account first name
			$(document).on('click',".qb-setting-content-account .btn-edit-lastname", function(){
				var lname = $('.qb-setting-content-account #qb-account-lastname').text();
				$('.qb-setting-input-account #qb-input-lastname-name').val(lname);
				settingProfile.showTabInputAccount('qb-setting-input-panel-lastname', getLocalize("home_last_name"));
			});
			// show input account birthdate
			$(document).on('click',".qb-setting-content-account .btn-edit-birthdate", function(){
				var birthDate = $('.qb-setting-content-account #qb-account-birthdate').text();
				var comp = birthDate.split('/');
				var m = parseInt(comp[1], 10);
				var d = parseInt(comp[0], 10);
				var y = parseInt(comp[2], 10);
				var date = new Date(y,m-1,d);
				if(date.getTime()){
					$( ".qb-setting-input-content.qb-setting-input-account #qb-input-birthdate" ).val(birthDate);
				}else{
					$( ".qb-setting-input-content.qb-setting-input-account #qb-input-birthdate" ).val("");
				}
				settingProfile.showTabInputAccount('qb-setting-input-panel-birthdate', getLocalize("birthday"));
				
			});
			// show input account sexiness
			$(document).on('click',".qb-setting-content-account .btn-edit-sexiness", function(){
				var value = $('.qb-setting-content-account #qb-account-sexiness').attr('value');
				$('.qb-radio-group-sexiness .qb-radio').removeClass('qb-radio-selected');
				$('.qb-radio-group-sexiness .qb-radio-'+value).addClass('qb-radio-selected');
				settingProfile.showTabInputAccount('qb-setting-input-panel-sexiness', getLocalize("sexiness"));
			});
			// show input account email
			$(document).on('click',".qb-setting-content-account .btn-edit-email", function(){
				settingProfile.showTabInputAccount('qb-setting-input-panel-email', getLocalize("home_email"));
			});
			// show input account smtp email
			$(document).on('click',".qb-setting-content-account .btn-edit-smtp", function(){
				var smtpEmail = $('.sett-plugin-list-content #qb-account-smtp').text();
				$('#qb-input-new-ssl').val('');
				$('#qb-input-new-smmtp').val('');
				$('#qb-input-new-port').val('');
				if (smtpEmail != '')
					settingProfile.loadList();
				settingProfile.showTabInputAccount('qb-setting-input-panel-smtp', "SMTP Email");
			});
			// send mail again
			$(document).on('click',".qb-setting-content-account .btn-sendmail-again", function(){
				settingProfile.sendMailAgain();
			});
			// show input pro
			$(document).on('click',".qb-setting-content-account .btn-edit-pro", function(){
				var currentJob = $('.qb-setting-content-account #qb-account-pro').text();
				settingProfile.showTabInputAccount('qb-setting-input-panel-pro',"Professional");
				$('.qb-setting-input-account #qb-input-job').text(currentJob);
			});
			// show input language
			$(document).on('click',".qb-setting-content-account .btn-edit-language", function(){
				settingProfile.showTabInputAccount('qb-setting-input-panel-language',getLocalize("language"));
				var currentLang = $('.qb-setting-content-account #qb-account-rlang').attr('value');
				$('.qb-radio-group-rlang .qb-radio').removeClass('qb-radio-selected');
				$('.qb-radio-group-rlang .qb-radio-'+currentLang).addClass('qb-radio-selected');
			});
			// show input upgrade
		/*	$(document).on('click',".btn-edit-upgrade", function(){
				console.log('show upgrade');
				settingProfile.showTabInputUpgrade('qb-setting-input-upgrade-version',getLocalize("upgrade"));
				$('.qb-ohhay-setting-wrapper .qb-setting-input-content .qb-setting-input-body').niceScroll();
				location.hash = "upgrade";
			});*/
			// choice country in upgrade
			$(document).on('click',"#input-country ul li", function(){
				$('#input-country button').val($(this).val());
				$('#input-country button b').text($(this).children('a').text());
			});
			
			/*
			 * onclick save action
			 */
			$(document).on('click', '.qb-setting-input-account #qb-btn-save-firstname', this.saveAccountFirstName);
			$(document).on('keydown','.qb-setting-input-account #qb-input-firstname-name', function(e){
				if (e.keyCode == 13) 
					settingProfile.saveAccountFirstName();
			});
			$(document).on('click', '.qb-setting-input-account #qb-btn-save-lastname', this.saveAccountLastName);
			$(document).on('keydown','.qb-setting-input-account #qb-input-lastname-name', function(e){
				if (e.keyCode == 13) 
					settingProfile.saveAccountLastName();
			});
			$(document).on('click', '.qb-setting-wrapper .input-field .btn-save-pass-bonevo', this.saveAccountPassWord);
			$(document).on('click', '.qb-setting-input-account #qb-btn-save-birthdate', this.saveAccountBirthDate);
			$(document).on('click', '.qb-setting-input-account #qb-btn-save-email', this.changeEmailNew);
			$(document).on('click', '.qb-setting-input-account .qb-dropdown-all-jobs li',this.saveAccountJob);
			$(document).on('click', '.qb-setting-input-account .qb-dropdown-all-jobs li',this.saveAccountJob);
			// onclick radio sexiness
			$(document).on('click',".qb-setting-input-account .qb-radio-group-sexiness .qb-radio", function(){
				if(!$(this).hasClass('qb-radio-selected'))
					settingProfile.saveAccountSexiness($(this).attr('value'));
			});
			// onclick radio rlang
			$(document).on('click',".qb-setting-input-account .qb-radio-group-rlang .qb-radio", function(){
				if(!$(this).hasClass('qb-radio-selected'))
					settingProfile.saveAccountRLang($(this).attr('value'));
			});
			$(document).on('click','.qb-setting-input-account #qb-btn-save-smtp',this.changeSMTPEmail	);
			//render hash
			if(location.hash && location.hash.length > 0)
			{
				var hash = location.hash.substring(1,location.hash.length);
				settingProfile.renderUIByHash(hash);
			}
			else
				settingProfile.renderUIByHash("");
			//remove google account
			$(document).on('click',"#btn-remove-account-gg", function(){
				settingProfile.removeGGaccount();
			});
			//remove fb account
			$(document).on('click',"#btn-remove-account-fb", function(){
				settingProfile.removeFBaccount();
			});
			// onclick set BONEVO password
			$(document).on('click',"#qb-btn-save-password-frist", function(){
				var pass = $('#qb-input-password-new-first').val();
				var rePass = $('#qb-input-password-new-again-first').val();
				settingProfile.setPasswordFirst(pass,rePass);
			});
		},
		renderUIByHash:function(hash)
		{		
			try{
				switch (hash) {
				case 'upgrade':
					//$('.btn-edit-upgrade').trigger('click');
					break;
				default:
					break;
				}
			}
			catch(err){
				
			}
		},
		/*
		 * action save avarta home
		 */
        saveImageAvartaBase64: function () {
            var currentLanguage = $('#qb-input-currentLanguage').val();
            var extend = $('#qb-input-extend').val();
            var cv905 = $('#dialog-image-avarta #inputcv905ImageHome').val();
            var pc900 = $('#dialog-image-avarta #inputpc900ImageHome').val();
            var cv902 = $('#dialog-image-avarta #inputcv902ImageHome').val();
            var imgBase64 = $(
                    '#dialog-image-avarta #input-img64-edit-img-avarta').val();
            var data = new FormData();
            data.append('avartaBase64', imgBase64);
            data.append('pc900', pc900);
            data.append('cv902', cv902);
            data.append('cv905', cv905);
            data.append('languageCode', currentLanguage);
            $.ajax({
                type: "POST",
                url: encodeUrl("setting.saveAvarta"),
                cache: false,
                contentType: false,
                processData: false,
                data: data,
                success: function (response) {
                    if (response.status == AJAX_SUCESS) {
                        $('#dialog-image-avarta').jsOhhayDialog('close');
                        if (imgBase64.length > 0)
                            $('.sett-tab-account-info-img img').attr('src', imgBase64);
                    } else {
                    }
                },
                error: function (error) {
                    $("#qb-dialog-message").jsOhhayDialog();
                }
            });
        },
		saveAccountPrivacyPhone: function(value){
			var fieldName = "MN906";
			settingProfile.saveAccountOneField(fieldName, value);
			// close dropdown
			$('.qb-sett-filter-hide').trigger('click');
			if(value == 0)
				$('.qb-setting-content-account #qb-account-privacy-phone').html(getLocalize("setting_onlyme"));
			else
				$('.qb-setting-content-account #qb-account-privacy-phone').html(getLocalize("setting_everyone"));
		},
		/*
		 * save account privacy email
		 */
		saveAccountPrivacyEmail: function(value){
			var fieldName = "MN912";
			settingProfile.saveAccountOneField(fieldName, value);
			// close dropdown
			$('.qb-sett-filter-hide').trigger('click');
			if(value == 0)
				$('.qb-setting-content-account #qb-account-privacy-email').html(getLocalize("setting_onlyme"));
			else
				$('.qb-setting-content-account #qb-account-privacy-email').html(getLocalize("setting_everyone"));
		},
		/*
		 * save account first name
		 */
		saveAccountFirstName: function(){
			var fieldName = "MV901";
			var value = $('.qb-setting-input-account #qb-input-firstname-name').val().trim();
			settingProfile.saveAccountOneField(fieldName, value);
		},
		/*
		 * save account last name
		 */
		saveAccountLastName: function(){
			var fieldName = "MV902";
			var value = $('.qb-setting-input-account #qb-input-lastname-name').val().trim();
//			((value=="")?util.messageDialog('Last Name not null') : settingProfile.saveAccountOneField(fieldName, value));
			settingProfile.saveAccountOneField(fieldName, value);
		},
		/*
		 * save account password
		 */
		saveAccountPassWord: function(){
			var passold = $('#qb-input-password-old').val();
			var passnew = $('#qb-input-password-new').val();
			var passnewagain = $('#qb-input-password-new-again').val();
			settingProfile.saveAccountChangePassword(passold, passnew, passnewagain);
		},
		/*
		 * save account birthdate
		 */
		saveAccountBirthDate: function(){
			var fieldName = "MD904";
			var value = $( ".qb-setting-input-content.qb-setting-input-account #qb-input-birthdate" ).val();
			var comp = value.split('/');
			var m = parseInt(comp[1], 10);
			var d = parseInt(comp[0], 10);
			var y = parseInt(comp[2], 10);
			var date = new Date(y,m-1,d);
			//compare with today
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
			var yyyy = today.getFullYear();
			var dateFix = new Date(1946,0,1);
			
			console.log("CHECK : "+date.getTime()<=today.getTime()+ " MMMSS : ");
			console.log("CSSSS : "+date.getTime()>=dateFix.getTime());
			if (date.getFullYear() == y && date.getMonth() + 1 == m && date.getDate() == d && date.getTime()<=today.getTime() && date.getTime()>=dateFix.getTime()) {
				settingProfile.saveAccountOneField(fieldName, value);
			} else {
				util.messageDialog('Invalid date')
			}
			
		},
		/*
		 * save account sexiness
		 */
		saveAccountSexiness: function(value){
			var fieldName = "MV905";
			settingProfile.saveAccountOneField(fieldName, value);
		},
		/*
		 * save job
		 */
		saveAccountJob: function(){
			if($(".qb-ohhay-ajax-content").css('display') == 'none'){
				var value = $(this).attr('value')+"#"+$(this).find('a').html();
				var fieldName = "JOB";
				settingProfile.saveAccountOneField(fieldName, value);
			}
		},
		/*
		 * on save change email
		 * 
		 */
		changeEmailNew: function(){
			var newEmail = $('.qb-setting-input-account #qb-input-new-email').val();
			var passWordConfirm = $('.qb-setting-input-account #qb-input-confirm-pass').val();
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.changeEmail"),
				data : "newEmail=" + encodeURIComponent(newEmail) + "&passwordConfirm="
				+ encodeURIComponent(passWordConfirm),
				success : function(response) {	
					var resultMessage = '';
					if (response.status == AJAX_SUCESS) {
						if(response.result > 0 )
						{
							$('.qb-setting-input-content.qb-setting-input-account').fadeOut();
							// setHtml(".group-setting-private
							// #changemail-mess-success",getLocalize("setting_changemail_message1"))
							resultMessage = getLocalize("setting_changemail_message1");
							$(".qb-setting-content-account #qb-not-confirm-email").html(newEmail);
							$(".qb-setting-content-account .panel-not-confirm-mail").css('display','block');
						}
						else if(response.result == -1 )
							resultMessage = getLocalize("setting_changemail_error1");
							// setHtml(".group-setting-private
							// #changemail-mess-error",getLocalize("setting_changemail_error1"));
						else if(response.result == -2 )
							resultMessage = getLocalize("setting_changemail_error2");
							// setHtml(".group-setting-private
							// #changemail-mess-error",getLocalize("setting_changemail_error2"));
						else if(response.result == -3 )
							resultMessage = getLocalize("setting_changemail_error3");
							// setHtml(".group-setting-private
							// #changemail-mess-error",getLocalize("setting_changemail_error3"));
						else
							resultMessage = getLocalize("setting_changemail_error4");
							// setHtml(".group-setting-private
							// #changemail-mess-error",getLocalize("setting_changemail_error4"));
					} else
					{
						resultMessage = getLocalize("message_ajaxunsuccess");
						// setHtml(".group-setting-private
						// #changemail-mess-error",getLocalize("message_ajaxunsuccess"));
					}
					showGrowlMessage(resultMessage);
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * save account rlang
		 */
		saveAccountRLang: function(value){
			var fieldName = "LANGUAGE";
			settingProfile.saveAccountOneField(fieldName, value);
		},
		/*
		 * load list combo box jobs type
		 */
		loadListD000New : function() {
			// khong load lai
			// if ($('.group-setting-private #select-job').html().trim().length
			// == 0) {
				/*
				 * $.ajaxSetup({ beforeSend : function() { }, complete :
				 * function() { $(".group-setting-private
				 * .ajax-content-profile").css('display','none');
				 * setDefaultAjaxStatus(); } });
				 */
				$.ajax({
					url : encodeUrl("setting.loadListD000"),
					data : {},
					success : function(response) {
						var list = response.result;
						var htmlString = '';
						for (i = 0; i < list.length; i++) {
							htmlString += "<li value='"+list[i].val+"'><a href='#'>"+list[i].label+"</a></li>";
						}
						$('.qb-setting-input-account .qb-dropdown-all-jobs').html(htmlString);
						var val =  $('.qb-setting-content-account #qb-account-pro').attr('val');
						$('.qb-setting-content-account #qb-account-pro').html($(".qb-dropdown-all-jobs li[value='"+val+"'] a").html());
					},
					error : function(e) {
						showGrowlMessageAjaxError();
					}
				});
			// }
		},
		/*
		 * render tab by class
		 */
		showTabInputAccount: function(showClass,tabName){
			$('.qb-setting-input-content.qb-setting-input-account .sett-tab-title label').text(tabName);
			$('.qb-setting-input-content.qb-setting-input-account').fadeIn();
			$('.qb-setting-input-content.qb-setting-input-account .qb-setting-input-sub-panel').addClass('qb-setting-input-unvisible-panel');
			$('.qb-setting-input-content.qb-setting-input-account .'+showClass).removeClass('qb-setting-input-unvisible-panel');
		},
		showTabInputUpgrade: function(showClass, titleName){
			$('.qb-setting-input-content.qb-setting-input-upgrade').fadeIn();
			$('.qb-setting-input-content.qb-setting-input-upgrade .qb-setting-input-header-title label').text(titleName);
			$('.qb-setting-input-content.qb-setting-input-upgrade .qb-setting-input-upgrade-item').hide();
			$('.qb-setting-input-content.qb-setting-input-upgrade .'+showClass).show();
		},
		// show input upgrade from comfirm dialog
		showTabInputUpgradeFormDialog: function(showClass, titleName){
			$('.qb-setting-input-content.qb-setting-input-upgrade').fadeIn();
			$('.qb-setting-input-content.qb-setting-input-upgrade .qb-setting-input-header-title label').text(titleName);
			$('.qb-setting-input-content.qb-setting-input-upgrade .qb-setting-input-upgrade-item').hide();
			$('.qb-setting-input-content.qb-setting-input-upgrade .'+showClass).show();
			$('.dialogconfirm').jsOhhayDialog("close");
		},
		/*
		 * Get list CombTabN750
		 */
		getListCountry: function(){
			$.ajax({
				url : encodeUrl("setting.getListCountry"),
				data :{},
				success : function(response) {
					var list = response.result;
					var htmlString = '';
					for (i = 0; i < list.length; i++) {
						htmlString += "<li value='"+list[i].val+"'><a>"+list[i].label1+"</a></li>";
					}
					$('#input-country ul').html(htmlString);
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * save account M900
		 */
		saveAccountOneField : function(fieldName, value) {
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.saveAccountOneField"),
				data : "fieldName=" + encodeURIComponent(fieldName) + "&value="
						+ encodeURIComponent(value),
				success : function(response) {
					if (response.status == AJAX_SUCESS) {
						if (response.result > 0) {
							// setHtml(".group-setting-private
							// #account-mess-success",
							// getLocalize("message_ajaxsuccess"));
							// setHtml(".group-setting-private
							// #account-mess-error", "");
							showGrowlMessageSuccess();
							/*
							 * reset content ui
							 */
							if(fieldName == 'MV901') {
								 $('.qb-setting-content-account #qb-account-firstname').text(value);
								 $('.qb-setting-input-content').fadeOut();
							}
							else if(fieldName == 'MV902'){
								$('.qb-setting-content-account #qb-account-lastname').text(value);
								$('.qb-setting-input-content').fadeOut();
							}
							else if(fieldName == 'MD904')
							{
								$('.qb-setting-content-account #qb-account-birthdate').text(value);
								$(".qb-setting-input-upgrade #input-birthday").val(value);
								$('.qb-setting-input-content').fadeOut();
							}
							else if(fieldName == 'MV905')
							{
								$('.qb-radio-group-sexiness .qb-radio').removeClass('qb-radio-selected');
								$('.qb-radio-group-sexiness .qb-radio-'+value).addClass('qb-radio-selected');
								$('.qb-setting-content-account #qb-account-sexiness').attr('value', value);
								if(value == 'F')
									$('.qb-setting-content-account #qb-account-sexiness').text(getLocalize("female"));
								else if(value == 'M')
									$('.qb-setting-content-account #qb-account-sexiness').text(getLocalize("male"));
								else
									$('.qb-setting-content-account #qb-account-sexiness').text(getLocalize("undefined"));
								$('.qb-setting-input-content').fadeOut();
							}
							else if(fieldName == 'LANGUAGE')
							{
								$('.qb-radio-group-rlang .qb-radio').removeClass('qb-radio-selected');
								$('.qb-radio-group-rlang .qb-radio-'+value).addClass('qb-radio-selected');
								$('.qb-setting-content-account #qb-account-rlang').attr('value', value);
								if(value == 'vi'){
									$('.qb-setting-content-account #qb-account-rlang').text(getLocalize("language_vie"));
									qbRedirect( $('#contextPath').val() + "/account?language=vi", false);
								}
								else if(value == 'de')
								{
									$('.qb-setting-content-account #qb-account-rlang').text(getLocalize("language_german"));
									qbRedirect( $('#contextPath').val() + "/account?language=de", false);
								}
								else{
									$('.qb-setting-content-account #qb-account-rlang').text(getLocalize("language_english"));
									qbRedirect( $('#contextPath').val() + "/account?language=en", false);
								}
								
							}
							else if(fieldName == 'JOB'){
								$('.qb-setting-content-account #qb-account-pro').text(value.split('#')[1]);
								$('.qb-setting-input-content').fadeOut();
							}
						} 
						else
							setHtml(".group-setting-private #account-mess-success", getLocalize("message_ajaxerror"));	
					} else
					{
						setHtml(".group-setting-private #account-mess-success", "");
						showGrowlMessage(response.result[0].defaultMessage);
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});

		},
		/*
		 * on change password
		 */
		saveAccountChangePassword : function(passold, passnew, passnewagain) {
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.changePassword"),
				data : "qv102Old=" + encodeURIComponent(passold)
				+ "&qv102New=" + encodeURIComponent(passnew)
				+ "&qv102ReNew=" + encodeURIComponent(passnewagain),
				success : function(response) {
					if (response.status == AJAX_SUCESS) {
						if (response.result == 1) {
							$('.error-mess-change-pass').html((getLocalize("setting.changepassword.success")));
							redirectTo(util.contextPath() + "/login");
						} else if (response.result == -3)
							$('.error-mess-change-pass').html((getLocalize("setting_changepassword_message_error6")));
						else if (response.result == -2)
							$('.error-mess-change-pass').html(getLocalize("setting_changepassword_message_error4"));
						else if (response.result == -1)
							$('.error-mess-change-pass').html(getLocalize("setting_changepassword_message_error7"));
						else
							$('.error-mess-change-pass').html(response.result[0].defaultMessage);
					}else
						$('.error-mess-change-pass').html(response.result[0].defaultMessage);
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});

		},
		/*
		 * SEND MAIL AGAIN
		 */
		sendMailAgain : function(){
			$('.dialogconfirm').jsOhhayDialog('close');
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.sendEmailAgain"),
				data :{},
				success : function(response) {	
					var resultMessage = '';
					if (response.status == AJAX_SUCESS) {
						if(response.result > 0 )
						{
							resultMessage = getLocalize("setting_sendemailagain_message");
						}
						else if(response.result == -1 )
							resultMessage = getLocalize("setting_changemail_error1");
						else if(response.result == -2 )
							resultMessage = getLocalize("setting_changemail_error2");
						else if(response.result == -3 )
							resultMessage = getLocalize("setting_changemail_error3");
						else
							resultMessage = getLocalize("setting_changemail_error4");
					} else
					{
						resultMessage = getLocalize("message_ajaxunsuccess");
					}
					showGrowlMessage(resultMessage);
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		
		/*
		 * save smtp
		 */
		
		changeSMTPEmail:function(){
			var email=$('#qb-input-confirm-email-smtp').val();
			var ssl=$('#qb-input-new-ssl').val();
			var smtpemail=$('#qb-input-new-smmtp').val();
			var port=$('#qb-input-new-port').val();
			var link = "<a href='https://support.google.com/accounts/answer/6010255?hl=en'"+"target='_blank'> Click here</a>";
			var password=$('#qb-input-confirm-pass-smtp').val();
			if(email && email.length > 0 && password && password.length >0){
				$.ajax({
						type : "POST",
						url : encodeUrl("setting.changeSMTPEmail"),
						data : "&nv119="+password+"&nv120="+email+"&nv116="+ssl+"&nv117="+smtpemail+"&nv118="+port,
						success: function(response) {	
							if (response.status == AJAX_SUCESS && response.result > 0) 
							{
								showGrowlMessageSuccess();
								$(".qb-setting-content-account #qb-account-smtp").html(email);
								$('.qb-setting-input-content').fadeOut();
								$("#set-error").fadeOut();
							}
							else {
								$("#set-error").css('display', 'block');
								$("#set-error").html('');
								var messObject = response.result;
								$("#set-error").html(messObject[0].defaultMessage+link);
							}
							
					},
					error : function(e) {
						showGrowlMessage(response.result[0]);
					}
				});
			}
		},

		/*
		 * auto fill author : phongdt create day :31/7/2015
		 */
		autoFillProfileEmail:function(email){
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.autoFillProfileEmail"),
				data : "email="+email,
				success: function(response) {
					if (response.status == AJAX_SUCESS) 
					{
						var list = response.result;
						$("#qb-input-new-ssl").val(list[0].style);
						$("#qb-input-new-smmtp").val(list[0].host);
						$("#qb-input-new-port").val(list[0].port);
						$("#set-error").fadeOut();
					}
					else{
						$("#set-error").css('display', 'block');
						$("#set-error").html('');
						$("#set-error").html(getLocalize('setting_error'));
// $("#set-error").fadeIn().delay(8000).fadeOut('slow');
						$("#set-error").fadeIn();
					}
					
					
						

			},
			error : function(e) {
				//showGrowlMessageAjaxError();
			}
		});
		},
		/*
		 * load infomation full author : phongdt create day 4/8/2015
		 */
		loadList:function(){
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.loadSMTP"),
				data : {},
				success : function (response) {
					var list = response.result;
					for(i=0 ; i< list.length ; i++)
					{
						$("#qb-input-confirm-email-smtp").val(list[i].nv120);
						$("#qb-input-confirm-pass-smtp").val(list[i].nv119);
						$("#qb-input-new-ssl").val(list[i].nv116);
						$("#qb-input-new-smmtp").val(list[i].nv117);
						$("#qb-input-new-port").val(list[i].nv118);
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * remove gg acount
		 */
		removeGGaccount : function(){
			$('.dialogconfirm').jsOhhayDialog('close');
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.removeGoogleAccount"),
				data :{},
				success : function(response) {	
					showGrowlMessageSuccess();
					window.location.reload();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * remove fb acount
		 */
		removeFBaccount : function(){
			$('.dialogconfirm').jsOhhayDialog('close');
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.removeFacebookAccount"),
				data :{},
				success : function(response) {	
					showGrowlMessageSuccess();
					window.location.reload();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * google sign in
		 */
		checkGoogleLoginState:function(){
			var googleUser = {};
			gapi.load('auth2', function(){
			      // Retrieve the singleton for the GoogleAuth library and set up the client.
			      auth2 = gapi.auth2.init({
			        client_id: '165118714590-8o0nmamr2ivkqh5cd9hju1btv4dit2a2.apps.googleusercontent.com',
			        cookiepolicy: 'single_host_origin',
			        // Request scopes in addition to 'profile' and 'email'
			        //scope: 'additional_scope'
			      	});
			      auth2.attachClickHandler($('#btn-set-account-gg')[0], {}, function(googleUser) {
			    	  var result = {
			    			 email: googleUser.getBasicProfile().getEmail(),
			    			 first_name: googleUser.getBasicProfile().getGivenName(),
			    			 last_name: googleUser.getBasicProfile().getFamilyName(),
			    			 id: googleUser.getBasicProfile().getId()
			    	  };
			    	  console.log(result);
			    	  settingProfile.addGoogleAccount(result.email, result.id);
					}, function(error) {
						console.log(JSON.stringify(error, undefined, 2));
					});
			});
		},
		/*
		 * get FB info
		 */
		getFacebookInfo:function(){
			FB.api('/me','GET',{'fields':'first_name,last_name,name,email,id'},function(result){
				console.log(result);
				if(!result.email && result.email.length == 0)
					showGrowlMessage("jslog_title1");
				else
					settingProfile.addFacebookAccount(result.email, result.id);
			});
		},
		/*
		 * check FB login stt
		 */
		checkFacebookLoginState:function(){
			FB.getLoginStatus(function(result){
				console.log(result);
				if (result.status === 'connected') {
					settingProfile.getFacebookInfo();
				} else if (result.status === 'not_authorized') {
					settingProfile.loginFacebook();
				} else {
					settingProfile.loginFacebook();
				}
			});
		},
		/*
		 * login to FB
		 */
		loginFacebook:function(){
			FB.login(function(result){
				settingProfile.checkFacebookLoginState();
			},{scope:'public_profile,email,user_photos'});
		},
		/*
		 * add Google Account
		 */
		addGoogleAccount: function(gv101,gv102){
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.addGoogleAccount"),
				data :{
					gv101:gv101,
					gv102:gv102
				},
				success : function(response) {	
					if(response.result > 0)
					{
						showGrowlMessageSuccess();
						window.location.reload();
					}
					else
						showGrowlMessage(getLocalize("aci_title13"));
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * add Facebook Account
		 */
		addFacebookAccount: function(fv101,fv102){
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.addFacebookAccount"),
				data :{
					fv101:fv101,
					fv102:fv102
				},
				success : function(response) {	
					if(response.result > 0)
					{
						showGrowlMessageSuccess();
						window.location.reload();
					}
					else
						showGrowlMessage(getLocalize("aci_title13"));
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * set BONEVO password when using social account
		 */
		setPasswordFirst: function(qv102New,qv102ReNew){
			$.ajax({
				type : "POST",
				url : encodeUrl("setting.setPasswordFirst"),
				data :{
					qv102New:qv102New,
					qv102ReNew:qv102ReNew
				},
				success : function(response) {	
					if (response.result == 1)
					{
						showGrowlMessageSuccess();
						window.location.reload();
					}
					else if (response.result == -3)
						showGrowlMessage((getLocalize("setting_changepassword_message_error6")));
					else if (response.result == -2)
						showGrowlMessage(getLocalize("setting_changepassword_message_error4"));
					else if (response.result == -1)
						showGrowlMessage(getLocalize("setting_changepassword_message_error7"));
					else
						showGrowlMessage(response.result[0].defaultMessage);
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		}
	}
}());