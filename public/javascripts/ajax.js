function sendGuessHelper() {
  var g = document.getElementById("guess").value;
  console.log("Guess: "+g);
  sendGuess(g);
}

function sendGuess(g) {
  var requestObj = new XMLHttpRequest();
  requestObj.onreadystatechange = function() {
    if (requestObj.readyState == 4 && requestObj.status == 200){
      document.getElementById("output").innerHTML = requestObj.responseText;
      requestObj = null;
    }
  }
  requestObj.open("GET", "guess?g="+g, true);
  requestObj.send();
}
