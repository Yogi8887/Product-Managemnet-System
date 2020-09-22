package sis.com.controller;

import java.io.IOException;
import java.sql.Connection;
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

public class ShowAllProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Product> list = new ArrayList<Product>();
		//fill using jdbc
		Connection con  = null;
		Statement stmt = null;
		ResultSet rs  = null;
		try{
			con  = SisDbConnectionUtil.getConnection();
			stmt  = con.createStatement();
			//join we need product and its cat
			//String sql ="select * from product p ,product_cat c where p.cat_id=c.id";
			//String sql ="select * from product ";
			String sql ="select p.id pid,p.name pname,p.price pprice,c.id cid,c.name cname from product p ,product_cat c where p.cat_id=c.id order by p.id desc";
			rs  = stmt.executeQuery(sql);
			while(rs.next()){
				//without join ok
				/*long id  = rs.getLong("id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				Product p  = new Product();
				long catId  = rs.getLong("cat_id");*/
				
				//ok with  join
				//long id  = rs.getLong("pid"); OR
				long id  = rs.getLong(1);
				String name = rs.getString("pname");
				float price = rs.getFloat("pprice");
				Product p  = new Product();
				long catId  = rs.getLong("cid");
				String cname = rs.getString("cname");
				
				p.setId(id);
				p.setName(name);
				p.setPrice(price);
				Category category = new Category();
				category.setId(catId);
				category.setName(cname);
				p.setCategory(category);
				
				//cat
				//add into list
				list.add(p);
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
		
 request.setAttribute("allproduct", list);		
 request.getRequestDispatcher("show_all_products.jsp").forward(request, response);		
		
		

	}

}
