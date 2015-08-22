/**
 * 관리자 disclosure.js
 */
$(function() {
	$.extend({
		getBoardList:function(){
			var pageNum = $("#pageNum").val();
			var searchUrl = "/ssrolcmanager/disclosures/"+pageNum;
			
			var hopeArea01 = $("#hopeArea01").val();
			var hopeArea02 = $("#hopeArea02").val();
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var pMemName = $("#pMemName").val();
			var deptType = "";  
		    $("input:checkbox:checked").each(function (index) {  
		    	if(index != 0){
		    		deptType += "," + $(this).val();  
		    	}else{
		    		deptType += $(this).val();  
		    	}
		    });  
		    console.log("deptType : "+deptType);  
			
		    var inputData = {"deptArea1":hopeArea01,"deptArea2":hopeArea02,"deptType":deptType,"startDate":startDate,"endDate":endDate,"memName":pMemName};
			console.log(inputData);
			
			$.ajax({
				url:searchUrl,
				type:"GET",
				cache: false,
				async: true,
				data: inputData,
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
	
	//달력버튼
	$("#startDate_img").on('click',function() {
		$("#startDate").datepicker( "show" );
	});

	$("#endDate_img").on('click',function() { 
		$("#endDate").datepicker( "show" );
	});

	// 검색버튼눌렀을 때
	$("#searchBtn").on("click",function() {
		$('#pageNum').val("1");
		$.getBoardList();
	});
	// 리셋 버튼
	$('#resetBtn').on('click',function(){
		location.href = "/ssrolcmanager/disclosures";
	});
	// 가맹희망지역 선택
	$('#hopeArea01').on('change',function(){
		var deptArea1 = $('#hopeArea01').val();
		if(deptArea1 == 'select'){
			$("#hopeArea02").find('option').each(function(){
				$(this).remove();
			});
			$("#hopeArea02").append("<option value='select'>지역선택</option>");
			return;
			}
		var inputData = {"deptArea1":deptArea1};
		$.ajax({
			url:"/ssrolcmanager/disclosures/deptArea",
			type:"GET",
			cache: false,
			async: true,
			data: inputData,
			dataType: "json",
			success: function(jsonData, textStatus, XMLHttpRequest) {
				$("#hopeArea02").find('option').each(function(){
					$(this).remove();
				});
				$("#hopeArea02").append("<option value='select'>지역선택</option>");
				for(var i=0; i<jsonData.length; i++){
					$("#hopeArea02").append("<option value='"+jsonData[i]+"'>"+jsonData[i]+"</option>");
				}
			},
			error:function (xhr, ajaxOptions, thrownError){	
				alert(thrownError);
			}
		});
	});
});
