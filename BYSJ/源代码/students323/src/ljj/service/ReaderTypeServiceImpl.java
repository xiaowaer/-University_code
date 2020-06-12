package ljj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ljj.dao.ReaderTypeDao;
import ljj.pojo.ReaderType;

@Service("readertypeService")
public class ReaderTypeServiceImpl implements ReaderTypeService{
	@Autowired
    private ReaderTypeDao readerTypeDao;

	@Override
	public void insertReaderType(ReaderType  readertype) {
		readerTypeDao.insertReaderType(readertype);
		
	}

	@Override
	public ReaderType findReadtypeById(Integer readerid) {
		// TODO Auto-generated method stub
		return readerTypeDao.findReadtypeById(readerid);
	}

	@Override
	public int updateReaderType(ReaderType readerTypeItem) {
		// TODO Auto-generated method stub
		return readerTypeDao.updateReaderType(readerTypeItem);
	}

	@Override
	public List<ReaderType> showReaderType() {
		// TODO Auto-generated method stub
		return readerTypeDao.showReaderType();
	}

	

}
