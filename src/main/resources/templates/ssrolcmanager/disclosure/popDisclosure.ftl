<#include "/ssrolcmanager/popupHeader.ftl">
<div style="padding:5px;">
<div id="popWrapper" class="wrapper" style="width:720px;">
	<!-- popHeader -->
	<div id="popHeader">
	 <h1><img src="/public/img/admin/popup/pop_logo.gif" alt="재능스스로러닝센터" /></h1>
	 <h2>정보공개서 열람증</h2>
	</div>

	<!-- popContent -->
	<div id="popContent" style="height:450px">
		<div class="tbl-type-E">
			<form method="post" action="">
			  <table width="100%" border="0" cellPadding="0" cellSpacing="0" summary="">
				<colgroup>
				<col width="15%">
				<col width="20%">
				<col width="15%">
				<col width="*%">						
				</colgroup>
				<tbody>
				  <tr>
					<th>이름</th>
					<td>김순호</td>
					<th>이메일</th>
					<td>mashisoon@jei.com</td>					
				  </tr>
				  <tr>
					<th>연락처</th>
					<td colspan="3">010-5617-2210</td>				
				  </tr>
				  <tr>
					<th>주소</th>
					<td colspan="3">서울 종로구 혜화동&nbsp;재능아카데미 테스트입니다.</td>				
				  </tr>								 
				    <tr>
					<th>가맹희망지역</th>
					<td>서울&nbsp;종로구</td>
					<th>가맹형태</th>
					<td>
					<span><input type="checkbox" disabled="disabled" title="" /><label>공부방</label></span><span><input type="checkbox" disabled="disabled" title=""   /><label>학원</label></span><span><input type="checkbox" disabled="disabled" title=""  checked="checked" /><label>교습소</label></span>
					</td>					
				  </tr>
				</tbody>
			  </table>
			</form>
		</div>	
		<div class="text-c mgt-20">
			<p>위 사람은 2015년 08월 10일 정보 공개서를 열람하였습니다.</p>
		</div>
		<div class="btn-box" id="printDiv">
			<span class="button btn-type-I"><a onclick="$.selfPrint();" style="cursor:pointer;">프린트</a></span>
		</div>
	</div>
</div>
</div>
</body>
</html>
