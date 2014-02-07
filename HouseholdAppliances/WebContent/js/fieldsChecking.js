function checkFields(form) {
	console.log('form is:');
	console.log(form);
	indicate = true;
	console.log('indicate 1:' + indicate);
	obj_fields = new Array();
	for ( var i = 0; i < form.length; i++) {
		var input = $(form.elements[i]);
		obj_fields[i] = input.val();
		if (obj_fields[i] == "") {
			form.elements[i].className = "mistakeField";
			indicate = false;
		}
	}
	console.log('indicate 2:' + indicate);
	if (indicate) {
		console.log('indicate 3:' + indicate);
		form.submit();
	}
}