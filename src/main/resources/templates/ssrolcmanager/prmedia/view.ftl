<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">
		
		<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/adm/main/root">홈</a> &gt; </li>
						<li><a href="/adm/optional/popuplist">부가관리</a> &gt; </li>
						<li class="current"><a href="/adm/prmedia/lists">홍보영상관리</a></li>
					</ul>
				</div>
					<p class="page-title">홍보영상관리</p>
					 <p></p>
					<div class="tbl-type-D">
							<form action="" method="post">
							  <table width="100%" border="0" cellpadding="0" cellspacing="0" summary="">
								<colgroup>
								<col width="10%">
								<col width="30%">
								<col width="10%">
								<col width="20%">
								<col width="10%">
								<col width="*">
								</colgroup>
								<tbody>
								  <tr>
									<th>글쓴이</th>
									<td><div class="td-left">${prmedia.regId}</div></td>
									<th>등록일</th>
									<td>${prmedia.getRegDate("yyyy-MM-dd HH:mm:ss")}</td>
									<th>조회수</th>
									<td>${prmedia.hitCnt}</td>
								  </tr>
								  <tr>
									<th>제목</th>
									<td colspan="5"><div class="td-left">${prmedia.prTitle}</div></td>
								  </tr>
								  <tr>
									<th>제작년월</th>
									<td colspan="5"><div class="td-left">${prmedia.makeTime}</div></td>
								  </tr>
								  <tr>
									<th>썸네일</th>
									<td colspan="5"><div class="td-left">${prmedia.thumnailName} (${prmedia.thumnailSize}KB)</div></td>
								  </tr>
								  <tr>
									<th>링크</th>
									<td colspan="5"><div class="td-left">${prmedia.mediaLinkUrl}</div></td>
								  </tr>
								  <tr>
									<th>메뉴위치</th>
									<td colspan="5"><div class="td-left">${prmedia.mediaLocation}</div></td>
								  </tr>
								</tbody>
							  </table>
							</form>
					 </div>
					<div class="mgt-20 float-r">
						<span class="button btn-type-I"><a class="w-65" href="/ssrolcmanager/prmedias">목록</a></span>
						<span class="button btn-type-J"><a class="w-65" href="/ssrolcmanager/prmedias/edit/${prmedia.aidx}">수정</a></span>
						<span class="button btn-type-J"><a class="w-65" onclick='$.prmediaDelete("6");' >삭제</a></span>
					</div>
			</div>
		
		</div>
	</div>
	<!-- //container -->
	