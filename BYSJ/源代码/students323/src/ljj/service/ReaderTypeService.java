package ljj.service;

import java.util.List;

import ljj.pojo.ReaderType;

public interface ReaderTypeService {
	
	void insertReaderType(ReaderType readertype);

	ReaderType findReadtypeById(Integer readerid);

	int updateReaderType(ReaderType readerTypeItem);

	List<ReaderType> showReaderType();
	
	
}
