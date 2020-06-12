package ljj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ljj.dao.VideoDao;
import ljj.pojo.PageInfo;
import ljj.pojo.Reader;
import ljj.pojo.Video;

@Service("videoService")
public class VideoServiceImpl implements VideoService{
	@Autowired
    private VideoDao  videoDao;

	@Override
	public int deleteVideo(Integer videoid) {
		// TODO Auto-generated method stub
		return videoDao.deleteVideo(videoid);
	}

	@Override
	public PageInfo<Video> findPageInfo(String videoname, String cid, Integer pageIndex, Integer pageSize) {
		PageInfo<Video> pi  = new PageInfo<Video>();
		pi.setPageIndex(pageIndex);
		pi.setPageSize(pageSize);
		//��ȡ������
		Integer totalCount  = videoDao.totalCount(videoname,cid);
		if (totalCount>0){
			pi.setTotalCount(totalCount);
			//ÿһҳ��ʾѧ����Ϣ��
			//currentPage = (pageIndex-1)*pageSize  ��ǰҳ������1*�������=��ʼ����
		List<Video> videoList =	videoDao.getVideoList(videoname,cid,
				     (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
		  pi.setList(videoList );
		}
		return pi;
	}

	@Override
	public Video findVideoById(Integer videoid) {
		// TODO Auto-generated method stub
		return videoDao.findVideoById(videoid);
	}

	@Override
	public int updateVideo(Video video) {
		// TODO Auto-generated method stub
		return videoDao.updateVideo(video);
	}

	@Override
	public int insertVideo(Video video) {
		// TODO Auto-generated method stub
		return videoDao.insertVideo(video);
	}

	

}
