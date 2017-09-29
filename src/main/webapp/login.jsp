<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Login</title>
   </head>
   <body>
      <h3>Login please</h3>

      <form method="POST" action="${pageContext.request.contextPath}/login">

       <label>User Name
              <input type="text" name="login" value= "${user.login}" />
       </label>

       <label>Password
             <input type="password" name="password" value= "${user.password}" />
       </label>

       <input type="submit" value= "Submit" />

      </form>
   </body>
</html>