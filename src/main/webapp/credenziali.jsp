<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Credenziali sbagliate</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="stile.css">
    <%@ include file="header.jsp" %>
</head>

<body>
<div class="sfondo">

  <div class="credenziali">
      <i class="fa-regular fa-face-frown"></i>
      <h2> Le credenziali inserite sono sbagliate!</h2>
      <a id="nosmile" href="login.jsp"><h3>Torna al login</h3></a>
  </div>

  <%@ include file="footer.jsp" %>
</div>
</body>

</html>
