package ljj.pojo;

import java.util.Date;

public class News {
	private Integer newsid;
	private String newsoriginurl;
	private String newstitle;
	private String newsorigin;
	private String newskeyword;
	private String cid;
	private String newsheadurl;
	private Date newsctime;
	private Date newsrtime;
	private String targeturl;
	private String newscontent;
	public String getNewscontent() {
		return newscontent;
	}
	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}
	public Integer getNewsid() {
		return newsid;
	}
	public void setNewsid(Integer newsid) {
		this.newsid = newsid;
	}
	public String getNewsoriginurl() {
		return newsoriginurl;
	}
	public void setNewsoriginurl(String newsoriginurl) {
		this.newsoriginurl = newsoriginurl;
	}
	public String getNewsorigin() {
		return newsorigin;
	}
	public void setNewsorigin(String newsorigin) {
		this.newsorigin = newsorigin;
	}
	public String getNewskeyword() {
		return newskeyword;
	}
	public void setNewskeyword(String newskeyword) {
		this.newskeyword = newskeyword;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getNewsheadurl() {
		return newsheadurl;
	}
	public void setNewsheadurl(String newsheadurl) {
		this.newsheadurl = newsheadurl;
	}
	public Date getNewsctime() {
		return newsctime;
	}
	public void setNewsctime(Date newsctime) {
		this.newsctime = newsctime;
	}
	public Date getNewsrtime() {
		return newsrtime;
	}
	public void setNewsrtime(Date newsrtime) {
		this.newsrtime = newsrtime;
	}
	public String getTargeturl() {
		return targeturl;
	}
	public void setTargeturl(String targeturl) {
		this.targeturl = targeturl;
	}
	@Override
	public String toString() {
		return "News [newsid=" + newsid + ", newsoriginurl=" + newsoriginurl + ", newsname=" + newstitle
				+ ", newsorigin=" + newsorigin + ", newskeyword=" + newskeyword + ", cid=" + cid + ", newsheadurl="
				+ newsheadurl + ", newsctime=" + newsctime + ", newsrtime=" + newsrtime + ", targeturl=" + targeturl
				+ "]";
	}
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNewstitle() {
		return newstitle;
	}
	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}
	
	
	

	

}
