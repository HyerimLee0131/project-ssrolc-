<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>
<table align="center" border="1">
	<tr>
	<td>신청자명</td>
	<td>${franchise.jslcName}</td>
	</tr>
	<tr>
	<td>신청자주소</td>
	<td>${franchise.jslcZip} ${franchise.jslcAddr1} ${franchise.jslcAddr2}</td>
	</tr>
	<tr>
	<td>휴대전화</td>
	<td>${franchise.jslcCP1}-${franchise.jslcCP2}-${franchise.jslcCP3}</td>
	</tr>
	<tr>
	<td>가맹유형</td>
	<td>${franchise.jslcType}</td>
	</tr>
	<tr>
	<td>희망과목</td>
	<td>${franchise.jslcSubject}</td>
	</tr>
	<tr>
	<td>희망지역</td>
	<td>${franchise.jslcArea1} ${franchise.jslcArea2}</td>
	</tr>
	<tr>
	<td>가맹희망일</td>
	<td>${franchise.jslcDate}</td>
	</tr>
	<tr>
	<td>상담시간</td>
	<td>${franchise.jslcTime1} ${franchise.jslcTime2}</td>
	</tr>
	<tr>
	<td>상담내용</td>
	<td>${franchise.jslComments}</td>
	</tr>
	

</table>

</body>
</html>
