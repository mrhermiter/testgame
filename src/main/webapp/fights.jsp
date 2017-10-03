<%@page import="net.developer.webappgame.model.User"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Fight</title>
   </head>
   <body>
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
   </body>
</html>