package cn.cai.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.cai.dao.BaseDaoImpl;
import cn.cai.dao.IBaseDao;
import cn.cai.entity.ChangeData;
import cn.cai.utils.JsonUtil;

/**
 * Servlet implementation class SaveDataServlet
 */
@WebServlet("/SaveDataServlet")
public class SaveDataServlet extends HttpServlet
{

	private static final long serialVersionUID = -4756180178388433604L;

	// 传入参数为changedData
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ChangeData data = new ChangeData();
		data = JsonUtil.getJavaBean(request, ChangeData.class);

		IBaseDao baseDao = new BaseDaoImpl();
		String sql = "update " + data.getTableName() + " set " + data.getChangeValueHeader() + " = ? where " + data.getPrimaryKeyName() + " = ?";

		if (!baseDao.update(sql, new String[] { data.getChangeValue(), data.getPrimaryKeyValue() }))
		{
			response.getWriter().write("修改失败");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
