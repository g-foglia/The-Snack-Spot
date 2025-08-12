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
<div class="carrello">
<h1> Il tuo carrello </h1>
<%
    List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
    Utente utente = (Utente) session.getAttribute("utente");
    if(prodotti != null){
        if(!prodotti.isEmpty()){
    List<Boolean> flag = (List<Boolean>) request.getAttribute("flag");
    List<Integer> quantita = (List<Integer>) request.getAttribute("quantita");
    double totale = (Double) request.getAttribute("totale");
    int i = -1;
    for(Prodotto prodotto : prodotti){
        i++;%>

<form class="contenuto" method="post" action="aggiunta-carrello">
    <div class="imcarrello">
        <img src="Immagini/<%=prodotto.getNomeProdotto()%>.jpg"><br>
    </div>
    <div class="prcarrello">
        <input type="hidden" name="id" value="<%=prodotto.getIdProdotto()%>">
        <label><b>Nome: </b> <%=prodotto.getNomeProdotto()%></label><br>
        <label><b>Quantità selezionata: </b> <%=quantita.get(i)%></label><br>
        <%if(flag.get(i)){%>
            <label><b>La quantità selezionata non era disponibile, ecco la quantità massima disponibile</b></label><br>
        <%}%>
        <label><b>Prezzo singolo prodotto:&#8364;</b> <%=prodotto.getPrezzo()%></label>
        <label><b>Prezzo cumulativo: &#8364;</b> <%=prodotto.getPrezzo() * quantita.get(i)%></label><br>
        <label><b>Produttore: </b> <%=prodotto.getProduttore()%></label><br>
        <label><b>Descrizione: </b> <%=prodotto.getDescrizione()%></label><br>
        <input type="hidden" name="pagina" value="carrello">
        <label id="modquantita">Modifica quantità:</label>
        <select name="quantita">
            <%for(int j=0;j<=prodotto.getQtDeposito();j++){%>
            <option value="<%=j%>"><%=j%></option><%}%>
        </select>
        <input  id="aggiornaquantità" type="submit" value="Aggiorna quantità">
    </div>
</form>
<%}%>
<div class="totale">
<label><b>Totale: &#8364;</b><%=totale%></label>
    <%if(utente != null){%>
    <form method="post" action="prepara-ordine">
    <input type="submit" value="Vai al pagamento">
    </form>
    <%}else{%>
    <form method="post" action="login.jsp">
        <input id="vaialpagamento" type="submit" value="Vai al pagamento">
    </form>
    <%}%>
</div>

<%}else{%>
    <h1>Carrello vuoto</h1>
    <%}
    }else{%>

        <h1>Carrello vuoto</h1>


<%}%>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
