import shared-gagutils;

var DEFAULT = {"a": "n", "b:": "h", "d": "n", "e": "m", "i": "n", "j":"n", "k":"h", "l":"m", "o": "n", "q":"g", "s":"ph", "t":"ph", "u":"n", "v":"f", "w":"mm", "x":"gh", "y":"m", "z": "ph"}

function modifyText(text) {
   return gagspeak(text, DEFAULT);
}