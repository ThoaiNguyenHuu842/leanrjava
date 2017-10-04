/**
 * ThoaiVt
 * create Apr 23, 2016
 */
(function(){
	administratorVideo = {
		/*
		 * init 
		 */
		init : function(){
			administratorVideo.eventListener();
			/*
			 * init dialog upload video and thumbnail
			 * 
			 */
			administratorVideo.dialogAddVideoThumbnail = $('#dialog-bonevo-video-bonevo-add').dialog({
				 autoOpen : false,
				 modal: true,  
				 resizable: false,
				 closeOnEscape: true,
				 draggable: false,
				 dialogClass : "dialog-add-video-thumbnail-bonevo",
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
			    			 administratorVideo.sendDataImage();
			    		 },text : "Ok",
			    		 class : 'btn-ok-confirm'
			    	 }
			     }
			});
		},
		/*
		 * event Listener
		 */
		eventListener : function(){
			/*
			 * delete video
			 */
			$(document).on('click','.bonevo-general-class-background-video .content-image-item .content-main .evo-image-item .evo-image-content .delete-bonevo-video',function(){
				var id = $(this).attr("idVideo");
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					administratorVideo.deleteVideo(id);
				});
			});
			
			/*
			 * event click add video
			 */
			$(document).on('click','.qb-administrator-account-bonevo-video .content-image-item .content-top .pull-left .button-add-video-thumbnail',function(){
				administratorVideo.dialogAddVideoThumbnail.dialog("open");
			});
			/*
			 * change video 
			 */
			$(document).on('click','#dialog-bonevo-video-bonevo-add .data-thumbail .choice .image-bonevo',function(){
				$('#file-update-video-bonevo').trigger('click');
			});
			/*
			 * change thumnail
			 */
			$(document).on('click','#dialog-bonevo-video-bonevo-add .data-thumbail .choice .thumnail-bonevo',function(){
				$('#file-update-thumbail-of-video-bonevo').trigger('click');
			});
			/*
			 * file choice change video
			 */
			$('#file-update-video-bonevo').on("change", function(evt){
				administratorVideo.chooseFileTemplate(evt, $('#file-update-video-bonevo'),$('#dialog-bonevo-video-bonevo-add .data-thumbail .choice .image-bonevo'),0);
			});
			/*
			 * file choice change image thumnail
			 */
			$('#file-update-thumbail-of-video-bonevo').on("change", function(evt){
				administratorVideo.chooseFileTemplate(evt, $('#file-update-thumbail-of-video-bonevo'),$('#dialog-bonevo-video-bonevo-add .data-thumbail .choice .thumnail-bonevo '),1);
			});
		},
		/*
		 * choice video and image thumbnail
		 */
		chooseFileTemplate : function(evt,thisElemnt,element,type) {
			var files = evt.target.files;
			var file = files[0];
			if (files && file) {
				var reader = new FileReader();
				reader.onload = function(readerEvt) {
					var binaryString = readerEvt.target.result;
					 ""+btoa(evt.target.result);
					/*administratorCMS.updateImage(btoa(binaryString),webId);*/
					 if(type==1){
						element.css("background-image", "url('data:image/png;base64," + btoa(binaryString) + "')");
						thisElemnt.attr("base64",btoa(binaryString));
					 }else{
						element.find("source").attr("src","data:video/webm;base64," + btoa(binaryString));
						thisElemnt.attr("binaryVideo",btoa(binaryString));
//						console.log("'data:video/webm;base64," + btoa(binaryString));
					 }
				};
				reader.readAsBinaryString(file);
			}
		},
		/*
		 * load video
		 */
		loadBonevoVideo : function() {
			$.ajax({
				url : encodeUrl("bonEvoVideo.getListVideo"),
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
						html+=		'<img alt="" class="check-imag-eerror animated bounceIn" src="'+data[i].pv702+'" thumbnail="'+data[i].pv702+'">';
						html+=		'<a class="delete-bonevo-video" idVideo="'+data[i].id+'"><i class="fa fa-times-circle" aria-hidden="true"></i></a>';
						html+=		'<video class="item-video" autoplay loop><source src="'+data[i].pv701+'" type="video/mp4"></video>';
						html+=	'</div>';
						html+='</div>';				
					}
					//set total image
					$('.bonevo-general-class-background-video .content-image-item .content-top .text-total .total').html(sizeNumber);
					//set data html
					$('.content-video-bonevo-item .evo-image-content .content-main').html(html);
					//check image error
					adminSetting.imageErrorUpdate();
					}
				},
				error : function(){
					
				}
			});
		},
		/*
		*
		* send data video
		*/
		sendDataImage : function(){
			console.log("Upload image now");
			var pv501 = $('#file-update-video-bonevo').attr("binaryVideo");
			var pv502 = $('#file-update-thumbail-of-video-bonevo').attr("base64");
			console.log(pv501+" ::: "+pv502);
//			administratorBonevoImage.updateImage(pv501,0);
			var filename = $('#file-update-video-bonevo')[0].files[0].name;
			//get extension for file
			var ext =  filename.slice((Math.max(0, filename.lastIndexOf(".")) || Infinity) + 1);	
			formData = new FormData();
			formData.append('pv501', "data:image/jpeg;base64,"+pv501);
			formData.append('pv502', "data:image/jpeg;base64,"+pv502);
			//set image base64
			formData.append('ext',ext);
			$.ajax({
				type : "POST",
				url : encodeUrl("adminEvoVideo.addVideoData"),
				processData: false,
				contentType: false,
				data : formData,
				success : function(response){
					var data = response.result;	
					if(data==1){
//						administratorVideo.dialogAddVideoThumbnail.dialog("close");
						administratorVideo.loadBonevoVideo();
					}else
						util.messageDialog("Add video bonevo image fail !");
				},
				error : function(){
					
				}
	    });
		},
		/*
		 * delete video
		 */
		deleteVideo : function(id){
			$.ajax({
				type : "POST",
				url : encodeUrl("adminEvoVideo.deleteVideo"),
				data : {
					"id" : id
				},
				success : function(response){
				var data = response.result;	
					administratorVideo.loadBonevoVideo();
				},
				error : function(){
					
				}
			});
		}
	}
}())