package com.XMLmine;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;



//import DAO.User;




//DOM4j的使用
public class TestDom4j {
	
	
	public static void main(String[] args) throws DocumentException, IOException{
		//SAXReader saxReader=new SAXReader();
//		得到解析器
	
//			Document document=saxReader.read(new File("src/XML/Users.xml"));
//			list(document.getRootElement());
//			read(document);
//			add(document);
//			del(document);
		
		//XMLFile xmlFile=XMLFile.getXml4j("src/XML/Users.xml");
		//list(xmlFile.getDocument().getRootElement());
		XMLFile xmlFile=XMLFile.getXml4j("src/com/XML_mine/Users.xml");
		//list(xmlFile.getDocument().getRootElement());
		 Class cla = null;
		try {
			cla = Class.forName("com.Dao.User");
			TestIdWeakHashMap(xmlFile, cla);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	

		

	
		
	}
	
	
	//通用修改
	//通用删除
	
	
	//测试map和xpath，构建参数列表，通用查询的方法。
	public static <T> void TestIdWeakHashMap(XMLFile xmlFile,Class<T> clazz){
		T obj=null;
		List<T> result = new LinkedList<T>();
		Map<Integer,String> map=new HashMap<>();
		map.put(1, "user");
		

//		1.当获得文档之后就可以使用xpath随便取值了
		//	xmlFile.getDocument().selectNodes(arg0);
//		元素返回多个节点
//		xmlFile.getDocument().selectSingleNode(arg0);
//		节点只是返回一个
		 List e=xmlFile.getDocument().selectNodes("//"+map.get(1));
		 System.out.println(e.size());
		 for( int i=0;i<e.size();i++){
			 //当前项
			 Element element=(Element)e.get(i);
			 try {
				obj=clazz.newInstance();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 //打印查找元素名
			 System.out.println(element.getName());
			 //子项列表
			 List eson=element.selectNodes("*");
			 
			 //利用泛型得到属性名，在通过属性名设置map的键值对，
			 //然后结果用List<map<String,String>>封装
			 
			// System.out.println(obj.getClass().getFields());
			 
			//  Class cls = e.getClass();
		        Field[] fields = obj.getClass().getDeclaredFields();
		        for(int ifields =0; ifields <fields.length; ifields++){
		            Field f = fields[ifields
		                             ];
		            f.setAccessible(true);
		            try {
						System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(obj));
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        } 

			 
			 System.out.println(eson.size());
			 for( int j=0;j<eson.size();j++){
				 
				 Element elementson=(Element)eson.get(j);
				 //打印查找元素名
				 System.out.println(elementson.getName()+elementson.getText());
			 }
		 }
	}
	
	
	//通用查询
	public static <T> void queryElement(XMLFile xmlFile, Map<T,T> map){
		
		//使用技术	XPath实现了便捷获取元素
//		1.获得XML文档，输入查询参数
//		2.查询过程，参看文档中是否存在符合参数的条件
//		3.成功返回
		
		//xmlFile.getDocument()
		
//		 public static void read(Document document){
//			 //获取根元素
//			 Element root = document.getRootElement();	
//			 //获取root下一级的所有user元素	
//			 //返回List，用一个泛型数组接受
//			 List<Element> list =(List<Element>)root.elements("user");
//			//根据列表长度遍历
//			 for( int i=0;i<list.size();i++){
//				 //当前项
//				 Element element=(Element)list.get(i);
//				 //子项第一项
//				 Element element1=element.element("username");
//				 //取出当前项+属性名+子项
//				 System.out.println(element.getName()+element.attributeValue("id")+element1.getText());
//			 }
		
//	 }
		
		
		
		
	}
	
	//使用.elementIterator();遍历xml文档；
	public static void list(Element element){
		
		System.out.println(element.getName()+element.getTextTrim());
		Iterator iterator=element.elementIterator();
		while(iterator.hasNext()){
			Element e=(Element)iterator.next();
			list(e);
		}
	}
	
	
	//读取到指定的某个元素（读取第一个元素，按照树形层次获取）
	 public static void read(Document document){
		 //获取根元素
		 Element root = document.getRootElement();	
		 //获取root下一级的所有user元素	
		 //返回List，用一个泛型数组接受
		 List<Element> list =(List<Element>)root.elements("user");
		//根据列表长度遍历
		 for( int i=0;i<list.size();i++){
			 //当前项
			 Element element=(Element)list.get(i);
			 //子项第一项
			 Element element1=element.element("username");
			 //取出当前项+属性名+子项
			 System.out.println(element.getName()+element.attributeValue("id")+element1.getText());
		 }
 }
	 
	 
	//读取到指定的某个元素（跨层次关系获取）
	 public static void read_Xpath(){
		 //如果不引入x_path
		 //	 Element root = document.getRootElement();	
		 //  List<Element> list
		 //            =(List<Element>)root.elements("username");
		 //list.size()=0;结论是在没有引入xpath不能跨层次。
	 
	 }
	 
	//添加一个元素到xml文件中;可能要解决一个中文乱码的问题
	 public static void add(Document document) throws IOException{
		 
		 //首先要创建一个节点对象
		 //DocumentHelper帮助我们创建新的节点，文件等
		 //创建节点
		 Element newuser=DocumentHelper.createElement("user");
		 Element newusername=DocumentHelper.createElement("username");
		 Element newpassword=DocumentHelper.createElement("password");
		 Element newrole=DocumentHelper.createElement("role");
		 //添加层次关系,到内存中
		 newuser.add(newusername);
		 newuser.add(newpassword);
		 newuser.add(newrole);
		 document.getRootElement().add(newuser);
		 //把xml文件保存并且更新
		 write(document);
		 
 }
	 
	//把xml文件保存并且更新,并且按照合理的格式输出。
	 public static void write(Document document) throws IOException{
		 
		 //保存xml文件,使用Filewriter
//		 XMLWriter writer =new XMLWriter(new FileWriter("src/XML/Users.xml"));
//		 writer.write(document);
//		 writer.close();
		 
		 //用OutputFormat解决输出格式问题，FileOutputstream按字节读取，不容易乱码
		 
		 OutputFormat format = OutputFormat.createPrettyPrint();
		 format.setEncoding("utf-8");
		 XMLWriter writer =new XMLWriter(new FileOutputStream("src/XML/Users.xml"),format);
		 writer.write(document);
		 writer.close();
		 
		 //乱码出现的原因
		 //读取的源代码格式和java程序读取的时候的字符集不一样
		 //FileOutputStream它也有自己读取字符的方式
		 
}
	 
	 //删除元素
	 public static void del(Document document) throws IOException{
		 //找到该元素
		 Element user=document.getRootElement().element("user");
		 //删除
		 user.getParent().remove(user);
		 write(document);
		 
	 }
	 
	 //给节点添加或者修改文本
	 public static void updateTextEle(){
		 
		 
	 }
	 
//	 //测试泛型泛型参数的方法
//	 public static <T> void testReflectMethod(Class<T> obj,XMLFile xmlFile) throws ClassNotFoundException{
//		 
//		 //获取xml文件
//		 if(xmlFile.getXmlFile().exists()){
//		 xmlFile.getXml4j(xmlFile.getXmlFilepath());
//		 }else {
//			System.out.println("请勿在不存在的xml文件中添加节点。");
//		 }
//		 
//		//Element newuser=DocumentHelper.createElement("user");
//
//		// Class cla=Class.forName("DAO.User");		
//		 System.out.println("获取该类的对象的包名"+obj.getSimpleName());
//		 System.out.println("获取该类的对象的包名"+obj.getClass().getSimpleName());
//
//		//创建根节点
//		String a=obj.getSimpleName().toUpperCase();
//		Element e=DocumentHelper.createElement(a);
//		 Field[] properties = obj.getFields();//获得实例的属性
//		 for (int i = 0; i < properties.length;i++) {
//			
//			Field field = properties[i];
//			System.out.println("获取该类的属性名"+field.getName());
//			Element eson=DocumentHelper.createElement(field.getName().toUpperCase());
//			e.add(eson);
//		}
//			//添加层次关系,到内存中，添加根节点
//			xmlFile.getDocument().getRootElement().add(e);
//			
//			//保存文件
//			try {
//				xmlFile.saveXmlFile(xmlFile);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//		 System.out.println("获取该类的属性名");
//		 
//	 }
	
}
