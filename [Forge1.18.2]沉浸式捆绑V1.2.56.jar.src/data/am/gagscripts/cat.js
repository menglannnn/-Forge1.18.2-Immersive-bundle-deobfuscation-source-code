import shared-gagutils;

var CATNOISE = ["mrr", "mhhrr", "meow", "myaw", "nya", "mew", "mraow", "miew", "myah", "miow", "myaow", "miaow"]

function modifyText(message) {
        return noiseSpeak(message, CATNOISE);
}