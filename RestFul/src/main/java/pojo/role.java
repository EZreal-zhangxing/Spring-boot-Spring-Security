package pojo;

public class role {
	private Integer id;
	private Integer userId;
	private String roleCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public role(Integer id, Integer userId, String roleCode) {
		super();
		this.id = id;
		this.userId = userId;
		this.roleCode = roleCode;
	}
	public role() {
		super();
	}
	@Override
	public String toString() {
		return "role [id=" + id + ", userId=" + userId + ", roleCode="
				+ roleCode + "]";
	}
	
}
