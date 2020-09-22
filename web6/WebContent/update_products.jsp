<%@page import="sis.com.bo.Product"%>
<%@page import="sis.com.bo.Category"%>
<%@page import="java.util.List"%>
<%@include file="sis_header.jsp"%>

<%
List<Category>catList  = (List<Category>)request.getAttribute("catList");
Product oldProduct  = (Product)request.getAttribute("oldProduct");

%>

<fieldset><legend>Update Product form</legend>
<form action="updateproduct" method="post">

ID :<input type="text" name="p_id"  value="<%=oldProduct.getId()%>" readonly="readonly" style="background-color: gray;"><br>
Name :<input type="text" name="p_name" autofocus="autofocus"  value="<%=oldProduct.getName()%>"><br>
price :<input type="text" name="p_price" value="<%=oldProduct.getPrice()%>"><br>
select category :
<!-- dynamic category creation -->
<%for(Category c:catList){ %>
   <%if(oldProduct.getCategory().getId()==c.getId()){%>
    <input type="radio" name="p_cat_id" value="<%=c.getId()%>" checked="checked"> <%=c.getName() %>
 <%}else{ %>
	<input type="radio" name="p_cat_id" value="<%=c.getId()%>"> <%=c.getName() %>
 <%} //end else %>
<%}//end for%>

<br>
<input type="submit" value="Update product">

</form>

</fieldset>




<%@include file="sis_footer.jsp"%>