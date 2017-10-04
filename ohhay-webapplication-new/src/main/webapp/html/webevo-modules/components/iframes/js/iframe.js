/**
 * @author ThoaiNH
 * create Oct 7, 2015
 * all iframe type js
 */
(function() {
	iframe = {
		COMP_MIN_H: 8,
		COMP_MIN_W: 8,
		iframeType : "",
		loopVideo : "",
		autoPlay : "",
		sourceData : "",
		iframeSelector : null,
		init : function() {
			
			iframe.eventListener();
		},
		eventListener: function(){
			/*
			 * event close dialog edit iframe
			 */
			$('#qb-edit-iframe-src').on('dialogclose', function(event) {
				$('#qb-edit-iframe-src .qb-panel-iframe-src .form-group-content .btn-toggle').attr('typePaste',0);
			 });
			
			/*
			 * on click edit iframe
			 */
			$(document).on('click','.content-box .grid-stack-item-content .btn-change-iframe-src',function(){
				$('.qb-panel-iframe-src #list-data-result').html("");
				//get id web
				var compId = $(this).closest('.grid-stack-item-content').attr('qb-component-id');
				//get type iframe
				var iframeType = $(this).closest('.grid-stack-item-content').attr('iframe-type');
				iframe.iframeType = iframeType;
				iframe.iframeSelector = $(this).closest('.grid-stack-item-content').find('iframe');
				iframe.sourceData = iframe.iframeSelector.attr('src');
				//get auto play from url
				iframe.autoPlay = util.getUrlParameter('autoplay',iframe.sourceData);
				//get loop play from url
				iframe.loopVideo = util.getUrlParameter('loop',iframe.sourceData);
				//check url
				if(!iframe.autoPlay || iframe.autoPlay != 1)
					iframe.autoPlay = 0;
				if(!iframe.loopVideo || iframe.loopVideo != 1)
					iframe.loopVideo = 0;
				var toogleBtn = $('.qb-panel-iframe-src .btn-toggle[type="autoplay"] button');
				toogleBtn.removeClass('btn-primary');
				toogleBtn.addClass('btn-default');
				var toogleBtnLoop = $('.qb-panel-iframe-src .btn-toggle[type="loop"] button');
				toogleBtnLoop.removeClass('btn-primary');
				toogleBtnLoop.addClass('btn-default');
				var crtoogleBtn;
				//reload edit tool
				if(iframe.autoPlay == 0)
					crtoogleBtn = $('.qb-panel-iframe-src .btn-toggle[type="autoplay"] button[data="off"]');
				else
					crtoogleBtn = $('.qb-panel-iframe-src .btn-toggle[type="autoplay"] button[data="on"]');
				crtoogleBtn.addClass('active');
				crtoogleBtn.addClass('btn-primary');
				crtoogleBtn.removeClass('btn-default');
				//fix loop
				var crtoogleBtnLoop;
				//reload edit tool
				if(iframe.loopVideo == 0)
					crtoogleBtnLoop = $('.qb-panel-iframe-src .btn-toggle[type="loop"] button[data="off"]');
				else
					crtoogleBtnLoop = $('.qb-panel-iframe-src .btn-toggle[type="loop"] button[data="on"]');
				crtoogleBtnLoop.addClass('active');
				crtoogleBtnLoop.addClass('btn-primary');
				crtoogleBtnLoop.removeClass('btn-default');
				$('.qb-panel-iframe-src #input-iframe-src').val(iframe.iframeSelector.attr('src'));
				var enableSearch = false;
				var videoOption = false;
				var topicOption = false;
				var enableTopicSearch = false;
				if(iframeType == 'normal_iframe_youtube' || iframeType == 'normal_iframe_vimeo')
				{
					enableSearch = true;
					videoOption = true;
				}else if(iframeType == "normal_iframe_topic"){
					topicOption = true;
					enableTopicSearch = true;
				}
				//open edit tool
				iframe.open({
					callBack: function(src){						
						iframe.iframeSelector.attr('src',src);
						var data  = {
							iframeType: iframeType,
							src: src
						};
						componentModelManager.updateItemField(compId,WEB_PRO_DATA,data);
					},
					enableSearch: enableSearch,
					videoOption: videoOption,
					topicOption:topicOption
				});
			});
			
			
		},
		toolEventListener : function(){
			iframe.autoComplete("youtube");
			/*
			 * on click search youtube
			 */
			$(document).on('click','#qb-edit-iframe-src #search-youtube-api',function(){
				var data = $('#qb-edit-iframe-src .input-fied-search #youtube').val();
				((iframe.iframeType == 'normal_iframe_youtube')?youtube.searchVideo(data):((iframe.iframeType == 'normal_iframe_vimeo')?vimeo.searchVideo(data):""));
			});
			var resultResearch = '';
			
			/*
			 * event paste video to source
			 * 
			 */
			$(document).on("paste","#input-iframe-src", function(e) {
				event.preventDefault();
				var urlPaster = e.originalEvent.clipboardData.getData('Text');
				//urlPaster = iframe.markUrlEmbed(urlPaster);
				var urlEmbed = iframe.markUrlEmbed(urlPaster,iframe.iframeType);
				//e.originalEvent.clipboardData.setData("Text",urlEmbed);
				$('#input-iframe-src').val(urlEmbed);
				$('#qb-edit-iframe-src .qb-panel-iframe-src .form-group-content .btn-toggle').attr('typePaste',1);
				$('#qb-edit-iframe-src .qb-panel-iframe-src .form-group-content .btn-toggle').attr('urlPaster',urlEmbed);
				//get auto play from url
				var tempAutoPlay = util.getUrlParameter('autoplay',urlPaster);
				//get loop play from url
				var tempLoopVideo = util.getUrlParameter('loop',urlPaster);
				//check url
				if(!tempAutoPlay || tempAutoPlay != 1)
					tempAutoPlay = 0;
				if(!tempLoopVideo || tempLoopVideo != 1)
					tempLoopVideo = 0;
				//fix loop
				var crtoogleBtnLoop;
				//reload edit tool
				 $('.qb-panel-iframe-src .btn-toggle[type="loop"] button').removeClass('btn-primary active');
				if(tempLoopVideo == 0){
					crtoogleBtnLoop = $('.qb-panel-iframe-src .btn-toggle[type="loop"] button[data="off"]');
				}
				else{
					crtoogleBtnLoop = $('.qb-panel-iframe-src .btn-toggle[type="loop"] button[data="on"]');
				}
				crtoogleBtnLoop.addClass('active');
				crtoogleBtnLoop.addClass('btn-primary');
				crtoogleBtnLoop.removeClass('btn-default');
				//auto play
				var crtoogleBtn;
				 $('.qb-panel-iframe-src .btn-toggle[type="autoplay"] button').removeClass('btn-primary active');
				//reload edit tool
				if(tempAutoPlay == 0)
					crtoogleBtn = $('.qb-panel-iframe-src .btn-toggle[type="autoplay"] button[data="off"]');
				else
					crtoogleBtn = $('.qb-panel-iframe-src .btn-toggle[type="autoplay"] button[data="on"]');
				crtoogleBtn.addClass('active');
				crtoogleBtn.addClass('btn-primary');
				crtoogleBtn.removeClass('btn-default');
			});
			/*
			 * on click search topic
			 */
			$(document).on('click','#qb-edit-iframe-src #search-topic-api',function(e){
				jQuery.support.cors = true;
				var otagString = $('#search_otag_topic').val();
				$.ajax({
				    url: "https://topic.oohhay.com/searchBean.searchM150",
				    data : "textSearch=" + otagString + "&fo100=" + 0,
				    type: "POST",
				    timeout: 30000,
				    dataType: "text", // "xml", "json"
				    success: function(data) {
				    	$('.input-data-topic ul').empty();
				    	data = $.parseJSON(data);
						var otagString = data.result;
						socketio.loadListSearchTopic(0,200, otagString);
						

				    },
				    error: function(jqXHR, textStatus, ex) {
				        alert(textStatus + "," + ex + "," + jqXHR.responseText);
				    }
				});
			});
			/*
			 * event toggle autoplay, loop
			 */
			$('#qb-edit-iframe-src .qb-panel-iframe-src .form-group-content .btn-toggle').click(function() {
				var dataPaster = $(this).attr('typePaste');
				if(dataPaster==0){
				$(this).find('.btn').toggleClass('active');  
			    if ($(this).find('.btn-primary').size()>0) 
			    	$(this).find('.btn').toggleClass('btn-primary');
			    $(this).find('.btn').toggleClass('btn-default');
			    var type = $(this).attr("type");
			    var src = iframe.iframeSelector.attr('src');
			    iframe.autoPlay = util.getUrlParameter('autoplay',src);
			    iframe.loopVideo = util.getUrlParameter('loop',src);
			    var newSrc = src;
			    if(type=="autoplay"){
			    	iframe.autoPlay = (iframe.autoPlay=='0' || !iframe.autoPlay)?'1':'0';
			    	newSrc = util.replaceUrlParam('autoplay', iframe.autoPlay, src);
			    }else if(type=="loop"){
			    	iframe.loopVideo = (iframe.loopVideo=='0' || !iframe.loopVideo)?'1':'0';//playlist
			    	newSrc = util.replaceUrlParam('loop', iframe.loopVideo, src);
			    }
			    iframe.callBack(newSrc);
		    	$('.qb-panel-iframe-src #input-iframe-src').val(newSrc);
				}else{
					$(this).find('.btn').toggleClass('active');  
				    if ($(this).find('.btn-primary').size()>0) 
				    	$(this).find('.btn').toggleClass('btn-primary');
				    $(this).find('.btn').toggleClass('btn-default');
				    var type = $(this).attr("type");
				    var src = $(this).attr('urlPaster');
				    var atPlay= util.getUrlParameter('autoplay',src);
				    var lpVideo = util.getUrlParameter('loop',src);
				    var newSrc = src;
				    if(type=="autoplay"){
				    	atPlay = (atPlay=='0' || !atPlay)?'1':'0';
				    	newSrc = util.replaceUrlParam('autoplay', atPlay, src);
				    }else if(type=="loop"){
				    	lpVideo = (lpVideo=='0' || !lpVideo)?'1':'0';//playlist
				    	newSrc = util.replaceUrlParam('loop',lpVideo, src);
				    }
				    $('#qb-edit-iframe-src .qb-panel-iframe-src .form-group-content .btn-toggle').attr('urlPaster',newSrc);
				    $('#input-iframe-src').val(newSrc);
				}
			});
			/*
			 * on click ressult search
			 */
			$(document).on('click','#input-data-youtube #list-data-result li',function(){
				var data=$(this).attr("id-url");
				if(iframe.iframeType == 'normal_iframe_youtube')
					iframe.sourceData = "https://www.youtube.com/embed/"+data+"?autoplay="+iframe.autoPlay+"&loop="+iframe.loopVideo+"&playlist="+$(this).attr('id-url');
				else if(iframe.iframeType == 'normal_iframe_vimeo')
					iframe.sourceData = 'https://player.vimeo.com/video'+data+"?autoplay="+iframe.autoPlay+"&loop="+iframe.loopVideo;
				else if(iframe.iframeType == 'normal_iframe_topic')
					iframe.sourceData = 'https://topic.web-evo.com/t'+data+"/demo";
				$('.qb-panel-iframe-src #input-iframe-src').val(iframe.sourceData);
				iframe.callBack(iframe.sourceData);
			});
			/*
			 * auto complete search
			 */
			$('#qb-edit-iframe-src #youtube').keypress(function (e) {
				 var key = e.which;
				 if(key == 13)  // the enter key code
				 {
					 var data = $('#qb-edit-iframe-src .input-fied-search #youtube').val();
					((iframe.iframeType == 'normal_iframe_youtube')?youtube.searchVideo(data):((iframe.iframeType == 'normal_iframe_vimeo')?vimeo.searchVideo(data):""));
				  }
				});  
			/*
			 * on click save edit iframe
			 */
			$(document).on('click','#qb-edit-iframe-src .qb-panel-iframe-src #btn-save-iframe',function(){
				var src = $('.qb-panel-iframe-src #input-iframe-src').val();
				iframe.callBack(src);
				$('#qb-edit-iframe-src').dialog("close");
				
			});
			
		},
		/*
		 * 	ThoaiVt
		 * 	auto complete
		 */
		autoComplete : function (type) {
			 $("#qb-edit-iframe-src #youtube").autocomplete({
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
			                  "client" : type
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
			      },select: function(e,ui) {      
			    	  //get value autocomplete
                  	  var data = ui.item.value;
                  	  ((iframe.iframeType == 'normal_iframe_youtube')? youtube.searchVideo(data) : ((iframe.iframeType == 'normal_iframe_vimeo')? vimeo.searchVideo(data) : ""));
                   }
			 });
		},
		/*
		 * ThoaiVt 
		 * 05/01/2016
		 */
		markUrlEmbed : function(urlWatch,typeIframe){
			if(typeIframe == "normal_iframe_empty")
				return urlWatch;
			//process input iframer
			var keyMatch ="",keySplit = "";
			var iframe = urlWatch,regEx = /(src)=["']([^"']*)["']/gi;
			regEx = /(src)=["']([^"']*)["']/gi;
			var urlTemp=null;
			iframe.replace(regEx, function(all, type, value) {
				urlTemp=value;
			});
			if(urlTemp!=null)
				return urlTemp;
			//end process input iframer
			//process input https://vimeo.com/channels/staffpicks/... or https://www.youtube.com/watch?v=....
			var el = document.createElement('a');
			el.href = urlWatch;
			var findMatch = el.pathname;
			//key match : key find url ex:https://www.youtube.com/embed or https://player.vimeo.com/video
			keyMatch = ((typeIframe=="normal_iframe_youtube") ? keyMatch="embed" : keyMatch="video");
			//url Temp : url default embed for iframe
			urlTemp = ((typeIframe=="normal_iframe_youtube") ? "https://www.youtube.com/embed/" : "https://player.vimeo.com/video");
			//key split : get url user paste from url view video
			keySplit = ((typeIframe=="normal_iframe_youtube") ? "watch" : "staffpicks");
			var match = urlWatch.search(keyMatch);
			if(match!=-1){
				if(typeIframe=="normal_iframe_youtube"){
					var urlNeed = String(urlWatch);
					if(urlNeed.search("playlist")==-1){
						var dataPl = urlNeed.substr(urlNeed.lastIndexOf("/")+1);
						urlNeed+="?playlist="+dataPl;
					}
					return urlNeed;
				}
				return urlWatch;
			}
				
			//case youtube paste https://youtu.be/bnRzpJtQf_w
			if(typeIframe=="normal_iframe_youtube"){
				if(urlWatch.indexOf("youtu.be")!=-1){
					var playList = findMatch.substring(1,findMatch.length);
					urlTemp+=playList+'?&playlist='+playList;
					return urlTemp;
				}
			}
			//parse url form key Split
			var indexChar=urlWatch.search(keySplit);
			//join url after parse
			if(indexChar!=-1){
				var stringMatch = urlWatch.substring(indexChar+keySplit.length+((typeIframe=="normal_iframe_youtube") ? 3 : 0),urlWatch.length);
				urlTemp+=stringMatch;
				if(typeIframe=="normal_iframe_youtube"){
					var urlNeed = String(urlTemp);//convert to string
					if(urlNeed.search("playlist")==-1){
						//chẹck string have playlist
						var dataPl = urlNeed.substr(urlNeed.lastIndexOf("/")+1);
						//add playlist
						urlNeed+="?playlist="+dataPl;
					}
					return urlNeed;
				}
				return urlTemp;
			}
			console.log("URL MARK : "+urlWatch);
			//check type video youtube
			if(typeIframe=="normal_iframe_youtube"){
				var urlNeed = String(urlWatch);//convert to string
				if(urlNeed.search("playlist")==-1){
					//chẹck string have playlist
					var dataPl = urlNeed.substr(urlNeed.lastIndexOf("/")+1);
					//add playlist
					urlNeed+="?playlist="+dataPl;
				}
				return urlNeed;
			}
			return urlWatch;
		}
		,
		/*
		 * add iframe
		 */
		add : function(grid, x, y, $self) {
			var widget;
			var type = $self.attr('id');
			var src;
			switch (type) {
			case "normal_iframe_empty":
				src = 'https://www.youtube.com/embed/HRGOF8YW7F4'; 
				var node = {
					x : x,
					y : y,
					width : 20,
					height : 20
				};
				widget = grid.add_widget($(buildTemplateIframe(src,type)), node.x, node.y, node.width, node.height, true);
				//update 17/11/2016 - call to action
				//widget.find('.btn-change-iframe-src').trigger('click');
				break;
			case "normal_iframe_topic":				
				src = "https://topic.bonevo.net/"+$("#qb-input-qv101").val()+"?npage=3&transparent=true&col=1&source=bonevo";
				var node = {
					x : x,
					y : y,
					width : 20,
					height : 20
				};
				widget = grid.add_widget($(buildTemplateIframe(src,type)), node.x, node.y, node.width, node.height, true);
				break;
			case "normal_iframe_youtube":
				src = 'https://www.youtube.com/embed/HRGOF8YW7F4'; 
				var node = {
					x : x,
					y : y,
					width : 20,
					height : 20
				};
				widget = grid.add_widget($(buildTemplateIframe(src,type)), node.x, node.y, node.width, node.height, true);
				//update 17/11/2016 - call to action
				//widget.find('.btn-change-iframe-src').trigger('click');
				break;
			case "normal_iframe_vimeo":
				src = 'https://player.vimeo.com/video/150988449'; 
				var node = {
					x : x,
					y : y,
					width : 20,
					height : 20
				};
				widget = grid.add_widget($(buildTemplateIframe(src,type)), node.x, node.y, node.width, node.height, true);
				//update 17/11/2016 - call to action
				//widget.find('.btn-change-iframe-src').trigger('click');
				break;
			case "normal_iframe_webinaris":
				src = 'https://webinaris.com/customer/showtime/150_thao_tac_su_dung_thu_vien_thiet_bi_tao_du_lieu_nguon/3386.html?source_id=ohay'; 
				var node = {
					x : x,
					y : y,
					width : 20,
					height : 28
				};
				widget = grid.add_widget($(buildTemplateIframe(src,type)), node.x, node.y, node.width, node.height, true);
				//update 17/11/2016 - call to action
				//widget.find('.btn-change-iframe-src').trigger('click');
				break;
			default:
				break;
			}
			//setup default data
			var data = {
				iframeType: type,
				src: src
			};
			widget[WEB_PRO_DATA] = data;
			widget.attr('iframe-type',type);
			return widget;
		},
		/*
		 * load iframe from data
		 */
		load: function(grid, node, data){
			return grid.add_widget($(buildTemplateIframe(data["src"], data["iframeType"])), node.x, node.y, node.width, node.height);
		},
		/*
		 * open edit iframe
		 */
		open: function(option){
			iframe.callBack = option.callBack;
			
			if(!iframe.inited)
			{
				iframe.toolEventListener();
				$('#qb-edit-iframe-src').webToolDialog(396);
				$('#qb-edit-iframe-src #list-data-result').niceScroll();
				iframe.inited = true;
			}
			if(option.enableSearch == false)
				$('#qb-edit-iframe-src .input-fied-search').hide();
			else
				$('#qb-edit-iframe-src .input-fied-search').show();
			if(option.videoOption == false)
				$('#qb-edit-iframe-src .input-fied-option').hide();
			else
				$('#qb-edit-iframe-src .input-fied-option').show();
			
			if(option.enableTopicSearch == false)
				$('#qb-edit-iframe-src .input-fied-search-topic').hide();
			else
				$('#qb-edit-iframe-src .input-fied-search-topic').show();
		
			$('#qb-edit-iframe-src').dialog("open");
		},
		/*
		 * append playlist to url
		*/
		
	}
}());