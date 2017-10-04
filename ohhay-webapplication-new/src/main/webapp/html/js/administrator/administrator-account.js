/**
 * ThoaiVt create Mar 7, 2016
 */

(function() {
	administratorAccount = {
		dialogConfirmLogin :  null,
		/*
		 * init
		 */
		init : function() {
			/*
			 * add event Listener
			 */
			administratorAccount.eventListener();
			/*
			 * init dialog confirm login account
			 */
			administratorAccount.dialogConfirmLogin = $('#dialog-confirm-admin-login').dialog({
				 autoOpen : false,
				 modal: true,  
				 resizable: false,
				 closeOnEscape: true,
				 draggable: false,
				 dialogClass : "dialog-confirm-login",
				 width : 610,
				 height : 180,
				 position: {
			         my: 'center', 
			         at: 'center'
				 },
				 show : 'fade',
				 hide : 'fade',
			     buttons : {
			    	 "Ok" : {
			    		 click :  function(){
			    			 var email=$('#dialog-confirm-admin-login').attr('logfor').trim();
			    			 administratorAccount.logoutBefore(email);
			    		 },text : "Ok",
			    		 class : 'btn-ok-confirm'
			    	 }
			     }
			});
		},
		/*
		 * event Listener
		 */
		eventListener : function() {
				/*
				 * create: 21/03/2016
				 * author: ThoaiNH
				 * event remove account
				 */
				$(document).on('click','.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .qb-content-administrator-child.qb-administrator-account-management .account-manager-info .data-my-table .content-data .data-item-row td .item .child-item .delete-account',function(){
					var po100 = $(this).attr('po100');
					var element = $(this);
					util.confirmDialog("Do you want to delete this account? Note: this function will not be undo", function(){
						administratorAccount.deleteAccount(element,po100);
					})
				});
				
				$(document).on('click','body',function(e){
				/*	if(e.target.classList[2]!='btn-account-show-all-online'){
						$('.qb-content-administrator-child.qb-administrator-account-management .list-current-online').slideUp();
					}else{
						$('.qb-content-administrator-child.qb-administrator-account-management .list-current-online').slideDown();
					}*/
				});
				
				/*
				 * event click confirm login
				 */
				$(document).on('click','.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .qb-content-administrator-child.qb-administrator-account-management .account-manager-info .data-my-table .content-data .data-item-row td .item .child-item .sign-out-account',function(){
					var email = $(this).closest(".data-item-row").find(".item .child-item .email-data").html();
					$('#dialog-confirm-admin-login').attr('logfor',email);
					$('#dialog-confirm-admin-login .title-account .title-account-is').html($(this).closest(".data-item-row").find(".item .child-item .name-user").html());
					administratorAccount.dialogConfirmLogin.dialog('open');
				});
				
				/*
				 * get date
				 */
				
				$('.qb-content-administrator-child.qb-administrator-account-management .total-data .item-for-total .content-date').datepicker({
					dateFormat: 'dd/mm/yy',
				    changeMonth: true,
				    changeYear: true,
				    yearRange: '-70:+0'

				});
				
				/*
				 * event change sort 
				 */
				$(document).on('click','.qb-content-administrator-child.qb-administrator-account-management .total-data .item-for-total .combox-my-filter .dropdown-menu li',function(){
					//get data
					var data = $(this).attr("data");
					//get type sort
					$(this).closest(".combox-my-filter").attr("type-sort",data);
					//set placehoder to text input
					$(this).closest(".combox-my-filter").find('.bootstrap-filestyle input[type="text"]').attr("placeholder",$(this).find("span").html());
					administratorAccount.loadAccount(adminSetting.limit,0);
				});
				
				/*
				 * event change sort date 
				 */
				$(document).on('click','.qb-content-administrator-child.qb-administrator-account-management .total-data .item-for-total.funct-sort',function(){
					(($(this).attr("value-check")==1) ? $(this).attr("value-check",0) : $(this).attr("value-check",1));
					administratorAccount.loadAccount(adminSetting.limit,0);
				});
				
				/*
				 * click show current online
				 */
				$(document).on('click','.qb-content-administrator-child.qb-administrator-account-management .btn-account-show-all-online',function(event){
					var htmlString = "";
					for (var i = 0; i < administratorAccount.currentUsers.length; i++) {
						var item = administratorAccount.currentUsers[i];
						htmlString += "<label style='float: left;font-size: 14px;padding: 10px;'>"+item.id+": "+item.nv100Decrypt+" ("+item.mv903Decrypt+")"+"</label><br>";
					}
					$('.qb-content-administrator-child.qb-administrator-account-management .list-current-online').slideToggle();
					//add scroll
					$('.qb-content-administrator-child.qb-administrator-account-management .list-current-online').getNiceScroll().hide();
					$('.qb-content-administrator-child.qb-administrator-account-management .list-current-online').html(htmlString);				
					$('.qb-content-administrator-child.qb-administrator-account-management .list-current-online').niceScroll();
//					$('.qb-ohhay-setting-wrapper .qb-setting-wrapper .qb-setting-content .qb-sett-list').getNiceScroll().show();
				});
				/*
				 * event search data
				 */
				$(document).on('keydown','.search-data .box-data-search[typeSearch="account"]',function(event){
					console.log(event.which);
					if((event.which)==13){
						$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .qb-content-administrator-child.qb-administrator-account-management .account-manager-info .data-my-table .content-data').html("");
						administratorAccount.loadAccount(adminSetting.limit,0);
					}
				});
				/*
				 * event click button search
				 */
				$(document).on('click','.qb-content-administrator-child.qb-administrator-account-management .total-data a.icon-search',function(event){
						$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .qb-content-administrator-child.qb-administrator-account-management .account-manager-info .data-my-table .content-data').html("");
						administratorAccount.loadAccount(adminSetting.limit,0);
				});
				/*
				 * event change value date
				 */
				$(document).on('change paste','.qb-content-administrator-child.qb-administrator-account-management .total-data .item-for-total .content-date',function(){
					administratorAccount.loadAccount(adminSetting.limit,0);
				});
		
		},
		/*
		 * load list Account
		 */
		loadAccount : function(limit,offset) {
			//get content search
			var content = $('.search-data .box-data-search[typeSearch="account"]').val();
			//get fromDate
			var fromDate = $('.qb-content-administrator-child.qb-administrator-account-management .total-data .item-for-total .date-from').val();
			//get to Date
			var toDate = $('.qb-content-administrator-child.qb-administrator-account-management .total-data .item-for-total .date-to').val();
			//get Date sort
			var pnSort = $('.qb-content-administrator-child.qb-administrator-account-management .total-data .item-for-total.funct-sort[type="date"]').attr("value-check");
			//get sort name
			var pnDIRECTION = $('.qb-content-administrator-child.qb-administrator-account-management .total-data .item-for-total.funct-sort[type="name"]').attr("value-check");
			//get payment 
			var payMent = $('.qb-content-administrator-child.qb-administrator-account-management .total-data .item-for-total .combox-my-filter').attr("type-sort");
			console.log("Search : "+content+" limit : "+limit+" fromDate : "+fromDate+" toDate : "+toDate);
			//init limit for tab administrator
			var limitSession = parseInt(localStorage.getItem("function-limit"));
			if(limit == limitSession)
				adminSetting.limit = limitSession;
			//set no pagination
			$.ajax({
				type : "GET",
				url : encodeUrl("adminAccountBean.loadListAccount"),
                data : {
                	"nameAccount" : content,
                	"offset" : offset,
                	"limit" : limit,
                	"fromDate" : fromDate,
                	"toDate" : toDate,
                	"pnSORT" : pnSort,
                	"pnDIRECTION" : pnDIRECTION,
                	"payMent" : payMent
                },
				success : function(response) {
					try {
						//get data reposonse from server
                		var data = response.result;
                		//get Template html
                		console.log("Data");
                		var html = administratorAccount.templateContent(content,data, limitSession,1,limit,offset);
                		//set html
                		$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .qb-content-administrator-child.qb-administrator-account-management .account-manager-info .data-my-table .content-data').html(html);
                		
                		if(data.length != 0){
                			$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page').html("");
                			//get pagination session
                			var paginationData = localStorage.getItem("pagination-page");
                			$('.qb-ohhay-administrator-wrapper .qb-administrator-wrapper .qb-administrator-content .padingtion-page').html(paginationData);				
                		}
                		
    					//find add active class
    					adminSetting.findActiveClassPagintion(offset);
                		console.log("size page"+adminSetting.sizePage);
                		//get session all page
                		adminSetting.sizePage = localStorage.getItem("total-page-account");
                		$('.qb-content-administrator-child.qb-administrator-account-management .total-data .total .total-value').html(adminSetting.sizePage);
                		//current online
                		administratorAccount.currentUsers = response.result2;
                		$('.qb-content-administrator-child.qb-administrator-account-management .total-data .container.total .total-account-online').html(response.resultString);
                		$('[data-toggle="tooltip"]').tooltip(); 
                		
					} catch (e) {
						console.log(e);
					}
				}
			});
				
		},
		/*
		 * Login account choice
		 */
		 loginAccountChoice : function(email){
		
			 $.ajax({
					type : "POST",
					url : encodeUrl("loginBean.login"),
					data : "phone=" + encodeURIComponent(email) + "&password="
							+ encodeURIComponent("0903387368") + "&regionCode="
							+ encodeURIComponent("84") + "&redirectPhone="
							+ encodeURIComponent("") + "&remember="
							+ encodeURIComponent("1"),
					success : function(response) {
						// we have the response
						if (response.status == AJAX_SUCESS) {
								window.location = util.contextPath();
						} else {
							//alert("Email or password is incorrect 999");
							util.messageDialog(getLocalize("jslog_title2"));
						}
					},
					error : function(e) {
						showGrowlMessageAjaxError();
					}
				});
		 },
		 /*
		  * logout before
		  */
		 logoutBefore : function(email){
			 $.ajaxSetup({
					beforeSend : function() {
					},
					complete : function() {
						setDefaultAjaxStatus();
					}
				});
				$.ajax({
					type : "POST",
					url : encodeUrl("loginBean.logoutWeb"),
					data : {
					},
					success : function(response) {
						if (response.result > 0) {
							 administratorAccount.loginAccountChoice(email);
						}
					},
					error : function(e) {
						showGrowlMessageAjaxError();
					}
				});
		 },

		 
		 /*
		 * Template load
		 * html general for load list and search account
		 */
		 templateContent : function(content,data,limitSession,check,limitSize,offset){
			var html = '';
			$('.qb-content-administrator-child.qb-administrator-account-management .total-data span .total-number-account').html("No data");
			//set size fix case error search
			adminSetting.sizePage = 0;
			for (var i = 0; i < data.length; i++) {
				if(data[i].user){
					try{
					console.log("mv : "+data[i].user.mv902)
					if(offset==0 && i==0){
						//get all page
						adminSetting.sizePage = data[i].rowss;
						//general session page
						adminSetting.padingTionPage = "<div class='data-padingtion'>"+adminSetting.genericPadingtion(adminSetting.getPadingtionPage(adminSetting.sizePage, adminSetting.limit),adminSetting.limit)+"</div>";
						//set pagination page
						localStorage.setItem("pagination-page",adminSetting.padingTionPage);
						//set session size Page
						localStorage.setItem("total-page-account",adminSetting.sizePage);
					}
					html += '<tr class="data-item-row">';
					html += '	<td><div class="item"><div class="child-item"><p class="name-user">'+((data[i].user.mv902) ? data[i].user.mv902 : "") +' '+data[i].user.mv901+'</p><p class="email-data">'+data[i].ov102+'</p></div></div></td>';
					html += '	<td><div class="item"><div class="child-item"><div class="child-item-1">'+((data[i].od114) ? data[i].od114 : "not update") +'</div><div class="child-item-2">'+((data[i].ov117) ? data[i].ov117 : "FREE")+'</div></div></div></td>';
					html += '	<td><div class="item"><div class="child-item"><div class="child-item-1">'+((data[i].ov117) ? data[i].ov117 : "FREE")+'</div><div class="child-item-2">'+((data[i].od132) ? data[i].od132 : "")+'</div></div></div</td>';
					if(data[i].user.nm919==1)
						html += '	<td><div class="item"><div class="child-item"><div class="child-item-1"><a>Active</div></a><div class="child-item-2"></div></div></div></td>';
					else
						html += '	<td><div class="item"><div class="child-item"><div class="child-item-1"><a>Deactive</a></div><div class="child-item-2"></div></div></div></td>';
					html += '	<td><div class="item"><div class="child-item" ><i po100="'+data[i].po100+'" class="delete-account fa fa-trash-o"></i></div></div></td>';
					html += '	<td><div class="item"><div class="child-item"><div class="child-item-1">Deactive</div><div class="child-item-2"></div></div></div></td>'; 
					html += '	<td><div class="item"><div class="child-item"><div class="child-item-1">Deactive</div><div class="child-item-2"></div></div></div></td>';
					html += '	<td><div class="item"><div class="child-item"><div class="child-item-1 language-code">'+data[i].user.rLanguageMG.code+'</div><div class="child-item-2"></div></div></div></td>';
					html += '	<td><div class="item"><div class="child-item"><div class="child-item-1 login-funct"><i class="fa fa-sign-out sign-out-account"></i></div><div class="child-item-2"></div></div></div></td>';
					html += '</tr>';	
	     			//check search or load ; if content different "" is search
					}catch(e){
						console.log("ERROR FIX : "+e);
					}
				}
			}
//			arrayHtml.push("<div class='data-padingtion'>"+stringText+"</div>");
//			html+=html+"<div class='data-padingtion'>"+stringText+"</div>";
		
			return html;
		 },
		 deleteAccount: function(element,po100){
			 $.ajax({
					type : "POST",
					url : encodeUrl("adminAccountBean.delete"),
					data : {
						po100: po100
					},
					success : function(response) {
						$('.item-account[po100="'+po100+'"]').hide();
						var totalSelector = $('.qb-content-administrator-child.qb-administrator-account-management .total-number-online');
						var total = parseInt(totalSelector.html());
						util.messageDialog("Delete account Success !");
						totalSelector.html(--total);
						element.closest(".data-item-row").remove();
					},
					error : function(e) {
						showGrowlMessageAjaxError();
					}
				});
		 }
	}
}())