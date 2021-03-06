
$(function() {
	$.extend({
	
		deleteFile:function(index,atticleFileNo){
			if(confirm("해당 파일을 정말 삭제하시겠습니까?")){
				$("#fileDiv"+index).empty();
				$("#fileDiv"+index).append('<input name="attachFile" type="file" class="attachFile" size="20"/>');
				$("#deleteAttachFiles").val(atticleFileNo);
			}
		}
		
	})
	
	
	$('#submitBtn').on('click',function(){
		// 에디터의 내용을 에디터 생성시에 사용했던 textarea에 넣어 줍니다.
		if(CrossEditor.GetTextValue () == ""){
			alert("내용을 입력해 주세요!");
			CrossEditor.SetFocusEditor(); // 크로스에디터 Focus 이동
			return false;
		}else{
			document.getElementById("boardContent").value  = CrossEditor.GetBodyValue();
		}
							
		var boardTitle = $.trim($("#boardTitle").val());
		var boardTitleLen = boardTitle.length;
		var boardContent = $.trim($("#boardContent").val());
		
		if(boardTitle == ""){
			alert("제목을 입력해주세요");
			return false;
		}
		
		if(boardTitleLen > 100){
			alert("제목을 100자 이내로 입력해주세요.");
			return false;
		}
		
		$("#boardFrm").submit();
	});
});