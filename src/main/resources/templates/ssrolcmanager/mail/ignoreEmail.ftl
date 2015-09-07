<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
 var result = "${result}";
 alert(result);
 if(result == "success"){
	alert('수신거부 처리가 되었습니다.');
 }else if(result == "fail"){
 	alert('이미 수신거부 처리가 되어있습니다.');
 }else{
 	alert('수신거부 처리가 실패 하였습니다.');
 }
 self.close();
</script>
</head>

<body>

</body>
</html>
