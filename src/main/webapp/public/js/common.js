$(function(){
	$.extend({
			pageUtil : function(pageNum, totalPageCnt, rowBlockSize,startPageNum,endPageNum) {
				var thisBlock = Math.ceil(pageNum / rowBlockSize); // 현재 페이징블럭
				var preBlock, nextBlock; // 이전, 다음 페이징 블럭
				var html = "";

				if (totalPageCnt > 0) {
					// 현재 페이지블럭의 시작페이지번호, 전페이지번호
					if (thisBlock > 1) {
						startPageNum = (thisBlock - 1) * rowBlockSize + 1;
						preBlock = startPageNum - 1;
					} else {
						startPageNum = preBlock = 1;
					}

					// 현재 페이지블럭의 끝페이지번호, 다음페이지번호
					if ((thisBlock * rowBlockSize) >= totalPageCnt) {
						endPageNum = totalPageCnt;
						nextBlock = endPageNum;
					} else {
						endPageNum = thisBlock * rowBlockSize;
						nextBlock = endPageNum + 1
					}
					
					if(pageNum > 1){
						html += "<a class='first naviPage' pageNo='1' href='javascript:;'><img alt='처음' src='/public/img/admin/board/btn_paging_first.gif'></a> "; // 맨처음으로 가기
						
						if (preBlock > 1) {
							html += "<a class='prev naviPage' pageNo='"+preBlock+"' href='javascript:;'><img alt='이전' src='/public/img/admin/board/btn_paging_prev.gif'></a> "; // 현재블럭의 전페이지
						}
					}

					for (i = startPageNum; i <= endPageNum; i++) {
						if(i != pageNum){
							html += " <a class='naviPage' href='javascript:;' pageNo='"+i+"'>"+i+"</a> ";
						} else {
							html += "<a class='current' href='javascript:;' pageNo='"+i+"'>"+i+"</a>";
						}
					}

					if(pageNum != totalPageCnt){
						if(nextBlock < totalPageCnt ){
							html += "<a class='next naviPage' pageNo='"+nextBlock+"' href='javascript:;'><img alt='다음' src='/public/img/admin/board/btn_paging_next.gif'></a> "; // 현재 블럭의 다음페이지
						}
						html += "<a class='last naviPage' pageNo='"+totalPageCnt+"' href='javascript:;'><img alt='끝' src='/public/img/admin/board/btn_paging_last.gif'></a> "; // 맨끝으로 가기
					}
				}
				return html;
			},
			
			
			leadingZeros:function(n, digits) {
				  var zero = '';
				  
				  n = n.toString();

				  if (n.length < digits) {
				    for (i = 0; i < digits - n.length; i++) {
				      
				      zero += '0';
				    }
				  }
				  
				  return zero + n;
			},
			openPopup:function(url,openWindowName,width,height,left,top){
				window.open(url,openWindowName,"width="+width+",height="+height+",left="+left+",top="+top+"scrollbars=no,toolbar=no,resizable=no");
			},
			numeric:function(str){
				var pattern = /(^[0-9]+$)/;
				if(!pattern.test(str)){
				    return false;
				}
				return true;
			}
	});

	
	$(".datePicker").datepicker(
			{
			changeYear : true,
			showAnim : "slide",
			dateFormat : 'yy-mm-dd',
			prevText : '이전 달',
			nextText : '다음 달',
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
			'9월', '10월', '11월', '12월' ],
			monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
			'8월', '9월', '10월', '11월', '12월' ],
			dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
			dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
			showMonthAfterYear : true,
			yearSuffix : '년'
		});
	
	//family site
	$('.fmTitle').click(function(e) {
		var target = $(this).attr('href');
		$(target).slideDown();
		$(target).find('.btn-close').click(function() {
			$(target).slideUp();
		});
	});
	
});
