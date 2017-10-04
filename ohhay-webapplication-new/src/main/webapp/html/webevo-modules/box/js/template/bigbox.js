function buildTemplateBigBox(boxData) {
	var boxID = 'qb-box-' + boxData[WEB_PRO_ID];
	var boxName = boxData[WEB_PRO_ID];
	if (boxData[WEB_PRO_NAME] && boxData[WEB_PRO_NAME] != 'null') {
		boxID = WEB_PREFIX_BOX + boxData[WEB_PRO_NAME];
		boxName = boxData[WEB_PRO_NAME];
	}
	var libHtml = getLibHTML(boxData);
	// widget name of this box (Ex: regis-webinaris)
	var widgetAttr = "";
	var widgetHTML = "";
	if (boxData[WEB_PRO_WIGETNAME] && boxData[WEB_PRO_WIGETNAME].length > 0) {
		widgetAttr = 'widget-name="' + boxData[WEB_PRO_WIGETNAME] + '"';
		widgetHTML = '<li class="btn-edit-widget-data"><a><i class="fa  fa-pencil-square-o"></i>' + getLocalize("bbtpt_title1") + '</a></li>';
	}
	var template_big_box = '';
	template_big_box += '<li ' + widgetAttr + ' id="' + boxID + '" qb-box-id="' + boxData.id + '" qb-gird-h="' + boxData.h + '" class="qb-body-box content-box qb-box-component">';
	template_big_box += '	<div class="layer-background bigbox"></div>';
	template_big_box += '	<div class="grid-stack">';
	template_big_box += '		<div class="layer-background girdbox"></div>';
	template_big_box += '		<div class="qb-gird-handle-custom qb-edit-tool">';
	template_big_box += '			<img src="' + $("#contextPath").val() + '/html/images/move-box.png"/>';
	template_big_box += '		</div>';
	template_big_box += '		<div class="menu-grid-right qb-edit-tool">';
	template_big_box += '<div class="item btn-dropdown-menu dropdown">';
	template_big_box += '<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_big_box += '<label>...</label>';
	template_big_box += '</button>';
	template_big_box += '<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	template_big_box += '<li class="btn-upload-bg-grid"><a><i class="fa fa-picture-o"></i>' + getLocalize("web_edit_changebg") + '</a></li>';
	template_big_box += '<li class="btn-grid-option"><a><i class="fa fa-cog"></i>' + getLocalize("weh_object_style") + '</a></li>';
	/*
	 * template_big_box += '<li class="btn-menu-change-smart-layout"><a><i
	 * class="fa fa-th"></i><span
	 * class="title">'+getLocalize("bbtpt_title3")+'</span></a></li>';
	 */
	template_big_box += '</ul>';
	template_big_box += '</div>';
	template_big_box += '		</div>';
	template_big_box += '	</div>';
	template_big_box += '	';
	template_big_box += '	<div class="menu-right">';
	template_big_box += '		<div title="' + getLocalize("aco_title3") + '" data-toggle="tooltip" data-placement="bottom" class="item btn-sort"><i class="fa fa-arrows"></i></div>';
	template_big_box += '<div class="item btn-dropdown-menu dropdown">';
	template_big_box += '<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_big_box += '<label>...</label>';
	template_big_box += '</button>';
	template_big_box += '<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	template_big_box += '<li class="btn-upload-big-box btn-mobile-section-tools"><a><i class="fa fa-picture-o"></i>' + getLocalize("web_edit_changebg") + '</a></li>';
	template_big_box += '<li class="btn-box-option btn-mobile-section-tools"><a><i class="fa fa-cog"></i>' + getLocalize("weh_object_style") + '</a></li>';
	template_big_box += '<li class="btn-box-duplicate"><a><i class="fa fa-copy"></i>' + getLocalize("setting_duplicate") + '</a></li>';
	template_big_box += libHtml;
	/* template_big_box += widgetHTML; */
	template_big_box += '<li class="btn-set-anchor-box border-top"><a><i class="fa fa-anchor"></i><label>' +getLocalize("jsmtp_title3")+" "+ boxName + '</label></a></li>';
	template_big_box += '<li class="btn-del-box border-top"><a><i class="fa fa-trash-o"></i>' + getLocalize("delete") + '</a></li>';
	template_big_box += '</ul>';
	template_big_box += '</div>';
	template_big_box += '	</div>';
	template_big_box += '</li>';
	return template_big_box;
}

function buildTemplateBigBoxHeader(boxData) {
	var boxID = boxData.name != null ? WEB_PREFIX_BOX + boxData.name : 'qb-box-' + boxData.id;
	var boxName = boxData[WEB_PRO_ID];
	if (boxData[WEB_PRO_NAME] && boxData[WEB_PRO_NAME] != 'null') {
		boxID = WEB_PREFIX_BOX + boxData[WEB_PRO_NAME];
		boxName = boxData[WEB_PRO_NAME];
	}
	var libHtml = getLibHTML(boxData);
	// widget name of this box (Ex: regis-webinaris)
	var widgetAttr = "";
	if (boxData[WEB_PRO_WIGETNAME] && boxData[WEB_PRO_WIGETNAME].length > 0)
		widgetAttr = 'widget-name="' + boxData[WEB_PRO_WIGETNAME] + '"';
	var template_big_box = '';
	template_big_box += '<li ' + widgetAttr + ' id="' + boxID + '" qb-box-id="' + boxData.id + '" qb-gird-h="' + boxData.h + '" class="qb-header qb-box-component content-box">';
	template_big_box += '	<div class="layer-background bigbox"></div>';
	template_big_box += '	<div class="grid-stack">';
	template_big_box += '	<div class="layer-background girdbox"></div>';
	template_big_box += '		<div class="qb-gird-handle-custom qb-edit-tool">';
	template_big_box += '			<img src="' + $("#contextPath").val() + '/html/images/move-box.png"/>';
	template_big_box += '		</div>';
	template_big_box += '		<div class="menu-grid-right qb-edit-tool">';
	template_big_box += '<div class="item btn-dropdown-menu dropdown">';
	template_big_box += '<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_big_box += '<label>...</label>';
	template_big_box += '</button>';
	template_big_box += '<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	template_big_box += '<li class="btn-upload-bg-grid"><a><i class="fa fa-picture-o"></i>' + getLocalize("web_edit_changebg") + '</a></li>';
	template_big_box += '<li class="btn-grid-option"><a><i class="fa fa-cog"></i>' + getLocalize("weh_object_style") + '</a></li>';
	/*
	 * template_big_box += '<li class="btn-menu-change-smart-layout"><a><i
	 * class="fa fa-th"></i><span
	 * class="title">'+getLocalize("bbtpt_title3")+'</span></a></li>';
	 */
	template_big_box += '</ul>';
	template_big_box += '</div>';
	template_big_box += '		</div>';
	template_big_box += '	</div>';
	template_big_box += '	';
	template_big_box += '	<div class="menu-right">';
	template_big_box += '<div class="item btn-dropdown-menu dropdown">';
	template_big_box += '<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_big_box += '<label>...</label>';
	template_big_box += '</button>';
	template_big_box += '<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	template_big_box += '<li class="btn-upload-big-box btn-mobile-section-tools"><a><i class="fa fa-picture-o"></i>' + getLocalize("web_edit_changebg") + '</a></li>';
	template_big_box += '<li class="btn-box-option btn-mobile-section-tools"><a><i class="fa fa-cog"></i>' + getLocalize("weh_object_style") + '</a></li>';
	template_big_box += libHtml;
	template_big_box += '<li class="btn-set-anchor-box"><a><i class="fa fa-anchor"></i><label>'+getLocalize("jsmtp_title3")+" "+ boxName + '</label></a></li>';
	template_big_box += '<li class="btn-del-box"><a><i class="fa fa-trash-o"></i>' + getLocalize("delete") + '</a></li>';
	template_big_box += '</ul>';
	template_big_box += '</div>';
	template_big_box += '	</div>';
	template_big_box += '	<div class="menu-left qb-edit-tool">';
	template_big_box += '		<div class="box-info item">';
	template_big_box += '			<label data-toggle="tooltip" data-placement="right">' + getLocalize("bbtpt_header") + ' <span>&#xf05a;</span></label>';
	template_big_box += '		</div>';
	template_big_box += '	</div>';
	template_big_box += '</li>';
	return template_big_box;
}

function buildTemplateBigBoxFooter(boxData) {
	var boxID = boxData.name != null ? WEB_PREFIX_BOX + boxData.name : 'qb-box-' + boxData.id;
	var boxName = boxData[WEB_PRO_ID];
	if (boxData[WEB_PRO_NAME] && boxData[WEB_PRO_NAME] != 'null') {
		boxID = WEB_PREFIX_BOX + boxData[WEB_PRO_NAME];
		boxName = boxData[WEB_PRO_NAME];
	}
	var libHtml = getLibHTML(boxData);
	var template_big_box = '';
	// widget name of this box (Ex: regis-webinaris)
	var widgetAttr = "";
	if (boxData[WEB_PRO_WIGETNAME] && boxData[WEB_PRO_WIGETNAME].length > 0)
		widgetAttr = 'widget-name="' + boxData[WEB_PRO_WIGETNAME] + '"';
	template_big_box += '<li ' + widgetAttr + ' id="' + boxID + '" qb-box-id="' + boxData.id + '" qb-gird-h="' + boxData.h + '" class="qb-state-disabled qb-footer qb-box-component content-box">';
	template_big_box += '	<div class="layer-background bigbox"></div>';
	template_big_box += '	<div class="grid-stack">';
	template_big_box += '	<div class="layer-background girdbox"></div>';
	template_big_box += '		<div class="qb-gird-handle-custom qb-edit-tool">';
	template_big_box += '			<img src="' + $("#contextPath").val() + '/html/images/move-box.png"/>';
	template_big_box += '		</div>';
	template_big_box += '		<div class="menu-grid-right qb-edit-tool">';
	template_big_box += '<div class="item btn-dropdown-menu dropdown">';
	template_big_box += '<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_big_box += '<label>...</label>';
	template_big_box += '</button>';
	template_big_box += '<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	template_big_box += '<li class="btn-upload-bg-grid"><a><i class="fa fa-picture-o"></i>' + getLocalize("web_edit_changebg") + '</a></li>';
	template_big_box += '<li class="btn-grid-option"><a><i class="fa fa-cog"></i>' + getLocalize("weh_object_style") + '</a></li>';
	/*
	 * template_big_box += '<li class="btn-menu-change-smart-layout"><a><i
	 * class="fa fa-th"></i><span
	 * class="title">'+getLocalize("bbtpt_title3")+'</span></a></li>';
	 */
	template_big_box += '</ul>';
	template_big_box += '</div>';
	template_big_box += '		</div>';
	template_big_box += '	</div>';
	template_big_box += '	';
	template_big_box += '	<div class="menu-right">';
	template_big_box += '<div class="item btn-dropdown-menu dropdown">';
	template_big_box += '<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_big_box += '<label>...</label>';
	template_big_box += '</button>';
	template_big_box += '<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	template_big_box += '<li class="btn-upload-big-box btn-mobile-section-tools"><a><i class="fa fa-picture-o"></i>' + getLocalize("web_edit_changebg") + '</a></li>';
	template_big_box += '<li class="btn-box-option btn-mobile-section-tools"><a><i class="fa fa-cog"></i>' + getLocalize("weh_object_style") + '</a></li>';
	template_big_box += libHtml;
	template_big_box += '<li class="btn-set-anchor-box"><a><i class="fa fa-anchor"></i><label>' +getLocalize("jsmtp_title3")+" "+ boxName + '</label></a></li>';
	template_big_box += '<li class="btn-del-box"><a><i class="fa fa-trash-o"></i>' + getLocalize("delete") + '</a></li>';
	template_big_box += '</ul>';
	template_big_box += '</div>';
	template_big_box += '	</div>';
	template_big_box += '	<div class="menu-left qb-edit-tool">';
	template_big_box += '		<div class="box-info item">';
	template_big_box += '			<label data-toggle="tooltip" data-placement="right">' + getLocalize("bbtpt_footer") + ' <span>&#xf05a;</span></label>';
	template_big_box += '		</div>';
	template_big_box += '	</div>';
	template_big_box += '</li>';
	return template_big_box;
}

function getLibHTML(boxData) {
	var libHtml = '<li class="btn-lib btn-add-to-lib"><a><i class="fa fa-folder-o"></i>' + getLocalize("menu_share") + '</a></li>';
	/*
	 * if(boxData.fe920 > 0) libHtml = '<li><a><i class="fa fa-folder-o"></i>Created
	 * from library</a></li>'; else
	 */
	if (boxData.libType == 1)
		libHtml = '<li class="btn-lib"><a><i class="fa fa-folder-o"></i>' + getLocalize("bbtpt_title4") + '</a></li>';
	else if (boxData.libType == 2)
		libHtml = '<li class="btn-lib"><a><i class="fa fa-folder-o"></i>' + getLocalize("bbtpt_title5") + '</a></li>';
	return libHtml;
}
function buildTemplateVideo(url) {
	url = url;
	var template = '<div class="background-video-box" id="background-video-box">';
	template += '		<video poster="" loop="" autoplay="" style="width:100%;">';
	template += '			<source type="video/mp4" src="' + url + '" id="mp4_source"></source>';
	template += '		</video>';
	template += '</div>';
	return template;
}