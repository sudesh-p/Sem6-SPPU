function validateForm() {
    var x = document.forms["myform"]["name"].value;
    if (x == "" || x.match(q)) {
      alert("Name must be filled out");
      return false;
    }
 var z= document.forms["myform"]["email"].value;
  let regexEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
  if (z.match(regexEmail)) {
    return true; 
  } else {
   alert("email must be filled out");
    return false; 
  }
  var y = document.forms["myform"]["message"].value;
  if (y == "") {
    alert("message must be filled out");
    return false;
  }  
}
    

  