/**
 * @author ThienND
 * 
 * created Nov 20, 2015
 */
(function() {
	hoverOption = {
		open : function(option) {
			if (!hoverOption.inited) {
				hoverOption.init();
				hoverOption.inited = true;
			}

			hoverOption.target = option.target;
			hoverOption.targetTitle = option.targetTitle;
			hoverOption.callBack = option.callBack;
			$('#qb-dlg-hover-option').dialog('open');

			var hoBackgroundTransition = hoverOption.target.attr('ho-transition');
			$('#qb-dlg-hover-option .option-duration.background-duration').val(hoBackgroundTransition ? hoBackgroundTransition : 0)
			var hoBackground = option.target.attr('ho-background');
			hoverOption.hoBackground = hoBackground ? hoBackground : 'white';
			$('.hover-option-component .hover-component .hover-option-item .option-background').css('background-color', hoverOption.hoBackground);

			var hoTitleTransition = hoverOption.target.attr('ho-title-transition');
			$('#qb-dlg-hover-option .option-duration.title-duration').val(hoTitleTransition ? hoTitleTransition : 0)
			var hoColor = option.target.attr('ho-title');
			hoverOption.hoColor = hoColor ? hoColor : 'white';
			$('.hover-option-component .hover-component .hover-option-item .option-title').css('background-color', hoverOption.hoColor);

			$('a.demo-effect').removeClass('selected-effect');
			hoverOption.selectedEffect = option.target.attr('ho-effect');
			if (hoverOption.selectedEffect && hoverOption.selectedEffect != 'hvr-no-effect') {
				$('a.' + hoverOption.selectedEffect).addClass('selected-effect');
			}
			if (hoverOption.selectedEffect != undefined) {
				$('a.option-remove.remove-hover-effect').removeClass('hidden')
			}
			if ($('#qb-dlg-hover-option .option-background').css('background-color') !== 'rgb(255, 255, 255)')
				$('a.option-remove.remove-background-effect').removeClass('hidden')
			if ($('#qb-dlg-hover-option .option-duration.background-duration').val() === 0)
				$('a.option-remove.remove-background-effect').removeClass('hidden')
		},
		close : function() {
			$('#qb-dlg-hover-option').dialog('close');
		},
		init : function() {
			hoverOption.eventListener();
			$('#qb-dlg-hover-option').webToolDialog(800);
			hoverOption.inited = true
			$('#qb-dlg-hover-option .option-duration.background-duration').spinner({
				step : 0.1,
				min : 0,
				spin : function(event, ui) {
					hoverOption.backgroundDurationProcess(event);
				},
				stop : function(event, ui) {
					hoverOption.backgroundDurationProcess(event);
				}
			});
			$('#qb-dlg-hover-option .option-duration.title-duration').spinner({
				step : 0.1,
				min : 0,
				spin : function(event, ui) {
					hoverOption.titleDurationProcess(event);
				},
				stop : function(event, ui) {
					hoverOption.titleDurationProcess(event);
				}
			});
		},
		eventListener : function() {
			$(document).on('click', '#qb-dlg-hover-option .option-background', function() {
				var colorShow = $(this);
				colorPicker.open({
					callBack : function(result) {
						$('a.option-remove.remove-background-effect').removeClass('hidden');
						var styleImg = hoverOption.target.find('.layer-background').css("background-image");
						$('#qb-dlg-hover-option .option-background').css('background-color', result.data);
						if (result.type === 'color') {
							// colorShow.css('background', result.data);
							hoverOption.callBack({
								type : 'hover',
								data : {
									// 'this.style.backgroundColor' :
									// result.data,
									'this.style.backgroundImage' : 'linear-gradient(' + result.data + ',' + result.data + '),' + styleImg,
								},
								data2 : {
									// 'background-color' : result.data,
									'background-image' : 'linear-gradient(' + result.data + ',' + result.data + '),' + styleImg,
								},
								attr_label : 'ho-background',
								attr_val : result.data,
							});
						} else if (result.type === 'gradient-x') {
							colorShow.css({
								'background-image' : 'linear-gradient(to right, ' + result.data + '),',
								'background-color' : 'initial',
							});
							hoverOption.callBack({
								type : 'hover',
								data : {
									'this.style.backgroundImage' : [ '-webkit-linear-gradient(left,' + result.data + ')', '-moz-linear-gradient(right, ' + result.data + ')', '-o-linear-gradient(right, ' + result.data + ')', 'linear-gradient(to right, ' + result.data + ')', ],
									'this.style.backgroundColor' : 'initial',
								},
								data2 : {
									'background-image' : 'linear-gradient(to right, ' + result.data + ')',
									'background-color' : 'initial',
								},
								attr_label : 'ho-background',
								attr_val : result.data,
							});
						} else if (result.type === 'gradient-y') {
							colorShow.css({
								'background' : 'linear-gradient(' + result.data + '),' + styleImg,
								'background-color' : 'initial'
							});
							hoverOption.callBack({
								type : 'hover',
								data : {
									'this.style.background' : [ '-webkit-linear-gradient(' + result.data + ')', '-moz-linear-gradient(' + result.data + ')', '-o-linear-gradient(' + result.data + ')', 'linear-gradient(' + result.data + ')', ],
									'this.style.backgroundColor' : 'initial',
								},
								data2 : {
									'background' : 'linear-gradient(' + result.data + '),' + styleImg,
									'background-color' : 'initial'
								},
								attr_label : 'ho-background',
								attr_val : result.data,
							});
						}
					},
					value : hoverOption.hoBackground,
					targetBox : colorShow,
					gradient : false,
				});
			});
			$(document).on('click', '#qb-dlg-hover-option .option-title', function() {
				var colorShow = $(this);
				colorPicker.open({
					callBack : function(result) {
						colorShow.css('background-color', result.data);
						hoverOption.callBack({
							type : 'hover',
							data : {
								'this.style.color' : result.data,
							},
							attr_label : 'ho-title',
							attr_val : result.data,
						});
					},
					value : hoverOption.hoBackground,
				});
			});
			$(document).on('click', '#qb-dlg-hover-option a.demo-effect', function(e) {
				var effect = $(this).attr('class').split(' ')[0];
				if (hoverOption.selectedEffect != undefined) {
					$('a.' + hoverOption.selectedEffect + '').removeClass('selected-effect');
				}
				// var tempSelected = hoverOption.selectedEffect;
				hoverOption.selectedEffect = effect;
				$(this).addClass('selected-effect');
				hoverOption.callBack({
					type : 'hover',
					data : {},
					attr_label : 'ho-effect',
					attr_val : effect,
				});
				// $('.hover-component
				// .slected-hover-btn').removeClass(tempSelected).addClass(hoverOption.selectedEffect);
				$('a.option-remove.remove-hover-effect').removeClass('hidden');
				/*
				 * ThoaiVT covert animation hover when choice effect
				 */
				var transform = $(this).css("transform");
				var transition_duration = $(this).css("transition-duration");
				var animation_duration = $(this).css("animation-duration");
				var animation_name = $(this).css("animation-name");
				var animation_timing_function = $(this).css("animation-timing-function");
				var animation_direction = $(this).css("animation-direction");
				var animation_iteration_count = $(this).css("animation-iteration-count");
				var box_shadow = $(this).css("box-shadow");
				var transition_property = $(this).css("transition-property");
				console.log("transform data : " + transform);
				$('.hover-component .slected-hover-btn').css({
					"transform" : transform,
					"transition-duration" : transition_duration,
					"animation-duration" : animation_duration,
					"animation-name" : animation_name,
					"animation-timing-function" : animation_timing_function,
					"animation-iteration-count" : animation_iteration_count,
					"animation-direction" : animation_direction,
					"box-shadow" : box_shadow,
					"transition-property" : transition_property
				});

			});
			$(document).on('click', 'a.option-remove.remove-background-effect', function() {
				$('#qb-dlg-hover-option .option-background').css('background-color', 'white');
				$('#qb-dlg-hover-option .option-duration.background-duration').val(0);
				hoverOption.callBack({
					type : 'remove-hover',
				});
				$('a.option-remove.remove-background-effect').addClass('hidden')
			});
			$(document).on('click', 'a.option-remove.remove-title-effect', function() {
				$('#qb-dlg-hover-option .option-title').css('background-color', 'white');
				$('#qb-dlg-hover-option .option-duration.title-duration').val(0);
				hoverOption.callBack({
					type : 'hover',
					data : {
						'this.style.transitionDuration' : '0s',
					},
					attr_label : 'ho-title-transition',
					attr_val : '0',
				});
				hoverOption.callBack({
					type : 'hover',
					data : {
						'this.style.color' : hoverOption.targetTitle.css('color'),
					},
					attr_label : 'ho-title',
					attr_val : hoverOption.targetTitle.css('color'),
				});
			});
			$(document).on('click', 'a.option-remove.remove-hover-effect', function() {
				$('a.demo-effect').removeClass('selected-effect');
				hoverOption.callBack({
					type : 'hover',
					data : {},
					attr_label : 'ho-effect',
					attr_val : 'hvr-no-effect',
				});
				$('a.option-remove.remove-hover-effect').addClass('hidden');
			});
		},
		backgroundDurationProcess : function(e) {
			var val = $('#qb-dlg-hover-option .option-duration.background-duration').val();
			console.log(val);
			$('a.option-remove.remove-background-effect').removeClass('hidden');
			hoverOption.callBack({
				type : 'hover',
				data : {
					'this.style.transitionDuration' : val + 's',
				},
				data2 : {
					'transition-duration' : val + 's'
				},
				attr_label : 'ho-transition',
				attr_val : val,
			});
		},
		titleDurationProcess : function(e) {
			var val = $('#qb-dlg-hover-option .option-duration.title-duration').val();
			console.log(val);
			hoverOption.callBack({
				type : 'hover',
				data : {
					'this.style.transitionDuration' : val + 's',
				},
				data2 : {
					'transition-duration' : val + 's'
				},
				attr_label : 'ho-title-transition',
				attr_val : val,
			});
		},
		goToEditMode : function() {
			$('[ho-effect]').each(function() {
				$(this).removeClass($(this).attr('ho-effect'));
			});
		},
		goToPreviewMode : function() {
			$('[ho-effect]').each(function() {
				if ($(this).attr('ho-effect') != 'hvr-no-effect')
					$(this).addClass($(this).attr('ho-effect'));
			});
		},
	};
}());