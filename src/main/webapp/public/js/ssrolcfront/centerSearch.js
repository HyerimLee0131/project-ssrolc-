$(function() {
	$.extend({
		//검색
		getJisaSearch:function(){
			var doName = $('#do').val();
			if(doName == "") {
				doName = "all";
			}

			var searchUrl = "/ssrolcfront/centerSearch/"+doName;
			$.ajax({
				url : searchUrl,
				type : 'GET',
				cache: false,
				async: true,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					//if(jsonData.result == "success" || doVal == "충남"){	//직영센터로 인해 충남 예외처리
					if(!jsonData) {
						$("#dataList").html($('#emptyList').text());
						$('#emptyList').show();
					} else { 
						if(jsonData.result == "success"){	//직영센터로 인해 충남 예외처리
							var source = $("#centersTemplate").html();
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
					}
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		},

		//맵 클릭시 검색
		getSearch:function(pDo) {
			$('#do').val(pDo);
			$.getJisaSearch(pDo);
		}
	});

	//셀렉트박스 선택
	$('#do').on('change',function(){
		$.getJisaSearch();
	});

	//IE area태그 포커스 점선 없애기
	$('area').each(function(){
		$(this).on("focus",function(){
			$(this).blur();
		});
	});

	//defalut 검색
	$.getJisaSearch();
});
