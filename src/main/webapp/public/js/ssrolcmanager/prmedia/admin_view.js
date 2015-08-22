
$(function() {
	$.extend({
		prmediaDelete:function(aidx){
			
			if(!confirm("삭제하시겠습니까?")) return;
			
			var targetUrl = "/ssrolcmanager/prmedias/delete/"+aidx;
			$.ajax({
				url:targetUrl,
				type:"delete",
				cache: false,
				async: true,
				success: function(jsonData, textStatus, XMLHttpRequest) {
					//alert("해당 글이 삭제 되었습니다.");
					location.href="/ssrolcmanager/prmedias";
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}
	});
	
});