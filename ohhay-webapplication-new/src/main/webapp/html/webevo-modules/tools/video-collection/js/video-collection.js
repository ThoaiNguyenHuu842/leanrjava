/**
 * @author ThoaiNH create Nov 10, 2015
 */
(function() {
	videoCollection = {
		/*
		 * open tool
		 */
		open : function(option) {
			this.callBack = option.callBack;
			// init when first call
			if (!videoCollection.inited) {
				videoCollection.init();
				videoCollection.inited = true;
			}
			$("#qb-dlg-video-col").dialog("close");
			$("#qb-dlg-video-col").dialog("open");
		},
		/*
		 * close tool
		 */
		close : function() {
			// $("#qb-dlg-album").dialog("close");
		},
		/*
		 * setup tools
		 */
		init : function() {
			videoCollection.loadOhayBackgroundVideo();
			$("#qb-dlg-video-col").webToolDialog(700);
			$('#qb-dlg-video-col .body .sub-panel ul').niceScroll();
			videoCollection.eventListener();
		},
		/*
		 * tool event
		 */
		eventListener : function() {
			/*
			 * Author: loc date created: 16/10/2015 change background to video
			 */
			$(document).on('click touchend', "#qb-dlg-video-col .background-edit-panel .body ul li", function(event) {
				var src_video = $(this).attr('urlVideo');
				videoCollection.setBackgroundVideo(src_video);
				$('#qb-dlg-video-col').dialog('close');
			});
			/*
			 * onclick covert btn-close .qb-tab-panel.qb-dlg-video-col .header .btn-close-dialog img
			 */
			$(document).on('click','#qb-dlg-video-col .qb-tab-panel.qb-tab-img-album .header .btn-close-dialog img',function(){
				$("#qb-dlg-video-col").dialog("close");
			});
		},
		/*
		 * load video O!hay
		 */
		loadOhayBackgroundVideo : function() {
			var htmlcontent = "";
			$.ajax({
				url : encodeUrl("bonEvoVideo.getListVideo"),
				success : function(response){
				var data = response.result;	
					console.log(data);
					if(!data)
						htmlcontent+='No data';
					else{
						for (var i = 0; i< data.length; i++) {
							if(data[i].dateDelete){
								console.log("DDD");
								continue;
							}
							htmlcontent += "<li lang='video_bg_'"+i+"'.mp4' urlVideo='"+data[i].pv701+"' class='animated bounceInLeft' style='background: url("+data[i].pv702+")'></li>";
						}
						$('#qb-dlg-video-col .background-edit-panel .body ul').html(htmlcontent);
					}
				},
				error : function(){
					
				}
			});
			
//			var htmlcontent = "<li lang='video_bg_1.mp4' class='animated bounceIn' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_1.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_2.mp4' class='animated bounceInLeft' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_2.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_3.mp4' class='animated bounceInDown' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_3.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_4.mp4' class='animated bounceInTop' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_4.png\'\) no-repeat scroll 0% 0% / cover;\"</li>";
//			htmlcontent += "<li lang='video_bg_5.mp4' class='animated bounceInDown' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_5.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_6.mp4' class='animated bounceInTop' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_6.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_7.mp4' class='animated bounceInLeft' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_7.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_8.mp4' class='animated bounceIn' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_8.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_9.mp4' class='animated bounceInRight' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_9.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_10.mp4' class='animated bounceInLeft' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_10.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_11.mp4' class='animated bounceInDown' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_11.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_12.mp4' class='animated bounceInTop' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_12.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_13.mp4' class='animated bounceInRight' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_13.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_14.mp4' class='animated bounceInLeft'  style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_14.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_15.mp4' class='animated bounceInDown'  style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_15.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//
//			htmlcontent += "<li lang='video_bg_16.mp4' class='animated bounceInLeft'  style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_16.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_17.mp4'  class='animated bounceInLeft' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_17.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_18.mp4' class='animated bounceInRight'  style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_18.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_19.mp4'  class='animated bounceInRight' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_19.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//
//			htmlcontent += "<li lang='video_bg_20.mp4'  class='animated bounceInLeft' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_20.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_21.mp4'  class='animated bounceInLeft' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_21.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_22.mp4'  class='animated bounceInLeft' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_22.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_23.mp4'  class='animated bounceInLeft' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_23.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//
//			htmlcontent += "<li lang='video_bg_24.mp4'  class='animated bounceInRight' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_24.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_25.mp4'  class='animated bounceInRight' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_25.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_26.mp4'  class='animated bounceInRight' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_26.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_27.mp4'  class='animated bounceInRight' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_27.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_28.mp4'  class='animated bounceInRight' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_28.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
//			htmlcontent += "<li lang='video_bg_29.mp4'  class='animated bounceInRight' style=\"background: url\(\'https:\/\/images.oohhay.com\/resource\/videos\/thumb\/video_bg_29.png\'\) no-repeat scroll 0% 0% / cover;\"></li>";
			
		},
		/*
		 * set background video
		 */
		setBackgroundVideo : function(src_video) {
			videoCollection.callBack({
				type : 'video',
				data : src_video
			});
		}
	}
}());