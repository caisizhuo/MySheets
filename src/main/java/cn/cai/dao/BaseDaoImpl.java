package cn.cai.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.cai.utils.DBUtils;

public class BaseDaoImpl implements IBaseDao
{

	@Override
	public boolean insert(String sql, Object[] params)
	{
		return DBUtils.executeUpdate(sql, params);
	}

	@Override
	public boolean delete(String sql, Object[] params)
	{
		return DBUtils.executeUpdate(sql, params);
	}

	@Override
	public boolean update(String sql, Object[] params)
	{
		return DBUtils.executeUpdate(sql, params);
	}

	@Override
	public ResultSet query(String sql, Object[] params)
	{
		return DBUtils.executeQuery(sql, params);
	}

	@Override
	public List<String> getTables(String databaseName)
	{
		List<String> list = new ArrayList<String>();
		try
		{
			ResultSet rs = DBUtils.getTables(databaseName);
			while (rs.next())
			{
				list.add(rs.getString("TABLE_NAME"));
			}
			return list;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			DBUtils.closeAll();
		}
	}

	@Override
	public List<String> getHeaders(String databaseName, String tableName)
	{
		List<String> list = new ArrayList<String>();
		try
		{
			ResultSet rs = DBUtils.getColums(databaseName, tableName);
			while (rs.next())
			{
				list.add(rs.getString("COLUMN_NAME"));
			}
			return list;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			DBUtils.closeAll();
		}
	}

}
