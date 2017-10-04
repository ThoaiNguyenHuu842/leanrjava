/*!
 *
 * jQuery TE 1.4.0 , http://jqueryte.com/
 * Copyright (C) 2013, Fatih Koca (fattih@fattih.com), (http://jqueryte.com/about)

 * jQuery TE is provided under the MIT LICENSE.
 *
*/

(function($){		
	$.fn.jqte = function(options){
  
		// default titles of buttons
		var varsTitle = [
				{title:"Text Format"},
				{title:"Bold"},
				{title:"Italic"},
				{title:"Underline"},
				{title:"Ordered List"},
				{title:"Unordered List"},
				{title:"Subscript"},
				{title:"Superscript"},
				{title:"Outdent"},
				{title:"Indent"},
				{title:"Justify Left"},
				{title:"Justify Center"},
				{title:"Justify Right"},
				{title:"Strike Through"},
				{title:"Remove Link"},
				{title:"Cleaner Style"},
				{title:"Horizontal Rule"},
				{title:"Source"},
				{title:"Undo"},		
				{title:"Redo"}		
			];
		
		/*default text formats*/
		var formats = [["p","Normal"],["h1","Header 1"],["h2","Header 2"],["h3","Header 3"],["h4","Header 4"],["h5","Header 5"],["h6","Header 6"],["pre","Preformatted"]];
		
		
		//font-family
		var fontfamilys = ["Open Sans","Roboto","arial","sans-serif","HelveticaNeue","Avenir Next","Lato","Ubuntu","Akronim","Itim","Alegreya Sans","Kanit","Fira Sans","Crimson Text","Roboto Slab","Aachen","MyriadPro"];
		
		// font-weight
		var fontweights = [["Light","300"],["Normal","400"],["Medium","500"],["Bold","700"]];

		// font icon
		var fonticons = ["&#xf0c7;","&#xf100;","&#xf101;","&#xf102;","&#xf103;","&#xf104;","&#xf105;","&#xf0c9;","&#xf0da;",
		                 "&#xf00c;","&#xf111;","&#xf0d8;","&#xf0d7;","&#xf099;","&#xf0e1;","&#xf060;","&#xf009;","&#xf00a;",
		                 "&#xf023;","&#xf030;","&#xf03d;","&#xf035;","&#xf061;","&#xf0dd;","&#xf0c5;","&#xf108;","&#xf0b0;",
		                 "&#xf09c;","&#xf13e;","&#xf10a;","&#xf234;","&#xf1fd;","&#xf0c1;","&#xf004;","&#xf197;","&#xf007;",
		                 "&#xf005;","&#xf123;","&#xf006;","&#xf260;","&#xf0e7;","&#xf1f9;","&#xf09e;","&#xf067;","&#xf10d;",
						 "&#xf10e;","&#xf0ec;","&#xf02b;","&#xf0a4;","&#xf043;","&#xf0f3;","&#xf02d;","&#xf02e;","&#xf073;",
						 "&#xf083;","&#xf0a3;","&#xf09d;","&#xf1c0;","&#xf219;","&#xf155;","&#xf16b;","&#xf0e0;","&#xf0ac;",
						 "&#xf015;",
		                 "&#xf001;","&#xf055;","&#xf12e;","&#xf029;","&#xf059;","&#xf1b8;","&#xf07a;","&#xf0c8;","&#xf129;",
		                 "&#xf179;","&#xf1ad;","&#xf008;","&#xf07b;","&#xf085;","&#xf10b;","&#xf21c;","&#xf095;","&#xf1bb;",
		                 "&#xf06b;","&#xf075;","&#xf0f4;","&#xf19c;","&#xf13d;","&#xf0c2;","&#xf013;","&#xf06d;","&#xf19d;",
		                 "&#xf025;","&#xf253;","&#xf03e;","&#xf084;","&#xf1cd;","&#xf149;","&#xf148;","&#xf041;","&#xf0e9;",
		                 "&#xf091;","&#xf0e8;","&#xf132;","&#xf233;","&#xf0c6;","&#xf0d0;","&#xf11b;","&#xf085;","&#xf080;",
		                 "&#xf24e;","&#xf069;","&#xf21d;","&#xf1eb;","&#xf044;","&#xf06e;","&#xf014;","&#xf040;","&#xf002;",
		                 "&#xf09a;","&#xf082;","&#xf09a;","&#xf194;","&#xf1ca;","&#xf231;","&#xf0d5;","&#xf27d;","&#xf167;",
		                 "&#xf231;","&#xf17e;","&#xf127;","&#xf18c;","&#xf121;","&#xf019;","&#xf017;","&#xf0d1;","&#xf068;",
		                 "&#xf01e;","&#xf0ad;","&#xf0c5;","&#xf114;","&#xf201;","&#xf1e0;","&#xf0c0;","&#xf01d;"];
		

		//var lineBehinds = ["white","red","yellow","green","blue","purple","gray","pink","brown","orange","black"];
		
		var vars = $.extend({
			// options
			'status'		: true,
			'css' 			: "jqte",
			'title'			: true,
			'titletext'		: varsTitle,
			'button'		: "OK",
			'format'		: true,
			'formats'		: formats,
			'funit'			: "px",
			'b' 			: true,
			'i' 			: true,
			'u' 			: true,
			'ol' 			: true,
			'ul' 			: true,
			'sub'			: true,
			'sup'			: true,
			'outdent'		: true,
			'indent'		: true,
			'left'			: true,
			'center'		: true,
			'right'			: true,
			'strike'		: true,
			'unlink'		: true,
			'remove'		: true,
			'rule'			: true,
			'source'		: false,
			'br'			: true,
			'p'				: true,
			'fonticons'		: fonticons,
			'fontfamilys'	: fontfamilys,
			'undo'			: true,
			'redo'			: true,
			'fontweights'	: fontweights,
			
			// events
			'change'		: "",
			'focus'			: "",
			'blur'			: ""
		}, options);
		
		// browser information is received
		var thisBrowser = navigator.userAgent.toLowerCase();
		
		// if browser is ie and it version is 7 or even older, close title property
		if(/msie [1-7]./.test(thisBrowser))
			vars.title = false;
		
		var buttons = [];
		
		// insertion function for parameters to toolbar
		function addParams(name,command,key,tag,emphasis)
		{
			var thisCssNo  = buttons.length+1;
			return buttons.push({name:name, cls:thisCssNo, command:command, key:key, tag:tag, emphasis:emphasis});
		};
		
		// add parameters for toolbar buttons
		addParams('format','formats','','',false); // text format button  --> no hotkey
		/*addParams('color','colors','','',false);*/ // text color button  --> no hotkey 
		addParams('b','Bold','B',["b","strong"],true); // bold --> ctrl + b
		addParams('i','Italic','I',["i","em"],true); // italic --> ctrl + i
		addParams('u','Underline','U',["u"],true); // underline --> ctrl + u
		addParams('ol','insertorderedlist','¾',["ol"],true); // ordered list --> ctrl + .(dot)
		addParams('ul','insertunorderedlist','¼',["ul"],true); // unordered list --> ctrl + ,(comma)
		addParams('sub','subscript','(',["sub"],true); // sub script --> ctrl + down arrow
		addParams('sup','superscript','&',["sup"],true); // super script --> ctrl + up arrow
		addParams('outdent','outdent','%',["blockquote"],false); // outdent --> ctrl + left arrow
		addParams('indent','indent','\'',["blockquote"],true); // indent --> ctrl + right arrow
		addParams('left','justifyLeft','','',false); // justify Left --> no hotkey
		addParams('center','justifyCenter','','',false); // justify center --> no hotkey
		addParams('right','justifyRight','','',false); // justify right --> no hotkey
		addParams('strike','strikeThrough','K',["strike"],true); // strike through --> ctrl + K
		addParams('unlink','unlink','',["a"],false); // remove link --> ctrl + N 
		addParams('remove','removeformat','.','',false); // remove all styles --> ctrl + delete
		addParams('rule','inserthorizontalrule','H',["hr"],false); // insertion horizontal rule --> ctrl + H
		addParams('source','displaysource','','',false); // feature of displaying source
		addParams('undo','undo','','',true);//undo --> no hotkey
		addParams('redo','redo','','',true);//redo --> no hotkey

		return this.each(function(){
			if(!$(this).data("jqte") || $(this).data("jqte")==null || $(this).data("jqte")=="undefined")
				$(this).data("jqte",true);
			else 
				$(this).data("jqte",false);
			
			// is the status false of the editor
			/*if(!vars.status || !$(this).data("jqte"))
			{
				// if wanting the false status later
				if($(this).closest("."+vars.css).length>0)
				{
					
					// add all attributes of element
					var thisElementAttrs = "";
					
					$($(this)[0].attributes).each(function()
					{
						if(this.nodeName!="style")
							thisElementAttrs = thisElementAttrs+" "+this.nodeName+'="'+this.nodeValue+'"';
					});
				}
				return;
			}*/
			/*thong:*/
			var thisCP = $('.qb-component');
			var evoRange = '';
			
			// element will converted to the jqte editor
			var thisElement = $(this);
			
			// tag name of the element
			var thisElementTag = $(this).prop('tagName').toLowerCase();
			
			// tag name of origin
			$(this).attr("data-origin",thisElementTag);
			
			// start jqte editor to after the element
			$(this).after('<div class="'+vars.css+'"></div>');
			
			// jqte
			var jQTE = $(this).next('.'+vars.css);
			
			// insert toolbar in jqte editor
			jQTE.html('<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true"></div>');
			jQTE.find('.panel-group').append('<div class="panel setattr '+vars.css+"_toolbar"+'" data-tool="toolbar"  role="toolbar" unselectable><div class="panel-heading" role="tab" id="headingOne">'
				+'<h4 class="panel-title"><a role="button" data-toggle="collapse" data-parent="#accordion" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Format</a></h4></div>'
				+'<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne"><div class="panel-body"></div></div></div>');
			jQTE.find('.panel-group').append('<div class="panel setattr '+vars.css+'_fontform extendform" data-tool="fontform" unselectable><div class="panel-heading" role="tab" id="headingTwo">'
				+'<h4 class="panel-title"> <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">Font</a></h4></div>'
	      		+'<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo"><div class="panel-body"></div></div></div>');
			jQTE.find('.panel-group').append('<div class="panel setattr '+vars.css+'_iconform extendform" data-tool="iconform" unselectable><div class="panel-heading" role="tab" id="headingThree">'
					+'<h4 class="panel-title"> <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">Icon</a></h4></div>'
					+'<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree"><div class="panel-body"></div></div></div>');
			jQTE.find('.panel-group').append('<div class="panel setattr '+vars.css+'_spacingform extendform" data-tool="spacingform" unselectable><div class="panel-heading" role="tab" id="headingFour">'
				+'<h4 class="panel-title"><a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">Spacing</a></h4></div>'
				+'<div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour"><div class="panel-body"></div></div></div>');
			jQTE.find('.panel-group').append('<div class="panel setattr '+vars.css+'_shadowform extendform" data-tool="shadowform" unselectable><div class="panel-heading" role="tab" id="headingFive">'
				+'<h4 class="panel-title"><a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">Shadow</a></h4></div>'
			    +'<div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive"><div class="panel-body"></div></div></div>');
			jQTE.find('.panel-group').append('<div class="panel setattr '+vars.css+'_linkform extendform" data-tool="linkform" unselectable><div class="panel-heading" role="tab" id="headingSix">'
			    +'<h4 class="panel-title"><a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" data-target="#collapseSix" aria-expanded="false" aria-controls="collapseSix">Add link</a></h4></div>'
		        +'<div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix"><div class="panel-body"></div></div></div>');
			
			var toolbar			= jQTE.find('.'+vars.css+"_toolbar"); // the toolbar variable
			var fontform		= jQTE.find('.'+vars.css+"_fontform");
			var iconform		= jQTE.find('.'+vars.css+"_iconform");
			var spacingform		= jQTE.find('.'+vars.css+"_spacingform");
			var shadowform		= jQTE.find('.'+vars.css+"_shadowform");
			var linkform		= jQTE.find('.'+vars.css+"_linkform"); // the link-form-area in the toolbar variable
			var emphasize		= vars.css+"_tool_depressed"; // highlight style of the toolbar buttons
			
			// add children for font
			fontform.find('.panel-body').append('<div class="dropdown '+vars.css+'_fontstyle" unselectable></div><div class="dropdown '+vars.css+'_fontweight" unselectable></div><span class="qty-wrap pull-left"><input type="number" class="form-control text-right qty '+vars.css+'_fontsize" value="0" min="0" id="kub-border-video"><a class="qty-1"><span class="iconqb-small-up"></span></a><a class="qty_1"><span class="iconqb-small"></span></a></span><span class="'+vars.css+'_fontcolor" data-val="#000000" style="background-color: #000000;"></span>');
			var fontstyle		= fontform.find("."+vars.css+"_fontstyle");
			var fontweight		= fontform.find("."+vars.css+"_fontweight");
			var fontsize		= fontform.find("."+vars.css+"_fontsize");
			var fontcolor		= fontform.find("."+vars.css+"_fontcolor");
			fontstyle.append('<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="dropdown-title">all</span><span class="caret"></span></button><ul class="dropdown-menu"></ul>');
			fontweight.append('<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="dropdown-title">Normal</span><span class="caret"></span></button><ul class="dropdown-menu"></ul>');
			// add link types to link-type-selector
			fontstyle.data("fontstyle","0");
			for(var n = 0; n < fontfamilys.length; n++)
			{
				fontstyle.find('ul').append('<li><a '+vars.css+'-fontstyle="'+n+'" unselectable style="font-family: '+vars.fontfamilys[n]+'">'+vars.fontfamilys[n]+'</a></li>');
			}
			fontweight.data("fontweight","0");
			for(var n = 0; n < vars.fontweights.length; n++)
			{
				fontweight.find('ul').append('<li><a '+vars.css+'-fontweight="'+n+'" unselectable data-val="'+vars.fontweights[n][1]+'" >'+vars.fontweights[n][0]+'</a></li>');
			}
			
			// add children for icon
			iconform.find('.panel-body').append('<div class="'+vars.css+'_iconlist"></div>');
			var iconlist		= iconform.find("."+vars.css+"_iconlist");
			for(var n = 0; n < fonticons.length; n++)
			{
				iconlist.append('<a '+vars.css+'-iconlist="'+n+'" unselectable><span class="" style="font-family:Fontawesome">'+vars.fonticons[n]+'</span></a>');
			}

			// add children for spacing
			spacingform.find('.panel-body').append('<div><span class="spacing-label">Line height:</span><span class="qty-wrap"><input type="number" class="form-control text-right qty '+vars.css+'_lheight" value="0" min="0" id="kub-border-video"><a class="qty-1"><span class="iconqb-small-up"></span></a><a class="qty_1"><span class="iconqb-small"></span></a></span></div><div><span class="spacing-label">Character spacing:</span><span class="qty-wrap"><input type="number" class="form-control text-right qty '+vars.css+'_space" value="0" min="0" id="kub-border-video"><a class="qty-1"><span class="iconqb-small-up"></span></a><a class="qty_1"><span class="iconqb-small"></span></a></span></div><div><span class="spacing-label">Letter spacing:</span><span class="qty-wrap"><input type="number" class="form-control text-right qty '+vars.css+'_letter" value="0" step="0.1" min="-3" id="kub-border-video"><a class="qty-1"><span class="iconqb-small-up"></span></a><a class="qty_1"><span class="iconqb-small"></span></a></span></div>');
			var lheight		= spacingform.find("."+vars.css+"_lheight");
			var space		= spacingform.find("."+vars.css+"_space");
			var letter		= spacingform.find("."+vars.css+"_letter");
			
			// add children for shadow
			shadowform.find('.panel-body').append('<div style="float:left;width:100%;"><p><img src="'+$("#contextPath").val()+'/html/images/shadow-h.png"/></p><p><img src="'+$("#contextPath").val()+'/html/images/shadow-v.png"/></p><p><img src="'+$("#contextPath").val()+'/html/images/shadow-blur.png"/></p></div>'
					+'<div style="float:left;width:100%;"><span class="qty-wrap shadow-item"><input type="number" class="form-control text-right qty '+vars.css+'_shadowH" value="0" id="kub-border-video"><a class="qty-1"><span class="iconqb-small-up"></span></a><a class="qty_1"><span class="iconqb-small"></span></a></span><span class="qty-wrap shadow-item"><input type="number" class="form-control text-right qty '+vars.css+'_shadowV" value="0" id="kub-border-video"><a class="qty-1"><span class="iconqb-small-up"></span></a><a class="qty_1"><span class="iconqb-small"></span></a></span><span class="qty-wrap shadow-item"><input type="number" class="form-control text-right qty '+vars.css+'_shadowBlur" value="0" min="0" id="kub-border-video"><a class="qty-1"><span class="iconqb-small-up"></span></a><a class="qty_1"><span class="iconqb-small"></span></a></span><span class="'+vars.css+'_shadowColor" data-val="#000000" style="background-color: #000000;"></span></div>');
			var shadowH		= shadowform.find("."+vars.css+"_shadowH"); // the input of insertion link
			var shadowV		= shadowform.find("."+vars.css+"_shadowV"); // the input of insertion link
			var shadowBlur	= shadowform.find("."+vars.css+"_shadowBlur"); // the input of insertion link
			var shadowColor	= shadowform.find("."+vars.css+"_shadowColor"); // the input of insertion link
			
			// add children for link
			linkform.find('.panel-body').append('<label style="width: 100%;color:#333;"><input class="'+vars.css+'_linkUnder" type="checkbox" value="" > Under-line</label><input class="'+vars.css+'_linkinput" type="text" value="" disabled><div class="'+vars.css+'_linkbutton editor-button" unselectable><i class="fa fa-link"></i></div>');
			var linkinput		= linkform.find("."+vars.css+"_linkinput"); // the input of insertion link
			var linkcheck		= linkform.find("."+vars.css+"_linkcheck"); // the input of target _blank
			var linkUnder		= linkform.find("."+vars.css+"_linkUnder"); // the input of underline
			var linkbutton		= linkform.find("."+vars.css+"_linkbutton"); // the button of insertion link
			
			// add to the link-type-selector sub tool parts
			var setdataflag		= vars.css+"-setflag"; // the selected text add to mark as "link will be added"
			
			
			// if the element isn't a textarea, convert this to textarea
			if(thisElementTag!="textarea")
			{
				// add all attributes of element to new textarea (type and value except)
				var thisElementAttrs = "";
				
				$(thisElement[0].attributes).each(function(){
					if(this.nodeName!="type" && this.nodeName!="value")
						thisElementAttrs = thisElementAttrs+" "+this.nodeName+'="'+this.nodeValue+'"';
				});
				
			}

			// insertion the toolbar button
			for(var n = 0; n < buttons.length; n++)
			{
				// if setting of this button is activated (is it true?)
				if(vars[buttons[n].name])
				{
					// if it have a title, add to this button
					var buttonHotkey = buttons[n].key.length>0 ? vars.titletext[n].hotkey!=null && vars.titletext[n].hotkey!="undefined" && vars.titletext[n].hotkey!="" ? ' (Ctrl+'+vars.titletext[n].hotkey+')' : '' : '';
					var buttonTitle = vars.titletext[n].title!=null && vars.titletext[n].title!="undefined" && vars.titletext[n].title!="" ? vars.titletext[n].title+buttonHotkey : '';
					
					// add this button to the toolbar
					toolbar.find('.panel-body').append('<div class="'+vars.css+'_tool '+vars.css+'_tool_'+buttons[n].cls+'" role="button" data-tool="'+n+'" unselectable><a class="'+vars.css+'_tool_icon" unselectable></a></div>');
					
					// add the parameters to this button
					toolbar.find('.panel-body').find('.'+vars.css+'_tool[data-tool='+n+']').data({tag : buttons[n].tag, command : buttons[n].command, emphasis : buttons[n].emphasis, title : buttonTitle});
					
					// format-selector field
					if(buttons[n].name=="format" && $.isArray(vars.formats))
					{
						// selected text format
						var toolLabel = vars.formats[0][1].length>0 && vars.formats[0][1]!="undefined" ? vars.formats[0][1] : "";
						toolbar.find('.'+vars.css+'_tool[data-tool='+n+']').addClass('dropdown');
						toolbar.find("."+vars.css+'_tool_'+buttons[n].cls).find("."+vars.css+"_tool_icon").replaceWith('<button class="btn btn-default dropdown-toggle '+vars.css+'_tool_label" type="button" id="dropdownFont" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" unselectable><span class="tt '+vars.css+'_tool_text" unselectable></span><span class="caret" unselectable></span></button><ul class="dropdown-menu '+vars.css+'_formats" aria-labelledby="dropdownFont" unselectable></ul></div>');
						
						// toolbar.find("."+vars.css+'_tool_'+buttons[n].cls).find('.dropdown-menu')
						// 	.append('');
						
						// add font-sizes to font-size-selector
						for(var f = 0; f < vars.formats.length; f++)
						{
							toolbar.find("."+vars.css+"_formats").append('<li><a '+vars.css+'-formatval="'+ vars.formats[f][0] +'" class="'+vars.css+'_format'+' '+vars.css+'_format_'+f+'" role="menuitem" unselectable>'+ vars.formats[f][1] +'</a></li>');
						}
						
						toolbar.find("."+vars.css+"_formats").data("status",false);
					}
					
					// color-selector field
					/*else if(buttons[n].name=="color" && $.isArray(colors)){
						toolbar.find("."+vars.css+'_tool_'+buttons[n].cls)
							.append('<div class="'+vars.css+'_cpalette" unselectable></div>');
						
						// create color palette to color-selector field
						for(var c = 0; c < colors.length; c++)
						{
							if(colors[c]!=null)
								toolbar.find("."+vars.css+"_cpalette").append('<a '+vars.css+'-styleval="'+ colors[c] +'" class="'+vars.css+'_color'+'" style="background-color: rgb('+ colors[c] +')" role="gridcell" unselectable></a>');
							else
								toolbar.find("."+vars.css+"_cpalette").append('<div class="'+vars.css+"_colorSeperator"+'"></div>');
						}
					}*/
					// font-icon selector field
					/*else if(buttons[n].name=="lineBehind" && $.isArray(vars.lineBehinds))
					{
						toolbar.find("."+vars.css+'_tool_'+buttons[n].cls).find('a').addClass('jqte_tool_lineBehind').html('title-line <span></span>');
						toolbar.find("."+vars.css+'_tool_'+buttons[n].cls)
						.append('<div class="'+vars.css+'_lineBehinds" unselectable></div>');
						
						// add font-sizes to font-size-selector
						for(var f = 0; f < vars.lineBehinds.length; f++)
						{
							toolbar.find("."+vars.css+"_lineBehinds").append('<a '+vars.css+'-styleval="'+ vars.lineBehinds[f] +'" class="'+vars.css+'_lineBehind'+'" role="menuitem" unselectable><span class="line_behind" style="background-color: '+vars.lineBehinds[f]+'">'+vars.lineBehinds[f]+'</span></a>');
						}
					}*/
					else if(buttons[n].name=="undo"){
						toolbar.find("."+vars.css+'_tool_'+buttons[n].cls).find('a').addClass('jqte_tool_undo').html('&#xf0e2;');
					}
					else if(buttons[n].name=="redo"){
						toolbar.find("."+vars.css+'_tool_'+buttons[n].cls).find('a').addClass('jqte_tool_redo').html('&#xf01e;');
					}
				}
			}
			
			// add the prefix of css according to browser
			var prefixCss = "";

			if(/msie/.test(thisBrowser)) // ie
				prefixCss = '-ms-';
			else if(/chrome/.test(thisBrowser) || /safari/.test(thisBrowser) || /yandex/.test(thisBrowser)) // webkit group (safari, chrome, yandex)
				prefixCss = '-webkit-';
			else if(/mozilla/.test(thisBrowser)) // firefox
				prefixCss = '-moz-';
			else if(/opera/.test(thisBrowser)) // opera
				prefixCss = '-o-';
			else if(/konqueror/.test(thisBrowser)) // konqueror
				prefixCss = '-khtml-';
			else 
				prefixCss = '';
				
			// make unselectable to unselectable attribute ones
			jQTE.find("[unselectable]")
				.css(prefixCss+"user-select","none")
				.addClass("unselectable")
				.attr("unselectable","on")
				.on("selectstart mousedown",false);
			
			// each button of the toolbar
			var toolbutton = toolbar.find("."+vars.css+"_tool");
			
			// format menu
			var formatbar = toolbar.find("."+vars.css+"_formats");
			
			// color palette
//			var cpalette = toolbar.find("."+vars.css+"_cpalette");
			
			//font icon filed
			var lineBehindbar = toolbar.find("."+vars.css+"_lineBehinds");
			
			// get the selected text as plain format
			function selectionGet()
			{
				// for webkit, mozilla, opera
				if (window.getSelection)
					return window.getSelection();
				// for ie
				else if (document.selection && document.selection.createRange && document.selection.type != "None")
					return document.selection.createRange();
			}
			
			// the function of changing to the selected text with "execCommand" method
			function selectionSet(addCommand,thirdParam)
			{
				var	range,
					sel = selectionGet();
				var thisSelection = $('.qb-component').find("["+setdataflag+"]");
				selectText(thisSelection);
				// for webkit, mozilla, opera
				if (window.getSelection)
				{
					if (sel.anchorNode && sel.getRangeAt)
						range = sel.getRangeAt(0);
						
					if(range)
					{
						sel.removeAllRanges();
						sel.addRange(range);
					}
					
					if(!thisBrowser.match(/msie/))
						document.execCommand('StyleWithCSS', false, false);
					
					document.execCommand(addCommand, false, thirdParam);
				}
				
				// for ie
				else if (document.selection && document.selection.createRange && document.selection.type != "None")
				{
					range = document.selection.createRange();
					range.execCommand(addCommand, false, thirdParam);
				}
				
				// change styles to around tags
				if(!addCommand=="inserthorizontalrule"){
					affectStyleAround(false,false);
				}
			}
			
			// the function of changing to the selected text with tags and tags's attributes
			function replaceSelection(tTag,tAttr,tVal) {
				var thisSelection = $('.qb-component').find("["+setdataflag+"]");
				selectText(thisSelection);
				
				// for webkit, mozilla, opera			
				if (window.getSelection)
				{
					var selObj = selectionGet(), selRange, newElement, documentFragment;
					
					if (selObj.anchorNode && selObj.getRangeAt)
					{
						selRange = selObj.getRangeAt(0);
						
						// create to new element
						newElement = document.createElement(tTag);
						
						// extract to the selected text
						documentFragment = selRange.extractContents();
						
						// add the contents to the new element
						newElement.appendChild(documentFragment);
						//case create flag
						
						$(newElement).attr(tAttr,tVal);
						selRange.insertNode(newElement);
						
						selObj.removeAllRanges();
						
						// if the attribute is "style", change styles to around tags
						affectStyleAround($(newElement),tVal);
						
					}
				}
				// for ie
				else if (document.selection && document.selection.createRange && document.selection.type != "None")
				{
					var range = document.selection.createRange();
					var selectedText = range.htmlText;
					
					var newText = '<'+tTag+' '+tAttr+'="'+tVal+'">'+selectedText+'</'+tTag+'>';
					
					document.selection.createRange().pasteHTML(newText);
				}
			}
			
			
			// the function of getting to the parent tag
			var getSelectedNode = function() {
				var thisSelection = $('.qb-component').find("["+setdataflag+"]");
				selectText(thisSelection);
				var node,selection;
				if(window.getSelection) {
					selection = getSelection();
					node = selection.anchorNode;
				}
				if(!node && document.selection && document.selection.createRange && document.selection.type != "None")
				{
					selection = document.selection;
					var range = selection.getRangeAt ? selection.getRangeAt(0) : selection.createRange();
					node = range.commonAncestorContainer ? range.commonAncestorContainer :
						   range.parentElement ? range.parentElement() : range.item(0);
				}
				if(node) {
					try{
						return (node.nodeName == "#text" ? $(node.parentNode) : $(node));
					}catch(e){
						return false;
					}
				}
				else 
					return false;
			};
			
			// the function of replacement styles to the around tags (parent and child)
			function affectStyleAround(element,style)
			{
				var selectedTag = getSelectedNode(); // the selected node
				
				selectedTag = selectedTag ? selectedTag : element;
				
				// (for replacement with execCommand) affect to child tags with parent tag's styles
				if(selectedTag && style==false)
				{
					// apply to the selected node with parent tag's styles
					if(selectedTag.parent().is("[style]"))
						selectedTag.attr("style",selectedTag.parent().attr("style"));
						
					// apply to child tags with parent tag's styles
					if(selectedTag.is("[style]"))
						selectedTag.find("*").attr("style",selectedTag.attr("style"));
				}
				// (for replacement with html changing method)
				else if(element && style && element.is("[style]"))
				{
					var styleKey = style.split(";"); // split the styles
					
					styleKey = styleKey[0].split(":"); // get the key of first style feature
					
					// apply to child tags with parent tag's styles
					if(element.is("[style*="+styleKey[0]+"]"))
						element.find("*").css(styleKey[0],styleKey[1]);
						
					// select to the selected node again
					selectText(element);
				}
			}

			// the function of making selected to a element
			function selectText(element)
			{
				if(element)
				{
					var element = element[0];
					
					if (document.body.createTextRange)
					{
						try{
							var range = document.body.createTextRange();
							range.moveToElementText(element);
							range.select();
						}catch (e) {
							var selection = window.getSelection();  
							var range = document.createRange();
							
							if(element != "undefined" && element != null)
							{
								range.selectNodeContents(element);
								selection.removeAllRanges();
								selection.addRange(range);
								
								if($(element).is(":empty"))
								{
									$(element).append("&nbsp;");
									selectText($(element));
								}
							}
						}
					}
					else if (window.getSelection)
					{
						var selection = window.getSelection();  
						var range = document.createRange();
						
						if(element != "undefined" && element != null)
						{
							range.selectNodeContents(element);
							selection.removeAllRanges();
							selection.addRange(range);
							
							if($(element).is(":empty"))
							{
								$(element).append("&nbsp;");
								selectText($(element));
							}
						}
					}
				}
			}

			// the function of converting text to link
			function selected2link()
			{
				if(!toolbar.data("sourceOpened"))
				{
					var selectedTag = getSelectedNode(); // the selected node
					var thisHrefLink = "http://"; // default the input value of the link-form-field
					
					if(selectedTag)
					{
						
						var thisTagName  = selectedTag.prop('tagName').toLowerCase();
						
						// if tag name of the selected node is "a" and the selected node have "href" attribute
						if(thisTagName == "a" && selectedTag.is('[href]'))
						{
							thisHrefLink = selectedTag.attr('href');
							
							selectedTag.attr(setdatalink,"");
						}
						// if it don't have "a" tag name
						else{
							replaceSelection("a",setdatalink,"");
						} 
						
					}
					else 
						linkinput.val(thisHrefLink).focus();
					
					// the method of displaying-hiding to link-types
					//thong:
					/*linktypeselect.click(function(e)
					{
						if($(e.target).hasClass(vars.css+"_linktypetext") || $(e.target).hasClass(vars.css+"_linktypearrow"))
							typeSwitch(true,linktypes);
					});*/
					
					// the method of selecting to link-types
					/*linktypes.find("a").click(function()
					{
						var thisLinkType = $(this).attr(vars.css+"-linktype");
						
						linktypes.data("linktype",thisLinkType)
						
						linktypeview.find("."+vars.css+"_linktypetext").html(linktypes.find('a:eq('+linktypes.data("linktype")+')').text());
						
						linkInputSet(thisHrefLink);
						//thong:
						typeSwitch(false,linktypes);
					});*/
					
					linkInputSet(thisHrefLink);
					
					// the method of link-input
					linkinput
						// auto focus
						.focus()
						// update to value
						.val(thisHrefLink)
						// the event of key to enter in link-input
						.bind("keypress keyup",function(e)
						{
							if(e.keyCode==13)
							{
								linkRecord($('.qb-component').find("a["+setdatalink+"]"));
								return false;
							}
						});
					
					// the event of click link-button
					linkbutton.click(function()
					{
						linkRecord($('.qb-component').find("a["+setdatalink+"]"));
					});
				}
			}
			
			// the function of converting text to link
			
			function linkRecord(thisSelection)
			{
				// focus to link-input
				linkinput.focus();
				
				// select to the selected node
				selectText(thisSelection);
				
				// remove pre-link attribute (mark as "link will be added") of the selected node
				thisSelection.removeAttr(setdatalink);
				
				// if not selected to link-type of picture
				if(linktypes.data("linktype")!="2"){
					var link = linkinput.val();
					
					selectionSet("createlink",link); // insert link url of link-input to the selected node

					if(linkcheck.prop('checked')) thisSelection.attr('target','_blank');
					else thisSelection.removeAttr('target');
					
					if(linkUnder.prop('checked')) thisSelection.css("text-decoration","underline");
					else thisSelection.css('text-decoration','none');
				}
				// if selected to link-type of picture
				else
				{
					selectionSet("insertImage",linkinput.val()); // insert image url of link-input to the selected node

					// the method of all pictures in the editor
//					editor.find("img").each(function(){
//						var emptyPrevLinks = $(this).prev("a");
//						var emptyNextLinks = $(this).next("a");
//						
//						// if "a" tags of the front and rear of the picture is empty, remove
//						if(emptyPrevLinks.length>0 && emptyPrevLinks.html()=="")
//							emptyPrevLinks.remove();
//						else if(emptyNextLinks.length>0 && emptyNextLinks.html()=="")
//							emptyNextLinks.remove();
//					});
				}

			}
			
			// the function of switching link-type-selector
			function typeSwitch(status,types)
			{
				if(status)
					types.show();
				else
					types.hide();
			}
			
			// the function of updating the link-input according to the link-type
			function linkInputSet(thisHrefLink)
			{
				var currentType = linktypes.data("linktype");
				
				// if selected type of e-mail
				if(currentType=="1" && (linkinput.val()=="http://" || linkinput.is("[value^=http://]") || !linkinput.is("[value^=mailto]"))) 
					linkinput.val("mailto:");
				else if(currentType!="1" && !linkinput.is("[value^=http://]"))
						linkinput.val("http://");
				else
					linkinput.val(thisHrefLink);
			}
			
			// the function of adding style to selected text
			function selected2style(styleCommand)
			{
				if(!toolbar.data("sourceOpened"))
				{
					// if selected to changing the text-color value
					/*if(styleCommand=="colors")
						styleField = cpalette;*/
					
					// if selected to changing the line-Behind value
					if(styleCommand=="lineBehinds")
						styleField = lineBehindbar;
					
					// display the style-field
						styleFieldSwitch(styleField,true);
						
					// the event of click to style button
					styleField.find("a").unbind("click").click(function()
					{
						var styleValue = $(this).attr(vars.css + "-styleval"); // the property of style value to be added
						
						// if selected to changing the text-color value
						/*if(styleCommand=="colors")
						{
							styleType  = "color";
							styleValue = "rgb("+styleValue + ")"; // combine color value with rgb
						}*/
						// if selected to changing the font icon value
						if(styleCommand=="lineBehinds")
						{
							styleType  = "line-behind";
							styleValue = styleValue; // combine the font icon
						}
						
						var prevStyles = refuseStyle(styleType); // affect styles to child tags (and extract to the new style attributes)
						
						// change to selected text
						if(styleCommand=="lineBehinds"){
							replaceSelection("span","class","line_behind line_behind_"+styleValue);
						}else{
							replaceSelection("span","style",styleType+":"+styleValue+";"+prevStyles);
						}
						
						// hide all style-fields
						styleFieldSwitch("",false);
						
						// remove title bubbles
						$('.'+vars.css+'_title').remove();
						
					});
					
				}
				else
					// hide the style-field
					styleFieldSwitch(styleField,false);
					
			}
			
			// the function of switching the style-field
			function styleFieldSwitch(styleField,status)
			{
				var mainData="", // the style data of the actual wanted
					allData = [/*{"d":"cpallOpened","f":cpalette},*/
					           {"d":"lineBehindOpened","f":lineBehindbar}]; // all style datas
				
				// if the style data of the actual wanted isn't empty
				if(styleField!="")
				{
					// return to all datas and find the main data
					for(var si=0; si < allData.length; si++)
					{
						if(styleField==allData[si]["f"])
							mainData = allData[si];
					}
				}
				// display the style-field
				if(status)
				{
					toolbar.data(mainData["d"],true); // stil seçme alanının açıldığını belirten parametre yaz 
					mainData["f"].slideDown(100); // stil seçme alanını aç
					
					// return to all datas and close the fields of external datas
					for(var si=0; si < allData.length; si++)
					{
						if(mainData["d"]!=allData[si]["d"] || allData[si]["f"].hasClass('open'))
						{
							toolbar.data(allData[si]["d"],false);
							allData[si]["f"].slideUp(100).removeClass('open');
						}else{
								allData[si]["f"].addClass('open');
						}
					}
				}
				// hide all style-fields
				else
				{
					// return to all datas and close all style fields
					for(var si=0; si < allData.length; si++)
					{
						toolbar.data(allData[si]["d"],false);
						allData[si]["f"].slideUp(100).removeClass('open');
					}
				}
			}
			
			// the function of removing all pre-link attribute (mark as "link will be added")
			function clearSetElement(elem)
			{
				$('.qb-component').find(elem).each(function(){
					$(this).before($(this).html()).remove();
				});
			}
			
			// the function of refusing some styles
			function refuseStyle(refStyle)
			{
				var selectedTag = getSelectedNode(); // the selected node
				
				// if the selected node have attribute of "style" and it have unwanted style
				if(selectedTag && selectedTag.is("[style]") && selectedTag.css(refStyle)!="")
				{
					var refValue = selectedTag.css(refStyle); // first get key of unwanted style
					
					selectedTag.css(refStyle,""); // clear unwanted style
					
					var cleanStyle = selectedTag.attr("style"); // cleaned style
					
					selectedTag.css(refStyle,refValue); // add unwanted style to the selected node again
					
					return cleanStyle; // print cleaned style
				}
				else
					return "";
			}
			
			// the function of adding style to selected text
			function selected2format()
			{
				//formatFieldSwitch(true);
				
				formatbar.find("a").click(function()
				{
					$("*",this).click(function(e)
					{
						e.preventDefault();
						return false;
					});
					
					formatLabelView($(this).text());
					
					var formatValue = $(this).attr(vars.css + "-formatval"); // the type of format value
					
					// convert to selected format
					selectionSet("formatBlock",'<'+formatValue+'>');
					
					//formatFieldSwitch(false);
				});
			}
			
			// the function of switching the style-field
			function formatFieldSwitch(status)
			{				
				// var thisStatus = status ? true : false;
				
				// thisStatus = status && formatbar.data("status") ? true : false;
				
				// if(thisStatus || !status)
				// 	formatbar.data("status",false).slideUp(200);
				// else
				// 	formatbar.data("status",true).slideDown(200);
			}
			
			// change format label
			function formatLabelView(str)
			{
				var formatLabel = formatbar.closest("."+vars.css+"_tool").find("."+vars.css+"_tool_label").find("."+vars.css+"_tool_text");
				
				if(str.length > 10)
						str = str.substr(0,7) + "...";
				
				// change format label of button
				formatLabel.html(str);
			}

			// the function of insertion a specific form to texts
			function extractToText(strings)
			{
				var $htmlContent, $htmlPattern, $htmlReplace;

				// first remove to unnecessary gaps
				$htmlContent = strings.replace(/\n/gim,'').replace(/\r/gim,'').replace(/\t/gim,'').replace(/&nbsp;/gim,' ');

				$htmlPattern =  [
					/\<span(|\s+.*?)><span(|\s+.*?)>(.*?)<\/span><\/span>/gim, // trim nested spans
					/<(\w*[^p])\s*[^\/>]*>\s*<\/\1>/gim, // remove empty or white-spaces tags (ignore paragraphs (<p>) and breaks (<br>))
					/\<div(|\s+.*?)>(.*?)\<\/div>/gim, // convert div to p
					/\<strong(|\s+.*?)>(.*?)\<\/strong>/gim, // convert strong to b
					/\<em(|\s+.*?)>(.*?)\<\/em>/gim // convert em to i
				];

				$htmlReplace = [
					'<span$2>$3</span>',
					'',
					'<p$1>$2</p>',
					'<b$1>$2</b>',
					'<i$1>$2</i>'
				];
				
				// repeat the cleaning process 5 times
				for(c=0; c<5; c++)
				{
					// create loop as the number of pattern
					for(var i = 0; i < $htmlPattern.length; i++)
					{
						$htmlContent = $htmlContent.replace($htmlPattern[i], $htmlReplace[i]);
					}
				}

				// if paragraph is false (<p>), convert <p> to <br>
				if(!vars.p)
					$htmlContent = $htmlContent.replace(/\<p(|\s+.*?)>(.*?)\<\/p>/ig, '<br/>$2');

				// if break is false (<br>), convert <br> to <p>
				if(!vars.br)
				{
					$htmlPattern =  [
						/\<br>(.*?)/ig,
						/\<br\/>(.*?)/ig
					];

					$htmlReplace = [
						'<p>$1</p>',
						'<p>$1</p>'
					];

					// create loop as the number of pattern (for breaks)
					for (var i = 0; i < $htmlPattern.length; i++) {
						$htmlContent = $htmlContent.replace($htmlPattern[i], $htmlReplace[i]);
					}
				}

				// if paragraph and break is false (<p> && <br>), convert <p> to <div>
				if(!vars.p && !vars.br)
					$htmlContent = $htmlContent.replace(/\<p>(.*?)\<\/p>/ig, '<div>$1</div>');

				return $htmlContent;
			}

			// the function of getting parent (or super parent) tag name of the selected node
			function detectElement(tags){
			
				var resultdetect=false, $node = getSelectedNode(), parentsTag;
				
				if($node)
				{
					$.each(tags, function(i, val){
						parentsTag = $node.prop('tagName').toLowerCase();

						if (parentsTag == val)
							resultdetect = true;
						else
						{
							$node.parents().each(function(){
								parentsTag = $(this).prop('tagName').toLowerCase();
								if (parentsTag == val && !$(this).hasClass('qb-oohhay-out-wrapper-main'))
									resultdetect = true;
							});
						}
					});
					
					return resultdetect;
				}
				else 
					return false;
			};

			// the function of highlighting the toolbar buttons according to the cursor position in jqte editor
			//used
			function buttonEmphasize(e)
			{
				
				for(var n = 0; n < buttons.length; n++)
				{				
					if(vars[buttons[n].name] && buttons[n].emphasis && buttons[n].tag!='')
						detectElement(buttons[n].tag) ? toolbar.find('.'+vars.css+'_tool_'+buttons[n].cls).addClass(emphasize) : $('.'+vars.css+'_tool_'+buttons[n].cls).removeClass(emphasize);
				}
				// showing text format
				if(vars.format && $.isArray(vars.formats))
				{
					var isFoundFormat = false;
					
					for(var f = 0; f < vars.formats.length; f++)
					{
						var thisFormat = [];
						thisFormat[0] = vars.formats[f][0];
						
						if(vars.formats[f][0].length>0 && detectElement(thisFormat))
						{
							formatLabelView(vars.formats[f][1]);
							
							isFoundFormat = true;
							break;
						}
					}
					
					if(!isFoundFormat)
						formatLabelView(vars.formats[0][1]);
				}
				
				// hide all style-fields
				styleFieldSwitch("",false);
				//formatFieldSwitch(false);
			}

			// the event of click to the toolbar buttons
			toolbutton
				.unbind("click")
				.click(function(e){	
					// if source button is clicked
					if($(this).data('command')=='displaysource' && !toolbar.data("sourceOpened"))
					{
						// hide all the toolbar buttons (except the source button)
						toolbar.find("."+vars.css+"_tool").addClass(vars.css+"_hiddenField");
						$(this).removeClass(vars.css+"_hiddenField");
						
						// update to data of source displaying
						toolbar.data("sourceOpened",true);
						
						// equalize height of the text field with height of the source field
//						thisElement.css("height",editor.outerHeight());
						
						thisElement.focus();
						
						// hide the link-form-field
						//thong:
						/*linkAreaSwitch(false);*/
						
						// hide all style-fields
						styleFieldSwitch("",false);
						
						// hide format field
						formatFieldSwitch();
						
					}
					// if other buttons is clicked
					else
					{
						// if source field is closed
						if(!toolbar.data("sourceOpened"))
						{
							// if the format button is clicked
							if($(this).data('command')=='formats')
							{
								if($(this).data('command')=='formats' && !$(e.target).hasClass(vars.css+"_format"))
									selected2format();
									
								// hide all style-fields
								//styleFieldSwitch("",false);
								
							}
							
							// if the style buttons are clicked
							else if($(this).data('command')=='colors' || $(this).data('command')=='lineBehinds')
							{
								if(
									($(this).data('command')=='colors' && !$(e.target).hasClass(vars.css+"_color")) || // the color button
									($(this).data('command')=='lineBehinds' && !$(e.target).hasClass(vars.css+"_lineBehind")) // the font icon button
								)
								selected2style($(this).data('command'));
								
								// hide format field
								formatFieldSwitch(false);
								
							}

							// if other buttons is clicked
							else
							{

								// apply command of clicked button to the selected text
								selectionSet($(this).data('command'),null);

								// hide all menu-fields
								styleFieldSwitch("",false);
								formatFieldSwitch(false);
								//thong:
								/*typeSwitch(false,linktypes);*/

								// to highlight the toolbar buttons according to the cursor position in jqte editor
								$(this).data('emphasis')==true && !$(this).hasClass(emphasize) ? $(this).addClass(emphasize) : $(this).removeClass(emphasize);

							}

						}
						// hide the source field and display the text field
						else
						{
							// update to data of source hiding
							toolbar.data("sourceOpened",false);

							// display all the toolbar buttons
							toolbar.find("."+vars.css+"_tool").removeClass(vars.css+"_hiddenField");
							
						}
						
					}
					
				})
				// the event of showing to the title bubble when mouse over of the toolbar buttons
				.hover(function(e){
					if(vars.title && $(this).data("title")!="" && ( $(e.target).hasClass(vars.css+"_tool") || $(e.target).hasClass(vars.css+"_tool_icon") ))
					{
						$('.'+vars.css+'_title').remove();

						// create the title bubble
						jQTE.append('<div class="'+vars.css+'_title"><div class="'+vars.css+'_titleArrow"><div class="'+vars.css+'_titleArrowIcon"></div></div><div class="'+vars.css+'_titleText">'+$(this).data("title")+'</div></div>');
						
						var thisTitle = $('.'+vars.css+'_title:first');
						var thisArrow = thisTitle.find('.'+vars.css+'_titleArrowIcon');
						var thisPosition = $(this).position();
						var thisAlignX = thisPosition.left + $(this).outerWidth() - (thisTitle.outerWidth()/2) - ($(this).outerWidth()/2);
						var thisAlignY = (thisPosition.top + $(this).outerHeight() + 5);

						// show the title bubble and set to its position
						thisTitle.delay(400).css({'top':thisAlignY, 'left':thisAlignX}).fadeIn(200);
					}
				},function(){
					$('.'+vars.css+'_title').remove();
				}).bind("mouseup",buttonEmphasize);

			// prevent multiple calling postToSource()
			var editorChangeTimer = null;

			// the methods of the text fields
			$('.asd')

				// trigger change method of the text field when the text field modified
				.bind("keypress keyup keydown drop cut copy paste DOMCharacterDataModified DOMSubtreeModified",function()
				{
						
					// if the change method is added run the change method   
					if($.isFunction(vars.change))
						vars.change();
					
				})
				
				// method of triggering to the highlight button
				.bind("mouseup keyup",buttonEmphasize)

				// the event of focus to the text field
				.focus(function()
				{
					// if the focus method is added run the focus method   
					if($.isFunction(vars.focus))
						vars.focus();
					
					// add onfocus class
					jQTE.addClass(vars.css+"_focused");
					
					// prevent focus problem on opera
					if(/opera/.test(thisBrowser))
					{
						var range = document.createRange();
						range.selectNodeContents(editor[0]);
						range.collapse(false);
						var selection = window.getSelection();
						selection.removeAllRanges();
						selection.addRange(range);
					}
				})

				// the event of focus out from the text field
				.focusout(function()
				{
					// remove to highlights of all toolbar buttons
					toolbutton.removeClass(emphasize);
					
					// hide all menu-fields
					styleFieldSwitch("",false);
					formatFieldSwitch(false);
					
					// if the blur method is added run the blur method   
					if($.isFunction(vars.blur))
						vars.blur();
					
					// remove onfocus class	
					jQTE.removeClass(vars.css+"_focused");
					
					// show default text format
					if($.isArray(vars.formats))
						formatLabelView(vars.formats[0][1]);
				});

			// the event of key in the source field
			thisElement
				.bind("keydown keyup",function()
				{
					// auto extension for the source field
					$(this).height($(this)[0].scrollHeight);
					
					// if the source field is empty, shorten to the source field
					if($(this).val()=="")
						$(this).height(0);
				})
				.focus(function()
				{
					// add onfocus class
					jQTE.addClass(vars.css+"_focused");
				})
				.focusout(function()
				{
					// remove onfocus class	
					jQTE.removeClass(vars.css+"_focused");
				});
			
			/* createFlag*/
			function createFlag() {
				var thisSelection = $('.qb-component').find("["+setdataflag+"]");
				selectText(thisSelection);
				
				// for webkit, mozilla, opera
				if (window.getSelection)
				{
					var selObj = selectionGet(), selRange, newElement, documentFragment;
					if (selObj.anchorNode && selObj.getRangeAt)
					{
						selRange = selObj.getRangeAt(0);
						var ancestor = selRange.commonAncestorContainer;
						if(ancestor.childNodes.length == 3 && (ancestor.childNodes[1].tagName.toLowerCase()=="span" || ancestor.childNodes[1].tagName.toLowerCase()=="a") && ancestor.childNodes[0].data=='' && ancestor.childNodes[2].data==''){
							/*case: da co the span va select ben ngoai*/
							$(ancestor.childNodes[1]).attr(setdataflag,'');
							console.log('da co the span va select ben ngoai');
						}else if(ancestor.childNodes.length > 2 && (ancestor.childNodes[1].tagName.toLowerCase()=="p" 
							|| ancestor.childNodes[1].tagName.toLowerCase()=="h1"
							|| ancestor.childNodes[1].tagName.toLowerCase()=="h2"
							|| ancestor.childNodes[1].tagName.toLowerCase()=="h3"
							|| ancestor.childNodes[1].tagName.toLowerCase()=="h4"
							|| ancestor.childNodes[1].tagName.toLowerCase()=="h5"
							|| ancestor.childNodes[1].tagName.toLowerCase()=="h6")){
							/*case: select bao the p,h1,h2,h3,h4,h5,h6*/
							console.log('select bao the p,h1,h2,h3,h4,h5,h6');
							// create to new element
							newElement = document.createElement('span');
							// extract to the selected text
							documentFragment = ancestor.childNodes[1];
							// add the contents to the new element
							newElement.appendChild(documentFragment);
							//case create flag
							$(newElement).attr('colorflag','');
							ancestor.childNodes[1].insertNode(newElement);
							var clo = $('.qb-component').find('[colorflag]').css('color');
							fontcolor.data('val',clo).css('background-color',clo);
							$('.qb-component').find('[colorflag]').removeAttr('colorflag').attr(setdataflag,'');
							selObj.removeAllRanges();
							affectStyleAround($(newElement),false);
						}
						else if(selRange.startOffset === 0 && ancestor.length === selRange.endOffset && (ancestor.parentNode.tagName.toLowerCase() === "span" || ancestor.parentNode.tagName.toLowerCase() === "a")){
							/*case: da co the span va select ben trong*/
							$(ancestor.parentNode).attr(setdataflag,'');
							console.log('da co the span va select ben trong');
						}else if(selRange.startOffset === 0 && ancestor.length === selRange.endOffset && ancestor.parentNode.tagName.toLowerCase() === "i"){
							/*case: da co the span va select ben trong*/
							console.log('da co the i va select ben trong i');
							console.log(selRange);
							console.log(ancestor);
							console.log(ancestor.parentNode.outerHTML);
							if(ancestor.parentNode.parentNode.childElementCount = 1 && ancestor.parentNode.parentNode.localName == 'span'){
								console.log('case ngoai i co span');
								$(ancestor.parentNode.parentNode).attr(setdataflag,'');
							}else{
								console.log('cass ngoai i ko co span');								
								// create to new element
								newElement = document.createElement('span');
								// extract to the selected text
								documentFragment = ancestor.parentNode.extractContents();
								// add the contents to the new element
								newElement.appendChild(documentFragment);
								//case create flag
								$(newElement).attr('colorflag','');
								selRange.insertNode(newElement);
								var clo = $('.qb-component').find('[colorflag]').css('color');
								fontcolor.data('val',clo).css('background-color',clo);
								$('.qb-component').find('[colorflag]').removeAttr('colorflag').attr(setdataflag,'');
								selObj.removeAllRanges();
								affectStyleAround($(newElement),false);
							}
						}else{
							console.log('binh thuong');
							// create to new element
							newElement = document.createElement('span');
							// extract to the selected text
							documentFragment = selRange.extractContents();
							// add the contents to the new element
							newElement.appendChild(documentFragment);
							//case create flag
							$(newElement).attr('colorflag','');
							selRange.insertNode(newElement);
							var clo = $('.qb-component').find('[colorflag]').css('color');
							fontcolor.data('val',clo).css('background-color',clo);
							$('.qb-component').find('[colorflag]').removeAttr('colorflag').attr(setdataflag,'');
							//clearClassNull();
							selObj.removeAllRanges();
							affectStyleAround($(newElement),false);
						}
//						selObj.removeAllRanges();
//						affectStyleAround($(newElement),false);
						$(".otext-wrapper-content i.fa:empty").remove();
						$(".otext-wrapper-content span:empty").remove();
						$(".otext-wrapper-content p:empty").remove();
						$(".otext-wrapper-content h1:empty").remove();
						$(".otext-wrapper-content h2:empty").remove();
						$(".otext-wrapper-content h3:empty").remove();
						$(".otext-wrapper-content h4:empty").remove();
						$(".otext-wrapper-content h5:empty").remove();
						$(".otext-wrapper-content h6:empty").remove();
					}
				}
				// for ie
				else if (document.selection && document.selection.createRange && document.selection.type != "None")
				{
					var range = document.selection.createRange();
					var selectedText = range.htmlText;
					
					var newText = '<'+tTag+' '+tAttr+'="'+tVal+'">'+selectedText+'</'+tTag+'>';
					
					document.selection.createRange().pasteHTML(newText);
				}
			}
			
			/*initEventStyle*/
			
			var thisSelection = $('.qb-component').find("["+setdataflag+"]");
			
			fontsize.bind("keypress keyup input change",function(e){
				styleElement('fontsize');
			});
			fontstyle.find('li').unbind('click').click(function(){
				thisSelection = $('.qb-component').find("["+setdataflag+"]");
				thisSelection.css('font-family',$(this).find('a').html());
				affectStyleAround(thisSelection,'font-family:'+$(this).find('a').html()+';'+refuseStyle("font-family"));
				fontstyle.find('.dropdown-title').html($(this).find('a').html());
				fontstyle.find('.dropdown-title').css('font-family',$(this).find('a').html());
			});
			fontweight.find('li').unbind('click').click(function(){
				thisSelection = $('.qb-component').find("["+setdataflag+"]");
				thisSelection.css('font-weight',$(this).find('a').data('val'));
				affectStyleAround(thisSelection,'font-weight:'+$(this).find('a').data('val')+';'+refuseStyle("font-weight"));
				fontweight.find('.dropdown-title').html($(this).find('a').html());
			});
			
			lheight.bind("keypress keyup input change",function(e){
				styleElement('lheight');
			});
			
			iconlist.find('a').unbind('click').click(function(){
				
				var iconvalue = $(this).children().html().trim();
				
				insertIcon("i","class","fa",iconvalue);
			});
			
			space.bind("keypress keyup input change",function(e){
				styleElement('space');
			});

			letter.bind("keypress keyup input change",function(e){
				styleElement('letter');
			});
			
			shadowform.find('input').bind("keypress keyup input change",function(e){
				styleElement('shadow');
			});
			
			linkbutton.click(function(){
				actionOption.open({
					callBack: function(result){
						$('.jqte_linkinput').val(result.dataLink);
						$('.jqte_linkinput').attr('data-action-link',result.dataLink);
						$('.jqte_linkinput').attr('data-action-type',result.dataType);
						$('.jqte_linkinput').attr('data-action-link-target',result.dataLinkTarget);
						styleElement('link');
					},
					target: $('.jqte_linkinput'),
				});
			});
			
			linkUnder.click(function(){
				styleElement('underline');
			});
			
			
			
			//THONG EDITOR NEW
			$(document).on('click','.setattr .panel-title a',function(){
				if($('.qb-component').find("["+setdataflag+"]").length == 0){
					var selectedTag = getSelectedNode();
					if($(selectedTag).closest('.qb-component').length > 0 && !$(this).closest('.setattr').hasClass('.jqte_toolbar')){
						createFlag();
					}
				}
				var ob_class = $(this).closest('.setattr').data('tool');
				getValForEditor(ob_class);
			});
			
			$(document).on('click','.jqte_toolbar .panel-collapse',function(){
				if(!$(this).hasClass('in')){
					$('.setattr .panel-collapse').css("height","0px").attr("aria-expanded","false").removeClass("in");
				}
			});
			
			$(document).on('mousedown','.otext-wrapper-content[contenteditable="true"], .grid-style-onedit .obutton-label[contenteditable="true"], .evo-editor', function(){
				if(!$('.jqte_toolbar .panel-collapse').hasClass('in')){
					$('.setattr .panel-collapse').css("height","0px").attr("aria-expanded","false").removeClass("in");
				}
				if($('.qb-component').find('[jqte-setflag]').length){
					if($('.qb-component').find('[jqte-setflag]').attr('style') || $('.qb-component').find('[jqte-setflag]').attr('data-action-type'))
						$('.qb-component').find('[jqte-setflag]').removeAttr('jqte-setflag');
					else{
						if(checkSelect())
							$('.qb-component').find('[jqte-setflag]').remove();
						else
							clearSetElement("[jqte-setflag]");
					}
				}
				
				//$(".otext-wrapper-content i.fa:empty").remove();
				// if ($('#qb-dlg-color-picker').dialog('isOpen') === true) {
				// 	colorPicker.close();
				// }
				//actionOption.close();
			});
			$(document).on("mouseup keyup",".otext-wrapper-content[contenteditable='true'], .grid-style-onedit .obutton-label[contenteditable='true']",function(e){
				buttonEmphasize(e);
			});
			
			//color picker text-shadow
			$(document).on('click','.jqte_shadowColor',function(){
            	colorPicker.open({
 					callBack : function(result){
 						shadowColor.data('val',result.data).css('background-color',result.data);
 						styleElement('shadow');
					},
     				targetBox : shadowColor,
     				gradient : false,
     			});
			});
			
			//color picker font color
			$(document).on('click','.jqte_fontcolor',function(){
				colorPicker.open({
					callBack : function(result){
						fontcolor.data('val',result.data).css('background-color',result.data);
						styleElement('fontcolor');
					},
					targetBox : fontcolor,
					gradient : false,
				});
			});
			
			//new
			function getValForEditor(ob_class){
				var thisSelection = $('.qb-component').find("["+setdataflag+"]");
				var styleVal = "";
				if(thisSelection.length>0){
					switch(ob_class.trim()) {
					    case 'toolbar':
					        break;
					    case 'fontform':
					    	styleVal = thisSelection.css('font-size').replace('px','').replace('%','');
							fontsize.val(styleVal);
							styleVal = thisSelection.css('font-family');
							fontstyle.find('.dropdown-title').html(styleVal);
							fontstyle.find('.dropdown-title').css('font-family',styleVal);
							styleVal = thisSelection.css('font-weight');
							if(styleVal =="300")
								fontweight.find('.dropdown-title').html('Light');
							else if(styleVal =="400")
								fontweight.find('.dropdown-title').html('Normal');
							else if(styleVal =="500")
								fontweight.find('.dropdown-title').html('Medium');
							else if(styleVal =="700")
								fontweight.find('.dropdown-title').html('Bold');
							else
								fontweight.find('.dropdown-title').html('Normal');
					        break;
					    case 'iconform':
					    	break;
					    case 'spacingform':
					    	styleVal = thisSelection.css('line-height').replace('px','').replace('%','');
							lheight.val(styleVal);
							styleVal = thisSelection.css('word-spacing').replace('px','').replace('%','');
							space.val(styleVal);
							styleVal = thisSelection.css('letter-spacing').replace('px','').replace('%','');
							letter.val(styleVal);
					    	break;
					    case 'shadowform':
					    	styleVal = thisSelection.css('text-shadow');
							if(styleVal !="none"){
								var ret = styleVal.split(")");
								shadowColor.data('val',ret[0]+')').css('background-color',ret[0]+')');
								var sdval = ret[1].split("px");
								shadowH.val(sdval[0].trim());
								shadowV.val(sdval[1].trim());
								shadowBlur.val(sdval[2].trim());
							}else{
								shadowH.val(0);
								shadowV.val(0);
								shadowBlur.val(0);
								shadowColor.data('val',$('.jqte_fontcolor').data('val')).css('background-color',$('.jqte_fontcolor').data('val'));
							}
					    	break;
					    case 'linkform':
					    	var templink = thisSelection.closest('a').attr('data-action-link');
							var templinkchild = thisSelection.find('[data-action-link]').attr('data-action-link');
							if(templink){
								linkinput.val(templink).attr('data-action-link',thisSelection.closest('a').attr('data-action-link')).attr('data-action-type',thisSelection.closest('a').attr('data-action-type')).attr('data-action-link-target',thisSelection.closest('a').attr('data-action-link-target'));
							}else if(templinkchild){
								linkinput.val(templinkchild).attr('data-action-link',thisSelection.find('[data-action-link]').attr('data-action-link')).attr('data-action-type',thisSelection.find('[data-action-link]').attr('data-action-type')).attr('data-action-link-target',thisSelection.find('[data-action-link]').attr('data-action-link-target'));
							}else{
								linkinput.val('').attr('data-action-link','').attr('data-action-type','').attr('data-action-link-target','');
							}
							/*underline*/
							if(thisSelection.parent().css('text-decoration')==='underline')
								linkUnder.prop('checked',true);
							else linkUnder.prop('checked',false);
					    	break;
						} 
					}
				};
				
				//New Function
				function styleElement(name){
					var thisSelection = $('.qb-component').find("["+setdataflag+"]");
					if(name==='fontsize'){
						thisSelection.css('font-size',fontsize.val()+'px');
						affectStyleAround(thisSelection,'font-size:'+fontsize.val()+'px;'+refuseStyle("font-size"));
					}else if(name==='fontcolor'){
						thisSelection.css('color',fontcolor.data('val')+';'+refuseStyle("font-size"));
						affectStyleAround(thisSelection,'color:'+fontcolor.data('val'));
					}else if(name==='lheight'){
						thisSelection.css('line-height',lheight.val()+'px');
						affectStyleAround(thisSelection,'line-height:'+lheight.val()+'px;'+refuseStyle("line-height"));
					}else if(name==='space'){
						thisSelection.css("word-spacing",space.val()+"px");
						affectStyleAround(thisSelection,'word-spacing:'+space.val()+'px;'+refuseStyle("word-spacing"));
					}else if(name==='letter'){
						thisSelection.css("letter-spacing",letter.val()+"px");
						affectStyleAround(thisSelection,'letter-spacing:'+letter.val()+'px;'+refuseStyle("letter-spacing"));
					}else if(name==='shadow'){
						var val = shadowH.val()+'px '+shadowV.val()+'px '+shadowBlur.val()+'px '+shadowColor.data("val");
						thisSelection.css('text-shadow',val);
						affectStyleAround(thisSelection,'text-shadow:'+val+';'+refuseStyle("text-shadow"));
					}else if(name==='link'){
						var link = linkinput.val();
						//changTagSelection('a');
						insertLink();
						thisSelection = $('.qb-component').find("["+setdataflag+"]");
						thisSelection.attr('data-action-link',linkinput.attr('data-action-link')).attr('data-action-type',linkinput.attr('data-action-type')).attr('data-action-link-target',linkinput.attr('data-action-link-target'));
					}else if(name==='underline'){
						if(linkUnder.prop('checked')){
							thisSelection.css("text-decoration","underline");
						}else {
							thisSelection.css('text-decoration','none');
						}
					}
				}
				
				function insertLink(){
					var thisSelection = $('.qb-component').find("["+setdataflag+"]");
					selectText(thisSelection);
					var thisTagName  = getSelectedNode().prop('nodeName').toLowerCase();
					if(thisTagName === 'a'){
						thisSelection.attr('data-action-link',linkinput.attr('data-action-link')).attr('data-action-type',linkinput.attr('data-action-type')).attr('data-action-link-target',linkinput.attr('data-action-link-target'));
					}else{
						if (window.getSelection)
						{
							var selObj = selectionGet(), selRange, newElement, documentFragment;
							
							if (selObj.anchorNode && selObj.getRangeAt)
							{
								selRange = selObj.getRangeAt(0);
								var node1 = selRange.commonAncestorContainer;
								if($(node1).closest('a').length >0){
									$(node1).closest('a').attr('data-action-link',linkinput.attr('data-action-link')).attr('data-action-type',linkinput.attr('data-action-type')).attr('data-action-link-target',linkinput.attr('data-action-link-target'))
									thisSelection.removeAttr('data-action-link').attr('data-action-type').attr('data-action-link-target');
								}else{
									// create to new element
									newElement = document.createElement('a');
									$(newElement).attr(setdataflag,'');
									$(newElement).attr('style',$(node1).attr('style')).attr('data-action-link',linkinput.attr('data-action-link')).attr('data-action-type',linkinput.attr('data-action-type')).attr('data-action-link-target',linkinput.attr('data-action-link-target'));
									if($(node1).find('a').length < 1){
										
									}else{
										newElement2 = document.createElement('span');
										$(newElement2).attr('style',$(node1).find('a').attr('style')).html($(node1).find('a').html());
										$(node1).find('a').before(newElement2).remove();
									}
									documentFragment = selRange.extractContents();
									// add the contents to the new element
									newElement.appendChild(documentFragment);
									$(node1).before(newElement).remove();
									
								}
								
								selObj.removeAllRanges();
							}
						}
						// for ie
						else if (document.selection && document.selection.createRange && document.selection.type != "None")
						{
							var range = document.selection.createRange();
							var selectedText = range.htmlText;
							
							var newText = '<'+tTag+' '+setdataflag+'="">'+selectedText+'</'+tTag+'>';
							
							document.selection.createRange().pasteHTML(newText);
						}
					}
				}
				
				function changTagSelection(tTag) {
					var thisSelection = $('.qb-component').find("["+setdataflag+"]");
					selectText(thisSelection);
					var selectedTag = getSelectedNode();
					var thisTagName  = selectedTag.prop('nodeName').toLowerCase();
					if(thisTagName!=tTag){
						if (window.getSelection)
						{
							var selObj = selectionGet(), selRange, newElement, documentFragment;
							
							if (selObj.anchorNode && selObj.getRangeAt)
							{
								selRange = selObj.getRangeAt(0);
								var node1 = selRange.commonAncestorContainer;
								// create to new element
								newElement = document.createElement(tTag);
								$(newElement).attr(setdataflag,'');
								$(newElement).attr('style',$(node1).attr('style'));
								documentFragment = selRange.extractContents();
								// add the contents to the new element
								newElement.appendChild(documentFragment);
								//$(newElement).attr('style',style);
								///$(newElement).attr(setdataflag,'');
								$(node1).before(newElement).remove();
								//parent.replaceChild(newElement,node1 );
								selObj.removeAllRanges();
							}
						}
						// for ie
						else if (document.selection && document.selection.createRange && document.selection.type != "None")
						{
							var range = document.selection.createRange();
							var selectedText = range.htmlText;
							
							var newText = '<'+tTag+' '+setdataflag+'="">'+selectedText+'</'+tTag+'>';
							
							document.selection.createRange().pasteHTML(newText);
						}
					}
				}
				
				function insertIcon(tTag,tAttr,tVal,tCont) {
					var thisSelection = $('.qb-component').find("["+setdataflag+"]");
					selectText(thisSelection);
					// for webkit, mozilla, opera			
					if (window.getSelection)
					{
						var selObj = selectionGet(), selRange, newElement;
						
						if (selObj.anchorNode && selObj.getRangeAt)
						{
							selRange = selObj.getRangeAt(0);
							// create to new element
							newElement = document.createElement(tTag);
							$(newElement).attr(tAttr,tVal);
							$(newElement).html(tCont);
							selRange.insertNode(newElement);
							selObj.removeAllRanges();
						}
					}
					// for ie
					else if (document.selection && document.selection.createRange && document.selection.type != "None")
					{
						var range = document.selection.createRange();
						var selectedText = range.htmlText;
						
						var newText = '<'+tTag+' '+tAttr+'="'+tVal+'">'+tCont+'</'+tTag+'>'+selectedText;
						
						document.selection.createRange().pasteHTML(newText);
					}
				}
				
				/*checkselect*/
				function checkSelect(){
					var thisSelection = $('.qb-component').find("["+setdataflag+"]");
					if(thisSelection.html()=="&nbsp;"){
						return true;
					}
					return false;
				}
				
				/*clear class null or empty*/
				function clearClassNull(){
					$.each( $('.qb-component').find('span:empty'), function() {
						$(this).remove();
					});
				}
				
		});
	};
})(jQuery);