$(function() {
		$.extend({
			changePass:function(oldPass,newPass,newPassConfirm){

				var sendData = {"oldPass":oldPass,"newPass":newPass,"newPassConfirm":newPassConfirm};

				$.ajax({
					url : '/ssrolcmanager/edit',
					type : 'put',
					data: sendData,
					cache: false,
					async: true,
					dataType : "json",
					success: function(jsonData, textStatus, XMLHttpRequest) {
							if(jsonData.message){
								alert(jsonData.message);
								$('#oldPass').val("");
								$('#newPass').val("");
								$('#newPassConfirm').val("");
							}else{
								alert("비밀번호 변경이 실패 하였습니다.");
							}
					},
					error:function (xhr, ajaxOptions, thrownError){	
						alert(thrownError);
					}
				});

			}
		});

		$('#passSubmitBtn').on('click',function(){
			var oldPass = $.trim($('#oldPass').val());
			var newPass = $.trim($('#newPass').val());
			var newPassConfirm = $.trim($('#newPassConfirm').val());

			if(oldPass.length < 6 || oldPass.length > 20 || newPass.length < 6 || newPass.length > 20 || newPassConfirm.length < 6 || newPassConfirm.length > 20){
				alert("비밀번호를 6자이상 20자이하로 입력해주세요.");
				return;
			}
			
			if(newPass === newPassConfirm){
				if(!confirm("비밀번호 변경을 하시겠습니까?")){
					return;
				}
				$.changePass(oldPass,newPass,newPassConfirm);
			}else{
				alert('새로 변경하고자 하는 비밀번호와 확인 비밀번호가 틀립니다.\n다시 확인 입력해주세요.');
				$('#newPass').val("");
				$('#newPassConfirm').val("");
				return;
			}

		});

	});