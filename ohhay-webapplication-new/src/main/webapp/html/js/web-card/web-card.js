/**
 * @author ThienND
 * created Jan 30, 2016
 * 
 */
(function(){
	webCard = {
			init : function(){
				webCard.eventListener();
			},
			eventListener : function(){
				$(document).on('click','button.share-facebook',function(){
					social.shareFacebook($(location).attr('href'));
				});
				$(document).on('click','.create-card', login.checkFacebookLoginState); 
			},
	}
}());