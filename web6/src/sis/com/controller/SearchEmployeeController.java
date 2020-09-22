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
import sis.com.bo.Employee;
import sis.com.bo.Product;
import sis.com.util.db.SisDbConnectionUtil;

/**
 * Servlet implementation class SearchEmployeeController
 */
public class SearchEmployeeController extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get search string 
		String searchNameText  = request.getParameter("search_name_text");
		List<Employee>list  = new ArrayList<Employee>();
		
		//fill list of emp
		Connection con  = null;
		Statement stmt = null;
		ResultSet rs  = null;
		try{
			con  = SisDbConnectionUtil.getConnection();
			stmt  = con.createStatement();
			String sql ="select * from emp where lower(ename) like lower('%"+searchNameText+"%')";
			rs  = stmt.executeQuery(sql);
			while(rs.next()){
				long empId  = rs.getLong("empno"); 
				String name = rs.getString("ename");
				float salary = rs.getFloat("sal");
				long deptId  = rs.getLong("deptno");
				String job = rs.getString("job");
				
				Employee emp = new Employee();
				emp.setId(empId);
				emp.setName(name);
				emp.setSalary(salary);
				emp.setDeptId(deptId);
				emp.setJob(job);
				list.add(emp);
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
		
		if(list.isEmpty()==true){
			//String newUrl ="https://www.google.com/search?q=java";
			String newUrl ="https://www.google.com/search?q="+searchNameText;
			//redirect google search
			response.sendRedirect(newUrl);
		}else{
			//our search page
			request.setAttribute("foundList",list );
			request.setAttribute("last_search",searchNameText );
			request.getRequestDispatcher("emp_search_result.jsp").forward(request, response);		
		}
		//send to result page

		//select * from emp where lower(ename) like lower('%e%')
		
	}

}
