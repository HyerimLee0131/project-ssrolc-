$(function(){
	$.extend({
		//쓰기, 수정
		popupWrite:function(){
			var pName = $.trim($('#pName').val());
			var pState = $.trim($('#pState').val());
			var startDate = $.trim($('#startDate').val());
			var endDate = $.trim($('#endDate').val());
			var pSize_width = $.trim($('#pSize_width').val());
			var pSize_height = $.trim($('#pSize_height').val());
			var location_top = $.trim($('#location_top').val());
			var location_left = $.trim($('#location_left').val());
			var pPopup_id = $.trim($('#pPopup_id').val());
			var pFileName = $.trim($('#pFileName').val());

			if(pName == ""){
				alert("팝업명을 입력해주세요.");
				return;
			}

			if(startDate == "" || endDate == ""){
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
			if(location_top == ""){
				$('#location_top').val("10");
			}else{
				if(!$.numeric(location_top)){
					alert("숫자만 입력해주세요.");
					return;
				}
			}

			//기본값 10
			if(location_left == ""){
				$('#location_left').val("10");
			}else{
				if(!$.numeric(location_left)){
					alert("숫자만 입력해주세요.");
					return false;
				}
			}

			if(pPopup_id == ""){
				alert("팝업ID를 입력해주세요.");
				return;
			}

			if(pFileName == ""){
				alert("팝업 파일명을 입력해주세요.");
				return;
			}

			alert('ok');
			return;
			//$('#popupFrm').submit();
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

	//리스트 검색 리셋 버튼
	$('#resetBtn').on('click',function(){
		location.href = "/ssrolcmanager/popups";
	});
});