package cn.cai.entity;

public class ChangeData
{
	private String tableName;
	private String primaryKeyName;
	private String primaryKeyValue;
	private String changeValueHeader;
	private String changeValue;

	public ChangeData()
	{
		super();
	}

	public ChangeData(String tableName, String primaryKeyName, String primaryKeyValue, String changeValueHeader, String changeValue)
	{
		super();
		this.tableName = tableName;
		this.primaryKeyName = primaryKeyName;
		this.primaryKeyValue = primaryKeyValue;
		this.changeValueHeader = changeValueHeader;
		this.changeValue = changeValue;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getPrimaryKeyName()
	{
		return primaryKeyName;
	}

	public void setPrimaryKeyName(String primaryKeyName)
	{
		this.primaryKeyName = primaryKeyName;
	}

	public String getPrimaryKeyValue()
	{
		return primaryKeyValue;
	}

	public void setPrimaryKeyValue(String primaryKeyValue)
	{
		this.primaryKeyValue = primaryKeyValue;
	}

	public String getChangeValueHeader()
	{
		return changeValueHeader;
	}

	public void setChangeValueHeader(String changeValueHeader)
	{
		this.changeValueHeader = changeValueHeader;
	}

	public String getChangeValue()
	{
		return changeValue;
	}

	public void setChangeValue(String changeValue)
	{
		this.changeValue = changeValue;
	}

	@Override
	public String toString()
	{
		return "ChangeData [tableName=" + tableName + ", primaryKeyName=" + primaryKeyName + ", primaryKeyValue=" + primaryKeyValue + ", changeValueHeader=" + changeValueHeader + ", changeValue="
				+ changeValue + "]";
	}

	public String[] toArray()
	{
		return new String[] { tableName, primaryKeyName, primaryKeyValue, changeValueHeader, changeValue };
	}
}