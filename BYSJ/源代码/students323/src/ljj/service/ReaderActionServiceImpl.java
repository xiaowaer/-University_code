package ljj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ljj.dao.ReaderActionDao;
import ljj.pojo.Notice;
import ljj.pojo.PageInfo;
import ljj.pojo.ReaderAction;



@Service("readerActionService")
public class ReaderActionServiceImpl implements ReaderActionService{
	@Autowired
    private ReaderActionDao readerActionDao;
	
	@Override
	public PageInfo<ReaderAction> findPageInfo(String actor, Integer pageIndex, Integer pageSize) {
		PageInfo<ReaderAction> pi = new PageInfo<ReaderAction>();
		
		pi.setPageIndex(pageIndex);
		pi.setPageSize(pageSize);
		//获取总条数
		Integer totalCount = readerActionDao.totalCount(actor);
		if (totalCount>0){
			pi.setTotalCount(totalCount);
			//每一页显示学生信息数
			//currentPage = (pageIndex-1)*pageSize  当前页码数减1*最大条数=开始行数
		List<ReaderAction> ReaderActionList =readerActionDao.getReaderActionList(actor, 
				     (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
		  pi.setList(ReaderActionList);
		}
		return pi;
	}

	@Override
	public int addCollects(String actor, String pageurl) {
			return readerActionDao.addCollects(actor,pageurl);
		

	}

	@Override
	public List<ReaderAction> findAction(String actor, String pageurl) {
	
		return readerActionDao.findAction(actor,pageurl);
	}

	@Override
	public int updateCollects(String actor, String pageurl) {
		// TODO Auto-generated method stub
		return readerActionDao.updateCollects(actor,pageurl);
	}

	@Override
	public int deleteCollects(String actor, String pageurl) {
		// TODO Auto-generated method stub
		return readerActionDao.deleteCollects(actor,pageurl);
	}

	@Override
	public int insertZan(String actor, String pageurl) {
		// TODO Auto-generated method stub
		return readerActionDao.insertZan(actor,pageurl);
	}

	@Override
	public int deleteZan(String actor, String pageurl) {
		// TODO Auto-generated method stub
		return readerActionDao.deleteZan(actor,pageurl);
		
	}

	@Override
	public int updateZan(String actor, String pageurl) {
		// TODO Auto-generated method stub
		return readerActionDao.updateZan(actor,pageurl);
	}

	@Override
	public int deleteActionBytarget(String targeturl) {
		// TODO Auto-generated method stub
		return readerActionDao.deleteActionBytarget(targeturl);
	}

	@Override
	public int deleteActionByreader(String nick) {
		// TODO Auto-generated method stub
		return  readerActionDao.deleteActionByreader(nick);
	}

	
	

}
