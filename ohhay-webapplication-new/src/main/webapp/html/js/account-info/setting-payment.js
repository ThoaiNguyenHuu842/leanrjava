(function() {
	settingPayment = {
		SKUID: 0,
		init : function() {
			settingPayment.eventListener();
		},
		eventListener : function() {
			$(document).on('click',".qb-setting-input-upgrade .btn-upgrade-nextstep", function(){
				settingPayment.onClickSubmitPayment();
			});
			$(document).on('click',".btn-upgrade", function(){
				if(settingPayment.SKUID == 0)
					showGrowlMessage("Please select one package");
				else {
					settingProfile.showTabInputUpgrade('qb-setting-input-upgrade-account-infor',getLocalize("info_account"));
					settingProfile.getListCountry();
				}
			});
			//onclick packet upgrade
			$(document).on('click touchend',".qb-setting-input-upgrade .qb-radio", function(){
				$(".qb-setting-input-upgrade .qb-radio").removeClass('qb-radio-selected');
				$(this).addClass('qb-radio-selected');
				settingPayment.SKUID = $(this).attr("packid");
			});
		},
		onClickSubmitPayment: function(){
				if($(".qb-setting-input-upgrade #input-birthday").val().length == 0)
					util.messageDialog(getLocalize("setting_payment_message_error1"));
				else {
					var widgetType = "p1_1";
					var userAddress = $(".qb-setting-input-upgrade #input-address").val();
					var userBirthDateString = $(".qb-setting-input-upgrade #input-birthday").val();
					var userCity = $(".qb-setting-input-upgrade #input-city").val();
					var userCountry = $(".qb-setting-input-upgrade #input-country button b").html();
					var userZip = $(".qb-setting-input-upgrade #input-zipcode").val();
					var userFisrtName = $(".qb-setting-input-upgrade #input-fname").val();
					var userLastName = $(".qb-setting-input-upgrade #input-lname").val();
					var userState = $(".qb-setting-input-upgrade #input-state").val();
					var userSex = $(".qb-setting-input-upgrade #input-gender").val();
					var userEmail = $(".qb-setting-input-upgrade #input-email").val();
					$.ajax({	
						type : "POST",
						url : encodeUrl("setting.getPayMentWidget"),
						data : "widgetType=" + encodeURIComponent(widgetType) + 
							   "&skuid=" + encodeURIComponent(settingPayment.SKUID)+
							   "&userAddress=" + encodeURIComponent(userAddress)+
							   "&userBirthDateString=" + encodeURIComponent(userBirthDateString)+
							   "&userCity=" + encodeURIComponent(userCity)+
							   "&userCountry=" + encodeURIComponent(userCountry)+
							   "&userZip=" + encodeURIComponent(userZip)+
							   "&userFisrtName=" + encodeURIComponent(userFisrtName)+
							   "&userLastName=" + encodeURIComponent(userLastName)+
							   "&userState=" + encodeURIComponent(userState)+
							   "&userSex=" + encodeURIComponent(userSex)+
							   "&userEmail=" + encodeURIComponent(userEmail),
						success : function(response) {
							if (response.status == AJAX_SUCESS) {
								if (response.result > 0)
								{
									settingProfile.showTabInputUpgrade('qb-setting-input-upgrade-payment-ui',getLocalize("upgrade"));
									$(".qb-setting-input-upgrade-payment-ui").html(response.result2);
								}
								else
									showGrowlMessageError();
							} else{
								showGrowlMessage(response.result[0].defaultMessage);
							}
						},
						error : function(e) {
							showGrowlMessageAjaxError();
						}
					});
				}
			}
	}
}());