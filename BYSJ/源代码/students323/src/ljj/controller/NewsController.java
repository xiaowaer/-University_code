package ljj.controller;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ljj.pojo.BysjUser;
import ljj.pojo.Categories;
import ljj.pojo.PageInfo;
import ljj.pojo.Video;
import ljj.pojo.News;
import ljj.service.BysjCommentService;
import ljj.service.CategoriesService;
import ljj.service.NewsService;
import ljj.service.ReaderActionService;
import ljj.service.RecommendService;
import ljj.util.FTPUtil;
import ljj.util.URLAvailability;

@Controller

public class NewsController{
	   @Autowired
	    private NewsService newsService;
	   @Autowired
	    private CategoriesService categoriesService;
	   @Autowired
	    private BysjCommentService bysjCommentService;
	   @Autowired
	    private ReaderActionService readerActionService;
	   @Autowired
	    private RecommendService recommendService;
	   
	   @RequestMapping(value = "/testurlnews",method=RequestMethod.POST)
	    @ResponseBody
		public JSONObject testurl( @RequestBody Map<String, Object> map) {
			String newsoriginurl=(String)map.get("newsoriginurl");  
			  String newsurl= "https://new.qq.com/omn/" + newsoriginurl.substring(0,8)+"/"+newsoriginurl+".html";
			 JSONObject data = new JSONObject();
			 System.out.println("url：" +  newsurl);
			URLAvailability u=new URLAvailability();
			  u.isConnect(newsurl);
     	   if(u.state==200){
     		   data.put("result", "ok");
     		
     		   data.put("url", newsurl);
     		   
     		   return data;
     	   } 
     	   		data.put("result", "false");
     	   	   return data;
		}
	
	   
	   //返回自定义新闻页面
	   @RequestMapping( "/insertNewsIndex")
	    public String insertNewsIndex(Model model) {
		   List<Categories> vcate= (List<Categories>)categoriesService.showNewsCate();
	        model.addAttribute("vcate",vcate);
	        return "insertnews";
	    }
	   
	   @RequestMapping(value="/selectnewspapebypage",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
		public JSONObject selectnewsbypage( Integer newsid,String newstitle,String cid,String page, Integer pageSize) {
		   		Integer	pageIndex=Integer.parseInt(page);
		   		pageSize=20;
		   		JSONObject data = new JSONObject();
				PageInfo<News> pi = newsService.findPageInfo(newsid,newstitle,cid,pageIndex,pageSize);
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
	
	   
	   @RequestMapping( "/findNewsById")
	    public String findnewsById(Integer newsid,HttpSession session,Model model) {
		   System.out.println(String.valueOf(newsid));
	        News news= newsService.findnewsById(newsid);
	        List<Categories> vcate= (List<Categories>)categoriesService.showNewsCate();
	        System.out.println(news.toString());
	        model.addAttribute("news",news);
	        model.addAttribute("vcate",vcate);
	        return "news_edit";
	    }
	   
	   @RequestMapping(value="/deleteNews",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject deletenews(@RequestBody Map<String, Object> map){
		   String newsid=(String)map.get("newsid");  
		   System.out.println("删除新闻！！！");
		   System.out.println(newsid);
		   Integer Inewsid = Integer.parseInt(newsid);
		   News news =newsService.findnewsById(Inewsid);
		   int b=bysjCommentService.deleteCommentBytarget(news.getTargeturl());
		   int c=readerActionService.deleteActionBytarget(news.getTargeturl());
		   int d=recommendService.deleteRecommendBytarget(news.getTargeturl());
	    	int a= newsService.deletenews(Inewsid);
	    	 System.out.println(String.valueOf(a));
	    	JSONObject data = new JSONObject();
	    	  System.out.println("a:"+a);
	    	if(a>0){
	    	data.put("status","ok");
	    	return data;
	    	}
	    	data.put("status","false");
	    	return data;
	    }
	   

		@RequestMapping(value = "/findNews")
		public String findnews( Integer newsid, String newstitle,String cid,Integer pageIndex, Integer pageSize,Model model) {
			System.out.println("查找新闻列表");
			System.out.println("编号："+String.valueOf(newsid));
				PageInfo<News> pi = newsService.findPageInfo(newsid,newstitle,cid,pageIndex,pageSize);
				model.addAttribute("pi",pi);

				return "newslist";
		}
		//一键发布
		
		 @RequestMapping(value="/targetAll")
		  @ResponseBody
		    public JSONObject TargetAll() throws ParseException {
				System.out.println("一键发布");
			  JSONObject data = new JSONObject();
			 List<News> AllnewsList= newsService.findallnews();
			 if(AllnewsList.size()>0){
				 News[] AllNews= new News[AllnewsList.size()];
				 AllnewsList.toArray(AllNews);
				 int numsuccess=0;
				 for (int i = 0; i < AllNews.length; i++) {
					Integer newsid=AllNews[i].getNewsid();
					String newsS=String.valueOf(newsid);
					News news=newsService.findnewsById(newsid);
					news.setTargeturl("n"+newsS);
					 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	  String nowTime = sdf.format(new Date());
			    	  Date newsrtime = sdf.parse( nowTime );
			    	  news.setNewsrtime(newsrtime);
						int a=newsService.updateNews(news);
						  if(a>0){
							  numsuccess++;
						  }
				}
				 System.out.println(numsuccess);
				 data.put("result","ok");
				 data.put("numsuccess",numsuccess);
				 return data;
			 }
		    	  data.put("result","false");
		    	  return data;
	  }
	
		//插入新闻
		
		 @RequestMapping(value="/insertNews",method=RequestMethod.POST)
		    @ResponseBody
		    public JSONObject insertNews(  
		    	@RequestBody Map<String, Object> map) throws ParseException {
			 	  String newstitle=(String)map.get("newstitle");  
		    	  String newskeyword=(String)map.get("newskeyword"); 
		    	  String cid=(String)map.get("cid"); 
		    	  String newsheadurl=(String)map.get("newsheadurl");
		    	  System.out.println(newstitle);
		    	  System.out.println(newskeyword);
		    	  System.out.println(cid);
		    	  System.out.println(newsheadurl);
		    	  JSONObject data = new JSONObject();
		    	 News news=new News();
		    	  news.setCid(cid);
		    	  news.setNewstitle(newstitle);
		    	  news.setNewskeyword(newskeyword);
		    	  news.setNewsheadurl(newsheadurl);
		    	  news.setNewsorigin("mynews");
		    	  news.setNewscontent("内容待填写");
		    	  String uuid = UUID.randomUUID().toString();
		    	  news.setNewsoriginurl(uuid);
		    	  int a= newsService.insertNews(news);
		    	  if(a>0){
		    	  System.out.println(String.valueOf(a));
		        	data.put("result","ok");
		        	return data;
		    	  }
		    	  data.put("result","false");
		    	  return data;
	  }
		 
	
		 
		
		/**
		 * 修改新闻信息
		 */
		  @RequestMapping(value="/updateNews",method=RequestMethod.POST)
		    @ResponseBody
		    public JSONObject  updatenews(  
		    		@RequestBody Map<String, Object> map,
		    		HttpSession session,
		            HttpServletResponse response) throws ParseException{

		    	  String newstitle=(String)map.get("newsname");  
		    	  String newskeyword=(String)map.get("newskeyword"); 
		    	  String cid=(String)map.get("cid"); 
		    	  String newsidtemp=(String)map.get("newsid");
		    	  Integer newsid=Integer.valueOf(newsidtemp);
		    	  System.out.println(newstitle);
		    	  System.out.println(newskeyword);
		    	  System.out.println(newsid); 
		    	 News news=new News();
		    	  news.setCid(cid);
		    	  news.setNewsid(newsid);
		    	  news.setNewstitle(newstitle);;
		    	  news.setNewskeyword(newskeyword);

		    	  JSONObject data = new JSONObject();
		    	  
		    	  int a =newsService.updateNews(news);
		    	  if(a>0){
		    	  System.out.println(String.valueOf(a));
		        	data.put("result","ok");
		        	return data;
		    	  }
		    	  data.put("result","false");
		    	  return data;
	  }
		  
		  
		  //上传新闻首图
		    @RequestMapping(value="/uploadnewsheadurl")
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
		     		String ftpUserName = ";
		     		String ftpPassword = ";
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
		                    File targetFile = new File("D:\\pics\\news\\headurl\\"+uploadFileName);
		                    tempVideoC.add(uploadFileName);
		                    file.transferTo(targetFile);
		                	FTPUtil.createDir(ftpClient,"/home/ftptest/www/news",targetFile);
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
			        	   String newsheadurl="https://xiaoliwaer.top:525/news/"+s;
			        	   u.isConnect(newsheadurl);
			        	   if(u.state==200){
			        		   data.put("url",newsheadurl);
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
			  
			  
			  
		  
		   //返回视频页面
		   @RequestMapping( "/uploadNewsIndex")
		    public String uploadnewsIndex(Integer newsid,HttpSession session,Model model) {
		        News news= newsService.findnewsById(newsid);
		        System.out.println(news.toString());
		        model.addAttribute("news",news);
				if(news!=null){
					String url=news.getNewsoriginurl();
					String newsurl=url;
					String date=url.substring(0,8);
//					String newsurl1= "https://new.qq.com/omn/" +date+"/"+newsurl+".html";
//					System.out.println(newsurl1);
//			    	System.out.println(newsContent.size());
			    	String  newscontent=news.getNewscontent();
			    	 System.out.println(newscontent.toString());
			    	  JSONObject data = new JSONObject();
			    	  data.put("newscontent",newscontent);
			    	  model.addAttribute("data",data);
				}
		        return "uploadnews";
		    }
		   
		   /**
			 * 上传新闻
		 * @param <T>
			 */
			  @RequestMapping(value="/uploadNewscontent",method=RequestMethod.POST)
			  @ResponseBody
			    public  JSONObject  updatenewsModel ( @RequestBody String params) throws ParseException{
		    	  
				  JSONArray jsonArray= JSONArray.parseArray(params);
		    	  System.out.println(jsonArray);
		    	  JSONObject newscontentObject = jsonArray.getJSONObject(0);
		    	  String newsidtemp= newscontentObject.getString("newsid");
		    	  String newsoriginurl=newscontentObject.getString("newsoriginurl");
		    	  JSONArray newsdata=newscontentObject.getJSONArray("data");
		    	  JSONObject newscontentdataObj = newsdata.getJSONObject(0);
		    	  String newscontent=newscontentdataObj.getString("newscontent");
		    	  
		    	  System.out.println(newsidtemp);
		    	  System.out.println(newscontentObject);
		    	  System.out.println(newsdata);
		    	  System.out.println(newscontent);
       		   		Integer newsid =Integer.valueOf(newsidtemp.trim());
       		   		  News news =newsService.findnewsById(newsid); 
			    	  news.setNewsid(newsid);
			    	  news.setTargeturl("n"+newsid);
			    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	  String nowTime = sdf.format(new Date());
			    	  Date newsrtime = sdf.parse( nowTime );
			    	  news.setNewsrtime(newsrtime);		    	  
			    	  news.setNewscontent(newscontent);
			    	  System.out.println(news.toString());
			    	  int a =newsService.updateNews(news);
			    	  JSONObject data = new JSONObject();
			    	  if(a>0){
			    	  System.out.println(String.valueOf(a));
			        	data.put("result","ok");
			        	  System.out.println(data);
			        	return data;
			    	  }
			    	  data.put("result","false");
			    	  System.out.println(data);
			    	  return data;
		  }
		   
}
