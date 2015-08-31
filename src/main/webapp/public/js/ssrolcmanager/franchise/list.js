/**
 * 관리자 franchise.js
 */
$(function() {
	$.extend({
		getBoardList:function(){
			var pageNum = $("#pageNum").val();
			var searchUrl = "/ssrolcmanager/franchise/faqs/"+pageNum;
			
			var hopeArea01 = $("#hopeArea01").val();
			var hopeArea02 = $("#hopeArea02").val();
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var jslcounseling = $("#counseling").val();
			var pMemName = $("#pMemName").val();
			var jslcType = "";  
		    $("input:checkbox:checked").each(function (index) {  
		    	if(index != 0){
		    		jslcType += "," + $(this).val();  
		    	}else{
		    		jslcType += $(this).val();  
		    	}
		    });  
			
		    var inputData = {"jslcArea1":hopeArea01,"jslcArea2":hopeArea02,"jslcType":jslcType,"startDate":startDate,"endDate":endDate,"jslcounseling":jslcounseling,"memName":pMemName};
			
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
					var source = $("#franchiseTemplate").html();
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
		//레이어팝업 클릭
		openFranchise:function(jslcId,joinState){
			$('#layer_pop').show();
			if(joinState == 0){
				$('#joinState0').prop("checked",true);
			}else if(joinState == 1){
				$('#joinState1').prop("checked",true);
			}else if(joinState == 2){
				$('#joinState2').prop("checked",true);
			}
			$('#layerSubmitBtn').off('click');
			$('#layerSubmitBtn').on('click',function(){
				var changejoinState = $('input[name="joinState"]:checked').val();
				if(changejoinState != joinState){
					//변경되므로 DB에 저장
					$.joinLayerSubmit(jslcId,changejoinState);
					$('#layer_pop').hide();
				}else{
					//변경되는게 없으므로 레이어창 닫기
					$('#layer_pop').hide();
				}
			});
			
		},
		joinLayerSubmit:function(jslcId,changejoinState){
			var inputData = {"jslcId":jslcId,"jslcounseling":changejoinState};
			$.ajax({
				type :"PUT",
				url  :"/ssrolcmanager/franchise/changeJoinState",
				data: inputData,
				cache: false,
				async: true,
				dataType : "json",	
				success: function(jsonData, textStatus, XMLHttpRequest) {
					if(jsonData.result == "success"){
						$.getBoardList();
					}
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		},
		
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
		location.href = "/ssrolcmanager/franchise/faqs";
	});
	// 가맹희망지역 선택
	$('#hopeArea01').on('change',function(){
		var jslcArea1 = $('#hopeArea01').val();
		if(jslcArea1 == 'select'){
			$("#hopeArea02").find('option').each(function(){
				$(this).remove();
			});
			$("#hopeArea02").append("<option value='select'>지역선택</option>");
			return;
			}
		var inputData = {"jslcArea1":jslcArea1};
		$.ajax({
			url:"/ssrolcmanager/franchise/deptArea",
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
