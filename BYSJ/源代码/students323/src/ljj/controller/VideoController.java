package ljj.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Base64.Decoder;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;

import ljj.pojo.Categories;
import ljj.pojo.PageInfo;
import ljj.pojo.Video;
import ljj.service.BysjCommentService;
import ljj.service.CategoriesService;
import ljj.service.ReaderActionService;
import ljj.service.RecommendService;
import ljj.service.VideoService;
import ljj.util.FTPUtil;
import ljj.util.URLAvailability;





@Controller

public class VideoController {
	   @Autowired
	    private VideoService videoService;
	   @Autowired
	    private CategoriesService categoriesService;
	   @Autowired
	    private BysjCommentService bysjCommentService;
	   @Autowired
	    private ReaderActionService readerActionService;
	   @Autowired
	    private RecommendService recommendService;
	   
	   
	   //插入视频界面
	   @RequestMapping( "/insertVideoIndex")
	    public String insertvideoIndex(Model model) {
		   List<Categories> vcate= (List<Categories>)categoriesService.showVideoCate();
	        model.addAttribute("vcate",vcate);
	        return "insertvideos";
	    }
	   
		@RequestMapping(value = "/testurl",method=RequestMethod.POST)
	    @ResponseBody
		public JSONObject testurl( @RequestBody Map<String, Object> map) {
			String videooriginurl=(String)map.get("videooriginurl");  
			 JSONObject data = new JSONObject();
			 System.out.println("url：" +  videooriginurl);
			URLAvailability u=new URLAvailability();
			  u.isConnect(videooriginurl);
       	   if(u.state==200){
       		   data.put("result", "ok");
       		   data.put("url", videooriginurl);
       		   return data;
       	   } 
       	   		data.put("result", "false");
       	   	   return data;
		}
	   
		   @RequestMapping(value="/selectvideopapebypage",produces = {"application/json;charset=UTF-8"})
		    @ResponseBody
			public JSONObject selectvideobypage( String videoname,String cid,String page, Integer pageSize) {
			   		Integer	pageIndex=Integer.parseInt(page);
			   		pageSize=20;
			   		JSONObject data = new JSONObject();
					PageInfo<Video> pi = videoService.findPageInfo(videoname,cid,pageIndex,pageSize);
					if(pi.getList().size()>0){
						data.put("data", pi);
						data.put("status", "ok");
						return data;
					}
					 else{
			    		  data.put("status", "empty");
			    		  System.out.println(data);
			    		  return data; 
			    	  }
			}
		   

			//插入视频
			
			 @RequestMapping(value="/insertvideo",method=RequestMethod.POST)
			    @ResponseBody
			    public JSONObject insertvideo(  
			    	@RequestBody Map<String, Object> map) throws ParseException {
				 	  String videoname=(String)map.get("videoname");  
			    	  String videokeyword=(String)map.get("videokeyword"); 
			    	  String cid=(String)map.get("cid"); 
			    	  String videoheadurl=(String)map.get("videoheadurl");
			    	  System.out.println(videoname);
			    	  System.out.println(videokeyword);
			    	  System.out.println(cid);
			    	  System.out.println(videoheadurl);
			    	  JSONObject data = new JSONObject();
			    	  Video video=new Video();
			    	  video.setCid(cid);
			    	  video.setVideoname(videoname);
			    	  video.setVideokeyword(videokeyword);
			    	  video.setVideoheadurl(videoheadurl);
			    	  video.setVideoorigin("myvideo");
			    	  String uuid = UUID.randomUUID().toString();
			    	  video.setVideooriginurl(uuid);
			    	  int a= videoService.insertVideo(video);
			    	  if(a>0){
			    	  System.out.println(String.valueOf(a));
			        	data.put("result","ok");
			        	return data;
			    	  }
			    	  data.put("result","false");
			    	  return data;
		  }
			 
		   
		   
		   //上传新闻首图
		    @RequestMapping(value="/uploadvideoheadurl")
		    @ResponseBody
		    public JSONObject  uploadvideoheadurl(HttpServletRequest request) throws IllegalStateException, IOException, ParseException
		    {
		    	   JSONObject data = new JSONObject();
		         long  startTime=System.currentTimeMillis();
		         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
		        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
		                request.getSession().getServletContext());
		        //检查form中是否有enctype="multipart/form-data"
		        if(multipartResolver.isMultipart(request))
		        {
		        	MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		            MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		           //获取multiRequest 中所有的文件名
		            Iterator iter=multipartRequest.getFileNames(); 
		     		int ftpPort = 21;
		     		String ftpUserName = "";
		     		String ftpPassword = "";
		     		String ftpHost = "";
		     		FTPClient ftpClient = FTPUtil.getFTPClient(ftpHost, ftpPort, ftpUserName, ftpPassword);
		     		// 设置PassiveMode传输
		             ftpClient.enterLocalPassiveMode();
		             // 设置以二进制流的方式传输
		             ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
		             List<String> tempVideoC=new ArrayList<>();
		            while(iter.hasNext())
		            {
		                //一次遍历所有文件
		                MultipartFile file=multipartRequest.getFile(iter.next().toString());
		                if(file!=null)
		                {
		                    String fileName=file.getOriginalFilename();
//		                    String fileExtentionName = fileName.substring(fileName.lastIndexOf(".") + 1);
//		                    String Name = fileName.substring(fileName.lastIndexOf("/"));
		                    System.out.println(fileName);
		                    //上传文件名 防止上传形同的文件名而造成文件的覆盖
//		                    String uploadFileName = UUID.randomUUID().toString() + "." + fileExtentionName;
		                    String uploadFileName =fileName;
		                    File targetFile = new File("D:\\pics\\video\\headurl\\"+uploadFileName);
		                    tempVideoC.add(uploadFileName);
		                    file.transferTo(targetFile);
		                	FTPUtil.createDir(ftpClient,"/home/ftptest/www/video/headurl/",targetFile);
		             		System.out.println("FTP 连接是否成功：" + ftpClient.isConnected());
		                     System.out.println("FTP 连接是否有效：" + ftpClient.isAvailable());
		                }   
		            }
		            FTPUtil.closeFTPConnect(ftpClient); 
		            long  endTime=System.currentTimeMillis();
			        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
			        String[] VideoAarry=tempVideoC.toArray(new String[tempVideoC.size()]); 
			        URLAvailability u=new URLAvailability();
			        int oknum=0;
			        for(String s:VideoAarry){
			        	   System.out.println(s);
			        	   String videoheadurl="https://xiaoliwaer.top:525/video/headurl/"+s;
			        	   u.isConnect(videoheadurl);
			        	   if(u.state==200){
			        		   data.put("url",videoheadurl);
			        		   oknum++;   
			        	   } 
			        	}
			        if(oknum>0){
			        	   data.put("oknum",oknum);   
			        	   data.put("status","ok");
			        	   System.out.println(data);
		        		   return data; }
		        }
		       data.put("status","false");
		       System.out.println(data);
	 		   return data; 
		    }
		
		
		@RequestMapping(value = "/findVideo")
		public String findNotice( String videoname,String cid,Integer pageIndex, Integer pageSize,Model model) {
				PageInfo<Video> pi = videoService.findPageInfo(videoname,cid,pageIndex,pageSize);
				model.addAttribute("pi",pi);
				return "videolist";
		}
		
		   @RequestMapping( "/findVideoById")
		    public String findVideoById(Integer videoid,HttpSession session,Model model) {

		        Video video= videoService.findVideoById(videoid);
		        List<Categories> vcate= (List<Categories>)categoriesService.showVideoCate();
		        System.out.println(video.toString());
		        session.setAttribute("video",video);
		        model.addAttribute("vcate",vcate);
		        return "video_edit";
		    }

	   
	   @RequestMapping(value="/deleteVideo",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject deleteVideo(@RequestBody Map<String, Object> map){
		   String videoid=(String)map.get("videoid");  
		   System.out.println("删除视频！！！");
		   System.out.println(videoid);
		   Integer Ivideoid = Integer.parseInt(videoid);
	    	int a= videoService.deleteVideo(Ivideoid);
	    	Video video=videoService.findVideoById(Ivideoid);
	    	   int b=bysjCommentService.deleteCommentBytarget(video.getTargeturl());
			   int c=readerActionService.deleteActionBytarget(video.getTargeturl());
			   int d=recommendService.deleteRecommendBytarget(video.getTargeturl());
	    	 System.out.println(String.valueOf(a));
	    	JSONObject data = new JSONObject();
	    	if(a>0){
	    	data.put("status","ok");
	    	return data;
	    	}
	    	data.put("status","false");
	    	return data;
	    }
	   
	   //返回视频页面
	   @RequestMapping( "/uploadVideoIndex")
	    public String uploadVideoIndex(Integer videoid,HttpSession session,Model model) {
	        Video video= videoService.findVideoById(videoid);
	        System.out.println(video.toString());
	        session.setAttribute("video",video);
	        return "uploadvideo";
	    }
	   
		/**
		 * 修改视频信息
		 */
		  @RequestMapping(value="/updateVideo",method=RequestMethod.POST)
		    @ResponseBody
		    public JSONObject  updateVideo(  
		    		@RequestBody Map<String, Object> map,
		    		HttpSession session,
		            HttpServletResponse response) throws ParseException{

		    	  String videoname=(String)map.get("videoname");  
		    	  String videokeyword=(String)map.get("videokeyword"); 
		    	  String cid=(String)map.get("cid"); 
		    	  String videoidtemp=(String)map.get("videoid");
		    	  Integer videoid=Integer.valueOf(videoidtemp);
		    	  System.out.println(videoname);
		    	  System.out.println(videokeyword);
		    	  System.out.println(cid);
		    	  System.out.println(videoid); 
		    	  Video video =new Video();
		    	  video.setCid(cid);
		    	  video.setVideoname(videoname);
		    	  video.setVideoid(videoid);
		    	  video.setVideokeyword(videokeyword);
	    	  
		    	  JSONObject data = new JSONObject();
		    	  
		    	  int a =videoService.updateVideo(video);
		    	  if(a>0){
		    	  System.out.println(String.valueOf(a));
		        	data.put("result","ok");
		        	return data;
		    	  }
		    	  data.put("result","false");
		    	  return data;
	  }
		  
	   //上传视频
	    @RequestMapping(value="/uploadVideo")
	    @ResponseBody
	    public JSONObject  uploadVideo(HttpServletRequest request) throws IllegalStateException, IOException, ParseException
	    {
	    	   JSONObject data = new JSONObject();
	         long  startTime=System.currentTimeMillis();
	         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
	        //检查form中是否有enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request))
	        {
	        	MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
	            MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
	           //获取multiRequest 中所有的文件名
	            Iterator iter=multipartRequest.getFileNames(); 
	     		int ftpPort = 21;
	     		String ftpUserName = "ftptest";
	     		String ftpPassword = "Lijunjie123";
	     		String ftpHost = "39.104.68.226";
	     		FTPClient ftpClient = FTPUtil.getFTPClient(ftpHost, ftpPort, ftpUserName, ftpPassword);
	     		// 设置PassiveMode传输
	             ftpClient.enterLocalPassiveMode();
	             // 设置以二进制流的方式传输
	             ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
	             List<String> tempVideoC=new ArrayList<>();
	            while(iter.hasNext())
	            {
	                //一次遍历所有文件
	                MultipartFile file=multipartRequest.getFile(iter.next().toString());
	                if(file!=null)
	                {
	                    String fileName=file.getOriginalFilename();
//	                    String fileExtentionName = fileName.substring(fileName.lastIndexOf(".") + 1);
//	                    String Name = fileName.substring(fileName.lastIndexOf("/"));
	                    System.out.println(fileName);
	                    //上传文件名 防止上传形同的文件名而造成文件的覆盖
//	                    String uploadFileName = UUID.randomUUID().toString() + "." + fileExtentionName;
	                    String uploadFileName =fileName;
	                    File targetFile = new File("D:\\pics\\video\\uploadok\\"+uploadFileName);
	                    tempVideoC.add(uploadFileName);
	                    file.transferTo(targetFile);
	                	FTPUtil.createDir(ftpClient,"/home/ftptest/www/video",targetFile);
	             		System.out.println("FTP 连接是否成功：" + ftpClient.isConnected());
	                     System.out.println("FTP 连接是否有效：" + ftpClient.isAvailable());
	                }   
	            }
	            FTPUtil.closeFTPConnect(ftpClient); 
	            long  endTime=System.currentTimeMillis();
		        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
		        String[] VideoAarry=tempVideoC.toArray(new String[tempVideoC.size()]); 
		        URLAvailability u=new URLAvailability();
		        int oknum=0;
		        for(String s:VideoAarry){
		        	   System.out.println(s);
		        	   String videooriginurl="https://xiaoliwaer.top:525/video/"+s;
		        	   u.isConnect(videooriginurl);
		        	   if(u.state==200){
		        		   String tvideoid=s.substring(0,s.length()-4);
		        		   System.out.println("tcid:"+tvideoid);
		        		   Integer videoid =Integer.valueOf(tvideoid);
			        		  Video video =new Video();  
					    	  video.setVideoid(videoid);
					    	  video.setVideooriginurl(videooriginurl);
					    	  video.setTargeturl("v"+tvideoid);
					    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    	  String nowTime = sdf.format(new Date());
					    	  Date videortime = sdf.parse( nowTime );
					    	  video.setVideortime(videortime);		    	  
					    	  int a =videoService.updateVideo(video);
		        		   oknum++;   
		        	   } 
		        	}
		        if(oknum>0){
		        	   data.put("oknum",oknum);   
		        	   data.put("status","ok");
		        	   System.out.println(data);
	        		   return data; }
	        }
	       data.put("status","false");
	       System.out.println(data);
 		   return data; 
	    }
		  
		  
		  

		 	
		  
		  
    	
    }
