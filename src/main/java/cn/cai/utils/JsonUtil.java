package cn.cai.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;


public class JsonUtil
{
	/**
	* @Description 将JSON字符串转化为指定对象
	* @param request HttpServletRequest请求对象
	* @param valueType 转化的制定格式
	* @return
	*/
	public static <T> T getJavaBean(HttpServletRequest request, Class<T> valueType)
	{
		T k = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));)
		{

			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null)
			{
				sb.append(line);
			}
			System.out.println(sb.toString());
			k = JSON.parseObject(sb.toString(), valueType);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return k;
	}
}
