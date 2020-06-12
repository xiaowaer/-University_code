package ljj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ljj.dao.ReaderTypeDao;
import ljj.dao.RecommendDao;
import ljj.pojo.PageInfo;
import ljj.pojo.Recommend;

@Service("recommendService")
public class RecommendServiceImpl implements RecommendService{
	@Autowired
    private RecommendDao recommendDao;

	
	@Override
	public Recommend findRecommendBytargeturl(String targeturl) {
		// TODO Auto-generated method stub
		return recommendDao.findRecommendBytargeturl(targeturl);
	}

	@Override
	public int insertRecommend(Recommend recommend) {
		// TODO Auto-generated method stub
		return recommendDao.insertRecommend(recommend);
	}

	@Override
	public int updateRecommend(Recommend recommend) {
		// TODO Auto-generated method stub
		return recommendDao.updateRecommend(recommend);
	}

	@Override
	public PageInfo<Recommend> findPageInfo(String re_intro, String rusertype, Integer pageIndex, Integer pageSize) {
		PageInfo<Recommend> pi = new PageInfo<Recommend>();
		pi.setPageIndex(pageIndex);
		pi.setPageSize(pageSize);
		//��ȡ������
		Integer totalCount = recommendDao.totalCount(re_intro,rusertype);
		if (totalCount>0){
			pi.setTotalCount(totalCount);
			//ÿһҳ��ʾѧ����Ϣ��
			//currentPage = (pageIndex-1)*pageSize  ��ǰҳ������1*�������=��ʼ����
		List<Recommend> recommendList =recommendDao.getRecommendList(re_intro,rusertype,
				     (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
		  pi.setList(recommendList);
		}
		return pi;
	}

	@Override
	public int deleterecommend(Integer recommendid) {
		// TODO Auto-generated method stub
		return recommendDao.deleterecommend(recommendid);
	}

	@Override
	public int deleteRecommendBytarget(String targeturl) {
		// TODO Auto-generated method stub
		return recommendDao.deleteRecommendBytarget(targeturl);
	}
	

}
