/**
 * ThoaiVT
 * 29/01/2016
 */
(function(){
	playListChange = {
			/*
			 * id box require change style
			 */
			ID_BOX_DATA : 0,
			/*
			 * init
			 */
			init : function(){
				playListChange.eventListener();
			},
			/*
			 * event
			 */
			eventListener : function(){
				$(document).on('click','#qb-dlg-change-video-playlist-style .qb-panel-iframe-src .data-player .btn-change-layout-playlist',function(){
					var obj = $('.grid-stack-item-content[qb-component-id="'+playListChange.ID_BOX_DATA+'"]');
					if(obj.find('.qb-component').hasClass('style1'))
					{
						obj.find('.qb-component').removeClass('style1');
						obj.find('.qb-component').addClass('style2');
					}
					else if(obj.find('.qb-component').hasClass('style2'))
					{
						obj.find('.qb-component').removeClass('style2');
						obj.find('.qb-component').addClass('style3');
					}
					else if(obj.find('.qb-component').hasClass('style3'))
					{
						obj.find('.qb-component').removeClass('style3');
						obj.find('.qb-component').addClass('style4');
					} 
					else if(obj.find('.qb-component').hasClass('style4'))
					{
						obj.find('.qb-component').removeClass('style4');
						obj.find('.qb-component').addClass('style5');
					}
					else
					{
						obj.find('.qb-component').removeClass('style5');
						obj.find('.qb-component').addClass('style1');
					}
					
					//get all class .qb-component
					var arrayListStyle = obj.find('.qb-component').attr("class").split(' '),classLayout;
					for (data in arrayListStyle) {
						var i=1;
						while(i<=10){//limited 10 style1,style2,...,style10
							if(arrayListStyle[data]=="style"+i){
								classLayout=arrayListStyle[data];
								break;
							}
							i++;
						}
					}
					console.log(classLayout+" NAME CLASS");
					playListChange.setDataField(playListChange.ID_BOX_DATA,classLayout,"layout");
				});
				
				//event open object style
				$(document).on('click','#qb-dlg-change-video-playlist-style .qb-panel-iframe-src .form-group .data-form .checkbox .item-funct .object-style,#qb-dlg-change-video-playlist-style .qb-panel-iframe-src .form-group .playlist-change .item-style .item-funct .object-style',function(){
					var type = $(this).parent().parent().attr("type");
					var ob = playListChange.getPlayListRequire(playListChange.ID_BOX_DATA,type);
					cp_Option.open({
						self : ob,callBack : function(result) {
							result.style === 'rotate' ? webUtils.setTransformRotate(ob, result.value) : ob.css(result.style, result.value);
							playListChange.setDataField(playListChange.ID_BOX_DATA, ob,type);
						},del : ['#cp-ot-border-inner']});
				});

				//event open change text style
				$(document).on('click','#qb-dlg-change-video-playlist-style .qb-panel-iframe-src .form-group .playlist-change .item-style .item-funct .color-pick',function(){
					var type = $(this).parent().parent().attr("type");
					editText.open({
						callBack: function(result){
							console.log(result.key+" : "+result.value)
							var element = playListChange.getPlayListRequire(playListChange.ID_BOX_DATA,type);
							element.css(result.key, result.value);//set style element require
							//call function update data
							playListChange.setDataField(playListChange.ID_BOX_DATA, element,type);
						}
					});
				});
				//event change background
				$(document).on('click','#qb-dlg-change-video-playlist-style .qb-panel-iframe-src .form-group .playlist-change .item-style .item-funct .bg-color-pick',function(){
					var type = $(this).parent().parent().attr("type");
					console.log("Data type : "+type);
					colorPicker.open({
						callBack : function(result) {
							console.log(result.data);
							$('.qb-component-youtube-playlist').each(function( index ) {
								var idComp = $(this).attr('qb-component-id');//get id box
								if(idComp == playListChange.ID_BOX_DATA){//find box === box click change style
									webUtils.setBackgroundColor(playListChange.getPlayListRequire(playListChange.ID_BOX_DATA,type), result)
									playListChange.setDataField(playListChange.ID_BOX_DATA, playListChange.getPlayListRequire(playListChange.ID_BOX_DATA,type),type);
								}
							});
					}});
				});
			 //event change display content 
				$(document).on('click','#qb-dlg-change-video-playlist-style .qb-panel-iframe-src .form-group .data-form .checkbox .item-style,#qb-dlg-change-video-playlist-style .qb-panel-iframe-src .form-group .playlist-change .item-style .checkbox',function(){
					//get type need change display
					var type =  $(this).parent().attr("type");
					console.log("Data type : "+type);
					//get status checkbox
					var checked = $(this).find("label .cr .cr-icon").css("opacity");
					var dataValue = ((checked==1)? "off" : "on");
					checked = ((checked==1)? "none" : "block");
					console.log("Type "+type+" display : "+checked);
					//set element change
					var element = playListChange.getPlayListRequire(playListChange.ID_BOX_DATA,type);
					//apply change
					console.log("CHANGE!!!");
					element.css("display",checked);
					playListChange.switchElementData(element,dataValue,type);
					//update field
					playListChange.setDataField(playListChange.ID_BOX_DATA, element,type);
				});
				
			},
			/*
			 * set css width element i  playlist
			 */
			switchElementData : function(element,value,type){
				console.log("DATA VALUE  : "+value);
				if(type=="player" || type=="playlist"){
					element.parent().attr(type,value);
				}else if(type=="title" || type=="title-channel" || type=="thumbnail"){
					type = ((type=="thumbnail")? "img" : "content");
					if(type!="img"){
						var data=element.parent().find(".video-title").css("display");
						var data1=element.parent().find(".channel-title").css("display");
						var valueX=((data=="none" && data1=="none") ? "off" : "on");
						element.parent().parent().parent().attr(type,valueX);
					}
					else
						element.parent().parent().attr(type,value);
				}
			},
			/*
			 * switch change
			*/
			switchType : function(type){
				type=type.trim();
				if(type=="title")
					return ".qb-component .content-label .video-item .info-panel .video-title";
				else if(type=="title-channel")
					return ".qb-component .content-label .video-item .info-panel .channel-title";
				else if(type=="thumbnail")
					return ".qb-component .content-label .video-item img";
				else if(type=="playlist")
					return ".qb-component .content-label";
				else if(type=="player")
					return ".qb-component .content-player";
			},
			/*
			 * set field data
			 */
			setDataField : function(id,element,type){
				//get data page
				var data = componentModelManager.listComponent[id][WEB_PRO_DATA];
				if(type!="layout"){
					//get data require by type
					var update = ((type=="title") ? data.title : ((type=="title-channel")? data.channelTitle : ((type=="thumbnail") ? data.thumbnail : ((type=="playlist") ? data.playlist : data.player))));
					//set css
					update.css = element.attr("style");
					update.visible = ((element.css("display")=="none"? "off" : "on"));
				}else
					data.layout = element;
				//update data
				componentModelManager.updateItemField(id, WEB_PRO_DATA, data);
			}
			/*
			 * find playlist require change
			 */
			,
			getPlayListRequire :  function(id,type){
				var data;
				$('.qb-component-youtube-playlist').each(function( index ) {
					var idComp = $(this).attr('qb-component-id');//get id box
					if(idComp == id){//find box === box click change style
						data=$(this).find(playListChange.switchType(type));
					}
				});
				return data;
			}
			,/*
				* open 
				*/
			open :  function(idBoxChange){
					//check playListChange been inited ?
					if(!playListChange.initedbtnChange)
					{
						//set event 
						playListChange.eventListener();
						//set size
						$('#qb-dlg-change-video-playlist-style').webToolDialog(300);
						//set flag after first time inited
						playListChange.initedbtnChange = true;
					}
					//set id box
					playListChange.ID_BOX_DATA = idBoxChange;
					//open
					$('#qb-dlg-change-video-playlist-style').dialog("open");
			}
	}
}());