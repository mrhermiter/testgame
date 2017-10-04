<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
    </head>
    <body>
        <center>
            <h3>Login</h3>
            <div>${message}</div>
            <form method="POST" action="${pageContext.request.contextPath}/login">
                <div>
                    <label>User Name<br />
                        <input type="text" name="login" value= "" />
                    </label>
                 </div>
                <div>
                    <label>Password<br />
                        <input type="password" name="password" value= "" />
                    </label>
                 </div>
                 <div>
                    <input type="submit" value= "Submit" />
                </div>
            </form>
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