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
				<h1 class="mgt-20"></h1>
				 <p class="page-title">유선 상담(상담 신청 정보 입력)</p>
				<div class="tbl-type-E">
				<form name="fwrite" id="fwrite" action="/ssrolcmanager/franchise/faq" method="POST">
					<table width="100%" cellPadding="0" cellSpacing="0" summary="">
						<colgroup>
							<col width="14%">
							<col width="*%">
						</colgroup>
						<tbody>
							<tr>
								<th>이름</th>
								<td>
									<input name="jslcName" type="text" class="text" id="pMemName" style="width: 200px;" title="">
								</td>
							</tr>
							<tr>
								<th>주소</th>
								<td>
									<div class="input-box">
										<span>
											<input name="jslcZip1" type="text" class="text zipcode" id="mb_zip1" title="" readonly='readonly' value="111" > - <input name="jslcZip2" type="text" class="text zipcode" id="mb_zip2" title="" readonly='readonly' value="111" >
											<span class="button"><a onclick="">우편번호 검색</a></span>
										</span>
										<span><input name="jslcAddr1"  type="text" class="text"  id="mb_addr1" title="" readonly='readonly' value="경북 포항시"></span>
										<span><input name="jslcAddr2" type="text" class="text" id="mb_addr2" title=""></span>
									</div>
								</td>
							</tr>
							<tr>
								<th>휴대전화</th>
								<td>
									<select name="jslcCP1" id="pPhone01" style="width: 80px;" title="">
										<option value="select">선택</option>
										<option value="010" selected>010</option>
										<option value="011" >011</option>
										<option value="016" >016</option>
										<option value="017" >017</option>
										<option value="018" >018</option>
										<option value="019" >019</option>
									</select>
									<input name="jslcCP2" type="text" class="text" id="pPhone02" style="width: 60px;" title="" maxlength="4"> - <input name="jslcCP3" type="text" class="text"  id="pPhone03" style="width: 60px;" title="" maxlength="4">
								</td>
							</tr>
							<tr>
								<th>가맹교실유형</th>
								<td>
									<span><input name="jslcType" type="radio" title="" onclick="$.checkDeptType();" value="공부방"/><label>공부방</label></span><span><input name="jslcType" type="radio" title="" onclick="$.checkDeptType();" value="학원" /><label>학원</label></span><span><input name="jslcType" type="radio" title="" onclick="$.checkDeptType();" value="교습소" /><label>교습소</label></span>
								</td>
							</tr>
							<tr>
								<th>희망과목</th>
								<td>
									<select name="jslcSubject" disabled id="jslcSubject" style="width: 80px;" title="">
										<option value='select'>선택</option>
										<option value='수학'>수학</option>
										<option value='국어'>국어</option>
										<option value='한자'>한자</option>
										<option value='피자'>피자</option>
										<option value='사회'>사회</option>
										<option value='과학'>과학</option>
										<option value='영어'>영어</option>
										<option value='중국어'>중국어</option>
										<option value='일본어'>일본어</option>
									</select>
									<input name="jslcSubject" id="jslcSubject" type="hidden" value="">
								</td>
							</tr>
							<tr>
								<th>가맹희망지역</th>
								<td>
									<label for="">시/도</label>
									<select id="hopeArea01" name="jslcArea1" title="지역선택" style="width: 80px;">
										<option value="select">지역선택</option>
										<#list cityList as city>
											<option value = "${city}">${city}</option>
										</#list>
									</select>&nbsp;&nbsp;&nbsp;
									<label for="">군/구</label>
									<select id="hopeArea02" name="jslcArea2" title="지역선택" style="width:100px">
										<option value="select">지역선택</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>가맹희망일</em></th>
								<td>
									<input name="jslcDate" type="text" class="text datePicker" id="joinHopeDate" style="width: 80px;" title=""  readonly='readonly'>
									<img src="/public/img/admin/common/icon_calendar.gif" alt="" id="joinHopeDate_img" style="cursor:pointer;">
								</td>
							</tr>
							<tr>
								<th>희망상담시간</th>
								<td>
									<select name="jslcTime1" id="pTime01" style="width: 80px;" title="">
										<option value="select">선택</option>
										<option value="오전">오전</option>
										<option value="오후" >오후</option>
									</select>&nbsp;&nbsp;&nbsp;
									<select name="jslcTime2" id="pTime02" style="width: 80px;" title="">
										<option value="select">선택</option>
										<option value="1">1시</option>
										<option value="2">2시</option>
										<option value="3">3시</option>
										<option value="4">4시</option>
										<option value="5">5시</option>
										<option value="6">6시</option>
										<option value="7">7시</option>
										<option value="8">8시</option>
										<option value="9">9시</option>
										<option value="10">10시</option>
										<option value="11">11시</option>
										<option value="12">12시</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>상담내용작성</th>
								<td>
									<textarea id="pContents" name="jslComments" cols="130" rows="15"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					<div>
						<p class="mgt-10 text-r"><input name="pCheck" type="checkbox" id="pCheck" title=""/>&nbsp;<span class="vert-2">개인정보 항목 및 수집방법에 대하여 충분히 설명하고 동의를 받았습니까?</span></p>
						<p class="mgt-10 text-r"><input name="joinCheck" type="checkbox" id="joinCheck" title="" value="1"/>&nbsp;<span class="vert-2">가맹 상담을 완료 하였습니까?(완료:체크,대기:미체크)</span></p>
						<input name="jslcounseling" id="jslcounseling" type="hidden" value="0">
					</div>
					<div class="btn-box float-r">
						<span class="button btn-type-I"><a href="javascript:$.joinWrite();" style="cursor:pointer;" >등록</a></span>
						<span class="button btn-type-J"><a href="/ssrolcmanager/franchise/faqs">취소</a></span>
					</div>
				</form>		
			</div>		
		</div>	
	</div>
<#include "/ssrolcmanager/footer.ftl">
	</div>

