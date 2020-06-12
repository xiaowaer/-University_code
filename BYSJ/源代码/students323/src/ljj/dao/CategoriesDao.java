package ljj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ljj.pojo.Categories;

public interface CategoriesDao {
	public  List<Categories> findAllnewScategories();

	public List<Categories> findAllvideoScategories();

	public List<Categories> findAllpicsScategories();

	public List<Categories> findAllcategories();

	public List<Categories> showVideoCate();

	public List<Categories> showPicsCate();

	public List<Categories> showNewsCate();

	public Integer totalCount();

	public List<Categories> getcategoriesList(@Param("currentPage")Integer currentPage, @Param("pageSize")Integer pageSize);

	public Categories findCtype(@Param("cid")String cid);

	public int deleteCategories(@Param("cid")String cid);

}
