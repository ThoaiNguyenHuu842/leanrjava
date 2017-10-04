/*
 * author: ThongQB
 * date create: 17/10/2015
 * element web background
 */
(function() {
	webBgRepControl = {
		data : '',
		init : function() {
			this.eventListener();
		},
		eventListener : function() {
			var self = this;
			$(document).on('click', '#web-edit-changebg', function(event) {
				backGroundGobal.open({
					callBack : function(result) {
						self.data = result;
						self.update();
					},
					targetBox : $('.wrapper-background')
				});
			});
		},
		/*
		 * create CSS background
		 */
		createBGCSSTemplate : function(src, type) {
			var height_window = $(window).height();
			var background_repeat = "no-repeat";
			var background_size = "cover";
			if (type == 'repeat') {
				background_size = "initial";
				background_repeat = "repeat";
			}
			$('.wrapper-background').css({
				'background-image' : 'url(' + src + ')',
				'background-size' : background_size,
				'background-repeat' : background_repeat,
				'position' : 'fixed',
				'background-color' : 'initial',
				'width' : '100%',
				'background-attachment' : 'fixed',
				'background-position' : 'top center',
				'top' : '0',
				'height' : height_window + 'px',
				'z-index' : '-10'
			});
		},
		/*
		 * 
		 */
		change : function(result) {
			webBgRepControl.data = result;
			webBgRepControl.update();
		},
		/*
		 * set data to web background
		 */
		update : function() {
			if (webBgRepControl.data.type == 'bg-repeat') {
				webBgRepControl.createBGCSSTemplate(webBgRepControl.data.data, "repeat");
				$('#wrapper-background-video').html('');
				$('.explainer_vid_bg').load();
			} else if (webBgRepControl.data.type == 'video') {
				var video = '<video style="width:100%;" autoplay="" class="explainer_vid_bg" loop="" poster="">';
				video += '		<source id="mp4_source" src="' + webBgRepControl.data.data + '" type="video/mp4"></source>';
				video += '	</video>';
				$('#wrapper-background-video').html(video);
				$('.explainer_vid_bg').load();
			} else if (webBgRepControl.data.type == 'color') {
				var height_window = $(window).height();
				$('#wrapper-background-video').html('');
				$('.wrapper-background').css({
					'background' : webBgRepControl.data.data,
					'position' : 'fixed',
					'width' : '100%',
					'background-attachment' : 'fixed',
					'background-position' : 'top center',
					'top' : '0',
					'height' : height_window + 'px',
					'z-index' : '-10'
				});
			} else if (webBgRepControl.data.type == 'image') {
				webBgRepControl.createBGCSSTemplate(webBgRepControl.data.data, "cover");
				$('#wrapper-background-video').html('');
			} else if (webBgRepControl.data.type == 'gradient-x') {
				$('.wrapper-background').css({
					"background" : "-webkit-linear-gradient(left, " + webBgRepControl.data.data + ")",
					"background" : "-moz-linear-gradient(right, " + webBgRepControl.data.data + ")",
					"background" : "-o-linear-gradient(right, " + webBgRepControl.data.data + ")",
					"background" : "linear-gradient(to right, " + webBgRepControl.data.data + ")",
					"background-color" : "initial",
					'position' : 'fixed',
					'width' : '100%',
					'background-attachment' : 'fixed',
					'background-position' : 'top center',
					'top' : '0',
					'height' : height_window + 'px',
					'z-index' : '-10'
				});
				$('#wrapper-background-video').html('');
			} else if (webBgRepControl.data.type == 'gradient-y') {
				$('.wrapper-background').css({
					"background" : "-webkit-linear-gradient(" + webBgRepControl.data.data + ")",
					"background" : "-moz-linear-gradient(" + webBgRepControl.data.data + ")",
					"background" : "-o-linear-gradient(" + webBgRepControl.data.data + ")",
					"background" : "linear-gradient(" + webBgRepControl.data.data + ")",
					"background-color" : "initial",
					'position' : 'fixed',
					'width' : '100%',
					'background-attachment' : 'fixed',
					'background-position' : 'top center',
					'top' : '0',
					'height' : height_window + 'px',
					'z-index' : '-10'
				});
				$('#wrapper-background-video').html('');
			}
		}
	}
}());