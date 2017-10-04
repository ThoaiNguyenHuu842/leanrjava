/**
 * @author ThoaiVt
 * 13/01/2016
 */

(function() {
	embedNew : 0,
	embedCode = {
		initialize : function(){
			//append text default when iframe load
			$('#qb-embedcode-'+embedCode.embedNew+' .qb-component .qb-iframe-embed').load(function(){
				$(this).contents().find('body').append(getLocalize("jsebc_title1"));
				embedCode.eventListener();
			});
		},eventListener : function(){
			
		}
	}
}());