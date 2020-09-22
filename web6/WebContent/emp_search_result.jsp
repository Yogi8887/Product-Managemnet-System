<%@page import="sis.com.bo.Employee"%>
<%@page import="sis.com.bo.Product"%>
<%@page import="java.util.List"%>
<%@include file="sis_header.jsp"%>

<%
String lastSearch="";
Object obj = request.getAttribute("last_search");
if(obj!=null){
	lastSearch = (String)obj;
}


%>
<fieldset><legend>Search employee form</legend>
<form action="searchemp" method="get">
Enter emp Name :
<input type="text" name="search_name_text" autofocus="autofocus"  value="<%=lastSearch%>">
<input type="submit" value="Search Employee">
</form>
</fieldset>

<%
List<Employee> empSearchList  = (List<Employee>)request.getAttribute("foundList");

%>

<table border="1" width="100%">
<tr>
<th>id</th>
<th>name</th>
<th>job</th>
<th>salary</th>
<th>dept_id</th>
</tr>
<%for(Employee emp : empSearchList){ %>
<tr>
<td><%=emp.getId()%></td>
<td><%=emp.getName()%></td>
<td><%=emp.getJob()%></td>
<td><%=emp.getSalary()%></td>
<td><%=emp.getDeptId()%></td>
</tr>

<%}%>

</table>


<%@include file="sis_footer.jsp"%>