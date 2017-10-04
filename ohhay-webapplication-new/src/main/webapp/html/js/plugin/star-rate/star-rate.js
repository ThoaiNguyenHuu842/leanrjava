/**
 * @author ThoaiVt - 25/11/2016
 */

(function() {
	starRate = {

		initedClick : 0,
		indexStart : 0,
		haftIndex : 0,
		rateData : function(el, callback) {

			el.each(function() {
				var temp = '';
				var numberRate = parseInt($(this).attr("currentrate"));
				console.log(numberRate);
				for (var i = 0; i < 5; i++)
					if ((numberRate) > i)
						temp += '<i class="fa fa-star" vthover="' + i + '" aria-hidden="true"></i>';
					else
						temp += '<i class="fa fa-star-o" vthover="' + i + '" aria-hidden="true"></i>';
				$(this).html(temp);
			});

			if (!starRate.inited) {
				starRate.eventData();
				starRate.inited = true;
			}
			starRate.tpCallback = callback;
			// callback(temp);
		},
		setStartRating : function(real) {
			var temp = '';
			var ind = parseFloat(real);
			if (ind == 0) {
				for (var i = 0; i < 5; i++)
					temp += '<i class="fa fa-star-o" vthover="' + i + '" aria-hidden="true"></i>';
				return temp;
			}

			var checkEq = (Math.round(parseFloat(real) * 2) / 2.0);
			console.log(checkEq);
			for (var i = 0; i < 5; i++) {
				if (ind != parseInt(real) && i > (checkEq - 1) && i == parseInt(real))
					temp += '<i class="fa fa-star-half-o" vthover="' + i + '" aria-hidden="true"></i>';
				else
					temp += '<i class="fa ' + (i < parseInt(real) ? "fa-star" : "fa-star-o") + '" vthover="' + i + '" aria-hidden="true"></i>';
			}
			return temp;
		},
		eventData : function() {
			$('.action-rating i').hover(function(e) {
				starRate.currentHover = $(this);
				var data = $(this).attr('vthover');
				starRate.setStarHover(-1);
				starRate.setStarHover(data);
				starRate.tempS = data;
			}, function() {

			});

			$(".action-rating").mousemove(function(e) {
				starRate.currentHover = $(this);
				starRate.setStarHover(starRate.tempS);
			});
			// mouse leave
			$(".action-rating").mouseleave(function() {
				console.log("Leav : ");
				starRate.currentHover = $(this);
				starRate.setStarHover(-1);
				var numberRate = parseInt($(this).attr("currentrate"));
				starRate.setStarHover(numberRate - 1);
			});
			// accept rate
			$('.action-rating i').click(function() {
				starRate.currentHover = $(this);
				var data = $(this).attr('vthover');
				// set star
				starRate.setStarHover(data);
				starRate.indexStar = parseInt(data);
				// update star
				$(this).parent().attr("currentrate", (parseInt(data) + 1));
				// callback
				starRate.tpCallback({
					star : parseInt(data) + 1,
					el : starRate.currentHover
				});
			});
		},
		setStarHover : function(vthover) {
			if (parseInt(vthover) != -1) {
				starRate.currentHover.children("i").each(function(index) {
					var ind = parseInt(vthover);
					if (index <= ind)
						$(this).removeClass("fa-star-o").addClass("fa-star");
					else
						$(this).removeClass("fa-star fa-star-half-o").addClass("fa-star-o");
				});
			} else
				starRate.currentHover.children('i').removeClass("fa-star fa-star-half-o").addClass("fa-star-o");

		},
		checkIsInt : function(number) {
			if (parseInt(number) === number)
				return true;
			return false;
		}
	}
}());