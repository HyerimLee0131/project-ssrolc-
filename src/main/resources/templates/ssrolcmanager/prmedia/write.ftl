<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">

			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/adm/main/root">홈</a> &gt; </li>
						<li><a href="/adm/optional/popuplist">부가관리</a> &gt; </li>
						<li class="current"><a href="/adm/prmedia/lists">홍보영상관리</a></li>
					</ul>
				</div>
				<p class="page-title">홍보영상관리</p>
					 <p></p>
					<div class="tbl-type-D">
							<form id="prmediaFrm" name="prmediaFrm" action="/ssrolcmanager/prmedias/write"  method="post" enctype="multipart/form-data">
							  <table width="100%" border="0" cellpadding="0" cellspacing="0" summary="">
								<colgroup>
								<col width="15%">
								<col width="*">							
								</colgroup>
								<tbody>
								  <tr>
									<th>제목</th>
									<td><div class="td-left"><input name="prTitle" type="text" class="text" id="prTitle" style="width:500px;" title="" maxlength="100" /></div></td>
								  </tr>	
								   <tr>
									<th>제작년월</th>
									<td><div class="td-left"><input name="makeTime" type="text" class="text datePicker" id="makeTime" style="width:80px;" title="" readonly='readonly' /><img src="/public/img/admin/common/icon_calendar.gif" alt="" class="vertical-m" id="makeTime_img" /></div></td>
								  </tr>
								  <tr>
									<th>썸네일</th>
									<td><div class="td-left"><input name="thumbAttachFile" type="file"  id="thumnailName" title="" /></div></td>
								  </tr>
								   <tr>
									<th>링크#1, mp4파일</th>
									<td><div class="td-left"><input name="mediaLinkUrl" type="text" class="text" id="mediaLinkUrl" style="width:330px;" title="" maxlength="200" /></div></td>
								   </tr>
								    <tr>
									<th>위치</th>
									<td>
										<div class="td-left">
											<select name="mediaLocation" id="mediaLocation" style="width:90px;" title="">
												<option value="quick" >퀵</option>
												<option value="center" >중앙</option>
												<option value="etc" >기타</option>
											</select>
										</div>
									</td>
								   </tr>
								</tbody>
							  </table>
							</form>
					 </div>
					 <div class="btn-box float-r">
						<span class="button btn-type-I"><a onclick="$.prmediaWrite();" style="cursor:pointer;"  >등록</a></span>
						<span class="button btn-type-J"><a href="/ssrolcmanager/prmedias">취소</a></span>
					</div>
					
				</div>
			</div>
		</div>
	<!-- //container -->
					