package ljj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ljj.pojo.ReaderType;

public interface ReaderTypeDao {
	void insertReaderType(ReaderType  readertype);

	ReaderType findReadtypeById(@Param("readerid")Integer readerid);

	int updateReaderType(ReaderType readerTypeItem);

	List<ReaderType> showReaderType();
	

}
