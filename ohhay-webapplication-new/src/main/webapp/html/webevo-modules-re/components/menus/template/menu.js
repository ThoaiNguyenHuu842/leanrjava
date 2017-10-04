function buildTemplateOmenu(data){
	console.log(data);
	var m_bder_c = '', m_bground_c = '';
	if(data.color){
		m_bder_c = 'style="border-color: '+data.color+'"'; 
		m_bground_c = 'style="background: '+data.color+'"';
	}
	var template_otext = '';
	template_otext += 	'<div class="grid-stack-item-content qb-component-menu omenu">';
	template_otext += 		'<div class="qb-component" style="border-color: rgb(0, 0, 0);border-style: solid;border-width: 0;">';
	template_otext += '			<div class="layer-background"></div>';
	template_otext += 		'	<div class="omenu-wrapper-content">';
	template_otext += 		'		<nav class="ohay-navbar navbar navbar-default">';
	template_otext += 		'  			<div class="navbar-header">';
	template_otext += 		'  				<button aria-expanded="false" data-target="#navbar-collapse-'+data.id+'" data-toggle="collapse" class="navbar-toggle collapsed" type="button" '+m_bder_c+'>';
	template_otext += 		'  					<span class="sr-only">Toggle navigation</span><span class="icon-bar" '+m_bground_c+'></span><span class="icon-bar" '+m_bground_c+'></span><span class="icon-bar" '+m_bground_c+'></span>';
	template_otext += 		'  				</button>';
	template_otext += 		'  			</div>';
	template_otext += 		'  			<div id="navbar-collapse-'+data.id+'" class="collapse navbar-collapse" '+m_bder_c+'>';
	template_otext += 		'				<ul class="'+data.cls+'" align="'+data.align+'" menu-fill="'+data.fill+'">';
	template_otext += 							data.text;
	template_otext += 		'				</ul>';
	template_otext += 		'			</div>';
	template_otext += 		'		</nav>';
	template_otext += 		'	</div>';
	template_otext +=		'</div>';
	
	template_otext +=       '<div class="function-panel">';
	template_otext +=			'<div class="item btn-draggable">';
	template_otext += 			'	<i class="fa fa-arrows"></i>';
	template_otext +=			'</div>';
	template_otext += 			'<div class="item btn-dropdown-menu dropdown">';
	template_otext +=          	'	<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
	template_otext +=          	'		<label>...</label>';
	template_otext +=          	'	</button>';
	template_otext +=          	'	<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
	template_otext +=          	'		<li class="btn-set-background"><a><i class="fa fa-picture-o"></i>'+getLocalize("web_edit_changebg")+'</a></li>';
	template_otext +=          	'		<li class="btn-option"><a><i class="fa fa-cog"></i>'+getLocalize("weh_object_style")+'</a></li>';
	template_otext +=      		'		<li class="btn-comp-duplicate"><a><i class="fa fa-copy"></i>'+getLocalize("setting_duplicate")+'</a></li>';
	template_otext +=  			'		<li class="btn-set-onload-effect"><a><i class="fa fa-exchange"></i>'+getLocalize("jsobt_title3")+'</a></li>';
	template_otext +=          	'		<li class="btn-menu-option"><a><i class="fa fa-list"></i>'+getLocalize("jsmtp_title1")+'</a></li>';
	template_otext +=          	'		<li class="btn-set-anchor border-top"><a ><i class="fa fa-anchor"></i><label>'+getLocalize("jsmtp_title2")+'</label></a></li>';
	template_otext +=          	'		<li class="btn-del border-top"><a><i class="fa fa-trash-o"></i>'+getLocalize("delete")+'</a></li>';
	template_otext +=          	'	</ul>';
	template_otext +=			'</div>';
	template_otext +=		'</div>';
	template_otext += '</div>';
	return template_otext;
}
