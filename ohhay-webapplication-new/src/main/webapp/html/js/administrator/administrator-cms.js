/**
 * ThoaiVt create Feb 25, 2016
 */
(function() {
	administratorCMS = {
		elementSelectPublish : null,
		/*
		 * init
		 */
		init : function() {
			administratorCMS.evenListener();
			administratorCMS.dialogForGet = $("#cms-publish-template").dialog({
				autoOpen : false,
				modal : true,
				resizable : false,
				closeOnEscape : true,
				draggable : false,
				dialogClass : "dialog-thumbail-templatecms",
				width : 610,
				height : 350,
				position : {
					my : 'center',
					at : 'center'
				},
				show : 'fade',
				hide : 'fade',
				buttons : {
					"Edit" : {
						click : function() {

						},
						text : "Edit",
						class : 'btn-editcms-confirm'
					}
					,"Publish" : {
						click : function() {

						},
						text : "Un/Publish",
						class : 'btn-ok-confirm'
					}
				}
			});
		},
		/*
		 * event Listener
		 */
		evenListener : function() {
			$(document).on('click', '#cms-publish-template .data-thumbail .choice .choice-file-img', function() {
				$('#file-update-templatecms').trigger('click');
			});
			$('#file-update-templatecms').on("change", function(evt) {
				var webId = $(this).attr("webId");
				administratorCMS.chooseFileTemplate(evt, webId);
			});
			$(document).on('click', '.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child.qb-administrator-content-cms .data-content .data-value .function-content .function-data li.btn-publish-and-unpublish', function() {
				// get status present
				var status = (($(this).closest(".data-value").find(".status-publish").attr('status')) == 1 ? status = 0 : status = 1);
				// get id for template
				var idWeb = $(this).parent().attr("idweb");
				var imageThs = $(this).closest('.item-template').find('.img-content .check-imag-eerror').attr('src');
				$('#cms-publish-template .data-thumbail .choice .img-content').attr('src', imageThs);
				$('.dialog-thumbail-templatecms .btn-ok-confirm').attr('idWeb', idWeb);
				$('.dialog-thumbail-templatecms .btn-ok-confirm').attr('status', status);
				$('.dialog-thumbail-templatecms .btn-editcms-confirm').attr('idWeb', idWeb);
				$('.dialog-thumbail-templatecms .btn-editcms-confirm').attr('status', status);
				$('#cms-publish-template .data-thumbail .choice .nameTemplate .name-data').val($(this).closest('.item-template').attr('nameCMS'));
				$('#file-update-templatecms').attr('webId', idWeb);
				administratorCMS.elementSelectPublish = $(this);
// if
// (administratorCMS.elementSelectPublish.closest(".data-value").find('.status-publish').attr('status')
// == '0')
					// opendialog
				administratorCMS.dialogForGet.dialog("open");
				var arFa950s = $(this).closest(".item-template").attr("category");
				administratorCMS.listCategory(0,arFa950s);
// else
// // call up status template
// administratorCMS.changeEn402(idWeb, status,
// administratorCMS.elementSelectPublish);

			});
			
			/*
			 * change category
			 */
			$(document).on('change', '#cms-publish-template .checkbox label input[type="checkbox"]', function() {
			    if(this.checked) {
			      // checkbox is checked
			    	console.log("Checked");
			    }else{
			    	console.log("nope");
			    }
			});
			
			
			/*
			 * only update ev403 and ev401
			 */
			$(document).on('click', '.dialog-thumbail-templatecms .btn-editcms-confirm', function() {
				 var idWeb = $(this).attr('idWeb');
				 var status = $(this).attr('status');
				 // get url image
				 var urlImage = $('#cms-publish-template .data-thumbail .choice .img-content').attr("src");
				 // get id web
				 var ev401 = $('#cms-publish-template .data-thumbail .choice .nameTemplate .name-data').val();
				 var arSelectCategory = administratorCMS.checkCategorySelected();
				 var el=administratorCMS.elementSelectPublish;
				 if(arSelectCategory.length>0){
					administratorCMS.updateCategoryPage(idWeb,arSelectCategory,el);
				 }
				 if (urlImage != undefined) {
					 if (urlImage.trim() != util.contextPath() + "/html/images/no-image.gif" && idWeb.trim() != "" && ev401.trim() != "") {
					 console.log("Update");
					 // update ev403
						 administratorCMS.changeEv403(idWeb, urlImage, ev401);
						 administratorCMS.dialogForGet.dialog("close");
					 } else
						 util.messageDialog(getLocalize("admp_title10"));
				 } else
					 util.messageDialog(getLocalize("admp_title10"));
				 // set url for template	
				 administratorCMS.elementSelectPublish.closest(".item-template").find(".img-content img").attr('src', urlImage);
				 // close dialog
			});
			/*
			 * publish / unpublish template cms
			 */
			$(document).on('click', '.dialog-thumbail-templatecms .btn-ok-confirm', function() {
				var idWeb = $(this).attr('idWeb');
				var status = $(this).attr('status');
				// get url image
				var urlImage = $('#cms-publish-template .data-thumbail .choice .img-content').attr("src");
				// get id web
				var ev401 = $('#cms-publish-template .data-thumbail .choice .nameTemplate .name-data').val();
				if (urlImage != undefined) {
					if (urlImage.trim() != util.contextPath() + "/html/images/no-image.gif" && idWeb.trim() != "" && ev401.trim() != "") {
						console.log("Update");
						// call up status template
						administratorCMS.changeEn402(idWeb, status, administratorCMS.elementSelectPublish);
						// update ev403
						administratorCMS.changeEv403(idWeb, urlImage, ev401);
						administratorCMS.dialogForGet.dialog("close");
					} else
						util.messageDialog(getLocalize("admp_title10"));
				} else
					util.messageDialog(getLocalize("admp_title10"));
				// set url for template
				administratorCMS.elementSelectPublish.closest(".item-template").find(".img-content img").attr('src', urlImage);
				// close dialog
			});

		},
		/*
		 * load template CMS
		 */
		loadCMS : function(limit, offset) {
			// get session choice page
			var limitSession = parseInt(localStorage.getItem("function-limit"));
			$.ajax({
				url : encodeUrl("adminTemplateCMS.loadListTemplateCMS"),
				data : "offset=" + encodeURIComponent(offset) + "&limit=" + encodeURIComponent(1000),
				success : function(response) {
					try {
						console.log("Update template");
						var data = response.result;
						var html = "";
						for (var i = 0; i < data.length; i++) {
							// set size page and value limit
							if (i == 0) {
								// if(data[i].sizeRowss!=null){
								// adminSetting.sizePage=data[i].sizeRowss;
								// ((offset==0)? adminSetting.padingTionPage =
								// "<div
								// class='data-padingtion'>"+adminSetting.genericPadingtion(adminSetting.getPadingtionPage(adminSetting.sizePage,
								// adminSetting.limit),adminSetting.limit)+"</div>"
								// : "" );
								// }
							}
							html += '<div class="row item-template" category="'+(data[i].fa950s!=null ? data[i].fa950s.toString() : '')+'" nameCMS="'+(data[i].ev401!=null ? data[i].ev401 : '')+'">';
							html += '	<div class="col-xs-4 img-content hvr-shrink">';
							html += '		<img idfor=' + data[i].id + ' class="check-imag-eerror" src="' + data[i].ev403 + '" />';
							html += '	</div>';
							html += '	<div class="col-xs-8 data-content">';
							html += '		<div class="data-value">';
							html += '			<div class="title-content">';
							html += '				<p class="name-content-template">';
							html += data[i].ev405 + '<span class="status-publish" status="' + data[i].en402 + '">' + ((data[i].en402 == 1) ? "Published" : "Not publish") + '</span>';
							html += '				</p>';
							html += '				<p class="detail-date">';
							html += '					Create : ' + data[i].el448String + ' ( by <i class="name-info">' + data[i].nv100 + '</i> ) | Modify : ' + data[i].el446String;
							html += '				</p>';
							html += '				<p class="all_using">';
							html += '					Using : ' + data[i].totalUser;
							html += '				</p>';
							html += '			</div>';
							html += '			<div class="function-content">';
							html += '				<ul class="function-data" idweb="' + data[i].id + '">';
							html += '					<li class="btn-preview" onclick="adminSetting.previewPageTemplate(' + data[i].id + ')" data-toggle="tooltip" title="Preview">';
							html += '						<i class="fa fa-search-plus"></i>';
							html += '					</li>';
							html += '					<li class="btn-publish-and-unpublish" data-toggle="tooltip" title="Publish/Not Publish" status="' + data[i].en402 + '">';
							html += '						<i class="fa fa-rss"></i>';
							html += '					</li>';
							/*
							 * html +='
							 * <li class="btn-edit-image-template" data-toggle="tooltip" title="Update image template" >';
							 * html +=' <i class="fa fa-send"></i>'; html +='
							 * </li>';
							 */
							if (data[i].roleAdmin == 1) {
								html += '					<li class="btn-edit-template"  data-toggle="tooltip" title="Edit template">';
								html += '						<a href=' + $("#contextPath").val() + "/e" + data[i].id + "/evo-editer" + '?editmode=element' + ' target="_blank"><i class="fa fa-pencil"></i></a>';
								html += '					</li>';
							}
							html += '				</ul>';
							html += '			</div>';
							html += '		</div>';
							html += '	</div>';
							html += '</div>';
						}
// console.log(html);
					} catch (e) {
						console.log("error : " + e);
					}
// console.log(html);
					// set html for content selected
					$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child.qb-administrator-content-cms .content-data-load-item').html(html);
					console.log("Paddingtion : " + adminSetting.padingTionPage);
					$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .qb-content-administrator-child .padingtion-page').html(adminSetting.padingTionPage);
					$('[data-toggle="tooltip"]').tooltip();
					adminSetting.imageErrorUpdate();
				},
				error : function(error) {
					console.log("ERROR : " + error);
				}
			});
		},
		/*
		 * choice file Image Update
		 */
		chooseFileTemplate : function(evt, webId) {
			var files = evt.target.files;
			var file = files[0];
			if (files && file) {
				var reader = new FileReader();
				reader.onload = function(readerEvt) {
					var binaryString = readerEvt.target.result;
					administratorCMS.updateImage(btoa(binaryString), webId);
				};
				reader.readAsBinaryString(file);
			}
		},
		/*
		 * Update image
		 */
		updateImage : function(base64, webId) {
			console.log("ID : " + webId);
			base64 = "data:image/jpeg;base64," + base64;
			// init form
			formData = new FormData();
			if (isNaN(webId))
				webId = webId.split('c')[1];
			// set idweb
			formData.append('webId', webId);
			// set image base64
			formData.append('imgBase64', base64);
			// set type for ...
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
						$('#cms-publish-template .data-thumbail .choice .img-content').attr('src', url);
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
		 * change en402 and e
		 */
		changeEn402 : function(webId, status, element) {
			// find class status and set attribute
			element.closest(".data-value").find(".status-publish").attr('status', status);
			// set value publish cms template

			var administratorCriteria = new FormData();
			administratorCriteria.append("id", webId);
			administratorCriteria.append("en402", status);
			$.ajax({
				type : "POST",
				url : encodeUrl("adminTemplateCMS.changePublishTemplate"),
				cache : false,
				contentType : false,
				processData : false,
				data : administratorCriteria,
				success : function(response) {
					if (response.status == AJAX_SUCESS) {
						// set status
						element.closest(".data-value").find('.status-publish').attr('status', status);
						// set html
						var valueHtml = element.closest(".data-value").children().find(".status-publish");
						// status is publish
						if (status == 1) {
							var urlImage = element.closest(".item-template").children().find("img").attr("src");
							$('#cms-publish-template .data-thumbail .choice .img-content').attr("src", urlImage);
							$('#file-update-templatecms').attr("webId", webId);
							/*
							 * open update image
							 */
							valueHtml.html("Publish");
							// administratorCMS.dialogForGet.dialog("open");
						} else
							valueHtml.html("Not Publish");
// administratorCMS.loadCMS(1000,0);
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
		 * change ev403
		 */
		changeEv403 : function(webId, urlImg, ev401) {
			var administratorCriteria = new FormData();
			administratorCriteria.append("id", webId);
			administratorCriteria.append("ev403", urlImg);
			administratorCriteria.append("ev401", ev401);
			$.ajax({
				type : "POST",
				url : encodeUrl("adminTemplateCMS.editTemplate"),
				cache : false,
				contentType : false,
				processData : false,
				data : administratorCriteria,
				success : function(response) {
					if (response.status == AJAX_SUCESS) {
						// status is publish
						if (status == 1){
							/*
							 * open update image
							 */
							administratorCMS.dialogForGet.dialog("open");
							
						}
						administratorCMS.loadCMS(1000,0);
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
		 * list category
		 */
		listCategory : function(idcategory,arFa950s){
			$.ajax({
				type : "GET",
				url : encodeUrl("adminTemplateCMS.listCategorys"),
				data : "idcategory=" + encodeURIComponent(idcategory) ,
				success : function(response) {
					if (response.status == AJAX_SUCESS) {
						// status is publish
							/*
							 * open update image
							 */
							var data = response.result;
							var langCr = $('#evo-current-lang').val();
							console.log(data);
							var html = "";
							for (var i = 0; i < data.length; i++) {
								html += '<div class="col-sm-4">';
								html += '	<div class="checkbox" idcategory='+data[i].id+'  '+'>';
								html += '		<label style="font-size: 1em"> <input type="checkbox" '+((administratorCMS.conditionChecked(data[i].id,arFa950s)===true ? 'checked' : '')) +' value="">';
								html += '			<span class="cr"><i class="cr-icon fa fa-check"></i>';
								html += '			</span>'+((langCr==="en") ? data[i].av101 : ((langCr==="vi") ? data[i].av103 :data[i].av104 ));
								html += '		</label>';
								html += '	</div>';
								html += '</div>';
							}
							console.log(html);
							$('#cms-publish-template .content-data-category').html(html);
					} else {
						showGrowlMessageError();
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		checkCategorySelected : function(){
			var listCategory = [];
			 $('#cms-publish-template .checkbox label input[type="checkbox"]').each(function(){
				 var el = $(this);
				 if(this.checked) {
					 var idCategory = el.closest(".checkbox").attr("idcategory");
					 listCategory.push(idCategory);
				 }
		     });
			 return listCategory;
		}
		,updateCategoryPage : function(idPage,listCategory,el){
			 var dataJoinPost = {
					 "id": idPage,
					 "listCategory": listCategory
			 }
			 
			 var jsonObjectData = JSON.stringify(dataJoinPost);
			 
			 $.ajax({
					type : "POST",
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					dataType : 'json',
					url : encodeUrl("adminTemplateCMS.updateCategorys"),
					data : jsonObjectData,
					success : function(response) {
						if (response.status == 'SUCCESS') {
							el.closest('.item-template').attr('category',listCategory.toString());
						}
					}
				});
			 console.log(listCategory);
		},
		conditionChecked : function(arCategory,arFa950s){
			var arFa950sRl = arFa950s.split(",");
			console.log(arFa950sRl);
			for (var j = 0; j < arFa950sRl.length; j++) {
				console.log(arFa950sRl[j]+" : "+arCategory+" = "+(parseInt(arCategory)===parseInt(arFa950sRl[j])));
				if(parseInt(arCategory)===parseInt(arFa950sRl[j])){
					return true;
				}
			}
			return false;
		}
	}
}());