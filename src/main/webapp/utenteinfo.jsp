<%@ page import="model.Utente"%>
<%@ page import="model.Pagamento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Utente Info</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
  <div class="sfondo">
  <%@ include file="header.jsp" %>

    <% Utente utente= (Utente)request.getSession().getAttribute("utente");
       List<Pagamento> pagamenti = (List<Pagamento>) request.getAttribute("pagamenti");
    %>

    <div class="divisione">
    <div class="blu">
       <div class="pagaccount">
            <h1>Il mio account</h1><br>
            <h3><b>Nome:</b> <%=utente.getNome()%></h3><br>
            <h3><b>Cognome:</b> <%=utente.getCognome()%></h3><br>
            <h3><b>Email:</b> <%=utente.getEmailCliente()%></h3><br>
            <h3><b>Via:</b> <%=utente.getVia()%></h3><br>
            <h3><b>Numero civico:</b> <%=utente.getNc()%></h3><br>
            <h3><b>Città:</b> <%=utente.getCitta()%></h3><br>
            <h3><b>Cap:</b> <%=utente.getCap()%></h3><br>
            <h3><b>Cellulare:</b> <%=utente.getCellulare()%></h3><br>
       </div>
       <div class="logout">
            <h1> Vuoi uscire dall'account?</h1>
            <a id="esci" href="logout"><h3>Premimi</h3></a>
       </div>

    </div>
     <div class="rossi">
       <div class="pagamento">
           <h1>Vuoi vedere i tuoi metodi di pagamento?</h1>
           <a id="mostrapagamenti" href="return-pagamenti"><h3>Premimi</h3></a>
       </div>
       <div class="ordini">
           <h1>Vuoi vedere i tuoi ordini?</h1>
           <a id="mostraordini" href="mostra-ordini"><h3>Premimi</h3></a>
       </div>


      <%if(utente.getTipo()){%>
          <div class="aggiunta">
            <h1>Vuoi aggiungere un prodotto?</h1>
            <a id="aggiungiprodotto" href="inserimentoProdotto.jsp"><h3>Premimi</h3></a>
          </div>
          <div class="rimozione">
            <h1>Vuoi modificare un prodotto?</h1>
            <a id="rimuoviprodotto" href="selezionaprodotto.jsp"><h3>Premimi</h3></a>
          </div>
      <%}%>
    </div>
    </div>


  <%@ include file="footer.jsp" %>
  </div>
</body>

</html>
