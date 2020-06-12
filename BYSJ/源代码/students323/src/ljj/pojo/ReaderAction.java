package ljj.pojo;

public class ReaderAction {
    private static final long serialVersionUID = 1L;
	private Integer actionid;
	private String actor;
	private String callzan;
	private String collect;
	private String pageurl;
	
	public ReaderAction() {
		super();
		// TODO Auto-generated constructor stub
	}
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
	public Integer getActionid() {
		return actionid;
	}
	public void setActionid(Integer actionid) {
		this.actionid = actionid;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getCallzan() {
		return callzan;
	}
	public void setCallzan(String callzan) {
		this.callzan = callzan;
	}
	public String getCollect() {
		return collect;
	}
	public void setCollect(String collect) {
		this.collect = collect;
	}
	public String getPageurl() {
		return pageurl;
	}
	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

}
