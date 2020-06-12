package ljj.pojo;

public class BysjUser {
	private Integer userid;
	private String nick;
	private String head;
	private String password;
    private String usertype;

    
    @Override
	public String toString() {
		return "BysjUser [userid=" + userid + ", nick=" + nick + ", head=" + head + ", password=" + password
				+ ", usertype=" + usertype + "]";
	}
	public BysjUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNick() {
  		return nick;
  	}
  	public void setNick(String nick) {
  		this.nick = nick;
  	}


	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
    

}
