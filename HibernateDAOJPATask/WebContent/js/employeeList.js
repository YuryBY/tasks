function previous(notesCount, notesOnPage) {
	var linksInContainer = 9;
	var pagesCount = Math.floor((notesCount / notesOnPage));
	if((notesCount % notesOnPage) !== 0) {
		pagesCount++;
	}
	var contaierArray = document.getElementsByClassName("container");
	for(var index = 0; index < contaierArray.length; index++) {
		var container = contaierArray[index];
		var linkArray = container.getElementsByTagName("a");
		var link = linkArray[0];
		var linkValue = parseInt(link.textContent);
		if(linkValue > 1) {
			container.innerHTML = "";
			if(linkValue - linksInContainer <= 0) {
				for(var linkIndex = 1; linkIndex <= pagesCount && 
						linkIndex <= linksInContainer; linkIndex++) {
					addLink(container, linkIndex, notesOnPage);
				}
			} else {
				for(var linkIndex = 1; linkIndex <= pagesCount && 
						linkIndex <= linksInContainer; linkIndex++) {
					addLink(container, linkIndex + (linkValue - linksInContainer -1), notesOnPage);
				}
			}
		} 
	}
}

function next(notesCount, notesOnPage) {
	var linksInContainer = 9;
	var pagesCount = Math.floor((notesCount / notesOnPage));
	if((notesCount % notesOnPage) !== 0) {
		pagesCount++;
	}
	var contaierArray = document.getElementsByClassName("container");
	for(var index = 0; index < contaierArray.length; index++) {
		var container = contaierArray[index];
		var linkArray = container.getElementsByTagName("a");
		var link = linkArray[linksInContainer - 1];
		var linkValue = parseInt(link.textContent);
		if(linkValue < pagesCount) {
			container.innerHTML = "";
			if(linkValue + linksInContainer > pagesCount) {
				for(var linkIndex = 1; linkIndex <= pagesCount && 
						linkIndex <= linksInContainer; linkIndex++) {
					addLink(container, (pagesCount - linksInContainer ) + linkIndex, notesOnPage);
				}
			} else {
				for(var linkIndex = 1; linkIndex <= pagesCount && 
						linkIndex <= linksInContainer; linkIndex++) {
					addLink(container, linkIndex + linkValue, notesOnPage);
				}
			}
		} 
	}
}

function start(notesCount, notesOnPage, currentPage) {
	var linksInContainer = 9;
	var pagesCount = Math.floor((notesCount / notesOnPage));
	var middlePage = Math.floor((linksInContainer / 2)) + 1;
	if((notesCount % notesOnPage) !== 0) {
		pagesCount++;
	}
	var contaierArray = document.getElementsByClassName("container");
	for(var index = 0; index < contaierArray.length; index++) {
		var container = contaierArray[index];
		var linkArray = container.getElementsByTagName("a");
		if(linkArray.length === 0) {
			container.innerHTML = "";
			var indent = 0;
			if(currentPage - middlePage > 0) {
				if(currentPage - middlePage + linksInContainer > pagesCount) {
					indent = pagesCount - linksInContainer;
				} else {
					indent = currentPage - middlePage;
				}
			}
			for(var linkIndex = 1; linkIndex <= pagesCount && 
						linkIndex <= linksInContainer; linkIndex++) {
				addLink(container, linkIndex + indent, notesOnPage);
			}
		}
	}
}

function addLink(container, pageNumber, notesOnPage) {
	var link = document.createElement("a");
	var text= document.createTextNode(pageNumber);
	var textSpace = document.createTextNode(" ");
	link.appendChild(text);
	var linkRef = 'EmployeeAction.do?method=list&pageItem.itemOnPage=' + notesOnPage
			+ '&pageItem.currentPage=' + pageNumber;
	link.setAttribute('href', linkRef);
	container.appendChild(link);
	container.appendChild(textSpace);
}