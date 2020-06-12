package ljj.service;

import java.util.List;

import ljj.pojo.Notice;
import ljj.pojo.PageInfo;
import ljj.pojo.Recommend;


public interface RecommendService  {

	Recommend findRecommendBytargeturl(String targeturl);

	int insertRecommend(Recommend recommend);

	int updateRecommend(Recommend recommend);

	PageInfo<Recommend> findPageInfo(String re_intro, String reusertype, Integer pageIndex, Integer pageSize);

	int deleterecommend(Integer irecommendid);

	int deleteRecommendBytarget(String targeturl);

	
	
	
}