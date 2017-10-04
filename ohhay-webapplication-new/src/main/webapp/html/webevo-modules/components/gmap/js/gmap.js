/*
 * @author Thoaivt
 * create 31/10/2015
 * 
 */
(function() {
	gmap = {
		ARRAY_MAP : [],// Array Golbal Map not save
		ARRAY_DATAMAP : [],// Array Golbal Map save Database
		newMAP : 0,// element had drag new to map
		selectorGmap : {},// element have been click edit
		dataMapSelect : {},// data map selected
		idWEB : 0,
		modeMarker : -1,// 0 edit markers , 1 add markers
		markersIndex : -1,
		markerDelete : new google.maps.Marker(),// save markers delete
		init : function() {
			this.eventListener();
		},
		eventListener : function() {

		},
		/*
		 * ThoaiVt create date 05/11/2015 init Gmap
		 */
		initialize : function() {// test load map multiple from database
			var myLatLng = new google.maps.LatLng(10.779401, 106.680438);
			var mapOptions = {
				id : 'map-canvas-' + gmap.newMAP,
				zoom : 16,
				scrollwheel : false,
				center : myLatLng,
				lat : 10.779401,
				lang : 106.680438,
				color : '#0000ff',
				mapTypeId : "terrain",
				markers : []
			};// set Option Map
			var map = new google.maps.Map(document.getElementById('map-canvas-' + gmap.newMAP), mapOptions);
			map.set('styles', [ {
				featureType : 'all',
				stylers : [ {
					hue : mapOptions.color
				} ]
			} ]);
			var obj = {
				id : 'map-canvas-' + gmap.newMAP,
				mapData : map,
			};// create object map add save map to ARRAY_MAP
			$('#qb-option-gmap .gmap-option #qb-panel-gmap-zoom').html(mapOptions.zoom + "x");
			$('#qb-option-gmap .gmap-option #qb-slider-gmap').slider({
				max : 25,
				value : mapOptions.zoom
			});

			gmap.ARRAY_DATAMAP.push(mapOptions);
			gmap.ARRAY_MAP.push(obj);
			$(window).resize(function() {
				// resize map with mouse resize
				google.maps.event.trigger(map, "resize");
			});
			google.maps.event.addListener(map, 'dragend', function() {
				console.log("id map : " + map.id);
				var lat = map.getCenter().lat();
				var lng = map.getCenter().lng();
				console.log(map.getCenter().lat() + " / " + map.getCenter().lng());
				var crCompId = $("#" + map.id).closest(".grid-stack-item-content").attr("qb-component-id");
				console.log(crCompId);
				mapOptions['lat'] = lat;
				mapOptions['lang'] = lng;
				componentModelManager.updateItemField(crCompId, WEB_PRO_DATA, mapOptions);
				console.log("map : " + JSON.stringify(mapOptions));
			});

		},
		updateValueAllMap : function(listData) {
			for (var i = 0; i < gmap.ARRAY_DATAMAP.length; i++) {
				if (gmap.checkExistMap(listData, gmap.ARRAY_DATAMAP[i].id) == false) {
					console.log("Remove id : " + gmap.ARRAY_DATAMAP[i].id);
					gmap.ARRAY_DATAMAP.splice(i, 1);
					gmap.ARRAY_MAP.splice(i, 1);
				}
			}
		},
		checkExistMap : function(listData, name) {
			for (var i = 0; i < listData.length; i++) {
				if (listData[i] == name)
					return true;
			}
			return false;
		},
		/*
		 * author ThoaiVt Load init Gmap author ThoaiNH update 10/10/2015 fix
		 * bug map not load full div
		 */
		loadInitGmap : function(dataGmap, mode, editMap) {
			// load map from database (single)
			if (!dataGmap) {
				dataGmap = {
					color : "#0000ff",
					zoom : 16,
					mapTypeId : "terrain",
					markers : [],
					lat : 10.779401,
					lng : 106.680438
				};
			}
			console.log(dataGmap['lat'] + "/ /" + dataGmap['lang']);
			var myLatLng = new google.maps.LatLng(((dataGmap['lat']) ? dataGmap['lat'] : 10.779401), ((dataGmap['lang']) ? dataGmap['lang'] : 106.680438));// set
			// position
			// map
			// set Option Map
			var mapOptions = {
				id : 'map-canvas-' + gmap.newMAP,
				zoom : dataGmap.zoom,
				scrollwheel : false,
				center : myLatLng,
				mapTypeId : dataGmap.mapTypeId,
				lat : dataGmap['lat'],
				lang : dataGmap['lng']
			};

			console.log(mapOptions.zoom + "ZOOM LOAD");

			// init map and check map
			var map = ((editMap == 0) ? new google.maps.Map(document.getElementById('map-canvas-' + gmap.newMAP), mapOptions) : gmap.selectorGmap.dataMap);
			if (editMap == 0) {
				map.set('styles', [ {
					featureType : 'all',
					stylers : [ {
						hue : dataGmap.color
					} ]
				} ]);
			}
			try {
				if(mapOptions.lat!==undefined && mapOptions.lang!==undefined){
					var mapGp = {lat:mapOptions.lat, lng:mapOptions.lang}
					console.log(mapGp);
					map.setCenter(mapGp);
				}
				
			} catch (e) {

			}

			// Load set all markers on Gmap
			var obj = {
				id : ((editMap == 0) ? 'map-canvas-' + gmap.newMAP : dataGmap.id),
				mapData : map,
				markers : [],
				windowInfo : []
			}
			var objData = {
				id : 'map-canvas-' + gmap.newMAP,
				zoom : dataGmap.zoom,
				color : dataGmap.color,
				mapTypeId : dataGmap.mapTypeId,
				markers : [],
				lat : dataGmap['lat'],
				lang : dataGmap['lng']
			};// create object map add save map to ARRAY_MAP
			if (editMap == 1) {
				gmap.markerDelete.setMap(null);
			}
			var listMarker = [];
			for (var i = 0; i < dataGmap.markers.length; i++) {
				console.log(dataGmap.markers[i]);
				var myLatlng = new google.maps.LatLng(dataGmap.markers[i]['lat'], dataGmap.markers[i]['lng']);
				// Icon Marker
				var image = (dataGmap.markers[i].iconUrl != null ? new google.maps.MarkerImage(dataGmap.markers[i].iconUrl, null, null, null, new google.maps.Size(64, 64)) : null);
				var marker = new google.maps.Marker({
					position : myLatlng,
					map : map,
					icon : ((dataGmap.markers[i].iconUrl && dataGmap.markers[i].iconUrl.length) > 0 ? image : null),
					title : dataGmap.markers[i].title
				});
				listMarker.push(marker);
				var infowindow = new google.maps.InfoWindow({
					content : '<div id="marker-show-address-' + i + '">' + dataGmap.markers[i].title + '</div>'
				});
				infowindow.open(map, marker);
				obj.windowInfo.push(infowindow);
				// json Marker
				if (editMap == 0) {
					obj.markers.push(marker);
					var jsonMarker = {
						lat : marker.position.lat(),
						lng : marker.position.lng(),
						iconUrl : dataGmap.markers[i].iconUrl,
						title : marker.title,
						address : dataGmap.markers[i].address
					}
					objData.markers.push(jsonMarker);
				}
			}

			if (editMap == 0) {
				gmap.ARRAY_DATAMAP.push(objData);
				gmap.ARRAY_MAP.push(obj);
				// resize map with mouse resize
				$(window).resize(function() {
					google.maps.event.trigger(map, "resize");
				});
				/*
				 * ThoaiNHL: fix bug map not load full div
				 */
				google.maps.event.addListenerOnce(map, 'idle', function() {
					google.maps.event.trigger(map, 'resize');
				});
				google.maps.event.addListener(map, 'dragend', function() {
					// TEST
					var lat = map.getCenter().lat();
					var lng = map.getCenter().lng();
					var crCompId = $("#" + map.id).closest(".grid-stack-item-content").attr("qb-component-id");
					console.log(crCompId + " : " + lat + "/ : " + lng + "/ ");
					dataMapSelectR = gmap.findIdInMapArray(map.id, gmap.ARRAY_DATAMAP);
					dataMapSelectR['lat'] = lat;
					dataMapSelectR['lang'] = lng;
					console.log("NEXT 2 " + JSON.stringify(dataMapSelectR));
					componentModelManager.updateItemField(crCompId, WEB_PRO_DATA, dataMapSelectR);
					// TEST
				});
				setTimeout(function() {
//					map.setCenter(myLatLng);
				}, 1000);
			}
		},
		onErorrImageMarker : function(index) {
			$("#list-marker-gmap .item-marker .data-item-img-marker #img-marker-gmap-item" + index).parent().css({
				"display" : "table"
			});
			$("#list-marker-gmap .item-marker .data-item-img-marker #img-marker-gmap-item" + index).css({
				'margin-top' : '10px',
				'margin-left' : '17px',
				'width' : 'auto',
				'height' : 'auto'
			});
			$("#list-marker-gmap .item-marker .data-item-img-marker #img-marker-gmap-item" + index).attr('src', util.contextPath() + '/html/images/gmap/no-image-marker.png');
		},
		/*
		 * markers load list
		 */
		loadListMarkes : function(listMarkers) {
			$('#list-marker-gmap').html("");
			if (listMarkers.length > 0) {
				for (var i = 0; i < listMarkers.length; i++) {
					$('#list-marker-gmap').append('<li class="row item-marker" style="margin-right:0;margin-left:0;">' + '<div class="col-sm-2 data-item-img-marker">' + '<div class="item" style="width:72px;height:72px;margin-left:5px;"><img class="img-marker" onerror="gmap.onErorrImageMarker(' + i + ')" id="img-marker-gmap-item' + i + '" style="width:100%;height:100%;" src="' + listMarkers[i].iconUrl + '" /></div>' + '</div><div class="col-sm-7 data">' + '<p class="title">' + listMarkers[i].title + '</p><p class="data">' + listMarkers[i].address + '</p>' + '</div><div class="col-sm-3"><div class="function-option-map" style="float:right"><i class="fa fa-pencil" markers-id="' + i + '" id="btn-edit-markers-gmap" get-map="' + gmap.selectorGmap.id + '" style="color:#a9a9a9"></i><i markers-id="' + i + '" class="fa fa-trash-o" id="btn-delete-markers-gmap" get-map="' + gmap.selectorGmap.id + '" style="color:#a9a9a9;margin-right:8px"></i></div></div>' + '</li>');
				}
			}else
				$('#list-marker-gmap').html("<p class='gmap-marker-nodata'>"+getLocalize("no_data")+"</p>");
		},
		/*
		 * Update slider
		 */
		updateSlider : function(data) {
			$('#qb-option-gmap .gmap-option #qb-panel-gmap-zoom').html(data + "x");
			$('#qb-option-gmap .gmap-option #qb-slider-gmap').slider({
				max : 25,
				value : data
			});
		},
		/*
		 * ThoaiVt create date 06/11/2015 Change style Gmap
		 */
		gmapChangeStyle : function(indexMap, styleObj, zoom, typemap) {
			if (styleObj != null)
				indexMap.mapData.set('styles', styleObj);
			if (zoom != null)
				indexMap.mapData.setZoom(zoom);
			if (typemap != null) {
				indexMap.mapData.setMapTypeId(typemap);
			}
		},
		// find ID Map selected
		findIdInMapArray : function(name, mapData) {
			for (var i = 0; i < mapData.length; i++) {
				// return map issus
				if (mapData[i].id === name)
					return mapData[i];
			}
			return null;
		},
		// function update MapData
		updateDataMap : function(gmapMap, idGmap) {
			var dataForMap = gmap.findIdInMapArray(idGmap, gmap.ARRAY_DATAMAP);
			dataForMap.zoom = gmapMap.getZoom();
			dataForMap.mapTypeId = gmapMap.getMapTypeId();
		},
		/*
		 * ThoaiVt create date 06/11/2015 get init Map
		 */
		initializeAutoComplete : function() {
			autocompleteEditor = new google.maps.places.Autocomplete((document.getElementById('inputFieldAutocompleteAddress')), {
				types : [ 'geocode' ]
			});
			google.maps.event.addListener(autocompleteEditor, 'place_changed', function() {
			});
		},
		/*
		 * delete markers
		 */
		deleteMarkers : function(indexMarker) {
			gmap.markerDelete = gmap.selectorGmap.markers[indexMarker];
			// delete data for marker seleted delete
			console.log("FIX : " + JSON.stringify(gmap.dataMapSelect.markers[indexMarker]));
			gmap.dataMapSelect.markers.splice(indexMarker, 1);
			gmap.selectorGmap.markers.splice(indexMarker, 1);
			console.log("Array marker " + JSON.stringify(gmap.dataMapSelect.markers) + "SIZE : " + gmap.dataMapSelect.markers.length);
			$("#content-wrapper .content-box .qb-component-gmap").each(function() {
				var compId = $(this).attr('qb-component-id');
				if (gmap.idWEB == compId) {
					gmap.loadInitGmap(gmap.dataMapSelect, 0, 1);
					componentModelManager.updateItemField(crCompId, WEB_PRO_DATA, gmap.dataMapSelect);
					return;
				}
			});
		},
		/*
		 * ThoaiVt create date 06/11/2015 get Location Map
		 */
		getLocationForMap : function() {
			var geocoder = new google.maps.Geocoder();
			var address = document.getElementById('inputFieldAutocompleteAddress').value;
			geocoder.geocode({
				'address' : address
			}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					// get lat and lng for inputFieldAutocomplete
					var myLatlng = new google.maps.LatLng(results[0].geometry.location.lat(), results[0].geometry.location.lng());
					// get src image
					var image = $('#qb-option-gmap .gmap-option #img_Data').attr('src');
					// create marker with add autocomplete input
					var marker = new google.maps.Marker({
						position : myLatlng,
						map : ((gmap.modeMarker == 0) ? gmap.selectorGmap.mapData : ""),
						icon : ((image && image.length > 0) ? new google.maps.MarkerImage(image, null, null, null, new google.maps.Size(64, 64)) : null),
						title : $('#iputlabelMap').val()
					});
					// check conflict marker
					var checkConfigMaker = gmap.configMarker(gmap.dataMapSelect, marker);
					try {
						if (checkConfigMaker == true)
							marker.setMap(gmap.selectorGmap.mapData);
						else
							return;
					} catch (e) {
						// TODO: handle exception
						console.log(e);
					}
					// open set value for window info
					var infowindow;
					infowindow = ((gmap.modeMarker == 1) ? new google.maps.InfoWindow({
						content : $('#iputlabelMap').val()
					}) : gmap.selectorGmap.windowInfo[gmap.markersIndex]);
					// set window info title
					infowindow.setContent($('#iputlabelMap').val());
					if (!gmap.selectorGmap.windowInfo)
						gmap.selectorGmap['windowInfo'] = [];
					// mode add push new marker to array markers
					((gmap.modeMarker == 1 && checkConfigMaker == true) ? gmap.selectorGmap.windowInfo.push(infowindow) : "");
					// set center marker seleted
//					gmap.selectorGmap.mapData.setCenter(myLatlng);
					// fix zoom map
					((checkConfigMaker == false) ? gmap.selectorGmap.mapData.setZoom(gmap.selectorGmap.mapData.getZoom() - 1) : "");
					// update value zoom
					gmap.dataMapSelect['zoom'] = gmap.selectorGmap.mapData.getZoom();
					// conflict not open change window info
					if (checkConfigMaker == true)
						infowindow.open(gmap.selectorGmap.mapData, marker);
					// change image if update
					if (gmap.modeMarker == 0) {
						((image && image.length > 0) ? gmap.dataMapSelect.markers[gmap.markersIndex]['iconUrl'] = image : "");
						((image && image.length > 0) ? $('#qb-option-gmap .gmap-option #img-marker-gmap-item' + gmap.markersIndex).attr('src', image) : "");
						// update data markers
						componentModelManager.updateItemField(crCompId, WEB_PRO_DATA, gmap.dataMapSelect);
					}
					// set value default
					$('#qb-option-gmap #iputlabelMap,#qb-option-gmap #inputFieldAutocompleteAddress').val("");
					$('#qb-option-gmap #img_Data').removeAttr('style src');
					$("#" + gmap.selectorGmap.id + " #marker-show-address-" + gmap.markersIndex + "").html();
					// set flag add
					// set lat and lang
					if (gmap.modeMarker == 1) {
						var lat = gmap.selectorGmap.mapData.getCenter().lat();
						var lang = gmap.selectorGmap.mapData.getCenter().lng();
						gmap.dataMapSelect['lat'] = lat;
						gmap.dataMapSelect['lang'] = lang;
//						gmap.selectorGmap.mapData.setCenter(myLatlng);
					}
					gmap.modeMarker = 1;
				}
			});
		},
		/*
		 * change Field map
		 */
		handleFileSelect : function(evt) {
			var files = evt.target.files;
			var file = files[0];
			if (files && file) {
				var reader = new FileReader();
				reader.onload = function(readerEvt) {
					var binaryString = readerEvt.target.result;
					gmap.saveCropbox(btoa(binaryString), gmap.idWEB);
				};
				reader.readAsBinaryString(file);
			}
		},
		/*
		 * add marker
		 */
		configMarker : function(gmapIndex, marker) {
			// check conflict marker
			var checkFlict = gmap.checkConflict(gmapIndex, marker, gmap.markersIndex, gmap.modeMarker);
			console.log("MYCHECK CONFLICT : " + checkFlict);
			if (checkFlict == true) {
				// alert("Conflict Marker ");
				util.messageDialog(getLocalize("jsgmp_title1"));
				return false;
			} else {
				console.log("No conflict !");
				var urlicon = $('#qb-option-gmap .gmap-option #img_Data').attr('src');
				var titleName = $('#iputlabelMap').val();
				var address = $('#inputFieldAutocompleteAddress').val();
				var myLatlng = new google.maps.LatLng(marker.position.lat(), marker.position.lng());
				var jsonMarker = {
					lat : marker.position.lat(),
					lng : marker.position.lng(),
					title : titleName,
					address : address,
					iconUrl : urlicon
				}
				if (gmap.modeMarker == 1) {// mode add
					// push new data marker to map
					gmap.dataMapSelect.markers.push(jsonMarker);
					// insert marker new to list marker for map
					if (!gmap.selectorGmap.markers)
						gmap.selectorGmap["markers"] = [];
					gmap.selectorGmap.markers.push(marker);
					gmap.loadListMarkes(gmap.dataMapSelect.markers);
					componentModelManager.updateItemField(crCompId, WEB_PRO_DATA, gmap.dataMapSelect);
					// fix zoom add new marker
					var arrayMap = [];
					for (var i = 0; i < gmap.dataMapSelect.markers.length; i++) {
						var myLatlng = new google.maps.LatLng(gmap.dataMapSelect.markers[i]['lat'], gmap.dataMapSelect.markers[i]['lng']);
						var marker = new google.maps.Marker({
							position : myLatlng
						});
						arrayMap.push(marker);
					}
					// fix zoom all marker for map
					gmap.fixZoomMarkerGmap(arrayMap, gmap.selectorGmap.mapData, 1);
					gmap.dataMapSelect['zoom'] = gmap.selectorGmap.mapData.getZoom();
					gmap.updateSlider(gmap.dataMapSelect['zoom']);
					componentModelManager.updateItemField(crCompId, WEB_PRO_DATA, gmap.dataMapSelect);
				} else if (gmap.modeMarker == 0) {// mode edit
					// edit marker selected
					gmap.editMarker(gmap.markersIndex, jsonMarker);
					// update marker after add new marker
					gmap.loadListMarkes(gmap.dataMapSelect.markers);
					componentModelManager.updateItemField(crCompId, WEB_PRO_DATA, gmap.dataMapSelect);
				}
				return true;
			}
		},
		checkConflict : function(listMarker, marker, indexMarker, mode) {
			for (var i = 0; i < listMarker.markers.length; i++) {
				if (((mode == 0) ? i != indexMarker : true) && marker.position.lat() == listMarker.markers[i]['lat'] && marker.position.lng() == listMarker.markers[i]['lng'])
					return true;
			}
			return false;
		},
		/*
		 * gmap.dataMapSelect edit markers Data
		 */
		editMarker : function(indexMarker, markers) {
			gmap.markerDelete = gmap.selectorGmap.markers[indexMarker];
			gmap.dataMapSelect.markers[indexMarker] = markers;
			$("#content-wrapper .content-box .qb-component-gmap ").each(function() {
				var compId = $(this).attr('qb-component-id');
				if (gmap.idWEB == compId) {
					gmap.loadInitGmap(gmap.dataMapSelect, 0, 1);
					return;
				}
			});
		},
		/*
		 * convert to Rbg to Hex
		 */
		rgbToHex : function(rgb) {
			rgb = rgb.match(/^rgba?[\s+]?\([\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?/i);
			return (rgb && rgb.length === 4) ? "#" + ("0" + parseInt(rgb[1], 10).toString(16)).slice(-2) + ("0" + parseInt(rgb[2], 10).toString(16)).slice(-2) + ("0" + parseInt(rgb[3], 10).toString(16)).slice(-2) : '';
		},
		/*
		 * gmap upload image Marker
		 */
		saveCropbox : function(base64, webId) {
			base64 = "data:image/jpeg;base64," + base64;
			formData = new FormData();
			if (isNaN(webId))
				webId = webId.split('c')[1];
			formData.append('webId', webId);
			formData.append('imgBase64', base64);
			formData.append('src', 'marker');
			formData.append('ext', '');
			$.ajax({
				type : "POST",
				url : encodeUrl("myCollectionBean.upLoad"),
				cache : false,
				contentType : false,
				processData : false,
				data : formData,
				success : function(response) {
					if (response.status == AJAX_SUCESS) {
						var url = response['result']['realSrc']
						console.log("URL IMAGE : " + response['result']['realSrc']);
						$('#qb-option-gmap .gmap-option #img_Data').attr('src', url);
						$('.gmap-option .qb-gmap-option-body .option-gmap .add-marker #img_Data').css({
							"background" : "url(" + url + ")",
							"background-size" : "100% 100%"
						});
					} else {
						showGrowlMessageError();
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * fix zoom center marker
		 */
		fixZoomMarkerGmap : function(listMarker, map, type) {
			var bounds = new google.maps.LatLngBounds();
			for ( var i in listMarker)
				// your marker list here
				bounds.extend(listMarker[i].position); // your marker position,
			// must be a LatLng
			// instance
			map.fitBounds(bounds); // map should be your map class
			if (type == 0) {
				setTimeout(function() {
//					map.setCenter(bounds.getCenter());
					gmap.updateSlider(map.getZoom());
				}, 1500);
			} else if (type == 1) {
				setTimeout(function() {
//					map.setCenter(bounds.getCenter());
				}, 100);
			}
			return bounds;
		},
		fixCenter : function(map, latLng) {
			setTimeout(function() {
//				map.setCenter(latLng);
			}, 1000);
		},
		/*
		 * generic load qb-component
		 * 
		 */genericLoadData : function() {
			var nurmber = 0, index_Map = 0, check = true;
			// count all emlement
			$(".google-map-canvas").each(function() {
				nurmber++;
			});
			// check element
			for (var i = 0; i <= nurmber; i++) {
				var select = "#map-canvas-" + (i);
				if ($(select).length > 0) {
					index_Map = i;
				} else {
					index_Map = i;
					check = false;
					break;
				}
			}
			((check == true) ? index_Map + 1 : "");
			gmap.newMAP = parseInt(index_Map);
			template_single_gmap = '';
			template_single_gmap += '<div class="qb-component">';
			template_single_gmap += '<div class="google-map-canvas" id="map-canvas-' + gmap.newMAP + '"></div>';
			template_single_gmap += '<div class="function-panel">';
			template_single_gmap += '<div class="item btn-option">';
			template_single_gmap += '<i class="fa fa-gear"></i>';
			template_single_gmap += '</div>';
			template_single_gmap += '<div class="item btn-edit btn-change-option-gmap-src" get-map="map-canvas-' + gmap.newMAP + '">';
			template_single_gmap += '<i class="fa fa-pencil"></i>';
			template_single_gmap += '</div>';
			template_single_gmap += '<div class="item btn-draggable">';
			template_single_gmap += '<i class="fa fa-arrows"></i>';
			template_single_gmap += '</div>';
			template_single_gmap += '<div class="item btn-del">';
			template_single_gmap += '<i class="fa fa-times-circle"></i>';
			template_single_gmap += '</div>';
			template_single_gmap += '</div>';
			template_single_gmap += '</div>';
			return template_single_gmap;
		}
	}
}());