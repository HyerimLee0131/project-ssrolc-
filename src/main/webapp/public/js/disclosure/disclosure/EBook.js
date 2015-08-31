	var nTid;
	var nRetryCnt;
	var bFlashLoaded = false;
	
	function init(){
	    nRetryCnt = 0;
	    nTid=setInterval(retrySetFlashVars, 100);
	}
	
	function retrySetFlashVars(){
	    try{
	    if(!bFlashLoaded){
			if(nRetryCnt<=2){
				FlashLoaded();
				nRetryCnt++;
			}else{
				clearInterval(nTid);
			}
		}
		}catch(e){
			return;
		}
	}
	
	function eBookHelp(){
	    var wndHelp = window.open("./help/help.htm", "eBookHelp", "scrollbars=no,status=no,toolbar=no,resizable=no,location=no,menu=no, width=500,height=600");
	    wndHelp.focus();
	}
	
	function thisMovie(movieName){
	    if (navigator.appName.indexOf("Microsoft") != -1) {
	        return window[movieName]
	    }
	    else {
	        return document[movieName]
	    }
	}
	
	function setFirstPage(){
		var search = document.location.search;
		var inx1 = search.indexOf("?");
		var inx2 = search.indexOf("=");
		
		if(search.substr(inx1+1,inx2-1)=="page"){
			var num = eval(search.substr(inx2+1,search.length-inx2-1));
			thisMovie("EBOOK").Book_goInputPage(num);
		}
	}
	
	function FlashLoaded(){
		setFlashVars('fbook_2776022674','Config/Config.txt');
	}
	
	function APILoaded(){
		thisMovie("EBOOK").Viewport_maxScale(500);
		thisMovie("EBOOK").Viewport_initScale(300);
		setFirstPage();
	}
	
	function setFlashVars(BOOK_ID, CONFIG_URL){
		var isInternetExplorer = navigator.appName.indexOf("Microsoft") != -1; 
		PageObj = isInternetExplorer ? document.all.EBOOK : document.EBOOK; 
		PageObj.SetVariable("BOOK_ID",BOOK_ID);
		PageObj.SetVariable("CONFIG_URL",CONFIG_URL);
		
		var strRetBookID = PageObj.GetVariable("BOOK_ID");
		
		if(strRetBookID==BOOK_ID){
			bFlashLoaded = true;
		}
	
	}
	
	function loadEBook(){
		if (navigator.appName.indexOf("Microsoft") == -1) {
		  document.writeln("		<embed name=\"EBOOK\" src=\"EBook.swf\" ");
		  document.writeln("			quality=\"high\" bgcolor=\"#ffffff\" ");
		  document.writeln("			width=\"100%\" ");
		 document.writeln("			height=\"100%\" ");
		  document.writeln("			align=\"middle\" ");
		  document.writeln("			wmode=\"transparent\" ");
		  document.writeln("			swLiveConnect=\"true\" ");
		  document.writeln("			allowScriptAccess=\"always\" ");
		  document.writeln("			type=\"application/x-shockwave-flash\" ");
		  document.writeln("			pluginspage=\"http://www.macromedia.com/go/getflashplayer\" />");
		}else{
		  document.writeln("<object id=\"EBOOK\" classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\"");
		  document.writeln(" codebase=\"http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0\" ");
		  document.writeln(" width=\"100%\" height=\"100%\" align=\"middle\">");
		  document.writeln("		<param name=\"wmode\" value=\"transparent\" />");
		  document.writeln("		<param name=\"allowScriptAccess\" value=\"always\" />");
		  document.writeln("		<param name=\"quality\" value=\"high\" />");
		  document.writeln("		<param name=\"bgcolor\" value=\"#ffffff\" />");
		  document.writeln("		<param name=\"movie\" value=\"EBook.swf\" />");
		  document.writeln("	</object>");
		}
	}
	document.onmousemove = function(){
		var obj = document.getElementById("TooltipLayer");
		if(obj.style.visibility){
			obj.style.left = window.event.clientX+15;
			obj.style.top = window.event.clientY+15;
		}		
	}

	function showTooltip(cinfo,_x,_y){
		var obj = document.getElementById("TooltipLayer");		
		if(cinfo.tooltip=="null") return;
		var msg = cinfo.tooltip.replace("\n","<BR>");		
		obj.innerHTML = "<p style='font-size:11px'>"+msg+"</p>";
		obj.style.visibility = "visible";
	}	

	function hideTooltip(msg){				
		var obj = document.getElementById("TooltipLayer");
		obj.style.visibility = "hidden";				
	}
	function popupWindow(_x,_y,_w,_h,_wname,_addr){	
		if(_wname=="null"){
			window.open(_addr,"_blank","left="+_x+", top="+_y+", width="+_w+", height="+_h);
		}else{
			window.open(_addr,_wname,"left="+_x+", top="+_y+", width="+_w+", height="+_h);
		}		
	}
