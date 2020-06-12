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

import ljj.pojo.Notice;
import ljj.pojo.PageInfo;
import ljj.pojo.Reader;
import ljj.service.NoticeService;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
   
	@RequestMapping(value = "/findNotice")
	public String findNotice( String publisher,String noticetitle,Integer pageIndex, Integer pageSize,Model model) {
			PageInfo<Notice> pi = noticeService.findPageInfo(publisher,noticetitle,pageIndex,pageSize);
			model.addAttribute("pi",pi);
			return "noticelist";
	}
	
	/**
	 * 添加通知信息
	 */
	
	  @RequestMapping(value="/addNotice",method=RequestMethod.POST)
	    @ResponseBody
	    public JSONObject  addNotice(  
	    		@RequestBody Map<String, Object> map,
	    		HttpSession session,
	            HttpServletResponse response) throws ParseException{
	
	    	  String publisher=(String)map.get("publisher");  
	    	  String noticetype=(String)map.get("noticetype"); 
	    	  String noticecontent=(String)map.get("noticecontent"); 
	    	  String noticetitle=(String)map.get("noticetitle"); 
	    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	  String nowTime = sdf.format(new Date());
	    	  Date publishtime = sdf.parse( nowTime );
	    	  System.out.println(publishtime);
	    	  System.out.println(nowTime);
	    	  System.out.println(noticetype);
	    	  System.out.println(noticecontent); 
	    	  Notice notice =new Notice();
	    	  notice.setNoticecontent(noticecontent);
	    	  notice.setNoticetitle(noticetitle);
	    	  notice.setNoticetype(noticetype);
	    	  notice.setPublisher(publisher);
	    	  notice.setNoticecontent(noticecontent);
	    	  notice.setPublishtime(publishtime);
	    	  
	    	  JSONObject data = new JSONObject();
	    	  int a =noticeService.addNotice(notice);
	    	  System.out.println(String.valueOf(a));
	        	data.put("result","ok");
	        	return data;

  }
	  
	  @RequestMapping(value="/shownotices",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject shownNotices(){
	    	
		  List<Notice> NoticeList= (List<Notice>)noticeService.findAllnotices();
	    	JSONObject data = new JSONObject();
	    	  if(NoticeList.size()>0){
	    	data.put("data",NoticeList);
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
	
 	


}
