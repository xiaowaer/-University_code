package ljj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ljj.dao.NoticeDao;
import ljj.pojo.Notice;
import ljj.pojo.PageInfo;
import ljj.pojo.Reader;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

	@Autowired
    private NoticeDao noticeDao;
	
	@Override
	public int addNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.addNotice(notice);
	}


	@Override
	public PageInfo<Notice> findPageInfo(String publisher, String noticetitle, Integer pageIndex, Integer pageSize) {
		PageInfo<Notice> pi = new PageInfo<Notice>();
		pi.setPageIndex(pageIndex);
		pi.setPageSize(pageSize);
		//获取总条数
		Integer totalCount = noticeDao.totalCount(publisher,noticetitle);
		if (totalCount>0){
			pi.setTotalCount(totalCount);
			//每一页显示学生信息数
			//currentPage = (pageIndex-1)*pageSize  当前页码数减1*最大条数=开始行数
		List<Notice> noticeList =noticeDao.getNoticeList(publisher,noticetitle,
				     (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
		  pi.setList(noticeList);
		}
		return pi;
	}


	@Override
	public List<Notice> findAllnotices() {
		// TODO Auto-generated method stub
		return noticeDao.findAllnotices();
	}

}
