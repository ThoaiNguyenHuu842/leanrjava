/**
 * ThoaiVt 31/03/2016
 */
(function() {
	evoComLib = {
		LIB_COMP_DATA : {},
		LIST_SORT_CPN : [],
		init : function() {
			if (!evoComLib.inited) {
				$('.dialogadmin-show-listcomponent').webToolDialog(350);
				evoComLib.inited = true;
			}
			console.log("INit");
			evoComLib.eventListener();
		},
		eventListener : function() {

			$(document).mouseup(function(e) {
				var container = $(".admin-library-load.load-cp-admin");
				// if the target of the click isn't the container nor a
				// descendant of the container
				if (!container.is(e.target) && container.has(e.target).length === 0) {
					// console.log("hiden : ");
					$(".admin-library-load.load-cp-admin").closest('.item').find('.item-icon-panel').trigger('click');
				}
			});

			/*
			 * embed code
			 */

			$("#web-tools .item#normal_embed_code").draggable({
				// revert: true,
				appendTo : "body",
				zIndex : 999999999,
				cursor : "move",
				opacity : 0.35,
				helper : function() {
					var $result = $('<div class="drappable-content normal-image"><img src="' + $(this).attr('src') + '"></div>');
					return $result;
				}
			});

			/*
			 * embed code
			 */
			$(document).on('click', '#web-tools .item:not(#normal_embed_code) .item-icon-panel', function() {
				var actionCB = $(this);
				var type = $(this).closest(".item").attr("id");
				console.log(evoComLib[type]);
				if (evoComLib[type] == undefined || web.MV922 == "ADMIN") {
					if (!$(this).hasClass('active'))
						evoComLib.loadListComponentLib($(this), type, "LIB", function() {
							console.log("load done");
							$(".admin-library-load .data-content").sortable({
								opacity : 0.6,
								revert : true,
								cursor : 'move',
								start : function(event, ui) {
									var start_pos = ui.item.index();
									ui.item.data('start_pos', start_pos);
									console.log("START MOVE");
								},
								update : function(event, ui) {
									console.log("DONE");
									var start_pos = ui.item.data('start_pos') + 1;
									var end_pos = ui.item.index() + 1;
									evoComLib.updateDataListCompo(start_pos-1,end_pos-1,function(){
										evoComLib.LIST_SORT_CPN.sort(function(a, b){
										    // Compare the 2 dates
										    if(a.libSort < b.libSort) return 1;
										    if(a.libSort > b.libSort) return -1;
										    return 0;
										});
										console.log(evoComLib.LIST_SORT_CPN);
										evoComLib.updateIDPForComponent(function() {
												var dataJoinPost = evoComLib.generateDataPost(start_pos-1,end_pos-1);
//												var dataJoinPost = evoComLib.generateDataPostFull();
												console.log(evoComLib.LIST_SORT_CPN);
												console.log(dataJoinPost);
												var jsonObjectData = JSON.stringify(dataJoinPost);
												var formData = new FormData();
												formData.append("admininstrationComponent", jsonObjectData);
												$.ajax({
													type : "POST",
													headers : {
														'Accept' : 'application/json',
														'Content-Type' : 'application/json'
													},
													dataType : 'json',
													url : encodeUrl("componentLibraryBean.swapComponent"),
													data : jsonObjectData,
													success : function(response) {
														if (response.status == 'SUCCESS') {
//															actionCB.trigger('click');
														}
													}
												});
											});
										});
									// update cpid for sort evoComLib.LIST_SORT_CPN
									
								}
							});
						});
					evoComLib[type] = true;
				}
				console.log($(this).hasClass("active"));
				if (!$(this).hasClass("active")) {
					try {
						$(".admin-library-load .data-content").getNiceScroll().hide();
						$(this).closest('.item').find(".admin-library-load .data-content").niceScroll({
							horizrailenabled : false,
							autohidemode : false,
							cursorcolor : '#36AA00',
							cursorwidth : 8,
						});
						$(this).closest('.item').find(".admin-library-load .data-content").getNiceScroll().show();
					} catch (e) {
						console.log("Not scroll");
					}
					$('.loadmore-components').find('.admin-library-load').removeClass('load-cp-admin');
					$('#web-tools .item .item-icon-panel').removeClass('active');
					$(this).addClass("active");
					$(this).closest('.item').find('.loadmore-components').find('.admin-library-load').addClass('load-cp-admin');
				} else {
					try {
						$(".admin-library-load .data-content").getNiceScroll().hide();
					} catch (e) {
						console.log("Not scroll");
					}
					$(this).removeClass("active");
					$(this).closest('.item').find('.loadmore-components').find('.admin-library-load').removeClass('load-cp-admin');
				}
			});

			/*
			 * 
			 */
			$(document).on('click', '#web-tools .item .admin-library-load .btn-menu-close', function() {
				$(this).closest('.loadmore-components').find('.admin-library-load').removeClass('load-cp-admin');
			});

			/*
			 * remove component from library
			 */
			$(document).on('click', '#web-tools .item .admin-library-load .btn-remove-comp-form-lib', function() {
				var id = $(this).closest('.item-child-adtp').attr('idp');
				util.confirmDialog(getLocalize("jsevl_title1"), function() {
					adminAddlibrary.removeFromLib(id);
				});
			});

		},
		/**
		 * Type default is* "LIB"
		 */
		loadListComponentLib : function(element, type, ev903, callback) {
			console.log("Load : " + type + " : " + ev903);
			if (type != "" && ev903 != "") {
				$.ajax({
					type : "GET",
					url : encodeUrl("componentLibraryBean.listConponentsLibrary"),
					data : "type=" + encodeURIComponent(type) + "&ev903=" + encodeURIComponent(ev903),
					success : function(response) {
						if (response.status == AJAX_SUCESS) {
							evoComLib.LIB_COMP_DATA[type] = {};
							console.log(response);
							var data = response.result;
							if (data === undefined || data === null)
								return;
							var template = "";
							evoComLib.LIST_SORT_CPN = [];
							for (var i = 0; i < data.length; i++) {
								template += "<div class='item-child-adtp comp-lib-item-" + data[i].id + "' idp='" + data[i].id + "' cpid='" + data[i].libSort + "'>";
								template += "	<img class='item-img-lbadmin' compId='" + data[i].id + "' src=" + data[i].ev902 + "></img>";
								if ((web.MV922 === "ADMIN")) {
									template += "<div class='function-component'>";
									template += "		<i class='move-component fa fa-arrows' aria-hidden='true'></i>";
									template += "		<i title='remove from libray' class='fa fa-trash-o btn-remove-comp-form-lib'></i>";
									template += "</div>";
								}
								template += "</div>";
								console.log("load temple");
								evoComLib.LIB_COMP_DATA[type][data[i].id] = data[i];
								var dataUp = {
									"id" : data[i].id,
									"libSort" : data[i].libSort
								}
								evoComLib.LIST_SORT_CPN.push(dataUp);
							}
//							console.log(template);
							element.closest(".item").find(".admin-library-load .data-content").html(template);

							$(".admin-library-load .data-content img,.admin-library-load .component-default .sub-item").draggable({
								// revert: true,
								appendTo : "body",
								zIndex : 999999999,
								cursor : "move",
								opacity : 0.35,
								helper : function() {
									var $result = $('<div class="drappable-content normal-image"><img src="' + $(this).attr('src') + '"></div>');
									console.log($result);
									return $result;
								}
							});
							console.log(evoComLib.LIB_COMP_DATA);
						} else {
							showGrowlMessageError();
						}
					},
					error : function(e) {
						showGrowlMessageAjaxError();
					}
				}).done(function() { // use this
					if (callback != undefined) {
						callback(callback);
					}
				});
				;
			} else {
				util.messageDialog("Cannot add component . check value !");
			}
		},
		updateIDPForComponent : function(callback) {
			$(".admin-library-load .data-content .item-child-adtp").each(function(index) {
				try {
					console.log("update  Now");
					$(this).attr("cpid", evoComLib.LIST_SORT_CPN[index]["libSort"]);
					evoComLib.LIST_SORT_CPN[index]["id"] = $(this).attr("idp");
					if (index === evoComLib.LIST_SORT_CPN.length - 1) {
						callback(callback);
					}
				} catch (e) {
					// TODO: handle exception
					console.log("bound index of! : " + e);
				}
			});
		},
		updateDataListCompo: function(a,b,callback){
			var lidA = evoComLib.LIST_SORT_CPN[a]["libSort"];
			var lidB = evoComLib.LIST_SORT_CPN[b]["libSort"];
			evoComLib.LIST_SORT_CPN[b]["libSort"] = lidA;
			evoComLib.LIST_SORT_CPN[a]["libSort"] = lidB;
			callback(callback);
		},
		generateDataPost : function(a,b) {
			var dataPost = [];
			console.log(a + " : " + b);
			var temp = {};
			for (var i = 0, max = evoComLib.LIST_SORT_CPN.length; i < max; i++) {
				if(i>=a && i<=b){
					temp = {
						id : parseInt(evoComLib.LIST_SORT_CPN[i]["id"]),
						libSort : parseInt(evoComLib.LIST_SORT_CPN[i]["libSort"])
					};
					dataPost.push(temp);
				}
			}
			return dataPost;
		},generateDataPostFull : function() {
			var dataPost = [];
			var id = 0;
			var dataEl = {};
			var cpid = 0;
			$(".admin-library-load .data-content .item-child-adtp").each(function(index) {
				id = $(this).attr('idp');
				cpid = $(this).attr('cpid');
				dataEl = {
					id : parseInt(id),
					libSort : parseInt(cpid)
				}
				var check = evoComLib.checkAddToUpdate(dataEl);
				if (check === false)
					dataPost.push(dataEl);

			});
			return dataPost;
		},
		checkAddToUpdate : function(data) {
			for (var i = 0, max = evoComLib.LIST_SORT_CPN.length; i < max; i++) {
				if (data["id"] === evoComLib.LIST_SORT_CPN[i]["id"] && data["libSort"] === evoComLib.LIST_SORT_CPN[i]["libSort"])
					return true;
			}
			return false;
		}
	}
}());
