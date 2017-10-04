/**
 * @author ThoaiVt date 15/12/2015
 * 
 */
(function() {
	animationOption = {
			effectAfter : null,
			//open dialog
			open : function(option) {
				if (!animationOption.inited){
					animationOption.init();
					animationOption.inited = true;
				}
				$('#qb-dlg-animation-onload-option').dialog('open');
				animationOption.callBack = option.callBack;
				animationOption.target = option.target;
				//active current effect
				animationOption.selectedEffect = option.target.attr('onload-effect');
				$('#qb-dlg-animation-onload-option .animation-effect').removeClass('selected-animation-effect');
				if( option.target.attr('onload-effect'))
					$('#qb-dlg-animation-onload-option .animation-effect[effect="'+animationOption.selectedEffect+'"]').addClass('selected-animation-effect');
				//set class animated for element
				animationOption.callBack({
					type : 'load',
					data : {},
					attr_label : 'class',
					attr_val : "animated"
				});
			},init : function() {
				animationOption.eventListener();
				//set width for dialog
				$('#qb-dlg-animation-onload-option').webToolDialog(700);
				animationOption.inited = true;
				//set value spinner
				$('#qb-dlg-animation-onload-option .option-duration').spinner({
					step:0.1,
					min:0,
					spin: function (event, ui) {
						animationOption.durationProcess(event);
				    },
				    stop: function (event, ui) {
				    	animationOption.durationProcess(event);
			 		}
				});
			},eventListener: function(){
//				//event chooise effect
//				$(document).on({
//					//mouse focus
//					mouseenter: function(){
//						var effectClass = $(this).attr("effect");
//						$(this).addClass(effectClass);
//					},
//					//mouse exit focus
//					mouseleave: function(){
//						var effectClass = $(this).attr("effect");
//						$(this).removeClass(effectClass);
//					}
//				},'.animation-option .animation-option-component .animation-component .animation-option-item .animation-effect');
				
				$(document).on('click','.animation-option .animation-option-component .animation-component .animation-option-item .animation-effect',function(){
					var effectClass = $(this).attr("effect");
					$('.animation-option .animation-option-component .animation-component .animation-option-item .animation-effect').each(function( index ) {
						$(this).removeClass(animationOption.effectAfter);
					});
					animationOption.effectAfter = effectClass;
					$(this).addClass(effectClass);
				});
				
				//remove effect
				$(document).on('click','#qb-dlg-animation-onload-option .animation-option .animation-option-component .hover-component .animation-option-header .remove-animation-background-effect',function(){
					//set spinner value default 0
					$('#qb-dlg-animation-onload-option .option-duration').val(0);
					$('#qb-dlg-animation-onload-option .animation-effect').removeClass('selected-animation-effect');
					animationOption.callBack({
						type:'removeEffect',
						data: {},
						attr_label : '',
						attr_val : '',
					});
				});
				
				//event select effect
				$(document).on('click','#qb-dlg-animation-onload-option a.animation-effect',function(){
					var effect = $(this).attr("effect");
					//check select effect 
					if (animationOption.selectedEffect != undefined){
						$('#qb-dlg-animation-onload-option a.animation-effect').removeClass('selected-animation-effect');
					}
					//set effect slected 
					animationOption.selectedEffect = effect;
					//add class 
					$(this).addClass('selected-animation-effect');
					$(this).addClass('animated');
					//update container box
					var animatedDura = $('#qb-dlg-animation-onload-option .option-duration').val();
					//set animated class
					animationOption.callBack({
						type:'load',
						data: {},
						attr_label : 'class',
						attr_val : effect,
					});
				
				});
			}
			,durationProcess : function(e){
				//get value spinner
				var val = $('#qb-dlg-animation-onload-option .option-duration').val();
				//call function set time animate for element
				animationOption.callBack({
					type:'duratime',
					data: {
						'time':val+'s',
					},
					attr_label:'data',
					attr_val:val,
				});
			}
	};
}());