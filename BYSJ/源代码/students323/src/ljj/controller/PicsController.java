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

import ljj.pojo.Categories;
import ljj.pojo.News;
import ljj.pojo.PageInfo;
import ljj.pojo.Pics;
import ljj.service.BysjCommentService;
import ljj.service.CategoriesService;
import ljj.service.PicsService;
import ljj.service.ReaderActionService;
import ljj.service.RecommendService;
import ljj.util.FTPUtil;
import ljj.util.URLAvailability;

@Controller

public class PicsController {
	   @Autowired
	    private PicsService picsService;
	   @Autowired
	    private CategoriesService categoriesService;
	   @Autowired
	    private BysjCommentService bysjCommentService;
	   @Autowired
	    private ReaderActionService readerActionService;
	   @Autowired
	    private RecommendService recommendService;
	   
	   
	   @RequestMapping(value = "/testurlpics",method=RequestMethod.POST)
	    @ResponseBody
		public JSONObject testurl( @RequestBody Map<String, Object> map) {
			String picsoriginurl=(String)map.get("picsoriginurl");  
			 JSONObject data = new JSONObject();
			 System.out.println("url：" +  picsoriginurl);
			URLAvailability u=new URLAvailability();
			  u.isConnect(picsoriginurl);
      	   if(u.state==200){
      		   data.put("result", "ok");
      		   data.put("url", picsoriginurl);
      		   return data;
      	   } 
      	   		data.put("result", "false");
      	   	   return data;
		}
	   
		//一键发布
		
		 @RequestMapping(value="/targetAllpics")
		  @ResponseBody
		    public JSONObject TargetAllpics() throws ParseException {
				System.out.println("一键发布");
			  JSONObject data = new JSONObject();
			 List<Pics> AllpicsList= picsService.findallpics();
			 if(AllpicsList.size()>0){
				 Pics[] AllPics= new Pics[AllpicsList.size()];
				 AllpicsList.toArray(AllPics);
				 int numsuccess=0;
				 for (int i = 0; i < AllPics.length; i++) {
					Integer picsid=AllPics[i].getPicsid();
					String picsS=String.valueOf(picsid);
					Pics pics=picsService.findPicsById(picsid);
					pics.setTargeturl("p"+picsS);
					 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	  String nowTime = sdf.format(new Date());
			    	  Date newsrtime = sdf.parse( nowTime );
			    	  pics.setPicsrtime(newsrtime);
						int a=picsService.updatePics(pics);
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
	   
	   //返回自定义图册页面
	   @RequestMapping( "/insertPicsIndex")
	    public String insertpicsIndex(Model model) {
		   List<Categories> vcate= (List<Categories>)categoriesService.showPicsCate();
	        model.addAttribute("vcate",vcate);
	        return "insertpics";
	    }
		//插入图册
		
		 @RequestMapping(value="/insertPics",method=RequestMethod.POST)
		    @ResponseBody
		    public JSONObject insertpics(  
		    	@RequestBody Map<String, Object> map) throws ParseException {
			 	  String picsname=(String)map.get("picsname");  
		    	  String picskeyword=(String)map.get("picskeyword"); 
		    	  String cid=(String)map.get("cid"); 
		    	  String picsheadurl=(String)map.get("picsheadurl");
		    	  System.out.println(picsname);
		    	  System.out.println(picskeyword);
		    	  System.out.println(cid);
		    	  System.out.println(picsheadurl);
		    	  JSONObject data = new JSONObject();
		    	  Pics pics=new Pics();
		    	  pics.setCid(cid);
		    	  pics.setPicsname(picsname);
		    	  pics.setPicskeyword(picskeyword);
		    	  pics.setPicsheadurl(picsheadurl);
		    	  pics.setPicsorigin("mypics");
		    	  pics.setPicscontent("内容待填写");
		    	  String uuid = UUID.randomUUID().toString();
		    	  pics.setPicsoriginurl(uuid);
		    	  int a= picsService.insertPics(pics);
		    	  if(a>0){
		    	  System.out.println(String.valueOf(a));
		        	data.put("result","ok");
		        	return data;
		    	  }
		    	  data.put("result","false");
		    	  return data;
	  }
		 
		 
		 //上传图册首图
		    @RequestMapping(value="/uploadpicsheadurl")
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
		     		String ftpUserName = "";
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
		                	FTPUtil.createDir(ftpClient,"/home/ftptest/www/pics/picshead",targetFile);
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
			        	   String newsheadurl="https://xiaoliwaer.top:525/pics/picshead/"+s;
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
	
	   
	   
	   @RequestMapping(value="/selectpicspapebypage",produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
		public JSONObject selectpicsbypage( Integer picsid,String picsname,String cid,String page, Integer pageSize) {
		   		Integer	pageIndex=Integer.parseInt(page);
		   		pageSize=20;
		   		JSONObject data = new JSONObject();
				PageInfo<Pics> pi = picsService.findPageInfo(picsid,picsname,cid,pageIndex,pageSize);
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
	
	   
	   @RequestMapping( "/findPicsById")
	    public String findPicsById(Integer picsid,HttpSession session,Model model) {
		   System.out.println(String.valueOf(picsid));
	        Pics pics= picsService.findPicsById(picsid);
	        List<Categories> vcate= (List<Categories>)categoriesService.showPicsCate();
	        System.out.println(pics.toString());
	        model.addAttribute("pics",pics);
	        model.addAttribute("vcate",vcate);
	        return "pics_edit";
	    }
	   
	   @RequestMapping(value="/deletePics",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public JSONObject deletePics(@RequestBody Map<String, Object> map){
		   String picsid=(String)map.get("picsid");  
		   System.out.println("删除图册！！！");
		   System.out.println(picsid);
		   Integer Ipicsid = Integer.parseInt(picsid);
		   Pics pics=picsService.findPicsById(Ipicsid);
		   int b=bysjCommentService.deleteCommentBytarget(pics.getTargeturl());
		   int c=readerActionService.deleteActionBytarget(pics.getTargeturl());
		   int d=recommendService.deleteRecommendBytarget(pics.getTargeturl());
	    	int a= picsService.deletePics(Ipicsid);
	    	 System.out.println(String.valueOf(a));
	    	JSONObject data = new JSONObject();
	    	if(a>0){
	    	data.put("status","ok");
	    	return data;
	    	}
	    	data.put("status","false");
	    	return data;
	    }
	   

		@RequestMapping(value = "/findPics")
		public String findPics( Integer picsid, String picsname,String cid,Integer pageIndex, Integer pageSize,Model model) {
			System.out.println("编号："+String.valueOf(picsid));
				PageInfo<Pics> pi = picsService.findPageInfo(picsid,picsname,cid,pageIndex,pageSize);
				model.addAttribute("pi",pi);

				return "picslist";
		}
		
		/**
		 * 修改视频信息
		 */
		  @RequestMapping(value="/updatePics",method=RequestMethod.POST)
		    @ResponseBody
		    public JSONObject  updatePics(  
		    		@RequestBody Map<String, Object> map,
		    		HttpSession session,
		            HttpServletResponse response) throws ParseException{

		    	  String picsname=(String)map.get("picsname");  
		    	  String picskeyword=(String)map.get("picskeyword"); 
		    	  String cid=(String)map.get("cid"); 
		    	  String picsidtemp=(String)map.get("picsid");
		    	  Integer picsid=Integer.valueOf(picsidtemp);
		    	  System.out.println(picsname);
		    	  System.out.println(picskeyword);
		    	  System.out.println(cid);
		    	  System.out.println(picsid); 
		    	  Pics pics=new Pics();
		    	  pics.setCid(cid);
		    	  pics.setPicsid(picsid);
		    	  pics.setPicsname(picsname);
		    	  pics.setPicskeyword(picskeyword);

		    	  JSONObject data = new JSONObject();
		    	  
		    	  int a =picsService.updatePics(pics);
		    	  if(a>0){
		    	  System.out.println(String.valueOf(a));
		        	data.put("result","ok");
		        	return data;
		    	  }
		    	  data.put("result","false");
		    	  return data;
	  }
		   //返回视频页面
		   @RequestMapping( "/uploadpicsIndex")
		    public String uploadpicsIndex(Integer picsid,HttpSession session,Model model) {
		        Pics pics= picsService.findPicsById(picsid);
		        System.out.println(pics.toString());
		        model.addAttribute("pics",pics);
				if(pics!=null){
					String url=pics.getPicsoriginurl();
					String  picscontent=pics.getPicscontent();
					 System.out.println(picscontent);
					String	picsurl=url+"?vt=4&hd=1";
			    	  JSONObject data = new JSONObject();
			    	  data.put("picscontent",picscontent);
			    	model.addAttribute("data",data);
				}
		        return "uploadpics";
		    }
		   
		   
		   /**
			 * 上传图册信息
		 * @param <T>
			 */
			  @RequestMapping(value="/uploadpicscontent",method=RequestMethod.POST)
			  @ResponseBody
			    public  JSONObject  updatePicsModel ( @RequestBody String params) throws ParseException{
		    	  
				  JSONArray jsonArray= JSONArray.parseArray(params);
		    	  System.out.println(jsonArray);
		    	  JSONObject picscontentObject = jsonArray.getJSONObject(0);
		    	  String picsidtemp= picscontentObject.getString("picsid");
		    	  String picsoriginurl=picscontentObject.getString("picsoriginurl");
		    	  JSONArray picsdata=picscontentObject.getJSONArray("data");
		    	  JSONObject picscontentdataObj = picsdata.getJSONObject(0);
		    	  String picscontent=picscontentdataObj.getString("picscontent");
		    	  System.out.println(picsidtemp);
		    	  System.out.println(picscontentObject);
		    	  System.out.println(picsdata);
		    	  System.out.println(picscontent);
       		   		Integer picsid =Integer.valueOf(picsidtemp.trim());
       		   		  Pics pics =picsService.findPicsById(picsid);  
			    	  pics.setPicsid(picsid);
			    	  pics.setTargeturl("p"+picsid);
			    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	  String nowTime = sdf.format(new Date());
			    	  Date picsrtime = sdf.parse( nowTime );
			    	  pics.setPicsrtime(picsrtime);		    	  
			    	  pics.setPicscontent(picscontent);	  
			    	  System.out.println(pics.toString());
			    	  int a =picsService.updatePics(pics);
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