package ljj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ljj.pojo.Recommend;

public interface RecommendDao {

	Recommend findRecommendBytargeturl(@Param("targeturl")String targeturl);

	int insertRecommend(Recommend recommend);
	int updateRecommend(Recommend recommend);

	List<Recommend> getRecommendList(@Param("re_intro")String re_intro, @Param("rusertype")String reusertype, @Param("currentPage")Integer currentPage, @Param("pageSize")Integer pageSize);
	Integer totalCount(@Param("re_intro")String re_intro, @Param("rusertype")String rusertype);

	int deleterecommend(@Param("recommendid")Integer recommendid);

	int deleteRecommendBytarget(@Param("re_url")String re_url);
}
