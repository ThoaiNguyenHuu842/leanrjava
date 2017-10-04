/**
 * ThoaiVt 19/02/2016
 */
(function(){
	/**
	 * 
	 */
	adminSetting = {
		/*
		 * init
		 */
		limit : 0,
		sizePage : 0,
		offset : 0,
		padingTionPage : "",
		hashUrlData : "",
		init : function(){
			adminSetting.eventListener();
			administratorCMS.init();
			administratorAccount.init();
			administratorAnalyst.init();
			administratorBonevoImage.init();
			administratorConnection.init();
			adminBackground.init();
			adminDomainManagement.init();
			administratorVideo.init();
			adminCronjobLog.init();
			administratorTopic.init();
			
			var dataSessionValue = (localStorage.getItem("session-administrator")!=null ? localStorage.getItem("session-administrator") : "qb-administrator-content-cms");
			if(typeof dataSessionValue != "undefined"){
				//check session function
				var dataFunction = (localStorage.getItem("function-call") !=null ? localStorage.getItem("function-call") : "loadCMS" );
				//check limit session
				var limit = (localStorage.getItem("function-limit")!=null ? localStorage.getItem("function-limit") : 10);
				//check offset session
				var offset =  (localStorage.getItem("function-offset")!=null ? localStorage.getItem("function-offset") : 0);
				//check hash url 
				adminSetting.hashUrlData = (localStorage.getItem("hashNameUrl-hash")!=null ? localStorage.getItem("hashNameUrl-hash") : "qb-administrator-content-cms");
				console.log("SSGS : "+localStorage.getItem("function-offset"));
				console.log("offsetX : "+offset + " limitX : "+limit);
				/*
				 * check number page url
				 */
				offset = adminSetting.offsetActiveReload(offset,limit);
				//call function load based on session
				adminSetting.loadPageRequire((dataSessionValue!=null ? dataSessionValue : "qb-administrator-content-cms"),dataFunction,limit,offset);
			}
			//set tooltop
			$('[data-toggle="tooltip"]').tooltip(); 
		},
		/*
		 * eventListener
		 */
		eventListener : function(){
			/*
			 * event click tag function top
			 */
			$(document).on('click','.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-header .qb-administrator-nav li',function(){
				adminSetting.padingTionPage="";
				// get hash
				var nameHash =  $(this).attr("lang");
				//set global hash
				adminSetting.hashUrlData = nameHash;
				// get function will call when set value
				var nameFunct =  "load"+$(this).attr("funct");
				// get limit
				var limit =  $(this).attr("size-lt");
				localStorage.setItem("function-limit",parseInt(limit));
				//set session offset
				localStorage.setItem("function-offset",0);
				console.log("DATA SIZEZLT : "+limit);
				$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page').html("");
				// call load page
				adminSetting.pushStateUrl(0,nameHash,0);
				adminSetting.loadPageRequire(nameHash,nameFunct,limit,0);
				adminSetting.limit=limit;
				console.log("LIMIT : "+adminSetting.limit);
			});

			/*
			 * event pagintion page
			 */
			$(document).on('click','.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .pagination .page-padingtion',function(){
				var indexOf = $(this).position();
				console.log("index : "+indexOf.left);
				//number page
				var numberPageCover  = $(this).children().attr("pageurl");
				//get hash
				var hashUrl =  $(this).children().attr("href");
				
				var offset = $(this).attr("offset");
				//save offset if reload page
				localStorage.setItem("function-offset",offset)
				//get limit
				var limit = $(this).attr("limit");
				//get name function will call
				var nameFunct = $(this).parent().attr("funcCall");
				console.log("OFFSET : "+offset+" --- LIMIT : "+limit);
				//call function
				
				/*
				 * get posotion 
				 */
				var posit = $(this).parent().position();
				console.log("POSITION ::: "+posit.left + " : "+posit.right);
				window.adminSetting[nameFunct](limit,offset);
				adminSetting.pushStateUrl(numberPageCover,hashUrl,offset);
			});
			
			$(document).on('click','#getWIDTHSSS',function(){
				var tts= $('.pagination>li>a').height();
				console.log(tts);
				console.log("sss ::: "+document.getElementsByClassName(".pagina-page-cover-full-page").offsetWidth);
			});
			/*
			 * event next page pagition
			 * 
			 */
			$(document).on('mouseover','.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .data-padingtion .item-scroll-page',function(){
				console.log("SAFE LOAD  ");
				var type=$(this).attr("typeMove");
				adminSetting.typePagintion=type;
//				console.log(type + "value");
				var element=$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .pagina-page-cover-full-page');
		    	adminSetting.timeOutAnimation(element);
			});
			$(document).on('mouseout','.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .data-padingtion .item-scroll-page',function(){
//				clearTimeout(adminSetting.timeOutPagintion);
				console.log("Stop !");
				$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .pagina-page-cover-full-page').stop();
			});
			/*
			 * header page pagintion
			 */
			$(document).on('click','.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .data-padingtion .header-page-pagintion',function(){
				console.log("SAFE LOAD  ");
				var type=$(this).attr("type");
				adminSetting.typePagintion=type;
				var element=$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .pagina-page-cover-full-page');
		    	adminSetting.timeOutAnimation(element);
			});
		
			
		},
		/**
		* set timeout
		**/
		timeOutAnimation : function(element){
			var totalPage = adminSetting.getTotalWidthPagintion();
			console.log("Total  Page : "+totalPage);
			if(adminSetting.typePagintion=="left"){
				$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .pagina-page-cover-full-page').animate({left:'-'+totalPage},10000);
			}else if(adminSetting.typePagintion=="right"){
				$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .pagina-page-cover-full-page').animate({left:'0'},10000);
			}else if(adminSetting.typePagintion=="headl"){
				console.log("SSS LÈTF");
				$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .pagina-page-cover-full-page').css({left:'-'+totalPage+"px"},1000);
			}else if(adminSetting.typePagintion=="headr"){
				$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .pagina-page-cover-full-page').css({left:'0'},1000);
			}
//			adminSetting.timeOutPagintion = setTimeout(adminSetting.timeOutAnimation,8);
		},
		/*
		 * change hash on url load page require
		 */
		loadPageRequire : function(urlHash,functionCall,limit,offset){
			console.log("load : "+functionCall+" : "+urlHash+" : "+limit);
			adminSetting.limit= limit;
			location.hash = urlHash;
			// set session page selected
			localStorage.setItem("session-administrator",urlHash);
			localStorage.setItem("function-call",functionCall);
			localStorage.setItem("hashNameUrl-hash",urlHash);
			var nameSelect = $('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .'+urlHash).attr("type");
			// set name for title
			$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-header-title .qb-administrator-nav-title').html(nameSelect);
			// set many sub div hidden
			$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .qb-content-administrator-child').css("display","none");
			// show main div
			$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content .'+urlHash).css("display","block");
			// set hash url and style
			$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-scrolls .qb-administrator-content').attr("type-width",urlHash);
			adminSetting.activeFunctionStyle(urlHash);
			window.adminSetting[functionCall](limit,offset);
		},
		/*
		 * load CMS
		 */
		loadCMS : function(limit,offset){
			administratorCMS.loadCMS(limit,offset);
		},
		/*
		 * load account management
		 */
		loadAccount :  function(limit,offset){
			administratorAccount.loadAccount(limit,offset);
		},
		/*
		 * Web Analyst
		 */
		loadAnalyst : function(limit,offset){
			administratorAnalyst.loadAnalyst(limit,offset);
		}
		,
		/*
		 * load list Domain by tunt date 28/03/2016
		 */
		loadListDomain : function(limit, offset){
			adminDomainManagement.loadListDomain(limit, offset);
		}
		,
		/*
		 * load background list
		 */
		loadBonevoBackground : function(){
			adminBackground.loadBonevoBackground();
		}
		,
		/*
		 * bonevo image
		 */	
		loadBonevoImage : function(){
			administratorBonevoImage.loadBonevoImage();
		},
		/*
		 * bonevo connection 
		 */	
		loadConections : function(){
			administratorConnection.loadConections();
		},
		/*
		 * bonevo video
		 */
		loadBonevoVideo : function(){
			administratorVideo.loadBonevoVideo();
		},
		/*
		 * bonevo Background repeat
		 */
		bonevoBackgroundRepeat : function(){
			
		},
		/*
		 * bonevo cronjob log
		 */
		loadCronjobLog : function(){
			adminCronjobLog.loadCronjobLog();
		},
		/*
		 * load topic
		 */
		loadAdminstratorTopic : function(){
			administratorTopic.loadAdminstratorTopic();
		},
		/*
		 * set style active function
		 */activeFunctionStyle : function(nameNeedActive){
			$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-header .qb-administrator-nav li').each(function (){
				$(this).removeClass("administrator-content-active");
				if($(this).attr("lang")==nameNeedActive)
					$(this).addClass("administrator-content-active");
			});
		},
		/*
		 * load image error
		 * 
		 */
		errorImage : function(idfor){
		/*	var linkImgError = util.contextPath()+"/html/images/no-image.gif";
			element.attr('src',linkImgError);*/
		}
		,
		/*
		 * preview page
		 */
		previewPageTemplate : function(id) {
			var url = $('#contextPath').val() + "/e"+id+"/template-preview";
			qbRedirect(url, true);
		}
		,
		/*
		 * number page padingtion 
		**/
		getPadingtionPage : function(size,limit){
			console.log("ksks : "+size+" : ::: "+limit);
			size= parseInt(size);
			limit= parseInt(limit);
			var totalPage = (parseInt(size/limit) + ((size%limit)==0 ? 0 : 1 ));
			return totalPage;
		}
		,/*
		  * generic padingtion page
		 */
		genericPadingtion: function(page,limit){
			var nameFunct = localStorage.getItem("function-call");
			page=parseInt(page);
			console.log("genericPadingtion : "+page+" : "+limit+" : NameFunct = "+nameFunct);
			var padingtionPage = '<div class="data-first"><span class="page-padingtion-first">';
				padingtionPage +='	<div class="col-sm-2 item-page-pg first-funct">';
				if(page>=11){
				padingtionPage +='		<ul class="pagination">';
				padingtionPage +='			<li class="header-page-pagintion" type="headr"><a class="fa fa-angle-double-left"></a></li>';
				padingtionPage +='			<li class="prev-page-pagintion item-scroll-page" typeMove="right"><a style="border-radius:0;border-right: 0;" class="fa fa-angle-left"></a></li>';
				padingtionPage +='		</ul>';
				}
				padingtionPage +='	</div>';
				padingtionPage +='	<div class="cover-number-page col-sm-8 item-page-pg">';
				padingtionPage +='		<ul class="pagination pagina-page-cover-full-page" funcCall='+nameFunct+'>';
			//add pagintion first page
			
			var tempOffset=0;
			if(page==1)
				return "";
			 var pst = 0;
			for (var i = 0; i < page; i++) {
				 ((i==6 && page>11) ? pst=39 : ((i>6 && i<=page-6)? pst+=(39) : pst));
				 padingtionPage += '<li class="page-padingtion" posit='+pst+' offset='+tempOffset+' limit='+limit+'><a style="'+((i==0)? "border-radius:0;" : "" )+'" href="#'+adminSetting.hashUrlData+'" pageUrl="'+(i+1)+'"><span class="data-page">'+(i+1)+'</span></a></li>';
				 tempOffset+=parseInt(limit);
			}
			padingtionPage += '</ul></div><div class="col-sm-2 item-page-pg last-funct">';
			if(page>=11){
			padingtionPage += '			<ul class="pagination next-pagintion-data">';
			padingtionPage += '				<li class="next-page-pagintion item-scroll-page" typeMove="left"><a style="border-radius:0;" class="fa fa-angle-right"></a></li>';
			padingtionPage += '				<li class="header-page-pagintion" type="headl"><a class="fa fa-angle-double-right"></a></li>';
			/*padingtionPage += '				<li class="header-page-pagintion page-next" type="headr"><a style="padding:0"><input class="form-control" style="border-radius:0;border:none;height:100%" type="text" ></a></li>';*/
/*			padingtionPage += '				<li class="header-page-pagintion page-next" type="headr"><a style="padding:0">Go</a></li>';*/
			padingtionPage += '			</ul>';
			padingtionPage += '</div></div>';
			}
			localStorage.setItem("pagination-page",padingtionPage);
			return padingtionPage;
		}
		,
		/*
		* find active class Selected
		*/
		findActiveClassPagintion : function(offset){
			//find class and active
			$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .pagination .page-padingtion').each(function( index ) {
				$(this).removeClass("active");
				if($(this).attr("offset")==offset){
					$(this).addClass("active");
					var leftPd =  $(this).attr("posit");
					$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .pagina-page-cover-full-page').css({left:'-'+leftPd+"px"});
				}
			});
		},
		/*
		 * find and set position 
		 */
		findClassActivePosition : function(){
			var totalWidth =  adminSetting.getTotalWidthPagintion();
			
		},
		/*
		 * image error
		 */
		imageErrorUpdate : function(){
		var linkImgError = util.contextPath()+"/html/images/no-image.gif";
		$('img.check-imag-eerror').each(function() {
		    var img  = new Image(),
		        self = this;
		    img.onerror = function(){
		        $(self).prop('src', linkImgError);
		    }
		    img.src = this.src;
		    
		});
		   
		},
		/*
		* get total page pagintion 
		*/
		getTotalWidthPagintion : function(){
			var width = 0;
			//get number page
			var numberPageReal = $('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .pagina-page-cover-full-page .page-padingtion:last-child a .data-page').html();
			//get width cover
			var widthRequest = $('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page .data-padingtion .cover-number-page').outerWidth();
			width = 40 * parseInt(numberPageReal);
			width = width -(parseInt(parseInt(numberPageReal)) + parseInt(widthRequest));
			return width;
		},
		/*
		 * set pagination when reload page
		 */
		paginationReloadPage : function(){
			var paginTion = localStorage.getItem("pagination-page");
//			console.log("PAGINATION : "+paginTion);
			$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page').html(paginTion);
			return paginTion;
		},
		/*
		 *  push pushState
		*/
		pushStateUrl :  function(indexPage,hashUrl,offset){
//			alert(arrUrl+" : "+indexPage+" : "+hashUrl);
			if (typeof (history.pushState) != "undefined") {// local, server: / 
				try{//set page change
					window.location.hash = '';
					var arrUrl = (window.location.href).split("/");
					var resultUrl = arrUrl[0] + "//" + arrUrl[2];
					var obj = { Title: "Page",Url: (($(location).attr('host')=="localhost:8080")? 'http://localhost:8080'+'/BonEvo' : resultUrl) + '/administrator'+((offset==0)? '' : '/p'+indexPage)};
					console.log(obj);
					history.pushState(obj, obj.Title,obj.Url);
				}
				catch(err){
					console.log(err.toString());
				}
		    }else{
		    	alert("UNDEFINE");
		    }
		},/*
		* find offset active when reload
		*/
		offsetActiveReload : function(offset,limit){
			var hostNeed = $(location).attr('host');
			var hrefNeed = ($(location).attr('pathname').split("/"));
			var numberPage;
			if(hostNeed=="localhost:8080"){
				try{
				numberPage = parseInt(hrefNeed[3].substring(1,hrefNeed[3].length));
				}catch(e){
				numberPage=0;
				}
			}
			if(numberPage>0)
				return (numberPage*parseInt(limit))-limit;
			else
				return 0;
		}
		
	}
}());