function buildTemplateGmap(src) {
	var nurmber=0, index_Map = 0, check = true,listMapTemp=[];
	//count all emlement
	$(".google-map-canvas").each(function() {
		nurmber++;
		listMapTemp.push($(this).attr("id"));
		console.log("Number !");
	});
	//Update map done delete  
	gmap.updateValueAllMap(listMapTemp);
	//check element 
	for (var i = 0; i <= nurmber; i++) {
		var select="#map-canvas-" + (i);
		console.log("ERORR : "+select);
		if ($(select).length > 0) {
			index_Map=i;
			console.log("Continue!");
		} else {
			index_Map = i;
			check = false;
			console.log("Break");
			break;
		}
	}
	((check == true) ? index_Map + 1 : "");
	gmap.newMAP = parseInt(index_Map);

	var template_single_gmap = '';
	template_single_gmap += '<div class="grid-stack-item-content qb-component-gmap" id="qb-google-map">';
	template_single_gmap += 	'<div class="qb-component" style="border-width: 0px; border-style: solid; border-color: rgb(0, 0, 0); padding: 15px 30px 30px; background-color: rgb(235, 235, 235); background-image: initial;" cp-pd-left="30" cp-pd-top="15" cp-pd-bottom="30" cp-pd-right="30">';
	template_single_gmap += '			<div class="layer-background"></div>';
	template_single_gmap += 		'<div class="google-map-canvas" id="map-canvas-' + gmap.newMAP+'">';
	template_single_gmap += 		'</div>';
	template_single_gmap +=       	'<div class="function-panel">';
	template_single_gmap +=				'<div class="item btn-draggable">';
	template_single_gmap += 					'<i class="fa fa-arrows"></i>';
	template_single_gmap +=				'</div>';
	template_single_gmap += 				'<div class="item btn-dropdown-menu dropdown">';
	template_single_gmap +=           		'<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_single_gmap +=           			'<label>...</label>';
	template_single_gmap +=           		'</button>';
	template_single_gmap +=           		'<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	template_single_gmap +=           			'<li class="btn-change-option-gmap-src" get-map="map-canvas-' + gmap.newMAP+'"><a><i class="fa fa-map-marker"></i>'+getLocalize("jsgmp_title4")+'</a></li>';
	template_single_gmap +=           			'<li class="btn-set-background"><a><i class="fa fa-picture-o"></i>'+getLocalize("web_edit_changebg")+'</a></li>';
	template_single_gmap +=           			'<li class="btn-option"><a><i class="fa fa-cog"></i>'+getLocalize("weh_object_style")+'</a></li>';
	template_single_gmap +=          			'<li class="btn-comp-duplicate"><a><i class="fa fa-copy"></i>'+getLocalize("setting_duplicate")+'</a></li>';
	template_single_gmap +=           			'<li class="btn-set-anchor border-top"><a ><i class="fa fa-anchor"></i><label>'+getLocalize("jsgmp_title3")+'</label></a></li>';
	template_single_gmap +=           			'<li class="btn-del border-top"><a><i class="fa fa-trash-o"></i>'+getLocalize("delete")+'</a></li>';
	template_single_gmap +=           		'</ul>';
	template_single_gmap +=				'</div>';
	template_single_gmap +=			'</div>';
	template_single_gmap +=			'<div class="component-blur"></div>';
	template_single_gmap +=		'</div>';
	template_single_gmap += '</div>';
	return template_single_gmap;
}