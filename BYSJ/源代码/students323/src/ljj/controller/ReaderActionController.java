package ljj.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import ljj.pojo.BysjComment;
import ljj.pojo.BysjUser;
import ljj.pojo.News;
import ljj.pojo.Notice;
import ljj.pojo.PageInfo;
import ljj.pojo.Pics;
import ljj.pojo.ReaderAction;
import ljj.pojo.Video;
import ljj.service.NewsService;
import ljj.service.PicsService;
import ljj.service.ReaderActionService;
import ljj.service.VideoService;


@Controller

public class ReaderActionController {
	   @Autowired
	    private ReaderActionService readerActionService;
	   @Autowired
	    private NewsService newsService;
	   @Autowired
	    private VideoService videoService;
	   @Autowired
	    private PicsService picsService;
		
	   @RequestMapping(value="/CollectsZX",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject CollectsZX(@RequestBody Map<String, Object> map,HttpSession session,
	    		HttpServletResponse response){
		   String actor=(String)map.get("actor");  
	    	  String pageurl=(String)map.get("pageurl"); 
	    	  String pageIndextemp=(String)map.get("pageIndex"); 
	    	  Integer pageIndex=Integer.valueOf(pageIndextemp);
	    	  
		Integer pageSize= new Integer(200);
		  System.out.println("是否点赞收藏函数！");
		  System.out.println(actor);
    	  System.out.println(pageurl);
    	  System.out.println(pageIndextemp); 
    	  
    	  
    	  
    	  List<ReaderAction> readerActionList = (List<ReaderAction>) readerActionService.findAction(actor,pageurl);
	    	JSONObject data = new JSONObject();
	    	if(readerActionList.size()>0){
	    		 System.out.println("收藏数目");
	    		 System.out.println(String.valueOf(readerActionList.size()));
	    		ReaderAction readerActions = new ReaderAction();
	    		readerActions =readerActionList.get(0);
	    		System.out.println(readerActions.getCallzan());
	    		if(readerActions.getCallzan()!=null&&readerActions.getCallzan().equals("yes")){
	    			data.put("Zstatus","yes");
	    		}
	    		if(readerActions.getCollect()!=null&&readerActions.getCollect().equals("yes")){
	    			data.put("Cstatus","yes");
	    		}
	    	data.put("data",readerActionList);
	    	System.out.println(data);
	    	return data;
	    	  }
	    	  else{
	    		  data.put("status", "empty");
	    		  System.out.println(data);
	    		  return data; 
	    	  }
	    }


	   @RequestMapping(value="/returnhistoryitem",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject returnhistoryitem(String target){
			System.out.println(target);
	    	JSONObject data = new JSONObject();
	    		if(target.substring(0,1).equals("v")){
					Integer videoid=Integer.valueOf(target.substring(1,target.length()));
	        		Video video=videoService.findVideoById(videoid);	
	        		data.put("historyitem", video);
	        		 data.put("status", "ok");
	     	    	System.out.println(data);
	     	    	return data;
				}
	    		else if(target.substring(0,1).equals("p")){
					Integer picsid=Integer.valueOf(target.substring(1,target.length()));
					Pics pics=picsService.findPicsById(picsid);  
					data.put("historyitem",pics);
					 data.put("status", "ok");
				    	System.out.println(data);
				    	return data;
				}
	    		else if(target.substring(0,1).equals("n")){
	        		Integer newsid=Integer.valueOf(target.substring(1,target.length()));
	        		News news=newsService.findnewsById(newsid);
	        		data.put("historyitem", news);
	    	 data.put("status", "ok");
	    	System.out.println(data);
	    	return data;
	    	  }
	    	data.put("status", "empty");
	    	System.out.println(data);
	    	return data; 	    	  
}	   

	   @RequestMapping(value="/loadingMyCollects",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject loadingMyCollects(String nick,String pageIndextemp){
		   String actor=nick;
	    	  Integer pageIndex=Integer.valueOf(pageIndextemp);
		   System.out.println("加载我的收藏！！！！！！！！！！！！！！");
		   System.out.println(nick); 
		   Integer pageSize= new Integer(200);
		   PageInfo<ReaderAction> pi = readerActionService.findPageInfo(actor,pageIndex,pageSize);
	    	JSONObject data = new JSONObject();
	    	JSONObject datanews = new JSONObject();
	    	JSONObject datapics = new JSONObject();
	    	JSONObject datavideo = new JSONObject(); 
	    	if(pi.getTotalCount()>0){
	    	System.out.println("查找总数："+Integer.toString(pi.getTotalCount()));
	    	System.out.println("列表长度："+Integer.toString(pi.getList().size()));
	    	for (int i = 0; i <pi.getList().size(); i++) {
	    		String url=pi.getList().get(i).getPageurl();
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
	   
	   
	   @RequestMapping(value="/AddCollects",method=RequestMethod.POST)
	    @ResponseBody
	    public JSONObject  addCollects(  
	    		@RequestBody Map<String, Object> map,
	    		HttpSession session,
	            HttpServletResponse response) {
		   
		   		String actor=(String)map.get("actor");  
	    	  String pageurl=(String)map.get("pageurl"); 
	    	
	    	  System.out.println(actor);
	    	  System.out.println(pageurl);
	    	  System.out.println("插入收藏"); 

	    	  JSONObject data = new JSONObject();
	    	  List<ReaderAction> readerActionList = (List<ReaderAction>) readerActionService.findAction(actor,pageurl);
	    	  if(readerActionList.size()==0){
	    	  int a =readerActionService.addCollects(actor,pageurl);
	    	  System.out.println(String.valueOf(a));
	    	  }
	    	  else{
	    		  int b =readerActionService.updateCollects(actor,pageurl);
	    	  }
	        	data.put("result","ok");
	        	return data;

 }
	   
	   @RequestMapping(value="/Callzan",method=RequestMethod.POST)
	    @ResponseBody
	    public JSONObject  Callzan(  
	    		@RequestBody Map<String, Object> map,
	    		HttpSession session,
	            HttpServletResponse response) {
		   
		   		String actor=(String)map.get("actor");  
	    	  String pageurl=(String)map.get("pageurl"); 
	    	
	    	  System.out.println(actor);
	    	  System.out.println(pageurl);
	    	  System.out.println("插入点赞");
	    	  

	    	  JSONObject data = new JSONObject();
	    	  List<ReaderAction> readerActionList = (List<ReaderAction>) readerActionService.findAction(actor,pageurl);
	    	  if(readerActionList.size()==0){	
	    	  int a =readerActionService.insertZan(actor,pageurl);
	    	  System.out.println(String.valueOf(a));
	    	  }
	    	  else{
	    		  int b =readerActionService.updateZan(actor,pageurl);
	    	  }
	        	data.put("result","ok");
	        	return data;

 }
	   
	   @RequestMapping(value="/deleteCollects",method=RequestMethod.POST)
	    @ResponseBody
	    public JSONObject  deleteCollects(  
	    		@RequestBody Map<String, Object> map,
	    		HttpSession session,
	            HttpServletResponse response) {
		   
		   		String actor=(String)map.get("actor");  
	    	  String pageurl=(String)map.get("pageurl"); 
	    	
	    	  System.out.println(actor);
	    	  System.out.println(pageurl);
	    	  System.out.println("删除收藏");

	    	  JSONObject data = new JSONObject();
	    		int b =readerActionService.deleteCollects(actor,pageurl);
	        	data.put("result","ok");
	        	return data;
	        	
	        	
}
	   
	   @RequestMapping(value="/deleteZan",method=RequestMethod.POST)
	    @ResponseBody
	    public JSONObject  deleteZan(  
	    		@RequestBody Map<String, Object> map,
	    		HttpSession session,
	            HttpServletResponse response) {
		   		String actor=(String)map.get("actor");  
	    	  String pageurl=(String)map.get("pageurl"); 
	    	
	    	  System.out.println(actor);
	    	  System.out.println(pageurl);
	    	  System.out.println("删除收藏");

	    	  JSONObject data = new JSONObject();
	    		int b =readerActionService.deleteZan(actor,pageurl);
	        	data.put("result","ok");
	        	return data;

}
    	
	   
    }