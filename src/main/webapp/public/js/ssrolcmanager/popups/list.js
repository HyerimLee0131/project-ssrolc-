/**
 * 관리자 admin_list.js
 */
$(function() {
	$.extend({
		getPopupList:function(){
			var pageNum = $("#pageNum").val();
			var searchField = $("#searchField").val();
			var searchValue = $.trim($("#searchValue").val());
			var searchUrl = "/ssrolcmanager/popups/"+pageNum;
			
			if(searchValue != ""){
				searchUrl += "/"+searchField+"/"+encodeURIComponent(searchValue);
			}

			$.ajax({
				url:searchUrl,
				type:"GET",
				cache: false,
				async: true,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					var pageInfo = jsonData.pageInfo;
					var totalRowCnt = pageInfo.totalRowCnt;
					$("#totalCnt").html(totalRowCnt);
					$("#pageNavi").html($.pageUtil(pageInfo.pageNum,pageInfo.totalPageCnt, 
							pageInfo.rowBlockSize,pageInfo.startPageNum,pageInfo.endPageNum));	
					var source = $("#popupsTemplate").html();
					var template = Handlebars.compile(source);
					Handlebars.registerHelper('inc', function (index) {
						return totalRowCnt - pageInfo.startRow - index;
					});
					Handlebars.registerHelper("prettifyDate", function(timestamp) {
						var d = new Date(timestamp);
						var month = d.getMonth()+1;
						var day = d.getDate();
						var convertMonth = $.leadingZeros(month,2);
						var convertDay = $.leadingZeros(day,2);
					    return d.getFullYear()+"-"+convertMonth+"-"+convertDay;
					});
					Handlebars.registerHelper('xIf', function (lvalue, operator, rvalue, options) {
					    var operators, result;
					    if (arguments.length < 3) {
					        throw new Error("Handlerbars Helper 'compare' needs 2 parameters");
					    }
					    if (options === undefined) {
					        options = rvalue;
					        rvalue = operator;
					        operator = "===";
					    }
					    operators = {
					        '==': function (l, r) { return l == r; },
					        '===': function (l, r) { return l === r; },
					        '!=': function (l, r) { return l != r; },
					        '!==': function (l, r) { return l !== r; },
					        '<': function (l, r) { return l < r; },
					        '>': function (l, r) { return l > r; },
					        '<=': function (l, r) { return l <= r; },
					        '>=': function (l, r) { return l >= r; },
					        'typeof': function (l, r) { return typeof l == r; }
					    };

					    if (!operators[operator]) {
					        throw new Error("'xIf' doesn't know the operator " + operator);
					    }

					    result = operators[operator](lvalue, rvalue);
					    if (result) {
					        return options.fn(this);
					    } else {
					        return options.inverse(this);
					    }
					});
					$("#dataList").empty();
					$("#dataList").append(template(jsonData));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		},

		selectDelete:function(){
			var aidxArry = new Array(); 

			$('input[name="popupCheckBox"]:checked').each(function(){
				aidxArry.push($(this).val());
			});

			if(aidxArry.length == 0){
				alert("게시물을 선택해주세요.");
				return;
			}

			if(!confirm("정말 삭제 하시겠습니까?")){
				return;
			}

			//게시물번호 문자열 조합
/*			var aidxs="";
			for(var i=0; i<aidxArry.length; i++) {
				aidxs += aidxArry[i];
				aidxs += ",";
			}

			aidxs = aidxs.substr(0,aidxs.length-1);
*/
			var delUrl = "/ssrolcmanager/popups/delete";
			var sendData = {'aidxs':aidxArry};
			$.ajax({
				url : delUrl,
				type : 'POST',
				data: sendData,
				cache: false,
				async: true,
				dataType : "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
						if(jsonData.rs == "ok"){
							alert('삭제되었습니다.');
						}else{
							alert('삭제에 실패하였습니다.');
						}

						$('#pageNum').val("1");
						$.getPopupList();
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});


		}
		
	});
	
	$.getPopupList();
	
	// paging 클릭
	$(".paging").on("click","a.naviPage",function() {
		var pageNum = $(this).attr('pageNo');	
		$('#pageNum').val(pageNum);
		$.getPopupList();
	});	

	// 검색 클릭
	$("#searchBtn").on("click",function() {
		$('#pageNum').val('1');
		$.getPopupList();
	});

	// 검색 리셋 클릭
	$('#resetBtn').on('click',function(){
		location.href = "/ssrolcmanager/popups";
	});

	// 전체 선택
	$('#allCheckBtn').on('click',function(){
		var thisFlag = $(this).is(":checked");

		$('input[name="popupCheckBox"]').each(function(){
			if(thisFlag){
				$(this).prop("checked",true);
			}else{
				$(this).prop("checked",false);
			}
		});
	});
});