import shared-gagutils;

var FOXNOISE = ["Yip", "he-he", "wa-uh", "yip-yip", "yip", "wa-Uh", "Wa-Uh", "Yip-yip"]

function modifyText(message) {
        return noiseSpeak(message, FOXNOISE);
}
