$(function() {
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
		$("#pageNum").val("1");
		$.getAddressList();
	});
});
