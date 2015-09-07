/**
 * 사용자 franchise.js
 */
$(function() {
	$.extend({
		joinWrite:function(){
			if($.trim($('#jslcName').val())==""){
				alert('이름을 입력해주세요.');
				$('#jslcName').focus();
				return false;
			}

			if($.trim($('#mb_zip1').val())==""){
				alert('우편번호를 입력해주세요.');
				return false;
			}
			if($.trim($('#mb_addr2').val())==""){
				alert('상세주소를 입력해주세요.');
				return false;
			}
			//휴대폰 번호 체크
			var formTel01 = $('#pPhone01').val();
			var formTel02 = $.trim($('#pPhone02').val());
			var formTel03 = $.trim($('#pPhone03').val());
			
			if(formTel01 == "select" || formTel02 == "" || formTel03 == ""){
				alert('휴대전화를 정확히 입력해주세요.');
				return false;
			}else{
				if(!$.numeric(formTel02) || !$.numeric(formTel03)){
					alert('휴대전화에 숫자만 입력해주세요.');
					return false;
				}
			}

			var jslcType = $('input[name="jslcType"]:checked').val();

			if(typeof(jslcType) == "undefined" || jslcType == ""){
				alert("가맹교실유형을 선택해주세요.");
				return false;
			}else{
				if(jslcType == "교습소"){
					if($('#jslcSubject').val()=="select"){
						alert("희망과목을 선택해주세요.");
						return false;
					}
				}
			}

			if($('#hopeArea01').val() == "select" || $('#hopeArea02').val() == "select"){
				alert('가맹희망지역을 선택해주세요.');
				return false;
			}

			if($.trim($('#joinHopeDate').val()) == ""){
				alert('가맹희망일을 선택해주세요.');
				return false;
			}

			if($('#pTime01').val()=="select" || $('#pTime02').val()=="select"  ){
				alert("희망상담시간을 선택해주세요.");
				return false;
			}
			if($.trim($('#pContents').val())==""){
				alert('상담내용을 입력해주세요.');
				$('#pContents').focus();
				return false;
			}

			if($('#pCheck').is(":checked")==false){
				alert('개인정보 항목 및 수집방법 설명 동의체크를 해주세요.');
				return false;
			}
			$('#fwrite').submit();
		},
		setAddr:function(formPost,addressDtl01){
			$('#formPost').val(formPost);
			$('#addressDtl01').val(addressDtl01);
		},
		checkDeptType:function(){
			var jslcType = $('input[name="jslcType"]:checked').val();
			if(jslcType == "교습소"){
				$('#jslcSubject').removeAttr('disabled');
			}else{
				$('#jslcSubject').attr('disabled','disabled');
			}
		},
		numeric:function(str){
			var pattern = /(^[0-9]+$)/;
			if(!pattern.test(str)){
			    return false;
			}
			return true;
		}
	});
	

	//달력버튼
	$("#joinHopeDate").on('click',function() {
		$("#joinHopeDate").datepicker( "show" );
	});

	$("#joinCheck").on('click',function(){
		if($("#joinCheck").is(":checked")){
			$("#jslcounseling").val("1");
		}else {
			$("#jslcounseling").val("0");
		}
	});
	// 가맹희망지역 선택
	$('#hopeArea01').on('change',function(){
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
			url:"/ssrolcmanager/franchise/deptArea",
			type:"GET",
			cache: false,
			async: true,
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
	});
});
