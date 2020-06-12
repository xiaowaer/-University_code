package ljj.service;

import java.util.List;

import ljj.pojo.PageInfo;
import ljj.pojo.Pics;

public interface PicsService {

	public PageInfo<Pics> findPageInfo(Integer picsid, String picsname, String cid, Integer pageIndex, Integer pageSize);

	public int deletePics(Integer ipicsid);

	public Pics findPicsById(Integer picsid);

	public int updatePics(Pics pics);

	public int insertPics(Pics pics);

	public List<Pics> findallpics();

	
}
