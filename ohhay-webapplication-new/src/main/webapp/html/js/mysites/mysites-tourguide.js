/**
 * @author ThoaiNH
 * create Nov 24, 2016 
 * my site user guide
 */
(function() {
	mySitesTourGuide = {
		init : function() {
			mySitesTourGuide.eventListener();

			var enjoyhint_instance = new EnjoyHint({
				onEnd : function() {
					historyManager.updateKey("NEWBIE_FIRST_ACCESS_MYSITE");
				}
			});
			var enjoyhint_script_steps = [
					{
						'click .info-detail' : getLocalize('tg_step1'),
						shape : 'circle',
						showNext : true,
						showSkip : true,
						"nextButton" : {text: getLocalize('tg_btnnext')},
	                    "skipButton" : {text: getLocalize('tg_btnskip')}
					},
					{
						'click .new-my-stites' : getLocalize('tg_step2'),
						shape : 'circle',
						showNext : true,
						showSkip : true,
						"nextButton" : {text: getLocalize('tg_btnnext')},
	                    "skipButton" : {text: getLocalize('tg_btnskip')}
					},
					{
						'click .img_page' : getLocalize('tg_step3'),
						shape : 'rect',
						showNext : true,
						showSkip : true,
						"nextButton" : {text: getLocalize('tg_btnnext')},
	                    "skipButton" : {text: getLocalize('tg_btnskip')}
					},
					{
						'click .goto-pc-editor-on-mysite' : getLocalize('tg_step4'),
						shape : 'circle',
						showNext : true,
						showSkip : true,
						"nextButton" : {text: getLocalize('tg_btnnext')},
	                    "skipButton" : {text: getLocalize('tg_btnskip')}
					},
					{
						'click .goto-seo-on-mysite' : getLocalize('tg_step5'),
						shape : 'circle',
						showNext : true,
						showSkip : true,
						"nextButton" : {text: getLocalize('tg_btnnext')},
	                    "skipButton" : {text: getLocalize('tg_btnskip')}
					},
					{
						'click .duplicate-on-mysite' : getLocalize('tg_step6'),
						shape : 'circle',
						showNext : true,
						showSkip : true,
						"nextButton" : {text: getLocalize('tg_btnnext')},
	                    "skipButton" : {text: getLocalize('tg_btnskip')}
					},
					{
						'click .view-chart-on-mysite' : getLocalize('tg_step7'),
						shape : 'circle',
						showNext : true,
						showSkip : true,
						"nextButton" : {text: getLocalize('tg_btnnext')},
	                    "skipButton" : {text: getLocalize('tg_btnskip')}
					},
					/*{
						'click .share-for-designer-on-mysite' : getLocalize('tg_step8'),
						shape : 'circle',
						showNext : true,
						showSkip : true,
						"nextButton" : {text: getLocalize('tg_btnnext')},
	                    "skipButton" : {text: getLocalize('tg_btnskip')}
					},*/
					{
						'click .delete-on-mysite' : getLocalize('tg_step9'),
						shape : 'circle',
						showNext : true,
						showSkip : true,
						"nextButton" : {text: getLocalize('tg_btnnext')},
	                    "skipButton" : {text: getLocalize('tg_btnskip')}
					}
			];
			enjoyhint_instance.set(enjoyhint_script_steps);
			enjoyhint_instance.run();
		},
		eventListener : function() {
			$(document).on('click', '.enjoyhint_close_btn, .enjoyhint_skip_btn', function() {
				historyManager.updateKey("NEWBIE_FIRST_ACCESS_MYSITE");
			});
		}
	}
}());