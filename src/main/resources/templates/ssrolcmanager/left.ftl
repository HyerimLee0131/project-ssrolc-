<!-- left -->
	<div id="container">
			<div id="content">
			<div class="side-content">
				<div class="btn-join-state">
					<p class="msg2 mgb-10">안녕하세요.<strong>관리자님</strong></p>
					<p class="mgb-5"><a href="/ssrolcmanager/edit"><img src="/public/img/admin/common/btn_gnb_pass.gif" alt="비밀번호변경" /></a></p>
					<p>
						<a href="/ssrolcmanager/logout"><img src="/public/img/admin/common/btn_gnb_logout.gif" alt="로그아웃" /></a>
					</p>
				</div>		
 				<div class="snb">
					<#if currentMenu == "disclosure" || currentMenu == "franchise" || currentMenu == "center"> 
					<h3>일반관리</h3>
					<ul>
						<li <#if currentMenu == "disclosure">  class="active" </#if>    ><a href="/ssrolcmanager/disclosures">정보공개서 열람 확인</a></li>
						<li <#if currentMenu == "franchise">  class="active" </#if>  ><a href="/ssrolcmanager/franchise/faqs">가맹문의 확인</a></li>
						<li <#if currentMenu == "center">  class="active" </#if>  ><a href="/ssrolcmanager/centers">우리동네 러닝센터 홈페이지 관리</a></li>
					</ul>
					</#if>
					<#if currentMenu == "boardCenter" || currentMenu == "boardFaq" || currentMenu == "boardNotice" || currentMenu == "boardStudent"> 
					<h3>게시판관리</h3>
					<ul>
						<li <#if currentMenu == "boardNotice">  class="active" </#if> ><a href="/ssrolcmanager/boards/notice">공지사항</a></li>
						<li <#if currentMenu == "boardFaq">  class="active" </#if> ><a href="/ssrolcmanager/boards/faq">자주묻는 질문</a></li>
						<li <#if currentMenu == "boardCenter">  class="active" </#if> ><a href="/ssrolcmanager/boards/center">우수센터장 소개</a></li>
						<li <#if currentMenu == "boardStudent">  class="active" </#if> ><a href="/ssrolcmanager/boards/student">우수학생 소개</a></li>
					</ul>
					</#if>					
 					<#if currentMenu == "popup" || currentMenu == "prmedia"> 
					<h3>부가관리</h3> 
 					<ul> 
 						<li <#if currentMenu == "popup">  class="active" </#if>  ><a href="/ssrolcmanager/popups">팝업관리</a></li>
 						<li <#if currentMenu == "prmedia">  class="active" </#if>  ><a href="/ssrolcmanager/prmedias">홍보영상관리</a></li>	
					</ul>
					</#if>
 				</div> 
			</div>
<!-- left -->