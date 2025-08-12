<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Inserimento</title>
  <%@ include file="header.jsp" %>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="sfondo">

<div class="admin">
<form action="mostra-prodotto" method="post" name="selezioneForm">
  <label for="idProdotto">Id Prodotto:</label>
  <input   type="text" id="idProdotto" name="idProdotto" required><br>

  <input id="modprodotto" type="submit" value="Vai">
</form>
  <br>
</div>
<%@ include file="footer.jsp" %>
</div>
</body>
</html>