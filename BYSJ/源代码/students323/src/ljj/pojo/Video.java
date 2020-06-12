package ljj.pojo;

import java.util.Date;

public class Video {

	private Integer videoid;
	private String videooriginurl;
	private String videoname;
	private String videoorigin;
	private String videokeyword;
	private String cid;
	private String videoheadurl;
	private Date videoctime;
	private Date videortime;
	private String targeturl;
	
	
	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Video [videoid=" + videoid + ", videooriginurl=" + videooriginurl + ", videoname=" + videoname
				+ ", videoorigin=" + videoorigin + ", videokeyword=" + videokeyword + ", cid=" + cid + ", videoheadurl="
				+ videoheadurl + ", videoctime=" + videoctime + ", videortime=" + videortime + ", targeturl="
				+ targeturl + "]";
	}
	public Integer getVideoid() {
		return videoid;
	}
	public void setVideoid(Integer videoid) {
		this.videoid = videoid;
	}
	public String getVideooriginurl() {
		return videooriginurl;
	}
	public void setVideooriginurl(String videooriginurl) {
		this.videooriginurl = videooriginurl;
	}
	public String getVideoname() {
		return videoname;
	}
	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}
	public String getVideoorigin() {
		return videoorigin;
	}
	public void setVideoorigin(String videoorigin) {
		this.videoorigin = videoorigin;
	}
	public String getVideokeyword() {
		return videokeyword;
	}
	public void setVideokeyword(String videokeyword) {
		this.videokeyword = videokeyword;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getVideoheadurl() {
		return videoheadurl;
	}
	public void setVideoheadurl(String videoheadurl) {
		this.videoheadurl = videoheadurl;
	}
	public Date getVideoctime() {
		return videoctime;
	}
	public void setVideoctime(Date videoctime) {
		this.videoctime = videoctime;
	}
	public Date getVideortime() {
		return videortime;
	}
	public void setVideortime(Date videortime) {
		this.videortime = videortime;
	}
	public String getTargeturl() {
		return targeturl;
	}
	public void setTargeturl(String targeturl) {
		this.targeturl = targeturl;
	}

	
}
