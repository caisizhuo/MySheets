package cn.cai.entity;

public class User
{
	// 主键，自增
	private String id;
	private String user;
	private String password;

	// 0为管理员，1为普通用户
	private String level;

	public User(String user, String password)
	{
		super();
		this.user = user;
		this.password = password;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getLevel()
	{
		return level;
	}

	public void setLevel(String level)
	{
		this.level = level;
	}

}
