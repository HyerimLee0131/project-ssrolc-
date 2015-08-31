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
					<li><a href="javascript:;" category="all" <#if searchCategory == "all"> class="on" </#if>>전체</a></li>
					<#if boardCategoryList?has_content>
						<#list boardCategoryList as boardCategory>
							<li><a href="javascript:;" category="${boardCategory.categoryCode}" <#if searchCategory == boardCategory.categoryCode> class="on" </#if> >${boardCategory.categoryName}</a></li>
						</#list>
					</#if>
				</ul>
			</div>
			<div class="info">총 <span id="totalCnt">0</span>건</div>
			<table class="faq_board_table">
				<caption>공지사항</caption>
				<colgroup>
					<col style="width: 100px" />
					<col style="width: 100px" />
					<col />
					<col style="width: 100px" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col">NO</th>
						<th scope="col">분류</th>
						<th scope="col">제목</th>
						<th scope="col">등록일</th>
					</tr>
				</thead>
				<tbody id=dataList></tbody>	
			</table>
			<!-- 페이징 -->
			<div class="pageNavi" id="pageNavi">
			</div>
			<!-- //페이징 -->
			<!-- 게시판검색 -->
			<div class="board_search_wrap">
				<input name='boardTable' id="boardTable" type='hidden' value='${boardInfo.boardTable}'/>
				<input name='pageNum' id="pageNum" type='hidden' value='1'/>
				<input name='categoryCode' id="categoryCode" type='hidden' value='${searchCategory}'/>
				<div class="board_search">
					<select name='searchField' id="searchField" class="input-select w120" title="검색조건선택">
						<option  value='title'>제목</option>
						<option  value='content'>내용</option>
						<option  value='title+content'>제목+내용</option>
					</select>
					<input name='searchValue' id="searchValue" class="input-text w200" title="검색어 입력창"  value='' maxlength='15'/>
					<input id="searchBtn" type="button" class="input-button" title="검색버튼" value="검색" />
				</div>
			</div>
			<!-- //게시판검색 -->
		</div>
	</div>
<script id="boardsTemplate" type="text/x-handlebars-template">
	{{#each articles}}
	<tr>
		<td class="center">{{inc @index}}</td>
		<td class="center">{{convertCate categoryCode}}</td>
		<td>
			<a href="javascript:$.viewArticle('{{boardTable}}',{{articleNo}})"; class="ellipsis">{{title}}</a>
		</td>
		<td class="center">{{prettifyDate regDate}}</td>
	</tr>
	{{else}}
	<tr>
		<td colspan="4" class="center">데이터가 없습니다.</td>
	</tr>
	{{/each}}
</script>
	<#include "/ssrolcfront/footer_new.ftl">