<#include "/ssrolcfront/header.ftl">
<body>
	<!--// content -->
	<div class="ex"></div>
	<div class="mainvisualimg">
		<div class="headcopy">
			<p class="j_d_none">
			A Better Life Through Better Education
			보다 나은 교육을 통한
			보다 나은 삶
			1:1 맞춤 완전학습교실
			</p>
		</div>
	</div>
	<div class="ban_a">
		<dl>
			<dd><a href="" name="centerjang" id="centerjang"><img src="/public/img/main/ban_01.png" alt="우수센터장 소개"></a></dd>
			<dd><a href="" name="beststudent" id="beststudent"><img src="/public/img/main/ban_02.png" alt="우수학생 소개"></a></dd>
			<dd><a href="" ><img src="/public/img/main/ban_03.png" alt="가맹점모집영상"></a></dd>
			<dd><a href="" id="centerserch"><img src="/public/img/main/ban_04.png" alt="우리동네 스스로러닝센터 찾기"></a></dd>
			<dd><a href="/disclosure" name="etc" id="etc"><img src="/public/img/main/ban_05.png" alt="정보공개서"></a></dd>
			<dd><a href="javascript:;" id="go_e"><img src="/public/img/main/ban_06.png" alt="스스로e 바로가기"></a></dd>
		</dl>
	</div>
	<div class="myungsim_a">
		<div class="myungsim_con_a">
			<p class="tit_myung">${myungsimbogam.title}</p>
			<p class="con_myung">${myungsimbogam.meanContents}</p>
			<p class="con_myung02">${myungsimbogam.hanjaContents}<p>
			<p class="con_myung03">${myungsimbogam.soundContents}<p>
		</div>
	</div>
	<div class="customercenter"></div>
	<div class="quickmenu">
		<dl>
			<dd><a href=""><img src="/public/img/main/quick_01.png" alt="공지사항"></a></dd>
			<dd><a href=""><img src="/public/img/main/quick_02.png" alt="자주 묻는 질문"></a></dd>
			<dd><a href="" target="_blank" ><img src="/public/img/main/quick_03.png" alt="e브로슈어"></a></dd>
			<dd><a href=""><img src="/public/img/main/quick_04.png" alt="센터홍보 영상"></a></dd>
		</dl>
	</div>
	<!-- content //-->
<!-- body // -->
<script type='text/javascript'>
$(document).ready(function(){  

	$("#event").on('click',function(){
		alert("서비스 예정입니다.");
	});

	$("#go_e").on('click',function(){
		window.open('http://ssroe.jei.com/','','fullscreen,scrollbars'); 
	});
});
</script>
<!-- 웹접근성 개선작업을 위한 div추가 2013-12-04 IT지원팀 장승균-->
<div>

<!-- body// -->
</div>
<!-- content //-->
</div>
</div>
<script type="text/javascript">
  //구글 웹분석 로그
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-16360157-43']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

<#include "/ssrolcfront/footer.ftl">
</div>
</body>
</html>
