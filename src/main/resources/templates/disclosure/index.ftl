<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="/public/js/mail.js"></script>
</head>

<body>
	
	
	
	<form name="sendEmail" method="post" id="sendEmail" action="/disclosure/mail">
		<input name="pEmailId" type="text" id="pEmailId" title="이메일아이디" maxlength="50"/><span>@</span><input name="pEmailAdd1" type="text" id="pEmailAdd1" title="이메일주소"  maxlength="40" readonly />
		<select name="pEmailAdd2" id="pEmailAdd2" title="이메일주소">
				<option value="select">선택하세요</option>
				<option value="inputEmail">직접입력</option>
				<option value="gmail.com">gmail.com</option>
				<option value="hanmail.net">hanmail.net</option>
				<option value="hotmail.com">hotmail.com</option>
				<option value="naver.com">naver.com</option>
				<option value="nate.com">nate.com</option>
				<option value="jei.com">jei.com</option>
			</select>
		<input type="submit" style="cursor:pointer;" value="메일전송" >
	</form>
</body>
</html>




