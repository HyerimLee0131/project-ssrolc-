
$(function() {
	$.extend({
		boardDelete:function(boardTable,articleNo){
			var targetUrl = "/ssrolcmanager/boards/"+boardTable+"/"+articleNo;
			$.ajax({
				url:targetUrl,
				type:"delete",
				cache: false,
				async: true,
				success: function(jsonData, textStatus, XMLHttpRequest) {
					alert("해당 글이 삭제 되었습니다.");
					location.href="/ssrolcmanager/boards/"+boardTable;
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}
	});
	
});