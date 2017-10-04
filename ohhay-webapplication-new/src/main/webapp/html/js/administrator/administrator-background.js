/**
 * ThoaiVt
 * create Apr 21, 2016
 */
(function(){
	adminBackground = {
			/*
			 * init
			 */
			init : function(){
				//add eventListener
				adminBackground.eventListener();
				/*
				 * dialog upload image and thumbnail
				 */
				
				adminBackground.dialogAddBackgroundImage = $('#dialog-bonevo-background-add').dialog({
					 autoOpen : false,
					 modal: true,  
					 resizable: false,
					 closeOnEscape: true,
					 draggable: false,
					 dialogClass : "dialog-add-image-thumnail-bonevo-image",
					 width : 350,
					 height : 180,
					 position: {
				         my: 'center', 
				         at: 'center'
					 },
					 show : 'fade',
					 hide : 'fade',
				     buttons : {
				    	 "Ok" : {
				    		 click :  function(){
				    			 adminBackground.sendDataImage();
				    		 },text : "Ok",
				    		 class : 'btn-ok-confirm'
				    	 }
				     }
				});
			},
			/*
			 * eventListener
			 */
			eventListener : function(){
				//delete background
				$(document).on('click','.qb-administrator-bonevo-background .content-background-item .content-main .evo-background-item .evo-background-content .delete-bonevo-background',function(){
					var id = $(this).attr("idImage");
					util.confirmDialog(getLocalize("jsstd_title1"), function() {
						adminBackground.deleteBackground(id);
					});
				});
				//change background
				$(document).on('click','#dialog-bonevo-background-add .data-thumbail .choice .image-bonevo',function(){
					console.log("ACTIVE !");
					$('#file-update-background-bonevo').trigger('click');
				});
				/*
				 * file choice change image background
				 */
				$('#file-update-background-bonevo').on("change", function(evt){
					administratorBonevoImage.chooseFileTemplate(evt, $('#file-update-background-bonevo'),$('#dialog-bonevo-background-add .data-thumbail .choice .image-bonevo'),0);
				});
				//event add image
				$(document).on('click','.qb-administrator-bonevo-background .content-background-item .content-top .pull-left a.button-add-image',function(){
					adminBackground.dialogAddBackgroundImage.dialog("open");
				});
				
			},
			/*
			 * load background
			 */
			loadBonevoBackground : function(){
				$.ajax({
					url : encodeUrl("adminBackground.loadListBackground"),
					success : function(response){
					var data = response.result;	
					var html ="";
					var sizeNumber = 0;
					console.log(data);
					if(!data)
						html+='No data';
					else{
						
						for (var i = 0; i< data.length; i++) {
							if(data[i].dateDelete){
								console.log("DDD");
								continue;
							}
							sizeNumber++;
							html+='<div class="col-md-3 evo-background-item">';
							html+=	'<div class="evo-background-content">';
							html+=		'<img alt="" class="check-imag-eerror animated bounceIn" src="'+data[i].pv601+'" >';
							html+=		'<a class="delete-bonevo-background" idImage="'+data[i].id+'"><i class="fa fa-times-circle" aria-hidden="true"></i></a>';
							html+=	'</div>';
							html+='</div>';				
						}
						//set total image
						$('.qb-administrator-bonevo-background .content-background-item .content-top .text-total .total').html(sizeNumber);
						//set data html
						$('.qb-administrator-bonevo-background .content-background-item .evo-background-content .content-main').html(html);
						//check image error
						adminSetting.imageErrorUpdate();
						}
					},
					error : function(){
						
					}
				});
			},
			/*
			 * delete background data
			 */
			deleteBackground:  function(id){
				$.ajax({
					type : "POST",
					url : encodeUrl("adminBackground.deleteBackgroundBonevo"),
					data : {
						"id" : id
					},
					success : function(response){
						var data = response.result;	
						adminBackground.loadBonevoBackground();
					},
					error : function(){
						
					}
				});
			},/*
			**
			* add background image
			*/
			sendDataImage : function(){
				var pv501 = $('#file-update-background-bonevo').attr("base64");
				formData = new FormData();
				formData.append('pv501', "data:image/jpeg;base64,"+pv501);
				//set image base64
				formData.append('ext',"");
				$.ajax({
					type : "POST",
					url : encodeUrl("adminBackground.addIBonevoBackground"),
					processData: false,
					contentType: false,
					data : formData,
					success : function(response){
						var data = response.result;	
						if(data==1){
							adminBackground.dialogAddBackgroundImage.dialog("close");
							adminBackground.loadBonevoBackground();
						}else
							util.messageDialog("Add background bonevo image fail !");
					},
					error : function(){
						
					}
		    });
			}
}}());