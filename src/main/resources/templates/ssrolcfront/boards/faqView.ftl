	<#include "/ssrolcfront/header_new.ftl">
	<#include "/ssrolcfront/left_new.ftl">
		<div class="content">
			<div class="path">
				<span class="home"><a href="/">홈</a></span> &gt; <a href="/ssrolcfront/boards/notice">커뮤니티</a>
				&gt; <a href="/ssrolcfront/boards/${boardInfo.boardTable}">${boardInfo.boardName}</a>
			</div>
			<div class="faq_board">
				<h3>${boardInfo.boardName}</h3>
				<div class="faq_cate">
					<ul class="clear clearfix">
						<li><a href="javascript:;" category="all" <#if searchCategory == "all"> class="on" </#if>  >전체</a></li>
						<#if boardCategoryList?has_content>
							<#list boardCategoryList as boardCategory>
								<li><a href="javascript:;" category="${boardCategory.categoryCode}" <#if searchCategory == boardCategory.categoryCode> class="on" </#if> >${boardCategory.categoryName}</a></li>
							</#list>
						</#if>
					</ul>
				</div>
				<table class="faq_view_table" summary="자주묻는질문 : 분류, 제목, 내용">
					<caption>${boardInfo.boardName}</caption>
					<colgroup>
						<col style="width:100px" />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th scope="row">분류</th>
							<td>${boardCategoryName}</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">제목</th>
							<td>${article.title}</td>
						</tr>
						<tr>
							<th scope="row">내용</th>
							<td>
								<div class="faq_board_contents">
								${article.content}
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="faq_view_btn">
					<a href="/ssrolcfront/boards/${boardInfo.boardTable}">목록으로</a>
				</div>
			</div>
		</div>
<#include "/ssrolcfront/footer_new.ftl">