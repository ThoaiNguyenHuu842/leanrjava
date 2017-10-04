/**
 * @author ThoaiNH
 * create Nov 25, 2015
 */
(function() {
	contactFormFrontEnd = {
		init : function() {
			contactFormFrontEnd.eventListener();
		},
		eventListener : function() {
			$(document).on('click','.grid-stack-item-content .qb-form-send-mail .qb-btn-submit-email', function(){
				contactFormFrontEnd.sendEmailContactTemplate.apply(this);
			});
		},
		/*
		 * contact email form
		 */
		sendEmailContactTemplate : function(){
			var validatedAllField = true;
			var email_receive = $(this).parents('.qb-form-send-mail').find('.qb-form-email-recive').val();
			var fo100 = $('#qb-input-fo100').val();
			console.log(fo100);
			var content_send = "<div style='background: #fff;'>";
			$(this).parents('.qb-form-send-mail').find('.qb-input-panel').each(function(){
				//panel title
				var panel_title = $(this).find(".panel-title").html();
				if(panel_title && panel_title.length > 0)
				{
					//set title field bold
					content_send += "<div class='not-remove' style='background: #b8d4ed none repeat scroll 0 0;float: left;font-size: 16px;font-weight: bold;padding: 10px;text-transform: uppercase;width: 100%;margin-bottom: 2px;'>";
					content_send += panel_title;
					content_send += "</div>";
					content_send += "<br class='not-remove'>";
				}
				//field content
				var i = 0;
				$(this).find('.form-group-ex').each(function(){
					i ++;
					var type = $(this).attr("qb-input-type");
					var fieldVal = "";
					if(i%2 == 1)
						fieldVal += "<div class='not-remove' style='background: #e6f0f9 none repeat scroll 0 0;float: left;width: 100%;margin-bottom: 2px;'>";
					else
						fieldVal += "<div class='not-remove' style='background: #ffffff none repeat scroll 0 0;float: left;width: 100%;margin-bottom: 2px;'>";
					fieldVal += "<div style='float: left; width: 150px; margin-right:2px;border-right: 2px solid #fff;padding:10px;font-size: 13px;font-weight: bold;text-transform: none;'>";
					var fieldTitle = $(this).find('.field-title').html();
					fieldVal += fieldTitle;
					fieldVal += "</div>";
					fieldVal += "<div style='float: left;padding:10px;font-size: 13px;font-weight: normal;'>";
					switch (type) {
					case "text":
						if($(this).attr('required-field') == 'true' && $(this).find('input').val().trim().length == 0)
						{
							validatedAllField = false;
							util.messageDialog(getLocalize('jscff_title2')+fieldTitle);
						}
						fieldVal += $(this).find('input').val();
						break;
					case "select":
						console.log($(this).find('.field-content select').val().trim());
						if($(this).attr('required-field') == 'true' && $(this).find('.field-content select').val().trim().length == 0)
						{
							validatedAllField = false;
							util.messageDialog(getLocalize('jscff_title2')+fieldTitle);
						}
						fieldVal += $(this).find("select").find(":selected").text();
						break;
					case "radio":
						var name = $(this).find("input").attr("name");
						var inputSelector = $(this).find("input[name='"+name+"']:checked").attr("qb-radio-value");
						var inputChecker = $(this).find("input[name='"+name+"']:checked").val();
						if($(this).attr('required-field') == 'true' && inputChecker == undefined)
						{
							validatedAllField = false;
							util.messageDialog(getLocalize('jscff_title2')+fieldTitle);
						}
						else {
							if(inputSelector && inputSelector.length > 0)
							{
								fieldVal += $(this).find("#"+inputSelector).val();
							}
							else
							{
								fieldVal += $(this).find("input[name='"+name+"']:checked").next().html();
							}
						}
						break;
					case "textarea":
						if($(this).attr('required-field') == 'true' && $(this).find('textarea').val().trim().length == 0)
						{
							validatedAllField = false;
							util.messageDialog(getLocalize('jscff_title2')+fieldTitle);
						}
						fieldVal += $(this).find('textarea').val();
						break;
					case "checkbox":
						var name = $(this).find("input").attr("name");
						var checkVal = $(this).find("input[name='"+name+"']").is(":checked");
						if($(this).attr('required-field') == 'true' && checkVal===false){
							validatedAllField = false;
							util.messageDialog(getLocalize('jscff_title2')+fieldTitle);
						}
						$(this).find("input:checked").each(function(){
							fieldVal += ".";
							fieldVal += $(this).next().html();
						});
						break;
					default:
						break;
					}
					fieldVal += "</div>";
					fieldVal += "</div>";
					content_send += fieldVal;
					content_send += "<br class='not-remove'>";
					content_send += "<br class='not-remove'>";
				});
			});
			content_send += "</div>";
			if(validatedAllField){
//				if(email_receive && email_receive.length > 0){
					//send mail
//					$.ajaxSetup({
//						beforeSend : function()  {
//							$('.qb-ohhay-ajax-content').css('display','block');
//						},
//						complete : function() {
//							$('.qb-ohhay-ajax-content').css('display','none');
//							setDefaultAjaxStatus();
//						}
//					});
					var message = $(this).parents('.qb-form-send-mail').find('.qb-form-submit-message').val();
					$.ajax({
		                type: "POST",
		                url: encodeUrl("webSmtpBean.sendContact"),
		                data: {
		                	fo100: fo100,
		                	email: email_receive,
		                	content: content_send
		                },
		                success: function (response) {
							util.messageDialog(message);
						},
		                error: function (e) {
		                    showGrowlMessageAjaxError();
		                }
		            });
//				}
//				else					
//					util.messageDialog(getLocalize("jscff_title1"));
			}
		}
	}
}());