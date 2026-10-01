const skippedSymbols = ['!', '?', '.', '>', '<', '/', '\\', '^'];

function modifyText(text) {
  return translateString(text);
}

function translateString(str) {
  if (str.length === 0) {
    return "";
  }
  
  var translatedString = "";
  var words = str.split(" ");
  for (var i=0; i<words.length; ++i) {
    translatedString += translateWord(words[i]) + " ";
  }
  
  return translatedString.substring(0, translatedString.length - 1);
}

function translateWord(word) {
  if (word.length === 0) {
    return "";
  }
  
  var translatedWord = "";
  var splitPoint1 = Math.floor(word.length / 3);
  var splitPoint2 = splitPoint1*2;
  
  for (var i=0; i<word.length; ++i) {
    if (skippedSymbols.indexOf(word[i]) >= 0) {
      translatedWord += word[i];
      continue;
    }
    
    if (i === 0) {
      translatedWord += "M";
    }
    else if (i < splitPoint1) {
      translatedWord += "m";
    }
    else if (i < splitPoint2) {
      translatedWord += "p";
    }
    else {
      translatedWord += "h";
    }
  }
  
  return translatedWord;
}

// testing code (can technically be removed)
if(typeof console !== "undefined") // <-- but you can keep it
	console.log(modifyText("Hello, how are you doing? ... ! >//<"))
