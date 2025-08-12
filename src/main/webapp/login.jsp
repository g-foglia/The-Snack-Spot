<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="stile.css">
    <title>Login</title>
    <%@ include file="header.jsp" %>

</head>
<body>

    <div class="due">
    <div class="login">
        <h1>Login</h1>
        <form action="login-servlet" method="post" name="loginform">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <input class="bottone" type="submit" value="Accedi" onclick="return validazioneEmail()"><br>
        </form>
    </div>
    <div class="reg">
         <h1>Registrazione</h1>
         <p>Non hai ancora un account? </p> <a href="registrationForm.jsp">Registrati ora!</a>
         <p>I tuoi dati personali verranno usati per processare il tuo ordine e  migliorare la tua esperienza nel nostro sito.</p>
    </div>
    </div>



    <script>
        function validazioneEmail(email)
        {
            // recupero il valore della email indicata nel form
            var email=document.forms['loginform'].email.value;
            // se non ho inserito nulla nel campo
            if(email==''){alert("Devi aggiungere un indirizzo email");
                return false;}
            // verifico se è un indirizzo valido
            if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
            {
                return true;
            }
            else {
                alert("L'indirizzo email che hai inserito non e' valido");
            }
            return false;
        }
    </script>

<%@ include file="footer.jsp" %>
</body>
</html>
