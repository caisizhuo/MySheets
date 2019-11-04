package cn.cai.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtil
{

	public static List<String[]> readExcel(String path)
	{
		List<String[]> list = new ArrayList<>();
		Workbook wb = null;
		try (InputStream stream = new FileInputStream(new File(path));)
		{
			wb = Workbook.getWorkbook(stream);
			Sheet sheet = wb.getSheet(0);
			int rows = sheet.getRows();
			int colums = sheet.getColumns();

			for (int i = 0; i < rows; i++)
			{
				String[] str = new String[colums];
				for (int j = 0; j < colums; j++)
				{
					Cell cell = sheet.getCell(j, i);
					str[j] = cell.getContents();
				}
				list.add(str);
			}
			return list;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (BiffException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (IOException e)
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
			if (wb != null)
				wb.close();
		}
	}

	/**
	 * @Description 生成Excel 文件名为当前日期.xls
	 * @param path
	 *            生成文件位置
	 * @param list
	 *            传入数据全为文本
	 * @return 返回文件名
	 */
	public static String generateExcel(String path, List<String[]> list)
	{

		WritableWorkbook wb = null;
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
		try
		{
			if (list != null)
			{
				wb = Workbook.createWorkbook(new File(path + "/" + fileName));

				WritableSheet sheet = wb.createSheet("Sheet1", 0);

				for (int i = 0; i < list.size(); i++)
				{
					for (int j = 0; j < list.get(i).length; j++)
					{
						Label label = new Label(j, i, list.get(i)[j]);
						sheet.addCell(label);
					}
				}
			}
			wb.write();
			return fileName;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (RowsExceededException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (WriteException e)
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
			if (wb != null)
				try
				{
					wb.close();
				}
				catch (WriteException | IOException e)
				{
					e.printStackTrace();
				}
		}

	}
}
