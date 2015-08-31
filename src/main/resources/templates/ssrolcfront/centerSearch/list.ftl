<#include "/ssrolcfront/header_new.ftl">
<#include "/ssrolcfront/left_new.ftl">

	<div class="content">
		<div class="path">
			<span class="home"><a href="/">홈</a></span> &gt; <a href="#">스스로러닝센터소개</a> &gt; <a href="/ssrolcfront/centerSearch">우리동네 러닝센터 찾기</a>
		</div>
		<div class="center_search">
			<h3>우리동네 러닝센터 찾기</h3>
			<div class="select_area">
				<label for="do">시/도 :</label>
				<select name="do" class="input-select w100" id="do" title="시/도선택">
					<option value="">선택</option>
					<#list doList as DO>
						<option value="${DO}">${DO}</option>
					</#list>
				</select>
				<span class="txt">현재 살고 계신 <strong>시/도</strong> 를 선택해주세요.</span>
			</div>
			<!-- 지도 -->
			<div class="mapWrap">
				<ul class="clear">
					<li class="step-1 map_one">
						<dl class="clear">
							<dt class="skip">지역선택</dt>
							<dd>
								<img src="/public/img/map/map_none.png" alt="전체" usemap="#branchMap" id="branchAllMap" />
								<map name="branchMap" id="branchMap">
									<area shape="poly" href="javascript:$.getSearch('서울');" alt="서울특별시" coords="65,102,56,101,59,111,62,111,78,114,86,107,73,97" data-show="#seoulMap" data-image-over="/public/img/map/map_seoul.png" data-sido="서울"/>
									<area shape="poly" href="javascript:$.getSearch('경기');" alt="경기도" coords="48,63,36,70,30,82,39,85,47,97,52,99,57,99,67,97,78,96,87,107,78,119,68,118,56,111,46,116,44,121,39,126,42,134,51,137,53,137,50,142,56,144,66,149,83,146,92,147,109,139,110,135,116,129,117,115,120,113,118,108,119,103,113,100,106,92,110,81,108,76,100,75,97,73,90,69,82,64,69,60" data-show="#gyeonggiMap"  data-image-over="/public/img/map/map_gyeonggi.png" data-sido="경기"/>
									<area shape="poly" href="javascript:$.getSearch('부산');" alt="부산광역시" coords="188,275,196,263,210,259,216,252,222,256,217,266,207,270,193,277" data-show="#busanMap"  data-image-over="/public/img/map/map_busan.png"  data-sido="부산"/>
									<area shape="poly" href="javascript:$.getSearch('인천');" alt="인천광역시" coords="30,92,35,100,27,98,35,106,47,106,47,111,54,109,58,105,48,100,41,93,35,88" data-show="#incheonMap"  data-image-over="/public/img/map/map_incheon.png" data-sido="인천"/>
									<area shape="poly" href="javascript:$.getSearch('대전');" alt="대전광역시" coords="92,180,83,189,87,195,107,198,101,192,101,183" data-show="#daejeonMap" data-image-over="/public/img/map/map_daejeon.png"  data-sido="대전"/>
									<area shape="poly" href="javascript:$.getSearch('대구');" alt="대구광역시" coords="162,217,164,226,158,234,160,237,169,236,174,228,184,231,182,209" data-show="#daeguMap"  data-image-over="/public/img/map/map_daegu.png" data-sido="대구"/>
									<area shape="poly" href="javascript:$.getSearch('울산');" alt="울산광역시" coords="208,232,203,242,210,249,223,254,228,248,235,236,235,234,228,232,223,234,219,235,214,231" data-show="#ulsanMap" data-image-over="/public/img/map/map_ulsan.png" data-sido="울산"/>
									<area shape="poly" href="javascript:$.getSearch('광주');" alt="광주광역시" coords="69,260,49,260,43,268,54,276,67,273,76,265" data-show="#gwangjuMap"  data-image-over="/public/img/map/map_gwangju.png"  data-sido="광주"/>
									<area shape="poly" href="javascript:$.getSearch('강원');" alt="강원도" coords="158,34,118,6,107,1,109,18,94,25,92,4,70,15,65,22,66,23,57,26,56,29,48,33,53,48,96,71,103,72,114,78,107,92,114,96,123,112,116,127,126,133,127,121,130,121,146,126,158,123,155,132,181,135,212,135,221,131,203,97,170,57" data-show="#gangwonMap" data-image-over="/public/img/map/map_gangwon.png" data-sido="강원"/>
									<area shape="poly" href="javascript:$.getSearch('충북');" alt="충청북도" coords="178,137,154,131,157,127,142,128,131,124,132,129,129,133,116,130,111,134,104,142,94,146,95,156,93,166,92,168,94,177,103,179,102,187,110,198,111,203,122,206,126,206,133,196,122,188,126,180,119,170,138,153,153,152,166,145" data-show="#chungbukMap" data-image-over="/public/img/map/map_chungbuk.png" data-sido="충북"/>
									<area shape="poly" href="javascript:$.getSearch('충남');" alt="충청남도" coords="84,149,75,148,66,150,61,147,44,139,30,142,25,147,12,155,14,162,28,181,37,201,44,204,46,204,49,209,68,196,83,206,86,201,98,207,101,203,110,202,103,199,95,201,84,196,81,191,82,187,72,165,78,155,87,152,95,156" data-show="#chungnamMap" data-image-over="/public/img/map/map_chungnam.png"  data-sido="충남"/>
									<area shape="poly" href="javascript:$.getSearch('세종');" alt="세종시" coords="93,179,89,160,94,154,83,154,76,160,73,167,82,184" data-show="#sejongMap" data-image-over="/public/img/map/map_sejong.png"  data-sido="세종"/>
									<area shape="poly" href="javascript:$.getSearch('경북');" alt="경상북도" coords="229,205,226,192,231,176,230,158,228,149,234,132,221,132,212,139,200,136,191,137,179,139,171,144,162,151,155,153,148,155,143,153,130,164,123,169,127,179,125,189,137,192,135,198,126,210,126,218,145,223,150,229,155,237,160,224,158,217,176,209,183,209,187,232,173,234,171,239,187,238,199,236,207,229,212,229,217,234,231,228,233,218,237,211,235,203,258,166,265,166,298,171,298,164,261,150,254,161" data-show="#gyeongbukMap" data-image-over="/public/img/map/map_gyeongbuk.png" data-sido="경북"/>
									<area shape="poly" href="javascript:$.getSearch('경남');" alt="경상남도" coords="131,221,118,226,113,242,111,254,107,257,121,280,122,287,127,296,134,300,139,288,131,281,138,279,162,294,167,277,179,276,168,289,175,296,187,288,183,277,192,265,204,261,212,254,200,245,203,237,187,241,173,239,162,242,151,234,147,226" data-show="#gyeongnamMap" data-image-over="/public/img/map/map_gyeongnam.png" data-sido="경남"/>
									<area shape="poly" href="javascript:$.getSearch('전북');" alt="전라북도" coords="62,202,54,208,48,210,42,209,38,211,33,213,50,218,34,224,35,229,33,237,43,235,45,237,38,238,32,245,37,254,39,254,44,252,54,243,63,245,70,247,71,251,74,253,83,253,90,254,96,250,105,254,111,247,111,236,115,226,123,218,123,208,103,203,99,208,95,206,87,203,82,207,70,200" data-show="#jeonbukMap" data-image-over="/public/img/map/map_jeonbuk.png" data-sido="전북"/>
									<area shape="poly" href="javascript:$.getSearch('전남');" alt="전라남도" coords="31,248,23,255,19,268,4,270,15,287,29,293,28,294,27,298,20,300,19,295,6,315,20,315,39,321,50,309,56,315,62,315,67,302,73,298,88,294,74,302,79,310,85,313,101,306,99,300,96,294,100,287,103,296,102,300,116,309,120,300,115,293,123,292,113,285,118,277,105,255,99,252,91,258,81,256,79,258,73,254,67,249,55,247,41,257,36,257" data-show="#jeonnamMap" data-image-over="/public/img/map/map_jeonnam.png" data-sido="전남"/>
									<area shape="poly" href="javascript:$.getSearch('제주');" alt="제주도" coords="30,356,14,363,8,371,9,374,26,378,63,368,71,359,57,354" data-show="#jejuMap" data-image-over="/public/img/map/map_jeju.png"  data-sido="제주"/>
								</map>
							</dd>
						</dl>
					</li>
				</ul>
			</div>
			<!-- //지도 -->
			<div class="center_result_list">
				<table class="center_table">
				<caption>센터정보</caption>
					<colgroup>
						<col style="width:150px">
						<col style="width:60px">
						<col style="width:50px">
						<col style="width:80px">
						<col />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">센터명</th>
							<th scope="col">센터 <br />둘러보기</th>
							<th scope="col">지역</th>
							<th scope="col">대표전화</th>
							<th scope="col" class="no_rightLine">주소</th>
						</tr>
					</thead>
					<tbody id="dataList"></tbody>
				</table>
			</div>
		</div>
	</div>

	<script id="centersTemplate" type="text/x-handlebars-template">
		{{! 가맹센터 리스트 }}
		{{#each centers1}}
		<tr>
			<td>{{deptName}}</td>
			<td>{{#xIf homeUrl "!=" ""}}<a href="{{homeUrl}}" class="link_site" target="_blank">바로가기</a>{{/xIf}}</td>
			<td>{{tel1}}</td>
			<td>{{#xIf tel1 "!=" ""}}{{tel2}}-{{tel3}}{{/xIf}}</td>
			<td class="no_rightLine">{{addr}}</td>
		</tr>
		{{/each}}
		{{! 직영센터 리스트 - 예외처리 }}
		{{#each centers2}}
		<tr>
			<td>{{deptName}}</td>
			<td>{{#xIf homeUrl "!=" ""}}<a href="{{homeUrl}}" class="link_site" target="_blank">바로가기</a>{{/xIf}}</td>
			<td>{{tel1}}</td>
			<td>{{#xIf tel1 "!=" ""}}{{tel2}}-{{tel3}}{{/xIf}}</td>
			<td class="no_rightLine">{{addr}}</td>
		</tr>
		{{/each}}
		{{! 가맹,직영 둘 다 없으면 }}
		{{#unless centers1}}
			{{#unless centers2}}
				<tr>
					<td colspan="5" class="no_rightLine">데이타가 없습니다.</td>
				</tr>
			{{/unless}}
		{{/unless}}
	</script>

	<#include "/ssrolcfront/footer_new.ftl">