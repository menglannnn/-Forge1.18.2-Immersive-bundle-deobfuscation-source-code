//!hidden
var SOUNDS_LOOKUP = {
    CHOKE : ["§o-Gnk!§r", "§o-Gngh!§r", "§o-gnH!§r", "§o-gnH!§r", "§o-nGH!§r", "§o-Agh!§r"],
}

// Utility Functions

function hasSpeech(message){
    for (var charnum = 0; charnum < message.length; charnum++) {
        var charcode = message.charCodeAt(charnum)
        if (charcode >= 65 && charcode <= 90) {
            return true;
        } else if (charcode >= 97 && charcode <= 122) {
            return true;
        }
    }
    return false
}


function getTrailPunt(message){
    if (message.length == 0) {
        return "";
    }
    var char = message[message.length-1];
    if (!hasSpeech(char)) {
        return getTrailPunt(message.substring(0,message.length-1)) + char
    }
    return ""
}


function getHeadPunt(message){
    if (message.length == 0) {
        return "";
    }
    var char = message[0];
    if (!hasSpeech(char)) {
        return char + getHeadPunt(message.substring(1));
    }
    return ""
}


function gagspeak(text, lookup) {
    var newtext = "";
    for (var charnum = 0; charnum < text.length; charnum++) {
        var char = text[charnum];
        if (lookup[char] == false) {

        } else if (lookup[char]) {
            newtext += lookup[char];
        } else if (lookup["*"] && hasSpeech(char)) {
            newtext += lookup["*"];
        } else if (lookup[char.toLowerCase()]) {
            newtext += lookup[char.toLowerCase()].toUpperCase();
        }

        else {
            newtext += char;
        }
    }
    return newtext;
}


function mapWords(text, func) {
    var text = text.split(" ")
    var output = text.map(func)
    return output.join(" ")
}


function stammer(text, intensity) {
    if (intensity == null) {
        intensity = 1;
    }

    function stammerize(word) {
        if (word.length > 0) {
            if (hasSpeech(word)) {
                if (Math.random() < 0.3) {
                    var head_punt = getHeadPunt(word);
                    if (head_punt.length > 0) {
                        if (head_punt[0] == "§") {
                            return word
                        }
                        var trimmed = word.substring(head_punt.length);
                        if (trimmed) {
                            return head_punt + trimmed[0] + "-" + trimmed
                        }
                    } else {
                        return word[0] + "-" + word
                    }
                }
            }
        }

        return word
    }

    if (intensity <= 1) {
        return mapWords(text, stammerize);;
    } else {
        return stammer(mapWords(text, stammerize), intensity-1)
    }


}

function hasWord(sentence, words) {
    var sentence = sentence.toLowerCase();
    for (var wordPos = 0; wordPos < words.length; wordPos++) {
        var word = words[wordPos].toLowerCase();
        if (sentence.indexOf(words[wordPos]) != -1) {
            return true
        }
    }
    return false
}

function noiseSpeak(text, noises) {
    function randomNoise(word) {
        if (hasSpeech(word)) {
            return getHeadPunt(word) + pickRandom(noises) + getTrailPunt(word)
        } else {
            return word
        }
    }

    return mapWords(text, randomNoise)


}


function pickRandom(arr) {
    return arr[Math.floor(Math.random() * arr.length)]
}

function moanGen(size) {
    if (!size) {
        var size = 1;
    }
    if (Math.random() > 0.5) {
        var m = "§o"+pickRandom(["Aah", "Aah", "Nyah"])

        for (var i=0; i < size; i++) {
            m += pickRandom(["h","hh"])
        }

        m  += pickRandom(["!", "~"]) + "§r"
        return m;

    } else {
        var m = "§o" + pickRandom(["Mmm", "Mm", "Nnn", "Mngh", "Mnnngh", "Mmmmm"])
        for (var i=0; i < size; i++) {
            m += pickRandom(["nnn", "ngh", "nn", "gg"])
        }

        m += pickRandom(["ng", "", "nh"])
        m += pickRandom(["...", "~"]) + "§r"
        return m;

    }
}

function choke_random(text) {
    if (!hasSpeech(text)) {
        return text;
    }

    return text.substring(0,Math.random() * text.length) + pickRandom(SOUNDS_LOOKUP.CHOKE)
}
