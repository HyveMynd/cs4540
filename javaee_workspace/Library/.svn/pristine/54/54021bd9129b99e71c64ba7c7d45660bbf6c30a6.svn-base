// Get the browser-specific request object, either for
// Internet Explorer 5 and 6 (top entry) or for Firefox,
// Safari, Opera, Mozilla, Netscape, or IE 7 (bottom entry).

function getRequestObject() {
  if (window.ActiveXObject) { 
    return(new ActiveXObject("Microsoft.XMLHTTP"));
  } 
  else if (window.XMLHttpRequest) {
    return(new XMLHttpRequest());
  } 
  else {
    return(null);
  }
}


// Invokes the given address, arranging for the
// result to be sent back to the provided callback.

function invokeAndCallBack (address, callback) {
  var request = getRequestObject();
  request.onreadystatechange = function() { waitForResult(request, callback); };
  request.open("GET", address, true);
  request.send(null);
}


function invokeAndCallBackPost (address, data, callback) {
  var request = getRequestObject();
  request.onreadystatechange = function() { waitForResult(request, callback); };
  request.open("POST", address, true);
  request.setRequestHeader("Content-Type",
                           "application/x-www-form-urlencoded");
  request.send(data);
}

// Invokes the callback if the request is ready

function waitForResult (request, callback) {
  if ((request.readyState == 4) && (request.status == 200)) {
    callback(request);
  }
}




// Trick so that the Firebug console.log function will
// be ignored (instead of crashing) in Internet Explorer.
// Also see Firebug Lite and Faux Console if you want 
// logging to actually do something on IE.
// Firebug Lite: http://www.getfirebug.com/lite.html
// Faux Console: http://icant.co.uk/sandbox/fauxconsole/
 
try { 
  console.log("Loading script"); 
} catch(e) { console = { log: function() {} }; }