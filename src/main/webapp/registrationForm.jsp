<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link rel="stylesheet" type="text/css" href="stile.css">
  <title>Registrazione</title>
  <%@ include file="header.jsp" %>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%-- <script src="scripts/jquery-3.js"></script> --%>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js " ></script>
  <script>
    //questa funzione chiama la funzione ajax quando il campo email perde il focus
    $(function (){
      $("#email").blur(function (){
        ajaxValidate();
      })
    })

    //questa funzione ajax permette di controllare se l'email con cui ci si prova a registrare è gia presente nel db
    function ajaxValidate(){
      let email = document.getElementById("email").value;
      let xml = new XMLHttpRequest();
      xml.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
          if(this.responseText === "invalid"){

            $(function(){
              alert("L'email \u00E8 gi\u00E0 associata ad un account")
            })
            return false
          }else {
            console.log("ciao");
            return true;
          }
        }
      }

      xml.open("POST","verifica-email?email="+email,true);
      xml.send();
    }
  </script>
</head>
<body class="sfondo">

<div class="registrazione">
<form action="registration-servlet" method="post" name="registrationForm">
  <h1>Crea un account!</h1>
  <label for="email">Email:</label>
  <input type="email" name="email" id="email" required><br>
  <label for="password">Password:</label> <br>
  <input type="password" id="password" name="password" onkeyup="passwordValidator()" required><br>
  <div id="ProgressBar">
    <div id="bar">
    </div>
    <p id="alert">
    </p>
  </div>
  <label for="nome">Nome:</label>
  <input type="text" id="nome" name="nome" required><br>
  <label for="cognome">Cognome:</label>
  <input type="text" id="cognome" name="cognome" required><br>
  <label for="Via">Via:</label>
  <input type="text" id="via" name="via" required><br>
  <label for="citta">Citt&#224:</label>
  <input type="text" id="citta" name="citta" required><br>
  <label for="nc">Numero Civico:</label>
  <input type="text" id="nc" name="nc" required><br>
  <label for="cap">Cap:</label>
  <input type="number" id="cap" name="cap" required><br>
  <label for="cellulare">Cellulare:</label>
  <input type="tel" id="cellulare" name="cellulare" required><br>
  <input id="button" type="submit"  onclick="return validazioneEmail()"  value="Registrati">
</form>
</div>

<script>
  function passwordValidator(){
    var x = 0;
    var password = document.getElementById('password').value;

    //controllo numeri
    var check=/[0-9]/;
    if(check.test(password)){
      x = x + 20;
    }
    //controllo minuscole
    var check2=/[a-z]/;
    if(check2.test(password)){
      x = x + 20;
    }
    //controllo maiuscole
    var check3=/[A-Z]/;
    if(check3.test(password)){
      x = x + 20;
    }
    //controllo simboli
    var check4=/[$-/:-?{-~!"^_`\[\]]/;
    if(check4.test(password)){
      x = x + 20;
    }
    // controllo lunghezza
    if(password.length >=10){
      x = x + 20;
    }

    // risultato
    $("#bar").css({"width": x+"%"})

    // voto massimo 100
    if (x == 100) {
      $("#bar").css({"background-color":"green"})
      $("#alert").html("Molto forte")
    }
    if (x >60) {
      $("#bar").css({"background-color":"green"})
      $("#alert").html("Forte")
    }
    if (x <=40) {
      $("#bar").css({"background-color":"yellow"})
      $("#alert").html("Buona")
    }
    //voto minimo 20
    if (x <=20) {
      $("#bar").css({"background-color":"red"})
      $("#alert").html("Debole")
    }

    if(password.length == 0){
      x == 0;
      $("#alert").html("")
    }

    //controllo spazi bianchi
    var check5=/\s\S/;
    if(check5.test(password)){
      $("#bar").css({"background-color":"red"})
      $("#alert").html("La password non può conenere spazi bianchi")
    }
  }

  function validazioneEmail(email)
  {
    // recupero il valore della email indicata nel form
    var email=document.forms['registrationForm'].email.value;
    // se non ho inserito nulla nel campo
    if(email==''){alert("Devi aggiungere un indirizzo email");
      return false;}
    // verifico se è un indirizzo valido
    if (/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(email))
    {
      return true;
    }
    else {
      alert("L'indirizzo email che hai inserito non e' valido\n" +
              "Un indirizzo valido ha la forma esempio@esempio.com");
    }
    return false;
  }
</script>
<%@ include file="footer.jsp" %>
</body>
</html>