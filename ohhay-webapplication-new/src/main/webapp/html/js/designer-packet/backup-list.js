/**
 * @author ThoaiNH 
 * create Mar 04, 2016
 * designer event on designer packet module
 */
(function() {
	backUpList = {
		fe400Cus: 0,
		pe150: 0,
		/*
		 * on click show backup list
		 */
		eventListener: function(){
			$(document).on('click','#qb-dlg-list-backup .btn-create-backup', function(){
				backUpList.backupVersion(backUpList.fe400Cus);
			});
			$(document).on('click','#qb-dlg-list-backup .btn-delete-backup', function(){
				var pe400 = $(this).parents('tr').attr("siteId");
				util.confirmDialog(getLocalize("jsstd_title1"), function() {
					$.ajax({
						type : "POST",
						url : encodeUrl("mysitesBean.remove"),
						data : {
							pe400 : pe400
						},
						success : function(response) {
							if(response.result > 0)
								backUpList.loadListBackup(backUpList.fe400Cus);
							else
								showGrowlMessageError();
						},
						error : function(e) {
							showGrowlMessageAjaxError();
						}
					});
				});
			});
			$(document).on('click','#qb-dlg-list-backup .btn-submit-to-cus', function(){
				var pe400 = $(this).parents('tr').attr("siteId");
				backUpList.submitToCustomer(backUpList.pe150,backUpList.fe400Cus, pe400);
			});
		},
		showBackupList: function(fe400, pe150){
			backUpList.fe400Cus = fe400;
			backUpList.pe150 = pe150;
			//init when first call
			if(!this.inited)
			{
				$('#qb-dlg-list-backup').webDialog(700);
				this.inited = true;
				this.eventListener();
			}
			$('#qb-dlg-list-backup').dialog('close');
			$('#qb-dlg-list-backup').dialog('open');
			this.loadListBackup(fe400);
		},
		/*
		 * load backup
		 */
		loadListBackup : function(fe400Cus) {
			$.ajax({
				url : encodeUrl("designerBean.loadListBackup"),
				data : {
					fe400Cus:fe400Cus
				},
				success : function(response) {
					var htmlString = "";
					if (response.result && response.result.length > 0) {
						for (index = response.result.length - 1; index >= 0; index--) {
							var item = response.result[index];
							var stt = "New";
							if(item.ev406 == "PENDING")
								stt = "Pending";
							else if(item.ev406 == "APPROVED")
								stt = "Approved";
							htmlString+='<tr siteId="'+item.id+'">';
							htmlString+='	<td><a href="'+util.contextPath()+'/'+item.ev405+'" target="_bank">'+util.contextPath()+'/'+item.ev405+'</a></td>';
							htmlString+='	<td>'+item.el448String+'</td>';		
							htmlString+='	<td>';
							htmlString+='		<a href="'+util.contextPath()+ "/e"+ item.id +"/evo-editer"+ '?editmode=element'+'" target="_bank"><span title="Edit this site" data-toggle="tooltip" data-placement="top" class="item fa fa-pencil hvr-shrink"></span></a>';
							htmlString+='		<span title="Delete" data-toggle="tooltip" data-placement="top" class="item fa fa-trash-o hvr-shrink btn-delete-backup"></span>';
							if(stt == "New")
								htmlString+='		<span title="Submit to customer" data-toggle="tooltip" data-placement="top" class="item fa fa-share hvr-shrink btn-submit-to-cus"></span>';
							htmlString+='	</td>';
							htmlString+='	<td>'+stt+'</td>';
							htmlString+='</tr>';
						}
						$('#qb-dlg-list-backup tbody').html(htmlString);
					}
					else {
						var nodata = '<span class="no-data-found">'+ getLocalize('no_data') + '</span>';
						$('#qb-dlg-list-backup tbody').html(nodata);
					}
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * create backup version
		 */
		backupVersion : function(fe400Cus) {
			$.ajax({
				type : "POST",
				url : encodeUrl("designerBean.backupVersion"),
				data : {
					fe400Cus: fe400Cus
				},
				success : function(response) {
					if(response.result > 0)
					{
						showGrowlMessageSuccess();
						backUpList.loadListBackup(backUpList.fe400Cus);
					}
					else
						showGrowlMessageError();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
		/*
		 * submit to customer
		 */
		submitToCustomer : function(pe150, fe400Cus, fe400Backup) {
			$.ajax({
				type : "POST",
				cache: false,
				url : encodeUrl("designerBean.submitToCustomer"),
				data : {
					pe150: pe150,
					fe400Cus: fe400Cus,
					fe400Backup: fe400Backup
				},
				success : function(response) {
					if(response.result > 0)
					{
						showGrowlMessageSuccess();
						$('#qb-dlg-list-backup').dialog('close');
						designerSite.loadListWebOfCus(designerSite.pe100);
					}
					else
						showGrowlMessageError();
				},
				error : function(e) {
					showGrowlMessageAjaxError();
				}
			});
		},
	}
}());