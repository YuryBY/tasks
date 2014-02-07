var status = 0;
$(document).ready(function() {
	$('.click').click(function() {
		if (status == 0) {
			var windowWidth = document.documentElement.clientWidth;
			var windowHeight = document.documentElement.clientHeight;
			var popupWidth = $(".tabbed_box").width();
			var top = 0;
			$(".back").css({
				"opacity" : "0.8",
				"height" : windowHeight
			});
			$(".back").fadeIn("slow");
			$('.tabbed_box').fadeIn('slow');
			$('.tabbed_box').css({
				'top' : top,
				'left' : windowWidth / 2 - popupWidth / 2
			});
			status = 1;
		}
	});
	$('.back').click(function() {
		if (status == 1) {
			$(".back").fadeOut("slow");
			$(".tabbed_box").fadeOut("slow");
			status = 0;
		}
	});
	$('.close_button').click(function() {
		if (status == 1) {
			$(".back").fadeOut("slow");
			$(".tabbed_box").fadeOut("slow");
			status = 0;
		}
	});
	initPasswordVisibilityToggle();
});

function initPasswordVisibilityToggle() {
	var pwd = $('input[name=pwd]');
	var ch = $('input[name=showPassword]');
	var pwdPlain = $('input[name=pwd-plain]');
	ch.change(function() {
		if (ch.is(':checked')) {
			pwd.hide();
			pwdPlain.show();
			pwdPlain.val(pwd.val());
		} else {
			pwdPlain.hide();
			pwd.show();
			pwd.val(pwdPlain.val());
		}
	});
}
$(document).keypress(function(e) {
	if (e.keyCode == 27 && status == 1) {
		$('.tabbed_box').fadeOut('slow');
		$('.back').fadeOut('slow');
		status = 0;
	}
});
