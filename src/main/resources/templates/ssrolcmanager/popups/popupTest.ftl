<!DOCTYPE html>
<html lang="ko">
<head>
	<title><#if popup??>${popup.name}</#if></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style type="text/css">
		* {margin:0; padding:0;font-size:11px; font-family:"돋움",doutm,Helvetica,sans-ferif;color:#444;}
		ul, li, em { font-style:normal; }
		ul { list-style: none; }
		table, caption, tbody, tfoot, thead, tr, th, td {padding:0;margin:0;border:0 none}
		caption { font-size:0; height:0; line-height:0; margin:0; padding:0; position:absolute; visibility:hidden; width:0; }
		table { border-spacing:0; border-collapse:collapse; table-layout:fixed; }
		img {border:0;}
		.commonH { font-size:0; height:0; line-height:0; margin:0; padding:0; position:absolute; visibility:hidden; width:0; }
		.wrap01 { width:460px; padding:5px; background:#fff;  }
		.wrap { border:1px solid #000; }
		.wrap h1 img { vertical-align:top; }
		.conWrap { padding:0 15px 27px 15px; }
		.scrollWrap { height:276px; border-bottom:1px solid #e4e4e4; overflow:hidden; overflow-y:scroll; }
		.popTbl { }
		.popTbl table { width:100%; }
		.popTbl table thead th,
		.popTbl table tbody td { text-align:center; padding:3px 3px; line-height:16px; }
		.popTbl table thead th { background:#d62027; color:#fff; border-top:1px solid #b0080e; border-bottom:1px solid #b0080e; }
		.popTbl table tbody td { background:#fff; color:#444444; border:1px solid #e4e4e4; }
		.num { text-align:center; padding:10px 0; }
		.num strong { font-size:14px; }
		.infoList {  }
		.infoList li { margin-bottom:2px; }
		.infoList li em { color:#ec2d00; }
		.popupFootArea {width:438px;height:12px;padding:5px 15px;}
		.popupFootArea .btn_todayclose {float:left;}
		.popupFootArea .btn_closewindow {text-align:right;}
	</style>
	<script language="javascript">
		//쿠키 생성
		function setCookie( name, value, expiredays ) {
			var todayDate = new Date();
			todayDate.setDate( todayDate.getDate() + expiredays );
			document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
		}

		//오늘하루이창열지않기
		function closeToday() {
			self.close();
			//setCookie( "<#if popup??>${popup.cookie_id}</#if>", "done" , 1);  //  쿠키를 유지할 기간을 설정
			//top.parent.$.modal().modal_close('<#if popup??>${popup.cookie_id}</#if>');
		}

		//닫기 
		function closeWin() {
			self.close();
			//top.parent.$.modal().modal_close('<#if popup??>${popup.cookie_id}</#if>');
		}
	</script>
</head>
<body>
	<div class="wrap01">
		<div class="wrap">


			<#if popup??>${popup.content}</#if>


			<!-- //컨텐츠 -->
			<div class="popupFootArea">
				<p class="btn_todayclose">
					<a href="javascript:closeToday();"><img src="/crosseditor/photos/popup/45" alt="오늘 하루 이창 열지 않기 " /></a>
				</p>
				<p class="btn_closewindow">
					<a href="javascript:closeWin();">
						<img src="http://local.ssrolc.com:8080/crosseditor/photos/popup/46" alt="이창닫기" />
					</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>