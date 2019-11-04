package cn.cai.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cai.dao.BaseDaoImpl;
import cn.cai.dao.IBaseDao;
import cn.cai.dao.ILoginDao;
import cn.cai.dao.LoginDaoImpl;
import cn.cai.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{

	private static final long serialVersionUID = -2849105119772771338L;

	// 传入参数为 username，password
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String userName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("password");
		User user = new User(userName, password);
		ILoginDao login = new LoginDaoImpl();

		if (login.login(user))
		{
			request.getSession().setAttribute("userName", userName);
			request.getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("ERROR", "用户名/密码错误！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}
}
