package ljj.pojo;

public class Reader {
	private static final long serialVersionUID = 1L;
	private Integer readerid;
    private String nick;
    private String birthday;

	private String telephone;
    private String sex;
    private String work;
    private String likeread;
	private String city;
	
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	private String intro;
    
    public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
    
	public Integer getReaderid() {
		return readerid;
	}
	public void setReaderid(Integer readerid) {
		this.readerid = readerid;
	}
    public String getLikeread() {
		return likeread;
	}
	public void setLikeread(String likeread) {
		this.likeread = likeread;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}

	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Reader() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	@Override
	public String toString() {
		return "Reader [readerid=" + readerid + ", nick=" + nick + ", birthday=" + birthday + ", telephone=" + telephone
				+ ", sex=" + sex + ", work=" + work + ", likeread=" + likeread + ", city=" + city + ", intro=" + intro
				+ "]";
	}

}
