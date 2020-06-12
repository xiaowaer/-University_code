package ljj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ljj.dao.PicsDao;
import ljj.pojo.PageInfo;
import ljj.pojo.Pics;

@Service("picsService")
public class PicsServiceImpl implements PicsService{
	@Autowired
    private PicsDao  picsDao;




	@Override
	public PageInfo<Pics> findPageInfo(Integer picsid, String picsname, String cid, Integer pageIndex,
			Integer pageSize) {
		PageInfo<Pics> pi  = new PageInfo<Pics>();
		pi.setPageIndex(pageIndex);
		pi.setPageSize(pageSize);
		//��ȡ������
		Integer totalCount  = picsDao.totalCount(picsid,picsname,cid);
		if (totalCount>0){
			pi.setTotalCount(totalCount);
			//ÿһҳ��ʾѧ����Ϣ��
			//currentPage = (pageIndex-1)*pageSize  ��ǰҳ������1*�������=��ʼ����
		List<Pics> picsList =picsDao.getPicsList(picsid,picsname,cid,
				     (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
		  pi.setList(picsList);
		}
		return pi;
	}




	@Override
	public int deletePics(Integer picsid) {
		// TODO Auto-generated method stub
		return picsDao.deletePics(picsid);
	}




	@Override
	public Pics findPicsById(Integer picsid) {
		// TODO Auto-generated method stub
		return picsDao.findPicsById(picsid);
	}




	@Override
	public int updatePics(Pics pics) {
		// TODO Auto-generated method stub
		return picsDao.updatePics(pics);
	}




	@Override
	public int insertPics(Pics pics) {
		// TODO Auto-generated method stub
		return picsDao.insertPics(pics);
	}




	@Override
	public List<Pics> findallpics() {
		// TODO Auto-generated method stub
		return picsDao.findallpics();
	}

	

}

