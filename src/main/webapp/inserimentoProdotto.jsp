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
<form action="aggiunta-prodotto" method="post" name="inserimentoForm" enctype="multipart/form-data">
    <label for="idProdotto">Id Prodotto:</label>
    <input type="text" id="idProdotto" name="idProdotto" required><br>
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" required><br>
    <label for="prezzo">Prezzo:</label>
    <input type="text" id="prezzo" name="prezzo" required><br>
    <label for="produttore">Produttore:</label>
    <input type="text" id="produttore" name="produttore" required><br>
    <label for="descrizione">Descrizione (massimo 50 caratteri):</label>
    <input type="text" id="descrizione" name="descrizione" maxlength="50" required><br>
    <label for="quantità">Quantita' in deposito:</label>
    <input type="number" id="quantità" name="quantità" min="0" required><br><br>

    <label>Categoria:</label>
    <select name="categoria" id="categoria">
        <option value="Dolce">Dolce</option>
        <option value="Salato">Salato</option>
        <option value="Bibita">Bibita</option>
    </select>

    <label>Allergeni:</label><br>
    <input type="checkbox" id="Glutine" name="Glutine" value="Glutine">
    <label for="Glutine"> Glutine</label><br>
    <input type="checkbox" id="Frutta a guscio" name="Frutta a guscio" value="Frutta a guscio">
    <label for="Frutta a guscio"> Frutta a guscio</label><br>
    <input type="checkbox" id="Lattosio" name="Lattosio" value="Lattosio">
    <label for="Lattosio"> Lattosio</label><br>
    <input type="checkbox" id="Uovo" name="Uovo" value="Uovo">
    <label for="Uovo"> Uovo</label><br>
    <label for="file">Foto prodotto:</label>
    <input type="file" id="file" name="file"/><br>

    <input id="badmin" type="submit" value="Aggiungi">
</form>
</div>
<%@ include file="footer.jsp" %>
</div>
</body>
</html>
