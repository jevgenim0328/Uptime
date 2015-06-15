// Get the browser-specific request object, either for
// Firefox, Safari, Opera, Mozilla, Netscape, IE 7, or IE 8 
// (top entry) or for IE 5.5 and 6 (second entry). 

function getRequestObject() {
  if (window.XMLHttpRequest) {
    return(new XMLHttpRequest());
  } else if (window.ActiveXObject) { 
    return(new ActiveXObject("Microsoft.XMLHTTP"));
  } else {
    return(null); 
  }
}


// Make an HTTP request to the given address. 
// Display result in the HTML element that has given ID.

function ajaxResult(address, resultRegion) {
	  var request = getRequestObject();
	  request.onreadystatechange = 
	    function() { showResponseText(request, 
	                                  resultRegion); };
	  request.open("GET", address, true);
	  request.send(null);
	}

// Put response text in the HTML element that has given ID.

function showResponseText(request, resultRegion) {
  if ((request.readyState == 4) &&
      (request.status == 200)) {
    htmlInsert(resultRegion, request.responseText);
  }
}

// Insert the HTML data into the element that has the specified id.

function htmlInsert(id, htmlData) {
  document.getElementById(id).innerHTML = htmlData;
}