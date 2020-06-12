package ljj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ljj.pojo.Notice;
import ljj.pojo.ReaderAction;

public interface ReaderActionDao {

	public Integer totalCount(@Param("actor") String actor);
	public List<ReaderAction> getReaderActionList(@Param("actor") String actor, @Param("currentPage")Integer currentPage, @Param("pageSize")Integer pageSize);
	public int addCollects(@Param("actor")String actor, @Param("pageurl") String pageurl);
	public List<ReaderAction> findAction(@Param("actor")String actor, @Param("pageurl")  String pageurl);
	public int updateCollects(@Param("actor")String actor, @Param("pageurl") String pageurl);
	public int deleteCollects(@Param("actor")String actor,  @Param("pageurl") String pageurl);
	public int insertZan(@Param("actor")String actor,@Param("pageurl") String pageurl);
	public int deleteZan(@Param("actor")String actor,@Param("pageurl") String pageurl);
	public int updateZan(@Param("actor")String actor,@Param("pageurl")String pageurl);
	public int deleteActionBytarget(@Param("pageurl")String pageurl);
	public int deleteActionByreader(@Param("actor")String actor);	
	
}
