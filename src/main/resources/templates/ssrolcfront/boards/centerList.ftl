	<#include "/ssrolcfront/header_new.ftl">
	<#include "/ssrolcfront/left_new.ftl">
	<div class="content">
		<div class="path">
			<span class="home"><a href="/">홈</a></span> &gt; <a href="/ssrolcfront/boards/notice">커뮤니티</a>
			&gt; <a href="/ssrolcfront/boards/${boardInfo.boardTable}">${boardInfo.boardName}</a>
		</div>
		<div class="center_info">
			<h3>${boardInfo.boardName}</h3>
			<div class="clearfix center_list_wrap">
				<div class="good_center_list">
					<div class="good_center">
						<a href="center_view.html"><span class="img"><img src="../images/center_img02.jpg" alt="" /></span><span class="title"><strong>아이들의 성적과 태도가 변화되는</strong> <em>경기광주쌍동점</em></span></a>
					</div>
				</div>
				<div class="center_list">
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
	{{#each articleAndAttachFileList}}
		<li>
			<a href="/ssrolcfront/board/{{boardTable}}/{{articleNo}}">
				<span class="img">
					{{#xIf attachFileList "!=" null}}<img src="/ssrolcmanager/thumbview/{{boardTable}}/{{attachFileList.convertFileName}}/{{attachFileList.fileSize}}">{{/xIf}}
				</span>
				<span class="title"><strong>{{title}}</strong> <em>{{etc1}}</em></span>
			</a>
		</li>
	{{/each}}
</script>
	<#include "/ssrolcfront/footer_new.ftl">