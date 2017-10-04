/**
 * @author ThoaiVt
 * 30/11/2015
 */

(function(){
	youtube = {
		searchVideo : function(data){
			$('.qb-panel-iframe-src #list-data-result').html("");
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
							console.log(item);
							var lblBy = "";
							if(item.snippet.channelTitle && item.snippet.channelTitle.length > 0)
								lblBy = "<label class='author'>"+"By: "+item.snippet.channelTitle+"</label>";
							if(item.id.videoId!=null){	//pass video not embed
							var data="<li style='cursor:pointer;' id-url="+item.id.videoId+">"+
											"<img src='"+item.snippet.thumbnails['default']['url'] +"' />"+
											"<div class='panel-video-info'>"+
												"<label class='title'>"+item.snippet.title+"</label>"+
												 lblBy+
											"</div>"+
										  "</li>";
							$('.qb-panel-iframe-src #list-data-result').append(data);
						}
					})
				}
			);	
		}
	}
}());