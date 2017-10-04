/**
 * @author ThoaiVt
 * 02/12/2015
 */
(function() {
	vimeo = {
		searchVideo : function(data) {
			$('.qb-panel-iframe-src #list-data-result').html("");
			$.ajaxSetup({
				beforeSend : function() {
				},
				complete : function() {
					setDefaultAjaxStatus();
				}
			});
			$.getJSON("https://api.vimeo.com/videos?", {
				query : data,
				per_page : 20,
				access_token : "0cdc0eaff8b81b11ab0505ee8dbc3d74"
			}, function(data) {
				console.log(data);
				$.each(data.data,function(i,data){
					var  a = document.createElement('a');
					a.href = data.link;
					var img="";
					try{
						img=data.pictures.sizes[2].link;
					}catch(e){
						console.log(e);
					}
					vimeo.fixUrlNotEmbed(data,a.pathname);
					lblBy = "<label class='author'>"+"By: "+data.user.name+"</label>";
					var data="<li style='cursor:pointer;' id-url="+a.pathname+">"+
					"<img src='"+data.pictures.sizes[2].link+"' />"+
					"<div class='panel-video-info'>"+
						"<label class='title'>"+data.name+"</label>"+lblBy+"</div>"+
					"</li>";
					$('.qb-panel-iframe-src #list-data-result').append(data);
				});
			});
		},
		/*
		 * fix url embed
		 */
		fixUrlNotEmbed : function(data,pathname){
			var dataAr = pathname.split("/");
			console.log(dataAr);
		}
	}
}());