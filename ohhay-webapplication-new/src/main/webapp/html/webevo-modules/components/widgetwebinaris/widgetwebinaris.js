function loadWidgetWebinaris() {
	var processWebinaris = function(data, objsection, fb050) {
		
		var data_webinaris = data.showtime;
		var timezone = data.timezone;
		
		function getNameTimeZoneByArea(){
			var result = '';
			$.each(timezone, function(kzone, vzone){
				if(vzone.VAL == data.area){
					result = vzone.LABEL;
				}
			})
			return result;
		}
		
		function validateEmail(email) {
		    var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		    return re.test(email);
		}
		
		if(!objsection.find(".clzz_hidden").hasClass("clzz_hidden")){
			
			
			var input_hidden = $('<div/>',{
				"class":"clzz_hidden"
			});
			objsection.append(input_hidden);
			var getNameTimeZoneByArea = getNameTimeZoneByArea();
			var template = '';
			template += "<input type=\"hidden\" class=\"input_hidden_timezone\" value=\""+data.area+"\"/>";
			template += "<input type=\"hidden\" class=\"input_hidden_name_timezone\" value=\""+getNameTimeZoneByArea+"\"/>";
			//template += "<input type=\"hidden\" class=\"input_hidden_showtime\" value=\""+timezone[0].LABEL+"\"/>";
			template += "<input type=\"hidden\" class=\"input_hidden_showtime\" value=\"\"/>";
			
			var local_time_val = data_webinaris[0].local_time_val;
			
			var timeValue = data_webinaris[0].timeValue;
			
			var radioshowtime = timeValue + "_" + local_time_val;
			template += "<input type=\"hidden\" class=\"input_hidden_radioshowtime\" value=\"\"/>";
			input_hidden.html(template);
		}
		
		
		

		var KEYS_WEBINARIS = {
			'section' : "[widget-name=\"regis-webinaris\"]",
			"form_timezone_webinaris" : "[widgeteleid=\"comb-utc-times\"]",
			"cb_showtime" : ".input_checkbox_showtime_webinaris",
			"label_showtime":"[widgeteleid=\"label_showtime\"] .full-label",
			"input_day" : ".input_day_showtime_webinaris",
			"input_month" : ".input_month_showtime_webinaris",
			"input_year" : ".input_year_showtime_webinaris",
			"input_time" : ".input_time_showtime_webinaris",
			"input_seat" : ".input_seat_showtime_webinaris",
		}
		
		
		//submit form
		objsection.find("[widgeteleid=\"regis_panel\"] .qb-btn-submit-regis").off('click');
		objsection.find("[widgeteleid=\"regis_panel\"] .qb-btn-submit-regis").click(function(e){
			e.preventDefault();
			e.stopPropagation();
			var firstname = objsection.find("[widgeteleid=\"regis_panel\"] .form-group-ex input.fname");
			var lastname = objsection.find("[widgeteleid=\"regis_panel\"] .form-group-ex  input.lname");
			var email = objsection.find("[widgeteleid=\"regis_panel\"] .form-group-ex  input.email");
			var boxId = objsection.attr('qb-box-id');
			
			var fb050_new = bigBoxModelManager.listBox[boxId][WEB_PRO_DATA];
			
			var FB050 = fb050_new;
			var FK100 = 1;
			var senddata = 1;
			var area = objsection.find('.input_hidden_timezone').val();
			var timezone = objsection.find('.input_hidden_name_timezone').val();
			var radioshowtime = objsection.find('.input_hidden_radioshowtime').val();
			
			var sendform = {
					firstname:firstname.val(),
					lastname:lastname.val(),
					email:email.val(),
					FB050:FB050,
					FK100:FK100,
					senddata:senddata,
					area:area,
					timezone:timezone,
					radioshowtime:radioshowtime,
					EVO:"1"
				}
			
			if(radioshowtime != '' && area != '' && timezone != '' && firstname.val().trim() != '' && lastname.val().trim() != '' && email.val().trim() != '' && validateEmail(email.val().trim())){
				
				$.ajax({
					url : "https://next.webinaris.com/customer/registers.html",
					method:"POST",
					data:sendform,
					success : function(url) {
						setTimeout(function(){
							
							if(url.indexOf("Sie haben sich") != -1){
								util.messageDialog(url);
							}else{
								window.open(url,'_blank');
								
							}
							
							
						},300)
						
					},
					error : function() {
					}
				});
			}else{
				if(radioshowtime == ''){
					util.messageDialog(getLocalize("jswgb_title1"));
				}
				
				if(firstname.val().trim() == ''){
					firstname.css({
						border: "1px solid red"
					})
				}else{
					firstname.css({
						border: "none"
					})
				}
				
				if(lastname.val().trim() == ''){
					lastname.css({
						border: "1px solid red"
					})
				}else {
					lastname.css({
						border: "none"
					})
				}
				
				if(email.val().trim() == '' || !validateEmail(email.val().trim())){
					//util.messageDialog('the email is wrong');
					email.css({
						border: "1px solid red"
					})
				}else{
					email.css({
						border: "none"
					})
				}
			}
			
		})
		
		
			
			var form_showtime_webinaris = objsection.find(KEYS_WEBINARIS.label_showtime);
			
			console.log("objsection",objsection);
			var form_register_webinaris = objsection.find('.grid-stack');
			var form_timezone_webinaris = objsection.find('.grid-stack');

			//UTC
			setValueForTimezone(form_timezone_webinaris);
			
			//showtime			
			setValueForShowtime();
			
			
		
		function setValueForTimezone(form_timezone_webinaris) {
			var template = "";
			template += "<div class=\"btn-group webinaris-utc\" style=\"width: 100%;\">";
			
			template += "  <button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">";
			
			template += "    " + objsection.find('.input_hidden_name_timezone').val() + " <span class=\"caret\"><\/span>";
			template += "  <\/button>";
			template += "  <ul class=\"dropdown-menu\">";
			$.each(timezone, function(kTimezone, vTimezone) {
				var label = vTimezone.LABEL;
				template += "    <li data-id=\""+vTimezone.VAL+"\"><a>"+label+"<\/a><\/li>";
			})

			template += "  <\/ul>";
			template += "<\/div>";

			
			// truong hop cua chi hang
			var style_button = form_timezone_webinaris.find(KEYS_WEBINARIS.form_timezone_webinaris).find('.obutton-link').attr('style');
			form_timezone_webinaris
					.find(KEYS_WEBINARIS.form_timezone_webinaris)
					.html(template);
			
			
			form_timezone_webinaris
			.find(KEYS_WEBINARIS.form_timezone_webinaris).find('button').addClass('obutton-link').attr('style',style_button).css('color','white');
			
			form_timezone_webinaris
			.find(KEYS_WEBINARIS.form_timezone_webinaris).find('.dropdown-menu').attr('style',style_button);
		
			form_timezone_webinaris
			.find(KEYS_WEBINARIS.form_timezone_webinaris).find('.dropdown-menu').height('auto').css({
				'text-align':'left',
				'margin-top': "-12px"
			});
			
			form_timezone_webinaris
			.find(KEYS_WEBINARIS.form_timezone_webinaris).find('.dropdown-menu').width('auto');
			
			form_timezone_webinaris
			.find(KEYS_WEBINARIS.form_timezone_webinaris).find('.dropdown-menu li a').css('color','white');
			
			form_timezone_webinaris
			.find(KEYS_WEBINARIS.form_timezone_webinaris).find('.dropdown-menu li a:hover').css('background-color','transparent');
			
			
			// thay doi UTC
			form_timezone_webinaris.find(".webinaris-utc li").off('click');
			form_timezone_webinaris.find(".webinaris-utc li").on('click',function(e){
				var self = $(this);
				var id = self.data('id');
				var name_timezone = self.text();
				objsection.find('.input_hidden_timezone').val(id);
				objsection.find('.input_hidden_name_timezone').val(name_timezone);
				
				$.ajax({
					url : "https://next.webinaris.com/customer/restf/1."+fb050,
					data:{
						"AREA":id
					},
					type:"post",
					success : function(json) {
						try{
							if(json.status != 'error'){	
								processWebinaris(json, objsection, fb050);
							}
						}catch(e){
							
						}
						
						
					},
					error : function() {
					}
				});
			})
		}
		function resetSelectCheckbox(){
			for(var i = 1; i < 20; i++){
				objsection.find('[widgeteleid="checkbox_showtime_'+i+'"] .obutton-label').html('');
			}
		}	
		
		resetSelectCheckbox();
		
		function selectCheckbox(number){			
			//reset lai checkbox			
			resetSelectCheckbox();
			
			
			var chkboxShowTime = objsection.find('[widgeteleid="checkbox_showtime_'+number+'"]');
			var symbo_select = chkboxShowTime.find('.obutton-label');
			console.log(symbo_select);
			var template = '<i class="fa"><span style="color: rgb(50, 89, 0); font-size: 14px;"><span style="color: rgb(35, 140, 0); font-size: 14px;">&#xf00c;</span></span></i>';
			symbo_select.html(template);
		}
		
		function setValueForShowtime() {			
			var data_showtime = data.showtime;
			
			$.each(data_showtime, function(key, value) {
				
				var local_time_val = value.local_time_val;
				
				var timeValue = value.timeValue;
				var local_time = value.local_time;

				var times = local_time_val.split('_');
				var year = times[0];
				var month = times[1];
				var day = times[2];
				var hour = times[3];
				var minus = times[4];

				var getmon = value.getmon;
				var clock = value.clock;
				var VFREE = value.VFREE;
				var MAXVI = value.MAXVI;
				
				var input_label_showtime = objsection.find("[widgeteleid=\"label_showtime_"+(key+1)+"\"] .full-label");
				input_label_showtime.css({
					"margin-left":"-44px"
				})
				var input_checkbox_showtime = objsection.find("[widgeteleid=\"checkbox_showtime_"+(key+1)+"\"]");
				$(input_label_showtime).html(local_time);
				
				$(input_label_showtime).attr('radioshowtime',timeValue + "_" + local_time_val);
				$(input_checkbox_showtime).attr('radioshowtime',timeValue + "_" + local_time_val);
				
				objsection.find("[widgeteleid=\"label_showtime_"+(key+1)+"\"]").show();
				objsection.find("[widgeteleid=\"checkbox_showtime_"+(key+1)+"\"]").show();
				
				input_label_showtime.off('click');
				input_label_showtime.on('click').click(function(e){
					var self = $(e.currentTarget);
					var data_showtime = self.attr('radioshowtime');
					var name = self.parents('[widgeteleid]').attr("widgeteleid");
					var number_showtime = name.replace("label_showtime_","");
					objsection.find('.input_hidden_radioshowtime').val(data_showtime);
					selectCheckbox(number_showtime);
				})
				
				input_checkbox_showtime.off('click');
				input_checkbox_showtime.click(function(e){
					var self = $(e.currentTarget);
					var name = self.attr("widgeteleid");
					var data_showtime = self.attr('radioshowtime');
					objsection.find('.input_hidden_radioshowtime').val(data_showtime);
					var number_showtime = name.replace("checkbox_showtime_","");
					selectCheckbox(number_showtime);
				});
				
			})
		}
	}
	
	serviceWebinaris(function(data, objsection, fb050){
		processWebinaris(data, objsection, fb050);
	})
}

function serviceWebinaris(callback) {
	for(var i = 0 ; i < 20;i++){
		$('[widgeteleid="label_showtime_'+i+'"]').hide();
		$("[widgeteleid=\"checkbox_showtime_"+i+"\"]").hide();
	}
	
	$.each($('[widget-name="regis-webinaris"]'), function(key, item){
		var self = $(item);
		var boxId = self.attr('qb-box-id');
		
		var fb050 = bigBoxModelManager.listBox[boxId][WEB_PRO_DATA];
		
		
		$.ajax({
			url : "https://next.webinaris.com/customer/restf/1."+fb050+"",
			success : function(json) {
				try{
					if(json.status != 'error'){	
						
						
						callback(json,$(item),fb050);
					}
				}catch(e){
					
				}
				
				
			},
			error : function() {
			}
		});
		
	})
	
	
	
}