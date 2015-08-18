/**
 * 관리자 disclosure.js
 */
$(function() {
	$.extend({
		getBoardList:function(){
			var pageNum = $("#pageNum").val();
			var searchUrl = "/ssrolcmanager/disclosures/"+pageNum;
//			
//			var inputData = {"hopeArea01":hopeArea01,"hopeArea02":hopeArea02,"deptType1":deptType1
//					,"deptType2":deptType2,"deptType3":deptType3,"startDate":startDate,"endDate":endDate
//					,"pMemName":pMemName};
			
			$.ajax({
				url:searchUrl,
				type:"GET",
				cache: false,
				async: true,
				//data: inputdata,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					var pageInfo = jsonData.pageInfo;
					var totalRowCnt = pageInfo.totalRowCnt;
					$("#totalCnt").html(totalRowCnt);
					$("#pageNavi").html($.pageUtil(pageInfo.pageNum,pageInfo.totalPageCnt, 
							pageInfo.rowBlockSize,pageInfo.startPageNum,pageInfo.endPageNum));	
					var source = $("#disclosureTemplate").html();
					var template = Handlebars.compile(source);
					Handlebars.registerHelper('inc', function (index) {
						return totalRowCnt - pageInfo.startRow - index;
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
		
		//열람증 클릭
		openDisclosure:function(aidx){
			window.open("/ssrolcmanager/disclosure/"+aidx,"popDisclosure","width=730,height=530,resizable");
		}
	});
	
	$.getBoardList();
	
	// paging 클릭
	$(".paging").on("click","a.naviPage",function() {
		var pageNum = $(this).attr('pageNo');	
		$('#pageNum').val(pageNum);
		$.getBoardList();
	});
	
	//정보공개서 확인날짜
	$("#startDate").datepicker();
	$("#endDate").datepicker();
	
	$("#startDate_img").on('click',function() {
		$("#startDate").datepicker( "show" );
	});
	
	$("#endDate_img").on('click',function() { 
		$("#endDate").datepicker( "show" );
	});
	
	// 검색버튼눌렀을 때
	$("#searchBtn").on("click",function() {
		$.getBoardList();
	});
	//리셋 버튼
	$('#resetBtn').on('click',function(){
		location.href = "/ssrolcmanager/disclosures";
	});
});
