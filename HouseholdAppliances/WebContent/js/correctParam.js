function correctParam(id) {
	var input;
	switch (id) {
	case (id = "update"):
		input = $("<input>").attr("type", "hidden").attr("name", "command")
				.val("Update Disk Info");
		$('#diskListForm').append($(input));
		$('#diskListForm').submit();
		break;
	case (id = "buy"):
		input = $("<input>").attr("type", "hidden").attr("name", "command")
				.val("Buy");
		$('#diskListForm').append($(input));
		$('#diskListForm').submit();
		break;
	case (id = "delete"):
		input = $("<input>").attr("type", "hidden").attr("name", "command")
				.val("Delete");
		$('#diskListForm').append($(input));
		$('#diskListForm').submit();
		break;
	default:
		alert('Command is wrong!');
	}
}