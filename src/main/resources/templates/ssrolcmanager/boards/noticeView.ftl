<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">
			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/ssrolcmanager/main">홈</a> &gt;</li>
						<li><a href="/ssrolcmanager/boards/notice">게시판관리</a> &gt;</li>
						<li class="current"><a
							href="/ssrolcmanager/boards/${boardInfo.boardTable}">${boardInfo.boardName}</a></li>
					</ul>
				</div>
				<p class="page-title">${boardInfo.boardName}</p>
				<h1 class="mgt-20"></h1>
				<div class="tbl-type-F">
					<table width="100%" cellSpacing="0" summary="">
						<colgroup>
							<col width="100">
							<col width="*">
							<col width="80">
							<col width="120">
						</colgroup>
						<tbody>
							<tr>
								<th>제목</th>
								<td>
									<div class="td-left">${article.title}</div>
								</td>
								<th>등록일</th>
								<td>${article.getRegDate("yyyy-MM-dd HH:mm:ss")}</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="3">
									<#if attachFiles?has_content>
										<#list attachFiles as attachFile>
											<p class="tb_1">
												<a href="/download/${attachFile.attachFileNo}" class="j_line">
												${attachFile.fileName}
												</a>
												<img src="/public/img/img_inc/icon_file.gif" alt="첨부파일">
											</p>
										</#list>
									</#if>
								</td>
							</tr>
							<tr>
								<th>내용</th>
								<td colspan="3">
									<div class="td-left">${article.content}</div>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="mgt-20">
						<span class="button btn-type-J">
							<a href="/ssrolcmanager/board/${boardInfo.boardTable}/edit/${article.articleNo}">수정</a>
						</span>
						<span class="button btn-type-J">
							<a onclick="$.boardDelete('${boardInfo.boardTable}','${article.articleNo}');">삭제</a>
						</span>
						<span class="button float-r btn-type-I">
							<a href="/ssrolcmanager/boards/${boardInfo.boardTable}">목록</a>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //container -->
<#include "/ssrolcmanager/footer.ftl">
</div>
</body>
</html>
