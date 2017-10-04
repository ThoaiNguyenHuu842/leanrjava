function buildYoutubePlayListVideoItem(videoItem, index,data){
	var htmlLabel = "";
	htmlLabel += '<div index="'+index+'" class="video-item" video-id="'+videoItem.videoId+'">';
	htmlLabel += '	<img style="'+data.thumbnail.css+'" src="https://i.ytimg.com/vi/'+videoItem.videoId+'/hqdefault.jpg" />';
	htmlLabel += '		<div class="info-panel">';
	htmlLabel += '			<label class="video-title" style="'+data.title.css+'">'+videoItem.title+'</label>';
	htmlLabel += '			<label class="channel-title" style="'+data.channelTitle.css+'">'+videoItem.channelTitle+'</label>';
	htmlLabel += '		</div>';
	htmlLabel += '</div>';
	return htmlLabel;
}
function buildYoutubePlayList(data,tempBG){
	var srcDefault = "";
	var htmlLabel = "";
	if(data.videos.length > 0)
	{
		srcDefault = "https://www.youtube.com/embed/"+data.videos[0].videoId+"?autoplay="+data.auto+"&enablejsapi=1";
		for(var i = 0; i < data.videos.length; i ++){
			var item = data.videos[i];
			htmlLabel += buildYoutubePlayListVideoItem(item, i,data);
		}
	}
	var template = '';
	template += '<div class="grid-stack-item-content qb-component-youtube-playlist">';
	template += 	'<div class="qb-component '+((data.layout!=null) ? data.layout : "style1")+'"  playlist="'+data.playlist.visible+'"  player="'+data.player.visible+'" >';
	template += '			<div class="layer-background" style="' + tempBG + '"></div>';
	template += 		'<div class="content-player" style="'+data.player.css+'"><iframe id="ytplayer" current="'+0+'" class="iframe-player" width = "100%" height="100%" src="'+srcDefault+'"></iframe></div>';
	template += 		'<div class="content-label" style="'+data.playlist.css+'" content="'+(((data.title.visible)=="on" && (data.channelTitle.visible)=="on")? "on" : "off")+'" img="'+data.thumbnail.visible+'">';
	template += 		htmlLabel;
	template += 		'</div>';
	template +=	'</div>';
	template +=       	'<div class="function-panel">';
	template +=				'<div class="item btn-draggable">';
	template += 					'<i class="fa fa-arrows"></i>';
	template +=				'</div>';
	template += 				'<div class="item btn-dropdown-menu dropdown">';
	template +=           		'<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template +=           			'<label>...</label>';
	template +=           		'</button>';
	template +=           		'<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	if(web.MV922==="ADMIN")
		template += 					'<li class="btnadmin-set-tolibrary"><a><i class="fa fa-bookmark-o"></i>Add to library</a></li>';
	template +=           			'<li class="btn-change-video-list"><a><i class="fa fa-film"></i>'+getLocalize("jsypl_title1")+'</a></li>';
	template +=           			'<li class="btn-change-playlist-style"><a><i class="fa fa-pencil-square-o"></i>'+getLocalize("jsypl_title4")+'</a></li>';
	template +=           			'<li class="btn-set-background"><a><i class="fa fa-picture-o"></i>'+getLocalize("web_edit_changebg")+'</a></li>';
	template +=           			'<li class="btn-option"><a><i class="fa fa-cog"></i>'+getLocalize("weh_object_style")+'</a></li>';
	template +=          			'<li class="btn-comp-duplicate"><a><i class="fa fa-copy"></i>'+getLocalize("setting_duplicate")+'</a></li>';
	template += 					'<li class="btn-comp-copy"><a><i class="icon-display"></i>' + 'Copy (Ctrl + C)' + '</a></li>';
	template +=           			'<li class="btn-set-anchor border-top"><a ><i class="fa fa-anchor"></i><label>'+getLocalize("jsift_title2")+'</label></a></li>';
	template +=           			'<li class="btn-del border-top"><a><i class="fa fa-trash-o"></i>'+getLocalize("delete")+'</a></li>';
	template +=           		'</ul>';
	template +=				'</div>';
	template +=			'</div>';

	template += '</div>';
	return template;
}