/**
 * @author TuNt
 * create date Jul 1, 2016
 * ohhay-webapplication-new
 */
(function(){
	administratorTopic = {
			idtopic : 0,
			fo100 : 0,
			checkChangeImg : false,
			init : function(){
				administratorTopic.eventListener();
				administratorTopic.dialogSaveTopic = $('#dialog-bonevo-topic-edit').dialog({
					 autoOpen : false,
					 modal: true,  
					 resizable: false,
					 closeOnEscape: true,
					 draggable: false,
					 width : 500,
					 height : 200,
					 position: {
				         my: 'center', 
				         at: 'center'
					 },
					 show : 'fade',
					 hide : 'fade',
				     buttons : {
				    	 "Ok" : {
				    		 click :  function(){
				    				 administratorTopic.saveAdministratorTopic();
				    		 },text : "Ok",
				    		 class : 'btn-ok-confirm'
				    	 }
				     }
				});
			},
	eventListener : function() {
		$(document).on('click','.qb-administrator-topic table.table tr td:nth-child(5)',function(){
			
			administratorTopic.idtopic=($(this).children().attr("idtopic"));
			administratorTopic.fo100=($(this).children().attr("fo100"));
			$('#mv201-input').val($(this).closest("tr").find("td:nth-child(1)").html());
			$('#mv204-input').val($(this).closest("tr").find("td:nth-child(4)").html());
			var image = 'url("'+($(this).closest("tr").find("td:nth-child(2)").children().attr("src"))+'")';
			$('.dialog-save-topic-subcontent .image-topic').css('background-image',image);
			administratorTopic.dialogSaveTopic.dialog("open");
			
		});
		
		$(document).on('click','.dialog-save-topic-subcontent .image-topic',function(){
			$('#file-update-topic-bonevo').trigger('click');
		});
		
		$('#file-update-topic-bonevo').on("change", function(evt){
			administratorTopic.chooseFileTemplate(evt, $('file-update-topic-bonevo'),$('.dialog-save-topic-subcontent .image-topic'),0);
		});
		
	},
	loadAdminstratorTopic : function(){
		$.ajax({
			url : encodeUrl("adminAccountBean.loadListM200"),
			success: function(response){
				var html = '';
				var data = response.result;
				for (var i = 0; i< data.length; i++){
					html+=		'<tr>';
					html+=			'<td>'+data[i].mv201+'</td>';
					html+=			'<td><img src="'+data[i].mv202+'" style="width:128px;height:128px;"></td>';
					html+=			'<td>'+data[i].mv203+'</td>';
					html+=			'<td>'+data[i].mv204+'</td>';
					html+=			'<td><a idtopic="'+data[i].id+'" fo100="'+data[i].fo100+'">Edit</a></td>';
					html+=		'</tr>';
				}
				$('.data').html(html);
			}
		})
	},
	saveAdministratorTopic: function(){
	
		var id = (administratorTopic.idtopic);
		var fo100 = (administratorTopic.fo100);
		var img = $('.dialog-save-topic-subcontent .image-topic').css('background-image');
		img = img.replace('url(','').replace(')','');

        var formData = new FormData();
        formData.append('mv201',$('#mv201-input').val());
        formData.append('mv204',$('#mv204-input').val());
        formData.append('id',id); 
        formData.append('fo100',fo100);
        if(administratorTopic.checkChangeImg){
        	formData.append('mv202Base',img);
		}
        $.ajax({
        	type:"POST",
        	url: encodeUrl("adminAccountBean.saveM200"),
        	processData: false,
			contentType: false,
			data : formData,
			success : function(response){
				var result = response.result;
				if(result==1)
				{
					administratorTopic.checkChangeImg = false;
					administratorTopic.dialogSaveTopic.dialog("close");
					administratorTopic.loadAdminstratorTopic();
				}
				else
				{
					util.messageDialog("Upload image fail !");

				}
			}
        })

	},
	chooseFileTemplate : function(evt,thisElemnt,element,type) {
		var files = evt.target.files;
		var file = files[0];
		if (files && file) {
			var reader = new FileReader();
			reader.onload = function(readerEvt) {
				var binaryString = readerEvt.target.result;
				administratorTopic.checkChangeImg = true;
				element.css("background-image", "url('data:image/png;base64," + btoa(binaryString) + "')");
				thisElemnt.attr("base64",btoa(binaryString));
			};
			reader.readAsBinaryString(file);
		}
	}
}}());