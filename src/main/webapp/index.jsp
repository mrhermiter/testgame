<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Home</title>
   </head>
   <body>
   <center>
      <h3>Home</h3>
      <a href="${pageContext.request.contextPath}/fights">Fights</a><br />
      <a href="${pageContext.request.contextPath}/logout">Exit</a>
   </center>
   <div>${pageContext.request.getAttribute("page")}</div>
   </body>
</html>