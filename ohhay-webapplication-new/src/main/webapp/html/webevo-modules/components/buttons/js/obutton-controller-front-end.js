/**
 * @author: ThienND date created: 3/11/2015
 */
(function() {
	obutton = {
		COMP_MIN_H : 2,
		COMP_MIN_W : 2,
		init : function() {
			this.eventListener();
		},
		eventListener : function() {
			console.log("init dt");
			$(document).on('mouseover', '.qb-component-button .qb-component', function() {
				var layerBG = $(this).find('.layer-background');
				var buttonID = $(this).parents('.grid-stack-item-content.qb-component-button.obutton').attr('qb-component-id');
				var data = componentModelManager.getDataFromID(buttonID);
				if (data['obuttonBGHover']) {
					if (!$.isEmptyObject(data['obuttonBGHover']['onmouseover'])) {
						layerBG.attr('style','');
						layerBG.css(data['obuttonBGHover']['onmouseover']);
					}
				}
			});
			$(document).on('mouseout', '.qb-component-button .qb-component', function() {
				var layerBG = $(this).find('.layer-background');
				var buttonID = $(this).parents('.grid-stack-item-content.qb-component-button.obutton').attr('qb-component-id');
				var data = componentModelManager.getDataFromID(buttonID);
				console.log(data);
				if (data['obuttonBGHover']) {
					if (!$.isEmptyObject(data['obuttonBGHover']['onmouseout'])) {
						layerBG.attr('style','');
						layerBG.css(data['obuttonBGHover']['onmouseout']);
					}
				}
			});
		}
	}
}());