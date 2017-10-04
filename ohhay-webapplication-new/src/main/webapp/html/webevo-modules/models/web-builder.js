/**
 * @author: ThoaiNH date create: 13/10/2015
 */
(function() {
	webBuilder = {
		init : function() {
			this.eventListener();
			try {
				login.init();
			} catch (e) {
				// TODO: handle exception
				console.log("Page trial");
			}
		},
		eventListener : function() {
			$(document).on('click', '#qb-save-web-success .btn-show-edit-own-path', function() {
				$(this).hide();
				$('#qb-save-web-success .panel-edit-sitename').show();
				$('#qb-save-web-success .link-preview').hide();
				$("#qb-save-web-success .input-own-path").val(webCreater.data.ev405);
			});
			$(document).on('click', '#qb-save-web-success .btn-close-edit-own-path', function() {
				$('#qb-save-web-success .panel-edit-sitename').hide();
				$('#qb-save-web-success .link-preview').show();
				$('#qb-save-web-success .btn-show-edit-own-path').show();
				$("#qb-save-web-success .input-own-path").val('');
			});
			$(document).on('click', '#qb-save-web-success .btn-save-dit-own-path', function() {
				webBuilder.updateSiteName();
			});
			$(document).on('click', '#qb-save-web-success .btn-edit-mobile-version', function() {
				setParam("editmode", "elementmb", document.URL);
			});
			$(document).on('click', '#qb-save-web-success .btn-close', function() {
				if ($('#qb-save-web-success .panel-edit-sitename').css("display") != "none")
					webBuilder.updateSiteName();
				$("#qb-save-web-success").dialog('close');
			});
			web.goToEditMode();
		},
		/*
		 * get web html to store view version
		 */
		getWebHTML : function() {
			if (!$("#menu-tools .btn-menu-desktop").hasClass("active"))
				$("#menu-tools .btn-menu-desktop").trigger('click');
			$(".grid-stack-item-content").each(function() {
				$(this).attr('qb-top', $(this).css('top'));
				$(this).attr('qb-height', $(this).css('height'));
			});
			/*
			 * get HTML save to view version (làm tạm)
			 */
			try {
				obutton.goToPreviewMode();
				actionOption.goToPreviewMode();
				hoverOption.goToPreviewMode();
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
			var content_wrapper = jQuery.extend(true, {}, $("#content-wrapper"));
			var fullWebHtml = content_wrapper.get(0).outerHTML;
			fullWebHtml += " <div class='wrapper-background' style='" + $(".wrapper-background").attr("style") + "'></div> ";
			if ($(".wrapper-background-video").get(0))
				fullWebHtml += $(".wrapper-background-video").get(0).outerHTML;
			try {
				obutton.goToEditMode();
				actionOption.goToEditMode();
				hoverOption.goToEditMode();
				// optionList.goToEditMode();
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
			return fullWebHtml;
		},
		getWebMobileHTML : function() {
			$('body').removeClass('qb-body-mobile');
			// $("#qb-phone-view-top, #qb-phone-view-bottom").hide();
			$("#content-wrapper").removeClass("content-wrapper-mobile");
			$(".grid-stack-item-content").each(function() {
				$(this).attr('qb-top', $(this).css('top'));
				$(this).attr('qb-height', $(this).css('height'));
			});
			/*
			 * get HTML save to view version (làm tạm)
			 */
			try {
				obutton.goToPreviewMode();
				actionOption.goToPreviewMode();
				hoverOption.goToPreviewMode();
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
			var content_wrapper = jQuery.extend(true, {}, $("#content-wrapper"));
			var fullWebHtml = content_wrapper.get(0).outerHTML;
			fullWebHtml += " <div class='wrapper-background' style='" + $(".wrapper-background").attr("style") + "'></div> ";
			try {
				obutton.goToEditMode();
				actionOption.goToEditMode();
				hoverOption.goToEditMode();
				optionList.goToEditMode();
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
			$('body').addClass('qb-body-mobile');
			// $("#qb-phone-view-top, #qb-phone-view-bottom").show();
			$("#content-wrapper").addClass("content-wrapper-mobile");
			return fullWebHtml;
		},
		save : function() {
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
			var formData = new FormData();
			formData.append('webId', webCreater.data.id);
			formData.append('apiCompSelector', '.qb-component-gmap');
			formData.append('editToolSelector', '.function-panel, .ui-resizable-handle, .menu-right, .qb-edit-tool, .ruler, .panel-no-eleme, .qb-spave-bottom-div, .evo-hair, .evo-grid-line, .qb-img-tool, .qb-box-hidden');
			if (web.MOBILE_EDITOR == 'on')
				formData.append('html', webBuilder.getWebMobileHTML());
			else
				formData.append('html', webBuilder.getWebHTML());
			formData.append('data', JSON.stringify(webCreater.data));
			formData.append('mobileEditor', web.MOBILE_EDITOR);
			/*
			 * WEB_PRO_FONTSCALE
			 */
			console.log(JSON.stringify(webCreater.data));
//			console.log(mData[WEB_PRO_DATA][WEB_PRO_FONTSCALE]);
//			alert(WEB_PRO_FONTSCALE);
			$.ajax({
				type : "POST",
				url : encodeUrl("webBuilderBean.save"),
				cache : false,
				contentType : false,
				processData : false,
				data : formData,
				success : function(response) {
					console.log(response);
					if(response.result!==0){
						// reload data
						bigBoxModelManager.syncServerData(response.result2);
						componentModelManager.syncServerData(response.result2);
						// show confirm dialog
						var pe400 = $('#qb-input-pe400').val();
						var onwPath = webCreater.data.ev405;
						$("#qb-save-web-success .link-preview").html(util.contextPath() + "/" + onwPath);
						$("#qb-save-web-success .link-preview").attr("href", util.contextPath() + "/" + onwPath);
						$("#qb-save-web-success .lbl-root-domain").html(util.contextPath() + "/");
						$("#qb-save-web-success .input-own-path").val(onwPath);
						if (webBuilder.initSaveDialog != true) {
							$("#qb-save-web-success").webDialog(590);
							webBuilder.initSaveDialog = true;
						}
						/*swal({
							title : getLocalize('weh_save_success'),
							text : "",
							type : "success",
							timer : 2000,
							showConfirmButton: false
						});
						setTimeout(function(){$("#qb-save-web-success").dialog('open');}, 2000);*/
						$("#qb-save-web-success").dialog('open');
						$("#data-button-save-success").html('<img style="width:14px;margin-right:3px;" src="' + util.contextPath() + '/html/images/menu-save.png">OK');
						// auto save mobile version
						/*
						 * if(web.MOBILE_EDITOR != 'on'){
						 * if(util.isEmpty($('#qb-iframe-mbeditor-temp').attr('src')))
						 * $('#qb-iframe-mbeditor-temp').attr('src',$('#qb-iframe-mbeditor-temp').attr('srctemp'));
						 * else
						 * document.getElementById('qb-iframe-mbeditor-temp').contentWindow.location.reload(); }
						 */
					}else{
						console.log("Timeout session");
						/*
						 * require login
						 */
						if(webBuilder.initLoginRequire){
							login.init();
						}
						$(".login-require-sesion").show();
						
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		saveTopicContentData : function(topicId) {
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
			var formData = new FormData();
			formData.append('topicId', topicId);
			formData.append('apiCompSelector', '.qb-component-gmap');
			formData.append('editToolSelector', '.function-panel, .ui-resizable-handle, .menu-right, .qb-edit-tool, .ruler');
			formData.append('data', JSON.stringify(webCreater.data));
			$.ajax({
				type : "POST",
				url : encodeUrl("webBuilderBean.saveTopicContent"),
				cache : false,
				contentType : false,
				processData : false,
				data : formData,
				success : function(response) {
					// reload data
					bigBoxModelManager.syncServerData(response.result2);
					componentModelManager.syncServerData(response.result2);
					// show confirm dialog
					$("#qb-save-web-success").dialog({
						modal : true,
						height : 250,
						width : 350,
						buttons : {
							Ok : function() {
								$(this).dialog("close");
							}
						}
					});
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		updateSiteName : function() {
			var siteName = $("#qb-save-web-success .input-own-path").val();
			$.ajax({
				type : "POST",
				url : encodeUrl("webBuilderBean.updateSiteName"),
				data : {
					webId : webCreater.data.id,
					siteName : siteName
				},
				success : function(response) {
					if (response.result == 1) {
						webCreater.data.ev405 = siteName;
						$("#qb-save-web-success .input-own-path").attr('orgin', siteName);
						$("#qb-save-web-success .link-preview").html(util.contextPath() + "/" + siteName);
						$("#qb-save-web-success .link-preview").attr("href", util.contextPath() + "/" + siteName);
						$("#qb-save-web-success .btn-close-edit-own-path").trigger('click');
					} else if (response.result == -1)
						util.messageDialog(getLocalize("jswbd_title3"));
					else if (response.result == -2 || response.result == -3)
						util.messageDialog(getLocalize("jswbd_title2"));
					else if (response.result == -4)
						util.messageDialog(getLocalize("jswbd_title1"));
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		}

	}
}());