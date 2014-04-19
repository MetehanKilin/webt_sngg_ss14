function sendGuessHelper() {
  var g = $("#guess").val();
  console.log("Guess: "+g);
  sendGuess(g);
}

function sendGuess(g) {

  // Version 1 without error handling!
  $("#output").load("guess?g="+g);


  // Version 2 with error handling!
/*
 $("#output").load("guess?g="+g, function(responseTxt, statusTxt, xhr){
    if(statusTxt == "sucess") {
      console.log("Sucess");
    } else if (statusTxt == "error") {
      alert("AJAX ERROR with status "+ xhr.status+": "+xhr.statusText);
    }
  });
*/
}
