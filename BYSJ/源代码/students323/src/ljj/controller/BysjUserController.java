package ljj.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Base64.Decoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import ljj.pojo.BysjUser;
import ljj.pojo.Reader;
import ljj.pojo.ReaderType;
import ljj.service.BysjUserService;
import ljj.service.ReaderService;
import ljj.service.ReaderTypeService;
import ljj.util.FTPUtil;

@Controller
public class BysjUserController {

    @Autowired
    private BysjUserService bysjuserService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private ReaderTypeService readertypeService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
 	 * 上传头像
 	 */
 	@ResponseBody
 	@RequestMapping(value = "/uploadHeadImg",method=RequestMethod.POST)
 	public JSONObject  uploadMethod(@RequestParam("imgData") String imgData,@RequestParam("nick") String nick,@RequestParam("readerid") String readerid,
 			HttpServletRequest request ,HttpServletResponse response) {
 		 System.out.println(imgData);	
 		 System.out.println(nick);
 		 System.out.println(readerid);
 	Decoder decoder = Base64.getDecoder();
 	JSONObject data = new JSONObject();
 	try {
         // Base64解码
         byte[] b = decoder.decode(imgData);  
         for (int i = 0; i < b.length; ++i) {
             if (b[i] < 0) {
            	 // 调整异常数据
                 b[i] += 256;
             }
         }
      
      // 生成jpeg图片

 String osName = System.getProperties().getProperty("os.name");
 String imgname ="r"+readerid+".jpg";


 	String imgFilePath = "D:\\pics\\pic\\header\\"+imgname;// 新生成的图片
     String headerimgPath=imgname;
     //插入头像
     System.out.println(imgname);
     int a=  bysjuserService.insertreaderheaderimg(nick,headerimgPath); 
     if(a>0)
     System.out.println("ok");
     if(a==0)
    System.out.println("哭了"); 
     	System.out.println(osName);
     	System.out.println(imgFilePath);
     	
         OutputStream out = new FileOutputStream(imgFilePath);
         out.write(b);
         out.flush();
         out.close();
         File file = new File(imgFilePath);
 		int ftpPort = 21;
 		String ftpUserName = "";
 		String ftpPassword = "";
 		String ftpHost = "";
 		FTPClient ftpClient = FTPUtil.getFTPClient(ftpHost, ftpPort, ftpUserName, ftpPassword);
 		// 设置PassiveMode传输
         ftpClient.enterLocalPassiveMode();
         // 设置以二进制流的方式传输
         ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

 			//创建目录
 		FTPUtil.createDir(ftpClient,"/home/ftptest/www/headerimg",file);
 		System.out.println("FTP 连接是否成功：" + ftpClient.isConnected());
         System.out.println("FTP 连接是否有效：" + ftpClient.isAvailable());
         FTPUtil.closeFTPConnect(ftpClient); 
         data.put("head",headerimgPath);
     } catch (Exception e) {
     	 data.put("status", "false");
         return  data;
     }
 	
 	
 	return data;

 	}
 
	/**
	 * 退出登录
	 */
	@RequestMapping(value = "/loginOut")
	public String loginOut(BysjUser admin, Model model, HttpSession session) {
		session.invalidate();
		return "login";

	}
 
    
    @RequestMapping(value="/adlogin",method=RequestMethod.POST)
    public String  adlogin(BysjUser admin, Model model,HttpSession session
    		, HttpServletRequest request  ){
    	  String nick=admin.getNick();
    	  String password=admin.getPassword();
    	  System.out.println("请求JSON数据！！！");
    	  System.out.println(nick);
    	  
    	
    	  System.out.println(bysjuserService+""+bysjuserService.toString());
    	  List<BysjUser> BysjUserList= (List<BysjUser>) bysjuserService.findAdministrator(nick, password);   
    	  System.out.println("符合条件的条目"+BysjUserList.size());
    	  
    	  
    	 if(BysjUserList.size()>0){
    	BysjUser[] BysjUsers = new BysjUser[BysjUserList.size()];
        BysjUserList.toArray(BysjUsers);
    	session.setAttribute("ad",BysjUsers[0]);
  			return "homepage";
          }
        	model.addAttribute("msg", "用户名或密码错误，请重新登录！");
      		return "login";    
    }
    
    
    
    @RequestMapping("/findbysjuser")
    @ResponseBody
    public String  getuser( @RequestBody BysjUser user, HttpSession session,
            HttpServletResponse response){
    	  System.out.println("请求JSON数据！！！");
    	  System.out.println(user);
    	  if(user==null){
    		  System.out.println("接受不到user");
    		  return "0";
    	  }
    	  else{
    		  System.out.println(user.getNick()+""+user.getPassword());
    	  }
    	  String nick =user.getNick();
    	  String password =user.getPassword();
    	  System.out.println(bysjuserService+""+bysjuserService.toString());
    	  List<BysjUser> BysjUserList= (List<BysjUser>) bysjuserService.findBysjUser(nick, password);
    	  System.out.println("符合条件的条目"+BysjUserList.size());
    	  JSONObject data = new JSONObject();
    	  if(BysjUserList.size()>0){
    	  BysjUser[] BysjUsers = new BysjUser[BysjUserList.size()];
    	  BysjUserList.toArray(BysjUsers);
              if(BysjUsers[0].getUsertype().equals("user")){
            	  data.put("result","success by user");
              }
              if(BysjUsers[0].getUsertype().equals("su")){
            	  data.put("result","success by su");
              }
              else{
            	  data.put("result","hahaha");
              }
          }else {
              data.put("result","false");
          }
    	  
      	  System.out.println(data.toJSONString());
          return data.toJSONString();

    }
    
    
    @RequestMapping(value="/readerlogin",method=RequestMethod.POST)
    @ResponseBody
    public String  readerlogin(@RequestBody Map<String, Object> map, HttpSession session,
            HttpServletResponse response){
    	  String nick=(String)map.get("nick");
    	  String password=(String)map.get("mima");
    	  System.out.println("请求JSON数据！！！");
    	  System.out.println(nick);
    	  if(nick==null){
    		  System.out.println("接受不到user");
    		  return "0";
    	  }
    	  else{
    		  System.out.println(nick);
    	  } 
    	  System.out.println(bysjuserService+""+bysjuserService.toString());
    	  List<BysjUser> BysjUserList= (List<BysjUser>) bysjuserService.findBysjUser(nick,password); 
    	  System.out.println("符合条件的条目"+BysjUserList.size());
    	  JSONObject data = new JSONObject();
    	  if(BysjUserList.size()>0){
    	  BysjUser[] BysjUsers = new BysjUser[BysjUserList.size()];
    	  BysjUserList.toArray(BysjUsers);
    	  String nick1 = BysjUsers[0].getNick();
    	  String head = BysjUsers[0].getHead();
    	  List<Reader> Readerlist= (List<Reader>)readerService.findreaderbynick(nick);
    	  Reader[] Readers = new Reader[Readerlist.size()];
    	  Readerlist.toArray(Readers);
    	  Integer readerid = Readers[0].getReaderid() ;
    	  data.put("nick",nick1);
    	  data.put("readerid", readerid);
    	  data.put("head", head);
    	  data.put("result","ok");
          }else {
              data.put("result","false");
          }
    	  
      	  System.out.println(data.toJSONString());
          return data.toJSONString();

    }
    
 
    @RequestMapping(value="/findbysjuserbynick",method=RequestMethod.POST)
    @ResponseBody
    public String  getuser(@RequestBody Map<String, Object> map, HttpSession session,
            HttpServletResponse response){
    	  String nick=(String)map.get("nick");	 
    	  System.out.println("请求JSON数据！！！");
    	  System.out.println(nick);
    	  
    	  if(nick==null){
    		  System.out.println("接受不到user");
    		  return "0";
    	  }
    	  else{
    		  System.out.println(nick);
    	  }
    	  System.out.println(bysjuserService+""+bysjuserService.toString());
    	  List<BysjUser> BysjUserList= (List<BysjUser>) bysjuserService.findBysjUserByNick(nick);
    	  
    	  System.out.println("符合条件的条目"+BysjUserList.size());
    	  JSONObject data = new JSONObject();
    	  if(BysjUserList.size()>0){
    	  BysjUser[] BysjUsers = new BysjUser[BysjUserList.size()];
    	  BysjUserList.toArray(BysjUsers);
    	  String nick1 = BysjUsers[0].getNick();
    	  String head = BysjUsers[0].getHead();
    	  
    	  data.put("nick",nick1);
    	  data.put("head", head);
          }else {
              data.put("result","false");
          }
    	  
      	  System.out.println(data.toJSONString());
          return data.toJSONString();

    }
    
    
    
    
    @RequestMapping("/findAllreader")
    @ResponseBody
    public List<BysjUser>  getuser(  HttpSession session,
            HttpServletResponse response){
    	  System.out.println("请求JSON数据！！！");
    
    	  List<BysjUser> BysjUserList= (List<BysjUser>) bysjuserService.findAllreader();
    	  System.out.println("符合条件的条目"+BysjUserList.size());
    	  JSONObject data = new JSONObject();
    	  return bysjuserService.findAllreader();

    }
    
    
    @RequestMapping(value="/insertreader",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject  insertreader(  
    		@RequestBody Map<String, Object> map,
    		HttpSession session,
            HttpServletResponse response){
    	  System.out.println("请求JSON数据！！！");
    	  String nick=(String)map.get("nick");	 
    	  String mima=(String)map.get("mima"); 
    	  System.out.println(nick);
    	  System.out.println(mima);
    	  List<BysjUser> BysjUserList= (List<BysjUser>) bysjuserService.findBysjUser(nick, mima);
    	  System.out.println("符合条件的条目"+BysjUserList.size());
    	  JSONObject data = new JSONObject();
    	  if(BysjUserList.size()>0){
    	  BysjUser[] BysjUsers = new BysjUser[BysjUserList.size()];
    	  BysjUserList.toArray(BysjUsers);
    	  data.put("result","no");
    	  return data;
          }else {
        	  
        	bysjuserService.insertReader(nick, mima,"temp.jpg","r");
        	readerService.insertReaderauto(nick);
        	List<Reader> reader=readerService.findreaderbynick(nick);
        	Integer readerid=reader.get(0).getReaderid();
        	ReaderType readertype=new ReaderType();
        	readertype.setLevel("1");
        	readertype.setPriority("A");
        	readertype.setReaderid(readerid);
        	readertype.setReadertype("默认");
        	readertypeService.insertReaderType(readertype);
        	 data.put("result","ok");
        	return data;
          }
    }
    
    
    
    
    
    


    }
    
