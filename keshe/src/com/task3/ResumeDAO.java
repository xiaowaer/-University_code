
package com.task3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.authenticator.SavedRequest;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.BackedList;

import com.XMLmine.XMLFile;
import com.admin.User;
import com.task2.DBUtil;

public class ResumeDAO {

	
	public int add(ResumeBasicinfo basicinfo, int applicantID){
		
		XMLFile xmlFile=XMLFile.getXml4j("D:/prefessional/foundation/programming_languages/java/workspace/keshe/src/com/XMLmine/ResumeBasicinfo.xml");
		 
			 //首先要创建一个节点对象
			 //DocumentHelper帮助我们创建新的节点，文件等
			 //创建节点
			 Element newperson=DocumentHelper.createElement("person");
			 Element newbasicinfoid=DocumentHelper.createElement("basicinfoid");
			 newbasicinfoid.setText(String.valueOf(applicantID));
			 Element newname=DocumentHelper.createElement("name");
			 newname.setText(basicinfo.getName());	 
			 Element newphone=DocumentHelper.createElement("phone");
			 newphone.setText(basicinfo.getPhone());
			 Element newemail=DocumentHelper.createElement("email");
			 newemail.setText(basicinfo.getEmail());
			 Element newaddress=DocumentHelper.createElement("address");
			 newaddress.setText(basicinfo.getAddress());
			 Element newidcard=DocumentHelper.createElement("idcard");
			 newidcard.setText(basicinfo.getIdcard()); 
			 Element newbumen=DocumentHelper.createElement("bumen");
			 newbumen.setText(basicinfo.bumen);
			 Element newzhicheng=DocumentHelper.createElement("zhicheng");
			 newzhicheng.setText(basicinfo.getZhicheng());
			 Element newsex=DocumentHelper.createElement("sex");
			 newsex.setText(basicinfo.getSex());


			 //添加层次关系,到内存中
			 newperson.add(newbasicinfoid);
			 newperson.add(newname);
			 newperson.add(newphone);
			 newperson.add(newemail);
			 newperson.add(newaddress);
			 newperson.add(newidcard);
			 newperson.add(newbumen);
			 newperson.add( newzhicheng);
			 newperson.add(newsex);
			 
			 xmlFile.getDocument().getRootElement().add(newperson);
			 //把xml文件保存并且更新
			
				try {
					xmlFile.saveXmlFile(xmlFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return 1;
			}//update
//	
	/*
	 * 查询个人信息（By applicantID）
	 */
	
	public ResumeBasicinfo select(int applicantID){
		XMLFile xmlFile=XMLFile.getXml4j("D:/prefessional/foundation/programming_languages/java/workspace/keshe/src/com/XMLmine/ResumeBasicinfo.xml");
		
		int basicinfoid = applicantID;
		ResumeBasicinfo resume = null;
		 List<Element> e=xmlFile.getDocument().selectNodes("//"+"basicinfoid");

		 
		 System.out.println(e.size());
			
			for(int i=0;i<e.size();i++){
				System.out.println( e.get(i).getText()+"=? "+basicinfoid);
			if(e.get(i).getText().equals(String.valueOf(applicantID))){
				System.out.println("basicinfoid");	
			resume=new ResumeBasicinfo();
				
				Element okbasicinfoid=e.get(i);
				
			
				System.out.println("尼玛");	
				List<Element> ebroname=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::name");
				System.out.println("fuck");	
				
				for (int j = 0; j <ebroname.size(); j++) {
					
					System.out.println(j+"  "+ebroname.get(i).getText());	
				}
				
				List<Element> ebrophone=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::phone");
				List<Element> ebroemail=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::email");
				List<Element> ebroaddress=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::address");
				List<Element> ebroidcard=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::idcard");
				List<Element> ebrobumen=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::bumen");
				
	for (int j = 0; j <ebrobumen.size(); j++) {
					
					System.out.println(j+"  "+ebrobumen.get(i).getText());	
				}
				
				List<Element> ebrozhicheng=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::zhicheng");
				List<Element> ebrosex=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::sex");
				
	for (int j = 0; j <ebrosex.size(); j++) {
					
					System.out.println(j+"  "+ebrosex.get(i).getText());	
				}
				
				resume.setBasicinfoID(basicinfoid);
				resume.setName(ebroname.get(i).getText());
				resume.setPhone(ebrophone.get(i).getText());
				resume.setEmail(ebroemail.get(i).getText());
				resume.setAddress(ebroaddress.get(i).getText());
				resume.setIdcard(ebroidcard.get(i).getText());
				resume.setBumen(ebrobumen.get(i).getText());
				resume.setZhicheng(ebrozhicheng.get(i).getText());
				resume.setSex(ebrosex.get(i).getText());
				
				System.out.println(	resume.getZhicheng());	
			
				
				resume.toString();
				
			}
		
	}
			return resume;
	}
	
	
	
	public List<ResumeBasicinfo> selectAll(){
		
		List<ResumeBasicinfo> list = new ArrayList<ResumeBasicinfo>();
		
		
	XMLFile xmlFile=XMLFile.getXml4j("D:/prefessional/foundation/programming_languages/java/workspace/keshe/src/com/XMLmine/ResumeBasicinfo.xml");
		 List<Element> e=xmlFile.getDocument().selectNodes("//"+"basicinfoid");

			for(int i=0;i<e.size();i++){
				ResumeBasicinfo  resume=new ResumeBasicinfo();

				Element okbasicinfoid=e.get(i);
				List<Element> ebroname=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::name");
				List<Element> ebrophone=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::phone");
				List<Element> ebroemail=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::email");
				List<Element> ebroaddress=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::address");
				List<Element> ebroidcard=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::idcard");
				List<Element> ebrobumen=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::bumen");
				List<Element> ebrozhicheng=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::zhicheng");
				List<Element> ebrosex=okbasicinfoid.selectNodes("//basicinfoid//following-sibling::sex");

				resume.setBasicinfoID(Integer.parseInt(e.get(i).getText()));
				resume.setName(ebroname.get(i).getText());
				resume.setPhone(ebrophone.get(i).getText());
				resume.setEmail(ebroemail.get(i).getText());
				resume.setAddress(ebroaddress.get(i).getText());
				resume.setIdcard(ebroidcard.get(i).getText());
				resume.setBumen(ebrobumen.get(i).getText());
				resume.setZhicheng(ebrozhicheng.get(i).getText());
				resume.setSex(ebrosex.get(i).getText());
				list.add(resume);
	}
		
		
		
		return list;
	}
	


	/*
	 * 更新个人信息
	 */
	public int update(ResumeBasicinfo basicinfo, int applicantID){
		
	//先删除
	//后插入
this.delete(applicantID);
this.add(basicinfo, applicantID);

return 1;

	}//update
	
	public int delete(int id){
//		1.获取文件
		XMLFile xmlFile=XMLFile.getXml4j("D:/prefessional/foundation/programming_languages/java/workspace/keshe/src/com/XMLmine/ResumeBasicinfo.xml");

		//2.删除指定的元素

		int basicinfoid = id;
		 List<Element> e=xmlFile.getDocument().selectNodes("//"+"basicinfoid");

		 System.out.println(e.size());
			
			for(int i=0;i<e.size();i++){
				System.out.println( e.get(i).getText()+"=? "+basicinfoid);
			if(e.get(i).getText().equals(String.valueOf(id))){
				System.out.println("basicinfoid");		
				Element okbasicinfoid=e.get(i);
				e.get(i).getParent().getParent().remove(e.get(i).getParent());
				
			}
		
	}
	
		//3.保存文件
		try {
			xmlFile.saveXmlFile(xmlFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 1;
				
	}
}
