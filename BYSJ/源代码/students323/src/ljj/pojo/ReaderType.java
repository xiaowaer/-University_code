package ljj.pojo;

public class ReaderType {
	private Integer readerid;
	private String readertype;
	private String priority;
	private String level;
	
	
	@Override
	public String toString() {
		return "ReaderType [readerid=" + readerid + ", readertype=" + readertype + ", priority=" + priority + ", level="
				+ level + "]";
	}
	public ReaderType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getReaderid() {
		return readerid;
	}
	public String getReadertype() {
		return readertype;
	}
	public String getPriority() {
		return priority;
	}
	public String getLevel() {
		return level;
	}
	public void setReaderid(Integer readerid) {
		this.readerid = readerid;
	}
	public void setReadertype(String readertype) {
		this.readertype = readertype;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	

}
