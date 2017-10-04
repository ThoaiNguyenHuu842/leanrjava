/**
 * @author ThoaiVt date 28/12/2015
 */
(function() {
	evotemplates = {
		init : function() {
			evotemplates.eventListener();
		},
		eventListener : function() {
			evotemplates.loadListTemplate();
			$(document).on('click', '#top_menu_login_evo .item_menu_top .item_menu .item .dropdown li', function() {
				var lang = $(this).attr("lang");
				var url = util.contextPath();
				setParam("language", lang, document.URL);
				$('#top_menu_login_evo .item_menu_top .item_menu .item .dropdown #dropdownMenu1').html("")
				var textLang = ((lang == "vi") ? "tiếng việt" : ((lang == "de") ? "Deutsch" : "English"));
				$('#top_menu_login_evo .item_menu_top .item_menu .item .dropdown #dropdownMenu1').html(textLang);

			});
			$(document).on('click', '.qb-ohhay-content-wrapper #top_menu_login .item_menu_top .item_menu .item.evo_template', function() {
				var type = $(this).attr("type");
				window.open(util.contextPath() + "/" + type);
			});
			$(document).on('click', '.qb-ohhay-content-wrapper .evo-template-item .obutton-label-data .tg-hover .tg-hover-content a.tg-button[type="edit"]', function() {
				var tempId = $(this).parents('.evo-template-item').attr('templateid');
				if ($('#qb-input-qv101').val().length == 0)
					window.location = util.contextPath() + "/trial?webId=" + tempId;
				else
					mySites.checkRightCreateWeb({
						action : function() {
							evotemplates.createWeb(tempId);
						}
					});
			});
			$(document).on('click', '#btn-create-new-fromevotemp', function() {
				mySites.checkRightCreateWeb({
					action : function() {
						mySites.create();
					}
				});
			});

			$(document).on('click', '#btn-create-new-re', function() {
				mySites.checkRightCreateWeb({
					action : function() {
						mySites.createResponsiveSite();
					}
				});
			});

		}, /*
			 * load list template
			 */
		loadListTemplate : function() {
			$.ajax({
				url : encodeUrl("evoTemplateBean.loadListTemplate"),
				data : {},
				success : function(response) {
					var htmlString = "";
					if (response.result && response.result.length > 0) {
						for (index = response.result.length - 1; index >= 0; index--) {
							var item = response.result[index];
							htmlString += '<div templateid="' + item.id + '" class="col-sm-4 evo-template-item">';
							htmlString += '	<div class="obutton-label-data">';
							htmlString += '		<img src="' + item.ev403 + '" />';
							htmlString += '<div class="tg-hover">';
							htmlString += '<div class="tg-hover-content">';
							htmlString += '<a class="tg-button -blue" href="' + item.ev405 + '" target="_blank" type="view">';
							htmlString += '<i class="fa fa-search-plus"></i>';
							htmlString += '<span>' + getLocalize("evot_view") + '</span>';
							htmlString += '</a>';
							htmlString += '<a class="tg-button -blue" target="_blank" type="edit">';
							htmlString += '<i class="fa fa-pencil"></i>';
							htmlString += '<span>' + getLocalize("evot_edit") + '</span>';
							htmlString += '</a>';
							htmlString += '</div>';
							htmlString += '</div>';
							htmlString += '	</div><h2 class="text_item">' + item.ev401 + '</h2>';
							/*
							 * '<a
							 * href="'+util.contextPath()+'/trial?webId='+item.id+'">'+ '<div
							 * class="obutton-link hvr-bounce-out"
							 * style="cursor: pointer;border: medium
							 * none;height: 300px;">'+ '<img
							 * src="'+util.contextPath()+'/html/images/thumb.jpg"
							 * style="font-size: 48px; width: 100%; height: 78%;
							 * color: #073763; display: table-cell;
							 * vertical-align: middle;float:left;border-radius:
							 * 10px;border: 1px solid #e8e8e8;" />'+ '<h2 style="font-size: 20px; width: 100%; height: 20%; color: #073763; display: table-cell; vertical-align: middle;float:left;">'+item.ev401+'</h2>'+ '</div>'+ '</a>'
							 */

							htmlString += '</div>';
						}
						console.log(htmlString);
						$('.body-evo-templates .qb-ohhay-content-wrapper .data_evotemplate .row').append(htmlString);
						$('[templateid="blank_template"], .item_new_blank_re-website').fadeIn();
					}
					// $('.qb-ohhay-content-wrapper
					// .template-col-panel').fadeIn("500");
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * create web site
		 */
		createWeb : function(templateId) {
			$.ajax({
				type : "POST",
				url : encodeUrl("evoTemplateBean.createWeb"),
				data : {
					templateId : templateId
				},
				success : function(response) {
					if (response.result > 1)
						window.location = util.contextPath() + "/e" + response.result + "/" + response.result2 + "?editmode=element";
					else if (response.result == -2)
						util.confirmDialog(response.mesg, function() {
							window.location = util.contextPath() + "/pricing";
						});
					else
						showGrowlMessageError();

				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		}
	}
}());