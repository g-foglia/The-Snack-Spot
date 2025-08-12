<%@ page import="model.Prodotto" %>
<%@ page import="model.Carrello" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>The Snack Spot</title>
    <%@ include file="header.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="stile.css">
    <script type="text/javascript">
        function prodottoInserito(){
            alert("Prodotto inserito nel carrello");
        }
    </script>
</head>
<body>
<div class="prodotti">
<%
    Carrello carrello= (Carrello) request.getAttribute("idCarrello");
    List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("salati");
    for(Prodotto prodotto : prodotti){
        if(prodotto.getQtDeposito() != 0){
%>
<form  method="post" action="aggiunta-carrello">
    <input type="hidden" name="pagina" value="salati">
    <input type="hidden" name="id" value="<%=prodotto.getIdProdotto()%>">
    <img src="Immagini/<%=prodotto.getNomeProdotto()%>.jpg"><br>
    <div class="products">
    <label><b>Nome:</b> <%=prodotto.getNomeProdotto()%></label><br>
    <label><b>Prezzo: &#8364;</b> <%=prodotto.getPrezzo()%></label><br>
    <label><b>Produttore:</b> <%=prodotto.getProduttore()%></label><br>
    <label><b>Descrizione:</b> <%=prodotto.getDescrizione()%></label><br>
    <label><b>Quantità:</b> <%=prodotto.getQtDeposito()%></label><br>
    <button class="aggcarrello" type="submit" name="option" value="aggiungi" id="addToCart" onclick="prodottoInserito()"><span>Aggiungi al carrello  </span><i class="fas fa-shopping-cart"></i></button>
    <label id="quantita">Quantità:</label>
        <select name="quantita">
            <%for(int i=1;i<=prodotto.getQtDeposito();i++){%>
            <option value="<%=i%>"><%=i%></option><%}%>
        </select>
        </div>
</form>
<%}
    }%>
    </div>
<%@ include file="footer.jsp" %>
</body>
</html>
