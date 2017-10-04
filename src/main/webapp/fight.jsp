<%@page import="net.developer.webappgame.service.FightService"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Fight</title>
    </head>
    <body>
        <div align="center">
            <h3>Fight</h3>
            <%FightService fightService = (FightService) session.getAttribute("fight");
            if(fightService==null){ %>
                <div>Wait....</div>
                <script type="text/javascript">
                setTimeout("window.location.reload()",1000)
                </script>
            <%}else{if("start".equals(request.getParameter("action"))){%>
                <style>
                    .inline-block{
                        display:inline-block;
                        margin:10px;
                    }
                    .healthBlock{
                        width:100px;
                        height:10px;
                        border:1px solid black;
                        text-align:right;
                    }
                    #healthEnemy{
                        width:<%=fightService.getEnemyUser().getFightHealth()%>px;
                        height:10px;
                        background-color:red;
                    }
                    #healthSelf{
                        width:<%=fightService.getSelfUser().getFightHealth()%>px;
                        height:10px;
                        background-color:green;
                    }
                </style>
                <%if(fightService.isFinish()){%>
                    <div>
                    <form method="POST" action="">
                    <input type="hidden" name="send" value="exit"/>
                    <input type="submit" value="Exit"/>
                    </form>
                    </div>
                <%}%>
                <div>
                    <div id="selfBlock" class="inline-block">
                    <div><%=fightService.getSelfUser().getLogin()%></div>
                    <div><div class="healthBlock"><div id="healthSelf"></div></div></div>
                    <div>Damage: <%=fightService.getEnemyUser().getDamage()%></div>
                </div>
                    <%if(!fightService.isFinish()){%>
                        <form method="POST" action=""  class="inline-block">
                            <input type="hidden" name="send" value="hit"/>
                            <input type="submit" value="Hit"/>
                        </form>
                    <%}%>
                    <div id="enemyBlock" class="inline-block">
                        <div><%=fightService.getEnemyUser().getLogin()%></div>
                        <div><div class="healthBlock"><div id="healthEnemy"></div></div></div>
                        <div>Damage: <%=fightService.getEnemyUser().getDamage()%></div>
                    </div>
                </div>
                <div id="log">
                <%if(request.getAttribute("log")!=null){
                List<String> log=(List<String>)request.getAttribute("log");
                for (String item:log){%>
                    <%=item%><br />
                <%}}%>
                </div>
                <%}else{%>
                <div><%=fightService.getSelfUser().getLogin()%> VS <%=fightService.getEnemyUser().getLogin()%></div>
                <div>The fight will begin in <span id="timer">60</span> sec.</div>
                <script type="text/javascript">
                    var timer=60;

                    var interval=setInterval(function timerTick(){
                                            if(timer>0){
                                                timer--;
                                                var timerBlock = document.getElementById('timer');
                                                timerBlock.innerHTML=timer;
                                            }
                                            else{
                                                clearInterval(interval);
                                                window.location+="action=start";
                                            }
                                        },1000);
                </script>
            <%}}%>
        </div>
        <style>
        .page-info{
        position:fixed;
        bottom:0;
        }
        </style>
        <div class="page-info">${pageContext.request.getAttribute("page")}</div>
    </body>
</html>