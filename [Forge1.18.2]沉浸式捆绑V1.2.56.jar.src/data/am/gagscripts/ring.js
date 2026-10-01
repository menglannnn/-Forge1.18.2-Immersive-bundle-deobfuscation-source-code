import shared-gagutils;

var RING = {"b":"h", "d":"n", "f":"h", "g":"h", "j":"gh", "k":"ch", "l":"u", "m":"nh", "p":"ch", "q":"ch", "r":"u", "s":"h", "t":"gh", "v":"gh", "w":"uu", "x":"ch", "z":"gh"}

function modifyText(text) {
   return gagspeak(text, RING);
}