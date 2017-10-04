/**
 * @author ThienND
 *
 * created Dec 17, 2015
 */
(function(){
	social = {
			checkFacebookLoginState:function(callBack){
				FB.getLoginStatus(function(result){
					console.log(result);
					if (result.status === 'connected') {
						callBack;
					} else if (result.status === 'not_authorized') {
						social.loginFacebook();
					} else {
						social.loginFacebook();
					}
				});
			},
			loginFacebook:function(callBack){
				FB.login(function(result){
					social.checkFacebookLoginState(callBack);
				},{scope:'public_profile,email,user_photos'});
			},
			checkLoginFlickr:function(callBack){
				$.ajax({
					url: encodeUrl('flickrBean.checkUserLogin'),
					type: 'POST',
					success:function(result){
						callBack.callBack({
							isUserLogin : result.status === 'SUCCESS',
						});
					},
					eror:function(error){
						console.log(error);
					}
				});
			},
			loginFlickr:function(){
				$.ajax({
					url : encodeUrl("flickrBean.loginFlickr"),
				    type: "POST",
				    success:function(result){
				    	var tempPopup = window.open(result.result,null,'width=1000,height=700,location=no,menubar=no,toolbar=no');
				    	// Set interval to check if it has been closed
				    	var windowCheckInterval = setInterval(function() { isPopupClosed(); }, 300);
				    	// Check function
				    	function isPopupClosed() {
				    	    if (!tempPopup || tempPopup.closed) {
				    	        clearInterval(windowCheckInterval);
				    	        social.getUserFlickr();
				    	    }
				    	};
				    },
				    error:function(e){
				    	console.log(e);
				    }
				})
			},
			logoutFlickr:function(callBack){
				$.ajax({
					url : encodeUrl("flickrBean.logoutFlickr"),
					type: "POST",
					success:function(result){
						if (result.status === 'SUCCESS'){
							callBack.callBack();
						}
					},
					error:function(e){
						console.log(e);
					}
				});
			},
			getUserFlickr:function(){
				$.ajax({
					url : encodeUrl('flickrBean.getUserFlickr'),
					type : 'POST',
					success:function(result){
						console.log(result);
						if (result.status === 'SUCCESS'){
							var user = jQuery.parseJSON(result.result);
							if (user.stat === 'ok'){
								imageAlbum.getFlickrAlbum();
							}
						}
					},
					error: function(e){
						console.log(e);
					}
				});
			},
			checkLoginInstagram:function(callBack){
				$.ajax({
					url: encodeUrl('instagramBean.checkUserLogin'),
					type: 'POST',
					success:function(result){
						callBack.callBack({
							isUserLogin : result.status === 'SUCCESS',
						});
					},
					eror:function(error){
						console.log(error);
					}
				});
			},
			loginInstagram:function(){
				$.ajax({
					url : encodeUrl("instagramBean.loginInstagram"),
				    type: "POST",
				    success:function(result){
				    	var tempPopup = window.open(result.result,null,'width=1000,height=700,location=no,menubar=no,toolbar=no');
				    	// Set interval to check if it has been closed
				    	var windowCheckInterval = setInterval(function() { isPopupClosed(); }, 300);
				    	// Check function
				    	function isPopupClosed() {
				    	    if (!tempPopup || tempPopup.closed) {
				    	        clearInterval(windowCheckInterval);
				    	        social.getUserInstagram();
				    	    }
				    	};
				    },
				    error:function(e){
				    	console.log(e);
				    }
				});
			},
			logoutInstagram:function(callBack){
				$.ajax({
					url : encodeUrl("instagramBean.logoutInstagram"),
					type: "POST",
					success:function(result){
						if (result.status === 'SUCCESS'){
							callBack.callBack();
						}
					},
					error:function(e){
						console.log(e);
					}
				});
			},
			getUserInstagram:function(){
				$.ajax({
					url : encodeUrl('instagramBean.getUserInstagram'),
					type : 'POST',
					success:function(result){
//						console.log(result);
						if (result.status === 'SUCCESS'){
							var user = jQuery.parseJSON(result.result);
							if (user.meta.code === 200){
								imageAlbum.getInstagramImages('');
							}
						}
					},
					error: function(e){
						console.log(e);
					}
				});
			},
			shareFacebook: function(urlToShare){
				FB.ui({
				    display: 'popup',
				    method: 'share',
				    href: ''+urlToShare+'',
				  }, function(response){
					  console.log(response);
				});
			},
	}
}())