package pojo;

public class user {
	private Integer id;
	private String username;
	private String password;
	private Integer enable;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public user(Integer id, String username, String password, Integer enable) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enable = enable;
	}
	
	public user(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	public user() {
		super();
	}
	@Override
	public String toString() {
		return "user [id=" + id + ", username=" + username + ", password="
				+ password + ", enable=" + enable + "]";
	}
	
}
