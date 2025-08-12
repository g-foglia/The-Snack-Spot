<%@ page import="model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>The Snack Spot</title>
    <%@ include file="header.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>

<div class="prodotti">
<%
    List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("trovati");
    if(prodotti != null){
        int i=0;
    for(Prodotto prodotto : prodotti){
        if(prodotto.getQtDeposito() > 0){%>
        <form method="post" action="aggiunta-carrello">
            <input type="hidden" name="pagina" value="ricerca">
            <input type="hidden" name="key" value="<%=request.getAttribute("key")%>">
            <input type="hidden" name="id" value="<%=prodotto.getIdProdotto()%>">
            <img src="Immagini/<%=prodotto.getNomeProdotto()%>.jpg"><br>
            <div class="products">
                <label><b>Nome:</b> <%=prodotto.getNomeProdotto()%></label><br>
                <label><b>Prezzo:</b> <%=prodotto.getPrezzo()%></label><br>
                <label><b>Produttore:</b> <%=prodotto.getProduttore()%></label><br>
                <label><b>Descrizione:</b> <%=prodotto.getDescrizione()%></label><br>
                <label><b>Quantità:</b> <%=prodotto.getQtDeposito()%></label><br>
                <button class="aggcarrello" type="submit" name="option" value="aggiungi" id="addToCart"><span>Aggiungi Al carrello  </span><i class="fas fa-shopping-cart" style="color: #393E46;"></i></button>
                <label id="quantita">Quantità:</label>
                <select name="quantita">
                    <%for(int j=1;j<=prodotto.getQtDeposito();j++){%>
                    <option value="<%=j%>"><%=j%></option><%}%>
                </select>
            </div>
        </form>
<%
            }else{
            i++;
            }
        }%>
    <%if(i == prodotti.size()){%>
        <div id="nontrovato">
            <h1>Nessun prodotto trovato</h1>
        </div>
    <%}
    }else{%>

    <div id="nontrovato">
        <h1>Nessun prodotto trovato</h1>
    </div>


<%  }%>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
