package ljj.pojo;

import java.util.Date;

public class BysjComment {
	private Integer commentid;
	private Integer userid;
	private String cmcontent;
	private String target;
	private Integer rootid;
	private Integer parent;
	private Date cmtime;
	private String commenter;
	private String cmhead;
	
	public String getCommenter() {
		return commenter;
	}
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}
	public String getCmhead() {
		return cmhead;
	}
	public void setCmhead(String cmhead) {
		this.cmhead = cmhead;
	}
	public Date getCmtime() {
		return cmtime;
	}
	public void setCmtime(Date cmtime) {
		this.cmtime = cmtime;
	}
	public Integer getCommentid() {
		return commentid;
	}
	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getCmcontent() {
		return cmcontent;
	}
	public void setCmcontent(String cmcontent) {
		this.cmcontent = cmcontent;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Integer getRootid() {
		return rootid;
	}
	public void setRootid(Integer rootid) {
		this.rootid = rootid;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public BysjComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
