/**
 * @author ThoaiVt : undo and redo 27/08/2016
 */
(function() {
	undoRedoMgr = {
		tempValue: [],
		dataValue: [],
		undoRedoChange: true,
		/*
		 * init
		 */
		init : function() {
			if(ENABLE_UNDOMANAGER==1){
				undoMgr = new UndoManager();
				undoRedoMgr.eventListener();
			}else{
				console.log("Turn off undo and redo");
			}
		},
		/*
		 * eventListener
		 */
		eventListener : function() {
			
			// event all listener
			
			var patternListner = "attr_style,attr_data-action-tracking,attr_data-action-link,attr_data-action-link-target,attr_ho-effect,attr_onload-effect";
			/*
			 * listener tracking childrend
			 */
			$(".qb-oohhay-out-wrapper-main .qb-box-component .grid-stack .grid-stack-item-content .qb-component,.qb-oohhay-out-wrapper-main .qb-box-component .grid-stack .grid-stack-item-content .qb-component *,#content-wrapper .content-box .grid-stack .layer-background").attrchange({
				  trackValues: true, 
				  callback: function (event) {
//					console.log(event.attributeName);
//					console.log(event.oldValue);
//					console.log(event.newValue);
//					console.log(event);
//					console.log("First data : "+undoRedoMgr.firstChange);						
					var data = undoRedoMgr.switchAttributeOldChange($(this),event);
//					console.log(data);
					if(data!=undefined && undoRedoMgr.undoRedoChange==true){
						undoRedoMgr.dataValue=data;
						undoRedoMgr.tempValue=[];
					}
				}

			});
			
			/*
			 * listener tracking parent
			 */
			$('.qb-oohhay-out-wrapper-main .qb-box-component .grid-stack .grid-stack-item-content').attrchange({
				  trackValues: true, 
				  callback: function (event) {
					if(event.attributeName=='class' || event.attributeName=='style')
						return;
//					console.log(event.attributeName);
//					console.log(event.oldValue);
//					console.log(event.newValue);
					if(event.oldValue!=event.newValue){
						var data = undoRedoMgr.switchAttributeOldChange($(this),event);
//						console.log(data);
						if(data!=undefined && undoRedoMgr.undoRedoChange==true){
							undoRedoMgr.dataValue=data;
							undoRedoMgr.tempValue=[];
						}
					}
				  }
			});
			
			/*
			 * listener change attr
			 */
			 $(".qb-oohhay-out-wrapper-main .qb-box-component .grid-stack .grid-stack-item-content .qb-component,.qb-oohhay-out-wrapper-main .qb-box-component .grid-stack .grid-stack-item-content .qb-component *,#content-wrapper .content-box .grid-stack .layer-background").ready(function(){
			 $(".qb-oohhay-out-wrapper-main .qb-box-component .grid-stack .grid-stack-item-content .qb-component,.qb-oohhay-out-wrapper-main .qb-box-component .grid-stack .grid-stack-item-content .qb-component *,#content-wrapper .content-box .grid-stack .layer-background").watch({
				 properties: patternListner,
				 watchChildren: true,
				 callback: function (data, i) {
					 // type value change ?
					 var propChanged = data.props[i].replace("attr_","");
//					 console.log("Type change : "+propChanged);
//					 console.log("Change value "+undoRedoMgr.undoRedoChange);
					 if(undoRedoMgr.undoRedoChange==true){
//						 console.log("Add undo and redo");
						 undoRedoMgr.switchUndoRedoAllComponent($(this),undoRedoMgr.dataValue);
						 undoRedoMgr.undoValueCheck=false;
						 undoRedoMgr.firstChange = undefined;
					 }
				 }
			 	});
			 });
			 
			 /*
				 * listener change attr for parent
				 */
			 $(".qb-oohhay-out-wrapper-main .qb-box-component .grid-stack .grid-stack-item-content").ready(function(){
				 $(".qb-oohhay-out-wrapper-main .qb-box-component .grid-stack .grid-stack-item-content").watch({
					 properties: "attr_id",
					 watchChildren: true,
					 callback: function (data, i) {
						 // type value change ?
						 var propChanged = data.props[i].replace("attr_","");
//						 console.log("Type change : "+propChanged);
//						 console.log("Change value "+undoRedoMgr.undoRedoChange);
						 if(undoRedoMgr.undoRedoChange==true){
//							 console.log("Add undo and redo");
							 undoRedoMgr.switchUndoRedoAllComponent($(this),undoRedoMgr.dataValue);
							 undoRedoMgr.undoValueCheck=false;
							 // undoRedoMgr.firstChange = undefined;
						 }
					 }
				 	});
				 });
			 
			 /*
				 * event grid-stack change size width on web setting slider bar
				 */
			 $("#content-wrapper .content-box .grid-stack").ready(function(){
				 $("#content-wrapper .content-box .grid-stack").watch({
					 properties: "attr_style",
					 watchChildren: true,
					 callback: function (data, i) {
						 var propChanged = data.props[i].replace("attr_","");
// console.log("Type change : "+propChanged);
// console.log("Change value "+undoRedoMgr.undoRedoChange);
						 if(undoRedoMgr.undoRedoChange==true){
//							 console.log("Add undo and redo");
							 undoRedoMgr.switchUndoRedoAllComponent($(this),undoRedoMgr.dataValue);
							 undoRedoMgr.undoValueCheck=false;
							 undoRedoMgr.firstChange = undefined;
						 }
//						 console.log("Change height !");
					 }
				 });
			 });
			 
			 /*
				 * content-wrapper watch prop_innerHTML
				 */
// $("#content-wrapper li").ready(function(){
// $("#content-wrapper li").watch({
// properties: "prop_innerHTML",
// watchChildren: false,
// callback: function (data, i) {
// // var propChanged = data.props[i].replace("attr_","");
// // // console.log("Type change : "+propChanged);
// // // console.log("Change value "+undoRedoMgr.undoRedoChange);
// // if(undoRedoMgr.undoRedoChange==true){
// // console.log("Add undo and redo");
// // undoRedoMgr.switchUndoRedoAllComponent($(this),undoRedoMgr.dataValue);
// // undoRedoMgr.undoValueCheck=false;
// // undoRedoMgr.firstChange = undefined;
// // }
// var propChanged = data.props[i];
// var newValue = data.vals[i];
// console.log("Change "+propChanged+" - Value : "+newValue);
// }
// });
// });
			 
			console.log("Watch JQUERY");
			
			
			
		},
		/*
		 * change attribute change
		 */
		changeAttributeChange: function(el,arValue,typeUndo){
			for(var i=0;i<arValue.length;i++){
				if(typeUndo==0){
					el.attr(arValue[i].key,arValue[i].oldValue);
				}else{
					el.attr(arValue[i].key,arValue[i].newValue);
				}
			}
		},
		switchAttributeOldChange: function(el,event){
			var value=[];
			var type=event.attributeName;
			switch(type){
				case "style":
					if(event.target.className.indexOf("layer-background")==-1){
						data={
							key: "style",
							oldValue: event.oldValue,
							newValue: event.newValue
						}
					}else{
						console.log("Background layout change "+undoRedoMgr.firstChange);
						if(!undoRedoMgr.firstChange){
							undoRedoMgr.firstChange = event.oldValue;					
//							console.log("Set firstChange ");
						}
						data={
							key: "style",
							oldValue: undoRedoMgr.firstChange,
							newValue: event.newValue
						}
					}
					value.push(data);
				return value;
				case "data-action-link":
					var data={
						key: "data-action-link",
						oldValue: event.oldValue,
						newValue: event.newValue
					}
					undoRedoMgr.tempValue.push(data);
				break;
				case "data-action-link-target":
					var data = {
						key: "data-action-link-target",
						oldValue: event.oldValue,
						newValue: event.newValue
					}
					undoRedoMgr.tempValue.push(data);
					undoRedoMgr.undoValueCheck=true;
				return undoRedoMgr.tempValue;
				case "data-action-type":
					var data = {
						key: "data-action-link-target",
						oldValue: event.oldValue,
						newValue: event.newValue
					}
					undoRedoMgr.tempValue.push(data);
				break;
				case "class":
					break;
				default:
					var data={
						key: event.attributeName,
						oldValue: event.oldValue,
						newValue: event.newValue
					}
					value.push(data);
				return value;
			}
			
		},
		/*
		 * switch undo redo all component
		 */
		switchUndoRedoAllComponent(el,arrData){
			// defined component change
			try{
				undoMgr.add({
					undo: function() {
						undoRedoMgr.changeAttributeChange(el,arrData,0);
					}
				, redo: function() {    
						undoRedoMgr.changeAttributeChange(el,arrData,1);
	         		}
					});
				}catch(e){
					console.log(e);
				}
		},
		/*
		 * update value component
		 */
		updateDataComponent(el){
			var id = el.closest('.grid-stack-item-content').attr('qb-component-id');
			var data = componentModelManager.listComponent[id];
			return data;
		},
		/*
		 * init watch for element
		 */
		initWatch: function(elStr,patternListener,watchChild){
			 $(elStr).ready(function(){
				 $(elStr).watch({
					 properties: patternListener,
					 watchChildren: watchChild,
					 callback: function (data, i) {
						 // type value change ?
						 var propChanged = data.props[i].replace("attr_","");
//						 console.log("Type change : "+propChanged);
//						 console.log("Change value "+undoRedoMgr.undoRedoChange);
						 if(undoRedoMgr.undoRedoChange==true){
//							 console.log("Add undo and redo");
							 undoRedoMgr.switchUndoRedoAllComponent($(this),undoRedoMgr.dataValue);
							 undoRedoMgr.undoValueCheck=false;
							 undoRedoMgr.firstChange = undefined;
						 }
					 }
				 	});
				 });
		}
	}
}())