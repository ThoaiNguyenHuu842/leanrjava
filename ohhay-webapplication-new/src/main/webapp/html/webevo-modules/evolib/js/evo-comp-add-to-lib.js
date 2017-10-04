/**
 * ThoaiVt 30-03-2017
 */

(function() {
	adminAddlibrary = {
		idCp : 0,
		init : function() {
			if (!adminAddlibrary.inited) {
				$('.dialogadmin-addcomponent-library').webToolDialog(350);
				adminAddlibrary.inited = true;
			}
			adminAddlibrary.eventListener();
			evoComLib.init();
		},
		open : function() {
			$('.dialogadmin-addcomponent-library').dialog("open");
			// evoComLib.loadListComponentLib("normal-button","LIB");
		},
		close : function() {
			$('.dialogadmin-addcomponent-library').dialog("close");
		},
		eventListener : function() {
			$(document).on('click', '.btnadmin-set-tolibrary', function() {
				adminAddlibrary.open();
				var element = $(this).closest('.grid-stack-item').find('.qb-component');
				adminAddlibrary.elementShot(element);
				adminAddlibrary.idCp = $(this).closest('.grid-stack-item-content').attr("qb-component-id");
			});
			/*
			 * image change
			 */
			$(document).on('click', '.dialogadmin-addcomponent-library .img_data', function(e) {
				e.preventDefault();
				$('#admindlg-addcoponent-tolibrary').trigger('click');
			});
			try {
				document.getElementById('admindlg-addcoponent-tolibrary').addEventListener('change', adminAddlibrary.handleFileSelect, false);
			} catch (e) {

			}

			$(document).on('click', '.dialogadmin-addcomponent-library .addcoponent-to-library', function(e) {
				e.preventDefault();
				adminAddlibrary.addComponentToLib();
			});
		},
		addComponentToLib : function() {
			var ev901 = $('.dialogadmin-addcomponent-library .name-component-toadd').val();
			var ev902 = $('.dialogadmin-addcomponent-library .img_data').attr('url-imag-cp');
			var idComponent = parseInt(adminAddlibrary.idCp);
			console.log(ev901 + " : " + ev902 + " : " + idComponent);
			if (ev901 != "" && ev902 != "") {
				$.ajax({
					type : "POST",
					url : encodeUrl("componentLibraryBean.addComponentToLib"),
					data : {
						"id" : idComponent,
						"ev901" : ev901,
						"ev902" : ev902
					},
					success : function(response) {
						if (response.status == AJAX_SUCESS) {
							console.log(response);
							showGrowlMessageSuccess();
							adminAddlibrary.close();
						} else {
							showGrowlMessageError();
						}
					},
					error : function(e) {
						showGrowlMessageAjaxError();
					}
				});
			} else {
				util.messageDialog("Cannot add component . check value !");
			}
		},
		saveCropbox : function(base64, webId, check) {
			base64 = ((check == true) ? base64 : "data:image/jpeg;base64," + base64);
			formData = new FormData();
			console.log(base64);
			if (isNaN(webId))
				webId = webId.split('c')[1];
			formData.append('webId', webId);
			formData.append('imgBase64', base64);
			formData.append('src', 'addminlibcp');
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
						// $('.dialogadmin-addcomponent-library
						// .img_Data').attr('src', url);
						$('.dialogadmin-addcomponent-library .img_data').attr('url-imag-cp', url);
						$('.dialogadmin-addcomponent-library .img_data').css({
							"background" : "url(" + url + ")",
							"background-size" : "100% 100%"
						});
					} else {
						showMessage("You must save this component before add to library");
					}
				},
				error : function(e) {
					showMessage("You must save this component before add to library");
				}
			});
		},
		handleFileSelect : function(evt) {
			var files = evt.target.files;
			var file = files[0];
			if (files && file) {
				var reader = new FileReader();
				reader.onload = function(readerEvt) {
					var binaryString = readerEvt.target.result;
					adminAddlibrary.saveCropbox(btoa(binaryString), 0, false);
				};
				reader.readAsBinaryString(file);
			}
		},
		elementShot : function(element) {
			html2canvas($(element), {
				onrendered : function(canvas) {
					theCanvas = canvas;
					console.log(canvas.toDataURL());
					adminAddlibrary.saveCropbox(canvas.toDataURL(), 0, true);
				}
			});
		},
		switchTypeShotImage : function(element, type) {
			var elementShot;
		},
		removeFromLib : function(compId) {
			$.ajax({
				type : "POST",
				url : encodeUrl("componentLibraryBean.removeComponentFromLib"),
				data : {
					"id" : parseInt(compId)
				},
				success : function(response) {
					if (response.status == 'SUCCESS') {
						$('#web-tools .item .admin-library-load .comp-lib-item-' + compId).hide();
					} else
						util.messageDialog(response.result[0].defaultMessage);
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		}
	}
}());