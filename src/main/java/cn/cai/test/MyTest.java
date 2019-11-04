package cn.cai.test;

import java.sql.Connection;

import org.junit.Test;

import cn.cai.utils.DBUtils;

public class MyTest
{
	@Test
	public void dataSourceTest()
	{
		System.out.println("Hello");
		Connection connection1 = DBUtils.getConnection();

		Connection connection2 = DBUtils.getConnection();
		if (connection1 == connection2)
		{
			System.out.println("yes");
		}
		else
		{
			System.out.println("no");
		}
	}
}
