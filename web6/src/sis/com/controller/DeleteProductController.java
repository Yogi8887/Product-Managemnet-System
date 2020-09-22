package sis.com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sis.com.util.db.SisDbConnectionUtil;

/**
 * Servlet implementation class DeleteProductController
 */
public class DeleteProductController extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String pidStr  =request.getParameter("pid");
		 long productID  = Long.parseLong(pidStr);
		 
		 
		 boolean isDeleted=false;
			Connection con  = null;
			PreparedStatement pstmt = null;
			try{
				con  = SisDbConnectionUtil.getConnection();
				
				String sql = "delete from product  where id =?";
				pstmt  = con.prepareStatement(sql);
				pstmt.setLong(1, productID);
				if(pstmt.executeUpdate()==1){
					isDeleted=true;
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
			
			

			request.setAttribute("isDeleted", isDeleted);
			request.getRequestDispatcher("delete_product_msg.jsp").forward(request, response);		

		 
		 
	}

}
