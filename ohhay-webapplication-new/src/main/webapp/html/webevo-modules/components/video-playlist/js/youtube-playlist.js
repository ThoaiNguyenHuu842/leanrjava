
/**
 * @author ThoaiNH
 * create Jan 26, 2016
 */
(function() {
	youtubePlaylist = {
		COMP_MIN_H: 8,
		COMP_MIN_W: 8,
		videos: [],//current edit video list
		LOOP : 0,
		AUTO : 0,
		init : function() {
			youtubePlaylist.eventListener();
		},
		/*
		 * component eventListener
		 */
		eventListener: function(){
			youtubePlaylist.searchVideo();
			/*
			 * on click change layout
			 */
			$(document).on('click','.content-box .qb-component-youtube-playlist .btn-change-video-layout',function(){
				var videoId = $(this).attr('video-id');
				//var data = componentModelManager.listComponent[compId][WEB_PRO_DATA];
				if($(this).parents('.qb-component-youtube-playlist').find('.qb-component').hasClass('style1'))
				{
					$(this).parents('.qb-component-youtube-playlist').find('.qb-component').removeClass('style1');
					$(this).parents('.qb-component-youtube-playlist').find('.qb-component').addClass('style2');
				}
				else
				{
					$(this).parents('.qb-component-youtube-playlist').find('.qb-component').removeClass('style2');
					$(this).parents('.qb-component-youtube-playlist').find('.qb-component').addClass('style1');
				}
			});
			/*
			 * on click edit video play list
			 */
			$(document).on('click','.content-box .qb-component-youtube-playlist .btn-change-video-list',function(){
				var gridItem = $(this).parents('.grid-stack-item-content');
				var compId = $(this).parents('.grid-stack-item-content').attr('qb-component-id');
				var data = componentModelManager.listComponent[compId][WEB_PRO_DATA];
				youtubePlaylist.videos = data.videos;
				youtubePlaylist.openChangeVideoPlaylist({
					callBack: function(){
						data.videos = youtubePlaylist.videos;
						data["loop"] = youtubePlaylist.LOOP;
						data["auto"] = youtubePlaylist.AUTO;
						componentModelManager.updateItemField(compId, WEB_PRO_DATA, data);
						//update current video playlist
						if(data.videos.length > 0){
							gridItem.find('.iframe-player').attr("src","https://www.youtube.com/embed/"+data.videos[0].videoId+"?autoplay="+youtubePlaylist.AUTO+"&enablejsapi=1");
							var htmlLabel = "";
							for(var i = 0; i < data.videos.length; i ++){
								var item = data.videos[i];
								htmlLabel += buildYoutubePlayListVideoItem(item,i,data);
							}
							gridItem.find('.content-label').html(htmlLabel);
						}
						else
						{
							gridItem.find('iframe').attr('src','');
							gridItem.find('.content-label').html('');
						}
					}
				});
			});
			
			/*
			 * Change styles playlist
			 * ThoaiVt 29/01/2016
			 * 
			 */
			$(document).on('click','.content-box .qb-component-youtube-playlist .btn-change-playlist-style',function(){
				var compId = $(this).closest('.grid-stack-item-content').attr('qb-component-id');
				playListChange.open(compId);
			});
		},
		/*
		 * tool edit eventListener
		 */
		toolEventListener: function(){
			/*
			 * on click save video playlist editor
			 */
			$(document).on('click','#qb-dlg-change-video-playlist #btn-save-change-video-playlist',function(){
				youtubePlaylist.callBack();
				$('#qb-dlg-change-video-playlist').dialog("close");
			});
			/*
			 * click remove video item
			 */
			$(document).on('click','#qb-dlg-change-video-playlist .current-playlist .btn-remove-video-item',function(){
				$(this).parents('.video-item').fadeOut();
				var videoId = $(this).parents('.video-item').attr('video-id');
				for(var i = youtubePlaylist.videos.length - 1; i >= 0; i--) {
				    if(youtubePlaylist.videos[i].videoId === videoId) {
				    	youtubePlaylist.videos.splice(i, 1);
				    	console.log(youtubePlaylist.videos);
				    	return;
				    }
				}
			});
			/*
			 * on add video item
			 */
			$(document).on('keydown', '#qb-dlg-change-video-playlist #input-video-playlist-src', function (event) {
				var data = $('#qb-dlg-change-video-playlist #input-video-playlist-src').val();
				youtubePlaylist.searchVideo(data);
				if (event.which == 13) {
					/*var videoid = $(this).val().trim();
					var videoItem = {videoId:videoid,title:"Linkin Park - Somewhere I Belong (Official Video)", channelTitle:"",publishedAt:"",description:""};
					youtubePlaylist.videos.push(videoItem);
					var html = "";
					html += "<div video-id='"+videoItem.id+"' class='video-item'>";
					html += "	<span class='btn-remove-video-item'>x</span>";
					html += "	<label>"+videoItem.title+"</label>";
					html += "</div>";
					$('#qb-dlg-change-video-playlist .current-playlist').append(html);
					$(this).val('');*/
					var data = $('#qb-dlg-change-video-playlist #input-video-playlist-src').val();
					youtubePlaylist.youtubeShow(data);
					$('.qb-panel-iframe-src .form-group .form-group-content .data-search-video .list-data-result').niceScroll();
					$('#qb-dlg-change-video-playlist .current-playlist').niceScroll();
				}
			});
			
			/*
			 * event select data on list search youtube
			 */
			$(document).on("click","#qb-dlg-change-video-playlist .qb-panel-iframe-src .form-group .form-group-content .data-search-video .list-data-result li",function(){
				var title = $(this).attr("title");
				var videoid = $(this).attr("id-url");
				var channelTitle = $(this).attr("channel");
				var publishedAt = $(this).attr("publishedat");
				var description = $(this).attr("description");
				var videoItem = {videoId:videoid,title:title, channelTitle:channelTitle,publishedAt:publishedAt,description:description};
				youtubePlaylist.videos.push(videoItem);
				var html = "";
				html += "<div video-id='"+videoItem.id+"' class='video-item'>";
				html += "	<span class='btn-remove-video-item'>x</span>";
				html += "	<label>"+videoItem.title+"</label>";
				html += "</div>";
				$('#qb-dlg-change-video-playlist .current-playlist').append(html);
			});
			
			/*
			 * search video when click button search
			 */
			$(document).on("click","#qb-dlg-change-video-playlist .qb-panel-iframe-src .form-group .form-group-content .input-search #search-youtube-api",function(){
				var data = $('#qb-dlg-change-video-playlist #input-video-playlist-src').val();
				youtubePlaylist.youtubeShow(data);
				$('#qb-dlg-change-video-playlist .qb-panel-iframe-src .form-group .form-group-content .data-search-video .list-data-result').niceScroll();
				$('#qb-dlg-change-video-playlist .current-playlist').niceScroll();
			});
			
			/*
			 * ThoaiVt 
			 * toggle class
			 */
			
			$(document).on('click','#qb-dlg-change-video-playlist .qb-panel-iframe-src .input-fied-option .form-group-content .btn-toggle',function(){
				$(this).find('.btn').toggleClass('active');  
			    if ($(this).find('.btn-primary').size()>0) 
			    	$(this).find('.btn').toggleClass('btn-primary');
			    $(this).find('.btn').toggleClass('btn-default');
			    var type = $(this).attr("type");
			    var turn = $(this).children('.active').attr('data');
			    if(type=="autoplay")
			    	youtubePlaylist.AUTO = (turn=='on')?'1':'0';
			    else if(type=="loop")
			    	youtubePlaylist.LOOP = (turn=='on')?'1':'0';//playlist
			    
			    console.log("DATA : LOOP - "+youtubePlaylist.LOOP+" AUTOPLAY - "+youtubePlaylist.AUTO);
			});
		},
		/*
		 * on add component
		 */
		add : function(grid, x, y, $self) {
			var data = {
				videos:[
			        {videoId:"zsCD5XCu6CM",title:"Linkin Park - Somewhere I Belong (Official Video)", channelTitle:"Linkin Park",publishedAt:"",description:""},
			        {videoId:"YLHpvjrFpe0",title:"Linkin Park - From The Inside (Official Video)", channelTitle:"Linkin Park",publishedAt:"",description:""},
			        {videoId:"51iquRYKPbs",title:"Linkin Park - The Catalyst (Official Video)", channelTitle:"Linkin Park",publishedAt:"",description:""}
			    ],
				title: {
					css:"",
					visible:"on"//on or off
				},
				channelTitle: {
					css:"",
					visible:"on"//on or off
				},
				thumbnail: {
					css:"",
					visible:"on"//on or off
				},
				player: {
					css:"",
					visible:"on"//on or off
				},
				playlist: {
					css: "",
					visible : "on"
				},
				layout: null
			};
			var node = {
				x : x,
				y : y,
				width : 48,
				height : 33,
			};
			var tempBG = 'background-color: #000;'
			var widget = grid.add_widget($(buildYoutubePlayList(data,tempBG)), node.x, node.y, node.width, node.height, true);
			/*setTimeout(function(){
				widget.find('.content-label').niceScroll();
			}, 2000);*/
			widget[WEB_PRO_DATA] = data;
			widget[WEB_PRO_BGCSS] = tempBG;
			//update 17/11/2016 - call to action
			//widget.find('.btn-change-video-list').trigger('click');
			return widget;
		},
		/*
		 * on load component
		 */
		load: function(grid, node, data){
			var widget = grid.add_widget($(buildYoutubePlayList(data)), node.x, node.y, node.width, node.height);
			return widget;
		},
		/*
		 * ThoaiVt 
		 * load check compoment
		 * 
		 */
		loadFlagChangeStyle : function(){
//			var 
		}
		,
		/*switchButton: function(selector,type){
			if(type==1){
				$(selector).first().addClass("btn-primary active");
				$(selector).last().addClass("btn-primary active");
			}
		}*/
		/*
		 * on click open edit video playlist
		 */
		openChangeVideoPlaylist: function(option){
			youtubePlaylist.callBack = option.callBack;
			if(!youtubePlaylist.inited)
			{
				youtubePlaylist.toolEventListener();
				$('#qb-dlg-change-video-playlist').webToolDialog(396);
				youtubePlaylist.inited = true;
			}
			if(youtubePlaylist.videos && youtubePlaylist.videos.length > 0){
				var html = "";
				for(var i = 0; i<youtubePlaylist.videos.length; i++)
				{
					var item = youtubePlaylist.videos[i];
					html += "<div video-id='"+item.videoId+"' class='video-item'>";
					html += "	<span class='btn-remove-video-item'>x</span>";
					html += "	<label>"+item.title+"</label>";
					html += "</div>"
				}
				$('#qb-dlg-change-video-playlist .current-playlist').html(html);
			}
			$('#qb-dlg-change-video-playlist').dialog("open");
		},
		//ThoaiVt Search Video
		searchVideo : function(){
			 $(".qb-panel-iframe-src .form-group .form-group-content #input-video-playlist-src").autocomplete({
			        source: function(request, response) {
			        	$.ajaxSetup({
							beforeSend : function() {
							},
							complete : function() {
								setDefaultAjaxStatus();
							}
						});
			        	// YouTube Data API
			            var data = $.getJSON("http://suggestqueries.google.com/complete/search?callback=?",{ 
			                  "hl" : "en", // Language
			                  "ds" : "yt", // Restrict lookup to youtube
			                  "jsonp" : "suggestCallBack", // jsonp callback function name
			                  "q" : request.term, // query term
			                  "client" : "youtube"
			               }
			            );
			            suggestCallBack = function (data) {
			                var suggestions = [];
			                $.each(data[1], function(key, val) {
			                    suggestions.push({"value":val[0]});
			                });
			                suggestions.length = 10; // list number response
			                response(suggestions);
			            };
			            //fix z-index hidden
			            var zindex = $(this).closest('.ui-dialog').zIndex();
			            console.log(zindex);
			      },select: function(e,ui) {      
			    	  //get value autocomplete
               	  var data = ui.item.value;
               	  youtubePlaylist.youtubeShow(data);
                }
			 });
		},youtubeShow : function(data){
			$('.qb-panel-iframe-src .form-group .form-group-content .data-search-video .list-data-result').html("");
			$.ajaxSetup({
				beforeSend : function() {
				},
				complete : function() {
					setDefaultAjaxStatus();
				}
			});
			$.get("https://www.googleapis.com/youtube/v3/search?",{
				part:'snippet',
				maxResults : 20,
				q : data,
				type : "video",//search video
				key : 'AIzaSyAyuBJ6ov3ODP0f5EykToTM1aHI_V_D8jI'},
				function(data){
					$.each(data.items,function(i,item){
							var lblBy = "";
							if(item.snippet.channelTitle && item.snippet.channelTitle.length > 0)
								lblBy = "<label class='author'>"+"By: "+item.snippet.channelTitle+"</label>";
							if(item.id.videoId!=null){	//pass video not embed
							var data="<li style='cursor:pointer;' title='"+item.snippet.title+"' id-url='"+item.id.videoId+"' publishedAt='"+item.snippet.publishedAt+"' channel='"+item.snippet.channelTitle+"' description='"+item.snippet.description+"'>"+
											"<img src='"+item.snippet.thumbnails['default']['url'] +"' />"+
											"<div style='cursor:pointer;' class='panel-video-info'>"+
												"<label class='title' data-toggle='tooltip' data-placement='bottom' title='"+item.snippet.title+"'>"+item.snippet.title+"</label>"+
												 lblBy+
											"</div>"+
										  "</li>";
							$('#qb-dlg-change-video-playlist .qb-panel-iframe-src .form-group .form-group-content .data-search-video .list-data-result').append(data);
							$('[data-toggle="tooltip"]').tooltip();
						}
					})
				}
			);	
		}
	}
}());