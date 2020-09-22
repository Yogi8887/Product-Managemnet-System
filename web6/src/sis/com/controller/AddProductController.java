package sis.com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sis.com.bo.Category;
import sis.com.bo.Product;
import sis.com.util.db.SisDbConnectionUtil;

/**
 * Servlet implementation class AddProductController
 */
public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//show form 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Category> list = new ArrayList<Category>();
		//fill using jdbc
		Connection con  = null;
		Statement stmt = null;
		ResultSet rs  = null;
		try{
			con  = SisDbConnectionUtil.getConnection();
			stmt  = con.createStatement();
			String sql ="select * from product_cat";
			rs  = stmt.executeQuery(sql);
			while(rs.next()){
				 
				long id  = rs.getLong("id");
				String name = rs.getString("name");
				 
				Category category = new Category();
				category.setId(id);
				category.setName(name);
				list.add(category);
			}//end while
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				SisDbConnectionUtil.closeConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("allcatlist", list);
		request.getRequestDispatcher("add_product_form.jsp").forward(request, response);		

	}

//save form data

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get param
		//parse
		//insert code
		String name = request.getParameter("p_name");
		String priceStr =request.getParameter("p_price");
		String catIdStr =request.getParameter("p_cat_id");
		float price = Float.parseFloat(priceStr);
		long catId  = Long.parseLong(catIdStr);
		
		
		
		boolean isAdded=false;
		Connection con  = null;
		PreparedStatement pstmt = null;
		try{
			con  = SisDbConnectionUtil.getConnection();
			
			String sql ="insert into product(id,name,price,cat_id)values"
					+"(product_seq.nextval,?,?,?)";
			pstmt  = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setFloat(2, price);
			pstmt.setLong(3, catId);
			if(pstmt.executeUpdate()==1){
				isAdded=true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				SisDbConnectionUtil.closeConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

		request.setAttribute("isAdd", isAdded);
		request.getRequestDispatcher("add_product_success.jsp").forward(request, response);		

		
		
	}//end DoPost

	
}
