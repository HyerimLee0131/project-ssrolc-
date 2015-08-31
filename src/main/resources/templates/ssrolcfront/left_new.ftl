		<!-- container -->
		<div class="container" id="container">
			<div class="clearfix">
				<div class="side_left">
					<h2>스스로러닝센터</h2>
					<ul class="clear">
						<li><a href="#">스스로러닝센터소개</a></li>
						<li><a href="/ssrolcfront/prmedias">홍보영상</a></li>
						<li><a href="/ssrolcfront/centerSearch">우리동네 러닝센터 찾기</a></li>
					</ul>
					<h2>가맹안내</h2>
					<ul class="clear">
						<li><a href="#">스스로러닝센터 경쟁력</a></li>
						<li><a href="#">가맹절차</a></li>
						<li><a href="/ssrolcfront/franchise/search">가맹지사찾기</a></li>
						<li><a href="franchisefaq.html">가맹문의</a></li>
					</ul>
					
					<#if currentMenu == "boardCenter" || currentMenu == "boardFaq" || currentMenu == "boardNotice" || currentMenu == "boardStudent">
					<h2>커뮤니티</h2>
					<ul class="clear">
						<li <#if currentMenu == "boardNotice">  class="active" </#if> ><a href="/ssrolcfront/boards/notice">공지사항</a></li>
						<li <#if currentMenu == "boardFaq">  class="active" </#if> ><a href="/ssrolcfront/boards/faq">자주묻는질문</a></li>
						<li <#if currentMenu == "boardCenter">  class="active" </#if> ><a href="/ssrolcfront/boards/center">우수센터장소개</a></li>
						<li <#if currentMenu == "boardStudent">  class="active" </#if> ><a href="/ssrolcfront/boards/student">우수학생소개</a></li>
					</ul>
					</#if>
				</div>
