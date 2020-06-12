package ljj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ljj.pojo.News;


public interface NewsDao {
	public Integer totalCount(@Param("newsid") Integer newsid, @Param("newstitle")String newstitle,@Param("cid") String cid);
	public List<News> getnewsList(@Param("newsid") Integer newsid,@Param("newstitle")String newstitle,@Param("cid") String cid, @Param("currentPage")Integer currentPage, @Param("pageSize")Integer pageSize);
	public int deletenews(@Param("newsid") Integer newsid);
	public News findnewsById(@Param("newsid")Integer newsid);
	public int updateNews(News news);
	public int insertNews(News news);
	public List<News> getAllnewsList();
	

}
