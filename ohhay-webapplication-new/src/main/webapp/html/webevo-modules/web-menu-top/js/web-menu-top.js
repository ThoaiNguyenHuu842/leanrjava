/*
 * author: ThoaiNH
 * date create: 26/10/2015
 */
 (function() {
	 webMenuTop = {
		init : function() {
			webMenuTop.eventListener();
		},
		eventListener : function() {
			/*
			 * on click show EVO library
			 */

			$('#content-wrapper').getNiceScroll().hide();
			$(document).on('click','#menu-tools .btn-show-evo-lib',function(){
				if(web.TRIAL != 'on')
					evoLib.open(2);
        		else
        			util.messageDialog(getLocalize("jswmt_title1"));
			});
			/*
			 * on click button show hidden elements
			 */
			$(document).on('click','#menu-tools #show-hiddent-element',function(){
				hiddenElements.open();
			});
			/*
			 * on/off mobile auto layout
			 */
			$(document).on('click','#menu-tools .btn-toggle-auto-layout',function(){
				$(this).find('.btn').toggleClass('active');  
			    if ($(this).find('.btn-primary').size()>0) 
			    	$(this).find('.btn').toggleClass('btn-primary');
			    $(this).find('.btn').toggleClass('btn-default');
			    var turn = $(this).children('.active').attr('data');
			    if(turn == 'off'){
			    	$('.content-wrapper-mobile').addClass('auto-layout');
			    }
			    else {
			    	$('.content-wrapper-mobile').removeClass('auto-layout');
			    }
			});
			/*
			 * ThoaiVt 09/06/2016
			 * event lazy load
			 */
			$('.qb-panel-web-config #option-lazy').on('click', function() { 
				var toogleBtn = $('.qb-panel-web-config #option-lazy button');
				toogleBtn.toggleClass('btn-default');
				toogleBtn.toggleClass('btn-primary');
				toogleBtn.toggleClass('active');
				var lazyLoad = ($('#qb-web-config #option-lazy button.active').attr('data')==='on');
				((lazyLoad==true)? webConfig.data.imgLazy = 1 : webConfig.data.imgLazy = 0 );
				console.log(webConfig.data.imgLazy + " lazy : "+lazyLoad);
			});
			/*
			 * on/off ruler
			 */
			$(document).on('click','#btn-ruler',function(){
				if($(this).hasClass('disable'))
				{
					webRuler.showRuler();
					$(this).removeClass('disable');
					$(this).attr('title',getLocalize("jswmt_title2"));
				}
				else
				{
					webRuler.hideRuler();
					$(this).addClass('disable');
					$(this).attr('title',getLocalize("jswmt_title3"));
				}
			});
            /*
             * show web config dialog
             */
            $(document).on('click','#menu-tools #web-config', function(){
            	console.log(util.evoProperties);
            	webConfig.open();
            });
            /*
             * onClick save
             */
            $(document).on('click','#menu-tools #save',function(){
            	if(PAGE_TOPIC_CONTANTS != 0){
            		web.goToPreviewMode();
            		socketio.saveTopic({
        				html:  webBuilder.getWebHTML()
        			});
            	}else{
            		/*Thong: remove seleted on editor*/
            		$('.qb-component').find('[jqte-setflag]').removeAttr('jqte-setflag');
            		//save normal
            		if(web.TRIAL != 'on')
            			webBuilder.save();
            		else
            		{
            			
            			if(web.FAST && web.FAST == 'fast-with-fb')
            				$('.btn-fb-login').trigger('click');//save when using create web with fb info
            			else
            				$('.register-panel').fadeIn();//save when using trial
            		}
            	}
			});
        	
            /*
             * onClick preview
             */
        	$(document).on('click','#menu-tools #preview',function(){
				$(this).removeClass('animated').addClass('animated');
				var btn_preview = '<p class="preview animated flipInY"><img src="'+util.contextPath()+'/html/images/menu-preview.png" alt="" style="width: 19px;">'+getLocalize("setting_preview")+'</p>';
				var btn_edit = '<p class="preview  animated flipInY">'+getLocalize("jswmt_title4")+'</p>';
				$(this).html('');
				if($(this).hasClass('preview'))
				{
					$(this).append(btn_preview);
					$(this).removeClass('preview');
					$('#content-wrapper').getNiceScroll().hide();
					//disiable all class for ipad landscape and portrait
					$('#content-wrapper').removeClass("fix_previewipad_height fix_height_content_ipad_lanscape");
					//remove class landscape
	        		$('#content-wrapper .content-box .grid-stack').removeClass("ipad-preview-fix-landscape-width fix_previewipad_width fix_width_ipad_landscape");
					//hidden class ipadreview
	        		$('#menu-tools #preview-ipad').removeClass("show-edit-pc-from-preview-ipad").addClass("hidden-edit-pc-from-preview-ipad");
					//hidden css draw ipad
	        		$('#qb-ipad-view-top,#qb-ipad-view-top-landscape').css("display","none");
	        		$('.qb-ohhay-notfound-wrapper .wrapper-background,#wrapper-background-video').removeClass("fix_landscape_background_ipad fix_portrait_background_ipad");
					web.goToEditMode();
					$('#qb-phone-view-body').hide();
				}
				else
				{
					$(this).append(btn_edit);
					$(this).addClass('preview');
					//show class ipadreview
					$("body").animate({ scrollTop: 0 },300,function(){
						web.goToPreviewMode();
						if(web.MOBILE_EDITOR == 'on')
							$('#qb-phone-view-body').show();
					});
				
					if(web.MOBILE_EDITOR == 'off'){
						$('#menu-tools #preview-ipad').removeClass("hidden-edit-pc-from-preview-ipad").addClass("show-edit-pc-from-preview-ipad");
						$('#qb-phone-view-body').hide();
						$('#content-wrapper').getNiceScroll().hide();
					}
					
				}
			});
        	
        	/*
        	 * ThoaiVt 21/01/2016
        	 * click preview on ipad
        	 */
        	$(document).on('click','#menu-tools #preview-ipad .ipad-portrait-preview',function(){
        		$("body").animate({ scrollTop: 0 },300,function(){
        		//show css draw ipad
        		$('#qb-ipad-view-top').css("display","block");
        		//set animated
        		$('#qb-ipad-view-top-landscape').css("display","none");
        		$('.qb-ohhay-notfound-wrapper .wrapper-background,#wrapper-background-video').removeClass("fix_landscape_background_ipad").addClass("fix_portrait_background_ipad");
        		$(this).removeClass('animated').addClass('animated');
        		//hidden button preview
				$('#preview-ipad').addClass("show-edit-pc-from-preview-ipad");
				//add class set height
				$('#content-wrapper').addClass("fix_previewipad_height");
				//remove class landscape
        		$('#content-wrapper').removeClass("fix_height_content_ipad_lanscape");
        		$('#content-wrapper .content-box .grid-stack').removeClass("fix_width_ipad_landscape").addClass("fix_previewipad_width");
				//set preview
				web.goToPreviewMode();
				$('#content-wrapper').getNiceScroll().hide();
        		$('#content-wrapper').niceScroll();
				$('#content-wrapper').getNiceScroll().show();
        		});
        	});
        	$(document).on('click','#menu-tools #preview-ipad .ipad-close-preview',function(){
        		//hidden css draw ipad
        		$('#qb-ipad-view-top,#qb-ipad-view-top-landscape').css("display","none");
        		//show preview
        		$('#preview').css("display","block");
        		//remove style css for background landscape and portrait
        		$('.qb-ohhay-notfound-wrapper .wrapper-background,#wrapper-background-video').removeClass("fix_landscape_background_ipad fix_portrait_background_ipad");
        		//remove class set height for ipad
				$('#content-wrapper').removeClass("fix_previewipad_height fix_height_content_ipad_lanscape");
				//remove class landscape
        		$('#content-wrapper .content-box .grid-stack').removeClass("ipad-preview-fix-landscape-width fix_previewipad_width fix_width_ipad_landscape");
				//remove class set width for ipad
        		web.goToPreviewMode();
        		$('#content-wrapper').getNiceScroll().hide();
        		$('#content-wrapper').niceScroll();
				$('#content-wrapper').getNiceScroll().show();
        	});
        	$(document).on('click','#menu-tools #preview-ipad .ipad-landscape-preview',function(){
        		$("body").animate({ scrollTop: 0 },300,function(){
        		//remove class set height for ipad
				$('#content-wrapper').removeClass("fix_previewipad_height").addClass("fix_height_content_ipad_lanscape");
				//set style css for background landscape and portrait
				$('.qb-ohhay-notfound-wrapper .wrapper-background,#wrapper-background-video').removeClass("fix_portrait_background_ipad").addClass("fix_landscape_background_ipad");
				//remove class set width for ipad
				$('#content-wrapper .content-box .grid-stack').removeClass("fix_previewipad_width").addClass("fix_width_ipad_landscape");	
				//add class style for landscape
        		$('#qb-ipad-view-top').css("display","none");
        		//show ipad landscape
        		$('#qb-ipad-view-top-landscape').css("display","block");
        		$('#content-wrapper').getNiceScroll().hide();
        		$('#content-wrapper').niceScroll();
				$('#content-wrapper').getNiceScroll().show();
        		});
        	});
        	//end preview ipad
        	
        	/*show on desktop*/
        	$(document).on('click','#menu-tools .btn-menu-desktop',function(){
        		if(web.MOBILE_EDITOR == 'on')
					setParam("editmode", "element", document.URL);
        	});
        		
        	/*show on mobile*/
        	$(document).on('click','#menu-tools .btn-menu-mobile',function(){
        		if(web.TRIAL != 'on')
        		{
        			/*
        			 * window.onbeforeunload = null;
        			$("body .dialogreload").dialog({
        		        resizable: false,
        		        modal: true,
        		        height: 222,
        		        width: 590,
        		        buttons: [
        		                  {
        		                  	text : getLocalize("webho_title3"),
        		                  	"class": 'dialogreload-submit_class_name',
        		                  	click : function () {
        		                  	$(this).dialog('close');
        		                  	}
        		                  },
        		                 { 
        		                	text : getLocalize("webho_title4"),
        		                    "class": 'dialogreload-submit_class_name1',
              		            	click : function () {
              		                $(this).dialog('close'),
              		                setParam("editmode", "elementmb", document.URL);
              		            	}
              		            }
              		            
        		              ]
        		      
        		        });
        			$(".ui-dialog-titlebar").hide();
        			$('body .ui-dialog-buttonpane .ui-dialog-buttonset .ui-button.dialogreload-submit_class_name').attr('style','background:rgba(181, 181, 181, 1) !important;color:white !important;padding: 3px;');
        			$('body .ui-dialog-buttonpane .ui-dialog-buttonset .ui-button.dialogreload-submit_class_name1').attr('style','background:rgba(0, 153, 255, 1) !important;color:white !important;padding: 3px;');
        			 */
        			setParam("editmode", "elementmb", document.URL);
        		}
        		else
        			util.messageDialog(getLocalize("jswmt_title1"));
        	});
        	
        	/*show dialog add box*/
        	$(document).on('click','#menu-tools .btn-add-box, .btn-click-add-box',function(){
        		$('#dia-add-box').show();
        	});
        	$(document).on('click','.content-start',function(){
        		$(this).hide();
        		$('#menu-tools').hide();
        		$('#panel-no-box').hide();
        		$('#dia-add-box').show();
        	});
        	/*hide dialog add box*/
        	$(document).on('click','#dia-add-box .dia-add-box-hide',function(){
        		if(web.START_WITH_NOBOX == false)
        			$('#dia-add-box').hide();
        	});
        	/*
        	 * show/hide grid line
        	 */
        	$(document).on('click','#menu-tools #show-grid-line',function(){
        		if(web.GRID_LINE == 'on'){
        			web.GRID_LINE = 'off';
        			grid.hideGridLine();
        			$(this).attr('title','Show grid line');
        			$(this).find('img').attr('src', util.contextPath() + '/html/images/menu-grid.png');
        		}
        		else {
        			web.GRID_LINE = 'on';
        			grid.showGridLine();
        			$(this).attr('title','Hide grid line');
        			$(this).find('img').attr('src', util.contextPath() + '/html/images/menu-grid-on.png');
        		}
        	});
        	/*
        	 * close tip
        	 */
        	 $(document).on('click','#menu-tools .btn-close-tip', function(){
             	$(this).parent('.panel-mobile-option').hide();
             	historyManager.updateKey("NEWBIE_FIRST_ACCESS_MOBILE_EDITOR");
             });
        	 
        	 /*
        	  * ThoaiVt - 09-08-2016
        	  * event undo and redo
        	  */
        	 $(document).on('click','#redo-undo-manager',function(){
        		 undoMgr.undo();
        		 undoRedoMgr.undoRedoChange=false;
        		 console.log("First data : "+undoRedoMgr.firstChange);
        		 console.log("undo ");
        	 });
        	 $(document).on('click','#redo-undo-manager-redo',function(){
        		 undoMgr.redo();
        		 undoRedoMgr.undoRedoChange=false;
        		 console.log("First data : "+undoRedoMgr.firstChange);
        		 console.log("redo ");
        		 
        	 });
        	 /*
        	  * Mouse up catch event change each element more times
        	  */
        	 $('#redo-undo-manager-redo,#redo-undo-manager').mouseleave(function(){
        		 undoRedoMgr.undoRedoChange=true;
        		 console.log("Mouse leave ! "+ undoRedoMgr.undoRedoChange);
        	 });
        	    	
		}
	}
}());