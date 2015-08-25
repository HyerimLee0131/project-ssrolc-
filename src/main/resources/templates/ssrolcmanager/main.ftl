<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">
			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/ssrolcmanager/main">홈</a></li>
					</ul>
				</div>
				<div class="mgt-20 search-class">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="">
                    	<tr>
                    		<td width="25%" rowspan="3" style="text-align:center;"><img src="/public/img/admin/common/bill_analysis.gif" alt="" style="margin:-10px 0 0 0;"/></td>
                        	<td width="75%" height="25"><strong>! 2015-08-25일 스스로러닝센터 관리자 사이트 방문을 환영합니다.</strong></td>
                    	</tr>
                        <tr>
                        	<td width="80%" height="25"><span style="color:#c00">▶</span> 금일 신규게시물 수 (가맹문의 <span style="color:#c00"><strong>0</strong></span>건, 공지사항 <span style="color:#c00"><strong>0</strong></span>건, 정보공개서 열람 <span style="color:#c00"><strong>0</strong></span>건)</td>
                        </tr>
                    </table>
				</div>
				<div class="top_tit">
					<h1>◎ 가맹문의</h1>
					<p class="mgb-5 float-r"><a href="/ssrolcmanager/franchise/faqs" style="color:#777;"><img src="/public/img/admin/common/icon_more.gif" alt="더보기" />&nbsp;더보기</a></p>
				</div>
				<div>
					<table width="100%" border="0" cellPadding="0" cellSpacing="0"
						class="tbl-type-F" summary="">
						<colgroup>
							<col width="15%">
							<col width="7%">
							<col width="13%">
							<col width="15%">
							<col width="10%">
							<col width="15%">
							<col width="10%">
							<col width="15%">
						</colgroup>
						<tbody>
							<tr>
								<th>상담신청일</th>
								<th>경로</th>
								<th>이름</th>
								<th>가맹희망지역</th>
								<th>교실유형</th>
								<th>가맹희망일</th>
								<th>희망상담시간</th>
								<th>휴대전화</th>
							</tr>
							<#if franchiseList?has_content>
								<#list franchiseList as franchise>
									<tr>
										<td>${franchise.getJslipDatetime("yyyy-MM-dd")}</td>
										<td>${franchise.inType}</td>
										<td>${franchise.jslcName}</td>
										<td>${franchise.jslcArea1}&nbsp;${franchise.jslcArea2}</td>
										<td>${franchise.jslcType}</td>
										<td>${franchise.jslcDate}</td>
										<td>${franchise.jslcTime1}&nbsp;${franchise.jslcTime2}</td>
										<td>${franchise.jslcCP1}-${franchise.jslcCP2}-${franchise.jslcCP3}</td>
									</tr>
								</#list>
							<#else>
							<tr>
								<td colspan="8">데이터가 없습니다.</td>
							</tr>
							</#if>
						</tbody>
					</table>
				</div>
				<br/>
				<div class="top_tit">
					<h1>◎ 공지사항</h1>
					<p class="mgb-5 float-r">
						<a href="/ssrolcmanager/boards/notice" style="color: #777;"><img
							src="/public/img/admin/common/icon_more.gif" alt="더보기" />&nbsp;더보기</a>
					</p>
				</div>
				<div>
					<table width="100%" cellSpacing="0" class="tbl-type-F" summary="">
						<colgroup>
							<col width="70%">
							<col width="10%">
							<col width="15%">
							<col width="5">
						<tbody>
							<tr>
								<th>제목</th>
								<th>글쓴이</th>
								<th>등록일</th>
								<th>조회수</th>
							</tr>
							<#if articleList?has_content>
								<#list articleList as article>
								<tr>
									<td><div class="td-left">
											<a href="/ssrolcmanager/board/${article.boardTable}/${article.articleNo}">${article.title}</a>
										</div></td>
									<td>${article.regId}</td>
									<td>${article.getRegDate("yyyy-MM-dd")}</td>
									<td>${article.hit}</td>
								</tr>
								</#list>
							<#else>
							<tr>
								<td colspan="4">데이터가 없습니다.</td>
							</tr>
							</#if>
						</tbody>
					</table>
				</div>
				<br/>
				<div class="top_tit">
					<h1>◎ 정보공개서 열람</h1>
					<p class="mgb-5 float-r"><a href="/ssrolcmanager/disclosures" style="color:#777;"><img src="/public/img/admin/common/icon_more.gif" alt="더보기" />&nbsp;더보기</a></p>
				<div>
					<table width="100%" border="0" cellPadding="0" cellSpacing="0"
						class="tbl-type-D" summary="">
						<colgroup>
							<col width="15%">
							<col width="15%">
							<col width="20%">
							<col width="15%">
							<col width="15%">
							<col width="20%">
						</colgroup>
						<tbody>
							<tr>
								<th>정보공개서확인일</th>
								<th>이름</th>
								<th>가맹희망지역</th>
								<th>가맹교실유형</th>
								<th>연락처</th>
								<th>이메일</th>
							</tr>
							<#if disclosureList?has_content>
								<#list disclosureList as disclosure>
								<tr>
									<td>${disclosure.getRegDate("yyyy-MM-dd")}</td>
									<td>${disclosure.memName}</td>
									<td>${disclosure.deptArea1}&nbsp;${disclosure.deptArea2}</td>
									<td>${disclosure.deptType}</td>
									<td>${disclosure.phone}</td>
									<td>${disclosure.email}</td>
								</tr>
								</#list>
							<#else>
								<tr>
									<td colspan="6">데이터가 없습니다.</td>
								</tr>
							</#if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- //container -->
<#include "/ssrolcmanager/footer.ftl">
</div>
</body>
</html>