package cn.cai.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import cn.cai.dao.BaseDaoImpl;
import cn.cai.dao.IBaseDao;

/**
 * Application Lifecycle Listener implementation class TableDetailListener
 *
 */
@WebListener
public class TableDetailListener implements HttpSessionAttributeListener
{

	@Override
	public void attributeAdded(HttpSessionBindingEvent event)
	{
		HttpSession session = event.getSession();
		if (null == session.getAttribute("loginCount"))
		{
			session.setAttribute("loginCount", "已经登录");

			Map<String, List<String>> tableDetail = new HashMap<String, List<String>>();
			// System.out.println(session.getAttribute("userName"));
			String databaseName = session.getServletContext().getInitParameter("databaseName");
			IBaseDao baseDao = new BaseDaoImpl();
			List<String> list = baseDao.getTables(databaseName);
			for (String tableName : list)
			{
				List<String> headers = baseDao.getHeaders(databaseName, tableName);
				tableDetail.put(tableName, headers);
			}
			// for (String table : tableDetail.keySet())
			// {
			// System.out.println(table + ":");
			// for (String string : tableDetail.get(table))
			// {
			// System.out.println(" " + string);
			// }
			// System.out.println("**************************");
			// }
			// System.out.println("完成");
			session.setAttribute("tableDetail", tableDetail);
		}

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event)
	{

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event)
	{

	}

}
