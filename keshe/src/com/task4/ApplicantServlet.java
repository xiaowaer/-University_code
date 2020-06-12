//用户删除Servlet
package com.task4;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.task2.ApplicantDAO;
import com.task3.ResumeDAO;

/**
 * Servlet implementation class ApplicantServlet
 */
@WebServlet("/ApplicantServlet")
public class ApplicantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取请求类型
		String type = request.getParameter("type");
		if(type.equals("delete")){
			int id = Integer.valueOf(request.getParameter("id6"));
			String trueid="1"+String.valueOf(id); 
			ApplicantDAO dao = new ApplicantDAO();
			ResumeDAO dao1 = new ResumeDAO();
	
	
			dao1.delete(Integer.valueOf(trueid));
			System.out.println("删除的用户的个人信息成功");
			

			dao.delete(String.valueOf(id));
			System.out.println("删除的用户的id是："+id);
			///////////////////////////////////////////////////////////////
			response.sendRedirect("All_ResumeServlet");
		   ///////////////////////////////////////////////////////////////
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
