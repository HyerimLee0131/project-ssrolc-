$(document).ready(function(){
/*cursor setting*/
	$('.jtab img').css({cursor:'pointer'});
	/* Tab Change */
	$('.jtab img').click(function(){
		/* Tab setting */
		var tabNum = $(this).parent("dd").index();
		var $tabImgAll = $('.jtab img');
		var $tabImg = $(this);
		$('.jtablist').hide().eq(tabNum).show();/*Tab list Show*/
		$tabImgAll.each(function(){  
			$(this).attr("src", $(this).attr("src").replace("_on", "_off"));
		});
		 $tabImg.attr("src", $tabImg.attr("src").replace("_off", "_on"));
	});
	
/*input TEXT Action*/
    $('input:text').focus(
    function(){
		$(this).removeClass('textoff');
        $(this).addClass('texton');
    });
    $('input:text').blur(
    function(){
        $(this).removeClass('texton');
        $(this).addClass('textoff');
    });
	
/* GNB */
	//GNB 1depth
	$('.gnb_a > dl > dd').hover(function(){
		//alert('hover test');
		var $gnb1depth = $(this);
		var $gnb2depth = $(this).children('.sub');
		var $gnb2depthClose = $('.gnb_a .sub').not($gnb2depth);
		var $gnb2depthMenu = $(this).children('.sub').children().children().children('a');
		if ($gnb1depth.hasClass('on') === false){	
			$gnb1depth.addClass('over');
			//2depth노출
		};

		$gnb2depthClose.hide();
		$gnb2depth.stop(true, true).show(100);
	},function(){ 	//마우스가 out하면
		//alert('hover test');
		var $gnb1depth = $(this);
		var $gnb2depth = $(this).children('.sub')
		var $gnb2depthClose = $('.gnb_a .sub').not($gnb2depth);
		if ($gnb1depth.hasClass('on') === false){	
			$gnb1depth.removeClass('over');
			//2depth노출
		};
		$gnb2depthClose.hide();
		$gnb2depth.hide(100);
	});
	//GNB 2depth
	$('.gnb_a div.sub > dl > dd').hover(function(){
		var $gnb2depth = $(this);
		var $gnb2depthTXT = $(this).children('a');
		if ($gnb2depth.hasClass('on') === false){	
			$gnb2depth.addClass('over');
			//2depth노출
		};
		
	},function(){ 	//마우스가 out하면
		var $gnb2depth = $(this);
		var $gnb2depthTXT = $(this).children('a');
		if ($gnb2depth.hasClass('on') === false){	
			$gnb2depth.removeClass('over');
			//2depth노출
		};
	});
	
	
/* 하루학습프로그램 */
	$('.Timetable > map > area').hover(function(){
		//alert('hover test');
		var oj_Num = $(this).index();
		var $target_oj = $('.Timetable > .layer_over > dd').fadeOut().eq(oj_Num);
		$target_oj.stop(true, true).fadeIn(200);
	});
});