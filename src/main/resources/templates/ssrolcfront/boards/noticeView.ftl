	<#include "/ssrolcfront/header_new.ftl">
	<#include "/ssrolcfront/left_new.ftl">
		<div class="content">
			<div class="path">
				<span class="home"><a href="/">홈</a></span> &gt; <a href="/ssrolcfront/boards/notice">커뮤니티</a>
				&gt; <a href="/ssrolcfront/boards/${boardInfo.boardTable}">${boardInfo.boardName}</a>
			</div>
			<div class="notice_board">
				<h3>${boardInfo.boardName}</h3>
				<table class="notice_view_table" summary="공지사항 : 제목, 첨부파일, 등록일, 내용">
					<caption>${boardInfo.boardName}</caption>
					<colgroup>
						<col style="width: 100px" />
						<col />
						<col style="width: 100px" />
						<col style="width: 150px" />
					</colgroup>
					<thead>
						<tr>
							<th scope="row">제목</th>
							<td>${article.title}</td>
							<th scope="row">등록일</th>
							<td>${article.getRegDate("yyyy-MM-dd HH:mm:ss")}</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">첨부파일</th>
							<td colspan="3">
								<#if attachFiles?has_content>
									<#list attachFiles as attachFile>
										<span class="add_file">
											<a href="/download/${attachFile.attachFileNo}">
											${attachFile.fileName}
											</a>
										</span>
									</#list>
								</#if>
							</td>
						</tr>
						<tr>
							<th scope="row">내용</th>
							<td colspan="3">
								<div class="notice_content">
									${article.content}
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="notice_view_btn">
					<a href="/ssrolcfront/boards/${boardInfo.boardTable}">목록으로</a>
				</div>
		
			</div>
		</div>
<#include "/ssrolcfront/footer_new.ftl">