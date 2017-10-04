(function() {
	imageTemplate = {
		buildTemplateGalleryImage : function(data) {
			var self = this;
			var template_gallery_image = '';
			template_gallery_image += '<div class="grid-stack-item-content comp-image ' + data['stat'] + ' ' + (data['isOne'] ? 'one' : '') + ' ' + (data['hasThumb'] ? 'use-thumbnail' : '') + ' ' + (data['hasCounter'] ? 'use-counter' : '') + ' ' + (data['hasAutoPlay'] ? 'auto-play' : '') + '"';
			template_gallery_image += '		data-mode="GALLERY" data-layout="' + data['layout'] + '">';
			template_gallery_image += '	<div class="qb-component">';
			template_gallery_image += '			<div class="layer-background"></div>';
			if (data['stat'] != 'new') {
				template_gallery_image += '	<div class="gallery-wraper">';
				template_gallery_image += '		<ul class="gallery-list transition5 ' + (data['isFill'] ? 'fill' : '') + '">';
				for ( var i in data['lstImg']) {
					template_gallery_image += '		<li data-index="' + (parseInt(i) + 1) + '" class="need-effect ' + (typeof data['filter'] != 'undefined' && (data['filter'].length > 0) ? 'has-effect' : '') + '"';
					template_gallery_image += '   		data-filter="' + data['filter'] + '" ';
					template_gallery_image += ' 	    	style="background-image: url(\'' + data['lstImg'][i] + '\');filter:' + data['filter'] + ';-webkit-filter:' + data['filter'] + ';" >';
					template_gallery_image += '			<div class="gal-item-tool">';
					template_gallery_image += '				<span class="qb-img-tool replace"><i class="fa fa-refresh"/></span>';
					template_gallery_image += '				<span class="qb-img-tool remove"><i class="fa fa-close"/></span>';
					template_gallery_image += '			</div>';
					template_gallery_image += '		</li>';
				}
				template_gallery_image += '		</ul>';
				template_gallery_image += '	</div>';

				template_gallery_image += '	<ul class="thumbnail-list-dotted transition2">';
				for ( var v in data['lstThumb']) {
					template_gallery_image += '	<li class="' + (v == 0 ? 'active' : '') + '" data-index="' + (parseInt(v) + 1) + '"></li>';
				}
				template_gallery_image += '	</ul>';

				template_gallery_image += '	<ul class="thumbnail-list transition5">';
				for ( var j in data['lstThumb']) {
					template_gallery_image += '	<li class="' + (j == 0 ? 'active' : '') + '" data-index="' + (parseInt(j) + 1) + '" style="background-image: url(\'' + data['lstThumb'][j] + '\');"></li>';
				}
				template_gallery_image += '	</ul>';

			} else {
				template_gallery_image += '	<div class="gallery-wraper">';
				template_gallery_image += '		<ul class="gallery-list fill">';
				template_gallery_image += '			<li data-index="0" style="background-image: url(\'' + data['img'][0] + '\');"></li>';
				template_gallery_image += '		</ul>';
				template_gallery_image += '	</div>';
				template_gallery_image += '	<ul class="thumbnail-list-dotted transition2">';
				template_gallery_image += '	</ul>';
				template_gallery_image += '	<ul class="thumbnail-list transition5">';
				template_gallery_image += '	</ul>';
			}
			template_gallery_image += '		<div class="gal-controller ' + (data['hasNavigator'] ? 'use-navigator' : '') + '" data-count="' + Object.keys(data['lstImg']).length + '">';
			template_gallery_image += '			<span class="tool prev">&#xf104;</span>';
			template_gallery_image += '			<span class="number">1/' + Object.keys(data['lstImg']).length + '</span>';
			template_gallery_image += '			<span class="tool next">&#xf105;</span>';
			template_gallery_image += '		</div>';

			template_gallery_image += '			<div class="fullscreen-action ' + (data['isFullScr'] ? 'use-fullscreen' : '') + '">';
			template_gallery_image += '				<i class="fa fa-expand"></i>';
			template_gallery_image += '			</div>';
			template_gallery_image += '			</div>';

			template_gallery_image += '		<div class="function-center-z qb-img-tool">';
			template_gallery_image += '			<div class="z-item item animated btn-save-croped-img qb-img-tool">';
			template_gallery_image += '				<i class="fa fa-check"></i>';
			template_gallery_image += '				<span>' + getLocalize("save") + '</span>';
			template_gallery_image += '			</div>';
			template_gallery_image += '			<div class="z-item item animated btn-cancel-crop-img qb-img-tool">';
			template_gallery_image += '				<i class="fa fa-close"></i>';
			template_gallery_image += '				<span>' + getLocalize("cancel") + '</span>';
			template_gallery_image += '			</div>';
			template_gallery_image += '			<div class="z-item item animated btn-image-effect qb-img-tool">';
			template_gallery_image += '				<i class="fa fa-magic"></i>';
			template_gallery_image += '				<span>' + getLocalize("filter") + '</span>';
			template_gallery_image += '			</div>';
			template_gallery_image += '			<div class="z-item animated btn-gallery-option qb-img-tool">';
			template_gallery_image += '				<i class="fa fa-gear"></i>';
			template_gallery_image += '				<span>' + getLocalize("jsimt_title1") + '</span>';
			template_gallery_image += '			</div>';
			template_gallery_image += '			<div class="z-item animated gallery-image-add qb-img-tool">';
			template_gallery_image += '				<i class="fa fa-camera"></i>';
			template_gallery_image += '				<span>' + getLocalize("add") + '</span>';
			template_gallery_image += '			</div>';
			template_gallery_image += '			<div class="item animated btn-edit-img-cuted qb-img-tool qb-img-tool">';
			template_gallery_image += '				<i class="fa fa-pencil"></i>';
			template_gallery_image += '			</div>';
			template_gallery_image += '		</div>';

			template_gallery_image += '		<div class="function-panel">';
			template_gallery_image += '			<div class="item btn-draggable qb-img-tool">';
			template_gallery_image += '				<i class="fa fa-arrows"></i>';
			template_gallery_image += '			</div>';
			template_gallery_image += ' 			<div class="item btn-dropdown-menu dropdown qb-img-tool">';
			template_gallery_image += '          		<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
			template_gallery_image += '          			<i class="fa fa-ellipsis-h"></i>';
			template_gallery_image += '         		</button>';
			template_gallery_image += '         		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
			if(web.MV922==="ADMIN")
				template_gallery_image += 						'<li class="btnadmin-set-tolibrary"><a><i class="fa fa-bookmark-o"></i>Add to library</a></li>';
			template_gallery_image += '         			<li class="btn-option border-top"><a><i class="fa fa-cog"></i>' + getLocalize("weh_object_style") + '</a></li>';
			/*template_gallery_image += '         			<li class="btn-set-background img-comp"><a><i class="fa fa-picture-o"></i>' + getLocalize("web_edit_changebg") + '</a></li>';*/
			template_gallery_image += '						<li class="btn-comp-duplicate"><a><i class="fa fa-copy"></i>' + getLocalize("setting_duplicate") + '</a></li>';
			template_gallery_image += 					   '<li class="btn-comp-copy"><a><i class="icon-display"></i>' + 'Copy (Ctrl + C)' + '</a></li>';
			template_gallery_image += '         			<li class="btn-set-anchor border-top"><a ><i class="fa fa-anchor"></i><label>' + getLocalize("jsimt_title2") + '</label></a></li>';
			template_gallery_image += '        				<li class="btn-del border-top qb-edit-tool"><a><i class="fa fa-trash-o"></i>' + getLocalize("delete") + '</a></li>';
			template_gallery_image += '         		</ul>';
			template_gallery_image += '			</div>';
			template_gallery_image += '		</div>';

			template_gallery_image += '</div>';
			return template_gallery_image;
		},
		buildTemplateSingleImage : function(data) {
			var template_single_image = '';
			template_single_image += '<div class="grid-stack-item-content comp-image ' + data['stat'] + '" data-mode="' + data['mode'] + '">';
			var bgDef = 'background-image: url(' + data['img'][0] + '); background-position: center center;background-size: cover;';
			var css = '';
			if (data['stat'] == 'new')
				css = typeof data['css'] != 'undefined' ? data['css'][0] : bgDef;

			template_single_image += '	<div class="qb-component">';
			template_single_image += '		<div class="box_thumb_img">';
			template_single_image += '			<div class="thumb-img-box" style="' + css + '">';

			template_single_image += '	<a class="obutton-link" data-action-type="' + (data.actionType ? data.actionType : '') + '" data-action-link="' + (data.actionLink ? data.actionLink : '') + '" data-action-link-target="' + (data.actionLinkTarget ? data.actionLinkTarget : '') + '">';
			if (typeof data['filter'] != 'undefined') {
				if (typeof data['realSrc'] != 'undefined')
					template_single_image += '		<img class="' + data['stat'] + ' main-img need-effect has-effect" src="' + data['img'][0] + '" data-real-src="' + data['realSrc'][0] + '" data-filter="' + data['filter'][0] + '" style="filter:' + data['filter'][0] + ';-webkit-filter:' + data['filter'][0] + ';" />';
				else
					template_single_image += '		<img class="' + data['stat'] + ' main-img need-effect has-effect" src="' + data['img'][0] + '" data-filter="' + data['filter'][0] + '" style="filter:' + data['filter'][0] + ';-webkit-filter:' + data['filter'][0] + ';" />';
			} else {
				if (typeof data['realSrc'] != 'undefined')
					template_single_image += '		<img class="' + data['stat'] + ' main-img need-effect" src="' + data['img'][0] + '" data-real-src="' + data['realSrc'][0] + '" />';
				else
					template_single_image += '		<img class="' + data['stat'] + ' main-img need-effect" src="' + data['img'][0] + '" />';
			}
			template_single_image += '		</a>';

			template_single_image += '				<div class="thumb-img-tool qb-img-tool single">';
			template_single_image += '					<span class="btn-upload-small-box pull-left animated qb-img-tool">';
			template_single_image += '						<i class="fa fa-camera"></i>';
			template_single_image += '					</span>';
			template_single_image += '					<span class="btn-zoom-in pull-left animated qb-img-tool">';
			template_single_image += '						<i class="fa fa-plus"></i>';
			template_single_image += '					</span>';
			template_single_image += '					<span class="btn-zoom-out pull-left animated qb-img-tool">';
			template_single_image += '						<i class="fa fa-minus"></i>';
			template_single_image += '					</span>';
			template_single_image += '					<span class="btn-rotate-img pull-left animated qb-img-tool">';
			template_single_image += '						<i class="fa fa-rotate-right"></i>';
			template_single_image += '					</span>';
			template_single_image += '				</div>';
			template_single_image += '			</div>';
			template_single_image += '		</div>';
			template_single_image += '	</div>';

			template_single_image += '			<div class="function-center-z qb-img-tool">';
			template_single_image += '				<div class="z-item item animated btn-cancel-crop-img qb-img-tool">';
			template_single_image += '					<i class="fa fa-close"></i>';
			template_single_image += '					<span>' + getLocalize("cancel") + '</span>';
			template_single_image += '				</div>';
			template_single_image += '				<div class="z-item item animated btn-image-effect qb-img-tool">';
			template_single_image += '					<i class="fa fa-magic"></i>';
			template_single_image += '					<span>' + getLocalize("filter") + '</span>';
			template_single_image += '				</div>';
			template_single_image += '				<div class="z-item item animated btn-save-croped-img qb-img-tool">';
			template_single_image += '					<i class="fa fa-check"></i>';
			template_single_image += '					<span>' + getLocalize("save") + '</span>';
			template_single_image += '				</div>';
			template_single_image += '				<div class="item animated btn-edit-img-cuted qb-img-tool">';
			template_single_image += '					<i class="fa fa-pencil"></i>';
			template_single_image += '				</div>';
			template_single_image += '				<div class="item animated chkb-keep-original qb-img-tool">';
			template_single_image += '					<label><input type="checkbox" class="keep-orginal"/>';
			template_single_image += '					<span></span>';
			template_single_image += '					Keep original</label>';
			template_single_image += '				</div>';
			//smallsize
			template_single_image += '				<div class="z-item item smallsize btn-rotate-img-s animated qb-img-tool">';
			template_single_image += '					<i class="fa fa-rotate-right"></i>';
			template_single_image += '				</div>';
			template_single_image += '				<div class="z-item item smallsize btn-zoom-out-s animated qb-img-tool">';
			template_single_image += '					<i class="fa fa-minus"></i>';
			template_single_image += '				</div>';
			template_single_image += '				<div class="z-item item smallsize btn-zoom-in-s animated qb-img-tool">';
			template_single_image += '					<i class="fa fa-plus"></i>';
			template_single_image += '				</div>';
			template_single_image += '				<div class="z-item item smallsize btn-upload-small-box-s animated qb-img-tool">';
			template_single_image += '					<i class="fa fa-camera"></i>';
			template_single_image += '				</div>';

			template_single_image += '			</div>';

			if (typeof data['isLibrary'] == 'undefined') {
				template_single_image += '		<div class="function-panel">';
				template_single_image += '			<div class="item btn-draggable qb-img-tool">';
				template_single_image += '				<i class="fa fa-arrows"></i>';
				template_single_image += '			</div>';
				template_single_image += ' 			<div class="item btn-dropdown-menu dropdown qb-img-tool">';
				template_single_image += '          	<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
				template_single_image += '      	 		<i class="fa fa-ellipsis-h"></i>';
				template_single_image += '       		</button>';
				template_single_image += '       		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
				if(web.MV922==="ADMIN")
					template_single_image += 					'<li class="btnadmin-set-tolibrary"><a><i class="fa fa-bookmark-o"></i>Add to library</a></li>';
				template_single_image += '					<li class="btn-set-action border-top"><a><i class="fa fa-link"></i>' + getLocalize("jsobt_title4") + '</a></li>';
				/*template_single_image += '        			<li class="btn-set-background img-comp"><a><i class="fa fa-picture-o"></i>' + getLocalize("web_edit_changebg") + '</a></li>';*/
				template_single_image += '        			<li class="btn-option"><a><i class="fa fa-cog"></i>' + getLocalize("weh_object_style") + '</a></li>';
				template_single_image += '					<li class="btn-comp-duplicate"><a><i class="fa fa-copy"></i>' + getLocalize("setting_duplicate") + '</a></li>';
				template_single_image += 					'<li class="btn-comp-copy"><a><i class="icon-display"></i>' + 'Copy (Ctrl + C)' + '</a></li>';
				template_single_image += '        			<li class="btn-set-anchor border-top"><a ><i class="fa fa-anchor"></i><label>' + getLocalize("jsimt_title2") + '</label></a></li>';
				template_single_image += '      			<li class="btn-del border-top qb-edit-tool"><a><i class="fa fa-trash-o"></i>' + getLocalize("delete") + '</a></li>';
				template_single_image += '       		</ul>';
				template_single_image += '			</div>';
				template_single_image += '		</div>';
			}
			template_single_image += '		</div>';

			template_single_image += '</div>';
			return template_single_image;
		},

		buildTemplateMultiImage : function(data) {
			var self = this, template_multi_image = '';
			template_multi_image += '<div class="grid-stack-item-content comp-image ' + data['stat'] + '" data-mode="' + data['mode'] + '" data-layout="' + data['layout'] + '">';
			template_multi_image += '	<div class="qb-component">';
			var i = 0;
			for ( var img in data['img']) {
				self.bgDef = 'background-image: url(' + data['img'][i] + '); background-position: center center;background-size: cover;';
				template_multi_image += self.buildSingleThumbImageBox(i, {
					mode : data['mode'],
					stat : data['stat'],
					filter : typeof data['filter'] != 'undefined' ? data['filter'][i] : '',
					css : (data['stat'] === 'new' && typeof data['css'] != 'undefined') ? data['css'][i] : this.bgDef,
					realSrc : typeof data['realSrc'] != 'undefined' ? data['realSrc'][i] : '',
					imgSrc : data['img'][i],
					actionType : data['actionType'] || null,
					actionLink : data['actionLink'] || null,
					actionLinkTarget : data['actionLinkTarget'] || null,
				});
				i++;
			}
			template_multi_image += '	</div>';

			template_multi_image += '		<div class="function-center-z qb-img-tool">';
			template_multi_image += '			<div class="z-item item animated btn-save-croped-img qb-img-tool">';
			template_multi_image += '				<i class="fa fa-check"></i>';
			template_multi_image += '				<span>' + getLocalize("save") + '</span>';
			template_multi_image += '			</div>';
			template_multi_image += '			<div class="z-item item animated btn-cancel-crop-img qb-img-tool">';
			template_multi_image += '				<i class="fa fa-close"></i>';
			template_multi_image += '				<span>' + getLocalize("cancel") + '</span>';
			template_multi_image += '			</div>';
			template_multi_image += '			<div class="z-item item animated btn-rotate-right qb-img-tool">';
			template_multi_image += '				<i class="fa fa-rotate-right"></i>';
			template_multi_image += '				<span>' + getLocalize("jsimt_title3") + '</span>';
			template_multi_image += '			</div>';
			template_multi_image += '			<div class="z-item item animated btn-image-effect qb-img-tool">';
			template_multi_image += '				<i class="fa fa-magic"></i>';
			template_multi_image += '				<span>' + getLocalize("filter") + '</span>';
			template_multi_image += '			</div>';
			template_multi_image += '			<div class="item animated btn-edit-img-cuted qb-img-tool qb-img-tool">';
			template_multi_image += '				<i class="fa fa-pencil"></i>';
			template_multi_image += '			</div>';
			template_multi_image += '		</div>';

			template_multi_image += '		<div class="function-panel">';
			template_multi_image += '			<div class="item btn-draggable qb-img-tool">';
			template_multi_image += '				<i class="fa fa-arrows"></i>';
			template_multi_image += '			</div>';
			template_multi_image += ' 			<div class="item btn-dropdown-menu dropdown qb-img-tool">';
			template_multi_image += '          		<button class="btn btn-default dropdown-toggle" type="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">';
			template_multi_image += '          			<i class="fa fa-ellipsis-h"></i>';
			template_multi_image += '         		</button>';
			template_multi_image += '         		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">';
			if(web.MV922==="ADMIN")	
				template_multi_image += 					'<li class="btnadmin-set-tolibrary"><a><i class="fa fa-bookmark-o"></i>Add to library</a></li>';
			template_multi_image += '         			<li class="btn-option border-top"><a><i class="fa fa-cog"></i>' + getLocalize("weh_object_style") + '</a></li>';
			template_multi_image += '         			<li class="btn-set-background img-comp"><a><i class="fa fa-picture-o"></i>' + getLocalize("web_edit_changebg") + '</a></li>';
			template_multi_image += '					<li class="btn-comp-duplicate"><a><i class="fa fa-copy"></i>' + getLocalize("setting_duplicate") + '</a></li>';
			template_multi_image += 					'<li class="btn-comp-copy"><a><i class="icon-display"></i>' + 'Copy (Ctrl + C)' + '</a></li>';
			template_multi_image += '         			<li class="btn-set-anchor border-top"><a ><i class="fa fa-anchor"></i><label>' + getLocalize("jsimt_title2") + '</label></a></li>';
			template_multi_image += '        			<li class="btn-del border-top qb-edit-tool"><a><i class="fa fa-trash-o"></i>' + getLocalize("delete") + '</a></li>';
			template_multi_image += '         		</ul>';
			template_multi_image += '			</div>';
			template_multi_image += '		</div>';

			template_multi_image += '</div>';
			return template_multi_image;
		},

		buildSingleThumbImageBox : function(i, data, hasWrapLink) {
			var template_thumbbox_image = '';

			template_thumbbox_image += '<div class="box_thumb_img number_img_' + (i + 1) + '" data-index="' + (i + 1) + '">';
			template_thumbbox_image += '	<div class="thumb-img-box" style="' + data['css'] + '">';
			if (hasWrapLink) {
				template_thumbbox_image += '<a class="obutton-link"';
				template_thumbbox_image += '	   data-action-type="' + data['actionType'] + '"';
				template_thumbbox_image += '	   data-action-link="' + data['actionLink'] + '"';
				template_thumbbox_image += 'data-action-link-target="' + data['actionLinkTarget'] + '">';
			}
			template_thumbbox_image += '		<img class="' + data['stat'] + ' need-effect ' + (data['filter'].length > 0 ? 'has-effect' : '') + '"';
			template_thumbbox_image += '			   src="' + data['imgSrc'] + '"';
			template_thumbbox_image += ' 	data-real-src="' + data['realSrc'] + '"';
			template_thumbbox_image += '   	  data-filter="' + data['filter'] + '" ';
			template_thumbbox_image += ' 		 	style="filter:' + data['filter'] + ';-webkit-filter:' + data['filter'] + ';" />';

			if (hasWrapLink)
				template_thumbbox_image += '</a>';

			template_thumbbox_image += '		<div class="thumb-img-tool qb-img-tool">';
			template_thumbbox_image += '			<span class="edit_box_thumb_img pull-left animated qb-img-tool">';
			template_thumbbox_image += '				<i class="fa fa-camera"></i>';
			template_thumbbox_image += '			</span>';
			template_thumbbox_image += '			<span class="btn-zoom-in pull-left animated qb-img-tool">';
			template_thumbbox_image += '				<i class="fa fa-plus"></i>';
			template_thumbbox_image += '			</span>';
			template_thumbbox_image += '			<span class="btn-zoom-out pull-left animated qb-img-tool">';
			template_thumbbox_image += '				<i class="fa fa-minus"></i>';
			template_thumbbox_image += '			</span>';
			template_thumbbox_image += '			<span class="btn-rotate-img pull-left animated qb-img-tool">';
			template_thumbbox_image += '				<i class="fa fa-rotate-right"></i>';
			template_thumbbox_image += '			</span>';
			template_thumbbox_image += '		</div>';

			template_thumbbox_image += '	</div>';// thumb-img-box
			template_thumbbox_image += '</div>';// box_thumb_img

			return template_thumbbox_image;
		}
	}
}());
