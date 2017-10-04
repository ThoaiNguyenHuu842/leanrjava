/**
 * @author ThienND
 * 
 * created Jan 28, 2016
 */
(function() {
	actionOption = {
		open : function(option) {
			// init when first call
			if (!actionOption.inited) {
				actionOption.init();
				actionOption.inited = true;
			}
			actionOption.callBack = option.callBack;
			actionOption.target = option.target;
			actionOption.link = option.target.attr('data-action-link');
			actionOption.type = actionOption.target.attr('data-action-type');
			actionOption.linkTarget = actionOption.target
					.attr('data-action-link-target');
			actionOption.idDoc = actionOption.target.attr('data-doc-id');
			// lấy danh sách các box
			var tempHTML = '';// '<li selected="selected"
			// disabled>'+getLocalize("aco_title24")+'</li>';
			for ( var id in bigBoxModelManager.listBox) {
				var status = bigBoxModelManager.listBox[id][WEB_PRO_STT];
				var name = bigBoxModelManager.getName(id);
				if (status == WEB_PRO_STT_UPDATE
						|| status == WEB_PRO_STT_NO_CHANGE) {
					if (name && name != 'null')
						tempHTML += '<li value="' + name + '"><a href="#">'
								+ name + '</a></li>';
					// else
					// tempHTML += '<option value="qb-box-'+ id +'"> Section '+
					// id +'</option>';
				} else {
					if (name && name != 'null')
						tempHTML += '<li value="' + name + '"><a href="#">'
								+ name + '</a></li>';
				}
			}
			var tempHTML2 = '';// '<li selected="selected"
			// disabled>'+getLocalize("aco_title22")+'</li>';
			var listAnchorName = componentModelManager
					.getListName(actionOption.target.closest(
							'.grid-stack-item-content').attr('qb-component-id'));
			$.each(listAnchorName, function(k, v) {
				// WEB_PREFIX_COMP
				if (v != 'null')
					tempHTML2 += '<li value="' + v + '"><a href="#">' + v
							+ '</a></li>';
			});
			// lấy danh sách các page
			if (web.TRIAL == 'off')
				actionOption.getListPage();

			$('#ao-list-page').attr('link', actionOption.link);
			$('li.menu-left-label').removeClass('selected');
			$('div.action-content').hide();
			$('#ao-list-box .dropdown-menu').html(tempHTML);
			$('#ao-list-component .dropdown-menu').html(tempHTML2);
			actionOption.type = actionOption.type ? actionOption.type : 'none';
			actionOption.showCurrentSelected(actionOption.type);
			$('#ao-web-address-field').val('');
			actionOption.linkTarget = actionOption.linkTarget ? actionOption.linkTarget
					: '_self';
			console.log('abcdefght' + actionOption.linkTarget)
			if (actionOption.type == 'web-address') {
				// tunt edit 21/1/2017
				// $('#ao-web-address-field').find('.dropdown-toggle
				// span.content').html(actionOption.link);
				// $('.ao-web-address
				// .ao-link-target').val(actionOption.linkTarget);
				$('.ao-web-address .ao-link-target').attr('type-vl',
						actionOption.linkTarget);
				$('#ao-web-address-field').val(actionOption.link);
				$('.action-content .ao-link-target .ao-link-target-child')
						.removeClass('selected');
				$(
						'.action-content .ao-link-target .ao-link-target-child[value='
								+ actionOption.linkTarget + ']').addClass(
						'selected');

			} else if (actionOption.type == 'my-docs') {
				actionOption.getListFile();
			} else if (actionOption.type == 'go-to-page') {
				console.log("Link target : " + actionOption.linkTarget);
				$('.ao-go-to-page .ao-link-target').attr('type-vl',
						actionOption.linkTarget);
				$(
						'.ao-go-to-page .ao-link-target .ao-link-target-child[value="'
								+ actionOption.linkTarget + '"]').trigger(
						'click');
			} else if (actionOption.type == 'scroll-to-box') {
				$('#ao-list-box').find('.dropdown-toggle span.content').html(
						actionOption.link);
			} else if (actionOption.type == 'scroll-to-component') {
				$('#ao-list-component').find('.dropdown-toggle span.content')
						.html(actionOption.link);
			}
			$('#qb-dlg-action-option .menu .menu-left li').show();
			if (option.del) {
				var del = option.del.split(" ");
				$.each(del, function(k, v) {
					$(v).hide();
				});
			}
			$('#qb-dlg-action-option').dialog('open');
		},
		close : function() {
			$('#qb-dlg-action-option').dialog('close');
		},
		init : function() {
			actionOption.eventListener();
			$('#qb-dlg-action-option').webToolDialog(900);
			actionOption.inited = true;
		},
		eventListener : function() {
			$(document).on('click', '#qb-dlg-action-option .ao-none',
					function() {
						actionOption.type = 'none';
						actionOption.showCurrentSelected(actionOption.type);
					});
			$(document).on('click', '#qb-dlg-action-option .ao-web-address',
					function() {
						actionOption.type = 'web-address';
						actionOption.showCurrentSelected(actionOption.type);
					});
			$(document)
					.on(
							'click',
							'#qb-dlg-action-option .ao-go-to-page',
							function() {
								if (web.TRIAL == 'off') {
									actionOption.type = 'go-to-page';
									actionOption
											.showCurrentSelected(actionOption.type);
								} else
									util
											.messageDialog(getLocalize("jswmt_title1"));
							});
			$(document).on('click',
					'#qb-dlg-action-option .ao-download-document', function() {
						actionOption.type = 'download-document';
						actionOption.showCurrentSelected(actionOption.type);
					});
			$(document).on('click', '#qb-dlg-action-option .ao-scroll-to-top',
					function() {
						actionOption.type = 'scroll-to-top';
						actionOption.showCurrentSelected(actionOption.type);
					});
			$(document).on('click',
					'#qb-dlg-action-option .ao-scroll-to-bottom', function() {
						actionOption.type = 'scroll-to-bottom';
						actionOption.showCurrentSelected(actionOption.type);
					});
			$(document)
					.on(
							'click',
							'#qb-dlg-action-option .ao-scroll-to-component',
							function() {
								actionOption.type = 'scroll-to-component';
								actionOption
										.showCurrentSelected(actionOption.type);
								actionOption.link = '#'
										+ $('#ao-list-component').val();
							});
			$(document).on('click', '#qb-dlg-action-option .ao-scroll-to-box',
					function() {
						actionOption.type = 'scroll-to-box';
						actionOption.showCurrentSelected(actionOption.type);
						actionOption.link = '#' + $('#ao-list-box').val();
					});
			$(document)
					.on(
							'click',
							'#qb-dlg-action-option .menu-left .ao-my-docs',
							function() {
								if (web.TRIAL == 'off') {
									actionOption.type = 'my-docs';
									actionOption
											.showCurrentSelected(actionOption.type);
									actionOption.getListFile();
								} else
									util
											.messageDialog(getLocalize("jswmt_title1"));
							});
			$(document).on('click', '.menu-right .ao-my-docs .my-files-top a',
					function() {
						actionOption.checkRoleUpDoc();
					});
			$(document).on('click', '#no-docs span', function() {
				actionOption.checkRoleUpDoc();
			});
			$(document)
					.on(
							'click',
							'.action-option-panel .menu .menu-right .ao-my-docs .my-files-content li',
							function() {
								$('.my-files-content li').removeClass(
										'file-selected');
								$(this).addClass('file-selected');
							});
			$('#mydocs').on('change', function(evt) {
				actionOption.upload(evt);
			});
			$(document).on('click', '#file-delete', function() {
				var fo100 = $(this).attr('fo100');
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					actionOption.removeFile(fo100);
				})
			});
			$(document).on(
					'click',
					'#file-download',
					function() {
						$('.my-files-content li').removeClass('file-selected');
						$(this).closest('li').addClass('file-selected');
						$('.my-files-content .list-download-file tbody tr')
								.removeClass("file-selected");
						$(this).parent().addClass("file-selected");
					});
			$(document)
					.on(
							'input',
							'#ao-web-address-field',
							function() {
								actionOption.link = $(this).val();
								if (actionOption.validUrl(actionOption.link)) {
									$(this).css('border', '1px solid #999');
									if (!(actionOption.link.indexOf('http://') > -1 || actionOption.link
											.indexOf('https://') > -1)) {
										actionOption.link = 'http://'
												+ actionOption.link;
									}
								} else
									$(this).css('border', '1px solid red');
							});

			// event choice dropdown
			$(document)
					.on(
							'click',
							'#ao-list-component .dropdown-menu li,#ao-list-box .dropdown-menu li,#ao-list-page .dropdown-menu li',
							function() {
								var value = $(this).attr('value');
								$(
										'.action-option-panel .menu-right .action-content #ao-web-address-field')
										.val(value);
								$(this).closest('.dropdown')
										.attr("link", value);
								$(this).closest('.dropdown').find(
										'.dropdown-toggle span.content').html(
										$(this).html());
							});

			$(document)
					.on(
							'click',
							'#qb-dlg-action-option .btn-save',
							function() {
								if (actionOption.type == 'web-address') {
									actionOption.link = $(
											'#ao-web-address-field').val();
									actionOption.linkTarget = $(
											'.ao-web-address .ao-link-target')
											.attr('type-vl');
								}

								else if (actionOption.type == 'my-docs') {
									actionOption.link = $(
											'.file-selected #file-download')
											.attr('link');
									actionOption.linkTarget = '_blank';
									actionOption.idDoc = $(
											'.file-selected #file-download')
											.attr('fo100');
									// if (actionOption.link == undefined)
									// {
									// showGrowlMessage(getLocalize('aco_title13'),
									// 2000);
									//						
									// }
								}

								else if (actionOption.type == 'go-to-page') {
									actionOption.link = $('#ao-list-page')
											.attr("link");
									actionOption.linkTarget = $(
											'.ao-go-to-page .ao-link-target')
											.attr("type-vl");
								} else if (actionOption.type == 'scroll-to-box') {
									actionOption.link = 'box-comp-name-'
											+ $('#ao-list-box').attr("link");
									actionOption.linkTarget = '';
								} else if (actionOption.type == 'scroll-to-component') {
									actionOption.link = 'comp-anchor-name-'
											+ $('#ao-list-component').attr(
													"link");
									actionOption.linkTarget = '';
								} else {
									actionOption.link = '';
									actionOption.linkTarget = '';
								}

								if (actionOption.type == 'web-address') {
									if (actionOption
											.validUrl(actionOption.link)) {
										actionOption
												.callBack({
													type : "action",
													dataType : actionOption.type,
													dataLink : actionOption.link,
													dataLinkTarget : actionOption.linkTarget,
												});
										actionOption.close();
										showGrowlMessageSuccess();
									} else {
										showGrowlMessage(
												getLocalize('aco_title14'),
												2000);
									}
								} else {
									if (actionOption.link == undefined
											&& actionOption.type == 'my-docs') {
										showGrowlMessage(
												getLocalize('aco_title13'),
												2000);
									} else {
										actionOption
												.callBack({
													type : "action",
													dataType : actionOption.type,
													dataLink : actionOption.link,
													dataDocId : actionOption.idDoc,
													dataLinkTarget : actionOption.linkTarget,
												});
										actionOption.close();
									}
								}
							});

			$(document)
					.on(
							'click',
							'#qb-dlg-action-option .action-content .ao-link-target .ao-link-target-child',
							function() {
								var type = $(this).attr("value");
								$(
										'#qb-dlg-action-option .action-content .ao-link-target .ao-link-target-child')
										.removeClass("selected");
								$(this).addClass("selected");
								$(this).parent().attr("type-vl", type);
							});

			$(document)
					.on(
							'click',
							'.my-files-content .list-download-file .file-name-download',
							function() {
								$(
										'.my-files-content .list-download-file .file-name-download')
										.removeClass("selected");
								$(this).addClass("selected");
							});

			/*
			 * change icon sort file
			 */
			$(document)
					.on(
							'click',
							'.action-option-panel .menu .menu-right .ao-my-docs  .filter-name-file .sortfile-by-name',
							function() {
								($(this).attr('type') == 1 ? $(this).attr(
										'type', 0) : $(this).attr('type', 1));

							});

		},
		showCurrentSelected : function(a) {
			$('#qb-dlg-action-option .menu .menu-left .ao-' + a + '').addClass(
					'selected').siblings().removeClass('selected');
			$('#qb-dlg-action-option .menu .menu-right .ao-' + a + '').show()
					.siblings().hide();
		},
		goToEditMode : function() {
			$('a[data-action-type]').each(function() {
				var link = $(this);
				switch (link.attr('data-action-type')) {
				case 'web-address':
				case 'my-docs':
				case 'go-to-page':
					link.attr('data-action-link', link.attr('href'));
					link.attr('data-action-link-target', link.attr('target'));
					break;
				case 'scroll-to-box':
					link.removeClass('scroll-to-box');
					link.attr('data-action-link', link.attr('href'));
					link.removeAttr('onclink');
					break;
				case 'scroll-to-component':
					link.removeClass('scroll-to-component');
					link.attr('data-action-link', link.attr('href'));
					link.removeAttr('onclink');
					break;
				case 'scroll-to-top':
					link.removeClass('scroll-to-top');
					break;
				case 'scroll-to-bottom':
					link.removeClass('scroll-to-bottom');
					link.removeAttr('onclick');
					break;
				case 'none':
					link.removeClass('none');
					break;
				default:
					break;
				}
				link.removeAttr('href');
				link.removeAttr('target');
				link.removeClass(link.attr('ho-effect'));
			});
		},
		goToPreviewMode : function() {
			$('a[data-action-type]')
					.each(
							function() {
								var link = $(this);
								switch (link.attr('data-action-type')) {
								case 'web-address':
								case 'my-docs':
								case 'go-to-page':
									link.removeAttr('href');
									link.removeAttr('target');
									link.removeClass('none');
									link.attr('href', link
											.attr('data-action-link'));
									link.attr('target', link
											.attr('data-action-link-target'));
									link.removeAttr('data-action-link');
									link.removeAttr('data-action-link-target');
									break;
								case 'scroll-to-box':
									link.removeAttr('href');
									link.removeAttr('target');
									link.removeClass('none');
									link.addClass('scroll-to-box');
									var box = link.attr('data-action-link');
									if (box) {
										link.attr('href', box);
										var onclick = 'smoothScroll("' + box
												+ '");return false;';
										link.attr('onclick', onclick);
										link.removeAttr('data-action-link');
									}
									break;
								case 'scroll-to-component':
									link.removeAttr('href');
									link.removeAttr('target');
									link.removeClass('none');
									link.addClass('scroll-to-component');
									var component = link
											.attr('data-action-link');
									if (component) {
										link.attr('href', component);
										var onclickComponent = 'smoothScroll("'
												+ component
												+ '");return false;';
										link.attr('onclick', onclickComponent);
										link.removeAttr('data-action-link');
									}
									break;
								case 'scroll-to-top':
									link.removeAttr('href');
									link.removeAttr('target');
									link.removeClass('none');
									link.addClass('scroll-to-top');
									link.attr('href', '#top');
									link.removeAttr('data-action-link');
									break;
								case 'scroll-to-bottom':
									link.removeAttr('href');
									link.removeAttr('target');
									link.removeClass('none');
									link.addClass('scroll-to-bottom');
									link
											.attr('onclick',
													'window.scrollTo(0,document.body.scrollHeight);');
									link.removeAttr('data-action-link');
									break;
								case 'none':
									link.removeAttr('href');
									link.removeAttr('target');
									link.removeClass('none');
									link.addClass('none');
									break;
								default:
									break;
								}
							});
		},
		getListPage : function() {
			$.ajax({
				url : encodeUrl('mysitesBean.load'),
				type : 'GET',
				data : {
					offset : 0,
					limit : 10000,
					pvSearch : ''
				},
				success : function(response) {
					console.log(response);
					var tempHTML = '';// '<option selected="selected"
					// disabled>'+getLocalize("aco_title23")+'</option>';
					var listPages = response.result;
					$.each(listPages, function(k, v) {
						tempHTML += '<li value="/e' + v.id
								+ '/evo-redirect-page"><a href="#">' + v.ev405
								+ '</a></li>';
					});
					$('#ao-list-page .dropdown-menu').html(tempHTML);
					var sls = $('#ao-list-page').attr('link');
					sls = sls.replace(/\//g, '\\/');
					// console.log("get slecte : "+sls);
					var dataHt = $(
							'#ao-list-page .dropdown-menu li[value="' + sls
									+ '"] a').html();
					// console.log(dataHt);
					$('#ao-list-page .dropdown-toggle .content').html(dataHt);
					if (actionOption.type == 'go-to-page')
						$('#ao-list-page').attr("link", actionOption.link);

				},
				error : function(error) {

				}
			});
		},
		validUrl : function(url) {
			var pattern = /[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)/ig;
			return pattern.test(url);
		},
		upload : function(evt) {
			var files = evt.target.files;
			var file = files[0];
			var fileName = file.name;
			var binaryString;
			if (files && file) {
				var reader = new FileReader();
				reader.onload = function(readerEvt) {
					var contentString = readerEvt.target.result;
					binaryString = btoa(contentString);
					actionOption.uploadAjax(fileName, binaryString);
				}
				reader.readAsBinaryString(file);
			}
		},
		uploadAjax : function(fileName, binaryString) {
			formData = new FormData();
			formData.append("dv101", fileName)
			formData.append("dv102", binaryString)
			$.ajax({
				type : "POST",
				url : encodeUrl("fileManagerBean.uploadFile"),
				processData : false,
				contentType : false,
				data : formData,
				success : function(response) {
					if (response.result == -2)
						util.messageDialog(getLocalize("aco_title15"));
					else
						actionOption.getListFile();
				},
				error : function() {

				}
			});
		},
		getListFile : function() {
			console.log('-------idDoc is ' + actionOption.idDoc);
			$
					.ajax({
						type : "GET",
						url : encodeUrl("fileManagerBean.getListFile"),
						processData : false,
						contentType : false,
						success : function(response) {
							var data = response.result;
							var html = "";
							var total = 0;
							if (data.length == 0) {
								$('#no-docs').addClass('nodocs');
							} else {
								for (var i = 0; i < data.length; i++) {
									if (data[i].dn102 != -1) {
										html += '<tr>'
										html += '	<td id="file-download" fo100="'
												+ data[i].id
												+ '" link="'
												+ data[i].dv103
												+ '" class="file-name-download '
												+ ((data[i].id == actionOption.idDoc) ? "selected"
														: "")
												+ '" title="'
												+ data[i].dv101
												+ '"><span>'
												+ data[i].dv101 + '<span></td>'
										html += '	<td>'
												+ actionOption
														.switchFileSize(data[i].dn102)
												+ '</td>'
										html += '	<td><i fo100="'
												+ data[i].id
												+ '" link="'
												+ data[i].dv103
												+ '" id="file-delete" class="fa fa-trash-o" aria-hidden="true"></i></td>'
										html += '</tr>';
										// if (data[i].id == actionOption.idDoc)
										// {
										// html += '<li class="file-selected">'
										// html += '<span title="' +
										// data[i].dv101 + '"
										// id="file-name">' + data[i].dv101 +
										// '</span>'
										// html += '<img alt="" src="' +
										// $('#contextPath').val() +
										// '/html/images/file-icon.png">'
										// html += '<div class="icon-action">'
										// html += '<a id="file-download"
										// class="fa
										// fa-check" aria-hidden="true" fo100="'
										// +
										// data[i].id + '"link="' +
										// data[i].dv103 +
										// '"></a>'
										// html += '<a id="file-delete"
										// class="fa
										// fa-times" aria-hidden="true" fo100="'
										// +
										// data[i].id + '"></a>'
										// html += '</div>'
										// html += '</li>'
										// total += data[i].dn102;
										// } else {
										// html += '<li>'
										// html += '<span id="file-name"
										// title="' +
										// data[i].dv101 + '">' + data[i].dv101
										// +
										// '</span>'
										// html += '<img alt="" src="' +
										// $('#contextPath').val() +
										// '/html/images/file-icon.png">'
										// html += '<div class="icon-action">'
										// html += '<a id="file-download"
										// class="fa
										// fa-check" aria-hidden="true" fo100="'
										// +
										// data[i].id + '"link="' +
										// data[i].dv103 +
										// '"></a>'
										// html += '<a id="file-delete"
										// class="fa
										// fa-times" aria-hidden="true" fo100="'
										// +
										// data[i].id + '"></a>'
										// html += '</div>'
										// html += '</li>'
										// total += data[i].dn102;
										// }
									}
								}
								;
								if ($('#no-docs').hasClass('nodocs')) {
									$('#no-docs').removeClass('nodocs')
								}
							}
							$(
									'.ao-my-docs .my-files-content .list-download-file tbody')
									.html(html);
							$(
									'.action-option-panel .menu .menu-right .ao-my-docs .my-files-content')
									.niceScroll();
							$('#total-storage').html(total / 1000);
							$('#total-file').html(data.length);
						},
						error : function() {

						}
					});
		},
		/*
		 * ThoaiVt - switch size file KB - MB - GB - TB
		 */
		switchFileSize : function(datasize) {
			var size = parseFloat(parseInt(datasize) / 1024);
			var result = "";
			if (size < 1024)
				result = size.toFixed(2) + " KB";
			else if (size >= 1024 && size < 1048576)
				result = (size / 1024).toFixed(2) + " MB";
			else if (size >= 1048576 && size < 1073741824)
				result = (size / 1048576).toFixed(2) + " GB";
			else if (size >= 1073741824 && size < 1099511627776)
				result = (size / 1073741824).toFixed(2) + " TB";
			return result;
		},
		removeFile : function(id) {
			$.ajax({
				type : "POST",
				url : encodeUrl("fileManagerBean.removeFile"),
				data : {
					'id' : id
				},
				success : function(response) {
					actionOption.getListFile();
				},
				error : function() {
				}
			});
		},
		checkRoleUpDoc : function() {
			$.ajax({
				type : "GET",
				url : encodeUrl("fileManagerBean.checkRightUpDoc"),
				async: false,
				success : function(response) {
					if (response.result) {
						$('.action-option-panel .menu #mydocs').trigger('click');
					} else {
						util.confirmDialog(getLocalize("updrade_message"),
								function() {
									window.location = util.contextPath()
											+ "/pricing";
								});
					}

				}
			})
		}
	};
}());
