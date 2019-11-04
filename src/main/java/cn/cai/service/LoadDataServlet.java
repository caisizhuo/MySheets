package cn.cai.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.cai.dao.BaseDaoImpl;
import cn.cai.dao.IBaseDao;

/**
 * Servlet implementation class LoadDataServlet
 */
@WebServlet("/LoadDataServlet")
public class LoadDataServlet extends HttpServlet
{

	private static final long serialVersionUID = 2388404735287730832L;

	// 传入参数为table
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		String table = request.getParameter("table");
		Map<String, List<String>> tableDetail = (Map<String, List<String>>) request.getSession().getAttribute("tableDetail");
		int headersSize = tableDetail.get(table).size();

		String sql = "select * from " + table;
		List<String[]> list = new ArrayList<String[]>();
		IBaseDao baseDao = new BaseDaoImpl();
		try
		{
			ResultSet rs = baseDao.query(sql, null);
			while (rs.next())
			{
				String[] string = new String[headersSize];
				for (int i = 0; i < string.length; i++)
				{
					string[i] = rs.getString(i + 1);
				}
				list.add(string);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		//System.out.println(JSON.toJSONString(list));
		response.getWriter().print(JSON.toJSONString(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}

}
