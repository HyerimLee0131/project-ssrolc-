$(document).ready(function(){
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
	
	 $("#sendEmail").submit(function(){
		 var id = $('#pEmailId').val();
			var email = id+"@"+ $('#pEmailAdd1').val();
			var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
			
			if(regex.test(email) === false) {  
			    alert("잘못된 이메일 형식입니다.");  
			    return false;  
			} else {  
			    alert('이메일보내기성공!!');
			} 
	 });
	 
});









