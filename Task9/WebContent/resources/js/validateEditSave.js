function validateFormSave() {

	var newsTitle = document.getElementById("editForm:titleText");
	var newsDate = document.getElementById("editForm:dateText");
	var newsBrief = document.getElementById("editForm:briefArea");
	var newsContent = document.getElementById("editForm:contentArea");
	var incorrectInputMessage = "";
	incorrectInputMessage += validateTitle(newsTitle);
	incorrectInputMessage += validateBrief(newsBrief);
	incorrectInputMessage += validateContent(newsContent);
	incorrectInputMessage += validateDate(newsDate);
	if (!isEmpty(incorrectInputMessage)) {
		alert(incorrectInputMessage);
		return false;
	}
	return true;
}

function isEmpty(element) {
	if (element == null || element == "")
		return true;
	else {
		return false;
	}
}

function exceed(element, length) {
	if (element > length)
		return true;
	else {
		return false;
	}
}

function validateTitle(title) {
	var message = "";
	if (isEmpty(title.value)) {
		message += titleEmpty + "\n";
	}
	if (exceed(title.value.length, 100)) {
		message += titleExceed + "\n";
	}
	return message;
}

function validateBrief(brief) {
	var message = "";
	if (isEmpty(brief.value)) {
		message += briefEmpty + "\n";
	}

	if (exceed(brief.value.length, 500)) {
		message += briefExceed + "\n";
	}
	return message;
}

function validateContent(content) {
	var message = "";
	if (isEmpty(content.value)) {
		message += contentEmpty + "\n";
	}

	if (exceed(content.value.length, 2048)) {
		message += contentExceed + "\n";
	}
	return message;
}

function validateDate(date) {
	var message = "";
	var dateReg = new RegExp("^(([0-9][0-9])/([0-3][0-9])/((19|20)[0-9][0-9]))");
	var dateArray = date.value.split("/");
	var month = dateArray[0];
	var day = dateArray[1];
	var year = dateArray[2];
	if (!validDate(month, day, year)) {
		message += dateError + "\n";
	}
	if (!dateReg.test(date.value)) {
		message += dateError + "\n";
	}
	return message;
}

function validDate(month, day, year) {
	var dateString = new Date(year, parseInt(month, 10) - 1, day);
	var dateCurrent = {
		"month" : month,
		"day" : day,
		"year" : year
	};
	return isEqualDate(dateString, dateCurrent);
}

function isEqualDate(dateObject, dateString) {
	if (dateObject.getMonth() + 1 != dateString["month"]) {
		return false;
	}
	if (dateObject.getDate() != dateString["day"]) {
		return false;
	}
	if (dateObject.getFullYear() != dateString["year"]) {
		return false;
	}
	return true;
}