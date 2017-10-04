/**
 * ThoaiVt - 27/12/2016
 */
(function() {
	paginaMain = {
		/*
		 * generic padingtion page
		 */
		leftPm : 0,
		arrayThread : [],
		genericPadingtion : function(el, callback, page, limit, enableHash) {
			 console.log("page : "+page);
			paginaMain.elPm = el;
			paginaMain.callbackPm = callback;
			page = parseInt(page);
			// var nameThred = paginaMain.getThreadFromEl(el[0].className);
			// console.log(nameThred);
			var padingtionPage = '<div class="pagination-main-page col-sm-12" enablehash='+enableHash+' limit=' + limit + ' page=' + page + '><span class="page-padingtion-first">';
			padingtionPage += '	<div class="col-sm-2 item-page-pg first-funct">';
			if (page >= limit) {
				padingtionPage += '		<ul class="pagination">';
				padingtionPage += '			<li class="header-page-pagintion goto-begin" type="headr"><a class="fa fa-angle-double-left"></a></li>';
				padingtionPage += '			<li class="prev-page-pagintion item-scroll-page" typeMove="right"><a style="border-radius:0;" class="fa fa-angle-left"></a></li>';
				padingtionPage += '		</ul>';
			}
			padingtionPage += '	</div>';
			padingtionPage += '	<div class="cover-number-page col-sm-8 item-page-pg">';
			padingtionPage += '		<ul class="pagination pagination-full-page">';
			// add pagintion first page
			var tempOffset = 0;
			if (page == 1) {
				el.html("");
				return "";
			}
			var pst = 0;
			for (var i = 0; i < page; i++) {
				// ((i == limit && page > limit) ? pst = 39 : ((i > 6 && i <=
				// page - 6) ? pst += (39) : pst));
				padingtionPage += '<li class="page-padingtion ' + ((i == 0) ? "active" : "") + '" offset=' + tempOffset + ' limit=' + limit + '><a style="' + ((i == 0) ? "border-radius:0;" : '"') + ' '+((enableHash==true ? ' href="#page=' + (i + 1) : '')) + '" pageUrl="' + (i + 1) + '"><span class="data-page">' + (i + 1) + '</span></a></li>';
				tempOffset += parseInt(limit);
			}
			padingtionPage += '</ul></div><div class="col-sm-2 item-page-pg last-funct">';
			if (page >= limit) {
				padingtionPage += '			<ul class="pagination next-pagintion-data">';
				padingtionPage += '				<li class="next-page-pagintion item-scroll-page" typeMove="left"><a style="border-radius:0;" class="fa fa-angle-right"></a></li>';
				padingtionPage += '				<li class="header-page-pagintion goto-end" type="headl"><a class="fa fa-angle-double-right"></a></li>';
				padingtionPage += '			</ul>';
				padingtionPage += '</div></div>';
			}
//			console.log("template : " + padingtionPage);
			el.html(padingtionPage);
			paginaMain.eventListener();
			// paginaMain.autoScrollPagination(el.find('.pagination-main-page
			// .page-padingtion:nth-child(' + index + ')'));
		},
		/*
		 * get thread for auto move
		 */
		getThreadFromEl : function(data) {
			var result = data.split(" ").toString();
			return result.replace(/#|_|,|-/g, '_');
		},
		getRealWidthFromIndex : function() {
			$('.pagination-main-page').each(function(index) {
				var widthPG = 0;
				var left = parseInt($(this).attr("limit"));
				$(this).find(".page-padingtion").each(function(index) {
					if (index < left)
						widthPG += $(this).width();
				});
				// console.log(widthPG);
				$(this).find('.page-padingtion-first .cover-number-page').css('width', widthPG);
				$(this).addClass("showpagination");
			});
		},

		/*
		 * eventlistener
		 */
		eventListener : function() {
			setTimeout(function() {
				paginaMain.getRealWidthFromIndex();
			}, 400);
			// go end page
			$(document).on('click', '.pagination-main-page .goto-end', function() {
				$(this).closest('.page-padingtion-first').find('.cover-number-page').animate({
					scrollLeft : 1000
				}, 400);
			});
			// go begin page
			$(document).on('click', '.pagination-main-page .goto-begin', function() {
				$(this).closest('.page-padingtion-first').find('.cover-number-page').animate({
					scrollLeft : 0
				}, 400);
			});

			// next one page
			$(document).on('click', '.next-page-pagintion', function() {
				$(this).closest('.page-padingtion-first').find('.cover-number-page').animate({
					scrollLeft : $('.cover-number-page').scrollLeft() + 150
				}, 400);
			});

			// prev one page
			$(document).on('click', '.prev-page-pagintion', function() {
				$(this).closest('.page-padingtion-first').find('.cover-number-page').animate({
					scrollLeft : $('.cover-number-page').scrollLeft() - 150
				}, 400);
			});

			// hover auto scroll
			$(".next-page-pagintion").hover(function() {
				// var nameIdTh =
				// $(this).closest('.pagination-main-page').attr("namthr");
				// // get thread
				// var threadRs = paginaMain.getArrayThread(nameIdTh,
				// paginaMain.arrayThread);
				// console.log(threadRs);
				paginaMain.setAutoMove($(this), paginaMain.autoMoveNext);
			}, function() {
				try {
					// var nameIdTh =
					// $(this).closest('.pagination-main-page').attr("namthr");
					// // get thread
					// var threadRs = paginaMain.getArrayThread(nameIdTh,
					// paginaMain.arrayThread);
					clearInterval(paginaMain.autoMoveNext);
				} catch (e) {
					// console.log("not start");
				}
			});
			// hover auto scroll
			$(".prev-page-pagintion").hover(function() {
				// var nameIdTh =
				// $(this).closest('.pagination-main-page').attr("namthr");
				// // get thread
				// var threadRs = paginaMain.getArrayThread(nameIdTh,
				// paginaMain.arrayThread);
				paginaMain.setAutoMovePrev($(this));
			}, function() {
				try {
					// var nameIdTh =
					// $(this).closest('.pagination-main-page').attr("namthr");
					// // get thread
					// var threadRs = paginaMain.getArrayThread(nameIdTh,
					// paginaMain.arrayThread);
					clearInterval(paginaMain.autoMovePrev);
				} catch (e) {
					// console.log("not start");
				}
			});
			// active class
			$(document).on('click', ".pagination-full-page .page-padingtion", function() {
				try {

					$(this).closest('.pagination-full-page').find('.page-padingtion').removeClass("active");
					$(this).addClass("active");
					// get position on scroll of this
					var offset = $(this).attr("offset");
					// find limit page
					var limit = $(this).attr("limit");
					// find number page
					var numberPage = parseInt($(this).find("a").attr('pageurl'));
					// get limit pagination general
					var limitEach = $(this).closest(".pagination-main-page").attr("limit");
					// mesurement element firstest to element limit
					var widthRl = paginaMain.mesurementeElement(0, limitEach);
					// get offset element this
					var left = $(this).offset().left;
					// get offset element firstest
					var left0 = paginaMain.getOffsetLeftPagination(1);
					$(this).closest('.pagination-full-page').parent('.cover-number-page').animate({
						scrollLeft : left - left0 - (widthRl / 2)
					}, 400);
					// console.log(offset + " : " + limit);
					paginaMain.callbackPm({
						offset : offset,
						limit : limit
					});
				} catch (e) {
					// TODO: handle exception
				}

			});
		},
		setAutoMove : function(thisDt) {
			paginaMain.autoMoveNext = setInterval(function() {
				thisDt.closest('.page-padingtion-first').find('.cover-number-page').animate({
					scrollLeft : thisDt.closest('.page-padingtion-first').find('.cover-number-page').scrollLeft() + 1
				}, 0);
			}, 20);
		},
		setAutoMovePrev : function(thisDt) {
			paginaMain.autoMovePrev = setInterval(function() {
				thisDt.closest('.page-padingtion-first').find('.cover-number-page').animate({
					scrollLeft : thisDt.closest('.page-padingtion-first').find('.cover-number-page').scrollLeft() - 1
				}, 0);
			}, 20);
		},
		getArrayThread : function(value, arrayThread) {
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
		},
		mesurementeElement : function(fromEl, toEl) {
			var widthPG = 0;
			$(paginaMain.elPm).find('.pagination-main-page .page-padingtion').each(function(index) {
				if (parseInt(fromEl) <= index - 1)
					widthPG += parseInt($(this).width());
//				console.log("to " + index + " - from " + fromEl + " : " + widthPG);
				if (parseInt(toEl) == index + 1)
					return false;
			});
			return widthPG;
		},
		getOffsetLeftPagination : function(index) {
			return $(paginaMain.elPm).find('.pagination-main-page .page-padingtion:nth-child(' + index + ')').offset().left;
		},
		autoScrollPagination : function(el) {
			try {
				// get limit pagination general
				var limitEach = el.closest(".pagination-main-page").attr("limit");
				// mesurement element firstest to element limit
				var widthRl = paginaMain.mesurementeElement(0, limitEach);
				// get offset element this
				var left = el.offset().left;
				// get offset element firstest
				var left0 = paginaMain.getOffsetLeftPagination(1);
				el.closest('.pagination-full-page').parent('.cover-number-page').animate({
					scrollLeft : left - left0 - (widthRl / 2)
				}, 400);
			} catch (e) {
				// TODO: handle exception
			}

		}
	}
}());