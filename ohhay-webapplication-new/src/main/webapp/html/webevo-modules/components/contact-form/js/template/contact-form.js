function buildTemplateContactForm(formHtml){
	var template_contact_form = '';
	template_contact_form += '<div class="grid-stack-item-content qb-component-contact-form">';
	template_contact_form += 	'<div class="qb-component">';
	template_contact_form += '			<div class="layer-background"></div>';
	template_contact_form +=		formHtml;
	template_contact_form +=	'</div>';
	template_contact_form +=       	'<div class="function-panel">';
	template_contact_form +=				'<div class="item btn-draggable">';
	template_contact_form += 					'<i class="fa fa-arrows"></i>';
	template_contact_form +=				'</div>';
	template_contact_form += 				'<div class="item btn-dropdown-menu dropdown">';
	template_contact_form +=           		'<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_contact_form +=           			'<label>...</label>';
	template_contact_form +=           		'</button>';
	template_contact_form +=           		'<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	if(web.MV922==="ADMIN")
		template_contact_form += 					'<li class="btnadmin-set-tolibrary"><a><i class="fa fa-bookmark-o"></i>Add to library</a></li>';
	template_contact_form +=           			'<li class="btn-edit-contact-form"><a><i class="fa fa-pencil"></i>'+getLocalize("jsctf_title1")+'</a></li>';
	template_contact_form +=           			'<li class="btn-set-background"><a><i class="fa fa-picture-o"></i>'+getLocalize("web_edit_changebg")+'</a></li>';
	template_contact_form +=           			'<li class="btn-option"><a><i class="fa fa-cog"></i>'+getLocalize("weh_object_style")+'</a></li>';
	template_contact_form +=          			'<li class="btn-comp-duplicate"><a><i class="fa fa-copy"></i>'+getLocalize("setting_duplicate")+'</a></li>';
	template_contact_form += 					'<li class="btn-comp-copy"><a><i class="icon-display"></i>' + 'Copy (Ctrl + C)' + '</a></li>';
	template_contact_form +=           			'<li class="btn-set-anchor border-top"><a ><i class="fa fa-anchor"></i><label>'+getLocalize("jsctf_title2")+'</label></a></li>';
	template_contact_form +=           			'<li class="btn-del border-top"><a><i class="fa fa-trash-o"></i>'+getLocalize("delete")+'</a></li>';
	template_contact_form +=           		'</ul>';
	template_contact_form +=				'</div>';
	template_contact_form +=			'</div>';
	template_contact_form += '</div>';
	return template_contact_form;
}