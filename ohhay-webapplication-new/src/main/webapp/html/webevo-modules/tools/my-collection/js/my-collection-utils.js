/**
 * @author ThoaiNH create Oct 17, 2015 utils for process collection data
 */
(function() {
	myCollectionUtils = {
		limit : 0,// limit page load
		numberPage : 0,// total summation page
		limitImgFree : 0,// limit page load free img
		numberPageImgFree : 0,// total summation page
		/*
		 * upload image to aws mode: web (upload hinh khi edit web), khac
		 * (upload hinh vao my collection) webId: id trang web evo imgBase64:
		 * img data src: image hay background mode: web , upload image for
		 * current web
		 */
		upload : function(params) {
			var webId = 0;
			if (params.mode == 'web' || web.TRIAL == 'on')
				webId = webCreater.data.id;
			var formData = new FormData();
			formData.append('webId', webId);
			formData.append('imgBase64', params.imgBase64);
			formData.append('src', params.src);
			formData.append('ext', params.ext);
			$.ajax({
				type : "POST",
				url : encodeUrl("myCollectionBean.upLoad"),
				cache : false,
				contentType : false,
				processData : false,
				data : formData,
				success : function(response) {
					params.callBack(response);
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});

		},
		/*
		 * load my image
		 */
		load : function(params, callback) {
			if (params.limit == 29)
				myCollectionUtils.limit = 29;
			$.ajax({
				type : "GET",
				url : encodeUrl("myCollectionBean.load"),
				data : {
					src : params.src,
					limit : params.limit,
					offset : ((params.offset) != undefined ? params.offset : 0)
				},
				success : function(response) {
					if (myCollectionUtils.limit == 29) {
						$.ajax({
							type : "GET",
							url : encodeUrl("myCollectionBean.load"),
							data : {
								src : params.src,
								limit : 1,
								offset : 0
							},
							success : function(response) {
								var data = response.result;
							},
							error : function() {

							}
						})
					}
					myCollectionUtils.limit += 24;
					if (response.result && response.result.length > 0) {
						if (params.offset == 0)
							myCollectionUtils.numberPage = response.result[0].rowss
						params.callBack(response, myCollectionUtils.numberPage);
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
		},
		/*
		 * load free image
		 */
		loadFreeImg : function(params, callback) {
			/*
			 * load free image of bonevo
			 */
			if (params.limit == 29)
				myCollectionUtils.limitImgFree = 29;
			$.ajax({
				type : "GET",
				url : encodeUrl("bonEvoImage.getListImage"),
				data : {
					src : params.src,
					limit : params.limit,
					offset : ((params.offset) != undefined ? params.offset : 0)
				},
				success : function(response) {
					if (myCollectionUtils.limitImgFree == 29) {
						$.ajax({
							type : "GET",
							url : encodeUrl("bonEvoImage.getListImage"),
							data : {
								src : params.src,
								limit : 1,
								offset : 0
							},
							success : function(response) {
								var data = response.result;
							},
							error : function() {

							}
						})
					}
					myCollectionUtils.limitImgFree += 24;
					if (response.result && response.result.length > 0) {
						if (params.offset == 0)
							myCollectionUtils.numberPageImgFree = response.result[0].rowss
						params.callBack(response, myCollectionUtils.numberPageImgFree);
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

		},
		/*
		 * remove image
		 */
		remove : function(pp950) {
			$.ajaxSetup({
				beforeSend : function() {
				},
				complete : function() {
					setDefaultAjaxStatus();
				}
			});
			$.ajax({
				type : "POST",
				url : encodeUrl("myCollectionBean.remove"),
				data : {
					pp950 : pp950
				},
				success : function(response) {
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		}
	}
}());