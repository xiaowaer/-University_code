package ljj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ljj.pojo.PageInfo;
import ljj.pojo.Reader;

public interface ReaderService {
	
	//∑÷“≥≤È—Ø
		public PageInfo<Reader> findPageInfo(String nick,String sex, Integer pageIndex, Integer pageSize);

	void insertReaderauto(String nick);

	List<Reader> findreaderbynick(String nick);

	int updatereaderinfo(Reader reader);
	
	public Reader findreaderById(Integer readerid);

	public int deletereader(Integer ireaderid);

	
	

}
