package cn.cai.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils
{
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	public static Connection getConnection()
	{
		try
		{
			return dataSource.getConnection();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description 增删改操作
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            传入参数
	 * @return
	 */
	public static Boolean executeUpdate(String sql, Object params[])
	{
		boolean isSuccess = false;
		try
		{
			conn = dataSource.getConnection();
			pstmt = createPrepareStatement(conn, sql, params);
			int resultCount = pstmt.executeUpdate();
			if (resultCount > 0)
			{
				isSuccess = true;
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeAll();
		}
		return isSuccess;
	}

	/**
	 * @Description 查询操作
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            传入参数
	 * @return
	 */
	public static ResultSet executeQuery(String sql, Object params[])
	{
		try
		{
			conn = dataSource.getConnection();
			pstmt = createPrepareStatement(conn, sql, params);
			rs = pstmt.executeQuery();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * @Description 关闭所有连接对象
	 * @param conn
	 *            connection对象
	 * @param stmt
	 *            statemen对象
	 * @param rs
	 *            resultset对象
	 */
	public static void closeAll(Connection conn, Statement stmt, ResultSet rs)
	{
		try
		{
			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

			if (conn != null)
				conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void closeAll()
	{
		closeAll(conn, pstmt, rs);
	}

	/**
	 * @Description 批量插入数据
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            传入参数
	 * @param submitCount
	 *            提交阈值
	 * @return
	 */
	public static boolean executeBatchUpdate(String sql, List<Object[]> params, int submitCount)
	{
		try
		{
			if (params != null)
			{
				int count = 0;
				conn = dataSource.getConnection();
				pstmt = conn.prepareStatement(sql);

				for (Object[] objects : params)
				{
					for (int i = 0; i < objects.length; i++)
					{
						pstmt.setObject(i + 1, objects[i]);
					}

					pstmt.addBatch();
					count++;

					if (count % submitCount == 0)
					{
						pstmt.executeBatch();
						pstmt.clearBatch();
					}
				}

				pstmt.executeBatch();
				pstmt.clearBatch();
				return true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			closeAll(conn, pstmt, rs);
		}
		return false;
	}

	/**
	 * @Description 创预编译语句
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	private static PreparedStatement createPrepareStatement(Connection conn, String sql, Object params[]) throws SQLException
	{
		pstmt = conn.prepareStatement(sql);
		if (params != null)
		{
			for (int i = 0; i < params.length; i++)
			{
				pstmt.setObject(i + 1, params[i]);
			}
		}
		return pstmt;
	}

	/**
	* @Description 获取指定库内的所有表名称	
	* @param databaseName 库名
	* @return
	*/
	public static ResultSet getTables(String databaseName)
	{
		try
		{
			conn = dataSource.getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			rs = metaData.getTables(databaseName, null, null, new String[] { "TABLE" });
			return rs;
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
	}

	/**
	 * @Description 获取表头
	 * @param databaseName
	 *            库名
	 * @param tableName
	 *            表名
	 * @return
	 */
	public static ResultSet getColums(String databaseName, String tableName)
	{
		try
		{
			conn = dataSource.getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			rs = metaData.getColumns(databaseName, null, tableName, null);
			return rs;
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
	}
}
