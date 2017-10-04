/**
 * @author TuNt create date Apr 12, 2016 ohhay-webapplication-new
 */
(function() {
	administratorBonevoImage = {
		/*
		 * init
		 */
		init : function() {
			
			administratorBonevoImage.eventListener();
			
			/*
			 * dialog upload image and thumbnail
			 */
			
			administratorBonevoImage.dialogAddImageThumbnail = $('#dialog-bonevo-image-add').dialog({
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
			    				 administratorBonevoImage.sendDataImage();
			    		 },text : "Ok",
			    		 class : 'btn-ok-confirm'
			    	 }
			     }
			});
		},
		/*
		 * event listener
		 */
		eventListener : function() {
			/*
			 * 
			 * delete image
			 */
			$(document).on('click','.qb-administrator-account-bonevo-image .content-image-item .content-main .evo-image-item .evo-image-content .delete-boevo-image',function(){
				var id = $(this).attr("idImage");
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					administratorBonevoImage.deleteBonevoImage(id);
				});
			});
			/*
			 * change image 
			 */
			$(document).on('click','#dialog-bonevo-image-add .data-thumbail .choice .image-bonevo',function(){
				$('#file-update-image-bonevo').trigger('click');
			});
			/*
			 * change thumnail
			 */
			$(document).on('click','#dialog-bonevo-image-add .data-thumbail .choice .thumnail-bonevo',function(){
				$('#file-update-thumbail-bonevo').trigger('click');
			});
			
			/*
			 * file choice change image
			 */
			$('#file-update-image-bonevo').on("change", function(evt){
				administratorBonevoImage.chooseFileTemplate(evt, $('#file-update-image-bonevo'),$('#dialog-bonevo-image-add .data-thumbail .choice .image-bonevo'),0);
			});
			/*
			 * file choice change thumnail
			 */
			$('#file-update-thumbail-bonevo').on("change", function(evt){
				administratorBonevoImage.chooseFileTemplate(evt,$('#file-update-thumbail-bonevo'),$('#dialog-bonevo-image-add .data-thumbail .choice .thumnail-bonevo'),-1);
			});
			
			
			/*
			 * event click add image
			 */
			$(document).on('click','.qb-administrator-account-bonevo-image .content-image-item .content-top .pull-left .button-add-image',function(){
				administratorBonevoImage.dialogAddImageThumbnail.dialog("open");
			});
			
		},
		/*
		 * load bonevo image
		 */
		loadBonevoImage : function() {
			$.ajax({
				url : encodeUrl("adminEvoImage.getListImage"),
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
						html+='<div class="col-md-3 evo-image-item">';
						html+=	'<div class="evo-image-content">';
						html+=		'<img alt="" class="check-imag-eerror animated bounceIn" src="'+data[i].pv501+'" thumbnail="'+data[i].pv502+'">';
						html+=		'<a class="delete-boevo-image" idImage="'+data[i].id+'"><i class="fa fa-times-circle" aria-hidden="true"></i></a>';
						html+=	'</div>';
						html+='</div>';				
					}
					//set total image
					$('.qb-administrator-account-bonevo-image .content-image-item .content-top .text-total .total').html(sizeNumber);
					//set data html
					$('.content-image-item .evo-image-content .content-main').html(html);
					//check image error
					adminSetting.imageErrorUpdate();
					}
				},
				error : function(){
					
				}
			});
		},
		/*
		* delete bonevo
		**/
		deleteBonevoImage : function(id){
			$.ajax({
				type : "POST",
				url : encodeUrl("adminEvoImage.deleteImageBonevo"),
				data : {
					"id" : id
				},
				success : function(response){
				var data = response.result;	
					administratorBonevoImage.loadBonevoImage();
				},
				error : function(){
					
				}
			});
		},
		/*
		* choiceImage
		*/
		chooseFileTemplate : function(evt,thisElemnt,element,type) {
			var files = evt.target.files;
			var file = files[0];
			if (files && file) {
				var reader = new FileReader();
				reader.onload = function(readerEvt) {
					var binaryString = readerEvt.target.result;
					/*administratorCMS.updateImage(btoa(binaryString),webId);*/
					element.css("background-image", "url('data:image/png;base64," + btoa(binaryString) + "')");
					thisElemnt.attr("base64",btoa(binaryString));
				};
				reader.readAsBinaryString(file);
			}
		},/*
		**
		* send data image
		*/
		sendDataImage : function(){
			console.log("Upload image now");
			var pv501 = $('#file-update-image-bonevo').attr("base64");
			var pv502 = $('#file-update-thumbail-bonevo').attr("base64");
			console.log(pv501+" ::: "+pv502);
//			administratorBonevoImage.updateImage(pv501,0);
			formData = new FormData();
			formData.append('pv501', "data:image/jpeg;base64,"+pv501);
			formData.append('pv502', "data:image/jpeg;base64,"+pv502);
			//set image base64
			formData.append('ext',"");
			$.ajax({
				type : "POST",
				url : encodeUrl("adminEvoImage.addImageBonevo"),
				processData: false,
				contentType: false,
				data : formData,
				success : function(response){
					var data = response.result;	
					if(data==1){
						administratorBonevoImage.dialogAddImageThumbnail.dialog("close");
						administratorBonevoImage.loadBonevoImage();
					}else
						util.messageDialog("Add image bonevo image fail !");
				},
				error : function(){
					
				}
	    });
		}
		
	};
}())