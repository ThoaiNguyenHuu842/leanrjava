/**
 * @author ThoaiNH create Jan 13, 2016
 */
(function() {
	evoLib = {
		libType : 2,
		numberPage : 0,// total summation page
		limit : 0,// limit page load
		init : function() {
			$('#qb-dlg-evo-lib').webDialog(952);
			$('#dia-evo-lib .content-data-box').niceScroll();
			this.eventListener();
		},
		eventListener : function() {

			/*
			 * on change tab image src
			 */
			$(document).on('click', '#qb-dlg-evo-lib .qb-tab-panel .tab-menu', function(event) {
				$('#qb-dlg-evo-lib .qb-tab-panel .tab-menu').removeClass('active');
				$(this).addClass('active');
				$('#qb-dlg-evo-lib .qb-tab-panel .body .tab-panel').removeClass('active');
				if ($(this).hasClass('tab-menu-1')) {
					$('#dia-evo-lib .type_box_search .dropdown_type .dropdown .btn-primary .title').html(getLocalize("dlib_my_library"));
					$('#qb-dlg-evo-lib .qb-tab-panel .body .tab-panel-1').addClass('active');
					evoLib.libType = 1;
					evoLib.load(evoLib.offset, 4);
				} else if ($(this).hasClass('tab-menu-2')) {
					$('#dia-evo-lib .type_box_search .dropdown_type .dropdown .btn-primary .title').html(getLocalize("dlib_public_library"));
					$('#qb-dlg-evo-lib .qb-tab-panel .body .tab-panel-2').addClass('active');
					evoLib.libType = 2;
					evoLib.load(evoLib.offset, 4);
				}
			});
			/*
			 * on click add to web
			 */
			$(document).on('click', '#qb-dlg-evo-lib .btn-add-to-web', function(event) {
				var boxId = $(this).parents('.data-box').attr('boxid');
				evoLib.addToWeb(boxId);
			});
			/*
			 * on click remove from lib
			 */
			$(document).on('click', '#qb-dlg-evo-lib .btn-remove', function(event) {
				var boxId = $(this).parents('.data-box').attr('boxid');
				var itemtype = $(this).parents('.data-box').attr('itemtype');
				util.confirmDialog(getLocalize("jsevl_title1"), function() {
					evoLib.removeFromLib(boxId, itemtype);
				});
			});
			/*
			 * onclick search
			 */
			$(document).on('click', '#qb-dlg-evo-lib .fa-search', function(event) {
				evoLib.load(evoLib.offset, 4);
			});
			$(document).on('keydown', '#qb-dlg-evo-lib .txt-search', function(event) {
				if (event.which == 13) {
					evoLib.load(evoLib.offset, 4);
				}
			});
			$(document).on('click', '#dia-evo-lib .qb-tab-panel .content-data-box .data-box .info-data-page .info-data p .otag', function(event) {
				$("#qb-dlg-evo-lib .txt-search").val($(this).attr("tag"));
				evoLib.load(evoLib.offset, 4);
			});
			/*
			 * on click fillter
			 */
			$(document).on('click', '#dia-evo-lib .qb-tab-panel .header .menu-seleted .menu_chooise li', function(event) {
				$('#dia-evo-lib .qb-tab-panel .header .menu-seleted .menu_chooise li').removeClass('active');
				$(this).addClass('active');
				evoLib.load(evoLib.offset, 4);
			});
			/*
			 * on click get to my lib
			 */
			$(document).on('click', '#qb-dlg-evo-lib .btn-iget', function(event) {
				var boxId = $(this).parents('.data-box').attr('boxid');
				evoLib.getToMyLib(boxId);
			});

			/*
			 * event data scroll bottom in div #dia-evo-lib .qb-tab-panel
			 * .content-data-box
			 */
			$('#dia-evo-lib .content-data-box').niceScroll().scrollend(function(info) {
				// get height div scroll
				var heightParent = $('#dia-evo-lib .qb-tab-panel .content-data-box').height();
				// get height nicescroll
				var dataNiceScroll = $('#dia-evo-lib .qb-tab-panel .content-data-box').getNiceScroll().resize();
				// check page current less total page then load ajax
				if ((evoLib.limit - 4) < evoLib.numberPage && info.end.y == dataNiceScroll[0]["page"]["maxh"])
					evoLib.load(evoLib.offset, evoLib.limit);
			});
		},
		/*
		 * open dialog evo library
		 */
		open : function(libType) {
			$("#qb-dlg-evo-lib .txt-search").val('');
			// init when first call
			if (!this.inited) {
				this.init();
				this.inited = true;
			}
			$('#qb-dlg-evo-lib').dialog('close');
			$('#qb-dlg-evo-lib').dialog('open');
			$('#qb-dlg-evo-lib .qb-tab-panel .tab-menu-' + libType).trigger('click');
		},
		/*
		 * get it to my library
		 */
		getToMyLib : function(pe920) {
			$.ajax({
				type : "POST",
				url : encodeUrl("libraryBean.getToMyLib"),
				data : {
					pe920 : pe920,
				},
				success : function(response) {
					if (response.result > 0) {
						// open my library
						$("#qb-dlg-evo-lib .txt-search").val("");
						$('#qb-dlg-evo-lib .qb-tab-panel .tab-menu-1').trigger('click');
					} else if (response.result == -1)
						util.messageDialog(getLocalize("jsevl_title2"));
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * add a lib to current site
		 */
		addToWeb : function(pe920) {
			$.ajax({
				url : encodeUrl("libraryBean.loadSectionData"),
				data : {
					pe920 : pe920,
				},
				success : function(response) {
					var orgrinal = response.result;
					if (orgrinal.editAble == 1) {
						if (evoLib.checkSectionRefHasAdded(pe920) == true) {
							util.messageDialog(getLocalize("dlib_title1"));
							return;
						}
					}
					var listComponent = response.result2;
					/*
					 * copy box model
					 */
					var box = jQuery.extend(true, {}, orgrinal);
					// remove anchor name
					delete box[WEB_PRO_NAME];
					var boxId = bigBoxModelManager.getNewId();
					box[WEB_PRO_WEBID] = webCreater.data[WEB_PRO_ID];
					box[WEB_PRO_ID] = boxId;
					box[WEB_PRO_INDEX] = ++bigBoxModelManager.crIndex;
					box[WEB_PRO_FE920] = orgrinal[WEB_PRO_ID];
					// when lib id reference
					if (orgrinal.editAble == 1)
						box[WEB_PRO_LIBTYPE] = 3;// set data is reference
					// from lib
					else
						box[WEB_PRO_LIBTYPE] = 0;// set data is non-lib
					bigBoxModelManager.addItemToModel(box);
					// THOANH UPDATE 09/07/2016 allow add footer, header to
					// library
					if (box.type == 'footer') {
						if (!$("#content-wrapper").find(".qb-footer").length)
							bigbox.addFooter(box);
						else {
							util.messageDialog(getLocalize("weh_message_addfooter"));
							return;
						}
					} else if (box.type == 'header') {
						if (!$("#content-wrapper").find(".qb-header").length)
							bigbox.addHeader(box);
						else {
							util.messageDialog(getLocalize("weh_message_addheader"));
							return;
						}
					} else
						bigbox.add(box);
					// END THOANH UPDATE 09/07/2016 allow add footer, header to
					// library
					if (orgrinal.editAble == 1) {
						/*
						 * ThoaiNH update 20/01/2015 disable editor of this
						 * section when reference from library
						 */
						$('.content-box[qb-box-id="' + boxId + '"]').addClass("content-box-from-lib");
						setTimeout(function() {
							bigBoxModelManager.disableEdit(boxId);
							for ( var id in componentModelManager.listComponent) {
								var component = componentModelManager.listComponent[id];
								if (component.boxId == boxId)
									delete componentModelManager.listComponent[id];
							}
						}, 1000);
					}
					/*
					 * load components
					 */
					for ( var id in listComponent) {
						var component = listComponent[id];
						var newComponent = jQuery.extend(true, {}, component);
						newComponent[WEB_PRO_BOXID] = box[WEB_PRO_ID];
						// create new id for component
						newComponent[WEB_PRO_ID] = componentModelManager.getNewId();
						// add to model when editable
						if (orgrinal.editAble == 0)
							componentModelManager.addItemToModel(newComponent);
						componentModelManager.loadComponent(newComponent);
					}

				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * remove section from library
		 */
		removeFromLib : function(pe920, itemtype) {
			$.ajax({
				type : "POST",
				url : encodeUrl("libraryBean.removeFromLib"),
				data : {
					pe920 : pe920,
					itemtype : itemtype
				},
				success : function(response) {
					if (response.result > 0) {
						$('#dia-evo-lib .data-box[boxid="' + pe920 + '"]').slideUp();
						// reset box status
						try {
							bigBoxModelManager.updateLibStatus(pe920, 0);
						} catch (e) {
							// TODO: handle exception
						}

					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * load library of web
		 */
		loadLibOfWeb : function(webId) {
			$.ajax({
				url : encodeUrl("libraryBean.loadLibOfWeb"),
				data : {
					webId : webId,
				},
				success : function(response) {
					if (response.result && response.result.length > 0) {
						for (index = 0; index < response.result.length; index++) {
							var item = response.result[index];
							if (item.editAble == 0)
								$('.content-box[qb-box-id="' + item.id + '"]').append('<div title="' + getLocalize("dlib_title2") + '" class="btn-publish-iget"><a class="item-iget-green item-iget"></a></div>');
							else
								$('.content-box[qb-box-id="' + item.id + '"]').append('<div title="' + getLocalize("dlib_title3") + '" class="btn-publish-iget"><a class="item-iget-white item-iget"></a></div>');
						}
						/*
						 * on click get to my lib
						 */
						$(document).on('click', '.content-box .btn-publish-iget', function(event) {
							var boxId = $(this).parents('.content-box').attr('qb-box-id');
							$.ajax({
								type : "POST",
								url : encodeUrl("libraryBean.getToMyLib"),
								data : {
									pe920 : boxId,
								},
								success : function(response) {
									if (response.result > 0)
										util.messageDialog(getLocalize("jsevl_title3"));
									else if (response.result == -1)
										util.messageDialog(getLocalize("jsevl_title2"));
								},
								error : function(e) {
									showGrowlMessageAjaxError();
								}
							});
						});
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});

		},
		/*
		 * check section reference added before
		 */
		checkSectionRefHasAdded : function(pe920) {
			if (bigBoxModelManager.listBox[pe920])
				return true;
			else {
				for ( var id in bigBoxModelManager.listBox) {
					if (bigBoxModelManager.listBox[id][WEB_PRO_STT] != WEB_PRO_STT_REMOVED && bigBoxModelManager.listBox[id][WEB_PRO_FE920] == pe920)
						return true
				}
			}
			return false;
		},
		/*
		 * load library
		 */
		load : function(offset, limit) {
			starRate.inited=undefined;
			console.log("Load data !");
			var textSearch = $("#qb-dlg-evo-lib .txt-search").val();
			console.log(textSearch);
			if (limit == 4)
				evoLib.limit = 4;
			$.ajax({
				url : encodeUrl("libraryBean.load"),
				data : {
					libType : evoLib.libType,
					textSearch : textSearch,
					offset : 0,
					limit : limit
				},
				success : function(response) {
					var htmlString = '';
					var onwerFunction = "";
					var publicFunction = "";
					if (evoLib.limit == 4) {
						$.ajax({
							url : encodeUrl("libraryBean.load"),
							data : {
								libType : evoLib.libType,
								textSearch : textSearch,
								offset : 0,
								limit : 1000
							},
							success : function(response) {
								if (response.result.length > 0)
									evoLib.numberPage = response.result.length;
								console.log("ALL PAGE : " + evoLib.numberPage);
							},
							error : function() {

							}
						});
					}
					evoLib.limit += 4;
					if (response.result && response.result.length > 0) {
						for (index = 0; index < response.result.length; index++) {
							var item = response.result[index];
							var publicFunction = "";
							var onwerFunction = "";
							var itemType = "pub";
							// public and from other user
							if (evoLib.libType == 2 && item.fo100 != webCreater.data.fo100) {
								publicFunction += '						<li title="' + getLocalize("dlib_title4") + '" data-toggle="tooltip" data-placement="bottom" class="item btn-iget">';
								publicFunction += '							<a class="obutton-link"><i class="fa fa-book"></i></a>';
								publicFunction += '						</li>';
							}
							// my lib and don't get from public library
							if (evoLib.libType == 1) {
								itemType = "ref";
								onwerFunction += '						<li title="' + getLocalize("dlib_title5") + '" data-toggle="tooltip" data-placement="bottom" class="item btn-remove">';
								onwerFunction += '							<a class="obutton-link"><i class="fa fa-trash-o"></i></a>';
								onwerFunction += '						</li>';
								if (item.fo100 == webCreater.data.fo100) {
									itemType = "my";
									/*
									 * onwerFunction += ' <li class="item">';
									 * onwerFunction += ' <a
									 * class="obutton-link"><i class="fa
									 * fa-pencil"></i></a>'; onwerFunction += '
									 * </li>';
									 */
									onwerFunction += '						<li title="' + getLocalize("dlib_title6") + '" data-toggle="tooltip" data-placement="bottom" class="item">';
									onwerFunction += '							<a target="_blank" href="' + util.contextPath() + "/e" + item.webId + "/evo-editer?editmode=element" + '" class="obutton-link"><i class="fa fa-edit"></i></a>';
									onwerFunction += '						</li>';
								}
							}
							var type = '';
							var customizable = item.editAble == 0 ? '<span class="fa fa-unlock lib-editale"></span><span class="lib-editale-title">' + getLocalize("datl_customizable") + '</span>' : ' <span class="fa fa-rss lib-editale"></span><span class="lib-editale-title">' + getLocalize("dlib_title7") + '</span>';
							var type = item.libType == 2 ? ' <span title="' + getLocalize("dlib_title6") + '" data-toggle="tooltip" data-placement="bottom" class="fa fa-globe lib-privacy"></span><span class="lib-editale-title">' + getLocalize("setting_public") + '</span>' : ' <span title="' + getLocalize("datl_private") + '" data-toggle="tooltip" data-placement="bottom" class="fa fa-lock lib-privacy"></span><span class="lib-editale-title">' + getLocalize("datl_private") + '</span>';
							if (evoLib.libType == 2)
								type = "";
							var otags = '';
							if (item.otags && item.otags.length > 0) {
								otags = '<p class="info">Tags: ';
								for ( var i in item.otags) {
									var tag = '<span tag="' + item.otags[i] + '" class="otag"><span class="fa fa-circle"></span>' + item.otags[i] + '</span>';
									if (i == 0)
										tag = '<span tag="' + item.otags[i] + '" class="otag">' + item.otags[i] + '</span>';
									otags += tag;
								}

								otags += '</p>'
							}
							var thumbHtml = util.isEmpty(item.libThumb) ? "<div class=\"img-item\"></div>" : "<div style=\"background-image: url(&quot;" + item.libThumb + "&quot;);\" class=\"img-item\"></div>";
							htmlString += '<div itemtype="' + itemType + '" webid="' + item.webId + '" boxid="' + item.id + '" class="data-box">';
							htmlString += '		<div class="col-sm-6 item data-iframe">';
							htmlString += thumbHtml;
							htmlString += '		</div>';
							htmlString += '		<div class="col-sm-6 item info">';
							htmlString += ' 		<div class="info-data-page">';
							htmlString += '				<div class="info-data">';
							htmlString += '					<h2>' + item.libTitle + '</h2>';
							htmlString += '					<p style="color: rgb(148, 148, 148);font-style: italic;" class="info">by <span style="color: rgb(0, 153, 255);">' + item.ownerName + '</span>' + type + customizable + '</p>';
							htmlString += '					<p class="info">&nbsp</p>';
							htmlString += '					<p class="info">' + getLocalize("dlib_title8") + ': <span style="color: rgb(102, 102, 102)">' + item.bl949String + '</span>   <span style="margin-left: 10px;margin-right: 10px;">|</span>' + '   ' + getLocalize("dlib_title9") + ': <span style="color: rgb(102, 102, 102)">' + item.bl946String + '</span></p>';
							if (evoLib.libType == 2)
								htmlString += '					<p style="display:inline-block;"><span class="info">Rate: <div class="cover-star-data"><div class="rate-content" strt="'+item.voteStarAvg+'">' + starRate.setStartRating(item.voteStarAvg) + '</div><div class="fix-hover-select"><div class="cover-rating-container"><label>Rate</label><div class="action-rating" currentrate="'+item.reVote+'"></div></div></div></div><span class="use-and-rate"> (<span class="total-add">' + item.totalAdded + '</span> use  |  <span class="total-rate">' +item.totalRate+ '</span> rating)</span></span></p>';
							htmlString += otags;
							htmlString += '				</div>';
							htmlString += '				<div class="info-function">';
							htmlString += '					<ul class="function">';
							htmlString += '						<li title="' + getLocalize("setting_preview") + '" data-toggle="tooltip" data-placement="bottom" class="item">';
							htmlString += '							<a target="_blank" class="obutton-link" href=' + util.contextPath() + '/web' + item.webId + '/' + item.id + '><i class="fa fa-eye"></i></a>';
							htmlString += '						</li>';
							htmlString += '						<li title="' + getLocalize("dlib_title10") + '" class="item btn-add-to-web" data-toggle="tooltip" data-placement="bottom" title="' + getLocalize("dlib_title11") + '">';
							htmlString += '							<a class="obutton-link"><i class="fa fa-plus"></i></a>';
							htmlString += '						</li>';
							htmlString += onwerFunction;
							htmlString += publicFunction;
							htmlString += '					</ul>';
							htmlString += '				</div>';
							htmlString += '			</div>';
							htmlString += '		</div>';
							htmlString += '</div>';

							/*
							 * htmlString += ' <div class="info">'; htmlString += '
							 * <div>'; htmlString += ' <h3>Box '+item.id+'
							 * '+type+'</h3>'; htmlString += ' </div>';
							 * htmlString += ' <div>'; htmlString += ' <a
							 * href="'+util.contextPath()+'/'+item.webName+'">'+util.contextPath()+'/'+item.webName+'</a>';
							 * htmlString += ' </div>'; htmlString += ' <div>';
							 * htmlString += ' <label>Created by:
							 * '+item.ownerName+'</label>'; htmlString += '
							 * </div>'; htmlString += ' <div>'; htmlString += '
							 * <label>Last modify: '+item.bl946String+'</label>';
							 * htmlString += ' </div>'; htmlString += ' <div>';
							 * htmlString += ' <label>Date add:
							 * '+item.bl949String+'</label>'; htmlString += '
							 * </div>'; htmlString += ' <div>'; htmlString += '
							 * <label>Total use: '+item.totalUsed+'</label>';
							 * htmlString += ' </div>'; htmlString += otags
							 * htmlString += ' </div>'; htmlString += ' <iframe
							 * scrolling="no"
							 * src="'+util.contextPath()+'/web'+item.webId+'/'+item.id+'"></iframe>';
							 * htmlString += ' <div class="function">';
							 * htmlString += ' <span title="Add to web"
							 * class="btn-add-to-web fa fa-plus"></span>';
							 * htmlString += privateFunction; htmlString += ' <a
							 * target="blank"
							 * href="'+util.contextPath()+'/web'+item.webId+'/'+item.id+'"><span
							 * title="Review" class="fa fa-search-plus"></span></a>';
							 * htmlString += ' </div>'; htmlString += '</div>';
							 */
						}
					} else
						htmlString = "<span style='width: 100%; text-align: center; float: left;'>No data</span>";
					if (evoLib.libType == 1)
						$('#dia-evo-lib .content-data-box').html(htmlString);
					else
						$('#dia-evo-lib .content-data-box').html(htmlString);
					/*
					 * show tooltip
					 */
					$('[data-toggle="tooltip"]').tooltip();
					/*
					 * rating
					 */
					starRate.rateData($(".action-rating"), function(el) {
						console.log(el);
						evoLib.updateVote(el);
						
					});

				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		updateVote : function(e){
			$.ajaxSetup({
			    beforeSend : function() {
			    },
			    complete : function() {
			     setDefaultAjaxStatus();
			    }
			});
			$.ajax({
				type:"POST",
				url: encodeUrl("libraryBean.u920UpdateVote"),
				data: {pe920:e.el.closest('.data-box').attr('boxid'), 
					star:e.star
				},
				success: function(response){
					var rate = JSON.parse(response.result);
					e.el.closest('.info-data').find('.rate-content').html(starRate.setStartRating(rate.VOTE_STAR_AVG));
					e.el.closest('.info-data').find('.total-rate').html(rate.VOTE_TOTAL);
				},
				error: function(er){
					showGrowlMessageAjaxError();
				}
			})
		},
	}
}());
