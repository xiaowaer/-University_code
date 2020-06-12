package ljj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ljj.dao.CategoriesDao;
import ljj.pojo.Categories;
import ljj.pojo.News;
import ljj.pojo.PageInfo;

@Service("categoriesService")
public class CategoriesServiceImpl implements CategoriesService{
	@Autowired
    private CategoriesDao CategoriesDao;

	@Override
	public List<Categories> findAllnewScategories() {
		return CategoriesDao.findAllnewScategories();
	}

	@Override
	public List<Categories> findAllvideoScategories() {
		return CategoriesDao.findAllvideoScategories();
	}

	@Override
	public List<Categories> findAllpicsScategories() {
		return CategoriesDao.findAllpicsScategories();
	}

	@Override
	public List<Categories>  findAllcategories() {
		// TODO Auto-generated method stub
		return CategoriesDao.findAllcategories();
	}

	@Override
	public List<Categories> showVideoCate() {
		// TODO Auto-generated method stub
		return CategoriesDao.showVideoCate();
	}

	@Override
	public List<Categories> showPicsCate() {
		// TODO Auto-generated method stub
		return CategoriesDao.showPicsCate();
	}

	@Override
	public List<Categories> showNewsCate() {
		// TODO Auto-generated method stub
		return CategoriesDao.showNewsCate();
	}

	@Override
	public PageInfo<Categories> findPageInfo(Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		PageInfo<Categories> pi  = new PageInfo<Categories>();
		pi.setPageIndex(pageIndex);
		pi.setPageSize(pageSize);
		//获取总条数
		Integer totalCount  = CategoriesDao.totalCount();
		if (totalCount>0){
			pi.setTotalCount(totalCount);
			//每一页显示学生信息数
			//currentPage = (pageIndex-1)*pageSize  当前页码数减1*最大条数=开始行数
		List<Categories> categoriesList =CategoriesDao.getcategoriesList(
				     (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
		  pi.setList(categoriesList);
		}
		return pi;
	}

	@Override
	public Categories findCtype(String categoriesid) {
		// TODO Auto-generated method stub
		return CategoriesDao.findCtype(categoriesid);
	}

	@Override
	public int deleteCategories(String categoriesid) {
		// TODO Auto-generated method stub
		return CategoriesDao.deleteCategories(categoriesid);
	}


	
}
