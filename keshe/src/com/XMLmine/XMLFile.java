package com.XMLmine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLFile {
	
	
	private Document document=null;
	private String xmlFilepath=null;
	private File xmlFile=null;
	
	private XMLFile(Document document, String xmlFilepath, File xmlFile) {
		super();
		this.document = document;
		this.xmlFilepath = xmlFilepath;
		this.xmlFile = xmlFile;
	}
	
	
	//获得XML文件，没有就创建。
	public static XMLFile getXml4j(String xmlFilepath) {		
		XMLFile xmlFile=new XMLFile(null,xmlFilepath, null);	
    	//XML解释器
        SAXReader reader = new SAXReader();    
        xmlFile.setXmlFile(new File(xmlFile.getXmlFilepath()));
        if (!xmlFile.getXmlFile().exists()) {
        	try {
        		 //创建一个xml文档
        		xmlFile.setDocument(DocumentHelper.createDocument());
        		//保存XML
				xmlFile.saveXmlFile(xmlFile);		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		        //创建文件
			System.out.println("文件不存在！,现在创建。");			
		}
        else{
        try {
        	//获取文件
        	xmlFile.setDocument(reader.read(xmlFile.getXmlFile()));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
	}
        return xmlFile;
    }
	
	
	//保存对指定XML文件的修改
	public void saveXmlFile(XMLFile xmlFile) throws IOException{

 		 //保存xml文件,使用Filewriter
//		 XMLWriter writer =new XMLWriter(new FileWriter("src/XML/Users.xml"));
//		 writer.write(document);
//		 writer.close();	 
		 //用OutputFormat解决输出格式问题，FileOutputstream按字节读取，不容易乱码
		 
		 OutputFormat format = OutputFormat.createPrettyPrint();
		 format.setEncoding("utf-8");
		 XMLWriter writer =new XMLWriter(new FileOutputStream(xmlFile.getXmlFilepath()),format);
		 writer.write(xmlFile.getDocument());
		 writer.close();
		 
		 //乱码出现的原因
		 //读取的源代码格式和java程序读取的时候的字符集不一样
		 //FileOutputStream它也有自己读取字符的方式
	 }

	//在XML文档中添加节点
	
	//逻辑
			//1.获取xml文件，获取新添节点。
			//2.修改xml
			//3.保存XML文件

		//添加一个元素到xml文件中;可能要解决一个中文乱码的问题
	 //测试泛型泛型参数的方法
	 public <T> void addElement(Class<T> obj,XMLFile xmlFile,Map<String,String> map) throws ClassNotFoundException{
		 
		 map=new HashMap <>();			

		 
		 //获取xml文件
		 if(xmlFile.getXmlFile().exists()){
		 xmlFile.getXml4j(xmlFile.getXmlFilepath());
		 }else {
			System.out.println("请勿在不存在的xml文件中添加节点。");
		 }
		 
		//Element newuser=DocumentHelper.createElement("user");

		// Class cla=Class.forName("DAO.User");		
		 System.out.println("获取该类的对象的包名"+obj.getSimpleName());

		//创建根节点
		String a=obj.getSimpleName().toLowerCase();
		Element e=DocumentHelper.createElement(a);
		 Field[] properties = obj.getFields();//获得实例的属性
		 for (int i = 0; i < properties.length;i++) {	
			Field field = properties[i];
			System.out.println("获取该类的属性名"+field.getName());
			Element eson=DocumentHelper.createElement(field.getName().toLowerCase());
			try {
				
				eson.addText(map.get(field.getName().toLowerCase()));
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.add(eson);
		}
			//添加层次关系,到内存中，添加根节点
			xmlFile.getDocument().getRootElement().add(e);
			
			//保存文件
			try {
				xmlFile.saveXmlFile(xmlFile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		 System.out.println("获取该类的属性名");	 
	 }
	 
	 // 通用查查询
	 public int queryElement(XMLFile xmlFile,Map<Integer,String> map){
		 
		 if(map.size()==0){
			 return 0;
		 }else if (map.size()==1) {
			 List e=xmlFile.getDocument().selectNodes("//"+map.get(1));
			 for( int i=0;i<e.size();i++){
				 //当前项
				 Element element=(Element)e.get(i);
				 //打印查找元素名
				 System.out.println(element.getName());
				 //子项列表
				 List eson=element.selectNodes("/*");
				 //
				 for( int j=0;i<eson.size();i++){

					 Element elementson=(Element)e.get(i);
					 //打印查找元素名
					 System.out.println(elementson.getName());
				 }
			 }				 
		}
		 
		 return 0;
		 
	 }
	 
	
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public String getXmlFilepath() {
		return xmlFilepath;
	}
	public void setXmlFilepath(String xmlFilepath) {
		this.xmlFilepath = xmlFilepath;
	}
	public File getXmlFile() {
		return xmlFile;
	}
	public void setXmlFile(File xmlFile) {
		this.xmlFile = xmlFile;
	}

}
