function concatStringFromArray(array) {
	var resultString = '';
	$.each(array, function(k, v) {
		if (Object.prototype.toString.call(v) === "[object Array]") {
			$.each(v, function(k1, v1) {
				resultString += ' ' + k + '=\'' + v1 + '\';';
			});
		} else
			resultString += ' ' + k + '=\'' + v + '\';';
	});
	return resultString;
}

function buildTemplateOButton(data, tempStyle,tempBG) {
	var obuttonOtherAttributes = '';
	var obuttonTitleOtherAttributes = '';
	var obuttonMouseOver = '';
	var obuttonMouseOut = '';
	var obuttonTitleMouseOver = '';
	var obuttonTitleMouseOut = '';
	var obuttonTracking = 'OFF';
	if (data.actionType == 'scroll-to-bottom')
		obuttonOtherAttributes += ' onclick="window.scrollTo(0,document.body.scrollHeight);"';
	if (data.obuttonAnimation)
		obuttonOtherAttributes += ' ho-effect="' + data.obuttonAnimation + '"';
	if (data.obuttonHover)
		if (typeof data.obuttonHover.onmouseover === 'object')
			obuttonMouseOver = concatStringFromArray(data.obuttonHover.onmouseover);
	if (data.obuttonHover)
		if (typeof data.obuttonHover.onmouseout === 'object')
			obuttonMouseOut = concatStringFromArray(data.obuttonHover.onmouseout);
	if (data.obuttonTitleHover)
		if (typeof data.obuttonTitleHover.onmouseover === 'object')
			obuttonTitleMouseOver = concatStringFromArray(data.obuttonTitleHover.onmouseover);
	if (data.obuttonTitleHover)
		if (typeof data.obuttonTitleHover.onmouseout === 'object')
			obuttonTitleMouseOut = concatStringFromArray(data.obuttonTitleHover.onmouseout);
	obuttonOtherAttributes += ' onmouseover="' + obuttonMouseOver + '"';
	obuttonOtherAttributes += ' onmouseout="' + obuttonMouseOut + '"';
	obuttonTitleOtherAttributes += ' onmouseover="' + obuttonTitleMouseOver + '"';
	obuttonTitleOtherAttributes += ' onmouseout="' + obuttonTitleMouseOut + '"';
	if (data.actionTracking)
		obuttonTracking = data.actionTracking == 0 ? 'OFF' : 'ON';
	var template_obutton = '<div class="grid-stack-item-content qb-component-button obutton">';
	template_obutton += '<div class="qb-component" style="overflow:hidden; '+tempStyle+'">';
	template_obutton += '			<div class="layer-background" style="' + tempBG + '" ';
//	template_obutton +=  obuttonOtherAttributes;
	template_obutton +=  '></div>';
	template_obutton += '<a class="obutton-link" data-action-type="' + data.actionType + '" data-action-link="' + data.actionLink + '" data-action-link-target="' + data.actionLinkTarget + '" data-action-tracking="' + data.actionTracking + '" ';
	template_obutton += '>';
	template_obutton += '<div class="obutton-label" contenteditable="true" style="' + data.obuttonTitleStyle + '" ' + obuttonTitleOtherAttributes + '>' + data.obuttonTitle + '</div>';
	template_obutton += '</a>';
	template_obutton += '<div class="component-blur"></div>';
	template_obutton += '</div>';
	template_obutton += '<div class="function-panel">';
	template_obutton += '<div class="item btn-draggable">';
	template_obutton += '<i class="fa fa-arrows"></i>';
	template_obutton += '</div>';
	template_obutton += '<div class="item btn-dropdown-menu dropdown">';
	template_obutton += '<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_obutton += '<label>...</label>';
	template_obutton += '</button>';
	template_obutton += '<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	template_obutton += '<li class="btn-set-background"><a><i class="fa fa-picture-o"></i>' + getLocalize("jsobt_title5") + '</a></li>';
	template_obutton += '<li class="btn-set-style"><a><i class="fa fa-cog"></i>' + getLocalize("jsobt_title6") + '</a></li>';
	template_obutton += '<li class="btn-set-action"><a><i class="fa fa-wrench"></i>' + getLocalize("jsobt_title4") + '</a></li>';
	template_obutton += '<li class="btn-comp-duplicate"><a><i class="fa fa-copy"></i>' + getLocalize("setting_duplicate") + '</a></li>';
	template_obutton += '<li class="btn-set-onload-effect"><a><i class="fa fa-exchange"></i>' + getLocalize("jsobt_title3") + '</a></li>';
	template_obutton += '<li class="btn-set-effect"><a><i class="fa fa-clone"></i>' + getLocalize("jsobt_title2") + '</a></li>';
	template_obutton += '<li class="btn-tracking-switch"><a><i class="fa fa-line-chart"></i><span class="title tracking-title">' + getLocalize("tracking") + ': ' + obuttonTracking + '</span></a></li>';
	template_obutton += '<li class="btn-set-anchor border-top"><a ><i class="fa fa-anchor"></i><label>' + getLocalize("jsobt_title1") + '</label></a></li>';
	template_obutton += '<li class="btn-del border-top"><a><i class="fa fa-trash-o"></i>' + getLocalize("delete") + '</a></li>';
	template_obutton += '</ul>';
	template_obutton += '</div>';
	template_obutton += '</div>';
	template_obutton += '</div>';
	return $(template_obutton);
};