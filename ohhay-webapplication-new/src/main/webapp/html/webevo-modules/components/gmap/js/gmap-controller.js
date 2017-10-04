/**
 * @author ThoaiVt
 * create 31/10/2015
 */
(function() {
	gmapController = {
		COMP_MIN_H: 8,
		COMP_MIN_W: 8,
		crCompId : 0,
		inited : false,
		addEvent : false,
		/*
		 * open tool
		 */
		open : function(){
			//init when first call
			if(!gmapController.inited)
			{
				$('#qb-option-gmap').webToolDialog(645);
				gmapController.inited = true;
			}
			$('#qb-option-gmap').dialog("open");
		},
		/*
		 * close tool
		 */
		close : function(){
			 $('#qb-option-gmap').dialog("close");
		},
		/*
		 * Add gmap
		 */
		add : function(grid, x, y, $self) {
			var node = {
				x : x,
				y : y,
				width : 48,
				height : 24
			};
			var widget = grid.add_widget($(buildTemplateGmap($self.html())),
					node.x, node.y, node.width, node.height, true);
			var myLatLng = new google.maps.LatLng(10.779401, 106.680438);
			var data = {
					zoom : 16,
					scrollwheel : false,
					center : myLatLng,
					lat :  10.779401,
					lng : 106.680438,
					color : '#0000ff',
					mapTypeId : "terrain",
					markers : []
			};
			widget[WEB_PRO_DATA] = data;
			gmap.initialize();
			//update 17/11/2016 - call to action
			/*setTimeout(function(){
				widget.find('.btn-change-option-gmap-src').trigger('click');
			}, 1000);*/
			return widget;
		},
		/*
		 * Load Gmap
		 */
		load : function(grid, node, data, compId) {
			var widget = grid.add_widget($(buildTemplateGmap("")), node.x,
					node.y, node.width, node.height);
			widget.attr('qb-component-id', compId);
			gmap.loadInitGmap(data, 0, 0);
			$('#qb-option-gmap .gmap-option #qb-panel-gmap-zoom').html(data.zoom + "x");
			$('#qb-option-gmap .gmap-option #qb-slider-gmap').slider({
				max : 25,
				value : data.zoom
			});
			return widget;
		},
		/*
		 * setup tool
		 */
		init : function() {
			gmapController.eventListener();
		},
		/*
		* event on Gmap
		*/
		eventListener : function() {
			//toggle class add markers
			$(document).on('click','#qb-google-map .btn-change-option-gmap-src',function() {
				console.log("Click edit map");
				//Load image type GMAP 
				var array=['/html/images/gmap/terrian.png','/html/images/gmap/normal.png','/html/images/gmap/satellite.png','/html/images/gmap/hybrid.png']
				$('#qb-type-map .qb-type-map').each(function(index){$(this).attr('src',util.contextPath()+array[index]);});
				//find id for Component
				crCompId = $(this).closest(".grid-stack-item-content").attr("qb-component-id");gmap.idWEB = crCompId;
				if(gmapController.addEvent==false){		
					//add flag marker 1 = add 0 = edit
					gmap.modeMarker=1;
					//set value default input by default = ""
					$('#qb-option-gmap .gmap-option #markerGmap #iputlabelMap,#qb-option-gmap .gmap-option #markerGmap #inputFieldAutocompleteAddress').val("");
					//find GMAP had selected
					gmap.selectorGmap = gmap.findIdInMapArray($(this).attr("get-map"),gmap.ARRAY_MAP);
					//find DATA GMAP had selected
					gmap.dataMapSelect = gmap.findIdInMapArray($(this).attr("get-map"),gmap.ARRAY_DATAMAP);
					//Load list marker for GMAP
					gmap.loadListMarkes(gmap.dataMapSelect.markers);
					$('#qb-type-map .qb-type-map').each(function(index){
						(($(this).attr("type-map")==gmap.dataMapSelect.mapTypeId)? $(this).addClass("gmap-selected-type-map") : "");
					});
					//set value default while onclick add marker
					$(document).on('click','#qb-option-gmap .gmap-option #markerGmap .input-fied .btn-add-marker',function(){
						//set value default
						$('#qb-option-gmap .gmap-option #markerGmap #iputlabelMap,#qb-option-gmap .gmap-option #markerGmap #inputFieldAutocompleteAddress').val("");
						if(gmap.modeMarker==1)
							((($('#qb-option-gmap .gmap-option #markerGmap .input-fied .add-marker').toggleClass("marker-for-map-hide")).hasClass('marker-for-map-hide')==true)? $('#qb-option-gmap .gmap-option #markerGmap .input-fied .add-marker').fadeIn(400) : $('#qb-option-gmap .gmap-option #markerGmap .input-fied .add-marker').fadeOut(400));
						//turn on mode add marker	
						gmap.modeMarker = 1;
					});
					//event input file change image file for marker
					document.getElementById('filePicker').addEventListener('change', gmap.handleFileSelect, false);
					//event double change image marker
					$(document).on('click','.gmap-option .qb-gmap-option-body .option-gmap .add-marker #img_Data',function(){
						$('#qb-option-gmap .gmap-option #filePicker').trigger('click');
						console.log("Click change image ");
					});
					//event click button save markers
					$(document).on('click','#qb-option-gmap .gmap-option .qb-gmap-option-body .option-gmap .add-marker .btn-save-marker',function(){
						gmap.getLocationForMap();
					});
					
					//event select list markers
					$(document).on('click','#list-marker-gmap nav .list-markers .row-markers',function(){
						//go to marker
						var index=gmap.dataMapSelect.markers[$(this).attr("marker-id")];//get marker selected
						var myLatlng = new google.maps.LatLng(index['lat'],index['lng']);//get lat and lng marker selected
						gmap.selectorGmap.mapData.setCenter(myLatlng);//go to marker
					});

					//event edit markers
					$(document).on('click','#list-marker-gmap #btn-edit-markers-gmap',function() {
						gmap.modeMarker = 0;
						try{
							var indexMarker=$(this).attr('markers-id');
							gmap.markersIndex=indexMarker;//get index marker want edit
							gmap.modeMarker=0;//turn on mode Edit Gmap
							gmap.loadListMarkes(gmap.dataMapSelect.markers);//load list GMap choose
							gmap.initializeAutoComplete();//auto complete
							//set icon for marker selected
							$('#qb-option-gmap #img_Data').css({'background':'url('+gmap.dataMapSelect.markers[indexMarker].iconUrl+')',"background-size":"100% 100%"});
							//set src image
							$('#qb-option-gmap #img_Data').attr("src",gmap.dataMapSelect.markers[indexMarker].iconUrl);
							//set title for marker selected
							$('#qb-option-gmap .gmap-option #iputlabelMap').val(gmap.dataMapSelect.markers[indexMarker].title);
							//set address for marker selected
							$('#qb-option-gmap .gmap-option #inputFieldAutocompleteAddress').val(gmap.dataMapSelect.markers[indexMarker].address);
							}catch(s){
								console.log(s);
							}
						});
						
						//event click delete markers
						$(document).on('click','#list-marker-gmap #btn-delete-markers-gmap',function(){
							//remove listener 
							var indexMarker=$(this).attr('markers-id');
							//var r=confirm("Are you sure Delete Markers!");
							util.confirmDialog(getLocalize("jsgmp_title2"), function () {
								gmap.markersIndex=indexMarker;
								gmap.deleteMarkers(gmap.markersIndex);
								gmap.loadListMarkes(gmap.dataMapSelect.markers);
								return;
							});
						});
						
						//Add event change Option map
						// set Type MAP
						$(document).on('click','#qb-option-gmap .qb-type-map',function() {
							$('#qb-option-gmap .qb-type-map').each(function(){
								$(this).removeClass("gmap-selected-type-map");
							});
							$(this).addClass("gmap-selected-type-map");
							//set Type with Index Map
							gmap.gmapChangeStyle(gmap.selectorGmap, null, null,$(this).attr('type-map'));
							//update data map Select
							gmap.updateDataMap(gmap.selectorGmap.mapData,gmap.selectorGmap.id);
							//update data save
							componentModelManager.updateItemField(crCompId,WEB_PRO_DATA, gmap.dataMapSelect);
						});
						// set Zoom MAP
						$(".ui-slider-handle").hover(function() {
							$(this).addClass("ui-state-hover");
						}, function() {
							$(this).removeClass("ui-state-hover");
						});
						//event slider change zoom map
						$('#qb-option-gmap .gmap-option #qb-slider-gmap').mousemove(function(event) {
							$(function() {
								$('#qb-option-gmap .gmap-option #qb-slider-gmap').slider(
								{
									max : 25,slide : function(event,ui) {
										$('#qb-option-gmap .gmap-option #qb-panel-gmap-zoom').html(ui.value+ "x");
										//set Zoom
										gmap.gmapChangeStyle(gmap.selectorGmap,null,ui.value,null);
										//update data map Select
										gmap.updateDataMap(gmap.selectorGmap.mapData,gmap.selectorGmap.id);
										//update list Object
										componentModelManager.updateItemField(crCompId,WEB_PRO_DATA, gmap.dataMapSelect);
									}
								});
							});
						});
						// change color
						$(document).on('click','.qb-color-gmap',function() {
								colorPicker.open({
								callBack : function(result) {
								var data=gmap.rgbToHex(result.data);
								styleRF = [ {
										featureType : 'all',
										stylers : [ {
										hue : gmap.rgbToHex(result.data)}]
								}];
								$('.gmap-option .qb-gmap-option-body #color-data-map').css("background",data);
								gmap.gmapChangeStyle(gmap.selectorGmap,styleRF, null, null);
								//need fix
								gmap.dataMapSelect.color = gmap.rgbToHex(result.data);
								//need fix
								componentModelManager.updateItemField(crCompId,WEB_PRO_DATA, gmap.dataMapSelect);
						}});
						});
						
						gmapController.addEvent=true;
				}else{
					//find GMAP had selected
					gmap.selectorGmap = gmap.findIdInMapArray($(this).attr("get-map"),gmap.ARRAY_MAP);
					//find DATA GMAP had selected
					gmap.dataMapSelect = gmap.findIdInMapArray($(this).attr("get-map"),gmap.ARRAY_DATAMAP);
					//Load list marker for GMAP
					gmap.loadListMarkes(gmap.dataMapSelect.markers);
					
				}
				gmap.initializeAutoComplete();// init Autocomplete
				gmapController.open();
				$('.gmap-option .qb-gmap-option-body #color-data-map').css('background',gmap.dataMapSelect.color);
				$('#qb-type-map .qb-type-map').each(function(index){
					$(this).removeClass("gmap-selected-type-map");
				});
				$('#qb-type-map .qb-type-map').each(function(index){
					(($(this).attr("type-map")==gmap.dataMapSelect.mapTypeId)? $(this).addClass("gmap-selected-type-map") : "");
				});
				$('#qb-option-gmap #iputlabelMap,#qb-option-gmap #inputFieldAutocompleteAddress').val("");
				$('#qb-option-gmap #img_Data').removeAttr('style');
			});
		}}
}());