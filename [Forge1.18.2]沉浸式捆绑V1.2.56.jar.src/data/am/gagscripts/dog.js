import shared-gagutils;

var DOGNOISE = ["arf", "woof", "bark", "ruff", "wruff", "yawp"]
function modifyText(message) {
    return noiseSpeak(message, DOGNOISE);
}
