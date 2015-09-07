<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">

			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/ssrolcmanager/main">홈</a> &gt; </li>
						<li><a href="/ssrolcmanager/boards/center">게시판관리</a> &gt; </li>
						<li class="current"><a href="/ssrolcmanager/boards/${boardInfo.boardTable}">${boardInfo.boardName}</a></li>
					</ul>
				</div>
				<p class="page-title">${boardInfo.boardName}</p>
				<div class="mgt-20 search-class">
					<input name='boardTable' id="boardTable" type='hidden' value='${boardInfo.boardTable}'/>
					<input name='pageNum' id="pageNum" type='hidden' value='1'/>
					<fieldset>
						<legend></legend>
						<p>
							<select name='searchField' id="searchField" style="width:75px;" title="검색조건선택">
								<option value='title'>제목</option>
								<option value='content'>내용</option>
								<option value='title+content'>제목+내용</option>
							</select>
							<input name='searchValue' id="searchValue" type="text" class="text" style="width:300px;" title="" value='' />
							<span class="button"><input type="button" id="searchBtn" title="검색버튼" value="검색" /></span>
						</p>
					</fieldset>
				</div>
				<h1 class="mgt-20"></h1>
				<p>* 총 <strong id="totalCnt">0</strong>건</p>
				<div class="tbl-type-D">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="">
						<colgroup>
							<!--col width="5%"-->
							<col width="5%">
							<col width="5%">
							<col width="10%">
							<col width="*%">
							<col width="15%">
							<col width="10%">	
						</colgroup>
						<thead>
							<tr>
								<!--th><input type="checkbox" id='allcheck' title="" /></th-->
								<th>NO</th>
								<th>상태</th>
								<th>썸네일</th>
								<th>제목</th>
								<th>센터명</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody id=dataList></tbody>
					</table>
				</div>
				<div class="paging">
					<span id="pageNavi"></span>
				</div>
				<div class="btn-box float-l">
					<!--span class="button btn-type-J"><a href="javascript:;" onclick="javascript:select_delete();">선택삭제</a></span-->
					<span class="button btn-type-I"><a href="/ssrolcmanager/boards/center/new">글쓰기</a></span>
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
			<!--td><input name='boardCheckBox' type='checkbox' title="" value='{{articleNo}}'/></td-->
			<td>{{inc @index}}</td>
			<td>{{#xIf useEnable "==" 0}}비활성{{else}}활성{{/xIf}}</td>
			<td>
				{{#each attachFileList}}
					{{#if @first}}
						{{#xIf convertFileName "!=" null}}<img src="/ssrolcmanager/thumbview/{{boardTable}}/{{convertFileName}}/{{fileSize}}" style="width:100px;">{{/xIf}}
					{{/if}}
				{{/each}}
			</td>
			<td>
				<a href='/ssrolcmanager/board/{{boardTable}}/{{articleNo}}'>{{title}}</a>
				<img src='/public/img/img_inc/icon_file.gif' title='파일' alt='파일'/>  <img src='/public/img/img_inc/ico_hot.gif' title='이슈' alt='이슈'/>
			</td>
			<td>{{etc1}}</td>
			<td>{{prettifyDate regDate}}</td>
		</tr>
		{{else}}
		<tr>
			<td colspan="7">데이터가 없습니다.</td>
		</tr>
		{{/each}}
	</script>
	</body>
	</html>