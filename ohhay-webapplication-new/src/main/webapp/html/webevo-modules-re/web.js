/**
 * author: ThoaiNH date create: 09/10/2015 shared function of all component
 */
(function() {
	web = {
		MODE : 'edit',// edit or preview
		RULER : 'off', // on or off
		MOBILE_EDITOR : 'off', // on or off
		GRID_LINE : 'off', // on or off
		TRIAL : 'off',// on or off
		START_WITH_NOBOX : false,
		SMART_LAYOUT : true,
		init : function() {
			/*
			 * try { socketio.connect(); } catch (e) { // TODO: handle exception
			 * console.log(e); }
			 */
			web.eventListener();
			grid.init();
			otext.init();
			smallbox.init();
			bigbox.init();
			imageController.init();
			webMenuLeft.init();
			webMenuTop.init();
			webBgRepControl.init();
			iframe.init();
			cp_Option.init();
			layout.init();
			cp_Editor.init();
			obutton.init();
			gmapController.init();
			embedCodeController.init();
			gridbox.init();
			actionOption.init();
			omenu.init();
			contactForm.init();
			contactFormFrontEnd.init();
			webBuilder.init();
			iframeTopic.init();
			if (web.TRIAL != 'on')
				webCreater.getWebData();
			else
				webCreater.getWebDataTrial();
			hoverOption.init();
			anchorOption.init();
			youtubePlaylist.init();
			subbox.init();
		},
		eventListener : function() {
			/*
			 * confirm when close window
			 */

			window.onbeforeunload = function(e) {
				if (!web.SUCCESS_SIGNUP)
					return '';
			};
			// show/hide tool edit box
			/*
			 * $('html').click(function() { // Hide the menus if visible
			 * $('.gird-stack-item-hover .function-panel').hide();
			 * $('.gird-stack-item-hover').removeClass('gird-stack-item-hover');
			 * });
			 */
			$(document).on('click mouseover', '#content-wrapper .content-box:not(".content-box-from-lib") .grid-stack-item-content', function(event) {
				event.stopPropagation();
				$('.gird-stack-item-hover .function-panel').hide();
				$('.gird-stack-item-hover').removeClass('gird-stack-item-hover');
				if (web.MODE == 'edit' || web.MODE == 'imgUpload') {
					$(this).addClass('gird-stack-item-hover');
					$(this).find('.function-panel').show();
				}
			});

			$(document).on('mouseleave', '#content-wrapper .content-box:not(".content-box-from-lib") .grid-stack-item-content', function() {
				if (smallbox.KEY_ALT == true)
					return;
				$(this).removeClass('gird-stack-item-hover');
				$(this).find('.function-panel').hide();
			});

			/*
			 * hover box
			 */
			$(document).on('click', '#content-wrapper .content-box', function(event) {
				$('.content-box').removeClass('box-selected');
				$(this).addClass('box-selected');
			});
		},
		/*
		 * go to preview mode
		 */
		goToPreviewMode : function() {
			// disable grid line
			if (web.GRID_LINE == 'on')
				$('#menu-tools #show-grid-line').trigger('click');
			web.MODE = 'preview';
			try {
				webCreater.data[WEB_PRO_BG] = webBgRepControl.data;
				webCreater.data[WEB_PRO_CONFIG] = webConfig.data;
				webCreater.data[WEB_PRO_LISTBOX] = bigBoxModelManager.listBox;
				componentModelManager.syncHtmlData();
				webCreater.data[WEB_PRO_LISTCOMP] = componentModelManager.listComponent;
				console.log(webCreater.data);
			} catch (e) {
				// TODO: handle exception
			}
			$(".qb-edit-tool").fadeOut();
			// imgbox
			$(".grid-stack-item-content").addClass('disabled');
			if ($("#btn-ruler").hasClass("disable") == false)
				$("#btn-ruler").trigger("click");
			
			$("#content-wrapper").removeClass("edit-mode");
			$('#content-wrapper .content-box').removeClass('grid-style-onedit');
			$('#content-wrapper .content-box .menu-right').hide();
			$('#content-wrapper .content-box .panel-no-eleme').hide();
			$(".ui-dialog-content").dialog("close");
			$('#web-tools').hide();
			$('.jqte_toolbar').hide();
			try {
				$('.content-box:not(".content-box-from-lib") .grid-stack').each(function() {
					$(this).data('gridstack').disable();
				});
			} catch (e) {
				console.log(e);
			}
			// ANPH: disable all cropbox
			try {
				$('img.cropbox-enable').each(function() {
					if (typeof $(this).data('cropbox') != 'undefined') {
						$(this).data('base64', $(this).attr('src'));
						$(this).attr('src', $(this).data('cropbox').getDataURL());
					}
					$(this).data('cropbox').remove();
				});
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
			// Thong: Editor
			cp_Editor.goToPreviewMode();
			// Thong: Menu
			omenu.goToPreviewMode();
			obutton.goToPreviewMode();
			actionOption.goToPreviewMode();
			hoverOption.goToPreviewMode();
			// ThoaiNH: hide space bottom div
			$('#qb-spave-bottom-div').hide();
			// setup nice scroll for mobile container
			$(".content-wrapper-mobile").niceScroll({
				oneaxismousemode : false
			});
			// zoom in background to fit mobile
			if (web.MOBILE_EDITOR == 'on')
				$('.wrapper-background, .wrapper-background-video').addClass('qb-mobile-view-bg');
		},
		/*
		 * goto edit mode
		 */
		goToEditMode : function() {
			web.MODE = 'edit';
			$(".qb-edit-tool").fadeIn();
			$("#content-wrapper").addClass("edit-mode");
			$('#content-wrapper .content-box .grid-stack-re').resizable('enable');
			$('#content-wrapper .content-box').addClass('grid-style-onedit');
			$('#content-wrapper .content-box .menu-right').show();
			$('#content-wrapper .content-box .panel-no-eleme').show();
			$('#content-wrapper .content-box .qb-edit-tool').show();
			// imgbox
			$(".grid-stack-item-content.disabled").removeClass('disabled');
			if (web.MOBILE_EDITOR != 'on')
				$('#web-tools').show();
			else
				$('#web-tools').hide();
			$('.jqte_toolbar').show();
			$('.content-box:not(".content-box-from-lib") .grid-stack').each(function() {
				$(this).data('gridstack').enable();
			});
			$('#content-wrapper .content-box').addClass('grid-style-onedit');
			/*
			 * turn on component editor (off on mobile editor mode)
			 */
			if (web.MOBILE_EDITOR != 'on') {
				// Thong: Editor
				cp_Editor.goToEditMode();
				// Thong: Menu
				omenu.goToEditMode();
				obutton.goToEditMode();
				actionOption.goToEditMode();
				hoverOption.goToEditMode();
			}
			// ThoaiNH: show space bottom div
			$('#qb-spave-bottom-div').show();
			// remove nice scroll for mobile container
			$(".content-wrapper-mobile").getNiceScroll().remove();
			$(".content-wrapper-mobile").css({
				'overflow' : 'hidden'
			});
			// zoom in background to fit mobile
			if (web.MOBILE_EDITOR == 'on')
				$('.wrapper-background, .wrapper-background-video').removeClass('qb-mobile-view-bg');
		},
		goToModeUploadImg : function() {
			try {
				webCreater.data[WEB_PRO_BG] = webBgRepControl.data;
				webCreater.data[WEB_PRO_CONFIG] = webConfig.data;
				webCreater.data[WEB_PRO_LISTBOX] = bigBoxModelManager.listBox;
				componentModelManager.syncHtmlData();
				webCreater.data[WEB_PRO_LISTCOMP] = componentModelManager.listComponent;
				// console.log(webCreater.data);
			} catch (e) {
				console.log(e);
			}
			$(".qb-edit-tool").fadeOut();
			$(".grid-stack-item-content:not(.processing)").addClass('disabled');
			$('#preview').fadeOut();
			if ($("#btn-ruler").hasClass("disable") == false)
				$("#btn-ruler").trigger("click");
			web.MODE = 'imgUpload';
			try {
				$('#content-wrapper .content-box .grid-stack').resizable('disable');
			} catch (e) {
				console.log(e);
			}
			$('#content-wrapper .content-box .menu-right').hide();
			$('#content-wrapper .content-box .panel-no-eleme').hide();
			$(".ui-dialog-content").dialog("close");
			$('#web-tools').hide();
			$('.jqte_toolbar').hide();
			obutton.goToPreviewMode();
		},
		endModeUploadImg : function() {
			$(".qb-edit-tool").show();
			$(".grid-stack-item-content.disabled").removeClass('disabled');
			$('#preview').fadeIn();
			web.MODE = 'edit';
			$('#content-wrapper .content-box .grid-stack').resizable('enable');
			$('#content-wrapper .content-box .menu-right').show();
			$('#content-wrapper .content-box .panel-no-eleme').show();
			$('#web-tools').show();
			$('.jqte_toolbar').show();
			obutton.goToEditMode();
			actionOption.goToEditMode();
			hoverOption.goToEditMode();
		},
		/*
		 * go to mobile editor
		 */
		goToMobileEditor : function() {
			web.MOBILE_EDITOR = 'on';
			// Thong: Editor
			cp_Editor.goToPreviewMode();
			// Thong: Menu
			omenu.goToPreviewMode();
			obutton.goToPreviewMode();
			// ThoaiNH: hide space bottom div
			$('#qb-spave-bottom-div').hide();
			// off ruler if open
			if (web.RULER == 'on')
				$('#btn-ruler').trigger('click');
			// disable grid line
			if (web.GRID_LINE == 'on')
				$('#menu-tools #show-grid-line').trigger('click');
			// turn off PC editor tool
			$('.pull-right-btn-ruler').hide();
			$('body').addClass('qb-body-mobile');
			$("#qb-phone-view-top, #qb-phone-view-bottom").show();
			$("#content-wrapper").addClass("content-wrapper-mobile");
			$("#content-wrapper").addClass("edit-mode");
			// webResponsive.gotoMobile();
		}
	}
}());