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
				<h1 class="mgt-20"></h1>
				<p class="page-title">${boardInfo.boardName}</p>
				<div class="tbl-type-F">
					<form id="boardFrm" action="/ssrolcmanager/board/${boardInfo.boardTable}/${article.articleNo}" method="POST" enctype="multipart/form-data">
						<input name="deleteAttachFiles" id="deleteAttachFiles" type="hidden" value="">
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
											<input name='boardTitle' id='boardTitle' type="text" class="text"  style="width: 820px;" title="제목" value='${article.title}' />
										</div>
									</td>
								</tr>
								<tr>
									<th>상태</th>
									<td colspan="3">
										<div class="td-left">
											<select name="useEnable" id="useEnable" title="상태">
												<option value="">선택하세요</option>
												<option value="1" <#if useEnable?exists && article.useEnable == '1'>selected</#if>>활성</option>
												<option value="0" <#if useEnable?exists && article.useEnable == '0'>selected</#if>>비활성</option>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<th>내용</th>
									<td class="pd-0" colSpan="3">
										<textarea id='boardContent' name='boardContent' class='ed' cols='94' rows='15'>
											${article.content}
										</textarea>
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
								<th>썸네일 이미지</th>
									<td colspan="3">
											<#if attachFiles?has_content>
												<#list attachFiles as attachFile>
													<div class="td-left" id="fileDiv${attachFile_index}">
														<span id="fileSpan${attachFile_index}">${attachFile.fileName}</span>
														&nbsp;
														<input type="button" onclick='$.deleteFile("${attachFile_index}","${attachFile.attachFileNo}");' value="삭제">
													</div>
												</#list>
											<#else>
												<div class="td-left">
													<input name="thumbAttachFile" type="file" class='attachFile' size="20"/>
												</div>
											</#if>
									</td>
								</tr>
								<tr>
									<th>센터명</th>
									<td colspan="3">
										<div class="td-left">
											<input id="etc1" name="etc1" type="text" class="text" style="width: 820px;" title="센터명" value='${article.etc1}' />
										</div>
									</td>
								</tr>
								<tr>
									<th>센터소개</th>
									<td colspan="3">
										<div class="td-left">
											<input id="etc2" name="etc2" type="text" class="text" style="width: 820px;" title="센터소개" value='${article.etc2}' />
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="btn-box float-r">
					<span class="button btn-type-I"><a id="submitBtn" >수정</a></span>
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
