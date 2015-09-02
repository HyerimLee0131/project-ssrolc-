	<#include "/ssrolcfront/header_new.ftl">
	<#include "/ssrolcfront/left_new.ftl">
	<div class="content">
		<div class="path">
			<span class="home"><a href="/">홈</a></span> &gt; <a href="/ssrolcfront/boards/notice">커뮤니티</a>
			&gt; <a href="/ssrolcfront/boards/${boardInfo.boardTable}">${boardInfo.boardName}</a>
		</div>
		<div class="student_info">
			<h3>${boardInfo.boardName}</h3>
			<div class="clearfix student_list_wrap">
				<div class="student_list">
					<ul class="clear">
						<li>
							<div class="line">
								<h4>초등학교 때의 수학기초학력부족, ‘수포자’의 지름길이에요</h4>
								<p>결손부분부터 탄탄한 기초학력까지 “One Stop Service”</p>
								<div class="info_wrap clearfix">
									<div class="img_list clearfix">
										<span class="img"><img src="../images/center_img08.jpg" alt="" /></span>
										<span class="img"><img src="../images/center_img07.jpg" alt="" /></span>
									</div>
									<div class="info_list">
										<ul class="clear">
											<li><strong>학생명 : </strong> 배정근</li>
											<li><strong>센터명 : </strong> 재능스스로러닝센터 대전노은점</li>
											<li><strong>센터장명 : </strong> 이미란 원장</li>
										</ul>
										<div class="link"><a href="student_view.html">우수학생소개 자세히 보기</a></div>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="line">
								<h4>“수학이 많이 어려웠는데, 이제는 잘 할 수 있게 됐어요!"</h4>
								<p>재능스스로러닝센터=재미넘치는 공부방</p>
								<div class="info_wrap clearfix">
									<div class="img_list clearfix">
										<span class="img"><img src="../images/center_img05.jpg" alt="" /></span>
										<span class="img"><img src="../images/center_img06.jpg" alt="" /></span>
									</div>
									<div class="info_list">
										<ul class="clear">
											<li><strong>학생명 : </strong> 배정근</li>
											<li><strong>센터명 : </strong> 재능스스로러닝센터 대전노은점</li>
											<li><strong>센터장명 : </strong> 이미란 원장</li>
										</ul>
										<div class="link"><a href="student_view.html">우수학생소개 자세히 보기</a></div>
									</div>
								</div>
							</div>
						</li>
					</ul>
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

	<#include "/ssrolcfront/footer_new.ftl">