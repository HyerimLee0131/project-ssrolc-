<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">
			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/ssrolcmanager/main">홈</a> &gt; </li>
						<li><a href="/ssrolcmanager/boards/notice">게시판관리</a> &gt; </li>
						<li class="current"><a href="/ssrolcmanager/boards/${boardInfo.boardTable}">${boardInfo.boardName}</a></li>
					</ul>
				</div>	
				<p class="page-title">${boardInfo.boardName}</p>
				<h1 class="mgt-20"></h1>
				<p>* 총 <strong id="totalCnt">0</strong>건</p>
				<div class="tbl-type-F">
					<table width="100%" cellSpacing="0" summary="">
						<colgroup>
							<col width="95">
							<col width="*">
							<col width="70">
							<col width="90">
						<thead>						
							<tr>
								<th>NO</th>								
								<th>제목</th>
								<th>첨부파일</th>
								<th>등록일</th>							
							</tr>
						</thead>
						<tbody id=dataList></tbody>					
					</table>
				</div>
				<div class="btn-box float-r">
					<span class="button btn-type-I"><a href="/ssrolcmanager/boards/notice/new">글쓰기</a></span>
				</div>
				<div class="paging">
					<span id="pageNavi"></span>
				</div>
				<div class="board-searchBox">
					<input name='boardTable' id="boardTable" type='hidden' value='${boardInfo.boardTable}'/>
					<input name='pageNum' id="pageNum" type='hidden' value='1'/>
					<fieldset class="board-search">
					<select name='searchField' id="searchField" class="formSelect" title="검색조건선택">
						<option  value='title'>제목</option>
						<option  value='content'>내용</option>
						<option  value='title+content'>제목+내용</option>
					</select>
					<input name='searchValue' id="searchValue" class="text"  style="width:200px;" value='' maxlength='15'/>
					<span class="button"><input id="searchBtn" type="button" title="검색버튼" value="검색" /></span>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
	<!-- //container -->
<#include "/ssrolcmanager/footer.ftl">
</div>
<script id="boardsTemplate" type="text/x-handlebars-template">
	{{#each articles}}
	<tr>
		<td>{{inc @index}}</td>
		<td>
			<p class="tb_1">
				<a href='/ssrolcmanager/board/{{boardTable}}/{{articleNo}}'>{{title}}</a>
			</p>
		</td>
		<td>{{#xIf fileCnt ">" 0}}<img src="/public/img/img_inc/icon_file.gif" title="파일" alt="파일">{{/xIf}}</td>
		<td>{{prettifyDate regDate}}</td>
	</tr>
	{{else}}
	<tr>
		<td colspan="4">데이터가 없습니다.</td>
	</tr>
	{{/each}}
</script>
</body>
</html>
