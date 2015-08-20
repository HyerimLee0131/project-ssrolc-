<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>FileUploader Callback</title>
</head>
<body>
	<script type='text/javascript' >
		// execute callback script
		var sUrl = document.location.search.substr(1);
		if (sUrl != "blank") {
			
	        var oParameter = {}; // query array
			var paramStr = '';
	        sUrl.replace(/([^=]+)=([^&]*)(&|$)/g, function(){
	            oParameter[arguments[1]] = arguments[2];
	            paramStr += ',"'+ arguments[1]+'":"'+arguments[2]+'"';
	            return "";
	        });
	        
	        paramStr = '{' + paramStr.substring(1,paramStr.length) + '}';
	        if(oParameter.callback_result=="error"){
	 	        if(oParameter.errstr=="fileSize"){
	 	        	alert('파일 업로드 용량 오류:2M 이하만 업로드 가능합니다.');
		        }else{
		        	alert('파일 업로드 불가능 합니다.');	
		        }
	        }else{
	        	parent.window.setInsertImageFile(oParameter.callback_result,paramStr); 	
	        } 
		}	
 	</script>
</body>
</html>