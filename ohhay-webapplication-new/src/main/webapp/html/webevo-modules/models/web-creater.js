/**
 * @author ThoaiNH create Oct 13, 2015
 */
(function() {
	webCreater = {
		profileImgs : [],
		coverImgs : [],
		albumImgs: [],
		fbInfo: '',
		/*
		 * create web evo from data
		 */
		createLayout : function() {
			console.log(webCreater.data);
//			webConfig.data.imgLazy = webCreater.data.config.imgLazy;
//			alert(webCreater.data.config.imgLazy);
//			webConfig.data.imgLazy
			/*
			 * 0) set data to model
			 */
			webConfig.data = webCreater.data[WEB_PRO_CONFIG];
			webBgRepControl.data = webCreater.data[WEB_PRO_BG];
			bigBoxModelManager.listBox = webCreater.data[WEB_PRO_LISTBOX];
			// not update model while settup component
			componentModelManager['ROLE_UPDATE'] = false;
			componentModelManager.listComponent = webCreater.data[WEB_PRO_LISTCOMP];
			if(webConfig.data.imgLazy==1){

				$('.qb-panel-web-config #option-lazy button').addClass('btn-default');
				$('.qb-panel-web-config #option-lazy button').removeClass('btn-primary');
				$('.qb-panel-web-config #option-lazy button').removeClass('active');
				$('.qb-panel-web-config #option-lazy button[data="on"]').addClass('active');
				$('.qb-panel-web-config #option-lazy button[data="on"]').addClass('btn-primary');	
				$('.qb-panel-web-config #option-lazy button[data="on"]').removeClass('.btn-default');
			}
			if (bigBoxModelManager.length() > 0) {
				/*
				 * 1) add box
				 */
				bigbox.update();
				/*
				 * 2) add component
				 */
				componentModelManager.update();
				/*
				 * 3) reset box height, do khi add component vao thi height box
				 * bi reset lai
				 */
				setTimeout(function() {
					for ( var id in bigBoxModelManager.listBox) {
						var boxData = bigBoxModelManager.listBox[id];
						var h = boxData.h;
						if (web.MOBILE_EDITOR == 'on')
						{
							if(boxData[WEB_PRO_MDATA] && boxData[WEB_PRO_MDATA][WEB_PRO_H])
								h = boxData[WEB_PRO_MDATA][WEB_PRO_H];
							else {
								//ThoaiNH update 23/08/2015, fix height of box over lap component when first time edit
								var contentH = bigBoxModelManager.getContentHeight(id)/gridConfig.cell_height;
								if(contentH > h)
									 h = contentH;
							}
						}
						$('#content-wrapper .content-box[qb-box-id="' + boxData.id + '"] .grid-stack').height(h * gridConfig.cell_height);
						$('#content-wrapper .qb-body-box[qb-box-id="' + boxData.id + '"]').attr('qb-gird-h', h);
						console.log("--reset height:"+id+","+h);
						/*
						 * ThoaiNH
						 * update 20/01/2015
						 * disable editor of this section when reference from library
						 */
						if(boxData[WEB_PRO_LIBTYPE] == 3){
							for ( var id in componentModelManager.listComponent) {
								var component = componentModelManager.listComponent[id];
								if (component.boxId == boxData[WEB_PRO_ID]) {
									delete componentModelManager.listComponent[id];
								}
							}
							$('#content-wrapper .content-box[qb-box-id="' + boxData.id + '"]').addClass("content-box-from-lib");
							bigBoxModelManager.disableEdit(boxData.id);
						}
						if(web.MOBILE_EDITOR == 'on' && boxData[WEB_PRO_MDATA] && boxData[WEB_PRO_MDATA][WEB_PRO_HIDE] == true)
							$('#content-wrapper .content-box[qb-box-id="' + boxData.id + '"]').addClass("qb-box-hidden");
					}
					componentModelManager['ROLE_UPDATE'] = true;
					/*
					 * setup tools for mobile editor
					 */
					if (web.MOBILE_EDITOR == 'on') {
						/*
						 * add mobile edit tool
						 */
						// tool to hide component
						$("#content-wrapper .grid-stack-item-content .btn-dropdown-menu ul").append('<li class="btn-comp-hidemb"><a><i class="fa fa-eye-slash"></i>'+getLocalize("grid_title5")+'</a></li>');
						$("#content-wrapper .menu-right .btn-dropdown-menu ul").append('<li class="btn-box-hidemb btn-mobile-section-tools"><a><i class="fa fa-eye-slash"></i>'+getLocalize("grid_title5")+'</a></li>');
						// tool edit font size
						$("#content-wrapper .grid-stack-item-content.qb-component-text .btn-dropdown-menu ul").append('<li class="btn-mb-font-style"><a><i class="fa fa-font"></i>'+getLocalize("weh_font_scale")+'</a></li>');
						$("#content-wrapper .grid-stack-item-content.qb-component-button .btn-dropdown-menu ul").append('<li class="btn-mb-font-style"><a><i class="fa fa-font"></i>'+getLocalize("weh_font_scale")+'</a></li>');
						/*
						 * off editor tools not allow on mobile mode
						 */
						// Thong: Editor
						cp_Editor.goToPreviewMode();
						// Thong: Menu
						omenu.goToPreviewMode();
						obutton.goToPreviewMode();
					}
					/*
					 * test fast fb
					 */
					if (web.FAST == 'fast-with-fb')
						webCreater.webFastWithFB();
					/*$(".grid-stack" ).selectable({
						filter: ".grid-stack-item-content"
					});*/
					if(getUrlParameter("autosave") == 1)
						$('#menu-tools .btn-menu-save').trigger('click');
					/*
					 * show tour guide
					 */
					if($('#flag-show-tour-guide').val() == 1)
					{
						$('#flag-show-tour-guide').val(0);
						editorTourGuide.init();
					}
				}, 1000);
			} else {
				$("#content-wrapper").append('<div id="panel-no-box" class="qb-edit-tool"><label>'+getLocalize("grid_title2")+' '+'<span class="btn-click-add-box">+</span>'+' '+getLocalize("grid_title3")+'</label></div>');
				componentModelManager['ROLE_UPDATE'] = true;
				web.START_WITH_NOBOX = true;
				$('.content-start').show();
			}
			/*
			 * 4) load web info
			 */
			webBgRepControl.update();
			if (web.MOBILE_EDITOR != 'on')
				webConfig.update();
			/*
			 * 5) render web tools
			 */
			webMenuLeft.render();
			/*
			 * ThoaiVt - 27/08/2016
			 * add event change all component
			 */	
			if(ENABLE_UNDOMANAGER==1)
				undoRedoMgr.eventListener();
			
		},
		/*
		 * get topic content evo data
		 */
		getTopicContentData : function() {
			$.ajax({
				type : "POST",
				url : encodeUrl("webCreatorBean.createTopicContentEvo"),
				data : {
					topicId : socketio.id_topic
				},
				success : function(response) {
					webCreater.data = response.result;
					console.log(response.result.listBox);
					webCreater.createLayout();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * get web data of user login
		 */
		getWebData : function() {
			$.ajax({
				type : "POST",
				url : encodeUrl("webCreatorBean.create"),
				data : {
					pe400 : $("#qb-input-pe400").val()
				},
				success : function(response) {
					webCreater.data = response.result;
					console.log(response.result.listBox);
					webCreater.createLayout();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * get web data from template when using trial
		 */
		getWebDataTrial : function() {
			var pe400 = 268;
			if ($('#webid-trial').val() > 0)
				pe400 = $('#webid-trial').val();
			if(getUrlParameter("template") > 0)
				pe400 = getUrlParameter("template");
			$.ajax({
				type : "POST",
				url : encodeUrl("webCreatorBean.createTrial"),
				data : {
					pe400 : pe400
				},
				success : function(response) {
					console.log(response.result.listBox);
					webCreater.data = response.result;
					webCreater.createLayout();
					webCreater.data.fe400 = webCreater.data.id;
					// set id test to webid
					webCreater.data.id = $("#selected-country").attr("idtest");
					// set crId
					bigBoxModelManager.crId = bigBoxModelManager.getMaxId();
					componentModelManager.crId = componentModelManager.getMaxId();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * get web data of userlogin
		 */
		getWebDataViewer : function() {
			$.ajaxSetup({
				beforeSend : function() {
					
				},
				complete : function() {
					setDefaultAjaxStatus();
				}
			});
			$.ajax({
				type : "POST",
				url : encodeUrl("webCreatorBean.create"),
				data : {
					pe400 : $("#qb-input-pe400").val()
				},
				success : function(response) {
					webCreater.data = response.result;
					webConfig.data = webCreater.data[WEB_PRO_CONFIG];
					webBgRepControl.data = webCreater.data[WEB_PRO_BG];
					bigBoxModelManager.listBox = webCreater.data[WEB_PRO_LISTBOX];
					componentModelManager.listComponent = webCreater.data[WEB_PRO_LISTCOMP];
					console.log(webCreater.data);
					/*
					 * setup for API components
					 */
					$("#content-wrapper .content-box .qb-component-gmap").each(function() {
						var compId = $(this).attr('qb-component-id');
						var dataMap = componentModelManager.getDataFromID(compId);
						console.log('gmap : ' + compId);
						console.log(gmap);
						var htmlGmap = gmap.genericLoadData();
						$(this).append(htmlGmap);
						console.log("Load " + htmlGmap);
						try {
							console.log(componentModelManager.getDataFromID(compId));
							console.log("Data : " + dataMap);
							gmap.loadInitGmap(dataMap, 1, 0);
							$(this).find(".qb-component").attr("style", componentModelManager.listComponent[compId][WEB_PRO_CSS]);
						} catch (s) {
							console.log("Data : " + s.toString());
							gmap.loadInitGmap(null, 1, 0);
						}
					});
					$("#content-wrapper .content-box .qb-component-iframe-embed").each(function() {
						var compId = $(this).attr('qb-component-id');
						var data = componentModelManager.getDataFromID(compId);						
						var ifr = document.createElement("iframe");
						ifr.style.width= "100%";
						ifr.style.height= "100%";
						ifr.className = 'qb-iframe-embed';
						ifr.setAttribute("frameborder", "0");
						ifr.setAttribute("id", "iframeResult");  
						$('.grid-stack-item-content[qb-component-id="'+compId+'"] .qb-iframe-embed').remove();
						$('.grid-stack-item-content[qb-component-id="'+compId+'"] .qb-component').prepend(ifr);
						var ifrw = (ifr.contentWindow) ? ifr.contentWindow : (ifr.contentDocument.document) ? ifr.contentDocument.document : ifr.contentDocument;
						ifrw.document.open();
						if(data)
							ifrw.document.write(data.html);  
						ifrw.document.close();
					});
					/*
					 * widgetwebinaris
					 */
					try {
						loadWidgetWebinaris();
					} catch (e) {
						// TODO: handle exception
						console.log(e);
					}
					/*
					 * set up youtube playlist
					 */
					$('.qb-component-youtube-playlist .content-label').niceScroll();
					$('.qb-component-youtube-playlist .video-item[index="0"]').addClass('playing');
					 // on click video item
					var player = new YT.Player('ytplayer', {
				          events: {
				        	 'onReady': onPlayerRady,
				            'onStateChange': onPlayerStateChange
				          }
				    });
					$(document).on('click','.qb-component-youtube-playlist .content-label .video-item',function(){
						$('.qb-component-youtube-playlist .video-item').removeClass('playing');
						$(this).addClass('playing');
						$("#ytplayer").attr('current', $(this).attr('index'));
						var videoId = $(this).attr('video-id');
						player.loadVideoById(videoId);
					});
					function onPlayerRady(event) {        
				    }
				    function onPlayerStateChange(event) {        
				    	if (event.data === 0)
				    	{
				    		var currentVideo = parseInt($('#ytplayer').attr('current'));
				    		var compId = $('#ytplayer').parents('.grid-stack-item-content').attr('qb-component-id');
				    		var data = componentModelManager.getDataFromID(compId)
				    		var videos = data.videos;
				    		console.log(videos);
				    		var nextVideo;
				    		if(videos.length -1 > currentVideo)
				    		{
				    			$('.qb-component-youtube-playlist .video-item').removeClass('playing');
				    			$('.qb-component-youtube-playlist .video-item[index="'+(currentVideo + 1)+'"]').addClass('playing');
				    			nextVideo = videos[currentVideo + 1];
				    			$("#ytplayer").attr('current',currentVideo + 1 +'');
				    		}
				    		else
				    		{
				    			$('.qb-component-youtube-playlist .video-item').removeClass('playing');
				    			$('.qb-component-youtube-playlist .video-item[index="0"]').addClass('playing');
				    			nextVideo = videos[0];
				    			$("#ytplayer").attr('current', 0 + '');
				    		}
				    		player.loadVideoById(nextVideo.videoId);
				    	}
				    }
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * radom img
		 */
		getRandomImg : function(array) {
			var pos = Math.floor(Math.random() * (array.length - 1)) + 0;
			return array[pos];
		},
		/*
		 * authen with FB
		 */
		webFastWithFB : function() {
			this.checkFacebookLoginState();
		},
		/*
		 * get FB info
		 */
		getFacebookInfo : function() {
			FB.api('/me', 'GET', {
				'fields' : 'first_name,last_name,name,email,id'
			}, function(result) {
				result["regisTypel"] = "FB";
				webCreater.fbInfo = result;
				console.log(webCreater.fbInfo);
			});
			FB.api('/me/albums', 'GET', {
				'fields' : 'name,cover_photo{images},count',
				'limit' : '25'
			}, function(result) {
				console.log(result);
				var pos = 0;
				var po2 = 0;
				var pos3 = 0;
				var maxCount = 0;
				for (i = 0; i < result.data.length; i++) {
					//console.log(result.data[i]['name']);
					if (result.data[i]['name'] == 'Cover Photos') {
						pos = i;
					}
					if (result.data[i]['name'] == 'Profile Pictures') {
						pos2 = i;
					}
					if (result.data[i]['count'] > maxCount) {
						maxCount = result.data[i]['count'];
						pos3 = i;
					}
				}
				console.log("pos:"+pos+",pos2:"+pos2);
				/*
				 * get first album images 
				 */ 
				FB.api('/' + result.data[pos].id + '/photos', 'GET', {
					"fields" : "images,name",
					'limit' : '25'
				}, function(result1) {
					if (result1.data) {
						if (result1.data.length > 0) {
							$.each(result1.data, function(k, v) {
								var images = v.images;
								var src = images[0].source;
								webCreater.coverImgs.push(src);
							})
						}
					}
					/*
					 * get second album images
					 */
					FB.api('/' + result.data[pos2].id + '/photos', 'GET', {
						"fields" : "images,name",
						'limit' : '25'
					}, function(result2) {
						console.log(result.data[pos2].id);
						if (result2.data) {
							if (result2.data.length > 0) {
								$.each(result2.data, function(k, v) {
									var images = v.images;
									var src = images[0].source;
									webCreater.profileImgs.push(src);
								})
							}
						}
						/*
						 * get third album images
						 */
						FB.api('/' + result.data[pos3].id + '/photos', 'GET', {
							"fields" : "images,name",
							'limit' : '25'
						}, function(result3) {
							if (result3.data) {
								if (result3.data.length > 0) {
									$.each(result3.data, function(k, v) {
										var images = v.images;
										var src = images[0].source;
										webCreater.albumImgs.push(src);
									})
								}
							}
							console.log(webCreater.profileImgs);
							console.log(webCreater.coverImgs);
							console.log(webCreater.albumImgs);
							$('.comp-image').each(function() {
								var size = parseInt($(this).attr('data-gs-width')) * parseInt($(this).attr('data-gs-height'));
								var i = 0;
								var compId = $(this).attr('qb-component-id');
								//console.log(compId);
								$(this).find('img').each(function() {
									var imgSrc = '';
									if (size > 100)
										imgSrc = webCreater.getRandomImg(webCreater.albumImgs);
									else
										imgSrc = webCreater.getRandomImg(webCreater.profileImgs);
									$(this).attr('src', imgSrc);
									$(this).attr('data-real-src', imgSrc);
									// update model
									componentModelManager.listComponent[compId][WEB_PRO_DATA]["img"][i] = imgSrc;
									i++;
								});

							});
							$('.qb-component-button').each(function() {
								var compId = $(this).attr('qb-component-id');
								var button = $(this).find('.obutton-link');
								if (button.css('background-image').indexOf('url') == 0) {
									var newImg = webCreater.getRandomImg(webCreater.profileImgs);
									var data = componentModelManager.listComponent[compId][WEB_PRO_DATA];
									data['obuttonHover']['onmouseout']['this.style.background'] = [ "rgba(0,0,0,0) url(" + newImg + ") repeat scroll 0 0 / cover" ];
									webUtils.setBackgroundImg(button, newImg);
									webUtils.addAttributeEffect(button, data.obuttonHover.onmouseout, 'onmouseout');
								}
							});
							$('.grid-stack').each(function() {
								var boxId = $(this).parents('.content-box').attr('qb-box-id');
								if ($(this).css('background-image').indexOf('url') == 0 || $(this).find('.layer-background').css('background-image').indexOf('url') == 0) {
									$(this).find('.layer-background').css('background-image', 'url("' + webCreater.getRandomImg(webCreater.coverImgs) + '")')
									// update model
									bigBoxModelManager.listBox[boxId][WEB_PRO_GRID_BGCSS] = webUtils.getCssEffect($(this).find('.layer-background'));
								}
							});
							$('.content-box').each(function() {
								var boxId = $(this).attr('qb-box-id');
								if ($(this).css('background-image').indexOf('url') == 0 || $(this).find('.layer-background').css('background-image').indexOf('url') == 0) {
									$(this).find('.layer-background').first().css('background-image', 'url("' + webCreater.getRandomImg(webCreater.coverImgs) + '")')
									// update model
									bigBoxModelManager.listBox[boxId][WEB_PRO_BGCSS] = webUtils.getCssEffect($(this).find('.layer-background').first());
								}
							});
							if ($('.wrapper-background').css('background-image').indexOf('url') == 0) {
								var img = webCreater.getRandomImg(webCreater.coverImgs);
								// update model
								webBgRepControl.data[WEB_PRO_DATA] = img;
								webBgRepControl.update();
							}
							$(".fast-with-fb-full-name").html(webCreater.fbInfo.name);
							$('.fb-ajax-content').hide();
						});
					});
				});
			});
		},
		/*
		 * check FB login stt
		 */
		checkFacebookLoginState : function() {
			FB.getLoginStatus(function(result) {
				console.log(result);
				if (result.status === 'connected') {
					webCreater.getFacebookInfo();
				} else if (result.status === 'not_authorized') {
					webCreater.loginFacebook();
				} else {
					webCreater.loginFacebook();
				}
			});
		},
		/*
		 * login to FB
		 */
		loginFacebook : function() {
			FB.login(function(result) {
				webCreater.checkFacebookLoginState();
			}, {
				scope : 'public_profile,email,user_photos'
			});
		},
	}
}());