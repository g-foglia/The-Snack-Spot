<%@ page import="model.Pagamento" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Ordine" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Utente Info</title>
</head>

<body>
<div class="sfondo">
  <%@ include file="header.jsp" %>

  <div class="orpassati">
  <%
    List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
  %>
  <%if(!ordini.isEmpty()){
  List<List<Prodotto>> prodotti= (List<List<Prodotto>>) request.getAttribute("prodotti");
  List<List<Integer>> quantita=(List<List<Integer>>) request.getAttribute("quantita");
  int i=-1;
  int j;
  %>

  <%for(Ordine ordine : ordini){
    i++; j=-1;
  %>

    <h1>Ordine passato</h1>
    <form>
      <label><b>Numero ordine: </b> <%=ordine.getnOrdine()%></label><br>
      <label><b>Stato: </b> <%=ordine.getStato()%></label><br>
      <label><b>Totale:&#8364; </b> <%=ordine.getTotale()%></label><br>
      <label><b>Data ordine: </b> <%=new SimpleDateFormat("dd/MM/yy").format(ordine.getDataOrdine().getTime())%></label><br>
      <label><b>Data consegna: </b> <%=new SimpleDateFormat("dd/MM/yy").format(ordine.getDataConsegna().getTime())%></label><br>
      <label><b>Numero conto: </b> <%=ordine.getnConto()%></label><br>
    </form>

  <%for(Prodotto prodotto: prodotti.get(i)){
    j++;%>
  <img src="Immagini/<%=prodotto.getNomeProdotto()%>.jpg"><br>
  <label><b>Nome: </b> <%=prodotto.getNomeProdotto()%></label><br>
  <label><b>Quantità selezionata: </b> <%=quantita.get(i).get(j)%></label><br>
  <label><b>Prezzo singolo prodotto:&#8364;</b> <%=prodotto.getPrezzo()%></label><br>
  <label><b>Produttore: </b> <%=prodotto.getProduttore()%></label><br>
  <label><b>Descrizione: </b> <%=prodotto.getDescrizione()%></label><br>

  <%}}%>
  </div>
  <%}else{%>
  <div class="noordini">
  <h1>Non hai ordini passati</h1>
  <%}%>
  </div>


</div>
<%@ include file="footer.jsp" %>
</body>
</html>
