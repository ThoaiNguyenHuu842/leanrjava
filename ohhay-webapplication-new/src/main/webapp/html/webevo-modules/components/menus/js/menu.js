/*
 * author: HQThongQB
 * date create: 11/11/2015
 */
(function() {
	omenu = {
		COMP_MIN_H: 4,
		COMP_MIN_W: 4,
		ob_self : null,
		self_item : null,
		val: null,
		init : function() {
			$('#qb-dlg-menu-option').webToolDialog(480);
			
			var fontstyles = ["Open Sans","Roboto","arial","sans-serif","HelveticaNeue","Avenir Next","Lato","Ubuntu","Akronim","Itim","Alegreya Sans","Kanit","Fira Sans","Crimson Text","Roboto Slab","Aachen","MyriadPro"];
			fontstyles.forEach(function(fontStyle){
				$('.menu-edit-font .dropdown-menu').append('<li><a style="font-family: '+fontStyle+'">'+fontStyle+'</a></li>');
			});
			var fontweights = [["Light","300"],["Normal","400"],["Medium","500"],["Bold","700"]];
			fontweights.forEach(function(fontWeight){
				$('.menu-edit-weight .dropdown-menu').append('<li><a data-val="'+fontWeight[1]+'" >'+fontWeight[0]+'</a></li>');
			});
			//$('#qb-menu-option').niceScroll();
			this.eventListener();
		},
		eventListener: function(){
			/*open tool*/
			$(document).on('click','.btn-menu-option',function(){
				var ob = $(this).closest('.grid-stack-item-content');
				omenu.open({
					self : ob
				});
			});
			
			/*add new item*/
			$(document).on('click','#qb-menu-option .menu-add-item',this.addItem);
			
			/*save update*/
			$(document).on('click','#qb-menu-option .menu-allow',this.apply);
			
			/*show color*/
			$(document).on('click','#qb-menu-option .menu-edit-color',function(){
				var self = $(this);
				colorPicker.open({
					callBack : function(result){
						$('#qb-menu-option .menu-option-content  ul li a.menu-item-link').css('color',result.data);
	                	self.css("background-color", result.data);
	                	$('#qb-menu-option .menu-allow').addClass('active');
					},
					targetBox : self,
					gradient : false
				});
			});
			
			/*change font-size*/
			$(document).on('keyup input','#qb-menu-option .menu-edit-fontsize', function(){
				var fsize = $(this).val();
				$('#qb-menu-option .menu-option-content  ul li a.menu-item-link').css("font-size",fsize+"px");
				$('#qb-menu-option .menu-allow').addClass('active');
			});
			
			/*change distance*/
			$(document).on('keyup input','#qb-menu-option .menu-edit-distance', function(){
				var padding = $(this).val();
				$('#qb-menu-option .menu-option-content  ul li a.menu-item-link').css({"padding-left":padding+"px","padding-right":padding+"px"});
				$('#qb-menu-option .menu-allow').addClass('active');
			});
			
			/*choice font-family*/
			$(document).on('click','#qb-menu-option .menu-edit-font ul li', this.choiceFontStyle);
			
			/*choose font-weight*/
			$(document).on('click','#qb-menu-option .menu-edit-weight ul li', this.choiceFontWeight);
			
			/*show edit link*/
			$(document).on('click','#qb-menu-option .menu-item-editlink',function(){
				actionOption.open({
					callBack: function(result){
						$('.menu-edit-link span').html(result.dataType);
						$('.menu-edit-link input').val(result.dataLink);
						$('.menu-item-editlink').attr('data-action-link', result.dataLink);
						$('.menu-item-editlink').attr('data-action-type', result.dataType);
						$('.menu-item-editlink').attr('data-action-link-target', result.dataLinkTarget);
						omenu.self_item.find('a.menu-item-link').attr('data-action-link', $('.menu-item-editlink').attr('data-action-link'));
						omenu.self_item.find('a.menu-item-link').attr('data-action-type', $('.menu-item-editlink').attr('data-action-type'));
						omenu.self_item.find('a.menu-item-link').attr('data-action-link-target', $('.menu-item-editlink').attr('data-action-link-target'));
						$('#qb-menu-option .menu-allow').addClass('active');
					},
					target: $('.menu-item-editlink'),
				});
			});
			
			/*choose fill*/
			$(document).on('click','.menu-edit-fill',function(){
				if($(this).is(':checked')){
					$('#qb-menu-option .menu-option-content ul').attr('menu-fill','true');
				}else{
					$('#qb-menu-option .menu-option-content ul').attr('menu-fill','false');
				}
				$('#qb-menu-option .menu-allow').addClass('active');
			});
			
			/*choose align*/
			$(document).on('click','.menu-edit-align',function(){
				var type = $(this).attr('data-type');
				$('.menu-edit-align').removeClass('flag');
				$(this).addClass('flag');
				$('#qb-menu-option .menu-option-content ul').attr('align',type);
				$('#qb-menu-option .menu-allow').addClass('active');
			});
			
			$(document).on('click','.menu-style-item',function(){
				$('.menu-style-item-check').html('');
				$(this).find('.menu-style-item-check').html('<span class="fa fa-check-circle"></span>');
				$('#qb-menu-option .menu-option-content ul').attr('class',$(this).find('ul').attr('class'));
				$('#qb-menu-option .menu-allow').addClass('active');
			});
			
			/*change name*/
			$(document).on('keyup input','.menu-edit-content',function(){
				omenu.self_item.find('a.menu-item-link').html($('.menu-edit-content').val());
				$('#qb-menu-option .menu-allow').addClass('active');
			});
			
			/*set active*/
			$(document).on('click','.menu-edit-active', this.setActive);
			
			/*load item*/
			$(document).on('click','#qb-menu-option .menu-option-content ul li', this.loadTabItem);
			
			/*khoi tao hover*/
			$(document).on('click','#qb-menu-option .menu-item-edithover',function(){
				var hoverObj = $('#qb-menu-option .menu-option-content .media-body li a');
				var background = $('#qb-menu-option .menu-option-content ul').css('background-color');
				
				var dataMenu = {
					animation : "",
					hover : {
						onmouseover : '',
						onmouseout : {'this.style.background':'transparent'}
					}
				};
				/*'this.style.color':hoverObj.css('color')*/
				hoverOption.open({
					target: hoverObj,
					callBack: function(result){
						console.log(result);
						if (result.type == 'hover') {
							if (result.attr_label != 'ho-effect'){
								hoverObj.attr('onmouseover',"this.style.background='"+result.attr_val+"'");
								hoverObj.attr('onmouseout',"this.style.background='transparent'");
//								
//								$.each(result.data,function(k,v){
//									dataMenu['hover']['onmouseover'][k] = v;
//								});
//								webUtils.addAttributeEffect(hoverObj, dataMenu.hover.onmouseover, 'onmouseover');
//								webUtils.addAttributeEffect(hoverObj, dataMenu.hover.onmouseout, 'onmouseout');
							}
							hoverObj.attr(result.attr_label,result.attr_val);
							
							$('#qb-menu-option .ohay-menu li.active a.menu-item-link').css('backgroundColor',result.attr_val).removeAttr('onmouseout');
						}
						$('#qb-menu-option .menu-allow').addClass('active');
					},
				});
			});
			
		},
		reEventListener: function(){
			$(document).on('click','.menu-tool-remove',function(){
				$(this).closest('.menu-item').remove();
			});
			$(document).on('click','.menu-option-header button',this.close);
			$(document).on('mouseenter','#qb-menu-option .menu-option-content ul li',function(){
				$(this).append('<button class="menu-tool-remove"><i class="fa fa-close"></i></button>');
			});
			$(document).on('mouseleave','#qb-menu-option .menu-option-content ul li',function(){
				$(this).find('.menu-tool-remove').remove();
			});
		},
		open : function(option){
			this.ob_self = option.self;
			var ob_component = option.self.find(".qb-component");
			var ob_layer = option.self.find(".layer-background");
			var ob_menu = option.self.find('.ohay-menu');
			
			omenu.callBack = option.callBack;
			if(!$('#qb-menu-option').hasClass('open')){
				$('#qb-menu-option').addClass('open');
				$('#qb-menu-option').show();
			}
			//get menu html
			var menuHTML = ob_menu[0].outerHTML;
			$('#qb-menu-option .menu-option-content .media-body').html(menuHTML);
			$('#qb-menu-option .menu-option-content ul').sortable();
			$('#qb-menu-option .menu-option-content ul').disableSelection();
			
			$('#qb-menu-option .menu-option-content .media-body').css({
				'background-color' : ob_layer.css('background-color'),
				'background-image' : ob_layer.css('background-image')
			});
			
			var color = ob_menu.find('li a').css('color');
			$('#qb-menu-option .menu-edit-color').data("val",color).css("background-color",color);
			
			var fontFamily = ob_menu.find('li a').css('font-family');
			$('#qb-menu-option .menu-edit-font .dd-tt').html(fontFamily).css('font-family',fontFamily);
			
			var fsize = ob_menu.find('li a').css('font-size').replace('px','');
			$('#qb-menu-option .menu-edit-fontsize').val(fsize);
			
			var padding = ob_menu.find('li a').css('padding-left').replace('px','');
			$('#qb-menu-option .menu-edit-distance').val(padding);
			
			var fontWeight = ob_menu.find('li a').css('font-weight');
			if(fontWeight ==='300')
				$('#qb-menu-option .menu-edit-weight .dd-tt').html('Light');
			else if(fontWeight ==='400')
				$('#qb-menu-option .menu-edit-weight .dd-tt').html('Normal');
			else if(fontWeight ==='500')
				$('#qb-menu-option .menu-edit-weight .dd-tt').html('Medium');
			else if(fontWeight ==='700')
				$('#qb-menu-option .menu-edit-weight .dd-tt').html('Bold');
			else
				$('#qb-menu-option .menu-edit-weight .dd-tt').html('Normal');
			
			//set properties fill
			if($('#qb-menu-option .menu-option-content ul').hasClass('menu-fill')){
				$('.menu-edit-fill').prop( "checked", true );
			}else{
				$('.menu-edit-fill').prop( "checked", false );
			}
			
			//set properties align
			$('.menu-edit-align').removeClass('flag');
			$('.menu-edit-align[align="'+$('#qb-menu-option .menu-option-content ul').attr('align')+'"]').addClass('active');
			
			$('.menu-option-style').addClass('fadeInRight').removeClass('fadeOutRight');
			$('.menu-option-edit').removeClass('fadeInRight fadeOutRight');
			
			$('.menu-edit-content').val('').attr('disabled','disabled');
			$('.menu-edit-active').prop('checked',false).attr('disabled','disabled');
			
			$('#qb-dlg-menu-option').dialog("open");
			omenu.reEventListener();
		},
		close : function(){
			if($('#qb-menu-option').hasClass('open')){
				$('#qb-menu-option').removeClass('open');
				$('#qb-menu-option').hide();
			}
		},
		/*
		 * add omenu
		 */
		add : function(grid,x,y, $self, id) {
			var node = {
				x : x,
				y : y,
				width : 32,
				height : 4
			};
			var menuTemp = '<li class="menu-item"><a class="menu-item-link" style="color: #000000;">Home</a></li><li class="menu-item"><a class="menu-item-link" style="color: #000000;">About</a></li><li class="menu-item"><a class="menu-item-link" style="color: #000000;">Tutorial</a></li>';
			var data = "";
		    data = { 
				text	: menuTemp,
				cls		: 'ohay-menu',
				align	: 'undefined',
				fill	: 'undefined',
				id		: id 
			}
			
			var widget = grid.add_widget($(buildTemplateOmenu(data)), node.x, node.y, node.width, node.height, true);
			//setup default data
			widget[WEB_PRO_DATA] = data;
			// khoi tao lai su kien
			omenu.reEventListener();
			//update 17/11/2016 - call to action
			//widget.find('.btn-menu-option').trigger('click');
			return widget;
		},
		/*
		 * get data from html
		 */
		getData: function(id){
			var menutext ='';
			$.each($(".grid-stack-item-content[qb-component-id='"+id+"'] .qb-component .omenu-wrapper-content ul.ohay-menu li.menu-item"), function( index, value ) {
				if(!value.hasAttribute('data-add-menu')){
					menutext += value.outerHTML;
				}
			});
			var menudata = {
				text	: menutext,
				cls 	: $(".grid-stack-item-content[qb-component-id='"+id+"'] .qb-component .omenu-wrapper-content ul.ohay-menu").attr("class"),
				align 	: $(".grid-stack-item-content[qb-component-id='"+id+"'] .qb-component .omenu-wrapper-content ul.ohay-menu").attr("align"),
				fill	: $(".grid-stack-item-content[qb-component-id='"+id+"'] .qb-component .omenu-wrapper-content ul.ohay-menu").attr("menu-fill"),
				color	: $(".grid-stack-item-content[qb-component-id='"+id+"'] .qb-component .omenu-wrapper-content ul.ohay-menu li a").css("color"),
				id		: id
			}
			return menudata;
		},
		/*
		 * set data to html
		 */
		load: function(grid, node, data){
			var widget =  grid.add_widget($(buildTemplateOmenu(data)), node.x, node.y, node.width, node.height);
			//khoi tao lai su kien
			omenu.reEventListener();
			return widget;
		},
		//add item for menu
		addItem:function(){
			var a_style = "";
			a_style += "color:"+$('#qb-menu-option .menu-edit-color').data("val")+";";
			a_style += "font-family:"+$('#qb-menu-option .menu-edit-font .dd-tt').html()+";";
			a_style += "font-size:"+$('#qb-menu-option .menu-edit-fontsize').val()+"px;";
			a_style += "padding: 0 "+$('#qb-menu-option .menu-edit-distance').val()+"px;";
			var fweight = $('#qb-menu-option .menu-edit-weight .dd-tt').html();
			if(fweight =='Light')
				a_style += "font-weight: 300;";
			else if(fweight =='Normal')
				a_style += "font-weight: 400;";
			else if(fweight =='Medium')
				a_style += "font-weight: 500;";
			else if(fweight =='Bold')
				a_style += "font-weight: 700;";
			else
				a_style += "font-weight: 500;";
			
			//var a_style = $('#qb-menu-option .menu-option-content  ul li a:first-child').attr('style');
			var li_style = $('#qb-menu-option .menu-option-content  ul li:first-child').attr('style');
			var li_over = $('#qb-menu-option .menu-option-content  ul li a:first-child').attr('onmouseover');
			var li_out = "this.style.background='transparent'";
			var ho_transition = $('#qb-menu-option .menu-option-content  ul li a:first-child').attr('ho-transition');
			var ho_background = $('#qb-menu-option .menu-option-content  ul li a:first-child').attr('ho-background')
			var ho_effect = $('#qb-menu-option .menu-option-content  ul li a:first-child').attr('ho-effect');
			var temp = '';
			temp += '<li class="menu-item" style="'+li_style+'" >';
			temp += '<a class="menu-item-link" data-action-link="" data-action-type="none" style="'+a_style+'" onmouseover="'+li_over+'" onmouseout="'+li_out+'" ho-transition="'+ho_transition+'" ho-background="'+ho_background+'" ho-effect="'+ho_effect+'">New item</a>';
			temp += '</li>';
			$('#qb-menu-option .menu-option-content ul').append(temp);
			omenu.reEventListener();
		},
		apply : function(){
			if(omenu.ob_self.find('.ohay-menu').find('.menu-item').length > 0){
				var tempMENU = $('#qb-menu-option .menu-option-content  ul');
				omenu.ob_self.find('.omenu-wrapper-content .ohay-menu').html(tempMENU.html()).attr('menu-fill',tempMENU.attr('menu-fill')).attr('align',tempMENU.attr('align'));
				color = tempMENU.find('li a').css('color');
				omenu.ob_self.find('.omenu-wrapper-content .navbar-collapse').css('border-color',color).siblings('.navbar-header').find('button').css('border-color',color).find('.icon-bar').css('background-color',color);
				$('#qb-menu-option .menu-allow').removeClass('active');
			}else{
				alert("Hãy thêm nội dung cho menu");
			}
		},
		loadTabItem : function(){
			omenu.self_item = $(this);
			var content = $(this).find('a.menu-item-link').html();
			var link = $(this).find('a.menu-item-link').attr('data-action-link') ? $(this).find('a.menu-item-link').attr('data-action-link') : '';
			var link_type = $(this).find('a.menu-item-link').attr('data-action-type') ? $(this).find('a.menu-item-link').attr('data-action-type') : 'none';
			var link_target = $(this).find('a.menu-item-link').attr('data-action-link-target');
			$('.menu-edit-content').val(content).removeAttr('disabled');
			$('.menu-edit-link input').val(link);
			$('.menu-edit-link span').html(link_type);
			$('.menu-item-editlink').attr('data-action-link',link);
			$('.menu-item-editlink').attr('data-action-type',link_type);
			$('.menu-item-editlink').attr('data-action-link-target',link_target);
			if(omenu.self_item.hasClass('active')){
				$('.menu-edit-active').prop('checked',true);
			}else{
				$('.menu-edit-active').prop('checked',false);
			}
			$('.menu-edit-active').removeAttr('disabled');
		},
		updateEditItem:function(){
			var type = $(this).attr('data-type');
			$('#qb-menu-option .menu-allow').addClass('active');
			if(type==="link"){
				omenu.self_item.find('a.menu-item-link').attr('data-action-link',$('.menu-item-editlink').attr('data-action-link'));
				omenu.self_item.find('a.menu-item-link').attr('data-action-type',$('.menu-item-editlink').attr('data-action-type'));
				omenu.self_item.find('a.menu-item-link').attr('data-action-link-target',$('.menu-item-editlink').attr('data-action-link-target'));
			}
			$(this).removeClass('active');
				
		},
		choiceFontStyle:function(){
			font = $(this).find('a').html().trim();
			$('#qb-menu-option .menu-option-content  ul li a.menu-item-link').css("font-family",font);
			$('#qb-menu-option .menu-edit-font button .dd-tt').html(font);
			$('#qb-menu-option .menu-edit-font button .dd-tt').css('font-family',font);
			$('#qb-menu-option .menu-allow').addClass('active');
		},
		choiceFontWeight:function(){
			weight = $(this).find('a').html().trim();
			$('#qb-menu-option .menu-option-content  ul li a.menu-item-link').css("font-weight",weight);
			$('#qb-menu-option .menu-edit-weight button .dd-tt').html(weight);
			$('#qb-menu-option .menu-allow').addClass('active');
		},
		setActive:function(){
			if($('.menu-edit-active').prop('checked') === true){
				$('#qb-menu-option .ohay-menu li').removeClass('active').find('a.menu-item-link').css('backgroundColor','transparent').attr('onmouseout','this.style.background=\'transparent\'');
				var bgr = $('#qb-menu-option .ohay-menu li a').attr('ho-background');
				omenu.self_item.addClass('active').find('a.menu-item-link').css('backgroundColor',bgr).removeAttr('onmouseout'); 
			}else{
				omenu.self_item.removeClass('active').find('a.menu-item-link').css('backgroundColor','transparent').attr('onmouseout','this.style.background=\'transparent\'');
			}
			$('#qb-menu-option .menu-allow').addClass('active');
		},
		goToEditMode : function() {
			var menus = $('#content-wrapper .omenu-wrapper-content .ohay-menu a.menu-item-link');
			var tempLength = menus.length;
			for (var i = 0; i < tempLength;i++) {
				var menuItem = menus.eq(i);
				menuItem.removeClass(menuItem.attr('ho-effect'));
			}
		},
		goToPreviewMode : function() {
			var menus = $('#content-wrapper .omenu-wrapper-content .ohay-menu a.menu-item-link');
			var tempLength = menus.length;
			for (var i = 0; i < tempLength;i++) {
				var menuItem = menus.eq(i);
				menuItem.removeClass('none');
				if (menuItem.attr('ho-effect') != 'hvr-no-effect'){
					menuItem.addClass(menuItem.attr('ho-effect'));
				}
			}
			$('.qb-component-menu').closest('.grid-stack').css('overflow', 'unset');
			$('.qb-component-menu').closest('.qp-box-component').css({'overflow': 'unset','z-index':'100'});
			$('.qb-component-menu').closest('.grid-stack').css('overflow', 'unset');
		},	
	}
}());