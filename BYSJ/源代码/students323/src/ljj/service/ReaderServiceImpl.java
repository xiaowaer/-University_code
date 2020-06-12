package ljj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ljj.dao.ReaderDao;
import ljj.pojo.PageInfo;
import ljj.pojo.Reader;


@Service("readerService")
public class ReaderServiceImpl implements ReaderService{
	@Autowired
    private ReaderDao readerDao;
	
	//��ҳ
	@Override
	public PageInfo<Reader> findPageInfo(String nick, String sex, Integer pageIndex, Integer pageSize) {
		PageInfo<Reader> pi = new PageInfo<Reader>();
		pi.setPageIndex(pageIndex);
		pi.setPageSize(pageSize);
		//��ȡ������
		Integer totalCount = readerDao.totalCount(nick,sex);
		if (totalCount>0){
			pi.setTotalCount(totalCount);
			//ÿһҳ��ʾѧ����Ϣ��
			//currentPage = (pageIndex-1)*pageSize  ��ǰҳ������1*�������=��ʼ����
		List<Reader> readerList =	readerDao.getReaderList(nick,sex,
				     (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
		  pi.setList(readerList);
		}
		return pi;
	}
	
	
	@Override
	public void insertReaderauto(String nick) {
		readerDao.insertReaderauto(nick);

	}
	
	@Override
	public List<Reader> findreaderbynick(String nick) {
		// TODO Auto-generated method stub
		return readerDao.findreaderbynick(nick);
	}

	@Override
	public int updatereaderinfo(Reader reader) {
		// TODO Auto-generated method stub
		return readerDao.updatereaderinfo(reader);
	}


	@Override
	public Reader findreaderById(Integer readerid) {
		// TODO Auto-generated method stub
		return readerDao.findreaderById(readerid);
	}


	@Override
	public int deletereader(Integer readerid) {
		// TODO Auto-generated method stub
		return readerDao.deletereader(readerid);
	}






}