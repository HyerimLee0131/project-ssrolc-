<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title?default('러닝센터')}</title>
</head>
<body>
<div>
	<div>
		<form name="loginFrm" method="post" action="https://devssl.ssro.com/jeislc/controller/loginchk_java.php">
			<div>
				<div>
					<p><input type="text" placeholder="아이디" name="userId" maxlength="10" /></p>
					<p><input type="password" placeholder="비밀번호"  name="userPassword" maxlength="100"/></p>
					<span><input type="submit" value="로그인" style="cursor:pointer;"/></span>
				</div>
			</div>
			<input type="hidden" name="returl" value="${returl?default('')}" />
		</form>
	</div>
</div>
</body>
</html>