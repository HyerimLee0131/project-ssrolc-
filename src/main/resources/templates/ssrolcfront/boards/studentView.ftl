	<#include "/ssrolcfront/header_new.ftl">
	<#include "/ssrolcfront/left_new.ftl">
		<div class="content">
			<div class="path">
				<span class="home"><a href="/">홈</a></span> &gt; <a href="/ssrolcfront/boards/notice">커뮤니티</a>
				&gt; <a href="/ssrolcfront/boards/${boardInfo.boardTable}">${boardInfo.boardName}</a>
			</div>
			<div class="student_info_view">
				<h3>${boardInfo.boardName}</h3>
				<table class="student_view_table" summary="우수학생소개 : 제목, 학생명, 조회수, 등록일, 내용">
					<caption>우수학생소개</caption>
					<colgroup>
						<col style="width:100px" />
						<col />
						<col style="width:100px" />
						<col style="width:150px" />
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
							<th scope="row">학생명</th>
							<td>${article.etc3}</td>
							<th scope="row">조회수</th>
							<td>${article.hit}</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="student_view_contents">
									${article.content}
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="student_view_btn">
					<a href="/ssrolcfront/boards/${boardInfo.boardTable}">목록으로</a>
				</div>
			</div>
		</div>
<#include "/ssrolcfront/footer_new.ftl">