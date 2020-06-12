package ljj.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import ljj.pojo.PageInfo;
import ljj.pojo.Reader;
import ljj.pojo.ReaderType;
import ljj.pojo.BysjUser;
import ljj.pojo.Reader;
import ljj.service.BysjCommentService;
import ljj.service.BysjUserService;
import ljj.service.ReaderActionService;
import ljj.service.ReaderService;
import ljj.service.ReaderTypeService;
import ljj.util.FTPUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;


@Controller
public class ReaderController {
	
	   @Autowired
	    private ReaderService readerService;
	   @Autowired
	    private  BysjUserService  bysjuserService;
	   @Autowired
	    private  ReaderTypeService  readertypeService;
	   @Autowired
	    private BysjCommentService bysjCommentService;
	   @Autowired
	    private ReaderActionService readerActionService;
	   

		/**
		 * 修改视频信息
		 */
		  @RequestMapping(value="/updateReadertype",method=RequestMethod.POST)
		    @ResponseBody
		    public JSONObject  updateVideo(  
		    		@RequestBody Map<String, Object> map,
		    		HttpSession session,
		            HttpServletResponse response) throws ParseException{

		    	  String readeridtemp=(String)map.get("readerid");  
		    	  String readertype=(String)map.get("readertype"); 
		    	  String level=(String)map.get("level"); 
		    	  String priority=(String)map.get("priority");
		    	  Integer readerid=Integer.valueOf(readeridtemp);
		    	 
		    	  System.out.println(readertype);
		    	  System.out.println(level);
		    	  System.out.println(priority); 
		    	  ReaderType readerTypeItem =new ReaderType();
		    	  readerTypeItem.setLevel(level);
		    	  readerTypeItem.setPriority(priority);
		    	  readerTypeItem.setReaderid(readerid);
		    	  readerTypeItem.setReadertype(readertype);
		    	  JSONObject data = new JSONObject();
		    	  int a =readertypeService.updateReaderType(readerTypeItem);
		    	  if(a>0){
		    	  System.out.println(String.valueOf(a));
		        	data.put("result","ok");
		        	return data;
		    	  }
		    	  data.put("result","false");
		    	  return data;
	  }
		  
		@RequestMapping(value = "/settingReaderType")
		public String settingReaderType( String readerid,Model model,HttpSession session) {
			Integer readertypeidI=Integer.valueOf(readerid);
		      ReaderType readertype =readertypeService.findReadtypeById(readertypeidI);
		      System.out.println(readertype.toString());
		      session.setAttribute("readertype",readertype);
		        return "editreadertype";
		}
		
	   
		@RequestMapping(value = "/findReader")
		public String findReader( String nick,String sex,Integer pageIndex, Integer pageSize,Model model) {
			PageInfo<Reader> pi = readerService.findPageInfo(nick,sex,pageIndex,pageSize);
				model.addAttribute("pi",pi);
				model.addAttribute("nick",nick);
				return "readerlist";
		}
		
  
		  @RequestMapping( "/findReaderById")
		    public String findnewsById(Integer readerid,HttpSession session,Model model) {
			   System.out.println(String.valueOf(readerid));
		        Reader reader= readerService.findreaderById(readerid);
		        String tempnick=reader.getNick();
		        BysjUser user=	bysjuserService.finduserBynick(tempnick);
		        model.addAttribute("reader",reader);		        
		        model.addAttribute("user",user);
		        System.out.println(reader.toString());
		        System.out.println(user.toString());
		        return "reader_edit";
		    }
		  
		   @RequestMapping(value="/deleteReader",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
		    @ResponseBody
		    public JSONObject deletenews(@RequestBody Map<String, Object> map){
			   String readerid=(String)map.get("readerid");  
			   System.out.println("删除读者！！！");
			   System.out.println(readerid);
			   Integer Ireaderid = Integer.parseInt(readerid);
			   Reader reader=readerService.findreaderById(Ireaderid);
		    	int a= readerService.deletereader(Ireaderid);
		    	int b=bysjCommentService.deleteCommentByreader(Ireaderid);
		    	int c= readerActionService.deleteActionByreader(reader.getNick());
		    	int d= bysjuserService.deleteUser(reader.getNick());
		    	 System.out.println(String.valueOf(a));
		    	JSONObject data = new JSONObject();
		    	if(a>0){
		    	data.put("status","ok");
		    	return data;
		    	}
		    	data.put("status","false");
		    	return data;
		    }
		   
	   
	   
    @RequestMapping(value="/showreaderinfo",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject findreaderbynick(String nick){
    	List<Reader> Newspage= (List<Reader>)readerService.findreaderbynick(nick);
    	JSONObject data = new JSONObject();
    	  if(Newspage.size()>0){
    	data.put("data",Newspage);
    	 data.put("status", "ok");
    	System.out.println(data);
    	//return newspageService.findAllnewpape(cid);
    	return data;
    	  }
    	  else{
    		  data.put("status", "empty");
    		  System.out.println(data);
    		  return data; 
    	  }
    }
    
      
    @RequestMapping(value="/updatereaderinfo",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject  updatereaderinfo(@RequestBody Map<String, Object> map, HttpSession session,
            HttpServletResponse response){

    	  String nick=(String)map.get("nick");
    	  String sex=(String)map.get("sex");
    	  String intro=(String)map.get("intro");
    	  String work=(String)map.get("work");
    	  String telephone=(String)map.get("phone");
    	  String likeread=(String)map.get("likeread");
    	  String birthday=(String)map.get("birthday");
    	  String city=(String)map.get("city");
    	  
    	  System.out.println("请求JSON数据！！！");
    	  
    	  
    	  Reader reader=new Reader();
    	  reader.setNick(nick);
    	  reader.setBirthday(birthday);
    	  reader.setCity(city);
    	  reader.setIntro(intro);
    	  reader.setLikeread(likeread);
    	  reader.setSex(sex);
    	  reader.setTelephone(telephone);
    	  reader.setWork(work);
    	  
    	  System.out.println(reader.toString());
    	  
    	  
    	  
    	 int a=  readerService.updatereaderinfo(reader); 
    	  
    	  JSONObject data = new JSONObject();

    	  if(a>0){
    	    	 data.put("status", "ok");
    	    	System.out.println(data);
    	    	return data;
    	    	  }
    	    	  else{
    	    		  data.put("status", "false");
    	    		  System.out.println(data);
    	    		  return data; 
    	    	  }
    	    }
    
    
 
     
	
}
