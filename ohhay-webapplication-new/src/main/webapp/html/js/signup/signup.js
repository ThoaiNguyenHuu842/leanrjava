/**
 * @author ThoaiNH
 * create Dec 14, 2015
 * sign up EVO account
 */
(function() {
	signup = {
		COUNTRY_CODE:'',
		PHONE_NUMBER:'',
		PROCESSED_PHONE: '',
		TEMPLATE_SELECTED: 0,
		JOB_SELECTED: 0,
		REGIS_TYPE: "be",
		ACTION: "",
		init : function() {
			$('.term-content .term-body').niceScroll({
				cursorcolor: "#2fa8de",
		        background: "",
		        cursorborder: "",
		        cursorwidth: "7px",
		        cursorborderradius :"4px",
		        autohidemode: false,
		        zindex: 999999,
		        railpadding: { top: 0, right: 2, left: 0, bottom: 0 }
			});
			$('.term-content .term-body').getNiceScroll().show();
			this.eventListener();
			$( ".container-fluid [title]" ).tooltip({
			      position: {
			        my: "left top",
			        at: "right+5 top-5"
			      }
			});
			signup.getParamInfo();
		},
		eventListener : function() {
			//on click confirm term
			$(document).on('click',".btn-confirm-term", function(){
				signup.onClickConfirmTerm();
			});
			//on click button create account
			$(document).on('click', '.btn-create-account', function(){
				if(web.TRIAL != 'on')
					signup.submit();
				else {
					signup.submitWithTrial();
				}					
			});
			//on enter confirm password input
			$(document).on('keydown', '#inputRePassword', function (event) {
				if (event.which == 13)
					$('.btn-create-account').trigger('click');
			});
			//on click button register in trial
			$(document).on('click', '.btn-register', function(){
				$('.register-select').fadeOut();
				$('.account-infor').fadeIn();
			});
			//on click submit confirm pass 
			$(document).on('click', '.confirm-pass-tologin .btn-login', function(){
				var password = $('.confirm-pass-tologin #input-confirm-pass-tologin').val();
				$('#inputPassword').val(password);
				$('#inputRePassword').val(password);
				signup.submit();
			});
			//on enter confirm password to login input
			$(document).on('keydown', '.confirm-pass-tologin #input-confirm-pass-tologin', function (event) {
				if (event.which == 13)
					$('.confirm-pass-tologin .btn-login').trigger('click')
			});
			
		},
		getParamInfo: function(){
			var fname = getUrlParameter("fname");
			var lname = getUrlParameter("lname");
			var email = getUrlParameter("email");
			var template = getUrlParameter("template");
			var kubvideo = getUrlParameter("kubvideo");
			var action = getUrlParameter("action");
			if(!util.isEmpty(fname) && !util.isEmpty(lname) && !util.isEmpty(email) && !util.isEmpty(template) && !util.isEmpty(kubvideo))
			{
				signup.REGIS_TYPE = "kb";
				
				$('#inputEmail').val(decodeURIComponent(email));
				$('#inputFName').val(decodeURIComponent(fname));
				$('#inputLName').val(decodeURIComponent(lname));
				$("#inputEmail").attr("disabled","true");
				$('.confirm-term').fadeOut();
				if(!util.isEmpty(action) && action == "confirmpass")
				{
					$('.confirm-pass-tologin').fadeIn();
					signup.ACTION = action;
				}
				else
					$('.account-infor').fadeIn();
				grecaptcha.reset();
			}
		},
		/*
		 * on click continue term
		 */
		onClickConfirmTerm: function(){
			if($('.input-check-term .value-checked').is(":checked")){
				$('.confirm-term').fadeOut();
				$('.account-infor').fadeIn();
				$('.term-content .term-body').getNiceScroll().remove();
			}
			else
				util.messageDialog(getLocalize("signup_agree_terms_error"));
		},
		onSelectJob: function(){
			$('#selected-job').html($(this).html());
			signup.JOB_SELECTED = $(this).attr('val');
		},
		/*
		 * load list combo box jobs type
		 */
		loadListD000 : function() {	
			$.ajax({
				type : "POST",
				url : encodeUrl("signUpBean.loadListD000Regis"),
				data : {},
				success : function(response) {
					var list = response.result;
					var htmlString = '';
					for (i = 0; i < list.length; i++) {
							htmlString += "<li class='job-item' val='"+list[i].val+"'><a >"+list[i].label+"</a></li>";
					}
					$('.dropdown-menu-jobs').html(htmlString);
					//set default value
					signup.JOB_SELECTED = list[0].val;
					$('#selected-job').html(list[0].label);
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});		
		},
		/*
		 * on validate register account 
		 */
		submit: function()
		{
			var response = grecaptcha.getResponse();

            if (response.length == 0)
               util.messageDialog("Please verify that you are not a robot !");
            else{
			var email = $('#inputEmail').val();
			var password = $('#inputPassword').val();
			var rePassword = $('#inputRePassword').val();
			var fName = $('#inputFName').val();
			var lName = $('#inputLName').val();
			var regionCode =  $("#selected-country").attr("regionCode");
			var languageName =  $("#selected-country").attr("languageName");
			var languageCode =  $("#selected-country").attr("languageCode");
			var countryCode = $("#selected-country").attr("countrycode");
			var fd000 = 2;
			var jobName = "IT";
			if(signup.ACTION == "confirmpass")
				$('.qb-ohhay-ajax-title').html(getLocalize("jsgup_title2"));
			else
				$('.qb-ohhay-ajax-title').html(getLocalize("jsgup_title3"));
			if(signup.REGIS_TYPE == "kb")
				$.ajax({
					type : "POST",
					url : encodeUrl("signUpBean.registerAccountWithKub"),
					data : "phone=" + encodeURIComponent(signup.PHONE_NUMBER) + 
						   "&fName=" + encodeURIComponent(fName) +
						   "&lName=" + encodeURIComponent(lName) +
						   "&email=" + encodeURIComponent(email) +
						   "&passWord=" + encodeURIComponent(password) +
						   "&rePassWord=" + encodeURIComponent(rePassword) +
						   "&region=" + encodeURIComponent(regionCode) +
						   "&languageName=" + encodeURIComponent(languageName) +
						   "&languageCode=" + encodeURIComponent(languageCode) + 
						   "&countryCode=" + encodeURIComponent(countryCode) +
						   "&templateId=" + encodeURIComponent(getUrlParameter("template")) + 
						   "&kubvideo=" + encodeURIComponent(getUrlParameter("kubvideo")) +
						   "&regisTypel=" + encodeURIComponent(signup.REGIS_TYPE),
					success : function(response) {
						if (response.status == AJAX_SUCESS) {
							if (response.result > 0)
								window.location = util.contextPath()+"/e"+response.result+"/evo-editer?editmode=element";
							else
							{
								if(signup.ACTION == "confirmpass")
									util.messageDialog(getLocalize("jsgup_title1"));
								else
									setHtml("#label-error-mess-3",getLocalize("signup_error4"));	
							}
						} else
						{
							if(signup.ACTION == "confirmpass")
								util.messageDialog(getLocalize("jsgup_title1"));
							else
								setHtml("#label-error-mess-3", response.result[0].defaultMessage);
						}
					},
					error : function(e) {
						setHtml("#label-error-mess-3",getLocalize("signup_error4"));	
					}
				});
			else 
				$.ajax({
						type : "POST",
						url : encodeUrl("signUpBean.registerAccount"),
						data : "phone=" + encodeURIComponent(signup.PHONE_NUMBER) + 
							   "&fName=" + encodeURIComponent(fName) +
							   "&lName=" + encodeURIComponent(lName) +
							   "&email=" + encodeURIComponent(email) +
							   "&passWord=" + encodeURIComponent(password) +
							   "&rePassWord=" + encodeURIComponent(rePassword) +
							   "&region=" + encodeURIComponent(regionCode) +
							   "&languageName=" + encodeURIComponent(languageName) +
							   "&languageCode=" + encodeURIComponent(languageCode) +
							   "&countryCode=" + encodeURIComponent(countryCode)+
							   "&regisTypel=" + encodeURIComponent(signup.REGIS_TYPE),
						success : function(response) {
							if (response.status == AJAX_SUCESS) {
								if (response.result > 0) {
									signup.onSignupFinish();
								} 
								else
									setHtml("#label-error-mess-3",getLocalize("signup_error4"));	
							} else
							{
								setHtml("#label-error-mess-3", response.result[0].defaultMessage);
							}
						},
						error : function(e) {
							setHtml("#label-error-mess-3",getLocalize("signup_error4"));	
						}
				});
		}},
		submitWithTrial: function()
		{
			var response = grecaptcha.getResponse();

            if (response.length == 0)
               util.messageDialog("Please verify that you are not a robot !");
            else{
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
			var email = $('#inputEmail').val();
			var password = $('#inputPassword').val();
			var rePassword = $('#inputRePassword').val();
			var fName = $('#inputFName').val();
			var lName = $('#inputLName').val();
			var regionCode =  $("#selected-country").attr("regionCode");
			var languageName =  $("#selected-country").attr("languageName");
			var languageCode =  $("#selected-country").attr("languageCode");
			var countryCode = $("#selected-country").attr("countrycode");
			var fd000 = 2;
			var jobName = "IT";
			var html = webBuilder.getWebHTML();
			var data = JSON.stringify(webCreater.data);
			var apiCompSelector = '.qb-component-gmap';
			var editToolSelector = '.function-panel, .ui-resizable-handle, .menu-right, .qb-edit-tool, .ruler, .panel-no-eleme, .qb-spave-bottom-div, .evo-hair, .evo-grid-line';
			var mobileEditor = web.MOBILE_EDITOR;
			var formData = new FormData();
            formData.append('phone', signup.PHONE_NUMBER);
            formData.append('fName', fName);
            formData.append('lName', lName);
            formData.append('email', email);
            formData.append('passWord', password);
            formData.append('rePassWord', rePassword);
            formData.append('region', regionCode);
            formData.append('languageName', languageName);
            formData.append('languageCode', languageCode);
            formData.append('countryCode', countryCode);
            formData.append('templateId', webCreater.data.fe400);
            formData.append('html', webBuilder.getWebHTML());
            formData.append('data', JSON.stringify(webCreater.data));
            formData.append('apiCompSelector', apiCompSelector);
            formData.append('editToolSelector', editToolSelector);
            formData.append('mobileEditor', mobileEditor);
			$.ajax({
					type : "POST",
					url : encodeUrl("signUpBean.registerAccount"),
					cache: false,
		            contentType: false,
		            processData: false,
		            data: formData,
					success : function(response) {
						if (response.status == AJAX_SUCESS) {
							if (response.result > 0) {
								web.SUCCESS_SIGNUP = true;
								signup.onSignupFinish();
							} 
							else
								setHtml("#label-error-mess-3",getLocalize("signup_error4"));	
						} else
						{
							setHtml("#label-error-mess-3", response.result[0].defaultMessage);
						}
					},
					error : function(e) {
						setHtml("#label-error-mess-3",getLocalize("signup_error4"));	
					}
			});}
		
		},
		onSignupFinish: function(){
			window.location = util.contextPath() + "/registration-successful";
		}
	};
}());