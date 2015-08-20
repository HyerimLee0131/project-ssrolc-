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
					<form action="/ssrolcmanager/boards/${boardInfo.boardTable}" method="POST">
					<table width="100%" cellSpacing="0" summary="">
						<colgroup>
							<col width="95">
							<col width="300">
							<col width="130">
							<col width="130">
							<col width="*px">
						</colgroup>						
						<tbody>
						<tr>
							<th>제목</th>
							<td colspan="3">
								<div class="td-left">
									<input name='boardTitle' id='boardTitle' type="text" class="text"  style="width: 820px;" value='' />
								</div>
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td class="pd-0" colSpan="3">
								<textarea id='boardContent' name='boardContent' class='ed' cols='94' rows='15'></textarea>
								<script type="text/javascript">
										var CrossEditor = new NamoSE('boardContent');
										CrossEditor.params.Width = "100%";
										CrossEditor.params.UserLang = "auto";
										CrossEditor.params.FullScreen = false;
										CrossEditor.params.UploadFileExecutePath = "/ssrolcmanager/boards/${boardInfo.boardTable}/imgfileupload";
										CrossEditor.EditorStart();
										function OnInitCompleted(e) {
											e.editorTarget.SetBodyValue(document
													.getElementById("boardContent").value);
										}
								</script>
							</td>
						</tr>
						<tr>
						<th>파일첨부</th>
							<td colspan="3">
								<div class="td-left">
									<input name="attachFile" type="file" class='attachFile' size="20"/>
								</div>
							</td>
						</tr>
						</tbody>
					</table>
					</form>
				</div>
				<div class="btn-box float-r">
					<span class="button btn-type-I"><a id="submitBtn" >등록</a></span>
					<span class="button btn-type-J"><a href="/ssrolcmanager/boards/${boardInfo.boardTable}">취소</a></span>
				</div>
			</div>
		</div>
	</div>
	<!-- //container -->
<#include "/ssrolcmanager/footer.ftl">
</div>
</body>
</html>
