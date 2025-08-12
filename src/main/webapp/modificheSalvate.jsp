<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>The Snack Spot</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" type="text/css" href="stile.css">

</head>
<body>
<%@ include file="header.jsp" %>

<div class="ordineok">
  <i class="fa-solid fa-envelope-circle-check"></i>
  <h3> Le modifiche sono state salvate </h3>
  <a href="index.jsp">
    <h3> Torna alla home </h3>
  </a>
</div>
<%@ include file="footer.jsp" %>

</body>
</html>
