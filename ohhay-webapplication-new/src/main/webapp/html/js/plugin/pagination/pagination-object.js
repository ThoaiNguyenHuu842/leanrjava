/**
 * ThoaiVt - 28/06/2017
 */
function PaginationObj(name) {
	var mainPn = this;
	
	/*
	 * set name generate generator event
	 */
	this.namePagination = name;
	
	/*
	 * clean init template
	 */
	this.clean = function(){
		this.checkInitTemplate=undefined;
	};
	
	/*
	 * active page case load
	 */
	
	/*
	 * annotation variable @el : element will append to @callback : function
	 * event click change page @totalPage : total page by limit @limit : limit
	 * page - default 0 @enableHash : hash after append to url #page-2
	 * shownavigate - pages number shown
	 */
	this.genericPadingtion = function(el, callback, totalPage, limit, enableHash,offsetPage,shownavigate,center) {
		if(this.checkInitTemplate!=true){
// console.log(this.checkInit);
			console.log("page : "+totalPage);
			this.elPm = el;
			this.callbackPm = callback;
			this.limit = limit;
			this.enableHash = enableHash;
			this.totalPage = parseInt(totalPage);
			this.page = (this.totalPage%this.limit==0 && this.totalPage >= this.limit ? parseInt(this.totalPage/this.limit) : parseInt(this.totalPage/this.limit)+1)
			this.shownavigate = shownavigate;
			this.center = center;
			console.log("Page : "+this.page+" - "+this.limit)
			// show navigator if page over-limit (prev)
			var padingtionPage = '<div class="pagination-main-page" nameGenerate='+this.namePagination+' enablehash='+this.enableHash+' limit=' + this.limit + ' page=' + this.page + ' shownavigate="'+(this.shownavigate!==undefined ? this.shownavigate : 0)+'"><span class="page-padingtion-first" center='+(this.center===true ? true : false)+'>';
			padingtionPage += '	<div class="col-sm-2 item-page-pg first-funct">';
			if (this.page >= this.limit || this.page >= this.shownavigate) {
				padingtionPage += '		<ul class="pagination">';
				padingtionPage += '			<li class="header-page-pagintion goto-begin" type="headr"><a class="fa fa-angle-double-left"></a></li>';
				padingtionPage += '			<li class="prev-page-pagintion item-scroll-page" typeMove="right"><a style="border-radius:0;" class="fa fa-angle-left"></a></li>';
				padingtionPage += '		</ul>';
			}
			padingtionPage += '	</div>';
			padingtionPage += '	<div class="cover-number-page col-sm-8 item-page-pg">';
			padingtionPage += '		<ul class="pagination pagination-full-page">';
			// add pagination first page
			var tempOffset = 0;
			if (this.page == 1) {
				el.html("");
				return "";
			}
			var pst = 0,offsetActv = 0;
			if(offsetPage!==undefined){
				offsetActv = offsetPage;
			}
// console.log("Offset active : "+offsetActv);
			// generator page and check active page
			for (var i = 0; i < this.page; i++) {
				console.log("check active : "+(tempOffset === offsetActv));
				padingtionPage += '<li class="page-padingtion ' + ((tempOffset === offsetActv) ? "active" : "") + '" offset=' + tempOffset + ' limit=' + this.limit + '><a style="' + ((i == 0) ? 'border-radius:0;' : '') + '" '+((this.enableHash==true ? ' href="#page=' + (i + 1) : '')) + '" pageUrl="' + (i + 1) + '"><span class="data-page">' + (i + 1) + '</span></a></li>';
				tempOffset += parseInt(this.limit);
			}
			padingtionPage += '</ul></div><div class="col-sm-2 item-page-pg last-funct">';
			// show navigator if page over-limit (next)
			if (this.page >= this.limit || this.page >= this.shownavigate) {
				padingtionPage += '			<ul class="pagination next-pagintion-data">';
				padingtionPage += '				<li class="next-page-pagintion item-scroll-page" typeMove="left"><a style="border-radius:0;" class="fa fa-angle-right"></a></li>';
				padingtionPage += '				<li class="header-page-pagintion goto-end" type="headl"><a class="fa fa-angle-double-right"></a></li>';
				padingtionPage += '			</ul>';
				padingtionPage += '</div></div>';
			}
			// append data to element
			el.html(padingtionPage);
			// init pagination
			var main = this;
			setTimeout(function() {
				main.getRealWidthFromIndex();
			}, 400);
			
			if(this.checkInitEvent!=true){
				this.eventListener();
				this.checkInitEvent=true;
// console.log(this.checkInitEvent);
			}
			this.checkInitTemplate=true;
		}
	};
	
	/*
	 * get thread for auto move
	 */
	this.getThreadFromEl = function(data) {
		var result = data.split(" ").toString();
		return result.replace(/#|_|,|-/g, '_');
	};
	
	/*
	 * 
	 */
	this.getRealWidthFromIndex = function() {
			var main = this;
			$('.pagination-main-page').each(function(index) {
				if(main.checkActionEvent($(this),true)){
					var widthPG = 0;
					var left = parseInt($(this).attr("shownavigate"));
					$(this).find(".page-padingtion").each(function(index) {
						if (index < left)
							widthPG += $(this).width();
					});
					// console.log(widthPG);
					$(this).find('.page-padingtion-first .cover-number-page').css('width', widthPG);
					
					console.log("Width : "+$(this).find('.page-padingtion-first .cover-number-page').width());
					$(this).addClass("showpagination");
				}	
			});
		
	};
	
	this.checkActionEvent = function(el,posnow){
		if(posnow===true){
// console.log(el.attr("nameGenerate"));
			return (el.attr("nameGenerate") === this.namePagination) ? true : false;
		}else{
			var name = el.closest(".pagination-main-page").attr("nameGenerate");
// console.log(name);
			return (name === this.namePagination) ? true : false;
		}
	};
	

	/*
	 * event listener
	 */
	this.eventListener = function() {		
		// go end page
		$(document).on('click', '.pagination-main-page .goto-end', function() {
			if(mainPn.checkActionEvent($(this),false)){
				$(this).closest('.page-padingtion-first').find('.cover-number-page').animate({
					scrollLeft : 1000
				}, 400);
			}
		});
		// go begin page
		$(document).on('click', '.pagination-main-page .goto-begin', function() {
			if(mainPn.checkActionEvent($(this),false)){
				$(this).closest('.page-padingtion-first').find('.cover-number-page').animate({
					scrollLeft : 0
				}, 400);
			}
		});

		// next one page
		$(document).on('click', '.next-page-pagintion', function() {
			if(mainPn.checkActionEvent($(this),false)){
				$(this).closest('.page-padingtion-first').find('.cover-number-page').animate({
					scrollLeft : $('.cover-number-page').scrollLeft() + 150
				}, 400);
			}
		});

		// prev one page
		$(document).on('click', '.prev-page-pagintion', function() {
			if(mainPn.checkActionEvent($(this),false)){
				$(this).closest('.page-padingtion-first').find('.cover-number-page').animate({
					scrollLeft : $('.cover-number-page').scrollLeft() - 150
				}, 400);
			}
		});

		// hover auto scroll
// $(".next-page-pagintion").hover(function() {
// mainPn.setAutoMove($(this), this.autoMoveNext);
// }, function() {
// try {
// clearInterval(this.autoMoveNext);
// } catch (e) {
// // console.log("not start");
// }
// });
// // hover auto scroll
// $(".prev-page-pagintion").hover(function() {
// mainPn.setAutoMovePrev($(this));
// }, function() {
// try {
// clearInterval(this.autoMovePrev);
// } catch (e) {
// // console.log("not start");
// }
// });
		// active class
		$(document).on('click', ".pagination-full-page .page-padingtion", function() {
			try {
				if(mainPn.checkActionEvent($(this),false)){
					console.log("change page");
					$(this).closest('.pagination-full-page').find('.page-padingtion').removeClass("active");
					$(this).addClass("active");
					// get position on scroll of this
					var offset = $(this).attr("offset");
					// find limit page
					var limit = $(this).attr("limit");
					// find number page
					var numberPage = parseInt($(this).find("a").attr('pageurl'));
					// get limit pagination general
					var limitEach = $(this).closest(".pagination-main-page").attr("shownavigate");
					// mesurement element firstest to element limit
					var widthRl = mainPn.mesurementeElement(0, limitEach);
					// get offset element this
					var left = $(this).offset().left;
					// get offset element firstest
					var left0 = mainPn.getOffsetLeftPagination(1);
					$(this).closest('.pagination-full-page').parent('.cover-number-page').animate({
						scrollLeft : left - left0 - (widthRl / 2)
					}, 400);
					console.log(left0);
					console.log(left);
					console.log(widthRl);
					// console.log(offset + " : " + limit);
					mainPn.callbackPm({
						offset : offset,
						limit : limit
					});
					$(this).closest('.pagination-full-page').find(".pagination-content[offset="+offset+"]").addClass("active");
				}
			} catch (e) {
				// TODO: handle exception
				console.log(e);
			}

		});
	}; 
	
	this.setAutoMove = function(thisDt) {
		mainPn.autoMoveNext = setInterval(function() {
			thisDt.closest('.page-padingtion-first').find('.cover-number-page').animate({
				scrollLeft : thisDt.closest('.page-padingtion-first').find('.cover-number-page').scrollLeft() + 1
			}, 0);
		}, 20);
	}; 
	
	this.setAutoMovePrev = function(thisDt) {
		mainPn.autoMovePrev = setInterval(function() {
			thisDt.closest('.page-padingtion-first').find('.cover-number-page').animate({
				scrollLeft : thisDt.closest('.page-padingtion-first').find('.cover-number-page').scrollLeft() - 1
			}, 0);
		}, 20);
	}; 
	
	this.getArrayThread = function(value, arrayThread) {
		// console.log("find : " + value);
		for (var i = 0; i < arrayThread.length; i++) {
			if (arrayThread[i].name == value) {
				// console.log("inited");
				return arrayThread[i];
			}
		}
		// console.log("not found");
		var dataInit = {
			name : value,
			data : value
		};
		arrayThread.push(dataInit);
		for (var i = 0; i < arrayThread.length; i++) {
			if (arrayThread[i].name == value) {
				// console.log("new inited");
				return arrayThread[i];
			}
		}
	};
	
	this.mesurementeElement = function(fromEl, toEl) {
		var widthPG = 0;
		$(this.elPm).find('.pagination-main-page .page-padingtion').each(function(index) {
			if (parseInt(fromEl) <= index - 1)
				widthPG += parseInt($(this).width());
			if (parseInt(toEl) == index + 1)
				return false;
		});
		return widthPG;
	};
	
	this.getOffsetLeftPagination = function(index) {
		return $(this.elPm).find('.pagination-main-page .page-padingtion:nth-child(' + index + ')').offset().left;
	};
	
	this.autoScrollPagination = function(el) {
		try {
			// get limit pagination general
			var limitEach = el.closest(".pagination-main-page").attr("limit");
			// mesurement element firstest to element limit
			var widthRl = mainPn.mesurementeElement(0, limitEach);
			// get offset element this
			var left = el.offset().left;
			// get offset element firstest
			var left0 = mainPn.getOffsetLeftPagination(1);
			el.closest('.pagination-full-page').parent('.cover-number-page').animate({
				scrollLeft : left - left0 - (widthRl / 2)
			}, 400);
		} catch (e) {
			// TODO: handle exception
		}
	}
}