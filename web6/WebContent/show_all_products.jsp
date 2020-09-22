<%@page import="sis.com.bo.Product"%>
<%@page import="java.util.List"%>
<%@include file="sis_header.jsp"%>
<%
List<Product> productList  = (List<Product>)request.getAttribute("allproduct");

%>

<h1>show all products</h1>
<table border="1" width="100%">
<tr>
<th>id</th>
<th>name</th>
<th>price</th>
<th>cat_id</th>
<th>cat_name</th>
<th>Action</th>
</tr>
<%for(Product product : productList){ %>
<tr>
<td><%=product.getId() %></td>
<td><%=product.getName() %></td>
<td><%=product.getPrice() %></td>
<td><%=product.getCategory().getId() %></td>
<td><%=product.getCategory().getName() %></td>
<td>
 
 <a href="updateproduct?pid=<%=product.getId()%>">Update</a> 
 <a href="deleteproduct?pid=<%=product.getId()%>">Delete</a> 
 
</td>
</tr>

<%}%>

</table>


<%@include file="sis_footer.jsp"%>