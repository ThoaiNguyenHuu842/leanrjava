/**
 * @author ThoaiNH
 * create Nov 18, 2015
 * contact form component event and contact form editor tools event 
 */
(function() {
	contactForm = {
		COMP_MIN_H: 8,
		COMP_MIN_W: 12,
		idFormEditting: 0,
		init : function() {
			contactForm.eventListener();
		},
		/*
		 * event listener for contact form edit tool
		 */
		eventListener: function(){
			$(document).on('click','.content-box .grid-stack-item-content .btn-edit-contact-form',function(){
				var grid = $(this).parents('.grid-stack').data('gridstack');
				var gridWidget = $(this).parents(".grid-stack-item-content")
				var formWrapper = $(this).parents(".grid-stack-item-content").find(".qb-component");
				var formHtml = $(this).parents(".grid-stack-item-content").find(".qb-form-send-mail").get(0).outerHTML;
				var compId =  $(this).parents(".grid-stack-item-content").attr('qb-component-id');
				contactForm.idFormEditting = compId;
				contactForm.open({
					callBack: function(result){
						formWrapper.find('.qb-form-send-mail').remove();
						formWrapper.append(result.data);
						var newH = Math.floor(formWrapper.find('.qb-form-send-mail').height()/gridConfig.cell_height);
						//update widget height wrap form content
						if(newH > gridWidget.attr('data-gs-height'))
							 grid.update(gridWidget, gridWidget.attr('data-gs-x'), gridWidget.attr('data-gs-y'), gridWidget.attr('data-gs-width'), newH + 1);
						//update model
						var data = {
							formHtml: result.data	
						};
						componentModelManager.updateItemField(compId,WEB_PRO_DATA,data);
					},
					formHtml: formHtml,
				});
			});
		},
		/*
		 *  event listener for contact form component
		 */
		toolEventListener: function(){
			/**
			 * menu tools events
			 */
			//save form
			$(document).on('click','#qb-dlg-contact-form .item-save',function(){
				//clear editor DOM
				$(this).parents('#qb-dlg-contact-form').find('.form-body-tool').find('.function-panel, .function-form-field, .function-button').remove();
				$(this).parents('#qb-dlg-contact-form').find('[contenteditable]').attr('contenteditable','false');
				//update sumbit message and email
				$('#qb-dlg-contact-form .form-body-tool .qb-form-submit-message').val($('#qb-dlg-contact-form .edit-subtmit-mess-tool .input-submit-mess').val());
				$('#qb-dlg-contact-form .form-body-tool .qb-form-email-recive').val($('#qb-dlg-contact-form .edit-email-revice-tool .input-email-recive').val());
				var formHtml = $(this).parents('#qb-dlg-contact-form').find('.form-body-tool').html();
				contactForm.callBack({
					data: formHtml
				});
				$('#qb-dlg-contact-form').dialog("close");
			});
			//open set email receive
			$(document).on('click','#qb-dlg-contact-form .item-edit-mail-revice',function(){
				contactForm.showBodyTools(".edit-email-revice-tool", $(this));
				$("#qb-dlg-contact-form .sub-items-design").hide();
				$("#qb-dlg-contact-form .item-edit-mail-revice").removeClass('item-border-top');
			});
			//open set email content editor
			$(document).on('click','#qb-dlg-contact-form .item-edit-mail-content',function(){
				contactForm.showBodyTools(".edit-email-content-tool", $(this));
				$("#qb-dlg-contact-form .sub-items-design").hide();
				$("#qb-dlg-contact-form .item-edit-mail-revice").removeClass('item-border-top');
			});
			//open set form message
			$(document).on('click','#qb-dlg-contact-form .item-edit-submit-mess',function(){
				contactForm.showBodyTools(".edit-subtmit-mess-tool", $(this));
				$("#qb-dlg-contact-form .sub-items-design").hide();
				$("#qb-dlg-contact-form .item-edit-mail-revice").removeClass('item-border-top');
			});
			//open design panel
			$(document).on('click','#qb-dlg-contact-form .item-design',function(){
				$("#qb-dlg-contact-form .sub-items-design").slideToggle();
				$("#qb-dlg-contact-form .item-edit-mail-revice").toggleClass('item-border-top');
				contactForm.showBodyTools(".form-body-tool", $(this));
			});
			//open design input panel
			$(document).on('click','#qb-dlg-contact-form .item-input-style',function(){
				$("#qb-dlg-contact-form .sub-items-right-input-design").slideToggle();
			});
			//set background input style
			$(document).on('click','#qb-dlg-contact-form .form-menu-tool .sub-items-right-input-design .btn-set-input-style-bg',function(){
				var targetBox = $('#qb-dlg-contact-form .form-body-tool .qb-form-send-mail .qb-form-send-mail-wrapper-panel .qb-input-panel .form-group-ex input,#qb-dlg-contact-form .form-body-tool .qb-form-send-mail .qb-form-send-mail-wrapper-panel .qb-input-panel .form-group-ex textarea');
				backgroundSrc.setBackground(targetBox, 'F');
			});
			//input style effect
			$(document).on('click','#qb-dlg-contact-form .form-menu-tool .sub-items-right-input-design .btn-set-input-style-effect',function(){
				var ob = $('#qb-dlg-contact-form .form-body-tool .qb-form-send-mail .qb-form-send-mail-wrapper-panel .qb-input-panel .form-group-ex input,#qb-dlg-contact-form .form-body-tool .qb-form-send-mail .qb-form-send-mail-wrapper-panel .qb-input-panel .form-group-ex textarea');
				cp_Option.open({
					callBack : function(result) {
						ob.css(result.style, result.value);
					},
					self : ob,
					del : ["#cp-ot-border-inner","#cp-ot-opacity","#cp-ot-blur","#cp-ot-width","#cp-ot-height","#cp-ot-rotate"]
				});
			});
			//input color
			$(document).on('click','#qb-dlg-contact-form .form-menu-tool .sub-items-right-input-design .btn-set-input-style-color',function(){
				var ob = $('#qb-dlg-contact-form .form-body-tool .qb-form-send-mail .qb-form-send-mail-wrapper-panel .qb-input-panel .form-group-ex input,#qb-dlg-contact-form .form-body-tool .qb-form-send-mail .qb-form-send-mail-wrapper-panel .qb-input-panel .form-group-ex textarea');
				colorPicker.open({
     				callBack: function(result){
     					ob.css('color',result.data);
     				}
     			});
			});
			//drag form field
			$("#qb-dlg-contact-form .item-field").draggable({ 
				//revert: true,
				appendTo: "body",
				zIndex: 999999999,
				cursor: "move",
				opacity: 0.35,
				helper: function(){
					return $(this).html();
				}
			});	
			/**
			 * panel events
			 */
			//show/hide edit panel tool
			$(document).on('mouseover', '#qb-dlg-contact-form .form-body-tool .qb-input-panel', function() {
				$(this).find('.function-panel').css({"opacity":"0.7"});
			});
			$(document).on('mouseleave', '#qb-dlg-contact-form .form-body-tool .qb-input-panel', function() {
				$(this).find('.function-panel').css({"opacity":"0.0"});
			});
			//add new panel
			$(document).on('click','#qb-dlg-contact-form .item-add-panel',function(){
				$('#qb-dlg-contact-form .qb-form-send-mail-wrapper-panel').append(contactForm.createPanelTemplate);
				contactForm.setUpPanelEdior($('#qb-dlg-contact-form .qb-form-send-mail-wrapper-panel .qb-input-panel:last-child'));
				contactForm.setUpPanelSortable();
				$('#qb-dlg-contact-form .form-body-tool').animate({ scrollTop: $('#qb-dlg-contact-form .form-body-tool').prop("scrollHeight")}, 1000);
				var panelStyle = $('#qb-dlg-contact-form  .panel-title').attr('style');
				$('#qb-dlg-contact-form  .panel-title').attr('style',panelStyle);
			});
			//remove panel
			$(document).on('click','#qb-dlg-contact-form .form-body-tool .qb-form-send-mail .qb-input-panel .function-panel .btn-del',function(){
				var self = $(this);
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					self.parents('.qb-input-panel').remove();
				});
			});
			//panel effect
			$(document).on('click','#qb-dlg-contact-form .function-panel .btn-option',function(){
				var ob = $(this).closest('.qb-input-panel ');
				cp_Option.open({
					callBack : function(result) {
						ob.css(result.style, result.value);
					},
					self : ob,
					del : [ "#cp-ot-border-inner","#cp-ot-opacity","#cp-ot-blur","#cp-ot-width","#cp-ot-height","#cp-ot-rotate"]
				});
			});
			//panel background
			$(document).on('click', '#qb-dlg-contact-form .function-panel .btn-set-background', function(event) {
				var targetBox = $(this).parents('.qb-input-panel');
				backgroundSrc.setBackground(targetBox, 'F');
			});
			/**
			 * form field events
			 */
			//show/hide edit form field tool
			$(document).on('mouseover', '#qb-dlg-contact-form .form-body-tool .qb-input-panel .form-group-ex', function() {
				$(this).find('.function-form-field').css({"opacity":"0.7"});
			});
			$(document).on('mouseleave', '#qb-dlg-contact-form .form-body-tool .qb-input-panel .form-group-ex', function() {
				$(this).find('.function-form-field').css({"opacity":"0.0"});
			});
			//remove form field
			$(document).on('click','#qb-dlg-contact-form .form-body-tool .qb-input-panel .form-group-ex .btn-del',function(){
				var self = $(this);
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					self.parents('.form-group-ex').remove();
				});
			});
			//edit radio field
			$(document).on('click','#qb-dlg-contact-form .form-body-tool .qb-input-panel .form-group-ex[qb-input-type="radio"] .btn-form-field-edit',function(){
				var data = "";
				$(this).parents(".form-group-ex").find(".field-content label").each(function(){
					data += $(this).html() + "\n";
				});
				var self = $(this).parents(".form-group-ex").find(".field-content");
				var name = $(this).parents(".form-group-ex").attr('name');
				contactFormEditField.open({
					callBack: function(result){
						var ss = result.split('\n');
						console.log(ss);
						var htmlString = '';
						if(ss && ss.length > 0){
							for(i=0; i<ss.length; i++){
								if(ss[i] && ss[i].trim().length > 0){
									htmlString += '<input type="radio" name="'+name+'">';
									htmlString += '<label contenteditable="true">'+ss[i]+'</label>';
								}
							}
						}
						self.html(htmlString);
					},
					data: data
				})
			});
			//edit checkbox field
			$(document).on('click','#qb-dlg-contact-form .form-body-tool .qb-input-panel .form-group-ex[qb-input-type="checkbox"] .btn-form-field-edit',function(){
				var data = "";
				$(this).parents(".form-group-ex").find(".field-content label").each(function(){
					data += $(this).html() + "\n";
				});
				var self = $(this).parents(".form-group-ex").find(".field-content");
				var name = $(this).parents(".form-group-ex").attr('name');
				contactFormEditField.open({
					callBack: function(result){
						var ss = result.split('\n');
						console.log(ss);
						var htmlString = '';
						if(ss && ss.length > 0){
							for(i=0; i<ss.length; i++){
								if(ss[i] && ss[i].trim().length > 0){
									htmlString += '<input type="checkbox" name="'+name+'">';
									htmlString += '<label contenteditable="true">'+ss[i]+'</label>';
								}
							}
						}
						self.html(htmlString);
					},
					data: data
				})
			});
			//edit combo box field
			$(document).on('click','#qb-dlg-contact-form .form-body-tool .qb-input-panel .form-group-ex[qb-input-type="select"] .btn-form-field-edit',function(){
				var data = "";
				$(this).parents(".form-group-ex").find(".field-content option").each(function(){
					data += $(this).html() + "\n";
				});
				var self = $(this).parents(".form-group-ex").find(".field-content");
				var name = $(this).parents(".form-group-ex").attr('name');
				contactFormEditField.open({
					callBack: function(result){
						var ss = result.split('\n');
						console.log(ss);
						var htmlString = '<select>';
						if(ss && ss.length > 0){
							for(i=0; i<ss.length; i++){
								if(ss[i] && ss[i].trim().length > 0){
									htmlString += 	'<option>'+ss[i]+'</option>';
								}
							}
						}
						htmlString += '</select>';
						self.html(htmlString);
					},
					data: data
				})
			});
			/**
			 * submit button events
			 */
			$(document).on('mouseover', '#qb-dlg-contact-form .form-body-tool .qb-contact-form-submit-content', function() {
				$(this).find('.function-button ').css({"opacity":"0.7"});
			});
			$(document).on('mouseleave', '#qb-dlg-contact-form .form-body-tool .qb-contact-form-submit-content', function() {
				$(this).find('.function-button ').css({"opacity":"0.0"});
			});
			//button effect
			$(document).on('click','#qb-dlg-contact-form .form-body-tool .qb-contact-form-submit-content .function-button .btn-option',function(){
				var ob = $(this).closest('.qb-contact-form-submit-content').find('.qb-btn-submit-email');
				cp_Option.open({
					callBack : function(result) {
						ob.css(result.style, result.value);
					},
					self : ob,
					del : ["#cp-ot-border-inner","#cp-ot-opacity","#cp-ot-blur","#cp-ot-width","#cp-ot-height","#cp-ot-rotate","#cp-ot-margin"]
				});
			});
			//button background
			$(document).on('click', '#qb-dlg-contact-form .form-body-tool .qb-contact-form-submit-content .function-button .btn-set-background', function(event) {
				var targetBox = $(this).closest('.qb-contact-form-submit-content').find('.qb-btn-submit-email');
				backgroundSrc.setBackground(targetBox, 'F');
			});
			//button align
			$(document).on('click', '#qb-dlg-contact-form .form-body-tool .qb-contact-form-submit-content .function-button .btn-set-align', function(event) {
				if($(this).find('.panel-align-adjust').css('display') == 'none')
					$(this).find('.panel-align-adjust').show();
				else
					$(this).find('.panel-align-adjust').hide();
			});
			$(document).on('click', '#qb-dlg-contact-form .form-body-tool .qb-contact-form-submit-content .function-button .btn-set-align .panel-align-adjust .fa', function(event) {
				if($(this).hasClass('fa-align-center'))
					$(this).parents('.qb-contact-form-submit-content').find('.qb-btn-submit-email').css({"float":"none"});
				else if($(this).hasClass('fa-align-left'))
					$(this).parents('.qb-contact-form-submit-content').find('.qb-btn-submit-email').css({"float":"left"});
				else if($(this).hasClass('fa-align-right'))
					$(this).parents('.qb-contact-form-submit-content').find('.qb-btn-submit-email').css({"float":"right"});
			});
			//button font
			$(document).on('click', '#qb-dlg-contact-form .form-body-tool .qb-contact-form-submit-content .function-button .btn-set-submit-font', function(event) {
				editText.open({
					callBack: function(result){
						$('#qb-dlg-contact-form .form-body-tool .qb-btn-submit-email span').css(result.key, result.value);
					}
				});
			});
			//on click set title font
			$(document).on('click', '#qb-dlg-contact-form .item-fontstyle-field-title', function(event) {
				editText.open({
					callBack: function(result){
						$('#qb-dlg-contact-form .form-body-tool .form-group-ex .field-title span, .qb-form-send-mail .qb-form-send-mail-wrapper-panel .qb-input-panel .form-group-ex .field-content label').css(result.key, result.value);
					}
				});
			});
			//on click set panel title font
			$(document).on('click', '#qb-dlg-contact-form .item-fontstyle-panel-title', function(event) {
				editText.open({
					callBack: function(result){
						$('#qb-dlg-contact-form .form-body-tool .qb-input-panel .panel-title').css(result.key, result.value);
					}
				});
			});
			/*
			 * on/off required field
			 */
			$(document).on('click', '#qb-dlg-contact-form .btn-set-require', function(event) {
				if($(this).parents('.form-group-ex').attr('required-field') == 'true')
				{
					$(this).parents('.form-group-ex').attr('required-field','false');
					$(this).css('background','#1caa00');
				}
				else {
					$(this).parents('.form-group-ex').attr('required-field','true');
					$(this).css('background','#0088CC');
				}
			});
		},
		/*
		 * add contact form
		 */
		add : function(grid, x, y, $self) { 
			var node = {
				x : x,
 				y : y,
				width : 16,
				height : 20
			};
			//setup default data
			var template_contact_form = 	'<div class="qb-form-send-mail">';
			template_contact_form += 	'	<input type="hidden" class="qb-form-radio-id" value="1"/>';
			template_contact_form += 	'	<input type="hidden" class="qb-form-checkbox-id" value="1"/>';
			template_contact_form += 	'	<input type="hidden" class="qb-form-submit-message" value="Thanks for your contact!"/>';
			template_contact_form += 	'	<input type="hidden" class="qb-form-email-recive" value=""/>';
			template_contact_form += 	'	<ul class="qb-form-send-mail-wrapper-panel">';
			template_contact_form += 	'			<li class="qb-input-panel">';
			template_contact_form += 	'				<div class="panel-title field-title"><span>Contact form</span></div>';
			template_contact_form += 	'				<div class="form-group-ex" qb-input-type="text">';
			template_contact_form += 	'					<div class="field-title "><span>Name</span></div> <input type="text">';
			template_contact_form += 	'				</div>';
			template_contact_form += 	'				<div class="form-group-ex" qb-input-type="text">';
			template_contact_form += 	'					<div class="field-title "><span>Phone</span></div> <input type="text">';
			template_contact_form += 	'				</div>';
			template_contact_form += 	'				<div class="form-group-ex" qb-input-type="textarea">';
			template_contact_form += 	'					<div class="field-title "><span>Content</span></div> <textarea rows="3" cols="6"></textarea>';
			template_contact_form += 	'				</div>';
			template_contact_form += 	'			</li>';
			template_contact_form += 	'	</ul>';
			template_contact_form += 	'	<div class="qb-contact-form-submit-content">';
			template_contact_form += 	'		<div class="qb-btn-submit-email"><div class="field-title "><span>Send</span></div></div>';
			template_contact_form += 	'	</div>';
			template_contact_form += 	'</div>';
			var data = {
				formHtml: template_contact_form	
			}
			var widget = grid.add_widget($(buildTemplateContactForm(data.formHtml)), node.x, node.y, node.width, node.height, true);
			widget[WEB_PRO_DATA] = data;
			return widget;
		},
		/*
		 * load contact form from data
		 */
		load: function(grid, node, data){
			return grid.add_widget($(buildTemplateContactForm(data.formHtml)), node.x, node.y, node.width, node.height);
		},
		/*
		 * open edit contact forms
		 */
		open: function(option){
			contactForm.callBack = option.callBack;
			if(!contactForm.inited)
			{
				$('#qb-dlg-contact-form').webToolDialog(650);
				$('#qb-dlg-contact-form .form-menu-tool').niceScroll();
				$('#qb-dlg-contact-form .form-body-tool').niceScroll();
				contactForm.toolEventListener();
				contactForm.inited = true;
			}
			$('#qb-dlg-contact-form .form-body-tool').html(option.formHtml);
			contactForm.setUpPanelEdior($('#qb-dlg-contact-form .form-body-tool .qb-input-panel'));
			contactForm.setUpPanelSortable();
			//turn on button submit editor
			$('#qb-dlg-contact-form .form-body-tool .qb-btn-submit-email .field-title').attr('contenteditable','true');
			//apend edit form field tool
			var function_panel = '';
			function_panel +=       '<div class="function-button">';
			function_panel +=			'<div class="item btn-option">';
			function_panel += 				'<i class="fa fa-gear"></i>';
			function_panel +=			'</div>';
			function_panel +=			'<div class="item btn-set-background">';
			function_panel +=           	'<i class="fa fa-picture-o"></i>';
			function_panel +=			'</div>';
			function_panel +=			'<div class="item btn-set-submit-font">';
			function_panel +=           	'<i class="fa fa-font"></i>';
			function_panel +=			'</div>';
			function_panel +=			'<div class="item btn-set-align">';
			function_panel +=           	'<i class="fa fa-align-justify"></i>';
			function_panel +=           	'<div class="panel-align-adjust">';
			function_panel +=           	'	<i class="fa fa-align-left"><label>Left</label></i>';
			function_panel +=           	'	<i class="fa fa-align-center"><label>Center</label></i>';
			function_panel +=           	'	<i class="fa fa-align-right"><label>Right</label></i>';
			function_panel +=           	'</div>';
			function_panel +=			'</div>';
			function_panel +=		'</div>';
			$('#qb-dlg-contact-form .form-body-tool .qb-contact-form-submit-content').prepend(function_panel);
			$('#qb-dlg-contact-form').dialog("open");
			//default tools panel
			if($("#qb-dlg-contact-form .sub-items-design").css('display') == 'none')
				$('#qb-dlg-contact-form .item-design').trigger('click');
			$('#qb-dlg-contact-form .edit-subtmit-mess-tool .input-submit-mess').val($('#qb-dlg-contact-form .form-body-tool .qb-form-submit-message').val());
			$('#qb-dlg-contact-form .edit-email-revice-tool .input-email-recive').val($('#qb-dlg-contact-form .form-body-tool .qb-form-email-recive').val());
		},
		/*
		 * setup droppable for form panel
		 */
		setUpPanelEdior: function(panel){
			//append edit panel tool
			var function_panel = '';
			function_panel +=       '<div class="function-panel">';
			function_panel +=			'<div class="item btn-option">';
			function_panel += 				'<i class="fa fa-gear"></i>';
			function_panel +=			'</div>';
			function_panel +=			'<div class="item btn-set-background">';
			function_panel +=           	'<i class="fa fa-picture-o"></i>';
			function_panel +=			'</div>';
			function_panel +=			'<div class="item btn-draggable">';
			function_panel += 				'<i class="fa fa-arrows"></i>';
			function_panel +=			'</div>';
			function_panel += 			'<div class="item btn-del">';
			function_panel +=           	'<i class="fa fa-times-circle"></i>';
			function_panel +=			'</div>';
			function_panel +=		'</div>';
			panel.prepend(function_panel);
			//apend edit form field tool
			var function_form_field = '';
			function_form_field +=       '<div class="function-form-field">';
			function_form_field +=			'<div class="item btn-form-field-edit">';
			function_form_field += 				'<i class="fa fa-pencil"></i>';
			function_form_field +=			'</div>';
			function_form_field +=			'<div class="item btn-draggable">';
			function_form_field += 				'<i class="fa fa-arrows"></i>';
			function_form_field +=			'</div>';
			function_form_field += 			'<div class="item btn-del">';
			function_form_field +=           	'<i class="fa fa-times-circle"></i>';
			function_form_field +=			'</div>';
			function_form_field +=		'</div>';
			//apend edit form field tool for text and textarea
			var function_form_field_input = '';
			function_form_field_input +=       '<div class="function-form-field">';
			function_form_field_input +=			'<div class="item btn-form-field-edit">';
			function_form_field_input += 				'<i class="fa fa-pencil"></i>';
			function_form_field_input +=			'</div>';
			function_form_field_input +=			'<div class="item btn-draggable">';
			function_form_field_input += 				'<i class="fa fa-arrows"></i>';
			function_form_field_input +=			'</div>';
			function_form_field_input +=			'<div title="Required field: On/Off" class="item btn-set-require">';
			function_form_field_input += 				'<i class="fa fa-asterisk"></i>';
			function_form_field_input +=			'</div>';
			function_form_field_input += 			'<div class="item btn-del">';
			function_form_field_input +=           	'<i class="fa fa-times-circle"></i>';
			function_form_field_input +=			'</div>';
			function_form_field_input +=		'</div>';
			panel.find('.form-group-ex').each(function(){
				if($(this).attr('qb-input-type') == 'text' || $(this).attr('qb-input-type') == 'textarea')
				{
					$(this).prepend(function_form_field_input);
					if($(this).attr('required-field') == 'true')
						$(this).find('.btn-set-require').css('background','#0088CC');
				}
				else
					$(this).prepend(function_form_field);
			});
			//open editable for label
			panel.find('.field-title').attr('contenteditable','true');
			//set up droppable
			panel.droppable({
				accept : "#qb-dlg-contact-form .item-field",
				activeClass : "ui-state-hover",
				hoverClass : "ui-state-active",
				drop : function(e, ui) { 
					if(ui.draggable.hasClass('item-field-textbox')){
						$(this).append(contactForm.createTextBoxTemplate());
					} else if(ui.draggable.hasClass('item-field-textarea')){
						$(this).append(contactForm.createTextAreaTemplate());
					} else if(ui.draggable.hasClass('item-field-checkbox')){
						$(this).append(contactForm.createCheckBoxTemplate());
					} else if(ui.draggable.hasClass('item-field-radio')){
						$(this).append(contactForm.createRadioButtonTemplate());
					} else if(ui.draggable.hasClass('item-field-combobox')){
						$(this).append(contactForm.createComboBoxTemplate());
					} else if(ui.draggable.hasClass('item-field-date-picker')){
						$(this).append(contactForm.createComboBoxTemplate());
					}
					var titleStyle = $('#qb-dlg-contact-form  .qb-form-send-mail-wrapper-panel .form-group-ex .field-title span').attr('style');
					if(panel.find('.form-group-ex:last-child').attr('qb-input-type') == 'text' || panel.find('.form-group-ex:last-child').attr('qb-input-type') == 'textarea')
						panel.find('.form-group-ex:last-child').prepend(function_form_field_input);
					else
						panel.find('.form-group-ex:last-child').prepend(function_form_field);
					panel.find('.form-group-ex:last .field-title span').attr('style',titleStyle);
				}
			});
			//setup sortable for form field
			panel.sortable({
				handle : '.btn-draggable',
				items : ".form-group-ex"
			});
		},
		showBodyTools: function(toolSelector, self){
			$('#qb-dlg-contact-form .form-menu-tool .item').removeClass('active');
			self.addClass('active');
			$('#qb-dlg-contact-form .body-tools').hide();
			$('#qb-dlg-contact-form '+toolSelector).show();
		},
		/*
		 * create form panel template
		 */
		createPanelTemplate: function(){
			return '<li class="qb-input-panel"><div contenteditable="true" class="panel-title field-title "><span>Panel title</span></div></li>';
		},
		createDatePickerTemplate: function(){
			
		},
		/*
		 * create text box template
		 */
		createTextBoxTemplate: function(){
			var template = 	'<div class="form-group-ex" qb-input-type="text">';
			template +=		'	<div class="function-form-field">';
			template +=		'	</div>';
			template += 	'	<div contenteditable="true" class="field-title "><span>Title</span></div> <input type="text">';
			template += 	'</div>';
			return template;
		},
		/*
		 * create text area template
		 */
		createTextAreaTemplate: function(){
			var template = 	'<div class="form-group-ex" qb-input-type="textarea">';
			template += 	'	<div contenteditable="true" class="field-title "><span>Title</span></div> <textarea rows="3" cols="6"></textarea>';
			template += 	'</div>';
			return template;
		},
		/*
		 * create radio button template
		 */
		createRadioButtonTemplate: function(){
			var name = "qbradio"+webCreater.data[WEB_PRO_ELEME_CR_ID][WEB_PRO_R_FORMID];
			webCreater.data[WEB_PRO_ELEME_CR_ID][WEB_PRO_R_FORMID]++;
			var template = 	'<div name="'+name+'" class="form-group-ex" qb-input-type="radio">';
			template += 	'	<div contenteditable="true" class="field-title "><span>Title</span></div>';
			template += 	'	<div class="field-content">';
			template += 	'		<input type="radio" name="'+name+'">';
			template += 	'		<label contenteditable="true">Value1</label>';
			template += 	'		<input type="radio" name="'+name+'">';
			template += 	'		<label contenteditable="true">Value2</label>';
			template += 	'	</div>';
			template += 	'</div>';
			return template;
		},
		/*
		 * create combo box template
		 */
		createComboBoxTemplate: function(){
			var template = 	'<div class="form-group-ex" qb-input-type="select">';
			template += 	'	<div contenteditable="true" class="field-title "><span>Title</span></div>';
			template += 	'	<div class="field-content">';
			template += 	'		<select>';
			template += 	'			<option><label contenteditable="true">Value1</label></option>';
			template += 	'			<option><label contenteditable="true">Value2</label></option>';
			template += 	'		</select>';
			template += 	'	</div>';
			template += 	'</div>';
			return template;
		},
		/*
		 * create check box template
		 */
		createCheckBoxTemplate: function(){
			var name = "qbcheckbox"+webCreater.data[WEB_PRO_ELEME_CR_ID][WEB_PRO_C_FORMID];
			webCreater.data[WEB_PRO_ELEME_CR_ID][WEB_PRO_C_FORMID]++;
			var template = 	'<div name="'+name+'" class="form-group-ex" qb-input-type="checkbox">';
			template += 	'	<div contenteditable="true" class="field-title "><span>Title</span></div>';
			template += 	'	<div class="field-content">';
			template += 	'		<input type="checkbox" name="'+name+'"><label contenteditable="true">Value1</label>';
			template += 	'		<input type="checkbox" name="'+name+'"><label contenteditable="true">Value2</label>';
			template += 	'	</div>';
			template += 	'</div>';
			return template;
		},
		/*
		 * setup sortable for form panels
		 */
		setUpPanelSortable: function(self){
			$("#qb-dlg-contact-form .form-body-tool .qb-form-send-mail-wrapper-panel").sortable({
				handle : '.btn-draggable',
				items : "li"
			});
		}
	}
}());