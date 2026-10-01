import shared-gagutils;

function modifyText(message) {
   if (Math.random() < 0.3) {
      var message = choke_random(message);
   }
    return message;    
}