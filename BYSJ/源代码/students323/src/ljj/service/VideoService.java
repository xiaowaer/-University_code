package ljj.service;

import java.util.Date;
import java.util.List;

import ljj.pojo.PageInfo;
import ljj.pojo.Video;


public interface VideoService {

	public int deleteVideo(Integer videoid);

	public PageInfo<Video> findPageInfo(String videoname, String cid, Integer pageIndex, Integer pageSize);

	public Video findVideoById(Integer videoid);

	public int updateVideo(Video video);

	public int insertVideo(Video video);
	
}
