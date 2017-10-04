/**
 * @author HQThongQB
 * 19/10/2015 
 */
(function(){
	 cp_Option ={
		/*object to style*/
		ob_self : null,
		/*mode of image*/
		mode:'',
		/*layout of image*/
		layout:'',
		/*open ui style*/
		open : function(option){
			//set object to style
			this.ob_self = option.self;
			
			//get value form component when open
			try{
				//set padding
				$(".option-padding[data-type='top']").val(option.self.css('padding-top') ? option.self.css('padding-top').replace('px','') : 0);
				$(".option-padding[data-type='right']").val(option.self.css('padding-right') ? option.self.css('padding-right').replace('px','') : 0);
				$(".option-padding[data-type='bottom']").val(option.self.css('padding-bottom') ? option.self.css('padding-bottom').replace('px','') : 0);
				$(".option-padding[data-type='left']").val(option.self.css('padding-left') ? option.self.css('padding-left').replace('px','') : 0);
				
				//set margin
				$(".option-margin[data-type='top']").val(option.self.css("margin-top") ? option.self.css("margin-top").replace('px','') : 0);
				$(".option-margin[data-type='right']").val(option.self.css("margin-right") ? option.self.css("margin-right").replace('px','') : 0);
				$(".option-margin[data-type='bottom']").val(option.self.css("margin-bottom") ? option.self.css("margin-bottom").replace('px','') : 0);
				$(".option-margin[data-type='left']").val(option.self.css("margin-left") ? option.self.css("margin-left").replace('px','') : 0);
				
				//set border
				$(".option-border[data-type='size']").val(option.self.css('borderLeftWidth') ? option.self.css('borderLeftWidth').replace('px','') : 0);
				if(option.self.css('borderLeftStyle')!="none"){
					$(".dropdown.option-border .dd-title").attr("data-type",option.self.css('borderLeftStyle'));
					$(".dropdown.option-border .dd-title").html("");
				}else{
					$(".dropdown.option-border .dd-title").attr("data-type","none");
					$(".dropdown.option-border .dd-title").html("none");
					//option.self.css('borderStyle','solid');
				}
				if(option.self.css('borderLeftColor')!="none"){
					$(".option-border[data-type='color']").attr("data-val",option.self.css('borderLeftColor'));
					$(".option-border[data-type='color']").css("background-color",option.self.css('borderLeftColor'));
				}else{
					$(".option-border[data-type='color']").attr("data-val","rgba(0,0,0)");
					$(".option-border[data-type='color']").css("background-color","rgba(0,0,0)");
				}
				
				//ANPH: set inner border
				if(option.self.closest('.grid-stack-item').hasClass('comp-image')){
					cp_Option.mode = option.self.closest('.grid-stack-item').attr('data-mode');
					cp_Option.layout = option.self.closest('.grid-stack-item').attr('data-layout');
					cp_Option.ob_self.find('.box_thumb_img').css({
						'border-width':'0px',
						'border-style':'solid',
						'border-color':'#000'
					});
					switch(cp_Option.mode) {
					    case 'TWO':
					    	console.log('two');
					        break;
					    case 'THREE':
					    	console.log('three');
					        break;
					    case 'FOUR':
					    	console.log('four');
					    	break;
					}
				}
				//ANPH: set inner border
				
				
				//set border-radius
				$(".option-border-radius[data-type='top']").val(option.self.css('borderTopLeftRadius') ? option.self.css('borderTopLeftRadius').replace('px','') : 0);
				$(".option-border-radius[data-type='right']").val(option.self.css('borderTopRightRadius') ? option.self.css('borderTopRightRadius').replace('px','') : 0);
				$(".option-border-radius[data-type='bottom']").val(option.self.css('borderBottomRightRadius') ? option.self.css('borderBottomRightRadius').replace('px','') : 0);
				$(".option-border-radius[data-type='left']").val(option.self.css('borderBottomLeftRadius') ? option.self.css('borderBottomLeftRadius').replace('px','') : 0);
				$("#cp-ot-radius li[data-type='top']").attr("data-flag","true");
				//kien tra gia tri theo top 
				if(option.self.css('borderTopLeftRadius').replace('px','') == option.self.css('borderTopRightRadius').replace('px','')){
					$("#cp-ot-radius li[data-type='right']").attr("data-flag","true");
				}else{
					$("#cp-ot-radius li[data-type='right']").attr("data-flag","false");
				}
				
				if(option.self.css('borderTopLeftRadius').replace('px','') == option.self.css('borderBottomRightRadius').replace('px','')){
					$("#cp-ot-radius li[data-type='bottom']").attr("data-flag","true");
				}else{
					$("#cp-ot-radius li[data-type='bottom']").attr("data-flag","false");
				}
				
				if(option.self.css('borderTopLeftRadius').replace('px','') == option.self.css('borderBottomLeftRadius').replace('px','')){
					$("#cp-ot-radius li[data-type='left']").attr("data-flag","true");
				}else{
					$("#cp-ot-radius li[data-type='left']").attr("data-flag","false");
				}
				
				//set box-shadow
				var shadow = option.self.css('boxShadow');
				
				if(!shadow || shadow == "none"){
					$('#cp-ot-shadow input[data-type="width"]').val(0);
					$('#cp-ot-shadow input[data-type="height"]').val(0);
					$('#cp-ot-shadow input[data-type="spread"]').val(0);
					$('#cp-ot-shadow input[data-type="blur"]').val(0);
					$('#cp-ot-shadow .option-shadow[data-type="color"]').attr("data-val","#000000");
					$('#cp-ot-shadow .color-show').css("background-color","#000000");
					$('#cp-ot-shadow .option-shadow[data-type="type"]').val("");
				}else{
					if(shadow.indexOf('inset') > -1)
						$('#cp-ot-shadow .option-shadow[data-type="type"]').val('inset');
					else 
						$('#cp-ot-shadow .option-shadow[data-type="type"]').val("");
					
					var ret = shadow.replace('inset','').split(")");
					$('#cp-ot-shadow .option-shadow[data-type="color"]').attr("data-val",ret[0]+")");
					$('#cp-ot-shadow .color-show').css("background-color",ret[0]+")");
					var retVal = ret[1].split('px');
					$('#cp-ot-shadow input[data-type="width"]').val(retVal[0]);
					$('#cp-ot-shadow input[data-type="height"]').val(retVal[1]);
					$('#cp-ot-shadow input[data-type="spread"]').val(retVal[2]);
					$('#cp-ot-shadow input[data-type="blur"]').val(retVal[3]);
				}
				
				//set opacity
				var opacity = option.self.attr("cp-opacity") ? option.self.attr("cp-opacity") : 1;
				$('.component-option .option-opacity').slider({
					value: opacity*100
				});
				$('#cp-ot-opacity label span').html(opacity*100);
	
				//set width
				var width = option.self.css('width');
				if(!width){
					$('#cp-ot-width .option-width').val(null);
				}else{
					$('#cp-ot-width .option-width').val(width.replace("%","").replace("px",""));
					if(width.indexOf("px")>=0){
						$('input#optionWidth1').prop('checked', true);
					}else{
						$('input#optionWidth2').prop('checked', true);
					}
				}
				
				//set height
				var height = option.self.css("height");
				if(!height){
					$('#cp-ot-height .option-height').val(null);
				}else{
					$('#cp-ot-height .option-height').val(height.replace("%","").replace("px",""));
					if(height.indexOf("px")>=0){
						$('input#optionHeight1').attr("checked", "checked");
					}else{
						$('input#optionHeight2').attr("checked", "checked");
					}
				}
				
				//set rotate
				var rotate = option.self.attr("cp-rotate");
				if(!rotate){
					$('#cp-ot-rotate .option-rotate').val(0);
					$('input#optionRotate').prop("checked",false);
				}else{
					var ret = rotate.split(';');
					$('#cp-ot-rotate .option-rotate').val(ret[0]);
					$('input#optionRotate').prop("checked",ret[1] === 'true');
				}
				
			}catch(Exception){
				console.log(Exception)
			}
			//hide option properties
			$('.component-option .form-group').show();
			if(option.del){
				$.each(option.del, function(k,v){
					$(v).hide();
				});
			}
			/*if(option.self.closest('.grid-stack-item').hasClass('comp-image') && (option.self.closest('.grid-stack-item').attr('data-mode')!='ONE')){
				$('#cp-ot-border-inner').show();
			}*/
			cp_Option.callBack = option.callBack;
			$('#qb-component-option').dialog("close");
			$('#qb-component-option').dialog("open");
		},
	 	init : function() {
	 		$('#qb-component-option').webToolDialog(295);
	 		
	 		//init padding spinner
	 		$('.component-option input.option-padding').spinner({
				spin: function (event, ui) {
					var ob = $(event.currentTarget).siblings('input');
					var ts = ob.attr('data-type');
					var val = ob.val();
					cp_Option.paddingProcess(ts, val);
			    },
		 		stop: function (event, ui) {
		 			var ob = $(event.currentTarget).siblings('input');
					var ts = ob.attr('data-type');
					var val = ob.val();
					cp_Option.paddingProcess(ts, val);
		 		}
			});
	 		
	 		//init margin spinner 
			$('.component-option input.option-margin').spinner({
				spin: function (event, ui) {
					var ob = $(event.currentTarget).siblings('input');
					var ts = ob.attr('data-type');
					var val = ob.val();
					cp_Option.marginProcess(ts,val);
			    },
			    stop: function (event, ui) {
			    	var ob = $(event.currentTarget).siblings('input');
					var ts = ob.attr('data-type');
					var val = ob.val();
			    	cp_Option.marginProcess(ts,val);
		 		}
			});
			
			//init border spinner
			$('.component-option input.border-size').spinner({
				spin: function (event, ui) {
					cp_Option.borderSpinnerProcess(event);
			    },
			    stop: function (event, ui) {
			    	cp_Option.borderSpinnerProcess(event);
		 		}
			});
			
			//init border spinner
			$('.component-option input.inner-size').spinner({
				spin: function (event, ui) {
					cp_Option.innerBorderSpinnerProcess(event);
				},
				stop: function (event, ui) {
					cp_Option.innerBorderSpinnerProcess(event);
				}
			});
			
			//init radius spinner
			$('.component-option .option-border-radius').spinner({
				spin: function (event, ui) {
					cp_Option.radiusProcess(event);
			    },
			    stop: function (event, ui) {
			    	cp_Option.radiusProcess(event);
		 		}
			});
			
			//init shadow spinner
			$('.component-option input.option-shadow').spinner({
				spin: function (event, ui) {
					cp_Option.shadowProcess(event);
			    },
			    stop: function (event, ui) {
			    	cp_Option.shadowProcess(event);
		 		}
			});
			
			//init width
			$('.component-option .option-width').spinner({
				spin: function (event, ui) {
					cp_Option.widthProcess(event);
			    },
			    stop: function (event, ui) {
			    	cp_Option.widthProcess(event);
		 		}
			});
			/*init height*/
			$('.component-option .option-height').spinner({
				spin: function (event, ui) {
					cp_Option.heightProcess(event);
				},
				stop: function (event, ui) {
					cp_Option.heightProcess(event);
				}
			});
			/*init rotate*/
			$('#cp-ot-rotate input.option-rotate').spinner({
				spin: function (event, ui) {
					cp_Option.rotateProcess(event);
			    },
			    stop: function (event, ui) {
			    	cp_Option.rotateProcess(event);
		 		}
			});
			
			
	 		this.eventListener();
		},
		eventListener: function(){
			//change padding by keyup event
			$(document).on('keyup','.option-padding', function(){
				var ts = $(this).attr('data-type');
				var val = $(this).val();
				cp_Option.paddingProcess(ts, val);
			});

			//change margin by keyup event
			$(document).on('keyup','.option-margin', function(){
				var ts = $(this).attr('data-type');
				var val = $(this).val();
				cp_Option.marginProcess(ts,val);
			});
			
			//change border by keyup event
			$(document).on('keyup','.option-border',this.borderProcess);
			
			//change border by click event
			$(document).on('click','.option-border ul li',this.borderProcess);

			//change inner border by click event
			$(document).on('click','.option-inner-border ul li',this.innerBorderProcess);
			
			//change radius 
			$(document).on('keyup change','.option-border-radius',function(event){
				cp_Option.radiusProcess(event);
			});
			
			//init colorpicker for border
			$(document).on('click','.component-option .option-border.color-show',function(){
				var self = $(this);
				colorPicker.open({
					callBack : function(result){
						cp_Option.callBack({
							style : "border-color",
							value : result.data,
							attr_label : "cp-border-color",
							attr_val : result.data
						});
	                	self.attr("data-val", result.data);
	                	self.css("background-color", result.data);
					},
					targetBox : self,
     				gradient : false
					//value : self.attr("data-val")
				});
			});
			
			//init colorpicker for shadow
			$(document).on('click','.component-option .option-shadow.color-show',function(){
				var self = $(this);
				colorPicker.open({
					callBack: function(result){
						self.attr("data-val", result.data);
	                	self.css("background-color", result.data);
						cp_Option.shadowProcess();
					},
					targetBox : self,
     				gradient : false
					//value : self.attr("data-val")
				});
			});
			
			//init colorpicker for border
			$(document).on('click','.component-option .option-inner-border.color-show',function(){
				var self = $(this);
				colorPicker.open({
					callBack : function(result){
//						cp_Option.callBack({
//							style : "border-color",
//							value : result.data,
//						});
						cp_Option.ob_self.find('.box_thumb_img').css('border-color',result.data);
	                	self.attr("data-val", result.data);
	                	self.css("background-color", result.data);
					},
					targetBox : self,
     				gradient : false
				});
			});
			
			$(document).on('keyup','input.option-shadow', this.shadowProcess);
			
			$(document).on('change','.option-shadow', this.shadowProcess);
			
			// set width when change value
			$(document).on('keyup change','.option-width',function(event){
				cp_Option.widthProcess(event);
			});
			$(document).on('click','input[name="optionWidth"]',function(event){
				cp_Option.widthProcess(event);
			});
			$(document).on('keyup change','.option-height',function(event){
				cp_Option.heightProcess(event);
			});
			$(document).on('click','input[name="optionHeight"]',function(event){
				cp_Option.heightProcess(event);
			});
			$(document).on('keyup change','#cp-ot-rotate .option-rotate',function(event){
				cp_Option.rotateProcess(event);
			});
			$(document).on('click','#cp-ot-rotate input[type="checkbox"]',function(event){
				cp_Option.rotateProcess(event);
			});

			var dragging = false;
			var offset = {
					x : 0,
					y: 0
				}
		    var target = $('.rotate-angel');
		    
		    target.mousedown(function(e) {
		        dragging = true;
		        offset = {
	    			x: e.pageX,
	    			y: e.pageY
	    		};
		    });
		    $(document).mouseup(function() {
		        dragging = false;
		    });
		    $(document).mousemove(function(e) {
		        if (dragging) {
		            var mouse_x = e.pageX;
		            var mouse_y = e.pageY;
		            var radians = Math.atan2(mouse_x - offset.x, mouse_y - offset.y);
		            var degree = Math.round((radians * (180 / Math.PI) * -1) + 180);
		            target.css('-moz-transform', 'rotate(' + degree + 'deg)');
		            target.css('-webkit-transform', 'rotate(' + degree + 'deg)');
		            target.css('-o-transform', 'rotate(' + degree + 'deg)');
		            target.css('-ms-transform', 'rotate(' + degree + 'deg)');
		            $('input.option-rotate').val(degree);
		            cp_Option.rotateProcess(e);
		        }
		    });
		},
		paddingProcess:function(ts,val){
			if(ts == "top"){
				cp_Option.callBack({
					style : "padding-top",
					value : val+"px",
					attr_label: "cp-pd-top",
					attr_val: val
				});
			}
			else if(ts == "right"){
				cp_Option.callBack({
					style : "padding-right",
					value : val+"px",
					attr_label: "cp-pd-right",
					attr_val: val
				});
			}
			else if(ts == "bottom"){
				cp_Option.callBack({
					style : "padding-bottom",
					value : val+"px",
					attr_label: "cp-pd-bottom",
					attr_val: val
				});
			}
			else if(ts == "left"){
				cp_Option.callBack({
					style : "padding-left",
					value : val+"px",
					attr_label: "cp-pd-left",
					attr_val: val
				});
			}
		},
		marginProcess:function(ts, val){
			if(ts == "top"){
				var mg_h_t = parseInt($('#cp-ot-margin .option-margin[data-type="top"]').val()) ? parseInt($('#cp-ot-margin .option-margin[data-type="top"]').val()) : 0;
				var mg_h_b =parseInt($('#cp-ot-margin .option-margin[data-type="bottom"]').val()) ? parseInt($('#cp-ot-margin .option-margin[data-type="bottom"]').val()): 0;
				var mg_h = mg_h_t + mg_h_b;
				cp_Option.ob_self.css("height","calc(100% - "+mg_h+"px )");
				cp_Option.callBack({
					style : "margin-top",
					value : val+"px",
					attr_label: "cp-mg-top",
					attr_val: val
				});
			}
			else if(ts == "right"){
				var mg_h_t = parseInt($('#cp-ot-margin .option-margin[data-type="left"]').val()) ? parseInt($('#cp-ot-margin .option-margin[data-type="left"]').val()) : 0;
				var mg_h_b =parseInt($('#cp-ot-margin .option-margin[data-type="right"]').val()) ? parseInt($('#cp-ot-margin .option-margin[data-type="right"]').val()): 0;
				var mg_h = mg_h_t + mg_h_b;
				cp_Option.ob_self.css("width","calc(100% - "+mg_h+"px )");
				cp_Option.callBack({
					style : "margin-right",
					value : val+"px",
					attr_label: "cp-mg-right",
					attr_val: val
				});
			}
			else if(ts == "bottom"){
				var mg_h = parseInt($('#cp-ot-margin .option-margin[data-type="top"]').val()) + parseInt($('#cp-ot-margin .option-margin[data-type="bottom"]').val());
				cp_Option.ob_self.css("height","calc(100% - "+mg_h+"px )");
				cp_Option.callBack({
					style : "margin-bottom",
					value : val+"px",
					attr_label: "cp-mg-bottom",
					attr_val: val
				});
			}
			else if(ts == "left"){
				var mg_h_t = parseInt($('#cp-ot-margin .option-margin[data-type="left"]').val()) ? parseInt($('#cp-ot-margin .option-margin[data-type="left"]').val()) : 0;
				var mg_h_b =parseInt($('#cp-ot-margin .option-margin[data-type="right"]').val()) ? parseInt($('#cp-ot-margin .option-margin[data-type="right"]').val()): 0;
				var mg_h = mg_h_t + mg_h_b;
				cp_Option.ob_self.css("width","calc(100% - "+mg_h+"px )");
				cp_Option.callBack({
					style : "margin-left",
					value : val+"px",
					attr_label: "cp-mg-left",
					attr_val: val
				});
			}
		},
		borderProcess:function(e){
			var type = $(this).attr('data-type');
			if(type == "size"){
				var val = $(this).val();
				if(val !=0 && $(".dropdown.option-border .dd-title").attr("data-type") == 'none'){
					$('.option-border ul li[data-val="solid"]').trigger('click');
				}
				cp_Option.callBack({
					style : "border-width",
					value : val+"px",
					attr_label: "cp-border-width",
					attr_val: val
				});
			}else if(type == "type"){
				var val = $(this).attr("data-val");
				if(val!="none"){
					$(this).parent().siblings("button").children('.dd-title').text('').attr('data-type',val);
				}
				else{
					$(this).parent().siblings("button").children('.dd-title').text('none').attr('data-type',val);
					/* khi border-style:none thì set border-width:0 */
					$('.option-border[data-type="size"]').val(0)
					/*cp_Option.callBack({
						style : "border-width",
						value : "0px",
						attr_label: "cp-border-width",
						attr_val: 0
					});*/
				}
				cp_Option.callBack({
					style : "border-style",
					value : val,
					attr_label: "cp-border-style",
					attr_val: val
				});
			}
		},
		borderSpinnerProcess:function(e){
			var ob = $(e.currentTarget).siblings('.option-border');
			var val = ob.val();
			if(val !=0 && $(".dropdown.option-border .dd-title").attr("data-type") == 'none'){
				$('.option-border ul li[data-val="solid"]').trigger('click');
			}
			cp_Option.callBack({
				style : "border-width",
				value : val+"px",
				attr_label: "cp-border-width",
				attr_val: val
			});
		},
		radiusProcess:function(e){
			cp_Option.ob_self.css("overflow","hidden");
			var ra_ob = $(e.target).closest("li"); 
			var type = ra_ob.attr("data-type");
			var val1 = $('#cp-ot-radius .option-border-radius[data-type="top"]').val() ? $('#cp-ot-radius .option-border-radius[data-type="top"]').val() : 0;
			
			if(type=="top"){
				$('#cp-ot-radius li[data-flag="true"] .option-border-radius').val(val1);
			}else{
				ra_ob.attr("data-flag","false");
			}
			var val2 = $('#cp-ot-radius .option-border-radius[data-type="right"]').val() ? $('#cp-ot-radius .option-border-radius[data-type="right"]').val() : 0;
			var val3 = $('#cp-ot-radius .option-border-radius[data-type="bottom"]').val() ? $('#cp-ot-radius .option-border-radius[data-type="bottom"]').val() : 0;
			var val4 = $('#cp-ot-radius .option-border-radius[data-type="left"]').val() ? $('#cp-ot-radius .option-border-radius[data-type="left"]').val() : 0;
			var val = val1+"px "+val2+"px "+val3+"px "+val4+"px";
			var val_attr = val1+" "+val2+" "+val3+" "+val4;
			cp_Option.callBack({
				style : "border-radius",
				value : val,
				attr_label: "cp-radius",
				attr_val: val_attr
			});
		},
		shadowProcess : function(e){
			var val1 = $('.option-shadow[data-type="width"]').val() ? $('.option-shadow[data-type="width"]').val() : 0;
			var val2 = $('.option-shadow[data-type="height"]').val() ? $('.option-shadow[data-type="height"]').val() : 0;
			var val3 = $('.option-shadow[data-type="spread"]').val() ? $('.option-shadow[data-type="spread"]').val() : 0;
			var val4 = $('.option-shadow[data-type="blur"]').val() ? $('.option-shadow[data-type="blur"]').val() : 0;
			var val5 = $('.option-shadow[data-type="color"]').attr("data-val") ? $('.option-shadow[data-type="color"]').attr("data-val") : "#000000";
			var val6 = $('.option-shadow[data-type="type"]').val();
			var val = (val1 == 0 && val2 == 0 && val3 == 0 && val4 == 0) ? 'none' : val1+"px "+val2+"px "+val3+"px "+val4+"px "+val5+" "+val6;
			var val_attr = val1+" "+val2+" "+val3+" "+val4+" "+val5+" "+val6;
			cp_Option.callBack({
				style : "box-shadow",
				value : val,
				attr_label : "cp-shadow",
				attr_val : val_attr
			});
		},
		widthProcess : function(e){
			var val = $('#cp-ot-width input.option-width').val();
			var val_attr = val;
			if($('#optionWidth1').is(':checked')){
				val = val+'px';
				val_attr = val_attr+';px';
			}else{
				val = val+'%';
				val_attr = val_attr+';%';
			}
			cp_Option.callBack({
				style : "width",
				value : val,
				attr_label: "cp-width",
				attr_val: val_attr
			});
		},
		heightProcess : function(e){
			var val = $('#cp-ot-height input.option-height').val();
			var val_attr = val;
			if($('#optionHeight1').is(':checked')){
				val = val+'px';
				val_attr = val_attr+';px';
			}else{
				val = val+'%';
				val_attr = val_attr+';%';
			}
			cp_Option.callBack({
				style : "height",
				value : val,
				attr_label: "cp-height",
				attr_val: val_attr
			});
		},
		rotateProcess : function(e){
			var val = $('#cp-ot-rotate .option-rotate').val();
			var val_check = $('#cp-ot-rotate input[type="checkbox"]').prop("checked");
			var val_attr = val+";"+val_check;
			var target = $('.rotate-angel');
			target.css('-moz-transform', 'rotate(' + val + 'deg)');
            target.css('-webkit-transform', 'rotate(' + val + 'deg)');
            target.css('-o-transform', 'rotate(' + val + 'deg)');
            target.css('-ms-transform', 'rotate(' + val + 'deg)');
			cp_Option.callBack({
				style : "rotate",
				value : val,
				value1: val_check,
				attr_label: "cp-rotate",
				attr_val: val_attr
			});
		},
		innerBorderSpinnerProcess:function(e){
			var ob = $(e.currentTarget).siblings('.inner-size');
			var val = ob.val();
			switch(cp_Option.mode) {
			    case 'TWO':
			    	cp_Option.innerBorderTwo(val);
			        break;
			    case 'THREE':
			    	cp_Option.innerBorderThree(val);
			        break;
			    case 'FOUR':
			    	cp_Option.innerBorderFour(val);
			    	break;
			}
		},
		innerBorderProcess : function(){
			var obj_item = cp_Option.ob_self.find('.box_thumb_img');
			var val = $(this).attr("data-val");
			if(val!="none")
				$(this).parent().siblings("button").children('.dd-title').text('').attr('data-type',val);
			else
				$(this).parent().siblings("button").children('.dd-title').text('none').attr('data-type',val);
			cp_Option.ob_self.find('.box_thumb_img').css('border-style', val);
		},
		innerBorderTwo : function(val){
			if(cp_Option.layout=="landscape"){
				cp_Option.ob_self.find('.number_img_1').css('border-bottom-width', val/2+"px");
				cp_Option.ob_self.find('.number_img_2').css('border-top-width', val/2+"px");
			}else{
				cp_Option.ob_self.find('.number_img_1').css('border-right-width', val/2+"px");
				cp_Option.ob_self.find('.number_img_2').css('border-left-width', val/2+"px");
			}
		},
		innerBorderThree : function(val){
			
		},
		innerBorderFour : function(val){
			
		},
	 }
}());