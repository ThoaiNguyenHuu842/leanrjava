/**
 * ThoaiVt - 04/04/2017
 */

(function() {
	ohhayHome = {
		init : function() {
			var get_hash = window.location.hash;
			if (get_hash != undefined && get_hash != "") {
				window.location.href = get_hash;
				var scroll = $(get_hash).offset().top;
				console.log(scroll);
				$(window).scrollTop(scroll+5);
			}

			ohhayHome.eventListener();
			
			$('.main-data-home .item-fix-data').css("opacity", 1);
			// ohhayHome.loadListEvoTemplate();

			// var backgroundMove = $(".main-data-home .data-home-first");
			var boxItem = $(".main-data-home .data-home-first .content-data .title-data .catalog-home .item-intro-home");
			var menutop = $(".menu-top");
			var textam1 = $(".main-data-home .data-home-first .content-data .title-data .text-am-1");
			var textam2 = $(".main-data-home .data-home-first .content-data .title-data .text-am-2");
			var textam3 = $(".main-data-home .data-home-first .content-data .title-data .text-am-3");
			var buttonCr = $(".main-data-home .data-home-first .content-data .title-data .create-page-now");

			var timeLine = new TimelineLite();

			timeLine.from(menutop, 0.8, {
				top : 200,
				autoAlpha : 0
			}, "+=0.5");
			timeLine.from(textam1, 0.8, {
				left : 100,
				autoAlpha : 0
			}, "-=0.25");
			timeLine.from(textam2, 0.8, {
				left : 100,
				autoAlpha : 0
			}, "-=0.25");
			timeLine.from(textam3, 0.8, {
				scale : 0,
				autoAlpha : 0
			}, "-=0.25");
			timeLine.add("stagger", "+=0.25");
			// this tween is added
			timeLine.staggerFrom(boxItem, 0.5, {
				scale : 0,
				autoAlpha : 0
			}, 0.4, "stagger");
			
			
			timeLine.from(buttonCr, 0.8, {
				left : 100,
				autoAlpha : 0
			}, "-=0.25");
			timeLine.play();
			ohhayHome.initScrollAnimations();
		},
		eventListener : function() {
		
			
			// open dialog play video
			$(document).on('click', '.main-data-home .data-home-first .content-data .title-data .custom-design .img-play-home', function() {
				document.getElementById("dialoghidden-video-home").style.height = "100%";
			});
			// close dialog play video
			$(document).on('click', '#dialoghidden-video-home .closebtn', function() {
				document.getElementById("dialoghidden-video-home").style.height = "0%";
			});

			$(document).on('click', '.content-data-info .content-data .text-content .list-choice-evotemplate .item', function() {
				// $(this).find(".tg-hover .tg-hover-content
				// .tg-hover-content")
			});
		},
		initScrollAnimations : function() {
			var controller = $.superscrollorama({
				triggerAtCenter : false,
				playoutAnimations : true,
				reverse : false
			});
			/*
			 * text animation
			 */

			var boxItem1 = $(".content-data-info .content-data .text-content .list-choice-evotemplate .item");
			/*var boxItem1 = $(".content-data-info .content-data .text-content .list-choice-evotemplate .cover-lineup1");
			var boxItem1a = $(".content-data-info .content-data .text-content .list-choice-evotemplate .cover-lineup5");
			var boxItem1b = $(".content-data-info .content-data .text-content .list-choice-evotemplate .cover-lineup9");
			var boxItem1c = $(".content-data-info .content-data .text-content .list-choice-evotemplate .cover-lineup13");
			var boxItem1d = $(".content-data-info .content-data .text-content .list-choice-evotemplate .cover-lineup17");*/

			var timeLineIt = new TimelineLite();
			timeLineIt.staggerFrom(boxItem1, 0.4, {
				scale : 0,
				autoAlpha : 0
			}, 0, "stagger");

			// this tween is added
			/*timeLineIt.staggerFrom(boxItem1,  0.4,{
				bottom : 200,
				autoAlpha : 0
			}, 0.4, "stagger");
			timeLineIt.add("stagger", "+=0.4")
			timeLineIt.staggerFrom(boxItem1a,   0.4,{
				bottom : 200,`
				autoAlpha : 0
			}, 0.4, "stagger");
			timeLineIt.add("stagger", "+=0.4")
			timeLineIt.staggerFrom(boxItem1b,   0.3,{
				bottom : 200,
				autoAlpha : 0
			}, 0.4, "stagger");
			timeLineIt.add("stagger", "+=0.4")
			timeLineIt.staggerFrom(boxItem1c,   0.4,{
				bottom : 200,
				autoAlpha : 0
			}, 0.4, "stagger");
			timeLineIt.add("stagger", "+=0.4")
			timeLineIt.staggerFrom(boxItem1d,   0.4,{
				bottom : 200,
				autoAlpha : 0
			}, 0.4, "stagger");*/
			

			var timeLine1 = new TimelineLite();
			var infos = $(".web-info .item-infos .am1");
			var infos1 = $(".web-info .item-infos .am2");
			var infos2 = $(".web-info .item-infos .am3");
			timeLine1.staggerFrom(infos,   0.3,{
				left : 100,
				autoAlpha : 0
			}, 0.3, "stagger");
			timeLine1.add("stagger", "+=0.3")
			timeLine1.staggerFrom(infos1,   0.3,{
				right : 100,
				autoAlpha : 0
			}, 0.3, "stagger");
			timeLine1.add("stagger", "+=0.3")
			timeLine1.staggerFrom(infos2,   0.3,{
				left : 100,
				autoAlpha : 0
			}, 0.3, "stagger");
			/*
			 * section generic animation
			 */
			if (ohhayHome.isMobile() == false) {

				/*controller.addTween('.main-data-home .data-home-first .content-data .title-data .catalog-home', TweenMax.from($('.content-data-info .content-data .text-1'), 1.1, {
					css : {
						opacity : 0,
						bottom : "300px"
					},
					ease : Expo.easeOut
				}));
				controller.addTween('.main-data-home .data-home-first .content-data .title-data .catalog-home', TweenMax.from($('.content-data-info .content-data .text-2'), 1.1, {
					css : {
						opacity : 0,
						right : "300px"
					},
					ease : Expo.easeOut
				}));
				controller.addTween('.main-data-home .data-home-first .content-data .title-data .catalog-home', TweenMax.from($('.content-data-info .content-data .text-3'), 1.1, {
					css : {
						opacity : 0,
						top : "300px"
					},
					ease : Expo.easeOut
				}));*/

				controller.addTween('.content-data-info.item-fix-data', timeLineIt);
				controller.addTween('.content-data-info .content-data .your-create-website .btn-your-createwebsite', timeLine1);

				controller.addTween('.content-data-info .content-data .your-create-website', TweenMax.from($('.web-info .content-section .anlong .name-customer'), 1.5, {
					css : {
						opacity : 0,
						right : "100px"
					},
					ease : Back.easeOut
				}));
				controller.addTween('.content-data-info .content-data .your-create-website', TweenMax.from($('.web-info .content-section .main-content.anlong .img-right'), 1.5, {
					css : {
						opacity : 0,
						left : "100px"
					},
					ease : Back.easeOut
				}));

//				var offset = $('.web-info .content-section .name-customer .divset-am-img1').offset().top + 200;
//				var offset1 = $('.web-info .content-section .name-customer .divset-am-img2').offset().top + 200;
//				var offset2 = $('.web-info .content-section .name-customer .divset-am-img3').offset().top + 200;

				

			} else {
				controller.addTween('.content-data-info .content-data .text-content .list-choice-evotemplate .item:last-child', TweenMax.from($('.web-info .content-section .anlong .name-customer'), 1.5, {
					css : {
						opacity : 0,
						right : "100px"
					},
					ease : Back.easeOut
				}));
				controller.addTween('.content-data-info .content-data .text-content .list-choice-evotemplate .item:last-child', TweenMax.from($('.web-info .content-section .main-content.anlong .img-right'), 1.5, {
					css : {
						opacity : 0,
						left : "100px"
					},
					ease : Back.easeOut
				}));

//				var offset = $(/*'.web-info .content-section .name-customer .divset-am-img1').offset().top + 20;
//				var offset1 = $('.web-info .content-section .name-customer .divset-am-img2'*/).offset().top + 20;
//				var offset2 = $('.web-info .content-section .name-customer .divset-am-img3').offset().top + 20;
				controller.addTween('.content-data-info.item-fix-data', timeLineIt);
				controller.addTween('.web-info', timeLine1);
/*				controller.addTween('.main-data-home .data-home-first .content-data .title-data .create-page-now', timeLine);*/
			}
		},
		isMobile : function() {
			try {
				document.createEvent("TouchEvent");
				return true;
			} catch (e) {
				return false;
			}
		},
		scrollToElement : function() {
			var element_position = $('#scroll-to').offset().top;

			$(window).on('scroll', function() {
				var y_scroll_pos = window.pageYOffset;
				var scroll_pos_test = element_position;

				if (y_scroll_pos > scroll_pos_test) {
					// do stuff
				}
			});
		}

	}
}())
