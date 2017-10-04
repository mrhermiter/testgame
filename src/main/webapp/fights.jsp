<%@page import="net.developer.webappgame.model.User"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Fight</title>
   </head>
   <body>
      <center>
        <h3>Fights</h3>
        <%User user = (User) session.getAttribute("user"); %>
        <div>
            <div><b><%=user.getLogin() %></b></div>
            <div>Rating: <%=user.getRating() %></div>
            <div>Health: <%=user.getHealth() %></div>
            <div>Damage: <%=user.getDamage() %></div>
            </div>
            <div>
            <form action="${pageContext.request.contextPath}/fight" method="GET">
            <input type="submit" value="Start Fight" />
            </form>
        </div>
      </center>
      <style>
        .page-info{
            position:fixed;
            bottom:0;
        }
      </style>
      <div class="page-info">${pageContext.request.getAttribute("page")}</div>
   </body>
</html>