/*
 *	Document   : init.js
 *	Created on : 2 Nov 2555, 14:22:41
 *	Author     : NIPIT
 *	Description:
 *		//Describe TODO here.
*/

$(function(){
	
	$('#slider').cycle({
		fx:      'fade',
		speed:   2000,
		timeout: 5000,
		pager:  '#navvv'
	});   

 }); //function
 
 $.fn.center = function() {
	var options = $.extend({ // Default values
		inside : window, // element, center into window
		transition : 0, // millisecond, transition time
		minX : 0, // pixel, minimum left element value
		minY : 0, // pixel, minimum top element value
		withScrolling : true, // booleen, take care of the scrollbar
		// (scrollTop)
		vertical : true, // booleen, center vertical
		horizontal : true
	// booleen, center horizontal
	}, options);
	return this.each(function() {
		var props = {
			position : 'absolute'
		};
		if (options.vertical) {
			var top = ($(options.inside).height() - $(this).outerHeight()) / 2;
			if (options.withScrolling)
				top += $(options.inside).scrollTop() || 0;
			top = (top > options.minY ? top : options.minY);
			$.extend(props, {
				top : top + 'px'
			});
		}
		if (options.horizontal) {
			var left = ($(options.inside).width() - $(this).outerWidth()) / 2;
			if (options.withScrolling)
				left += $(options.inside).scrollLeft() || 0;
			left = (left > options.minX ? left : options.minX);
			$.extend(props, {
				left : left + 'px'
			});
		}
		if (options.transition > 0)
			$(this).animate(props, options.transition);
		else
			$(this).css(props);
		return $(this);
	});
}

$.fn.ForceNumericOnly = function() {
	return this
			.each(function() {
				$(this)
						.keydown(
								function(e) {
									var key = e.charCode || e.keyCode || 0;
									// allow backspace, tab, delete, enter,
									// arrows, numbers and keypad numbers ONLY
									// home, end, period, and numpad decimal
									return (key == 8 || key == 9 || key == 13
											|| key == 46 || key == 110
											|| key == 190
											|| (key >= 35 && key <= 40)
											|| (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
								});
			});
};

function moveMessageboxToCenter(title) {
	var $msgbox = jq('.z-messagebox-window:contains(' + title + ')');
	var $ok = jq('button:contains("OK")');
	if ($ok != null) {
		$ok.on("click", function() {
			$msgbox.hide();
			return true;
		});
	}
	$msgbox.center({
		transition : 300
	});
}
 


