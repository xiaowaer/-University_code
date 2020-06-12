package ljj.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ljj.dao.BysjCommentDao;
import ljj.pojo.BysjComment;
import ljj.pojo.News;
import ljj.pojo.PageInfo;



@Service("bysjCommentService")
public class BysjCommentServiceIImpl implements BysjCommentService{
	

	@Autowired
    private BysjCommentDao  bysjCommentDao;

	@Override
	public PageInfo<BysjComment> findPageInfo( String target, String commenter,String cmcontent, Integer pageIndex,
			Integer pageSize) {
		PageInfo<BysjComment> pi  = new PageInfo<BysjComment>();
		pi.setPageIndex(pageIndex);
		pi.setPageSize(pageSize);
		//获取总条数
		Integer totalCount  = bysjCommentDao.totalCount(target,commenter,cmcontent);
		if (totalCount>0){
			pi.setTotalCount(totalCount);
			//每一页显示学生信息数
			//currentPage = (pageIndex-1)*pageSize  当前页码数减1*最大条数=开始行数
		List<BysjComment> commentList =bysjCommentDao.getcommentList(target,commenter,cmcontent,
				     (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
		  pi.setList(commentList);
		}
		return pi;
	}


	@Override
	public List<BysjComment> loadingCommentfortarget(String target) {
		
		return bysjCommentDao.loadingCommentfortarget(target);
	}

	@Override
	public void insertComment(Integer userid, String target, String cmcontent, Date cmtime,String commenter,String cmhead) {
		 bysjCommentDao.insertComment(userid,target,cmcontent,cmtime,commenter,cmhead);
		
	}

	@Override
	public void insertReply(Integer userid,Integer parent, String cmcontent, Date cmtime, String commenter,
			String cmhead) {
		bysjCommentDao.insertReply(userid,parent,cmcontent,cmtime,commenter,cmhead);
		
	}

	@Override
	public List<BysjComment> loadingReplyforparent(Integer parent) {
		// TODO Auto-generated method stub
		return bysjCommentDao.loadingReplyforparent(parent);
	}

	@Override
	public List<BysjComment> loadingMyComment(String nick) {
		// TODO Auto-generated method stub
		return bysjCommentDao.loadingMyComment(nick);
	}

	@Override
	public int deleteMyComment(Integer commentid) {
		// TODO Auto-generated method stub
		return bysjCommentDao.deleteMyComment(commentid);
	}

	@Override
	public List<BysjComment> loadingMyReply(String nick) {
		// TODO Auto-generated method stub
		return bysjCommentDao.loadingMyReply(nick);
	}

	@Override
	public int deleteMyReply(Integer commentid) {
		// TODO Auto-generated method stubs
		return bysjCommentDao.deleteMyReply(commentid);
	}


	@Override
	public int deleteCommentBytarget(String targeturl) {
		// TODO Auto-generated method stub
		return bysjCommentDao.deleteCommentBytarget(targeturl);
	}


	@Override
	public int deleteCommentByreader(Integer readerid) {
		// TODO Auto-generated method stub
		return bysjCommentDao.deleteCommentByreader(readerid);
	}
	

}
