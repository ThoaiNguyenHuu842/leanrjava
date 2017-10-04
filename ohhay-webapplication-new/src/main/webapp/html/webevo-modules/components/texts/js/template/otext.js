function buildTemplateOtextAdd(appendText, node) {
	var template_otext = '';
	template_otext += '<div class="grid-stack-item-content qb-component-text otext">';
	template_otext += '<div class="qb-component clearfix" style="' + node.css + '">';
	template_otext += '			<div class="layer-background" style="' + node.bgCss + '"></div>';
	template_otext += '<div class="otext-wrapper-content" contenteditable="true">';
	template_otext += appendText;
	template_otext += '</div>';
	template_otext += '</div>';

	template_otext += '<div class="function-panel">';
	template_otext += '<div class="item btn-draggable">';
	template_otext += '<i class="fa fa-arrows"></i>';
	template_otext += '</div>';
	template_otext += '<div class="item btn-dropdown-menu dropdown">';
	template_otext += '<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_otext += '<label>...</label>';
	template_otext += '</button>';
	template_otext += '<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	if(web.MV922==="ADMIN")
		template_otext += '<li class="btnadmin-set-tolibrary"><a><i class="fa fa-bookmark-o"></i>Add to library</a></li>';
	template_otext += '<li class="btn-set-background"><a><i class="fa fa-picture-o"></i>' + getLocalize("web_edit_changebg") + '</a></li>';
	template_otext += '<li class="btn-option"><a><i class="fa fa-cog"></i>' + getLocalize("weh_object_style") + '</a></li>';
	template_otext += '<li class="btn-comp-duplicate"><a><i class="fa fa-copy"></i>' + getLocalize("setting_duplicate") + '</a></li>';
	template_otext += '<li class="btn-comp-copy"><a><i class="icon-display"></i>' + 'Copy (Ctrl + C)' + '</a></li>';
	template_otext += '<li class="btn-set-onload-effect"><a><i class="fa fa-exchange"></i>' + getLocalize("jsobt_title3") + '</a></li>';
	template_otext += '<li class="btn-set-writing-mode"><a><i class="fa fa-eye"></i><span class="title writing-mode">' + getLocalize("jsott_title1") + '</span></a></li>';
	template_otext += '<li class="btn-set-anchor border-top"><a ><i class="fa fa-anchor"></i><label>' + getLocalize("jsott_title2") + '</label></a></li>';
	template_otext += '<li class="btn-del border-top"><a><i class="fa fa-trash-o"></i>' + getLocalize("delete") + '</a></li>';
	template_otext += '</ul>';
	template_otext += '</div>';
	template_otext += '</div>';
	template_otext += '</div>';
	return template_otext;
}

function buildTemplateOtextLoad(appendText) {
	var template_otext = '';
	template_otext += '<div class="grid-stack-item-content qb-component-text otext">';
	template_otext += '<div class="qb-component clearfix">';
	template_otext += '			<div class="layer-background"></div>';
	template_otext += '<div class="otext-wrapper-content" contenteditable="true">';
	template_otext += appendText;
	template_otext += '</div>';
	template_otext += '</div>';

	template_otext += '<div class="function-panel">';
	template_otext += '<div class="item btn-draggable">';
	template_otext += '<i class="fa fa-arrows"></i>';
	template_otext += '</div>';
	template_otext += '<div class="item btn-dropdown-menu dropdown">';
	template_otext += '<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_otext += '<label>...</label>';
	template_otext += '</button>';
	template_otext += '<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	if(web.MV922==="ADMIN")
		template_otext += '<li class="btnadmin-set-tolibrary"><a><i class="fa fa-bookmark-o"></i>Add to library</a></li>';
	template_otext += '<li class="btn-set-background"><a><i class="fa fa-picture-o"></i>' + getLocalize("web_edit_changebg") + '</a></li>';
	template_otext += '<li class="btn-option"><a><i class="fa fa-cog"></i>' + getLocalize("weh_object_style") + '</a></li>';
	template_otext += '<li class="btn-comp-duplicate"><a><i class="fa fa-copy"></i>' + getLocalize("setting_duplicate") + '</a></li>';
	template_otext += '<li class="btn-comp-copy"><a><i class="icon-display"></i>' + 'Copy (Ctrl + C)' + '</a></li>';
	template_otext += '<li class="btn-set-onload-effect"><a><i class="fa fa-exchange"></i>' + getLocalize("jsobt_title3") + '</a></li>';
	template_otext += '<li class="btn-set-writing-mode"><a><i class="fa fa-eye"></i><span class="title writing-mode">' + getLocalize("jsott_title1") + '</span></a></li>';
	template_otext += '<li class="btn-set-anchor border-top"><a ><i class="fa fa-anchor"></i><label>' + getLocalize("jsott_title2") + '</label></a></li>';
	template_otext += '<li class="btn-del border-top"><a><i class="fa fa-trash-o"></i>' + getLocalize("delete") + '</a></li>';
	template_otext += '</ul>';
	template_otext += '</div>';
	template_otext += '</div>';

	template_otext += '</div>';
	return template_otext;
}
