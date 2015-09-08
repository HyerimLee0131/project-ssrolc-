<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/public/css/common_admin.css" />
<link rel="stylesheet" type="text/css" href="/public/css/jquery-ui.1.11.4.min.css" />
<script src="/public/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/public/js/jquery-ui.1.11.4.min.js"></script>
<script src="/public/js/common.js"></script>
<title>${title?default('러닝센터')}</title>
<#if error?default("") == "true" >
<script type="text/javascript">
	$(function(){
		alert("계정과 암호가 일치하지 않습니다.");
		$("#loginFrm").attr("action","/ssrolcmanager/login");
		$("#loginFrm").submit();
	});
</script>
</#if>
</head>
<body id="login">
<div id="loginWrapper">
	<div id="header">
		<h1><a href="#"><img src="/public/img/admin/login/logo.gif" alt="재능스스로러닝센터" /></a></h1>
	</div>
	<div id="loginContainer">
		<h2><img src="/public/img/admin/login/h2_login.gif" alt="로그인" /></h2>
		<form name="loginFrm" id="loginFrm" method="post" action="/ssrolcmanager/loginCheck">
			<div class="login-box">
				<dl>
					<dt><img src="/public/img/admin/login/tit_fc_login.gif" alt="재능스스로러닝센터 관리시스템" /></dt>
				</dl>
				<fieldset>
					<legend>재능 스스로 러닝센터</legend>
					<div>
						<p><input type="text" placeholder="아이디" name="userId" maxlength="10" /></p>
						<p><input type="password" placeholder="비밀번호"  name="userPassword" maxlength="100"/></p>
						<span class="button btn-login"><input type="submit" value="로그인" style="cursor:pointer;"/></span>
					</div>
					<p class="save-chk">
						<input name="rememberId" type="checkbox" id="rememberId" title="아이디저장" value="ok" /><label for="reId">아이디 저장</label>
					</p>
					<ul>
						<li>비밀번호가 기억이 안나신다구요? 본사 <em>02-2028-5612</em>로 연락주시기 바랍니다.</li>
						<li>통합관리자 계정 정보를 입력해 주시고 관련 정보는 유출되지 않도록 보안사항을 지켜주세요.</li>
					</ul>
				</fieldset>
			</div>
			<input type="hidden" name="returl" value="${returl?default('')}" />
		</form>
	</div>
</div>
<#include "/ssrolcmanager/footer.ftl">
</body>
</html>