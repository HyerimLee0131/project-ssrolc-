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
				url  :"/ssrolcfront/disclosure/email",
				data: inputData,
				cache: false,
				async: true,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					if(jsonData.result=="mailSend"){ 
						alert('이메일이 발송되었습니다.');
					}else if(jsonData.result=="mailIgnore"){
						alert('수신거부된 이메일입니다.');
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
				url  :"/ssrolcfront/disclosure/email",
				data: inputData,
				cache: false,
				async: true,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					if(jsonData.result=="authKeyOk"){ 
						alert('인증번호가 일치합니다.');
						if(jsonData.disclosure){
							var disclosure = jsonData.disclosure;
							$("#name").val(disclosure.memName);
							$("#email").val(disclosure.email);
							var phoneArray = disclosure.phone.split('-');
							$("#formTel01").val(phoneArray[0]);
							$("#formTel02").val(phoneArray[1]);
							$("#formTel03").val(phoneArray[2]);
							$("#formPost").val(disclosure.zip);
							$("#addressDtl01").val(disclosure.addressDtl01);
							$("#addressDtl02").val(disclosure.addressDtl02);
							$("#hopeArea01").val(disclosure.deptArea1);
							$.getSubArea();

							$("#hopeArea02").val(disclosure.deptArea2);

							if($("#formClass01").val()==disclosure.deptType){
								$("#formClass01").attr('checked','checked');
							}
							if($("#formClass02").val()==disclosure.deptType){
								$("#formClass02").attr('checked','checked');
							}
							if($("#formClass03").val()==disclosure.deptType){
								$("#formClass03").attr('checked','checked');
							}
						}
						$("#emailOk").show();
						$("#emailAuthYN").val("Y");
					}else{
						alert('인증번호가 일치하지 않습니다. 다시 입력해주세요');
					}
				},
				error:function (xhr, ajaxOptions, thrownError){
					alert(thrownError);
				}
			});
			
		},
		setAddr:function(formPost,addressDtl01){
			$('#formPost').val(formPost);
			$('#addressDtl01').val(addressDtl01);
			$('#addressDtl01').attr('readonly','readonly');
		},
		joinWrite:function(){

			//개인정보 동의 체크
			if($('input[name="infoAgree"]:checked').val()=="N"){
		        alert("개인정보 동의를 눌러주세요");
		        return false;
			}
			
			//휴대폰 번호 체크
			var Tel01 = $('#formTel01').val();
			var Tel02 = $.trim($('#formTel02').val());
			var Tel03 = $.trim($('#formTel03').val());
			var phone = Tel01+"-"+Tel02+"-"+Tel03;
			$('#phone').val(phone);
			
			
			if(Tel01 == "select" || Tel02 == "" || Tel03 == ""){
				alert('연락처를 정확히 입력해주세요.');
				return false;
			}else{
				if(!$.numeric(Tel02) || !$.numeric(Tel03)){
					alert('연락처에 숫자만 입력해주세요.');
					$.trim($('#formTel02').val(""));
					$.trim($('#formTel03').val(""));
					return false;
				}
			}
			
			if($.trim($('#formPost').val())==""){
				alert('우편번호를 입력해주세요.');
				return false;
			}
			if($.trim($('#addressDtl02').val())==""){
				alert('상세주소를 입력해주세요.');
				return false;
			}
			
			if($('#hopeArea01').val() == "select" || $('#hopeArea02').val() == "select"){
				alert('가맹희망지역을 선택해주세요.');
				return false;
			}
			
			var deptType = $('input[name="deptType"]:checked').val();
			
			if(typeof(deptType) == "undefined" || deptType == ""){
				alert("가맹교실유형을 선택해주세요.");
				return false;
			}

			if($('input[name="deptType"]:checked').length > 1){
				alert("가맹교실유형을 하나만 선택해주세요.");
				return false;
			}
			
			var inputData = $("#frm2").serialize();
			
			$.ajax({
				url:"/ssrolcfront/disclosure",
				type:"POST",
				cache: false,
				async: false,
				data: inputData,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					window.open(jsonData.ebookUrl,"disclosure","width=730,height=530,resizable");
					location.href="/ssrolcfront/disclosure";
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
			
			//$('#frm2').submit();
		},
		getSubArea:function(){
			var jslcArea1 = $('#hopeArea01').val();
			if(jslcArea1 == 'select'){
				$("#hopeArea02").find('option').each(function(){
					$(this).remove();
				});
				$("#hopeArea02").append("<option value='select'>지역선택</option>");
				return;
				}
			var inputData = {"jslcArea1":jslcArea1};
			$.ajax({
				url:"/ssrolcfront/franchise/deptArea",
				type:"GET",
				cache: false,
				async: false,
				data: inputData,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					$("#hopeArea02").find('option').each(function(){
						$(this).remove();
					});
					$("#hopeArea02").append("<option value='select'>지역선택</option>");
					for(var i=0; i<jsonData.length; i++){
						$("#hopeArea02").append("<option value='"+jsonData[i]+"'>"+jsonData[i]+"</option>");
					}
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
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
		
		var name = $('#pMemName').val();
		var email = $('#pEmailId').val()+"@"+ $('#pEmailAdd1').val();
		$('#name').val(name);
		$('#email').val(email);
		
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
	
	// 가맹희망지역 선택
	$('#hopeArea01').on('change',function(){
		$.getSubArea();
	});
	


});
