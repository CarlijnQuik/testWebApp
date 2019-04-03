<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<body>
<div style="text-align: center;">
    <h1>
        Available Brands
    </h1>
        <%
List result= (List) request.getAttribute("brands");
String number = (String) request.getAttribute("count");
Iterator it = result.iterator();
out.println("Your random number is:" + number);
out.println("<br>We have <br><br>");
while(it.hasNext()){
out.println(it.next()+"<br>");
}
%>
</body>
</html>