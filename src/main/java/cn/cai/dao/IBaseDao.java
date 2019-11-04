package cn.cai.dao;

import java.sql.ResultSet;
import java.util.List;

public interface IBaseDao
{
	public boolean insert(String sql, Object[] params);

	// public boolean batchInsert(String sql, List<Object[]> params, int
	// submitCount);

	public boolean delete(String sql, Object[] params);

	public boolean update(String sql, Object[] params);

	public ResultSet query(String sql, Object[] params);

	public List<String> getHeaders(String databaseName, String tableName);

	public List<String> getTables(String databaseName);
}
