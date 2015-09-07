<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>
<form action="/ssrolcfront/disclosure/ignoreEmail" method="post" name="ignoreEmail" id="ignoreEmail">
	<table width="683" height="683" border="0" cellpadding="0" cellspacing="0" background="http://${hostName}/public/img/etc/mailing_00.gif" id="Table_01" summary="">
	<tr><td height="225">&nbsp;</td></tr>
	<tr>
		<td height="69"  style="padding-left: 60px; font-size:12px; color:#515151;line-height:13px;" >안녕하세요. <span style="font-weight:bold; font-size:12px; color:#00b46e;">${name}</span> 님!<br/>정보공개서 열람 시 필요한 본인인증 확인을 위한 인증번호를 알려드립니다.</td>
	</tr>
	<tr>
		<td height="39" style="padding-left:80px; font-size:14px; color:#777777; font-weight:bold;">인증번호 : <span style="color:#F00006;font-size:14px;">${securityKey}</span></td>
	</tr>
	<tr>
		<td><img src="http://${hostName}/public/img/etc/mailing_01.gif" alt="" width="683px" height="107" usemap="#Map" border="0"></td>
	</tr>
	<tr>
		<td height="124" style="padding-left: 80px; color: #8a8a8a; font-size: 12px; line-height: 13px;"><p>본 메일은 발신전용 메일이며 문의에 대한 회신 처리가 되지 않습니다.<br>메일수신을 원하지 않을 경우 수신거부를 클릭하시면 수신거부처리가 이루어집니다.<a href="http://${hostUrl }/ssrolcfront/disclosure/ignoreEmail?email=${email}" target="_blank"><img src="http://${hostName}/public/img/etc/btn_reject.gif" alt="수신거부" border="0"></a></p><p style="font-size:12px;">If you do not wish to recive emails from us, <span style="color:#ec121b;font-size:12px;"><a href="http://${hostUrl }/ssrolcfront/disclosure/ignoreEmail?email=${email}" target="_blank">click here</a></span></p></td>
	</tr>
	<tr>
		<td height="28">&nbsp;</td></tr>
		<tr>
			<td>
				<img src="http://${hostName}/public/img/etc/mailing_01.gif" alt="" width="683px" height="91px" usemap="#Map2" border="0">
			</td>
	</tr></table>
	<!-- cnotents// -->
	<map name="Map" alt="">
		<area shape="rect" coords="322,38,553,78" href="" alt="재능스스로러닝센터 홈페이지"  target="_blank">
		<area shape="rect" coords="124,38,312,79" href="" alt="인증번호 입력하러 가기"  target="_blank">
	</map>


	<map name="Map2" alt="">
		<area shape="rect" coords="83,43,164,58" href="http://${hostName}/infomation/infomation_pop.htm" target="_blank" alt="개인정보취급방침">
		<area shape="rect" coords="27,42,68,57" href="http://jeigroup.co.kr/" target="_blank" alt="회사소개">	
	</map>
	</form>
</body>
</html>
