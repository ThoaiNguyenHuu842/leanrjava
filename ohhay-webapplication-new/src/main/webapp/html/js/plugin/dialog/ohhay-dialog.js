(function(window) {

	'use strict';

	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - show dialog
	 *  + $.jsOhhayDialog() thuc hien muc hop thoai
	 *  + $.jsOhhayDialog('close') thuc hien dong hop thoai
	 *  + ho tro nhieu hieu ung
	 *  
	 * ----------------------------------------------
	 */

	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - thuc hien animation
	 *  
	 * ----------------------------------------------
	 */
	var support = {
		animations : Modernizr.cssanimations
	}, animEndEventNames = {
		'WebkitAnimation' : 'webkitAnimationEnd',
		'OAnimation' : 'oAnimationEnd',
		'msAnimation' : 'MSAnimationEnd',
		'animation' : 'animationend'
	}, animEndEventName = animEndEventNames[Modernizr.prefixed('animation')], onEndAnimation = function(
			el, callback) {
		var onEndCallbackFn = function(ev) {
			if (support.animations) {
				if (ev.target != this)
					return;
				// console.log(this);
				$(this).on(animEndEventName, onEndCallbackFn);
			}
			if (callback && typeof callback === 'function') {
				callback.call();
			}
		};
		if (support.animations) {
			$(el).on(animEndEventName, onEndCallbackFn);
		} else {
			onEndCallbackFn();
		}
	};

	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - mo rong doi tuong
	 *  
	 * ----------------------------------------------
	 */
	function extend(a, b) {
		for ( var key in b) {
			if (b.hasOwnProperty(key)) {
				a[key] = b[key];
			}
		}
		return a;
	}

	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - doi tuong chinh
	 *  
	 * ----------------------------------------------
	 */
	function jsQueenbDialog(el, options) {
		this.el = $(el);
		this.options = extend({}, this.options);
		extend(this.options, options);
		this.ctrlClose = this.el.find('.data-dialog-close');
		this.isOpen = false;
		if (typeof this.options.type != 'undefined'
				&& (this.options.type == 'confirm' || this.options.type == 'alert')) {
			this._initEventConfirms();
		}else
		{
			this._initEvents();
		}

	}

	jsQueenbDialog.prototype._initEventConfirms = function() {
		var self = this;

		this.template();
		console.log("_initEventConfirms");

		// close action
		this.el.find('.data-dialog-close').on('click', function() {
			self.el.removeClass('dialog--open');
			self.el.addClass('dialog--close');

			/*
			 * onEndAnimation(self.el.find('.dialog__content'), function() {
			 * self.el.removeClass('dialog--close');
			 * 
			 * });
			 */

		});

		// moi hop thoai ngay lap tuc khi tao doi tuong
		self.toggle.apply(this);

		this.buildCSS();

	}

	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - thuoc tinh mo rong
	 *  
	 * ----------------------------------------------
	 */
	jsQueenbDialog.prototype.options = {
		// callbacks
		onOpenDialog : function() {
			return false;
		},
		onCloseDialog : function() {
			return false;
		}
	}

	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - template cho dialog
	 *  
	 * ----------------------------------------------
	 */
	jsQueenbDialog.prototype.template = function() {
		var self = this;

		if (typeof this.options.type != 'undefined'
				&& this.options.type == 'confirm') {
			var content = $('body .dialogconfirm');
		} else if(typeof this.options.type != 'undefined'
				&& this.options.type == 'alert') {
			//get content mark dialog
			var content =  $('body .dialogalert');
			console.log("CREATE  ALERT");
		} else {
			var content = $(self.el);
		}

		if (typeof this.options.title != 'undefined') {
			var title = this.options.title;
		} else {
			var title = content.attr('title');
		}

		var isSave = '';
		if (typeof self.options.save != 'undefined' && typeof self.options.save === 'string') {
			isSave = self.options.save;
			if(isSave.indexOf('(') == -1 || isSave.indexOf(')') == -1){
				isSave += '()'; 
			}
		}else{
			isSave = self.options.save;
		}
		

		var templateDialog = "<div class='dialog__overlay'></div>";
		templateDialog += " <div class='dialog__content'>";

		if (typeof this.options.content != 'undefined'
			&& this.options.content != '') {
		}else{
			templateDialog += " <div class='title__dialog'>";
			templateDialog += "  <div class='dialog-inner mb-left-bt-close'>";
			templateDialog += "    <button class='action data-dialog-close data-dialog-close-link btn btn-default' data-dialog-close>"
					+ "Back" + "</button>";
			templateDialog += " </div>";
			templateDialog += "<span>" + title + "</span>";

			if (isSave != '') {
				
				templateDialog += " <div class='qb-form-action1 mb-right-btn-save'>";
//				templateDialog += '   <input type="button" value="'
//						+ getLocalize("save") + '" onclick="' + isSave + ';">';
				templateDialog += "<button class='action btn btn-default' onclick='"+isSave+";'>"
					+ 'LUU' + "</button>";
				templateDialog += '  </div>';
			}
			
			templateDialog += "</div>";
			
			
		}
	
		templateDialog += " <div class='wrapper'>";
		// templateDialog += " {template_ohhay}";

		templateDialog += "</div>";
		templateDialog += "</div>";

		if (!content.hasClass('active-dialog')) {
			// content.html('');
			// templateDialog = templateDialog.replace("{template_ohhay}",
			// content
			// .html());
			content.addClass('active-dialog');

			content.append(templateDialog);
			if (typeof this.options.content != 'undefined'
					&& this.options.content != '') {
				content.html('');
				var title = this.options.content;

				// content.html('');
				
				content.children('.dialog__content').children(".wrapper")
						.append('<div class="input-field">' + title + '</div>');
				content.children('.dialog__content').children(".wrapper")
						.append($('<div class="qb-form-action"></div>'))

			} else {
				

				var wrapperform = content.children(".qb-form-content");
				var wrapperaction = content.children(".qb-form-action");

				content.children('.dialog__content').children(".wrapper")
						.wrapInner(wrapperform);

				// content.html('');
			}
			
			//neu la dialog text
			var isDialogText = $(self.el).attr('id')== 'dialog-text'?true: false;
			
			var isDialogVideoMarketingTitle =$(self.el).attr('id')== 'dialog-input-video-marketing-title'?true: false;
			
			if(isDialogText){
				// template danh cho edit text
				var template ='';
				template += '<div style="text-align: center;padding:0px;" class="col-xs-12">';
				template += '<p id="radio-edit-text" class="edittext-tab-btn active"><span style="font-family: times new roman;margin-right: 4px;font-size: 12px">T</span>Text</p>';
				template += '<p id="radio-edit-iframe" style="margin-left: 5px;" class="edittext-tab-btn"><i style="padding-right:4px;" class="fa fa-code"></i>HTML</p>';
				template += '<p id="radio-edit-rss" style="margin-left: 5px;" class="edittext-tab-btn"><i style="padding-right:4px;" class="fa fa-rss"></i>RSS</p>';
				template += '</div>';
			}else{
				var template ='';
				//template += '<div class="col-xs-2 edittext-action" style="display: block;"><p>Hủy</p></div>';
				template += '<p id="btnCrop" class="edittext-tab-btn" onclick="'+ isSave + ';"'+'><i class="fa fa-crop" style="padding-right:6px;"></i>'+ ''+ '</p>';
				template += '<p id="btnZoomOut" style="margin-left: 5px;" class="edittext-tab-btn"><i class="fa fa-expand" style="padding-right:6px;"></i></i>'+ ''+ '</p>';
				template += '<p id="btnZoomIn" style="margin-left: 5px;" class="edittext-tab-btn"><i style="padding-right:6px;" class="fa fa-compress"></i>'+ ''+ '</p>';
				template += '<p id="btnRotateLeft" style="margin-left: 5px;" class="edittext-tab-btn"><i class="fa fa-reply" style="padding-right:6px;"></i>'+ ''+ '</p>';
				template += '<p id="btnRotateRight" style="margin-left: 5px;" class="edittext-tab-btn" onclick=""><i class="fa fa-share" style="padding-right:6px;"></i>'+''+ '</p>';
				template += '<p id="btnCancel" style="margin-left: 5px;" class="edittext-tab-btn"><i style="padding-right:6px;" class="fa fa-times"></i>'+ '' + '</p>';
				//template += '<div class="col-xs-2 edittext-action qb-edittext-done" style="display: block;"><p>Xong</p></div>';
			
			}
			
			if (isSave != '') {
				content.children('.dialog__content').children(".wrapper")
						.append(
								'<div class="qb-form-action">'
								+'<button class="btn-save" value="'+ "Save"+ '"></button>'
								+'<button class="action data-dialog-close data-dialog-close-btn" data-dialog-close value="'+"Close" + '"></button></div>');
				//content.children('.dialog__content').children(".wrapper").children('.qb-form-content').children('.dialog-edittext-header').css('width','100%')
				//.append('<div class="col-xs-2 edittext-action" style="display: block;"><p>Hủy</p></div>');
				$(self.el).children().find('.btn-save').click(function(){
					isSave($(self.el));
					
					$(self.el).jsOhhayDialog("close"); 
				})
				//neu la dialog text
				if(isDialogText){

					if(!isDialogVideoMarketingTitle){
						content.children('.dialog__content').children(".wrapper").children('.qb-form-content').children('.dialog-edittext-header')
						.append(template);
					}
					

					//content.children('.dialog__content').children(".wrapper").children('.qb-form-content').children('.dialog-edittext-header').css('width','100%')
					//.append('<div class="col-xs-2 edittext-action qb-edittext-done" style="display: block;" onclick="'+ isSave + ';"'+'><p>Xong</p></div>');
				}else{  // nguoc lai dialog image, avarta
					content.children('.dialog__content').children(".wrapper").children('.qb-form-content').children('.dialog-edittext-header').children('.qb-fn-edit-img').css('width','100%')
					.append(template);
					//content.children('.dialog__content').children(".wrapper").children('.qb-form-content').children('.dialog-edittext-header').css('width','100%')
					//.append('<div class="col-xs-offset-8 col-xs-2 edittext-action qb-edittext-done" style="display: block;" onclick="'+ isSave + ';"'+'><p>Xong</p></div>');
				}
				
			}

			content.children(".qb-form-content").remove();
			content.children(".qb-form-action").remove();
		} else {
			
			if (typeof this.options.type != 'undefined'
					&& this.options.type == 'confirm') {
				content.html('');
				content.append(templateDialog);

				if (typeof this.options.content != 'undefined'
						&& this.options.content != '') {
					var title = this.options.content;
					content.children('.dialog__content').children(".wrapper")
							.html('<div class="qb-form-content"><div class="input-field">' + title + '</div></div>');
				}
				
				$('.dialog__content .title__dialog').show();
				
				content.children('.dialog__content').children(".wrapper")
				.append('<div class="qb-form-action"></div>');
				

			}else if (typeof this.options.type != 'undefined'
					&& this.options.type == 'alert') {
				content.html('');
				content.append(templateDialog);

				if (typeof this.options.content != 'undefined'
						&& this.options.content != '') {
					var title = this.options.content;
					content.children('.dialog__content').children(".wrapper")
							.html('<div class="qb-form-content"><div class="input-field">' + title + ' </div></div>');
				}
				
				$('.dialog__content .title__dialog').show();
				
				content.children('.dialog__content').children(".wrapper")
				.append('<div class="qb-form-action"></div>');

			}else {
				//$('.dialog__content .title__dialog').hide();
			}

			if (isSave != '') {

				
				
				if(typeof this.options.type != 'undefined'
					&& this.options.type == 'confirm') {
					content.children('.dialog__content').children(".wrapper")
					.children('.qb-form-action').show().html('').append(
							'<hr /><div class="item-data">'
							+ '<button type="button" class="data-dialog-close data-dialog-close-btn btn btn-primary col-md-offset-1" data-dialog-close >'+getLocalize("no")+'</button><div class="item-data">'
							+ '<button type="button" class="dialog-save btn btn-primary" > <img style="width:14px;margin-right:4px;" src="'+util.contextPath()+'/html/images/menu-save.png">'
							+ getLocalize("yes")
							+ '</button>'
							);
					//add event enter after dialog appear
					$(document).keypress(function(e) {
						  if(e.which == 13) {
						    // enter pressed
							 if($('.dialogconfirm.dialog--open .dialog__overlay').css('opacity')==1){
								 $('.dialogconfirm .dialog__content .wrapper .dialog-save').trigger('click');
							 }
						  }
					});
					 $('.dialogconfirm .dialog__content .wrapper .dialog-save').focus();
					//add event dialog save
					$('.dialogconfirm .dialog__content .wrapper .dialog-save').click(function(){
						isSave();
						$(".dialogconfirm").jsOhhayDialog("close"); 
					})
					
				}/*
				 * 	Dialog alert append template
				 *	Thoai vt - 20/01/2016
				 *
		 		*/else if(typeof this.options.type != 'undefined'
					&& this.options.type == 'alert') {
					content.children('.dialog__content').children(".wrapper")
					.children('.qb-form-action').show().html('').append(
							'<hr /><div class="item-data">'
							+ '<button type="button" class="data-dialog-close data-dialog-close-btn btn btn-primary col-md-offset-1" data-dialog-close ><img style="width:14px;margin-right:3px;" src="'+util.contextPath()+'/html/images/menu-save.png">Ok</button>');
					$('.data-dialog-close-btn').click(function(){
							$(".dialogalert").jsOhhayDialog("close");
					})
				}else {
					//content.children('.dialog__content').children(".wrapper")
					//.children('.qb-form-action').html('').append(
					//		'<div class="qb-form-action"><input type="button" onclick="'
					//				+ isSave + ';" value="'
					//				+ getLocalize("done").trim()
					//				+ '"></div>');
					
					content.children('.dialog__content').children(".wrapper")
					.children('.qb-form-action').show().html('').append(
							'<button class="btn-save" value="'
									+ "Save"
									+ '"></button><button class="action data-dialog-close data-dialog-close-btn" data-dialog-close value="'+ "Close" + '"></button></div>');
					
					$(self.el).children().find('.btn-save').click(function(){
						isSave($(self.el));	
						$(self.el).findChild('.content-report').val('');
						$(self.el).jsOhhayDialog("close"); 
					})
					
					//content.children('.dialog__content').children(".wrapper").children('.qb-form-content').children('.dialog-edittext-header').children('.edittext-action.qb-edittext-done')
					//.html('').append('<div class="col-xs-2 edittext-action qb-edittext-done" style="display: block;" onclick="'+ isSave + ';"'+'><p>Xong</p></div>');
					
				}
				
			}
		}
	}

	jsQueenbDialog.prototype.buildCSS = function() {
		var self = this;
		self.el.find('.dialog__overlay').css('height',
				$(document).height() + "px");
		if (isTable()) {
//			if(self.el.attr('id') == 'dialog-image'){
//				self.el.find('.dialog__content').css('margin-top',
//						$(window).scrollTop()- 50 + 2 + "px");
//			}else{
//				self.el.find('.dialog__content').css('margin-top',
//						$(window).scrollTop() + 2 + "px");
//			}
			self.el.find('.dialog__content').css('float', "left");
			$('html, body').animate({
				scrollTop : $(window).scrollTop()
			}, 2000);
		} else {
			//truong hop hinh
			if(self.el.attr('id') == 'dialog-image'){
				self.el.find('.dialog__content').css('top',
						$(window).scrollTop()- 160  + 20 + "px");
			}else{
				self.el.find('.dialog__content').css('top',
						$(window).scrollTop() + 70 + "px");
			}
		}

	}

	function isTable() {
		var isiPad = /iphone|ipod/i.test(navigator.userAgent.toLowerCase())
				|| /android/i.test(navigator.userAgent.toLowerCase())
				|| /blackberry/i.test(navigator.userAgent.toLowerCase())
				|| /windows phone/i.test(navigator.userAgent.toLowerCase());
		return isiPad;
	}

	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - chuc nang chinh
	 *  
	 * ----------------------------------------------
	 */
	var count = 0;
	jsQueenbDialog.prototype._initEvents = function() {
		var self = this;

		// tao template
		this.template();

		// close action
		this.el.find('.data-dialog-close').on('click', function() {
			self.el.removeClass('dialog--open');
			self.el.addClass('dialog--close');
			$(self.el).findChild('.content-report').val('');
			/*
			 * onEndAnimation(self.el.find('.dialog__content'), function() {
			 * self.el.removeClass('dialog--close');
			 * 
			 * });
			 */

		});

		// esc key closes dialog
		document.addEventListener('keydown', function(ev) {
			var keyCode = ev.keyCode || ev.which;
			if (keyCode === 27 && self.isOpen) {
				self.toggle();
			}
		});
	

		// moi hop
		self.toggle.apply(this);

		this.buildCSS();
		//
		// dong hop thoai khi click ra ngoai
		this.el.find('.dialog__overlay').on('click', this.toggle.bind(this));
	}

	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - dong hop thoai
	 *  
	 * ----------------------------------------------
	 */
	jsQueenbDialog.prototype.close = function() {
		var self = this;
		self.removeClass('dialog--open');
		self.addClass('dialog--close');
	}

	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - mo hop thoai
	 *  
	 * ----------------------------------------------
	 */
	jsQueenbDialog.prototype.open = function() {
		self.removeClass('dialog--close');
		self.addClass('dialog--open');
	}

	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - thuc hien dong hoac mo hop thoai
	 *  
	 * ----------------------------------------------
	 */
	jsQueenbDialog.prototype.toggle1 = function() {
		var self = this;
		if (!this.isOpen) {
			self.el.addClass('dialog--open');
			self.el.addClass('dialog--close');

			onEndAnimation(this.el.find('.dialog__content'), function() {
				if (self.el.hasClass('dialog--open')) {
					self.el.removeClass('dialog--close');
					self.el.addClass('dialog--open');
					console.log('open');
				} else {
					self.el.addClass('dialog--close');
					self.el.removeClass('dialog--open');
					console.log('close');
				}

			});

		}
		console.log(this.isOpen);
		this.isOpen = !this.isOpen;
	};

	jsQueenbDialog.prototype.toggle = function() {
		var self = this;
		if (this.isOpen) {
			self.el.removeClass('dialog--open');
			self.el.addClass('dialog--close');

			// onEndAnimation(this.el.find('.dialog__content'), function() {
			// self.el.removeClass('dialog--close');
			// });

			console.log('close');
			// callback on close
			this.options.onCloseDialog(this);
		} else {
			self.el.addClass('dialog--open');
			self.el.removeClass('dialog--close');
			//console.log('open');
			//alert('open');
			// callback on open
			//this.options.onOpenDialog(this);
		}
		this.isOpen = !this.isOpen;
	};

	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - dinh nghia bien toan cuc
	 *  
	 * ----------------------------------------------
	 */
	// window.jsQueenbDialog = jsQueenbDialog;
	/*------------------------------------------------
	 * Author: locnt 26-12-2014
	 * -----------------------------------------------
	 * Chuc nang chinh:
	 * - dinh nghia plugin
	 *  
	 * ----------------------------------------------
	 */
	$.fn.jsOhhayDialog = function(method) {

		if (typeof method === "string") {
			var data = (new jsQueenbDialog(this, method));
			return data[method].apply(this, Array.prototype.slice.call(
					arguments, 1));
		} else if (typeof method === 'object' || !method) {
			return new jsQueenbDialog(this, method);
		} else {
			$.error('Method ' + method
					+ ' does not exist on jQuery.jsOhhayDialog');
		}

	};

})(window);