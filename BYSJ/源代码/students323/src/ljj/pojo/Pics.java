package ljj.pojo;

import java.util.Date;

public class Pics {
	private Integer picsid;
	private String picsoriginurl;
	private String picsname;
	private String picsorigin;
	private String picskeyword;
	private String cid;
	private String picsheadurl;
	private Date picsctime;
	private Date picsrtime;
	private String targeturl;
	private String picscontent;
	

	@Override
	public String toString() {
		return "Pics [picsid=" + picsid + ", picsriginurl=" + picsoriginurl + ", picsname=" + picsname + ", picsorigin="
				+ picsorigin + ", picskeyword=" + picskeyword + ", cid=" + cid + ", picsheadurl=" + picsheadurl
				+ ", picsctime=" + picsctime + ", picsrtime=" + picsrtime + ", targeturl=" + targeturl + "]";
	}
	public Pics() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPicscontent() {
		return picscontent;
	}
	public void setPicscontent(String picscontent) {
		this.picscontent = picscontent;
	}
	public Integer getPicsid() {
		return picsid;
	}
	public void setPicsid(Integer picsid) {
		this.picsid = picsid;
	}

	public String getPicsoriginurl() {
		return picsoriginurl;
	}
	public void setPicsoriginurl(String picsoriginurl) {
		this.picsoriginurl = picsoriginurl;
	}
	public String getPicsname() {
		return picsname;
	}
	public void setPicsname(String picsname) {
		this.picsname = picsname;
	}
	public String getPicsorigin() {
		return picsorigin;
	}
	public void setPicsorigin(String picsorigin) {
		this.picsorigin = picsorigin;
	}
	public String getPicskeyword() {
		return picskeyword;
	}
	public void setPicskeyword(String picskeyword) {
		this.picskeyword = picskeyword;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPicsheadurl() {
		return picsheadurl;
	}
	public void setPicsheadurl(String picsheadurl) {
		this.picsheadurl = picsheadurl;
	}
	public Date getPicsctime() {
		return picsctime;
	}
	public void setPicsctime(Date picsctime) {
		this.picsctime = picsctime;
	}
	public Date getPicsrtime() {
		return picsrtime;
	}
	public void setPicsrtime(Date picsrtime) {
		this.picsrtime = picsrtime;
	}
	public String getTargeturl() {
		return targeturl;
	}
	public void setTargeturl(String targeturl) {
		this.targeturl = targeturl;
	}

	
	
	

}
