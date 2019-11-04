package cn.cai.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.cai.entity.User;
import cn.cai.utils.DBUtils;

public class LoginDaoImpl implements ILoginDao

{
	@Override
	public boolean login(User user)
	{
		String sql = "select * from loginuser where userName=? and userPass =?";

		try
		{
			ResultSet rs = DBUtils.executeQuery(sql, new String[] { user.getUser(), user.getPassword() });
			if (rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			DBUtils.closeAll();
		}
	}
}
