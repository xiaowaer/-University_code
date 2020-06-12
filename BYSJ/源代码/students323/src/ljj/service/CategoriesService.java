package ljj.service;

import java.util.List;

import ljj.pojo.Categories;
import ljj.pojo.PageInfo;

public interface CategoriesService {
	public List<Categories> findAllnewScategories();
	public List<Categories> findAllvideoScategories();
	public List<Categories> findAllpicsScategories();
	public List<Categories> findAllcategories();
	public List<Categories> showVideoCate();
	public List<Categories> showPicsCate();
	public List<Categories> showNewsCate();
	public PageInfo<Categories> findPageInfo(Integer pageIndex, Integer pageSize);
	public Categories findCtype(String categoriesid);
	public int deleteCategories(String categoriesid);





}
