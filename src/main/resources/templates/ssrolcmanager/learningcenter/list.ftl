<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">
<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/ssrolcmanager/main">홈</a> &gt; </li>
						<li><a href="/ssrolcmanager/disclosures">일반관리</a> &gt; </li>
						<li class="current">우리동네 러닝선테 홈페이지 관리</li>
					</ul>
				</div>
				<p class="page-title">우리동네 러닝센터 홈페이지 관리</p>
					 <h1 class="mgt-20"></h1>
					 <p>* 총 <em class="bor-none" id="totalCnt">${learningCenters?size}</em>건</p>
					 <div class="tbl-type-D">
						<table width="100%" border="0" cellPadding="0" cellSpacing="0" summary="">
							<colgroup>
							<col width="5%">
							<col width="15%">
							<col width="15%">
							<col width="*%">							
							</colgroup>
							<tr>
								<th>번호</th>
								<th>센터ID</th>
								<th>센터명</th>
								<th>홈페이지URL</th>
							</tr>
							<#list learningCenters as lc>
							<tr>
								<td>${lc_index+1}</td>
								<td><a href="/ssrolcmanager/center/${lc.deptId1}">${lc.deptId1}</a></td>
								<td>${lc.deptName}<#if lc.homeUrl != "">&nbsp;<a href="${lc.homeUrl}" target="_blank"><img src="/public/img/center/icon_centerHome.png" alt="홈페이지아이콘"></a></#if></td>
								<td>${lc.homeUrl}</td>
							</tr>
							</#list>
						</table>
					</div>
			</div>
		</div>
		<#include "/ssrolcmanager/footer.ftl">
	</div>
</body>
</html>

