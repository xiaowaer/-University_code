package ljj.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
import ljj.pojo.PageInfo;
import ljj.pojo.Pics;
import ljj.pojo.Reader;
import ljj.pojo.Video;
import ljj.service.BysjCommentService;
import ljj.service.NewsService;
import ljj.service.PicsService;
import ljj.service.VideoService;



@Controller
public class BysjCommentController {
	
	   @Autowired
	    private BysjCommentService bysjCommentService;
	   @Autowired
	    private NewsService newsService;
	   @Autowired
	    private VideoService videoService;
	   @Autowired
	    private PicsService picsService;
	   	
		@RequestMapping(value = "/findContent")
	    @ResponseBody
		public JSONObject findComment( String targeturl) {
			System.out.println("查找评论对应内容");
			System.out.println(targeturl);
			JSONObject data = new JSONObject();
			if(targeturl.substring(0,1).equals("n")){
				System.out.println("查找评论对应新闻");
        		Integer newsid=Integer.valueOf(targeturl.substring(1,targeturl.length()));
        		News news=newsService.findnewsById(newsid);
        		data.put("type", "n");
        		data.put("data", news);
        	}
			else if (targeturl.substring(0,1).equals("v")){
				System.out.println("查找评论对应视频");
				Integer videoid=Integer.valueOf(targeturl.substring(1,targeturl.length()));
        		Video video=videoService.findVideoById(videoid);	
        		data.put("type", "v");
        		data.put("data", video);
			}
			else if(targeturl.substring(0,1).equals("p")){
				System.out.println("查找评论对应图册");
				Integer picsid=Integer.valueOf(targeturl.substring(1,targeturl.length()));
				Pics pics=picsService.findPicsById(picsid);  
				data.put("type", "p");
        		data.put("data", pics);
			}
			else{
				data.put("status", "false");
			}
				System.out.println(data);
				return data;
		}
		
	   
	   

		/**
		 * 分页查询
		 * pageIndex 当前页码
		 * pageSize  显示条数
		 * @throws ParseException 
		 */
	    
	   
	   
		@RequestMapping(value = "/findComment")
		public String  findComment( String target, String commenter,String cmcontent,Integer pageIndex, Integer pageSize,Model model) {
			System.out.println("查找评论列表");
				PageInfo<BysjComment> pi = bysjCommentService.findPageInfo(target,commenter,cmcontent,pageIndex,pageSize);
				System.out.println(pi.toString());
				model.addAttribute("pi",pi);
				return "commentlist";
		}
		
	   
	   @RequestMapping(value="/insertcomment",method=RequestMethod.POST)
	    @ResponseBody
	    public JSONObject  insertComment(  
	    		@RequestBody Map<String, Object> map,
	    		HttpSession session,
	            HttpServletResponse response) throws ParseException{
	    	  System.out.println("请求JSON数据！！！");
	    	  
	    	  String useridtemp=(String)map.get("userid");  
	    	  String target=(String)map.get("target"); 
	    	  String cmcontent=(String)map.get("cmcontent"); 
	    	  String cmhead=(String)map.get("cmhead"); 
	    	  String commenter=(String)map.get("commenter"); 
	    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	  String nowTime = sdf.format(new Date());
	    	  Date cmtime = sdf.parse( nowTime );
	    	  System.out.println(cmtime);
	    	  System.out.println(nowTime);
 
	    	  System.out.println(useridtemp);
	    	  System.out.println(target);
	    	  System.out.println(cmcontent);
	    	  System.out.println(commenter);
	    	  System.out.println(cmhead);
	    	  
	    	  Integer userid = Integer.valueOf(useridtemp);
	   
	    	  
	    	  JSONObject data = new JSONObject();
	         bysjCommentService.insertComment(userid,target,cmcontent,cmtime,commenter,cmhead);
	        	data.put("result","ok");
	        	return data;

    }
	   
	   @RequestMapping(value="/insertreply",method=RequestMethod.POST)
	    @ResponseBody
	    public JSONObject  insertReply(  
	    		@RequestBody Map<String, Object> map,
	    		HttpSession session,
	            HttpServletResponse response) throws ParseException{
	    	  System.out.println("请求JSON数据！！！");
	    	  
	    	  String useridtemp=(String)map.get("userid");  
	    	  String parenttemp=(String)map.get("parent"); 
	    	  String cmcontent=(String)map.get("cmcontent"); 
	    	  String cmhead=(String)map.get("cmhead"); 
	    	  String commenter=(String)map.get("commenter"); 
	    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	  String nowTime = sdf.format(new Date());
	    	  Date cmtime = sdf.parse( nowTime );
	    	  System.out.println(cmtime);
	    	  System.out.println(nowTime);
	    	  System.out.println(useridtemp);
	    	  System.out.println(parenttemp);
	    	  System.out.println(cmcontent);
	    	  System.out.println(commenter);
	    	  System.out.println(cmhead);    	  
	    	  Integer userid = Integer.valueOf(useridtemp);
	    	  Integer parent = Integer.valueOf(parenttemp);
	    	  
	    	  JSONObject data = new JSONObject();
	         bysjCommentService.insertReply(userid,parent,cmcontent,cmtime,commenter,cmhead);
	        	data.put("result","ok");
	        	return data;

   }
	   
	   

	   @RequestMapping(value="/loadingCommentfortarget",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject loadingCommentfortarget(String target){
		   
		   System.out.println("请求评论数据！！！");
		   System.out.println(target);
		   System.out.println("^");
	    	List<BysjComment> bysjComments= (List<BysjComment>)bysjCommentService.loadingCommentfortarget(target);
	    	System.out.println("符合条件的条目"+bysjComments.size());
	    	JSONObject data = new JSONObject();
	    	if(bysjComments.size()>0){
	    	data.put("data",bysjComments);
	    	data.put("status","ok");
//	    	  return newspageService.findAllnewpape(cid);
	    	return data;
	    	}
	    	data.put("status","false");
	    	return data;
	    }
	   
	   @RequestMapping(value="/loadingMyReply",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject loadingMyReply(String nick){
		   System.out.println("请求评论数据！！！");
		   System.out.println(nick);
	    	List<BysjComment> bysjComments= (List<BysjComment>)bysjCommentService.loadingMyReply(nick);
	    	System.out.println("符合条件的条目"+bysjComments.size());
	    	JSONObject data = new JSONObject();
	    	if(bysjComments.size()>0){
	    	data.put("data",bysjComments);
	    	data.put("status","ok");
	    	return data;
	    	}
	    	data.put("status","false");
	    	return data;
	    }
	   
	   @RequestMapping(value="/deleteMyReply",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject deleteMyReply(String commentid){
		   System.out.println("删除回复！！！");
		   System.out.println(commentid);
		   Integer Icommentid = Integer.valueOf(commentid);
		 
	    	int a= bysjCommentService.deleteMyReply(Icommentid);
	    	 System.out.println(String.valueOf(a));
	    	JSONObject data = new JSONObject();
	    	if(a>0){
	    	data.put("status","ok");
	    	return data;
	    	}
	    	data.put("status","false");
	    	return data;
	    }
  
	   
	   @RequestMapping(value="/loadingMyComment",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject loadingMyComment(String nick){
		   System.out.println("请求评论数据！！！");
		   System.out.println(nick);
	    	List<BysjComment> bysjComments= (List<BysjComment>)bysjCommentService.loadingMyComment(nick);
	    	System.out.println("符合条件的条目"+bysjComments.size());
	    	JSONObject data = new JSONObject();
	    	if(bysjComments.size()>0){
	    	data.put("data",bysjComments);
	    	data.put("status","ok");
	    	return data;
	    	}
	    	data.put("status","false");
	    	return data;
	    }
   
	   
	   @RequestMapping(value="/deleteMyComment",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject deleteMyComment(String commentid){
		   System.out.println("删除评论！！！");
		   System.out.println(commentid);
		   Integer Icommentid = Integer.valueOf(commentid);
		   
	    	int a= bysjCommentService.deleteMyComment(Icommentid);
	    	 System.out.println(String.valueOf(a));
	    	JSONObject data = new JSONObject();
	    	if(a>0){
	    	data.put("status","ok");
	    	return data;
	    	}
	    	data.put("status","false");
	    	return data;
	    }
   
    
	   @RequestMapping(value="/loadingReplyforparent",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject loadingReplyforparent(String parent){
		   
		   System.out.println("请求评论数据！！！");
		   System.out.println(parent);
		   
		   Integer Iparent = Integer.valueOf(parent);
	    	List<BysjComment> bysjComments= (List<BysjComment>)bysjCommentService.loadingReplyforparent(Iparent);
	    	System.out.println("符合条件的条目"+bysjComments.size());
	    	JSONObject data = new JSONObject();
	    	if(bysjComments.size()>0){
	    	data.put("data",bysjComments);
	    	data.put("status","ok");
//	    	  return newspageService.findAllnewpape(cid);
	    	return data;
	    	}
	    	data.put("status","false");
	    	return data;
	    }
	   
	   
   
 
    
    
    
    
    
}