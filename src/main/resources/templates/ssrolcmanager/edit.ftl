<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">
			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/ssrolcmanager/main">홈</a> &gt; </li>
						<li class="current"><a href="/ssrolcmanager/edit">비밀번호 변경</a></li>
					</ul>
				</div>
				<h1 class="mgt-20">&nbsp;</h1>
				<div class="tbl-type-E">
					<table width="100%" cellPadding="0" cellSpacing="0" summary="">
						<colgroup>
							<col width="14%">
							<col width="*%">
						</colgroup>
						<tbody>
							<tr>
								<th>기존 비밀번호</th>
								<td>
									<input name="oldPass" type="password" class="text" id="oldPass" style="width: 200px;" title="" maxlength="20">
								</td>
							</tr>
							<tr>
								<th>새 비밀번호</th>
								<td>
									<input name="newPass" type="password" class="text" id="newPass" style="width: 200px;" title="" maxlength="20">
								</td>
							</tr>
							<tr>
								<th>새 비밀번호 확인</th>
								<td>
									<input name="newPassConfirm" type="password" class="text" id="newPassConfirm" style="width: 200px;" title="" maxlength="20">
								</td>
							</tr>
							<tr>
								<th>☞</th>
								<td>
									※비밀번호는 영문 소문자또는 숫자,특수문자를 조합하여 6~20내에서 입력해 주세요.
								</td>
							</tr>
						</tbody>
					</table>
					<div class="mgt-20 float-r">
                    		<span class="button btn-type-I"><a class="w-65" id="passSubmitBtn" style="width:100px;cursor:pointer;">비밀번호 변경</a></span>
							<span class="button btn-type-J"><a class="w-65" href="/ssrolcmanager/edit" style="width:75px;">다시입력</a></span>
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