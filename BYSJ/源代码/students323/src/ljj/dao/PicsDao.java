package ljj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ljj.pojo.Pics;


public interface PicsDao {
	public Integer totalCount(@Param("picsid") Integer picsid, @Param("picsname")String picsname,@Param("cid") String cid);
	public List<Pics> getPicsList(@Param("picsid") Integer picsid,@Param("picsname")String picsname,@Param("cid") String cid, @Param("currentPage")Integer currentPage, @Param("pageSize")Integer pageSize);
	public int deletePics(@Param("picsid") Integer picsid);
	public Pics findPicsById(@Param("picsid")Integer picsid);
	public int updatePics(Pics pics);
	public int insertPics(Pics pics);
	public List<Pics> findallpics();
	

}
