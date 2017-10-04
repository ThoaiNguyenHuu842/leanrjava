function escapeHtml(unsafe) {
	return unsafe.replace(/'/g, "21dauphay12").replace(/;/g, "21champhay12");
}

function unescapeHtml(unsafe) {
	return unsafe.replace(/21dauphay12/g, "'").replace(/21champhay12/g, ";");
}

// ham xac dinh bien co ton tai khong
function NOTUNDEFINED(value) {
	return (typeof value != 'undefined' && value != null ? true : false);
}

function unescapeHtml(unsafe) {
    return unsafe
            .replace(/21dauphay12/g, "'")
            .replace(/21champhay12/g, ";");
}

(function() {
	socketio = {
		socket : null,
		url : 'https://oohhay.com:3001',
		UserModel : {},
		PostModel : {},
		id_topic : 0,
		host : "http://dev.topic.oohhay.com/",
		// lay parame tu url
		getUrlParameter : function(sParam) {
			var sPageURL = window.location.search.substring(1);
			var sURLVariables = sPageURL.split('&');
			for (var i = 0; i < sURLVariables.length; i++) {
				var sParameterName = sURLVariables[i].split('=');
				if (sParameterName[0] == sParam) {
					return sParameterName[1];
				}
			}
		},
		connect : function() {
			socket = io.connect(this.url);
			this.conectServer();
			this.receiverM150LISTOFTABM150OText();
		},
		init : function() {
			this.showTopicToolbar();
			socket = io.connect(this.url);
			this.id_topic = (typeof this.getUrlParameter('tid') != 'undefined' ? this
					.getUrlParameter('tid')
					: 0);
			$('#topic_id').val(this.id_topic);

			this.conectServer();
			this.getTopic();

			this.addEventListener();
			this.loadBackground();
		},
		loadListSearchTopic : function(offset, limit, otagString) {

			// load du lieu
			this.PostModel.offset = offset;
			this.PostModel.limit = limit;
			this.PostModel.status_search = 0;

			var otagsStringnew = [];

			var total = this.PostModel.offset + this.PostModel.limit;
			var min = Math.min(total, otagString.length);

			for (var i = offset; i < min; i++) {
				otagsStringnew[i - offset] = otagString[i];
			}

			// var otagString = otagString.replace('!',"");
			this.PostModel.otagString = otagsStringnew;

			// gan du lieu
			this.UserModel.PostModel = this.PostModel;

			this.UserModel.pageCurrent = this.getUserLogin().FO100;
			this.UserModel.FO100U = this.getUserLogin().FO100;

			socket.emit('sendM150LISTOFTABM150OText', this.UserModel);

		},
		receiverM150LISTOFTABM150OText : function(data) {
			socket.on('receiverM150LISTOFTABM150OText', function(data) {
				console.log(data.result);
				var template = ''
				$.each(data.result, function(key, value) {
					
					template += '<li id-url="'+value._id+'">';
					template += '<img src="http://placehold.it/120x60">';
					template += '<div class="panel-video-info"><label class="title">'+decodeURI(unescapeHtml(value.MV151))+'</label></div>';
					//<li id-url="CO4b1_-v42A" style="cursor:pointer;"><img src="https://i.ytimg.com/vi/CO4b1_-v42A/default.jpg"><label class="author">By: TheBestVines</label></div></li>
					template += '</li>';
				})

				$('.input-data-topic ul').append(template);
			})

		},
		addLayout : function() {
			if (this.id_topic == 0) {
				$('.btn-add-body-box').trigger('click');
				$('#panel-no-box').hide();
			}
			/*
			 * if(this.id_topic == 0){ $('#normal_box_body').trigger('click');
			 * $('#panel-no-box').hide(); }
			 * 
			 * var boxData = { id: bigBoxModelManager.getNewId(), h: 1, type:
			 * "footer", index: ++bigBoxModelManager.crIndex };
			 * bigBoxModelManager.addItemToModel(boxData);
			 * //bigbox.addFooter(boxData); $('#web-tools .sub-menu').fadeOut();
			 */

		},
		setActiveMenuShare : function($topic, clz, selected) {
			var menus = $topic.findChild(clz + " li");

			var menuItem = $topic.findChild(clz + " li[data-menu='" + selected
					+ "']");
			menus.removeClass('active');
			menuItem.addClass('active');
		},
		/*
		 * kiem tra cho co chon object name neu co bat edit object name nguoc
		 * lai tat
		 * 
		 */
		ckbObjectName : function(status) {
			if (status) {
				$('.input-object-name-ckb').attr('checked', true);
				$('.input-object-name').css('background-color', 'white').attr(
						'contenteditable', 'true');
			} else {
				$('.input-object-name-ckb').attr('checked', false);
				$('.input-object-name').css('background-color', '#f6f6f6')
						.attr('contenteditable', 'false');
			}
		},
		/*
		 * danh cho tat cả
		 * 
		 */
		setSelectDropdown : function($topic, clzCombo, value) {
			var comboComponent = $topic.findChild(clzCombo);
			if (NOTUNDEFINED(value)) {
				comboComponent.attr('val', value);
				var select = $topic.findChild(clzCombo + " ul li[data-menu='"
						+ value + "']");
				// $(clzTopic).findChild(clzCombo + " ul
				// li[lang='"+value+"']").attr('selected','selected');
			} else {
				var select = $topic
						.findChild(clzCombo + " ul li[class=active]");
				var language = select.data('menu');
				comboComponent.attr('val', language);
			}
			select.addClass('active');

			var img = select.children('a').children('b').clone();
			var button = comboComponent.children('button');
			button.children('b').remove();
			button.prepend(img);
			// comboComponent.children('button').children('i').remove();

		},
		getTopic : function() {
			var $self = $(this);
			this.receiverM150LISTOFTABM150OText();

			if (this.id_topic != 0) {
				this.PostModel.id = this.id_topic;
				this.UserModel.PostModel = this.PostModel;
				socket.emit('sendM150LISTOFTABM150ONEADD', this.UserModel);
				socket
						.on(
								'receiverM150LISTOFTABM150ONEADD',
								function(data) {
									var result = data.result;

									if (typeof data.result.OTAGS != 'undefined'
											&& data.result.OTAGS != null) {
										$self.buildOTags(data.result.OTAGS);
									}

									if (typeof data.result.MV157 != 'undefined') {
										$('.toolbar-item input-object-name')
												.text(data.result.MV157);
									}

									if (typeof data.result.MV151 != 'undefined') {
										$('#topic_title')
												.val(data.result.MV151);
									}

									if (NOTUNDEFINED(result.MN160)
											&& result.MN160 != 0) {
										$self.ckbObjectName(true);
									} else {
										$self.ckbObjectName(false);
									}

									var $topic = $('.qb-ohhay-notfound-wrapper');
									console.log(data);
									// render comment
									$self.setActiveMenuShare($topic,
											'.topic-create-setup-private',
											result.ROLE.MN202);
									$self
											.setSelectDropdown(
													$topic,
													'.topic-create-setup-private',
													(result.ROLE.MN202 != 0) ? result.ROLE.MN202
															: 1);

									// render topic
									$self.setActiveMenuShare($topic,
											'.topic-create-setup-comment',
											result.ROLE.MN201);
									$self
											.setSelectDropdown(
													$topic,
													'.topic-create-setup-comment',
													(result.ROLE.MN201 != 0) ? result.ROLE.MN201
															: 1);

									console.log(data.result);
									// if
									// (NOTUNDEFINED(data.result.ROLE_COMMENT))
									// {

									// _$self.setRoleCommend($topic,
									// result.ROLE_COMMENT);
									// }

									var contenttopic = data.result.MV152;
									// $('#content-wrapper').remove();
									// $(".qb-ohhay-notfound-wrapper").append(decodeURI(unescapeHtml(contenttopic)));
									$('.grid-stack').css('border-left',
											'1px dashed');
									$('.grid-stack').css('border-right',
											'1px dashed');
									$('#content-wrapper li').css(
											'border-bottom', '1px dashed');
									// khoi tao
									// init();

								});
			}

			var $self = this;
			socket
					.on(
							'receiverM150INSERTTABM150',
							function(data) {
								
								$('#qb-ohhay-ajax-content').fadeOut();
								//web.goToEditMode();
								console.log('receiverM150INSERTTABM150');
								console.log(data);
								
								this.id_topic = data.PostModel.id;
								this.title_topic = data.PostModel.title;

								$('#topic_id').val(this.id_topic);
								this.title_topic = socketio.utilBuildFriendLy
										.apply($self, [ this.id_topic,
												this.title_topic ]);

								var url = this.title_topic;
								var template = "";
								template += "<div style=\"float: left; padding-left: 15px; margin-top: 1px;\">";
								template += "		<p>Your Topic: <\/p>";
								template += "		<a href=\"" + url
										+ "\" target=\"_blank\">" + url
										+ "<\/a>";
								template += "	<\/div>";

								$('#qb-save-web-success').html(template);
								$("#qb-save-web-success").dialog({
									modal : true,
									height : 250,
									width : 350,
									buttons : {
										Ok : function() {
											//web.goToEditMode();
											$(this).dialog("close");
										}
									}
								});
								// save model EVO
								webBuilder.saveTopicContentData(this.id_topic);
							});

		},
		showTopicToolbar : function() {
			// alert(PAGE_TOPIC_CONTANTS);
			$('#web-config').parent().remove();
			// $('#normal_box_header').remove();
			// $('#normal_box_footer').remove();
			$('#btn-ruler').parent().remove();
			$('#web-edit-changebg').parent().remove();
			$('.item[title="Add gallery"]').remove();
			$('.item[title="Add menu"]').remove();
			$('.item[title="Add contact form"]').remove();
			//$('#normal-button').remove();
			$('#preview').remove();
		},
		/*
		 * 
		 * lay thong tin khach hang
		 * 
		 */
		getUserLogin : function() {
			return {
				name : ($('#qb-userlogin-nv100').val() != '' ? $.trim($(
						'#qb-userlogin-nv100').val()) : 'Anonymus Person'),
				avarta : $("#qb-userlogin-avarta").val(),
				FO100 : (this.getFO100FromCookie() != '' ? this
						.getFO100FromCookie() : 0)
			}
		},
		conectServer : function() {
			var userlogin = this.getFO100FromCookie();

			var name = $('#qb-userlogin-nv100').val();
			this.UserModel.pageLogin = userlogin;
			this.UserModel.name = name;
			this.UserModel.FO100U = (this.getFO100FromCookie() != 0 ? this
					.getFO100FromCookie() : 0);
			this.UserModel.avarta = this.getUserLogin().avarta;

			this.UserModel.pageCurrent = userlogin;

			this.UserModel.numberpagecurrent = $('#qb-input-currentPhone')
					.val();
			this.UserModel.numberlogin = $('#qb-userlogin-qv101').val();

			this.UserModel.roomPublic = this
					.getNameRoomPublic(this.UserModel.pageCurrent);

			this.UserModel.roomPrivate = this
					.getNameRoomPrivate(this.UserModel.pageLogin);

			socket.emit('provideInfoUser', this.UserModel);
		},
		/*
		 * 
		 * lay danh sach phong
		 * 
		 */
		getNameRoomPrivate : function(owner) {
			return owner + '_private';
		},
		// lấy tên room chát public
		// owner: sdt nguoi login
		getNameRoomPublic : function(owner) {
			return owner + '_public';
		},
		readCookie : function(name) {
			var nameEQ = name + "=";
			var ca = document.cookie.split(';');
			for (var i = 0; i < ca.length; i++) {
				var c = ca[i];
				while (c.charAt(0) == ' ')
					c = c.substring(1, c.length);
				if (c.indexOf(nameEQ) == 0)
					return c.substring(nameEQ.length, c.length);
			}
			return null;
		},
		isLocalhost : function() {
			var currentLocation = window.location;
			if (currentLocation.href.indexOf('localhost') != -1) {
				return true;
			}
			return false;
		},
		getFO100FromCookie : function() {
			// console.log('_$self.readCookie("REB3629dhb198nEGHFDN")1' +
			// _$self.readCookie("REB3629dhb198nEGHFDN"));
			// console.log('_$self.readCookie("REB3629dhb198nEGHFDN")2' +
			// Aes.decrypt($.trim(_$self.readCookie("REB3629dhb198nEGHFDN"))));
			if (this.readCookie("REB3629dhb198nEGHFDN") != null) {
				var inhidden_fo100user = $('#qb-userlogin-fo100').val();
				return inhidden_fo100user != '' ? Aes.decrypt($
						.trim(inhidden_fo100user)) : 0;

				/*
				 * console.log('demo 1'); var fo100_login =
				 * Aes.decrypt(_$self.readCookie("REB3629dhb198nEGHFDN")).split('##qb##');
				 * return Aes.decrypt(fo100_login[3]);
				 */
			} else if (this.isLocalhost()) {
				var inhidden_fo100user = $('#qb-userlogin-fo100').val();
				return inhidden_fo100user != '' ? Aes.decrypt($
						.trim(inhidden_fo100user)) : 0;
			}

			return 0;

		},
		contextPath : function() {
			var url = window.location;
			return url.protocol + '//' + url.host + url.pathname;
		},
		confirmDialog : function(message, callback) {
			$('.dialogconfirm').jsOhhayDialog({
				type : 'confirm',
				title : 'confirm',
				content : message,
				save : callback
			});
		},
		/*
		 * phat sinh ID tu dong
		 * 
		 */
		generateUUID : function() {
			var d = new Date().getTime();
			var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,
					function(c) {
						var r = (d + Math.random() * 16) % 16 | 0;
						d = Math.floor(d / 16);
						return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
					});
			return uuid;
		},
		saveTopic : function(html, JSONdata) {

			var $self = this;
			var topic_name = $('#topic_title');
			if (socketio.id_topic == 0 && topic_name.val() == '') {

				var template = "";
				template += "<div style=\"float: left; padding-left: 15px; margin-top: 1px;\">";
				template += "		<p>Your need input the title: <\/p>";
				template += "		<input value='' class='txt_title_topic' />";
				template += "	<\/div>";

				$('#qb-save-web-success').html(template);
				$("#qb-save-web-success")
						.dialog(
								{
									modal : true,
									height : 250,
									width : 350,
									buttons : {
										Ok : function() {
											var txt_title_topic = $('.txt_title_topic');
											if (txt_title_topic.val() != '') {
												topic_name.val(txt_title_topic
														.val());
												socketio.nodejsSaveTopic.apply(
														$self, [ html ]);
												$(this).dialog("close");
											}
												
										}
									}
								});
			} else {
				socketio.nodejsSaveTopic.apply($self, [ html ]);
			}
			web.goToEditMode();
		},
		buildOTags : function(otags) {
			console.log(otags);
			var template = '';
			if (otags != null && otags.length > 0) {
				for (var i = 0; i < otags.length; i++) {
					var val = decodeURI(otags[i].trim())
					if (val != '') {
						template += '<p>';
						template += "!" + val;
						template += '</p>';
					}

				}
				template += '<span>All</span>';

				console.log(template);
				$('.topic-panel-item-content-otask div').html(template);

			}
			return template;
		},
		nodejsSaveTopic : function(html) {
			
			$('#qb-ohhay-ajax-content').fadeIn();
			this.id_topic = $('#topic_id').val();
			// M150_INSERTTABM150(3728,230,'demo','demo','',1,1,'',0,'vn')
			this.PostModel.id_status = this.id_topic;
			this.PostModel.otagString = $('#tags-add-otext').val();
			this.PostModel.avarta = this.getUserLogin().avarta;
			this.PostModel.title = $('#topic_title').val();
			this.PostModel.timepost = new Date().toISOString();
			this.PostModel.objectname = $('.toolbar-item input-object-name')
					.text();
			this.PostModel.ckbobjectname = ($('.input-object-name-ckb').is(
					":checked") ? 1 : 0);

			this.PostModel.UUID = this.generateUUID();
			this.PostModel.roleView = $(
					'.topic-create-setup-comment ul li.active').data('menu');
			this.PostModel.roleComment = $(
					'.topic-create-setup-private ul li.active').data('menu');
			this.UserModel.pageCurrent = Aes
					.decrypt($('#qb-input-fo100').val());
			this.PostModel.nshare = 0;
			this.PostModel.language = 'vn';
			this.PostModel.content = encodeURI(escapeHtml(html.html));
			this.UserModel.PostModel = this.PostModel;
			
			socket.emit('sendM150INSERTTABM150', this.UserModel);
		},
		loadBackground : function() {
			$('#wrapper-background-video').css(
					{
						'background' : 'url("https://topic.oohhay.com'
								+ $('#qb-topic-background').val() + '")',
						" background-attachment" : "fixed",
						"background-position" : "center top",
						"background-repeat" : "no-repeat",
						"background-size" : "cover"
					});
		},
		addEventListener : function() {
			var $self = $(this)[0];
			$(document).on(
					'click',
					'.btn-menu-close',
					function(e) {
						e.preventDefault();
						window.location.href = socketio.host
								+ $('#qb-userlogin-hv101').val();
					})

			// su kien danh cho checkbox object name
			$(document).on('change', '.input-object-name-ckb', function() {
				console.log($self);
				$self.ckbObjectName($(this).is(":checked"));
			})

			// su kien cho otag
			$(document)
					.on(
							'itemAdded',
							'input[data-role="tagsinput"]',
							function(event) {

								var otag = '.topic-create-tag';

								$
										.each(
												$(otag
														+ ' .tag.label.label-info'),
												function(key, value) {
													var val = $(value).text()
															.trim();
													if (val.indexOf('!') == -1) {
														$(value)
																.html(
																		"!"
																				+ val
																				+ '<span data-role="remove"></span>');
													}
												})
							});

			// su kien cap quyen khi tao và edit topic
			$(document).on('click', '.topic-create-func-setup li',
					function(event) {
						var $self = $(this);

						var value = $self.data('menu');
						var parent = $self.parents('.topic-create-func-setup');

						parent.findChild('li').removeClass('active');
						$self.addClass('active');

						var input_role = parent.findChild('.input_roletopic');
						input_role.val(value);
					})

			/*
			 * su kien cho combox
			 * 
			 */
			$(document).on('click', '.btn-group:not(.not) ul li',
					function(event) {
						var $self = $(event.currentTarget);
						$self.parent().children('li').each(function() {
							$(this).removeClass('active');
						})

						var icon = $self.findChild('b').clone();

						// truong hop co item
						// if(icon.html() == ''){
						$self.addClass('active');
						var button = $self.parents('ul').prev();
						button.children('b').remove();
						button.prepend(icon);
						// }

					})
		},
		getContextPath : function() {
			return $('#contextPath').val()
		},
		// https://topic.oohhay.com/sdt/2556/nhac-cua-tui?language
		utilBuildFriendLy : function(topicid, friendly, http) {
			friendly = decodeURI(unescapeHtml(friendly));

			var currentLocation = window.location;

			// var newURL = this.getContextPath() + "/" ;
			var newURL = socketio.host;

			newURL += "t" + topicid + "/" + this.string_to_slug(friendly);

			// var search = currentLocation.search.substring(1);
			// if(search != ''){
			// newURL += "?" + search;
			// status_url_search = "?" + search;
			// }
			return newURL;
		},
		locdau : function(str) {
			str = str.toLowerCase().trim();
			str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
			str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
			str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
			str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
			str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
			str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
			str = str.replace(/đ/g, "d");
			// str=
			// str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'|
			// |\"|\&|\#|\[|\]|~|$|_/g,"-");
			/* tìm và thay thế các kí tự đặc biệt trong chuỗi sang kí tự - */
			// str= str.replace(/-+-/g,"-"); //thay thế 2- thành 1-
			str = str.replace(/^\-+|\-+$/g, "");
			// cắt bỏ ký tự - ở đầu và cuối chuỗi
			return str;
		},
		string_to_slug : function(str) {
			str = this.locdau(str);
			str = str.replace(/^\s+|\s+$/g, ''); // trim
			str = str.toLowerCase();

			// remove accents, swap ñ for n, etc
			var from = "àáäâèéëêìíïîòóöôùúüûñç·/_,:;";
			var to = "aaaaeeeeiiiioooouuuunc------";
			for (var i = 0, l = from.length; i < l; i++) {
				str = str
						.replace(new RegExp(from.charAt(i), 'g'), to.charAt(i));
			}

			str = str.replace(/[^a-z0-9 -]/g, '') // remove invalid chars
			.replace(/\s+/g, '-') // collapse whitespace and replace by -
			.replace(/-+/g, '-'); // collapse dashes

			return str;
		},
		dialogOhhay : function(elementId, callback) {
			// dialog-report
			$(elementId).jsOhhayDialog({
				save : callback
			});
		}
	}
}());