/*
 * author: ThoaiNH
 * date create: 16/10/2015
 */
(function() {
	colorPicker = {
		targetObj : null,
		gradient : true,
		/*
		 * open tool
		 */
		open : function(option) {
			this.targetObj = option.targetBox;
			// if(option.gradient)
			this.gradient = option.gradient;

			// init when first call
			if (!colorPicker.inited) {
				colorPicker.init();
				colorPicker.inited = true;
			}
			colorPicker.callBack = option.callBack;
			if (option.targetBox) {
				if (this.gradient == false) {
					// case color
					$('.colorpickerBody .flag-display').hide();
					$('.colorpickerBody .flag-visible').css('visibility', 'hidden');
					$('#gradientCheck').prop('checked', false);
					if (option.targetBox.css('background-color') != 'transparent' && option.targetBox.css('background-color') != 'rgba(0, 0, 0, 0)')
						colorPicker.getValFromObj(option.targetBox.css('background-color'));
					else {
						colorPicker.getDefault();
					}
				} else {
					// case background
					$('.colorpickerBody .flag-display').show();
					$('.colorpickerBody .flag-visible').css('visibility', 'visible');
					if (option.targetBox.css('background-image').indexOf('linear-gradient') > -1) {
						// case gradient
						var clor_tmp = option.targetBox.css('background-image');
						if (clor_tmp.indexOf('left') > -1 || clor_tmp.indexOf('right') > -1) {
							$('#gradientType .dd-title').attr('data-type', 'x');
						} else
							$('#gradientType .dd-title').attr('data-type', 'y');

						$('#gradientCheck').prop('checked', true);
						var gcolor = clor_tmp.replace('linear-gradient(to right,', '').replace('-moz-linear-gradient(right,', '').replace('-webkit-linear-gradient(left,', '').replace('-o-linear-gradient(right,', '').replace('linear-gradient(', '').replace('-moz-linear-gradient(', '').replace('-webkit-linear-gradient(', '').replace('-o-linear-gradient(', '');

						var gclor_re = gcolor.trim().split('%,');
						var gclor_from = gclor_re[0].trim().split(")");
						var gclor_to = gclor_re[1].trim().replace(')', '');
						var from = gclor_from[0] + ")";
						colorPicker.getValFromObj(from);
						$('.gradient-panel .color-show').removeClass('active');
						$('.gradient-color-first').data('val', from).css('background-color', from).addClass('active');
						$('.gradient-color-last').data('val', gclor_to).css('background-color', gclor_to);
						$('.gradient-val').val(gclor_from[1]);
						$('.colorpickerBody .gradient-slider').slider({
							value : gclor_from[1]
						});
						$('.val-slider').html(gclor_from[1]);
					} else {
						// case background color
						$('#gradientCheck').prop('checked', false);
						if (option.targetBox.css('background-color') != 'transparent' && option.targetBox.css('background-color') != 'rgba(0, 0, 0, 0)')
							colorPicker.getValFromObj(option.targetBox.css('background-color'));
						else
							colorPicker.getDefault();
					}

				}
			}

			$('#qb-dlg-color-picker').dialog("open");
			if (option.value) {
				$('.colorpickerHolder').ColorPickerSetColor(option.value);
			}

		},
		getValFromObj : function(rgba) {
			var objcolor = rgba.split(',');
			var colov = webUtils.rgb2hex(rgba);
			$("#colorpickerValue_r").val(objcolor[0].replace('rgb(', '').replace('rgba(', ''));
			$("#colorpickerValue_g").val(objcolor[1]);
			$("#colorpickerValue_b").val(objcolor[2].replace(")", ''));
			$('.colorpickerHolder').ColorPickerSetColor(colov);
			var colorFrom = "rgba(" + objcolor[0].replace('rgb(', '').replace('rgba(', '') + "," + objcolor[1] + "," + objcolor[2].replace(")", '') + ",0)";
			var colorTo = "rgba(" + objcolor[0].replace('rgb(', '').replace('rgba(', '') + "," + objcolor[1] + "," + objcolor[2].replace(")", '') + ",1)";
			// set opacity
			if (objcolor[3]) {
				var opa = objcolor[3].replace(")", '');
				$(".gradient-wapper p label").html(opa * 100);
				$("#colorpickerOpacity").val(opa);
				$('.colorpickerOpacity').slider({
					value : opa * 100
				});
			} else {
				$(".gradient-wapper p label").html(100);
				$("#colorpickerOpacity").val(1);
				$('.colorpickerOpacity').slider({
					value : 100
				});
			}
			// set background for opacity slider
			$('.colorpickerOpacity').css({
				"background" : "-webkit-linear-gradient(left, " + colorFrom + ", " + colorTo + ")",
				"background" : "-moz-linear-gradient(right, " + colorFrom + ", " + colorTo + ")",
				"background" : "-o-linear-gradient(right, " + colorFrom + ", " + colorTo + ")",
				"background" : "linear-gradient(to right, " + colorFrom + ", " + colorTo + ")"
			});
		},
		getDefault : function() {
			$("#colorpickerValue_r").val('0');
			$("#colorpickerValue_g").val('0');
			$("#colorpickerValue_b").val('0');
			$('.colorpickerHolder').ColorPickerSetColor('#000000');
			var colorFrom = "rgba(0,0,0,0)";
			var colorTo = "rgba(0,0,0,1)";
			// set opacity
			$(".gradient-wapper p label").html(100);
			$("#colorpickerOpacity").val(1);
			$('.colorpickerOpacity').slider({
				value : 100
			});
			// set background for opacity slider
			$('.colorpickerOpacity').css({
				"background" : "-webkit-linear-gradient(left, " + colorFrom + ", " + colorTo + ")",
				"background" : "-moz-linear-gradient(right, " + colorFrom + ", " + colorTo + ")",
				"background" : "-o-linear-gradient(right, " + colorFrom + ", " + colorTo + ")",
				"background" : "linear-gradient(to right, " + colorFrom + ", " + colorTo + ")"
			});
		},
		/*
		 * close tool
		 */
		close : function() {
			$('#qb-dlg-color-picker').dialog("close");
		},
		/*
		 * setup tool
		 */
		init : function() {
			colorPicker.eventListener();
			$('.colorpickerHolder').ColorPicker({
				flat : true,
				color : "#ff0099",
				onChange : function(hsb, hex, rgb) {
					var val_color = 'rgba(' + rgb.r + ',' + rgb.g + ',' + rgb.b + ',' + $('#colorpickerOpacity').val() + ')';
					// set value for input hidden
					$('#colorpickerValue_r').val(rgb.r);
					$('#colorpickerValue_g').val(rgb.g);
					$('#colorpickerValue_b').val(rgb.b);

					// set background for slider
					var colorTo = "rgba(" + rgb.r + "," + rgb.g + "," + rgb.b + ",0)";
					var colorFrom = "rgba(" + rgb.r + "," + rgb.g + "," + rgb.b + ",1)";
					$('.colorpickerOpacity').css({
						"background" : "-webkit-linear-gradient(left, " + colorTo + ", " + colorFrom + ")",
						"background" : "-moz-linear-gradient(right, " + colorTo + ", " + colorFrom + ")",
						"background" : "-o-linear-gradient(right, " + colorTo + ", " + colorFrom + ")",
						"background" : "linear-gradient(to right, " + colorTo + ", " + colorFrom + ")"
					});
					if (!$('#gradientCheck').prop('checked')) {
						colorPicker.callBack({
							type : 'color',
							data : val_color
						});
					} else {
						var color_obj = $('.colorpickerBody .gradient-panel .color-show.active');
						if (color_obj.length == 1) {
							color_obj.data("val", val_color).css("background-color", val_color);
						}
						colorPicker.gradientProcess();
					}
				}
			});
			$('.colorpickerOpacity').slider({
				max : 100,
				min : 0,
				value : 100,
				slide : function(event, ui) {
					$('#colorpickerOpacity').val(ui.value / 100);
					$('.colorpickerBody p label').html(ui.value);
					var color_r = $('#colorpickerValue_r').val();
					var color_g = $('#colorpickerValue_g').val();
					var color_b = $('#colorpickerValue_b').val();
					var val_color = 'rgba(' + color_r + ',' + color_g + ',' + color_b + ',' + ui.value / 100 + ')';

					if (!$('#gradientCheck').prop('checked')) {
						colorPicker.callBack({
							type : 'color',
							data : val_color
						});
					} else {
						var color_obj = $('.colorpickerBody .gradient-panel .color-show.active');
						if (color_obj.length == 1) {
							color_obj.data("val", val_color).css("background-color", val_color);
						}
						colorPicker.gradientProcess();
					}
				}
			});

			$('.colorpickerBody .gradient-slider').slider({
				max : 100,
				min : 0,
				value : 0,
				slide : function(event, ui) {
					$('#gradient-val').val(ui.value);
					$('.val-slider').html(ui.value);
					colorPicker.gradientProcess();
				}
			});

			$('.colorpickerOpacity').css({
				"background" : "-webkit-linear-gradient(left, rgba(255,0,153,0),rgba(255,0,153,1))",
				"background" : "-moz-linear-gradient(right, rgba(255,0,153,0),rgba(255,0,153,1))",
				"background" : "-o-linear-gradient(right, rgba(255,0,153,0),rgba(255,0,153,1))",
				"background" : "linear-gradient(to right, rgba(255,0,153,0),rgba(255,0,153,1))"
			});

			/*
			 * $('.colorpickerHolder #evo-colorpicker').minicolors({
			 * inline:true, opacity: true, change: function(value, opacity) {
			 * console.log(value + ' - ' + opacity+' -
			 * '+$("#evo-colorpicker").val()); }, format: 'rgb', hide:
			 * function(){ console.log($("#evo-colorpicker").val()); } });
			 */

			$('#qb-dlg-color-picker').webToolDialog(358);
		},
		/*
		 * tools event
		 */
		eventListener : function() {
			$(document).on('click', '.colorpickerBody .gradient-panel .color-show', function() {
				$('.colorpickerBody .gradient-panel .color-show').removeClass('active');
				$(this).addClass('active');
				colorPicker.getValFromObj($(this).data('val'));
			});
			$(document).on('click', '.gradient-type ul li', function() {
				$('#gradientType .dd-title').attr('data-type', $(this).data('val'));
				colorPicker.gradientProcess();
			});
		},
		gradientProcess : function() {
			var colorTo = $('.colorpickerBody .gradient-color-first').data('val') ? $('.colorpickerBody .gradient-color-first').data('val') : 'rgba(0,0,0,0)';
			var colorFrom = $('.colorpickerBody .gradient-color-last').data('val') ? $('.colorpickerBody .gradient-color-last').data('val') : 'rgba(0,0,0,0)';
			var inputval = $('#gradient-val').val() ? $('#gradient-val').val() : 0;
			var val_color = colorTo + " " + inputval + "% , " + colorFrom;
			$('.gradient-panel .gradient-slider').css({
				"background" : "-webkit-linear-gradient(left, " + val_color + ")",
				"background" : "-moz-linear-gradient(right, " + val_color + ")",
				"background" : "-o-linear-gradient(right, " + val_color + ")",
				"background" : "linear-gradient(to right, " + val_color + ")"
			});
			var type = $('.gradient-type .dd-title').attr('data-type');
			if (type === "x") {
				console.log(val_color);
				colorPicker.targetObj.css({
					"background" : "-webkit-linear-gradient(left, " + val_color + ")",
					"background" : "-moz-linear-gradient(right, " + val_color + ")",
					"background" : "-o-linear-gradient(right, " + val_color + ")",
					"background" : "linear-gradient(to right, " + val_color + ")",
					"background-color" : "initial"
				});
			} else {
				colorPicker.targetObj.css({
					"background" : "-webkit-linear-gradient(" + val_color + ")",
					"background" : "-moz-linear-gradient(" + val_color + ")",
					"background" : "-o-linear-gradient(" + val_color + ")",
					"background" : "linear-gradient(" + val_color + ")",
					"background-color" : "initial"
				});
			}
			colorPicker.callBack({
				type : 'gradient-' + type,
				data : val_color
			});
		},
	}
}());