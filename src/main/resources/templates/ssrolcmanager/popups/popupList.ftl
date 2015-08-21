<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">

			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/ssrolcmanager/main">홈</a> &gt; </li>
						<li><a href="/ssrolcmanager/popups">부가관리</a> &gt; </li>
						<li class="current"><a href="/ssrolcmanager/popups">팝업관리</a></li>
					</ul>
				</div>
				<p class="page-title">팝업관리</p>
				<div class="mgt-20 search-class">
					<fieldset class="">
						<legend></legend>
						<p>
							<select style="width:75px" title="" name='searchField' id="searchField">
								<option value="name">팝업명</option>
							</select>&nbsp;&nbsp;
							<input name="searchValue" type="text" class="text" id="searchValue" style="width:100px" title="" />
							&nbsp;<span class="button"><input type="button" id="searchBtn" title="" value="검색"></span>
							&nbsp;<span class="button"><input type="button" id="resetBtn" title="" value="초기화"></span>
						</p>
					</fieldset>
				</div>
				<h1 class="mgt-20"></h1>
				<p>* 총 <em class="bor-none"><span id="totalCnt">0</span></em>건</p>
				<div class="tbl-type-D">
		  			<table width="100%" border="0" cellPadding="0" cellSpacing="0" summary="">
						<colgroup>
							<col width="5%">
							<col width="5%">
							<col width="15%">
							<col width="20%">
							<col width="22%">
							<col width="18%">
							<col width="5%">					
							<col width="10%">						
						</colgroup>
						<tbody>
			  				<tr>
								<th><input type="checkbox" id="allCheckBtn" title="" /></th>
								<th>NO</th>
								<th>팝업명</th>
								<th>팝업사이즈</th>
								<th>팝업파일명</th>
								<th>게재기간</th>
								<th>상태</th>
								<th>등록일</th>
			  				</tr>
						</tbody>
						<tbody id="dataList"></tbody>
		  			</table>
					<span class="float-r mgt-10 button btn-type-I"><a href="/ssrolcmanager/popups/new">팝업등록</a></span>
					<span class="float-l mgt-10 button btn-type-J"><a onclick="$.selectDelete();">선택삭제</a></span>
 				</div>
 				<div class="paging" >
					<span id="pageNavi"></span>
				</div>
				<input name='pageNum' id="pageNum" type='hidden' value='1'/>			
			</div>
		</div>
	</div>
	<!-- //container -->
<#include "/ssrolcmanager/footer.ftl">
</div>
<script id="popupsTemplate" type="text/x-handlebars-template">
	{{#each popups}}
	<tr>
		<td><input name="popupCheckBox" type="checkbox" title="" value="{{aidx}}"/></td>
		<td>{{inc @index}}</td>
		<td>
			<p class="tb_1">
				<a href='/ssrolcmanager/popup/{{aidx}}'>{{name}}</a>
			</p>
		</td>
		<td>가로 : {{size_width}}, 세로 : {{size_height}}</td>
		<td>{{fileName}}</td>
		<td>{{startDate}} ~ {{endDate}}</td>
		<td>{{#xIf state "==" 0}}비활성{{else}}활성{{/xIf}}</td>
		<td>{{regDate}}</td>
	</tr>
	{{else}}
	<tr>
		<td colspan="8">데이터가 없습니다.</td>
	</tr>
	{{/each}}
</script>
</body>
</html>

