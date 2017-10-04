
/**
 * @author ThoaiNH
 * create Dec 22, 2015
 * login EVO in trial page
 * 
 */
window.fbAsyncInit = function() {
    FB.init({
      appId      : '381681832036999',
      xfbml      : true,
      version    : 'v2.3'
    });
    FB.AppEvents.logPageView();
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
(function() {
	loginTrial = {
		init : function() {
			this.eventListener();
			loginTrial.checkGoogleLoginState();
		},
		eventListener : function() {
			$(document).on('click','.btn-fb-login', loginTrial.checkFacebookLoginState);			
			/*share facebook*/
			$(document).on('click','.btn-share-web-to-fb', function(){
			    FB.ui({
			    	method: 'share',
			    	href: util.contextPath() + "/" + webCreater.data.ev405
			    });
			});
			/*btn redirect to pc editor*/
			$(document).on('click','.btn-continue-edit-web', function(){
				window.location = util.contextPath() + "/e" + webCreater.data.id + "/evo-editer?editmode=element";
			});
		},
		/*
		 * login by social
		 */
		loginBySocial: function(result){
			console.log(result);
			/*
			 * get model data
			 */
			webCreater.data[WEB_PRO_BG] = webBgRepControl.data;
			webCreater.data[WEB_PRO_CONFIG] = webConfig.data;
			webCreater.data[WEB_PRO_LISTBOX] = bigBoxModelManager.listBox;
			componentModelManager.syncHtmlData();
			webCreater.data[WEB_PRO_LISTCOMP] = componentModelManager.listComponent;
			console.log(webCreater.data);
			/*
			 * save data
			 */
			if(result["email"]){
				var email = result["email"]
				var password = result["id"];
				var rePassword =  result["id"];
				var fName =  result["first_name"];
				var lName = result["last_name"];
				var regionCode =  $("#selected-country").attr("regionCode");
				var languageName =  $("#selected-country").attr("languageName");
				var languageCode =  $("#selected-country").attr("languageCode");
				var countryCode = $("#selected-country").attr("countrycode");
				var regisTypel = result["regisTypel"];
				var fd000 = 2;
				var jobName = "IT";
				var html = webBuilder.getWebHTML();
				var data = JSON.stringify(webCreater.data);
				var apiCompSelector = '.qb-component-gmap';
				var editToolSelector = '.function-panel, .ui-resizable-handle, .menu-right, .qb-edit-tool, .ruler, .panel-no-eleme, .qb-spave-bottom-div, .evo-hair, .evo-grid-line';
				var mobileEditor = web.MOBILE_EDITOR;
				var formData = new FormData();
	            formData.append('phone', '');
	            formData.append('fName', fName);
	            formData.append('lName', lName);
	            formData.append('email', email);
	            formData.append('passWord', password);
	            formData.append('rePassWord', rePassword);
	            formData.append('region', regionCode);
	            formData.append('languageName', languageName);
	            formData.append('languageCode', languageCode);
	            formData.append('countryCode', countryCode);
	            formData.append('regisTypel', regisTypel);
	            formData.append('socialId', password);
	            formData.append('templateId', webCreater.data.fe400);
	            formData.append('html', webBuilder.getWebHTML());
	            formData.append('data', JSON.stringify(webCreater.data));
	            formData.append('apiCompSelector', apiCompSelector);
	            formData.append('editToolSelector', editToolSelector);
	            formData.append('mobileEditor', mobileEditor);
				$.ajax({
							type : "POST",
							url : encodeUrl("loginBean.loginBySocialTrial"),
							cache: false,
				            contentType: false,
				            processData: false,
				            data: formData,
							success : function(response) {
								if (response.status == AJAX_SUCESS && response.result2 && response.result2 != null) {
									web.SUCCESS_SIGNUP = true;
									$('.register-panel').hide();
									var fo100 = response.result;
									var pe400 = response.result2;
									var onwPath = response.resultString;
									webCreater.data.ev405 = onwPath;
									webCreater.data.id = pe400;
									$(".ui-dialog-titlebar-close").remove();
									$("#qb-save-web-success .link-preview").html(util.contextPath() + "/" + onwPath);
									$("#qb-save-web-success .link-preview").attr("href", util.contextPath() + "/" + onwPath);
									$("#qb-save-web-success .lbl-root-domain").html(util.contextPath() + "/");
									$("#qb-save-web-success .input-own-path").val(onwPath);
									$("#qb-save-web-success").webDialog(590);
									$("#qb-save-web-success").dialog('open');
									$(".ui-dialog-titlebar-close").hide();
									$("#qb-save-web-success #data-button-save-success").html('<img style="width:14px;margin-right:3px;" src="' + util.contextPath() + '/html/images/menu-save.png">OK');
								} else
								{
										util.messageDialog(getLocalize("jslgt_title1"));
//										alert('Your social email has signined before');
									
								}
							},
							error : function(e) {
								//setHtml("#label-error-mess-3",getLocalize("signup_error4"));
								//alert(getLocalize("signup_error4"));
								util.messageDialog(getLocalize("signup_error4"));
							}
				});
			}
			else
//				alert("Sorry, We don't support social account without right email!");
				util.messageDialog(getLocalize("jslog_title1"));
		},
		/*
		 * get FB info
		 */
		getFacebookInfo:function(){
			FB.api('/me','GET',{'fields':'first_name,last_name,name,email,id'},function(result){
				result["regisTypel"] = "fb";
				console.log(result);
				loginTrial.loginBySocial(result);
			});
		},
		/*
		 * check FB login stt
		 */
		checkFacebookLoginState:function(){
			FB.getLoginStatus(function(result){
				console.log(result);
				if (result.status === 'connected') {
					loginTrial.getFacebookInfo();
				} else if (result.status === 'not_authorized') {
					loginTrial.loginFacebook();
				} else {
					loginTrial.loginFacebook();
				}
			});
		},
		/*
		 * login to FB
		 */
		loginFacebook:function(){
			FB.login(function(result){
				loginTrial.checkFacebookLoginState();
			},{scope:'public_profile,email,user_photos'});
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
			      auth2.attachClickHandler($('.btn-gg-login')[0], {}, function(googleUser) {
			    	  var result = {
			    			 email: googleUser.getBasicProfile().getEmail(),
			    			 first_name: googleUser.getBasicProfile().getGivenName(),
			    			 last_name: googleUser.getBasicProfile().getFamilyName(),
			    			 id: googleUser.getBasicProfile().getId()
			    	  };
			    	  result["regisTypel"] = "gg";
			    	  loginTrial.loginBySocial(result);
					}, function(error) {
						console.log(JSON.stringify(error, undefined, 2));
					});
			});
		},
	};
}());