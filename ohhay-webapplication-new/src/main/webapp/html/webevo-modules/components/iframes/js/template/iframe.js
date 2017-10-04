function buildTemplateIframe(src, type){
	var template = '';
	template += '<div class="grid-stack-item-content qb-component-iframe" iframe-type="'+type+'">';
	template += 	'<div class="qb-component">';
	template += '		<div class="layer-background"></div>';
	template += 		'<iframe width = "100%" height="100%" src="'+src+'"></iframe>';
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
	template +=           			((type!='normal_iframe_topic') ? '<li class="btn-change-iframe-src"><a><i class="fa fa-pencil"></i>'+getLocalize("jsift_title1")+'</a></li>' : '<li class="btn-change-topic-src"><a><i class="fa fa-pencil"></i>'+getLocalize("jsift_title1")+'</a></li>');
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
