package ljj.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import ljj.pojo.BysjComment;

public interface BysjCommentDao {
	public void insertComment(@Param("userid")Integer userid,@Param("target")String target, @Param("cmcontent")String cmcontent,@Param("cmtime")Date cmtime,@Param("commenter") String commenter,@Param("cmhead") String cmhead);

	public List<BysjComment> loadingCommentfortarget(@Param("target")String target);
	
	public void insertReply(@Param("userid")Integer userid,@Param("parent")Integer parent, 
			@Param("cmcontent")String cmcontent,@Param("cmtime")Date cmtime,
			@Param("commenter") String commenter,@Param("cmhead") String cmhead);
	
	public List<BysjComment> loadingReplyforparent(@Param("parent")Integer parent);

	public List<BysjComment> loadingMyComment(@Param("nick")String nick);

	public int deleteMyComment(@Param("commentid")Integer commentid);

	public List<BysjComment> loadingMyReply(@Param("nick")String nick);

	public int deleteMyReply(@Param("commentid")Integer  commentid);

	public List<BysjComment> getcommentList(@Param("target")String target, @Param("commenter")String commenter, @Param("cmcontent")String cmcontent, @Param("currentPage")Integer currentPage, @Param("pageSize")Integer pageSize);

	public Integer totalCount(@Param("target")String target,@Param("commenter") String commenter, @Param("cmcontent")String cmcontent);

	public int deleteCommentBytarget(@Param("target")String target);

	public int deleteCommentByreader(@Param("userid")Integer userid);

}
