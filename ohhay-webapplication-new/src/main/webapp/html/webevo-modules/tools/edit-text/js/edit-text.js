/**
 * @author ThoaiVt 01/02/2016
 */
(function() {
	editText = {
		self : null,
		/*
		 * init
		 */
		init : function() {
			var fontstyles = [ "Open Sans", "Roboto", "arial", "sans-serif", "HelveticaNeue", "Avenir Next", "Lato", "Ubuntu", "Akronim", "Itim", "Alegreya Sans", "Kanit", "Fira Sans", "Crimson Text", "Roboto Slab", "Aachen", "MyriadPro" ];
			for (var n = 0; n < fontstyles.length; n++) {
				$('#qb-dlg-edit-text #qb-edit-text-option .form-group .font-edit-text .dropdown-menu').append('<li><a style="font-family: ' + fontstyles[n] + '">' + fontstyles[n] + '</a></li>');
				// $('#qb-dlg-edit-text #qb-edit-text-option .form-group
				// .font-edit-text
				// .dropdown-menu').getNiceScroll().remove();
				$('#qb-dlg-edit-text #qb-edit-text-option .form-group .font-edit-text .dropdown-menu').niceScroll();
			}
			var fontweights = [ [ "Light", "300" ], [ "Normal", "400" ], [ "Medium", "500" ], [ "Bold", "bold" ] ];
			var fontWgSelf = editText.self.css("font-weight");
			$('#qb-edit-text-option .form-group .font-type-edit-text .dropdown-toggle .dd-tt').html('Normal');
			for (var n = 0; n < fontweights.length; n++) {
				$('#qb-dlg-edit-text #qb-edit-text-option .form-group .font-type-edit-text .dropdown-menu').append('<li><a data-val="' + fontweights[n][1] + '" >' + fontweights[n][0] + '</a></li>');
				if (fontWgSelf == fontweights[n][1]) {
					$('#qb-edit-text-option .form-group .font-type-edit-text .dropdown-toggle .dd-tt').html(fontweights[n][0]);
				}
			}
			editText.eventListener();

		}
		/*
		 * open
		 */
		,
		open : function(option, self) {
			editText.self = self;
			if (!editText.inited) {
				editText.init();
				$('#qb-dlg-edit-text').webToolDialog(450);
				editText.inited = true;
			}
			editText.setStyleActive();
			editText.callBack = option.callBack;
			editText.rowTextArea = option.rowTextArea;
			// set row size
			if (editText.rowTextArea == true) {
				$('#qb-dlg-edit-text').dialog({width:'250px'});
				$('#qb-dlg-edit-text').closest('.ui-dialog').find('.ui-dialog-titlebar .ui-dialog-title').html("&#xf040;&nbsp;&nbsp;Number Row");
				editText.choiceRow(true);
				var rowSize = editText.selfRs.attr("rows");
				// console.log("Show Choice Row : "+rowSize);
				document.querySelectorAll("#qb-edit-text-option .form-group .form-group-content .edit-text-item.choice-rowtextarea .item-text-choice-row input")[0].value = rowSize;
			}else{
				editText.choiceRow(false);
				$('#qb-dlg-edit-text').closest('.ui-dialog').find('.ui-dialog-titlebar .ui-dialog-title').html("&#xf031;&nbsp;&nbsp;"+getLocalize("weh_title12"));
				$('#qb-dlg-edit-text').dialog({width:'450px'});
			}
			$('#qb-dlg-edit-text').dialog("open");
		},
		// flag show choice row
		choiceRow : function(isRow) {
			if (isRow == true) {
				$('#qb-edit-text-option .form-group .form-group-content .edit-text-item').css("display","none");
				$('#qb-edit-text-option .form-group .form-group-content .edit-text-item.choice-rowtextarea').css("display","block");
			} else {
				$('#qb-edit-text-option .form-group .form-group-content .edit-text-item').css("display","block");
				$('#qb-edit-text-option .form-group .form-group-content .edit-text-item.choice-rowtextarea').css("display","none");
			}
		},
		/*
		 * set value active dialog
		 */
		setStyleActive : function() {
			try {
				console.log(editText.self);
				console.log("Show font default : " + editText.self.css("font-size"));
				console.log("Font size : " + $('#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-content .font-size-edit-textZ input[type="number"]').val());
				// $('#qb-edit-text-option .form-group .form-group-content
				// .edit-text-item .item-content .font-size-edit-text
				// input[type="number"]').val(110);

				var textAlign = editText.self.css("text-align");
				var textDecoration = editText.self.css("text-decoration");
				var textTransform = editText.self.css("text-transform");
				var fontSize = editText.self.css("font-size").replace('px', '');
				var distance = editText.self.css("margin-left").replace('px', '');

				console.log("textAlign : " + textAlign);
				// set font size
				document.querySelectorAll('#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-content .font-size-edit-textZ input')[0].value = fontSize;
				document.querySelectorAll('#qb-edit-text-option .form-group .form-group-content .edit-text-item .distance-edit-textZ input')[0].value = distance;
				// set color
				$('#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-content .color-edit-text').css('background', editText.self.css("color"));
				// set font-family
				$('#qb-edit-text-option .form-group .font-edit-text .dropdown-toggle .dd-tt').html(editText.self.css("font-family"));
				// set font size
				$('#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-content .font-size-edit-text input[type="number"]').val(editText.self.css("font-size"));
				// set text align
				$("#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-align-content .edit-text-align").removeClass("active");
				$("#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-align-content .edit-text-align[data-type='" + textAlign + "']").addClass("active");
				// set text decoration
				$('#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-decoration-content .edit-text-decoration').removeClass("active");
				$("#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-decoration-content .edit-text-decoration[data-type='" + textDecoration + "']").addClass("active");
				// set item-text-transform
				$("#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-transform-content .edit-text-transform").removeClass("active");
				$("#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-transform-content .edit-text-transform[data-type='" + textTransform + "']").addClass("active");
			} catch (e) {
				console.log("ERROR : " + e);
			}
		},
		/*
		 * close
		 */
		close : function() {
			$('#qb-dlg-edit-text').dialog("close");
		},
		/*
		 * event
		 */
		eventListener : function() {
			//change row 
			$(document).on('click','#qb-edit-text-option .form-group .form-group-content .edit-text-item.choice-rowtextarea .item-text-choice-row input',function(){
				var data = $(this).val();
				editText.callBack({
					'key' : 'rows',
					'value' : data
				});
			});
			// font color
			$(document).on('click', '#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-content .color-edit-text', function() {
				var self = $(this);
				colorPicker.open({
					callBack : function(result) {
						console.log(result);
						editText.callBack({
							'key' : 'color',
							'value' : result.data
						});
						$('#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-content .color-edit-text').css("background", result.data);
					},
					targetBox : self,
					gradient : false
				});
			});
			// font family
			$(document).on('click', '#qb-dlg-edit-text #qb-edit-text-option .form-group .font-edit-text .dropdown-menu li', function() {
				var data = $(this).children().css("font-family");
				$(this).parent().parent().find(".dropdown-toggle .dd-tt").html($(this).children().html());
				editText.callBack({
					'key' : 'font-family',
					'value' : data
				});
			});
			// font size
			$(document).on('change keydown keyup', '#qb-dlg-edit-text #qb-edit-text-option .form-group .font-size-edit-textZ input[type="number"]', function() {
				var data = $(this).val();
				editText.callBack({
					'key' : 'font-size',
					'value' : data + "px"
				});
			});
			// font weight
			$(document).on('click', '#qb-dlg-edit-text #qb-edit-text-option .form-group .font-type-edit-text .dropdown-menu li', function() {
				var data = $(this).children().attr("data-val");
				console.log($(this).children().html() + " ;;; ");
				$(this).parent().parent().find(".dropdown-toggle .dd-tt").html($(this).children().html());
				editText.callBack({
					'key' : 'font-weight',
					'value' : data
				});
			});
			// text align
			$(document).on('click', '#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-align-content .edit-text-align', function() {
				var data = $(this).attr("data-type");
				$('#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-align-content .edit-text-align').removeClass("active");
				$("#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-align-content .edit-text-align[data-type='" + data + "']").addClass("active");

				editText.callBack({
					'key' : 'text-align',
					'value' : data
				});
			});
			// text distance ,margin left and right
			$(document).on('change keydown keyup', '#qb-edit-text-option .form-group .form-group-content .edit-text-item .distance-edit-textZ input[type="number"]', function() {
				var data = $(this).val();
				editText.callBack({
					'key' : 'margin',
					'value' : "0 " + data + "px " + 0 + " " + data + "px"
				});
			});
			// text decoration
			$(document).on('click', '#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-decoration-content .edit-text-decoration', function() {
				var data = $(this).attr("data-type");
				$('#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-decoration-content .edit-text-decoration').removeClass("active");
				$("#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-decoration-content .edit-text-decoration[data-type='" + data + "']").addClass("active");

				editText.callBack({
					'key' : 'text-decoration',
					'value' : data
				});
			});
			// transform
			$(document).on('click', '#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-transform-content .edit-text-transform', function() {
				var data = $(this).attr("data-type");
				$('#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-transform-content .edit-text-transform').removeClass("active");
				$("#qb-edit-text-option .form-group .form-group-content .edit-text-item .item-text-transform-content .edit-text-transform[data-type='" + data + "']").addClass("active");

				editText.callBack({
					'key' : 'text-transform',
					'value' : data
				});
			});
		}
	}
}());