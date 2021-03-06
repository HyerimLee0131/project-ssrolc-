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
				<h1 class="mgt-20">* 팝업 파일 위치 : 서버주소(xxx.xxx.xxx.xxx)\jslslc\public_html\popup</h1>
				<div class="tbl-type-E">
					<form id="popupFrm" name="popupFrm" action="/ssrolcmanager/popups" method="POST">
						<table width="100%" cellPadding="0" cellSpacing="0" summary="">
							<colgroup>
								<col width="14%">
								<col width="*%">
							</colgroup>
							<tbody>
								<tr>
									<th>팝업명</th>
									<td>
										<input name="pName" type="text" class="text" id="pName" style="width: 234px;" title="" maxlength="100" value="<#if popup??>${popup.name}</#if>" >
									</td>
								</tr>
								<tr>
									<th>상태</th>
									<td>
										<select name="pState" id="pState" style="width: 80px;" title="" >
											<option value="0" <#if popup?? && popup.state == '0'>selected</#if>>비활성</option>
											<option value="1" <#if popup?? && popup.state == '1'>selected</#if>>활성</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>게재기간</th>
									<td>
										<input name="pStartDate" type="text" class="text datePicker" id="pStartDate" style="width: 104px;" title="" value="<#if popup??>${popup.startDate}</#if>" readonly='readonly' />&nbsp;~&nbsp;
										<input name="pEndDate" type="text" class="text datePicker" id="pEndDate" style="width: 103px;" title="" value="<#if popup??>${popup.endDate}</#if>" readonly='readonly' />
									</td>
								</tr>
								<tr>
									<th>팝업 사이즈</th>
									<td>
	                                	<span style="width:0;">&nbsp;</span>
	                                	<label style="vertical-align: -3px;">width : </label><input name="pSize_width" type="text" class="text" id="pSize_width" style="width: 60px;" title="" value="<#if popup??>${popup.size_width}</#if>" maxlength="4" />&nbsp;&nbsp;&nbsp;&nbsp;
	                                	<label style="vertical-align: -3px;">height : </label><input name="pSize_height" type="text" class="text" id="pSize_height" style="width: 60px;" title="" value="<#if popup??>${popup.size_height}</#if>" maxlength="4" />
	                                </td>
								</tr>
								<tr>
									<th>위치 조정</th>
									<td>
	                                	*두개이상 올라갈때 필요함 (하나일경우 생략)<br/>
	                                	<span style="width:0;">&nbsp;</span>
	                                	<label style="vertical-align: -3px;">top : </label><input name="pLocation_top" type="text" class="text" id="pLocation_top" style="width: 60px;" title="" value="<#if popup??>${popup.location_top}</#if>" maxlength="4" />&nbsp;&nbsp;&nbsp;&nbsp;
	                                	<label style="vertical-align: -3px;">left : </label><input name="pLocation_left" type="text" class="text" id="pLocation_left" style="width: 60px;" title="" value="<#if popup??>${popup.location_left}</#if>" maxlength="4" />
									</td>
								</tr>
								<tr>
									<th>팝업ID</th>
									<td>
										* 활성화중인 다른 팝업 아이디들과 중복되면 안됩니다.<br/>
										<input name="pPopup_id" type="text" class="text" id="pPopup_id" style="width: 234px;" title="" value="<#if popup??>${popup.cookie_id}</#if>" maxlength="100" />
									</td>
								</tr>
								<!--tr>
									<th>팝업 파일명</th>
									<td>
	                               		* 확장자명 .ftl 까지 써주세요.<br/>
	                                	<input name="pFileName" id="pFileName" type="file" class="text" id="pFileName" style="width: 234px;" title="" value="" maxlength="255"/>
	                                	<#if popup??>${popup.fileName}</#if>
	                                </td>
								</tr-->
								<tr>
									<th>팝업 내용</th>
									<td>
										<textarea id='pContent' name='pContent' class='ed' cols='94' rows='15'>
											<#if popup?? && popup.content??>${popup.content}</#if>
										</textarea>
										<script type="text/javascript">
											var CrossEditor = new NamoSE('pContent');
											CrossEditor.params.Width = "100%";
											CrossEditor.params.UserLang = "auto";
											CrossEditor.params.FullScreen = false;
											CrossEditor.params.UploadFileExecutePath = "/ssrolcmanager/popups/imgfileupload";
											CrossEditor.EditorStart();
											function OnInitCompleted(e) {
												e.editorTarget.SetBodyValue(document.getElementById("pContent").value);
											}
										</script>
									</td>
								</tr>
							</tbody>
						</table>
						<input type="hidden" name="pAidx" value="<#if popup?exists>${popup.aidx}</#if>" title="aidx" >
						<input type="hidden" name="writeType" value="<#if popup?exists>1<#else>0</#if>" title="구분값" >
					</form>

					<div class="mgt-20 float-r">
                		<span class="button btn-type-I"><a href="/ssrolcmanager/popups">목록</a></span>
						<span class="button btn-type-J"><a href="javascript:$.popupWrite();" style="cursor:pointer;"><#if popup?exists>수정<#else>등록</#if></a></span>
						<span class="button btn-type-J"><a href="/ssrolcmanager/popups">취소</a></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //container -->
<#include "/ssrolcmanager/footer.ftl">
</div>
</body>
</html>

