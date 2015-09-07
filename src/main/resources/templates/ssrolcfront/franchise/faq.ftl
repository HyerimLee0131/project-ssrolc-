<#include "/ssrolcfront/header_new.ftl">
<#include "/ssrolcfront/left_new.ftl">
				<div class="content">
					<div class="path">
						<span class="home"><a href="/">홈</a></span> &gt; <a href="#">가맹안내</a> &gt; <a href="/ssrolcfront/franchise/faq">가맹문의</a>
					</div>
					<form action="/ssrolcfront/franchise/faq" method="post" id="fwrite" name="fwrite">
					<input type="hidden" name="inType" value="사이트">
					<div class="faq">
						<h3>가맹문의</h3>
						<h4>이메일상담(상담 신청 정보 입력)</h4>
						<table class="faq_table" summary="상담정보 : 이름, 주소, 휴대전화, 가맹교실유형, 희망과목, 가맹희망지역, 가맹희망일, 희망상담시간, 내용 작성">
							<caption>이메일상담 신청정보입력</caption>
							<colgroup>
								<col style="width:150px" />
								<col />
							</colgroup>
							<thead>
								<tr>
									<th scope="row"><label for="jslcName">이름</label></th>
									<td><input type="text" class="input-text w150" name="jslcName" id="jslcName" /></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row"><label for="jslcZip">주소</label></th>
									<td>
										<span class="input-wrap">
										<input type="text" class="input-text w80" name="jslcZip" id="formPost" readonly='readonly' title="우편번호" />
										<a href="javascript:$.openPopup('/post','postSearch',400,500,10,30);" class="btn_address_search">우편번호검색</a>
										</span>
										<span class="input-wrap">
										<input type="text" class="input-text w98p" name="jslcAddr1" id="addressDtl01" title="주소" readonly='readonly' />
										</span>
										<span class="input-wrap">
										<input type="text" class="input-text w98p" name="jslcAddr2" id="mb_addr2" title="상세주소"/>
										</span>
									</td>
								</tr>
								<tr>
									<th scope="row"><label for="jslcCP1">휴대전화</label></th>
									<td>
										<select name="jslcCP1" id="pPhone01" class="input-select w80" title="휴대전화 앞자리">
											<option value="010">010</option>
											<option value="011">011</option>
											<option value="016">016</option>
											<option value="017">017</option>
											<option value="018">018</option>
											<option value="019">019</option>
										</select> - 
										<input type="text" class="input-text w80" name="jslcCP2" id="pPhone02" title="휴대전화 가운데자리" maxlength="4" /> - 
										<input type="text" class="input-text w80" name="jslcCP3" id ="pPhone03" title="휴대전화 뒷자리" maxlength="4" />
									</td>
								</tr>
								<tr>
									<th scope="row">가맹교실유형</th>
									<td>
										<span class="radio-wrap">
										<label><input type="radio" name="jslcType" onclick="$.checkDeptType();" value="공부방" /> 공부방</label>
										<label><input type="radio" name="jslcType" onclick="$.checkDeptType();" value="학원" /> 학원</label>
										<label><input type="radio" name="jslcType" onclick="$.checkDeptType();" value="교습소" /> 교습소</label>
										</span>
									</td>
								</tr>
								<tr>
									<th scope="row"><label for="jslcSubject">희망과목</label></th>
									<td>
										<select name="jslcSubject" disabled id="jslcSubject" class="input-select">
											<option value=''>선택</option>
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
									<th scope="row">가맹희망지역</th>
									<td>
										<label for="hopeArea01">시/도 :</label>
										<span class="select-wrap">
										<select name="jslcArea1" id="hopeArea01" class="input-select">
											<option value=''>선택</option>
												<#list cityList as city>
												<option value = "${city}">${city}</option>
												</#list>
										</select>
										</span>
										<span class="select-wrap">
										<label for="jslcArea2">군/구 :</label>
										<select name="jslcArea2" id="hopeArea02" class="input-select">
											<option value=''>선택</option>
										</select>
										</span>
									</td>
								</tr>
								<tr>
									<th scope="row"><label for="jslcDate">가맹희망일</label></th>
										<td>
										<input name="jslcDate" type="text" class="text datePicker" id="joinHopeDate" style="width: 80px;" title=""  readonly='readonly'>
										<img src="/public/img/admin/common/icon_calendar.gif" alt="" id="joinHopeDate_img" style="cursor:pointer;">
										</td>
								</tr>
								<tr>
									<th scope="row"><label for="jslcTime1">희망상담시간</label></th>
									<td>
										<span class="select-wrap">
										<select name="jslcTime1" id="pTime01" class="input-select w80" title="오전/오후">
											<option value=''>선택</option>
											<option value="오전">오전</option>
											<option value="오후" >오후</option>
										</select>
										<select name="jslcTime2" id="pTime02" class="input-select w80" title="시간">
											<option value=''>선택</option>
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
										</span>
									</td>
								</tr>
								<tr>
									<th scope="row"><label for="jslComments">상담내용작성</label></th>
									<td>
										<textarea name="jslComments" id="pContents" class="faq_contents w98p"></textarea>
									</td>
								</tr>
							</tbody>
						</table>
						<h4>상담약관 동의</h4>
						<div class="privacy_agree">
							<div class="privacy_agree_txt">
								<h5>개인정보 항목 및 수집방법</h5>
								<p>■ 개인정보 수집·이용 및 제공 동의</p>
								<dl>
									<dt>1. 개인정보의 수집 및 이용 목적</dt>
									<dd>- 가맹문의에 대한 상담서비스 제공을 위한 수집</dd>
								</dl>
								<dl>
									<dt>2. 수집·이용하는 개인정보 항목</dt>
									<dd>
										<p>[온라인문의]</p>
										<p>- 필수항목 : 이름, 주소, 휴대전화, 교실유형, 가맹희망지역, 가맹희망일, 희망상담시간, 상담내용</p>
									</dd>
								</dl>
								<dl>
									<dt>3. 개인정보의 보유 및 이용기간 </dt>
									<dd>
										<p>원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. </p>
										<p>단, 다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다. </p>

										<ul class="clear">
											<li>보존 항목 : 이름, 전화번호, 주소 </li>
											<li>보존 근거 : 부정 이용 방지 </li>
											<li>보존 기간 : 1년 </li>
										</ul>
										<p>[관계법령에 의한 정보보유 사유]</p>
										<p>관계법령의 규정에 의하여 보존할 필요가 있는 경우 회사는 아래와 같이 관계법령에서 정한 일정한 기간 동안 개인정보를 보관합니다. </p>
										<ul class="skip">
											<li>- 방문에 관한 기록 : 3개월</li>
											<li>- 설문조사, 이벤트 등 일시적 목적을 위하여 수집한 경우 : 당 설문조사, 이벤트 등이 종료된 때</li>
										</ul>
									</dd>
								</dl>
							</div>
							<div class="argree_chk">
								<label><input type="radio" name="agreeChk"  value="1" class="input-radio" /> 동의함</label>
								<label><input type="radio" name="agreeChk"  value="2" class="input-radio" checked /> 동의하지 않음</label>
							</div>
						</div>
						<div class="faq_submit">
							<a href="javascript:$.joinWrite();" class="btn_faq_submit">상담신청서 제출</a>
						</div>
					</div>
					</form>
				</div>


		<!-- //container -->
		<!-- footer -->
		<#include "/ssrolcfront/footer_new.ftl">
		<!-- //footer -->

				
