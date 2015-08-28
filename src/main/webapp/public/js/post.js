$(function() {
	var postSendCheckFlag = false;
	$.extend({  
		getAddressList:function(){
			var pageNum = $("#pageNum").val();
			var srchwrd = $.trim($("#srchwrd").val());
			
			if(srchwrd == "" || srchwrd.length == 0){
				return;
			}
			
			var searchUrl = "/post/"+srchwrd+"/"+pageNum;
			
			$.ajax({
				url:searchUrl,
				type:"GET",
				cache: false,
				async: true,
				dataType: "json",
				beforeSend:function(){
					postSendCheckFlag = true;
				},
				success: function(jsonData, textStatus, XMLHttpRequest) {
					jsonData.srchwrd = srchwrd;
					var postHeader = jsonData.postHeader;
					$("#pageNavi").html($.pageUtil(postHeader.currentPage,postHeader.totalPage, 
							postHeader.countPerPage,postHeader.startPageNum,postHeader.endPageNum));	
					var source = $("#postTemplate").html();
					var template = Handlebars.compile(source);
					$("#dataList").empty();
					$("#dataList").append(template(jsonData));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				},
				complete:function(){
					postSendCheckFlag = false;
				}
			});	
		},
		setPost:function(zip,addr){
			opener.$.setAddress(zip,addr);
			self.close();
		}
	
	});
	
	// paging 클릭
	$(".paging").on("click","a.naviPage",function() {
		var pageNum = $(this).attr('pageNo');	
		$('#pageNum').val(pageNum);
		$.getAddressList();
	});	
	
	$("#searchBtn").on("click",function() {
		if(postSendCheckFlag){
			alert("처리중입니다.");
			return;
		}		
		$("#pageNum").val("1");
		$.getAddressList();
	});
});
