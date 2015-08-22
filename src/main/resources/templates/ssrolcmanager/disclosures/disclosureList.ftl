<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">
			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/ssrolcmanager/main">홈</a> &gt; </li>
						<li><a href="/ssrolcmanager/disclosure/disclosureList">일반관리</a> &gt; </li>
						<li class="current">정보공개서 열람 확인</li>
					</ul>
				</div>	
				<p class="page-title">정보공개서 열람확인</p>
				<div class="mgt-20 search-class">
						
							<fieldset class="">
								<legend></legend>
								<p>
									<label for="">※ 가맹희망지역</label>
									<select id="hopeArea01" title="지역선택" style="width:100px">
										<option value="select">지역선택</option>
										<#list cityList as city>
											<option value = "${city}">${city}</option>
										</#list>
									</select>&nbsp;&nbsp;
									<select id="hopeArea02" title="지역선택" style="width:100px">
										<option value="select">지역선택</option>
									</select>&nbsp;&nbsp;&nbsp;&nbsp;
									<span class="search_tit">※ 가맹형태</span>
									<input type="checkbox" id="deptType01" title="" value="공부방"/><label for="deptType01">공부방</label><input type="checkbox"  id="deptType02" title="" value="학원"/><label for="deptType02">학원</label><input type="checkbox"  id="deptType03" title=""  value="교습소"/><label for="deptType03">교습소</label>
								</p>
								<p>
									<label for="">※ 정보공개서 확인일</label>
									<input type="text" class="text datePicker" id="startDate" style="width:100px" title="" readonly='readonly' alt="검색시작일자" />
									<img src="/public/img/admin/common/icon_calendar.gif" alt="" class="vertical-m " id="startDate_img" /> ~ &nbsp;<input type="text" class="text datePicker" id="endDate" style="width:100px" title="" readonly='readonly' alt="검색끝난일자" />
									<img src="/public/img/admin/common/icon_calendar.gif" alt="" class="vertical-m" id="endDate_img" /> 
									<label for="pMemName">※ 이름</label>
									<input type="text" class="text" id="pMemName" style="width:100px" title="" maxlength="30" />
									&nbsp;<span class="button"><input type="button" id="searchBtn" title="" value="검색"></span>
									<span class="button"><input type="button" id="resetBtn" title="" value="초기화"></span>
								</p>
							</fieldset>
					</div>
				<h1 class="mgt-20"></h1>
				<p>* 총 <strong id="totalCnt">0</strong>건</p>
				<div class="tbl-type-F">
				
				<input name='pageNum' id="pageNum" type='hidden' value='1'/>
					<table width="100%" cellSpacing="0" summary="">
						<colgroup>
							<col width="5%">
							<col width="12%">
							<col width="7%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="18%">					
							<col width="22%">
							<col width="*%">
						<thead>						
							<tr>
								<th>번호</th>
								<th>정보공개서확인일</th>
								<th>이름</th>
								<th>가맹희망지역</th>
								<th>가맹교실유형</th>
								<th>연락처</th>
								<th>이메일</th>
								<th>주소</th>
								<th>열람증</th>							
							</tr>
						</thead>
						<tbody id=dataList></tbody>					
					</table>
				</div>
				
				<div class="paging">
					<span id="pageNavi"></span>
				</div>
			</div>
		</div>
	</div>
	<!-- //container -->
<#include "/ssrolcmanager/footer.ftl">
</div>
<script id="disclosureTemplate" type="text/x-handlebars-template">
	{{#each disclosureInfo}}
	<tr>
		<td>{{inc @index}}</td>
		<td>{{prettifyDate regDate}}</td>
		<td>{{memName}}</td>
		<td>{{deptArea1}}&nbsp;{{deptArea2}}</td>
		<td>{{deptType}}</td>
		<td>{{phone}}</td>
		<td>{{email}}</td>
		<td>{{addressDtl01}}{{addressDtl02}}</td>
		<td><a href="javascript:$.openDisclosure({{aidx}});"><img src="/public/img/admin/common/icon_offline.gif" alt="" style="cursor:pointer;" /></a></td>
	</tr>
	{{else}}
	<tr>
		<td colspan="9">데이터가 없습니다.</td>
	</tr>
	{{/each}}
</script>

</body>
</html>
