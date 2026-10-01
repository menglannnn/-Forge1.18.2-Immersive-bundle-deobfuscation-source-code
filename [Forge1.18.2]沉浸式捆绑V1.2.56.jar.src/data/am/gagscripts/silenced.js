import shared-gagutils;

var SILENCED = {"*":".", " ":false, "'":false}

function modifyText(text) {
   return gagspeak(text, SILENCED);
}