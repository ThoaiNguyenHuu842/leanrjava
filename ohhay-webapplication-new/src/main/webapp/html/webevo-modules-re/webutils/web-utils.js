/**
 * @author ThoaiNH create Nov 11, 2015 util function for web editor
 */
(function() {
	webUtils = {
		/*
		 * set background video
		 */
		setBackgroundVideo : function(backgroundLayer, result) {
			if (result.type == 'video') {
				webUtils.removeBackground(backgroundLayer);
				backgroundLayer.append(buildTemplateVideo(result.data));
			}
		},
		/*
		 * remove background
		 */
		removeBackground : function(object) {
			object.css("background", "none");
			if (object.hasClass('layer-background'))
				object.html('');
			object.css('filter', 'none');
			object.css('-webkit-filter', 'none');
		},
		/*
		 * param: box object, data (color or img url )
		 */
		setBackgroundRepeat : function(object, data) {
			object.css('filter', 'none');
			object.css('-webkit-filter', 'none');
			object.css("background-color", "inherit");
			object.css('background-image', 'url(' + data + ')');
			object.css('background-repeat', 'repeat');
			object.css('background-size', 'auto');
		},
		/*
		 * param: box object, data (color or img url )
		 */
		setBackgroundImg : function(object, imgUrl, bgSize) {
			bgSize = bgSize || 'cover';
			object.css("background-color", "inherit");
			object.css('background-image', 'url(' + imgUrl + ')');
			object.css('background-repeat', 'no-repeat');
			object.css('background-size', bgSize);
		},
		/*
		 * param: box object, data (color or img url )
		 */
		setBackgroundColor : function(object, result) {
			object.css('filter', 'none');
			object.css('-webkit-filter', 'none');
			if (object.attr('cp-opacity'))// kiem tra co set opacity ko
			{
				var bg_color = 'rgba(' + result._rgb.r + ',' + result._rgb.g + ',' + result._rgb.b + ',' + object.attr('cp-opacity') + ')';
				object.css({
					'background-color' : bg_color,
					'background-image' : 'initial'
				});
			} else
				object.css({
					'background-color' : result.data,
					'background-image' : 'initial'
				});
		},
		/*
		 * set background gradient
		 */
		setBackgroundGradient : function(obj, val, type) {
			if (type === 'x') {
				obj.css({
					"background" : "-webkit-linear-gradient(left," + val + ")",
					"background" : "-moz-linear-gradient(right, " + val + ")",
					"background" : "-o-linear-gradient(right, " + val + ")",
					"background" : "linear-gradient(to right, " + val + ")",
				});
			} else {
				obj.css({
					"background" : "-webkit-linear-gradient(" + val + ")",
					"background" : "-moz-linear-gradient(" + val + ")",
					"background" : "-o-linear-gradient(" + val + ")",
					"background" : "linear-gradient(" + val + ")",
				});
			}
		},
		/*
		 * comment ?
		 */
		getCssEffect : function(object) {
			try {
				var styletag = object.attr('style').slice(0, -1);
				var stylestemp = styletag.split('; ');
				var c = '';
				var result = '';
				for ( var x in stylestemp) {
					var ss = stylestemp[x].replace(':', 'qb##qb');
					c = ss.split('qb##qb');
					if ($.trim(c[0]).length > 0 && $.trim(c[0]) != 'width' && $.trim(c[0]) != 'height' && $.trim(c[0]) != 'margin')
						result += $.trim(c[0]) + ':' + $.trim(c[1]) + ';';
				}

				return result;
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
			return "";
		},
		getCssEffectV2 : function(object) {
			try {
				var styletag = object.attr('style').slice(0, -1);
				var stylestemp = styletag.split('; ');
				var c = '';
				var result = '';
				for ( var x in stylestemp) {
					var ss = stylestemp[x].replace(':', 'qb##qb');
					c = ss.split('qb##qb');
					if ($.trim(c[0]).length > 0 && $.trim(c[0]) != 'margin')
						result += $.trim(c[0]) + ':' + $.trim(c[1]) + ';';
				}
				
				return result;
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
			return "";
		},
		/*
		 * comment ?
		 */
		setTransformRotate : function(object, value) {
			try {
				object.css('-ms-transform', 'rotate(' + value + 'deg)');
				object.css('-webkit-transform', 'rotate(' + value + 'deg)');
				object.css('transform', 'rotate(' + value + 'deg)');
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}
		},
		/*
		 * comment ?
		 */
		addAttributeEffect : function(target, hoverEffect, attribute) {
			var onMouseOver = '';
			$.each(hoverEffect, function(k, v) {
				if (Object.prototype.toString.call(v) === "[object Array]") {
					$.each(v, function(k1, v1) {
						onMouseOver += k + '=\'' + v1 + '\'; ';
					});
				} else
					onMouseOver += k + '=\'' + v + '\'; ';
			});
			target.attr(attribute, onMouseOver);
		},
		/*
		 * convert color from hex to rgb
		 * 
		 */
		hexToRgb : function(hex) {
			var shorthandRegex = /^#?([a-f\d])([a-f\d])([a-f\d])$/i;
			hex = hex.replace(shorthandRegex, function(m, r, g, b) {
				return r + r + g + g + b + b;
			});
			var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
			return result ? {
				r : parseInt(result[1], 16),
				g : parseInt(result[2], 16),
				b : parseInt(result[3], 16)
			} : null;
		},
		/*
		 * convert color from rgb to hex
		 */
		rgb2hex : function(rgb) {
			rgb = rgb.match(/^rgba?[\s+]?\([\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?/i);
			return (rgb && rgb.length === 4) ? "#" + ("0" + parseInt(rgb[1], 10).toString(16)).slice(-2) + ("0" + parseInt(rgb[2], 10).toString(16)).slice(-2) + ("0" + parseInt(rgb[3], 10).toString(16)).slice(-2) : '';
		},
		/*
		 * change writing mode for text 
		 */
		changeWritingMode: function(target,isVertical){
			if (isVertical)
				target.css({
					'writing-mode': 'vertical-rl',
					'-webkit-writing-mode': 'vertical-rl',
					'-ms-writing-mode': 'vertical-rl',
				});
			else 
				target.css({
					'writing-mode': '',
					'-webkit-writing-mode': '',
					'-ms-writing-mode': '',
				});
		},
		/*
		 * remove data from onMouseOut for button
		 */
		removeAllBackgroundOnMouseOutButton: function(buttonData){
			if(buttonData && buttonData['obuttonBGHover']['onmouseout']){
				var onMouseOut = buttonData['obuttonBGHover']['onmouseout'];
				if (onMouseOut['background'])
					delete onMouseOut['background'];
				if (onMouseOut['background-color'])
					delete onMouseOut['background-color'];
				if (onMouseOut['background-image'])
					delete onMouseOut['background-image'];
				if (onMouseOut['background-repeat'])
					delete onMouseOut['background-repeat'];
				if (onMouseOut['background-attachment'])
					delete onMouseOut['background-attachment'];
				if (onMouseOut['background-position'])
					delete onMouseOut['backgroun-position'];
				if (onMouseOut['background-clip'])
					delete onMouseOut['background-clip'];
				if (onMouseOut['background-origin'])
					delete onMouseOut['background-origin'];
				if (onMouseOut['background-size'])
					delete onMouseOut['background-size'];
			}
		},
		/*
		 * get template path
		 */
		getTemplatePath: function(templateName) {
			return util.contextPath()+'/html/webevo-modules/components/templates/'+templateName;
		}
	}
}());
(function($) {
	if ($.fn.style) {
		return;
	}

	// Escape regex chars with \
	var escape = function(text) {
		return text.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
	};

	// For those who need them (< IE 9), add support for CSS functions
	var isStyleFuncSupported = !!CSSStyleDeclaration.prototype.getPropertyValue;
	if (!isStyleFuncSupported) {
		CSSStyleDeclaration.prototype.getPropertyValue = function(a) {
			return this.getAttribute(a);
		};
		CSSStyleDeclaration.prototype.setProperty = function(styleName, value, priority) {
			this.setAttribute(styleName, value);
			var priority = typeof priority != 'undefined' ? priority : '';
			if (priority != '') {
				// Add priority manually
				var rule = new RegExp(escape(styleName) + '\\s*:\\s*' + escape(value) + '(\\s*;)?', 'gmi');
				this.cssText = this.cssText.replace(rule, styleName + ': ' + value + ' !' + priority + ';');
			}
		};
		CSSStyleDeclaration.prototype.removeProperty = function(a) {
			return this.removeAttribute(a);
		};
		CSSStyleDeclaration.prototype.getPropertyPriority = function(styleName) {
			var rule = new RegExp(escape(styleName) + '\\s*:\\s*[^\\s]*\\s*!important(\\s*;)?', 'gmi');
			return rule.test(this.cssText) ? 'important' : '';
		}
	}

	// The style function
	$.fn.style = function(styleName, value, priority) {
		// DOM node
		var node = this.get(0);
		// Ensure we have a DOM node
		if (typeof node == 'undefined') {
			return this;
		}
		// CSSStyleDeclaration
		var style = this.get(0).style;
		// Getter/Setter
		if (typeof styleName != 'undefined') {
			if (typeof value != 'undefined') {
				// Set style property
				priority = typeof priority != 'undefined' ? priority : '';
				style.setProperty(styleName, value, priority);
				return this;
			} else {
				// Get style property
				return style.getPropertyValue(styleName);
			}
		} else {
			// Get CSSStyleDeclaration
			return style;
		}
	};
})(jQuery);