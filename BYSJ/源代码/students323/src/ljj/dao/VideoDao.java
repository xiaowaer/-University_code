package ljj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ljj.pojo.Video;

public interface VideoDao {
	public int deleteVideo(@Param("videoid")Integer videoid);
	public Integer totalCount(@Param("videoname")String videoname,@Param("cid") String cid);
	public List<Video> getVideoList(@Param("videoname")String videoname,@Param("cid") String cid, @Param("currentPage")Integer currentPage, @Param("pageSize")Integer pageSize);
	public Video findVideoById(@Param("videoid")Integer videoid);
	public int updateVideo(Video video);
	public int insertVideo(Video video);
	
	
}
