	<#include "/ssrolcfront/header_new.ftl">
	<#include "/ssrolcfront/left_new.ftl">
	<div class="content">
		<div class="path">
			<span class="home"><a href="/">홈</a></span> &gt; <a href="/ssrolcfront/boards/notice">커뮤니티</a>
			&gt; <a href="/ssrolcfront/boards/${boardInfo.boardTable}">${boardInfo.boardName}</a>
		</div>
		<div class="student_info">
			<h3>${boardInfo.boardName}</h3>
			<div class="clearfix student_list_wrap">
				<div class="student_list">
					<ul class="clear" id=dataList></ul>
				</div>
			</div>
			<!-- 페이징 -->
			<div class="pageNavi" id="pageNavi"></div>
			<!-- //페이징 -->
			<!-- 게시판검색 -->
			<div class="board_search_wrap">
				<input name='boardTable' id="boardTable" type='hidden' value='${boardInfo.boardTable}'/>
				<input name='pageNum' id="pageNum" type='hidden' value='1'/>
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
		<li>
			<div class="line">
				<h4>{{title}}</h4>
				<p>{{etc4}}</p>
				<div class="info_wrap clearfix">
					<div class="img_list clearfix">
						{{#each attachFileList}}
							{{#xIf convertFileName "!=" null}}<span class="img"><img src="/ssrolcfront/thumbview/{{boardTable}}/{{convertFileName}}/{{fileSize}}" alt="" /></span>{{/xIf}}
						{{/each}}
					</div>
					<div class="info_list">
						<ul class="clear">
							<li><strong>학생명 : </strong> {{etc3}}</li>
							<li><strong>센터명 : </strong> {{etc1}}</li>
							<li><strong>센터장명 : </strong> {{etc2}}</li>
						</ul>
						<div class="link"><a href="/ssrolcfront/board/{{boardTable}}/{{articleNo}}">우수학생소개 자세히 보기</a></div>
					</div>
				</div>
			</div>
		</li>
	{{/each}}
</script>
	<#include "/ssrolcfront/footer_new.ftl">
