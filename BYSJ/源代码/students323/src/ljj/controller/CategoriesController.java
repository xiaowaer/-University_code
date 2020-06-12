package ljj.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import ljj.pojo.Categories;
import ljj.pojo.News;
import ljj.pojo.PageInfo;
import ljj.pojo.Pics;
import ljj.pojo.Video;
import ljj.service.BysjCommentService;
import ljj.service.CategoriesService;
import ljj.service.NewsService;
import ljj.service.PicsService;
import ljj.service.ReaderActionService;
import ljj.service.RecommendService;
import ljj.service.VideoService;

@Controller
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private BysjCommentService bysjCommentService;
   @Autowired
    private ReaderActionService readerActionService;
   @Autowired
    private RecommendService recommendService;
   @Autowired
   private NewsService newsService;
   @Autowired
   private PicsService picsService;
   
   

	@RequestMapping(value = "/findCategories")
	public String findnews( Integer pageIndex, Integer pageSize,Model model) {
		System.out.println("查找频道列表");
			PageInfo<Categories> pi = categoriesService.findPageInfo(pageIndex,pageSize);
			model.addAttribute("pi",pi);

			return "categorieslist";
	}
    
    @RequestMapping(value="/showVideoCate",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject showVideoCate(){
    	
	  List<Categories> CategoriesList= (List<Categories>)categoriesService.showVideoCate();
    	JSONObject data = new JSONObject();
    	  if( CategoriesList.size()>0){
    	data.put("data",CategoriesList);
    	 data.put("status", "ok");
    	System.out.println(data);
    	return data;
    	  }
    	  else{
    		  data.put("status", "empty");
    		  System.out.println(data);
    		  return data; 
    	  }
    }
   
    @RequestMapping(value="/selectnewscategories",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject findAllnewScategories(){
    	List<Categories> CategoriesList= (List<Categories>)categoriesService.findAllnewScategories();
    	JSONObject data = new JSONObject();  	
      	data.put("data",CategoriesList);     	
      	return data;
    }
    
    @RequestMapping(value="/selectnewscategories1",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject findAllnewScategories1(){
    	List<Categories> CategoriesList= (List<Categories>)categoriesService.findAllnewScategories();
    	JSONObject data = new JSONObject(); 
    	JSONObject data1 = new JSONObject(); 
    	 if(CategoriesList.size()>0){
    	 Categories[] Categoriesall = new Categories[CategoriesList.size()];
    	 CategoriesList.toArray(Categoriesall); 
    	 String datastr="";
    	 String datastrtemp="";
    	 for (int i = 0; i < Categoriesall.length; i++) {
    		 String id=String.valueOf(Categoriesall[i].getCid());
    		 String name=Categoriesall[i].getChname();
    		data1.put(id, name);   
    	 	}
    
    	
    	 data.put("data",data1);  
    	 data.put("status","ok");
    	 System.out.println( datastrtemp);
	
		}
    	
      	   	
      	return data;
    }
    
    @RequestMapping(value="/selectnewscategories2",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject findAllnewScategories2(){
    	List<Categories> CategoriesList= (List<Categories>)categoriesService.findAllvideoScategories();
    	JSONObject data = new JSONObject(); 
    	JSONObject data1 = new JSONObject(); 
    	 if(CategoriesList.size()>0){
    	 Categories[] Categoriesall = new Categories[CategoriesList.size()];
    	 CategoriesList.toArray(Categoriesall); 
    	 String datastr="";
    	 String datastrtemp="";
    	 for (int i = 0; i < Categoriesall.length; i++) {
    		 String id=String.valueOf(Categoriesall[i].getCid());
    		 String name=Categoriesall[i].getChname();
    		data1.put(id, name);   
    	 	}
    
    	
    	 data.put("data",data1);  
    	 data.put("status","ok");
    	 System.out.println( datastrtemp);
	
		}
    	
      	   	
      	return data;
    }
    
    @RequestMapping(value="/selectnewscategoriespics",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject findAllnewScategoriespics(){
    	List<Categories> CategoriesList= (List<Categories>)categoriesService.findAllpicsScategories();
    	JSONObject data = new JSONObject(); 
    	JSONObject data1 = new JSONObject(); 
    	 if(CategoriesList.size()>0){
    	 Categories[] Categoriesall = new Categories[CategoriesList.size()];
    	 CategoriesList.toArray(Categoriesall); 
    	 String datastr="";
    	 String datastrtemp="";
    	 for (int i = 0; i < Categoriesall.length; i++) {
    		 String id=String.valueOf(Categoriesall[i].getCid());
    		 String name=Categoriesall[i].getChname();
    		data1.put(id, name);   
    	 	}
    
    	
    	 data.put("data",data1);  
    	 data.put("status","ok");
    	 System.out.println( datastrtemp);
	
		}
    	
      	   	
      	return data;
    }
    
    @RequestMapping(value="/findAllcategories",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject findAllcategories(){
    	List<Categories> CategoriesList= (List<Categories>)categoriesService.findAllcategories();
    	JSONObject data = new JSONObject(); 
    	JSONObject data1 = new JSONObject(); 
    	 if(CategoriesList.size()>0){
    	 Categories[] Categoriesall = new Categories[CategoriesList.size()];
    	 CategoriesList.toArray(Categoriesall); 
    	 String datastr="";
    	 String datastrtemp="";
    	 for (int i = 0; i < Categoriesall.length; i++) {
    		 String id=String.valueOf(Categoriesall[i].getCid());
    		 String name=Categoriesall[i].getChname();
    		data1.put(id, name);   
    	 	}
    
    	
    	 data.put("data",data1);  
    	 data.put("status","ok");
    	 System.out.println( datastrtemp);
	
		}
      	   	
      	return data;
    }
    
    @RequestMapping(value="/deletecategories",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject deletecategories(String categoriesid){
	   
	   System.out.println("删除频道！！！");
	   System.out.println(categoriesid);
	   Categories categories=categoriesService.findCtype(categoriesid);
	   JSONObject data = new JSONObject();
	   if(categories.getOrigin().equals("hksp")){
		   PageInfo<Video> pi =videoService.findPageInfo(null, categoriesid, null, null);
		   for (int i = 0; i < pi.getList().size(); i++) {
		    int b=bysjCommentService.deleteCommentBytarget(pi.getList().get(i).getTargeturl());
			int c=readerActionService.deleteActionBytarget(pi.getList().get(i).getTargeturl());
			int d=recommendService.deleteRecommendBytarget(pi.getList().get(i).getTargeturl());
			int a=videoService.deleteVideo(pi.getList().get(i).getVideoid());
			int e=categoriesService.deleteCategories(categoriesid);
		}
		   data.put("status","ok");
	    	return data;
	   }
	   if(categories.getOrigin().equals("txnews")){
		   PageInfo<News> pi =newsService.findPageInfo(null,null, categoriesid, null, null);
		   for (int i = 0; i < pi.getList().size(); i++) {
		    int b=bysjCommentService.deleteCommentBytarget(pi.getList().get(i).getTargeturl());
			int c=readerActionService.deleteActionBytarget(pi.getList().get(i).getTargeturl());
			int d=recommendService.deleteRecommendBytarget(pi.getList().get(i).getTargeturl());
			int a=newsService.deletenews(pi.getList().get(i).getNewsid());
			int e=categoriesService.deleteCategories(categoriesid);
		}
		   data.put("status","ok");
	    	return data;
	   }
	   if(categories.getOrigin().equals("xltp")){
		   PageInfo<Pics> pi =picsService.findPageInfo(null,null, categoriesid, null, null);
		   for (int i = 0; i < pi.getList().size(); i++) {
		    int b=bysjCommentService.deleteCommentBytarget(pi.getList().get(i).getTargeturl());
			int c=readerActionService.deleteActionBytarget(pi.getList().get(i).getTargeturl());
			int d=recommendService.deleteRecommendBytarget(pi.getList().get(i).getTargeturl());
			int a=picsService.deletePics(pi.getList().get(i).getPicsid());
			int e=categoriesService.deleteCategories(categoriesid);
		}
		   data.put("status","ok");
	    	return data;
	   }
    	data.put("status","false");
    	return data;
    }
   
    
        
}
