<%@page import="sis.com.bo.Category"%>
<%@page import="java.util.List"%>
<%@include file="sis_header.jsp"%>

<%
List<Category>catList  = (List<Category>)request.getAttribute("allcatlist");

%>

<fieldset><legend>Add Product form</legend>
<form action="addproduct" method="post">

Name :<input type="text" name="p_name" autofocus="autofocus"><br>
price :<input type="text" name="p_price"><br>
select category :
<!-- <input type="radio" name="p_cat_id" value="101">c1
<input type="radio" name="p_cat_id" value="102">c2
<input type="radio" name="p_cat_id" value="103">c3 -->
<!-- dynamic category creation -->
<%for(Category c:catList){ %>
<input type="radio" name="p_cat_id" value="<%=c.getId()%>"> <%=c.getName() %>
<%}%>

<br>
<input type="submit" value="add product">

</form>

</fieldset>




<%@include file="sis_footer.jsp"%>