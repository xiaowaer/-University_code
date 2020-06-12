package ljj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ljj.pojo.Notice;

public interface NoticeDao {
	
	public int addNotice(Notice notice);
	public Integer totalCount(@Param("publisher") String publisher, @Param("noticetitle") String noticetitle);
	public List<Notice> getNoticeList(@Param("publisher") String publisher, @Param("noticetitle") String noticetitle, @Param("currentPage")Integer currentPage, @Param("pageSize")Integer pageSize);
	public List<Notice> findAllnotices();
	


}
