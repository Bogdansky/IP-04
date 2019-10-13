<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.*"%>
<%!
    String getHello(){
        int hour = LocalDateTime.now().getHour();
        if (hour > 17){
            return "Good evening!";
        }
        if (hour > 11){
            return "Good afternoon!";
        }
        if (hour > 5){
            return "Good morning!";
        }
        return "Good night!";
    }
%>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
    <p><%= getHello() %></p>
    <table style="border:1px solid">
        <thead>
            <th>Дата</th>
            <th>Номер дня недели</th>
        </thead>
        <tbody>
            <%
                LocalDateTime now = LocalDateTime.now();
                for(int i = 0; i < 7; i++){
                    LocalDateTime date = now.plusDays(i);
                    out.println("<tr><td>"+date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"</td><td>"+date.getDayOfWeek().getValue()+"</td></tr>");
                }
            %>
        </tbody>
    </table>
    <form action="index.jsp?repeat=true">
        <button>Press</button>
    </form>
    <% if (request.getParameter("repeat")=="true"){ %>
        <%@include file="night.jsp" %>
    <%}%>
</body>
</html>