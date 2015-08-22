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
										<input name="startDate" type="text" class="text datePicker" id="startDate" style="width: 104px;" title="" value="<#if popup??>${popup.startDate}</#if>" readonly='readonly' />&nbsp;~&nbsp;
										<input name="endDate" type="text" class="text datePicker" id="endDate" style="width: 103px;" title="" value="<#if popup??>${popup.endDate}</#if>" readonly='readonly' />
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
	                                	<label style="vertical-align: -3px;">top : </label><input name="location_top" type="text" class="text" id="location_top" style="width: 60px;" title="" value="<#if popup??>${popup.location_top}</#if>" maxlength="4" />&nbsp;&nbsp;&nbsp;&nbsp;
	                                	<label style="vertical-align: -3px;">left : </label><input name="location_left" type="text" class="text" id="location_left" style="width: 60px;" title="" value="<#if popup??>${popup.location_left}</#if>" maxlength="4" />
									</td>
								</tr>
								<tr>
									<th>팝업ID</th>
									<td>
										* 활성화중인 다른 팝업 아이디들과 중복되면 안됩니다.<br/>
										<input name="pPopup_id" type="text" class="text" id="pPopup_id" style="width: 234px;" title="" value="<#if popup??>${popup.cookie_id}</#if>" maxlength="100" />
									</td>
								</tr>
								<tr>
									<th>팝업 파일명</em></th>
									<td>
	                               		* 확장자명 .ftl 까지 써주세요.<br/>
	                                	<input name="pFileName" id="pFileName" type="file" class="text" id="pFileName" style="width: 234px;" title="" value="" maxlength="255"/>
	                                	<#if popup??>${popup.fileName}</#if>
	                                </td>
								</tr>
							</tbody>
						</table>
					</form>

					<div class="mgt-20 float-r">
                		<span class="button btn-type-I"><a href="/ssrolcmanager/popups">목록</a></span>
						<span class="button btn-type-J"><a href="javascript:$.popupWrite();" style="cursor:pointer;">등록</a></span>
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

