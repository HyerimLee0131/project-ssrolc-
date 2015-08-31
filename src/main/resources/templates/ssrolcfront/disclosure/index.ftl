<#include "/ssrolcfront/header_new.ftl">
<!-- container -->
		<div class="container" id="container">
			<div class="clearfix">
				<div class="content no_left">
					<div class="path">
						<span class="home"><a href="/">홈</a></span> &gt; <a href="/ssrolcfront/disclosure">정보공개서</a>	
					</div>
					<form action="" name="">
					<div class="etc_agree">
						<p>
							<img src="/public/img/etc/etc_txt01.png" alt="정보공개서 내용" />
						</p>
						<div class="info_agree_list">
							<ul class="clear">
								<li>
									<label for="pMemName">이름</label>
									<input type="text" class="input-text w200" name="pMemName" id="pMemName" />
								</li>
								<li>
									<label for="pEmail01">이메일</label>
									<input type="text" class="input-text w150" name="pEmailId" id="pEmailId" title="이메일 아이디" /> @
									<input type="text" class="input-text w150" name="pEmailAdd1" id="pEmailAdd1" title="이메일 주소" readonly /> 
									<select name="pEmailAdd2" id="pEmailAdd2" title="이메일 주소 선택" class="input-select w150">
										<option value="">선택하세요</option>
										<option value="inputEmail">직접입력</option>
										<option value="gmail.com">gmail.com</option>
										<option value="hanmail.net">hanmail.net</option>
										<option value="hotmail.com">hotmail.com</option>
										<option value="naver.com">naver.com</option>
										<option value="nate.com">nate.com</option>
										<option value="jei.com">jei.com</option>
									</select>
									<a href="javascript:$.emailAuthSubmit();" class="btn_email_confirm">이메일인증하기</a>
						
								</li>
								<li>
									<label for="pAuthCode">인증번호</label>
									<input type="text" class="input-text w100" name="pAuthCode" id="pAuthCode" />
									<a href="javascript:$.AuthKeyChk();" class="btn_code_confirm">인증번호입력</a>
									<span id="emailOk" class="code_ok" style="display:none;">*이메일 인증이 성공적으로 완료되었습니다.</span>
								</li>
							</ul>
						</div>
						<input type="hidden" id="emailAuthYN" value="N">
						<!-- 개인정보수집동의 -->
						<div class="etc_agree_chk">
							<h4>개인정보의 수집 및 이용동의</h4>
							<div class="txt">
								<p>당사는 귀하의 개인정보보호를 매우 중요시하며,『개인정보보호법』상의 개인정보보호 규정을 준수하고 있습니다.</p>
								<dl class="clear">
									<dt>1. 개인정보의 수집•이용 목적</dt>
									<dd>- 정보공개서 제공을 위한 수집</dd>
									<dt>2. 수집•이용하는 개인정보 항목</dt>
									<dd>- 필수항목 : 이름, 이메일</dd>
									<dt>3. 개인정보의 보유 및 이용기간</dt>
									<dd>
										<p>회사는 고객이 개인정보의 수집 및 이용에 대한 동의를 철회하는 때까지 고객이 제공한 모든 개인 정보를 보유 및 이용하게 되며 고객이 이용동의를 철회하는 경우에는 고객의 개인 정보를 재생 불가능한 방법으로 지체 없이 저장 장치에서 삭제하여 어떠한 방법으로도 열람 또는 이용할 수 없도록 만듭니다. </p>
										<p>다만, 관계법령에서 일정한 보관기간을 정한 경우는 예외로 합니다. 이 경우 회사는 보관하는 정보를 그 보관의 목적으로만 이용하며 보존기간은 아래와 같습니다.</p>
										<ul class="clear">
											<li>- 소비자의 불만 또는 분쟁처리에 관한 기록 : 3년</li>
											<li>- 방문에 관한 기록 : 3개월</li>
										</ul>
									</dd>
								</dl>
							</div>
							<div class="chk_wrap">
								<label><input type="checkbox" name="agreeChk" value="1" />개인정보수집 및 이용에 동의합니다.</label>
							</div>
						</div>
						<!-- //개인정보수집동의 -->
						<div class="etc_agree_btn">
							<a href="#" class="btn_info_read"><span>정보공개서열람</span></a>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
		<!-- //container -->
		<!-- 정보공개서 열람 레이어팝업 -->
		<div class="layer_pop_wrap">
			<div class="form_info_confirm">
				<form action="/ssrolcfront/disclosure" method="post" name="frm2" id="frm2">
				<h4>정보공개서 열람</h4>
				<!-- 정보공개서 열람하기 전 확인	 -->	
				<div class="info_confirm_wrap">
					<h5>정보공개서 열람하기 전 확인하세요!!!</h5>
					<p><img src="/public/img/etc/etc_txt02.gif" alt="" /></p>
				</div>
				<!-- //정보공개서 열람하기 전 확인 -->
				<!-- 개인정보 동의 -->
				<div class="info_confirm_wrap">
					<h5>개인정보 동의</h5>
					<div class="agree_chk">
						<div class="txt">
							<p>개인정보 항목 및 수집방법</p>
							<p>■ 개인정보 수집·이용 및 제공 동의</p>
							<dl class="clear">
								<dt>1. 개인정보의 수집 및 이용 목적</dt>
								<dd>- 정보공개서 열람자(가맹희망자) 상담서비스 제공을 위한 수집</dd>
								<dt>2. 수집·이용하는 개인정보 항목</dt>
								<dd>
									<p>[온라인문의]</p>
									<p>- 필수항목 : 이름, 연락처, 이메일, 우편번호, 주소, 가맹희망지역, 가맹교실유형</p>
								</dd>
								<dt>3. 개인정보의 보유 및 이용기간</dt>
								<dd>
									<p>원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. </p>
									<p>단, 다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다.</p>
									<p>[내부 방침에 의한 정보보유 사유]</p>
									<ul class="clear">
										<li>보존 항목 : 이름, 전화번호, 주소</li>
										<li>보존 근거 : 부정 이용 방지</li>
										<li>보존 기간 : 1년</li>
									</ul>
									<p>[관계법령에 의한 정보보유 사유]</p>
									<p>관계법령의 규정에 의하여 보존할 필요가 있는 경우 회사는 아래와 같이 관계법령에서 정한 일정한 기간 동안 개인정보를 보관합니다.</p>
									<ul class="clear">
										<li>- 방문에 관한 기록 : 3개월</li>
										<li>- 설문조사, 이벤트 등 일시적 목적을 위하여 수집한 경우 : 당 설문조사, 이벤트 등이 종료된 때</li>
									</ul>
								</dd>
							</dl>
						</div>
						<div class="chk_wrap">
							<label><input type="radio" name="infoAgree" value="Y" /> 동의함</label>
							<label><input type="radio" name="infoAgree" value="N" checked /> 동의하지않음</label>
						</div>
					</div>
				</div>
				<!-- //개인정보 동의 -->
				<div class="info_confirm_wrap">
					<h5>가맹희망자 상세정보 확인</h5>
					<p class="notice_txt"><span>가맹상담을 원하시면 아래 정보를 입력해주세요.</span></p>
					<table class="etc_info_table">
						<colgroup>
							<col style="width:120px" />
							<col />
							<col style="width:120px" />
							<col />
						</colgroup>
						<thead>
							<tr>
								<th scope="row">이름</th>
								<td><input type="text" id="name" name="memName" value="" /></td>
								<th scope="row">이메일</th>
								<td><input type="text" id="email" name="email" value="" /></td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row"><label for="formTel01">연락처</label></th>
								<td colspan="3">
									<select id="formTel01" name="formTel01" title="지역번호 or 통신사번호" class="input-select w100">
										<option value="select">선택</option>
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="016">016</option>
										<option value="017">017</option>
										<option value="018">018</option>
										<option value="019">019</option>
										<option value="02">02</option>
										<option value="031">031</option>
										<option value="032">032</option>
										<option value="033">033</option>
										<option value="041">041</option>
										<option value="042">042</option>
										<option value="043">043</option>
										<option value="051">051</option>
										<option value="052">052</option>
										<option value="053">053</option>
										<option value="054">054</option>
										<option value="055">055</option>
										<option value="061">061</option>
										<option value="062">062</option>
										<option value="063">063</option>
										<option value="064">064</option>
										<option value="070">070</option>
									</select> - 
									<input type="text" class="input-text w80 " id="formTel02" name="formTel02" maxlength=4 title="전화번호 가운데자리" /> - 
									<input type="text" class="input-text w80 " id="formTel03" name="formTel03" maxlength=4 title="전화번호 마지막자리" />
									<input type="hidden" id="phone" name="phone" value="">
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="formPost">우편번호</label></th>
								<td colspan="3">
									<input name="zip" type="text" class="input-text w80" id="formPost" title="우편번호" readonly="readonly" /> 
									
									<a href="javascript:$.openPopup('/post','postSearch',400,500,10,30);" class="btn_address_search">우편번호검색</a>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="formAddr01">주소</label></th>
								<td colspan="3">
									<span class="input-wrap">
										<input name="addressDtl01" type="text" class="input-text w98p" id="addressDtl01" readonly="readonly" />
									</span>
									<span class="input-wrap">
										<input name="addressDtl02" type="text" class="input-text w98p" id="addressDtl02" title="상세주소" />
									</span>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="hopeArea01">가맹희망지역</label></th>
								<td>
									<select id="hopeArea01" title="지역선택" name="deptArea1" class="input-select w120">
										<option value="select">지역선택</option>
										<#list cityList as city>
											<option value = "${city}">${city}</option>
										</#list>
									</select>
									<select id="hopeArea02" title="지역선택" name="deptArea2" class="input-select w120" >
										<option value="select">지역선택</option>
									</select>
								</td>
								<th scope="row"><label for="formClass">가맹교실유형</label></th>
								<td>
									<span class="radio_wrap">
										<label><input type="checkbox" class="" id="formClass01" name="deptType" title="" value="공부방"> 공부방</label>
										<label><input type="checkbox" class="" id="formClass02" name="deptType" title=""   value="학원"> 학원</label>
										<label><input type="checkbox" class="" id="formClass03" name="deptType" title=""   value="교습소"> 교습소</label>
									</span>
									
								</td>
						</tbody>
					</table>
					<div class="etc_info_btn">
						<a href="javascript:$.joinWrite();" class="btn_read_fin">정보공개서열람</a>
					</div>
				</div>
				</form>
				<a href="" class="btn_pop_close">닫기</a>
			</div>
		</div>
	</div>
<#include "/ssrolcfront/footer_new.ftl">
