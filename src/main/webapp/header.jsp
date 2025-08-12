<%@ page import="model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <meta charset="UTF-8">
  <title>Benvenuti</title>
  <link rel="stylesheet" type="text/css" href="stile.css">
  <script> $(function(){
    $(".fa-bars").click(function(){
      let menu=document.getElementById("menu").style.display
        if (menu==="none")
            $("#menu").css("display","flex")
        else
            $("#menu").css("display","none")
    })
  })
  </script>

</head>

<body>
<div class="row">
  <div class="header">
    <h3>thesnackspot@gmail.com </h3>
    <h3>spedizione gratuita su tutti gli ordini</h3>
    <h3><i class="fa-solid fa-location-dot"></i>UNISA</h3>
  </div>

  <div class="nav">
      <div class="logo">
          <a href="index.jsp"><img class="logo" src="Immagini/Senza%20titolo-1.png"></a>
      </div>
      <div class="search">
      <i class="fa-solid fa-bars"></i>
      <form action="ricerca">
        <input type="text" placeholder="Cerca un prodotto!" name="key">
        <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
      </form>
       </div>
       <div class="icons">
       <a href="mostra-carrello"><i class="fa-solid fa-basket-shopping"></i></a>
           <%if(request.getSession().getAttribute("utente") == null){%>
           <a href="login.jsp"> <i class="fa-solid fa-circle-user"></i></a>
           <%}
           else{%>
           <a href="utenteinfo.jsp"> <i class="fa-solid fa-circle-user"></i></a>
                   <%}%>
       </div>
  </div>

  <div id="menu">
    <a href="mostra-dolci">Dolci</a>
    <a href="mostra-bibite">Bevande</a>
    <a href="mostra-salati">Salati</a>
  </div>
</div>
</body>

</html>
