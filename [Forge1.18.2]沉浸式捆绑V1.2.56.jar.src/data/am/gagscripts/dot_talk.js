// do not change the name of this function (it will be called from the mod)
function modifyText(text) {
  // custom gag text generation here
  return  text.replace(/[a-zA-Z]/g,'.')
}

// testing code (can technically be removed)
if(typeof console !== "undefined") // <-- but you can keep it
	console.log(modifyText("Hello, how are you doing?"))