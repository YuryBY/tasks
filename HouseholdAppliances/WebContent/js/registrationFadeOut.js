function provregistration(form) {
	var indicate = true;
	console.log('form:');
	console.log(form);
	obj_fields = new Array();
	for ( var i = 0; i < form.length; i++) {
		var input = $(form.elements[i]);
		if (!input.hasClass('hidden')) {
			obj_fields[i] = input.val();
			if (obj_fields[i] == "") {
				form.elements[i].className = "mistakeFieldRegistration";
				indicate = false;
			}
		}
	}	
	if (document.getElementById("password").value!=document.getElementById("confirmpwd").value) {
		
		//TODO FIXED CHECK PASSWORDS!
		alert('TODO FIXED CHECK PASSWORDS!');
	
			/*var windowWidth = document.documentElement.clientWidth;
			var windowHeight = document.documentElement.clientHeight;
			var popupHeight = $(".congratulations").height();
			var popupWidth = $(".congratulations").width();
			var top = windowHeight / 2 - popupHeight / 2;
			if (top < 0)
				top = 0;
			$(".back").css({
				"opacity" : "0.8",
				"height" : windowHeight
			});
			$(".back").fadeIn("slow");
			$('.congratulations').fadeIn('slow');
			$('.congratulations').css({
				'top' : top,
				'left' : windowWidth / 2 - popupWidth / 2
			});		*/
		indicate = false;
	}
	
	
	if (document.getElementById("check").checked == false) {
		document.getElementById("ch").className = "blankCheck";
		indicate = false;
	}
	console.log('indicate:' + indicate);
	if (indicate) {
		$('.tabbed_box').fadeOut('slow');
		$('.back').fadeOut('slow');
		form.submit();
		
		//TODO FIXED CONGRATULATIONS!!!
	/*	$(".congratulations").fadeIn("slow", function test() {
			$('.close_button_1').click(function() {
				if (indicate) {
					$(".congratulations").fadeOut("slow");
					indicate = 0;
				}
			});
			$(document).keypress(function(e) {
				if (e.keyCode == 27 && indicate) {
					$(".congratulations").fadeOut('slow');
					indicate = 0;
				}
			});
			$(".congratulations").fadeOut(7000);
		});*/
		// $(".congratulations").dequeue();

	}
}
