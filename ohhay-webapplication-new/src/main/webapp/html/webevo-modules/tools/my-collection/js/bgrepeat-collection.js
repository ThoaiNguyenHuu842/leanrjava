/**
 * @author ThoaiNH create Oct 16, 2015
 */
(function() {
	bgRepeatCollection = {
		/*
		 * open tool
		 */
		open : function(option) {
			// init when first call
			if (!bgRepeatCollection.inited) {
				bgRepeatCollection.init();
				bgRepeatCollection.inited = true;
			}
			bgRepeatCollection.callBack = option.callBack;
			$("#qb-dlg-bg-repeat-col").dialog("close");
			$("#qb-dlg-bg-repeat-col").dialog("open");
			$('#qb-dlg-bg-repeat-col .qb-tab-panel .header .tab-menu.default-tab').trigger('click');
		},
		/*
		 * close tool
		 */
		close : function() {
			$("#qb-dlg-bg-repeat-col").dialog("close");
		},
		/*
		 * setup tools
		 */
		init : function() {
			$("#qb-dlg-bg-repeat-col").webToolDialog(700);
			$('#qb-dlg-bg-repeat-col .qb-tab-panel .body .tab-panel').niceScroll();
			bgRepeatCollection.eventListener();
		},
		/*
		 * tool event
		 */
		eventListener : function() {
			/*
			 * on click o!hay img
			 */
			$(document).on('click', '#qb-dlg-bg-repeat-col .qb-tab-panel .body .tab-panel-2 li', function() {
				var url = $(this).attr("urlImg");// 'https://images.oohhay.com/bgohay/'
													// + $(this).attr('lang');
				var data = bgRepeatCollection.callBack({
					type : 'bg-repeat',
					data : url
				});
				$('#qb-dlg-bg-repeat-col').dialog('close');

			});

			/*
			 * onclick covert btn-close .qb-tab-panel.qb-dlg-bg-repeat-col
			 * .header .btn-close-dialog img
			 */
			$(document).on('click', '#qb-dlg-bg-repeat-col .qb-tab-panel.qb-tab-img-album .header .btn-close-dialog img', function() {
				$("#qb-dlg-bg-repeat-col").dialog("close");
			});
			/*
			 * on click my img
			 */
			$(document).on('click', '#qb-dlg-bg-repeat-col .qb-tab-panel .body .tab-panel-1 li', function() {
				var url = $(this).attr('lang');
				bgRepeatCollection.callBack({
					type : 'bg-repeat',
					data : url
				});
				$('#qb-dlg-bg-repeat-col').dialog('close');
			});
			/*
			 * on change tab image src
			 */
			$(document).on('click', '#qb-dlg-bg-repeat-col .qb-tab-panel .tab-menu', function(event) {
				$('#qb-dlg-bg-repeat-col .qb-tab-panel .tab-menu').removeClass('active');
				$(this).addClass('active');
				$('#qb-dlg-bg-repeat-col .qb-tab-panel .body .tab-panel').removeClass('active');
				if ($(this).hasClass('tab-menu-1')) {
					$('#qb-dlg-bg-repeat-col .qb-tab-panel .body .tab-panel-1').addClass('active');
					if (web.TRIAL != 'on') {
						myCollectionUtils.load({
							src : "background",
							ext : "",
							limit : 1000,
							callBack : function(response) {
								var htmlString = '';
								var imgHtml = '';
								for (i = response.result.length - 1; i >= 0; i--)
									htmlString += "<div imgId='" + response.result[i].id + "'  class='myimg-warrper'><li lang='" + response.result[i].pv951 + "' data-thumb='" + response.result[i].pv952 + "' class='animated bounceIn'>" + "</li><div class='function-panel'><span class='remove-img'><img src='" + util.contextPath() + "/html/images/menu-close.png' /></span></div></div>";
								$("#qb-dlg-bg-repeat-col .qb-my-image-panel .content-data-append").html(htmlString);
								$("#qb-dlg-bg-repeat-col .qb-my-image-panel li").each(function() {
									$(this).css('background-image', 'url(' + $(this).attr('data-thumb') + ')');
								});
							}
						});
					}
				} else {
					$('#qb-dlg-bg-repeat-col .qb-tab-panel .body .tab-panel-2').addClass('active');
					bgRepeatCollection.loadOhayImg();
				}
			});
			/*
			 * on click remove my img
			 */
			$(document).on('click', '#qb-dlg-bg-repeat-col .qb-tab-panel .body .tab-panel-1 .function-panel .remove-img', function(event) {
				var self = $(this);
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					self.parents(".myimg-warrper").fadeOut();
					myCollectionUtils.remove(self.parents(".myimg-warrper").attr("imgid"));
				});
			});
			/*
			 * on click add image
			 */
			$(document).on('click', '#qb-dlg-bg-repeat-col .qb-tab-img-album .btn-add-img', function(event) {
				$('#qb-dlg-bg-repeat-col .upload_background').trigger('click');
			});
			// using mutter
			$('#qb-dlg-bg-repeat-col .upload_background').on('change', function(event) {
				var data = new FormData(), files = this.files;
				$.each(files, function(i, file) {
					data.append(i, file);
				});
				data.append('rId', imageController.rId);
				data.append('siteid', $("#qb-input-pe400").val());
				data.append('fo100', $('#qb-userlogin-fo100').val());
				data.append('region', $('#qb-input-region').val());
				data.append('trial', web.TRIAL);
				data.append('targetSocket', 'reponseS3UploadBGRepeat');
				$.ajax({
					url : SOCKET_LINK + 'multerUpload' + '?stateLog=loged',
					data : data,
					cache : false,
					contentType : false,
					processData : false,
					type : 'POST'
				})

			});
			$('#qb-dlg-bg-repeat-col .upload_background.olddddd').on('change', function(event) {
				var input = $('#qb-dlg-bg-repeat-col .upload_background');
				var file = input[0].files[0];
				var $fileupload = $(this);
				var reader = new FileReader();
				var height_window = $(window).height();
				reader.onload = function(e) {
					var base64 = e.target.result;
					myCollectionUtils.upload({
						imgBase64 : base64,
						src : "background",
						callBack : function(response) {
							// we have the response
							if (response.status == AJAX_SUCESS) {
								var imgHtml = "<div imgId='" + response.result.id + "'  class='myimg-warrper'><li lang='" + response.result.imgUrl + "' class='animated bounceIn'>" + "</li><div class='function-panel'><span class='remove-img'><img src='" + util.contextPath() + "/html/images/menu-close.png' /></span></div></div>";
								$(imgHtml).insertAfter("#qb-dlg-bg-repeat-col .qb-my-image-panel .btn-add-img");
								$("#qb-dlg-bg-repeat-col .qb-my-image-panel li").each(function() {
									$(this).css('background-image', 'url(' + $(this).attr('lang') + ')');
								});
							} else {
								showGrowlMessageError();
							}
						}
					});
				}
				var file = this.files;
				reader.readAsDataURL(file[0]);
			});
		},
		/*
		 * load o!hay images
		 */
		loadOhayImg : function() {
			if ($("#qb-dlg-bg-repeat-col .qb-ohhay-image-panel").html().trim().length == 0) {
				// var htmlcontent = "<li lang='bg-rep-1.gif' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-2.png' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-3.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-4.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-5.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-6.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-7.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-9.gif' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-10.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-11.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-12.png' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-13.png' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-14.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-15.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-16.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-17.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-18.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-20.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-21.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-22.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-23.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-24.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-25.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-26.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-27.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-28.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-29.png' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-30.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-31.jpg' class='animated
				// bounceIn'></li>";
				// htmlcontent += "<li lang='bg-rep-32.png' class='animated
				// bounceIn'></li>";
				// $("#qb-dlg-bg-repeat-col
				// .qb-ohhay-image-panel").html(htmlcontent);
				// call ajax load image
				$.ajax({
					url : encodeUrl("bonevoBackgroundFree.getListImage"),
					success : function(response) {
						var data = response.result;
						var html = "";
						if (!data)
							html += 'No data';
						else {

							for (var i = 0; i < data.length; i++) {
								if (data[i].dateDelete) {
									continue;
								}
								html += '<li lang="bg-rep-' + i + '" urlImg="' + data[i].pv601 + '" class="animated bounceIn" style="background-image : url(' + data[i].pv601 + ');">';
								html += '</li>';
							}
							$("#qb-dlg-bg-repeat-col .qb-ohhay-image-panel").html(html);
						}
					},
					error : function() {

					}
				});
			}
			// $("#qb-dlg-bg-repeat-col .qb-ohhay-image-panel
			// li").each(function() {
			// $(this).css('background-image', 'url(' +
			// 'https://images.oohhay.com/bgohay/' + $(this).attr('lang') +
			// ')');
			// });
		}
	}
}());