<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">
<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/ssrolcmanager/main">홈</a> &gt; </li>
						<li><a href="/ssrolcmanager/disclosures">일반관리</a> &gt; </li>
						<li class="current">우리동네 러닝센터 홈페이지 관리</li>
					</ul>
				</div>
				<p class="page-title">우리동네 러닝센터 홈페이지 관리</p>
					<div class="tbl-type-D">
							<form id="writeFrm" name="writeFrm" action="/ssrolcmanager/center" method="post">
							  <table width="100%" border="0" cellpadding="0" cellspacing="0" summary="">
								<colgroup>
								<col width="15%">
								<col width="*">							
								</colgroup>
								<tbody>
								  <tr>
									<th>센터ID</th>
									<td><div class="td-left"><input name="deptId1" id="infoDeptId" type="text" class="text" readonly='readonly' value="${deptId1}" title="" /></div></td>
								  </tr>	
								   <tr>
									<th>센터명</th>
									<td><div class="td-left"><input id="infoDeptName" name="deptName" type="text" class="text" title="" readonly='readonly' value="${centerName}" /></div></td>
								  </tr>
								  <tr>
									<th>홈페이지URL<br/>(예)http://www.jei.com</th>
									<td><div class="td-left"><input name="homeUrl" id="infoDeptHomeUrl" type="text" style="width: 100%;" maxlength="255" class="text" value="${homeUrl?default('')}"  title="" /></div></td>
								  </tr>
								</tbody>
							  </table>
							  <input type="hidden" name="writeType" value="<#if homeUrl?exists>1<#else>0</#if>" title="구분값" >
							</form>
					 </div>
					<div class="mgt-20 float-r">
						<span class="button btn-type-I"><a class="w-65" href="/ssrolcmanager/centers">목록</a></span>
						<span class="button btn-type-J"><a class="w-65" style="cursor:pointer;" onclick="$('#writeFrm').submit();"><#if homeUrl?exists>수정<#else>등록</#if></a></span>
						<span class="button btn-type-J"><a class="w-65" href="/ssrolcmanager/centers">취소</a></span>
					</div>
			</div>
		</div>
	</div>
		<#include "/ssrolcmanager/footer.ftl">
</div>
		

</body>
</html>

