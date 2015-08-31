
$(function() {
	$(".faq_cate ul li").on("click","a",function(){
		var category = $(this).attr("category");
		location.href="/ssrolcfront/boards/faq?searchcategory="+category;
	});
});