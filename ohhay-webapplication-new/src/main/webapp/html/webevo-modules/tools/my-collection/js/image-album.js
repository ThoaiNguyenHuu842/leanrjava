/**
 * @author ThoaiNH create Oct 16, 2015
 */
(function() {
	imageAlbum = {
		limit : 0,// limit page load
		limitPage : 50,
		limitPageImgFree : 50,
		offsetTemp : 0,
		totalPageTemp : 0,
		totalPageTempFreeImg : 0,
		numberPage : 0,// total summation page
		selectMode : "single",
		selectedImgs : [],
		/*
		 * open tool
		 */
		open : function(option) {
			// init when first call
			if (!imageAlbum.inited) {
				imageAlbum.init();
				imageAlbum.inited = true;
				imageAlbum.imagePaginationFree = new PaginationObj("imageFree");
				imageAlbum.imagePaginationMy = new PaginationObj("imageAlbum");
			}
			imageAlbum.callBack = option.callBack;
			// render mode
			if (option.selectMode)
				imageAlbum.selectMode = option.selectMode;
			else
				imageAlbum.selectMode = "single";
			if (imageAlbum.selectMode == "single")
				$("#qb-dlg-album .form-group-btn").hide();
			else
				$("#qb-dlg-album .form-group-btn").show();
			imageAlbum.selectedImgs = [];
			// reset selected style
			$("#qb-dlg-album .selected-img").removeClass("selected-img");
			$("#qb-dlg-album .total-selected").html(0);

			$("#qb-dlg-album").dialog("close");
			$("#qb-dlg-album").dialog("open");
			$('#qb-dlg-album .qb-tab-panel .header .tab-menu.default-tab')
					.trigger('click');
		},
		/*
		 * close tool
		 */
		close : function() {
			$("#qb-dlg-album").dialog("close");
		},
		/*
		 * setup tools
		 */
		init : function() {
			$("#qb-dlg-album").webToolDialog(700);
			$('#qb-dlg-album .qb-tab-panel .body .tab-panel').niceScroll();
			imageAlbum.eventListener();
			var dropboxOptions = {
				// Required. Called when a user selects an item in the Chooser.
				success : function(files) {
					console.log(files[0].link);
					var data = files[0].link;
					imageAlbum.callBack({
						type : 'image',
						data : data,
					});
				},
				// Optional. Called when the user closes the dialog without
				// selecting a file
				// and does not include any parameters.
				cancel : function() {

				},
				// Optional. "preview" (default) is a preview link to the
				// document for sharing,
				// "direct" is an expiring link to download the contents of the
				// file. For more
				// information about link types, see Link types below.
				linkType : "direct", // or "direct"

				// Optional. A value of false (default) limits selection to a
				// single file, while
				// true enables multiple file selection.
				multiselect : false, // or true

				// Optional. This is a list of file extensions. If specified,
				// the user will
				// only be able to select files with these extensions. You may
				// also specify
				// file types, such as "video" or "images" in the list. For more
				// information,
				// see File types below. By default, all extensions are allowed.
				extensions : [ '.jpg', '.jpeg', '.png', '.tiff', '.tif',
						'.gif', '.bmp', '.webp', '.wbmp' ],
			};
			var dropboxButton = Dropbox.createChooseButton(dropboxOptions);
			$(
					'#qb-dlg-album .qb-tab-img-album .body .right-content div.dropbox-api')
					.append(dropboxButton);

		},
		/*
		 * tool event
		 */
		eventListener : function() {
			/*
			 * onclick done mutilple select
			 */
			$(document).on('click', '#qb-dlg-album .btn-finish-select',
					function() {
						imageAlbum.callBack({
							type : 'gallery',
							data : imageAlbum.selectedImgs
						});
					});
			/*
			 * onclick covert btn-close .qb-tab-panel.qb-tab-img-album .header
			 * .btn-close-dialog img
			 */
			$(document)
					.on(
							'click',
							'#qb-dlg-album .qb-tab-panel.qb-tab-img-album .header .btn-close-dialog img',
							function() {
								$("#qb-dlg-album").dialog("close");
							});
			/*
			 * on click o!hay img
			 */
			$(document).on('click',
					'#qb-dlg-album .qb-tab-panel .body .tab-panel-2 img',
					function() {
						var data = $(this).attr("data-real-src");
						imageAlbum.onClickImg(data, $(this));
					});
			/*
			 * on click my img
			 */
			$(document)
					.on(
							'click',
							"#qb-dlg-album .qb-tab-panel .body .tab-panel-1 img[data-real-src]",
							function() {
								var data = $(this).attr('data-real-src');
								imageAlbum.onClickImg(data, $(this));
							});
			$(document)
					.on(
							'click',
							'#qb-dlg-album .qb-tab-img-album .body .right-content .facebook-album div.facebook-api.social-one-image',
							function() {
								var data = $(this).attr('facebook-img-source');
								imageAlbum.onClickImg(data, $(this));
							});
			$(document)
					.on(
							'click',
							'#qb-dlg-album .qb-tab-img-album .body .right-content .flickr-album div.flickr-api.social-one-image',
							function() {
								var data = $(this).attr('flickr-img-source');
								imageAlbum.onClickImg(data, $(this));
							});
			$(document)
					.on(
							'click',
							'#qb-dlg-album .qb-tab-img-album .body .right-content .instagram-album div.instagram-api.social-one-image',
							function() {
								var data = $(this).attr('instagram-img-source');
								imageAlbum.onClickImg(data, $(this));
							});
			/*
			 * on change tab image src
			 */
			$(document).on(
					'click',
					'#qb-dlg-album .qb-tab-panel .tab-menu',
					function(event) {
						$('#qb-dlg-album .qb-tab-panel .tab-menu').removeClass(
								'active');
						$(this).addClass('active');
						$('#qb-dlg-album .qb-tab-panel .body .tab-panel')
								.removeClass('active');
						if ($(this).hasClass('tab-menu-1')) {
							$('#qb-dlg-album .qb-tab-panel .body .tab-panel-1')
									.addClass('active');
							$('#qb-dlg-album .pagination-content').addClass(
									'active');
							imageAlbum.offsetTemp = 0;
							imageAlbum.initLoadPgn = undefined;
							if (web.TRIAL != 'on') {
								imageAlbum.imagePaginationMy.clean();
								imageAlbum.imagePaginationFree.clean();
								imageAlbum.getAlbumImage(0);
							}
						} else if ($(this).hasClass('tab-menu-2')) {
							imageAlbum.initLoadPgn = undefined;
							imageAlbum.offsetTemp = 0;
							$('#qb-dlg-album .qb-tab-panel .body .tab-panel-2')
									.addClass('active');
							$('#qb-dlg-album .pagination-content').addClass(
									'active');
							imageAlbum.imagePaginationMy.clean();
							imageAlbum.imagePaginationFree.clean();
							imageAlbum.getAlbumImageFree(0);
						} else if ($(this).hasClass('tab-menu-3')) {
							$('#qb-dlg-album .qb-tab-panel .body .tab-panel-3')
									.addClass('active');
							$('#qb-dlg-album .pagination-content').removeClass(
									'active');

						}
						// reset selected style
						imageAlbum.selectedImgs = [];
						$("#qb-dlg-album .selected-img").removeClass(
								"selected-img");
						$("#qb-dlg-album .total-selected").html(0);
					});
			/*
			 * event data scroll my collection
			 */
			$('#qb-dlg-album .qb-tab-panel .body .tab-panel-1').scroll(
					function() {
					});

			/*
			 * on click remove my img
			 */
			$(document)
					.on(
							'click',
							'#qb-dlg-album .qb-tab-panel .body .tab-panel-1 .function-panel .remove-img',
							function(event) {
								var self = $(this);
								util.confirmDialog(getLocalize("jsstd_title1"),
										function() {
											console.log("Fade out IMage");
											self.parents(".myimg-warrper")
													.fadeOut();
											myCollectionUtils.remove(self
													.parents(".myimg-warrper")
													.attr("imgid"));
										});
							});
			/*
			 * on click add image
			 */
			$(document).on('click',
					'#qb-dlg-album .qb-tab-img-album .btn-add-img',
					function(event) {
						$('#qb-dlg-album .upload_background').trigger('click');
					});
			// use multer
			$('#qb-dlg-album .upload_background').on('change', function(e) {
				var data = new FormData(), files = this.files;
				$.each(files, function(i, file) {
					data.append(i, file);
				});
				data.append('rId', imageController.rId);
				data.append('siteid', $("#qb-input-pe400").val());
				data.append('fo100', $('#qb-userlogin-fo100').val());
				data.append('region', $('#qb-input-region').val());
				data.append('trial', web.TRIAL);
				data.append('targetSocket', 'reponseS3UploadLibrary');
				$.ajax({
					url : SOCKET_LINK + 'multerUpload' + '?stateLog=loged',
					data : data,
					cache : false,
					contentType : false,
					processData : false,
					type : 'POST'
				});
			});
			// use socket-io.stream: treamming data
			$('#qb-dlg-album .upload_background-io.stream').on(
					'change',
					function(e) {
						var d = new Date(), n = d.getTime(), id = n
								+ imageUtil.rand_string(), files = this.files;
						for (var i = 0; i < files.length; i++) {
							var stream = ss.createStream();
							ss(imageController.socket).emit('uploadEvoMyImage',
									stream, {
										index : i,
										size : files[i].size,
										siteid : webCreater.data.id,
										fo100 : $('#qb-userlogin-fo100').val(),
										region : $('#qb-input-region').val(),
										id : id
									});
							ss.createBlobReadStream(files[i]).pipe(stream);
						}
					});
			$('#qb-dlg-album .upload_background-old')
					.on(
							'change',
							function(event) {
								var input = $('#qb-dlg-album .upload_background');
								var file = input[0].files[0];
								var ext = file.name.split('.').pop()
										.toLowerCase();
								var height_window = $(window).height();
								var files = this.files;
								for (i = 0; i < files.length; i++) {
									var reader = new FileReader();
									reader.onload = function(e) {
										var base64 = e.target.result;
										myCollectionUtils
												.upload({
													imgBase64 : base64,
													src : "image",
													ext : ext,
													callBack : function(
															response) {
														if (response.status == AJAX_SUCESS) {
															var imgHtml = "<div imgId='"
																	+ response.result.id
																	+ "'class='myimg-warrper'><img src='"
																	+ response.result.imgUrl
																	+ "'/><div class='function-panel'><span class='remove-img'><img src='"
																	+ util
																			.contextPath()
																	+ "/html/images/menu-close.png' /></span></div></div>";
															if (web.TRIAL == 'on')
																imgHtml = "<div imgId='"
																		+ response.result.id
																		+ "'class='myimg-warrper'><img src='"
																		+ response.result.imgUrl
																		+ "'/><div class='function-panel'><span class='remove-img'><img src='"
																		+ util
																				.contextPath()
																		+ "/html/images/menu-close.png' /></span></div></div>";
															$(imgHtml)
																	.insertAfter(
																			".qb-my-image-panel .btn-add-img");
														} else {
															showGrowlMessageError();
														}
													}
												});
									}
									reader.readAsDataURL(files[i]);
								}
							});
			$(document)
					.on(
							'click',
							'#qb-dlg-album .qb-tab-img-album .body li',
							function() {
								$(
										'#qb-dlg-album .qb-tab-img-album .body .right-content div')
										.removeClass('active');
								if ($(this).hasClass('facebook-api')) {
									$(
											'#qb-dlg-album .qb-tab-img-album .body .right-content .facebook-api')
											.addClass('active');
									$(
											'#qb-dlg-album .qb-tab-img-album .body .right-content .facebook-api.album-content')
											.siblings().removeClass('active');
									$(
											'#qb-dlg-album .qb-tab-img-album .body li.facebook-api')
											.addClass('active').siblings()
											.removeClass('active');
									social.checkFacebookLoginState(imageAlbum
											.getFacebookAlbums());
								} else if ($(this).hasClass('instagram-api')) {
									$(
											'#qb-dlg-album .qb-tab-img-album .body .right-content .instagram-api')
											.addClass('active');
									$(
											'#qb-dlg-album .qb-tab-img-album .body .right-content .instagram-api.album-content')
											.siblings().removeClass('active');
									$(
											'#qb-dlg-album .qb-tab-img-album .body li.instagram-api')
											.addClass('active').siblings()
											.removeClass('active');
									social.checkLoginInstagram({
										callBack : function(result) {
											result.isUserLogin ? imageAlbum
													.getInstagramImages('')
													: social.loginInstagram();
										}
									});
								} else if ($(this).hasClass('flickr-api')) {
									$(
											'#qb-dlg-album .qb-tab-img-album .body .right-content .flickr-api')
											.addClass('active');
									$(
											'#qb-dlg-album .qb-tab-img-album .body .right-content .flickr-api.album-content')
											.siblings().removeClass('active');
									$(
											'#qb-dlg-album .qb-tab-img-album .body li.flickr-api')
											.addClass('active').siblings()
											.removeClass('active');
									social
											.checkLoginFlickr({
												callBack : function(result) {
													imageAlbum.currentFlickrPage = 1;
													result.isUserLogin ? imageAlbum
															.getFlickrAlbum(imageAlbum.currentFlickrPage)
															: social
																	.loginFlickr();
												}
											});
								} else if ($(this).hasClass('dropbox-api')) {
									$(
											'#qb-dlg-album .qb-tab-img-album .body .right-content .dropbox-api')
											.addClass('active');
									$(
											'#qb-dlg-album .qb-tab-img-album .body .right-content .dropbox-api.album-content')
											.siblings().removeClass('active');
									$(
											'#qb-dlg-album .qb-tab-img-album .body li.dropbox-api')
											.addClass('active').siblings()
											.removeClass('active');
								}
								$(
										'#qb-dlg-album .qb-tab-img-album .body .left-menu .logout-service')
										.addClass('active');
							});
			$(document)
					.on(
							'click',
							'#qb-dlg-album .qb-tab-img-album .body .left-menu .logout-service.active',
							function() {
								$(
										'#qb-dlg-album .qb-tab-img-album .body .left-menu .logout-service')
										.removeClass('active');
								var currentService = $('#qb-dlg-album .qb-tab-img-album .body li.active');
								if (currentService.hasClass('facebook-api')) {
									FB
											.logout(function(result) {
												console.log(result);
												$(
														'#qb-dlg-album .qb-tab-img-album .body .facebook-api')
														.removeClass('active');
												$(
														'#qb-dlg-album .qb-tab-img-album .body .right-content .facebook-album')
														.html('');
											});
								} else if (currentService
										.hasClass('instagram-api')) {
									social
											.logoutInstagram({
												callBack : function() {
													$(
															'#qb-dlg-album .qb-tab-img-album .body .instagram-api')
															.removeClass(
																	'active');
													$(
															'#qb-dlg-album .qb-tab-img-album .body .right-content .instagram-album')
															.html('');
												}
											});
								} else if (currentService
										.hasClass('flickr-api')) {
									social
											.logoutFlickr({
												callBack : function() {
													$(
															'#qb-dlg-album .qb-tab-img-album .body .flickr-api')
															.removeClass(
																	'active');
													$(
															'#qb-dlg-album .qb-tab-img-album .body .right-content .flickr-album')
															.html('');
												}
											});
								}
							});
			$(document)
					.on(
							'click',
							'#qb-dlg-album .qb-tab-img-album .body .right-content div.facebook-api.social-one-album',
							function() {
								var albumID = $(this).attr('facebook-album-id');
								$(
										'#qb-dlg-album .qb-tab-img-album .body .right-content a.album-link')
										.addClass('active-link');
								imageAlbum.getFacebookPhotosInAlbum(albumID);
							});
			$(document)
					.on(
							'click',
							'#qb-dlg-album .qb-tab-img-album .body .right-content div.flickr-api.social-one-album',
							function() {
								imageAlbum.currentFlickrAlbum = $(this).attr(
										'flickr-album-id');
								imageAlbum.currentFlickrPage = 1;
								$(
										'#qb-dlg-album .qb-tab-img-album .body .right-content a.album-link')
										.addClass('active-link');
								imageAlbum.getFlickrPhotosInAlbum(
										imageAlbum.currentFlickrAlbum,
										imageAlbum.currentFlickrPage);
							});
			$(document)
					.on(
							'click',
							'#qb-dlg-album .qb-tab-img-album .body .right-content a.facebook-api.album-link.active-link',
							function() {
								$(this).removeClass('active-link');
								imageAlbum.getFacebookAlbums();
							});
			$(document)
					.on(
							'click',
							'#qb-dlg-album .qb-tab-img-album .body .right-content a.flickr-api.album-link.active-link',
							function() {
								$(this).removeClass('active-link');
								imageAlbum.currentFlickrPage = 1;
								imageAlbum
										.getFlickrAlbum(imageAlbum.currentFlickrPage);
							});
			$(
					'#qb-dlg-album .qb-tab-img-album .body .right-content div.facebook-album')
					.scroll(
							function() {
								if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
									if (!imageAlbum.isGettingData) {
										if (imageAlbum.currentFacebookType === 'albums'
												&& imageAlbum.currentFacebookAfter) {
											imageAlbum
													.getFacebookAlbums(imageAlbum.currentFacebookAfter);
										} else if (imageAlbum.currentFacebookType === 'images'
												&& imageAlbum.currentFacebookAfter) {
											imageAlbum
													.getFacebookPhotosInAlbum(
															imageAlbum.currentFacebookAlbumID,
															imageAlbum.currentFacebookAfter);
										}
									}
								}
							});
			$(
					'#qb-dlg-album .qb-tab-img-album .body .right-content div.flickr-album')
					.scroll(
							function() {
								if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
									if (!imageAlbum.isGettingData) {
										if (imageAlbum.currentFlickrType === 'albums'
												&& !imageAlbum.isLastPageFlickr) {
											imageAlbum
													.getFlickrAlbum(imageAlbum.flickrCurrentPage + 1);
										} else if (imageAlbum.currentFlickrType === 'images'
												&& !imageAlbum.isLastPageFlickr) {
											imageAlbum
													.getFlickrPhotosInAlbum(
															imageAlbum.currentFlickrAlbum,
															imageAlbum.currentFlickrPage + 1);
										}
									}
								}
							});
			$(
					'#qb-dlg-album .qb-tab-img-album .body .right-content div.instagram-album')
					.scroll(
							function() {
								if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
									if (!imageAlbum.isGettingData) {
										if (imageAlbum.instagramNextMaxID) {
											imageAlbum
													.getInstagramImages(imageAlbum.instagramNextMaxID)
										}
									}
								}
							});
		},
		/*
		 * on click image
		 */
		onClickImg : function(data, self) {
			if (imageAlbum.selectMode == 'multiple') {
				if (self.hasClass('selected-img'))
					imageAlbum.selectedImgs.splice(imageAlbum.selectedImgs
							.indexOf(data), 1);
				else
					imageAlbum.selectedImgs.push(data);
				self.toggleClass('selected-img');
				$("#qb-dlg-album .total-selected").html(
						imageAlbum.selectedImgs.length);
			} else
				imageAlbum.callBack({
					type : 'image',
					data : data
				});
			console.log("select");
			console.log(data);
			if (imageAlbum.selectMode == "single")
				$("#qb-dlg-album").dialog('close');
		},
		getAlbumImageFree : function(offset) {
			myCollectionUtils
					.loadFreeImg(
							{
								src : 'image',
								limit : imageAlbum.limitPageImgFree,
								offset : offset,
								callBack : function(response, numberpage) {
									var htmlString = '';
									for (var i = 0; i < response.result.length; i++) {
										var imgHtml = "<div imgId='"
												+ response.result[i].id
												+ "' class='myimg-warrper'>";
										imgHtml += "		<img class='img-fromlist' src='"
												+ response.result[i].pv502
												+ "' data-real-src='"
												+ response.result[i].pv501
												+ "'/>";
										imgHtml += "	</div>";
										htmlString += imgHtml;
									}
									console.log(htmlString);
									imageAlbum.totalPageTempFreeImg = numberpage;
									$(
											"#qb-dlg-album .qb-ohhay-image-panel .content-data-append")
											.html(htmlString);
								}
							},
							function() {
								numberpage = imageAlbum.totalPageTempFreeImg;
								console.log(numberpage);
								imageAlbum.imagePaginationFree
										.genericPadingtion(
												$('.pagination-testvtt'),
												function(data) {
													console.log(data);
													console.log("FREE IMAGE");
													imageAlbum
															.getAlbumImageFree(data.offset);
													imageAlbum.offsetTemp = data.offset;
												}, numberpage,
												imageAlbum.limitPageImgFree,
												false, undefined, 15, true);

							});

		},
		getFacebookInfo : function() {
			FB.api('/me', 'GET', {
				'fields' : 'name,email,id'
			}, function(result) {
				console.log(result);
			});
		},
		getFacebookAlbums : function(after) {
			imageAlbum.isGettingData = true;
			if (after === undefined) {
				$(
						'#qb-dlg-album .qb-tab-img-album .body .right-content .facebook-album')
						.html('');
			}
			FB
					.api(
							'/me/albums',
							'GET',
							{
								'fields' : 'name,cover_photo{images},count',
								'limit' : '25',
								'after' : after === undefined ? '' : after
							},
							function(result) {
								if (result.data != undefined) {
									if (result.data.length > 0) {
										$
												.each(
														result.data,
														function(k, v) {
															if (v.count > 0) {

															}
															if (v.cover_photo != undefined) {
																var coverPhotoImages = v.cover_photo.images;
																var oneAlbum = '';
																oneAlbum += '<div class="facebook-api social-one-album active" style="background-image:url('
																		+ coverPhotoImages[coverPhotoImages.length - 1].source
																		+ ')" facebook-album-id="'
																		+ v.id
																		+ '" facebook-album-name="'
																		+ v.name
																		+ '">';
																oneAlbum += '<p class="album-name">'
																		+ v.name
																		+ '</p>';
																oneAlbum += '</div>';
																$(
																		'#qb-dlg-album .qb-tab-img-album .body .right-content .facebook-album')
																		.append(
																				oneAlbum);
															} else {
																var oneAlbum = '';
																oneAlbum += '<div class="facebook-api social-one-album active" style="background-color:#0099ff" facebook-album-id="'
																		+ v.id
																		+ '" facebook-album-name="'
																		+ v.name
																		+ '">';
																oneAlbum += '<p class="album-name">'
																		+ v.name
																		+ '</p>';
																oneAlbum += '</div>';
																$(
																		'#qb-dlg-album .qb-tab-img-album .body .right-content .facebook-album')
																		.append(
																				oneAlbum);
															}
															if (k == result.data.length - 1) {
																imageAlbum.isGettingData = false;
															}
														});
										if (result.paging && result.paging.next) {
											imageAlbum.currentFacebookAfter = result.paging.cursors.after;
											imageAlbum.currentFacebookType = 'albums';
										} else {
											imageAlbum.currentFacebookAfter = undefined;
										}
									}

								} else {
									imageAlbum.getFacebookAlbums();
								}
							});

		},
		getAlbumImage : function(offset) {
			myCollectionUtils.load({
				src : 'image',
				limit : imageAlbum.limitPage,
				offset : offset,
				callBack : function(response, numberpage) {
					var htmlString = '';
					for (var i = 0; i < response.result.length; i++) {
						var imgHtml = "<div imgId='" + response.result[i].id
								+ "' class='myimg-warrper'>";
						imgHtml += "		<img class='img-fromlist' src='"
								+ response.result[i].thumb
								+ "' data-real-src='"
								+ response.result[i].realSrc + "'/>";
						imgHtml += "		<div class='function-panel'>";
						imgHtml += "			<span class='remove-img'><img src='"
								+ util.contextPath()
								+ "/html/images/menu-close.png' /></span>";
						imgHtml += "		</div>";
						imgHtml += "	</div>";
						htmlString += imgHtml;
					}
					imageAlbum.totalPageTemp = numberpage;
					$("#qb-dlg-album .qb-my-image-panel .content-data-append")
							.html(htmlString);
				}
			}, function() {
				numberpage = imageAlbum.totalPageTemp;
				console.log(numberpage);
				// imageAlbum.imagePaginationMy.cleanGenerateTemplate();
				imageAlbum.imagePaginationMy.genericPadingtion(
						$('.pagination-testvtt'), function(data) {
							console.log(data);
							console.log("Load my image");
							imageAlbum.getAlbumImage(data.offset);
							imageAlbum.offsetTemp = data.offset;
						}, numberpage, imageAlbum.limitPage, false, undefined,
						15, true);
			});
		},
		getFacebookPhotosInAlbum : function(albumID, after) {
			imageAlbum.isGettingData = true;
			if (after === undefined) {
				$(
						'#qb-dlg-album .qb-tab-img-album .body .right-content .facebook-album')
						.html('');
			}
			FB
					.api(
							'/' + albumID + '/photos',
							'GET',
							{
								"fields" : "images,name",
								'limit' : '25',
								'after' : after === undefined ? '' : after
							},
							function(result) {
								console.log(result);
								if (result.data) {
									if (result.data.length > 0) {
										$
												.each(
														result.data,
														function(k, v) {
															var images = v.images;
															var oneImage = '<div class="facebook-api social-one-image active" style="background-image:url('
																	+ images[images.length - 1].source
																	+ ')" facebook-img-source="'
																	+ images[0].source
																	+ '"></div>';
															$(
																	'#qb-dlg-album .qb-tab-img-album .body .right-content .facebook-album')
																	.append(
																			oneImage);
															if (k == result.data.length - 1) {
																imageAlbum.isGettingData = false;
															}
														})

										if (result.paging && result.paging.next) {
											imageAlbum.currentFacebookAfter = result.paging.cursors.after;
											imageAlbum.currentFacebookType = 'images';
											imageAlbum.currentFacebookAlbumID = albumID;
										} else {
											imageAlbum.currentFacebookAfter = undefined;
											imageAlbum.currentFacebookAlbumID = undefined;
										}
									}
								} else {
									imageAlbum.getFacebookPhotosInAlbum(
											albumID, after);
								}
							});
		},
		getFlickrAlbum : function(currentPage) {
			imageAlbum.isGettingData = true;
			if (currentPage === 1)
				$(
						'#qb-dlg-album .qb-tab-img-album .body .right-content .flickr-album')
						.html('');
			var param = {
				primary_photo_extras : 'url_s,url_m,url_o',
				page : currentPage,
				per_page : 50,
			};
			$
					.ajax({
						url : encodeUrl('flickrBean.callAPI'),
						type : 'POST',
						data : {
							method : 'flickr.photosets.getList',
							paramArray : $.toJSON(param),
							withUser : true,
						},
						success : function(result) {
							// console.log(result);
							var jsonResult = jQuery.parseJSON(result.result);
							var photosets = jsonResult.photosets;
							imageAlbum.isLastPageFlickr = photosets.page == photosets.pages;
							imageAlbum.currentFlickrType = 'albums';
							imageAlbum.currentFlickrPage = currentPage;
							if (photosets.photoset.length > 0) {
								$
										.each(
												photosets.photoset,
												function(k, v) {
													// console.log(v.primary_photo_extras.url_s);
													var oneAlbum = '';
													oneAlbum += '<div class="flickr-api social-one-album active" style="background-image:url('
															+ v.primary_photo_extras.url_s
															+ ')" flickr-album-id="'
															+ v.id
															+ '" flickr-album-name="'
															+ v.title._content
															+ '">';
													oneAlbum += '<p class="album-name">'
															+ v.title._content
															+ '</p>';
													oneAlbum += '</div>';
													$(
															'#qb-dlg-album .qb-tab-img-album .body .right-content .flickr-album')
															.append(oneAlbum);
												});
								imageAlbum.isGettingData = false;
							}
						},
						error : function(e) {

						}
					});
		},
		getFlickrPhotosInAlbum : function(albumID, currentPage) {
			imageAlbum.isGettingData = true;
			if (currentPage === 1)
				$(
						'#qb-dlg-album .qb-tab-img-album .body .right-content .flickr-album')
						.html('');
			var param = {
				photoset_id : albumID,
				extras : 'url_s,url_m,url_o',
				page : currentPage,
				per_page : 50,
				media : 'photos',
			};
			$
					.ajax({
						url : encodeUrl('flickrBean.callAPI'),
						type : 'POST',
						data : {
							method : 'flickr.photosets.getPhotos',
							paramArray : $.toJSON(param),
							withUser : true,
						},
						success : function(result) {
							// console.log(result);
							var jsonResult = jQuery.parseJSON(result.result);
							var photoset = jsonResult.photoset;
							imageAlbum.isLastPageFlickr = photoset.page == photoset.pages;
							imageAlbum.currentFlickrType = 'images';
							imageAlbum.currentFlickrPage = currentPage;
							if (photoset.photo.length > 0) {
								$
										.each(
												photoset.photo,
												function(k, v) {
													// console.log(v.url_s);
													var oneImage = '<div class="flickr-api social-one-image active" style="background-image:url('
															+ v.url_s
															+ ')" flickr-img-source="'
															+ v.url_o
															+ '"></div>';
													$(
															'#qb-dlg-album .qb-tab-img-album .body .right-content .flickr-album')
															.append(oneImage);
												});
								imageAlbum.isGettingData = false;
							}
						},
						error : function(error) {

						}
					});
		},
		getInstagramImages : function(maxID) {
			imageAlbum.isGettingData = true;
			if (maxID === '')
				$(
						'#qb-dlg-album .qb-tab-img-album .body .right-content .instagram-album')
						.html('');
			var param = {
				count : 20,
				max_id : maxID,
			}
			$
					.ajax({
						url : encodeUrl('instagramBean.callAPI'),
						type : 'POST',
						data : {
							endpoint : 'users/self/media/recent',
							paramArray : $.toJSON(param),
						},
						success : function(result) {
							// console.log(result);
							var jsonResult = jQuery.parseJSON(result.result);
							var photos = jsonResult.data;
							imageAlbum.instagramNextMaxID = jsonResult.pagination.next_max_id;
							if (photos.length > 0) {
								$
										.each(
												photos,
												function(k, v) {
													// console.log(v.images.low_resolution.url);
													var oneImage = '<div class="instagram-api social-one-image active" style="background-image:url('
															+ v.images.low_resolution.url
																	.split('?')[0]
															+ ')" instagram-img-source="'
															+ v.images.standard_resolution.url
																	.split('?')[0]
															+ '"></div>';
													$(
															'#qb-dlg-album .qb-tab-img-album .body .right-content .instagram-album')
															.append(oneImage);
												});
								imageAlbum.isGettingData = false;
							}
						},
						error : function(error) {

						}
					});
		}
	}
}());