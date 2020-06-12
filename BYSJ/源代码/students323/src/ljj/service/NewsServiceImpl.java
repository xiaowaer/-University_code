package ljj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ljj.dao.NewsDao;
import ljj.pojo.PageInfo;
import ljj.pojo.News;

@Service("newsService")
public class NewsServiceImpl implements NewsService{
	@Autowired
    private NewsDao  newsDao;



	@Override
	public PageInfo<News> findPageInfo(Integer newsid, String newstitle, String cid, Integer pageIndex,
			Integer pageSize) {
		PageInfo<News> pi  = new PageInfo<News>();
		pi.setPageIndex(pageIndex);
		pi.setPageSize(pageSize);
		//��ȡ������
		Integer totalCount  = newsDao.totalCount(newsid,newstitle,cid);
		if (totalCount>0){
			pi.setTotalCount(totalCount);
			//ÿһҳ��ʾѧ����Ϣ��
			//currentPage = (pageIndex-1)*pageSize  ��ǰҳ������1*�������=��ʼ����
		List<News> newsList =newsDao.getnewsList(newsid,newstitle,cid,
				     (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
		  pi.setList(newsList);
		}
		return pi;
	}




	@Override
	public int deletenews(Integer newsid) {
		// TODO Auto-generated method stub
		return newsDao.deletenews(newsid);
	}




	@Override
	public News findnewsById(Integer newsid) {
		// TODO Auto-generated method stub
		return newsDao.findnewsById(newsid);
	}




	@Override
	public int updateNews(News news) {
		// TODO Auto-generated method stub
		return newsDao.updateNews(news);
	}




	@Override
	public int insertNews(News news) {
		// TODO Auto-generated method stub
		return newsDao.insertNews(news);
	}




	@Override
	public List<News> findallnews() {
		// TODO Auto-generated method stub
		return newsDao.getAllnewsList();
	}

	

}

