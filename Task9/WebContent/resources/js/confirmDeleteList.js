function confirmDeleteList() {
	/* var checkBoxes = document.getElementsByName("selectedNews"); */
	var checkBoxes = document.getElementsByClassName("Cb001");
	var result = false;
	for ( var i = 0; i < checkBoxes.length; i++) {
		if (checkBoxes[i].checked) {
			result = true;
		}
	}
	if (!result) {
		alert(deleteIncorrect);
		return false;
	}

	var isDelete = confirm(deleteConfirm);
	if (isDelete) {
		return true;
	} else {
		return false;
	}
}