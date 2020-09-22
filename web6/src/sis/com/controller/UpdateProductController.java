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
 * Servlet implementation class UpdateProductController
 */
public class UpdateProductController extends HttpServlet {
	 
	//generate filled form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pidStr  = request.getParameter("pid");
		Long productId  = Long.parseLong(pidStr);
		
		Product product = null;
		List<Category> list = new ArrayList<Category>();
		
		Connection con  = null;
		Statement stmt = null;
		ResultSet rs  = null;
		try{
			con  = SisDbConnectionUtil.getConnection();
			stmt  = con.createStatement();
					String sql ="select p.id pid,p.name pname,p.price pprice,c.id cid,c.name cname from product p ,product_cat c where p.cat_id=c.id and p.id="+productId;
			rs  = stmt.executeQuery(sql);
			if(rs.next()){
				long id  =productId; //;
				String name = rs.getString("pname");
				float price = rs.getFloat("pprice");
				long catId  = rs.getLong("cid");
				String cname = rs.getString("cname");
				
				product  = new Product();
				product.setId(id);
				product.setName(name);
				product.setPrice(price);
				Category category = new Category();
				category.setId(catId);
				category.setName(cname);
				product.setCategory(category);
				
			}//end if
			
			//create list of category
			rs= null;
			String sql2 ="select * from product_cat";
			rs  = stmt.executeQuery(sql2);
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
		
//send it to form
		request.setAttribute("catList",list );
		request.setAttribute("oldProduct",product );
		
		request.getRequestDispatcher("update_products.jsp").forward(request, response);		

	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		//send msg
		

		//get update info
		String pidStr = request.getParameter("p_id");
		String name = request.getParameter("p_name");
		String priceStr =request.getParameter("p_price");
		String catIdStr =request.getParameter("p_cat_id");
		
		long productId  = Long.parseLong(pidStr);
		float price = Float.parseFloat(priceStr);
		long catId  = Long.parseLong(catIdStr);
		
		//update into db
		
		
		boolean isUpdated=false;
		Connection con  = null;
		PreparedStatement pstmt = null;
		try{
			con  = SisDbConnectionUtil.getConnection();
			
			String sql ="update product  set name=? ,price=?,cat_id=? "
					+ " where id =?";
			pstmt  = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setFloat(2, price);
			pstmt.setLong(3, catId);
			pstmt.setLong(4,productId);
			
			if(pstmt.executeUpdate()==1){
				isUpdated=true;
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
		
		

		request.setAttribute("isUpdate", isUpdated);
		request.getRequestDispatcher("update_product_msg.jsp").forward(request, response);		

		
		
	}

}
