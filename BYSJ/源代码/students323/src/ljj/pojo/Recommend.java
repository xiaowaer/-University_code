package ljj.pojo;

import java.util.Date;

public class Recommend {

		private Integer recommendid;
		private String re_url;
		private String re_intro;
		private String rusertype;
		private Date recommendtime;
	
		public String getRe_url() {
			return re_url;
		}
		public String getRe_intro() {
			return re_intro;
		}

		public Date getRecommendtime() {
			return recommendtime;
		}
		
		public Integer getRecommendid() {
			return recommendid;
		}

		public void setRecommendid(Integer recommendid) {
			this.recommendid = recommendid;
		}

		public void setRe_url(String re_url) {
			this.re_url = re_url;
		}
		public void setRe_intro(String re_intro) {
			this.re_intro = re_intro;
		}
	
		public String getRusertype() {
			return rusertype;
		}
		public void setRusertype(String rusertype) {
			this.rusertype = rusertype;
		}
		public void setRecommendtime(Date recommendtime) {
			this.recommendtime = recommendtime;
		}
		

}
