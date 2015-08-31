/**
 * 사용자 list.js
 */
$(function() {
	$.extend({
		getPrmediaList:function(){
			var pageNum = $("#pageNum").val();
			var searchField = $("#searchField").val();
			var searchValue = $.trim($("#searchValue").val());
			var searchUrl = "/ssrolcfront/prmedias/"+pageNum;
			
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
					//alert(jsonData.prmedia[0].mediaLinkUrl);
					$("#totalCnt").html(totalRowCnt);
					$("#pageNavi").html($.pageUtil(pageInfo.pageNum,pageInfo.totalPageCnt, 
							pageInfo.rowBlockSize,pageInfo.startPageNum,pageInfo.endPageNum));	
					var source = $("#prmediasTemplate").html();
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
					//alert(jsonData.prmedia[0].mediaLinkUrl);
					//$("#fileName").val(jsonData.prmedia[0].mediaLinkUrl);
					//$("#fileName2").attr('src',jsonData.prmedia[0].mediaLinkUrl);
					//document.getElementById("mediaUrlId").play();
					//$("#tv_player").append($.writePlayer(jsonData.prmedia[0].mediaLinkUrl));
					
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		},
		
		// 홍보영상 선택재생
		playStart:function(aidx) {
			location.href='/ssrolcfront/prmediasView/'+aidx;
		}

		
	});
	
	$.getPrmediaList();
	
	// paging 클릭
	$(".paging").on("click","a.naviPage",function() {
		var pageNum = $(this).attr('pageNo');	
		$('#pageNum').val(pageNum);
		$.getPrmediaList();
	});	
	
	// 검색 클릭
	$("#searchBtn").on("click",function() {
		if($.trim($('#searchValue').val()) == "" ){
			alert('검색어를 입력하세요.');
			$('#searchValue').focus();
			return false;
		}
		$('#pageNum').val('1');
		$.getPrmediaList();
	});

	// 검색 리셋 클릭
	$('#resetBtn').on('click',function(){
		location.href = "/ssrolcmanager/prmedias";
	});

	// 전체 선택
	$('#allCheckBtn').on('click',function(){
		var thisFlag = $(this).is(":checked");

		$('input[name="checkBox"]').each(function(){
			if(thisFlag){
				$(this).prop("checked",true);
			}else{
				$(this).prop("checked",false);
			}
		});
	});
	
	
});