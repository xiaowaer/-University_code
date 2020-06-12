package ljj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import ljj.pojo.Reader;

public interface ReaderDao {
	
	 //获取总条数
    public Integer totalCount(@Param("nick") String nick, @Param("sex")String sex);
    
	 //获取用户列表
    public List<Reader> getReaderList(@Param("nick") String nick, @Param("sex")String sex, @Param("currentPage")Integer currentPage, @Param("pageSize")Integer pageSize);

	void insertReaderauto(@Param("nick")String nick);

	List<Reader> findreaderbynick(@Param("nick")String nick);

	int updatereaderinfo(Reader reader);
	
	public Reader findreaderById(@Param("readerid")Integer readerid);

	public int deletereader(@Param("readerid")Integer readerid);

	
	
	
	
}
