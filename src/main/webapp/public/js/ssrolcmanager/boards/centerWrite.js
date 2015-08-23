
$(function() {
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
		var useEnable = $.trim($('#useEnable option:selected').val());
		var etc1 = $.trim($("#etc1").val());
		var etc2 = $.trim($("#etc2").val());

		if(boardTitle == ""){
			alert("제목을 입력해주세요");
			return;
		}

		if(boardTitleLen > 100){
			alert("제목을 100자 이내로 입력해주세요.");
			return;
		}

		if(useEnable == ""){
			alert("상태를 선택해주세요");
			return;
		}

		if(etc1 == ""){
			alert("센터명을 입력해주세요");
			return;
		}

		if(etc2 == ""){
			alert("센터소개를 입력해주세요");
			return;
		}

		$("#boardFrm").submit();
	});
});