
$(function() {
	$.extend({
		getBoardList:function(){
			var pageNum = $("#pageNum").val();
			var boardTable = $("#boardTable").val();
			var searchField = $("#searchField").val();
			var searchValue = $.trim($("#searchValue").val());
			var searchUrl = "/ssrolcfront/boards/"+boardTable+"/"+pageNum;

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
					var source = $("#boardsTemplate").html();
					var template = Handlebars.compile(source);
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
		}
	});
	
	$.getBoardList();

	// paging 클릭
	$(".pageNavi").on("click","a",function() {
		var pageNum = $(this).attr('pageNo');	
		$('#pageNum').val(pageNum);
		$.getBoardList();
	});	
	
	$("#searchBtn").on("click",function() {
		$("#pageNum").val("1");
		$.getBoardList();
	});
});