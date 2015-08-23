
$(function() {
	$.extend({
		boardDelete:function(boardTable,articleNo){
			if(!confirm("한번 삭제한 자료는 복구할 방법이 없습니다.\n\n정말 삭제하시겠습니까?")){
				return;
			} 			
			
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