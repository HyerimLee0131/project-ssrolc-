<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">
			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/ssrolcmanager/main">홈</a> &gt; </li>
						<li><a href="/ssrolcmanager/disclosure/disclosureList">일반관리</a> &gt; </li>
						<li class="current">가맹문의 확인</li>
					</ul>
				</div>	
				<p class="page-title">가맹문의 확인</p>
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
									<label for="">※ 상담 신청일</label>
									<input type="text" class="text datePicker" id="startDate" style="width:100px" title="" readonly='readonly' alt="검색시작일자" />
									<img src="/public/img/admin/common/icon_calendar.gif" alt="" class="vertical-m " id="startDate_img" /> ~ &nbsp;<input type="text" class="text datePicker" id="endDate" style="width:100px" title="" readonly='readonly' alt="검색끝난일자" />
									<img src="/public/img/admin/common/icon_calendar.gif" alt="" class="vertical-m" id="endDate_img" /> 
									
									<label for="counseling">※ 접수상태</label>
										<select name="counseling" id="counseling" style="width:100px" title="">
											<option value="select">선택</option>
											<option value="0">대기</option>
											<option value="1">상담완료</option>
											<option value="2">가맹완료</option>
										</select>&nbsp;&nbsp;
								
									<label for="pMemName">※ 이름</label>
									<input type="text" class="text" id="pMemName" style="width:100px" title="" maxlength="30" />
									&nbsp;<span class="button"><input type="button" id="searchBtn" title="" value="검색"></span>
									<span class="button"><input type="button" id="resetBtn" title="" value="초기화"></span>
								</p>
							</fieldset>
							
							<!--레이어팝업 창-->
							<div id="layer_pop" style="width:550px;display:none;">
							<!-- popHeader -->
							<div id="popHeader">
								<h2>접수상태 변경</h2>	
								<a class="btnClose" onclick='$("#layer_pop").hide();' style="cursor:pointer;">
									<img alt="창 닫기" src="/public/img/admin/popup/btn_popup_close.gif">
								</a>
							</div>
							<!-- popContent -->
							<div id="popContent" style="height:150px;background-color:white;">
								<div class="tbl-type-C">
								<table cellspacing="0" summary="">									
								<colgroup>
								<col width="550px">		
								</colgroup>
								<tbody>
									<tr>
										<td>
											<span><input name="joinState" type="radio" id="joinState0" title="" value="0" /><label>대기</label></span>
											<span><input name="joinState" type="radio" id="joinState1" title="" value="1" /><label>상담완료</label></span>
											<span><input name="joinState" type="radio" id="joinState2" title="" value="2" /><label>가맹완료</label></span>
										</td>
									</tr>									
								</tbody>							
								</table>		
							</div>
							<div class="btn-box-03">
								<span class="button btn-type-I">
								<a class="w-65" id="layerSubmitBtn" style="cursor:pointer;">확인</a>
							</span>
						</div>		
					</div>
				</div>
			</div>
				<h1 class="mgt-20"></h1>
				<p>* 총 <strong id="totalCnt">0</strong>건(대기 <em id="waitCnt">0</em>건)</p>
				<div class="tbl-type-F">
				
				<input name='pageNum' id="pageNum" type='hidden' value='1'/>
					<table width="100%" cellSpacing="0" summary="">
						<colgroup>
							<col width="5%">
							<col width="10%">
							<col width="7%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="10%">					
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="*%">
						<thead>						
							<tr>
								<th>NO</th>
								<th>상담신청일</th>
								<th>경로</th>
								<th>이름</th>
								<th>가맹희망지역</th>
								<th>교실유형</th>
								<th>희망과목</th>
								<th>가맹희망일</th>
								<th>희망상담시간</th>
								<th>휴대전화</th>
								<th>접수상태</th>							
							</tr>
						</thead>
						<tbody id="dataList"></tbody>					
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
<script id="franchiseTemplate" type="text/x-handlebars-template">
	{{#each franchiseInfo}}
	<tr>
		<td>{{inc @index}}</td>
		<td>{{jslipDatetime}}</td>
		<td>{{inType}}</td>
		<td>{{jslcName}}</td>
		<td>{{jslcArea1}}&nbsp;{{jslcArea2}}</td>
		<td>{{jslcType}}</td>
		<td>{{jslcSubject}}</td>
		<td>{{jslcDate}}</td>
		<td>{{jslcTime1}}&nbsp;{{jslcTime2}}</td>
		<td>{{jslcCP1}}-{{jslcCP2}}-{{jslcCP3}}</td>
		<td>{{#xIf jslcounseling "==" 0}}
				<span class="button btn-type-H"><a href="javascript:$.openFranchise({{jslcId}},{{jslcounseling}});" class="c-2">대기</a></span>
			{{else xIf jslcounseling "==" 1}}
				<span class="button btn-type-H"><a href="javascript:$.openFranchise({{jslcId}},{{jslcounseling}});" class="c-1">상담완료</a></span>
			{{else xIf jslcounseling "==" 2}}
				<span class="button btn-type-H"><a href="javascript:$.openFranchise({{jslcId}},{{jslcounseling}});" class="c-3">가맹완료</a></span>
			{{/xIf }}
		</td>
	</tr>
	{{else}}
	<tr>
		<td colspan="11">데이터가 없습니다.</td>
	</tr>
	{{/each}}
</script>



</body>
</html>
