$(document).ready(function() {
	$(function() {
		$.extend({
			prmediaWrite:function(){

				if($.trim($('#prTitle').val()) == "" ){
					alert('제목을 입력해주세요.');
					return false;
				}

				if($.trim($('#makeTime').val()) == "" ){
					alert('제작년월을 입력해주세요.');
					return false;
				}
				/*
				if($.trim($('#thumnailName').val()) == "" ){
					alert('썸네일을 등록해주세요.');
					return false;
				}else{
					var attachFile = $.trim($('#thumnailName').val());
					
					var FileFilter = /\.(jpg|gif|png)$/i;
					
					if(!attachFile.match(FileFilter)){
						$('#thumnailName').val("");
						alert("올바른 썸네일 형식을 올려주세요.(jpg,gif,png)만 허용");
						return false;
					}
				}
*/
				
				if($.trim($('#mediaLinkUrl').val()) == "" ){
					alert('링크를 입력해주세요.');
					return false;
				}

				$('#mediaLinkUrl').val($.trim($('#mediaLinkUrl').val()));

				$('#prmediaFrm').submit();
			},
			// 숫자인지검사
			numeric:function(str){
				var pattern = /(^[0-9]+$)/;
				if(!pattern.test(str)){
				    return false;
				}
				return true;
			},
			
			//썸네일삭제
			deleteFile:function(thumbFileName){
				if(confirm("해당 파일을 정말 삭제하시겠습니까?")){
					$("#fileDiv").empty();
					$("#fileDiv").append('<input name="thumnailName" type="file"  id="thumnailName" title="" />');
					$("#deleteAttachFileNo").val(thumbFileName);
				}
			}
		});


	});
});