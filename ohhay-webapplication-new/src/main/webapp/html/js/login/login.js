/**
 * @author ThoaiNH create Oct 16, 2015 login EVO
 */
(function () {
	login = {
		REMEMBER: 1,// flag remember me
		dialogForGet: null,
		dialogNotConfirmMail: null,
		init: function () {
			console.log("INIT DATA !");
			this.eventListener();
			login.checkGoogleLoginState();
			$('.qb-ohhay-content-wrapper .template-col-panel .row').niceScroll();
			$(document).on('click', '.qb-ohhay-content-wrapper #top_menu_login .item_menu_top .item_menu .item.change_lang', function () {
				var lang = $(this).attr("lang");
				var url = util.contextPath();
				setParam("language", lang, document.URL);
			});
			$(document).on('click', '.btn-resend-confirm-mail', function () {
				login.resendConfirmEmail();
			});
			dialogForGet = $(".qb-ohhay-content-wrapper .dialog-forget-pass").dialog({
				autoOpen: false,
				modal: true,
				resizable: false,
				closeOnEscape: true,
				draggable: false,
				dialogClass: "dialog-forgetpass",
				width: 400,
				height: 230,
				position: {
					my: 'top',
					at: 'top'
				},
				show: 'fade',
				hide: 'fade'
			});
			dialogNotConfirmMail = $(".qb-ohhay-content-wrapper .dialog-email-not-confirm").dialog({
				autoOpen: false,
				modal: true,
				resizable: false,
				closeOnEscape: true,
				draggable: false,
				dialogClass: "dialog-forgetpass",
				width: 400,
				height: 230,
				position: {
					my: 'top',
					at: 'top'
				},
				show: 'fade',
				hide: 'fade'
			});

		},
		eventListener: function () {
			$(document).on('click', '.qb-ohhay-content-wrapper .content-account-login .main-login-content .close-form-login', function () {
				if (window.history.back() != undefined)
					window.history.back();
				else
					$('.qb-ohhay-content-wrapper .content-account-login .text-intro').trigger('click');
			});
			$(document).on('click ', '#btn-login', login.loginHome);
			$(document).on('click ', '#btn-login-saverequire', login.loginHomeSaveRequire);
			$(document).on('keydown', '#inputPassword', function (e) {
				login.onEnterLogin(e);
			});
			$(document).on('click', '.btn-fb-login', login.checkFacebookLoginState);
			$(document).on('click', '.btn-register', function () {
				window.location = util.contextPath() + "/signup";
			});
			$(document).on('click', '.start-a-blank', function () {
				window.location = util.contextPath() + "/trial";
			});
			$(document).on('click', '.checkbox-remember-me', function () {
				if ($(this).hasClass('checked')) {
					login.REMEMBER = 0;
					//					$(this).html('');
				} else {
					//					$(this).html('<i class="fa fa-check"></i>');
					login.REMEMBER = 1;
				}
				$(this).toggleClass('checked');
			});
			$(document).on('click', '.build-from-template', function () {
				window.location = util.contextPath() + "/evo-templates";
			});
			$(document).on('click', '.qb-ohhay-content-wrapper #close_dialog', function () {
				$('.qb-ohhay-content-wrapper .template-col-panel').fadeOut("300");
			});

			$(document).on('click', '#top_menu_login_evo .item_menu_top .item_menu .item .dropdown li', function () {
				var lang = $(this).attr("lang");
				var url = util.contextPath();
				setParam("language", lang, document.URL);
				$('#top_menu_login_evo .item_menu_top .item_menu .item .dropdown #dropdownMenu1').html("")
				$('#top_menu_login_evo .item_menu_top .item_menu .item .dropdown #dropdownMenu1').html("")
				var textLang = ((lang == "vi") ? "tiếng việt" : ((lang == "de") ? "Deutsch" : "English"));
				$('#top_menu_login_evo .item_menu_top .item_menu .item .dropdown #dropdownMenu1').html(textLang);

			});

			/*
			 * forget password ThoaiVt
			 */
			$(document).on('click', ".qb-ohhay-content-wrapper .content-account-login .data_component a.forget-password", function () {
				grecaptcha.reset();
				dialogForGet.dialog("open");
			});
			/*
			 * button send mail
			 */
			$(document).on('click', '.btn-forget-pass', function () {
				login.sendEmailResetPass();
			});
			/*
			 * button save change password
			 */
			$(document).on('click', '.qb-ohhay-content-wrapper .change-pass-forgetpass .content-data .item .btn-save-forgotpass', function () {
				login.saveResetPass();
			});
			/*
			 * event scroll to login and register
			 */
			$(document).on('click', '.fix_top_menu_login_evo .top_menu_login_evo .funct-account span', function () {
				var type = $(this).attr("type");
				var data_scroll = ((type == "login") ? $('#inputPhoneNumber').offset().top : $(".qb-ohhay-content-wrapper .content-account-login .data_component .button-login.btn-register").offset().top);
				$('body').animate({
					scrollTop: data_scroll
				}, function () {

				});
			});

			$(document).on('keydown', '#inputPassword', function (event) {

			});
		},
		onEnterLogin: function (event) {
			if (event.which == 13) {
				login.loginHome();
			}
		}, 
		/*
		 * login require (case lost session)
		 */
		loginHomeSaveRequire: function(){
			console.log("Login Require");
			var phone = $('#inputPhoneNumber').val();
			var password = $('#inputPassword').val();
			var regionCode = "84";
			var currentPhone = $('#qb-input-currentPhone').val();
			var remember = login.REMEMBER;
			$.ajaxSetup({
				beforeSend: function () {
					$(".ajax-content-login").show();
					$("#btn-login-saverequire").hide();
				},
				complete: function () {
					$(".ajax-content-login").hide();
					$("#btn-login-saverequire").show();
					setDefaultAjaxStatus();
				}
			});

			$.ajax({
				type: "POST",
				url: encodeUrl("loginBean.login"),
				data: "phone=" + encodeURIComponent(phone) + "&password=" + encodeURIComponent(password) + "&regionCode=" + encodeURIComponent(regionCode) + "&redirectPhone=" + encodeURIComponent(currentPhone) + "&remember=" + encodeURIComponent(remember) + "&src=" + encodeURIComponent("be"),
				success: function (response) {
					// we have the response
					if (response.status == AJAX_SUCESS) {
						if (response.result > 0) {
							$(".login-require-sesion").hide();
							$('#menu-tools .btn-menu-save').trigger('click');
						} else
							dialogNotConfirmMail.dialog("open");
						// util.messageDialog(response.resultString);
					} else {
						util.messageDialog(getLocalize("jslog_title2"));
						$('.data-dialog-close').focus();
					}
				},
				error: function (e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * login normal
		 */
		loginHome: function () {
			console.log("Login");
			var phone = $('#inputPhoneNumber').val();
			var password = $('#inputPassword').val();
			var regionCode = "84";
			var currentPhone = $('#qb-input-currentPhone').val();
			var remember = login.REMEMBER;
			$.ajaxSetup({
				beforeSend: function () {
					$(".ajax-content-login").show();
					$("#btn-login").hide();
				},
				complete: function () {
					$(".ajax-content-login").hide();
					$("#btn-login").show();
					setDefaultAjaxStatus();
				}
			});

			$.ajax({
				type: "POST",
				url: encodeUrl("loginBean.login"),
				data: "phone=" + encodeURIComponent(phone) + "&password=" + encodeURIComponent(password) + "&regionCode=" + encodeURIComponent(regionCode) + "&redirectPhone=" + encodeURIComponent(currentPhone) + "&remember=" + encodeURIComponent(remember) + "&src=" + encodeURIComponent("be"),
				success: function (response) {
					// we have the response
					if (response.status == AJAX_SUCESS) {
						if (response.result > 0) {
							// Anph: call token sender
							// đưa redirect vào callback để đợi socket chạy xong
							// mới chạy
							// login.sendLoginStack(response.resultString, function () {
							var redirectTo = getUrlParameter('re');
							if (redirectTo && redirectTo == 'merian')
								window.location = MERIAN_ROOT_PATH;
							else if (redirectTo)
								window.location = decodeURIComponent(redirectTo);
							else
								window.location = util.contextPath() + "/mysites";
							// });
							// Anph: call token sender

						} else
							dialogNotConfirmMail.dialog("open");
						// util.messageDialog(response.resultString);
					} else {
						util.messageDialog(getLocalize("jslog_title2"));
						$('.data-dialog-close').focus();
					}
				},
				error: function (e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * login by social
		 */
		loginBySocial: function (result) {
			console.log(result);
			/*
			 * var result = { email: "thoainguyen842@gmail.com", first_name:
			 * "thoai", id: "114756149491555777312", last_name: "nguyen huu",
			 * regisTypel: "GOO" };
			 */
			if (result["email"]) {
				var email = result["email"]
				var password = result["id"];
				var rePassword = result["id"];
				var fName = result["first_name"];
				var lName = result["last_name"];
				var regionCode = $("#selected-country").attr("regionCode");
				var languageName = $("#selected-country").attr("languageName");
				var languageCode = $("#selected-country").attr("languageCode");
				var countryCode = $("#selected-country").attr("countrycode");
				var regisTypel = result["regisTypel"];
				var fd000 = 2;
				var jobName = "IT";
				$.ajax({
					type: "POST",
					url: encodeUrl("loginBean.loginBySocial"),
					data: "phone=" + encodeURIComponent('') + "&fName=" + encodeURIComponent(fName) + "&lName=" + encodeURIComponent(lName) + "&email=" + encodeURIComponent(email) + "&passWord=" + encodeURIComponent(password) + "&rePassWord=" + encodeURIComponent(rePassword) + "&region=" + encodeURIComponent(regionCode) + "&languageName=" + encodeURIComponent(languageName) + "&languageCode=" + encodeURIComponent(languageCode) + "&countryCode=" + encodeURIComponent(countryCode) + "&regisTypel=" + encodeURIComponent(regisTypel) + "&socialId=" + encodeURIComponent(password),
					success: function (response) {
						if (response.status == AJAX_SUCESS) {
							if (login.REDIRECT_UTL && login.REDIRECT_UTL.trim().length > 0)
								window.location = login.REDIRECT_UTL;
							else
								window.location = util.contextPath() + "/mysites";
						} else {
							if (response.result2 == -5) {
								util.messageDialog(getLocalize("jslog_title3"));
								$('.data-dialog-close').focus();
								$('#inputPhoneNumber').val(email);
							}
						}
					},
					error: function (e) {
						// setHtml("#label-error-mess-3",getLocalize("signup_error4"));
						// alert(getLocalize("signup_error4"));
						util.messageDialog(getLocalize("signup_error4"));
						$('.data-dialog-close').focus();
					}
				});
			} else {
				util.messageDialog(getLocalize("jslog_title1"));
				$('.data-dialog-close').focus();
			}
			// alert("Sorry, We don't support social account without right
			// email!");
		},
		/*
		 * get FB info
		 */
		getFacebookInfo: function () {
			FB.api('/me', 'GET', {
				'fields': 'first_name,last_name,name,email,id'
			}, function (result) {
				result["regisTypel"] = "fb";
				console.log(result);
				login.loginBySocial(result);
			});
		},
		/*
		 * check FB login stt
		 */
		checkFacebookLoginState: function () {
			FB.getLoginStatus(function (result) {
				console.log(result);
				if (result.status === 'connected') {
					login.getFacebookInfo();
				} else if (result.status === 'not_authorized') {
					login.loginFacebook();
				} else {
					login.loginFacebook();
				}
			});
		},
		/*
		 * login to FB
		 */
		loginFacebook: function () {
			FB.login(function (result) {
				login.checkFacebookLoginState();
			}, {
					scope: 'public_profile,email,user_photos'
				});
		},
		/*
		 * google sign in
		 */
		checkGoogleLoginState: function () {
			var googleUser = {};
			gapi.load('auth2', function () {
				// Retrieve the singleton for the GoogleAuth library and set up
				// the client.
				auth2 = gapi.auth2.init({
					client_id: '165118714590-8o0nmamr2ivkqh5cd9hju1btv4dit2a2.apps.googleusercontent.com',
					cookiepolicy: 'single_host_origin',
					// Request scopes in addition to 'profile' and 'email'
					// scope: 'additional_scope'
				});
				auth2.attachClickHandler($('.btn-gg-login')[0], {}, function (googleUser) {
					var result = {
						email: googleUser.getBasicProfile().getEmail(),
						first_name: googleUser.getBasicProfile().getGivenName(),
						last_name: googleUser.getBasicProfile().getFamilyName(),
						id: googleUser.getBasicProfile().getId()
					};
					result["regisTypel"] = "gg";
					login.loginBySocial(result);
				}, function (error) {
					console.log(JSON.stringify(error, undefined, 2));
				});
			});
		},
		logout: function () {
			$.ajaxSetup({
				beforeSend: function () {
				},
				complete: function () {
					setDefaultAjaxStatus();
				}
			});
			$.ajax({
				type: "POST",
				url: encodeUrl("loginBean.logoutWeb"),
				data: {},
				success: function (response) {
					if (response.result > 0)
						//ANPH login.removeStackWhenLogout(function() {
						var redirectTo = getUrlParameter('re');

					if (redirectTo && redirectTo == 'topic')
						window.location = TOPIC_ROOT_PATH;
					else if (redirectTo && redirectTo == 'merian')
						window.location = MERIAN_ROOT_PATH;
					else if (redirectTo && redirectTo == 'relogin')
						window.location = OHHAY_ROOT_PATH + '/login';
					else if (redirectTo)
						window.location = decodeURIComponent(redirectTo);
					else
						window.location = util.contextPath();
					// });
				},
				error: function (e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * Test forget password
		 * 
		 */
		sendEmailResetPass: function () {
			var email = $('#inputEmail').val();
			console.log("SEND PASS TO EMAIL : " + email);
			$.ajaxSetup({
				beforeSend: function () {
					$(".dialog-forgetpass .dialog-forget-pass .data-content .funct .ajax-content-login").css('display', 'block');
					$(".dialog-forgetpass .dialog-forget-pass .data-content .funct button").css('display', 'none');
				},
				complete: function () {
					$(".dialog-forgetpass .dialog-forget-pass .data-content .funct .ajax-content-login").css('display', 'none');
					$(".dialog-forgetpass .dialog-forget-pass .data-content .funct button").css('display', 'block');
					setDefaultAjaxStatus();
				}
			});
			var response = grecaptcha.getResponse();

			if (response.length == 0)
				util.messageDialog("Please verify that you are not a robot !");
			else {
				$.ajax({
					type: "POST",
					url: encodeUrl("loginBean.sendEmailResetPass"),
					data: "email=" + encodeURIComponent(email),
					success: function (response) {
						// we have the response
						if (response.status == AJAX_SUCESS) {
							if (response.result == 1) {
								util.messageDialog(getLocalize("setting_sendemailagain_message"));
								dialogForGet.dialog("close");
							}
						} else
							util.messageDialog(getLocalize("setting_email_emailinvaild"));
					},
					error: function (e) {
						showGrowlMessageAjaxError();
					}
				});
			}
		},
		/*
		 * reset password
		 */
		saveResetPass: function () {
			var pass = $('#inputResetPassword').val();
			var passagain = $('#inputResetPasswordAgain').val();
			var email = getUrlParameter("email");
			var key = getUrlParameter("eid");
			if (pass != passagain) {
				util.messageDialog(getLocalize("setting_changepassword_message_error7"));
			} else {
				$.ajaxSetup({
					beforeSend: function () {
						$(".login-content .ajax-content-login").css('display', 'block');
						$(".login-content #btn-login").css('display', 'none');
					},
					complete: function () {
						$(".login-content .ajax-content-login").css('display', 'none');
						$(".login-content #btn-login").css('display', 'inline');
						setDefaultAjaxStatus();
					}
				});
				$.ajax({
					type: "POST",
					url: encodeUrl("loginBean.saveResetPass"),
					data: "password=" + encodeURIComponent(pass) + "&email=" + encodeURIComponent(email) + "&key=" + encodeURIComponent(key),
					success: function (response) {
						// we have the response
						if (response.status == AJAX_SUCESS) {
							util.messageDialog(getLocalize("message_ajaxsuccess"));
							redirectTo("https://bonevo.net/login");
						} else {
							if (response.result == -1)
								util.messageDialog(getLocalize("setting_changepassword_message_error6"));
							else if (response.result == 0)
								util.messageDialog(getLocalize("message_ajaxunsuccess"));
						}
					},
					error: function (e) {
						showGrowlMessageAjaxError();
					}
				});
			}
		},
		/*
		 * resend Confirmation Email
		 */
		resendConfirmEmail: function () {
			var email = $('#inputPhoneNumber').val();
			$.ajax({
				type: "POST",
				url: encodeUrl("loginBean.resendConfirmEmail"),
				data: "&email=" + encodeURIComponent(email),
				success: function (response) {
					// we have the response
					if (response.status == AJAX_SUCESS) {
						dialogNotConfirmMail.dialog('close');
						if (response.result > 0)
							util.messageDialog(getLocalize("setting_sendemailagain_message"));
						else
							util.messageDialog(getLocalize("login_mess1"));
					} else
						util.messageDialog(getLocalize("message_ajaxunsuccess"));
				},
				error: function (e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		// ANPH: detect private browser -> generate token -> send to nodejs
		// storage
		// token => secret key need store on node
		sendLoginStack: function (dataToken, callback) {
			dataToken = dataToken || '';
			if (dataToken.length > 0)
				privateBrowerChecker.detectPrivateMode(function (is_private, browser) {//
					new Fingerprint2().get(function (result, components) {//
						var token = result + '_' + browser + '_' + (is_private ? 'pri' : 'pub'),
							//
							socket = io.connect(SOCKET_LINK + '?stateLog=notlog');
						socket.on('connect', function () {
							socket.emit('recieveLoginStack', {
								tokenid: token,
								tokendata: dataToken
							}, function (err) {
								if (err === null)
									callback();
							});
						});
					});
				});
		},
		removeStackWhenLogout: function (callback) {
			privateBrowerChecker.detectPrivateMode(function (is_private, browser) {//
				new Fingerprint2().get(function (result, components) {//
					var token = result + '_' + browser + '_' + (is_private ? 'pri' : 'pub');
					socket = io.connect(SOCKET_LINK + '?stateLog=notlog');
					socket.on('connect', function () {
						socket.emit('removeStackWhenLogout', {
							tokenid: token
						}, function (err) {
							if (err === null)
								callback();
						});
					});
				});
			});
		}
		// ANPH: END
	};
}());