package ljj.util;

import java.net.HttpURLConnection;  
import java.net.URL;  
  
/** 
* �ļ�����Ϊ��URLAvailability.java 
* �ļ����ܼ����� ����һ��URL��ַ�Ƿ���Ч 
* @author Jason 
* @time   2010-9-14  
*  
*/  
public class URLAvailability {  
public  URL url;  
public  HttpURLConnection con;  
public int state = -1;  
  
/** 
   * ���ܣ���⵱ǰURL�Ƿ�����ӻ��Ƿ���Ч, 
   * ����������������� 5 ��, ��� 5 �ζ����ɹ�����Ϊ�õ�ַ������ 
   * @param urlStr ָ��URL�����ַ 
   * @return URL 
   */  
public synchronized URL isConnect(String urlStr) {  
   int counts = 0;  
   if (urlStr == null || urlStr.length() <= 0) {                         
    return null;                   
   }  
   while (counts < 3) {  
    try {  
     url = new URL(urlStr);  
     con = (HttpURLConnection) url.openConnection();  
     state = con.getResponseCode();  
     System.out.println("cishu="+counts +" code= "+state);  
     if (state == 200) {  
      System.out.println("URL���ã�");  
     }  
     break;  
    }catch (Exception ex) {  
     counts++;   
     System.out.println("URL�����ã����ӵ� "+counts+" ��");  
     urlStr = null;  
     continue;  
    }  
   }  
   return url;  
}  
//public static void main(String[] args) {  
//    URLAvailability u=new URLAvailability(); 
//    
//    u.isConnect("http://www.baidu.com");
//    
//    	System.out.println(Integer.toString(u.state));  	
//   
//    
//}  
}  