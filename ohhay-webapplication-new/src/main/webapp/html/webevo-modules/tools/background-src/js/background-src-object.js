/**
 * ThoaiVt - 20/05/2017 update module to object
 */

var backGroundGobal = new BackGroundSrcObj("gobalBackground");
var backGroundSection = new BackGroundSrcObj("sectionBackground");
var backGroundBigBox = new BackGroundSrcObj("bigboxBackground");
var backGroundSmallBox = new BackGroundSrcObj("smallboxBackground");
var backGroundForm = new BackGroundSrcObj("formBackground");
var backGroundMgDilg = new BackGroundSrcObj("backGroundMgDilg");

function BackGroundSrcObj(typeBgSrc) {
	this.typeBgSrc = typeBgSrc;

	this.open = function(option) {
		var self = this;
		self.targetBox = option.targetBox;
		backGroundMgDilg.targetBox = option.targetBox;
		//console.log(backGroundMgDilg.targetBox);
		backGroundMgDilg.type = 'U';
		self.boxId = self.targetBox.closest('.content-box').attr("qb-box-id"), backGroundMgDilg.compId = self.targetBox.closest('.grid-stack-item-content').attr('qb-component-id');
		self.gradient = option.gradient;
		self.callBack = option.callBack;
		$('.background-edit-panel .menu .button-menu').removeClass('selected');
		// setup when first call
		self.init(true, 1);
		try {
			// close sub tool
			colorPicker.close();
			imageAlbum.close();
		} catch (err) {

		}
		// render option
		if (option.video == false)
			$("#qb-dlg-background-src .background-edit-panel  .sample_videos").hide();
		else
			$("#qb-dlg-background-src .background-edit-panel  .sample_videos").show();
		if (option.image == false)
			$("#qb-dlg-background-src .background-edit-panel  .button-menu-image").hide();
		else
			$("#qb-dlg-background-src .background-edit-panel  .button-menu-image").show();
		// set callback
		// open dialog
		$('#qb-dlg-background-src').dialog("close");
		$('#qb-dlg-background-src').dialog("open");
	};

	this.setBackground = function(targetBox, type) {
		console.log(this);
		var self = this;
		backGroundMgDilg.self=self;
		self.targetBox = targetBox;
		backGroundMgDilg.targetBox = targetBox;
		//console.log(backGroundMgDilg.targetBox);
		self.type = type || 'U';
		//console.log("set type : " + self.type);
		backGroundMgDilg.type = self.type;
		self.boxId = self.targetBox.closest('.content-box').attr("qb-box-id"), backGroundMgDilg.compId = self.targetBox.closest('.grid-stack-item-content').attr('qb-component-id');
		//console.log("ID : " + backGroundMgDilg.compId);
		self.cssProperty = function() {
			if (backGroundMgDilg.type == 'S' || backGroundMgDilg.type == 'I')
				return WEB_PRO_BGCSS;// WEB_PRO_CSS;
			else if (backGroundMgDilg.type == 'G')
				return WEB_PRO_GRID_BGCSS;
			else if (backGroundMgDilg.type == 'B')
				return WEB_PRO_BGCSS;
		};
		
		//console.log("Background component");
		$('.background-edit-panel .menu .button-menu').removeClass('selected');
		self.init(true, 2);
		try {
			colorPicker.close();
			imageAlbum.close();
		} catch (err) {
		}
		if (type == 'B')
			$("#qb-dlg-background-src .background-edit-panel .sample_videos").show();
		else
			$("#qb-dlg-background-src .background-edit-panel .sample_videos").hide();
		if (type == 'U' || type == 'I' || type == 'F')
			$("#qb-dlg-background-src").find(" .button-menu-image, .button-menu-position, .button-menu-size").hide();
		else
			$("#qb-dlg-background-src").find(" .button-menu-image, .button-menu-position, .button-menu-size").show();
		// type khong hien thi video, hinh
		if (type == 'F')
			$("#qb-dlg-background-src .background-edit-panel .button-menu-image").hide();
		else
			$("#qb-dlg-background-src .background-edit-panel .button-menu-image").show();
		$('#qb-dlg-background-src').dialog("close");  
		$('#qb-dlg-background-src').dialog("open");
	};

	this.init = function(firstTime, type) {
		var self = this;
		backGroundMgDilg.self = self;
		console.log(backGroundMgDilg.self);
		backGroundMgDilg.typeGB = type;
		//console.log(backGroundMgDilg.typeGB);
		//console.log("Change data : "+type);
		if (!backGroundMgDilg.setUpedDialog) {
			$('#qb-dlg-background-src').webToolDialog(264);
			backGroundMgDilg.setUpedDialog = true;
			/*
			 * set none background
			 */
			$('#qb-dlg-background-src .btn-none-bg').unbind('click');
			$(document).on('click', '#qb-dlg-background-src .btn-none-bg', function() {
				//console.log("REMOVE TYPE : " + backGroundMgDilg.typeGB);
				var self = backGroundMgDilg.self;
				if (backGroundMgDilg.typeGB === 1) {
					console.log(self);
					$(this).addClass('selected').siblings().removeClass('selected');
					webUtils.removeBackground(backGroundMgDilg.targetBox);
					$('#qb-dlg-background-src').dialog("close");
					self.callBack({
						type : 'none',
						data : ''
					});
				} else if (backGroundMgDilg.typeGB === 2) {
					//console.log(backGroundMgDilg.type);
					console.log(self);
					//console.log(self.cssProperty())
					$(this).addClass('selected').siblings().removeClass('selected');
					webUtils.removeBackground(backGroundMgDilg.targetBox);
					if (backGroundMgDilg.type != 'U')
						if (backGroundMgDilg.type == 'S') {
							a
							if (componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_TYPE] == WEB_COMP_TYPE_BTN) {
								var tempData = componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_DATA];
								webUtils.removeAllBackgroundOnMouseOutButton(tempData);
								tempData['obuttonBGHover']['onmouseout']['background'] = 'none';
								componentModelManager.updateItemField(backGroundMgDilg.compId, WEB_PRO_DATA, tempData);
							}
							componentModelManager.updateItemField(backGroundMgDilg.compId, self.cssProperty(), webUtils.getCssEffect(backGroundMgDilg.targetBox));
						} else
							bigBoxModelManager.updateItemField(self.boxId, self.cssProperty(), webUtils.getCssEffect(backGroundMgDilg.targetBox));

					self.initedN = 0;
					$('#qb-dlg-background-src').dialog("close");
				}
			});
		}
		if (backGroundMgDilg.typeGB == 1)
			this.eventListener();
		else if (backGroundMgDilg.typeGB == 2)
			this.registerAllEvent();

	};

	this.eventListener = function() {
		var self = this;

		 /*
		 * open video collection
		 */
		$('#qb-dlg-background-src .sample_videos').unbind('click');
		$(document).on('click', '#qb-dlg-background-src .sample_videos', function() {
			$(this).addClass('selected').siblings().removeClass('selected');
			videoCollection.open({
				callBack : self.callBack
			});
			$('#qb-dlg-background-src').dialog("close");
		});
		/*
		 * open background repeat collection
		 */
		$('#qb-dlg-background-src .sample_backgrounds').unbind('click');
		$(document).on('click', '#qb-dlg-background-src .sample_backgrounds', function() {
			$(this).addClass('selected').siblings().removeClass('selected');
			bgRepeatCollection.open({
				callBack : self.callBack
			});
			$('#qb-dlg-background-src').dialog("close");
		});
		/*
		 * open color picker
		 */
		$('#qb-dlg-background-src .qb-btn-color').unbind('click');
		$(document).on('click', '#qb-dlg-background-src .qb-btn-color', function() {
			$(this).addClass('selected').siblings().removeClass('selected');
			colorPicker.open({
				callBack : self.callBack,
				targetBox : self.targetBox,
				gradient : self.gradient,
			});
			$('#qb-dlg-background-src').dialog("close");
		});
		/*
		 * open album collection
		 */
		$('#qb-dlg-background-src .background-edit-panel .button-menu-image').unbind('click');
		$(document).on('click touchend', "#qb-dlg-background-src  .background-edit-panel .button-menu-image", function(event) {
			$(this).addClass('selected').siblings().removeClass('selected');
			imageAlbum.open({
				callBack : self.callBack
			});
			$('#qb-dlg-background-src').dialog("close");
		});
	};

	// ANPH:::::::::::::::
	this.registerAllEvent = function() {
		var self = this;
		// TEMP: se xoa, dung de remove bigbox li bg
		if (self.type == 'B') {
			webUtils.removeBackground(self.targetBox.closest('li'));
			bigBoxModelManager.updateItemField(self.boxId, WEB_PRO_CSS, webUtils.getCssEffect(self.targetBox.closest('li')));
		}
		// TEMP: se xoa, dung de remove bigbox li bg
		
		/*
		 * open video collection
		 */
		$('#qb-dlg-background-src .sample_videos').unbind('click');
		$(document).on('click', '#qb-dlg-background-src .sample_videos', function() {
			$(this).addClass('selected').siblings().removeClass('selected');
			videoCollection.open({
				callBack : function(result) {
					if (result.data != 0) {
						webUtils.setBackgroundVideo(self.targetBox, result);
						if (self.type != 'U')
							if (self.type != 'S')
								bigBoxModelManager.updateItemField(self.boxId, WEB_PRO_BG_VID, result);
						self.initedN = 0;
					}
				}
			});
			$('#qb-dlg-background-src').dialog("close");
		});
		/*
		 * open background repeat collection
		 */
		$('#qb-dlg-background-src sample_backgrounds').unbind('click');
		$(document).on('click', '#qb-dlg-background-src .sample_backgrounds', function() {
			$(this).addClass('selected').siblings().removeClass('selected');
			bgRepeatCollection.open({
				callBack : function(result) {
					webUtils.setBackgroundRepeat(self.targetBox, result.data);
					if (self.type != 'U')
						if (self.type == 'S') {
							if (componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_TYPE] == WEB_COMP_TYPE_BTN) {
								var tempData = componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_DATA];
								webUtils.removeAllBackgroundOnMouseOutButton(tempData);
								tempData['obuttonBGHover']['onmouseout']['background-color'] = 'inherit';
								tempData['obuttonBGHover']['onmouseout']['background-image'] = 'url(' + result.data + ')';
								tempData['obuttonBGHover']['onmouseout']['background-repeat'] = 'repeat';
								tempData['obuttonBGHover']['onmouseout']['background-size'] = 'auto';
								componentModelManager.updateItemField(backGroundMgDilg.compId, WEB_PRO_DATA, tempData);
							}
							componentModelManager.updateItemField(backGroundMgDilg.compId, self.cssProperty(), webUtils.getCssEffect(self.targetBox));
						} else
							{
							console.log(self.targetBox);
							bigBoxModelManager.updateItemField(self.boxId, self.cssProperty(), webUtils.getCssEffect(self.targetBox));

							}
					self.initedN = 0;
				}
			});
			$('#qb-dlg-background-src').dialog("close");
		});
		/*
		 * open color picker
		 */
		$('#qb-dlg-background-src .qb-btn-color').unbind('click');
		$(document).on('click', '#qb-dlg-background-src .qb-btn-color', function() {
			$(this).addClass('selected').siblings().removeClass('selected');
			colorPicker.open({
				callBack : function(result) {
					if (result.type.indexOf('gradient') == -1) {
						webUtils.removeBackground(self.targetBox);
						webUtils.setBackgroundColor(self.targetBox, result);
					}
					if (self.type != 'U')
						if (self.type == 'S') {
							if (componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_TYPE] == WEB_COMP_TYPE_BTN) {
								var tempData = componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_DATA];
								webUtils.removeAllBackgroundOnMouseOutButton(tempData);
								if (result.type === 'gradient-x') {
									tempData['obuttonBGHover']['onmouseout']['background-image'] = 'linear-gradient(to right, ' + result.data + ')';
									tempData['obuttonBGHover']['onmouseout']['background-color'] = 'initial';
								} else if (result.type === 'gradient-y') {
									tempData['obuttonBGHover']['onmouseout']['background-image'] = 'linear-gradient(' + result.data + ')';
									tempData['obuttonBGHover']['onmouseout']['background-color'] = 'initial';
								} else {
									tempData['obuttonBGHover']['onmouseout']['background-color'] = result.data;
									tempData['obuttonBGHover']['onmouseout']['background-image'] = 'initial';
								}
								componentModelManager.updateItemField(backGroundMgDilg.compId, WEB_PRO_DATA, tempData);
							}
							componentModelManager.updateItemField(backGroundMgDilg.compId, self.cssProperty(), webUtils.getCssEffect(self.targetBox));
						} else
							bigBoxModelManager.updateItemField(self.boxId, self.cssProperty(), webUtils.getCssEffect(self.targetBox));
					self.initedN = 0;
				},
				targetBox : self.targetBox
			});
			$('#qb-dlg-background-src').dialog("close");
		});
		/*
		 * open album collection
		 */
		$('#qb-dlg-background-src .background-edit-panel .button-menu-image').unbind('click');
		$(document).on('click touchend', "#qb-dlg-background-src  .background-edit-panel .button-menu-image", function(event) {
			$(this).addClass('selected').siblings().removeClass('selected');
			if (self.targetBox.hasClass('wrapper-background'))
				imageAlbum.open({
					callBack : self.callBack
				});
			else
				imageAlbum.open({
					callBack : function(result) {
						imageBGUtil.generateBGImage(self.targetBox, result.data, self.type, function(src, css) {
							if (self.type != 'U')
								if (self.type == 'S') {
									if (componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_TYPE] == WEB_COMP_TYPE_BTN) {
										var tempData = componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_DATA];
										webUtils.removeAllBackgroundOnMouseOutButton(tempData);
										tempData['obuttonBGHover']['onmouseout']['background-color'] = 'inherit';
										tempData['obuttonBGHover']['onmouseout']['background-image'] = 'url(' + result.data + ')';
										tempData['obuttonBGHover']['onmouseout']['background-repeat'] = 'no-repeat';
										tempData['obuttonBGHover']['onmouseout']['background-attachment'] = 'scroll';
										tempData['obuttonBGHover']['onmouseout']['background-position'] = '0% 0%';
										tempData['obuttonBGHover']['onmouseout']['background-clip'] = 'border-box';
										tempData['obuttonBGHover']['onmouseout']['background-origin'] = 'padding-box';
										tempData['obuttonBGHover']['onmouseout']['background-size'] = 'cover';
										componentModelManager.updateItemField(backGroundMgDilg.compId, WEB_PRO_DATA, tempData);
									}
									componentModelManager.updateItemField(backGroundMgDilg.compId, self.cssProperty(), webUtils.getCssEffect(self.targetBox));
								} else
									bigBoxModelManager.updateItemField(self.boxId, self.cssProperty(), css);
							self.initedN = 0;
						});
					}
				});
		});
		/*
		 * Background position
		 */
		$(document).on('click touchend', "#qb-dlg-background-src  .background-edit-panel .button-menu-position", function(event) {
			$(this).addClass('selected').siblings().removeClass('selected');

			$('.option-horizontal ul.dropdown-menu>li').unbind('click');
			$('.option-horizontal ul.dropdown-menu>li').on('click', function() {
				var dataVal = $(this).attr('data-val').trim();
				$(this).parent().prev().find('.dd-title').text(dataVal);
				// manual option
				if (dataVal == 'manual')
					$('.button-menu-position ul.content>li:LAST-CHILD').show();
				else
					$('.button-menu-position ul.content>li:LAST-CHILD').hide();

				// ! manual||initial option
				if (!(dataVal == 'manual' || dataVal == 'initial')) {
					$('.button-menu-position ul.content>li:FIRST-CHILD .dropup:LAST-CHILD').show();
					self.pos1 = dataVal;
					self.setBackgroundPosition(self.pos1 + ' ' + self.pos2);
				} else {
					$('.button-menu-position ul.content>li:FIRST-CHILD .dropup:LAST-CHILD').hide();
					if (dataVal == 'initial')
						self.setBackgroundPosition(dataVal);
				}

			});
			$('.option-vertical ul.dropdown-menu>li').unbind('click');
			$('.option-vertical ul.dropdown-menu>li').on('click', function() {
				var dataVal = $(this).attr('data-val').trim();
				$(this).parent().prev().find('.dd-title').text(dataVal);
				self.pos2 = dataVal;
				self.setBackgroundPosition(self.pos1 + ' ' + self.pos2);
			});
			$('.bg-position').unbind('keyup');
			$('.bg-position').on('keyup', function() {
				var dataVal = $(this).val().trim();
				if ($(this).hasClass('bg-position-hon')) {
					self.pos1 = dataVal;
					self.setBackgroundPosition(self.pos1 + ' ' + self.pos2);
				} else {
					self.pos2 = dataVal;
					self.setBackgroundPosition(self.pos1 + ' ' + self.pos2);
				}
			});
		});
		/*
		 * Background size
		 */
		$(document).on('click touchend', "#qb-dlg-background-src  .background-edit-panel .button-menu-size", function(event) {
			$(this).addClass('selected').siblings().removeClass('selected');

			$('.option-bg-size ul.dropdown-menu>li').unbind('click');
			$('.option-bg-size ul.dropdown-menu>li').on('click', function() {
				var dataVal = $(this).attr('data-val').trim();
				$(this).parent().prev().find('.dd-title').text(dataVal);
				// manual option
				if (dataVal == 'manual')
					$('.button-menu-size ul.content>li:LAST-CHILD').show();
				else {
					$('.button-menu-size ul.content>li:LAST-CHILD').hide();
					self.setBackgroundSize(dataVal);
				}
			});
			$('.bg-size').unbind('keyup');
			$('.bg-size').on('keyup', function() {
				var dataVal = $(this).val().trim();
				if ($(this).hasClass('bg-size-w')) {
					self.size1 = dataVal;
					self.setBackgroundSize(self.size1 + ' ' + self.size2);
				} else {
					self.size2 = dataVal;
					self.setBackgroundSize(self.size1 + ' ' + self.size2);
				}
			});
		});
	};

	this.setBackgroundPosition = function(val) {
		var self = this;
		self.targetBox.css('background-position', val);
		if (self.type == 'S') {
			if (componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_TYPE] == WEB_COMP_TYPE_BTN) {
				var tempData = componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_DATA];
				tempData['obuttonBGHover']['onmouseout']['backgroun-position'] = val;
				componentModelManager.updateItemField(backGroundMgDilg.compId, WEB_PRO_DATA, tempData);
			}
			componentModelManager.updateItemField(backGroundMgDilg.compId, self.cssProperty(), webUtils.getCssEffect(self.targetBox));
		} else
			bigBoxModelManager.updateItemField(self.boxId, self.cssProperty(), webUtils.getCssEffect(self.targetBox));

	};

	this.setBackgroundSize = function(val) {
		var self = this;
		self.targetBox.css('background-size', val);
		if (self.type == 'S') {
			if (componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_TYPE] == WEB_COMP_TYPE_BTN) {
				var tempData = componentModelManager.listComponent[backGroundMgDilg.compId][WEB_PRO_DATA];
				tempData['obuttonBGHover']['onmouseout']['background-size'] = val;
				componentModelManager.updateItemField(backGroundMgDilg.compId, WEB_PRO_DATA, tempData);
			}
			componentModelManager.updateItemField(backGroundMgDilg.compId, self.cssProperty(), webUtils.getCssEffect(self.targetBox));
		} else
			bigBoxModelManager.updateItemField(self.boxId, self.cssProperty(), webUtils.getCssEffect(self.targetBox));

	}

}