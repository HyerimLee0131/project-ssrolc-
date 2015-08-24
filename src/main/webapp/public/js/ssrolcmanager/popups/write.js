$(function(){
	$.extend({
		//쓰기, 수정
		popupWrite:function(){
			// 에디터의 내용을 에디터 생성시에 사용했던 textarea에 넣어 줍니다.
			if(CrossEditor.GetTextValue () == ""){
				alert("내용을 입력해 주세요!");
				CrossEditor.SetFocusEditor(); // 크로스에디터 Focus 이동
				return;
			}else{
				document.getElementById("pContent").value  = CrossEditor.GetBodyValue();
			}

			var pName = $.trim($('#pName').val());
			var pState = $.trim($('#pState').val());
			var pStartDate = $.trim($('#pStartDate').val());
			var pEndDate = $.trim($('#pEndDate').val());
			var pSize_width = $.trim($('#pSize_width').val());
			var pSize_height = $.trim($('#pSize_height').val());
			var pLocation_top = $.trim($('#pLocation_top').val());
			var pLocation_left = $.trim($('#pLocation_left').val());
			var pPopup_id = $.trim($('#pPopup_id').val());
			
			var pFileName = $.trim($('#pFileName').val());

			if(pName == ""){
				alert("팝업명을 입력해주세요.");
				return;
			}

			if(pStartDate == "" || pEndDate == ""){
				alert("게재기간을 선택해주세요.");
				return;
			}

			if(pSize_width == "" || pSize_height == ""){
				alert("팝업사이즈를 입력해주세요.");
				return;
			}else{
				if(!$.numeric(pSize_width) || !$.numeric(pSize_height)){
					alert("숫자만 입력해주세요.");
					return;
				}
			}

			//기본값 10
			if(pLocation_top == ""){
				$('#pLocation_top').val("10");
			}else{
				if(!$.numeric(pLocation_top)){
					alert("숫자만 입력해주세요.");
					return;
				}
			}

			//기본값 10
			if(pLocation_left == ""){
				$('#pLocation_left').val("10");
			}else{
				if(!$.numeric(pLocation_left)){
					alert("숫자만 입력해주세요.");
					return;
				}
			}

			if(pPopup_id == ""){
				alert("팝업ID를 입력해주세요.");
				return;
			}
/*
			if(pFileName == ""){
				alert("팝업 파일명을 입력해주세요.");
				return;
			}
*/
			$('#popupFrm').submit();
		},

		//숫자인지검사
		numeric:function(str){
			var pattern = /(^[0-9]+$)/;
			if(!pattern.test(str)){
			    return false;
			}
			return true;
		}
		
	});
});