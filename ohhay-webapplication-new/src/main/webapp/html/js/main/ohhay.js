/*
 * author: ThoaiNH
 * date create: 09/10/2015
 */
(function() {
	util = {
		evoProperties : {},
		contextPath : function() {
			if ($("#contextPath").val())
				return $("#contextPath").val();
			var url = window.location;
			return url.protocol + '//' + url.host;
		},
		confirmDialog : function(message, callback) {
			$('.dialogconfirm').jsOhhayDialog({
				type : 'confirm',
				title : 'confirm',
				content : message,
				save : callback
			});
		},
		/*
		 * ThoaiVt 20/01/2016
		 */
		messageDialog : function(message) {
			$('.dialogalert').jsOhhayDialog({
				type : 'alert',
				title : 'alert',
				content : message
			});
		}
		/*
		 * END TEST ThoaiVt 20/01/2016
		 */,
		dialogOhhay : function(elementId, callback) {
			// dialog-report
			$(elementId).jsOhhayDialog({
				save : callback
			});
		},
		isEmailAddress : function(str) {
			var pattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
			return pattern.test(str);
		},
		replaceUrlParam : function(key, value, sourceURL) {
			console.log(key + "," + value + "," + sourceURL);
			if (sourceURL[sourceURL.length - 1] == '#')
				sourceURL = sourceURL.substring(0, sourceURL.length - 1);
			// remove parammeter
			var rtn = sourceURL.split("?")[0], param, params_arr = [], queryString = (sourceURL.indexOf("?") !== -1) ? sourceURL.split("?")[1] : "";
			if (queryString !== "") {
				params_arr = queryString.split("&");
				for (var i = params_arr.length - 1; i >= 0; i -= 1) {
					param = params_arr[i].split("=")[0];
					if (param === key) {
						params_arr.splice(i, 1);
					}
				}
				rtn = rtn + "?" + params_arr.join("&");
			}
			// remove ? if ad last
			if (rtn.indexOf("?") == rtn.length - 1)
				rtn = rtn.split("?")[0];
			// set parameter
			if (rtn.indexOf("?") > 0)
				rtn = rtn + "&" + key + "=" + value;
			else
				rtn = rtn + "?" + key + "=" + value;
			return rtn;
		},
		getUrlParameter : function(name, url) {
			name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
			var regexS = "[\\?&]" + name + "=([^&#]*)";
			var regex = new RegExp(regexS);
			var results = regex.exec(url);
			if (results == null)
				return "";
			else
				return results[1];
		},
		/*
		 * change scaleNumber font size of object
		 */
		scaleFontSize : function(object, scaleNumber) {
			var crFontSize = object.css('font-size');
			object.attr('cr-fontsize', crFontSize);
			var newFontSize = parseInt(crFontSize.substring(0, crFontSize.indexOf('px'))) + scaleNumber;
			object.css('font-size', newFontSize + 'px');
		},
		isEmpty : function(text) {
			return (typeof text == 'undefined' || !text || text.length == 0);
		},
		headerMobile : function() {
			$(document).on('click', '.btn-toggle-menumobile', function() {
				$(".mobile-toggleside-menu").toggleClass("shown-toggle-mbmenu");
				$(".qb-ohhay-setting-wrapper").toggleClass("menu-mobile-togglefix");
			});

			$(document).on('click', '.mbshown-toogle-dropdown .dropdown-toggle', function() {
				if ($(this).hasClass("open")) {
					$(this).parent().find('.choice-langmb-caret').slideUp("fast");
					$(this).find('.choice-lang-caret').removeClass("fa-caret-down").addClass("fa-caret-right");
				} else {
					$(this).parent().find('.choice-langmb-caret').slideDown("fast");
					$(this).find('.choice-lang-caret').removeClass("fa-caret-right").addClass("fa-caret-down");
				}
				$(this).toggleClass('open');
			});
		}
	}
}());
// find component
$.fn.findChild = function(option) {
	return $(this).children().find(option);
};
// go to element
(function($) {
	$.fn.goTo = function() {
		$('html, body').animate({
			scrollTop : $(this).offset().top + 'px'
		}, 400);
		return this; // for chaining...
	}
})(jQuery);
function setDefaultAjaxStatus() {
	$.ajaxSetup({
		beforeSend : function() {
			try {
				// NProgress.start();
				if ($('.qb-ohhay-ajax-content').css('display') == 'none')
					$('.qb-ohhay-ajax-content').css('display', 'block');
				NProgress.inc();
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
		},
		complete : function() {
			try {
				$('.qb-ohhay-ajax-content').css('display', 'none');
				NProgress.done();
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
		}
	});
};
setDefaultAjaxStatus();
// render screen for edit
function renderScreen() {
	var width = $(window).width();
	var kq = 0;
	if (width < 768)
		kq = 3;
	else if (width <= 1024)
		kq = 2;
	else
		kq = 1;
	return kq;
}
// set html for div
function setHtml(mClass, mess) {
	$(mClass).html(mess);
	$(mClass).css('display', 'block');
}
// reset when close
function onCloseDialog() {
	$('.error-edit-text').html('');
}
// set hinh sau khi chon file
function readURL(input, imgSelector, inputBase64) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$(inputBase64).val(e.target.result);
			$(imgSelector).attr('src', e.target.result);
			$(imgSelector).css('display', 'block');
		};
		reader.readAsDataURL(input.files[0]);
	}
}
$(document).on('click', 'body', function() {
	// dlg = new DialogFx(document.getElementById('form-criteria-bg'));
	// dlg.toggle.apply(dlg);
	// var option = {
	// onOpenDialog : function( instance ) {
	// classie.add( container, 'container--move' );
	// },
	// onCloseDialog : function( instance ) {
	// classie.remove( container, 'container--move' );
	// }
	// }
	// $('#form-criteria-bg').jsOhhayDialog(option);
});

// get parameter of url (use to reload at postion edited)
function getUrlParameter(sParam) {
	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('&');
	for (var i = 0; i < sURLVariables.length; i++) {
		var sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] == sParam) {
			return sParameterName[1];
		}
	}
}
// get localize
function getLocalize(key) {
	return util.evoProperties[key];
}
// move to top page
function moveToTop() {
	$("html, body").animate({
		scrollTop : 0
	}, "slow");
}
// move to bottom page
function moveToBottom() {
	$("html, body").animate({
		scrollTop : $(document).height()
	}, "slow");
}
// move to a div
function moveToBox(targetBox) {
	$("html, body").animate({
		scrollTop : targetBox.offset().top
	}, "slow");
}
// show message
function showMessage(message) {
	$("#qb-dialog-message").find('#mess-label').text(message);
	$("#qb-dialog-message").dialog("open");
}
function showGrowlMessageError() {
	showGrowlMessage(getLocalize("message_ajaxunsuccess"));
}
function showGrowlMessageSuccess() {
	showGrowlMessage(getLocalize("message_ajaxsuccess"));
}
function showGrowlMessageAjaxError() {
	showGrowlMessage(getLocalize("message_ajaxerror"));
}
// show growl message
function showGrowlMessage(message, time) {
	$("#growl-message").find('#growl-mess-label').text(message);
	if (time)
		$("#growl-message").fadeIn().delay(time).fadeOut('slow');
	else
		$("#growl-message").fadeIn().delay(2000).fadeOut('slow');
}
// share
function initShare() {
	$('#sfacebook').share({
		networks : ['facebook']
	});
}
// redirect den mot url khi o trang video marketing
function redirectToInVideoMarketing(url, fo100) {
	try {
		var videoId = $('.qb-oohhay-out-wrapper .qb-iframe-wrap .qb-iframe-clip').attr('videoId');
		window.location = url + '?video=' + fo100 + '-' + videoId + '-' + player.getCurrentTime();
	} catch (err) {
		window.location = url;
	}
}
// redirect den mot url
function redirectTo(url) {
	console.log('redirect to:' + url);
	// neu dang o trang video marketing
	if (document.URL.indexOf('videomarketing') > 0) {
		var videoRootInfor = $('#qb-data-video-root-infor').val();
		// redirect vo video infor parameter de play popup video
		redirectToInVideoMarketing(url, videoRootInfor);
	} else {
		try {
			var currentVideo = getUrlParameter('video');
			var videoInfor = currentVideo.split("-");
			// redirect when seeing video
			if (typeof currentVideo != 'undefined' && videoInfor.length == 3) {
				var iframe = document.getElementById('qb-video-marketing-iframe');
				var innerDoc = iframe.contentDocument;
				// get current video time, add time 5s load page
				var currentVideoTime = innerDoc.getElementById('video-current-time').value + 5;
				if ($('.qb-data-video-iframe').css('display') != 'none')// when
					// not
					// close
					// video
					setParam("video", videoInfor[0] + '-' + videoInfor[1] + '-' + currentVideoTime, url);
				else
					// stop play video
					setParam("video", "0", url);
			} else
				window.location = url;
		} catch (err) {
			window.location = url;
		}
	}
}
// set gia tri cho param hien tai cua url sau do reload lai page
function setParam(key, value, sourceURL) {
	if (sourceURL[sourceURL.length - 1] == '#')
		sourceURL = sourceURL.substring(0, sourceURL.length - 1);
	// remove parammeter
	var rtn = sourceURL.split("?")[0], param, params_arr = [], queryString = (sourceURL.indexOf("?") !== -1) ? sourceURL.split("?")[1] : "";
	if (queryString !== "") {
		params_arr = queryString.split("&");
		for (var i = params_arr.length - 1; i >= 0; i -= 1) {
			param = params_arr[i].split("=")[0];
			if (param === key) {
				params_arr.splice(i, 1);
			}
		}
		rtn = rtn + "?" + params_arr.join("&");
	}
	// remove ? if ad last
	if (rtn.indexOf("?") == rtn.length - 1)
		rtn = rtn.split("?")[0];
	// set parameter
	if (rtn.indexOf("?") > 0)
		rtn = rtn + "&" + key + "=" + value;
	else
		rtn = rtn + "?" + key + "=" + value;
	window.location = rtn;
}
// set gia tri cho param hien tai cua url sau do mo tab moi
function setParamNewTab(key, value, sourceURL) {
	// remove parammeter
	var rtn = sourceURL.split("?")[0], param, params_arr = [], queryString = (sourceURL.indexOf("?") !== -1) ? sourceURL.split("?")[1] : "";
	if (queryString !== "") {
		params_arr = queryString.split("&");
		for (var i = params_arr.length - 1; i >= 0; i -= 1) {
			param = params_arr[i].split("=")[0];
			if (param === key) {
				params_arr.splice(i, 1);
			}
		}
		rtn = rtn + "?" + params_arr.join("&");
	}
	// remove ? if ad last
	if (rtn.indexOf("?") == rtn.length - 1)
		rtn = rtn.split("?")[0];
	// set parameter
	if (rtn.indexOf("?") > 0)
		rtn = rtn + "&" + key + "=" + value;
	else
		rtn = rtn + "?" + key + "=" + value;
	var win = window.open(rtn, '_blank');
	win.focus();
}
// REDIRECT TO URL (TARGET: BLANK TRUE OR FALSE)
function qbRedirect(url, targetBlank) {
	if (targetBlank == true) {
		var win = window.open(url, '_blank');
		win.focus();
	} else
		window.location = url;
}
// REDIRECT TO TOPIC, NOT OPEN TWO TOPIC
var windowTopic;
function qbRedirectTopic(url) {
	// windowTopic = localStorage.getItem("windowTopic");
	if (windowTopic)
		windowTopic.close();
	windowTopic = window.open(url, '_blank');
	windowTopic.focus();
	// localStorage.setItem("windowTopic", windowTopic);
}
// REDIRECT TO SHOP, NOT OPEN TWO SHOP
var windowShop;
function qbRedirectShop(url) {
	// windowTopic = localStorage.getItem("windowTopic");
	if (windowShop)
		windowShop.close();
	windowShop = window.open(url, '_blank');
	windowShop.focus();
	// localStorage.setItem("windowTopic", windowTopic);
}
// SHOW PANEL
function qbShowpanel($selt) {
	$($selt).fadeIn();
}
// HIDE PANEL
function qbHidepanel($selt) {
	$($selt).fadeOut();
}
// ADD CLASS ACTIVE
function qbShowpanelAddActive($selt) {
	$($selt).addClass('active');
}
// REMOVE CLASS ACTIVE
function qbHidepanelRemoveActive($selt) {
	$($selt).removeClass('active');
}
// ADD CLASS OPEN
function qbShowpanelAddOpen($selt) {
	$($selt).addClass('open');
}
// REMOVE CLASS ACTIVE
function qbHidepanelRemoveOpen($selt) {
	$($selt).removeClass('open');
}
// ADD PATH OF URL
function encodeUrl(url) {
	if (url.indexOf('http') >= 0)
		return url;
	return $('#contextPath').val() + "/" + url;
}
// CHECK VARIABLE IS NUMBER
function isNumber(o) {
	return !isNaN(o - 0) && o !== null && o !== "" && o !== false;
}
// setup webtools dialog
$.fn.webToolDialog = function(width) {
	$(this).dialog({
		width : width,
		resizable : false,
		title : $(this).attr('title'),
		autoOpen : false,
		dialogClass : 'fadeIn'
	});
};
// normal dialog
$.fn.webDialog = function(width) {
	$(this).dialog({
		width : width,
		resizable : false,
		draggable : false,
		title : $(this).attr('title'),
		modal : true,
		autoOpen : false,
		position : {
			my : "top",
			at : "top+60",
			of : window
		}
	});
};
// disable console
// console.log = function() {};
// user change language handler
function changeLanguageHandle(languageCode) {
	$.ajaxSetup({
		beforeSend : function() {
		},
		complete : function() {
			setDefaultAjaxStatus();
		}
	});
	$.ajax({
		type : "POST",
		url : encodeUrl("systemManagerBean.changeLanguage"),
		data : {
			languageCode : languageCode
		},
		success : function(response) {
			setParam('language', languageCode, document.URL);
		},
		error : function(e) {
		}
	});
}