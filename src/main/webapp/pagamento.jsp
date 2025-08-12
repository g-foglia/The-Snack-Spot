<%@ page import="java.util.List" %>
<%@ page import="model.Pagamento" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>The Snack Spot</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%@ include file="header.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="stile.css">

</head>
<body>
<div class="sfondo">

    <div class="seleziona">
    <h1>Seleziona un metodo di pagamento o aggiungine uno</h1>
        <%
            List<Pagamento> pagamenti = (List<Pagamento>) request.getAttribute("pagamenti");
            if(!pagamenti.isEmpty()){
                for(Pagamento pagamento : pagamenti){%>

                    <form class="metodi"  method="post" action="crea-ordine">
                        <div>
                            <input type="hidden" name="nConto" id="nConto1" value="<%=pagamento.getnConto()%>">
                            <label><b>Numero conto: </b> <%=pagamento.getnConto()%></label><br>
                            <label><b>Intestatario: </b> <%=pagamento.getIntestatario()%></label><br>
                            <label><b>Circuito: </b> <%=pagamento.getCircuito()%></label><br>
                            <label><b>Scadenza: </b> <%=pagamento.getScadenza()%></label><br>
                            <label><b>CVV: </b> <%=pagamento.getCvv()%></label><br>
                            <button type="submit" name="pagamento" value="<%=pagamento.getnConto()%>"><span>Seleziona pagamento</span></button>
                        </div>
                    </form>
            <%  }
            }%>

        <form method="post" action="crea-ordine" name="pagamentoForm">
            <label for="nConto2">Numero conto:</label>
            <input type="text" name="nConto" id="nConto2" required><br>
            <label for="intestatario">Intestatario:</label> <br>
            <input type="text" id="intestatario" name="intestatario" required><br>
            <label for="circuito">Circuito:</label>
            <select name="circuito" id="circuito">
                <option value="Mastercard">Mastercard</option>
                <option value="Visa">Visa</option>
                <option value="American Express">American Express</option>
            </select><br>
            <label for="scadenza">Scadenza (MM/AAAA):</label>
            <input type="text" id="scadenza" name="scadenza" required><br>
            <label for="cvv">CVV:</label>
            <input type="number" id="cvv" name="cvv" required><br>
            <input id="button" type="submit"  onclick="return validazioneScadenza()"  value="Salva e paga">
        </form>
    </div>



    <script>
        function validazioneScadenza(){
            var scadenza = document.getElementById("scadenza").value;
            var cvv = document.getElementById("cvv").value;

            if(/(0?[1-9]|1[012])[\/\-]\d{4}$/.test(scadenza)){
                var mese = scadenza.substring(0,2);
                var anno = scadenza.substring(3);
                var date = new Date();

                if(mese < 1 || mese > 12 || mese < date.getMonth()){
                    alert("I numeri di mese consentiti vanno da 1 a 12 e il mese non deve essere precedente a quello corrente")
                }
                else if(anno < date.getFullYear()){
                    alert("L'anno non può essere antecedente a quello corrente")
                }
                else if(cvv.length != 3){
                    alert("La lunghezza del cvv deve essere esattamente 3")
                }
                else{
                    return true;
                }
            }
            else{
                alert("La data di scadenza è nel formato sbagliato");
            }
            return false;
        }
    </script>

<%@ include file="footer.jsp" %>
</div>
</body>
</html>
