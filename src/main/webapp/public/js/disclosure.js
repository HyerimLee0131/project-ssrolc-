$(function() {
	$.extend({  
		selfPrint:function(){
			$('#printDiv').hide();
			window.print();
			$('#printDiv').show();
		}
	});
});
