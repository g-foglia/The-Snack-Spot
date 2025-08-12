<%@ page import="model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Modifica</title>
  <%@ include file="header.jsp" %>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>

<div class="sfondo">
<% Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");
   if(prodotto != null){
     String categoria = (String) request.getAttribute("categoria");
     List<String> allergeni = (List<String>) request.getAttribute("allergeni");
%>
  <div class="admin">
<form action="modifica-prodotto" method="post" name="inserimentoForm" enctype="multipart/form-data">
  <label>Id Prodotto: <%=prodotto.getIdProdotto()%></label><br>
  <input type="hidden" id="idProdotto" name="idProdotto" value="<%=prodotto.getIdProdotto()%>"><br>
  <label for="nome">Nome:</label>
  <input type="text" id="nome" name="nome" value="<%=prodotto.getNomeProdotto()%>" required><br>
  <label for="prezzo">Prezzo:</label>
  <input type="text" id="prezzo" name="prezzo" value="<%=prodotto.getPrezzo()%>" required><br>
  <label for="produttore">Produttore:</label>
  <input type="text" id="produttore" name="produttore" value="<%=prodotto.getProduttore()%>" required><br>
  <label for="descrizione">Descrizione (massimo 50 caratteri):</label>
  <input type="text" id="descrizione" name="descrizione" maxlength="50" value="<%=prodotto.getDescrizione()%>" required><br>
  <label for="quantità">Quantita' in deposito:</label>
  <input type="number" id="quantità" name="quantità" min="0" value="<%=prodotto.getQtDeposito()%>" required><br><br>

  <label>Categoria attuale: <%=categoria%></label><br>
  <label>Nuova categoria:</label>
  <select name="categoria" id="categoria">
    <option value="Dolce">Dolce</option>
    <option value="Salato">Salato</option>
    <option value="Bibita">Bibita</option>
  </select><br>

  <label>Allergeni attuali: </label>
  <%for(String allergene : allergeni){%>
  <label><%=allergene%></label><br>
  <%}%>
  <label>Nuovi allergeni:</label><br>
  <input type="checkbox" id="Glutine" name="Glutine" value="Glutine">
  <label for="Glutine"> Glutine</label><br>
  <input type="checkbox" id="Frutta a guscio" name="Frutta a guscio" value="Frutta a guscio">
  <label for="Frutta a guscio"> Frutta a guscio</label><br>
  <input type="checkbox" id="Lattosio" name="Lattosio" value="Lattosio">
  <label for="Lattosio"> Lattosio</label><br>
  <input type="checkbox" id="Uovo" name="Uovo" value="Uovo">
  <label for="Uovo"> Uovo</label><br>

  <img src="Immagini/<%=prodotto.getNomeProdotto()%>.jpg"><br>

  <label for="file">Nuova foto prodotto:</label>
  <input type="file" id="file" name="file"/><br>

  <label>Impostare la quantità di un prodotto a 0 equivale alla sua cancellazione dal catagolo</label><br>
  <input id="modifica" type="submit" value="Modifica">
</form>
<%}else{%>
    <div class="idsbagliato">
    <h1>Nessun prodotto con questo codice</h1>
    </div>
<%}%>
  </div>
<%@ include file="footer.jsp" %>
</div>
</body>
</html>
