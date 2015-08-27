/**
 * 사용자 disclosure.js
 */
$(function() {
	$.extend({
		emailAuthSubmit:function(){
				var name = $('#pMemName').val();
			 	var id = $('#pEmailId').val();
			 	var email1 = $('#pEmailAdd1').val();
				var email = id+"@"+ $('#pEmailAdd1').val();
				var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
				
				if(name ==""){
					alert('이름을 입력해주세요.');
					$('#pMemName').focus();
					return;
				}
				
				if(id ==""){
					alert('이메일 아이디를 입력해주세요.');
					$('#pEmailId').focus();
					return;
				}
				
				if(email1 ==""){
					alert('이메일 주소를 입력해주세요.');
					$('#pEmailAdd1').focus();
					return;
				}
				
				if(regex.test(email) === false) {  
				    alert("잘못된 이메일 형식입니다.");  
				    return;  
				}
		
				var inputData = {"pMemName":name,"pEmailId":id,"pEmailAdd1":email1};
			
				$.ajax({
				type :"POST",
				url  :"/disclosure/mail",
				data: inputData,
				cache: false,
				async: true,
				dataType: "text",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					 alert('이메일이 발송되었습니다.');
				},
				error:function (xhr, ajaxOptions, thrownError){
					alert(thrownError);
				}
			});
		},
		
		AuthKeyChk:function(){
			var inputAuthKey = $('#pAuthCode').val();
			alert(inputAuthKey);
			
		}
		
	});
	
	//레이어팝업열기
	$('.btn_info_read').on("click",function(e){
		var agreeChk = $('input[name="agreeChk"]:checked').val();
		if(agreeChk=="1"){
			$('.layer_pop_wrap').css('display','block');
		}else{
			alert("개인정보수집 및 이용에 동의체크를 눌러주세요");
		}
		e.preventDefault();
	});
	//레이어팝업닫기
	$('.btn_pop_close').on("click",function(){
		$('.layer_pop_wrap').css('display','none');
	});
	//팝업오픈 openPopup(경로, 넓이, 높이, top, left)
	function openPopup(path, width, height, top, left){
		window.open(path, "","width="+width+",height="+height+",top="+top+",left="+left+",noresizable,toolbar=no,status=no,scrollbars=yes,directory=no");
	}
	
	
	//메일입력
	$("#pEmailAdd2").change(function(){
		var email2 = $('#pEmailAdd2').val();
		if(email2 == "inputEmail"){
			$('#pEmailAdd1').val('');
			$('#pEmailAdd1').removeAttr('readonly');
		}else if(email2 == "select"){
			$('#pEmailAdd1').val('');
			$('#pEmailAdd1').attr('readonly','readonly');
		}else{
			$('#pEmailAdd1').val(email2);
			$('#pEmailAdd1').attr('readonly','readonly');	
		}	
	});
	


});
