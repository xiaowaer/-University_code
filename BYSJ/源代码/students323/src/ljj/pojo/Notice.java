package ljj.pojo;

import java.util.Date;

public class Notice {
    private static final long serialVersionUID = 1L;
	private Integer noticeid;
    private String noticecontent;
    private String noticetitle;
    private String publisher;
    private Date  publishtime;
    private String noticetype;
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
	public Integer getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(Integer noticeid) {
		this.noticeid = noticeid;
	}
	public String getNoticecontent() {
		return noticecontent;
	}
	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}
	public String getNoticetitle() {
		return noticetitle;
	}
	public void setNoticetitle(String noticetitle) {
		this.noticetitle = noticetitle;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	public String getNoticetype() {
		return noticetype;
	}
	public void setNoticetype(String noticetype) {
		this.noticetype = noticetype;
	}
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    
}
