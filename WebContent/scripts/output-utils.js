function getNextPage(numberFrom,resultRegion) {
	  var baseAddress = "next-page";
	  var data = "startrow=" + numberFrom;
	  var address = baseAddress + "?" + data;
	  ajaxResult(address, resultRegion);
	
}
function showData(idField, resultRegion) {
  var UPDATE_DELAY_TIME = 5000;	
  var baseAddress = "data-controller";
  var data = "sisestus=" + getValue(idField);
  var address = baseAddress + "?" + data;
  ajaxResult(address, resultRegion);
  (function refreshRates() {

	  setTimeout(function() {
            getNextPage(0,resultRegion);
            refreshRates();      
	  },UPDATE_DELAY_TIME);
  })();
}

function getValue(id) {
	  return(escape(document.getElementById(id).value));
	}

