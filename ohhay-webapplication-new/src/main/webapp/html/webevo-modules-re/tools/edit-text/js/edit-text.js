/**
 * @author ThoaiVt
 * 01/02/2016
 */
(function(){
	editText = {
		/*
		* init
		*/
		init : function(){
			var fontstyles = ["Open Sans","Roboto","arial","sans-serif","HelveticaNeue","Avenir Next","Lato","Ubuntu","Akronim","Itim","Alegreya Sans","Kanit","Fira Sans","Crimson Text","Roboto Slab","Aachen","MyriadPro"];
			for(var n = 0; n < fontstyles.length; n++)
			{
				$('#qb-dlg-edit-text #qb-edit-text-option .form-group .font-edit-text .dropdown-menu').append('<li><a style="font-family: '+fontstyles[n]+'">'+fontstyles[n]+'</a></li>');
				$('#qb-dlg-edit-text #qb-edit-text-option .form-group .font-edit-text .dropdown-menu').getNiceScroll().remove();
				$('#qb-dlg-edit-text #qb-edit-text-option .form-group .font-edit-text .dropdown-menu').niceScroll();
			}
			var fontweights = [["Light","300"],["Normal","400"],["Medium","500"],["Bold","bold"]];
			for(var n = 0; n < fontweights.length; n++)
			{
				$('#qb-dlg-edit-text #qb-edit-text-option .form-group .font-type-edit-text .dropdown-menu').append('<li><a data-val="'+fontweights[n][1]+'" >'+fontweights[n][0]+'</a></li>');
			}
			editText.eventListener();
		}
		/*
		 * open
		 */
		,open : function(option){
			if(!editText.inited){
				editText.init();
				$('#qb-dlg-edit-text').webToolDialog(450);
				editText.inited = true;
			}
			editText.callBack = option.callBack;
			$('#qb-dlg-edit-text').dialog("open");
		}
		,
		/*
		 * close
		 */
		close : function(){
			$('#qb-dlg-edit-text').dialog("close");
		}
		,
		/*
		 * event
		 */
		 eventListener : function(){
			//font color
			$(document).on('click','#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-content .color-edit-text',function(){
				colorPicker.open({
					gradient: false,
					callBack : function(result) {
						console.log(result);
						editText.callBack(
								{
									'key':'color',
								    'value':result.data
							    }
					    );
						$('#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-content .color-edit-text').css("background",result.data);					
				}});
			});
			//font family
			$(document).on('click','#qb-dlg-edit-text #qb-edit-text-option .form-group .font-edit-text .dropdown-menu li',function(){
				var data = $(this).children().css("font-family");
				$(this).parent().parent().find(".dropdown-toggle .dd-tt").html($(this).children().html());
				editText.callBack(
						{
							'key':'font-family',
						    'value': data
					    }
			    );
			});
			//font size
			$(document).on('change keydown keyup','#qb-dlg-edit-text #qb-edit-text-option .form-group .font-size-edit-text input[type="number"]',function(){
				var data=$(this).val();
				editText.callBack(
						{
							'key':'font-size',
						    'value': data+"px"
					    }
			    );
			});
			//font weight
			$(document).on('click','#qb-dlg-edit-text #qb-edit-text-option .form-group .font-type-edit-text .dropdown-menu li',function(){
				var data=$(this).children().attr("data-val");
				console.log($(this).children().html()+" ;;; ");
				$(this).parent().parent().find(".dropdown-toggle .dd-tt").html($(this).children().html());
				editText.callBack(
						{
							'key':'font-weight',
						    'value': data
					    }
			    );
			});
			//text align
			$(document).on('click','#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-align-content .edit-text-align',function(){
				var data = $(this).attr("data-type");
				editText.callBack(
						{
							'key':'text-align',
						    'value': data
					    }
			    );
			});
			//text distance ,margin left and right
			$(document).on('change keydown keyup','#qb-edit-text-option .form-group .form-group-content .edit-text-item .distance-edit-text input[type="number"]',function(){
				var data=$(this).val();
				editText.callBack(
						{
							'key':'margin',
						    'value': "0 "+data+"px "+0+" "+data+"px"
					    }
			    );
			});
			//text decoration
			$(document).on('click','#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-decoration-content .edit-text-decoration',function(){
				var data=$(this).attr("data-type");
				editText.callBack(
						{
							'key':'text-decoration',
						    'value': data
					    }
			    );
			});
			//transform
			$(document).on('click','#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-transform-content .edit-text-transform',function(){
				var data=$(this).attr("data-type");
				editText.callBack(
						{
							'key':'text-transform',
						    'value': data
					    }
			    );
			});
		}
	}
}());