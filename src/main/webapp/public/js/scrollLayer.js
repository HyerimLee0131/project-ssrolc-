// 표준 html DTD 사용시
// 퀵메뉴  위치 및 움직임
var quick = null;
var y_quick = 0;
var time_id = null;
var cnt_move = 0;

function moveSmooth()
{
	clearInterval( time_id );
	cnt_move = 50;
	time_id = setInterval( moveSmoothDetail , 10 );
}

function moveSmoothDetail()
{
	if ( cnt_move <= 0 )
	{
		clearInterval( time_id );
		time_id = null;
		cnt_move = 0;
		return;
	}

	quick.style.top = ( parseInt( quick.style.top , 10 ) + ( y_quick - parseInt( quick.style.top , 10 ) ) / 10 ) + "px";
	cnt_move--;
}

function initPos(top_pos)
{
	quick.style.top = top_pos + "px";
}

function keepQuickPosition(obj_div, top_pos)
{
	quick = document.getElementById(obj_div);	
	
    var scroll_top	= 0;
	scroll_top	= document.body.scrollTop > 0 ? document.body.scrollTop : document.documentElement.scrollTop;

	window.status = y_quick + "/" + scroll_top;

	if ( y_quick != parseInt( scroll_top , 10 ) + top_pos )
	{
		
		//스크롤 고정시 
		var quickScrollFixObj = document.getElementById("quickScrollFix");
		if (!(quickScrollFixObj.checked)){
			moveSmooth();				
		}
	}
	
	y_quick = parseInt( scroll_top , 10 ) + top_pos ;
}

function scrollLayer_quick(obj_div, top_pos)
{
	keepQuickPosition(obj_div, top_pos);
	initPos(top_pos);

	if ( navigator.userAgent.indexOf('Firefox') >= 0 )
	{  
		if ( window.addEventListener )
		{
			window.addEventListener( "resize" , keepQuickPosition , false );
			window.addEventListener( "resizestart" , keepQuickPosition , false );
			window.addEventListener( "resizeend" , keepQuickPosition , false );
			window.addEventListener( "scroll" , keepQuickPosition , false );
		}
	}
	else
	{
		var obj_body	= null;
		
		obj_body	= document.body;
		obj_body.onresize = obj_body.onresizestart = obj_body.onresizeend = obj_body.onscroll = function() { keepQuickPosition(obj_div, top_pos); }
	}

}
/// 위치 및 움직임



// 표준 html DTD 사용시
function initMoving(target, position, topLimit, btmLimit) {
    if (!target)
        return false;

    var obj = target;
    obj.initTop = position;
    obj.topLimit = topLimit;
    obj.bottomLimit = document.documentElement.scrollHeight - btmLimit;

    obj.style.position = "absolute";//"absolute";
    obj.top = obj.initTop;
    obj.left = obj.initLeft;

    if (typeof(window.pageYOffset) == "number") {
        obj.getTop = function() {
            return window.pageYOffset;
        }
    } else if (typeof(document.documentElement.scrollTop) == "number") {
        obj.getTop = function() {
            return document.documentElement.scrollTop;
        }
    } else {
        obj.getTop = function() {
            return 0;
        }
    }

    if (self.innerHeight) {
        obj.getHeight = function() {
            return self.innerHeight;
        }
    } else if(document.documentElement.clientHeight) {
        obj.getHeight = function() {
            return document.documentElement.clientHeight;
        }
    } else {
        obj.getHeight = function() {
            return 500;
        }
    }


	//스크롤 고정시 
	//var quickScrollFixObj = document.getElementById("quickScrollFix");
	//if (quickScrollFixObj.checked){
	//	obj.style.top = topLimit;
	//	clearInterval(obj.move);
    //    return false;
	//}//

    obj.move = setInterval(function() {
        if (obj.initTop > 0) {
            pos = obj.getTop() + obj.initTop;
        } else {
            pos = obj.getTop() + obj.getHeight() + obj.initTop;
            //pos = obj.getTop() + obj.getHeight() / 2 - 15;
        }

        if (pos > obj.bottomLimit)
            pos = obj.bottomLimit;
        if (pos < obj.topLimit)
            pos = obj.topLimit;

        interval = obj.top - pos;
        obj.top = obj.top - interval / 5;
        obj.style.top = obj.top + "px";
    }, 30)

}

