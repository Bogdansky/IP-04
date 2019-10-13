<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.*"%>
<%!
	String greeting = getHello();
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
	<meta charset="ascii">
</head>
<body>
    <p><%= greeting %></p>
    <table>
        <thead style="border:1px solid">
            <th>Date</th>
            <th>Day of week number</th>
        </thead>
        <tbody style="border:1px solid">
            <%
                LocalDateTime now = LocalDateTime.now();
                for(int i = 0; i < 7; i++){
                    LocalDateTime date = now.plusDays(i);
                    out.println("<tr><td>"+date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"</td><td style='text-align:center'>"+date.getDayOfWeek().getValue()+"</td></tr>");
                }
            %>
        </tbody>
    </table>
    <form>
		<input type="hidden" name="repeat" value="1" />
        <button>Press</button>
    </form>
    <% if (request.getParameter("repeat")!= null){ %>
		<%if(greeting.equals("Good evening!")){%>
			<%@include file="evening.jsp" %>
		<%}%>
		<%if(greeting.equals("Good afternoon!")){%>
			<%@include file="afternoon.jsp" %>
		<%}%>
		<%if(greeting.equals("Good morning!")){%>
			<%@include file="morning.jsp" %>
		<%}%>
		<%if(greeting.equals("Good night!")){%>
			<%@include file="night.jsp" %>
		<%}%>
    <%}%>
</body>
</html>