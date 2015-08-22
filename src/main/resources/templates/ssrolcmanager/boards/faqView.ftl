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
							<col width="80">
							<col width="*">
						</colgroup>
						<tbody>
							<tr>
								<th>분류</th>
								<td>
									<div class="td-left">${boardCategoryName}</div>
								</td>							
							</tr>
							<tr>
								<th>제목</th>
								<td>
									<div class="td-left">${article.title}</div>
								</td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
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
