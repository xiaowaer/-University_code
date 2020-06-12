

package ljj.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.management.NotificationEmitter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import ljj.pojo.BysjUser;
import ljj.pojo.News;
import ljj.pojo.Notice;
import ljj.pojo.PageInfo;
import ljj.pojo.Pics;
import ljj.pojo.ReaderAction;
import ljj.pojo.ReaderType;
import ljj.pojo.Recommend;
import ljj.pojo.Video;
import ljj.service.BysjUserService;
import ljj.service.NewsService;
import ljj.service.PicsService;
import ljj.service.ReaderTypeService;
import ljj.service.RecommendService;
import ljj.service.VideoService;



@Controller
public class RecommendController  {

   @Autowired
    private RecommendService recommendService;
    @Autowired
    private ReaderTypeService readerTypeService;
    @Autowired
    private BysjUserService bysjuserService;
    @Autowired
    private NewsService newsService;
   @Autowired
    private VideoService videoService;
   @Autowired
    private PicsService picsService;
    
    @RequestMapping(value="/loadingMyRecommend",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject loadingMyCollects(String readerid,String pageIndextemp){
    	Integer readeridI=Integer.valueOf(readerid);	
	   ReaderType readerType=readerTypeService.findReadtypeById(readeridI);
	   	String reusertype=readerType.getReadertype();
	   Integer pageIndex=Integer.valueOf(pageIndextemp);
	   System.out.println("加载我的收藏！！！！！！！！！！！！！！");
	   Integer pageSize= new Integer(15);
	   String re_intro="";
		PageInfo<Recommend> pi = recommendService.findPageInfo(re_intro,reusertype, pageIndex,pageSize);
    	JSONObject data = new JSONObject();
    	JSONObject datanews = new JSONObject();
    	JSONObject datapics = new JSONObject();
    	JSONObject datavideo = new JSONObject(); 
    	if(pi.getTotalCount()>0){
    	System.out.println(Integer.toString(pi.getTotalCount()));
    	for (int i = 0; i <pi.getTotalCount(); i++) {
    		String url=pi.getList().get(i).getRe_url();
        	System.out.println(url);
        	if(url.substring(0,1).equals("n")){
        		Integer newsid=Integer.valueOf(url.substring(1,url.length()));
        		News news=newsService.findnewsById(newsid);
        		datanews.put("news"+String.valueOf(i), news);
        	}
			if(url.substring(0,1).equals("v")){
				Integer videoid=Integer.valueOf(url.substring(1,url.length()));
        		Video video=videoService.findVideoById(videoid);	
        		datavideo.put("video"+String.valueOf(i), video);
			}
			if(url.substring(0,1).equals("p")){
				Integer picsid=Integer.valueOf(url.substring(1,url.length()));
				Pics pics=picsService.findPicsById(picsid);  
				datapics.put("pics"+String.valueOf(i),pics);
			}
			
		} 
    	 data.put("datanews",datanews);
    	 data.put("datapics",datapics);
    	 data.put("datavideo",datavideo);
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
    
    @RequestMapping(value="/deleteRecommend",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject deleteRecommend(@RequestBody Map<String, Object> map){
	   String recommendid=(String)map.get("recommendid");  
	   System.out.println("删除推荐！！！");
	   System.out.println(recommendid);
	   Integer Irecommendid = Integer.parseInt(recommendid);
    	int a= recommendService.deleterecommend(Irecommendid);
    	 System.out.println(String.valueOf(a));
    	JSONObject data = new JSONObject();
    	if(a>0){
    	data.put("status","ok");
    	return data;
    	}
    	data.put("status","false");
    	return data;
    }
    
    
	@RequestMapping(value = "/findRecommend")
	public String findRecommend( String re_intro,String reusertype,Integer pageIndex, Integer pageSize,Model model) {
		System.out.println("推荐列表");
		PageInfo<Recommend> pi = recommendService.findPageInfo(re_intro,reusertype,pageIndex,pageSize);
			model.addAttribute("pi",pi);
			return "recommendlist";
	}
	
    
	@RequestMapping(value = "/settingRecommend")
	public String findRecommend( String targeturl,Model model) {
		System.out.println("跳转推荐页面");
		System.out.println(targeturl);
		List<ReaderType> readerTypes= (List<ReaderType>)readerTypeService.showReaderType();
		System.out.println(readerTypes.toString());
			model.addAttribute("readertype",readerTypes);
			model.addAttribute("targeturl",targeturl);
			Recommend recommend=recommendService.findRecommendBytargeturl(targeturl);
			model.addAttribute("recommend",recommend);
			return "settingrecommend";
	}
   
	  @RequestMapping(value="/updateRecommend",method=RequestMethod.POST)
	    @ResponseBody
	    public JSONObject updateRecommend(  
	    		@RequestBody Map<String, Object> map,
	    		HttpSession session,
	            HttpServletResponse response) throws ParseException{
	    	  String rusertype=(String)map.get("readertype");  
	    	  String re_intro=(String)map.get("re_intro"); 
	    	  String re_url=(String)map.get("targeturl"); 
	    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	  String nowTime = sdf.format(new Date());
	    	  Date recommendtime = sdf.parse( nowTime ); 
	    	  System.out.println(rusertype);
	    	  System.out.println(nowTime);
	    	  System.out.println(re_intro);
	    	  JSONObject data = new JSONObject();
	    	  Recommend re=recommendService.findRecommendBytargeturl(re_url);
	    	  if(re==null){
	    		  System.out.println("插入推荐");
	    		  Recommend recommend =new Recommend();	
	    		  recommend.setRe_intro(re_intro);
	    		  recommend.setRe_url(re_url);
	    		  recommend.setRecommendtime(recommendtime);
	    		  recommend.setRusertype(rusertype);
	    		  int a=recommendService.insertRecommend(recommend);
	    		  data.put("result","ok");
	    		System.out.println(a);
		          return data;
	    	  }else{
	    		  System.out.println("修改推荐");
	    		  Recommend recommend =new Recommend();	
	    		  recommend.setRe_intro(re_intro);
	    		  recommend.setRe_url(re_url);
	    		  recommend.setRecommendtime(recommendtime);
	    		  recommend.setRusertype(rusertype);
	    		  int a=recommendService.updateRecommend(recommend);
	    		  System.out.println(a);
	    		  if(a>0){
		        	data.put("result","ok");
		        	 System.out.println(a);
		         	return data;
	    		  }
	    		  data.put("result","false");
		    	  return data;
	    	  }	    	  
  }
	  
	/*  @RequestMapping(value="/showRecommends",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject shownRecommends(){
	    	
		  List<Recommend> RecommendList= (List<Recommend>)RecommendService.findAllRecommends();
	    	JSONObject data = new JSONObject();
	    	  if(RecommendList.size()>0){
	    	data.put("data",RecommendList);
	    	data.put("img", "https://xiaoliwaer.top:525/headerimg/ljj.jpg");
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
	
 	*/


}
