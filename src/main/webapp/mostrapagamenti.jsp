<%@ page import="model.Pagamento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Utente Info</title>
</head>

<body>
<div class="sfondo">
  <%@ include file="header.jsp" %>
  <div class="salvati">
  <%
    List<Pagamento> pagamenti = (List<Pagamento>) request.getAttribute("pagamenti");
  %>
    <%if(!pagamenti.isEmpty()){%>
    <h1>Metodi di pagamento salvati</h1>
    <%for(Pagamento pagamento : pagamenti){%>
    <form method="post" action="rimuovi-pagamento">
      <input type="hidden" name="nConto" value="<%=pagamento.getnConto()%>">
      <label><b>Numero conto: </b> <%=pagamento.getnConto()%></label><br>
      <label><b>Intestatario: </b> <%=pagamento.getIntestatario()%></label><br>
      <label><b>Circuito: </b> <%=pagamento.getCircuito()%></label><br>
      <label><b>Scadenza: </b> <%=pagamento.getScadenza()%></label><br>
      <label><b>CVV: </b> <%=pagamento.getCvv()%></label><br>
      <button id="cancellametodo" type="submit">Cancella metodo</button>
    </form>

    <%}%>
  </div>

    <%}else{%>


  <div class="noordini">
    <h1>Non hai metodi di pagamento salvati</h1>
  </div>


    <%}%>


</div>
<%@ include file="footer.jsp" %>
</body>
</html>
