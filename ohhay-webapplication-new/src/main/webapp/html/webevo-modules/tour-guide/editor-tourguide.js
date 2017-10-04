/**
 * @author ThoaiNH
 * create Feb 16, 2017 
 * web editor user guide
 */
(function() {
	editorTourGuide = {
		init : function() {
			editorTourGuide.eventListener();

			var enjoyhint_instance = new EnjoyHint({
				onEnd : function() {
					if(web.TRIAL != 'on')
						historyManager.updateKey("NEWBIE_FIRST_ACCESS_PC_EDITOR");
					else
						sessionStorage.hasClosedTourguide = 1;
				}
			});
			var enjoyhint_script_steps = [
					{
						'click .btn-add-box' : getLocalize('pce_step1'),
						shape : 'rect',
						showNext : true,
						showSkip : true
					},
					{
						'click .btn-show-evo-lib' :  getLocalize('pce_step2'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click #web-edit-changebg' :  getLocalize('pce_step3'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click #web-config' :  getLocalize('pce_step4'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click #show-grid-line' :  getLocalize('pce_step5'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click .btn-menu-mobile' :  getLocalize('pce_step6'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click .btn-menu-preview' :  getLocalize('pce_step7'),
						shape : 'rect',
						showNext : true,
						showSkip : true
					},
					{
						'click .btn-menu-save' :  getLocalize('pce_step8'),
						shape : 'rect',
						showNext : true,
						showSkip : true
					},
					{
						'click .btn-menu-close' :  getLocalize('pce_step9'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click #normal-text' :  getLocalize('pce_step10'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click #normal-image' :  getLocalize('pce_step11'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click #normal-iframe' :  getLocalize('pce_step12'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click #normal-button' :  getLocalize('pce_step13'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click #normal-gmap' :  getLocalize('pce_step14'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click #normal-menu' :  getLocalize('pce_step15'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click #normal_contact_form' :  getLocalize('pce_step16'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					},
					{
						'click #normal_embed_code' :  getLocalize('pce_step17'),
						shape : 'circle',
						showNext : true,
						showSkip : true
					}
			];
			enjoyhint_instance.set(enjoyhint_script_steps);
			enjoyhint_instance.run();
		},
		eventListener : function() {
			$(document).on('click', '.enjoyhint_close_btn, .enjoyhint_skip_btn', function() {
				if(web.TRIAL != 'on')
					historyManager.updateKey("NEWBIE_FIRST_ACCESS_PC_EDITOR");
				else
					sessionStorage.hasClosedTourguide = 1;
			});
		}
	}
}());