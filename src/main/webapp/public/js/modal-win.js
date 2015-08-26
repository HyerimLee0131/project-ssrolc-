/// <reference path="jquery-1.3.2.min-vsdoc.js" />

/************************************************************************************************************
* SIMPLE MODAL v 2.0
* ?2009 FISHBOWL MEDIA LLC
* http://fishbowlmedia.com
***********************************************************************************************************/

(function ($) {

	/**********************************
	* CUSTOMIZE THE DEFAULT SETTINGS
	* Ex: 
	* var _settings = {
	* 	id: 'modal',
	* 	src: function(sender){
	*		return jQuery(sender).attr('href');
	*	},
	* 	width: 800,
	* 	height: 600
	* }
	**********************************/
	var _settings = {
		width: 800, // Use this value if not set in CSS or HTML
		height: 600, // Use this value if not set in CSS or HTML
		top:50,
		left:50,
		overlayOpacity:.85, // Use this value if not set in CSS or HTML
		id: 'modal',
		src: function (sender) {
			return sender;
		},
		fadeInSpeed: 0,
		fadeOutSpeed: 0,
		divClose:false
	}
	/**********************************
	* DO NOT CUSTOMIZE BELOW THIS LINE
	**********************************/
	$.modal = function (options) {
		return _modal(this, options);
	}
	$.modal.modal_open = function () {
		_modal.modal_open();
	}
	$.modal.modal_close = function () {
		_modal.modal_close();
	}
	
	$.fn.modal = function (options) {
		return _modal(this, options);
	}
	_modal = function (sender, params) {
		this.options = {
			parent: null,
			overlayOpacity: null,
			id: null,
			content: null,
			width: null,
			height: null,
			top:50,
			left:50,
			modalClassName: null,
			imageClassName: null,
			closeClassName: null,
			overlayClassName: null,
			src: null,
			divClose:false
		}
		
		
		this.options = $.extend({}, options, _defaults);
		this.options = $.extend({}, options, _settings);
		this.options = $.extend({}, options, params);
		this.modal_close = function (mid) {
			try{
				$("input:visible:enabled:first").focus();
			}catch(e){}
			$("#"+mid).remove();
			//jQuery('.' + options.modalClassName + ', .' + options.overlayClassName).fadeOut(_settings.fadeOutSpeed, function () { jQuery(this).unbind().remove(); });
		}
		this.close_hide = function () {
			jQuery('.' + options.modalClassName + ', .' + options.overlayClassName).fadeOut(_settings.fadeOutSpeed, function () { jQuery(this).unbind().hide(); });
		}
		this.close_location = function(url){
			parent.document.location.href=url;
			jQuery('.' + options.modalClassName + ', .' + options.overlayClassName).fadeOut(_settings.fadeOutSpeed, function () { jQuery(this).unbind().remove(); });

		}
		this.close_back = function(){
			jQuery('.' + options.modalClassName + ', .' + options.overlayClassName).fadeOut(_settings.fadeOutSpeed, function () { jQuery(this).unbind().remove(); });
			parent.history.back();
		}

		this.modal_open = function () {
			
			if (typeof options.src == 'function') {
				options.src = options.src(sender)
			} else {
				options.src = options.src || _defaults.src(sender);
			}
			
			
			var marginTop = ($(window).height() - options.height)/2;//($(window).scrollTop() + ($(window).height() - options.height) / 2);
			var marginLeft = ($(window).width() - options.width)/2;//($(window).scrollLeft() + ($(window).width() - options.width) / 2);
						
			if(options.top != null) marginTop = options.top;
			if(options.left != null) marginLeft = options.left;
			
			
			var fileExt = /^.+\.((jpg)|(gif)|(jpeg)|(png)|(jpg))$/i;
			var contentHTML = '';
			if (fileExt.test(options.src)) {
				contentHTML = '<div class="' + options.imageClassName + '"><img src="' + options.src + '"/></div>';
				
			} else {
				contentHTML = '<iframe width="' + options.width + '" height="' + options.height + '" frameborder="0" scrolling="no" allowtransparency="true" src="' + options.src + '" id="modal_frame">&lt/iframe>';
			}
			options.content = options.content || contentHTML;
			
			
			if (jQuery('.' + options.modalClassName).length && jQuery('.' + options.overlayClassName).length) {
				jQuery('.' + options.modalClassName).html(options.content); 
			} else {
				$overlay = jQuery((_isIE6()) ? '<iframe src="BLOCKED SCRIPT\'&lt;html&gt;&lt;/html&gt;\';" scrolling="no" frameborder="0" class="' + options.overlayClassName + '"></iframe><div class="' + options.overlayClassName + '"></div>' : '<div class="' + options.overlayClassName + '"></div>');
				//if(options.divClose == false){
					//$overlay.hide().appendTo(options.parent);
				//}

				$modal = jQuery('<div id="' + options.id + '" class="' + options.modalClassName + '" style="width:' + options.width + 'px; height:' + options.height + 'px; top:' + marginTop + 'px; left:' + marginLeft + 'px;">' + options.content + '</div>');
				$modal.hide().appendTo(options.parent);

				$close = jQuery('<a class="' + options.closeClassName + '"></a>');
				$close.appendTo($modal);

				var overlayOpacity = _getOpacity($overlay.not('iframe')) || options.overlayOpacity;
				$overlay.fadeTo(0, 0).show().not('iframe').fadeTo(_settings.fadeInSpeed,options.overlayOpacity);
				$modal.fadeIn(_settings.fadeInSpeed);

				$close.click(function () { jQuery.modal().modal_close(); });
				$overlay.click(function () {
					if(options.divClose == false){
						jQuery.modal().modal_close(); }
					}
				); //alert(options.id + "///" + marginTop + "///" + marginLeft);
			}
		}
		return this;
	}
	_isIE6 = function () {
		
	}
	_getOpacity = function (sender) {
		
		
		$sender = jQuery(sender);
		opacity = $sender.css('opacity');
		
		filter = $sender.css('filter');
			
		/*if (filter.indexOf("opacity=") >= 0) {
			
			return parseFloat(filter.match(/opacity=([^)]*)/)[1]) / 100;
		}
		else*/ if (opacity != '') {
			
			return opacity;
		}
		return '';
	}
	_defaults = {
		parent: 'body',
		overlayOpacity: 85,
		id: 'modal',
		content: null,
		width: 800,
		height: 600,
		top:0,
		height:0,
		modalClassName: 'modal-window',
		imageClassName: 'modal-image',
		closeClassName: 'close-window',
		overlayClassName: 'modal-overlay',
		src: function (sender) {
			return sender;
		},
		divClose:false
	}
})(jQuery);