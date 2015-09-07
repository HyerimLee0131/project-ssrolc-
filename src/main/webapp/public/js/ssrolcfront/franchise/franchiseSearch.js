$(function() {
	$.extend({
		// 도 선택
		resetGugun:function(){
			
			$("#seoulAllMap").attr('src',"/public/img/map/map_seoul_00.png");
			$("#gyeonggiAllMap").attr('src',"/public/img/map/map_gyeonggi_00.png");
			$("#incheonAllMap").attr('src',"/public/img/map/map_incheon_00.png");
			$("#busanAllMap").attr('src',"/public/img/map/map_busan_00.png");
			$("#daejeonAllMap").attr('src',"/public/img/map/map_daejeon_00.png");
			$("#daeguAllMap").attr('src',"/public/img/map/map_daegu_00.png");
			$("#ulsanAllMap").attr('src',"/public/img/map/map_ulsan_00.png");
			$("#gwangjuAllMap").attr('src',"/public/img/map/map_gwangju_00.png");
			$("#gangwonAllMap").attr('src',"/public/img/map/map_gangwon_00.png");
			$("#chungbukAllMap").attr('src',"/public/img/map/map_chungbuk_00.png");
			$("#chungnamAllMap").attr('src',"/public/img/map/map_chungnam_00.png");
			$("#sejongAllMap").attr('src',"/public/img/map/map_sejong_00.png");
			$("#gyeongbukAllMap").attr('src',"/public/img/map/map_gyeongbuk_00.png");
			$("#gyeongnamAllMap").attr('src',"/public/img/map/map_gyeongnam_00.png");
			$("#jeonbukAllMap").attr('src',"/public/img/map/map_jeonbuk_00.png");
			$("#jeonnamAllMap").attr('src',"/public/img/map/map_jeonnam_00.png");
			$("#jejuAllMap").attr('src',"/public/img/map/map_jeju_00.png");

		},
		selectList:function(){
			var doName = $('#do').val();
			var si = $('#guGun').val();
			$.ajax({
				url:"/ssrolcfront/franchise/"+doName+"/"+si,
				type:"GET",
				cache: false,
				async: true,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					if(jsonData.result == "success"){
						var source = $("#franchisesTemplate").html();
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
					}
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}
		
	});
	
	/*
	 * IE area태그 포커스 점선 없애기
	 * */
	$('area').each(function(){
		$(this).on("focus",function(){
			$(this).blur();
		});
	});
	
	// 시도 선택
	$('#do').on('change',function(){
		var doName = $('#do').val();
		$("#branchMap > area").each(function() {
			if($(this).attr("data-sido") == doName) {
				$('.rightMap').hide();
				$($(this).attr('data-show')).show();
				$("#branchAllMap").attr('src',$(this).attr('data-image-over'));

				return false;
			}
		});
		if(doName == 'select'){
			$("#guGun").append("<option value='select'>선택</option>");
			return;
			}
		
		$.ajax({
			url:"/ssrolcfront/franchise/"+doName,
			type:"GET",
			cache: false,
			async: true,
			
			dataType: "json",
			success: function(jsonData, textStatus, XMLHttpRequest) {
				var guGunList = jsonData.guGunList;
				$("#guGun").find('option').each(function(){
					$(this).remove();
				});
				$("#guGun").append("<option value='select'>선택</option>");
				for(var i=0; i<guGunList.length; i++){
					$("#guGun").append("<option value='"+guGunList[i].area2+"'>"+guGunList[i].area2+"</option>");
				}
				
			},
			error:function (xhr, ajaxOptions, thrownError){	
				alert(thrownError);
			}
		});
	});
	
	// 구/군 선택
	$('#guGun').on('change',function(){
		var guGun = $('#guGun').val();
		$(".rightMap >map> area").each(function() {
			if($(this).attr("data-gungu") == guGun) {
				$(".rightMap>img").attr('src',$(this).attr('data-image-over'));
				return false;
			}
		});
		$.selectList();
	});
	
	/*
	* 전국 시/도 맵
	*/
	$("map[name=branchMap] area").on('click', function () {
		$(".rightMap").hide();
		$($(this).attr('data-show')).show();
		$("#branchAllMap").attr('src',$(this).attr('data-image-over'));
		var doName = $(this).attr('data-sido');
		$('#do').val(doName);
		$('#do').trigger("change");
		$.resetGugun();
	});
	

	/*
	* 군/구 상세 맵
	*/
	$("map[name=seoulMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_seoul.png');
		$("#seoulAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});
	
	$("map[name=gyeonggiMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_gyeonggi.png');
		$("#gyeonggiAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		alert(si);
		$.selectList();
	});
	
	$("map[name=incheonMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_incheon.png');
		$("#incheonAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		alert(si);
		$.selectList();
	});

	$("map[name=busanMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_busan.png');
		$("#busanAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});
	
	$("map[name=daejeonMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_daejeon.png');
		$("#daejeonAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});
	
	$("map[name=daeguMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_daegu.png');
		$("#daeguAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});			

	$("map[name=ulsanMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_ulsan.png');
		$("#ulsanAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});

	$("map[name=gwangjuMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_gwangju.png');
		$("#gwangjuAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});

	$("map[name=gangwonMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_gangwon.png');
		$("#gangwonAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});

	$("map[name=chungbukMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_chungbuk.png');
		$("#chungbukAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});

	$("map[name=chungnamMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_chungnam.png');
		$("#chungnamAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});

	$("map[name=sejongMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_sejong.png');
		$("#sejongAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});

	$("map[name=gyeongbukMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_gyeongbuk.png');
		$("#gyeongbukAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});

	$("map[name=gyeongnamMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_gyeongnam.png');
		$("#gyeongnamAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});

	$("map[name=jeonbukMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_jeonbuk.png');
		$("#jeonbukAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});

	$("map[name=jeonnamMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_jeonnam.png');
		$("#jeonnamAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});

	$("map[name=jejuMap] area").on('click', function () {
		$("#branchAllMap").attr('src','/public/img/map/map_jeju.png');
		$("#jejuAllMap").attr('src',$(this).attr('data-image-over'));
		var si = $(this).attr('data-gungu');
		$('#guGun').val(si);
		$.selectList();
	});

});
