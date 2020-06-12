package ljj.service;

import java.util.Date;
import java.util.List;

import ljj.pojo.BysjComment;
import ljj.pojo.PageInfo;

public interface BysjCommentService {
	
	public void insertComment(Integer userid,String target, String cmcontent, Date cmtime, String commenter, String cmhead);
	public List<BysjComment> loadingCommentfortarget(String target);
	public void insertReply(Integer userid, Integer parent, String cmcontent, Date cmtime, String commenter,
			String cmhead);
	public List<BysjComment> loadingReplyforparent(Integer parent);
	public List<BysjComment> loadingMyComment(String nick);
	public int deleteMyComment(Integer commentid);
	public List<BysjComment> loadingMyReply(String nick);
	public int deleteMyReply(Integer icommentid);
	public PageInfo<BysjComment> findPageInfo(String target, String commenter, String cmcontent,
			Integer pageIndex, Integer pageSize);
	public int deleteCommentBytarget(String targeturl);
	public int deleteCommentByreader(Integer ireaderid);

	

}
