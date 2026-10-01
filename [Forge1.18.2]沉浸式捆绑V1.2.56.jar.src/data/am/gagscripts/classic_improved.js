var letterTranslation = Object.create(null);

function modifyText(text) {
  createMapping();
  return translateString(text);
}

function translateString(str) {
  var translatedString = "";

  for (var i=0; i<str.length; ++i) {
    var character = str[i];
    var translatedCharacter = letterTranslation[character];
    translatedString += (typeof translatedCharacter !== "undefined") ? translatedCharacter : character;
  }

  return translatedString;
}

function createMapping() {
  letterTranslation['a'] = "n";
  letterTranslation['b'] = "h";
  letterTranslation['c'] = "c";
  letterTranslation['d'] = "n";
  letterTranslation['e'] = "m";
  letterTranslation['f'] = "f";
  letterTranslation['g'] = "g";
  letterTranslation['h'] = "h";
  letterTranslation['i'] = "n";
  letterTranslation['j'] = "n";
  letterTranslation['k'] = "h";
  letterTranslation['l'] = "m";
  letterTranslation['m'] = "m";
  letterTranslation['n'] = "n";
  letterTranslation['o'] = "n";
  letterTranslation['p'] = "p";
  letterTranslation['q'] = "g";
  letterTranslation['r'] = "r";
  letterTranslation['s'] = "ph";
  letterTranslation['t'] = "ph";
  letterTranslation['u'] = "n";
  letterTranslation['v'] = "f";
  letterTranslation['w'] = "mm";
  letterTranslation['x'] = "gh";
  letterTranslation['y'] = "m";
  letterTranslation['z'] = "ph";
  letterTranslation['A'] = "N";
  letterTranslation['B'] = "H";
  letterTranslation['C'] = "C";
  letterTranslation['D'] = "N";
  letterTranslation['E'] = "M";
  letterTranslation['F'] = "F";
  letterTranslation['G'] = "G";
  letterTranslation['H'] = "H";
  letterTranslation['I'] = "N";
  letterTranslation['J'] = "N";
  letterTranslation['K'] = "H";
  letterTranslation['L'] = "M";
  letterTranslation['M'] = "M";
  letterTranslation['N'] = "N";
  letterTranslation['O'] = "N";
  letterTranslation['P'] = "P";
  letterTranslation['Q'] = "G";
  letterTranslation['R'] = "R";
  letterTranslation['S'] = "PH";
  letterTranslation['T'] = "PH";
  letterTranslation['U'] = "N";
  letterTranslation['V'] = "F";
  letterTranslation['W'] = "MM";
  letterTranslation['X'] = "GH";
  letterTranslation['Y'] = "M";
  letterTranslation['Z'] = "PH";
}

// testing code (can be removed or kept in)
if(typeof console !== "undefined") {
	console.log(modifyText("Hello, how are you doing?"));
}
