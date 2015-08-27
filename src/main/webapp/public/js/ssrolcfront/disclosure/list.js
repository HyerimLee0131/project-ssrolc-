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
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					if(jsonData.result=="MailSendOk"){ 
						alert('이메일이 발송되었습니다.');
					}
				},
				error:function (xhr, ajaxOptions, thrownError){
					alert(thrownError);
				}
			});
		},
		
		AuthKeyChk:function(){
		
			var inputAuthKey = $('#pAuthCode').val();
			var name = $('#pMemName').val();
			var email = $('#pEmailId').val()+"@"+ $('#pEmailAdd1').val();
			var inputData = {"authKey":inputAuthKey,"memName":name,"email":email};
			
			if(inputAuthKey ==""){
				alert('인증번호를 입력하지 않았습니다.다시입력해주세요');
				$('#pAuthCode').focus();
				return;
			}
			$.ajax({
				type :"GET",
				url  :"/disclosure/mail",
				data: inputData,
				cache: false,
				async: true,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					if(jsonData.result=="authKeyOk"){ 
						alert('인증번호가 일치합니다.');
						$("#emailOk").show();
						$("#emailAuthYN").val("Y");
					}else if(jsonData.result=="authKeyFail"){
						alert('인증번호가 일치하지 않습니다. 다시 입력해주세요');
					}else if(jsonData.result=="authKeyNull"){
						alert('인증번호가 일치하지 않습니다. 다시 입력해주세요');
					}
				},
				error:function (xhr, ajaxOptions, thrownError){
					alert(thrownError);
				}
			});
			
		},
		setAddr:function(formPost,formAddr01){
			$('#formPost').val(formPost);
			$('#formAddr01').val(formAddr01);
			
		}
		
	});
	
	//레이어팝업열기
	$('.btn_info_read').on("click",function(e){
		//이메일인증여부
		var authChk = $("#emailAuthYN").val();
		//개인정보수집 동의여부
		var agreeChk = $('input[name="agreeChk"]:checked').val();
		if(authChk !="Y"){
			alert('이메일 인증을 해주세요');
			return;
		}
		if(agreeChk !="1"){
			alert("개인정보수집 및 이용에 동의체크를 눌러주세요");
			return;
		}
		if(authChk=="Y" && agreeChk=="1"){
			$('.layer_pop_wrap').css('display','block');
		}
		
		e.preventDefault();
	});
	//레이어팝업닫기
	$('.btn_pop_close').on("click",function(){
		$('.layer_pop_wrap').css('display','none');
	});


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
