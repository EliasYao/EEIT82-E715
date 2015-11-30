<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
model.CustomerService service1 = new model.CustomerService();
model.ProductService service2 = new model.ProductService();
%>
<h3><%=service1.login("Alex", "AAA")%></h3>
<h3><%=service2.select(null)%></h3>

<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.*" %>
<%
javax.naming.Context ctx = new javax.naming.InitialContext();
DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
Connection conn = ds.getConnection();
Statement stmt = conn.createStatement();
ResultSet rset = stmt.executeQuery("select * from dept");
while(rset.next()) {
	String col1 = rset.getString(1);
	String col2 = rset.getString(2);
%>
	<h3><%=col1%>, <%=col2%></h3>	
<%
}
rset.close();
stmt.close();
conn.close();
%>
</body>
</html>
