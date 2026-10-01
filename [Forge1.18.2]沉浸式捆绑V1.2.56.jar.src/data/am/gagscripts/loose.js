import shared-gagutils;

var LOOSE = {"l":"w", "s":"f", "t":"g"};

function modifyText(text) {
   return gagspeak(text, LOOSE);
}