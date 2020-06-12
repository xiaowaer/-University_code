package ljj.service;


import ljj.pojo.PageInfo;

import java.util.List;

import ljj.pojo.News;

public interface NewsService {

	public PageInfo<News> findPageInfo(Integer newsid, String newstitle, String cid, Integer pageIndex, Integer pageSize);

	public int deletenews(Integer newsid);

	public News findnewsById(Integer newsid);

	public int updateNews(News news);

	public int insertNews(News news);

	public List<News> findallnews();

	
}
